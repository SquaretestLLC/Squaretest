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

public interface PsiToTemplateVarsMapper {
    void putMethod(@NotNull PsiMethod psiMethod, @NotNull Api.Method method, final InitStrategy initStrategy);

    PsiMethod getPsiMethod(@NotNull Api.Method apiMethod);

    void putVariable(@NotNull PsiVariable psiVariable, @NotNull Api.Variable variable, final InitStrategy initStrategy);

    Api.Method getMethod(@NotNull PsiMethod psiMethod, final InitStrategy initStrategy);

    default boolean hasMethod(@NotNull PsiMethod psiMethod, final InitStrategy initStrategy) {
        return getMethod(psiMethod, initStrategy) != null;
    }

    Api.Variable getVariable(@NotNull PsiVariable variable, final InitStrategy initStrategy);

    PsiVariable getPsiVariable(Api.Variable apiVariable);

    Api.SourceClass getSourceClass(@NotNull PsiClass psiClass, final InitStrategy initStrategy);

    void putSourceClass(@NotNull PsiClass psiClass, @NotNull Api.SourceClass sourceClass, final InitStrategy initStrategy);

}
