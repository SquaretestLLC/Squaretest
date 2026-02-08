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
package com.squaretest.generation;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiVariable;
import com.squaretest.template.api.Api;
import org.jetbrains.annotations.NotNull;

public class NoOpPsiToTemplateVarsMapper implements PsiToTemplateVarsMapper {
    @Override
    public void putMethod(
            @NotNull final PsiMethod psiMethod, @NotNull final Api.Method method, final InitStrategy initStrategy) {
    }

    @Override
    public PsiMethod getPsiMethod(@NotNull final Api.Method apiMethod) {
        return null;
    }

    @Override
    public void putVariable(
            @NotNull final PsiVariable psiVariable, @NotNull final Api.Variable variable, final InitStrategy initStrategy) {
    }

    @Override
    public Api.Method getMethod(@NotNull final PsiMethod psiMethod, final InitStrategy initStrategy) {
        return null;
    }

    @Override
    public Api.Variable getVariable(@NotNull final PsiVariable variable, final InitStrategy initStrategy) {
        return null;
    }

    @Override
    public PsiVariable getPsiVariable(final Api.Variable apiVariable) {
        return null;
    }

    @Override
    public Api.SourceClass getSourceClass(@NotNull final PsiClass psiClass, final InitStrategy initStrategy) {
        return null;
    }

    @Override
    public void putSourceClass(@NotNull final PsiClass psiClass, @NotNull final Api.SourceClass sourceClass, final InitStrategy initStrategy) {

    }
}
