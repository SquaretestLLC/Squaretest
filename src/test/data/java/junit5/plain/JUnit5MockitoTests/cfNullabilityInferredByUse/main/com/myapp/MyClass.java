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

import java.util.Optional;

public class MyClass {
    private final OrderService orderService;

    public MyClass(final OrderService orderService) {
        this.orderService = orderService;
    }

    public String getOrderDescription1(final String orderId) {
        final Order order = orderService.getOrderWithId1(orderId);
        if(order != null) {
            return order.getDescription();
        }
        return null;
    }

    public String getOrderDescription2(final String orderId) {
        Order order;
        order = orderService.getOrderWithId1(orderId);
        if(order != null) {
            return order.getDescription();
        }
        return null;
    }

    public String getOrderDescription3(final String orderId) {
        final Order order = orderService.getOrderWithId1(orderId);
        if(order == null) {
            return null;
        }
        return order.getDescription();
    }

    public String getOrderDescription4(final String orderId) {
        final Order order = getOrderHelper4(orderId);
        if(order == null) {
            return null;
        }
        return order.getDescription();
    }

    public String getOrderDescription5(final String orderId) {
        Order order = orderService.getOrderWithId1(orderId);
        if(order != null) {
            return order.getDescription();
        }
        order = orderService.getOrderWithId2(orderId);
        if(order != null) {
            return order.getDescription();
        }
        return null;
    }

    public String getOrderDescription6(final String orderId) {
        Order order = orderService.getOrderWithId1(orderId);
        return Optional.ofNullable(order).orElseThrow().getDescription();
    }

    private Order getOrderHelper4(final String orderId) {
        try {
            return orderService.getOrderWithId1(orderId);
        } catch (final Exception ex) {
            return null;
        }
    }

    public Order getOrder1(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).orElseThrow();
    }

    public Order getOrder2(final String orderId) {
        final Order orderWithId = orderService.getOrderWithId1(orderId);
        return Optional.ofNullable(orderWithId).orElseThrow();
    }
}
