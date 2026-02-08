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
package com.squaretest.generation.keys;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class IdentityKeyTuple<T1, T2> {
    @NotNull
    private final T1 t1;
    @NotNull
    private final T2 t2;

    public IdentityKeyTuple(@NotNull final T1 t1, @NotNull final T2 t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final IdentityKeyTuple<?, ?> that = (IdentityKeyTuple<?, ?>) o;
        return t1 == that.t1 && t2 == that.t2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(System.identityHashCode(t1), System.identityHashCode(t2));
    }
}
