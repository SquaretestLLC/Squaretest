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

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class MyClass {
    public static final boolean STREAM_ENABLED = true;
    public static final ProjectionType PROJECTION_TYPE = ProjectionType.KEYS_ONLY;
    public static final long READ_CAPACITY_UNITS = 10L;
    private DynamoDbClient dynamoDbClient;

    public MyClass(final DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void tryCreateTable1() {
        final CreateTableRequest.Builder createTableRequest = AWSHelpers.newCreateTableRequest("theParam");
        createTableRequest.attributeDefinitions(AttributeDefinition.builder()
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
                                .projectionType(PROJECTION_TYPE)
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
                                .readCapacityUnits(READ_CAPACITY_UNITS)
                                .writeCapacityUnits(5L)
                                .build())
                        .build())
                .billingMode(BillingMode.PROVISIONED)
                .provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(READ_CAPACITY_UNITS)
                        .writeCapacityUnits(5L)
                        .build())
                .streamSpecification(StreamSpecification.builder()
                        .streamEnabled(STREAM_ENABLED)
                        .streamViewType(StreamViewType.NEW_IMAGE)
                        .build())
                .sseSpecification(SSESpecification.builder()
                        .enabled(true)
                        .sseType(SSEType.KMS)
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
        final CreateTableResponse result = dynamoDbClient.createTable(createTableRequest.build());
    }
}
