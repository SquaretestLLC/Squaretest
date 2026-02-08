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

import com.intellij.psi.PsiArrayType;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiPrimitiveType;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypes;
import com.squaretest.generation.InitStrategy;
import com.squaretest.generation.PsiConverterUtils;
import com.squaretest.generation.SuggestedNameProvider;
import com.squaretest.generation.TypeSubstitutorProvider;
import com.squaretest.generation.VisibilityInfoProvider;
import com.squaretest.generation.WildcardInfoProvider;
import com.squaretest.generation.dataflow.usedmethods.CalledMethodsInfoProvider;
import com.squaretest.generation.defaulttypes.beans.BeanInfoProvider;
import com.squaretest.generation.defaulttypes.builders.BuilderInfoProvider;
import com.squaretest.generation.dependencyinteraction.FieldToSourceVariableCollector;
import com.squaretest.settings.store.SettingsProvider;
import com.squaretest.template.AccessModifier;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.FluentListImpl;
import com.squaretest.template.impl.TypeImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Converts {@link PsiType PsiTypes} to {@link TypeImpl Types} using default values suitable for use in Groovy tests.
 */
public class GroovyTypeCreator extends TypeCreator {

    private static final String ARRAY_INIT_FORMAT = "[] as %s";
    private static final String ARRAY_INIT_FORMAT_WITH_PLACEHOLDER = "[{{VALUE1}}] as %s";

    public GroovyTypeCreator(
            @NotNull final JavaGroovyCommonTypesCreator javaGroovyCommonTypesCreator,
            @NotNull final BeanInfoProvider beanInfoProvider,
            @NotNull final SuggestedNameProvider suggestedNameProvider,
            @NotNull final AltIoExpressionPopulator altIoExpressionPopulator,
            @NotNull final TestDependencyInfoProvider testDependencyInfoProvider,
            @NotNull final SuperTypesProvider superTypesProvider,
            @NotNull final FieldToSourceVariableCollector fieldToSourceVariableCollector,
            @NotNull final SettingsProvider settingsProvider,
            @NotNull final BuilderInfoProvider builderInfoProvider,
            @NotNull final CalledMethodsInfoProvider calledMethodsInfoProvider,
            @NotNull final TypeSubstitutorProvider typeSubstitutorProvider,
            @NotNull final WildcardInfoProvider wildcardInfoProvider,
            @NotNull final EnumValueProvider enumValueProvider,
            @NotNull final JavaLibraryReferenceProvider javaLibraryReferenceProvider,
            @NotNull final VisibilityInfoProvider visibilityInfoProvider) {
        super(javaGroovyCommonTypesCreator, beanInfoProvider, suggestedNameProvider, testDependencyInfoProvider,
                altIoExpressionPopulator, superTypesProvider, fieldToSourceVariableCollector, settingsProvider, builderInfoProvider, calledMethodsInfoProvider, typeSubstitutorProvider, enumValueProvider, javaLibraryReferenceProvider, wildcardInfoProvider, visibilityInfoProvider);
    }

