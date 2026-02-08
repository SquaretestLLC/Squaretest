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

import javax.persistence.Id;

public class Order {
    @Id
    private String orderId;
    private String orderFirstValue;
    private String orderSecondValue;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }

    public String getOrderFirstValue() {
        return orderFirstValue;
    }

    public void setOrderFirstValue(final String orderFirstValue) {
        this.orderFirstValue = orderFirstValue;
    }

    public String getOrderSecondValue() {
        return orderSecondValue;
    }

    public void setOrderSecondValue(final String orderSecondValue) {
        this.orderSecondValue = orderSecondValue;
    }
}
