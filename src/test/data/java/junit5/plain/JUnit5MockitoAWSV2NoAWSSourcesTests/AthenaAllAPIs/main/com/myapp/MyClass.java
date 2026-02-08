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

import software.amazon.awssdk.services.athena.AthenaClient;
import software.amazon.awssdk.services.athena.model.*;
import software.amazon.awssdk.services.athena.paginators.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

public class MyClass {

    private AthenaClient athenaClient;

    public MyClass(final AthenaClient athenaClient) {
        this.athenaClient = athenaClient;
    }

    public void tryBatchGetNamedQuery1() {
        final BatchGetNamedQueryRequest batchGetNamedQueryRequest = BatchGetNamedQueryRequest.builder()
                .namedQueryIds("namedQueryIds")
                .build();
        final BatchGetNamedQueryResponse varThatUsesProps = BatchGetNamedQueryResponse.builder()
                .namedQueries(NamedQuery.builder()
                        .name("name")
                        .description("description")
                        .database("database")
                        .queryString("queryString")
                        .namedQueryId("namedQueryId")
                        .workGroup("workGroup")
                        .build())
                .unprocessedNamedQueryIds(UnprocessedNamedQueryId.builder()
                        .namedQueryId("namedQueryId")
                        .errorCode("errorCode")
                        .errorMessage("errorMessage")
                        .build())
                .build();
        final BatchGetNamedQueryResponse result = athenaClient.batchGetNamedQuery(batchGetNamedQueryRequest);
    }

    public void tryBatchGetNamedQuery2() {
        final BatchGetNamedQueryResponse varThatUsesProps = BatchGetNamedQueryResponse.builder()
                .namedQueries(NamedQuery.builder()
                        .name("name")
                        .description("description")
                        .database("database")
                        .queryString("queryString")
                        .namedQueryId("namedQueryId")
                        .workGroup("workGroup")
                        .build())
                .unprocessedNamedQueryIds(UnprocessedNamedQueryId.builder()
                        .namedQueryId("namedQueryId")
                        .errorCode("errorCode")
                        .errorMessage("errorMessage")
                        .build())
                .build();
        final BatchGetNamedQueryResponse result = athenaClient.batchGetNamedQuery(Object::toString);
    }

