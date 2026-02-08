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

import com.google.common.base.Optional;

public class MyClass {
    private final OrderService orderService;
    private final MetricService metricService;

    public MyClass(final OrderService orderService, final MetricService metricService) {
        this.orderService = orderService;
        this.metricService = metricService;
    }

    public Order tryCreate1(final Order order) {
        final String orderId = order.getOrderId();
        final Order existingOrder = orderService.getOrderWithId1(orderId);
        if (existingOrder != null) throw new OrderAlreadyExistsException(orderId);
        metricService.recordCreateSuccess(existingOrder.getOrderId());
        return orderService.create(existingOrder);
    }

    public Order tryCreate2(final Order order) {
        final String orderId = order.getOrderId();
        final Optional<Order> existingOrder = Optional.fromNullable(orderService.getOrderWithId1(orderId));
        if (existingOrder.isPresent()) throw new OrderAlreadyExistsException(orderId);
        metricService.recordCreateSuccess(order.getOrderId());
        return orderService.create(order);
    }

    public Order tryCreate3(final Order order) {
        final String orderId = order.getOrderId();
        final Order orderOrNull = Optional.fromNullable(orderService.getOrderWithId1(orderId)).or((Order)null);
        if (orderOrNull != null) throw new OrderAlreadyExistsException(orderId);
        metricService.recordCreateSuccess(order.getOrderId());
        return orderService.create(order);
    }

    public Order tryCreate4(final Order order) {
        final String orderId = order.getOrderId();
        final boolean orderExists = orderService.getOrderWithId1(orderId) != null;
        if (orderExists) throw new OrderAlreadyExistsException(orderId);
        metricService.recordCreateSuccess(order.getOrderId());
        return orderService.create(order);
    }

    public Order tryCreate5(final Order order) {
        final String orderId = order.getOrderId();
        final boolean orderExists = Optional.fromNullable(orderService.getOrderWithId1(orderId)).isPresent();
        if (orderExists) throw new OrderAlreadyExistsException(orderId);
        metricService.recordCreateSuccess(order.getOrderId());
        return orderService.create(order);
    }

    public Order tryCreate6(final Order order) {
        final String orderId = order.getOrderId();
        final boolean exists = Optional.fromNullable(orderService.getOrderWithId1(orderId)).or((Order)null) != null;
        if (exists) throw new OrderAlreadyExistsException(orderId);
        metricService.recordCreateSuccess(order.getOrderId());
        return orderService.create(order);
    }

    public Order tryCreate7(final Order order) {
        final String orderId = order.getOrderId();
        final Optional<Order> existingOrder = Optional.fromNullable(orderService.getOrderWithId1(orderId));
        if (existingOrder.isPresent()) throw new OrderAlreadyExistsException(orderId);
        metricService.recordCreateSuccess(order.getOrderId());
        return orderService.create(order);
    }

    public Order tryCreate8(final Order order) {
        final String orderId = order.getOrderId();
        final Order orderOrNull = Optional.fromNullable(orderService.getOrderWithId1(orderId)).orNull();
        if (orderOrNull != null) throw new OrderAlreadyExistsException(orderId);
        metricService.recordCreateSuccess(order.getOrderId());
        return orderService.create(order);
    }

    public Order updateOrder1(final Order order) {
        final String orderId = order.getOrderId();
        final Order existingOrder = orderService.getOrderWithId1(orderId);
        if (existingOrder == null) throw new OrderNotFoundException(orderId);
        metricService.recordUpdateSuccess(orderId);
        return orderService.update(order);
    }

    public Order updateOrder2(final Order order) {
        final String orderId = order.getOrderId();
        final Order existingOrder = orderService.getOrderWithId1(orderId);
        if (!Optional.fromNullable(existingOrder).isPresent()) throw new OrderNotFoundException(orderId);
        metricService.recordUpdateSuccess(orderId);
        return orderService.update(order);
    }

    public Order updateOrder3(final Order order) {
        final String orderId = order.getOrderId();
        final Order existingOrder = orderService.getOrderWithId1(orderId);
        if (!Optional.fromNullable(existingOrder).isPresent()) throw new OrderNotFoundException(orderId);
        metricService.recordUpdateSuccess(orderId);
        return orderService.update(order);
    }

    public Order updateOrder4(final Order order) {
        final String orderId = order.getOrderId();
        if (Optional.fromNullable(orderService.getOrderWithId1(orderId)).or((Order)null) == null)
            throw new OrderNotFoundException(orderId);
        metricService.recordUpdateSuccess(orderId);
        return orderService.update(order);
    }

    public Order updateOrder5(final Order order) {
        final String orderId = order.getOrderId();
        final boolean exists = orderService.getOrderWithId1(orderId) != null;
        if (!exists) throw new OrderNotFoundException(orderId);
        metricService.recordUpdateSuccess(orderId);
        return orderService.update(order);
    }

    public Order updateOrder6(final Order order) {
        final String orderId = order.getOrderId();
        final boolean exists = Optional.fromNullable(orderService.getOrderWithId1(orderId)).isPresent();
        if (!exists) throw new OrderNotFoundException(orderId);
        metricService.recordUpdateSuccess(orderId);
        return orderService.update(order);
    }

    public Order updateOrder7(final Order order) {
        final String orderId = order.getOrderId();
        final boolean exists = Optional.fromNullable(orderService.getOrderWithId1(orderId)).isPresent();
        if (!exists) throw new OrderNotFoundException(orderId);
        metricService.recordUpdateSuccess(orderId);
        return orderService.update(order);
    }

    public Order updateOrder8(final Order order) {
        final String orderId = order.getOrderId();
        final boolean exists = Optional.fromNullable(orderService.getOrderWithId1(orderId)).or((Order)null) != null;
        if (!exists) throw new OrderNotFoundException(orderId);
        metricService.recordUpdateSuccess(orderId);
        return orderService.update(order);
    }

    public Order getOrderOrDefault1(final String orderId) {
        final Order order = orderService.getOrderWithId1(orderId);
        if(order == null) return getDefaultOrder(orderId);
        return fixOrder(order);
    }

    public Order getOrderOrDefault2(final String orderId) {
        final Order order = orderService.getOrderWithId1(orderId);
        if(order != null) return fixOrder(order);
        return getDefaultOrder(orderId);
    }

    public Order getOrderOrDefault3(final String orderId) {
        final Order order = orderService.getOrderWithId1(orderId);
        if(order == null)
            return getDefaultOrder(orderId);
        else
            return fixOrder(order);
    }

    public Order getOrderOrDefault4(final String orderId) {
        final Order order = orderService.getOrderWithId1(orderId);
        if(order != null)
            return fixOrder(order);
        else
            return getDefaultOrder(orderId);
    }

    private Order getDefaultOrder(final String orderId) {
        metricService.recordGetDefaultOrderCalled(orderId);
        return new Order(orderId, "default description");
    }

    private Order fixOrder(final Order order) {
        metricService.recordFixOrderCalled(order.getOrderId());
        return order;
    }
}
