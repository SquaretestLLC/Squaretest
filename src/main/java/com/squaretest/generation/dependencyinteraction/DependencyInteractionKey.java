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

import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class DependencyInteractionKey {
    @NotNull
    private final PsiField psiField;
    @NotNull
    private final PsiMethod psiMethod;

    public DependencyInteractionKey(@NotNull final PsiField psiField, @NotNull final PsiMethod psiMethod) {
        this.psiField = psiField;
        this.psiMethod = psiMethod;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final DependencyInteractionKey that = (DependencyInteractionKey) o;
        return psiField == that.psiField && psiMethod == that.psiMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(System.identityHashCode(psiField), System.identityHashCode(psiMethod));
    }
}
