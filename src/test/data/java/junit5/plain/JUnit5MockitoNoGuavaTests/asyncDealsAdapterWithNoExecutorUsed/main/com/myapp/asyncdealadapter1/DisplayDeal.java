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
package com.myapp.asyncdealadapter1;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Objects;

public class DisplayDeal {
    private final String id;
    private final String productName;
    private final BigDecimal price;
    private final URI imageUri;

    public DisplayDeal(final String id, final String productName, final BigDecimal price, final URI imageUri) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.imageUri = imageUri;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final DisplayDeal that = (DisplayDeal) o;
        return Objects.equals(id, that.id) && Objects.equals(productName, that.productName) && Objects.equals(price,
                that.price) && Objects.equals(imageUri, that.imageUri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, price, imageUri);
    }
}
