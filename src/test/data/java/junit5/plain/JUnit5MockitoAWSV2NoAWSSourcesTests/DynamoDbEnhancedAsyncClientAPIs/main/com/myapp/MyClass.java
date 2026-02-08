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

import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.internal.client.DefaultDynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.model.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MyClass {

    private DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient;

    public MyClass(final DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient) {
        this.dynamoDbEnhancedAsyncClient = dynamoDbEnhancedAsyncClient;
    }

    public void tryTable() {
        final TableSchema<StoredOrder> tableSchema = null;
        final DynamoDbAsyncTable<StoredOrder> result = dynamoDbEnhancedAsyncClient.table("tableName", tableSchema);
    }

    public void tryBatchGetItem() {
        final BatchGetItemEnhancedRequest request = BatchGetItemEnhancedRequest.builder().build();
        final BatchGetResultPagePublisher result = dynamoDbEnhancedAsyncClient.batchGetItem(request);
    }

    public void tryBatchGetItem1() {
        final BatchGetResultPagePublisher result = dynamoDbEnhancedAsyncClient.batchGetItem(Object::toString);
    }

    public void tryBatchWriteItem() {
        final BatchWriteItemEnhancedRequest request = BatchWriteItemEnhancedRequest.builder().build();
        final CompletableFuture<BatchWriteResult> result = dynamoDbEnhancedAsyncClient.batchWriteItem(request);
    }

    public void tryBatchWriteItem1() {
        final CompletableFuture<BatchWriteResult> result = dynamoDbEnhancedAsyncClient.batchWriteItem(Object::toString);
    }

    public void tryTransactGetItems() {
        final TransactGetItemsEnhancedRequest request = TransactGetItemsEnhancedRequest.builder().build();
        final CompletableFuture<List<Document>> result = dynamoDbEnhancedAsyncClient.transactGetItems(request);
    }

    public void tryTransactGetItems1() {
        final CompletableFuture<List<Document>> result = dynamoDbEnhancedAsyncClient.transactGetItems(Object::toString);
    }

    public void tryTransactWriteItems() {
        final TransactWriteItemsEnhancedRequest request = TransactWriteItemsEnhancedRequest.builder().build();
        final CompletableFuture<Void> result = dynamoDbEnhancedAsyncClient.transactWriteItems(request);
    }

    public void tryTransactWriteItems1() {
        final CompletableFuture<Void> result = dynamoDbEnhancedAsyncClient.transactWriteItems(Object::toString);
    }

    public void tryEquals() {
        final boolean result = dynamoDbEnhancedAsyncClient.equals("o");
    }

    public void tryHashCode() {
        final int result = dynamoDbEnhancedAsyncClient.hashCode();
    }

    public void tryBuilder() {
        final DefaultDynamoDbEnhancedAsyncClient.Builder result = DefaultDynamoDbEnhancedAsyncClient.builder();
    }
}
