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
package com.squaretest.generation.dependencyinteraction;

import com.intellij.psi.*;
import com.intellij.util.ThreeState;
import com.squaretest.generation.CompiledUtils;
import com.squaretest.generation.dataflow.ValidationMethodUtils;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.defaulttypes.beans.BeanInfoProvider;
import com.squaretest.utils.IdentityMultimapWithIdentitySets;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

import static com.squaretest.annotations.AnnotationCreator.getAnnotationName;
import static com.squaretest.generation.CompiledUtils.getMethodWithSourceCode;

public class FieldToSourceVariableCollectorImpl {

    private static final String[] LombokAnnotationNames = new String[]{"RequiredArgsConstructor", "AllArgsConstructor", "Data", "Builder"};
    private static final String[] LombokAnnotationCanonicalNames = new String[]{"lombok.RequiredArgsConstructor", "lombok.AllArgsConstructor", "lombok.Data", "lombok.Value", "lombok.Builder"};

    private final BeanInfoProvider beanInfoProvider;

    private final PsiClass topLevelClass;
    private final boolean classHasLombokAnnotation;
    private final Set<PsiClass> superClasses;
    private final Set<PsiFile> superClassContainingFileSet;

    public FieldToSourceVariableCollectorImpl(final BeanInfoProvider beanInfoProvider, final PsiClass psiClass) {
        this.beanInfoProvider = beanInfoProvider;
        this.topLevelClass = psiClass;
        this.classHasLombokAnnotation = determineIfClassHasLombokAnnotation(psiClass);
        this.superClasses = DependencyInteractionCollectorUtils.computeSuperClasses(psiClass);
        this.superClassContainingFileSet = DependencyInteractionCollectorUtils.computeSuperClassFileSet(psiClass, superClasses);
    }

    private static boolean determineIfClassHasLombokAnnotation(final PsiClass psiClass) {
        for(final PsiAnnotation annotation : psiClass.getAnnotations()) {
            final String annotationName = getAnnotationName(annotation);
            if(StringUtils.equalsAny(annotationName, LombokAnnotationNames) || StringUtils.equalsAny(annotation.getQualifiedName(), LombokAnnotationCanonicalNames)) {
                return true;
            }
        }
        return false;
    }

    public IdentityMultimapWithIdentitySets<PsiField, PsiVariable> computeSourceVariablesForMembers() {
        final IdentityMultimapWithIdentitySets<PsiField, PsiVariable> ret = new IdentityMultimapWithIdentitySets<>();
        addAllFieldsToMap(ret);
        if(topLevelClass.isRecord()) {
            computeSourceVarsForRecord(ret);
            return ret;
        }
        computeSourceVarsFromConstructors(ret);
        computeSourceVarsFromSetterMethods(ret);
        return ret;
    }

    private void computeSourceVarsForRecord(final IdentityMultimapWithIdentitySets<PsiField, PsiVariable> ret) {
        for(final PsiMethod constructor : topLevelClass.getConstructors()) {
            for(final PsiParameter param : constructor.getParameterList().getParameters()) {
                final PsiField field = topLevelClass.findFieldByName(param.getName(), false);
                if(field != null) {
                    ret.put(field, param);
                }
            }
        }
    }

    private void computeSourceVarsFromConstructors(final IdentityMultimapWithIdentitySets<PsiField, PsiVariable> ret) {
        final Set<PsiMethod> traversalStack = SetUtils.newIdentityHashSet();
        final IdentityMultimapWithIdentitySets<PsiParameter, PsiParameter> paramsFromOtherConstructors = new IdentityMultimapWithIdentitySets<>();

        for(final PsiMethod method : topLevelClass.getConstructors()) {
            if(classHasLombokAnnotation && StringUtils.contains(method.getClass().getCanonicalName(), JavaNames.LombokLightMethodName)) {
                computeSourceVarsFromLombokConstructor(method, ret);
            } else {
                computeSourceVarsFromConstructor(method, traversalStack, paramsFromOtherConstructors, ret);
            }
        }
    }

