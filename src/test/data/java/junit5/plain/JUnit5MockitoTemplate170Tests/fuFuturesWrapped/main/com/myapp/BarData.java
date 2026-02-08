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

import java.time.LocalDateTime;
import java.util.Objects;

public class BarData {
    private final String barId;
    private final String otherId;
    private final LocalDateTime createDate;

    public BarData(String barId, String otherId, LocalDateTime createDate) {
        this.barId = barId;
        this.otherId = otherId;
        this.createDate = createDate;
    }

    public String getBarId() {
        return barId;
    }

    public String getOtherId() {
        return otherId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BarData barData = (BarData) o;
        return Objects.equals(barId, barData.barId) && Objects.equals(otherId, barData.otherId) && Objects.equals(createDate, barData.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barId, otherId, createDate);
    }
}
