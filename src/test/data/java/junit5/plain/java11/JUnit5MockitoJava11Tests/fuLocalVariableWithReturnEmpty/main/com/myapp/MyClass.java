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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MyClass {
    private final OrderService orderService;
    private final MetricService metricService;

    public MyClass(final OrderService orderService, final MetricService metricService) {
        this.orderService = orderService;
        this.metricService = metricService;
    }

    public List<Order> getOrders1(final String searchCriteria) {
        final List<Order> orders1 = orderService.getOrders1(searchCriteria);
        return orders1;
    }

    public List<Order> getOrders2(final String searchCriteria) {
        final List<Order> orders1 = orderService.getOrders1(searchCriteria);
        return Optional.ofNullable(orders1).orElse(Collections.emptyList());
    }

    public List<Order> getOrders3(final String searchCriteria) {
        final Optional<List<Order>> orders1 = Optional.ofNullable(orderService.getOrders1(searchCriteria));
        return orders1.orElse(List.of());
    }

    public List<Order> getOrders4(final String searchCriteria) {
        final List<Order> orders1 = orderService.getOrders1(searchCriteria);
        return Optional.ofNullable(orders1).orElse(null);
    }

    public List<Order> getOrders5(final String searchCriteria) {
        final List<Order> orders1 = orderService.getOrders1(searchCriteria);
        return getOrDefault1(orders1);
    }

    private List<Order> getOrDefault1(final List<Order> orders) {
        metricService.recordGetOrDefaultCalled();
        return orders == null ? Collections.emptyList() : orders;
    }

    public List<Order> getOrders6(final String searchCriteria) {
        final List<Order> orders3 = orderService.getOrders3(searchCriteria);
        return getOrDefault2(orders3);
    }

    private List<Order> getOrDefault2(final List<Order> orders) {
        metricService.recordGetOrDefaultCalled();
        return orders == null ? Collections.emptyList() : orders;
    }

    public List<Order> getOrders7(final String searchCriteria) {
        final List<Order> ordersHelper1 = getOrdersHelper1(searchCriteria);
        return ordersHelper1;
    }

    private List<Order> getOrdersHelper1(final String searchCriteria) {
        return orderService.getOrders1(searchCriteria);
    }

    public List<Order> getOrders8(final String searchCriteria) {
        final List<Order> ordersHelper2 = getOrdersHelper2(searchCriteria);
        return Optional.ofNullable(ordersHelper2).orElse(Collections.emptyList());
    }

    private List<Order> getOrdersHelper2(final String searchCriteria) {
        return orderService.getOrders1(searchCriteria);
    }

    public List<Order> getOrders9(final String searchCriteria) {
        final List<Order> ordersHelper3 = getOrdersHelper3(searchCriteria);
        return Optional.ofNullable(ordersHelper3).orElse(List.of());
    }

    private List<Order> getOrdersHelper3(final String searchCriteria) {
        return orderService.getOrders1(searchCriteria);
    }

    public List<Order> getOrders10(final String searchCriteria) {
        final Optional<List<Order>> ordersHelper4 = Optional.ofNullable(getOrdersHelper4(searchCriteria));
        return ordersHelper4.orElse(null);
    }

    private List<Order> getOrdersHelper4(final String searchCriteria) {
        return orderService.getOrders1(searchCriteria);
    }

    public List<Order> getOrders11(final String searchCriteria) {
        final List<Order> ordersHelper5 = getOrdersHelper5(searchCriteria);
        return getOrDefault1(ordersHelper5);
    }

    private List<Order> getOrdersHelper5(final String searchCriteria) {
        return orderService.getOrders1(searchCriteria);
    }

    public Order[] getOrders12(final String searchCriteria) {
        final List<Order> orders2 = orderService.getOrders2(searchCriteria);
        return orders2.toArray(new Order[0]);
    }

    public List<Order> getOrders13(final String searchCriteria) {
        final List<Order> orders2 = orderService.getOrders2(searchCriteria);
        return orders2.stream().map(this::updateDescription).collect(Collectors.toList());
    }

    public OrderData getOrders14(final String searchCriteria) {
        final List<Order> orders2 = orderService.getOrders2(searchCriteria);
        return new OrderData(orders2);
    }

    public List<String> getOrders15(final String searchCriteria) {
        final List<Order> orders2 = orderService.getOrders2(searchCriteria);
        return orders2.stream().map(Order::getOrderId).collect(Collectors.toList());
    }

    public int countOrders14(final String searchCriteria) {
        final List<Order> orders2 = orderService.getOrders2(searchCriteria);
        return orders2.size();
    }

    private Order updateDescription(final Order order) {
        metricService.recordUpdatingDescription(order);
        return new Order(order.getOrderId(), "updated");
    }

    public boolean orderExists1(final String searchCriteria) {
        final List<Order> orders1 = orderService.getOrders1(searchCriteria);
        return Optional.ofNullable(orders1).orElse(Collections.emptyList()).isEmpty();
    }

    public boolean orderExists2(final String searchCriteria) {
        final List<Order> orders = orderService.getOrders1(searchCriteria);
        return orders != null && !orders.isEmpty();
    }

    public boolean orderExists3(final String searchCriteria) {
        final List<Order> orders2 = orderService.getOrders2(searchCriteria);
        return !orders2.isEmpty();
    }

    public boolean orderExists4(final String searchCriteria, final Order orderToCheckFor) {
        final List<Order> orders2 = orderService.getOrders2(searchCriteria);
        return orders2.contains(orderToCheckFor);
    }

    public boolean orderExists5(final String searchCriteria) {
        final List<Order> orders2 = orderService.getOrders2(searchCriteria);
        return orders2.size() != 0;
    }

    public boolean orderExists6(final String searchCriteria) {
        final List<Order> orders2 = orderService.getOrders2(searchCriteria);
        return orders2.size() >= 0;
    }

    public boolean orderExists7(final String searchCriteria) {
        final List<Order> orders2 = orderService.getOrders2(searchCriteria);
        return orders2.size() > 0;
    }

    public boolean orderExists8(final String searchCriteria) {
        final List<Order> orders2 = orderService.getOrders2(searchCriteria);
        return orders2.size() + 1 > 1;
    }

    public boolean canCreate1(final String searchCriteria) {
        final List<Order> orders2 = orderService.getOrders2(searchCriteria);
        return orders2.isEmpty();
    }

    public boolean canCreate2(final String searchCriteria, final Order orderToCheckFor) {
        final List<Order> orders2 = orderService.getOrders2(searchCriteria);
        return !orders2.contains(orderToCheckFor);
    }

    public boolean canCreate3(final String searchCriteria) {
        final List<Order> orders2 = orderService.getOrders2(searchCriteria);
        return orders2.size() == 0;
    }
}
