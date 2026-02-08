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

import java.util.List;
import java.util.Objects;

public class FooData {
    private final String id;
    private final String barId;
    private List<BarData> barData;

    public FooData(final String id, final String barId) {
        this.id = id;
        this.barId = barId;
    }

    public String getId() {
        return id;
    }

    public String getBarId() {
        return barId;
    }

    public List<BarData> getBarData() {
        return barData;
    }

    public void setBarData(final List<BarData> barData) {
        this.barData = barData;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FooData fooData = (FooData) o;
        return Objects.equals(id, fooData.id) && Objects.equals(barId, fooData.barId) && Objects.equals(barData, fooData.barData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, barId, barData);
    }
}
