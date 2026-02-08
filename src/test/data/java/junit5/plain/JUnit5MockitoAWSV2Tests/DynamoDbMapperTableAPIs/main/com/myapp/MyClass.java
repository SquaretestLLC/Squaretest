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

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbIndex;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.Projection;
import software.amazon.awssdk.services.dynamodb.model.ProjectionType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;

import java.util.Map;

public class MyClass {

    private DynamoDbTable<StoredOrder> dynamoDbTable;

    public MyClass(final DynamoDbTable dynamoDbTable) {
        this.dynamoDbTable = dynamoDbTable;
    }

    public void tryIndex() {
        final DynamoDbIndex<StoredOrder> varThatUsesProps = null;
        final DynamoDbIndex<StoredOrder> result = dynamoDbTable.index("indexName");
    }

    public void tryCreateTable1() {
        final CreateTableEnhancedRequest request = CreateTableEnhancedRequest.builder()
                .provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(0L)
                        .writeCapacityUnits(0L)
                        .build())
                .localSecondaryIndices(EnhancedLocalSecondaryIndex.create("indexName", Projection.builder()
                        .projectionType(ProjectionType.ALL)
                        .nonKeyAttributes("nonKeyAttributes")
                        .build()))
                .globalSecondaryIndices(EnhancedGlobalSecondaryIndex.builder()
                        .indexName("indexName")
                        .projection(Projection.builder()
                                .projectionType(ProjectionType.ALL)
                                .nonKeyAttributes("nonKeyAttributes")
                                .build())
                        .provisionedThroughput(ProvisionedThroughput.builder()
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .build())
                .build();
        dynamoDbTable.createTable(request);
    }

    public void tryCreateTable2() {
        dynamoDbTable.createTable(Object::toString);
    }

    public void tryCreateTable3() {
        dynamoDbTable.createTable();
    }

    public void tryDeleteItem1() {
        final DeleteItemEnhancedRequest request = DeleteItemEnhancedRequest.builder()
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
                .build();
        final StoredOrder varThatUsesProps = null;
        final StoredOrder result = dynamoDbTable.deleteItem(request);
    }

    public void tryDeleteItem2() {
        final StoredOrder varThatUsesProps = null;
        final StoredOrder result = dynamoDbTable.deleteItem(Object::toString);
    }

    public void tryDeleteItem3() {
        final Key key = Key.builder()
                .partitionValue("partitionKey")
                .sortValue("sortKey")
                .build();
        final StoredOrder varThatUsesProps = null;
        final StoredOrder result = dynamoDbTable.deleteItem(key);
    }

    public void tryDeleteItem4() {
        final StoredOrder keyItem = null;
        final StoredOrder varThatUsesProps = null;
        final StoredOrder result = dynamoDbTable.deleteItem(keyItem);
    }

    public void tryGetItem1() {
        final GetItemEnhancedRequest request = GetItemEnhancedRequest.builder()
                .consistentRead(false)
                .key(Key.builder()
                        .partitionValue("partitionKey")
                        .sortValue("sortKey")
                        .build())
                .build();
        final StoredOrder varThatUsesProps = null;
        final StoredOrder result = dynamoDbTable.getItem(request);
    }

    public void tryGetItem2() {
        final StoredOrder varThatUsesProps = null;
        final StoredOrder result = dynamoDbTable.getItem(Object::toString);
    }

    public void tryGetItem3() {
        final Key key = Key.builder()
                .partitionValue("partitionKey")
                .sortValue("sortKey")
                .build();
        final StoredOrder varThatUsesProps = null;
        final StoredOrder result = dynamoDbTable.getItem(key);
    }

    public void tryGetItem4() {
        final StoredOrder keyItem = null;
        final StoredOrder varThatUsesProps = null;
        final StoredOrder result = dynamoDbTable.getItem(keyItem);
    }

    public void tryQuery1() {
        final QueryEnhancedRequest request = QueryEnhancedRequest.builder()
                .queryConditional(QueryConditional.keyEqualTo(Key.builder()
                        .partitionValue("partitionKey")
                        .sortValue("sortKey")
                        .build()))
                .scanIndexForward(false)
                .exclusiveStartKey(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                        .s("value")
                        .build())))
                .limit(0)
                .consistentRead(false)
                .filterExpression(Expression.builder()
                        .expression("expression")
                        .expressionValues(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                                .s("value")
                                .build())))
                        .expressionNames(Map.ofEntries(Map.entry("value", "value")))
                        .build())
                .attributesToProject("attributesToProject")
                .build();
        final PageIterable<StoredOrder> varThatUsesProps = null;
        final PageIterable<StoredOrder> result = dynamoDbTable.query(request);
    }

    public void tryQuery2() {
        final PageIterable<StoredOrder> varThatUsesProps = null;
        final PageIterable<StoredOrder> result = dynamoDbTable.query(Object::toString);
    }

    public void tryQuery3() {
        final QueryConditional queryConditional = QueryConditional.keyEqualTo(Key.builder()
                .partitionValue("partitionKey")
                .sortValue("sortKey")
                .build());
        final PageIterable<StoredOrder> varThatUsesProps = null;
        final PageIterable<StoredOrder> result = dynamoDbTable.query(queryConditional);
    }

    public void tryPutItem1() {
        dynamoDbTable.putItem(Object::toString);
    }

    public void tryPutItem2() {
        dynamoDbTable.putItem(Object::toString);
    }

    public void tryPutItem3() {
        final StoredOrder item = null;
        dynamoDbTable.putItem(item);
    }

    public void tryScan1() {
        final ScanEnhancedRequest request = ScanEnhancedRequest.builder()
                .exclusiveStartKey(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                        .s("value")
                        .build())))
                .limit(0)
                .consistentRead(false)
                .filterExpression(Expression.builder()
                        .expression("expression")
                        .expressionValues(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                                .s("value")
                                .build())))
                        .expressionNames(Map.ofEntries(Map.entry("value", "value")))
                        .build())
                .attributesToProject("attributesToProject")
                .build();
        final PageIterable<StoredOrder> varThatUsesProps = null;
        final PageIterable<StoredOrder> result = dynamoDbTable.scan(request);
    }

    public void tryScan2() {
        final PageIterable<StoredOrder> varThatUsesProps = null;
        final PageIterable<StoredOrder> result = dynamoDbTable.scan(Object::toString);
    }

    public void tryScan3() {
        final PageIterable<StoredOrder> varThatUsesProps = null;
        final PageIterable<StoredOrder> result = dynamoDbTable.scan();
    }

    public void tryUpdateItem1() {
        final UpdateItemEnhancedRequest<StoredOrder> request = UpdateItemEnhancedRequest.builder(StoredOrder.class).item(new StoredOrder()).build();
        final StoredOrder varThatUsesProps = null;
        final StoredOrder result = dynamoDbTable.updateItem(request);
    }

    public void tryUpdateItem2() {
        final StoredOrder varThatUsesProps = null;
        final StoredOrder result = dynamoDbTable.updateItem(Object::toString);
    }

    public void tryUpdateItem3() {
        final StoredOrder item = null;
        final StoredOrder varThatUsesProps = null;
        final StoredOrder result = dynamoDbTable.updateItem(item);
    }
}
