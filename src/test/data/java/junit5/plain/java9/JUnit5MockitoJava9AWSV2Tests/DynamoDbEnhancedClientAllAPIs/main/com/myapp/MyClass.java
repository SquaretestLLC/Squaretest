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
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DeleteRequest;
import software.amazon.awssdk.services.dynamodb.model.PutRequest;
import software.amazon.awssdk.services.dynamodb.model.WriteRequest;

import java.util.List;
import java.util.Map;

public class MyClass {

    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public MyClass(final DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
    }

    public void tryTable() {
        final TableSchema<StoredOrder> tableSchema = TableSchema.fromClass(StoredOrder.class);
        final DynamoDbTable<StoredOrder> varThatUsesProps = null;
        final DynamoDbTable<StoredOrder> result = dynamoDbEnhancedClient.table("tableName", tableSchema);
    }

    public void tryBatchGetItem1() {
        final BatchGetItemEnhancedRequest request = BatchGetItemEnhancedRequest.builder()
                .readBatches(ReadBatch.builder(StoredOrder.class)
                        .addGetItem(GetItemEnhancedRequest.builder()
                                .consistentRead(false)
                                .key(Key.builder()
                                        .partitionValue("partitionKey")
                                        .sortValue("sortKey")
                                        .build())
                                .build())
                        .build())
                .addReadBatch(ReadBatch.builder(StoredOrder.class)
                        .addGetItem(GetItemEnhancedRequest.builder()
                                .consistentRead(false)
                                .key(Key.builder()
                                        .partitionValue("partitionKey")
                                        .sortValue("sortKey")
                                        .build())
                                .build())
                        .build())
                .build();
        final BatchGetResultPageIterable varThatUsesProps = null;
        final BatchGetResultPageIterable result = dynamoDbEnhancedClient.batchGetItem(request);
    }

    public void tryBatchGetItem2() {
        final BatchGetResultPageIterable varThatUsesProps = null;
        final BatchGetResultPageIterable result = dynamoDbEnhancedClient.batchGetItem(Object::toString);
    }

    public void tryBatchWriteItem1() {
        final BatchWriteItemEnhancedRequest request = BatchWriteItemEnhancedRequest.builder()
                .writeBatches(WriteBatch.builder(StoredOrder.class)
                        .addDeleteItem(DeleteItemEnhancedRequest.builder()
                                .key(Key.builder()
                                        .partitionValue("partitionKey")
                                        .sortValue("sortKey")
                                        .build())
                                .conditionExpression(Expression.builder()
                                        .expression("expression")
                                        .expressionValues(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                                                .s("value")
                                                .build())))
                                        .expressionNames(Map.ofEntries(Map.entry("value", "value")))
                                        .build())
                                .build())
                        .addPutItem(PutItemEnhancedRequest.builder(StoredOrder.class).item(new StoredOrder()).build())
                        .build())
                .addWriteBatch(WriteBatch.builder(StoredOrder.class)
                        .addDeleteItem(DeleteItemEnhancedRequest.builder()
                                .key(Key.builder()
                                        .partitionValue("partitionKey")
                                        .sortValue("sortKey")
                                        .build())
                                .conditionExpression(Expression.builder()
                                        .expression("expression")
                                        .expressionValues(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                                                .s("value")
                                                .build())))
                                        .expressionNames(Map.ofEntries(Map.entry("value", "value")))
                                        .build())
                                .build())
                        .addPutItem(PutItemEnhancedRequest.builder(StoredOrder.class).item(new StoredOrder()).build())
                        .build())
                .build();
        final BatchWriteResult varThatUsesProps = BatchWriteResult.builder()
                .unprocessedRequests(Map.ofEntries(Map.entry("value", List.of(WriteRequest.builder()
                        .putRequest(PutRequest.builder()
                                .item(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                                        .s("value")
                                        .build())))
                                .build())
                        .deleteRequest(DeleteRequest.builder()
                                .key(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                                        .s("value")
                                        .build())))
                                .build())
                        .build()))))
                .build();
        final BatchWriteResult result = dynamoDbEnhancedClient.batchWriteItem(request);
    }

    public void tryBatchWriteItem2() {
        final BatchWriteResult varThatUsesProps = BatchWriteResult.builder()
                .unprocessedRequests(Map.ofEntries(Map.entry("value", List.of(WriteRequest.builder()
                        .putRequest(PutRequest.builder()
                                .item(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                                        .s("value")
                                        .build())))
                                .build())
                        .deleteRequest(DeleteRequest.builder()
                                .key(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                                        .s("value")
                                        .build())))
                                .build())
                        .build()))))
                .build();
        final BatchWriteResult result = dynamoDbEnhancedClient.batchWriteItem(Object::toString);
    }

    public void tryTransactGetItems1() {
        final TransactGetItemsEnhancedRequest request = TransactGetItemsEnhancedRequest.builder().build();
        final List<Document> varThatUsesProps = List.of();
        final List<Document> result = dynamoDbEnhancedClient.transactGetItems(request);
    }

    public void tryTransactGetItems2() {
        final List<Document> varThatUsesProps = List.of();
        final List<Document> result = dynamoDbEnhancedClient.transactGetItems(Object::toString);
    }

    public void tryTransactWriteItems1() {
        dynamoDbEnhancedClient.transactWriteItems(TransactWriteItemsEnhancedRequest.builder()
                .clientRequestToken("clientRequestToken")
                .build());
    }

    public void tryTransactWriteItems2() {
        dynamoDbEnhancedClient.transactWriteItems(Object::toString);
    }
}
