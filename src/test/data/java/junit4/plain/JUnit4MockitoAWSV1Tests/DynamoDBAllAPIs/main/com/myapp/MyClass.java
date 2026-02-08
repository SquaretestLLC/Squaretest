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

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.metrics.RequestMetricCollector;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.waiters.AmazonDynamoDBWaiters;

import java.util.*;

public class MyClass {

    private AWSCredentialsProvider awsCredentialsProvider;
    private ClientConfiguration clientConfiguration;
    private RequestMetricCollector requestMetricCollector;

    private AmazonDynamoDBClient amazonDynamoDBClient;

    public MyClass(AmazonDynamoDBClient amazonDynamoDBClient) {
        this.amazonDynamoDBClient = amazonDynamoDBClient;
    }

    public void tryBatchGetItem() {
        final BatchGetItemRequest request = new BatchGetItemRequest(new HashMap<>(), "returnConsumedCapacity");
        final BatchGetItemResult result = amazonDynamoDBClient.batchGetItem(request);
    }

    public void tryBatchGetItem1() {
        final Map<String, KeysAndAttributes> requestItems = new HashMap<>();
        final BatchGetItemResult result = amazonDynamoDBClient.batchGetItem(requestItems, "returnConsumedCapacity");
    }

    public void tryBatchGetItem2() {
        final Map<String, KeysAndAttributes> requestItems = new HashMap<>();
        final BatchGetItemResult result = amazonDynamoDBClient.batchGetItem(requestItems);
    }

    public void tryBatchWriteItem() {
        final BatchWriteItemRequest request = new BatchWriteItemRequest(new HashMap<>());
        final BatchWriteItemResult result = amazonDynamoDBClient.batchWriteItem(request);
    }

    public void tryBatchWriteItem1() {
        final Map<String, List<WriteRequest>> requestItems = new HashMap<>();
        final BatchWriteItemResult result = amazonDynamoDBClient.batchWriteItem(requestItems);
    }

    public void tryCreateBackup() {
        final CreateBackupRequest request = new CreateBackupRequest();
        request.setTableName("tableName");
        request.setBackupName("backupName");

        final CreateBackupResult result = amazonDynamoDBClient.createBackup(request);
    }

    public void tryCreateGlobalTable() {
        final CreateGlobalTableRequest request = new CreateGlobalTableRequest();
        request.setGlobalTableName("globalTableName");
        request.setReplicationGroup(Arrays.asList());

        final CreateGlobalTableResult result = amazonDynamoDBClient.createGlobalTable(request);
    }

    public void tryCreateTable() {
        final CreateTableRequest request = new CreateTableRequest("tableName", Arrays.asList());
        final CreateTableResult result = amazonDynamoDBClient.createTable(request);
    }

    public void tryCreateTable1() {
        final List<AttributeDefinition> attributeDefinitions = Arrays.asList();
        final List<KeySchemaElement> keySchema = Arrays.asList();
        final ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput(0L, 0L);
        final CreateTableResult result = amazonDynamoDBClient.createTable(attributeDefinitions, "tableName", keySchema, provisionedThroughput);
    }

    public void tryDeleteBackup() {
        final DeleteBackupRequest request = new DeleteBackupRequest();
        request.setBackupArn("backupArn");

        final DeleteBackupResult result = amazonDynamoDBClient.deleteBackup(request);
    }

    public void tryDeleteItem() {
        final DeleteItemRequest request = new DeleteItemRequest("tableName", new HashMap<>(), "returnValues");
        final DeleteItemResult result = amazonDynamoDBClient.deleteItem(request);
    }

    public void tryDeleteItem1() {
        final Map<String, AttributeValue> key = new HashMap<>();
        final DeleteItemResult result = amazonDynamoDBClient.deleteItem("tableName", key);
    }

    public void tryDeleteItem2() {
        final Map<String, AttributeValue> key = new HashMap<>();
        final DeleteItemResult result = amazonDynamoDBClient.deleteItem("tableName", key, "returnValues");
    }

