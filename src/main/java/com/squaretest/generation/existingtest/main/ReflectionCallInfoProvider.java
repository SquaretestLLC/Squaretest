/*
 * Copyright 2026 Squaretest LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squaretest.generation.existingtest.main;

import com.intellij.psi.JavaRecursiveElementWalkingVisitor;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiConstantEvaluationHelper;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiLocalVariable;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiVariable;
import com.intellij.psi.util.InheritanceUtil;
import com.intellij.psi.util.PsiUtil;
import com.squaretest.generation.InitStrategy;
import com.squaretest.generation.PsiToTemplateVarsMapper;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.existingtest.common.Constants;
import com.squaretest.generation.existingtest.common.MemberField;
import com.squaretest.generation.existingtest.common.RefInfo;
import com.squaretest.generation.existingtest.common.RefType;
import com.squaretest.generation.existingtest.common.VariableInfo;
import com.squaretest.template.api.Api;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.plugins.groovy.lang.psi.GroovyPsiElement;
import org.jetbrains.plugins.groovy.lang.psi.GroovyRecursiveElementVisitor;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.GrField;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.GrVariable;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.expressions.GrExpression;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.expressions.GrReferenceExpression;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.expressions.path.GrMethodCallExpression;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.squaretest.generation.existingtest.main.ExistingInfoPopulator.findSetupMethod;
import static com.squaretest.generation.existingtest.main.Utils.isMocked;

public class ReflectionCallInfoProvider {
    @NotNull
    private final PsiConstantEvaluationHelper psiConstantEvaluationHelper;
    @NotNull
    private final PsiToTemplateVarsMapper psiToTemplateVarsMapper;
    @NotNull
    private final PsiClass sourceClass;
    @NotNull
    private final PsiClass testClass;
    @NotNull
    private final List<MemberField> memberFields;

    public ReflectionCallInfoProvider(
            @NotNull final PsiConstantEvaluationHelper psiConstantEvaluationHelper, @NotNull final PsiToTemplateVarsMapper psiToTemplateVarsMapper,
            @NotNull final PsiClass sourceClass, @NotNull final PsiClass testClass,
            @NotNull final List<MemberField> memberFields) {
        this.psiConstantEvaluationHelper = psiConstantEvaluationHelper;
        this.psiToTemplateVarsMapper = psiToTemplateVarsMapper;
        this.sourceClass = sourceClass;
        this.testClass = testClass;
        this.memberFields = memberFields;
    }


    @Nullable
    public Map<Api.Variable, VariableInfo> computeReflectionInfo() {
        final Map<Api.Variable, VariableInfo> variableInfoMap = new IdentityHashMap<>();
        final PsiMethod setupMethod = findSetupMethod(testClass);
        if(setupMethod != null) {
            // Add info from the setup method first.
            addRefelectionInfo(setupMethod, variableInfoMap);
        }
        // Add info from the rest of the file. This will not overwrite info added for the setup method.
        addRefelectionInfo(testClass, variableInfoMap);
        return variableInfoMap;
    }

    private void addRefelectionInfo(final PsiElement element, final Map<Api.Variable, VariableInfo> variableInfoMap) {
        if(element == null) {
            return;
        }
        if(element instanceof final GroovyPsiElement groovyPsiElement) {
            groovyPsiElement.accept(new GroovyRecursiveElementVisitor() {

                @Override
                public void visitMethodCallExpression(@NotNull final GrMethodCallExpression methodCallExpression) {
                    super.visitMethodCallExpression(methodCallExpression);
                    final PsiMethod psiMethod = methodCallExpression.resolveMethod();
                    if(psiMethod == null) {
                        return;
                    }

                    final GroovyReflectionCallInfo reflectionCallInfo = computeReflectionCallInfo(psiMethod, methodCallExpression);
                    if(reflectionCallInfo == null) {
                        return;
                    }

                    final GrExpression objectParamExpression = reflectionCallInfo.objectParamExpression();
                    // Ensure the object param is a reference to a local variable or member variable whose type is the source class's type.
                    if(!(objectParamExpression instanceof final GrReferenceExpression objectParamRef)) {
                        return;
                    }
                    final PsiElement objectParamRefTarget = objectParamRef.resolve();
                    if(!(objectParamRefTarget instanceof final PsiVariable objectParamRefVariable)) {
                        return;
                    }
                    final PsiType type = objectParamRefVariable.getType();
                    if(!isSourceClassType(type)) {
                        return;
                    }

                    // Get the name param. Ensure the sourceClass has a field with that name. Call sourceClass.findFieldWithName(..).
                    final GrExpression nameParamExpression = reflectionCallInfo.nameParamExpression();
                    final String nameParamValue = getStringConstantValue(nameParamExpression);
                    if(nameParamValue == null) {
                        return;
                    }
                    final PsiField psiFieldBeingSet = sourceClass.findFieldByName(nameParamValue, true);
                    if(psiFieldBeingSet == null) {
                        return;
                    }
                    final Api.Variable apiFieldBeingSet = psiToTemplateVarsMapper.getVariable(psiFieldBeingSet, InitStrategy.Default);
                    if(variableInfoMap.containsKey(apiFieldBeingSet)) {
                        return;
                    }

                    // Get the value param. use computeVariableInfo(..) to determine if it is mocked.
                    final GrExpression valueParamExpression = reflectionCallInfo.valueParamExpression();
                    final VariableInfo variableInfo = computeVariableInfo(valueParamExpression);
                    variableInfoMap.put(apiFieldBeingSet, variableInfo);
                }
            });
        } else {
            element.accept(new JavaRecursiveElementWalkingVisitor() {
                @Override
                public void visitMethodCallExpression(@NotNull final PsiMethodCallExpression methodCallExpression) {
                    super.visitMethodCallExpression(methodCallExpression);
                    final PsiMethod psiMethod = methodCallExpression.resolveMethod();
                    if(psiMethod == null) {
                        return;
                    }
                    final Api.Method method = psiToTemplateVarsMapper.getMethod(psiMethod, InitStrategy.Default);
                    if(method == null) {
                        return;
                    }
                    if(!method.isSetter() || method.getTargetField() == null) {
                        return;
                    }
                    final Api.Variable sourceClassMember = method.getParameters().first();
                    if(sourceClassMember == null) {
                        return;
                    }
                    if(variableInfoMap.containsKey(sourceClassMember)) {
                        return;
                    }
                    final PsiExpression[] args = methodCallExpression.getArgumentList().getExpressions();
                    if(args.length == 0) {
                        return;
                    }
                    final VariableInfo variableInfo = computeVariableInfo(args[0]);
                    variableInfoMap.put(sourceClassMember, variableInfo);
                }
            });
        }
    }

    private String getStringConstantValue(final GrExpression nameParamExpression) {
        final Object constantValue = psiConstantEvaluationHelper.computeConstantExpression(nameParamExpression);
        if(!(constantValue instanceof String)) {
            return null;
        }
        final String strConstant = StringUtils.trim((String) constantValue);
        if(StringUtils.isBlank(strConstant)) {
            return null;
        }
        return strConstant;
    }

    private boolean isSourceClassType(final PsiType type) {
        final PsiClass typeClass = PsiUtil.resolveClassInType(type);
        if(typeClass == null) {
            return false;
        }
        return InheritanceUtil.isInheritorOrSelf(typeClass, sourceClass, true);
    }

    private GroovyReflectionCallInfo computeReflectionCallInfo(
            @NotNull final PsiMethod psiMethod, @NotNull final GrMethodCallExpression methodCallExpression) {
        if(!isReflectionTestUtilsSetFieldMethod(psiMethod)) {
            return null;
        }
        final PsiParameter[] params = psiMethod.getParameterList().getParameters();
        final GrExpression[] expressionArguments = methodCallExpression.getArgumentList().getExpressionArguments();
        GrExpression firstObjectParam = null;
        GrExpression firstStringParam = null;
        GrExpression secondObjectParam = null;
        for(int i = 0; i < params.length && i < expressionArguments.length; i++) {
            final String canonicalText = params[i].getType().getCanonicalText();
            if(canonicalText.equals(JavaNames.JavaLangObject)) {
                if(firstObjectParam == null) {
                    firstObjectParam = expressionArguments[i];
                } else if(secondObjectParam == null) {
                    secondObjectParam = expressionArguments[i];
                } else {
                    // There shouldn't be a third param with Object.
                    return null;
                }
                continue;
            }
            if(canonicalText.equals(JavaNames.JavaLangString)) {
                if(firstStringParam == null) {
                    firstStringParam = expressionArguments[i];
                }
                continue;
            }
        }
        if(firstObjectParam == null || secondObjectParam == null || firstStringParam == null) {
            return null;
        }
        return new GroovyReflectionCallInfo(firstObjectParam, firstStringParam, secondObjectParam);
    }

    private boolean isReflectionTestUtilsSetFieldMethod(final PsiMethod psiMethod) {
        final PsiClass containingClass = psiMethod.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        if(!StringUtils.equals(containingClass.getQualifiedName(), "org.springframework.test.util.ReflectionTestUtils")) {
            return false;
        }
        if(!psiMethod.getName().equals("setField")) {
            return false;
        }
        if(!psiMethod.hasModifierProperty(PsiModifier.STATIC)) {
            return false;
        }
        if(psiMethod.hasModifierProperty(PsiModifier.PRIVATE)) {
            return false;
        }
        return true;
    }

    @NotNull
    private VariableInfo computeVariableInfo(final PsiElement rightExpression) {
        if(rightExpression instanceof final PsiReference rightReferenceExp) {
            // If this points to a field, check to see if the field is mocked.
            final PsiElement resolvedElement = rightReferenceExp.resolve();
            if(resolvedElement instanceof final PsiField resolvedField) {
                final MemberField member = getMemberFieldForPsiFieldInTestClass(resolvedField);
                if(member != null) {
                    return new VariableInfo(new RefInfo(member.name(), RefType.TestClassMember), member.isMock(), "null", true);
                }
            }
            if(resolvedElement instanceof final GrVariable grLocalVariable && !(resolvedElement instanceof GrField)) {
                // We have a GrVariable that is not a field. Assume it is a local variable.
                final boolean isMock = isMocked(grLocalVariable);
                return new VariableInfo(new RefInfo(grLocalVariable.getName(), RefType.LocalVariable), isMock, "null", true);
            }
            if(resolvedElement instanceof final PsiLocalVariable psiLocalVariable) {
                final boolean isMock = isMocked(psiLocalVariable);
                return new VariableInfo(new RefInfo(psiLocalVariable.getName(), RefType.LocalVariable), isMock, "null", true);
            }
        } else if(rightExpression instanceof final PsiMethodCallExpression rightMethodCallExp) {
            // Otherwise, check to see if it is a methodCallExpression and it is a mock; e.g. "mock(Service.class)";
            final PsiMethod calledMethod = rightMethodCallExp.resolveMethod();
            if((calledMethod != null && Objects.equals(Constants.MockMethodName, calledMethod.getName())) || (Objects.equals(Constants.MockMethodName, rightMethodCallExp.getMethodExpression().getReferenceName()))) {
                // If the called method has name: MockMethodName or the called method couldn't be resolved but the reference name is MockMethodName (happens when Mockito isn't setup correctly in the IDEA project),
                // assume the field is set to a mock value.
                return new VariableInfo(null, true, "null", false);
            }
            // We called some other method that isn't a mock.
            return new VariableInfo(null, false, "null", false);
        } else if(rightExpression instanceof final GrMethodCallExpression rightMethodCallExp) {
            // Otherwise, check to see if it is a methodCallExpression and it is a mock; e.g. "mock(Service.class)";
            final PsiMethod calledMethod = rightMethodCallExp.resolveMethod();
            if((calledMethod != null && Objects.equals(Constants.MockMethodName, calledMethod.getName())) || (Objects.equals(Constants.MockMethodName, rightMethodCallExp.getInvokedExpression().getText()))) {
                // If the called method has name: MockMethodName or the called method couldn't be resolved but the reference name is MockMethodName (happens when Mockito isn't setup correctly in the IDEA project),
                // assume the field is set to a mock value.
                return new VariableInfo(null, true, "null", false);
            }
            // We called some other method that isn't a mock.
            return new VariableInfo(null, false, "null", false);
        } else {
            // Otherwise, assume its a value. set shouldBeMocked(false).
            return new VariableInfo(null, false, "null", false);
        }

        // Its not a method call expression or a reference to a field.
        return new VariableInfo(null, false, "null", false);
    }

    @Nullable
    private MemberField getMemberFieldForPsiFieldInTestClass(final PsiField resolvedField) {
        if(resolvedField.getContainingClass() != testClass) {
            return null;
        }
        for(final MemberField memberField : memberFields) {
            if(memberField.name().equals(resolvedField.getName())) {
                return memberField;
            }
        }
        return null;
    }
}
