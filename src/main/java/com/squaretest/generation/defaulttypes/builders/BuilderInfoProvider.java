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
import com.squaretest.generation.CompiledUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class BuilderInfoProvider {
    @NotNull
    private final List<IBuilderInitInfoProvider> builderInitInfoProviders;

    public BuilderInfoProvider(@NotNull final List<IBuilderInitInfoProvider> builderInitInfoProviders) {
        this.builderInitInfoProviders = builderInitInfoProviders;
    }

    public Optional<BuilderInitInfo> getBuilderInfo(@NotNull PsiClass psiClass) {
        psiClass = CompiledUtils.getClassWithSourceCode(psiClass);
        for(final IBuilderInitInfoProvider builderInitInfoProvider : builderInitInfoProviders) {
            final Optional<BuilderInitInfo> info = builderInitInfoProvider.getBuilderInfo(psiClass);
            if(info.isPresent()) {
                return info;
            }
        }
        return Optional.empty();
    }
}
