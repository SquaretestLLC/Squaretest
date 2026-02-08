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

public class AllData {
    private final FooData fooData;
    private final FooExtraData fooExtraData;
    private final BarData barData;
    private final OtherData otherData;

    public AllData(FooData fooData, FooExtraData fooExtraData, BarData barData, OtherData otherData) {
        this.fooData = fooData;
        this.fooExtraData = fooExtraData;
        this.barData = barData;
        this.otherData = otherData;
    }

    public FooData getFooData() {
        return fooData;
    }

    public FooExtraData getFooExtraData() {
        return fooExtraData;
    }

    public BarData getBarData() {
        return barData;
    }

    public OtherData getOtherData() {
        return otherData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllData allData = (AllData) o;
        return Objects.equals(fooData, allData.fooData) && Objects.equals(fooExtraData, allData.fooExtraData) && Objects.equals(barData, allData.barData) && Objects.equals(otherData, allData.otherData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fooData, fooExtraData, barData, otherData);
    }
}
