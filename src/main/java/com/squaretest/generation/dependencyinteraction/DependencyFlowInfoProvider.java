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
package com.squaretest.generation.dependencyinteraction;

import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.squaretest.generation.NullabilityDecider;
import com.squaretest.generation.defaulttypes.DefaultTypesHolder;
import com.squaretest.generation.dependencyinteraction.followup.FollowupInfoProvider;
import com.squaretest.generation.dependencyinteraction.outcomes.BooleanInfo;
import com.squaretest.generation.dependencyinteraction.outcomes.DepConfig;
import com.squaretest.generation.dependencyinteraction.outcomes.MethodAndDi;
import com.squaretest.generation.dependencyinteraction.outcomes.MethodPathsInfoProvider;
import com.squaretest.generation.dependencyinteraction.outcomes.OutcomeInfo;
import com.squaretest.generation.dependencyinteraction.outcomes.OutcomePopulator;
import com.squaretest.generation.exceptions.PsiExceptionProvider;

import java.util.Map;

public class DependencyFlowInfoProvider {
    private final PsiExceptionProvider psiExceptionProvider;
    private final FollowupInfoProvider followupInfoProvider;
    private final NullabilityDecider nullabilityDecider;
    private final DefaultTypesHolder defaultTypesHolder;
    private final DependencyInteractionCollector dependencyInteractionCollector;
    private final JavaPsiFacade javaPsiFacade;
    private final MethodPathsInfoProvider methodPathsInfoProvider;
    private final PsiClass mainSourceClass;
    private Map<MethodAndDi, BooleanInfo> computedBooleanInfo;

    public DependencyFlowInfoProvider(
            final PsiExceptionProvider psiExceptionProvider,
            final FollowupInfoProvider followupInfoProvider,
            final NullabilityDecider nullabilityDecider,
            final DefaultTypesHolder defaultTypesHolder,
            final DependencyInteractionCollector dependencyInteractionCollector,
            final JavaPsiFacade javaPsiFacade,
            final MethodPathsInfoProvider methodPathsInfoProvider,
            final PsiClass mainSourceClass) {
        this.psiExceptionProvider = psiExceptionProvider;
        this.followupInfoProvider = followupInfoProvider;
        this.nullabilityDecider = nullabilityDecider;
        this.defaultTypesHolder = defaultTypesHolder;
        this.dependencyInteractionCollector = dependencyInteractionCollector;
        this.javaPsiFacade = javaPsiFacade;
        this.methodPathsInfoProvider = methodPathsInfoProvider;
        this.mainSourceClass = mainSourceClass;
    }

    public Map<PsiMethod, DependencyInteractionSet> computeMethodToPsiMap() {
        return dependencyInteractionCollector.computeMethodToPsiMap();
    }

    public OutcomeInfo computeOutcomeInfo() {
        return computeOutcomeInfo(null);
    }

    public OutcomeInfo computeOutcomeInfo(final DepConfig depConfig) {
        final Map<PsiMethod, DependencyInteractionSet> sourceMethodToDisMap = dependencyInteractionCollector.computeMethodToPsiMap();
        return new OutcomePopulator(psiExceptionProvider, followupInfoProvider, nullabilityDecider, defaultTypesHolder, javaPsiFacade, mainSourceClass, sourceMethodToDisMap, methodPathsInfoProvider, depConfig).createOutcomeInfo();
    }

    public Map<MethodAndDi, BooleanInfo> getBooleanInfoMap() {
        if(computedBooleanInfo == null) {
            final Map<PsiMethod, DependencyInteractionSet> sourceMethodToDisMap = dependencyInteractionCollector.computeMethodToPsiMap();
            computedBooleanInfo = new OutcomePopulator(psiExceptionProvider, followupInfoProvider, nullabilityDecider, defaultTypesHolder, javaPsiFacade, mainSourceClass, sourceMethodToDisMap, methodPathsInfoProvider, null).computeBooleanInfo();
        }
        return computedBooleanInfo;
    }
}
