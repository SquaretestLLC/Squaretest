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

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.squaretest.generation.TypeSubstitutorProvider;

import java.util.Map;

public class DependencyInteractionCollector {
    private final TypeSubstitutorProvider typeSubstitutorProvider;
    private final MethodCallsProvider methodCallsProvider;
    private final PsiClass mainSourceClass;
    private Map<PsiMethod, DependencyInteractionSet> computedValue;

    public DependencyInteractionCollector(
            final TypeSubstitutorProvider typeSubstitutorProvider,
            final MethodCallsProvider methodCallsProvider,
            final PsiClass mainSourceClass) {
        this.typeSubstitutorProvider = typeSubstitutorProvider;
        this.methodCallsProvider = methodCallsProvider;
        this.mainSourceClass = mainSourceClass;
        this.computedValue = null;
    }

    public Map<PsiMethod, DependencyInteractionSet> computeMethodToPsiMap() {
        if(computedValue == null) {
            computedValue = new DependencyInteractionCollectorImpl(mainSourceClass, typeSubstitutorProvider, methodCallsProvider).computeMethodToPsiMap();
        }
        return computedValue;
    }
}
