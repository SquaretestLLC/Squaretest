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
import software.amazon.awssdk.services.dynamodb.paginators.BatchGetItemIterable;
import software.amazon.awssdk.services.dynamodb.paginators.ListTablesIterable;
import software.amazon.awssdk.services.dynamodb.paginators.QueryIterable;
import software.amazon.awssdk.services.dynamodb.paginators.ScanIterable;

public class MyClass {

    private DynamoDbClient dynamoDbClient;

    public MyClass(final DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void tryBatchGetItem() {
        final BatchGetItemRequest batchGetItemRequest = BatchGetItemRequest.builder().build();
        final BatchGetItemResponse result = dynamoDbClient.batchGetItem(batchGetItemRequest);
    }

    public void tryBatchGetItem1() {
        final BatchGetItemResponse result = dynamoDbClient.batchGetItem(Object::toString);
    }

    public void tryBatchGetItemPaginator() {
        final BatchGetItemRequest batchGetItemRequest = BatchGetItemRequest.builder().build();
        final BatchGetItemIterable result = dynamoDbClient.batchGetItemPaginator(batchGetItemRequest);
    }

    public void tryBatchGetItemPaginator1() {
        final BatchGetItemIterable result = dynamoDbClient.batchGetItemPaginator(Object::toString);
    }

    public void tryBatchWriteItem() {
        final BatchWriteItemRequest batchWriteItemRequest = BatchWriteItemRequest.builder().build();
        final BatchWriteItemResponse result = dynamoDbClient.batchWriteItem(batchWriteItemRequest);
    }

    public void tryBatchWriteItem1() {
        final BatchWriteItemResponse result = dynamoDbClient.batchWriteItem(Object::toString);
    }

    public void tryCreateBackup() {
        final CreateBackupRequest createBackupRequest = CreateBackupRequest.builder().build();
        final CreateBackupResponse result = dynamoDbClient.createBackup(createBackupRequest);
    }

    public void tryCreateBackup1() {
        final CreateBackupResponse result = dynamoDbClient.createBackup(Object::toString);
    }

    public void tryCreateGlobalTable() {
        final CreateGlobalTableRequest createGlobalTableRequest = CreateGlobalTableRequest.builder().build();
        final CreateGlobalTableResponse result = dynamoDbClient.createGlobalTable(createGlobalTableRequest);
    }

    public void tryCreateGlobalTable1() {
        final CreateGlobalTableResponse result = dynamoDbClient.createGlobalTable(Object::toString);
    }

    public void tryCreateTable() {
        final CreateTableRequest createTableRequest = CreateTableRequest.builder().build();
        final CreateTableResponse result = dynamoDbClient.createTable(createTableRequest);
    }

    public void tryCreateTable1() {
        final CreateTableResponse result = dynamoDbClient.createTable(Object::toString);
    }

    public void tryDeleteBackup() {
        final DeleteBackupRequest deleteBackupRequest = DeleteBackupRequest.builder().build();
        final DeleteBackupResponse result = dynamoDbClient.deleteBackup(deleteBackupRequest);
    }

    public void tryDeleteBackup1() {
        final DeleteBackupResponse result = dynamoDbClient.deleteBackup(Object::toString);
    }

    public void tryDeleteItem() {
        final DeleteItemRequest deleteItemRequest = DeleteItemRequest.builder().build();
        final DeleteItemResponse result = dynamoDbClient.deleteItem(deleteItemRequest);
    }

    public void tryDeleteItem1() {
        final DeleteItemResponse result = dynamoDbClient.deleteItem(Object::toString);
    }

    public void tryDeleteTable() {
        final DeleteTableRequest deleteTableRequest = DeleteTableRequest.builder().build();
        final DeleteTableResponse result = dynamoDbClient.deleteTable(deleteTableRequest);
    }

    public void tryDeleteTable1() {
        final DeleteTableResponse result = dynamoDbClient.deleteTable(Object::toString);
    }

    public void tryDescribeBackup() {
        final DescribeBackupRequest describeBackupRequest = DescribeBackupRequest.builder().build();
        final DescribeBackupResponse result = dynamoDbClient.describeBackup(describeBackupRequest);
    }

    public void tryDescribeBackup1() {
        final DescribeBackupResponse result = dynamoDbClient.describeBackup(Object::toString);
    }

    public void tryDescribeContinuousBackups() {
        final DescribeContinuousBackupsRequest describeContinuousBackupsRequest = DescribeContinuousBackupsRequest.builder().build();
        final DescribeContinuousBackupsResponse result = dynamoDbClient.describeContinuousBackups(describeContinuousBackupsRequest);
    }

    public void tryDescribeContinuousBackups1() {
        final DescribeContinuousBackupsResponse result = dynamoDbClient.describeContinuousBackups(Object::toString);
    }

    public void tryDescribeEndpoints() {
        final DescribeEndpointsResponse result = dynamoDbClient.describeEndpoints();
    }

    public void tryDescribeEndpoints1() {
        final DescribeEndpointsRequest describeEndpointsRequest = DescribeEndpointsRequest.builder().build();
        final DescribeEndpointsResponse result = dynamoDbClient.describeEndpoints(describeEndpointsRequest);
    }

    public void tryDescribeEndpoints2() {
        final DescribeEndpointsResponse result = dynamoDbClient.describeEndpoints(Object::toString);
    }

    public void tryDescribeGlobalTable() {
        final DescribeGlobalTableRequest describeGlobalTableRequest = DescribeGlobalTableRequest.builder().build();
        final DescribeGlobalTableResponse result = dynamoDbClient.describeGlobalTable(describeGlobalTableRequest);
    }

    public void tryDescribeGlobalTable1() {
        final DescribeGlobalTableResponse result = dynamoDbClient.describeGlobalTable(Object::toString);
    }

    public void tryDescribeGlobalTableSettings() {
        final DescribeGlobalTableSettingsRequest describeGlobalTableSettingsRequest = DescribeGlobalTableSettingsRequest.builder().build();
        final DescribeGlobalTableSettingsResponse result = dynamoDbClient.describeGlobalTableSettings(describeGlobalTableSettingsRequest);
    }

    public void tryDescribeGlobalTableSettings1() {
        final DescribeGlobalTableSettingsResponse result = dynamoDbClient.describeGlobalTableSettings(Object::toString);
    }

    public void tryDescribeLimits() {
        final DescribeLimitsResponse result = dynamoDbClient.describeLimits();
    }

    public void tryDescribeLimits1() {
        final DescribeLimitsRequest describeLimitsRequest = DescribeLimitsRequest.builder().build();
        final DescribeLimitsResponse result = dynamoDbClient.describeLimits(describeLimitsRequest);
    }

    public void tryDescribeLimits2() {
        final DescribeLimitsResponse result = dynamoDbClient.describeLimits(Object::toString);
    }

    public void tryDescribeTable() {
        final DescribeTableRequest describeTableRequest = DescribeTableRequest.builder().build();
        final DescribeTableResponse result = dynamoDbClient.describeTable(describeTableRequest);
    }

    public void tryDescribeTable1() {
        final DescribeTableResponse result = dynamoDbClient.describeTable(Object::toString);
    }

    public void tryDescribeTimeToLive() {
        final DescribeTimeToLiveRequest describeTimeToLiveRequest = DescribeTimeToLiveRequest.builder().build();
        final DescribeTimeToLiveResponse result = dynamoDbClient.describeTimeToLive(describeTimeToLiveRequest);
    }

    public void tryDescribeTimeToLive1() {
        final DescribeTimeToLiveResponse result = dynamoDbClient.describeTimeToLive(Object::toString);
    }

    public void tryGetItem() {
        final GetItemRequest getItemRequest = GetItemRequest.builder().build();
        final GetItemResponse result = dynamoDbClient.getItem(getItemRequest);
    }

    public void tryGetItem1() {
        final GetItemResponse result = dynamoDbClient.getItem(Object::toString);
    }

    public void tryListBackups() {
        final ListBackupsResponse result = dynamoDbClient.listBackups();
    }

    public void tryListBackups1() {
        final ListBackupsRequest listBackupsRequest = ListBackupsRequest.builder().build();
        final ListBackupsResponse result = dynamoDbClient.listBackups(listBackupsRequest);
    }

    public void tryListBackups2() {
        final ListBackupsResponse result = dynamoDbClient.listBackups(Object::toString);
    }

    public void tryListGlobalTables() {
        final ListGlobalTablesResponse result = dynamoDbClient.listGlobalTables();
    }

    public void tryListGlobalTables1() {
        final ListGlobalTablesRequest listGlobalTablesRequest = ListGlobalTablesRequest.builder().build();
        final ListGlobalTablesResponse result = dynamoDbClient.listGlobalTables(listGlobalTablesRequest);
    }

    public void tryListGlobalTables2() {
        final ListGlobalTablesResponse result = dynamoDbClient.listGlobalTables(Object::toString);
    }

    public void tryListTables() {
        final ListTablesResponse result = dynamoDbClient.listTables();
    }

    public void tryListTables1() {
        final ListTablesRequest listTablesRequest = ListTablesRequest.builder().build();
        final ListTablesResponse result = dynamoDbClient.listTables(listTablesRequest);
    }

    public void tryListTables2() {
        final ListTablesResponse result = dynamoDbClient.listTables(Object::toString);
    }

    public void tryListTablesPaginator() {
        final ListTablesIterable result = dynamoDbClient.listTablesPaginator();
    }

    public void tryListTablesPaginator1() {
        final ListTablesRequest listTablesRequest = ListTablesRequest.builder().build();
        final ListTablesIterable result = dynamoDbClient.listTablesPaginator(listTablesRequest);
    }

    public void tryListTablesPaginator2() {
        final ListTablesIterable result = dynamoDbClient.listTablesPaginator(Object::toString);
    }

    public void tryListTagsOfResource() {
        final ListTagsOfResourceRequest listTagsOfResourceRequest = ListTagsOfResourceRequest.builder().build();
        final ListTagsOfResourceResponse result = dynamoDbClient.listTagsOfResource(listTagsOfResourceRequest);
    }

    public void tryListTagsOfResource1() {
        final ListTagsOfResourceResponse result = dynamoDbClient.listTagsOfResource(Object::toString);
    }

    public void tryPutItem() {
        final PutItemRequest putItemRequest = PutItemRequest.builder().build();
        final PutItemResponse result = dynamoDbClient.putItem(putItemRequest);
    }

    public void tryPutItem1() {
        final PutItemResponse result = dynamoDbClient.putItem(Object::toString);
    }

    public void tryQuery() {
        final QueryRequest queryRequest = QueryRequest.builder().tableName("TableName").keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue").build();
        final QueryResponse result = dynamoDbClient.query(queryRequest);
    }

    public void tryQuery1() {
        final QueryResponse result = dynamoDbClient.query(Object::toString);
    }

    public void tryQueryPaginator() {
        final QueryRequest queryRequest = QueryRequest.builder().tableName("TableName").keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue").build();
        final QueryIterable result = dynamoDbClient.queryPaginator(queryRequest);
    }

    public void tryQueryPaginator1() {
        final QueryIterable result = dynamoDbClient.queryPaginator(Object::toString);
    }

    public void tryRestoreTableFromBackup() {
        final RestoreTableFromBackupRequest restoreTableFromBackupRequest = RestoreTableFromBackupRequest.builder().build();
        final RestoreTableFromBackupResponse result = dynamoDbClient.restoreTableFromBackup(restoreTableFromBackupRequest);
    }

    public void tryRestoreTableFromBackup1() {
        final RestoreTableFromBackupResponse result = dynamoDbClient.restoreTableFromBackup(Object::toString);
    }

    public void tryRestoreTableToPointInTime() {
        final RestoreTableToPointInTimeRequest restoreTableToPointInTimeRequest = RestoreTableToPointInTimeRequest.builder().build();
        final RestoreTableToPointInTimeResponse result = dynamoDbClient.restoreTableToPointInTime(restoreTableToPointInTimeRequest);
    }

    public void tryRestoreTableToPointInTime1() {
        final RestoreTableToPointInTimeResponse result = dynamoDbClient.restoreTableToPointInTime(Object::toString);
    }

    public void tryScan() {
        final ScanRequest scanRequest = ScanRequest.builder().tableName("TableName").build();
        final ScanResponse result = dynamoDbClient.scan(scanRequest);
    }

    public void tryScan1() {
        final ScanResponse result = dynamoDbClient.scan(Object::toString);
    }

    public void tryScanPaginator() {
        final ScanRequest scanRequest = ScanRequest.builder().tableName("TableName").build();
        final ScanIterable result = dynamoDbClient.scanPaginator(scanRequest);
    }

    public void tryScanPaginator1() {
        final ScanIterable result = dynamoDbClient.scanPaginator(Object::toString);
    }

    public void tryTagResource() {
        final TagResourceRequest tagResourceRequest = TagResourceRequest.builder().build();
        final TagResourceResponse result = dynamoDbClient.tagResource(tagResourceRequest);
    }

    public void tryTagResource1() {
        final TagResourceResponse result = dynamoDbClient.tagResource(Object::toString);
    }

    public void tryTransactGetItems() {
        final TransactGetItemsRequest transactGetItemsRequest = TransactGetItemsRequest.builder().build();
        final TransactGetItemsResponse result = dynamoDbClient.transactGetItems(transactGetItemsRequest);
    }

    public void tryTransactGetItems1() {
        final TransactGetItemsResponse result = dynamoDbClient.transactGetItems(Object::toString);
    }

    public void tryTransactWriteItems() {
        final TransactWriteItemsRequest transactWriteItemsRequest = TransactWriteItemsRequest.builder().build();
        final TransactWriteItemsResponse result = dynamoDbClient.transactWriteItems(transactWriteItemsRequest);
    }

    public void tryTransactWriteItems1() {
        final TransactWriteItemsResponse result = dynamoDbClient.transactWriteItems(Object::toString);
    }

    public void tryUntagResource() {
        final UntagResourceRequest untagResourceRequest = UntagResourceRequest.builder().build();
        final UntagResourceResponse result = dynamoDbClient.untagResource(untagResourceRequest);
    }

    public void tryUntagResource1() {
        final UntagResourceResponse result = dynamoDbClient.untagResource(Object::toString);
    }

    public void tryUpdateContinuousBackups() {
        final UpdateContinuousBackupsRequest updateContinuousBackupsRequest = UpdateContinuousBackupsRequest.builder().build();
        final UpdateContinuousBackupsResponse result = dynamoDbClient.updateContinuousBackups(updateContinuousBackupsRequest);
    }

    public void tryUpdateContinuousBackups1() {
        final UpdateContinuousBackupsResponse result = dynamoDbClient.updateContinuousBackups(Object::toString);
    }

    public void tryUpdateGlobalTable() {
        final UpdateGlobalTableRequest updateGlobalTableRequest = UpdateGlobalTableRequest.builder().build();
        final UpdateGlobalTableResponse result = dynamoDbClient.updateGlobalTable(updateGlobalTableRequest);
    }

    public void tryUpdateGlobalTable1() {
        final UpdateGlobalTableResponse result = dynamoDbClient.updateGlobalTable(Object::toString);
    }

    public void tryUpdateGlobalTableSettings() {
        final UpdateGlobalTableSettingsRequest updateGlobalTableSettingsRequest = UpdateGlobalTableSettingsRequest.builder().build();
        final UpdateGlobalTableSettingsResponse result = dynamoDbClient.updateGlobalTableSettings(updateGlobalTableSettingsRequest);
    }

    public void tryUpdateGlobalTableSettings1() {
        final UpdateGlobalTableSettingsResponse result = dynamoDbClient.updateGlobalTableSettings(Object::toString);
    }

    public void tryUpdateItem() {
        final UpdateItemRequest updateItemRequest = UpdateItemRequest.builder().build();
        final UpdateItemResponse result = dynamoDbClient.updateItem(updateItemRequest);
    }

    public void tryUpdateItem1() {
        final UpdateItemResponse result = dynamoDbClient.updateItem(Object::toString);
    }

    public void tryUpdateTable() {
        final UpdateTableRequest updateTableRequest = UpdateTableRequest.builder().build();
        final UpdateTableResponse result = dynamoDbClient.updateTable(updateTableRequest);
    }

    public void tryUpdateTable1() {
        final UpdateTableResponse result = dynamoDbClient.updateTable(Object::toString);
    }

    public void tryUpdateTimeToLive() {
        final UpdateTimeToLiveRequest updateTimeToLiveRequest = UpdateTimeToLiveRequest.builder().build();
        final UpdateTimeToLiveResponse result = dynamoDbClient.updateTimeToLive(updateTimeToLiveRequest);
    }

    public void tryUpdateTimeToLive1() {
        final UpdateTimeToLiveResponse result = dynamoDbClient.updateTimeToLive(Object::toString);
    }
}
