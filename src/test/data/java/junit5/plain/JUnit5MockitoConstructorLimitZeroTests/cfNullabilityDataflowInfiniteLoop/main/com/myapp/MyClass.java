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

public class MyClass {
    private final OrderService orderService;

    public MyClass(final OrderService orderService) {
        this.orderService = orderService;
    }

    public String getOrderDescription1(final String orderId) {
        final Order order = getOrderDescriptionImpl1(orderId, 0);
        if(order != null) {
            return order.getDescription();
        }
        return "placeholder";
    }

    private Order getOrderDescriptionImpl1(final String orderId, final int count) {
        if(count >= 10) {
            throw new RuntimeException("exceeded retries");
        }
        return getOrderDescriptionImpl1(orderId, count+1);
    }

    public String getOrderDescription2(final String orderId) {
        final Order order = getOrderDescriptionImpl2(orderId, 0);
        if(order != null) {
            return order.getDescription();
        }
        return "placeholder";
    }

    private Order getOrderDescriptionImpl2(final String orderId, final int count) {
        if(count >= 10) {
            throw new RuntimeException("exceeded retries");
        }
        final Order order = orderService.getOrderWithId2(orderId);
        if(order != null) {
            return order;
        }
        return getOrderDescriptionImpl1(orderId, count+1);
    }

    public String getOrderDescription3(final String orderId) {
        int count = 0;
        while(++count < 10) {
            Order order = orderService.getOrderWithId2(orderId);
            if (order != null) {
                return order.getDescription();
            }
        }
        return "placeholder";
    }

    public String getOrderDescription4(final String orderId) {
        final Order order = getOrderDescriptionImpl3(orderId, 0);
        if(order != null) {
            return order.getDescription();
        }
        return "placeholder";
    }

    private Order getOrderDescriptionImpl3(final String orderId, final int count) {
        if(count >= 10) {
            throw new RuntimeException("exceeded retries");
        }
        return getOrderDescriptionImpl4(orderId, count+1);
    }

    private Order getOrderDescriptionImpl4(final String orderId, final int count) {
        if(count >= 10) {
            throw new RuntimeException("exceeded retries");
        }
        return getOrderDescriptionImpl3(orderId, count+1);
    }

    public Order getOrderDescription5(final String orderId, final int count) {
        if(count >= 10) {
            throw new RuntimeException("exceeded retries");
        }
        final Order order = orderService.getOrderWithId2(orderId);
        if(order != null) {
            return order;
        }
        return getOrderDescription5(orderId, count + 1);
    }

    public String getOrderDescription6(final String orderId, final int count) {
        final Order order = orderService.getOrderWithId2(orderId);
        return order != null ? order.getDescription() : null;
    }
}
