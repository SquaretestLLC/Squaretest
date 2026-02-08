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

import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.squaretest.generation.dependencyinteraction.Node;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class CacheKey {
    @NotNull
    private final Node node;
    @NotNull
    private final PsiField diField;
    @NotNull
    private final PsiMethod diMethod;
    @Nullable
    private final String thrownExceptionCanonicalText;
    @Nullable
    private final ReturnOutcome returnOutcome;
    private final boolean preserveUnknown;

    public CacheKey(
            @NotNull final Node node,
            @NotNull final PsiField diField, @NotNull final PsiMethod diMethod,
            @Nullable final String thrownExceptionCanonicalText, @Nullable final ReturnOutcome returnOutcome,
            final boolean preserveUnknown) {
        this.node = node;
        this.diField = diField;
        this.diMethod = diMethod;
        this.thrownExceptionCanonicalText = thrownExceptionCanonicalText;
        this.returnOutcome = returnOutcome;
        this.preserveUnknown = preserveUnknown;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final CacheKey cacheKey = (CacheKey) o;
        return returnOutcome == cacheKey.returnOutcome
                && Objects.equals(node, cacheKey.node)
                && diField == cacheKey.diField
                && diMethod == cacheKey.diMethod
                && Objects.equals(thrownExceptionCanonicalText, cacheKey.thrownExceptionCanonicalText)
                && preserveUnknown == cacheKey.preserveUnknown;
    }

    @Override
    public int hashCode() {
        return Objects.hash(node,
                System.identityHashCode(diField),
                System.identityHashCode(diMethod),
                thrownExceptionCanonicalText, returnOutcome, preserveUnknown);
    }
}
