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
package com.squaretest.generation.dependencyinteraction.outcomes;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class OutcomeInfo {
    @NotNull
    private final Map<String, UnfilteredPrimaryFlowInfo> primaryFlowInfoMap;
    @NotNull
    private final Map<String, FilteredPrimaryFlowInfo> filteredPrimaryFlowInfoMap;
    @NotNull
    private final Map<String, FilteredPrimaryFlowInfo> filteredPrimaryFlowWithoutDisInfoMap;
    @NotNull
    private final Map<String, AltFlowInfo> exceptionAltFlowMap;
    @NotNull
    private final Map<String, AltFlowInfo> valueAltFlowMap;

    public OutcomeInfo(
            @NotNull final Map<String, UnfilteredPrimaryFlowInfo> primaryFlowInfoMap,
            @NotNull final Map<String, FilteredPrimaryFlowInfo> filteredPrimaryFlowInfoMap,
            @NotNull final Map<String, FilteredPrimaryFlowInfo> filteredPrimaryFlowWithoutDisInfoMap,
            @NotNull final Map<String, AltFlowInfo> exceptionAltFlowMap,
            @NotNull final Map<String, AltFlowInfo> valueAltFlowMap) {
        this.filteredPrimaryFlowInfoMap = filteredPrimaryFlowInfoMap;
        this.filteredPrimaryFlowWithoutDisInfoMap = filteredPrimaryFlowWithoutDisInfoMap;
        this.exceptionAltFlowMap = exceptionAltFlowMap;
        this.primaryFlowInfoMap = primaryFlowInfoMap;
        this.valueAltFlowMap = valueAltFlowMap;
    }

    @Nullable
    public UnfilteredPrimaryFlowInfo getUnfilteredPrimaryFlowInfo(final String methodKey) {
        return primaryFlowInfoMap.get(methodKey);
    }

    @Nullable
    public FilteredPrimaryFlowInfo getFilteredPrimaryFlowInfo(final String methodAndFirstDiKey) {
        return filteredPrimaryFlowInfoMap.get(methodAndFirstDiKey);
    }

    @Nullable
    public FilteredPrimaryFlowInfo getFilteredNonDiPrimaryFlowInfo(final String methodKey) {
        return filteredPrimaryFlowWithoutDisInfoMap.get(methodKey);
    }

    @Nullable
    public AltFlowInfo getExceptionAltFlowInfo(final String altFlowKey) {
        return exceptionAltFlowMap.get(altFlowKey);
    }

    @Nullable
    public AltFlowInfo getValueAltFlowInfo(final String altFlowKey) {
        return valueAltFlowMap.get(altFlowKey);
    }
}
