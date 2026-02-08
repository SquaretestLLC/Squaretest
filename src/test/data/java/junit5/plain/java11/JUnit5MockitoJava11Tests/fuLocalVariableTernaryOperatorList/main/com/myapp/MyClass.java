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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyClass {
    private final OrderService orderService;
    private final MetricService metricService;

    public MyClass(final OrderService orderService, final MetricService metricService) {
        this.orderService = orderService;
        this.metricService = metricService;
    }

    public List<Order> getOrders1(final String searchCriteria) {
        final List<Order> orders = orderService.getOrders1(searchCriteria);
        return orders.isEmpty() ? getDefaultOrders(searchCriteria) : orders;
    }

    public List<Order> getChildOrders1(final String searchCriteria) {
        final List<Order> orders = orderService.getOrders1(searchCriteria);
        return orders.isEmpty() ? orders : orders.get(0).getChildOrders();
    }

    private List<Order> getDefaultOrders(final String searchCriteria) {
        metricService.recordGetDefaultOrdersCalled(searchCriteria);
        return Arrays.asList(new Order("defaultId", "defaultDescription", Collections.emptyList()));
    }

    public Order getFirstChildOrder(final String orderId) {
        final Order order = orderService.getOrder1(orderId);
        return order == null ? order : getFirstChildOrder(order);
    }

    private Order getFirstChildOrder(final Order order) {
        final List<Order> childOrders = order.getChildOrders();
        if(childOrders.isEmpty()) {
            return null;
        }
        return childOrders.get(0);
    }
}
