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
        return orderService.getOrders1(searchCriteria);
    }

    public List<Order> getOrders2(final String searchCriteria) {
        return Optional.ofNullable(orderService.getOrders1(searchCriteria)).orElse(Collections.emptyList());
    }

    public List<Order> getOrders3(final String searchCriteria) {
        return Optional.ofNullable(orderService.getOrders1(searchCriteria)).orElse(List.of());
    }

    public List<Order> getOrders4(final String searchCriteria) {
        return Optional.ofNullable(orderService.getOrders1(searchCriteria)).orElse(null);
    }

    public List<Order> getOrders5(final String searchCriteria) {
        return getOrDefault1(orderService.getOrders1(searchCriteria));
    }

    private List<Order> getOrDefault1(final List<Order> orders) {
        metricService.recordGetOrDefaultCalled();
        return orders == null ? Collections.emptyList() : orders;
    }

    public List<Order> getOrders6(final String searchCriteria) {
        return getOrDefault2(orderService.getOrders3(searchCriteria));
    }

    private List<Order> getOrDefault2(final List<Order> orders) {
        metricService.recordGetOrDefaultCalled();
        return orders == null ? Collections.emptyList() : orders;
    }

    public List<Order> getOrders7(final String searchCriteria) {
        return getOrdersHelper1(searchCriteria);
    }

    private List<Order> getOrdersHelper1(final String searchCriteria) {
        return orderService.getOrders1(searchCriteria);
    }

    public List<Order> getOrders8(final String searchCriteria) {
        return Optional.ofNullable(getOrdersHelper2(searchCriteria)).orElse(Collections.emptyList());
    }

    private List<Order> getOrdersHelper2(final String searchCriteria) {
        return orderService.getOrders1(searchCriteria);
    }

    public List<Order> getOrders9(final String searchCriteria) {
        return Optional.ofNullable(getOrdersHelper3(searchCriteria)).orElse(List.of());
    }

    private List<Order> getOrdersHelper3(final String searchCriteria) {
        return orderService.getOrders1(searchCriteria);
    }

    public List<Order> getOrders10(final String searchCriteria) {
        return Optional.ofNullable(getOrdersHelper4(searchCriteria)).orElse(null);
    }

    private List<Order> getOrdersHelper4(final String searchCriteria) {
        return orderService.getOrders1(searchCriteria);
    }

    public List<Order> getOrders11(final String searchCriteria) {
        return getOrDefault1(getOrdersHelper5(searchCriteria));
    }

    private List<Order> getOrdersHelper5(final String searchCriteria) {
        return orderService.getOrders1(searchCriteria);
    }

    public Order[] getOrders12(final String searchCriteria) {
        return orderService.getOrders2(searchCriteria).toArray(new Order[0]);
    }

    public List<Order> getOrders13(final String searchCriteria) {
        return orderService.getOrders2(searchCriteria).stream().map(this::updateDescription).collect(Collectors.toList());
    }

    public OrderData getOrders14(final String searchCriteria) {
        return new OrderData(orderService.getOrders2(searchCriteria));
    }

    public List<String> getOrders15(final String searchCriteria) {
        return orderService.getOrders2(searchCriteria).stream().map(Order::getOrderId).collect(Collectors.toList());
    }

    public int countOrders14(final String searchCriteria) {
        return orderService.getOrders2(searchCriteria).size();
    }

    private Order updateDescription(final Order order) {
        metricService.recordUpdatingDescription(order);
        return new Order(order.getOrderId(), "updated");
    }

    public boolean orderExists1(final String searchCriteria) {
        return Optional.ofNullable(orderService.getOrders1(searchCriteria)).orElse(Collections.emptyList()).isEmpty();
    }

    public boolean orderExists2(final String searchCriteria) {
        return orderService.getOrders1(searchCriteria) != null && !orderService.getOrders1(searchCriteria).isEmpty();
    }

    public boolean orderExists3(final String searchCriteria) {
        return !orderService.getOrders2(searchCriteria).isEmpty();
    }

    public boolean orderExists4(final String searchCriteria, final Order orderToCheckFor) {
        return orderService.getOrders2(searchCriteria).contains(orderToCheckFor);
    }

    public boolean orderExists5(final String searchCriteria) {
        return orderService.getOrders2(searchCriteria).size() != 0;
    }

    public boolean orderExists6(final String searchCriteria) {
        return orderService.getOrders2(searchCriteria).size() >= 0;
    }

    public boolean orderExists7(final String searchCriteria) {
        return orderService.getOrders2(searchCriteria).size() > 0;
    }

    public boolean orderExists8(final String searchCriteria) {
        return orderService.getOrders2(searchCriteria).size() + 1 > 1;
    }

    public boolean canCreate1(final String searchCriteria) {
        return orderService.getOrders2(searchCriteria).isEmpty();
    }

    public boolean canCreate2(final String searchCriteria, final Order orderToCheckFor) {
        return !orderService.getOrders2(searchCriteria).contains(orderToCheckFor);
    }

    public boolean canCreate3(final String searchCriteria) {
        return orderService.getOrders2(searchCriteria).size() == 0;
    }
}
