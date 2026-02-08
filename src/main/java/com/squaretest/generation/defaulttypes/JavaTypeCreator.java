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

import com.intellij.psi.PsiType;
import com.squaretest.generation.SuggestedNameProvider;
import com.squaretest.generation.TypeSubstitutorProvider;
import com.squaretest.generation.VisibilityInfoProvider;
import com.squaretest.generation.WildcardInfoProvider;
import com.squaretest.generation.dataflow.usedmethods.CalledMethodsInfoProvider;
import com.squaretest.generation.defaulttypes.beans.BeanInfoProvider;
import com.squaretest.generation.defaulttypes.builders.BuilderInfoProvider;
import com.squaretest.generation.dependencyinteraction.FieldToSourceVariableCollector;
import com.squaretest.settings.store.SettingsProvider;
import com.squaretest.template.impl.TypeImpl;
import org.jetbrains.annotations.NotNull;

/**
 * Converts {@link PsiType PsiTypes} to {@link TypeImpl Types} using default values suitable for use in java tests.
 */
public class JavaTypeCreator extends TypeCreator {

    public JavaTypeCreator(
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
}
