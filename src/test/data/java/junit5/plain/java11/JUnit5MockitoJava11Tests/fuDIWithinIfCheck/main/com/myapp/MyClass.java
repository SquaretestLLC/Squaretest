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

    public Order tryCreate1(final Order order) {
        final String orderId = order.getOrderId();
        if (orderService.getOrderWithId1(orderId) != null) {
            metricService.recordCreateFailed(order.getOrderId());
            throw new OrderAlreadyExistsException(orderId);
        }
        metricService.recordCreateSuccess(order.getOrderId());
        return orderService.create(order);
    }

    public Order tryCreate2(final Order order) {
        final String orderId = order.getOrderId();
        if (Optional.ofNullable(orderService.getOrderWithId1(orderId)).isPresent()) {
            metricService.recordCreateFailed(order.getOrderId());
            throw new OrderAlreadyExistsException(orderId);
        }
        metricService.recordCreateSuccess(order.getOrderId());
        return orderService.create(order);
    }

    public Order tryCreate3(final Order order) {
        final String orderId = order.getOrderId();
        if (Optional.ofNullable(orderService.getOrderWithId1(orderId)).orElse(null) != null) {
            metricService.recordCreateFailed(order.getOrderId());
            throw new OrderAlreadyExistsException(orderId);
        }
        metricService.recordCreateSuccess(order.getOrderId());
        return orderService.create(order);
    }

    public Order tryCreate4(final Order order) {
        final String orderId = order.getOrderId();
        if (!Optional.ofNullable(orderService.getOrderWithId1(orderId)).isEmpty()) {
            metricService.recordCreateFailed(order.getOrderId());
            throw new OrderAlreadyExistsException(orderId);
        }
        metricService.recordCreateSuccess(order.getOrderId());
        return orderService.create(order);
    }

    public Order updateOrder1(final Order order) {
        final String orderId = order.getOrderId();
        if (orderService.getOrderWithId1(orderId) == null) {
            metricService.recordUpdateFail(orderId);
            throw new OrderNotFoundException(orderId);
        }
        metricService.recordUpdateSuccess(orderId);
        return orderService.update(order);
    }

    public Order updateOrder2(final Order order) {
        final String orderId = order.getOrderId();
        if (!Optional.ofNullable(orderService.getOrderWithId1(orderId)).isPresent()) {
            metricService.recordUpdateFail(orderId);
            throw new OrderNotFoundException(orderId);
        }
        metricService.recordUpdateSuccess(orderId);
        return orderService.update(order);
    }

    public Order updateOrder3(final Order order) {
        final String orderId = order.getOrderId();
        if (Optional.ofNullable(orderService.getOrderWithId1(orderId)).isEmpty()) {
            metricService.recordUpdateFail(orderId);
            throw new OrderNotFoundException(orderId);
        }
        metricService.recordUpdateSuccess(orderId);
        return orderService.update(order);
    }

    public Order updateOrder4(final Order order) {
        final String orderId = order.getOrderId();
        if (Optional.ofNullable(orderService.getOrderWithId1(orderId)).orElse(null) == null) {
            metricService.recordUpdateFail(orderId);
            throw new OrderNotFoundException(orderId);
        }
        metricService.recordUpdateSuccess(orderId);
        return orderService.update(order);
    }
}
