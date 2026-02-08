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

public class FooAndBarData {
    private final FooData fooData;
    private final BarData barData;

    public FooAndBarData(FooData fooData, BarData barData) {
        this.fooData = fooData;
        this.barData = barData;
    }

    public FooData getFooData() {
        return fooData;
    }

    public BarData getBarData() {
        return barData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FooAndBarData that = (FooAndBarData) o;
        return Objects.equals(fooData, that.fooData) && Objects.equals(barData, that.barData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fooData, barData);
    }
}
