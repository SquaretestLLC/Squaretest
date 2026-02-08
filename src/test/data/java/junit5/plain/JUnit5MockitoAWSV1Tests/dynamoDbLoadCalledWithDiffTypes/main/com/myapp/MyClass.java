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

public class MyClass {
    private final DynamoDBMapper dynamoDBMapper;

    public MyClass(final DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public OrderAndCustomer getInfo(final String orderId, final String customerId) {
        final StoredOrder storedOrder = dynamoDBMapper.load(StoredOrder.class, orderId);
        final Customer customer = dynamoDBMapper.load(Customer.class, customerId);
        return new OrderAndCustomer(storedOrder, customer);
    }

    public Customer getCustomer(final String customerId) {
        return dynamoDBMapper.load(Customer.class, customerId);
    }

    public StoredOrder getOrder(final String orderId) {
        return dynamoDBMapper.load(StoredOrder.class, orderId);
    }
}
