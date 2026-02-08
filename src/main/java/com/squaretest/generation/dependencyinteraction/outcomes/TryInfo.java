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

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiTryStatement;
import org.jetbrains.annotations.NotNull;

public class TryInfo extends ParentInfo {
    @NotNull
    private final PsiTryStatement tryStatement;
    @NotNull
    private final TryElementType tryElementType;

    public TryInfo(
            @NotNull final PsiTryStatement tryStatement,
            @NotNull final PsiElement parentElement,
            @NotNull final TryElementType tryElementType) {
        super(parentElement);
        this.tryStatement = tryStatement;
        this.tryElementType = tryElementType;
    }

    @NotNull
    public PsiTryStatement getTryStatement() {
        return tryStatement;
    }

    @NotNull
    public TryElementType getTryElementType() {
        return tryElementType;
    }
}
