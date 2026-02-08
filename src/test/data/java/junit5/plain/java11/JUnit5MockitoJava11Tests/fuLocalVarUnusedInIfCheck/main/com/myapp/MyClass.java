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
    private final OrderService altOrderService;
    private final OrderDataService orderDataService;
    private final MetricService metricService;

    public MyClass(
            final OrderService orderService, final OrderService altOrderService,
            final OrderDataService orderDataService, final MetricService metricService) {
        this.orderService = orderService;
        this.altOrderService = altOrderService;
        this.orderDataService = orderDataService;
        this.metricService = metricService;
    }

    public Order getOrder1(final String orderId, final boolean includeFullOrder) {
        final Order order = orderService.getOrderWithId1(orderId);
        metricService.recordFirstGetOrderCallSuccess(orderId);
        if (order == null) {
            metricService.recordNullOrder(orderId);
            return null;
        }
        if (includeFullOrder) {
            metricService.recordIncludeFullOrder(orderId);
            final String data = orderDataService.getOrderData(orderId);
            order.setData(data);
        }
        return order;
    }

    public Order getOrder2(final String orderId, final boolean includeFullOrder) {
        final Order order = orderService.getOrderWithId1(orderId);
        metricService.recordFirstGetOrderCallSuccess(orderId);
        if (order != null && includeFullOrder) {
            metricService.recordIncludeFullOrder(orderId);
            final String data = orderDataService.getOrderData(orderId);
            order.setData(data);
        }
        return order;
    }

    public Order getOrder3(final String orderId, final boolean includeFullOrder) {
        final Order order = orderService.getOrderWithId1(orderId);
        metricService.recordFirstGetOrderCallSuccess(orderId);
        if (includeFullOrder) {
            if (order == null) {
                metricService.recordNullOrder(orderId);
                return null;
            }
            metricService.recordIncludeFullOrder(orderId);
            final String data = orderDataService.getOrderData(orderId);
            order.setData(data);
        }
        return order;
    }

    public Order getOrder4(final String orderId, final boolean includeFullOrder) {
        final Order order = orderService.getOrderWithId1(orderId);
        metricService.recordFirstGetOrderCallSuccess(orderId);
        if (orderId.endsWith("FU")) {
            metricService.recordOrderIdEndsWithFu(orderId);
            return null;
        }
        if (order == null) {
            metricService.recordNullOrder(orderId);
            return null;
        }
        if (includeFullOrder) {
            metricService.recordIncludeFullOrder(orderId);
            final String data = orderDataService.getOrderData(orderId);
            order.setData(data);
        }
        return order;
    }

    public Order getOrder5(final String orderId, final boolean includeFullOrder) {
        Order order = orderService.getOrderWithId1(orderId);
        metricService.recordFirstGetOrderCallSuccess(orderId);
        if (order == null) {
            metricService.recordFirstCallNull(orderId);
            order = altOrderService.getOrderWithId1(orderId);
        }
        if (order == null) {
            return null;
        }
        if (includeFullOrder) {
            order.setData(orderDataService.getOrderData(orderId));
        }
        return order;
    }

    public Order getOrder6(final String orderId, final boolean includeFullOrder) {
        Order order = orderService.getOrderWithId1(orderId);
        metricService.recordFirstGetOrderCallSuccess(orderId);
        order = order == null ? altOrderService.getOrderWithId1(orderId) : order;
        if (order == null) {
            metricService.recordFirstCallNull(orderId);
            return null;
        }
        if (includeFullOrder) {
            order.setData(orderDataService.getOrderData(orderId));
        }
        return order;
    }
}
