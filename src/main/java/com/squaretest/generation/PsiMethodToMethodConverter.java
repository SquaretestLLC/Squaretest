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
package com.squaretest.generation;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiCompiledElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypes;
import com.intellij.psi.impl.light.LightElement;
import com.intellij.util.ThreeState;
import com.squaretest.annotations.AnnotationCreator;
import com.squaretest.generation.dataflow.usedmethods.UsedPropertyInfoProvider;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.defaulttypes.SQAnnotationUtil;
import com.squaretest.generation.defaulttypes.TypeCreator;
import com.squaretest.generation.defaulttypes.beans.BeanInfoProvider;
import com.squaretest.generation.dependencyinteraction.DependencyInteractionCollectorUtils;
import com.squaretest.generation.dependencyinteraction.outcomes.MethodPathsInfoProvider;
import com.squaretest.generation.exceptions.PsiExceptionProvider;
import com.squaretest.generation.simpleexit.SimpleExitInfoProvider;
import com.squaretest.template.AccessModifier;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.ConstructorImpl;
import com.squaretest.template.impl.FluentListImpl;
import com.squaretest.template.impl.MethodImpl;
import com.squaretest.template.impl.TypeImpl;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.squaretest.generation.defaulttypes.TypeCreatorUtil.getMethodKey;

public class PsiMethodToMethodConverter {
    private final TypeCreator typeCreator;
    private final PsiExceptionProvider psiExceptionProvider;
    private final ExceptionCreator exceptionCreator;
    private final PsiFieldToVariableConverter psiFieldToVariableConverter;
    private final AnnotationCreator annotationCreator;
    private final BeanInfoProvider beanInfoProvider;
    private final NullabilityDecider nullabilityDecider;
    private final UsedPropertyInfoProvider usedPropertyInfoProvider;
    private final SimpleExitInfoProvider simpleExitInfoProvider;
    private final MethodPathsInfoProvider methodPathsInfoProvider;
    private final VisibilityInfoProvider visibilityInfoProvider;
    private final PsiClass sourceClass;
    private final Set<PsiClass> sourceAndSupers;
    private final boolean isSourceClassSpringController;

    public PsiMethodToMethodConverter(
            final PsiClass sourceClass, final TypeCreator typeCreator, final PsiExceptionProvider psiExceptionProvider,
            final ExceptionCreator exceptionCreator, final PsiFieldToVariableConverter psiFieldToVariableConverter,
            final AnnotationCreator annotationCreator, final BeanInfoProvider beanInfoProvider,
            final NullabilityDecider nullabilityDecider, final UsedPropertyInfoProvider usedPropertyInfoProvider,
            final SimpleExitInfoProvider simpleExitInfoProvider, final MethodPathsInfoProvider methodPathsInfoProvider,
            final VisibilityInfoProvider visibilityInfoProvider) {
        this.typeCreator = typeCreator;
        this.psiExceptionProvider = psiExceptionProvider;
        this.exceptionCreator = exceptionCreator;
        this.psiFieldToVariableConverter = psiFieldToVariableConverter;
        this.annotationCreator = annotationCreator;
        this.beanInfoProvider = beanInfoProvider;
        this.nullabilityDecider = nullabilityDecider;
        this.usedPropertyInfoProvider = usedPropertyInfoProvider;
        this.simpleExitInfoProvider = simpleExitInfoProvider;
        this.methodPathsInfoProvider = methodPathsInfoProvider;
        this.visibilityInfoProvider = visibilityInfoProvider;
        this.sourceClass = sourceClass;
        this.sourceAndSupers = new LinkedHashSet<>();
        this.sourceAndSupers.add(sourceClass);
        this.sourceAndSupers.addAll(DependencyInteractionCollectorUtils.computeSuperClasses(sourceClass));
        this.isSourceClassSpringController = PsiConverterUtils.isSpringController(sourceAndSupers);
    }

