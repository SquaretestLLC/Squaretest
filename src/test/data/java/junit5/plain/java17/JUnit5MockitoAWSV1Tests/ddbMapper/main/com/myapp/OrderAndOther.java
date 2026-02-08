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

public class OrderAndOther {
    private final Order order;
    private final String otherData;

    public OrderAndOther(final Order order, final String otherData) {
        this.order = order;
        this.otherData = otherData;
    }

    public Order getOrder() {
        return order;
    }

    public String getOtherData() {
        return otherData;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OrderAndOther that = (OrderAndOther) o;
        return Objects.equals(order, that.order) && Objects.equals(otherData, that.otherData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, otherData);
    }
}