    public void tryBatchGetQueryExecution1() {
        final BatchGetQueryExecutionRequest batchGetQueryExecutionRequest = BatchGetQueryExecutionRequest.builder()
                .queryExecutionIds("queryExecutionIds")
                .build();
        final BatchGetQueryExecutionResponse varThatUsesProps = BatchGetQueryExecutionResponse.builder()
                .queryExecutions(QueryExecution.builder()
                        .queryExecutionId("queryExecutionId")
                        .query("query")
                        .statementType(StatementType.DDL)
                        .resultConfiguration(ResultConfiguration.builder()
                                .outputLocation("outputLocation")
                                .encryptionConfiguration(EncryptionConfiguration.builder()
                                        .encryptionOption(EncryptionOption.SSE_S3)
                                        .kmsKey("kmsKey")
                                        .build())
                                .build())
                        .queryExecutionContext(QueryExecutionContext.builder()
                                .database("database")
                                .catalog("catalog")
                                .build())
                        .status(QueryExecutionStatus.builder()
                                .state(QueryExecutionState.QUEUED)
                                .stateChangeReason("stateChangeReason")
                                .submissionDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .completionDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .statistics(QueryExecutionStatistics.builder()
                                .engineExecutionTimeInMillis(0L)
                                .dataScannedInBytes(0L)
                                .dataManifestLocation("dataManifestLocation")
                                .totalExecutionTimeInMillis(0L)
                                .queryQueueTimeInMillis(0L)
                                .queryPlanningTimeInMillis(0L)
                                .serviceProcessingTimeInMillis(0L)
                                .build())
                        .workGroup("workGroup")
                        .build())
                .unprocessedQueryExecutionIds(UnprocessedQueryExecutionId.builder()
                        .queryExecutionId("queryExecutionId")
                        .errorCode("errorCode")
                        .errorMessage("errorMessage")
                        .build())
                .build();
        final BatchGetQueryExecutionResponse result = athenaClient.batchGetQueryExecution(
                batchGetQueryExecutionRequest);
    }

    public void tryBatchGetQueryExecution2() {
        final BatchGetQueryExecutionResponse varThatUsesProps = BatchGetQueryExecutionResponse.builder()
                .queryExecutions(QueryExecution.builder()
                        .queryExecutionId("queryExecutionId")
                        .query("query")
                        .statementType(StatementType.DDL)
                        .resultConfiguration(ResultConfiguration.builder()
                                .outputLocation("outputLocation")
                                .encryptionConfiguration(EncryptionConfiguration.builder()
                                        .encryptionOption(EncryptionOption.SSE_S3)
                                        .kmsKey("kmsKey")
                                        .build())
                                .build())
                        .queryExecutionContext(QueryExecutionContext.builder()
                                .database("database")
                                .catalog("catalog")
                                .build())
                        .status(QueryExecutionStatus.builder()
                                .state(QueryExecutionState.QUEUED)
                                .stateChangeReason("stateChangeReason")
                                .submissionDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .completionDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .statistics(QueryExecutionStatistics.builder()
                                .engineExecutionTimeInMillis(0L)
                                .dataScannedInBytes(0L)
                                .dataManifestLocation("dataManifestLocation")
                                .totalExecutionTimeInMillis(0L)
                                .queryQueueTimeInMillis(0L)
                                .queryPlanningTimeInMillis(0L)
                                .serviceProcessingTimeInMillis(0L)
                                .build())
                        .workGroup("workGroup")
                        .build())
                .unprocessedQueryExecutionIds(UnprocessedQueryExecutionId.builder()
                        .queryExecutionId("queryExecutionId")
                        .errorCode("errorCode")
                        .errorMessage("errorMessage")
                        .build())
                .build();
        final BatchGetQueryExecutionResponse result = athenaClient.batchGetQueryExecution(Object::toString);
    }

    public void tryCreateDataCatalog1() {
        final CreateDataCatalogRequest createDataCatalogRequest = CreateDataCatalogRequest.builder()
                .name("name")
                .type(DataCatalogType.LAMBDA)
                .description("description")
                .parameters(Map.ofEntries(Map.entry("value", "value")))
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build();
        final CreateDataCatalogResponse varThatUsesProps = CreateDataCatalogResponse.builder().build();
        final CreateDataCatalogResponse result = athenaClient.createDataCatalog(createDataCatalogRequest);
    }

    public void tryCreateDataCatalog2() {
        final CreateDataCatalogResponse varThatUsesProps = CreateDataCatalogResponse.builder().build();
        final CreateDataCatalogResponse result = athenaClient.createDataCatalog(Object::toString);
    }

    public void tryCreateNamedQuery1() {
        final CreateNamedQueryRequest createNamedQueryRequest = CreateNamedQueryRequest.builder()
                .name("name")
                .description("description")
                .database("database")
                .queryString("queryString")
                .clientRequestToken("clientRequestToken")
                .workGroup("workGroup")
                .build();
        final CreateNamedQueryResponse varThatUsesProps = CreateNamedQueryResponse.builder()
                .namedQueryId("namedQueryId")
                .build();
        final CreateNamedQueryResponse result = athenaClient.createNamedQuery(createNamedQueryRequest);
    }

    public void tryCreateNamedQuery2() {
        final CreateNamedQueryResponse varThatUsesProps = CreateNamedQueryResponse.builder()
                .namedQueryId("namedQueryId")
                .build();
        final CreateNamedQueryResponse result = athenaClient.createNamedQuery(Object::toString);
    }

    public void tryCreateWorkGroup1() {
        final CreateWorkGroupRequest createWorkGroupRequest = CreateWorkGroupRequest.builder()
                .name("name")
                .configuration(WorkGroupConfiguration.builder()
                        .resultConfiguration(ResultConfiguration.builder()
                                .outputLocation("outputLocation")
                                .encryptionConfiguration(EncryptionConfiguration.builder()
                                        .encryptionOption(EncryptionOption.SSE_S3)
                                        .kmsKey("kmsKey")
                                        .build())
                                .build())
                        .enforceWorkGroupConfiguration(false)
                        .publishCloudWatchMetricsEnabled(false)
                        .bytesScannedCutoffPerQuery(0L)
                        .requesterPaysEnabled(false)
                        .build())
                .description("description")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build();
        final CreateWorkGroupResponse varThatUsesProps = CreateWorkGroupResponse.builder().build();
        final CreateWorkGroupResponse result = athenaClient.createWorkGroup(createWorkGroupRequest);
    }

    public void tryCreateWorkGroup2() {
        final CreateWorkGroupResponse varThatUsesProps = CreateWorkGroupResponse.builder().build();
        final CreateWorkGroupResponse result = athenaClient.createWorkGroup(Object::toString);
    }

    public void tryDeleteDataCatalog1() {
        final DeleteDataCatalogRequest deleteDataCatalogRequest = DeleteDataCatalogRequest.builder()
                .name("name")
                .build();
        final DeleteDataCatalogResponse varThatUsesProps = DeleteDataCatalogResponse.builder().build();
        final DeleteDataCatalogResponse result = athenaClient.deleteDataCatalog(deleteDataCatalogRequest);
    }

    public void tryDeleteDataCatalog2() {
        final DeleteDataCatalogResponse varThatUsesProps = DeleteDataCatalogResponse.builder().build();
        final DeleteDataCatalogResponse result = athenaClient.deleteDataCatalog(Object::toString);
    }

    public void tryDeleteNamedQuery1() {
        final DeleteNamedQueryRequest deleteNamedQueryRequest = DeleteNamedQueryRequest.builder()
                .namedQueryId("namedQueryId")
                .build();
        final DeleteNamedQueryResponse varThatUsesProps = DeleteNamedQueryResponse.builder().build();
        final DeleteNamedQueryResponse result = athenaClient.deleteNamedQuery(deleteNamedQueryRequest);
    }

    public void tryDeleteNamedQuery2() {
        final DeleteNamedQueryResponse varThatUsesProps = DeleteNamedQueryResponse.builder().build();
        final DeleteNamedQueryResponse result = athenaClient.deleteNamedQuery(Object::toString);
    }

    public void tryDeleteWorkGroup1() {
        final DeleteWorkGroupRequest deleteWorkGroupRequest = DeleteWorkGroupRequest.builder()
                .workGroup("workGroup")
                .recursiveDeleteOption(false)
                .build();
        final DeleteWorkGroupResponse varThatUsesProps = DeleteWorkGroupResponse.builder().build();
        final DeleteWorkGroupResponse result = athenaClient.deleteWorkGroup(deleteWorkGroupRequest);
    }

    public void tryDeleteWorkGroup2() {
        final DeleteWorkGroupResponse varThatUsesProps = DeleteWorkGroupResponse.builder().build();
        final DeleteWorkGroupResponse result = athenaClient.deleteWorkGroup(Object::toString);
    }

    public void tryGetDataCatalog1() {
        final GetDataCatalogRequest getDataCatalogRequest = GetDataCatalogRequest.builder()
                .name("name")
                .build();
        final GetDataCatalogResponse varThatUsesProps = GetDataCatalogResponse.builder()
                .dataCatalog(DataCatalog.builder()
                        .name("name")
                        .description("description")
                        .type(DataCatalogType.LAMBDA)
                        .parameters(Map.ofEntries(Map.entry("value", "value")))
                        .build())
                .build();
        final GetDataCatalogResponse result = athenaClient.getDataCatalog(getDataCatalogRequest);
    }

    public void tryGetDataCatalog2() {
        final GetDataCatalogResponse varThatUsesProps = GetDataCatalogResponse.builder()
                .dataCatalog(DataCatalog.builder()
                        .name("name")
                        .description("description")
                        .type(DataCatalogType.LAMBDA)
                        .parameters(Map.ofEntries(Map.entry("value", "value")))
                        .build())
                .build();
        final GetDataCatalogResponse result = athenaClient.getDataCatalog(Object::toString);
    }

    public void tryGetDatabase1() {
        final GetDatabaseRequest getDatabaseRequest = GetDatabaseRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .build();
        final GetDatabaseResponse varThatUsesProps = GetDatabaseResponse.builder()
                .database(Database.builder()
                        .name("name")
                        .description("description")
                        .parameters(Map.ofEntries(Map.entry("value", "value")))
                        .build())
                .build();
        final GetDatabaseResponse result = athenaClient.getDatabase(getDatabaseRequest);
    }

    public void tryGetDatabase2() {
        final GetDatabaseResponse varThatUsesProps = GetDatabaseResponse.builder()
                .database(Database.builder()
                        .name("name")
                        .description("description")
                        .parameters(Map.ofEntries(Map.entry("value", "value")))
                        .build())
                .build();
        final GetDatabaseResponse result = athenaClient.getDatabase(Object::toString);
    }

    public void tryGetNamedQuery1() {
        final GetNamedQueryRequest getNamedQueryRequest = GetNamedQueryRequest.builder()
                .namedQueryId("namedQueryId")
                .build();
        final GetNamedQueryResponse varThatUsesProps = GetNamedQueryResponse.builder()
                .namedQuery(NamedQuery.builder()
                        .name("name")
                        .description("description")
                        .database("database")
                        .queryString("queryString")
                        .namedQueryId("namedQueryId")
                        .workGroup("workGroup")
                        .build())
                .build();
        final GetNamedQueryResponse result = athenaClient.getNamedQuery(getNamedQueryRequest);
    }

    public void tryGetNamedQuery2() {
        final GetNamedQueryResponse varThatUsesProps = GetNamedQueryResponse.builder()
                .namedQuery(NamedQuery.builder()
                        .name("name")
                        .description("description")
                        .database("database")
                        .queryString("queryString")
                        .namedQueryId("namedQueryId")
                        .workGroup("workGroup")
                        .build())
                .build();
        final GetNamedQueryResponse result = athenaClient.getNamedQuery(Object::toString);
    }

    public void tryGetQueryExecution1() {
        final GetQueryExecutionRequest getQueryExecutionRequest = GetQueryExecutionRequest.builder()
                .queryExecutionId("queryExecutionId")
                .build();
        final GetQueryExecutionResponse varThatUsesProps = GetQueryExecutionResponse.builder()
                .queryExecution(QueryExecution.builder()
                        .queryExecutionId("queryExecutionId")
                        .query("query")
                        .statementType(StatementType.DDL)
                        .resultConfiguration(ResultConfiguration.builder()
                                .outputLocation("outputLocation")
                                .encryptionConfiguration(EncryptionConfiguration.builder()
                                        .encryptionOption(EncryptionOption.SSE_S3)
                                        .kmsKey("kmsKey")
                                        .build())
                                .build())
                        .queryExecutionContext(QueryExecutionContext.builder()
                                .database("database")
                                .catalog("catalog")
                                .build())
                        .status(QueryExecutionStatus.builder()
                                .state(QueryExecutionState.QUEUED)
                                .stateChangeReason("stateChangeReason")
                                .submissionDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .completionDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .statistics(QueryExecutionStatistics.builder()
                                .engineExecutionTimeInMillis(0L)
                                .dataScannedInBytes(0L)
                                .dataManifestLocation("dataManifestLocation")
                                .totalExecutionTimeInMillis(0L)
                                .queryQueueTimeInMillis(0L)
                                .queryPlanningTimeInMillis(0L)
                                .serviceProcessingTimeInMillis(0L)
                                .build())
                        .workGroup("workGroup")
                        .build())
                .build();
        final GetQueryExecutionResponse result = athenaClient.getQueryExecution(getQueryExecutionRequest);
    }

    public void tryGetQueryExecution2() {
        final GetQueryExecutionResponse varThatUsesProps = GetQueryExecutionResponse.builder()
                .queryExecution(QueryExecution.builder()
                        .queryExecutionId("queryExecutionId")
                        .query("query")
                        .statementType(StatementType.DDL)
                        .resultConfiguration(ResultConfiguration.builder()
                                .outputLocation("outputLocation")
                                .encryptionConfiguration(EncryptionConfiguration.builder()
                                        .encryptionOption(EncryptionOption.SSE_S3)
                                        .kmsKey("kmsKey")
                                        .build())
                                .build())
                        .queryExecutionContext(QueryExecutionContext.builder()
                                .database("database")
                                .catalog("catalog")
                                .build())
                        .status(QueryExecutionStatus.builder()
                                .state(QueryExecutionState.QUEUED)
                                .stateChangeReason("stateChangeReason")
                                .submissionDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .completionDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .build())
                        .statistics(QueryExecutionStatistics.builder()
                                .engineExecutionTimeInMillis(0L)
                                .dataScannedInBytes(0L)
                                .dataManifestLocation("dataManifestLocation")
                                .totalExecutionTimeInMillis(0L)
                                .queryQueueTimeInMillis(0L)
                                .queryPlanningTimeInMillis(0L)
                                .serviceProcessingTimeInMillis(0L)
                                .build())
                        .workGroup("workGroup")
                        .build())
                .build();
        final GetQueryExecutionResponse result = athenaClient.getQueryExecution(Object::toString);
    }

    public void tryGetQueryResults1() {
        final GetQueryResultsRequest getQueryResultsRequest = GetQueryResultsRequest.builder()
                .queryExecutionId("queryExecutionId")
                .nextToken("nextToken")
                .maxResults(0)
                .build();
        final GetQueryResultsResponse varThatUsesProps = GetQueryResultsResponse.builder()
                .updateCount(0L)
                .resultSet(ResultSet.builder()
                        .rows(Row.builder()
                                .data(Datum.builder()
                                        .varCharValue("varCharValue")
                                        .build())
                                .build())
                        .resultSetMetadata(ResultSetMetadata.builder()
                                .columnInfo(ColumnInfo.builder()
                                        .catalogName("catalogName")
                                        .schemaName("schemaName")
                                        .tableName("tableName")
                                        .name("name")
                                        .label("label")
                                        .type("type")
                                        .precision(0)
                                        .scale(0)
                                        .nullable(ColumnNullable.NOT_NULL)
                                        .caseSensitive(false)
                                        .build())
                                .build())
                        .build())
                .nextToken("nextToken")
                .build();
        final GetQueryResultsResponse result = athenaClient.getQueryResults(getQueryResultsRequest);
    }

    public void tryGetQueryResults2() {
        final GetQueryResultsResponse varThatUsesProps = GetQueryResultsResponse.builder()
                .updateCount(0L)
                .resultSet(ResultSet.builder()
                        .rows(Row.builder()
                                .data(Datum.builder()
                                        .varCharValue("varCharValue")
                                        .build())
                                .build())
                        .resultSetMetadata(ResultSetMetadata.builder()
                                .columnInfo(ColumnInfo.builder()
                                        .catalogName("catalogName")
                                        .schemaName("schemaName")
                                        .tableName("tableName")
                                        .name("name")
                                        .label("label")
                                        .type("type")
                                        .precision(0)
                                        .scale(0)
                                        .nullable(ColumnNullable.NOT_NULL)
                                        .caseSensitive(false)
                                        .build())
                                .build())
                        .build())
                .nextToken("nextToken")
                .build();
        final GetQueryResultsResponse result = athenaClient.getQueryResults(Object::toString);
    }

    public void tryGetQueryResultsPaginator1() {
        final GetQueryResultsRequest getQueryResultsRequest = GetQueryResultsRequest.builder()
                .queryExecutionId("queryExecutionId")
                .nextToken("nextToken")
                .maxResults(0)
                .build();
        final GetQueryResultsIterable varThatUsesProps = null;
        final GetQueryResultsIterable result = athenaClient.getQueryResultsPaginator(getQueryResultsRequest);
    }

    public void tryGetQueryResultsPaginator2() {
        final GetQueryResultsIterable varThatUsesProps = null;
        final GetQueryResultsIterable result = athenaClient.getQueryResultsPaginator(Object::toString);
    }

    public void tryGetTableMetadata1() {
        final GetTableMetadataRequest getTableMetadataRequest = GetTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .tableName("tableName")
                .build();
        final GetTableMetadataResponse varThatUsesProps = GetTableMetadataResponse.builder()
                .tableMetadata(TableMetadata.builder()
                        .name("name")
                        .createTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .lastAccessTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .tableType("tableType")
                        .columns(Column.builder()
                                .name("name")
                                .type("type")
                                .comment("comment")
                                .build())
                        .partitionKeys(Column.builder()
                                .name("name")
                                .type("type")
                                .comment("comment")
                                .build())
                        .parameters(Map.ofEntries(Map.entry("value", "value")))
                        .build())
                .build();
        final GetTableMetadataResponse result = athenaClient.getTableMetadata(getTableMetadataRequest);
    }

    public void tryGetTableMetadata2() {
        final GetTableMetadataResponse varThatUsesProps = GetTableMetadataResponse.builder()
                .tableMetadata(TableMetadata.builder()
                        .name("name")
                        .createTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .lastAccessTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .tableType("tableType")
                        .columns(Column.builder()
                                .name("name")
                                .type("type")
                                .comment("comment")
                                .build())
                        .partitionKeys(Column.builder()
                                .name("name")
                                .type("type")
                                .comment("comment")
                                .build())
                        .parameters(Map.ofEntries(Map.entry("value", "value")))
                        .build())
                .build();
        final GetTableMetadataResponse result = athenaClient.getTableMetadata(Object::toString);
    }

    public void tryGetWorkGroup1() {
        final GetWorkGroupRequest getWorkGroupRequest = GetWorkGroupRequest.builder()
                .workGroup("workGroup")
                .build();
        final GetWorkGroupResponse varThatUsesProps = GetWorkGroupResponse.builder()
                .workGroup(WorkGroup.builder()
                        .name("name")
                        .state(WorkGroupState.ENABLED)
                        .configuration(WorkGroupConfiguration.builder()
                                .resultConfiguration(ResultConfiguration.builder()
                                        .outputLocation("outputLocation")
                                        .encryptionConfiguration(EncryptionConfiguration.builder()
                                                .encryptionOption(EncryptionOption.SSE_S3)
                                                .kmsKey("kmsKey")
                                                .build())
                                        .build())
                                .enforceWorkGroupConfiguration(false)
                                .publishCloudWatchMetricsEnabled(false)
                                .bytesScannedCutoffPerQuery(0L)
                                .requesterPaysEnabled(false)
                                .build())
                        .description("description")
                        .creationTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .build();
        final GetWorkGroupResponse result = athenaClient.getWorkGroup(getWorkGroupRequest);
    }

    public void tryGetWorkGroup2() {
        final GetWorkGroupResponse varThatUsesProps = GetWorkGroupResponse.builder()
                .workGroup(WorkGroup.builder()
                        .name("name")
                        .state(WorkGroupState.ENABLED)
                        .configuration(WorkGroupConfiguration.builder()
                                .resultConfiguration(ResultConfiguration.builder()
                                        .outputLocation("outputLocation")
                                        .encryptionConfiguration(EncryptionConfiguration.builder()
                                                .encryptionOption(EncryptionOption.SSE_S3)
                                                .kmsKey("kmsKey")
                                                .build())
                                        .build())
                                .enforceWorkGroupConfiguration(false)
                                .publishCloudWatchMetricsEnabled(false)
                                .bytesScannedCutoffPerQuery(0L)
                                .requesterPaysEnabled(false)
                                .build())
                        .description("description")
                        .creationTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .build();
        final GetWorkGroupResponse result = athenaClient.getWorkGroup(Object::toString);
    }

    public void tryListDataCatalogs1() {
        final ListDataCatalogsRequest listDataCatalogsRequest = ListDataCatalogsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build();
        final ListDataCatalogsResponse varThatUsesProps = ListDataCatalogsResponse.builder()
                .dataCatalogsSummary(DataCatalogSummary.builder()
                        .catalogName("catalogName")
                        .type(DataCatalogType.LAMBDA)
                        .build())
                .nextToken("nextToken")
                .build();
        final ListDataCatalogsResponse result = athenaClient.listDataCatalogs(listDataCatalogsRequest);
    }

    public void tryListDataCatalogs2() {
        final ListDataCatalogsResponse varThatUsesProps = ListDataCatalogsResponse.builder()
                .dataCatalogsSummary(DataCatalogSummary.builder()
                        .catalogName("catalogName")
                        .type(DataCatalogType.LAMBDA)
                        .build())
                .nextToken("nextToken")
                .build();
        final ListDataCatalogsResponse result = athenaClient.listDataCatalogs(Object::toString);
    }

    public void tryListDataCatalogsPaginator1() {
        final ListDataCatalogsRequest listDataCatalogsRequest = ListDataCatalogsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build();
        final ListDataCatalogsIterable varThatUsesProps = null;
        final ListDataCatalogsIterable result = athenaClient.listDataCatalogsPaginator(listDataCatalogsRequest);
    }

    public void tryListDataCatalogsPaginator2() {
        final ListDataCatalogsIterable varThatUsesProps = null;
        final ListDataCatalogsIterable result = athenaClient.listDataCatalogsPaginator(Object::toString);
    }

    public void tryListDatabases1() {
        final ListDatabasesRequest listDatabasesRequest = ListDatabasesRequest.builder()
                .catalogName("catalogName")
                .nextToken("nextToken")
                .maxResults(0)
                .build();
        final ListDatabasesResponse varThatUsesProps = ListDatabasesResponse.builder()
                .databaseList(Database.builder()
                        .name("name")
                        .description("description")
                        .parameters(Map.ofEntries(Map.entry("value", "value")))
                        .build())
                .nextToken("nextToken")
                .build();
        final ListDatabasesResponse result = athenaClient.listDatabases(listDatabasesRequest);
    }

    public void tryListDatabases2() {
        final ListDatabasesResponse varThatUsesProps = ListDatabasesResponse.builder()
                .databaseList(Database.builder()
                        .name("name")
                        .description("description")
                        .parameters(Map.ofEntries(Map.entry("value", "value")))
                        .build())
                .nextToken("nextToken")
                .build();
        final ListDatabasesResponse result = athenaClient.listDatabases(Object::toString);
    }

    public void tryListDatabasesPaginator1() {
        final ListDatabasesRequest listDatabasesRequest = ListDatabasesRequest.builder()
                .catalogName("catalogName")
                .nextToken("nextToken")
                .maxResults(0)
                .build();
        final ListDatabasesIterable varThatUsesProps = null;
        final ListDatabasesIterable result = athenaClient.listDatabasesPaginator(listDatabasesRequest);
    }

    public void tryListDatabasesPaginator2() {
        final ListDatabasesIterable varThatUsesProps = null;
        final ListDatabasesIterable result = athenaClient.listDatabasesPaginator(Object::toString);
    }

    public void tryListNamedQueries1() {
        final ListNamedQueriesResponse varThatUsesProps = ListNamedQueriesResponse.builder()
                .namedQueryIds("namedQueryIds")
                .nextToken("nextToken")
                .build();
        final ListNamedQueriesResponse result = athenaClient.listNamedQueries();
    }

    public void tryListNamedQueries2() {
        final ListNamedQueriesRequest listNamedQueriesRequest = ListNamedQueriesRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build();
        final ListNamedQueriesResponse varThatUsesProps = ListNamedQueriesResponse.builder()
                .namedQueryIds("namedQueryIds")
                .nextToken("nextToken")
                .build();
        final ListNamedQueriesResponse result = athenaClient.listNamedQueries(listNamedQueriesRequest);
    }

    public void tryListNamedQueries3() {
        final ListNamedQueriesResponse varThatUsesProps = ListNamedQueriesResponse.builder()
                .namedQueryIds("namedQueryIds")
                .nextToken("nextToken")
                .build();
        final ListNamedQueriesResponse result = athenaClient.listNamedQueries(Object::toString);
    }

    public void tryListNamedQueriesPaginator1() {
        final ListNamedQueriesIterable varThatUsesProps = null;
        final ListNamedQueriesIterable result = athenaClient.listNamedQueriesPaginator();
    }

    public void tryListNamedQueriesPaginator2() {
        final ListNamedQueriesRequest listNamedQueriesRequest = ListNamedQueriesRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build();
        final ListNamedQueriesIterable varThatUsesProps = null;
        final ListNamedQueriesIterable result = athenaClient.listNamedQueriesPaginator(listNamedQueriesRequest);
    }

    public void tryListNamedQueriesPaginator3() {
        final ListNamedQueriesIterable varThatUsesProps = null;
        final ListNamedQueriesIterable result = athenaClient.listNamedQueriesPaginator(Object::toString);
    }

    public void tryListQueryExecutions1() {
        final ListQueryExecutionsResponse varThatUsesProps = ListQueryExecutionsResponse.builder()
                .queryExecutionIds("queryExecutionIds")
                .nextToken("nextToken")
                .build();
        final ListQueryExecutionsResponse result = athenaClient.listQueryExecutions();
    }

    public void tryListQueryExecutions2() {
        final ListQueryExecutionsRequest listQueryExecutionsRequest = ListQueryExecutionsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build();
        final ListQueryExecutionsResponse varThatUsesProps = ListQueryExecutionsResponse.builder()
                .queryExecutionIds("queryExecutionIds")
                .nextToken("nextToken")
                .build();
        final ListQueryExecutionsResponse result = athenaClient.listQueryExecutions(listQueryExecutionsRequest);
    }

    public void tryListQueryExecutions3() {
        final ListQueryExecutionsResponse varThatUsesProps = ListQueryExecutionsResponse.builder()
                .queryExecutionIds("queryExecutionIds")
                .nextToken("nextToken")
                .build();
        final ListQueryExecutionsResponse result = athenaClient.listQueryExecutions(Object::toString);
    }

    public void tryListQueryExecutionsPaginator1() {
        final ListQueryExecutionsIterable varThatUsesProps = null;
        final ListQueryExecutionsIterable result = athenaClient.listQueryExecutionsPaginator();
    }

    public void tryListQueryExecutionsPaginator2() {
        final ListQueryExecutionsRequest listQueryExecutionsRequest = ListQueryExecutionsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build();
        final ListQueryExecutionsIterable varThatUsesProps = null;
        final ListQueryExecutionsIterable result = athenaClient.listQueryExecutionsPaginator(
                listQueryExecutionsRequest);
    }

    public void tryListQueryExecutionsPaginator3() {
        final ListQueryExecutionsIterable varThatUsesProps = null;
        final ListQueryExecutionsIterable result = athenaClient.listQueryExecutionsPaginator(Object::toString);
    }

    public void tryListTableMetadata1() {
        final ListTableMetadataRequest listTableMetadataRequest = ListTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .expression("expression")
                .nextToken("nextToken")
                .maxResults(0)
                .build();
        final ListTableMetadataResponse varThatUsesProps = ListTableMetadataResponse.builder()
                .tableMetadataList(TableMetadata.builder()
                        .name("name")
                        .createTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .lastAccessTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .tableType("tableType")
                        .columns(Column.builder()
                                .name("name")
                                .type("type")
                                .comment("comment")
                                .build())
                        .partitionKeys(Column.builder()
                                .name("name")
                                .type("type")
                                .comment("comment")
                                .build())
                        .parameters(Map.ofEntries(Map.entry("value", "value")))
                        .build())
                .nextToken("nextToken")
                .build();
        final ListTableMetadataResponse result = athenaClient.listTableMetadata(listTableMetadataRequest);
    }

    public void tryListTableMetadata2() {
        final ListTableMetadataResponse varThatUsesProps = ListTableMetadataResponse.builder()
                .tableMetadataList(TableMetadata.builder()
                        .name("name")
                        .createTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .lastAccessTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .tableType("tableType")
                        .columns(Column.builder()
                                .name("name")
                                .type("type")
                                .comment("comment")
                                .build())
                        .partitionKeys(Column.builder()
                                .name("name")
                                .type("type")
                                .comment("comment")
                                .build())
                        .parameters(Map.ofEntries(Map.entry("value", "value")))
                        .build())
                .nextToken("nextToken")
                .build();
        final ListTableMetadataResponse result = athenaClient.listTableMetadata(Object::toString);
    }

    public void tryListTableMetadataPaginator1() {
        final ListTableMetadataRequest listTableMetadataRequest = ListTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .expression("expression")
                .nextToken("nextToken")
                .maxResults(0)
                .build();
        final ListTableMetadataIterable varThatUsesProps = null;
        final ListTableMetadataIterable result = athenaClient.listTableMetadataPaginator(listTableMetadataRequest);
    }

    public void tryListTableMetadataPaginator2() {
        final ListTableMetadataIterable varThatUsesProps = null;
        final ListTableMetadataIterable result = athenaClient.listTableMetadataPaginator(Object::toString);
    }

    public void tryListTagsForResource1() {
        final ListTagsForResourceRequest listTagsForResourceRequest = ListTagsForResourceRequest.builder()
                .resourceARN("resourceARN")
                .nextToken("nextToken")
                .maxResults(0)
                .build();
        final ListTagsForResourceResponse varThatUsesProps = ListTagsForResourceResponse.builder()
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .nextToken("nextToken")
                .build();
        final ListTagsForResourceResponse result = athenaClient.listTagsForResource(listTagsForResourceRequest);
    }

    public void tryListTagsForResource2() {
        final ListTagsForResourceResponse varThatUsesProps = ListTagsForResourceResponse.builder()
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .nextToken("nextToken")
                .build();
        final ListTagsForResourceResponse result = athenaClient.listTagsForResource(Object::toString);
    }

    public void tryListTagsForResourcePaginator1() {
        final ListTagsForResourceRequest listTagsForResourceRequest = ListTagsForResourceRequest.builder()
                .resourceARN("resourceARN")
                .nextToken("nextToken")
                .maxResults(0)
                .build();
        final ListTagsForResourceIterable varThatUsesProps = null;
        final ListTagsForResourceIterable result = athenaClient.listTagsForResourcePaginator(
                listTagsForResourceRequest);
    }

    public void tryListTagsForResourcePaginator2() {
        final ListTagsForResourceIterable varThatUsesProps = null;
        final ListTagsForResourceIterable result = athenaClient.listTagsForResourcePaginator(Object::toString);
    }

    public void tryListWorkGroups1() {
        final ListWorkGroupsRequest listWorkGroupsRequest = ListWorkGroupsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build();
        final ListWorkGroupsResponse varThatUsesProps = ListWorkGroupsResponse.builder()
                .workGroups(WorkGroupSummary.builder()
                        .name("name")
                        .state(WorkGroupState.ENABLED)
                        .description("description")
                        .creationTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .nextToken("nextToken")
                .build();
        final ListWorkGroupsResponse result = athenaClient.listWorkGroups(listWorkGroupsRequest);
    }

    public void tryListWorkGroups2() {
        final ListWorkGroupsResponse varThatUsesProps = ListWorkGroupsResponse.builder()
                .workGroups(WorkGroupSummary.builder()
                        .name("name")
                        .state(WorkGroupState.ENABLED)
                        .description("description")
                        .creationTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .nextToken("nextToken")
                .build();
        final ListWorkGroupsResponse result = athenaClient.listWorkGroups(Object::toString);
    }

    public void tryListWorkGroupsPaginator1() {
        final ListWorkGroupsRequest listWorkGroupsRequest = ListWorkGroupsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build();
        final ListWorkGroupsIterable varThatUsesProps = null;
        final ListWorkGroupsIterable result = athenaClient.listWorkGroupsPaginator(listWorkGroupsRequest);
    }

    public void tryListWorkGroupsPaginator2() {
        final ListWorkGroupsIterable varThatUsesProps = null;
        final ListWorkGroupsIterable result = athenaClient.listWorkGroupsPaginator(Object::toString);
    }

    public void tryStartQueryExecution1() {
        final StartQueryExecutionRequest startQueryExecutionRequest = StartQueryExecutionRequest.builder()
                .queryString("queryString")
                .clientRequestToken("clientRequestToken")
                .queryExecutionContext(QueryExecutionContext.builder()
                        .database("database")
                        .catalog("catalog")
                        .build())
                .resultConfiguration(ResultConfiguration.builder()
                        .outputLocation("outputLocation")
                        .encryptionConfiguration(EncryptionConfiguration.builder()
                                .encryptionOption(EncryptionOption.SSE_S3)
                                .kmsKey("kmsKey")
                                .build())
                        .build())
                .workGroup("workGroup")
                .build();
        final StartQueryExecutionResponse varThatUsesProps = StartQueryExecutionResponse.builder()
                .queryExecutionId("queryExecutionId")
                .build();
        final StartQueryExecutionResponse result = athenaClient.startQueryExecution(startQueryExecutionRequest);
    }

    public void tryStartQueryExecution2() {
        final StartQueryExecutionResponse varThatUsesProps = StartQueryExecutionResponse.builder()
                .queryExecutionId("queryExecutionId")
                .build();
        final StartQueryExecutionResponse result = athenaClient.startQueryExecution(Object::toString);
    }

    public void tryStopQueryExecution1() {
        final StopQueryExecutionRequest stopQueryExecutionRequest = StopQueryExecutionRequest.builder()
                .queryExecutionId("queryExecutionId")
                .build();
        final StopQueryExecutionResponse varThatUsesProps = StopQueryExecutionResponse.builder().build();
        final StopQueryExecutionResponse result = athenaClient.stopQueryExecution(stopQueryExecutionRequest);
    }

    public void tryStopQueryExecution2() {
        final StopQueryExecutionResponse varThatUsesProps = StopQueryExecutionResponse.builder().build();
        final StopQueryExecutionResponse result = athenaClient.stopQueryExecution(Object::toString);
    }

    public void tryTagResource1() {
        final TagResourceRequest tagResourceRequest = TagResourceRequest.builder()
                .resourceARN("resourceARN")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build();
        final TagResourceResponse varThatUsesProps = TagResourceResponse.builder().build();
        final TagResourceResponse result = athenaClient.tagResource(tagResourceRequest);
    }

    public void tryTagResource2() {
        final TagResourceResponse varThatUsesProps = TagResourceResponse.builder().build();
        final TagResourceResponse result = athenaClient.tagResource(Object::toString);
    }

    public void tryUntagResource1() {
        final UntagResourceRequest untagResourceRequest = UntagResourceRequest.builder()
                .resourceARN("resourceARN")
                .tagKeys("tagKeys")
                .build();
        final UntagResourceResponse varThatUsesProps = UntagResourceResponse.builder().build();
        final UntagResourceResponse result = athenaClient.untagResource(untagResourceRequest);
    }

    public void tryUntagResource2() {
        final UntagResourceResponse varThatUsesProps = UntagResourceResponse.builder().build();
        final UntagResourceResponse result = athenaClient.untagResource(Object::toString);
    }

    public void tryUpdateDataCatalog1() {
        final UpdateDataCatalogRequest updateDataCatalogRequest = UpdateDataCatalogRequest.builder()
                .name("name")
                .type(DataCatalogType.LAMBDA)
                .description("description")
                .parameters(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final UpdateDataCatalogResponse varThatUsesProps = UpdateDataCatalogResponse.builder().build();
        final UpdateDataCatalogResponse result = athenaClient.updateDataCatalog(updateDataCatalogRequest);
    }

    public void tryUpdateDataCatalog2() {
        final UpdateDataCatalogResponse varThatUsesProps = UpdateDataCatalogResponse.builder().build();
        final UpdateDataCatalogResponse result = athenaClient.updateDataCatalog(Object::toString);
    }

    public void tryUpdateWorkGroup1() {
        final UpdateWorkGroupRequest updateWorkGroupRequest = UpdateWorkGroupRequest.builder()
                .workGroup("workGroup")
                .description("description")
                .configurationUpdates(WorkGroupConfigurationUpdates.builder()
                        .enforceWorkGroupConfiguration(false)
                        .resultConfigurationUpdates(ResultConfigurationUpdates.builder()
                                .outputLocation("outputLocation")
                                .removeOutputLocation(false)
                                .encryptionConfiguration(EncryptionConfiguration.builder()
                                        .encryptionOption(EncryptionOption.SSE_S3)
                                        .kmsKey("kmsKey")
                                        .build())
                                .removeEncryptionConfiguration(false)
                                .build())
                        .publishCloudWatchMetricsEnabled(false)
                        .bytesScannedCutoffPerQuery(0L)
                        .removeBytesScannedCutoffPerQuery(false)
                        .requesterPaysEnabled(false)
                        .build())
                .state(WorkGroupState.ENABLED)
                .build();
        final UpdateWorkGroupResponse varThatUsesProps = UpdateWorkGroupResponse.builder().build();
        final UpdateWorkGroupResponse result = athenaClient.updateWorkGroup(updateWorkGroupRequest);
    }

    public void tryUpdateWorkGroup2() {
        final UpdateWorkGroupResponse varThatUsesProps = UpdateWorkGroupResponse.builder().build();
        final UpdateWorkGroupResponse result = athenaClient.updateWorkGroup(Object::toString);
    }
}
