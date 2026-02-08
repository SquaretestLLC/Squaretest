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
package com.squaretest.generation.defaulttypes;

import com.intellij.psi.*;
import com.intellij.psi.impl.light.LightElement;
import com.intellij.psi.impl.source.PsiClassReferenceType;
import com.intellij.psi.util.InheritanceUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtil;
import com.squaretest.generation.CompiledUtils;
import com.squaretest.generation.InitStrategy;
import com.squaretest.generation.PsiConverterUtils;
import com.squaretest.generation.SuggestedNameProvider;
import com.squaretest.generation.TypeSubstitutorProvider;
import com.squaretest.generation.VisibilityInfoProvider;
import com.squaretest.generation.WildcardInfoProvider;
import com.squaretest.generation.dataflow.DataflowUtils;
import com.squaretest.generation.dataflow.usedmethods.CalledMethodInfo;
import com.squaretest.generation.dataflow.usedmethods.CalledMethodsInfoProvider;
import com.squaretest.generation.defaulttypes.beans.BeanInfoProvider;
import com.squaretest.generation.defaulttypes.builders.BuilderInfoProvider;
import com.squaretest.generation.defaulttypes.builders.BuilderInitInfo;
import com.squaretest.generation.dependencyinteraction.FieldToSourceVariableCollector;
import com.squaretest.generation.sourcevars.builders.LombokBuilderSourceVariableProvider;
import com.squaretest.settings.store.SettingsProvider;
import com.squaretest.template.AccessModifier;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.FluentListImpl;
import com.squaretest.template.impl.TypeImpl;
import com.squaretest.utils.SQMutableInt;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.squaretest.generation.defaulttypes.AWSBuilderUtils.hasConsumerParam;
import static com.squaretest.generation.defaulttypes.AbsentUtil.updateAbsentInitExpressionIfNeeded;
import static com.squaretest.generation.defaulttypes.TypeCreatorUtil.*;
import static com.squaretest.generation.dependencyinteraction.outcomes.MethodPathsInfoProvider.ErrorClassNameRegex;

/**
 * Defines the interface for the TypeCreator. Implementors must convert {@link PsiType PsiTypes} into
 * {@link TypeImpl Types}.
 */
public abstract class TypeCreator {

    // Constants used to control the recursive type initialization.
    private static final int MaxRecursiveInitDepth = 15;

    // Open source packages containing classes that shouldn't be mocked; e.g. TMap, Multimap, etc.
    private static final String[] OpenSourceCollectionsPackages = new String[]{"com.google.common.collect", "org.apache.commons.collections", "gnu.trove"};

    // Default init expressions and formats.
    private static final String ArrayInitFormat = "new %s{}";
    public static final String DefaultInitExpression = "null";

    @NotNull
    final JavaGroovyCommonTypesCreator javaGroovyCommonTypesCreator;
    @NotNull
    final BeanInfoProvider beanInfoProvider;
    @NotNull
    private final SuggestedNameProvider suggestedNameProvider;
    @NotNull
    private final TestDependencyInfoProvider testDependencyInfoProvider;
    @NotNull
    private final AltIoExpressionPopulator altIOExpressionPopulator;
    @NotNull
    protected final SuperTypesProvider superTypesProvider;
    @NotNull
    private final FieldToSourceVariableCollector fieldToSourceVariableCollector;
    @NotNull
    private final BuilderInfoProvider builderInfoProvider;
    @NotNull
    private final CalledMethodsInfoProvider calledMethodsInfoProvider;
    @NotNull
    private final TypeSubstitutorProvider typeSubstitutorProvider;
    @NotNull
    private final EnumValueProvider enumValueProvider;
    @NotNull
    private final JavaLibraryReferenceProvider javaLibraryReferenceProvider;
    @NotNull
    private final WildcardInfoProvider wildcardInfoProvider;
    @NotNull
    private final VisibilityInfoProvider visibilityInfoProvider;
    @NotNull
    private final SettingsProvider settingsProvider;
    private final Set<CanonicalTextAndInitStrategy> typesThatExceededInitLimit;

    TypeCreator(
            @NotNull final JavaGroovyCommonTypesCreator javaGroovyCommonTypesCreator,
            @NotNull final BeanInfoProvider beanInfoProvider,
            @NotNull final SuggestedNameProvider suggestedNameProvider,
            @NotNull final TestDependencyInfoProvider testDependencyInfoProvider,
            @NotNull final AltIoExpressionPopulator altIoExpressionPopulator,
            @NotNull final SuperTypesProvider superTypesProvider,
            @NotNull final FieldToSourceVariableCollector fieldToSourceVariableCollector,
            @NotNull final SettingsProvider settingsProvider,
            @NotNull final BuilderInfoProvider builderInfoProvider,
            @NotNull final CalledMethodsInfoProvider calledMethodsInfoProvider,
            @NotNull final TypeSubstitutorProvider typeSubstitutorProvider,
            @NotNull final EnumValueProvider enumValueProvider,
            @NotNull final JavaLibraryReferenceProvider javaLibraryReferenceProvider,
            @NotNull final WildcardInfoProvider wildcardInfoProvider,
            @NotNull final VisibilityInfoProvider visibilityInfoProvider) {
        this.javaGroovyCommonTypesCreator = javaGroovyCommonTypesCreator;
        this.beanInfoProvider = beanInfoProvider;
        this.suggestedNameProvider = suggestedNameProvider;
        this.testDependencyInfoProvider = testDependencyInfoProvider;
        this.altIOExpressionPopulator = altIoExpressionPopulator;
        this.superTypesProvider = superTypesProvider;
        this.fieldToSourceVariableCollector = fieldToSourceVariableCollector;
        this.javaLibraryReferenceProvider = javaLibraryReferenceProvider;
        this.builderInfoProvider = builderInfoProvider;
        this.calledMethodsInfoProvider = calledMethodsInfoProvider;
        this.typeSubstitutorProvider = typeSubstitutorProvider;
        this.enumValueProvider = enumValueProvider;
        this.wildcardInfoProvider = wildcardInfoProvider;
        this.settingsProvider = settingsProvider;
        this.visibilityInfoProvider = visibilityInfoProvider;
        this.typesThatExceededInitLimit = new HashSet<>();
    }

    public Api.Type createTypeForActualParameterForTemplate(
            final String formalParamName, final Api.Type formalParamType, @NotNull final PsiType actualType, final PsiElement source) {
        final PsiClass typeClass = CompiledUtils.getClassWithSourceCode(PsiUtil.resolveClassInType(actualType));
        if(typeClass != null && Objects.equals(JavaNames.JavaLangClass, typeClass.getQualifiedName())) {
            final PsiClassType.ClassResolveResult resolveResult = PsiUtil.resolveGenericsClassInType(actualType);
            final Map<PsiTypeParameter, PsiType> substitutionMap = resolveResult.getSubstitutor().getSubstitutionMap();
            if(substitutionMap.size() == 1) {
                final Map.Entry<PsiTypeParameter, PsiType> mapEntry = substitutionMap.entrySet().iterator().next();
                PsiType typeToUse = mapEntry.getValue();
                if(typeToUse instanceof PsiCapturedWildcardType) {
                    typeToUse = ((PsiCapturedWildcardType) typeToUse).getUpperBound();
                }
                if(typeToUse == null) {
                    // This happens when we receive a Class instance from another place, and the class instance does
                    // not have any type params.
                    // Example:
                    // Class clazz = foo.getClassToUse();
                    // otherFoo.doSomethingWith(clazz);
                    typeToUse = actualType;
                }
                return createTypeForFormalParameter(formalParamName, typeToUse, InitStrategy.Default, source);
            }
        }

        // Handle the case where the actual type is null (the method is called with null).
        if(isNullArg(actualType)) {
            return formalParamType;
        }

        // Handle the case where the method is called with a lambda or method reference expression; e.g. Object::nonNull.
        if(actualType instanceof PsiLambdaExpressionType || actualType instanceof PsiMethodReferenceExpression || actualType instanceof PsiMethodReferenceType) {
            return formalParamType;
        }

        return createTypeForFormalParameter(formalParamName, actualType, InitStrategy.Default, source);
    }

    public Api.Type createTypeForActualParameterInternal(
            final String formalParamName, final Api.Type formalParamType, @NotNull final PsiType actualType, final PsiElement source) {
        // Handle the case where the actual type is null (the method is called with null).
        if(isNullArg(actualType)) {
            return formalParamType;
        }

        // Handle the case where the method is called with a lambda or method reference expression; e.g. Object::nonNull.
        if(actualType instanceof PsiLambdaExpressionType || actualType instanceof PsiMethodReferenceExpression || actualType instanceof PsiMethodReferenceType) {
            return formalParamType;
        }

        return createTypeForFormalParameter(formalParamName, actualType, InitStrategy.Default, source);
    }

    public TypeImpl createTypeForFormalParameter(@NotNull final String parameterName, @NotNull final PsiType parameterType, final InitStrategy startingInitStrategy, final PsiElement source) {
        final PsiType typeToUse = useSourceClassSubs(parameterType, source);
        final InitStrategy[] initStrategies = InitStrategy.values();
        final String canonicalText = typeToUse.getCanonicalText();
        final SQMutableInt numberOfConstructorParamsUsed = new SQMutableInt(0);
        for(int i = startingInitStrategy.ordinal(); i < initStrategies.length - 1; i++) {
            final InitStrategy currentStrategy = initStrategies[i];
            final CanonicalTextAndInitStrategy canonicalTextAndInitStrategy = new CanonicalTextAndInitStrategy(canonicalText, currentStrategy);
            if(typesThatExceededInitLimit.contains(canonicalTextAndInitStrategy)) {
                continue;
            }
            final int constructorParamLimit = getConstructorParamLimit(currentStrategy);
            numberOfConstructorParamsUsed.setValue(0);
            TypeImpl typeImpl = createTypeForFormalParamRecursive(parameterName, typeToUse, source, false, currentStrategy, 0,
                    numberOfConstructorParamsUsed, new SQMutableInt(0), new HashSet<>());
            if(numberOfConstructorParamsUsed.getValue() > constructorParamLimit) {
                typesThatExceededInitLimit.add(canonicalTextAndInitStrategy);
                continue;
            }
            return typeImpl;
        }

        // If we reach this point, use the last init strategy.
        numberOfConstructorParamsUsed.setValue(0);
        return createTypeForFormalParamRecursive(parameterName, typeToUse, source, false, InitStrategy.LastOption, 0,
                numberOfConstructorParamsUsed, new SQMutableInt(0), new HashSet<>());
    }

    private int getConstructorParamLimit(final InitStrategy currentStrategy) {
        return switch(currentStrategy) {
            case Default -> settingsProvider.getConstructorMaxLength();
            case DataBeans -> settingsProvider.getConstructorLimitWhenBeanOptionAvailable();
            case GeneralBeans -> settingsProvider.getConstructorLimitWhenOtherOptionAvailable();
            default -> settingsProvider.getConstructorLimitFinal();
        };
    }

    private PsiType useSourceClassSubs(final PsiType psiType, final PsiElement source) {
        if(source instanceof PsiParameter) {
            final PsiMethod containingMethod = DataflowUtils.getContainingMethodForParameter((PsiVariable) source);
            if(containingMethod == null) {
                return psiType;
            }
            final PsiClass containingClass = containingMethod.getContainingClass();
            if(containingClass == null) {
                return psiType;
            }
            final PsiSubstitutor substitutor = typeSubstitutorProvider.getSubstitutor(containingClass);
            if(substitutor != null) {
                return substitutor.substitute(psiType);
            }
            return psiType;
        }

        if(source instanceof PsiExpression) {
            // The source is an actual param expression.
            final PsiSubstitutor substitutor = getSubstitutorForArgumentExpression((PsiExpression) source);
            if(substitutor != null) {
                return substitutor.substitute(psiType);
            }
            return psiType;
        }

        if(source instanceof PsiField) {
            final PsiClass containingClass = ((PsiField) source).getContainingClass();
            if(containingClass == null) {
                return psiType;
            }
            final PsiSubstitutor substitutor = typeSubstitutorProvider.getSubstitutor(containingClass);
            if(substitutor != null) {
                return substitutor.substitute(psiType);
            }
            return psiType;
        }
        if(source instanceof PsiMethod) {
            final PsiClass containingClass = ((PsiMethod) source).getContainingClass();
            if(containingClass == null) {
                return psiType;
            }
            final PsiSubstitutor substitutor = typeSubstitutorProvider.getSubstitutor(containingClass);
            if(substitutor != null) {
                return substitutor.substitute(psiType);
            }
            return psiType;
        }
        return psiType;
    }

