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
import software.amazon.awssdk.services.dynamodb.model.*;

public class AWSHelpers {
    public static final boolean STREAM_ENABLED = false;
    public static final ProjectionType PROJECTION_TYPE = ProjectionType.ALL;
    public static final long READ_CAPACITY_UNITS = 11L;
    public static CreateTableRequest.Builder newCreateTableRequest(final String param) {
        final CreateTableRequest.Builder createTableRequest = CreateTableRequest.builder()
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
                        .enabled(false)
                        .sseType(SSEType.KMS)
                        .kmsMasterKeyId("kmsMasterKeyId")
                        .build())
                .tags(Tag.builder()
                        .key("key")
                        .build());
        return createTableRequest;
    }
}
