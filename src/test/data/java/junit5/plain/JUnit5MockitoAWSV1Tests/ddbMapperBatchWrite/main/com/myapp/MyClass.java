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

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.Collections;
import java.util.List;

public class MyClass {
    private final DynamoDBMapper dynamoDBMapper;

    public MyClass(final DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void putOrders1(final List<Order> orders) {
        final List<DynamoDBMapper.FailedBatch> failedBatches = dynamoDBMapper.batchWrite(orders, Collections.emptyList());
        if(!failedBatches.isEmpty()) {
            throw new BatchWriteException(orders.get(0).getId());
        }
    }
    public void putOrders2(final List<Order> orders) {
        final List<DynamoDBMapper.FailedBatch> failedBatches = dynamoDBMapper.batchSave(orders);
        if(!failedBatches.isEmpty()) {
            throw new BatchWriteException(orders.get(0).getId());
        }
    }
    public void putOrders3(final Order order) {
        final List<DynamoDBMapper.FailedBatch> failedBatches = dynamoDBMapper.batchSave(order);
        if(!failedBatches.isEmpty()) {
            throw new BatchWriteException(order.getId());
        }
    }
    public void putOrders4(final Order... orders) {
        final List<DynamoDBMapper.FailedBatch> failedBatches = dynamoDBMapper.batchSave((Object[]) orders);
        if(!failedBatches.isEmpty()) {
            throw new BatchWriteException(orders[0].getId());
        }
    }
}
