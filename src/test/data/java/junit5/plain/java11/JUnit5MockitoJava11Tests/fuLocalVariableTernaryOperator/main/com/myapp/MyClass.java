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
    private final MetricService metricService;

    public MyClass(final OrderService orderService, final MetricService metricService) {
        this.orderService = orderService;
        this.metricService = metricService;
    }

    public String getOrderWithId1(final String orderId) {
        final Order order = orderService.getOrderWithId1(orderId);
        return order != null ? order.getDescription() : null;
    }

    public String getOrderWithId2(final String orderId) {
        final Order order = orderService.getOrderWithId1(orderId);
        return order == null ? null : order.getDescription();
    }

    public String getOrderWithId3(final String orderId) {
        final Optional<Order> order = Optional.ofNullable(orderService.getOrderWithId1(orderId));
        return order.isPresent() ? order.get().getDescription() : null;
    }

    public String getOrderWithId4(final String orderId) {
        final Optional<Order> order = Optional.ofNullable(orderService.getOrderWithId1(orderId));
        return !order.isPresent() ? null : order.get().getDescription();
    }

    public String getOrderWithId5(final String orderId) {
        final Optional<Order> order = Optional.ofNullable(orderService.getOrderWithId1(orderId));
        return order.isEmpty() ? null : order.get().getDescription();
    }

    public String getOrderWithId6(final String orderId) {
        final Order order = orderService.getOrderWithId1(orderId);
        return order != null ? getDescription(order) : null;
    }

    public String getOrderWithId7(final String orderId) {
        final Order order = orderService.getOrderWithId1(orderId);
        return order == null ? null : getDescription(order);
    }

    public String getOrderWithId8(final String orderId) {
        final Optional<Order> order = Optional.ofNullable(orderService.getOrderWithId1(orderId));
        return order.isPresent() ? getDescription(order.get()) : null;
    }

    public String getOrderWithId9(final String orderId) {
        final Optional<Order> order = Optional.ofNullable(orderService.getOrderWithId1(orderId));
        return !order.isPresent() ? null : getDescription(order.get());
    }

    public String getOrderWithId10(final String orderId) {
        final Optional<Order> order = Optional.ofNullable(orderService.getOrderWithId1(orderId));
        return order.isEmpty() ? null : getDescription(order.get());
    }

    private String getDescription(final Order order) {
        metricService.recordOrderPresent(order.getOrderId());
        return order.getDescription();
    }
}
