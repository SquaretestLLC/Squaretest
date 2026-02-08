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

import software.amazon.awssdk.enhanced.dynamodb.Document;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.internal.client.DefaultDynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.model.*;

import java.util.List;

public class MyClass {

    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public MyClass(final DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
    }

    public void tryTable() {
        final TableSchema<StoredOrder> tableSchema = null;
        final DynamoDbTable<StoredOrder> result = dynamoDbEnhancedClient.table("tableName", tableSchema);
    }

    public void tryBatchGetItem() {
        final BatchGetItemEnhancedRequest request = BatchGetItemEnhancedRequest.builder().build();
        final BatchGetResultPageIterable result = dynamoDbEnhancedClient.batchGetItem(request);
    }

    public void tryBatchGetItem1() {
        final BatchGetResultPageIterable result = dynamoDbEnhancedClient.batchGetItem(Object::toString);
    }

    public void tryBatchWriteItem() {
        final BatchWriteItemEnhancedRequest request = BatchWriteItemEnhancedRequest.builder().build();
        final BatchWriteResult result = dynamoDbEnhancedClient.batchWriteItem(request);
    }

    public void tryBatchWriteItem1() {
        final BatchWriteResult result = dynamoDbEnhancedClient.batchWriteItem(Object::toString);
    }

    public void tryTransactGetItems() {
        final TransactGetItemsEnhancedRequest request = TransactGetItemsEnhancedRequest.builder().build();
        final List<Document> result = dynamoDbEnhancedClient.transactGetItems(request);
    }

    public void tryTransactGetItems1() {
        final List<Document> result = dynamoDbEnhancedClient.transactGetItems(Object::toString);
    }

    public void tryTransactWriteItems() {
        final TransactWriteItemsEnhancedRequest request = TransactWriteItemsEnhancedRequest.builder().build();
        final Void result = dynamoDbEnhancedClient.transactWriteItems(request);
    }

    public void tryTransactWriteItems1() {
        final Void result = dynamoDbEnhancedClient.transactWriteItems(Object::toString);
    }

    public void tryEquals() {
        final boolean result = dynamoDbEnhancedClient.equals("o");
    }

    public void tryHashCode() {
        final int result = dynamoDbEnhancedClient.hashCode();
    }

    public void tryBuilder() {
        final DefaultDynamoDbEnhancedClient.Builder result = DefaultDynamoDbEnhancedClient.builder();
    }
}