    private void computeSourceVarsFromLombokConstructor(final PsiMethod psiMethod, final IdentityMultimapWithIdentitySets<PsiField, PsiVariable> ret) {
        if(psiMethod.hasModifierProperty(PsiModifier.STATIC)) {
            return;
        }
        final PsiCodeBlock methodBody = psiMethod.getBody();
        if(methodBody == null) {
            return;
        }
        methodBody.accept(new JavaRecursiveElementWalkingVisitor() {
            @Override
            public void visitAssignmentExpression(@NotNull final PsiAssignmentExpression expression) {
                super.visitAssignmentExpression(expression);
                if(!JavaTokenType.EQ.equals(expression.getOperationTokenType())) {
                    return;
                }

                final PsiField leftField = getFieldThisExpressionResolvesTo(expression.getLExpression());
                if(leftField != null) {
                    PsiParameter rightParam = getFormalParamThisArgumentRefersTo(expression.getRExpression());
                    if(rightParam == null) {
                        // This is the reason we need to special case lombok constructors.
                        // The right side of the assignment expression fails to resolve to the method parameter in
                        // lombok generated constructors. I believe the root cause is: https://github.com/mplushnikov/lombok-intellij-plugin/issues/391.
                        // We will resolve the parameter ourselves by finding the method parameter with the same name
                        // as the right side's reference name.
                        rightParam = tryFindMatchingMethodParam(expression.getRExpression(), psiMethod);
                    }
                    if(rightParam != null) {
                        // Store leftField -> rightParam
                        ret.put(leftField, rightParam);
                    }
                }
            }
        });
    }

    private static PsiParameter tryFindMatchingMethodParam(final PsiExpression rightExpression, final PsiMethod psiMethod) {
        if(rightExpression instanceof final PsiReferenceExpression rightReferenceExp) {
            String nameToUse = rightReferenceExp.getReferenceName();
            if(nameToUse == null) {
                nameToUse = rightReferenceExp.getText();
            }
            for(final PsiParameter psiParameter : psiMethod.getParameterList().getParameters()) {
                if(nameToUse != null && StringUtils.equals(psiParameter.getName(), nameToUse)) {
                    return psiParameter;
                }
            }
        }
        return null;
    }

    private void computeSourceVarsFromConstructor(
            final PsiMethod psiMethod,
            final Set<PsiMethod> traversalStack,
            final IdentityMultimapWithIdentitySets<PsiParameter, PsiParameter> paramsFromOtherConstructors,
            final IdentityMultimapWithIdentitySets<PsiField, PsiVariable> ret) {
        if(psiMethod.hasModifierProperty(PsiModifier.STATIC)) {
            return;
        }
        final PsiCodeBlock methodBody = psiMethod.getBody();
        if(methodBody == null) {
            return;
        }
        // Visit all method-call expressions in this method.
        methodBody.accept(new JavaRecursiveElementWalkingVisitor() {
            @Override
            public void visitMethodCallExpression(@NotNull final PsiMethodCallExpression methodCallExpression) {
                super.visitMethodCallExpression(methodCallExpression);

                // If this is a no-arg method, don't follow it.
                if(methodCallExpression.getArgumentList().getExpressions().length == 0) {
                    return;
                }

                final PsiMethod methodToCheckRecursivly = getMethodToCheckRecursivelyIfPresent(methodCallExpression);
                if(methodToCheckRecursivly != null) {
                    // Build the paramsFromOtherConstructors map to link our parameters to the parameters in
                    // the method call.
                    // Resolve each expression.
                    final IdentityMultimapWithIdentitySets<PsiParameter, PsiParameter> paramsFromUs = new IdentityMultimapWithIdentitySets<>();
                    final PsiExpression[] argumentExpressions = methodCallExpression.getArgumentList().getExpressions();
                    final PsiParameter[] formalParams = methodToCheckRecursivly.getParameterList().getParameters();
                    if(formalParams.length != argumentExpressions.length) {
                        // This shouldn't happen. If this happens, there is a syntax error in the method-call.
                        return;
                    }

                    boolean hasAtLeastOneFormalArgInCallExpression = false;
                    for(int i = 0; i < argumentExpressions.length; i++) {
                        final PsiParameter ourFormalParam = getFormalParamThisArgumentRefersTo(argumentExpressions[i]);
                        if(ourFormalParam != null) {
                            hasAtLeastOneFormalArgInCallExpression = true;
                            // We are calling methodToCheckRecursivly() and the value of parameter[i] is a reference
                            // to one of our own formal-parameters.
                            paramsFromUs.put(formalParams[i], ourFormalParam);
                            paramsFromUs.putAll(formalParams[i], paramsFromOtherConstructors.get(ourFormalParam));
                        }
                    }

                    if(!hasAtLeastOneFormalArgInCallExpression) {
                        return;
                    }
                    if(methodToCheckRecursivly == psiMethod || traversalStack.contains(methodToCheckRecursivly)) {
                        return;
                    }

                    traversalStack.add(psiMethod);
                    computeSourceVarsFromConstructor(methodToCheckRecursivly, traversalStack, paramsFromUs, ret);
                    traversalStack.remove(psiMethod);
                }
            }

            @Override
            public void visitAssignmentExpression(@NotNull final PsiAssignmentExpression expression) {
                super.visitAssignmentExpression(expression);
                if(!JavaTokenType.EQ.equals(expression.getOperationTokenType())) {
                    return;
                }

                final PsiField leftField = getFieldThisExpressionResolvesTo(expression.getLExpression());
                if(leftField != null) {
                    final PsiParameter rightParam = getFormalParamThisArgumentRefersTo(expression.getRExpression());
                    if(rightParam != null) {
                        // Store leftField -> rightParam
                        // Store leftField -> paramsFromOtherConstructors.get(rightParam).
                        ret.put(leftField, rightParam);
                        ret.putAll(leftField, paramsFromOtherConstructors.get(rightParam));
                    }
                }
            }
        });
    }

