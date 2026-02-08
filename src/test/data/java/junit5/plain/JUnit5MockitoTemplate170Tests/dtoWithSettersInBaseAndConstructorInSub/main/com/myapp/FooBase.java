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

public class FooBase {
    private String fooBaseId;
    private String fooBaseValue;
    private String fooBaseOtherValue;

    public FooBase() {
    }

    public FooBase(final String fooBaseId, final String fooBaseValue, final String fooBaseOtherValue) {
        this.fooBaseId = fooBaseId;
        this.fooBaseValue = fooBaseValue;
        this.fooBaseOtherValue = fooBaseOtherValue;
    }

    public String getFooBaseId() {
        return fooBaseId;
    }

    public void setFooBaseId(final String fooBaseId) {
        this.fooBaseId = fooBaseId;
    }

    public String getFooBaseValue() {
        return fooBaseValue;
    }

    public void setFooBaseValue(final String fooBaseValue) {
        this.fooBaseValue = fooBaseValue;
    }

    public String getFooBaseOtherValue() {
        return fooBaseOtherValue;
    }

    public void setFooBaseOtherValue(final String fooBaseOtherValue) {
        this.fooBaseOtherValue = fooBaseOtherValue;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FooBase fooBase = (FooBase) o;
        return Objects.equals(fooBaseId, fooBase.fooBaseId) && Objects.equals(fooBaseValue, fooBase.fooBaseValue) && Objects.equals(fooBaseOtherValue, fooBase.fooBaseOtherValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fooBaseId, fooBaseValue, fooBaseOtherValue);
    }
}
