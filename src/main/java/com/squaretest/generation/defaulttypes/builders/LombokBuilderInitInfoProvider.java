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
package com.squaretest.generation.defaulttypes.builders;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.squaretest.generation.dataflow.usedmethods.UsedPropertyInfoProvider;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.sourcevars.builders.LombokBuilderSourceVariableProvider;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class LombokBuilderInitInfoProvider implements IBuilderInitInfoProvider {
    @NotNull
    private final LombokBuilderSourceVariableProvider lombokBuilderSourceVariableProvider;
    @NotNull
    private final UsedPropertyInfoProvider usedPropertyInfoProvider;
    @NotNull
    private final Map<PsiClass, Optional<BuilderInitInfo>> cache;

    public LombokBuilderInitInfoProvider(
            @NotNull final LombokBuilderSourceVariableProvider lombokBuilderSourceVariableProvider,
            @NotNull final UsedPropertyInfoProvider usedPropertyInfoProvider) {
        this.lombokBuilderSourceVariableProvider = lombokBuilderSourceVariableProvider;
        this.usedPropertyInfoProvider = usedPropertyInfoProvider;
        this.cache = new IdentityHashMap<>();
    }

    @Override
    public Optional<BuilderInitInfo> getBuilderInfo(@NotNull final PsiClass classToInitialize) {
        return cache.computeIfAbsent(classToInitialize, this::getBuilderMethodsToCallImpl);
    }

    private Optional<BuilderInitInfo> getBuilderMethodsToCallImpl(@NotNull final PsiClass psiClass) {
        final String qualifiedName = psiClass.getQualifiedName();
        if(qualifiedName == null) {
            return Optional.empty();
        }
        final PsiMethod builderFactoryMethod = LombokBuilderSourceVariableProvider.getBuilderFactoryMethod(psiClass);
        if(builderFactoryMethod == null) {
            return Optional.empty();
        }
        if(builderFactoryMethod.hasModifierProperty(PsiModifier.PRIVATE)) {
            return Optional.empty();
        }
        final PsiClass builderClass = LombokBuilderSourceVariableProvider.getBuilderClass(builderFactoryMethod);
        if(builderClass == null) {
            return Optional.empty();
        }
        final List<PsiMethod> builderMethodsToCall = new ArrayList<>();
        final PsiMethod[] builderMethods = builderClass.getAllMethods();
        final Set<PsiField> alreadySetProperties = SetUtils.newIdentityHashSet();
        for(final PsiMethod builderMethod : builderMethods) {
            if(builderMethod.isConstructor()) {
                continue;
            }
            if(!StringUtils.contains(builderMethod.getClass().getCanonicalName(), JavaNames.LombokLightMethodName)) {
                // This is not a lombok builder method.
                continue;
            }
            if(builderMethod.getParameterList().getParameters().length != 1) {
                continue;
            }
            if(hasBetterMethodForSameProperty(builderClass, builderMethod)) {
                continue;
            }
            final PsiField targetField = lombokBuilderSourceVariableProvider.getTargetField(builderMethod);
            if(targetField != null && alreadySetProperties.contains(targetField)) {
                continue;
            }
            if(isPropertyUsed(builderMethod)) {
                builderMethodsToCall.add(builderMethod);
                if(targetField != null) {
                    alreadySetProperties.add(targetField);
                }
            }
        }
        final PsiMethod buildMethod = LombokBuilderSourceVariableProvider.getBuildMethod(psiClass, builderClass);
        if(buildMethod == null) {
            return Optional.empty();
        }
        return Optional.of(new BuilderInitInfo(qualifiedName, builderFactoryMethod, builderMethodsToCall, buildMethod, false));
    }

    private boolean hasBetterMethodForSameProperty(final PsiClass builderClass, final PsiMethod builderMethod) {
        final PsiElement builderMethodNavTarget = builderMethod.getNavigationElement();
        if(builderMethodNavTarget == null) {
            return false;
        }
        final String builderMethodName = builderMethod.getName();
        final List<String> altNames = List.of(builderMethodName + "s", builderMethodName + "es");
        for(final String altName : altNames) {
            final PsiMethod[] methodsWithPluralName = builderClass.findMethodsByName(altName, false);
            for(final PsiMethod method : methodsWithPluralName) {
                final PsiElement altMethodNavTarget = method.getNavigationElement();
                if(builderMethodNavTarget == altMethodNavTarget) {
                    // We found a plural version of the builder method.
                    // This is the preferred method.
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isPropertyUsed(final PsiMethod builderMethod) {
        return usedPropertyInfoProvider.calledInSource(builderMethod)
                || usedPropertyInfoProvider.calledInStaticHelpers(builderMethod)
                || usedPropertyInfoProvider.propertyIsUsedInSource(builderMethod)
                || usedPropertyInfoProvider.propertyIsUsedInStaticHelpers(builderMethod);
    }
}