    public Api.Constructor createConstructor(
            final PsiMethod method, final boolean isOverload, final int overloadCount,
            final int indexInSourceClass, final PsiToTemplateVarsMapper psiToTemplateVarsMapper, final InitStrategy initStrategy) {
        if(psiToTemplateVarsMapper.hasMethod(method, initStrategy)) {
            return (Api.Constructor) psiToTemplateVarsMapper.getMethod(method, initStrategy);
        }
        final Api.FluentList<Api.Variable> variables = createVariablesFromMethod(method, psiToTemplateVarsMapper, initStrategy);
        final AccessModifier modifier = PsiConverterUtils.modifierFromPsi(method);

        final Api.Type returnType = createReturnTypeFromMethod(method.getReturnType(), method, initStrategy);
        final boolean calledInSource = usedPropertyInfoProvider.calledInSource(method);
        final boolean propertyIsUsedInSource = false;
        final boolean calledInStaticHelpers = usedPropertyInfoProvider.calledInStaticHelpers(method);
        final boolean propertyIsUsedInStaticHelpers = false;
        final boolean doesNothing = determineIfDoesNothing(method);
        final boolean isWritable = method.isWritable();
        final boolean isVisibleToTestClass = visibilityInfoProvider.isVisibleToTestClass(method);
        final boolean isInMainSourceClass = isInMainSourceClass(method);
        final boolean isLombokSuperBuilderConstructor = determineIfLombokSuperBuilderConstructor(method);
        final Api.Constructor constructor = new ConstructorImpl(
                method.getName(),
                variables,
                modifier,
                returnType, annotationCreator.createAnnotations(method),
                isVisibleToTestClass, getMethodKey(method), isOverload,
                method.isDeprecated(), false, false,
                isWritable, false, false, false, isInMainSourceClass, overloadCount, indexInSourceClass, calledInSource, propertyIsUsedInSource, calledInStaticHelpers, propertyIsUsedInStaticHelpers, doesNothing, isLombokSuperBuilderConstructor);
        psiToTemplateVarsMapper.putMethod(method, constructor, initStrategy);
        return constructor;
    }

    public Api.Method createMethod(
            final PsiToTemplateVarsMapper psiToTemplateVarsMapper, final PsiMethod method, final int overloadCount, final int indexInSourceClass, final InitStrategy initStrategy) {
        return createMethod(psiToTemplateVarsMapper, method, overloadCount, indexInSourceClass, initStrategy, null, null);
    }

