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

public class BarData {
    private final String id;
    private final String alphaId;
    private List<AlphaData> alphaData;

    public BarData(final String id, final String alphaId) {
        this.id = id;
        this.alphaId = alphaId;
    }

    public String getId() {
        return id;
    }

    public String getAlphaId() {
        return alphaId;
    }

    public List<AlphaData> getAlphaData() {
        return alphaData;
    }

    public void setAlphaData(final List<AlphaData> alphaData) {
        this.alphaData = alphaData;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BarData barData = (BarData) o;
        return Objects.equals(id, barData.id) && Objects.equals(alphaId, barData.alphaId) && Objects.equals(alphaData, barData.alphaData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alphaId, alphaData);
    }
}
