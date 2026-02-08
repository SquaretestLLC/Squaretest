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

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Deal {
    private UUID id;
    private String productName;
    private BigDecimal price;
    private int quantityRemaining;
    private String imageUrl;

    public Deal() {

    }

    public Deal(final UUID id, final String productName, final BigDecimal price, final int quantityRemaining, final String imageUrl) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantityRemaining = quantityRemaining;
        this.imageUrl = imageUrl;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public int getQuantityRemaining() {
        return quantityRemaining;
    }

    public void setQuantityRemaining(final int quantityRemaining) {
        this.quantityRemaining = quantityRemaining;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Deal deal = (Deal) o;
        return quantityRemaining == deal.quantityRemaining && Objects.equals(id, deal.id) && Objects.equals(productName, deal.productName) && Objects.equals(price, deal.price) && Objects.equals(imageUrl, deal.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, price, quantityRemaining, imageUrl);
    }
}
