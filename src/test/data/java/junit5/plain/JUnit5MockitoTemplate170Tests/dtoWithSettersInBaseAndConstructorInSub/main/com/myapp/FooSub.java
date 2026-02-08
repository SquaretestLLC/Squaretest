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
package com.myapp;

import java.util.Objects;

public class FooSub extends FooBase {
    private String fooSubValue;
    private String fooSubOtherValue;

    public FooSub() {

    }

    public FooSub(
            final String fooBaseId, final String fooBaseValue, final String fooBaseOtherValue,
            final String fooSubValue, final String fooSubOtherValue) {
        super(fooBaseId, fooBaseValue, fooBaseOtherValue);
        this.fooSubValue = fooSubValue;
        this.fooSubOtherValue = fooSubOtherValue;
    }

    public String getFooSubValue() {
        return fooSubValue;
    }

    public void setFooSubValue(final String fooSubValue) {
        this.fooSubValue = fooSubValue;
    }

    public String getFooSubOtherValue() {
        return fooSubOtherValue;
    }

    public void setFooSubOtherValue(final String fooSubOtherValue) {
        this.fooSubOtherValue = fooSubOtherValue;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        final FooSub fooSub = (FooSub) o;
        return Objects.equals(fooSubValue, fooSub.fooSubValue) && Objects.equals(fooSubOtherValue, fooSub.fooSubOtherValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fooSubValue, fooSubOtherValue);
    }
}
