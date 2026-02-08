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

import com.intellij.psi.PsiCapturedWildcardType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypes;
import com.squaretest.generation.InitStrategy;
import com.squaretest.generation.NoOpPsiToTemplateVarsMapper;
import com.squaretest.generation.PsiMethodToMethodConverter;
import com.squaretest.generation.PsiToTemplateVarsMapper;
import com.squaretest.generation.defaulttypes.TypeCreator;
import com.squaretest.generation.defaulttypes.TypeCreatorUtil;
import com.squaretest.generation.dependencyinteraction.outcomes.BooleanInfo;
import com.squaretest.generation.dependencyinteraction.outcomes.MethodAndDi;
import com.squaretest.generation.dependencyinteraction.outcomes.OutcomeKeyUtils;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.ArgumentExpressionImpl;
import com.squaretest.template.impl.DependencyInteractionImpl;
import com.squaretest.template.impl.FluentListImpl;
import com.squaretest.template.impl.MethodCallExpressionImpl;
import com.squaretest.template.impl.MethodImpl;
import com.squaretest.template.impl.TypeImpl;
import com.squaretest.template.impl.VariableImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.intellij.psi.util.TypeConversionUtil.isNullType;

public class DependencyInteractionPopulator {
    private final PsiToTemplateVarsMapper noOpTemplateVarsMapper;
    private final PsiToTemplateVarsMapper sourceClassPsiToTemplateVarsMapper;
    private final PsiMethodToMethodConverter psiMethodToMethodConverter;
    private final TypeCreator typeCreator;
    private final FieldsWithSameSourceVarProvider fieldsWithSameSourceVarProvider;
    private final DependencyFlowInfoProvider dependencyFlowInfoProvider;

    public DependencyInteractionPopulator(
            final PsiToTemplateVarsMapper sourceClassPsiToTemplateVarsMapper,
            final PsiMethodToMethodConverter psiMethodToMethodConverter, final TypeCreator typeCreator,
            final FieldsWithSameSourceVarProvider fieldsWithSameSourceVarProvider, final DependencyFlowInfoProvider dependencyFlowInfoProvider) {
        this.sourceClassPsiToTemplateVarsMapper = sourceClassPsiToTemplateVarsMapper;
        this.psiMethodToMethodConverter = psiMethodToMethodConverter;
        this.typeCreator = typeCreator;
        this.fieldsWithSameSourceVarProvider = fieldsWithSameSourceVarProvider;
        this.dependencyFlowInfoProvider = dependencyFlowInfoProvider;
        this.noOpTemplateVarsMapper = new NoOpPsiToTemplateVarsMapper();
    }

    public void populateDependencies() {
        final Map<PsiMethod, DependencyInteractionSet> dependencyInteractionMap = dependencyFlowInfoProvider.computeMethodToPsiMap();
        for(final Map.Entry<PsiMethod, DependencyInteractionSet> interactionsForMethod : dependencyInteractionMap.entrySet()) {
            final PsiMethod sourceMethod = interactionsForMethod.getKey();
            final Api.Method methodImpl = sourceClassPsiToTemplateVarsMapper.getMethod(sourceMethod, InitStrategy.Default);
            if(methodImpl == null) {
                // This can happen if the dependency method is in an inner class.
                continue;
            }
            final Set<Api.DependencyInteraction> dependencyInteractions = createDependencyInteractions(sourceMethod, interactionsForMethod.getValue());
            ((MethodImpl) methodImpl).addDependencyInteractions(dependencyInteractions);
        }
    }

    private Set<Api.DependencyInteraction> createDependencyInteractions(final PsiMethod sourceMethod, final DependencyInteractionSet interactionsForMethod) {
        final Set<Api.DependencyInteraction> ret = new LinkedHashSet<>();
        for(final InternalDependencyInteraction internalDependencyInteraction : interactionsForMethod) {
            final PsiField diField = internalDependencyInteraction.getPsiField();
            final Api.ClassMember dependency = (Api.ClassMember) sourceClassPsiToTemplateVarsMapper.getVariable(diField, InitStrategy.Default);
            if(dependency == null) {
                // This can happen if the dependency is in an inner class.
                continue;
            }
            final Api.Method method = createMethodForDI(internalDependencyInteraction.getPsiMethod(), getAdjustedType(internalDependencyInteraction.getInternalMethodCallExpression().type()), internalDependencyInteraction.getInternalMethodCallExpression().methodCallExpression());
            final Api.MethodCallExpression methodCallExpression = createMethodCallExpression(method, internalDependencyInteraction.getInternalMethodCallExpression());
            final String canonicalKey = TypeCreatorUtil.getCanonicalKey(internalDependencyInteraction.getPsiMethod());
            updateMethodParamsWithCallArgsWhenNeeded(method, methodCallExpression);
            final Set<PsiField> fieldsWithSameDep = fieldsWithSameSourceVarProvider.getFieldsWithSameSourceVar(diField);
            final Set<String> diKeys = OutcomeKeyUtils.convertToDiKeys(internalDependencyInteraction, fieldsWithSameDep);
            updateReturnValueBooleanInitInfo(method, sourceMethod, internalDependencyInteraction);
            ret.add(new DependencyInteractionImpl(dependency, method, diKeys, methodCallExpression, canonicalKey, internalDependencyInteraction.isReturnValueIgnored()));
        }
        return ret;
    }

