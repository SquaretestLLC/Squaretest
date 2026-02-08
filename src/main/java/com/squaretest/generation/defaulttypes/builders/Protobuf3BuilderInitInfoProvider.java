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
import com.intellij.psi.PsiModifier;
import com.intellij.psi.util.PsiUtil;
import com.squaretest.generation.CompiledUtils;
import com.squaretest.generation.dataflow.usedmethods.UsedPropertyInfoProvider;
import com.squaretest.generation.defaulttypes.ProtobufUtils;
import com.squaretest.generation.sourcevars.builders.Protobuf3BuilderSourceVariableProvider;
import org.apache.commons.collections4.SetUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.squaretest.generation.defaulttypes.ProtobufUtils.findNewBuilderMethod;

public class Protobuf3BuilderInitInfoProvider implements IBuilderInitInfoProvider {
    @NotNull
    private final UsedPropertyInfoProvider usedPropertyInfoProvider;
    @NotNull
    private final Map<PsiClass, Optional<BuilderInitInfo>> cache;

    public Protobuf3BuilderInitInfoProvider(@NotNull final UsedPropertyInfoProvider usedPropertyInfoProvider) {
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
        final PsiMethod builderFactoryMethod = CompiledUtils.getMethodWithSourceCode(findNewBuilderMethod(psiClass));
        if(builderFactoryMethod == null) {
            return Optional.empty();
        }
        final PsiClass builderClass = PsiUtil.resolveClassInType(builderFactoryMethod.getReturnType());
        if(builderClass == null) {
            return Optional.empty();
        }
        final List<PsiMethod> builderMethodsToCall = new ArrayList<>();
        final List<PsiMethod> builderMethods = ProtobufUtils.determineBuilderMethodsToUse(builderClass).stream().map(CompiledUtils::getMethodWithSourceCode).toList();
        final Set<PsiField> alreadySetProperties = SetUtils.newIdentityHashSet();
        final Set<String> alreadyCalledMethodNames = new HashSet<>();
        for(final PsiMethod builderMethod : builderMethods) {
            final String methodName = builderMethod.getName();
            if(alreadyCalledMethodNames.contains(methodName)) {
                continue;
            }
            final PsiField targetField = Protobuf3BuilderSourceVariableProvider.getTargetField(psiClass, builderMethod);
            if(targetField != null && alreadySetProperties.contains(targetField)) {
                // We've already called a builder method for this field.
                continue;
            }
            if(isPropertyUsed(builderClass, targetField, builderMethod)) {
                builderMethodsToCall.add(builderMethod);
                if(targetField != null) {
                    alreadySetProperties.add(targetField);
                }
                alreadyCalledMethodNames.add(methodName);
            }
        }
        final PsiMethod buildMethod = CompiledUtils.getMethodWithSourceCode(getBuildMethod(builderClass));
        if(buildMethod == null) {
            return Optional.empty();
        }
        return Optional.of(new BuilderInitInfo(qualifiedName, builderFactoryMethod, builderMethodsToCall, buildMethod, false));
    }

    private PsiMethod getBuildMethod(final PsiClass builderClass) {
        final PsiMethod[] buildMethods = builderClass.findMethodsByName("build", false);
        for(final PsiMethod buildMethod : buildMethods) {
            if(!buildMethod.hasModifierProperty(PsiModifier.STATIC)
                    && buildMethod.hasModifierProperty(PsiModifier.PUBLIC)
                    && buildMethod.getParameterList().getParameters().length == 0) {
                return buildMethod;
            }
        }
        return null;
    }

    private boolean isPropertyUsed(final PsiClass builderClass, final PsiField targetField, final PsiMethod builderMethod) {
        // Squaretest can't determine the target field in cases where the class uses a "One of" data structure.
        // See https://developers.google.com/protocol-buffers/docs/proto3#oneof for more info.
        // The field name is called input_.
        // See com.google.cloud.dialogflow.cx.v3.QueryInput.Builder.setText(TextInput.Builder) for an example.
        // We can't map builder methods to a field called input_, because we could end up mapping any unrecognized
        // builder method to the same field. Squaretest would then think all of those unrecognized methods are used.

        // The solution is: If we have a targetField (we were able to identify one), use the normal logic.
        // Otherwise, check to see if either this method or one of its overloads were called in the source class or
        // related classes.
        if(targetField != null) {
            return usedPropertyInfoProvider.calledInSource(builderMethod)
                    || usedPropertyInfoProvider.calledInStaticHelpers(builderMethod)
                    || usedPropertyInfoProvider.propertyIsUsedInSource(builderMethod)
                    || usedPropertyInfoProvider.propertyIsUsedInStaticHelpers(builderMethod);
        }
        if(usedPropertyInfoProvider.calledInSource(builderMethod)
                || usedPropertyInfoProvider.calledInStaticHelpers(builderMethod)) {
            return true;
        }
        final List<PsiMethod> builderMethodOverloads = Arrays.stream(builderClass.findMethodsByName(builderMethod.getName(), false))
                .map(CompiledUtils::getMethodWithSourceCode).toList();
        for(final PsiMethod overloadMethod : builderMethodOverloads) {
            if(usedPropertyInfoProvider.calledInSource(overloadMethod)
                    || usedPropertyInfoProvider.calledInStaticHelpers(overloadMethod)) {
                return true;
            }
        }
        return false;
    }
}
