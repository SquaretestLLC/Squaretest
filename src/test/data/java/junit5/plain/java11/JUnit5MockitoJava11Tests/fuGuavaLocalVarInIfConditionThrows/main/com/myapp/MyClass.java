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

    public Order getOrder1(final String orderId) {
        final Optional<Order> order = orderService.getOrderWithId1(orderId);
        if(order.toJavaUtil().orElseThrow(() -> new OrderNotFoundException(orderId)).getOrderId().startsWith("FU")) {
            metricService.recordOrPresent(orderId);
            final String newOrderId = order.get().getOrderId();
            return new Order(newOrderId, "desc");
        }
        metricService.recordOutsideIfCheck(orderId);
        return order.get();
    }

    public Order getOrder2(final String orderId) {
        final Optional<Order> order = orderService.getOrderWithId1(orderId);
        if(order.isPresent()) {
            metricService.recordOrPresent(orderId);
            return order.get();
        }
        return order.toJavaUtil().orElseThrow();
    }

    public Order getOrder3(final String orderId) {
        final Optional<Order> order = orderService.getOrderWithId1(orderId);
        if(order.isPresent()) {
            metricService.recordOrPresent(orderId);
            return order.get();
        } else {
            return order.toJavaUtil().orElseThrow();
        }
    }

    public Order getOrder4(final String orderId) {
        final Optional<Order> order = orderService.getOrderWithId1(orderId);
        if(order.isPresent()) {
            metricService.recordOrPresent(orderId);
            return order.get();
        } else
            return order.toJavaUtil().orElseThrow();
    }

    public Order getOrder5(final String orderId) {
        final Optional<Order> order = orderService.getOrderWithId1(orderId);
        if(order.isPresent()) {
            metricService.recordOrPresent(orderId);
            return order.get();
        }
        return order.get();
    }

    public Order getOrder6(final String orderId) {
        final Optional<Order> order = orderService.getOrderWithId1(orderId);
        if(order.isPresent()) {
            metricService.recordOrPresent(orderId);
            return order.get();
        } else {
            return order.get();
        }
    }

    public Order getOrder7(final String orderId) {
        final Optional<Order> order = orderService.getOrderWithId1(orderId);
        if(order.isPresent()) {
            metricService.recordOrPresent(orderId);
            return order.get();
        } else
            return order.get();
    }

    public Order getOrder8(final String orderId) {
        final Optional<Order> order = orderService.getOrderWithId1(orderId);
        if(!order.isPresent()) {
            return order.toJavaUtil().orElseThrow();
        }
        metricService.recordOrPresent(orderId);
        return order.get();
    }
}