    private void updateReturnValueBooleanInitInfo(final Api.Method method, final PsiMethod sourceMethod, final InternalDependencyInteraction internalDependencyInteraction) {
        final Map<MethodAndDi, BooleanInfo> booleanInfoMap = dependencyFlowInfoProvider.getBooleanInfoMap();
        final MethodAndDi key = new MethodAndDi(sourceMethod, internalDependencyInteraction);
        final BooleanInfo booleanInfo = booleanInfoMap.get(key);
        if(booleanInfo == null) {
            return;
        }
        final TypeImpl returnType = (TypeImpl) method.getReturnType();
        if(returnType == null) {
            return;
        }
        if(!StringUtils.equalsAny(returnType.getCanonicalName(), "boolean", "java.lang.Boolean")) {
            return;
        }
        if(booleanInfo == BooleanInfo.TrueIsNormal) {
            returnType.setInitExpression("true");
            returnType.setDefaultInitExpression("true");
            returnType.setFailureInitExpression("false");
            return;
        }
        if(booleanInfo == BooleanInfo.FalseIsNormal) {
            returnType.setInitExpression("false");
            returnType.setDefaultInitExpression("false");
            returnType.setFailureInitExpression("true");
        }
    }

    private static void updateMethodParamsWithCallArgsWhenNeeded(final Api.Method method, final Api.MethodCallExpression methodCallExpression) {
        for(int i = 0; i < method.getParameters().size(); i++) {
            final Api.Variable formalParam = method.getParameters().get(i);
            final Api.Type formalParamType = formalParam.getType();
            final Api.Type actualParamType = ((ArgumentExpressionImpl) methodCallExpression.getArguments().get(i)).getInternalActualType();
            if(formalParamType == actualParamType) {
                // The formal type and actual type will point to the same object (the formal type) if the actual type is
                // 1. missing (the method call expression is incomplete).
                // 2. null (a single lexical token that is the word: null).
                // 3. a lambda expression or method reference expression.
                continue;
            }

            if(formalParamType.isClassT()) {
                ((VariableImpl) formalParam).setType((TypeImpl) actualParamType);
                continue;
            }
            if(formalParamType.isGeneric() && !actualParamType.isGeneric()) {
                ((VariableImpl) formalParam).setType((TypeImpl) actualParamType);
                continue;
            }
            final boolean canUseActualType = actualParamType.getCanonicalName() != null
                    && (!actualParamType.isNested() || (actualParamType.isNested() && actualParamType.isStatic() && !actualParamType.isPrivate()));
            if(!canUseActualType) {
                continue;
            }
            if(formalParamType.is("java.lang.Throwable")) {
                ((VariableImpl) formalParam).setType((TypeImpl) actualParamType);
                continue;
            }
            if(hasAnyNestedGeneric(formalParamType) && !hasAnyNestedGeneric(actualParamType)) {
                ((VariableImpl) formalParam).setType((TypeImpl) actualParamType);
                continue;
            }
            if(formalParamType.isPrimitive()) {
                continue;
            }
            final boolean actualTypeHasPreferentialEqualsStatus = !formalParamType.getOverridesEquals() && (actualParamType.getOverridesEquals() || actualParamType.isPrimitive());
            if((formalParamType.isInterface() || formalParamType.isAbstract() || formalParamType.getParameters().isEmpty()) && !actualTypeHasPreferentialEqualsStatus) {
                continue;
            }
            ((VariableImpl) formalParam).setType((TypeImpl) actualParamType);
        }
    }

    private static boolean hasAnyNestedGeneric(final Api.Type formalParamType) {
        if(formalParamType.isClassT()) {
            return false;
        }
        for(final Api.Type type : formalParamType.getParameters()) {
            if(type.isGeneric()) {
                return true;
            }
            if(hasAnyNestedGeneric(type)) {
                return true;
            }
        }
        return false;
    }

