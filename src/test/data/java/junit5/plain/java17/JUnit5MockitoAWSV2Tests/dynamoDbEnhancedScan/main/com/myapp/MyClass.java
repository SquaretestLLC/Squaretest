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

import org.apache.commons.lang3.Validate;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MyClass {

    private final DynamoDbTable<Order> ordersTable;
    private final MetricService metricService;
    private final OtherService otherService;

    public MyClass(final DynamoDbTable<Order> ordersTable, final MetricService metricService, final OtherService otherService) {
        this.ordersTable = ordersTable;
        this.metricService = metricService;
        this.otherService = otherService;
    }

    public Order getOrder1(final String orderId) throws OrderStoreException {
        Validate.notBlank(orderId, "Orderid cannot be blank");

        // Make the service call.
        final Order order;
        try {
            order = ordersTable.getItem(Key.builder().partitionValue(orderId).build());
        } catch (final Exception e) {
            throw new OrderStoreException(e);
        }

        // Validate the order.
        if (order == null) {
            throw new OrderStoreException("Order with ID " + orderId + " not found.");
        }
        return order;
    }

    public List<Order> getOrders1(final String orderId) throws OrderStoreException {
        Validate.notBlank(orderId, "Orderid cannot be blank");
        try {
            final PageIterable<Order> orders = ordersTable.scan();
            return orders.items().stream().toList();
        } catch (final Exception e) {
            throw new OrderStoreException(e);
        }
    }

    public List<Order> getOrders2(final String orderId) throws OrderStoreException {
        Validate.notBlank(orderId, "Orderid cannot be blank");
        try {
            final PageIterable<Order> orders = ordersTable.scan();
            final List<Order> ordersList = orders.items().stream().toList();
            for (final Order order : ordersList) {
                metricService.recordOrder(order.getId());
            }
            return ordersList;
        } catch (final Exception e) {
            throw new OrderStoreException(e);
        }
    }

    public List<Order> getOrders3(final String orderId) throws OrderStoreException {
        Validate.notBlank(orderId, "Orderid cannot be blank");
        try {
            final PageIterable<Order> orders = ordersTable.scan();
            final List<Order> ordersList = orders.items()
                    .stream().mapMulti((BiConsumer<Order, Consumer<Order>>) (order, consumer) -> {
                        final String otherData = otherService.getOtherData(order.getOtherId());
                        if (otherData != null) {
                            order.setOtherData(otherData);
                            consumer.accept(order);
                        }
                    }).toList();
            for (final Order order : ordersList) {
                metricService.recordOrder(order.getId());
            }
            return ordersList;
        } catch (final Exception e) {
            throw new OrderStoreException(e);
        }
    }

    public Order getOrder2(final String orderId) throws OrderStoreException {
        Validate.notBlank(orderId, "Orderid cannot be blank");
        try {
            final PageIterable<Order> orders = ordersTable.scan();
            final Order firstOrder = orders.items()
                    .stream().mapMulti((BiConsumer<Order, Consumer<Order>>) (order, consumer) -> {
                        final String otherData = otherService.getOtherData(order.getOtherId());
                        if (otherData != null) {
                            order.setOtherData(otherData);
                            consumer.accept(order);
                        }
                    }).findAny().orElse(null);
            if(firstOrder == null) {
                metricService.recordNoOrderFound(orderId);
            }
            return firstOrder;
        } catch (final Exception e) {
            throw new OrderStoreException(e);
        }
    }
}