    private PsiSubstitutor getSubstitutorForArgumentExpression(final PsiExpression source) {
        final List<PsiClass> containingClasses = PsiTreeUtil.collectParents(source, PsiClass.class, false, x -> x instanceof PsiFile);
        for(final PsiClass containingClass : containingClasses) {
            final PsiSubstitutor substitutor = typeSubstitutorProvider.getSubstitutor(containingClass);
            if(substitutor != null) {
                return substitutor;
            }
        }
        return null;
    }

    @NotNull
    private TypeImpl createTypeForFormalParamRecursive(
            @NotNull final String parameterName, @NotNull final PsiType parameterType,
            final PsiElement source, final boolean isWildcard, final InitStrategy initStrategy,
            final int recursiveCounter, final SQMutableInt numberOfConstructorParams,
            final SQMutableInt numberOfBuilderMethodsCalled, final Set<String> canonicalTextsInStack) {
        final TypeImpl typeImpl = createTypeFromConfig(parameterName, parameterType, source, initStrategy);
        typeImpl.setWildcard(isWildcard);
        if(isAWSV1Request(parameterType)) {
            typeImpl.setShouldOnlySetUsedProperties(true);
        }
        final String parameterTypeCanonicalText = parameterType.getCanonicalText();
        if(recursiveCounter >= MaxRecursiveInitDepth || typeImpl.isGeneric() || canonicalTextsInStack.contains(parameterTypeCanonicalText)) {
            return typeImpl;
        }
        if(numberOfConstructorParams.intValue() > getConstructorParamLimit(initStrategy)) {
            return typeImpl;
        }
        // Add this parameter type to the call stack.
        canonicalTextsInStack.add(parameterTypeCanonicalText);

        // Resolve the parameters in the type.
        final ResolveTypeInfo resolveTypeInfo = resolveTypeParams(parameterName, parameterType, initStrategy, recursiveCounter, numberOfConstructorParams.clone(), numberOfBuilderMethodsCalled.clone(), canonicalTextsInStack);
        typeImpl.addParams(resolveTypeInfo.params());

        // Set testClassMemberName and testClassLocalFieldName. We need to do this here, because we get a more accurate name if
        // we provide the full type; e.g. the suggested name for List<User> will be "users" instead of "list".
        final String suggestedTestClassLocalFieldName = suggestedNameProvider.suggestLocalFieldName(typeImpl.getName(), parameterType);
        final String suggestedTestClassMemberName = suggestedNameProvider.suggestMemberFieldName(typeImpl.getName(), parameterType);
        typeImpl.setTestClassLocalFieldName(suggestedTestClassLocalFieldName);
        typeImpl.setTestClassMemberName(suggestedTestClassMemberName);
        useInitExpressionWithPlaceholderIfPossible(typeImpl, resolveTypeInfo, numberOfConstructorParams, numberOfBuilderMethodsCalled);
        useFailureInitExpressionWithPlaceholderIfPossible(typeImpl);
        updateEmptyInitExpressionIfNeeded(typeImpl, parameterType, initStrategy, recursiveCounter, numberOfConstructorParams.clone(), numberOfBuilderMethodsCalled.clone(), canonicalTextsInStack);
        updateAbsentInitExpressionIfNeeded(typeImpl, parameterType);
        swapEmptyAndFailureIfNeeded(typeImpl);
        swapAbsentAndFailureIfNeeded(typeImpl);

        // We recognize the type. There is no more initialization to do here.
        if(typeImpl.isRecognized()) {
            updateCountersWithTypeParamInfoIfNeeded(numberOfConstructorParams, numberOfBuilderMethodsCalled, typeImpl, resolveTypeInfo);
            altIOExpressionPopulator.populateAltIoExpressions(typeImpl);
            canonicalTextsInStack.remove(parameterTypeCanonicalText);
            return typeImpl;
        }

        // If this is an AWS SdkIterable, say that we do recognize the type and should use a mock for it.
        // The same is true for GCSBackgroundResource types; these are service clients for the Google Cloud Services.
        // We want to mock those to ensure they're closed and also allow the user to configure their behavior.
        if(isAWSSdkIterable(parameterType) || isAWSSdkPublisher(parameterType) || isGCSBackgroundResource(parameterType)) {
            typeImpl.setShouldBeMocked(true);
            typeImpl.setRecognizedType(true);
            canonicalTextsInStack.remove(parameterTypeCanonicalText);
            return typeImpl;
        }

        // If this is in a recognized open source collections package, do not mock it.
        if(isInKnownCollectionsPackage(parameterType)) {
            typeImpl.setShouldBeMocked(false);
        }

        // Populate the failureInitExpression if needed.
        addFailureInitInfo(typeImpl, parameterType, initStrategy, recursiveCounter, numberOfConstructorParams.clone(), numberOfBuilderMethodsCalled.clone(), canonicalTextsInStack);

        // Determine if the type is a custom Optional<> class and update it's absentInitExpression if needed.
        addOptionalTypeInfoIfNeeded(typeImpl, parameterType, initStrategy);

        // Determine and set the initExpression info for this type.
        addInitInfo(typeImpl, parameterType, recursiveCounter, numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack, initStrategy);

        if(isNonStaticInnerClass(parameterType)) {
            typeImpl.setShouldBeMocked(true);
            // Treat the type as though it is recognized. Mocking is the only option for non-static inner classes.
            typeImpl.setRecognizedType(true);
        }

        // Set the empty/broken IO init expression info if needed.
        altIOExpressionPopulator.populateAltIoExpressions(typeImpl);

        updateCountersWithTypeParamInfoIfNeeded(numberOfConstructorParams, numberOfBuilderMethodsCalled, typeImpl, resolveTypeInfo);
        canonicalTextsInStack.remove(parameterTypeCanonicalText);
        return typeImpl;
    }

    private static void updateCountersWithTypeParamInfoIfNeeded(final SQMutableInt numberOfConstructorParams, final SQMutableInt numberOfBuilderMethodsCalled, final TypeImpl typeImpl, final ResolveTypeInfo resolveTypeInfo) {
        if(!typeImpl.initExpressionUsesAllTypeParams() && typeImpl.getParameters().size() == 1) {
            // If we have a single type param, but do not have an init expression that uses it, add the number of params
            // anyway. We need to process the parameter and use an alternative init strategy if needed, in case the user
            // declares a local variable in the template in order to initialize it.
            numberOfBuilderMethodsCalled.add(resolveTypeInfo.numberOfBuilderMethodsCalled());
            numberOfConstructorParams.add(resolveTypeInfo.numberOfConstructorParams());
        }
    }

    private void swapEmptyAndFailureIfNeeded(final TypeImpl typeImpl) {
        if(!typeImpl.is(JavaNames.JavaLangIterable)) {
            return;
        }
        final Api.Type firstType = typeImpl.getParameters().first();
        if(firstType == null) {
            return;
        }
        if(!firstType.isAny(JavaNames.ConstraintViolation, JavaNames.DynamoDbFailedBatch)) {
            return;
        }
        final String emptyInitExpression = typeImpl.getEmptyInitExpression();
        if(emptyInitExpression == null) {
            return;
        }
        final String temp = typeImpl.getInitExpression();
        final Api.FluentList<Api.Type> tempInitExpressionBeans = new FluentListImpl<>(typeImpl.getInitExpressionBeans());
        typeImpl.setInitExpression(emptyInitExpression);
        typeImpl.setDefaultInitExpression(emptyInitExpression);
        typeImpl.setEmptyInitExpression(null);
        typeImpl.setFailureInitExpression(temp);
        typeImpl.setDefaultFailureInitExpression(temp);
        typeImpl.setFailureInitExpressionBeans(tempInitExpressionBeans);
        typeImpl.setInitExpressionBeans(new FluentListImpl<>());
    }

    private void swapAbsentAndFailureIfNeeded(final TypeImpl typeImpl) {
        if(!typeImpl.isAny(JavaNames.JavaUtilOptional, JavaNames.GuavaOptional)) {
            return;
        }
        final Api.Type firstType = typeImpl.getParameters().first();
        if(firstType == null) {
            return;
        }
        if(!ErrorClassNameRegex.matcher(firstType.getName()).matches() && !firstType.is(JavaNames.Throwable)) {
            return;
        }
        final String absentInitExpression = typeImpl.getAbsentInitExpression();
        if(absentInitExpression == null) {
            return;
        }
        final String temp = typeImpl.getInitExpression();
        final Api.FluentList<Api.Type> tempInitExpressionBeans = new FluentListImpl<>(typeImpl.getInitExpressionBeans());
        typeImpl.setInitExpression(absentInitExpression);
        typeImpl.setDefaultInitExpression(absentInitExpression);
        typeImpl.setAbsentInitExpression(null);
        typeImpl.setFailureInitExpression(temp);
        typeImpl.setDefaultFailureInitExpression(temp);
        typeImpl.setFailureInitExpressionBeans(tempInitExpressionBeans);
        typeImpl.setInitExpressionBeans(new FluentListImpl<>());
    }