    public void tryDeleteTable() {
        final DeleteTableRequest request = new DeleteTableRequest("tableName");
        final DeleteTableResult result = amazonDynamoDBClient.deleteTable(request);
    }

    public void tryDeleteTable1() {
        final DeleteTableResult result = amazonDynamoDBClient.deleteTable("tableName");
    }

    public void tryDescribeBackup() {
        final DescribeBackupRequest request = new DescribeBackupRequest();
        request.setBackupArn("backupArn");

        final DescribeBackupResult result = amazonDynamoDBClient.describeBackup(request);
    }

    public void tryDescribeContinuousBackups() {
        final DescribeContinuousBackupsRequest request = new DescribeContinuousBackupsRequest();
        request.setTableName("tableName");

        final DescribeContinuousBackupsResult result = amazonDynamoDBClient.describeContinuousBackups(request);
    }

    public void tryDescribeEndpoints() {
        final DescribeEndpointsRequest request = new DescribeEndpointsRequest();
        final DescribeEndpointsResult result = amazonDynamoDBClient.describeEndpoints(request);
    }

    public void tryDescribeGlobalTable() {
        final DescribeGlobalTableRequest request = new DescribeGlobalTableRequest();
        request.setGlobalTableName("globalTableName");

        final DescribeGlobalTableResult result = amazonDynamoDBClient.describeGlobalTable(request);
    }

    public void tryDescribeGlobalTableSettings() {
        final DescribeGlobalTableSettingsRequest request = new DescribeGlobalTableSettingsRequest();
        request.setGlobalTableName("globalTableName");

        final DescribeGlobalTableSettingsResult result = amazonDynamoDBClient.describeGlobalTableSettings(request);
    }

    public void tryDescribeLimits() {
        final DescribeLimitsRequest request = new DescribeLimitsRequest();
        final DescribeLimitsResult result = amazonDynamoDBClient.describeLimits(request);
    }

    public void tryDescribeTable() {
        final DescribeTableRequest request = new DescribeTableRequest("tableName");
        final DescribeTableResult result = amazonDynamoDBClient.describeTable(request);
    }

    public void tryDescribeTable1() {
        final DescribeTableResult result = amazonDynamoDBClient.describeTable("tableName");
    }

    public void tryDescribeTimeToLive() {
        final DescribeTimeToLiveRequest request = new DescribeTimeToLiveRequest();
        request.setTableName("tableName");

        final DescribeTimeToLiveResult result = amazonDynamoDBClient.describeTimeToLive(request);
    }

    public void tryGetItem() {
        final GetItemRequest request = new GetItemRequest("tableName", new HashMap<>(), false);
        final GetItemResult result = amazonDynamoDBClient.getItem(request);
    }

    public void tryGetItem1() {
        final Map<String, AttributeValue> key = new HashMap<>();
        final GetItemResult result = amazonDynamoDBClient.getItem("tableName", key);
    }

    public void tryGetItem2() {
        final Map<String, AttributeValue> key = new HashMap<>();
        final GetItemResult result = amazonDynamoDBClient.getItem("tableName", key, false);
    }

