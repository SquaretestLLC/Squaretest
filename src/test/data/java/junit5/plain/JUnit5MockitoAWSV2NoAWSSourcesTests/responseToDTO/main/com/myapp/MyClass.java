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

import com.myapp.helpers.GlobalTableDTO;
import com.myapp.helpers.GlobalTableHelper;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.List;
import java.util.Map;

public class MyClass {
    private DynamoDbClient dynamoDbClient;

    public MyClass(final DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public String tryBatchWriteItem1() {
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

        final BatchWriteItemResponse result = dynamoDbClient.batchWriteItem(batchWriteItemRequest);
        // Access properties on the response. Squaretest should detect this and call the corresponding builder methods.
        // Call getters to acceess some properties of the unprocessedItems.
        final Map<String, List<WriteRequest>> unprocessedItems = result.unprocessedItems();
        for(final Map.Entry<String, List<WriteRequest>> item : unprocessedItems.entrySet()) {
            final List<WriteRequest> requests = item.getValue();
            for(final WriteRequest request : requests) {
                final Map<String, AttributeValue> item1 = request.putRequest().item();
            }
        }

        // Call getters to acceess some properties of the consumed capacity.
        final List<ConsumedCapacity> consumedCapacities = result.consumedCapacity();
        for(final ConsumedCapacity consumedCapacity : consumedCapacities) {
            final Double capacityUnits = consumedCapacity.capacityUnits();
            final String tableName = consumedCapacity.tableName();
            return tableName;
        }
        return null;
    }

    public void tryCreateBackup1() {
        final CreateBackupRequest createBackupRequest = CreateBackupRequest.builder()
                .tableName("tableName")
                .backupName("backupName")
                .build();
        final CreateBackupResponse result = dynamoDbClient.createBackup(createBackupRequest);
    }

    public GlobalTableDTO tryCreateGlobalTable1(final String tableNameParam, final String regionNameParam) {
        final CreateGlobalTableRequest createGlobalTableRequest = CreateGlobalTableRequest.builder()
                .globalTableName(tableNameParam)
                .replicationGroup(Replica.builder()
                        .regionName(regionNameParam)
                        .build())
                .build();
        final CreateGlobalTableResponse result = dynamoDbClient.createGlobalTable(createGlobalTableRequest);
        return GlobalTableHelper.convert(result);
    }
}
