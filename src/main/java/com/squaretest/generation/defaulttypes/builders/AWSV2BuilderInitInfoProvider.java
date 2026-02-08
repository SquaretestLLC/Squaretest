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
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.squaretest.generation.CompiledUtils;
import com.squaretest.generation.dataflow.usedmethods.UsedPropertyInfoProvider;
import com.squaretest.generation.defaulttypes.AWSBuilderUtils;
import com.squaretest.generation.sourcevars.builders.AWSV2BuilderSourceVariableProvider;
import org.apache.commons.collections4.SetUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class AWSV2BuilderInitInfoProvider implements IBuilderInitInfoProvider {

    @NotNull
    private final UsedPropertyInfoProvider usedPropertyInfoProvider;
    @NotNull
    private final Map<PsiClass, Optional<BuilderInitInfo>> cache;

    public AWSV2BuilderInitInfoProvider(@NotNull final UsedPropertyInfoProvider usedPropertyInfoProvider) {
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
        final PsiMethod builderFactoryMethod = CompiledUtils.getMethodWithSourceCode(AWSV2BuilderSourceVariableProvider.getBuilderFactoryMethod(psiClass));
        if(builderFactoryMethod == null) {
            return Optional.empty();
        }
        final PsiClass builderClass = AWSV2BuilderSourceVariableProvider.getBuilderClass(builderFactoryMethod);
        if(builderClass == null) {
            return Optional.empty();
        }
        final List<PsiMethod> builderMethodsToCall = new ArrayList<>();
        final List<PsiMethod> builderMethods = AWSBuilderUtils.determineBuilderMethodsToUse(builderClass).stream().map(CompiledUtils::getMethodWithSourceCode).toList();
        final Set<PsiField> alreadySetProperties = SetUtils.newIdentityHashSet();
        final Set<String> calledMethodNames = new HashSet<>();
        for(final PsiMethod builderMethod : builderMethods) {
            final PsiField targetField = AWSV2BuilderSourceVariableProvider.getTargetField(psiClass, builderMethod);
            if(targetField != null && alreadySetProperties.contains(targetField)) {
                // We've already called a builder method for this field.
                continue;
            }
            if(calledMethodNames.contains(builderMethod.getName())) {
                continue;
            }
            if(isPropertyUsed(builderMethod)) {
                builderMethodsToCall.add(builderMethod);
                if(targetField != null) {
                    alreadySetProperties.add(targetField);
                }
                calledMethodNames.add(builderMethod.getName());
            }
        }
        final PsiMethod buildMethod = CompiledUtils.getMethodWithSourceCode(AWSV2BuilderSourceVariableProvider.getBuildMethod(builderClass));
        if(buildMethod == null) {
            return Optional.empty();
        }
        return Optional.of(new BuilderInitInfo(qualifiedName, builderFactoryMethod, builderMethodsToCall, buildMethod, true));
    }

    private boolean isPropertyUsed(final PsiMethod builderMethod) {
        return usedPropertyInfoProvider.calledInSource(builderMethod)
                || usedPropertyInfoProvider.calledInStaticHelpers(builderMethod)
                || usedPropertyInfoProvider.propertyIsUsedInSource(builderMethod)
                || usedPropertyInfoProvider.propertyIsUsedInStaticHelpers(builderMethod);
    }
}
