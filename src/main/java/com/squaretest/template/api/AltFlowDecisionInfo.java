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
package com.squaretest.template.api;

import com.squaretest.generation.dependencyinteraction.outcomes.AltFlowInfo;
import org.jetbrains.annotations.NotNull;

public class AltFlowDecisionInfo {
    @NotNull
    private final AltFlowInfo altFlowInfo;
    private final boolean isFallbackChoice;

    public AltFlowDecisionInfo(@NotNull final AltFlowInfo altFlowInfo, final boolean isFallbackChoice) {
        this.altFlowInfo = altFlowInfo;
        this.isFallbackChoice = isFallbackChoice;
    }

    @NotNull
    public AltFlowInfo getAltFlowInfo() {
        return altFlowInfo;
    }

    public boolean isFallbackChoice() {
        return isFallbackChoice;
    }
}