    public Api.Method createMethod(
            final PsiToTemplateVarsMapper psiToTemplateVarsMapper, final PsiMethod method, final int overloadCount,
            final int indexInSourceClass, final InitStrategy initStrategy, @Nullable final PsiType returnTypeExpression, @Nullable final PsiMethodCallExpression methodCallExpression) {
        if(psiToTemplateVarsMapper.hasMethod(method, initStrategy)) {
            return psiToTemplateVarsMapper.getMethod(method, initStrategy);
        }
        final AccessModifier modifier = PsiConverterUtils.modifierFromPsi(method);
        final Api.FluentList<Api.Variable> params = createVariablesFromMethod(method, methodCallExpression, psiToTemplateVarsMapper, initStrategy);
        final String methodName = method.getName();
        final boolean isStatic = method.hasModifierProperty(PsiModifier.STATIC);
        final boolean isAbstract = isAbstract(method);
        final boolean isNative = method.hasModifierProperty(PsiModifier.NATIVE);
        boolean isDeprecated = method.isDeprecated();
        boolean isWritable = method.isWritable();
        final Api.FluentList<Api.Exception> declaredExceptions = getDeclaredExceptions(method);
        final Api.FluentList<Api.Exception> javadocExceptions = getJavadocExceptions(method);
        final Api.FluentList<Api.Exception> undeclaredExceptions = getUndeclaredExceptions(method);
        final boolean isGetter = determineIfGetter(method);
        final boolean isSetter = determineIfSetter(method);
        final boolean isJaxbGetter = beanInfoProvider.isJAXBGetter(method);
        final boolean calledInSource = usedPropertyInfoProvider.calledInSource(method);
        final boolean propertyIsUsedInSource = usedPropertyInfoProvider.propertyIsUsedInSource(method);
        final boolean calledInStaticHelpers = usedPropertyInfoProvider.calledInStaticHelpers(method);
        final boolean propertyIsUsedInStaticHelpers = usedPropertyInfoProvider.propertyIsUsedInStaticHelpers(method);
        final Api.ClassMember targetField = getTargetField(method, initStrategy, psiToTemplateVarsMapper);
        final PsiType returnTypeToUse = returnTypeExpression != null ? returnTypeExpression : method.getReturnType();
        final PsiElement sourceToUse = methodCallExpression != null ? methodCallExpression : method;
        final Api.Type returnType = createReturnTypeFromMethod(returnTypeToUse, sourceToUse, initStrategy);
        final Api.SimpleExitInfo simpleExitInfo = simpleExitInfoProvider.computeSimpleExitInfo(method, returnType);
        final PsiMethod[] superPsiMethods = method.findSuperMethods();
        final boolean simpleExit = simpleExitInfo.getReturnExpression() != null || simpleExitInfo.getThrownException() != null;
        final boolean alwaysThrows = methodPathsInfoProvider.alwaysThrows(method);
        final boolean shouldUseLastParam = determineIfShouldUseLastParam(method, methodCallExpression);
        final boolean doesNothing = !isSourceClassSpringController && determineIfDoesNothing(method);
        final boolean isVisibleToTestClass = visibilityInfoProvider.isVisibleToTestClass(method);
        final boolean isInMainSourceClass = isInMainSourceClass(method);
        // Determine if we should use a simple test method.
        boolean shouldUseSimpleTest = returnType != null && returnType.isSimple() && isStatic && params.stream().allMatch(x -> x.getType().isSimple());
        if(methodCallExpression == null
                && (!isSourceClassSpringController || isStatic)
                && isInSourceOrSuper(method)
                && simpleExit) {
            shouldUseSimpleTest = true;
        }
        // Update the parameters and return type for simple test methods.
        if(shouldUseSimpleTest) {
            for(final Api.Variable param : params) {
                param.setShouldStoreInReference(false);
            }
            if(returnType != null) {
                returnType.setShouldStoreInReference(false);
            }
        }
        // Compute the super methods.
        final Api.FluentList<Api.Method> superMethods = new FluentListImpl<>(superPsiMethods.length);
        for(final PsiMethod superMethod : superPsiMethods) {
            final PsiMethod superMethodWithSource = CompiledUtils.getMethodWithSourceCode(superMethod);
            superMethods.add(createMethod(psiToTemplateVarsMapper, superMethodWithSource, overloadCount, indexInSourceClass, initStrategy, returnTypeExpression, methodCallExpression));
        }
        final NullabilityStatus nullabilityStatus = nullabilityDecider.determineIfNullable(method, superPsiMethods, methodCallExpression);
        final boolean isOverload = PsiConverterUtils.isOverload(method);
        final boolean isLombokSuperBuilderConstructor = determineIfLombokSuperBuilderConstructor(method);
        final Api.Method apiMethod = new MethodImpl(methodName, params, modifier, returnType, isVisibleToTestClass, declaredExceptions,
                javadocExceptions, undeclaredExceptions, superMethods,
                annotationCreator.createAnnotations(method), targetField, simpleExitInfo, getMethodKey(method),
                nullabilityStatus, isOverload,
                isDeprecated, isStatic, isAbstract, isWritable, isNative, isGetter, isSetter,
                false, isInMainSourceClass, overloadCount, shouldUseSimpleTest, isJaxbGetter, indexInSourceClass, alwaysThrows, calledInSource, propertyIsUsedInSource, calledInStaticHelpers, propertyIsUsedInStaticHelpers, doesNothing, isLombokSuperBuilderConstructor, shouldUseLastParam);
        psiToTemplateVarsMapper.putMethod(method, apiMethod, initStrategy);
        return apiMethod;
    }