    private Api.MethodCallExpression createMethodCallExpression(final Api.Method method, final InternalMethodCallExpression methodCallExpression) {
        final FluentListImpl<Api.ArgumentExpression> argumentExpressions = new FluentListImpl<>(method.getParameters().size());
        final List<Api.Variable> formalParams = method.getParameters();
        final List<PsiType> actualParams = methodCallExpression.paramTypes();
        final PsiExpression[] psiArgumentExpressions = methodCallExpression.getArgumentExpressions();
        for(int i = 0; i < formalParams.size(); i++) {
            final Api.Variable formalParam = formalParams.get(i);
            final Api.Type formalParamType = formalParam.getType();
            final String formalParamName = formalParam.getDeclaredName();
            Api.Type actualParamType = formalParamType;
            Api.Type actualInternalParamType = formalParamType;
            // Check to see if we have an actual parameter for the formal parameter. The user may be missing arguments in their method call.
            // This will cause a compiler error. We should handle it gracefully.
            if(i < actualParams.size() && actualParams.get(i) != null) {
                final PsiType actualTypeToUse = getAdjustedType(actualParams.get(i));

                // Determine the source element.
                PsiElement sourceElement;
                if(i < psiArgumentExpressions.length) {
                    sourceElement = psiArgumentExpressions[i];
                } else {
                    sourceElement = null;
                }

                final Api.Type possibleActualParam = typeCreator.createTypeForActualParameterForTemplate(formalParamName, formalParamType, actualTypeToUse, sourceElement);
                if(possibleActualParam != null) {
                    actualParamType = possibleActualParam;
                }

                final Api.Type possibleInternalActualParam = typeCreator.createTypeForActualParameterInternal(formalParamName, formalParamType, actualTypeToUse, sourceElement);
                if(possibleInternalActualParam != null) {
                    actualInternalParamType = possibleInternalActualParam;
                }
            }
            argumentExpressions.add(new ArgumentExpressionImpl(formalParamType, actualParamType, actualInternalParamType));
        }

        // Determine the call expression type.
        Api.Type callExpressionApiType = null;
        final PsiType callExpressionType = getAdjustedType(methodCallExpression.type());
        if(callExpressionType != null && !PsiTypes.voidType().equals(callExpressionType)) {
            callExpressionApiType = typeCreator.createTypeForFormalParameter("value", callExpressionType, InitStrategy.Default, methodCallExpression.methodCallExpression());
        }
        return new MethodCallExpressionImpl(argumentExpressions, callExpressionApiType);
    }

    private PsiType getAdjustedType(final PsiType psiType) {
        if(!(psiType instanceof final PsiCapturedWildcardType paramValue)) {
            return psiType;
        }
        final PsiType upperBound = paramValue.getUpperBound();
        final PsiType lowerBound = paramValue.getLowerBound();
        if(!isNullType(upperBound)) {
            return upperBound;
        }
        if(!isNullType(lowerBound)) {
            return lowerBound;
        }
        return psiType;
    }

    private Api.Method createMethodForDI(final PsiMethod psiMethod, final PsiType callExpressionType, final PsiMethodCallExpression psiMethodCallExpression) {
        // Do not check noOpTemplateVarsMapper to see if we've already converted this PsiMethod into an Api.Method.
        // The template can modify the method properties; e.g. set di.method.parameters.get(0).initExpression to
        // 'any(Runnable.class)'. Normally, this isn't a problem, as you always want to use any(Runnable.class) whenever the
        // argument to a dependency interaction is a Runnable.

        // This does become a problem when the di.method is an instance method with type T, and multiple source methods call the
        // same instance method with different type arguments. These will have different init expressions. This breaks if they
        // all point to the same Method object.

        // An example is: class OrderAdapter depends on DynamoDBMapper.
        // OrderAdapter.getFoo(foo) calls dynamoDBMapper.load(foo).
        // OrderAdapter.getBar(bar) calls dynamoDBMapper.load(bar).
        // If getFoo.dependencyInteractions.get(0).method and getBar.dependencyInteractions.get(0).method point to the same
        // Api.Method object, things break; the template code sets method.parameters.get(0) to 'any(Foo.class)'
        // and 'any(Bar.class)', respectively. Both will be either 'any(Bar.class)' or 'any(Foo.class)' (whichever
        // set expression is executed last).
        return psiMethodToMethodConverter.createMethod(noOpTemplateVarsMapper, psiMethod, 0, 0, InitStrategy.Default, callExpressionType, psiMethodCallExpression);
    }
}