    private void addInitInfo(
            @NotNull final TypeImpl typeImpl, @NotNull final PsiType parameterType, final int recursiveCounter,
            final SQMutableInt numberOfConstructorParams, final SQMutableInt numberOfBuilderMethodsCalled,
            final Set<String> canonicalTextsInStack, final InitStrategy initStrategy) {
        if(initStrategy == InitStrategy.LastOption) {
            return;
        }
        // Use special init logic for types that have type params; e.g. Wrapper<T>.
        List<Api.Type> params = typeImpl.getParameters();
        if(!params.isEmpty()) {
            addInitInfoForTypeWithParams(typeImpl, parameterType, recursiveCounter, numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack, initStrategy);
            return;
        }

        // Try to find a builder.
        final BuilderInitInfo builderInitInfo = tryGetBuilderInfo(parameterType);
        if(builderInitInfo != null) {
            final InitInfo initInfo = callBuilderMethods(builderInitInfo, initStrategy, recursiveCounter, canonicalTextsInStack,
                    numberOfConstructorParams, numberOfBuilderMethodsCalled);
            typeImpl.setDefaultInitExpression(initInfo.initExpression());
            typeImpl.setInitExpression(initInfo.initExpression());
            if(builderInitInfo.allowBeans()) {
                typeImpl.addInitExpressionBeans(initInfo.initExpressionBeans());
            }
            return;
        }

        if(initStrategy.ordinal() >= InitStrategy.OtherOption.ordinal()) {
            final String builderCall = TypeCreatorUtil.tryCreateSimpleBuilderCall(parameterType);
            if(builderCall != null) {
                typeImpl.setDefaultInitExpression(builderCall);
                typeImpl.setInitExpression(builderCall);
                return;
            }
        }

        // Try to find a constructor.
        final ConstructorInfo constructorInfo = tryGetConstructorInfo(parameterType, initStrategy);
        if(constructorInfo != null) {
            final InitInfo initInfo = createConstructorCall(constructorInfo, initStrategy, recursiveCounter, numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
            typeImpl.setDefaultInitExpression(initInfo.initExpression());
            typeImpl.setInitExpression(initInfo.initExpression());
            typeImpl.addInitExpressionBeans(initInfo.initExpressionBeans());
            return;
        }

        // Try to call a simple builder call. For example: Foo.builder().build().
        final String builderCall = TypeCreatorUtil.tryCreateSimpleBuilderCall(parameterType);
        if(builderCall != null) {
            typeImpl.setDefaultInitExpression(builderCall);
            typeImpl.setInitExpression(builderCall);
            return;
        }

        // Try to find and call a static creator method; e.g. Uri.parse(...).
        final StaticInitInfo staticInitInfo = tryGetStaticInitInfo(parameterType, initStrategy);
        if(staticInitInfo != null) {
            final InitInfo initInfo = callStaticMethod(staticInitInfo, initStrategy, recursiveCounter, numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
            typeImpl.setDefaultInitExpression(initInfo.initExpression());
            typeImpl.setInitExpression(initInfo.initExpression());
            typeImpl.addInitExpressionBeans(initInfo.initExpressionBeans());
        }
    }

    private void addInitInfoForTypeWithParams(final TypeImpl typeImpl, final PsiType parameterType, final int recursiveCounter, final SQMutableInt numberOfConstructorParams, final SQMutableInt numberOfBuilderMethodsCalled, final Set<String> canonicalTextsInStack, final InitStrategy initStrategy) {
        final ConstructorInfo constructorInfo = tryGetConstructorInfoForTypeWithParams(parameterType, initStrategy);
        if(constructorInfo != null) {
            if(constructorInfo.usesAllTypeParams()) {
                // The constructor uses all type params. Use it and return.
                final InitInfo initInfo = createConstructorCall(constructorInfo, initStrategy, recursiveCounter, numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
                typeImpl.setDefaultInitExpression(initInfo.initExpression());
                typeImpl.setInitExpression(initInfo.initExpression());
                typeImpl.addInitExpressionBeans(initInfo.initExpressionBeans());
                typeImpl.setInitExpressionUsesAllTypeParams(true);
                return;
            }
            // The constructor call does not use all type params.
            // Try to call a static init method that does.
            final StaticInitInfo staticInitInfo = tryGetStaticInitInfoForClassWithTypeParams(typeImpl, parameterType, initStrategy);
            if(staticInitInfo != null && staticInitInfo.usesAllTypeParams()) {
                final InitInfo initInfo = callStaticInitMethodForClassWithTypeParams(staticInitInfo, initStrategy, recursiveCounter, numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
                typeImpl.setDefaultInitExpression(initInfo.initExpression());
                typeImpl.setInitExpression(initInfo.initExpression());
                typeImpl.addInitExpressionBeans(initInfo.initExpressionBeans());
                typeImpl.setInitExpressionUsesAllTypeParams(true);
                return;
            }
            // We don't have a static method that uses all type params either.
            // In this case, use the constructor call.
            final InitInfo initInfo = createConstructorCall(constructorInfo, initStrategy, recursiveCounter, numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
            typeImpl.setDefaultInitExpression(initInfo.initExpression());
            typeImpl.setInitExpression(initInfo.initExpression());
            typeImpl.addInitExpressionBeans(initInfo.initExpressionBeans());
            return;
        }

        // We could not find a constructor.
        // Try to find and call a static creator method; e.g. ResponseWrapper.successResponse(T).
        final StaticInitInfo staticInitInfo = tryGetStaticInitInfoForClassWithTypeParams(typeImpl, parameterType, initStrategy);
        if(staticInitInfo != null) {
            final InitInfo initInfo = callStaticInitMethodForClassWithTypeParams(staticInitInfo, initStrategy, recursiveCounter, numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
            typeImpl.setDefaultInitExpression(initInfo.initExpression());
            typeImpl.setInitExpression(initInfo.initExpression());
            typeImpl.addInitExpressionBeans(initInfo.initExpressionBeans());
            typeImpl.setInitExpressionUsesAllTypeParams(staticInitInfo.usesAllTypeParams());
            return;
        }

        // Try to find a builder.
        final BuilderInitInfo builderInitInfo = tryGetBuilderInfo(parameterType);
        if(builderInitInfo != null) {
            final InitInfo initInfo = callBuilderMethods(builderInitInfo, initStrategy, recursiveCounter, canonicalTextsInStack,
                    numberOfConstructorParams, numberOfBuilderMethodsCalled);
            typeImpl.setDefaultInitExpression(initInfo.initExpression());
            typeImpl.setInitExpression(initInfo.initExpression());
            if(builderInitInfo.allowBeans()) {
                typeImpl.addInitExpressionBeans(initInfo.initExpressionBeans());
            }
            return;
        }

        final String builderCall = TypeCreatorUtil.tryCreateSimpleBuilderCall(parameterType);
        if(builderCall != null) {
            typeImpl.setDefaultInitExpression(builderCall);
            typeImpl.setInitExpression(builderCall);
        }
    }

    @NotNull
    private InitInfo callStaticInitMethodForClassWithTypeParams(@NotNull final StaticInitInfo staticInitInfo, final InitStrategy initStrategy, final int recursiveCounter, final SQMutableInt numberOfConstructorParams, final SQMutableInt numberOfBuilderMethodsCalled, final Set<String> canonicalTextsInStack) {
        if(staticInitInfo.hasSingleTypeParamArg()) {
            return callStaticInitMethodWithSingleTypeParamArg(staticInitInfo, initStrategy, recursiveCounter, numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
        }
        return callStaticInitMethodWithMultipleParams(staticInitInfo, initStrategy, recursiveCounter, canonicalTextsInStack, numberOfConstructorParams, numberOfBuilderMethodsCalled);
    }

    private InitInfo callStaticInitMethodWithSingleTypeParamArg(@NotNull final StaticInitInfo staticInitInfo, final InitStrategy initStrategy, final int recursiveCounter, final SQMutableInt numberOfConstructorParams, final SQMutableInt numberOfBuilderMethodsCalled, final Set<String> canonicalTextsInStack) {
        // Create the static method call.
        final PsiParameter[] parameters = staticInitInfo.method().getParameterList().getParameters();
        final PsiParameter firstParam = parameters[0];
        final PsiSubstitutor genericSubstitutor = staticInitInfo.psiSubstitutor();
        final PsiType typeToUse;
        final Map<PsiTypeParameter, PsiType> subsMap = genericSubstitutor.getSubstitutionMap();
        final PsiType onlyValue = getOnlyValue(subsMap);
        if(onlyValue != null) {
            typeToUse = onlyValue;
        } else {
            PsiType firstParamType = firstParam.getType();
            if(firstParam.isVarArgs() && firstParamType instanceof PsiEllipsisType) {
                firstParamType = ((PsiEllipsisType) firstParamType).getComponentType();
            }
            typeToUse = TypeCreatorUtil.substituteByName(genericSubstitutor, firstParamType);
        }
        final TypeImpl firstTypeParam = createTypeForFormalParamRecursive("value", typeToUse, null, false, initStrategy, recursiveCounter + 1,
                numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
        String paramInitExpression = firstTypeParam.getInitExpression();
        final String initExpression = staticInitInfo.className() + "." + staticInitInfo.method().getName() + "(" + paramInitExpression + ")";
        return new InitInfo(initExpression, firstTypeParam.getInitExpressionBeans());
    }

    @Nullable
    private PsiType getOnlyValue(final Map<PsiTypeParameter, PsiType> subsMap) {
        if(subsMap.size() != 1) {
            return null;
        }
        return subsMap.values().iterator().next();
    }

    @NotNull
    private InitInfo callStaticInitMethodWithMultipleParams(
            @NotNull final StaticInitInfo staticInitInfo, final InitStrategy initStrategy, final int recursiveCounter,
            final Set<String> canonicalTextsInStack, final SQMutableInt numberOfConstructorParams, final SQMutableInt numberOfBuilderMethodsCalled) {
        return createStaticMethodCall(staticInitInfo.className(), staticInitInfo.psiSubstitutor(), staticInitInfo.method(),
                initStrategy, recursiveCounter, numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
    }

    public BuilderInitInfo tryGetBuilderInfo(final PsiType psiType) {
        final PsiClassType.ClassResolveResult resolveResult = PsiUtil.resolveGenericsClassInType(psiType);
        final PsiClass psiClass = resolveResult.getElement();
        if(psiClass == null || psiClass.getQualifiedName() == null) {
            return null;
        }
        if(isNonStaticInnerClass(psiClass)) {
            return null;
        }
        final Optional<BuilderInitInfo> builderInfoResult = builderInfoProvider.getBuilderInfo(psiClass);
        return builderInfoResult.orElse(null);
    }

    private InitInfo callBuilderMethods(final BuilderInitInfo builderInfo, final InitStrategy initStrategy, final int recursiveCounter, final Set<String> canonicalTextsInStack, final SQMutableInt numberOfConstructorParams, final SQMutableInt numberOfBuilderMethodsCalled) {
        final PsiMethod builderFactoryMethod = builderInfo.builderFactoryMethod();
        final StringBuilder initCall = new StringBuilder();
        final String canonicalName = builderInfo.targetClassQualifiedName();
        initCall.append(canonicalName).append(".");
        initCall.append(builderFactoryMethod.getName());
        // Handle the case where the builder factory method takes in a Class argument.
        // For example: software.amazon.awssdk.enhanced.dynamodb.model.WriteBatch.builder(Foo.class).
        // In this case use the actual Class object passed to the method.
        final ClassInfo classInfo = getClassArg(builderFactoryMethod);
        if(classInfo != null) {
            initCall.append("(").append(classInfo.psiClass().getQualifiedName()).append(".class").append(")\n");
        } else {
            initCall.append("()\n");
        }
        final List<PsiMethod> builderMethods = builderInfo.builderMethodsToCall();
        // Generate the builder method calls.
        final List<Api.Type> initExpressionBeans = new ArrayList<>();
        for(final PsiMethod method : builderMethods) {
            if(numberOfBuilderMethodsCalled.getValue() >= settingsProvider.getMaxNumberOfBuilderMethodsToCall()) {
                break;
            }
            final PsiParameter[] parameters = method.getParameterList().getParameters();
            if(Arrays.stream(parameters).anyMatch(x -> canonicalTextsInStack.contains(x.getType().getCanonicalText()))) {
                continue;
            }
            numberOfBuilderMethodsCalled.increment();
            // Convert PsiParameters to TypeImpl parameters.
            final List<TypeImpl> sqParams = Arrays.stream(parameters).map(x -> {
                TypeImpl possibleType = createTypeForFormalParamRecursive(x.getName(), x.getType(), x, false,
                        initStrategy, recursiveCounter + 1, numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
                if(classInfo != null && possibleType.isGeneric()) {
                    possibleType = createTypeForFormalParamRecursive(x.getName(), classInfo.psiType(), x, false,
                            initStrategy, recursiveCounter + 1, numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
                }
                return possibleType;
            }).toList();
            // We're going to invoke this setter method. Generate the call.
            initCall.append(".").append(method.getName()).append("(");
            for(int i = 0; i < sqParams.size(); i++) {
                final TypeImpl sqParam = sqParams.get(i);
                initCall.append(sqParam.getInitExpression());
                if(i != parameters.length - 1) {
                    initCall.append(", ");
                }
                initExpressionBeans.addAll(sqParam.getInitExpressionBeans());
            }
            initCall.append(")\n");
        }
        initCall.append(".").append(builderInfo.buildMethod().getName()).append("()");
        final String initCallString = removeExtraNewlines(canonicalName, initCall.toString());
        return new InitInfo(initCallString, initExpressionBeans);
    }

    @Nullable
    private ClassInfo getClassArg(final PsiMethod builderFactoryMethod) {
        final PsiParameter[] params = builderFactoryMethod.getParameterList().getParameters();
        if(params.length == 0) {
            return null;
        }
        final PsiParameter firstParam = params[0];
        final PsiType firstParamType = firstParam.getType();
        final PsiClass firstParamClass = PsiUtil.resolveClassInType(firstParamType);
        if(firstParamClass == null) {
            return null;
        }
        if(!Objects.equals(JavaNames.JavaLangClass, firstParamClass.getQualifiedName())) {
            return null;
        }
        CalledMethodInfo calledMethodInfo = calledMethodsInfoProvider.getSourceAndSuperMethodCallInfo(builderFactoryMethod);
        if(calledMethodInfo == null) {
            calledMethodInfo = calledMethodsInfoProvider.getStaticMethodCallInfo(builderFactoryMethod);
        }
        if(calledMethodInfo == null) {
            return null;
        }
        final List<PsiCall> methodCallExpressions = calledMethodInfo.getMethodCallExpressions();
        for(final PsiCall psiCall : methodCallExpressions) {
            final PsiExpressionList argumentList = psiCall.getArgumentList();
            if(argumentList == null) {
                continue;
            }
            final PsiExpression[] expressions = argumentList.getExpressions();
            if(expressions.length == 0) {
                continue;
            }
            final PsiExpression firstArg = expressions[0];
            if(!(firstArg instanceof final PsiClassObjectAccessExpression firstArgClassExpression)) {
                continue;
            }
            final PsiType firstClassType = firstArgClassExpression.getOperand().getType();
            final PsiClass firstArgClass = PsiUtil.resolveClassInType(firstClassType);
            if(firstArgClass == null) {
                continue;
            }
            final String firstArgQualifiedName = firstArgClass.getQualifiedName();
            if(firstArgQualifiedName == null) {
                continue;
            }
            return new ClassInfo(firstArgClass, firstClassType);
        }
        return null;
    }

    private void addOptionalTypeInfoIfNeeded(final TypeImpl typeImpl, final PsiType parameterType, final InitStrategy initStrategy) {
        if(initStrategy == InitStrategy.LastOption) {
            return;
        }
        if(isOptional(parameterType)) {
            typeImpl.setOptional(true);
            if(typeImpl.getOverridesEquals()) {
                typeImpl.setIsSimpleIfTypeParamsAreSimple(true);
            }
        }
    }

    private void addFailureInitInfo(
            final TypeImpl typeImpl, final PsiType psiType,
            final InitStrategy initStrategy, final int recursiveCounter, final SQMutableInt numberOfConstructorParams, final SQMutableInt numberOfBuilderMethodsCalled, final Set<String> canonicalTextsInStack) {

        // Resolve the class and any generics.
        final PsiClassType.ClassResolveResult resolveResult = PsiUtil.resolveGenericsClassInType(psiType);
        final PsiClass typeClass = resolveResult.getElement();
        final PsiSubstitutor typeSubstitutor = resolveResult.getSubstitutor();
        if(typeClass == null || typeClass.getQualifiedName() == null) {
            return;
        }
        if(typeClass.isEnum()) {
            return;
        }
        if(isNonStaticInnerClass(typeClass)) {
            return;
        }

        // Determine the static init method to use for the failure case.
        final PsiMethod methodToUse = findFailureInitMethod(typeClass);
        if(methodToUse == null) {
            return;
        }

        // Invoke the constructor.
        final String className = getNameToUseForInitCall(typeClass);
        if(className == null) {
            return;
        }
        final InitInfo initInfo = createStaticMethodCall(className, typeSubstitutor, methodToUse, initStrategy, recursiveCounter, numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);

        // Set the failure expression.
        typeImpl.setFailureInitExpression(initInfo.initExpression());
    }

    private void updateEmptyInitExpressionIfNeeded(@NotNull final TypeImpl typeImpl, final PsiType parameterType, final InitStrategy initStrategy, final int recursiveCounter, final SQMutableInt numberOfConstructorParams, final SQMutableInt numberOfBuilderMethodsCalled, final Set<String> canonicalTextsInStack) {
        if(typeImpl.isRecognized()) {
            updateEmptyInitExpressionForKnownTypeIfNeeded(typeImpl);
            return;
        }
        updateEmptyInitExpressionForUnknownTypeIfNeeded(typeImpl, parameterType, initStrategy, recursiveCounter, numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
    }

    private void updateEmptyInitExpressionForUnknownTypeIfNeeded(final TypeImpl typeImpl, final PsiType psiType, final InitStrategy initStrategy, final int recursiveCounter, final SQMutableInt numberOfConstructorParams, final SQMutableInt numberOfBuilderMethodsCalled, final Set<String> canonicalTextsInStack) {
        final PsiClassType.ClassResolveResult resolveResult = PsiUtil.resolveGenericsClassInType(psiType);
        final PsiClass psiClass = resolveResult.getElement();
        final PsiSubstitutor typeSubstitutor = resolveResult.getSubstitutor();
        if(psiClass == null) {
            return;
        }
        final String canonicalName = psiClass.getQualifiedName();
        if(canonicalName == null) {
            return;
        }
        if(isNonStaticInnerClass(psiClass)) {
            return;
        }

        // If this is an Iterable, determine it's empty init expression; e.g. Collections.emptyList().
        if(InheritanceUtil.isInheritor(psiClass, CommonClassNames.JAVA_LANG_ITERABLE)) {
            determineEmptyInitExpressionForIterable(typeImpl, psiClass, canonicalName);
            return;
        }

        // Handle the cases where the class ha a single type param AND the actual value for that type param has an
        // emptyInitExpression; e.g. ResponseWrapper<List<Foo>>.
        final Api.FluentList<Api.Type> sqTypeParams = typeImpl.getParameters();
        if(!(sqTypeParams.size() == 1 && sqTypeParams.get(0).getEmptyInitExpression() != null && psiClass.getTypeParameters().length == 1)) {
            return;
        }
        final String firstTypeParamEmptyInitExpression = sqTypeParams.get(0).getEmptyInitExpression();

        // Check to see if the class has a static creator method that uses the type parameter.
        // This covers cases like: Flux<List<Foo>>. In that case, the absentInitExpression will be: Flux.empty()
        // We want to set the empty init expression to Flux.just(Collections.emptyList()).
        // Note: Flux is from https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html.
        final PsiMethod staticCreatorMethod = TypeCreatorUtil.findPublicStaticCreatorMethodThatUsesSingleTypeParam(psiClass);
        if(staticCreatorMethod != null) {
            typeImpl.setEmptyInitExpression(canonicalName + "." + staticCreatorMethod.getName() + "(" + firstTypeParamEmptyInitExpression + ")");
            return;
        }

        // Check to see if the class has a constructor that uses the type parameter; e.g. in ResponseWrapper<List<Foo>>, ResponseWrapper has
        // constructor: ResponseWrapper(int statusCode, T payloadObject, String errorMessage).
        final PsiMethod[] constructors = psiClass.getConstructors();
        final List<PsiMethod> candidateConstructors = Arrays.stream(constructors)
                .filter(x -> x.hasModifierProperty(PsiModifier.PUBLIC) && !x.isDeprecated() && !isCopyMethod(psiClass, x))
                .toList();
        // Check to see if the class has a constructor that uses the type parameter.
        final PsiTypeParameter typeParameter = psiClass.getTypeParameters()[0];
        Optional<PsiMethod> constructorToUse = candidateConstructors.stream()
                .filter(x -> TypeCreatorUtil.hasTypeParameter(x, typeParameter, false))
                .min(Comparator.comparing(x -> x.getParameterList().getParameters().length));
        if(constructorToUse.isPresent()) {
            final String className = getNameToUseForInitCall(psiClass);
            if(className == null) {
                return;
            }
            final String constructorCall = createConstructorCallWithEmptyValueForTypeParam(psiClass, className, typeSubstitutor, constructorToUse.get(), typeParameter, firstTypeParamEmptyInitExpression, initStrategy, recursiveCounter, numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
            typeImpl.setEmptyInitExpression(constructorCall);
        }
    }

    private void determineEmptyInitExpressionForIterable(final TypeImpl typeImpl, final PsiClass typeClass, final String canonicalName) {
        final boolean isInterface = typeClass.isInterface();
        final boolean isInterfaceOrAbstract = isInterface || typeClass.hasModifierProperty(PsiModifier.ABSTRACT);

        // Search for a no-args static creator method to invoke.
        List<PsiMethod> candidateMethods = Arrays.stream(typeClass.getMethods())
                .filter(x -> (x.hasModifierProperty(PsiModifier.PUBLIC) || (isInterface && x.hasModifierProperty(PsiModifier.PACKAGE_LOCAL))) && x.hasModifierProperty(PsiModifier.STATIC)
                        && !x.isDeprecated() && x.getParameterList().getParameters().length == 0 && TypeCreatorUtil.hasSameType(
                        typeClass, x.getReturnType()))
                .toList();
        if(!candidateMethods.isEmpty()) {
            Optional<PsiMethod> methodToUse = candidateMethods.stream().filter(x -> x.getName().equals("empty")).findAny();
            if(methodToUse.isPresent()) {
                typeImpl.setEmptyInitExpression(canonicalName + "." + methodToUse.get().getName() + "()");
                return;
            }

            methodToUse = candidateMethods.stream().filter(x -> StringUtils.containsAny(x.getName(), "empty", "Empty", "absent", "Absent")).findAny();
            if(methodToUse.isPresent()) {
                typeImpl.setEmptyInitExpression(canonicalName + "." + methodToUse.get().getName() + "()");
                return;
            }

            typeImpl.setEmptyInitExpression(canonicalName + "." + candidateMethods.get(0).getName() + "()");
        }

        // If this is an interface or abstract, bail out.
        // We can't call a constructor.
        if(isInterfaceOrAbstract) {
            return;
        }

        // Determine the name to use for the constructor call.
        String name = typeClass.getQualifiedName();
        if(name == null) {
            name = typeClass.getName();
        }
        final String genericStr = typeClass.hasTypeParameters() ? "<>" : "";

        // Search for a no-args constructor to invoke.
        final PsiMethod[] constructors = typeClass.getConstructors();
        final List<PsiMethod> candidateConstructors = Arrays.stream(constructors)
                .filter(x -> x.hasModifierProperty(PsiModifier.PUBLIC) && !x.isDeprecated() && !isCopyMethod(typeClass, x))
                .toList();
        final Optional<PsiMethod> noArgConstructor = candidateConstructors.stream().filter(x -> x.getParameterList().getParameters().length == 0).findAny();
        if(constructors.length == 0 || noArgConstructor.isPresent()) {
            final String constructorCallExpression = "new " + name + genericStr + "()";
            typeImpl.setEmptyInitExpression(constructorCallExpression);
            return;
        }

        // Search for a constructor that takes in a single argument that is a known type with an emptyInitExpression.
        final Optional<PsiMethod> listArgConstructor = candidateConstructors.stream().filter(x -> x.getParameterList().getParameters().length == 1 && hasEmptyInitExpression(x.getParameterList().getParameters()[0])).findAny();
        if(listArgConstructor.isPresent()) {
            final String emptyInitExpressionConstructorCall = getEmptyInitExpression(listArgConstructor.get().getParameterList().getParameters()[0]);
            final String constructorCallExpression = "new " + name + genericStr + "(" + emptyInitExpressionConstructorCall + ")";
            typeImpl.setEmptyInitExpression(constructorCallExpression);
        }
    }

    private boolean hasEmptyInitExpression(final PsiParameter parameter) {
        return getEmptyInitExpression(parameter) != null;
    }

    @Nullable
    private String getEmptyInitExpression(final PsiParameter parameter) {
        // Check to see if the parameter is a known type with an emptyInitExpression.
        final PsiClass typeClass = PsiUtil.resolveClassInType(parameter.getType());
        if(typeClass == null) {
            return null;
        }
        final String canonicalName = typeClass.getQualifiedName();
        if(canonicalName == null) {
            return null;
        }
        final DefaultTypeInfo defaultTypeInfo = javaGroovyCommonTypesCreator.createType(canonicalName, parameter.getName(), parameter);
        if(defaultTypeInfo != null) {
            return defaultTypeInfo.getEmptyInitExpression();
        }
        return null;
    }

    private boolean isInKnownCollectionsPackage(final PsiType parameterType) {
        final PsiClass typeClass = PsiUtil.resolveClassInType(parameterType);
        if(typeClass == null) {
            return false;
        }
        final String canonicalName = typeClass.getQualifiedName();
        if(canonicalName == null) {
            return false;
        }
        return StringUtils.startsWithAny(canonicalName, OpenSourceCollectionsPackages);
    }

    @NotNull
    private ResolveTypeInfo resolveTypeParams(
            final String parameterName, final PsiType psiType, final InitStrategy initStrategy,
            final int recursiveCounter, final SQMutableInt numberOfConstructorParams,
            final SQMutableInt numberOfBuilderMethodsCalled, final Set<String> canonicalTextsInStack) {
        final int constructorParamsBefore = numberOfConstructorParams.intValue();
        final int builderMethodsBefore = numberOfBuilderMethodsCalled.intValue();
        final List<Api.Type> params = resolveTypeParamsImpl(parameterName, psiType, initStrategy, recursiveCounter, numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
        return new ResolveTypeInfo(params, numberOfConstructorParams.intValue() - constructorParamsBefore, numberOfBuilderMethodsCalled.intValue() - builderMethodsBefore);
    }

    @NotNull
    private List<Api.Type> resolveTypeParamsImpl(
            final String parameterName, final PsiType psiType, final InitStrategy initStrategy,
            final int recursiveCounter, final SQMutableInt numberOfConstructorParams,
            final SQMutableInt numberOfBuilderMethodsCalled, final Set<String> canonicalTextsInStack) {
        // If the type is varargs, treat it as though it is the component type.
        PsiType typeToUse = psiType;
        if(psiType instanceof PsiEllipsisType) {
            typeToUse = ((PsiEllipsisType) psiType).getComponentType();
        }

        if(typeToUse instanceof final PsiArrayType psiArrayType) {
            final PsiType type = psiArrayType.getComponentType();
            final Api.Type apiType = createTypeForFormalParamRecursive(parameterName, type, null, false, initStrategy, recursiveCounter + 1,
                    numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
            return Collections.singletonList(apiType);
        }

        final PsiClassType.ClassResolveResult resolveResult = PsiUtil.resolveGenericsClassInType(typeToUse);
        final PsiClass psiClass = resolveResult.getElement();
        final PsiSubstitutor typeSubstitutor = resolveResult.getSubstitutor();
        if(psiClass == null || psiClass.getQualifiedName() == null) {
            return Collections.emptyList();
        }
        final PsiTypeParameterList typeParameterList = psiClass.getTypeParameterList();
        if(typeParameterList == null) {
            return Collections.emptyList();
        }
        final List<Api.Type> ret = new ArrayList<>();
        for(final PsiTypeParameter param : typeParameterList.getTypeParameters()) {
            TypeImpl apiType;
            PsiType paramValue = typeSubstitutor.substitute(param);
            if(paramValue != null) {
                final WildcardInfo info = wildcardInfoProvider.getWildcardInfoWithReplacement(paramValue);
                apiType = createTypeForFormalParamRecursive("value", info.adjustedType(), null, info.isWildcard(), initStrategy, recursiveCounter + 1,
                        numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
            } else {
                apiType = createTypeFromGeneric(param);
            }
            ret.add(apiType);
        }
        return ret;
    }

    @NotNull
    protected TypeImpl createTypeFromConfig(
            @NotNull final String parameterName, @NotNull final PsiType parameterType, final PsiElement source, final InitStrategy initStrategy) {

        if(parameterType instanceof PsiPrimitiveType) {
            return createTypeFromPrimitive((PsiPrimitiveType) parameterType, parameterName, source);
        }

        if(parameterType instanceof PsiEllipsisType) {
            return createTypeFromEllipsis((PsiEllipsisType) parameterType, parameterName, initStrategy, source);
        }

        if(parameterType instanceof PsiArrayType) {
            return createTypeFromArrayType((PsiArrayType) parameterType, parameterName, initStrategy, source);
        }

        // Resolve the class.
        final PsiClass psiClass = CompiledUtils.getClassWithSourceCode(PsiUtil.resolveClassInType(parameterType));

        // Handle the generic type case.
        if(psiClass instanceof PsiTypeParameter) {
            // The class is a generic; e.g. T, K.
            return createTypeFromClassType(parameterType);
        }

        // Create the type from the resolved type.
        if(psiClass != null) {
            return createTypeFromPsiClass(psiClass, parameterType, parameterType.getCanonicalText(), TypeCreatorUtil.determineName(parameterType), parameterName, initStrategy, source);
        }

        final String name = TypeCreatorUtil.determineName(parameterType);
        return new TypeImpl(name, null, parameterType.getCanonicalText(), DefaultInitExpression, null, false, "null", null, null, null, null, null, Api.FluentListFactory.emptyList(), SuperTypesProvider.DefaultSuperTypes, false,
                false, AccessModifier.Public, true, true, false, false, TypeCreatorUtil.overridesEquals(psiClass), false, false, false, false, false, false,
                false, false, false, false, false, false, false, initStrategy);
    }

    protected TypeImpl createTypeFromArrayType(final PsiArrayType parameterType, final String parameterName, final InitStrategy initStrategy, final PsiElement source) {
        final String canonicalText = parameterType.getCanonicalText();
        final int dimension = parameterType.getArrayDimensions();
        // Determine the init expression. Use a special init expression for byte[]s.
        String initExpression;
        String emptyInitExpression;
        final PsiType componentType = parameterType.getComponentType();
        if(PsiTypes.byteType().equals(componentType) && dimension == 1) {
            initExpression = "\"content\".getBytes()";
            emptyInitExpression = null;
        } else {
            initExpression = String.format(ArrayInitFormat, canonicalText);
            emptyInitExpression = String.format(ArrayInitFormat, canonicalText);
        }

        // Determine the placeholder string.
        final String innerTypeCanonicalText = parameterType.getDeepComponentType().getCanonicalText();
        final String placeholderString = "new " + innerTypeCanonicalText + StringUtils.repeat("[]", dimension)
                + StringUtils.repeat("{", dimension) + "{{VALUE1}}" + StringUtils.repeat("}", dimension);

        return new TypeImpl(TypeCreatorUtil.determineName(parameterType), null, canonicalText, initExpression, placeholderString, false, "null", emptyInitExpression, null, null, null, null, Api.FluentListFactory.emptyList(), SuperTypesProvider.DefaultSuperTypes, false,
                false, AccessModifier.Public, false, false, true, false, false, false, false, false, false, false, false,
                false, false, true, isSimpleArray(parameterType, parameterName, initStrategy, source), false, false, false, initStrategy);
    }

    private TypeImpl createTypeFromClassType(final PsiType type) {
        return new TypeImpl(TypeCreatorUtil.determineName(type), null, type.getCanonicalText(), DefaultInitExpression, null, false, "null", null, null, null, null, null, Api.FluentListFactory.emptyList(), SuperTypesProvider.DefaultSuperTypes, false,
                false, AccessModifier.Public, false, false, false, false, false, false, true, false, false, false, false,
                false, false, false, false, false, false, false, InitStrategy.Default);
    }

    private TypeImpl createTypeFromGeneric(final PsiTypeParameter type) {
        final String typeName = type.getName() != null ? type.getName() : "Type";
        return new TypeImpl(typeName, null, typeName, DefaultInitExpression, null, false, "null", null, null, null, null, null, Api.FluentListFactory.emptyList(), SuperTypesProvider.DefaultSuperTypes, false,
                false, AccessModifier.Public, false, false, false, false, false, false, true, false, false, false, false,
                false, false, false, false, false, false, false, InitStrategy.Default);
    }

    private TypeImpl createTypeFromEllipsis(final PsiEllipsisType parameterType, final String parameterName, final InitStrategy initStrategy, final PsiElement source) {
        final PsiType componentType = parameterType.getComponentType();
        return createTypeFromConfig(parameterName, componentType, source, initStrategy);
    }

    protected TypeImpl createTypeFromPsiClass(final PsiClass psiClass, PsiType psiType, final String canonicalText, final String parameterTypeName, final String parameterName, final InitStrategy initStrategy, final PsiElement source) {
        boolean isClosable = isClosable(psiClass);
        String canonicalName;
        final boolean isMockable =
                !(psiClass.hasModifierProperty(PsiModifier.FINAL)
                        || psiClass.isEnum()
                        || psiClass.hasModifierProperty(PsiModifier.STATIC));
        canonicalName = psiClass.getQualifiedName();
        final boolean isClassT = Objects.equals(JavaNames.JavaLangClass, canonicalName);
        final boolean isEnum = psiClass.isEnum();
        final DefaultTypeInfo defaultTypeInfo = javaGroovyCommonTypesCreator.createType(canonicalName, parameterName, source);
        final String typeName = psiClass.getName() != null ? psiClass.getName() : parameterTypeName;
        final boolean isNested = psiClass.getContainingClass() != null;
        final boolean isStatic = psiClass.hasModifierProperty(PsiModifier.STATIC);
        final boolean isInterface = psiClass.isInterface();
        final boolean isAbstract = psiClass.hasModifierProperty(PsiModifier.ABSTRACT);
        final AccessModifier accessModifier = PsiConverterUtils.modifierFromPsi(psiClass);

        final Api.FluentList<String> superTypeCanonicalNames = superTypesProvider.getSuperTypes(psiClass);
        if(defaultTypeInfo != null) {
            boolean hasJava9Support = testDependencyInfoProvider.testPathIsAtLeastJava9();
            // Determine if the source was called with a special init expression that we want to use.
            String initExpression;
            boolean overridesEquals;
            if(isEnum) {
                initExpression = enumValueProvider.getInitExpressionForEnum(source);
            } else {
                initExpression = javaLibraryReferenceProvider.getInitExpressionForJavaLibraryReference(source);
            }
            if(initExpression == null) {
                initExpression = hasJava9Support && defaultTypeInfo.getJava9InitExpression() != null
                        ? defaultTypeInfo.getJava9InitExpression() : defaultTypeInfo.getInitExpression();
                overridesEquals = defaultTypeInfo.overridesEquals();
            } else {
                // If the source was called with a special enum constant or reference to a known library constant,
                // treat the value as though it overridesEquals. In the enum case, this is true. In the library case,
                // reference equals will do the right thing.
                overridesEquals = true;
            }
            // Determine the init expressions with placeholders.
            String initExpressionWithPlaceholder = hasJava9Support && defaultTypeInfo.getJava9InitExpressionWithTypePlaceholder() != null
                    ? defaultTypeInfo.getJava9InitExpressionWithTypePlaceholder() : defaultTypeInfo.getInitExpressionWithTypePlaceholder();
            String failureInitExpression = hasJava9Support && defaultTypeInfo.getJava9FailureInitExpression() != null
                    ? defaultTypeInfo.getJava9FailureInitExpression() : defaultTypeInfo.getFailureInitExpression();
            String canonicalTextToUse = canonicalText;
            if(defaultTypeInfo.getCanonicalTextOverride() != null) {
                canonicalTextToUse = defaultTypeInfo.getCanonicalTextOverride();
            }
            return new TypeImpl(typeName, canonicalName, canonicalTextToUse, initExpression, initExpressionWithPlaceholder, defaultTypeInfo.isOptional(), defaultTypeInfo.getAbsentInitExpression(), defaultTypeInfo.getEmptyInitExpression(), null, null, failureInitExpression, defaultTypeInfo.getFailureInitExpressionWithTypePlaceholder(), new FluentListImpl<>(defaultTypeInfo.getImportsRequired()), superTypeCanonicalNames, isNested,
                    isStatic, accessModifier, defaultTypeInfo.isMockable, defaultTypeInfo.getShouldBeMocked(), false, defaultTypeInfo.isPrimitive(), overridesEquals, isClassT, false, defaultTypeInfo.isBean(), defaultTypeInfo.isBeanWithInputIOProperty(), isEnum, isClosable,
                    isInterface, isAbstract, true, defaultTypeInfo.isSimple(), defaultTypeInfo.isSimpleIfTypeParamsAreSimple(),
                    defaultTypeInfo.isAbsentIfTypeParamsAreAbsent(), defaultTypeInfo.isEmptyIfTypeParamsAreEmpty(), initStrategy);
        }

        // Determine the init expression.
        String initExpression;
        boolean overridesEquals;
        boolean isRecognized = false;
        if(isEnum) {
            initExpression = enumValueProvider.getInitExpressionForEnum(source);
            if(initExpression == null) {
                initExpression = getFirstEnumValue(psiClass);
            }
            overridesEquals = true;
            isRecognized = true;
        } else {
            initExpression = javaLibraryReferenceProvider.getInitExpressionForJavaLibraryReference(source);
            if(initExpression == null) {
                initExpression = DefaultInitExpression;
                overridesEquals = TypeCreatorUtil.overridesEquals(psiClass);
            } else {
                overridesEquals = true;
                isRecognized = true;
            }
        }

        // We don't have a config entry for this type.
        boolean isDTOBean = beanInfoProvider.isBean(psiClass, initStrategy);
        boolean isDTOBeanWithInputIoProperty = beanInfoProvider.isDtoBeanWithInputIoProperty(psiClass);
        return new TypeImpl(typeName, canonicalName, canonicalText, initExpression, null, false, "null", null, null, null, null, null, Api.FluentListFactory.emptyList(), superTypeCanonicalNames, isNested,
                isStatic, accessModifier, isMockable, isMockable, false, false, overridesEquals, isClassT, false, isDTOBean, isDTOBeanWithInputIoProperty, isEnum, isClosable,
                isInterface, isAbstract, isRecognized, isEnum /* Enums are simple types. */, false, false, false, initStrategy);
    }

    protected TypeImpl createTypeFromPrimitive(final PsiPrimitiveType parameterType, final String parameterName, final PsiElement source) {
        final String canonicalName = parameterType.getCanonicalText();
        final DefaultTypeInfo defaultTypeInfo = javaGroovyCommonTypesCreator.createType(canonicalName, parameterName, source);
        if(defaultTypeInfo != null) {
            return new TypeImpl(TypeCreatorUtil.determineName(parameterType), canonicalName, canonicalName, defaultTypeInfo.getInitExpression(), defaultTypeInfo.getInitExpressionWithTypePlaceholder(), defaultTypeInfo.isOptional(), defaultTypeInfo.getAbsentInitExpression(), defaultTypeInfo.getEmptyInitExpression(), null, null, defaultTypeInfo.getFailureInitExpression(), null, new FluentListImpl<>(defaultTypeInfo.getImportsRequired()), SuperTypesProvider.NoSuperTypes, false,
                    false, AccessModifier.Public, defaultTypeInfo.isMockable, false, false, defaultTypeInfo.isPrimitive(), defaultTypeInfo.overridesEquals(), false, false, false, defaultTypeInfo.isBeanWithInputIOProperty(), false, false,
                    false, false, true, defaultTypeInfo.isSimple(), false, defaultTypeInfo.isAbsentIfTypeParamsAreAbsent(),
                    defaultTypeInfo.isEmptyIfTypeParamsAreEmpty(),
                    InitStrategy.Default);
        }
        // This shouldn't happen. The only way this can happen is if Java adds a new primitive type; e.g. a new int, byte, etc.
        return new TypeImpl(TypeCreatorUtil.determineName(parameterType), null, parameterType.getCanonicalText(), DefaultInitExpression, null, false, "null", null, null, null, null, null, Api.FluentListFactory.emptyList(), SuperTypesProvider.NoSuperTypes, false,
                false, AccessModifier.Public, false, false, false, false, false, false, true, false, false, false, false,
                false, false, false, false, false, false, false, InitStrategy.Default);
    }

    protected final boolean isSimpleArray(final PsiArrayType parameterType, final String parameterName, final InitStrategy initStrategy, final PsiElement source) {
        final PsiType componentType = parameterType.getComponentType();
        if(componentType instanceof PsiArrayType) {
            // Multidimensional arrays (2D, 3D, etc) are not simple types. They should be initialized in local fields.
            return false;
        }
        return createTypeFromConfig(parameterName, componentType, source, initStrategy).isSimple();
    }

    @Nullable
    private ConstructorInfo tryGetConstructorInfo(
            @NotNull final PsiType psiType, @NotNull final InitStrategy initStrategy) {
        final PsiClassType.ClassResolveResult resolveResult = PsiUtil.resolveGenericsClassInType(psiType);
        final PsiClass psiClass = resolveResult.getElement();
        final PsiSubstitutor typeSubstitutor = resolveResult.getSubstitutor();
        if(psiClass == null || psiClass.getQualifiedName() == null) {
            return null;
        }
        if(isNonStaticInnerClass(psiClass)) {
            return null;
        }
        final String className = getNameToUseForInitCall(psiClass);
        if(className == null) {
            return null;
        }
        if(psiClass.isInterface() || psiClass.isEnum() || psiClass.hasModifierProperty(PsiModifier.ABSTRACT)) {
            return null;
        }

        final PsiMethod[] constructors = psiClass.getConstructors();
        if(constructors.length == 0) {
            // Call the default, no-args constructor provided by the Java language spec.
            final String defaultConstructorCall = TypeCreatorUtil.createDefaultConstructorCall(psiClass);
            if(defaultConstructorCall != null) {
                return new ConstructorInfo(psiClass, className, typeSubstitutor, null, true);
            }
            return null;
        }

        final PsiMethod constructor = chooseConstructor(psiClass, constructors, initStrategy);
        if(constructor == null) {
            return null;
        }
        return new ConstructorInfo(psiClass, className, typeSubstitutor, constructor, true);
    }

    private ConstructorInfo tryGetConstructorInfoForTypeWithParams(final PsiType psiType, final InitStrategy initStrategy) {
        final PsiClassType.ClassResolveResult resolveResult = PsiUtil.resolveGenericsClassInType(psiType);
        final PsiClass psiClass = resolveResult.getElement();
        final PsiSubstitutor typeSubstitutor = resolveResult.getSubstitutor();
        if(psiClass == null || psiClass.getQualifiedName() == null) {
            return null;
        }
        if(psiClass.isInterface() || psiClass.isEnum() || psiClass.hasModifierProperty(PsiModifier.ABSTRACT)) {
            return null;
        }
        if(isNonStaticInnerClass(psiClass)) {
            return null;
        }
        final String className = getNameToUseForInitCall(psiClass);
        if(className == null) {
            return null;
        }
        final PsiMethod[] constructors = psiClass.getConstructors();
        if(constructors.length == 0) {
            // Call the default, no-args constructor provided by the Java language spec.
            return new ConstructorInfo(psiClass, className, typeSubstitutor, null, false);
        }

        final PsiMethod constructor = chooseConstructorForClassWithTypeParams(psiClass, constructors, initStrategy);
        if(constructor == null) {
            return null;
        }
        return new ConstructorInfo(psiClass, className, typeSubstitutor, constructor, TypeCreatorUtil.usesAllTypeParams(psiClass, constructor));
    }

    @Nullable
    private PsiMethod chooseConstructor(
            final PsiClass psiClass, final PsiMethod[] constructors, final InitStrategy initStrategy) {
        // Find all public, non-deprecated constructors.
        final List<PsiMethod> candidateConstructors = Arrays.stream(constructors)
                .filter(x -> {
                    if(x.isDeprecated() || isCopyMethod(psiClass, x)) {
                        return false;
                    }
                    if(isLombokBuilderConstructor(psiClass, x)) {
                        return false;
                    }
                    return visibilityInfoProvider.isVisibleToTestClass(x);
                }).toList();
        if(candidateConstructors.isEmpty()) {
            return null;
        }

        // We only found one constructor. Use it.
        if(candidateConstructors.size() == 1) {
            return candidateConstructors.get(0);
        }

        // We have two constructors. If one of them is a no-args constructor, it's likely the other is a
        // convenience constructor. The no-args constructor is likely required for a reflection based serializer.
        // The convenience constructor is created for developers to use.
        if(candidateConstructors.size() == 2) {
            if(candidateConstructors.get(0).getParameterList().getParameters().length == 0) {
                if(initStrategy.ordinal() >= InitStrategy.DataBeans.ordinal() && beanInfoProvider.isBean(psiClass, initStrategy)) {
                    return candidateConstructors.get(0);
                }
                if(initStrategy.ordinal() >= InitStrategy.GeneralBeans.ordinal() && beanInfoProvider.isBean(psiClass, initStrategy)) {
                    return candidateConstructors.get(0);
                }
                if(initStrategy.ordinal() >= InitStrategy.OtherOption.ordinal()) {
                    return candidateConstructors.get(0);
                }
                return candidateConstructors.get(1);
            }
            if(candidateConstructors.get(1).getParameterList().getParameters().length == 0) {
                if(initStrategy.ordinal() >= InitStrategy.DataBeans.ordinal() && beanInfoProvider.isBean(psiClass, initStrategy)) {
                    return candidateConstructors.get(1);
                }
                if(initStrategy.ordinal() >= InitStrategy.GeneralBeans.ordinal() && beanInfoProvider.isBean(psiClass, initStrategy)) {
                    return candidateConstructors.get(1);
                }
                if(initStrategy.ordinal() >= InitStrategy.OtherOption.ordinal()) {
                    return candidateConstructors.get(1);
                }
                return candidateConstructors.get(0);
            }
        }

        // If the class is a record or use the longest constructor.
        if(psiClass.isRecord()) {
            if(initStrategy.ordinal() >= InitStrategy.OtherOption.ordinal()) {
                return candidateConstructors.stream()
                        .min(Comparator.comparing(x -> x.getParameterList().getParameters().length)).get();
            }
            return candidateConstructors.stream()
                    .max(Comparator.comparing(x -> x.getParameterList().getParameters().length)).get();
        }

        // Determine if we should use the no-arg constructor.
        final Optional<PsiMethod> noArgConstructor = candidateConstructors.stream().filter(x -> x.getParameterList().getParameters().length == 0).findAny();
        final boolean hasDataAnnotation = SQAnnotationUtil.hasDataAnnotation(psiClass);
        if(hasDataAnnotation && initStrategy.ordinal() >= InitStrategy.DataBeans.ordinal() && noArgConstructor.isPresent() && beanInfoProvider.isBean(psiClass, initStrategy)) {
            return noArgConstructor.get();
        }
        if(initStrategy.ordinal() >= InitStrategy.GeneralBeans.ordinal() && noArgConstructor.isPresent() && beanInfoProvider.isBean(psiClass, initStrategy)) {
            return noArgConstructor.get();
        }
        if(initStrategy.ordinal() >= InitStrategy.OtherOption.ordinal() && noArgConstructor.isPresent()) {
            return noArgConstructor.get();
        }

        // If the class's source code is in the project (writable), use the longest constructor.
        if(hasDataAnnotation || psiClass.isWritable()) {
            return candidateConstructors.stream()
                    .max(Comparator.comparing(x -> x.getParameterList().getParameters().length)).get();
        }

        // Check to see if we have a constructor with at least one parameter, where all parameters are
        // a know type or an enum. Use the longest constructor if present.
        Optional<PsiMethod> constructorToUse = candidateConstructors.stream()
                .filter(x -> x.getParameterList().getParameters().length != 0 && allArgsKnownOrEnum(x, initStrategy))
                .max(Comparator.comparing(x -> x.getParameterList().getParameters().length));
        if(constructorToUse.isPresent()) {
            return constructorToUse.get();
        }

        // Use the shortest constructor.
        constructorToUse = candidateConstructors.stream()
                .min(Comparator.comparing(x -> x.getParameterList().getParameters().length));
        return constructorToUse.get();
    }

    private boolean isLombokBuilderConstructor(final PsiClass psiClass, final PsiMethod method) {
        if(!method.isConstructor()) {
            return false;
        }
        final PsiParameter[] params = method.getParameterList().getParameters();
        if(params.length != 1) {
            return false;
        }
        if(!(params[0] instanceof LightElement)) {
            return false;
        }
        final PsiClass paramClass = PsiUtil.resolveClassInType(params[0].getType());
        if(paramClass == null) {
            return false;
        }
        final PsiMethod builderFactoryMethod = LombokBuilderSourceVariableProvider.getBuilderFactoryMethod(psiClass);
        if(builderFactoryMethod == null) {
            return false;
        }
        final PsiClass builderClass = LombokBuilderSourceVariableProvider.getBuilderClass(builderFactoryMethod);
        if(builderClass == null) {
            return false;
        }
        final String builderClassQualifiedName = builderClass.getQualifiedName();
        if(builderClassQualifiedName == null) {
            return false;
        }
        if(StringUtils.equals(paramClass.getQualifiedName(), builderClassQualifiedName)) {
            return true;
        }
        return false;
    }

    private PsiMethod chooseConstructorForClassWithTypeParams(final PsiClass psiClass, final PsiMethod[] constructors, final InitStrategy initStrategy) {
        // Find all public, non-deprecated constructors.
        final List<PsiMethod> candidateConstructors = Arrays.stream(constructors)
                .filter(x -> {
                    if(x.isDeprecated()) {
                        return false;
                    }
                    if(isCopyMethod(psiClass, x)) {
                        return false;
                    }
                    return visibilityInfoProvider.isVisibleToTestClass(x);
                }).collect(Collectors.toList());
        if(candidateConstructors.isEmpty()) {
            return null;
        }

        // We only found one constructor. Use it.
        if(candidateConstructors.size() == 1) {
            return candidateConstructors.get(0);
        }

        // If the class has a single type parameter, try to find a constructor that uses it.
        Optional<PsiMethod> constructorToUse = tryFindConstructorUsingSingleTypeParameter(psiClass, candidateConstructors);
        if(constructorToUse.isPresent()) {
            return constructorToUse.get();
        }

        // If the class is a custom Map, try to find a constructor that takes in a Map.
        constructorToUse = tryFindConstructorUsingMapPattern(psiClass, candidateConstructors);
        if(constructorToUse.isPresent()) {
            return constructorToUse.get();
        }

        // Try to find a constructor that uses all type params.
        constructorToUse = tryFindConstructorUsingAllTypeParam(psiClass, candidateConstructors);
        if(constructorToUse.isPresent()) {
            return constructorToUse.get();
        }

        if(psiClass.getTypeParameters().length > 1) {
            constructorToUse = tryFindConstructorUsingAnyTypeParam(psiClass, candidateConstructors);
            if(constructorToUse.isPresent()) {
                return constructorToUse.get();
            }
        }

        // We have two constructors. If one of them is a no-args constructor, it's likely the other is a
        // convenience constructor. The no-args constructor is likely required for a reflection based serializer.
        // The convenience constructor is created for developers to use.
        if(candidateConstructors.size() == 2) {
            if(candidateConstructors.get(0).getParameterList().getParameters().length == 0) {
                return candidateConstructors.get(1);
            }
            if(candidateConstructors.get(1).getParameterList().getParameters().length == 0) {
                return candidateConstructors.get(0);
            }
        }

        // If the class is a record or use the longest constructor.
        if(psiClass.isRecord()) {
            return candidateConstructors.stream()
                    .max(Comparator.comparing(x -> x.getParameterList().getParameters().length)).get();
        }

        // Determine if we should use the no-arg constructor.
        final boolean hasDataAnnotation = SQAnnotationUtil.hasDataAnnotation(psiClass);
        // If the class's source code is in the project (writable), use the longest constructor.
        if(hasDataAnnotation || psiClass.isWritable()) {
            return candidateConstructors.stream()
                    .max(Comparator.comparing(x -> x.getParameterList().getParameters().length)).get();
        }

        // Check to see if we have a constructor with at least one parameter, where all parameters are
        // a know type or an enum. Use the longest constructor if present.
        constructorToUse = candidateConstructors.stream()
                .filter(x -> x.getParameterList().getParameters().length != 0 && allArgsKnownOrEnum(x, initStrategy))
                .max(Comparator.comparing(x -> x.getParameterList().getParameters().length));
        if(constructorToUse.isPresent()) {
            return constructorToUse.get();
        }

        // Use the shortest constructor.
        constructorToUse = candidateConstructors.stream()
                .min(Comparator.comparing(x -> x.getParameterList().getParameters().length));
        return constructorToUse.get();
    }

    private Optional<PsiMethod> tryFindConstructorUsingMapPattern(final PsiClass psiClass, final List<PsiMethod> candidateConstructors) {
        final String canonicalName = psiClass.getQualifiedName();
        if(canonicalName == null) {
            return Optional.empty();
        }
        if(!canonicalName.endsWith("Map")) {
            return Optional.empty();
        }
        final PsiTypeParameter[] typeParameters = psiClass.getTypeParameters();
        if(typeParameters.length != 2) {
            return Optional.empty();
        }
        if(!InheritanceUtil.isInheritor(psiClass, CommonClassNames.JAVA_UTIL_MAP)) {
            return Optional.empty();
        }

        // We have a Map with two type parameters. Find a constructor that takes in a map.
        return candidateConstructors.stream().filter(x -> hasMapWithTypeParameters(x, typeParameters[0], typeParameters[1]))
                .min(Comparator.comparing(x -> x.getParameterList().getParameters().length));
    }

    private Optional<PsiMethod> tryFindConstructorUsingSingleTypeParameter(
            final PsiClass psiClass, final List<PsiMethod> candidateConstructors) {
        // If the class has one type parameter, find a constructor that uses the type parameter.
        if(psiClass.getTypeParameters().length == 1) {
            // Check to see if the class has a constructor that uses the type parameter.
            final PsiTypeParameter typeParameter = psiClass.getTypeParameters()[0];
            return tryFindConstructorUsingTypeParam(candidateConstructors, typeParameter);
        }
        return Optional.empty();
    }

    private Optional<PsiMethod> tryFindConstructorUsingTypeParam(final List<PsiMethod> candidateConstructors, final PsiTypeParameter typeParameter) {
        Optional<PsiMethod> constructorToUse = candidateConstructors.stream()
                .filter(x -> TypeCreatorUtil.hasTypeParameter(x, typeParameter, false))
                .min(Comparator.comparing(x -> x.getParameterList().getParameters().length));
        if(constructorToUse.isPresent()) {
            return constructorToUse;
        }
        // Check to see if the class has a constructor that uses the type parameter as a vararg (e.g. T...).
        constructorToUse = candidateConstructors.stream()
                .filter(x -> TypeCreatorUtil.hasTypeParameter(x, typeParameter, true))
                .min(Comparator.comparing(x -> x.getParameterList().getParameters().length));
        if(constructorToUse.isPresent()) {
            return constructorToUse;
        }
        // Check to see if we have a constructor with a known type that has the type parameter as an inner type;
        // e.g. PageImpl<T> has a constructor that takes in a List<T>.
        constructorToUse = candidateConstructors.stream()
                .filter(x -> hasKnownTypeWithTypeParameter(x, typeParameter))
                .min(Comparator.comparing(x -> x.getParameterList().getParameters().length));
        if(constructorToUse.isPresent()) {
            return constructorToUse;
        }
        // Otherwise, check to see if we have a constructor with an unknown type that has our type parameter;
        // e.g. CallImpl<T> takes in a Response<T>.
        constructorToUse = candidateConstructors.stream()
                .filter(x -> hasTypeWithTypeParameter(x, typeParameter))
                .min(Comparator.comparing(x -> x.getParameterList().getParameters().length));
        return constructorToUse;
    }

    private Optional<PsiMethod> tryFindConstructorUsingAllTypeParam(final PsiClass psiClass, final List<PsiMethod> candidateConstructors) {
        final PsiTypeParameter[] typeParameters = psiClass.getTypeParameters();
        final Set<PsiTypeParameter> typeParamsSet = SetUtils.newIdentityHashSet();
        typeParamsSet.addAll(Arrays.asList(typeParameters));
        List<PsiMethod> constructorsToUse = new ArrayList<>();
        for(final PsiMethod constructor : candidateConstructors) {
            final Set<PsiTypeParameter> usedTypeParams = TypeCreatorUtil.getUsedTypeParams(constructor);
            if(usedTypeParams.containsAll(typeParamsSet)) {
                constructorsToUse.add(constructor);
            }
        }

        // Try to find methods that only use simple type params as args (not Map.Entry<>).
        final List<PsiMethod> constructorsUsingSimpleTypeArgs = constructorsToUse.stream().filter(x -> allParamsSimpleTypeParamArgs(psiClass, x)).collect(Collectors.toList());
        if(!constructorsUsingSimpleTypeArgs.isEmpty()) {
            constructorsToUse = constructorsUsingSimpleTypeArgs;
        }

        return constructorsToUse.stream().min(Comparator.comparing(x -> x.getParameterList().getParametersCount()));
    }

    private Optional<PsiMethod> tryFindConstructorUsingAnyTypeParam(final PsiClass psiClass, final List<PsiMethod> candidateConstructors) {
        for(final PsiTypeParameter typeParameter : psiClass.getTypeParameters()) {
            final Optional<PsiMethod> psiMethod = tryFindConstructorUsingTypeParam(candidateConstructors, typeParameter);
            if(psiMethod.isPresent()) {
                return psiMethod;
            }
        }
        return Optional.empty();
    }

    private boolean hasTypeWithTypeParameter(final PsiMethod constructor, final PsiTypeParameter typeParameter) {
        return hasTypeWithTypeParameter(constructor, typeParameter, false);
    }

    private boolean hasKnownTypeWithTypeParameter(final PsiMethod constructor, final PsiTypeParameter typeParameter) {
        return hasTypeWithTypeParameter(constructor, typeParameter, true);
    }

    private boolean hasTypeWithTypeParameter(
            final PsiMethod constructor,
            final PsiTypeParameter typeParameter,
            final boolean onlyInspectKnownTypes) {
        for(final PsiParameter param : constructor.getParameterList().getParameters()) {
            final PsiType paramType = param.getType();
            if(onlyInspectKnownTypes && !isKnownType(paramType)) {
                continue;
            }
            if(paramType instanceof PsiClassReferenceType) {
                final PsiType[] innerTypeParams = ((PsiClassReferenceType) paramType).getParameters();
                if(innerTypeParams.length != 0 && innerTypeParams[0] instanceof final PsiClassReferenceType innerTypeParam) {
                    if(innerTypeParam.resolve() == typeParameter) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean hasMapWithTypeParameters(
            final PsiMethod constructor,
            final PsiTypeParameter keyParameter,
            final PsiTypeParameter valueParameter) {
        for(final PsiParameter param : constructor.getParameterList().getParameters()) {
            final PsiType paramType = param.getType();
            if(!isMap(paramType)) {
                continue;
            }
            if(paramType instanceof PsiClassReferenceType) {
                final PsiType[] innerTypeParams = ((PsiClassReferenceType) paramType).getParameters();
                final List<PsiTypeParameter> resolvedTypeParams = getResolvedTypeReferences(innerTypeParams);
                if(resolvedTypeParams != null && resolvedTypeParams.size() == 2) {
                    if(keyParameter == resolvedTypeParams.get(0) && valueParameter == resolvedTypeParams.get(1)) {
                        return true;
                    } else if(valueParameter == resolvedTypeParams.get(0) && keyParameter == resolvedTypeParams.get(1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Nullable
    private List<PsiTypeParameter> getResolvedTypeReferences(final PsiType[] innerTypeParams) {
        final List<PsiTypeParameter> resolvedParams = new ArrayList<>(innerTypeParams.length);
        for(final PsiType type : innerTypeParams) {
            if(type instanceof final PsiWildcardType wildcardType) {
                if(wildcardType.isExtends() && wildcardType.getExtendsBound() instanceof PsiClassReferenceType) {
                    final PsiClass resolvedReference = ((PsiClassReferenceType) wildcardType.getExtendsBound()).resolve();
                    if(resolvedReference instanceof PsiTypeParameter) {
                        resolvedParams.add((PsiTypeParameter) resolvedReference);
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else if(type instanceof PsiClassReferenceType) {
                final PsiClass resolvedReference = ((PsiClassReferenceType) type).resolve();
                if(resolvedReference instanceof PsiTypeParameter) {
                    resolvedParams.add((PsiTypeParameter) resolvedReference);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
        return resolvedParams;
    }

    private boolean isKnownType(final PsiType paramType) {
        final PsiClass psiClass = PsiUtil.resolveClassInType(paramType);
        if(psiClass == null || psiClass.getQualifiedName() == null) {
            return false;
        }
        return javaGroovyCommonTypesCreator.isKnown(psiClass.getQualifiedName());
    }

    @Nullable
    private StaticInitInfo tryGetStaticInitInfoForClassWithTypeParams(
            final TypeImpl typeImpl, @NotNull final PsiType psiType, final InitStrategy initStrategy) {
        final PsiClassType.ClassResolveResult resolveResult = PsiUtil.resolveGenericsClassInType(psiType);
        final PsiClass psiClass = resolveResult.getElement();
        final PsiSubstitutor typeSubstitutor = resolveResult.getSubstitutor();
        if(psiClass == null || psiClass.getQualifiedName() == null) {
            return null;
        }
        if(psiClass.isEnum()) {
            return null;
        }
        if(isNonStaticInnerClass(psiClass)) {
            return null;
        }
        final String className = getNameToUseForInitCall(psiClass);
        if(className == null) {
            return null;
        }
        final PsiMethod[] methods = psiClass.getMethods();
        List<PsiMethod> candidateMethods = Arrays.stream(methods)
                .filter(x -> {
                    if(!x.hasModifierProperty(PsiModifier.STATIC)) {
                        return false;
                    }
                    if(x.isDeprecated()) {
                        return false;
                    }
                    if(!TypeCreatorUtil.hasSameType(psiClass, x.getReturnType())) {
                        return false;
                    }
                    if(isCopyMethod(psiClass, x)) {
                        return false;
                    }
                    return visibilityInfoProvider.isVisibleToTestClass(x);
                }).collect(Collectors.toList());
        if(candidateMethods.isEmpty()) {
            return null;
        }

        // Check for the case where the type Foo has a single type parameter T and we have a single creator method with a generic
        // type T that takes in a parameter of type T and returns Foo<T>. This is the pattern used in Optional.of(T),
        // Observable.just(T), etc.
        final StaticInitInfo singleParamInitInfo = tryGetSingleGenericParamInitMethod(typeImpl, psiClass, typeSubstitutor, candidateMethods);
        if(singleParamInitInfo != null) {
            return singleParamInitInfo;
        }

        // If we have methods that do not take in a Consumer, prefer those.
        final List<PsiMethod> methodsWithoutConsumerArgs = candidateMethods.stream().filter(x -> !hasConsumerParam(x)).collect(Collectors.toList());
        if(!methodsWithoutConsumerArgs.isEmpty()) {
            candidateMethods = methodsWithoutConsumerArgs;
        }

        // Try to find static methods that uses all type params.
        final PsiTypeParameter[] classTypeParams = psiClass.getTypeParameters();
        final List<PsiMethod> methodsThatUseAllTypeParams = candidateMethods.stream().filter(
                        x -> x.getTypeParameters().length == classTypeParams.length && x.getParameterList().getParametersCount() != 0)
                .collect(Collectors.toList());
        if(!methodsThatUseAllTypeParams.isEmpty()) {
            candidateMethods = methodsThatUseAllTypeParams;
        }

        // Choose method that takes in the type params as simple args.
        // For example: in org.apache.commons.lang3.tuple.Pair<L, R>
        // Prefer factory method: Pair.of(L left, R right)
        // Over factory method: Pair.of(final Map.Entry<L, R> pair)
        final List<PsiMethod> methodsThatUseTypeParamsSingleArgs = candidateMethods.stream().filter(
                TypeCreatorUtil::allParamsSimpleTypeParamArgs
        ).toList();
        if(!methodsThatUseTypeParamsSingleArgs.isEmpty()) {
            final Optional<PsiMethod> methodToUse = methodsThatUseTypeParamsSingleArgs.stream().min(Comparator.comparing(x -> x.getParameterList().getParameters().length));
            return new StaticInitInfo(className, typeSubstitutor, methodToUse.get(), true, false);
        }

        // Check to see if we have a method with at least one parameter, where all parameters are
        // a know type or an enum. Use the shortest such method if present.
        Optional<PsiMethod> methodToUse = candidateMethods.stream()
                .filter(x -> x.getParameterList().getParameters().length != 0 && allArgsKnownOrEnum(x, initStrategy))
                .min(Comparator.comparing(x -> x.getParameterList().getParameters().length));
        if(methodToUse.isPresent()) {
            return new StaticInitInfo(className, typeSubstitutor, methodToUse.get(), false, false);
        }

        // Just take the candidate method with the fewest number of params.
        methodToUse = candidateMethods.stream().min(Comparator.comparing(x -> x.getParameterList().getParameters().length));
        return new StaticInitInfo(className, typeSubstitutor, methodToUse.get(), false, false);
    }

    @Nullable
    private StaticInitInfo tryGetStaticInitInfo(
            @NotNull final PsiType psiType, final InitStrategy initStrategy) {
        final PsiClassType.ClassResolveResult resolveResult = PsiUtil.resolveGenericsClassInType(psiType);
        final PsiClass psiClass = resolveResult.getElement();
        final PsiSubstitutor typeSubstitutor = resolveResult.getSubstitutor();
        if(psiClass == null || psiClass.getQualifiedName() == null) {
            return null;
        }
        if(psiClass.isEnum()) {
            return null;
        }
        if(isNonStaticInnerClass(psiClass)) {
            return null;
        }
        final String className = getNameToUseForInitCall(psiClass);
        if(className == null) {
            return null;
        }
        final PsiMethod[] methods = psiClass.getMethods();
        List<PsiMethod> candidateMethods = Arrays.stream(methods)
                .filter(x -> {
                    if(!x.hasModifierProperty(PsiModifier.STATIC)) {
                        return false;
                    }
                    if(x.isDeprecated()) {
                        return false;
                    }
                    if(!TypeCreatorUtil.hasSameType(psiClass, x.getReturnType())) {
                        return false;
                    }
                    if(isCopyMethod(psiClass, x)) {
                        return false;
                    }
                    return visibilityInfoProvider.isVisibleToTestClass(x);
                }).collect(Collectors.toList());
        if(candidateMethods.isEmpty()) {
            return null;
        }

        // If we have methods that do not take in a Consumer, prefer those.
        final List<PsiMethod> methodsWithoutConsumerArgs = candidateMethods.stream().filter(x -> !hasConsumerParam(x)).collect(Collectors.toList());
        if(!methodsWithoutConsumerArgs.isEmpty()) {
            candidateMethods = methodsWithoutConsumerArgs;
        }

        // Check to see if we have a method with at least one parameter, where all parameters are
        // a know type or an enum. Use the shortest such method if present.
        Optional<PsiMethod> methodToUse = candidateMethods.stream()
                .filter(x -> x.getParameterList().getParameters().length != 0 && allArgsKnownOrEnum(x, initStrategy))
                .min(Comparator.comparing(x -> x.getParameterList().getParameters().length));
        if(methodToUse.isPresent()) {
            return new StaticInitInfo(className, typeSubstitutor, methodToUse.get(), true, false);
        }

        // Just take the candidate method with the fewest number of params.
        methodToUse = candidateMethods.stream().min(Comparator.comparing(x -> x.getParameterList().getParameters().length));
        return new StaticInitInfo(className, typeSubstitutor, methodToUse.get(), true, false);
    }

    @Nullable
    private StaticInitInfo tryGetSingleGenericParamInitMethod(
            final TypeImpl typeImpl, final PsiClass psiClass, final PsiSubstitutor typeSubstitutor, final List<PsiMethod> candidateMethods) {
        final PsiTypeParameterList typeParamsList = psiClass.getTypeParameterList();
        if(typeParamsList == null || typeParamsList.getTypeParameters().length != 1 || typeImpl.getParameters().size() != 1) {
            return null;
        }
        final String className = getNameToUseForInitCall(psiClass);
        if(className == null) {
            return null;
        }
        // Check for a creator method that takes in a single type param; e.g. T.
        PsiMethod creatorMethod = findMethodWithSingleTypeParam(candidateMethods, false);
        if(creatorMethod == null) {
            // Check for one that takes in a var arg of the type; e.g. T... instead of T.
            creatorMethod = findMethodWithSingleTypeParam(candidateMethods, true);
        }
        if(creatorMethod == null) {
            return null;
        }

        // Compute init expression for the actual type.
        return new StaticInitInfo(className, typeSubstitutor, creatorMethod, true, true);
    }

    private boolean allArgsKnownOrEnum(final PsiMethod method, final InitStrategy initStrategy) {
        for(final PsiParameter param : method.getParameterList().getParameters()) {
            final TypeImpl type = createTypeFromConfig(param.getName(), param.getType(), param, initStrategy);
            if(!type.isRecognized() && !type.isEnum()) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    private InitInfo createConstructorCall(
            @NotNull final ConstructorInfo constructorInfo, @NotNull final InitStrategy initStrategy,
            final int recursiveCounter, final SQMutableInt numberOfConstructorParams, final SQMutableInt numberOfBuilderMethodsCalled, final Set<String> canonicalTextsInStack) {
        if(constructorInfo.constructor() == null) {
            // Call the default, no-args constructor provided by the Java language spec.
            final String defaultConstructorCall = TypeCreatorUtil.createDefaultConstructorCall(constructorInfo);
            return new InitInfo(defaultConstructorCall, Collections.emptyList());
        }
        return createConstructorCall(
                constructorInfo.classToConstruct(), constructorInfo.className(),
                constructorInfo.psiSubstitutor(), constructorInfo.constructor(), initStrategy,
                recursiveCounter, numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
    }

    @NotNull
    private InitInfo createConstructorCall(
            final PsiClass psiClass, final String className, final PsiSubstitutor genericSubstitutor, final PsiMethod psiMethod,
            final InitStrategy initStrategy, final int recursiveCounter, final SQMutableInt numberOfConstructorParams, final SQMutableInt numberOfBuilderMethodsCalled, final Set<String> canonicalTextsInStack) {
        final String genericStr = psiClass.hasTypeParameters() ? "<>" : "";
        final String prefix = "new " + className + genericStr + "(";
        final StringBuilder constructorCall = new StringBuilder(prefix);
        final List<Api.Type> initExpressionBeans = new ArrayList<>();
        final PsiParameter[] parameters = psiMethod.getParameterList().getParameters();
        for(int i = 0; i < parameters.length; i++) {
            numberOfConstructorParams.increment();
            final PsiParameter param = parameters[i];
            final TypeImpl paramType = createTypeForFormalParamRecursive(param.getName(), genericSubstitutor.substitute(param.getType()), param, false, initStrategy, recursiveCounter + 1,
                    numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
            constructorCall.append(paramType.getInitExpression());
            if(i != parameters.length - 1) {
                constructorCall.append(", ");
            }
            initExpressionBeans.addAll(paramType.getInitExpressionBeans());
        }
        constructorCall.append(")");
        return new InitInfo(constructorCall.toString(), initExpressionBeans);
    }

    @Nullable
    private static String getNameToUseForInitCall(final PsiClass psiClass) {
        String name = psiClass.getQualifiedName();
        if(name == null) {
            name = psiClass.getName();
        }
        if(name == null) {
            return null;
        }
        return name;
    }

    @NotNull
    private String createConstructorCallWithEmptyValueForTypeParam(
            final PsiClass psiClass, final String className, final PsiSubstitutor genericSubstitutor, final PsiMethod psiMethod,
            final PsiTypeParameter typeParamToUseEmptyValueFor,
            final String emptyInitExpression,
            final InitStrategy initStrategy, final int recursiveCounter, final SQMutableInt numberOfConstructorParams, final SQMutableInt numberOfBuilderMethodsCalled, final Set<String> canonicalTextsInStack) {
        final String genericStr = psiClass.hasTypeParameters() ? "<>" : "";
        final String prefix = "new " + className + genericStr + "(";
        final StringBuilder constructorCall = new StringBuilder(prefix);
        final PsiParameter[] parameters = psiMethod.getParameterList().getParameters();
        for(int i = 0; i < parameters.length; i++) {
            final PsiParameter param = parameters[i];
            if(TypeCreatorUtil.isTypeParam(typeParamToUseEmptyValueFor, param, false)) {
                constructorCall.append(emptyInitExpression);
            } else {
                final Api.Type paramType = createTypeForFormalParamRecursive(param.getName(),
                        genericSubstitutor.substitute(param.getType()), param, false, initStrategy, recursiveCounter + 1,
                        numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
                constructorCall.append(paramType.getInitExpression());
            }
            if(i != parameters.length - 1) {
                constructorCall.append(", ");
            }
        }
        constructorCall.append(")");
        return constructorCall.toString();
    }

    @NotNull
    private InitInfo callStaticMethod(
            final StaticInitInfo staticInitInfo, final InitStrategy initStrategy, final int recursiveCounter,
            final SQMutableInt numberOfConstructorParams, final SQMutableInt numberOfBuilderMethodsCalled, final Set<String> canonicalTextsInStack) {
        return createStaticMethodCall(staticInitInfo.className(),
                staticInitInfo.psiSubstitutor(), staticInitInfo.method(), initStrategy, recursiveCounter,
                numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
    }

    @NotNull
    private InitInfo createStaticMethodCall(
            final String className, final PsiSubstitutor genericSubstitutor, final PsiMethod psiMethod,
            final InitStrategy initStrategy, final int recursiveCounter, final SQMutableInt numberOfConstructorParams, final SQMutableInt numberOfBuilderMethodsCalled, final Set<String> canonicalTextsInStack) {

        // Create the static method call.
        final String prefix = className + "." + psiMethod.getName() + "(";
        final StringBuilder staticMethodCall = new StringBuilder(prefix);
        final List<Api.Type> initExpressionBeans = new ArrayList<>();
        final PsiParameter[] parameters = psiMethod.getParameterList().getParameters();
        for(int i = 0; i < parameters.length; i++) {
            numberOfConstructorParams.increment();
            final PsiParameter param = parameters[i];
            final TypeImpl paramType = createTypeForFormalParamRecursive(param.getName(), TypeCreatorUtil.substituteByName(genericSubstitutor, param.getType()), param, false, initStrategy, recursiveCounter + 1,
                    numberOfConstructorParams, numberOfBuilderMethodsCalled, canonicalTextsInStack);
            staticMethodCall.append(paramType.getInitExpression());
            if(i != parameters.length - 1) {
                staticMethodCall.append(", ");
            }
            initExpressionBeans.addAll(paramType.getInitExpressionBeans());
        }
        staticMethodCall.append(")");
        return new InitInfo(staticMethodCall.toString(), initExpressionBeans);
    }

    private boolean isCopyMethod(final PsiClass psiClass, final PsiMethod method) {
        final boolean isConstructor = method.isConstructor();
        for(final PsiParameter param : method.getParameterList().getParameters()) {
            final PsiClass paramClass = PsiUtil.resolveClassInType(param.getType());
            if(paramClass == null) {
                continue;
            }
            if(Objects.equals(paramClass.getQualifiedName(), psiClass.getQualifiedName())) {
                if(isConstructor) {
                    final Set<PsiField> fields = fieldToSourceVariableCollector.getFieldsForVariable(method.getContainingClass(), param);
                    if(fields.isEmpty()) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
