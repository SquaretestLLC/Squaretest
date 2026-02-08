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
package com.squaretest.template.impl;

import com.squaretest.template.api.Api;
import org.jetbrains.annotations.NotNull;

public class ArgumentExpressionImpl extends PropsHolderImpl implements Api.ArgumentExpression {
    @NotNull
    private final Api.Type declaredType;
    @NotNull
    private final Api.Type actualType;

    @NotNull
    private final Api.Type internalActualType;

    public ArgumentExpressionImpl(
            @NotNull final Api.Type declaredType,
            @NotNull final Api.Type actualType,
            @NotNull final Api.Type internalActualType) {
        this.declaredType = declaredType;
        this.actualType = actualType;
        this.internalActualType = internalActualType;
    }

    @Override
    @NotNull
    public Api.Type getDeclaredType() {
        return declaredType;
    }

    @Override
    @NotNull
    public Api.Type getActualType() {
        return actualType;
    }

    @NotNull
    public Api.Type getInternalActualType() {
        return internalActualType;
    }
}
