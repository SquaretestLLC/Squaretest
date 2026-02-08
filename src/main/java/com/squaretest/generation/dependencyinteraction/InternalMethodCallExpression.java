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

import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record InternalMethodCallExpression(List<PsiType> paramTypes, PsiType type,
                                           @Nullable PsiMethodCallExpression methodCallExpression) {

    public InternalMethodCallExpression(
            @NotNull final List<PsiType> paramTypes,
            @Nullable final PsiType type,
            @Nullable final PsiMethodCallExpression methodCallExpression) {
        this.paramTypes = paramTypes;
        this.type = type;
        this.methodCallExpression = methodCallExpression;
    }

    @Override
    @Nullable
    public PsiType type() {
        return type;
    }

    @NotNull
    public PsiExpression[] getArgumentExpressions() {
        if(methodCallExpression != null) {
            return methodCallExpression.getArgumentList().getExpressions();
        }
        return PsiExpression.EMPTY_ARRAY;
    }
}