    private boolean determineIfLombokSuperBuilderConstructor(final PsiMethod method) {
        if(!method.isConstructor()) {
            return false;
        }
        if(!StringUtils.contains(method.getClass().getName(), JavaNames.LombokLight)) {
            return false;
        }
        if(!method.hasModifierProperty(PsiModifier.PROTECTED)) {
            return false;
        }
        final PsiParameter[] params = method.getParameterList().getParameters();
        if(params.length != 1) {
            return false;
        }
        final PsiClass containingClass = method.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        if(!SQAnnotationUtil.hasAnnotation(containingClass, JavaNames.LombokSuperBuilders)) {
            return false;
        }
        return true;
    }

    private boolean isInMainSourceClass(final PsiMethod method) {
        final PsiClass containingClass = method.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName == null) {
            return false;
        }
        return Objects.equals(sourceClass.getQualifiedName(), qualifiedName);
    }

    private boolean determineIfGetter(final PsiMethod method) {
        final boolean isGetter = beanInfoProvider.isGetter(method);
        if(!isGetter) {
            return false;
        }
        if(!isInSourceOrSuper(method)) {
            return true;
        }
        if(method instanceof LightElement) {
            return true;
        }
        if(isAbstract(method) || method.getBody() == null) {
            return true;
        }
        final ThreeState confirmedGetterStatusByCode = beanInfoProvider.getConfirmedGetterStatusByCode(method);
        return confirmedGetterStatusByCode != ThreeState.NO;
    }

    private boolean determineIfSetter(final PsiMethod method) {
        final boolean isSetter = beanInfoProvider.isSetter(method);
        if(!isSetter) {
            return false;
        }
        if(!isInSourceOrSuper(method)) {
            return true;
        }
        if(method instanceof LightElement) {
            return true;
        }
        if(isAbstract(method) || method.getBody() == null) {
            return true;
        }
        final ThreeState confirmedSetterStatusByCode = beanInfoProvider.getConfirmedSetterStatusByCode(method);
        return confirmedSetterStatusByCode != ThreeState.NO;
    }

    public static boolean isAbstract(final PsiMethod method) {
        if(method.hasModifierProperty(PsiModifier.ABSTRACT)) {
            return true;
        }
        final PsiClass containingClass = method.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        return containingClass.isInterface() && !method.hasModifierProperty(PsiModifier.DEFAULT) && !method.hasModifierProperty(PsiModifier.STATIC);
    }

    private boolean determineIfDoesNothing(final PsiMethod method) {
        if(isAbstract(method)) {
            return false;
        }
        final PsiCodeBlock body = method.getBody();
        if(body == null) {
            return false;
        }
        return body.getStatementCount() == 0;
    }

    private boolean determineIfShouldUseLastParam(final PsiMethod method, @Nullable final PsiMethodCallExpression methodCallExpression) {
        if(methodCallExpression == null) {
            return true;
        }
        if(!method.isVarArgs()) {
            return true;
        }
        final PsiExpression[] args = methodCallExpression.getArgumentList().getExpressions();
        if(args.length >= method.getParameterList().getParameters().length) {
            return true;
        }
        return false;
    }

    private boolean isInSourceOrSuper(final PsiMethod method) {
        final PsiClass containingClass = method.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        return sourceAndSupers.contains(containingClass);
    }

    @Nullable
    private Api.ClassMember getTargetField(final PsiMethod method, final InitStrategy initStrategy, final PsiToTemplateVarsMapper psiToTemplateVarsMapper) {
        final PsiField targetField = usedPropertyInfoProvider.getTargetField(method);
        if(targetField == null) {
            return null;
        }
        final Api.Variable variable = psiToTemplateVarsMapper.getVariable(targetField, initStrategy);
        if(variable instanceof Api.ClassMember) {
            return (Api.ClassMember) variable;
        }
        return null;
    }

    private Api.FluentList<Api.Exception> getDeclaredExceptions(final PsiMethod method) {
        final List<PsiType> declaredExceptions = psiExceptionProvider.getDeclaredExceptions(method);
        return exceptionCreator.createExceptionTypes(declaredExceptions);
    }

    private Api.FluentList<Api.Exception> getUndeclaredExceptions(final PsiMethod method) {
        final List<PsiType> undeclaredExceptions = psiExceptionProvider.getUndeclaredExceptions(method);
        return exceptionCreator.createExceptionTypes(undeclaredExceptions);
    }

    private Api.FluentList<Api.Exception> getJavadocExceptions(final PsiMethod method) {
        final List<PsiType> javadocExceptions = psiExceptionProvider.getJavadocExceptions(method);
        return exceptionCreator.createExceptionTypes(javadocExceptions);
    }

    private Api.FluentList<Api.Variable> createVariablesFromMethod(
            @NotNull final PsiMethod method, @Nullable final PsiMethodCallExpression methodCallExpression,
            final PsiToTemplateVarsMapper psiToTemplateVarsMapper, final InitStrategy initStrategy) {
        final PsiParameter[] parameterList = method.getParameterList().getParameters();
        final PsiExpression[] methodArguments;
        if(methodCallExpression != null) {
            methodArguments = methodCallExpression.getArgumentList().getExpressions();
        } else {
            methodArguments = PsiExpression.EMPTY_ARRAY;
        }
        final FluentListImpl<Api.Variable> variables = new FluentListImpl<>(parameterList.length);
        for(int i = 0; i < parameterList.length; i++) {
            final PsiParameter parameter = parameterList[i];
            // Determine if the parameter is used in the method body. If we don't have the source code for the method
            // (body = null), the class is compiled or the method is abstract; in that case assume the params are used.
            final PsiCodeBlock methodBody = method.getBody();
            final boolean isUsed = methodBody == null || VariableUsedVisitor.isVariableUsedIn(parameter, methodBody);
            final String paramName = determineParamName(method, parameter);
            PsiElement sourceElement = parameter;
            if(i < methodArguments.length) {
                sourceElement = methodArguments[i];
            }
            final Api.Variable templateVar = psiFieldToVariableConverter.createFieldFromPsiVariable(parameter, sourceElement, paramName, isUsed, initStrategy);
            variables.add(templateVar);
            psiToTemplateVarsMapper.putVariable(parameter, templateVar, initStrategy);
        }
        return variables;
    }

    private Api.FluentList<Api.Variable> createVariablesFromMethod(
            @NotNull final PsiMethod method, final PsiToTemplateVarsMapper psiToTemplateVarsMapper, final InitStrategy initStrategy) {
        return createVariablesFromMethod(method, null, psiToTemplateVarsMapper, initStrategy);
    }

    private String determineParamName(final PsiMethod method, final PsiParameter parameter) {
        final PsiMethod methodToUse = CompiledUtils.getMethodWithSourceCode(method);
        if(methodToUse.isConstructor()) {
            return parameter.getName();
        }
        final String paramName = parameter.getName();
        String nameToUse = paramName;
        if(methodToUse instanceof PsiCompiledElement) {
            if(beanInfoProvider.isSetter(methodToUse)) {
                final PsiField targetField = beanInfoProvider.getFieldForSetter(methodToUse);
                if(targetField != null) {
                    nameToUse = StringUtils.stripStart(targetField.getName(), "_");
                    if(nameToUse.isEmpty()) {
                        // The field is literally named "_" or "__".
                        nameToUse = paramName;
                    }
                }
            }
        }
        return nameToUse;
    }

    private Api.Type createReturnTypeFromMethod(@Nullable final PsiType psiType, final PsiElement source, final InitStrategy initStrategy) {
        if(psiType == null || PsiTypes.voidType().equals(psiType)) {
            return null;
        }
        final TypeImpl returnType = this.typeCreator.createTypeForFormalParameter("result", psiType, initStrategy, source);
        // Set shouldBeMocked and shouldStoreInReference.
        // By default, type.shouldBeMocked will be true if type.canBeMocked is true. This works well for dependencies,
        // because the default behavior for unknown types should be: use a mock. For return types, we want the default behavior
        // to be: do not use a mock. We will only use a mock if the type is recognized (in the config) AND we've decided to
        // mock (in the config).
        if(returnType.isRecognized()) {
            returnType.setShouldStoreInReference(returnType.getShouldBeMocked() || returnType.isDtoBean());
        } else {
            returnType.setShouldBeMocked(false);
            returnType.setShouldStoreInReference(returnType.isDtoBean());
        }
        return returnType;
    }
}
