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
package com.squaretest.generation.dataflow.usedmethods;

import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import org.jetbrains.annotations.Nullable;

class UsedInStaticHelpersDecider implements MethodCalledDecider {
    private final CalledMethodsInfoProvider calledMethodsInfoProvider;

    public UsedInStaticHelpersDecider(final CalledMethodsInfoProvider calledMethodsInfoProvider) {
        this.calledMethodsInfoProvider = calledMethodsInfoProvider;
    }

    @Override
    @Nullable
    public CalledMethodInfo getCalledMethodInfo(final PsiMethod psiMethod) {
        return calledMethodsInfoProvider.getStaticMethodCallInfo(psiMethod);
    }

    @Override
    public boolean isReferenced(final PsiField psiField) {
        return calledMethodsInfoProvider.referencedInStaticHelpers(psiField);
    }
}
