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
import java.util.List;
import java.util.Objects;

public class Order {
    private String id;
    private String shipAddress;
    private LocalDateTime shipDate;
    private List<Product> products;

    private String otherId;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(final String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public LocalDateTime getShipDate() {
        return shipDate;
    }

    public void setShipDate(final LocalDateTime shipDate) {
        this.shipDate = shipDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(final List<Product> products) {
        this.products = products;
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(final String otherId) {
        this.otherId = otherId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(shipAddress, order.shipAddress) && Objects.equals(shipDate, order.shipDate) && Objects.equals(products, order.products) && Objects.equals(otherId, order.otherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shipAddress, shipDate, products, otherId);
    }
}
