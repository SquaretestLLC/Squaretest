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
import org.jetbrains.annotations.Nullable;

public class SimpleExitInfoImpl extends PropsHolderImpl implements Api.SimpleExitInfo {

    public static final Api.SimpleExitInfo EmptyInfo = new SimpleExitInfoImpl(null, null);

    @Nullable
    private final String returnExpression;
    @Nullable
    private final Api.Exception thrownException;

    public SimpleExitInfoImpl(@Nullable final String returnExpression, @Nullable final Api.Exception thrownException) {
        this.returnExpression = returnExpression;
        this.thrownException = thrownException;
    }

    @Override
    @Nullable
    public String getReturnExpression() {
        return returnExpression;
    }

    @Override
    @Nullable
    public Api.Exception getThrownException() {
        return thrownException;
    }
}