    @Override
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
            initExpression = String.format(ARRAY_INIT_FORMAT, canonicalText);
            emptyInitExpression = String.format(ARRAY_INIT_FORMAT, canonicalText);
        }

        // Determine the placeholder string if this is a 1d array.
        String placeholderString = null;
        if(dimension == 1) {
            placeholderString = String.format(ARRAY_INIT_FORMAT_WITH_PLACEHOLDER, canonicalText);
        }

        return new TypeImpl(TypeCreatorUtil.determineName(parameterType), null, canonicalText, initExpression, placeholderString, false, "null", emptyInitExpression, null, null, null, null, Api.FluentListFactory.emptyList(), SuperTypesProvider.DefaultSuperTypes, false,
                false, AccessModifier.Public, false, false, true, false, false, false, false, false, false, false, false,
                false, false, true, isSimpleArray(parameterType, parameterName, initStrategy, source), false, false, false, initStrategy);
    }

    @Override
    protected TypeImpl createTypeFromPrimitive(final PsiPrimitiveType parameterType, final String parameterName, final PsiElement source) {
        final String name = TypeCreatorUtil.determineName(parameterType);
        final String canonicalName = parameterType.getCanonicalText();
        final DefaultTypeInfo defaultTypeInfo = javaGroovyCommonTypesCreator.createType(canonicalName, parameterName, source);
        if(defaultTypeInfo != null && defaultTypeInfo.getGroovyInitExpression() != null) {
            return createTypeFromDefaultTypeInfo(defaultTypeInfo, SuperTypesProvider.NoSuperTypes, name, canonicalName, canonicalName, false, false, AccessModifier.Public, defaultTypeInfo.isSimple(), false, false);
        }
        return super.createTypeFromPrimitive(parameterType, parameterName, source);
    }

    @Override
    protected TypeImpl createTypeFromPsiClass(
            final PsiClass psiClass, PsiType psiType, final String canonicalText, final String parameterTypeName, final String parameterName, final InitStrategy initStrategy, final PsiElement source) {
        final String canonicalName = psiClass.getQualifiedName();
        final DefaultTypeInfo defaultTypeInfo = javaGroovyCommonTypesCreator.createType(canonicalName, parameterName, source);
        if(defaultTypeHasGroovyInfo(defaultTypeInfo)) {
            final boolean isNested = psiClass.getContainingClass() != null;
            final boolean isStatic = psiClass.hasModifierProperty(PsiModifier.STATIC);
            final AccessModifier accessModifier = PsiConverterUtils.modifierFromPsi(psiClass);
            final Api.FluentList<String> superTypeCanonicalNames = superTypesProvider.getSuperTypes(psiClass);
            final boolean isInterface = psiClass.isInterface();
            final boolean isAbstract = psiClass.hasModifierProperty(PsiModifier.ABSTRACT);
            return createTypeFromDefaultTypeInfo(defaultTypeInfo, superTypeCanonicalNames, parameterTypeName, canonicalName, canonicalText, isNested, isStatic, accessModifier,
                    defaultTypeInfo.isSimple(), isInterface, isAbstract);
        }
        return super.createTypeFromPsiClass(psiClass, psiType, canonicalText, parameterTypeName, parameterName, initStrategy, source);
    }

    private static boolean defaultTypeHasGroovyInfo(final DefaultTypeInfo defaultTypeInfo) {
        return defaultTypeInfo != null
                && (defaultTypeInfo.getGroovyInitExpression() != null
                || defaultTypeInfo.getGroovyInitExpressionWithTypePlaceholder() != null);
    }

    private static TypeImpl createTypeFromDefaultTypeInfo(
            @NotNull final DefaultTypeInfo defaultTypeInfo, Api.FluentList<String> superTypeCanonicalNames, @NotNull final String name, @Nullable final String canonicalName,
            @NotNull final String canonicalText, boolean isNested, boolean isStatic, AccessModifier accessModifier, final boolean isSimple, final boolean isInterface, final boolean isAbstract) {
        // Use the groovy initExpression if one is available.
        String initExpression = defaultTypeInfo.getGroovyInitExpression();
        if(initExpression == null) {
            initExpression = defaultTypeInfo.getInitExpression();
        }

        // Use the groovy initExpressionWithTypePlaceholder if one is available.
        String initExpressionWithTypePlaceholder = defaultTypeInfo.getGroovyInitExpressionWithTypePlaceholder();
        if(initExpressionWithTypePlaceholder == null) {
            initExpressionWithTypePlaceholder = defaultTypeInfo.getInitExpressionWithTypePlaceholder();
        }

        // Use the groovy failureInitExpressionWithTypePlaceholder if one is available.
        String failureInitExpressionWithTypePlaceholder = defaultTypeInfo.getGroovyFailureInitExpressionWithTypePlaceholder();
        if(failureInitExpressionWithTypePlaceholder == null) {
            failureInitExpressionWithTypePlaceholder = defaultTypeInfo.getFailureInitExpressionWithTypePlaceholder();
        }

        // Use the groovy emptyInitExpression if one is available.
        String emptyInitExpression = defaultTypeInfo.getGroovyEmptyInitExpression();
        if(emptyInitExpression == null) {
            emptyInitExpression = defaultTypeInfo.getEmptyInitExpression();
        }

        // Use the groovy failureInitExpression if one is available.
        String failureInitExpression = defaultTypeInfo.getGroovyFailureInitExpression();
        if(failureInitExpression == null) {
            failureInitExpression = defaultTypeInfo.getFailureInitExpression();
        }

        return new TypeImpl(name, canonicalName, canonicalText, initExpression, initExpressionWithTypePlaceholder, defaultTypeInfo.isOptional(), defaultTypeInfo.getAbsentInitExpression(), emptyInitExpression, null, null, failureInitExpression, failureInitExpressionWithTypePlaceholder, new FluentListImpl<>(defaultTypeInfo.getImportsRequired()), superTypeCanonicalNames, isNested,
                isStatic, accessModifier, defaultTypeInfo.isMockable, defaultTypeInfo.getShouldBeMocked(), false, defaultTypeInfo.isPrimitive(), defaultTypeInfo.overridesEquals(), false, false, defaultTypeInfo.isBean(), defaultTypeInfo.isBeanWithInputIOProperty(), false, false,
                isInterface, isAbstract, true, isSimple, defaultTypeInfo.isSimpleIfTypeParamsAreSimple(),
                defaultTypeInfo.isAbsentIfTypeParamsAreAbsent(), defaultTypeInfo.isEmptyIfTypeParamsAreEmpty(), InitStrategy.Default);
    }
}
