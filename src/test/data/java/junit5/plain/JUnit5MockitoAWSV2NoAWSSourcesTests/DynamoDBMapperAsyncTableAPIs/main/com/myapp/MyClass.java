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
import software.amazon.awssdk.enhanced.dynamodb.model.*;

import java.util.concurrent.CompletableFuture;

public class MyClass {

    private final DynamoDbAsyncTable<StoredOrder> dynamoDbAsyncTable;

    public MyClass(final DynamoDbAsyncTable<StoredOrder> dynamoDbAsyncTable) {
        this.dynamoDbAsyncTable = dynamoDbAsyncTable;
    }

    public void tryMapperExtension() {
        final DynamoDbEnhancedClientExtension result = dynamoDbAsyncTable.mapperExtension();
    }

    public void tryIndex() {
        final DynamoDbAsyncIndex<StoredOrder> result = dynamoDbAsyncTable.index("indexName");
    }

    public void tryCreateTable() {
        final CreateTableEnhancedRequest request = CreateTableEnhancedRequest.builder().build();
        final CompletableFuture<Void> result = dynamoDbAsyncTable.createTable(request);
    }

    public void tryCreateTable1() {
        final CompletableFuture<Void> result = dynamoDbAsyncTable.createTable(Object::toString);
    }

    public void tryCreateTable2() {
        final CompletableFuture<Void> result = dynamoDbAsyncTable.createTable();
    }

    public void tryDeleteItem() {
        final DeleteItemEnhancedRequest request = DeleteItemEnhancedRequest.builder().key(Key.builder().partitionValue("key").build()).build();
        final CompletableFuture<StoredOrder> result = dynamoDbAsyncTable.deleteItem(request);
    }

    public void tryDeleteItem1() {
        final CompletableFuture<StoredOrder> result = dynamoDbAsyncTable.deleteItem(Object::toString);
    }

    public void tryDeleteItem2() {
        final Key key = Key.builder().partitionValue("key").build();
        final CompletableFuture<StoredOrder> result = dynamoDbAsyncTable.deleteItem(key);
    }

    public void tryDeleteItem3() {
        final StoredOrder keyItem = null;
        final CompletableFuture<StoredOrder> result = dynamoDbAsyncTable.deleteItem(keyItem);
    }

    public void tryGetItem() {
        final GetItemEnhancedRequest request = GetItemEnhancedRequest.builder().key(Key.builder().partitionValue("key").build()).build();
        final CompletableFuture<StoredOrder> result = dynamoDbAsyncTable.getItem(request);
    }

    public void tryGetItem1() {
        final CompletableFuture<StoredOrder> result = dynamoDbAsyncTable.getItem(Object::toString);
    }

    public void tryGetItem2() {
        final Key key = Key.builder().partitionValue("key").build();
        final CompletableFuture<StoredOrder> result = dynamoDbAsyncTable.getItem(key);
    }

    public void tryGetItem3() {
        final StoredOrder keyItem = null;
        final CompletableFuture<StoredOrder> result = dynamoDbAsyncTable.getItem(keyItem);
    }

    public void tryQuery() {
        final QueryEnhancedRequest request = QueryEnhancedRequest.builder().queryConditional(QueryConditional.keyEqualTo(Key.builder().partitionValue("key").build())).build();
        final PagePublisher<StoredOrder> result = dynamoDbAsyncTable.query(request);
    }

    public void tryQuery1() {
        final PagePublisher<StoredOrder> result = dynamoDbAsyncTable.query(Object::toString);
    }

    public void tryQuery2() {
        final QueryConditional queryConditional = QueryConditional.keyEqualTo(Key.builder().partitionValue("key").build());
        final PagePublisher<StoredOrder> result = dynamoDbAsyncTable.query(queryConditional);
    }

    public void tryPutItem() {
        final PutItemEnhancedRequest<StoredOrder> request = null;
        final CompletableFuture<Void> result = dynamoDbAsyncTable.putItem(request);
    }

    public void tryPutItem1() {
        final CompletableFuture<Void> result = dynamoDbAsyncTable.putItem(Object::toString);
    }

    public void tryPutItem2() {
        final StoredOrder item = null;
        final CompletableFuture<Void> result = dynamoDbAsyncTable.putItem(item);
    }

    public void tryScan() {
        final ScanEnhancedRequest request = ScanEnhancedRequest.builder().build();
        final PagePublisher<StoredOrder> result = dynamoDbAsyncTable.scan(request);
    }

    public void tryScan1() {
        final PagePublisher<StoredOrder> result = dynamoDbAsyncTable.scan(Object::toString);
    }

    public void tryScan2() {
        final PagePublisher<StoredOrder> result = dynamoDbAsyncTable.scan();
    }

    public void tryUpdateItem() {
        final UpdateItemEnhancedRequest<StoredOrder> request = null;
        final CompletableFuture<StoredOrder> result = dynamoDbAsyncTable.updateItem(request);
    }

    public void tryUpdateItem1() {
        final CompletableFuture<StoredOrder> result = dynamoDbAsyncTable.updateItem(Object::toString);
    }

    public void tryUpdateItem2() {
        final StoredOrder item = null;
        final CompletableFuture<StoredOrder> result = dynamoDbAsyncTable.updateItem(item);
    }

    public void tryKeyFrom() {
        final StoredOrder item = null;
        final Key result = dynamoDbAsyncTable.keyFrom(item);
    }

    public void tryEquals() {
        final boolean result = dynamoDbAsyncTable.equals("o");
    }

    public void tryHashCode() {
        final int result = dynamoDbAsyncTable.hashCode();
    }
}
