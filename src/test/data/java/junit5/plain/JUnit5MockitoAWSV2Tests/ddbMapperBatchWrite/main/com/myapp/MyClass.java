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

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.model.BatchWriteItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.BatchWriteResult;
import software.amazon.awssdk.enhanced.dynamodb.model.WriteBatch;

import java.util.List;

public class MyClass {
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private final DynamoDbTable<Order> ordersTable;

    public MyClass(final DynamoDbEnhancedClient dynamoDbEnhancedClient, final DynamoDbTable<Order> ordersTable) {
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
        this.ordersTable = ordersTable;
    }

    public void batchWrite(final Order order) {
        final BatchWriteItemEnhancedRequest request = BatchWriteItemEnhancedRequest.builder().addWriteBatch(WriteBatch.builder(Order.class).addPutItem(order).build()).build();
        final BatchWriteResult result = dynamoDbEnhancedClient.batchWriteItem(request);
        final List<Order> orders = result.unprocessedPutItemsForTable(ordersTable);
        if(!orders.isEmpty()) {
            throw new BatchPutException(order.getId());
        }
    }
}