    @Nullable
    private PsiParameter getFormalParamThisArgumentRefersTo(final PsiExpression rightExpression) {
        final PsiExpression expressionToUse = removeValidationCalls(rightExpression);
        if(expressionToUse instanceof final PsiReferenceExpression rightReferenceExp) {
            final PsiElement rightElement = rightReferenceExp.resolve();
            if(rightElement instanceof PsiParameter) {
                return (PsiParameter) rightElement;
            }
        }
        return null;
    }

    private static PsiExpression removeValidationCalls(final PsiExpression psiExpression) {
        PsiExpression expressionToUse = ValidationMethodUtils.getExpressionWithinValidationMethod(psiExpression);
        if(expressionToUse != null) {
            return expressionToUse;
        }
        return psiExpression;
    }

    @Nullable
    private PsiField getFieldThisExpressionResolvesTo(final PsiExpression leftExpression) {
        if(leftExpression instanceof final PsiReferenceExpression leftReferenceExp) {
            final PsiElement leftField = leftReferenceExp.resolve();
            if(leftField instanceof PsiField) {
                return CompiledUtils.getFieldWithSourceCode((PsiField) leftField);
            }
        }
        return null;
    }

    @Nullable
    private PsiMethod getMethodToCheckRecursivelyIfPresent(final PsiMethodCallExpression methodCallExpression) {
        final PsiMethod calledMethod = getMethodWithSourceCode(methodCallExpression.resolveMethod());
        if(calledMethod == null) {
            return null;
        }

        // Only check this method if it's defined in this file or its super types.
        if(superClassContainingFileSet.contains(calledMethod.getContainingFile()) && !calledMethod.hasModifierProperty(PsiModifier.STATIC)) {
            return calledMethod;
        }
        return null;
    }

    private void computeSourceVarsFromSetterMethods(final IdentityMultimapWithIdentitySets<PsiField, PsiVariable> ret) {
        computeSourceVarsFromSetterMethods(topLevelClass, ret);
        for(final PsiClass superClass : superClasses) {
            computeSourceVarsFromSetterMethods(superClass, ret);
        }
    }

    private void computeSourceVarsFromSetterMethods(final PsiClass psiClass, final IdentityMultimapWithIdentitySets<PsiField, PsiVariable> ret) {
        for(final PsiMethod method : psiClass.getMethods()) {
            if(!beanInfoProvider.isSetter(method)) {
                continue;
            }
            final PsiField targetField = beanInfoProvider.getFieldForSetter(method);
            if(targetField == null) {
                continue;
            }
            if(beanInfoProvider.getConfirmedSetterStatusByCode(method) == ThreeState.NO) {
                continue;
            }
            ret.put(targetField, method.getParameterList().getParameters()[0]);
        }
    }

    private void addAllFieldsToMap(final IdentityMultimapWithIdentitySets<PsiField, PsiVariable> ret) {
        for(final PsiField field : topLevelClass.getFields()) {
            ret.put(field, field);
        }
        for(final PsiClass superClass : superClasses) {
            for(final PsiField field : superClass.getFields()) {
                ret.put(field, field);
            }
        }
    }
}
