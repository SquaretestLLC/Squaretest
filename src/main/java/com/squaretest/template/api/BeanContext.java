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
package com.squaretest.template.api;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BeanContext {
    @NotNull
    private final Api.Type topLevelClassType;
    private final int minSettersToCall;
    private final int maxSettersToCall;

    public BeanContext(@NotNull final Api.Type topLevelClassType, final int minSettersToCall, final int maxSettersToCall) {
        this.topLevelClassType = topLevelClassType;
        this.minSettersToCall = minSettersToCall;
        this.maxSettersToCall = maxSettersToCall;
    }

    @NotNull
    public Api.Type getTopLevelClassType() {
        return topLevelClassType;
    }

    public int getMinSettersToCall() {
        return minSettersToCall;
    }

    public int getMaxSettersToCall() {
        return maxSettersToCall;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final BeanContext that = (BeanContext) o;
        return Objects.equals(topLevelClassType.getType().getCanonicalText(),
                that.topLevelClassType.getType().getCanonicalText())
                && minSettersToCall == that.minSettersToCall && maxSettersToCall == that.maxSettersToCall;
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLevelClassType.getCanonicalName(), minSettersToCall, maxSettersToCall);
    }
}
