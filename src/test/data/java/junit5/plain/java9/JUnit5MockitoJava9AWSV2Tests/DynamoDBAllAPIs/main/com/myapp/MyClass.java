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

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.paginators.*;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

public class MyClass {

    private DynamoDbClient dynamoDbClient;

    public MyClass(final DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void tryBatchGetItem1() {
        final BatchGetItemRequest batchGetItemRequest = BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build();
        final BatchGetItemResponse varThatUsesProps = BatchGetItemResponse.builder()
                .responses(Map.of(
                        "TableName1", List.of(
                                Map.ofEntries(
                                        Map.entry("PrimaryKey1",
                                                AttributeValue.builder().s("PrimaryKeyValue1").build()),
                                        Map.entry("KeyName2", AttributeValue.builder().s("Value2").build()))
                        ),
                        "TableName2", List.of(
                                Map.ofEntries(
                                        Map.entry("PrimaryKey2",
                                                AttributeValue.builder().s("PrimaryKeyValue2").build()),
                                        Map.entry("KeyName2", AttributeValue.builder().s("Value2").build()))
                        ))).build();
        final BatchGetItemResponse result = dynamoDbClient.batchGetItem(batchGetItemRequest);
    }

    public void tryBatchGetItem2() {
        final BatchGetItemResponse varThatUsesProps = BatchGetItemResponse.builder()
                .responses(Map.of(
                        "TableName1", List.of(
                                Map.ofEntries(
                                        Map.entry("PrimaryKey1",
                                                AttributeValue.builder().s("PrimaryKeyValue1").build()),
                                        Map.entry("KeyName2", AttributeValue.builder().s("Value2").build()))
                        ),
                        "TableName2", List.of(
                                Map.ofEntries(
                                        Map.entry("PrimaryKey2",
                                                AttributeValue.builder().s("PrimaryKeyValue2").build()),
                                        Map.entry("KeyName2", AttributeValue.builder().s("Value2").build()))
                        ))).build();
        final BatchGetItemResponse result = dynamoDbClient.batchGetItem(Object::toString);
    }

    public void tryBatchGetItemPaginator1() {
        final BatchGetItemRequest batchGetItemRequest = BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build();
        final BatchGetItemIterable varThatUsesProps = null;
        final BatchGetItemIterable result = dynamoDbClient.batchGetItemPaginator(batchGetItemRequest);
    }

    public void tryBatchGetItemPaginator2() {
        final BatchGetItemIterable varThatUsesProps = null;
        final BatchGetItemIterable result = dynamoDbClient.batchGetItemPaginator(Object::toString);
    }

    public void tryBatchWriteItem1() {
        final BatchWriteItemRequest batchWriteItemRequest = BatchWriteItemRequest.builder().requestItems(
                Map.of(
                        "TableName1", List.of(
                                WriteRequest.builder().putRequest(
                                        PutRequest.builder().item(Map.ofEntries(
                                                Map.entry("Table1PrimaryKeyName",
                                                        AttributeValue.builder().s("Table1PrimaryKeyValue1").build())
                                        )).build()).build(),
                                WriteRequest.builder().putRequest(
                                        PutRequest.builder().item(Map.ofEntries(
                                                Map.entry("Table1PrimaryKeyName",
                                                        AttributeValue.builder().s("Table1PrimaryKeyValue2").build())
                                        )).build()).build()
                        )
                )).build();
        final BatchWriteItemResponse varThatUsesProps = BatchWriteItemResponse.builder()
                .unprocessedItems(Map.ofEntries(Map.entry("value", List.of(WriteRequest.builder()
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
                .itemCollectionMetrics(Map.ofEntries(Map.entry("value", List.of(ItemCollectionMetrics.builder()
                        .itemCollectionKey(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                                .s("value")
                                .build())))
                        .sizeEstimateRangeGB(0.0)
                        .build()))))
                .consumedCapacity(ConsumedCapacity.builder()
                        .tableName("tableName")
                        .capacityUnits(0.0)
                        .readCapacityUnits(0.0)
                        .writeCapacityUnits(0.0)
                        .table(Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())
                        .localSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .globalSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .build())
                .build();
        final BatchWriteItemResponse result = dynamoDbClient.batchWriteItem(batchWriteItemRequest);
    }

    public void tryBatchWriteItem2() {
        final BatchWriteItemResponse varThatUsesProps = BatchWriteItemResponse.builder()
                .unprocessedItems(Map.ofEntries(Map.entry("value", List.of(WriteRequest.builder()
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
                .itemCollectionMetrics(Map.ofEntries(Map.entry("value", List.of(ItemCollectionMetrics.builder()
                        .itemCollectionKey(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                                .s("value")
                                .build())))
                        .sizeEstimateRangeGB(0.0)
                        .build()))))
                .consumedCapacity(ConsumedCapacity.builder()
                        .tableName("tableName")
                        .capacityUnits(0.0)
                        .readCapacityUnits(0.0)
                        .writeCapacityUnits(0.0)
                        .table(Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())
                        .localSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .globalSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .build())
                .build();
        final BatchWriteItemResponse result = dynamoDbClient.batchWriteItem(Object::toString);
    }

    public void tryCreateBackup1() {
        final CreateBackupRequest createBackupRequest = CreateBackupRequest.builder()
                .tableName("tableName")
                .backupName("backupName")
                .build();
        final CreateBackupResponse varThatUsesProps = CreateBackupResponse.builder()
                .backupDetails(BackupDetails.builder()
                        .backupArn("backupArn")
                        .backupName("backupName")
                        .backupSizeBytes(0L)
                        .backupStatus(BackupStatus.CREATING)
                        .backupType(BackupType.USER)
                        .backupCreationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .backupExpiryDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .build();
        final CreateBackupResponse result = dynamoDbClient.createBackup(createBackupRequest);
    }

    public void tryCreateBackup2() {
        final CreateBackupResponse varThatUsesProps = CreateBackupResponse.builder()
                .backupDetails(BackupDetails.builder()
                        .backupArn("backupArn")
                        .backupName("backupName")
                        .backupSizeBytes(0L)
                        .backupStatus(BackupStatus.CREATING)
                        .backupType(BackupType.USER)
                        .backupCreationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .backupExpiryDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .build();
        final CreateBackupResponse result = dynamoDbClient.createBackup(Object::toString);
    }

    public void tryCreateGlobalTable1() {
        final CreateGlobalTableRequest createGlobalTableRequest = CreateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicationGroup(Replica.builder()
                        .regionName("regionName")
                        .build())
                .build();
        final CreateGlobalTableResponse varThatUsesProps = CreateGlobalTableResponse.builder()
                .globalTableDescription(GlobalTableDescription.builder()
                        .replicationGroup(ReplicaDescription.builder()
                                .regionName("regionName")
                                .replicaStatus(ReplicaStatus.CREATING)
                                .replicaStatusDescription("replicaStatusDescription")
                                .replicaStatusPercentProgress("replicaStatusPercentProgress")
                                .kmsMasterKeyId("kmsMasterKeyId")
                                .provisionedThroughputOverride(ProvisionedThroughputOverride.builder()
                                        .readCapacityUnits(0L)
                                        .build())
                                .globalSecondaryIndexes(ReplicaGlobalSecondaryIndexDescription.builder()
                                        .indexName("indexName")
                                        .provisionedThroughputOverride(ProvisionedThroughputOverride.builder()
                                                .readCapacityUnits(0L)
                                                .build())
                                        .build())
                                .build())
                        .globalTableArn("globalTableArn")
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .globalTableStatus(GlobalTableStatus.CREATING)
                        .globalTableName("globalTableName")
                        .build())
                .build();
        final CreateGlobalTableResponse result = dynamoDbClient.createGlobalTable(createGlobalTableRequest);
    }

    public void tryCreateGlobalTable2() {
        final CreateGlobalTableResponse varThatUsesProps = CreateGlobalTableResponse.builder()
                .globalTableDescription(GlobalTableDescription.builder()
                        .replicationGroup(ReplicaDescription.builder()
                                .regionName("regionName")
                                .replicaStatus(ReplicaStatus.CREATING)
                                .replicaStatusDescription("replicaStatusDescription")
                                .replicaStatusPercentProgress("replicaStatusPercentProgress")
                                .kmsMasterKeyId("kmsMasterKeyId")
                                .provisionedThroughputOverride(ProvisionedThroughputOverride.builder()
                                        .readCapacityUnits(0L)
                                        .build())
                                .globalSecondaryIndexes(ReplicaGlobalSecondaryIndexDescription.builder()
                                        .indexName("indexName")
                                        .provisionedThroughputOverride(ProvisionedThroughputOverride.builder()
                                                .readCapacityUnits(0L)
                                                .build())
                                        .build())
                                .build())
                        .globalTableArn("globalTableArn")
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .globalTableStatus(GlobalTableStatus.CREATING)
                        .globalTableName("globalTableName")
                        .build())
                .build();
        final CreateGlobalTableResponse result = dynamoDbClient.createGlobalTable(Object::toString);
    }

    public void tryCreateTable1() {
        final CreateTableRequest createTableRequest = CreateTableRequest.builder()
                .attributeDefinitions(AttributeDefinition.builder()
                        .attributeName("attributeName")
                        .attributeType(ScalarAttributeType.S)
                        .build())
                .tableName("tableName")
                .keySchema(KeySchemaElement.builder()
                        .attributeName("attributeName")
                        .keyType(KeyType.HASH)
                        .build())
                .localSecondaryIndexes(LocalSecondaryIndex.builder()
                        .indexName("indexName")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("attributeName")
                                .keyType(KeyType.HASH)
                                .build())
                        .projection(Projection.builder()
                                .projectionType(ProjectionType.ALL)
                                .nonKeyAttributes("nonKeyAttributes")
                                .build())
                        .build())
                .globalSecondaryIndexes(GlobalSecondaryIndex.builder()
                        .indexName("indexName")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("attributeName")
                                .keyType(KeyType.HASH)
                                .build())
                        .projection(Projection.builder()
                                .projectionType(ProjectionType.ALL)
                                .nonKeyAttributes("nonKeyAttributes")
                                .build())
                        .provisionedThroughput(ProvisionedThroughput.builder()
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .build())
                .billingMode(BillingMode.PROVISIONED)
                .provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(0L)
                        .writeCapacityUnits(0L)
                        .build())
                .streamSpecification(StreamSpecification.builder()
                        .streamEnabled(false)
                        .streamViewType(StreamViewType.NEW_IMAGE)
                        .build())
                .sseSpecification(SSESpecification.builder()
                        .enabled(false)
                        .sseType(SSEType.AES256)
                        .kmsMasterKeyId("kmsMasterKeyId")
                        .build())
                .tags(Tag.builder()
                        .key("key")
                        .build())
                .build();
        final CreateTableResponse varThatUsesProps = CreateTableResponse.builder()
                .tableDescription(TableDescription.builder()
                        .attributeDefinitions(AttributeDefinition.builder()
                                .attributeName("attributeName")
                                .attributeType(ScalarAttributeType.S)
                                .build())
                        .tableName("tableName")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("attributeName")
                                .keyType(KeyType.HASH)
                                .build())
                        .tableStatus(TableStatus.CREATING)
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .provisionedThroughput(ProvisionedThroughputDescription.builder()
                                .lastIncreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .lastDecreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .numberOfDecreasesToday(0L)
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .tableSizeBytes(0L)
                        .itemCount(0L)
                        .tableArn("tableArn")
                        .tableId("tableId")
                        .billingModeSummary(BillingModeSummary.builder()
                                .billingMode(BillingMode.PROVISIONED)
                                .lastUpdateToPayPerRequestDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .localSecondaryIndexes(LocalSecondaryIndexDescription.builder()
                                .indexName("indexName")
                                .keySchema(KeySchemaElement.builder()
                                        .attributeName("attributeName")
                                        .keyType(KeyType.HASH)
                                        .build())
                                .projection(Projection.builder()
                                        .projectionType(ProjectionType.ALL)
                                        .nonKeyAttributes("nonKeyAttributes")
                                        .build())
                                .indexSizeBytes(0L)
                                .itemCount(0L)
                                .indexArn("indexArn")
                                .build())
                        .globalSecondaryIndexes(GlobalSecondaryIndexDescription.builder().build())
                        .build())
                .build();
        final CreateTableResponse result = dynamoDbClient.createTable(createTableRequest);
    }

    public void tryCreateTable2() {
        final CreateTableResponse varThatUsesProps = CreateTableResponse.builder()
                .tableDescription(TableDescription.builder()
                        .attributeDefinitions(AttributeDefinition.builder()
                                .attributeName("attributeName")
                                .attributeType(ScalarAttributeType.S)
                                .build())
                        .tableName("tableName")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("attributeName")
                                .keyType(KeyType.HASH)
                                .build())
                        .tableStatus(TableStatus.CREATING)
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .provisionedThroughput(ProvisionedThroughputDescription.builder()
                                .lastIncreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .lastDecreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .numberOfDecreasesToday(0L)
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .tableSizeBytes(0L)
                        .itemCount(0L)
                        .tableArn("tableArn")
                        .tableId("tableId")
                        .billingModeSummary(BillingModeSummary.builder()
                                .billingMode(BillingMode.PROVISIONED)
                                .lastUpdateToPayPerRequestDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .localSecondaryIndexes(LocalSecondaryIndexDescription.builder()
                                .indexName("indexName")
                                .keySchema(KeySchemaElement.builder()
                                        .attributeName("attributeName")
                                        .keyType(KeyType.HASH)
                                        .build())
                                .projection(Projection.builder()
                                        .projectionType(ProjectionType.ALL)
                                        .nonKeyAttributes("nonKeyAttributes")
                                        .build())
                                .indexSizeBytes(0L)
                                .itemCount(0L)
                                .indexArn("indexArn")
                                .build())
                        .globalSecondaryIndexes(GlobalSecondaryIndexDescription.builder().build())
                        .build())
                .build();
        final CreateTableResponse result = dynamoDbClient.createTable(Object::toString);
    }

    public void tryDeleteBackup1() {
        final DeleteBackupRequest deleteBackupRequest = DeleteBackupRequest.builder()
                .backupArn("backupArn")
                .build();
        final DeleteBackupResponse varThatUsesProps = DeleteBackupResponse.builder()
                .backupDescription(BackupDescription.builder()
                        .backupDetails(BackupDetails.builder()
                                .backupArn("backupArn")
                                .backupName("backupName")
                                .backupSizeBytes(0L)
                                .backupStatus(BackupStatus.CREATING)
                                .backupType(BackupType.USER)
                                .backupCreationDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .backupExpiryDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .sourceTableDetails(SourceTableDetails.builder()
                                .tableName("tableName")
                                .tableId("tableId")
                                .tableArn("tableArn")
                                .tableSizeBytes(0L)
                                .keySchema(KeySchemaElement.builder()
                                        .attributeName("attributeName")
                                        .keyType(KeyType.HASH)
                                        .build())
                                .tableCreationDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .provisionedThroughput(ProvisionedThroughput.builder()
                                        .readCapacityUnits(0L)
                                        .writeCapacityUnits(0L)
                                        .build())
                                .itemCount(0L)
                                .billingMode(BillingMode.PROVISIONED)
                                .build())
                        .sourceTableFeatureDetails(SourceTableFeatureDetails.builder()
                                .localSecondaryIndexes(LocalSecondaryIndexInfo.builder()
                                        .indexName("indexName")
                                        .keySchema(KeySchemaElement.builder()
                                                .attributeName("attributeName")
                                                .keyType(KeyType.HASH)
                                                .build())
                                        .projection(Projection.builder()
                                                .projectionType(ProjectionType.ALL)
                                                .nonKeyAttributes("nonKeyAttributes")
                                                .build())
                                        .build())
                                .globalSecondaryIndexes(GlobalSecondaryIndexInfo.builder()
                                        .indexName("indexName")
                                        .keySchema(KeySchemaElement.builder()
                                                .attributeName("attributeName")
                                                .keyType(KeyType.HASH)
                                                .build())
                                        .projection(Projection.builder()
                                                .projectionType(ProjectionType.ALL)
                                                .nonKeyAttributes("nonKeyAttributes")
                                                .build())
                                        .provisionedThroughput(ProvisionedThroughput.builder()
                                                .readCapacityUnits(0L)
                                                .writeCapacityUnits(0L)
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
        final DeleteBackupResponse result = dynamoDbClient.deleteBackup(deleteBackupRequest);
    }

    public void tryDeleteBackup2() {
        final DeleteBackupResponse varThatUsesProps = DeleteBackupResponse.builder()
                .backupDescription(BackupDescription.builder()
                        .backupDetails(BackupDetails.builder()
                                .backupArn("backupArn")
                                .backupName("backupName")
                                .backupSizeBytes(0L)
                                .backupStatus(BackupStatus.CREATING)
                                .backupType(BackupType.USER)
                                .backupCreationDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .backupExpiryDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .sourceTableDetails(SourceTableDetails.builder()
                                .tableName("tableName")
                                .tableId("tableId")
                                .tableArn("tableArn")
                                .tableSizeBytes(0L)
                                .keySchema(KeySchemaElement.builder()
                                        .attributeName("attributeName")
                                        .keyType(KeyType.HASH)
                                        .build())
                                .tableCreationDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .provisionedThroughput(ProvisionedThroughput.builder()
                                        .readCapacityUnits(0L)
                                        .writeCapacityUnits(0L)
                                        .build())
                                .itemCount(0L)
                                .billingMode(BillingMode.PROVISIONED)
                                .build())
                        .sourceTableFeatureDetails(SourceTableFeatureDetails.builder()
                                .localSecondaryIndexes(LocalSecondaryIndexInfo.builder()
                                        .indexName("indexName")
                                        .keySchema(KeySchemaElement.builder()
                                                .attributeName("attributeName")
                                                .keyType(KeyType.HASH)
                                                .build())
                                        .projection(Projection.builder()
                                                .projectionType(ProjectionType.ALL)
                                                .nonKeyAttributes("nonKeyAttributes")
                                                .build())
                                        .build())
                                .globalSecondaryIndexes(GlobalSecondaryIndexInfo.builder()
                                        .indexName("indexName")
                                        .keySchema(KeySchemaElement.builder()
                                                .attributeName("attributeName")
                                                .keyType(KeyType.HASH)
                                                .build())
                                        .projection(Projection.builder()
                                                .projectionType(ProjectionType.ALL)
                                                .nonKeyAttributes("nonKeyAttributes")
                                                .build())
                                        .provisionedThroughput(ProvisionedThroughput.builder()
                                                .readCapacityUnits(0L)
                                                .writeCapacityUnits(0L)
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
        final DeleteBackupResponse result = dynamoDbClient.deleteBackup(Object::toString);
    }

    public void tryDeleteItem1() {
        final DeleteItemRequest deleteItemRequest = DeleteItemRequest.builder()
                .tableName("TableName")
                .key(Map.of(
                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                )).build();
        final DeleteItemResponse varThatUsesProps = DeleteItemResponse.builder()
                .attributes(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                        .s("value")
                        .build())))
                .consumedCapacity(ConsumedCapacity.builder()
                        .tableName("tableName")
                        .capacityUnits(0.0)
                        .readCapacityUnits(0.0)
                        .writeCapacityUnits(0.0)
                        .table(Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())
                        .localSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .globalSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .build())
                .itemCollectionMetrics(ItemCollectionMetrics.builder()
                        .itemCollectionKey(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                                .s("value")
                                .build())))
                        .sizeEstimateRangeGB(0.0)
                        .build())
                .build();
        final DeleteItemResponse result = dynamoDbClient.deleteItem(deleteItemRequest);
    }

    public void tryDeleteItem2() {
        final DeleteItemResponse varThatUsesProps = DeleteItemResponse.builder()
                .attributes(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                        .s("value")
                        .build())))
                .consumedCapacity(ConsumedCapacity.builder()
                        .tableName("tableName")
                        .capacityUnits(0.0)
                        .readCapacityUnits(0.0)
                        .writeCapacityUnits(0.0)
                        .table(Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())
                        .localSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .globalSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .build())
                .itemCollectionMetrics(ItemCollectionMetrics.builder()
                        .itemCollectionKey(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                                .s("value")
                                .build())))
                        .sizeEstimateRangeGB(0.0)
                        .build())
                .build();
        final DeleteItemResponse result = dynamoDbClient.deleteItem(Object::toString);
    }

    public void tryDeleteTable1() {
        final DeleteTableRequest deleteTableRequest = DeleteTableRequest.builder()
                .tableName("tableName")
                .build();
        final DeleteTableResponse varThatUsesProps = DeleteTableResponse.builder()
                .tableDescription(TableDescription.builder()
                        .attributeDefinitions(AttributeDefinition.builder()
                                .attributeName("attributeName")
                                .attributeType(ScalarAttributeType.S)
                                .build())
                        .tableName("tableName")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("attributeName")
                                .keyType(KeyType.HASH)
                                .build())
                        .tableStatus(TableStatus.CREATING)
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .provisionedThroughput(ProvisionedThroughputDescription.builder()
                                .lastIncreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .lastDecreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .numberOfDecreasesToday(0L)
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .tableSizeBytes(0L)
                        .itemCount(0L)
                        .tableArn("tableArn")
                        .tableId("tableId")
                        .billingModeSummary(BillingModeSummary.builder()
                                .billingMode(BillingMode.PROVISIONED)
                                .lastUpdateToPayPerRequestDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .localSecondaryIndexes(LocalSecondaryIndexDescription.builder()
                                .indexName("indexName")
                                .keySchema(KeySchemaElement.builder()
                                        .attributeName("attributeName")
                                        .keyType(KeyType.HASH)
                                        .build())
                                .projection(Projection.builder()
                                        .projectionType(ProjectionType.ALL)
                                        .nonKeyAttributes("nonKeyAttributes")
                                        .build())
                                .indexSizeBytes(0L)
                                .itemCount(0L)
                                .indexArn("indexArn")
                                .build())
                        .globalSecondaryIndexes(GlobalSecondaryIndexDescription.builder().build())
                        .build())
                .build();
        final DeleteTableResponse result = dynamoDbClient.deleteTable(deleteTableRequest);
    }

    public void tryDeleteTable2() {
        final DeleteTableResponse varThatUsesProps = DeleteTableResponse.builder()
                .tableDescription(TableDescription.builder()
                        .attributeDefinitions(AttributeDefinition.builder()
                                .attributeName("attributeName")
                                .attributeType(ScalarAttributeType.S)
                                .build())
                        .tableName("tableName")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("attributeName")
                                .keyType(KeyType.HASH)
                                .build())
                        .tableStatus(TableStatus.CREATING)
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .provisionedThroughput(ProvisionedThroughputDescription.builder()
                                .lastIncreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .lastDecreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .numberOfDecreasesToday(0L)
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .tableSizeBytes(0L)
                        .itemCount(0L)
                        .tableArn("tableArn")
                        .tableId("tableId")
                        .billingModeSummary(BillingModeSummary.builder()
                                .billingMode(BillingMode.PROVISIONED)
                                .lastUpdateToPayPerRequestDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .localSecondaryIndexes(LocalSecondaryIndexDescription.builder()
                                .indexName("indexName")
                                .keySchema(KeySchemaElement.builder()
                                        .attributeName("attributeName")
                                        .keyType(KeyType.HASH)
                                        .build())
                                .projection(Projection.builder()
                                        .projectionType(ProjectionType.ALL)
                                        .nonKeyAttributes("nonKeyAttributes")
                                        .build())
                                .indexSizeBytes(0L)
                                .itemCount(0L)
                                .indexArn("indexArn")
                                .build())
                        .globalSecondaryIndexes(GlobalSecondaryIndexDescription.builder().build())
                        .build())
                .build();
        final DeleteTableResponse result = dynamoDbClient.deleteTable(Object::toString);
    }

    public void tryDescribeBackup1() {
        final DescribeBackupRequest describeBackupRequest = DescribeBackupRequest.builder()
                .backupArn("backupArn")
                .build();
        final DescribeBackupResponse varThatUsesProps = DescribeBackupResponse.builder()
                .backupDescription(BackupDescription.builder()
                        .backupDetails(BackupDetails.builder()
                                .backupArn("backupArn")
                                .backupName("backupName")
                                .backupSizeBytes(0L)
                                .backupStatus(BackupStatus.CREATING)
                                .backupType(BackupType.USER)
                                .backupCreationDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .backupExpiryDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .sourceTableDetails(SourceTableDetails.builder()
                                .tableName("tableName")
                                .tableId("tableId")
                                .tableArn("tableArn")
                                .tableSizeBytes(0L)
                                .keySchema(KeySchemaElement.builder()
                                        .attributeName("attributeName")
                                        .keyType(KeyType.HASH)
                                        .build())
                                .tableCreationDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .provisionedThroughput(ProvisionedThroughput.builder()
                                        .readCapacityUnits(0L)
                                        .writeCapacityUnits(0L)
                                        .build())
                                .itemCount(0L)
                                .billingMode(BillingMode.PROVISIONED)
                                .build())
                        .sourceTableFeatureDetails(SourceTableFeatureDetails.builder()
                                .localSecondaryIndexes(LocalSecondaryIndexInfo.builder()
                                        .indexName("indexName")
                                        .keySchema(KeySchemaElement.builder()
                                                .attributeName("attributeName")
                                                .keyType(KeyType.HASH)
                                                .build())
                                        .projection(Projection.builder()
                                                .projectionType(ProjectionType.ALL)
                                                .nonKeyAttributes("nonKeyAttributes")
                                                .build())
                                        .build())
                                .globalSecondaryIndexes(GlobalSecondaryIndexInfo.builder()
                                        .indexName("indexName")
                                        .keySchema(KeySchemaElement.builder()
                                                .attributeName("attributeName")
                                                .keyType(KeyType.HASH)
                                                .build())
                                        .projection(Projection.builder()
                                                .projectionType(ProjectionType.ALL)
                                                .nonKeyAttributes("nonKeyAttributes")
                                                .build())
                                        .provisionedThroughput(ProvisionedThroughput.builder()
                                                .readCapacityUnits(0L)
                                                .writeCapacityUnits(0L)
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
        final DescribeBackupResponse result = dynamoDbClient.describeBackup(describeBackupRequest);
    }

    public void tryDescribeBackup2() {
        final DescribeBackupResponse varThatUsesProps = DescribeBackupResponse.builder()
                .backupDescription(BackupDescription.builder()
                        .backupDetails(BackupDetails.builder()
                                .backupArn("backupArn")
                                .backupName("backupName")
                                .backupSizeBytes(0L)
                                .backupStatus(BackupStatus.CREATING)
                                .backupType(BackupType.USER)
                                .backupCreationDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .backupExpiryDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .sourceTableDetails(SourceTableDetails.builder()
                                .tableName("tableName")
                                .tableId("tableId")
                                .tableArn("tableArn")
                                .tableSizeBytes(0L)
                                .keySchema(KeySchemaElement.builder()
                                        .attributeName("attributeName")
                                        .keyType(KeyType.HASH)
                                        .build())
                                .tableCreationDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .provisionedThroughput(ProvisionedThroughput.builder()
                                        .readCapacityUnits(0L)
                                        .writeCapacityUnits(0L)
                                        .build())
                                .itemCount(0L)
                                .billingMode(BillingMode.PROVISIONED)
                                .build())
                        .sourceTableFeatureDetails(SourceTableFeatureDetails.builder()
                                .localSecondaryIndexes(LocalSecondaryIndexInfo.builder()
                                        .indexName("indexName")
                                        .keySchema(KeySchemaElement.builder()
                                                .attributeName("attributeName")
                                                .keyType(KeyType.HASH)
                                                .build())
                                        .projection(Projection.builder()
                                                .projectionType(ProjectionType.ALL)
                                                .nonKeyAttributes("nonKeyAttributes")
                                                .build())
                                        .build())
                                .globalSecondaryIndexes(GlobalSecondaryIndexInfo.builder()
                                        .indexName("indexName")
                                        .keySchema(KeySchemaElement.builder()
                                                .attributeName("attributeName")
                                                .keyType(KeyType.HASH)
                                                .build())
                                        .projection(Projection.builder()
                                                .projectionType(ProjectionType.ALL)
                                                .nonKeyAttributes("nonKeyAttributes")
                                                .build())
                                        .provisionedThroughput(ProvisionedThroughput.builder()
                                                .readCapacityUnits(0L)
                                                .writeCapacityUnits(0L)
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
        final DescribeBackupResponse result = dynamoDbClient.describeBackup(Object::toString);
    }

    public void tryDescribeContinuousBackups1() {
        final DescribeContinuousBackupsRequest describeContinuousBackupsRequest = DescribeContinuousBackupsRequest.builder()
                .tableName("tableName")
                .build();
        final DescribeContinuousBackupsResponse varThatUsesProps = DescribeContinuousBackupsResponse.builder()
                .continuousBackupsDescription(ContinuousBackupsDescription.builder()
                        .continuousBackupsStatus(ContinuousBackupsStatus.ENABLED)
                        .pointInTimeRecoveryDescription(PointInTimeRecoveryDescription.builder()
                                .pointInTimeRecoveryStatus(PointInTimeRecoveryStatus.ENABLED)
                                .earliestRestorableDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .latestRestorableDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .build())
                .build();
        final DescribeContinuousBackupsResponse result = dynamoDbClient.describeContinuousBackups(
                describeContinuousBackupsRequest);
    }

    public void tryDescribeContinuousBackups2() {
        final DescribeContinuousBackupsResponse varThatUsesProps = DescribeContinuousBackupsResponse.builder()
                .continuousBackupsDescription(ContinuousBackupsDescription.builder()
                        .continuousBackupsStatus(ContinuousBackupsStatus.ENABLED)
                        .pointInTimeRecoveryDescription(PointInTimeRecoveryDescription.builder()
                                .pointInTimeRecoveryStatus(PointInTimeRecoveryStatus.ENABLED)
                                .earliestRestorableDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .latestRestorableDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .build())
                .build();
        final DescribeContinuousBackupsResponse result = dynamoDbClient.describeContinuousBackups(Object::toString);
    }

    public void tryDescribeContributorInsights1() {
        final DescribeContributorInsightsRequest describeContributorInsightsRequest = DescribeContributorInsightsRequest.builder()
                .tableName("tableName")
                .indexName("indexName")
                .build();
        final DescribeContributorInsightsResponse varThatUsesProps = DescribeContributorInsightsResponse.builder()
                .tableName("tableName")
                .indexName("indexName")
                .contributorInsightsRuleList("contributorInsightsRuleList")
                .contributorInsightsStatus(ContributorInsightsStatus.ENABLING)
                .lastUpdateDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .failureException(FailureException.builder()
                        .exceptionName("exceptionName")
                        .exceptionDescription("exceptionDescription")
                        .build())
                .build();
        final DescribeContributorInsightsResponse result = dynamoDbClient.describeContributorInsights(
                describeContributorInsightsRequest);
    }

    public void tryDescribeContributorInsights2() {
        final DescribeContributorInsightsResponse varThatUsesProps = DescribeContributorInsightsResponse.builder()
                .tableName("tableName")
                .indexName("indexName")
                .contributorInsightsRuleList("contributorInsightsRuleList")
                .contributorInsightsStatus(ContributorInsightsStatus.ENABLING)
                .lastUpdateDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .failureException(FailureException.builder()
                        .exceptionName("exceptionName")
                        .exceptionDescription("exceptionDescription")
                        .build())
                .build();
        final DescribeContributorInsightsResponse result = dynamoDbClient.describeContributorInsights(Object::toString);
    }

    public void tryDescribeEndpoints1() {
        final DescribeEndpointsResponse varThatUsesProps = DescribeEndpointsResponse.builder()
                .endpoints(Endpoint.builder()
                        .address("address")
                        .cachePeriodInMinutes(0L)
                        .build())
                .build();
        final DescribeEndpointsResponse result = dynamoDbClient.describeEndpoints();
    }

    public void tryDescribeEndpoints2() {
        final DescribeEndpointsRequest describeEndpointsRequest = DescribeEndpointsRequest.builder().build();
        final DescribeEndpointsResponse varThatUsesProps = DescribeEndpointsResponse.builder()
                .endpoints(Endpoint.builder()
                        .address("address")
                        .cachePeriodInMinutes(0L)
                        .build())
                .build();
        final DescribeEndpointsResponse result = dynamoDbClient.describeEndpoints(describeEndpointsRequest);
    }

    public void tryDescribeEndpoints3() {
        final DescribeEndpointsResponse varThatUsesProps = DescribeEndpointsResponse.builder()
                .endpoints(Endpoint.builder()
                        .address("address")
                        .cachePeriodInMinutes(0L)
                        .build())
                .build();
        final DescribeEndpointsResponse result = dynamoDbClient.describeEndpoints(Object::toString);
    }

    public void tryDescribeGlobalTable1() {
        final DescribeGlobalTableRequest describeGlobalTableRequest = DescribeGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .build();
        final DescribeGlobalTableResponse varThatUsesProps = DescribeGlobalTableResponse.builder()
                .globalTableDescription(GlobalTableDescription.builder()
                        .replicationGroup(ReplicaDescription.builder()
                                .regionName("regionName")
                                .replicaStatus(ReplicaStatus.CREATING)
                                .replicaStatusDescription("replicaStatusDescription")
                                .replicaStatusPercentProgress("replicaStatusPercentProgress")
                                .kmsMasterKeyId("kmsMasterKeyId")
                                .provisionedThroughputOverride(ProvisionedThroughputOverride.builder()
                                        .readCapacityUnits(0L)
                                        .build())
                                .globalSecondaryIndexes(ReplicaGlobalSecondaryIndexDescription.builder()
                                        .indexName("indexName")
                                        .provisionedThroughputOverride(ProvisionedThroughputOverride.builder()
                                                .readCapacityUnits(0L)
                                                .build())
                                        .build())
                                .build())
                        .globalTableArn("globalTableArn")
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .globalTableStatus(GlobalTableStatus.CREATING)
                        .globalTableName("globalTableName")
                        .build())
                .build();
        final DescribeGlobalTableResponse result = dynamoDbClient.describeGlobalTable(describeGlobalTableRequest);
    }

    public void tryDescribeGlobalTable2() {
        final DescribeGlobalTableResponse varThatUsesProps = DescribeGlobalTableResponse.builder()
                .globalTableDescription(GlobalTableDescription.builder()
                        .replicationGroup(ReplicaDescription.builder()
                                .regionName("regionName")
                                .replicaStatus(ReplicaStatus.CREATING)
                                .replicaStatusDescription("replicaStatusDescription")
                                .replicaStatusPercentProgress("replicaStatusPercentProgress")
                                .kmsMasterKeyId("kmsMasterKeyId")
                                .provisionedThroughputOverride(ProvisionedThroughputOverride.builder()
                                        .readCapacityUnits(0L)
                                        .build())
                                .globalSecondaryIndexes(ReplicaGlobalSecondaryIndexDescription.builder()
                                        .indexName("indexName")
                                        .provisionedThroughputOverride(ProvisionedThroughputOverride.builder()
                                                .readCapacityUnits(0L)
                                                .build())
                                        .build())
                                .build())
                        .globalTableArn("globalTableArn")
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .globalTableStatus(GlobalTableStatus.CREATING)
                        .globalTableName("globalTableName")
                        .build())
                .build();
        final DescribeGlobalTableResponse result = dynamoDbClient.describeGlobalTable(Object::toString);
    }

    public void tryDescribeGlobalTableSettings1() {
        final DescribeGlobalTableSettingsRequest describeGlobalTableSettingsRequest = DescribeGlobalTableSettingsRequest.builder()
                .globalTableName("globalTableName")
                .build();
        final DescribeGlobalTableSettingsResponse varThatUsesProps = DescribeGlobalTableSettingsResponse.builder()
                .globalTableName("globalTableName")
                .replicaSettings(ReplicaSettingsDescription.builder().build())
                .build();
        final DescribeGlobalTableSettingsResponse result = dynamoDbClient.describeGlobalTableSettings(
                describeGlobalTableSettingsRequest);
    }

    public void tryDescribeGlobalTableSettings2() {
        final DescribeGlobalTableSettingsResponse varThatUsesProps = DescribeGlobalTableSettingsResponse.builder()
                .globalTableName("globalTableName")
                .replicaSettings(ReplicaSettingsDescription.builder().build())
                .build();
        final DescribeGlobalTableSettingsResponse result = dynamoDbClient.describeGlobalTableSettings(Object::toString);
    }

    public void tryDescribeLimits1() {
        final DescribeLimitsResponse varThatUsesProps = DescribeLimitsResponse.builder()
                .accountMaxReadCapacityUnits(0L)
                .accountMaxWriteCapacityUnits(0L)
                .tableMaxReadCapacityUnits(0L)
                .tableMaxWriteCapacityUnits(0L)
                .build();
        final DescribeLimitsResponse result = dynamoDbClient.describeLimits();
    }

    public void tryDescribeLimits2() {
        final DescribeLimitsRequest describeLimitsRequest = DescribeLimitsRequest.builder().build();
        final DescribeLimitsResponse varThatUsesProps = DescribeLimitsResponse.builder()
                .accountMaxReadCapacityUnits(0L)
                .accountMaxWriteCapacityUnits(0L)
                .tableMaxReadCapacityUnits(0L)
                .tableMaxWriteCapacityUnits(0L)
                .build();
        final DescribeLimitsResponse result = dynamoDbClient.describeLimits(describeLimitsRequest);
    }

    public void tryDescribeLimits3() {
        final DescribeLimitsResponse varThatUsesProps = DescribeLimitsResponse.builder()
                .accountMaxReadCapacityUnits(0L)
                .accountMaxWriteCapacityUnits(0L)
                .tableMaxReadCapacityUnits(0L)
                .tableMaxWriteCapacityUnits(0L)
                .build();
        final DescribeLimitsResponse result = dynamoDbClient.describeLimits(Object::toString);
    }

    public void tryDescribeTable1() {
        final DescribeTableRequest describeTableRequest = DescribeTableRequest.builder()
                .tableName("tableName")
                .build();
        final DescribeTableResponse varThatUsesProps = DescribeTableResponse.builder()
                .table(TableDescription.builder()
                        .attributeDefinitions(AttributeDefinition.builder()
                                .attributeName("attributeName")
                                .attributeType(ScalarAttributeType.S)
                                .build())
                        .tableName("tableName")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("attributeName")
                                .keyType(KeyType.HASH)
                                .build())
                        .tableStatus(TableStatus.CREATING)
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .provisionedThroughput(ProvisionedThroughputDescription.builder()
                                .lastIncreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .lastDecreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .numberOfDecreasesToday(0L)
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .tableSizeBytes(0L)
                        .itemCount(0L)
                        .tableArn("tableArn")
                        .tableId("tableId")
                        .billingModeSummary(BillingModeSummary.builder()
                                .billingMode(BillingMode.PROVISIONED)
                                .lastUpdateToPayPerRequestDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .localSecondaryIndexes(LocalSecondaryIndexDescription.builder()
                                .indexName("indexName")
                                .keySchema(KeySchemaElement.builder()
                                        .attributeName("attributeName")
                                        .keyType(KeyType.HASH)
                                        .build())
                                .projection(Projection.builder()
                                        .projectionType(ProjectionType.ALL)
                                        .nonKeyAttributes("nonKeyAttributes")
                                        .build())
                                .indexSizeBytes(0L)
                                .itemCount(0L)
                                .indexArn("indexArn")
                                .build())
                        .globalSecondaryIndexes(GlobalSecondaryIndexDescription.builder().build())
                        .build())
                .build();
        final DescribeTableResponse result = dynamoDbClient.describeTable(describeTableRequest);
    }

    public void tryDescribeTable2() {
        final DescribeTableResponse varThatUsesProps = DescribeTableResponse.builder()
                .table(TableDescription.builder()
                        .attributeDefinitions(AttributeDefinition.builder()
                                .attributeName("attributeName")
                                .attributeType(ScalarAttributeType.S)
                                .build())
                        .tableName("tableName")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("attributeName")
                                .keyType(KeyType.HASH)
                                .build())
                        .tableStatus(TableStatus.CREATING)
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .provisionedThroughput(ProvisionedThroughputDescription.builder()
                                .lastIncreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .lastDecreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .numberOfDecreasesToday(0L)
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .tableSizeBytes(0L)
                        .itemCount(0L)
                        .tableArn("tableArn")
                        .tableId("tableId")
                        .billingModeSummary(BillingModeSummary.builder()
                                .billingMode(BillingMode.PROVISIONED)
                                .lastUpdateToPayPerRequestDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .localSecondaryIndexes(LocalSecondaryIndexDescription.builder()
                                .indexName("indexName")
                                .keySchema(KeySchemaElement.builder()
                                        .attributeName("attributeName")
                                        .keyType(KeyType.HASH)
                                        .build())
                                .projection(Projection.builder()
                                        .projectionType(ProjectionType.ALL)
                                        .nonKeyAttributes("nonKeyAttributes")
                                        .build())
                                .indexSizeBytes(0L)
                                .itemCount(0L)
                                .indexArn("indexArn")
                                .build())
                        .globalSecondaryIndexes(GlobalSecondaryIndexDescription.builder().build())
                        .build())
                .build();
        final DescribeTableResponse result = dynamoDbClient.describeTable(Object::toString);
    }

    public void tryDescribeTableReplicaAutoScaling1() {
        final DescribeTableReplicaAutoScalingRequest describeTableReplicaAutoScalingRequest = DescribeTableReplicaAutoScalingRequest.builder()
                .tableName("tableName")
                .build();
        final DescribeTableReplicaAutoScalingResponse varThatUsesProps = DescribeTableReplicaAutoScalingResponse.builder()
                .tableAutoScalingDescription(TableAutoScalingDescription.builder()
                        .tableName("tableName")
                        .tableStatus(TableStatus.CREATING)
                        .replicas(ReplicaAutoScalingDescription.builder().build())
                        .build())
                .build();
        final DescribeTableReplicaAutoScalingResponse result = dynamoDbClient.describeTableReplicaAutoScaling(
                describeTableReplicaAutoScalingRequest);
    }

    public void tryDescribeTableReplicaAutoScaling2() {
        final DescribeTableReplicaAutoScalingResponse varThatUsesProps = DescribeTableReplicaAutoScalingResponse.builder()
                .tableAutoScalingDescription(TableAutoScalingDescription.builder()
                        .tableName("tableName")
                        .tableStatus(TableStatus.CREATING)
                        .replicas(ReplicaAutoScalingDescription.builder().build())
                        .build())
                .build();
        final DescribeTableReplicaAutoScalingResponse result = dynamoDbClient.describeTableReplicaAutoScaling(
                Object::toString);
    }

    public void tryDescribeTimeToLive1() {
        final DescribeTimeToLiveRequest describeTimeToLiveRequest = DescribeTimeToLiveRequest.builder()
                .tableName("tableName")
                .build();
        final DescribeTimeToLiveResponse varThatUsesProps = DescribeTimeToLiveResponse.builder()
                .timeToLiveDescription(TimeToLiveDescription.builder()
                        .timeToLiveStatus(TimeToLiveStatus.ENABLING)
                        .attributeName("attributeName")
                        .build())
                .build();
        final DescribeTimeToLiveResponse result = dynamoDbClient.describeTimeToLive(describeTimeToLiveRequest);
    }

    public void tryDescribeTimeToLive2() {
        final DescribeTimeToLiveResponse varThatUsesProps = DescribeTimeToLiveResponse.builder()
                .timeToLiveDescription(TimeToLiveDescription.builder()
                        .timeToLiveStatus(TimeToLiveStatus.ENABLING)
                        .attributeName("attributeName")
                        .build())
                .build();
        final DescribeTimeToLiveResponse result = dynamoDbClient.describeTimeToLive(Object::toString);
    }

    public void tryGetItem1() {
        final GetItemRequest getItemRequest = GetItemRequest.builder()
                .tableName("TableName")
                .key(Map.of("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()))
                .build();
        final GetItemResponse varThatUsesProps = GetItemResponse.builder()
                .item(Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherKeyValue").build())
                )).build();
        final GetItemResponse result = dynamoDbClient.getItem(getItemRequest);
    }

    public void tryGetItem2() {
        final GetItemResponse varThatUsesProps = GetItemResponse.builder()
                .item(Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherKeyValue").build())
                )).build();
        final GetItemResponse result = dynamoDbClient.getItem(Object::toString);
    }

    public void tryListBackups1() {
        final ListBackupsResponse varThatUsesProps = ListBackupsResponse.builder()
                .backupSummaries(BackupSummary.builder()
                        .tableName("tableName")
                        .tableId("tableId")
                        .tableArn("tableArn")
                        .backupArn("backupArn")
                        .backupName("backupName")
                        .backupCreationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .backupExpiryDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .backupStatus(BackupStatus.CREATING)
                        .backupType(BackupType.USER)
                        .backupSizeBytes(0L)
                        .build())
                .lastEvaluatedBackupArn("lastEvaluatedBackupArn")
                .build();
        final ListBackupsResponse result = dynamoDbClient.listBackups();
    }

    public void tryListBackups2() {
        final ListBackupsRequest listBackupsRequest = ListBackupsRequest.builder()
                .tableName("tableName")
                .limit(0)
                .timeRangeLowerBound(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .timeRangeUpperBound(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .exclusiveStartBackupArn("exclusiveStartBackupArn")
                .backupType(BackupTypeFilter.USER)
                .build();
        final ListBackupsResponse varThatUsesProps = ListBackupsResponse.builder()
                .backupSummaries(BackupSummary.builder()
                        .tableName("tableName")
                        .tableId("tableId")
                        .tableArn("tableArn")
                        .backupArn("backupArn")
                        .backupName("backupName")
                        .backupCreationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .backupExpiryDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .backupStatus(BackupStatus.CREATING)
                        .backupType(BackupType.USER)
                        .backupSizeBytes(0L)
                        .build())
                .lastEvaluatedBackupArn("lastEvaluatedBackupArn")
                .build();
        final ListBackupsResponse result = dynamoDbClient.listBackups(listBackupsRequest);
    }

    public void tryListBackups3() {
        final ListBackupsResponse varThatUsesProps = ListBackupsResponse.builder()
                .backupSummaries(BackupSummary.builder()
                        .tableName("tableName")
                        .tableId("tableId")
                        .tableArn("tableArn")
                        .backupArn("backupArn")
                        .backupName("backupName")
                        .backupCreationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .backupExpiryDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .backupStatus(BackupStatus.CREATING)
                        .backupType(BackupType.USER)
                        .backupSizeBytes(0L)
                        .build())
                .lastEvaluatedBackupArn("lastEvaluatedBackupArn")
                .build();
        final ListBackupsResponse result = dynamoDbClient.listBackups(Object::toString);
    }

    public void tryListContributorInsights1() {
        final ListContributorInsightsRequest listContributorInsightsRequest = ListContributorInsightsRequest.builder()
                .tableName("tableName")
                .nextToken("nextToken")
                .maxResults(0)
                .build();
        final ListContributorInsightsResponse varThatUsesProps = ListContributorInsightsResponse.builder()
                .contributorInsightsSummaries(ContributorInsightsSummary.builder()
                        .tableName("tableName")
                        .indexName("indexName")
                        .contributorInsightsStatus(ContributorInsightsStatus.ENABLING)
                        .build())
                .nextToken("nextToken")
                .build();
        final ListContributorInsightsResponse result = dynamoDbClient.listContributorInsights(
                listContributorInsightsRequest);
    }

    public void tryListContributorInsights2() {
        final ListContributorInsightsResponse varThatUsesProps = ListContributorInsightsResponse.builder()
                .contributorInsightsSummaries(ContributorInsightsSummary.builder()
                        .tableName("tableName")
                        .indexName("indexName")
                        .contributorInsightsStatus(ContributorInsightsStatus.ENABLING)
                        .build())
                .nextToken("nextToken")
                .build();
        final ListContributorInsightsResponse result = dynamoDbClient.listContributorInsights(Object::toString);
    }

    public void tryListContributorInsightsPaginator1() {
        final ListContributorInsightsRequest listContributorInsightsRequest = ListContributorInsightsRequest.builder()
                .tableName("tableName")
                .nextToken("nextToken")
                .maxResults(0)
                .build();
        final ListContributorInsightsIterable varThatUsesProps = null;
        final ListContributorInsightsIterable result = dynamoDbClient.listContributorInsightsPaginator(
                listContributorInsightsRequest);
    }

    public void tryListContributorInsightsPaginator2() {
        final ListContributorInsightsIterable varThatUsesProps = null;
        final ListContributorInsightsIterable result = dynamoDbClient.listContributorInsightsPaginator(
                Object::toString);
    }

    public void tryListGlobalTables1() {
        final ListGlobalTablesResponse varThatUsesProps = ListGlobalTablesResponse.builder()
                .globalTables(GlobalTable.builder()
                        .globalTableName("globalTableName")
                        .replicationGroup(Replica.builder()
                                .regionName("regionName")
                                .build())
                        .build())
                .lastEvaluatedGlobalTableName("lastEvaluatedGlobalTableName")
                .build();
        final ListGlobalTablesResponse result = dynamoDbClient.listGlobalTables();
    }

    public void tryListGlobalTables2() {
        final ListGlobalTablesRequest listGlobalTablesRequest = ListGlobalTablesRequest.builder()
                .exclusiveStartGlobalTableName("exclusiveStartGlobalTableName")
                .limit(0)
                .regionName("regionName")
                .build();
        final ListGlobalTablesResponse varThatUsesProps = ListGlobalTablesResponse.builder()
                .globalTables(GlobalTable.builder()
                        .globalTableName("globalTableName")
                        .replicationGroup(Replica.builder()
                                .regionName("regionName")
                                .build())
                        .build())
                .lastEvaluatedGlobalTableName("lastEvaluatedGlobalTableName")
                .build();
        final ListGlobalTablesResponse result = dynamoDbClient.listGlobalTables(listGlobalTablesRequest);
    }

    public void tryListGlobalTables3() {
        final ListGlobalTablesResponse varThatUsesProps = ListGlobalTablesResponse.builder()
                .globalTables(GlobalTable.builder()
                        .globalTableName("globalTableName")
                        .replicationGroup(Replica.builder()
                                .regionName("regionName")
                                .build())
                        .build())
                .lastEvaluatedGlobalTableName("lastEvaluatedGlobalTableName")
                .build();
        final ListGlobalTablesResponse result = dynamoDbClient.listGlobalTables(Object::toString);
    }

    public void tryListTables1() {
        final ListTablesResponse varThatUsesProps = ListTablesResponse.builder()
                .tableNames("tableNames")
                .lastEvaluatedTableName("lastEvaluatedTableName")
                .build();
        final ListTablesResponse result = dynamoDbClient.listTables();
    }

    public void tryListTables2() {
        final ListTablesRequest listTablesRequest = ListTablesRequest.builder()
                .exclusiveStartTableName("exclusiveStartTableName")
                .limit(0)
                .build();
        final ListTablesResponse varThatUsesProps = ListTablesResponse.builder()
                .tableNames("tableNames")
                .lastEvaluatedTableName("lastEvaluatedTableName")
                .build();
        final ListTablesResponse result = dynamoDbClient.listTables(listTablesRequest);
    }

    public void tryListTables3() {
        final ListTablesResponse varThatUsesProps = ListTablesResponse.builder()
                .tableNames("tableNames")
                .lastEvaluatedTableName("lastEvaluatedTableName")
                .build();
        final ListTablesResponse result = dynamoDbClient.listTables(Object::toString);
    }

    public void tryListTablesPaginator1() {
        final ListTablesIterable varThatUsesProps = null;
        final ListTablesIterable result = dynamoDbClient.listTablesPaginator();
    }

    public void tryListTablesPaginator2() {
        final ListTablesRequest listTablesRequest = ListTablesRequest.builder()
                .exclusiveStartTableName("exclusiveStartTableName")
                .limit(0)
                .build();
        final ListTablesIterable varThatUsesProps = null;
        final ListTablesIterable result = dynamoDbClient.listTablesPaginator(listTablesRequest);
    }

    public void tryListTablesPaginator3() {
        final ListTablesIterable varThatUsesProps = null;
        final ListTablesIterable result = dynamoDbClient.listTablesPaginator(Object::toString);
    }

    public void tryListTagsOfResource1() {
        final ListTagsOfResourceRequest listTagsOfResourceRequest = ListTagsOfResourceRequest.builder()
                .resourceArn("resourceArn")
                .nextToken("nextToken")
                .build();
        final ListTagsOfResourceResponse varThatUsesProps = ListTagsOfResourceResponse.builder()
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .nextToken("nextToken")
                .build();
        final ListTagsOfResourceResponse result = dynamoDbClient.listTagsOfResource(listTagsOfResourceRequest);
    }

    public void tryListTagsOfResource2() {
        final ListTagsOfResourceResponse varThatUsesProps = ListTagsOfResourceResponse.builder()
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .nextToken("nextToken")
                .build();
        final ListTagsOfResourceResponse result = dynamoDbClient.listTagsOfResource(Object::toString);
    }

    public void tryPutItem1() {
        final PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName("TableName")
                .item(Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherValue").build())))
                .build();
        final PutItemResponse varThatUsesProps = PutItemResponse.builder()
                .attributes(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                        .s("value")
                        .build())))
                .consumedCapacity(ConsumedCapacity.builder()
                        .tableName("tableName")
                        .capacityUnits(0.0)
                        .readCapacityUnits(0.0)
                        .writeCapacityUnits(0.0)
                        .table(Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())
                        .localSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .globalSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .build())
                .itemCollectionMetrics(ItemCollectionMetrics.builder()
                        .itemCollectionKey(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                                .s("value")
                                .build())))
                        .sizeEstimateRangeGB(0.0)
                        .build())
                .build();
        final PutItemResponse result = dynamoDbClient.putItem(putItemRequest);
    }

    public void tryPutItem2() {
        final PutItemResponse varThatUsesProps = PutItemResponse.builder()
                .attributes(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                        .s("value")
                        .build())))
                .consumedCapacity(ConsumedCapacity.builder()
                        .tableName("tableName")
                        .capacityUnits(0.0)
                        .readCapacityUnits(0.0)
                        .writeCapacityUnits(0.0)
                        .table(Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())
                        .localSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .globalSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .build())
                .itemCollectionMetrics(ItemCollectionMetrics.builder()
                        .itemCollectionKey(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                                .s("value")
                                .build())))
                        .sizeEstimateRangeGB(0.0)
                        .build())
                .build();
        final PutItemResponse result = dynamoDbClient.putItem(Object::toString);
    }

    public void tryQuery1() {
        final QueryRequest queryRequest = QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build();
        final QueryResponse varThatUsesProps = QueryResponse.builder().items(
                Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue1").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherKeyValue1").build())
                ),
                Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue2").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherKeyValue2").build())
                )
        ).build();
        final QueryResponse result = dynamoDbClient.query(queryRequest);
    }

    public void tryQuery2() {
        final QueryResponse varThatUsesProps = QueryResponse.builder().items(
                Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue1").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherKeyValue1").build())
                ),
                Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue2").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherKeyValue2").build())
                )
        ).build();
        final QueryResponse result = dynamoDbClient.query(Object::toString);
    }

    public void tryQueryPaginator1() {
        final QueryRequest queryRequest = QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build();
        final QueryIterable varThatUsesProps = null;
        final QueryIterable result = dynamoDbClient.queryPaginator(queryRequest);
    }

    public void tryQueryPaginator2() {
        final QueryIterable varThatUsesProps = null;
        final QueryIterable result = dynamoDbClient.queryPaginator(Object::toString);
    }

    public void tryRestoreTableFromBackup1() {
        final RestoreTableFromBackupRequest restoreTableFromBackupRequest = RestoreTableFromBackupRequest.builder()
                .targetTableName("targetTableName")
                .backupArn("backupArn")
                .billingModeOverride(BillingMode.PROVISIONED)
                .globalSecondaryIndexOverride(GlobalSecondaryIndex.builder()
                        .indexName("indexName")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("attributeName")
                                .keyType(KeyType.HASH)
                                .build())
                        .projection(Projection.builder()
                                .projectionType(ProjectionType.ALL)
                                .nonKeyAttributes("nonKeyAttributes")
                                .build())
                        .provisionedThroughput(ProvisionedThroughput.builder()
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .build())
                .localSecondaryIndexOverride(LocalSecondaryIndex.builder()
                        .indexName("indexName")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("attributeName")
                                .keyType(KeyType.HASH)
                                .build())
                        .projection(Projection.builder()
                                .projectionType(ProjectionType.ALL)
                                .nonKeyAttributes("nonKeyAttributes")
                                .build())
                        .build())
                .provisionedThroughputOverride(ProvisionedThroughput.builder()
                        .readCapacityUnits(0L)
                        .writeCapacityUnits(0L)
                        .build())
                .sseSpecificationOverride(SSESpecification.builder()
                        .enabled(false)
                        .sseType(SSEType.AES256)
                        .kmsMasterKeyId("kmsMasterKeyId")
                        .build())
                .build();
        final RestoreTableFromBackupResponse varThatUsesProps = RestoreTableFromBackupResponse.builder()
                .tableDescription(TableDescription.builder()
                        .attributeDefinitions(AttributeDefinition.builder()
                                .attributeName("attributeName")
                                .attributeType(ScalarAttributeType.S)
                                .build())
                        .tableName("tableName")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("attributeName")
                                .keyType(KeyType.HASH)
                                .build())
                        .tableStatus(TableStatus.CREATING)
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .provisionedThroughput(ProvisionedThroughputDescription.builder()
                                .lastIncreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .lastDecreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .numberOfDecreasesToday(0L)
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .tableSizeBytes(0L)
                        .itemCount(0L)
                        .tableArn("tableArn")
                        .tableId("tableId")
                        .billingModeSummary(BillingModeSummary.builder()
                                .billingMode(BillingMode.PROVISIONED)
                                .lastUpdateToPayPerRequestDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .localSecondaryIndexes(LocalSecondaryIndexDescription.builder()
                                .indexName("indexName")
                                .keySchema(KeySchemaElement.builder()
                                        .attributeName("attributeName")
                                        .keyType(KeyType.HASH)
                                        .build())
                                .projection(Projection.builder()
                                        .projectionType(ProjectionType.ALL)
                                        .nonKeyAttributes("nonKeyAttributes")
                                        .build())
                                .indexSizeBytes(0L)
                                .itemCount(0L)
                                .indexArn("indexArn")
                                .build())
                        .globalSecondaryIndexes(GlobalSecondaryIndexDescription.builder().build())
                        .build())
                .build();
        final RestoreTableFromBackupResponse result = dynamoDbClient.restoreTableFromBackup(
                restoreTableFromBackupRequest);
    }

    public void tryRestoreTableFromBackup2() {
        final RestoreTableFromBackupResponse varThatUsesProps = RestoreTableFromBackupResponse.builder()
                .tableDescription(TableDescription.builder()
                        .attributeDefinitions(AttributeDefinition.builder()
                                .attributeName("attributeName")
                                .attributeType(ScalarAttributeType.S)
                                .build())
                        .tableName("tableName")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("attributeName")
                                .keyType(KeyType.HASH)
                                .build())
                        .tableStatus(TableStatus.CREATING)
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .provisionedThroughput(ProvisionedThroughputDescription.builder()
                                .lastIncreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .lastDecreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .numberOfDecreasesToday(0L)
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .tableSizeBytes(0L)
                        .itemCount(0L)
                        .tableArn("tableArn")
                        .tableId("tableId")
                        .billingModeSummary(BillingModeSummary.builder()
                                .billingMode(BillingMode.PROVISIONED)
                                .lastUpdateToPayPerRequestDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .localSecondaryIndexes(LocalSecondaryIndexDescription.builder()
                                .indexName("indexName")
                                .keySchema(KeySchemaElement.builder()
                                        .attributeName("attributeName")
                                        .keyType(KeyType.HASH)
                                        .build())
                                .projection(Projection.builder()
                                        .projectionType(ProjectionType.ALL)
                                        .nonKeyAttributes("nonKeyAttributes")
                                        .build())
                                .indexSizeBytes(0L)
                                .itemCount(0L)
                                .indexArn("indexArn")
                                .build())
                        .globalSecondaryIndexes(GlobalSecondaryIndexDescription.builder().build())
                        .build())
                .build();
        final RestoreTableFromBackupResponse result = dynamoDbClient.restoreTableFromBackup(Object::toString);
    }

    public void tryRestoreTableToPointInTime1() {
        final RestoreTableToPointInTimeRequest restoreTableToPointInTimeRequest = RestoreTableToPointInTimeRequest.builder()
                .sourceTableArn("sourceTableArn")
                .sourceTableName("sourceTableName")
                .targetTableName("targetTableName")
                .useLatestRestorableTime(false)
                .restoreDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .billingModeOverride(BillingMode.PROVISIONED)
                .globalSecondaryIndexOverride(GlobalSecondaryIndex.builder()
                        .indexName("indexName")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("attributeName")
                                .keyType(KeyType.HASH)
                                .build())
                        .projection(Projection.builder()
                                .projectionType(ProjectionType.ALL)
                                .nonKeyAttributes("nonKeyAttributes")
                                .build())
                        .provisionedThroughput(ProvisionedThroughput.builder()
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .build())
                .localSecondaryIndexOverride(LocalSecondaryIndex.builder()
                        .indexName("indexName")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("attributeName")
                                .keyType(KeyType.HASH)
                                .build())
                        .projection(Projection.builder()
                                .projectionType(ProjectionType.ALL)
                                .nonKeyAttributes("nonKeyAttributes")
                                .build())
                        .build())
                .provisionedThroughputOverride(ProvisionedThroughput.builder()
                        .readCapacityUnits(0L)
                        .writeCapacityUnits(0L)
                        .build())
                .sseSpecificationOverride(SSESpecification.builder()
                        .enabled(false)
                        .sseType(SSEType.AES256)
                        .kmsMasterKeyId("kmsMasterKeyId")
                        .build())
                .build();
        final RestoreTableToPointInTimeResponse varThatUsesProps = RestoreTableToPointInTimeResponse.builder()
                .tableDescription(TableDescription.builder()
                        .attributeDefinitions(AttributeDefinition.builder()
                                .attributeName("attributeName")
                                .attributeType(ScalarAttributeType.S)
                                .build())
                        .tableName("tableName")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("attributeName")
                                .keyType(KeyType.HASH)
                                .build())
                        .tableStatus(TableStatus.CREATING)
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .provisionedThroughput(ProvisionedThroughputDescription.builder()
                                .lastIncreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .lastDecreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .numberOfDecreasesToday(0L)
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .tableSizeBytes(0L)
                        .itemCount(0L)
                        .tableArn("tableArn")
                        .tableId("tableId")
                        .billingModeSummary(BillingModeSummary.builder()
                                .billingMode(BillingMode.PROVISIONED)
                                .lastUpdateToPayPerRequestDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .localSecondaryIndexes(LocalSecondaryIndexDescription.builder()
                                .indexName("indexName")
                                .keySchema(KeySchemaElement.builder()
                                        .attributeName("attributeName")
                                        .keyType(KeyType.HASH)
                                        .build())
                                .projection(Projection.builder()
                                        .projectionType(ProjectionType.ALL)
                                        .nonKeyAttributes("nonKeyAttributes")
                                        .build())
                                .indexSizeBytes(0L)
                                .itemCount(0L)
                                .indexArn("indexArn")
                                .build())
                        .globalSecondaryIndexes(GlobalSecondaryIndexDescription.builder().build())
                        .build())
                .build();
        final RestoreTableToPointInTimeResponse result = dynamoDbClient.restoreTableToPointInTime(
                restoreTableToPointInTimeRequest);
    }

    public void tryRestoreTableToPointInTime2() {
        final RestoreTableToPointInTimeResponse varThatUsesProps = RestoreTableToPointInTimeResponse.builder()
                .tableDescription(TableDescription.builder()
                        .attributeDefinitions(AttributeDefinition.builder()
                                .attributeName("attributeName")
                                .attributeType(ScalarAttributeType.S)
                                .build())
                        .tableName("tableName")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("attributeName")
                                .keyType(KeyType.HASH)
                                .build())
                        .tableStatus(TableStatus.CREATING)
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .provisionedThroughput(ProvisionedThroughputDescription.builder()
                                .lastIncreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .lastDecreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .numberOfDecreasesToday(0L)
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .tableSizeBytes(0L)
                        .itemCount(0L)
                        .tableArn("tableArn")
                        .tableId("tableId")
                        .billingModeSummary(BillingModeSummary.builder()
                                .billingMode(BillingMode.PROVISIONED)
                                .lastUpdateToPayPerRequestDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .localSecondaryIndexes(LocalSecondaryIndexDescription.builder()
                                .indexName("indexName")
                                .keySchema(KeySchemaElement.builder()
                                        .attributeName("attributeName")
                                        .keyType(KeyType.HASH)
                                        .build())
                                .projection(Projection.builder()
                                        .projectionType(ProjectionType.ALL)
                                        .nonKeyAttributes("nonKeyAttributes")
                                        .build())
                                .indexSizeBytes(0L)
                                .itemCount(0L)
                                .indexArn("indexArn")
                                .build())
                        .globalSecondaryIndexes(GlobalSecondaryIndexDescription.builder().build())
                        .build())
                .build();
        final RestoreTableToPointInTimeResponse result = dynamoDbClient.restoreTableToPointInTime(Object::toString);
    }

    public void tryScan1() {
        final ScanRequest scanRequest = ScanRequest.builder()
                .tableName("TableName")
                .build();
        final ScanResponse varThatUsesProps = ScanResponse.builder()
                .items(
                        Map.ofEntries(
                                Map.entry("KeyName1", AttributeValue.builder().s("Value1").build()),
                                Map.entry("KeyName2", AttributeValue.builder().s("Value2").build())
                        ),
                        Map.ofEntries(
                                Map.entry("KeyName1", AttributeValue.builder().s("Value12").build()),
                                Map.entry("KeyName2", AttributeValue.builder().s("Value22").build())
                        )
                ).build();
        final ScanResponse result = dynamoDbClient.scan(scanRequest);
    }

    public void tryScan2() {
        final ScanResponse varThatUsesProps = ScanResponse.builder()
                .items(
                        Map.ofEntries(
                                Map.entry("KeyName1", AttributeValue.builder().s("Value1").build()),
                                Map.entry("KeyName2", AttributeValue.builder().s("Value2").build())
                        ),
                        Map.ofEntries(
                                Map.entry("KeyName1", AttributeValue.builder().s("Value12").build()),
                                Map.entry("KeyName2", AttributeValue.builder().s("Value22").build())
                        )
                ).build();
        final ScanResponse result = dynamoDbClient.scan(Object::toString);
    }

    public void tryScanPaginator1() {
        final ScanRequest scanRequest = ScanRequest.builder()
                .tableName("TableName")
                .build();
        final ScanIterable varThatUsesProps = null;
        final ScanIterable result = dynamoDbClient.scanPaginator(scanRequest);
    }

    public void tryScanPaginator2() {
        final ScanIterable varThatUsesProps = null;
        final ScanIterable result = dynamoDbClient.scanPaginator(Object::toString);
    }

    public void tryTagResource1() {
        final TagResourceRequest tagResourceRequest = TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build();
        final TagResourceResponse varThatUsesProps = TagResourceResponse.builder().build();
        final TagResourceResponse result = dynamoDbClient.tagResource(tagResourceRequest);
    }

    public void tryTagResource2() {
        final TagResourceResponse varThatUsesProps = TagResourceResponse.builder().build();
        final TagResourceResponse result = dynamoDbClient.tagResource(Object::toString);
    }

    public void tryTransactGetItems1() {
        final TransactGetItemsRequest transactGetItemsRequest = TransactGetItemsRequest.builder()
                .transactItems(TransactGetItem.builder().get(
                        Get.builder()
                                .tableName("TableName")
                                .key(Map.of(
                                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                                )).build()).build()).build();
        final TransactGetItemsResponse varThatUsesProps = TransactGetItemsResponse.builder().responses(
                ItemResponse.builder()
                        .item(Map.ofEntries(
                                Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build())
                        )).build(),
                ItemResponse.builder()
                        .item(Map.ofEntries(
                                Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue2").build())
                        )).build()
        ).build();
        final TransactGetItemsResponse result = dynamoDbClient.transactGetItems(transactGetItemsRequest);
    }

    public void tryTransactGetItems2() {
        final TransactGetItemsResponse varThatUsesProps = TransactGetItemsResponse.builder().responses(
                ItemResponse.builder()
                        .item(Map.ofEntries(
                                Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build())
                        )).build(),
                ItemResponse.builder()
                        .item(Map.ofEntries(
                                Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue2").build())
                        )).build()
        ).build();
        final TransactGetItemsResponse result = dynamoDbClient.transactGetItems(Object::toString);
    }

    public void tryTransactWriteItems1() {
        final TransactWriteItemsRequest transactWriteItemsRequest = TransactWriteItemsRequest.builder().transactItems(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(Map.ofEntries(
                                                Map.entry("PrimaryKeyName",
                                                        AttributeValue.builder().s("PrimaryKeyValue").build())
                                        )).build())
                                .build())
                .build();
        final TransactWriteItemsResponse varThatUsesProps = TransactWriteItemsResponse.builder()
                .consumedCapacity(ConsumedCapacity.builder()
                        .tableName("tableName")
                        .capacityUnits(0.0)
                        .readCapacityUnits(0.0)
                        .writeCapacityUnits(0.0)
                        .table(Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())
                        .localSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .globalSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .build())
                .itemCollectionMetrics(Map.ofEntries(Map.entry("value", List.of(ItemCollectionMetrics.builder()
                        .itemCollectionKey(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                                .s("value")
                                .build())))
                        .sizeEstimateRangeGB(0.0)
                        .build()))))
                .build();
        final TransactWriteItemsResponse result = dynamoDbClient.transactWriteItems(transactWriteItemsRequest);
    }

    public void tryTransactWriteItems2() {
        final TransactWriteItemsResponse varThatUsesProps = TransactWriteItemsResponse.builder()
                .consumedCapacity(ConsumedCapacity.builder()
                        .tableName("tableName")
                        .capacityUnits(0.0)
                        .readCapacityUnits(0.0)
                        .writeCapacityUnits(0.0)
                        .table(Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())
                        .localSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .globalSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .build())
                .itemCollectionMetrics(Map.ofEntries(Map.entry("value", List.of(ItemCollectionMetrics.builder()
                        .itemCollectionKey(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                                .s("value")
                                .build())))
                        .sizeEstimateRangeGB(0.0)
                        .build()))))
                .build();
        final TransactWriteItemsResponse result = dynamoDbClient.transactWriteItems(Object::toString);
    }

    public void tryUntagResource1() {
        final UntagResourceRequest untagResourceRequest = UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build();
        final UntagResourceResponse varThatUsesProps = UntagResourceResponse.builder().build();
        final UntagResourceResponse result = dynamoDbClient.untagResource(untagResourceRequest);
    }

    public void tryUntagResource2() {
        final UntagResourceResponse varThatUsesProps = UntagResourceResponse.builder().build();
        final UntagResourceResponse result = dynamoDbClient.untagResource(Object::toString);
    }

    public void tryUpdateContinuousBackups1() {
        final UpdateContinuousBackupsRequest updateContinuousBackupsRequest = UpdateContinuousBackupsRequest.builder()
                .tableName("tableName")
                .pointInTimeRecoverySpecification(PointInTimeRecoverySpecification.builder()
                        .pointInTimeRecoveryEnabled(false)
                        .build())
                .build();
        final UpdateContinuousBackupsResponse varThatUsesProps = UpdateContinuousBackupsResponse.builder()
                .continuousBackupsDescription(ContinuousBackupsDescription.builder()
                        .continuousBackupsStatus(ContinuousBackupsStatus.ENABLED)
                        .pointInTimeRecoveryDescription(PointInTimeRecoveryDescription.builder()
                                .pointInTimeRecoveryStatus(PointInTimeRecoveryStatus.ENABLED)
                                .earliestRestorableDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .latestRestorableDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .build())
                .build();
        final UpdateContinuousBackupsResponse result = dynamoDbClient.updateContinuousBackups(
                updateContinuousBackupsRequest);
    }

    public void tryUpdateContinuousBackups2() {
        final UpdateContinuousBackupsResponse varThatUsesProps = UpdateContinuousBackupsResponse.builder()
                .continuousBackupsDescription(ContinuousBackupsDescription.builder()
                        .continuousBackupsStatus(ContinuousBackupsStatus.ENABLED)
                        .pointInTimeRecoveryDescription(PointInTimeRecoveryDescription.builder()
                                .pointInTimeRecoveryStatus(PointInTimeRecoveryStatus.ENABLED)
                                .earliestRestorableDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .latestRestorableDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .build())
                .build();
        final UpdateContinuousBackupsResponse result = dynamoDbClient.updateContinuousBackups(Object::toString);
    }

    public void tryUpdateContributorInsights1() {
        final UpdateContributorInsightsRequest updateContributorInsightsRequest = UpdateContributorInsightsRequest.builder()
                .tableName("tableName")
                .indexName("indexName")
                .contributorInsightsAction(ContributorInsightsAction.ENABLE)
                .build();
        final UpdateContributorInsightsResponse varThatUsesProps = UpdateContributorInsightsResponse.builder()
                .tableName("tableName")
                .indexName("indexName")
                .contributorInsightsStatus(ContributorInsightsStatus.ENABLING)
                .build();
        final UpdateContributorInsightsResponse result = dynamoDbClient.updateContributorInsights(
                updateContributorInsightsRequest);
    }

    public void tryUpdateContributorInsights2() {
        final UpdateContributorInsightsResponse varThatUsesProps = UpdateContributorInsightsResponse.builder()
                .tableName("tableName")
                .indexName("indexName")
                .contributorInsightsStatus(ContributorInsightsStatus.ENABLING)
                .build();
        final UpdateContributorInsightsResponse result = dynamoDbClient.updateContributorInsights(Object::toString);
    }

    public void tryUpdateGlobalTable1() {
        final UpdateGlobalTableRequest updateGlobalTableRequest = UpdateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicaUpdates(ReplicaUpdate.builder()
                        .create(CreateReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .delete(DeleteReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .build())
                .build();
        final UpdateGlobalTableResponse varThatUsesProps = UpdateGlobalTableResponse.builder()
                .globalTableDescription(GlobalTableDescription.builder()
                        .replicationGroup(ReplicaDescription.builder()
                                .regionName("regionName")
                                .replicaStatus(ReplicaStatus.CREATING)
                                .replicaStatusDescription("replicaStatusDescription")
                                .replicaStatusPercentProgress("replicaStatusPercentProgress")
                                .kmsMasterKeyId("kmsMasterKeyId")
                                .provisionedThroughputOverride(ProvisionedThroughputOverride.builder()
                                        .readCapacityUnits(0L)
                                        .build())
                                .globalSecondaryIndexes(ReplicaGlobalSecondaryIndexDescription.builder()
                                        .indexName("indexName")
                                        .provisionedThroughputOverride(ProvisionedThroughputOverride.builder()
                                                .readCapacityUnits(0L)
                                                .build())
                                        .build())
                                .build())
                        .globalTableArn("globalTableArn")
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .globalTableStatus(GlobalTableStatus.CREATING)
                        .globalTableName("globalTableName")
                        .build())
                .build();
        final UpdateGlobalTableResponse result = dynamoDbClient.updateGlobalTable(updateGlobalTableRequest);
    }

    public void tryUpdateGlobalTable2() {
        final UpdateGlobalTableResponse varThatUsesProps = UpdateGlobalTableResponse.builder()
                .globalTableDescription(GlobalTableDescription.builder()
                        .replicationGroup(ReplicaDescription.builder()
                                .regionName("regionName")
                                .replicaStatus(ReplicaStatus.CREATING)
                                .replicaStatusDescription("replicaStatusDescription")
                                .replicaStatusPercentProgress("replicaStatusPercentProgress")
                                .kmsMasterKeyId("kmsMasterKeyId")
                                .provisionedThroughputOverride(ProvisionedThroughputOverride.builder()
                                        .readCapacityUnits(0L)
                                        .build())
                                .globalSecondaryIndexes(ReplicaGlobalSecondaryIndexDescription.builder()
                                        .indexName("indexName")
                                        .provisionedThroughputOverride(ProvisionedThroughputOverride.builder()
                                                .readCapacityUnits(0L)
                                                .build())
                                        .build())
                                .build())
                        .globalTableArn("globalTableArn")
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .globalTableStatus(GlobalTableStatus.CREATING)
                        .globalTableName("globalTableName")
                        .build())
                .build();
        final UpdateGlobalTableResponse result = dynamoDbClient.updateGlobalTable(Object::toString);
    }

    public void tryUpdateGlobalTableSettings1() {
        final UpdateGlobalTableSettingsRequest updateGlobalTableSettingsRequest = UpdateGlobalTableSettingsRequest.builder()
                .globalTableName("globalTableName")
                .globalTableBillingMode(BillingMode.PROVISIONED)
                .globalTableProvisionedWriteCapacityUnits(0L)
                .globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate(AutoScalingSettingsUpdate.builder()
                        .minimumUnits(0L)
                        .maximumUnits(0L)
                        .autoScalingDisabled(false)
                        .autoScalingRoleArn("autoScalingRoleArn")
                        .scalingPolicyUpdate(AutoScalingPolicyUpdate.builder()
                                .policyName("policyName")
                                .targetTrackingScalingPolicyConfiguration(
                                        AutoScalingTargetTrackingScalingPolicyConfigurationUpdate.builder()
                                                .disableScaleIn(false)
                                                .scaleInCooldown(0)
                                                .scaleOutCooldown(0)
                                                .targetValue(0.0)
                                                .build())
                                .build())
                        .build())
                .globalTableGlobalSecondaryIndexSettingsUpdate(GlobalTableGlobalSecondaryIndexSettingsUpdate.builder()
                        .indexName("indexName")
                        .provisionedWriteCapacityUnits(0L)
                        .provisionedWriteCapacityAutoScalingSettingsUpdate(AutoScalingSettingsUpdate.builder()
                                .minimumUnits(0L)
                                .maximumUnits(0L)
                                .autoScalingDisabled(false)
                                .autoScalingRoleArn("autoScalingRoleArn")
                                .scalingPolicyUpdate(AutoScalingPolicyUpdate.builder()
                                        .policyName("policyName")
                                        .targetTrackingScalingPolicyConfiguration(
                                                AutoScalingTargetTrackingScalingPolicyConfigurationUpdate.builder()
                                                        .disableScaleIn(false)
                                                        .scaleInCooldown(0)
                                                        .scaleOutCooldown(0)
                                                        .targetValue(0.0)
                                                        .build())
                                        .build())
                                .build())
                        .build())
                .replicaSettingsUpdate(ReplicaSettingsUpdate.builder().build())
                .build();
        final UpdateGlobalTableSettingsResponse varThatUsesProps = UpdateGlobalTableSettingsResponse.builder()
                .globalTableName("globalTableName")
                .replicaSettings(ReplicaSettingsDescription.builder().build())
                .build();
        final UpdateGlobalTableSettingsResponse result = dynamoDbClient.updateGlobalTableSettings(
                updateGlobalTableSettingsRequest);
    }

    public void tryUpdateGlobalTableSettings2() {
        final UpdateGlobalTableSettingsResponse varThatUsesProps = UpdateGlobalTableSettingsResponse.builder()
                .globalTableName("globalTableName")
                .replicaSettings(ReplicaSettingsDescription.builder().build())
                .build();
        final UpdateGlobalTableSettingsResponse result = dynamoDbClient.updateGlobalTableSettings(Object::toString);
    }

    public void tryUpdateItem1() {
        final UpdateItemRequest updateItemRequest = UpdateItemRequest.builder()
                .tableName("tableName")
                .key(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                        .s("value")
                        .build())))
                .attributeUpdates(Map.ofEntries(Map.entry("value", AttributeValueUpdate.builder()
                        .value(AttributeValue.builder()
                                .s("value")
                                .build())
                        .action(AttributeAction.ADD)
                        .build())))
                .expected(Map.ofEntries(Map.entry("value", ExpectedAttributeValue.builder()
                        .value(AttributeValue.builder()
                                .s("value")
                                .build())
                        .exists(false)
                        .comparisonOperator(ComparisonOperator.EQ)
                        .attributeValueList(AttributeValue.builder()
                                .s("value")
                                .build())
                        .build())))
                .conditionalOperator(ConditionalOperator.AND)
                .returnValues(ReturnValue.NONE)
                .returnConsumedCapacity(ReturnConsumedCapacity.INDEXES)
                .returnItemCollectionMetrics(ReturnItemCollectionMetrics.SIZE)
                .updateExpression("updateExpression")
                .conditionExpression("conditionExpression")
                .expressionAttributeNames(Map.ofEntries(Map.entry("value", "value")))
                .expressionAttributeValues(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                        .s("value")
                        .build())))
                .build();
        final UpdateItemResponse varThatUsesProps = UpdateItemResponse.builder()
                .attributes(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                        .s("value")
                        .build())))
                .consumedCapacity(ConsumedCapacity.builder()
                        .tableName("tableName")
                        .capacityUnits(0.0)
                        .readCapacityUnits(0.0)
                        .writeCapacityUnits(0.0)
                        .table(Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())
                        .localSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .globalSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .build())
                .itemCollectionMetrics(ItemCollectionMetrics.builder()
                        .itemCollectionKey(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                                .s("value")
                                .build())))
                        .sizeEstimateRangeGB(0.0)
                        .build())
                .build();
        final UpdateItemResponse result = dynamoDbClient.updateItem(updateItemRequest);
    }

    public void tryUpdateItem2() {
        final UpdateItemResponse varThatUsesProps = UpdateItemResponse.builder()
                .attributes(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                        .s("value")
                        .build())))
                .consumedCapacity(ConsumedCapacity.builder()
                        .tableName("tableName")
                        .capacityUnits(0.0)
                        .readCapacityUnits(0.0)
                        .writeCapacityUnits(0.0)
                        .table(Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())
                        .localSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .globalSecondaryIndexes(Map.ofEntries(Map.entry("value", Capacity.builder()
                                .readCapacityUnits(0.0)
                                .writeCapacityUnits(0.0)
                                .capacityUnits(0.0)
                                .build())))
                        .build())
                .itemCollectionMetrics(ItemCollectionMetrics.builder()
                        .itemCollectionKey(Map.ofEntries(Map.entry("value", AttributeValue.builder()
                                .s("value")
                                .build())))
                        .sizeEstimateRangeGB(0.0)
                        .build())
                .build();
        final UpdateItemResponse result = dynamoDbClient.updateItem(Object::toString);
    }

    public void tryUpdateTable1() {
        final UpdateTableRequest updateTableRequest = UpdateTableRequest.builder()
                .attributeDefinitions(AttributeDefinition.builder()
                        .attributeName("attributeName")
                        .attributeType(ScalarAttributeType.S)
                        .build())
                .tableName("tableName")
                .billingMode(BillingMode.PROVISIONED)
                .provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(0L)
                        .writeCapacityUnits(0L)
                        .build())
                .globalSecondaryIndexUpdates(GlobalSecondaryIndexUpdate.builder()
                        .update(UpdateGlobalSecondaryIndexAction.builder()
                                .indexName("indexName")
                                .provisionedThroughput(ProvisionedThroughput.builder()
                                        .readCapacityUnits(0L)
                                        .writeCapacityUnits(0L)
                                        .build())
                                .build())
                        .create(CreateGlobalSecondaryIndexAction.builder()
                                .indexName("indexName")
                                .keySchema(KeySchemaElement.builder()
                                        .attributeName("attributeName")
                                        .keyType(KeyType.HASH)
                                        .build())
                                .projection(Projection.builder()
                                        .projectionType(ProjectionType.ALL)
                                        .nonKeyAttributes("nonKeyAttributes")
                                        .build())
                                .provisionedThroughput(ProvisionedThroughput.builder()
                                        .readCapacityUnits(0L)
                                        .writeCapacityUnits(0L)
                                        .build())
                                .build())
                        .delete(DeleteGlobalSecondaryIndexAction.builder()
                                .indexName("indexName")
                                .build())
                        .build())
                .streamSpecification(StreamSpecification.builder()
                        .streamEnabled(false)
                        .streamViewType(StreamViewType.NEW_IMAGE)
                        .build())
                .sseSpecification(SSESpecification.builder()
                        .enabled(false)
                        .sseType(SSEType.AES256)
                        .kmsMasterKeyId("kmsMasterKeyId")
                        .build())
                .replicaUpdates(ReplicationGroupUpdate.builder().build())
                .build();
        final UpdateTableResponse varThatUsesProps = UpdateTableResponse.builder()
                .tableDescription(TableDescription.builder()
                        .attributeDefinitions(AttributeDefinition.builder()
                                .attributeName("attributeName")
                                .attributeType(ScalarAttributeType.S)
                                .build())
                        .tableName("tableName")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("attributeName")
                                .keyType(KeyType.HASH)
                                .build())
                        .tableStatus(TableStatus.CREATING)
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .provisionedThroughput(ProvisionedThroughputDescription.builder()
                                .lastIncreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .lastDecreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .numberOfDecreasesToday(0L)
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .tableSizeBytes(0L)
                        .itemCount(0L)
                        .tableArn("tableArn")
                        .tableId("tableId")
                        .billingModeSummary(BillingModeSummary.builder()
                                .billingMode(BillingMode.PROVISIONED)
                                .lastUpdateToPayPerRequestDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .localSecondaryIndexes(LocalSecondaryIndexDescription.builder()
                                .indexName("indexName")
                                .keySchema(KeySchemaElement.builder()
                                        .attributeName("attributeName")
                                        .keyType(KeyType.HASH)
                                        .build())
                                .projection(Projection.builder()
                                        .projectionType(ProjectionType.ALL)
                                        .nonKeyAttributes("nonKeyAttributes")
                                        .build())
                                .indexSizeBytes(0L)
                                .itemCount(0L)
                                .indexArn("indexArn")
                                .build())
                        .globalSecondaryIndexes(GlobalSecondaryIndexDescription.builder().build())
                        .build())
                .build();
        final UpdateTableResponse result = dynamoDbClient.updateTable(updateTableRequest);
    }

    public void tryUpdateTable2() {
        final UpdateTableResponse varThatUsesProps = UpdateTableResponse.builder()
                .tableDescription(TableDescription.builder()
                        .attributeDefinitions(AttributeDefinition.builder()
                                .attributeName("attributeName")
                                .attributeType(ScalarAttributeType.S)
                                .build())
                        .tableName("tableName")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("attributeName")
                                .keyType(KeyType.HASH)
                                .build())
                        .tableStatus(TableStatus.CREATING)
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .provisionedThroughput(ProvisionedThroughputDescription.builder()
                                .lastIncreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .lastDecreaseDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .numberOfDecreasesToday(0L)
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .tableSizeBytes(0L)
                        .itemCount(0L)
                        .tableArn("tableArn")
                        .tableId("tableId")
                        .billingModeSummary(BillingModeSummary.builder()
                                .billingMode(BillingMode.PROVISIONED)
                                .lastUpdateToPayPerRequestDateTime(
                                        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .localSecondaryIndexes(LocalSecondaryIndexDescription.builder()
                                .indexName("indexName")
                                .keySchema(KeySchemaElement.builder()
                                        .attributeName("attributeName")
                                        .keyType(KeyType.HASH)
                                        .build())
                                .projection(Projection.builder()
                                        .projectionType(ProjectionType.ALL)
                                        .nonKeyAttributes("nonKeyAttributes")
                                        .build())
                                .indexSizeBytes(0L)
                                .itemCount(0L)
                                .indexArn("indexArn")
                                .build())
                        .globalSecondaryIndexes(GlobalSecondaryIndexDescription.builder().build())
                        .build())
                .build();
        final UpdateTableResponse result = dynamoDbClient.updateTable(Object::toString);
    }

    public void tryUpdateTableReplicaAutoScaling1() {
        final UpdateTableReplicaAutoScalingRequest updateTableReplicaAutoScalingRequest = UpdateTableReplicaAutoScalingRequest.builder()
                .globalSecondaryIndexUpdates(GlobalSecondaryIndexAutoScalingUpdate.builder()
                        .indexName("indexName")
                        .provisionedWriteCapacityAutoScalingUpdate(AutoScalingSettingsUpdate.builder()
                                .minimumUnits(0L)
                                .maximumUnits(0L)
                                .autoScalingDisabled(false)
                                .autoScalingRoleArn("autoScalingRoleArn")
                                .scalingPolicyUpdate(AutoScalingPolicyUpdate.builder()
                                        .policyName("policyName")
                                        .targetTrackingScalingPolicyConfiguration(
                                                AutoScalingTargetTrackingScalingPolicyConfigurationUpdate.builder()
                                                        .disableScaleIn(false)
                                                        .scaleInCooldown(0)
                                                        .scaleOutCooldown(0)
                                                        .targetValue(0.0)
                                                        .build())
                                        .build())
                                .build())
                        .build())
                .tableName("tableName")
                .provisionedWriteCapacityAutoScalingUpdate(AutoScalingSettingsUpdate.builder()
                        .minimumUnits(0L)
                        .maximumUnits(0L)
                        .autoScalingDisabled(false)
                        .autoScalingRoleArn("autoScalingRoleArn")
                        .scalingPolicyUpdate(AutoScalingPolicyUpdate.builder()
                                .policyName("policyName")
                                .targetTrackingScalingPolicyConfiguration(
                                        AutoScalingTargetTrackingScalingPolicyConfigurationUpdate.builder()
                                                .disableScaleIn(false)
                                                .scaleInCooldown(0)
                                                .scaleOutCooldown(0)
                                                .targetValue(0.0)
                                                .build())
                                .build())
                        .build())
                .replicaUpdates(ReplicaAutoScalingUpdate.builder().build())
                .build();
        final UpdateTableReplicaAutoScalingResponse varThatUsesProps = UpdateTableReplicaAutoScalingResponse.builder()
                .tableAutoScalingDescription(TableAutoScalingDescription.builder()
                        .tableName("tableName")
                        .tableStatus(TableStatus.CREATING)
                        .replicas(ReplicaAutoScalingDescription.builder().build())
                        .build())
                .build();
        final UpdateTableReplicaAutoScalingResponse result = dynamoDbClient.updateTableReplicaAutoScaling(
                updateTableReplicaAutoScalingRequest);
    }

    public void tryUpdateTableReplicaAutoScaling2() {
        final UpdateTableReplicaAutoScalingResponse varThatUsesProps = UpdateTableReplicaAutoScalingResponse.builder()
                .tableAutoScalingDescription(TableAutoScalingDescription.builder()
                        .tableName("tableName")
                        .tableStatus(TableStatus.CREATING)
                        .replicas(ReplicaAutoScalingDescription.builder().build())
                        .build())
                .build();
        final UpdateTableReplicaAutoScalingResponse result = dynamoDbClient.updateTableReplicaAutoScaling(
                Object::toString);
    }

    public void tryUpdateTimeToLive1() {
        final UpdateTimeToLiveRequest updateTimeToLiveRequest = UpdateTimeToLiveRequest.builder()
                .tableName("tableName")
                .timeToLiveSpecification(TimeToLiveSpecification.builder()
                        .enabled(false)
                        .attributeName("attributeName")
                        .build())
                .build();
        final UpdateTimeToLiveResponse varThatUsesProps = UpdateTimeToLiveResponse.builder()
                .timeToLiveSpecification(TimeToLiveSpecification.builder()
                        .enabled(false)
                        .attributeName("attributeName")
                        .build())
                .build();
        final UpdateTimeToLiveResponse result = dynamoDbClient.updateTimeToLive(updateTimeToLiveRequest);
    }

    public void tryUpdateTimeToLive2() {
        final UpdateTimeToLiveResponse varThatUsesProps = UpdateTimeToLiveResponse.builder()
                .timeToLiveSpecification(TimeToLiveSpecification.builder()
                        .enabled(false)
                        .attributeName("attributeName")
                        .build())
                .build();
        final UpdateTimeToLiveResponse result = dynamoDbClient.updateTimeToLive(Object::toString);
    }

    public void tryWaiter() {
        final DynamoDbWaiter varThatUsesProps = DynamoDbWaiter.builder()
                .client(DynamoDbClient.create())
                .build();
        final DynamoDbWaiter result = dynamoDbClient.waiter();
    }
}