    public void tryListBackups() {
        final ListBackupsRequest request = new ListBackupsRequest();
        request.setTableName("tableName");
        request.setLimit(0);
        request.setTimeRangeLowerBound(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        request.setTimeRangeUpperBound(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        request.setExclusiveStartBackupArn("exclusiveStartBackupArn");
        request.setBackupType("backupType");

        final ListBackupsResult result = amazonDynamoDBClient.listBackups(request);
    }

    public void tryListGlobalTables() {
        final ListGlobalTablesRequest request = new ListGlobalTablesRequest();
        request.setExclusiveStartGlobalTableName("exclusiveStartGlobalTableName");
        request.setLimit(0);
        request.setRegionName("regionName");

        final ListGlobalTablesResult result = amazonDynamoDBClient.listGlobalTables(request);
    }

    public void tryListTables() {
        final ListTablesRequest request = new ListTablesRequest("exclusiveStartTableName", 0);
        final ListTablesResult result = amazonDynamoDBClient.listTables(request);
    }

    public void tryListTables1() {
        final ListTablesResult result = amazonDynamoDBClient.listTables();
    }

    public void tryListTables2() {
        final ListTablesResult result = amazonDynamoDBClient.listTables("exclusiveStartTableName");
    }

    public void tryListTables3() {
        final ListTablesResult result = amazonDynamoDBClient.listTables("exclusiveStartTableName", 0);
    }

    public void tryListTables4() {
        final ListTablesResult result = amazonDynamoDBClient.listTables(0);
    }

    public void tryListTagsOfResource() {
        final ListTagsOfResourceRequest request = new ListTagsOfResourceRequest();
        request.setResourceArn("resourceArn");
        request.setNextToken("nextToken");

        final ListTagsOfResourceResult result = amazonDynamoDBClient.listTagsOfResource(request);
    }

    public void tryPutItem() {
        final PutItemRequest request = new PutItemRequest("tableName", new HashMap<>(), "returnValues");
        final PutItemResult result = amazonDynamoDBClient.putItem(request);
    }

    public void tryPutItem1() {
        final Map<String, AttributeValue> item = new HashMap<>();
        final PutItemResult result = amazonDynamoDBClient.putItem("tableName", item);
    }

    public void tryPutItem2() {
        final Map<String, AttributeValue> item = new HashMap<>();
        final PutItemResult result = amazonDynamoDBClient.putItem("tableName", item, "returnValues");
    }

    public void tryQuery() {
        final QueryRequest request = new QueryRequest("tableName");
        final QueryResult result = amazonDynamoDBClient.query(request);
    }

    public void tryRestoreTableFromBackup() {
        final RestoreTableFromBackupRequest request = new RestoreTableFromBackupRequest();
        request.setTargetTableName("targetTableName");
        request.setBackupArn("backupArn");

        final RestoreTableFromBackupResult result = amazonDynamoDBClient.restoreTableFromBackup(request);
    }

    public void tryRestoreTableToPointInTime() {
        final RestoreTableToPointInTimeRequest request = new RestoreTableToPointInTimeRequest();
        final RestoreTableToPointInTimeResult result = amazonDynamoDBClient.restoreTableToPointInTime(request);
    }

    public void tryScan() {
        final ScanRequest request = new ScanRequest("tableName");
        final ScanResult result = amazonDynamoDBClient.scan(request);
    }

    public void tryScan1() {
        final List<String> attributesToGet = Arrays.asList();
        final ScanResult result = amazonDynamoDBClient.scan("tableName", attributesToGet);
    }

    public void tryScan2() {
        final Map<String, Condition> scanFilter = new HashMap<>();
        final ScanResult result = amazonDynamoDBClient.scan("tableName", scanFilter);
    }

    public void tryScan3() {
        final List<String> attributesToGet = Arrays.asList();
        final Map<String, Condition> scanFilter = new HashMap<>();
        final ScanResult result = amazonDynamoDBClient.scan("tableName", attributesToGet, scanFilter);
    }

    public void tryTagResource() {
        final TagResourceRequest request = new TagResourceRequest();
        request.setResourceArn("resourceArn");
        request.setTags(Arrays.asList());

        final TagResourceResult result = amazonDynamoDBClient.tagResource(request);
    }

    public void tryTransactGetItems() {
        final TransactGetItemsRequest request = new TransactGetItemsRequest();
        request.setTransactItems(Arrays.asList());
        request.setReturnConsumedCapacity("returnConsumedCapacity");

        final TransactGetItemsResult result = amazonDynamoDBClient.transactGetItems(request);
    }

    public void tryTransactWriteItems() {
        final TransactWriteItemsRequest request = new TransactWriteItemsRequest();
        request.setTransactItems(Arrays.asList());
        request.setReturnConsumedCapacity("returnConsumedCapacity");
        request.setReturnItemCollectionMetrics("returnItemCollectionMetrics");
        request.setClientRequestToken("clientRequestToken");

        final TransactWriteItemsResult result = amazonDynamoDBClient.transactWriteItems(request);
    }

    public void tryUntagResource() {
        final UntagResourceRequest request = new UntagResourceRequest();
        request.setResourceArn("resourceArn");
        request.setTagKeys(Arrays.asList());

        final UntagResourceResult result = amazonDynamoDBClient.untagResource(request);
    }

    public void tryUpdateContinuousBackups() {
        final UpdateContinuousBackupsRequest request = new UpdateContinuousBackupsRequest();
        request.setTableName("tableName");
        request.setPointInTimeRecoverySpecification(new PointInTimeRecoverySpecification());

        final UpdateContinuousBackupsResult result = amazonDynamoDBClient.updateContinuousBackups(request);
    }

    public void tryUpdateGlobalTable() {
        final UpdateGlobalTableRequest request = new UpdateGlobalTableRequest();
        request.setGlobalTableName("globalTableName");
        request.setReplicaUpdates(Arrays.asList());

        final UpdateGlobalTableResult result = amazonDynamoDBClient.updateGlobalTable(request);
    }

    public void tryUpdateGlobalTableSettings() {
        final UpdateGlobalTableSettingsRequest request = new UpdateGlobalTableSettingsRequest();
        request.setGlobalTableName("globalTableName");
        request.setGlobalTableBillingMode("globalTableBillingMode");
        request.setGlobalTableProvisionedWriteCapacityUnits(0L);
        request.setGlobalTableProvisionedWriteCapacityAutoScalingSettingsUpdate(new AutoScalingSettingsUpdate());
        request.setGlobalTableGlobalSecondaryIndexSettingsUpdate(Arrays.asList());
        request.setReplicaSettingsUpdate(Arrays.asList());

        final UpdateGlobalTableSettingsResult result = amazonDynamoDBClient.updateGlobalTableSettings(request);
    }

    public void tryUpdateItem() {
        final UpdateItemRequest request = new UpdateItemRequest("tableName", new HashMap<>(), new HashMap<>(), "returnValues");
        final UpdateItemResult result = amazonDynamoDBClient.updateItem(request);
    }

    public void tryUpdateItem1() {
        final Map<String, AttributeValue> key = new HashMap<>();
        final Map<String, AttributeValueUpdate> attributeUpdates = new HashMap<>();
        final UpdateItemResult result = amazonDynamoDBClient.updateItem("tableName", key, attributeUpdates);
    }

    public void tryUpdateItem2() {
        final Map<String, AttributeValue> key = new HashMap<>();
        final Map<String, AttributeValueUpdate> attributeUpdates = new HashMap<>();
        final UpdateItemResult result = amazonDynamoDBClient.updateItem("tableName", key, attributeUpdates, "returnValues");
    }

    public void tryUpdateTable() {
        final UpdateTableRequest request = new UpdateTableRequest("tableName", new ProvisionedThroughput(0L, 0L));
        final UpdateTableResult result = amazonDynamoDBClient.updateTable(request);
    }

    public void tryUpdateTable1() {
        final ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput(0L, 0L);
        final UpdateTableResult result = amazonDynamoDBClient.updateTable("tableName", provisionedThroughput);
    }

    public void tryUpdateTimeToLive() {
        final UpdateTimeToLiveRequest request = new UpdateTimeToLiveRequest();
        request.setTableName("tableName");
        request.setTimeToLiveSpecification(new TimeToLiveSpecification());

        final UpdateTimeToLiveResult result = amazonDynamoDBClient.updateTimeToLive(request);
    }

    public void tryGetCachedResponseMetadata() {
        final AmazonWebServiceRequest request = null;
        final ResponseMetadata result = amazonDynamoDBClient.getCachedResponseMetadata(request);
    }

    public void tryWaiters() {
        final AmazonDynamoDBWaiters result = amazonDynamoDBClient.waiters();
    }

    public void tryShutdown() {
        amazonDynamoDBClient.shutdown();
    }

    public void tryBuilder() {
        final AmazonDynamoDBClientBuilder result = AmazonDynamoDBClient.builder();
    }
}
