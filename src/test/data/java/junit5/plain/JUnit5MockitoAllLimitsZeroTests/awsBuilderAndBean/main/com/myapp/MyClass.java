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

public class MyClass {

    private final DynamoDbTable<Order> ordersTable;
    private final MetricsAdapter metricsAdapter;

    public MyClass(
            final DynamoDbTable<Order> ordersTable,
            final MetricsAdapter metricsAdapter) {
        this.ordersTable = ordersTable;
        this.metricsAdapter = metricsAdapter;
    }

    public Order getOrder(final String orderId) throws OrderStoreException {
        Validate.notBlank(orderId, "orderId cannot be blank");

        // Make the service call.
        final Order order;
        try {
            order = ordersTable.getItem(Key.builder().partitionValue(orderId).build());
        } catch (final Exception e) {
            metricsAdapter.recordOrderStoreException(e);
            throw new OrderStoreException(e);
        }

        // Validate the order.
        if (order == null) {
            metricsAdapter.recordOrderNotFound(orderId);
            throw new OrderStoreException("Order with ID " + orderId + " not found.");
        }
        metricsAdapter.recordOrderRetrieved(orderId);
        return order;
    }
}
