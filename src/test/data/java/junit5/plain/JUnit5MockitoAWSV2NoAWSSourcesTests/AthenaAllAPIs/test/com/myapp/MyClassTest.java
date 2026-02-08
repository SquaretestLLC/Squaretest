package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.athena.AthenaClient;
import software.amazon.awssdk.services.athena.model.*;
import software.amazon.awssdk.services.athena.paginators.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AthenaClient mockAthenaClient;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockAthenaClient);
    }

    @Test
    void testTryBatchGetNamedQuery1() {
        // Setup
        // Configure AthenaClient.batchGetNamedQuery(...).
        final BatchGetNamedQueryResponse batchGetNamedQueryResponse = BatchGetNamedQueryResponse.builder()
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
        when(mockAthenaClient.batchGetNamedQuery(BatchGetNamedQueryRequest.builder()
                .namedQueryIds("namedQueryIds")
                .build())).thenReturn(batchGetNamedQueryResponse);

        // Run the test
        myClassUnderTest.tryBatchGetNamedQuery1();

        // Verify the results
    }

    @Test
    void testTryBatchGetNamedQuery1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.batchGetNamedQuery(BatchGetNamedQueryRequest.builder()
                .namedQueryIds("namedQueryIds")
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryBatchGetNamedQuery1());
    }

    @Test
    void testTryBatchGetNamedQuery1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.batchGetNamedQuery(BatchGetNamedQueryRequest.builder()
                .namedQueryIds("namedQueryIds")
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryBatchGetNamedQuery1());
    }

    @Test
    void testTryBatchGetNamedQuery1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.batchGetNamedQuery(BatchGetNamedQueryRequest.builder()
                .namedQueryIds("namedQueryIds")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryBatchGetNamedQuery1());
    }

    @Test
    void testTryBatchGetNamedQuery1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.batchGetNamedQuery(BatchGetNamedQueryRequest.builder()
                .namedQueryIds("namedQueryIds")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryBatchGetNamedQuery1());
    }

    @Test
    void testTryBatchGetNamedQuery1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.batchGetNamedQuery(BatchGetNamedQueryRequest.builder()
                .namedQueryIds("namedQueryIds")
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryBatchGetNamedQuery1());
    }

    @Test
    void testTryBatchGetNamedQuery2() {
        // Setup
        // Configure AthenaClient.batchGetNamedQuery(...).
        final BatchGetNamedQueryResponse batchGetNamedQueryResponse = BatchGetNamedQueryResponse.builder()
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
        when(mockAthenaClient.batchGetNamedQuery(any(Consumer.class))).thenReturn(batchGetNamedQueryResponse);

        // Run the test
        myClassUnderTest.tryBatchGetNamedQuery2();

        // Verify the results
    }

    @Test
    void testTryBatchGetNamedQuery2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.batchGetNamedQuery(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryBatchGetNamedQuery2());
    }

    @Test
    void testTryBatchGetNamedQuery2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.batchGetNamedQuery(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryBatchGetNamedQuery2());
    }

    @Test
    void testTryBatchGetNamedQuery2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.batchGetNamedQuery(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryBatchGetNamedQuery2());
    }

    @Test
    void testTryBatchGetNamedQuery2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.batchGetNamedQuery(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryBatchGetNamedQuery2());
    }

    @Test
    void testTryBatchGetNamedQuery2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.batchGetNamedQuery(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryBatchGetNamedQuery2());
    }

    @Test
    void testTryBatchGetQueryExecution1() {
        // Setup
        // Configure AthenaClient.batchGetQueryExecution(...).
        final BatchGetQueryExecutionResponse batchGetQueryExecutionResponse = BatchGetQueryExecutionResponse.builder()
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
        when(mockAthenaClient.batchGetQueryExecution(BatchGetQueryExecutionRequest.builder()
                .queryExecutionIds("queryExecutionIds")
                .build())).thenReturn(batchGetQueryExecutionResponse);

        // Run the test
        myClassUnderTest.tryBatchGetQueryExecution1();

        // Verify the results
    }

    @Test
    void testTryBatchGetQueryExecution1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.batchGetQueryExecution(BatchGetQueryExecutionRequest.builder()
                .queryExecutionIds("queryExecutionIds")
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryBatchGetQueryExecution1());
    }

    @Test
    void testTryBatchGetQueryExecution1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.batchGetQueryExecution(BatchGetQueryExecutionRequest.builder()
                .queryExecutionIds("queryExecutionIds")
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryBatchGetQueryExecution1());
    }

    @Test
    void testTryBatchGetQueryExecution1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.batchGetQueryExecution(BatchGetQueryExecutionRequest.builder()
                .queryExecutionIds("queryExecutionIds")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryBatchGetQueryExecution1());
    }

    @Test
    void testTryBatchGetQueryExecution1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.batchGetQueryExecution(BatchGetQueryExecutionRequest.builder()
                .queryExecutionIds("queryExecutionIds")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryBatchGetQueryExecution1());
    }

    @Test
    void testTryBatchGetQueryExecution1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.batchGetQueryExecution(BatchGetQueryExecutionRequest.builder()
                .queryExecutionIds("queryExecutionIds")
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryBatchGetQueryExecution1());
    }

    @Test
    void testTryBatchGetQueryExecution2() {
        // Setup
        // Configure AthenaClient.batchGetQueryExecution(...).
        final BatchGetQueryExecutionResponse batchGetQueryExecutionResponse = BatchGetQueryExecutionResponse.builder()
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
        when(mockAthenaClient.batchGetQueryExecution(any(Consumer.class))).thenReturn(batchGetQueryExecutionResponse);

        // Run the test
        myClassUnderTest.tryBatchGetQueryExecution2();

        // Verify the results
    }

    @Test
    void testTryBatchGetQueryExecution2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.batchGetQueryExecution(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryBatchGetQueryExecution2());
    }

    @Test
    void testTryBatchGetQueryExecution2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.batchGetQueryExecution(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryBatchGetQueryExecution2());
    }

    @Test
    void testTryBatchGetQueryExecution2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.batchGetQueryExecution(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryBatchGetQueryExecution2());
    }

    @Test
    void testTryBatchGetQueryExecution2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.batchGetQueryExecution(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryBatchGetQueryExecution2());
    }

    @Test
    void testTryBatchGetQueryExecution2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.batchGetQueryExecution(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryBatchGetQueryExecution2());
    }

    @Test
    void testTryCreateDataCatalog1() {
        // Setup
        // Configure AthenaClient.createDataCatalog(...).
        final CreateDataCatalogResponse createDataCatalogResponse = CreateDataCatalogResponse.builder().build();
        when(mockAthenaClient.createDataCatalog(CreateDataCatalogRequest.builder()
                .name("name")
                .type(DataCatalogType.LAMBDA)
                .description("description")
                .parameters(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenReturn(createDataCatalogResponse);

        // Run the test
        myClassUnderTest.tryCreateDataCatalog1();

        // Verify the results
    }

    @Test
    void testTryCreateDataCatalog1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.createDataCatalog(CreateDataCatalogRequest.builder()
                .name("name")
                .type(DataCatalogType.LAMBDA)
                .description("description")
                .parameters(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryCreateDataCatalog1());
    }

    @Test
    void testTryCreateDataCatalog1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.createDataCatalog(CreateDataCatalogRequest.builder()
                .name("name")
                .type(DataCatalogType.LAMBDA)
                .description("description")
                .parameters(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryCreateDataCatalog1());
    }

    @Test
    void testTryCreateDataCatalog1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.createDataCatalog(CreateDataCatalogRequest.builder()
                .name("name")
                .type(DataCatalogType.LAMBDA)
                .description("description")
                .parameters(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateDataCatalog1());
    }

    @Test
    void testTryCreateDataCatalog1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.createDataCatalog(CreateDataCatalogRequest.builder()
                .name("name")
                .type(DataCatalogType.LAMBDA)
                .description("description")
                .parameters(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateDataCatalog1());
    }

    @Test
    void testTryCreateDataCatalog1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.createDataCatalog(CreateDataCatalogRequest.builder()
                .name("name")
                .type(DataCatalogType.LAMBDA)
                .description("description")
                .parameters(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryCreateDataCatalog1());
    }

    @Test
    void testTryCreateDataCatalog2() {
        // Setup
        // Configure AthenaClient.createDataCatalog(...).
        final CreateDataCatalogResponse createDataCatalogResponse = CreateDataCatalogResponse.builder().build();
        when(mockAthenaClient.createDataCatalog(any(Consumer.class))).thenReturn(createDataCatalogResponse);

        // Run the test
        myClassUnderTest.tryCreateDataCatalog2();

        // Verify the results
    }

    @Test
    void testTryCreateDataCatalog2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.createDataCatalog(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryCreateDataCatalog2());
    }

    @Test
    void testTryCreateDataCatalog2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.createDataCatalog(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryCreateDataCatalog2());
    }

    @Test
    void testTryCreateDataCatalog2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.createDataCatalog(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateDataCatalog2());
    }

    @Test
    void testTryCreateDataCatalog2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.createDataCatalog(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateDataCatalog2());
    }

    @Test
    void testTryCreateDataCatalog2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.createDataCatalog(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryCreateDataCatalog2());
    }

    @Test
    void testTryCreateNamedQuery1() {
        // Setup
        // Configure AthenaClient.createNamedQuery(...).
        final CreateNamedQueryResponse createNamedQueryResponse = CreateNamedQueryResponse.builder()
                .namedQueryId("namedQueryId")
                .build();
        when(mockAthenaClient.createNamedQuery(CreateNamedQueryRequest.builder()
                .name("name")
                .description("description")
                .database("database")
                .queryString("queryString")
                .clientRequestToken("clientRequestToken")
                .workGroup("workGroup")
                .build())).thenReturn(createNamedQueryResponse);

        // Run the test
        myClassUnderTest.tryCreateNamedQuery1();

        // Verify the results
    }

    @Test
    void testTryCreateNamedQuery1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.createNamedQuery(CreateNamedQueryRequest.builder()
                .name("name")
                .description("description")
                .database("database")
                .queryString("queryString")
                .clientRequestToken("clientRequestToken")
                .workGroup("workGroup")
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryCreateNamedQuery1());
    }

    @Test
    void testTryCreateNamedQuery1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.createNamedQuery(CreateNamedQueryRequest.builder()
                .name("name")
                .description("description")
                .database("database")
                .queryString("queryString")
                .clientRequestToken("clientRequestToken")
                .workGroup("workGroup")
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryCreateNamedQuery1());
    }

    @Test
    void testTryCreateNamedQuery1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.createNamedQuery(CreateNamedQueryRequest.builder()
                .name("name")
                .description("description")
                .database("database")
                .queryString("queryString")
                .clientRequestToken("clientRequestToken")
                .workGroup("workGroup")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateNamedQuery1());
    }

    @Test
    void testTryCreateNamedQuery1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.createNamedQuery(CreateNamedQueryRequest.builder()
                .name("name")
                .description("description")
                .database("database")
                .queryString("queryString")
                .clientRequestToken("clientRequestToken")
                .workGroup("workGroup")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateNamedQuery1());
    }

    @Test
    void testTryCreateNamedQuery1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.createNamedQuery(CreateNamedQueryRequest.builder()
                .name("name")
                .description("description")
                .database("database")
                .queryString("queryString")
                .clientRequestToken("clientRequestToken")
                .workGroup("workGroup")
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryCreateNamedQuery1());
    }

    @Test
    void testTryCreateNamedQuery2() {
        // Setup
        // Configure AthenaClient.createNamedQuery(...).
        final CreateNamedQueryResponse createNamedQueryResponse = CreateNamedQueryResponse.builder()
                .namedQueryId("namedQueryId")
                .build();
        when(mockAthenaClient.createNamedQuery(any(Consumer.class))).thenReturn(createNamedQueryResponse);

        // Run the test
        myClassUnderTest.tryCreateNamedQuery2();

        // Verify the results
    }

    @Test
    void testTryCreateNamedQuery2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.createNamedQuery(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryCreateNamedQuery2());
    }

    @Test
    void testTryCreateNamedQuery2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.createNamedQuery(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryCreateNamedQuery2());
    }

    @Test
    void testTryCreateNamedQuery2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.createNamedQuery(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateNamedQuery2());
    }

    @Test
    void testTryCreateNamedQuery2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.createNamedQuery(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateNamedQuery2());
    }

    @Test
    void testTryCreateNamedQuery2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.createNamedQuery(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryCreateNamedQuery2());
    }

    @Test
    void testTryCreateWorkGroup1() {
        // Setup
        // Configure AthenaClient.createWorkGroup(...).
        final CreateWorkGroupResponse createWorkGroupResponse = CreateWorkGroupResponse.builder().build();
        when(mockAthenaClient.createWorkGroup(CreateWorkGroupRequest.builder()
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
                .build())).thenReturn(createWorkGroupResponse);

        // Run the test
        myClassUnderTest.tryCreateWorkGroup1();

        // Verify the results
    }

    @Test
    void testTryCreateWorkGroup1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.createWorkGroup(CreateWorkGroupRequest.builder()
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
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryCreateWorkGroup1());
    }

    @Test
    void testTryCreateWorkGroup1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.createWorkGroup(CreateWorkGroupRequest.builder()
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
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryCreateWorkGroup1());
    }

    @Test
    void testTryCreateWorkGroup1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.createWorkGroup(CreateWorkGroupRequest.builder()
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
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateWorkGroup1());
    }

    @Test
    void testTryCreateWorkGroup1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.createWorkGroup(CreateWorkGroupRequest.builder()
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
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateWorkGroup1());
    }

    @Test
    void testTryCreateWorkGroup1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.createWorkGroup(CreateWorkGroupRequest.builder()
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
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryCreateWorkGroup1());
    }

    @Test
    void testTryCreateWorkGroup2() {
        // Setup
        // Configure AthenaClient.createWorkGroup(...).
        final CreateWorkGroupResponse createWorkGroupResponse = CreateWorkGroupResponse.builder().build();
        when(mockAthenaClient.createWorkGroup(any(Consumer.class))).thenReturn(createWorkGroupResponse);

        // Run the test
        myClassUnderTest.tryCreateWorkGroup2();

        // Verify the results
    }

    @Test
    void testTryCreateWorkGroup2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.createWorkGroup(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryCreateWorkGroup2());
    }

    @Test
    void testTryCreateWorkGroup2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.createWorkGroup(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryCreateWorkGroup2());
    }

    @Test
    void testTryCreateWorkGroup2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.createWorkGroup(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateWorkGroup2());
    }

    @Test
    void testTryCreateWorkGroup2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.createWorkGroup(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateWorkGroup2());
    }

    @Test
    void testTryCreateWorkGroup2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.createWorkGroup(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryCreateWorkGroup2());
    }

    @Test
    void testTryDeleteDataCatalog1() {
        // Setup
        // Configure AthenaClient.deleteDataCatalog(...).
        final DeleteDataCatalogResponse deleteDataCatalogResponse = DeleteDataCatalogResponse.builder().build();
        when(mockAthenaClient.deleteDataCatalog(DeleteDataCatalogRequest.builder()
                .name("name")
                .build())).thenReturn(deleteDataCatalogResponse);

        // Run the test
        myClassUnderTest.tryDeleteDataCatalog1();

        // Verify the results
    }

    @Test
    void testTryDeleteDataCatalog1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.deleteDataCatalog(DeleteDataCatalogRequest.builder()
                .name("name")
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryDeleteDataCatalog1());
    }

    @Test
    void testTryDeleteDataCatalog1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.deleteDataCatalog(DeleteDataCatalogRequest.builder()
                .name("name")
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryDeleteDataCatalog1());
    }

    @Test
    void testTryDeleteDataCatalog1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.deleteDataCatalog(DeleteDataCatalogRequest.builder()
                .name("name")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteDataCatalog1());
    }

    @Test
    void testTryDeleteDataCatalog1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.deleteDataCatalog(DeleteDataCatalogRequest.builder()
                .name("name")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteDataCatalog1());
    }

    @Test
    void testTryDeleteDataCatalog1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.deleteDataCatalog(DeleteDataCatalogRequest.builder()
                .name("name")
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryDeleteDataCatalog1());
    }

    @Test
    void testTryDeleteDataCatalog2() {
        // Setup
        // Configure AthenaClient.deleteDataCatalog(...).
        final DeleteDataCatalogResponse deleteDataCatalogResponse = DeleteDataCatalogResponse.builder().build();
        when(mockAthenaClient.deleteDataCatalog(any(Consumer.class))).thenReturn(deleteDataCatalogResponse);

        // Run the test
        myClassUnderTest.tryDeleteDataCatalog2();

        // Verify the results
    }

    @Test
    void testTryDeleteDataCatalog2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.deleteDataCatalog(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryDeleteDataCatalog2());
    }

    @Test
    void testTryDeleteDataCatalog2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.deleteDataCatalog(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryDeleteDataCatalog2());
    }

    @Test
    void testTryDeleteDataCatalog2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.deleteDataCatalog(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteDataCatalog2());
    }

    @Test
    void testTryDeleteDataCatalog2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.deleteDataCatalog(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteDataCatalog2());
    }

    @Test
    void testTryDeleteDataCatalog2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.deleteDataCatalog(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryDeleteDataCatalog2());
    }

    @Test
    void testTryDeleteNamedQuery1() {
        // Setup
        // Configure AthenaClient.deleteNamedQuery(...).
        final DeleteNamedQueryResponse deleteNamedQueryResponse = DeleteNamedQueryResponse.builder().build();
        when(mockAthenaClient.deleteNamedQuery(DeleteNamedQueryRequest.builder()
                .namedQueryId("namedQueryId")
                .build())).thenReturn(deleteNamedQueryResponse);

        // Run the test
        myClassUnderTest.tryDeleteNamedQuery1();

        // Verify the results
    }

    @Test
    void testTryDeleteNamedQuery1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.deleteNamedQuery(DeleteNamedQueryRequest.builder()
                .namedQueryId("namedQueryId")
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryDeleteNamedQuery1());
    }

    @Test
    void testTryDeleteNamedQuery1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.deleteNamedQuery(DeleteNamedQueryRequest.builder()
                .namedQueryId("namedQueryId")
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryDeleteNamedQuery1());
    }

    @Test
    void testTryDeleteNamedQuery1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.deleteNamedQuery(DeleteNamedQueryRequest.builder()
                .namedQueryId("namedQueryId")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteNamedQuery1());
    }

    @Test
    void testTryDeleteNamedQuery1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.deleteNamedQuery(DeleteNamedQueryRequest.builder()
                .namedQueryId("namedQueryId")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteNamedQuery1());
    }

    @Test
    void testTryDeleteNamedQuery1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.deleteNamedQuery(DeleteNamedQueryRequest.builder()
                .namedQueryId("namedQueryId")
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryDeleteNamedQuery1());
    }

    @Test
    void testTryDeleteNamedQuery2() {
        // Setup
        // Configure AthenaClient.deleteNamedQuery(...).
        final DeleteNamedQueryResponse deleteNamedQueryResponse = DeleteNamedQueryResponse.builder().build();
        when(mockAthenaClient.deleteNamedQuery(any(Consumer.class))).thenReturn(deleteNamedQueryResponse);

        // Run the test
        myClassUnderTest.tryDeleteNamedQuery2();

        // Verify the results
    }

    @Test
    void testTryDeleteNamedQuery2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.deleteNamedQuery(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryDeleteNamedQuery2());
    }

    @Test
    void testTryDeleteNamedQuery2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.deleteNamedQuery(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryDeleteNamedQuery2());
    }

    @Test
    void testTryDeleteNamedQuery2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.deleteNamedQuery(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteNamedQuery2());
    }

    @Test
    void testTryDeleteNamedQuery2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.deleteNamedQuery(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteNamedQuery2());
    }

    @Test
    void testTryDeleteNamedQuery2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.deleteNamedQuery(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryDeleteNamedQuery2());
    }

    @Test
    void testTryDeleteWorkGroup1() {
        // Setup
        // Configure AthenaClient.deleteWorkGroup(...).
        final DeleteWorkGroupResponse deleteWorkGroupResponse = DeleteWorkGroupResponse.builder().build();
        when(mockAthenaClient.deleteWorkGroup(DeleteWorkGroupRequest.builder()
                .workGroup("workGroup")
                .recursiveDeleteOption(false)
                .build())).thenReturn(deleteWorkGroupResponse);

        // Run the test
        myClassUnderTest.tryDeleteWorkGroup1();

        // Verify the results
    }

    @Test
    void testTryDeleteWorkGroup1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.deleteWorkGroup(DeleteWorkGroupRequest.builder()
                .workGroup("workGroup")
                .recursiveDeleteOption(false)
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryDeleteWorkGroup1());
    }

    @Test
    void testTryDeleteWorkGroup1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.deleteWorkGroup(DeleteWorkGroupRequest.builder()
                .workGroup("workGroup")
                .recursiveDeleteOption(false)
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryDeleteWorkGroup1());
    }

    @Test
    void testTryDeleteWorkGroup1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.deleteWorkGroup(DeleteWorkGroupRequest.builder()
                .workGroup("workGroup")
                .recursiveDeleteOption(false)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteWorkGroup1());
    }

    @Test
    void testTryDeleteWorkGroup1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.deleteWorkGroup(DeleteWorkGroupRequest.builder()
                .workGroup("workGroup")
                .recursiveDeleteOption(false)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteWorkGroup1());
    }

    @Test
    void testTryDeleteWorkGroup1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.deleteWorkGroup(DeleteWorkGroupRequest.builder()
                .workGroup("workGroup")
                .recursiveDeleteOption(false)
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryDeleteWorkGroup1());
    }

    @Test
    void testTryDeleteWorkGroup2() {
        // Setup
        // Configure AthenaClient.deleteWorkGroup(...).
        final DeleteWorkGroupResponse deleteWorkGroupResponse = DeleteWorkGroupResponse.builder().build();
        when(mockAthenaClient.deleteWorkGroup(any(Consumer.class))).thenReturn(deleteWorkGroupResponse);

        // Run the test
        myClassUnderTest.tryDeleteWorkGroup2();

        // Verify the results
    }

    @Test
    void testTryDeleteWorkGroup2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.deleteWorkGroup(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryDeleteWorkGroup2());
    }

    @Test
    void testTryDeleteWorkGroup2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.deleteWorkGroup(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryDeleteWorkGroup2());
    }

    @Test
    void testTryDeleteWorkGroup2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.deleteWorkGroup(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteWorkGroup2());
    }

    @Test
    void testTryDeleteWorkGroup2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.deleteWorkGroup(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteWorkGroup2());
    }

    @Test
    void testTryDeleteWorkGroup2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.deleteWorkGroup(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryDeleteWorkGroup2());
    }

    @Test
    void testTryGetDataCatalog1() {
        // Setup
        // Configure AthenaClient.getDataCatalog(...).
        final GetDataCatalogResponse getDataCatalogResponse = GetDataCatalogResponse.builder()
                .dataCatalog(DataCatalog.builder()
                        .name("name")
                        .description("description")
                        .type(DataCatalogType.LAMBDA)
                        .parameters(new HashMap<>())
                        .build())
                .build();
        when(mockAthenaClient.getDataCatalog(GetDataCatalogRequest.builder()
                .name("name")
                .build())).thenReturn(getDataCatalogResponse);

        // Run the test
        myClassUnderTest.tryGetDataCatalog1();

        // Verify the results
    }

    @Test
    void testTryGetDataCatalog1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.getDataCatalog(GetDataCatalogRequest.builder()
                .name("name")
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryGetDataCatalog1());
    }

    @Test
    void testTryGetDataCatalog1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.getDataCatalog(GetDataCatalogRequest.builder()
                .name("name")
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryGetDataCatalog1());
    }

    @Test
    void testTryGetDataCatalog1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.getDataCatalog(GetDataCatalogRequest.builder()
                .name("name")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetDataCatalog1());
    }

    @Test
    void testTryGetDataCatalog1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.getDataCatalog(GetDataCatalogRequest.builder()
                .name("name")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetDataCatalog1());
    }

    @Test
    void testTryGetDataCatalog1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.getDataCatalog(GetDataCatalogRequest.builder()
                .name("name")
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryGetDataCatalog1());
    }

    @Test
    void testTryGetDataCatalog2() {
        // Setup
        // Configure AthenaClient.getDataCatalog(...).
        final GetDataCatalogResponse getDataCatalogResponse = GetDataCatalogResponse.builder()
                .dataCatalog(DataCatalog.builder()
                        .name("name")
                        .description("description")
                        .type(DataCatalogType.LAMBDA)
                        .parameters(new HashMap<>())
                        .build())
                .build();
        when(mockAthenaClient.getDataCatalog(any(Consumer.class))).thenReturn(getDataCatalogResponse);

        // Run the test
        myClassUnderTest.tryGetDataCatalog2();

        // Verify the results
    }

    @Test
    void testTryGetDataCatalog2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.getDataCatalog(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryGetDataCatalog2());
    }

    @Test
    void testTryGetDataCatalog2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.getDataCatalog(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryGetDataCatalog2());
    }

    @Test
    void testTryGetDataCatalog2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.getDataCatalog(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetDataCatalog2());
    }

    @Test
    void testTryGetDataCatalog2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.getDataCatalog(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetDataCatalog2());
    }

    @Test
    void testTryGetDataCatalog2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.getDataCatalog(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryGetDataCatalog2());
    }

    @Test
    void testTryGetDatabase1() {
        // Setup
        // Configure AthenaClient.getDatabase(...).
        final GetDatabaseResponse getDatabaseResponse = GetDatabaseResponse.builder()
                .database(Database.builder()
                        .name("name")
                        .description("description")
                        .parameters(new HashMap<>())
                        .build())
                .build();
        when(mockAthenaClient.getDatabase(GetDatabaseRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .build())).thenReturn(getDatabaseResponse);

        // Run the test
        myClassUnderTest.tryGetDatabase1();

        // Verify the results
    }

    @Test
    void testTryGetDatabase1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.getDatabase(GetDatabaseRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryGetDatabase1());
    }

    @Test
    void testTryGetDatabase1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.getDatabase(GetDatabaseRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryGetDatabase1());
    }

    @Test
    void testTryGetDatabase1_AthenaClientThrowsMetadataException() {
        // Setup
        when(mockAthenaClient.getDatabase(GetDatabaseRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .build())).thenThrow(MetadataException.class);

        // Run the test
        assertThrows(MetadataException.class, () -> myClassUnderTest.tryGetDatabase1());
    }

    @Test
    void testTryGetDatabase1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.getDatabase(GetDatabaseRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetDatabase1());
    }

    @Test
    void testTryGetDatabase1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.getDatabase(GetDatabaseRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetDatabase1());
    }

    @Test
    void testTryGetDatabase1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.getDatabase(GetDatabaseRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryGetDatabase1());
    }

    @Test
    void testTryGetDatabase2() {
        // Setup
        // Configure AthenaClient.getDatabase(...).
        final GetDatabaseResponse getDatabaseResponse = GetDatabaseResponse.builder()
                .database(Database.builder()
                        .name("name")
                        .description("description")
                        .parameters(new HashMap<>())
                        .build())
                .build();
        when(mockAthenaClient.getDatabase(any(Consumer.class))).thenReturn(getDatabaseResponse);

        // Run the test
        myClassUnderTest.tryGetDatabase2();

        // Verify the results
    }

    @Test
    void testTryGetDatabase2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.getDatabase(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryGetDatabase2());
    }

    @Test
    void testTryGetDatabase2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.getDatabase(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryGetDatabase2());
    }

    @Test
    void testTryGetDatabase2_AthenaClientThrowsMetadataException() {
        // Setup
        when(mockAthenaClient.getDatabase(any(Consumer.class))).thenThrow(MetadataException.class);

        // Run the test
        assertThrows(MetadataException.class, () -> myClassUnderTest.tryGetDatabase2());
    }

    @Test
    void testTryGetDatabase2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.getDatabase(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetDatabase2());
    }

    @Test
    void testTryGetDatabase2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.getDatabase(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetDatabase2());
    }

    @Test
    void testTryGetDatabase2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.getDatabase(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryGetDatabase2());
    }

    @Test
    void testTryGetNamedQuery1() {
        // Setup
        // Configure AthenaClient.getNamedQuery(...).
        final GetNamedQueryResponse getNamedQueryResponse = GetNamedQueryResponse.builder()
                .namedQuery(NamedQuery.builder()
                        .name("name")
                        .description("description")
                        .database("database")
                        .queryString("queryString")
                        .namedQueryId("namedQueryId")
                        .workGroup("workGroup")
                        .build())
                .build();
        when(mockAthenaClient.getNamedQuery(GetNamedQueryRequest.builder()
                .namedQueryId("namedQueryId")
                .build())).thenReturn(getNamedQueryResponse);

        // Run the test
        myClassUnderTest.tryGetNamedQuery1();

        // Verify the results
    }

    @Test
    void testTryGetNamedQuery1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.getNamedQuery(GetNamedQueryRequest.builder()
                .namedQueryId("namedQueryId")
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryGetNamedQuery1());
    }

    @Test
    void testTryGetNamedQuery1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.getNamedQuery(GetNamedQueryRequest.builder()
                .namedQueryId("namedQueryId")
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryGetNamedQuery1());
    }

    @Test
    void testTryGetNamedQuery1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.getNamedQuery(GetNamedQueryRequest.builder()
                .namedQueryId("namedQueryId")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetNamedQuery1());
    }

    @Test
    void testTryGetNamedQuery1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.getNamedQuery(GetNamedQueryRequest.builder()
                .namedQueryId("namedQueryId")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetNamedQuery1());
    }

    @Test
    void testTryGetNamedQuery1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.getNamedQuery(GetNamedQueryRequest.builder()
                .namedQueryId("namedQueryId")
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryGetNamedQuery1());
    }

    @Test
    void testTryGetNamedQuery2() {
        // Setup
        // Configure AthenaClient.getNamedQuery(...).
        final GetNamedQueryResponse getNamedQueryResponse = GetNamedQueryResponse.builder()
                .namedQuery(NamedQuery.builder()
                        .name("name")
                        .description("description")
                        .database("database")
                        .queryString("queryString")
                        .namedQueryId("namedQueryId")
                        .workGroup("workGroup")
                        .build())
                .build();
        when(mockAthenaClient.getNamedQuery(any(Consumer.class))).thenReturn(getNamedQueryResponse);

        // Run the test
        myClassUnderTest.tryGetNamedQuery2();

        // Verify the results
    }

    @Test
    void testTryGetNamedQuery2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.getNamedQuery(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryGetNamedQuery2());
    }

    @Test
    void testTryGetNamedQuery2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.getNamedQuery(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryGetNamedQuery2());
    }

    @Test
    void testTryGetNamedQuery2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.getNamedQuery(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetNamedQuery2());
    }

    @Test
    void testTryGetNamedQuery2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.getNamedQuery(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetNamedQuery2());
    }

    @Test
    void testTryGetNamedQuery2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.getNamedQuery(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryGetNamedQuery2());
    }

    @Test
    void testTryGetQueryExecution1() {
        // Setup
        // Configure AthenaClient.getQueryExecution(...).
        final GetQueryExecutionResponse getQueryExecutionResponse = GetQueryExecutionResponse.builder()
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
        when(mockAthenaClient.getQueryExecution(GetQueryExecutionRequest.builder()
                .queryExecutionId("queryExecutionId")
                .build())).thenReturn(getQueryExecutionResponse);

        // Run the test
        myClassUnderTest.tryGetQueryExecution1();

        // Verify the results
    }

    @Test
    void testTryGetQueryExecution1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.getQueryExecution(GetQueryExecutionRequest.builder()
                .queryExecutionId("queryExecutionId")
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryGetQueryExecution1());
    }

    @Test
    void testTryGetQueryExecution1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.getQueryExecution(GetQueryExecutionRequest.builder()
                .queryExecutionId("queryExecutionId")
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryGetQueryExecution1());
    }

    @Test
    void testTryGetQueryExecution1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.getQueryExecution(GetQueryExecutionRequest.builder()
                .queryExecutionId("queryExecutionId")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetQueryExecution1());
    }

    @Test
    void testTryGetQueryExecution1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.getQueryExecution(GetQueryExecutionRequest.builder()
                .queryExecutionId("queryExecutionId")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetQueryExecution1());
    }

    @Test
    void testTryGetQueryExecution1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.getQueryExecution(GetQueryExecutionRequest.builder()
                .queryExecutionId("queryExecutionId")
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryGetQueryExecution1());
    }

    @Test
    void testTryGetQueryExecution2() {
        // Setup
        // Configure AthenaClient.getQueryExecution(...).
        final GetQueryExecutionResponse getQueryExecutionResponse = GetQueryExecutionResponse.builder()
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
        when(mockAthenaClient.getQueryExecution(any(Consumer.class))).thenReturn(getQueryExecutionResponse);

        // Run the test
        myClassUnderTest.tryGetQueryExecution2();

        // Verify the results
    }

    @Test
    void testTryGetQueryExecution2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.getQueryExecution(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryGetQueryExecution2());
    }

    @Test
    void testTryGetQueryExecution2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.getQueryExecution(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryGetQueryExecution2());
    }

    @Test
    void testTryGetQueryExecution2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.getQueryExecution(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetQueryExecution2());
    }

    @Test
    void testTryGetQueryExecution2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.getQueryExecution(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetQueryExecution2());
    }

    @Test
    void testTryGetQueryExecution2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.getQueryExecution(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryGetQueryExecution2());
    }

    @Test
    void testTryGetQueryResults1() {
        // Setup
        // Configure AthenaClient.getQueryResults(...).
        final GetQueryResultsResponse getQueryResultsResponse = GetQueryResultsResponse.builder()
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
        when(mockAthenaClient.getQueryResults(GetQueryResultsRequest.builder()
                .queryExecutionId("queryExecutionId")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(getQueryResultsResponse);

        // Run the test
        myClassUnderTest.tryGetQueryResults1();

        // Verify the results
    }

    @Test
    void testTryGetQueryResults1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.getQueryResults(GetQueryResultsRequest.builder()
                .queryExecutionId("queryExecutionId")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryGetQueryResults1());
    }

    @Test
    void testTryGetQueryResults1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.getQueryResults(GetQueryResultsRequest.builder()
                .queryExecutionId("queryExecutionId")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryGetQueryResults1());
    }

    @Test
    void testTryGetQueryResults1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.getQueryResults(GetQueryResultsRequest.builder()
                .queryExecutionId("queryExecutionId")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetQueryResults1());
    }

    @Test
    void testTryGetQueryResults1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.getQueryResults(GetQueryResultsRequest.builder()
                .queryExecutionId("queryExecutionId")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetQueryResults1());
    }

    @Test
    void testTryGetQueryResults1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.getQueryResults(GetQueryResultsRequest.builder()
                .queryExecutionId("queryExecutionId")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryGetQueryResults1());
    }

    @Test
    void testTryGetQueryResults2() {
        // Setup
        // Configure AthenaClient.getQueryResults(...).
        final GetQueryResultsResponse getQueryResultsResponse = GetQueryResultsResponse.builder()
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
        when(mockAthenaClient.getQueryResults(any(Consumer.class))).thenReturn(getQueryResultsResponse);

        // Run the test
        myClassUnderTest.tryGetQueryResults2();

        // Verify the results
    }

    @Test
    void testTryGetQueryResults2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.getQueryResults(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryGetQueryResults2());
    }

    @Test
    void testTryGetQueryResults2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.getQueryResults(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryGetQueryResults2());
    }

    @Test
    void testTryGetQueryResults2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.getQueryResults(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetQueryResults2());
    }

    @Test
    void testTryGetQueryResults2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.getQueryResults(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetQueryResults2());
    }

    @Test
    void testTryGetQueryResults2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.getQueryResults(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryGetQueryResults2());
    }

    @Test
    void testTryGetQueryResultsPaginator1() {
        // Setup
        // Configure AthenaClient.getQueryResultsPaginator(...).
        final GetQueryResultsIterable mockGetQueryResultsIterable = mock(GetQueryResultsIterable.class);
        when(mockAthenaClient.getQueryResultsPaginator(GetQueryResultsRequest.builder()
                .queryExecutionId("queryExecutionId")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(mockGetQueryResultsIterable);

        // Run the test
        myClassUnderTest.tryGetQueryResultsPaginator1();

        // Verify the results
    }

    @Test
    void testTryGetQueryResultsPaginator1_AthenaClientReturnsNoItems() {
        // Setup
        // Configure AthenaClient.getQueryResultsPaginator(...).
        final GetQueryResultsIterable mockGetQueryResultsIterable = mock(GetQueryResultsIterable.class);
        when(mockAthenaClient.getQueryResultsPaginator(GetQueryResultsRequest.builder()
                .queryExecutionId("queryExecutionId")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(mockGetQueryResultsIterable);

        // Run the test
        myClassUnderTest.tryGetQueryResultsPaginator1();

        // Verify the results
    }

    @Test
    void testTryGetQueryResultsPaginator1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.getQueryResultsPaginator(GetQueryResultsRequest.builder()
                .queryExecutionId("queryExecutionId")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryGetQueryResultsPaginator1());
    }

    @Test
    void testTryGetQueryResultsPaginator1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.getQueryResultsPaginator(GetQueryResultsRequest.builder()
                .queryExecutionId("queryExecutionId")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryGetQueryResultsPaginator1());
    }

    @Test
    void testTryGetQueryResultsPaginator1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.getQueryResultsPaginator(GetQueryResultsRequest.builder()
                .queryExecutionId("queryExecutionId")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetQueryResultsPaginator1());
    }

    @Test
    void testTryGetQueryResultsPaginator1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.getQueryResultsPaginator(GetQueryResultsRequest.builder()
                .queryExecutionId("queryExecutionId")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetQueryResultsPaginator1());
    }

    @Test
    void testTryGetQueryResultsPaginator1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.getQueryResultsPaginator(GetQueryResultsRequest.builder()
                .queryExecutionId("queryExecutionId")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryGetQueryResultsPaginator1());
    }

    @Test
    void testTryGetQueryResultsPaginator2() {
        // Setup
        // Configure AthenaClient.getQueryResultsPaginator(...).
        final GetQueryResultsIterable mockGetQueryResultsIterable = mock(GetQueryResultsIterable.class);
        when(mockAthenaClient.getQueryResultsPaginator(any(Consumer.class))).thenReturn(mockGetQueryResultsIterable);

        // Run the test
        myClassUnderTest.tryGetQueryResultsPaginator2();

        // Verify the results
    }

    @Test
    void testTryGetQueryResultsPaginator2_AthenaClientReturnsNoItems() {
        // Setup
        // Configure AthenaClient.getQueryResultsPaginator(...).
        final GetQueryResultsIterable mockGetQueryResultsIterable = mock(GetQueryResultsIterable.class);
        when(mockAthenaClient.getQueryResultsPaginator(any(Consumer.class))).thenReturn(mockGetQueryResultsIterable);

        // Run the test
        myClassUnderTest.tryGetQueryResultsPaginator2();

        // Verify the results
    }

    @Test
    void testTryGetQueryResultsPaginator2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.getQueryResultsPaginator(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryGetQueryResultsPaginator2());
    }

    @Test
    void testTryGetQueryResultsPaginator2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.getQueryResultsPaginator(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryGetQueryResultsPaginator2());
    }

    @Test
    void testTryGetQueryResultsPaginator2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.getQueryResultsPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetQueryResultsPaginator2());
    }

    @Test
    void testTryGetQueryResultsPaginator2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.getQueryResultsPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetQueryResultsPaginator2());
    }

    @Test
    void testTryGetQueryResultsPaginator2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.getQueryResultsPaginator(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryGetQueryResultsPaginator2());
    }

    @Test
    void testTryGetTableMetadata1() {
        // Setup
        // Configure AthenaClient.getTableMetadata(...).
        final GetTableMetadataResponse getTableMetadataResponse = GetTableMetadataResponse.builder()
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
                        .parameters(new HashMap<>())
                        .build())
                .build();
        when(mockAthenaClient.getTableMetadata(GetTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .tableName("tableName")
                .build())).thenReturn(getTableMetadataResponse);

        // Run the test
        myClassUnderTest.tryGetTableMetadata1();

        // Verify the results
    }

    @Test
    void testTryGetTableMetadata1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.getTableMetadata(GetTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .tableName("tableName")
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryGetTableMetadata1());
    }

    @Test
    void testTryGetTableMetadata1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.getTableMetadata(GetTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .tableName("tableName")
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryGetTableMetadata1());
    }

    @Test
    void testTryGetTableMetadata1_AthenaClientThrowsMetadataException() {
        // Setup
        when(mockAthenaClient.getTableMetadata(GetTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .tableName("tableName")
                .build())).thenThrow(MetadataException.class);

        // Run the test
        assertThrows(MetadataException.class, () -> myClassUnderTest.tryGetTableMetadata1());
    }

    @Test
    void testTryGetTableMetadata1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.getTableMetadata(GetTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .tableName("tableName")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetTableMetadata1());
    }

    @Test
    void testTryGetTableMetadata1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.getTableMetadata(GetTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .tableName("tableName")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetTableMetadata1());
    }

    @Test
    void testTryGetTableMetadata1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.getTableMetadata(GetTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .tableName("tableName")
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryGetTableMetadata1());
    }

    @Test
    void testTryGetTableMetadata2() {
        // Setup
        // Configure AthenaClient.getTableMetadata(...).
        final GetTableMetadataResponse getTableMetadataResponse = GetTableMetadataResponse.builder()
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
                        .parameters(new HashMap<>())
                        .build())
                .build();
        when(mockAthenaClient.getTableMetadata(any(Consumer.class))).thenReturn(getTableMetadataResponse);

        // Run the test
        myClassUnderTest.tryGetTableMetadata2();

        // Verify the results
    }

    @Test
    void testTryGetTableMetadata2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.getTableMetadata(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryGetTableMetadata2());
    }

    @Test
    void testTryGetTableMetadata2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.getTableMetadata(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryGetTableMetadata2());
    }

    @Test
    void testTryGetTableMetadata2_AthenaClientThrowsMetadataException() {
        // Setup
        when(mockAthenaClient.getTableMetadata(any(Consumer.class))).thenThrow(MetadataException.class);

        // Run the test
        assertThrows(MetadataException.class, () -> myClassUnderTest.tryGetTableMetadata2());
    }

    @Test
    void testTryGetTableMetadata2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.getTableMetadata(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetTableMetadata2());
    }

    @Test
    void testTryGetTableMetadata2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.getTableMetadata(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetTableMetadata2());
    }

    @Test
    void testTryGetTableMetadata2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.getTableMetadata(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryGetTableMetadata2());
    }

    @Test
    void testTryGetWorkGroup1() {
        // Setup
        // Configure AthenaClient.getWorkGroup(...).
        final GetWorkGroupResponse getWorkGroupResponse = GetWorkGroupResponse.builder()
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
        when(mockAthenaClient.getWorkGroup(GetWorkGroupRequest.builder()
                .workGroup("workGroup")
                .build())).thenReturn(getWorkGroupResponse);

        // Run the test
        myClassUnderTest.tryGetWorkGroup1();

        // Verify the results
    }

    @Test
    void testTryGetWorkGroup1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.getWorkGroup(GetWorkGroupRequest.builder()
                .workGroup("workGroup")
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryGetWorkGroup1());
    }

    @Test
    void testTryGetWorkGroup1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.getWorkGroup(GetWorkGroupRequest.builder()
                .workGroup("workGroup")
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryGetWorkGroup1());
    }

    @Test
    void testTryGetWorkGroup1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.getWorkGroup(GetWorkGroupRequest.builder()
                .workGroup("workGroup")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetWorkGroup1());
    }

    @Test
    void testTryGetWorkGroup1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.getWorkGroup(GetWorkGroupRequest.builder()
                .workGroup("workGroup")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetWorkGroup1());
    }

    @Test
    void testTryGetWorkGroup1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.getWorkGroup(GetWorkGroupRequest.builder()
                .workGroup("workGroup")
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryGetWorkGroup1());
    }

    @Test
    void testTryGetWorkGroup2() {
        // Setup
        // Configure AthenaClient.getWorkGroup(...).
        final GetWorkGroupResponse getWorkGroupResponse = GetWorkGroupResponse.builder()
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
        when(mockAthenaClient.getWorkGroup(any(Consumer.class))).thenReturn(getWorkGroupResponse);

        // Run the test
        myClassUnderTest.tryGetWorkGroup2();

        // Verify the results
    }

    @Test
    void testTryGetWorkGroup2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.getWorkGroup(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryGetWorkGroup2());
    }

    @Test
    void testTryGetWorkGroup2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.getWorkGroup(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryGetWorkGroup2());
    }

    @Test
    void testTryGetWorkGroup2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.getWorkGroup(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetWorkGroup2());
    }

    @Test
    void testTryGetWorkGroup2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.getWorkGroup(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetWorkGroup2());
    }

    @Test
    void testTryGetWorkGroup2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.getWorkGroup(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryGetWorkGroup2());
    }

    @Test
    void testTryListDataCatalogs1() {
        // Setup
        // Configure AthenaClient.listDataCatalogs(...).
        final ListDataCatalogsResponse listDataCatalogsResponse = ListDataCatalogsResponse.builder()
                .dataCatalogsSummary(DataCatalogSummary.builder()
                        .catalogName("catalogName")
                        .type(DataCatalogType.LAMBDA)
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockAthenaClient.listDataCatalogs(ListDataCatalogsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(listDataCatalogsResponse);

        // Run the test
        myClassUnderTest.tryListDataCatalogs1();

        // Verify the results
    }

    @Test
    void testTryListDataCatalogs1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listDataCatalogs(ListDataCatalogsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListDataCatalogs1());
    }

    @Test
    void testTryListDataCatalogs1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listDataCatalogs(ListDataCatalogsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListDataCatalogs1());
    }

    @Test
    void testTryListDataCatalogs1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listDataCatalogs(ListDataCatalogsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListDataCatalogs1());
    }

    @Test
    void testTryListDataCatalogs1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listDataCatalogs(ListDataCatalogsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListDataCatalogs1());
    }

    @Test
    void testTryListDataCatalogs1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listDataCatalogs(ListDataCatalogsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListDataCatalogs1());
    }

    @Test
    void testTryListDataCatalogs2() {
        // Setup
        // Configure AthenaClient.listDataCatalogs(...).
        final ListDataCatalogsResponse listDataCatalogsResponse = ListDataCatalogsResponse.builder()
                .dataCatalogsSummary(DataCatalogSummary.builder()
                        .catalogName("catalogName")
                        .type(DataCatalogType.LAMBDA)
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockAthenaClient.listDataCatalogs(any(Consumer.class))).thenReturn(listDataCatalogsResponse);

        // Run the test
        myClassUnderTest.tryListDataCatalogs2();

        // Verify the results
    }

    @Test
    void testTryListDataCatalogs2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listDataCatalogs(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListDataCatalogs2());
    }

    @Test
    void testTryListDataCatalogs2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listDataCatalogs(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListDataCatalogs2());
    }

    @Test
    void testTryListDataCatalogs2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listDataCatalogs(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListDataCatalogs2());
    }

    @Test
    void testTryListDataCatalogs2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listDataCatalogs(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListDataCatalogs2());
    }

    @Test
    void testTryListDataCatalogs2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listDataCatalogs(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListDataCatalogs2());
    }

    @Test
    void testTryListDataCatalogsPaginator1() {
        // Setup
        // Configure AthenaClient.listDataCatalogsPaginator(...).
        final ListDataCatalogsIterable mockListDataCatalogsIterable = mock(ListDataCatalogsIterable.class);
        when(mockAthenaClient.listDataCatalogsPaginator(ListDataCatalogsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(mockListDataCatalogsIterable);

        // Run the test
        myClassUnderTest.tryListDataCatalogsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListDataCatalogsPaginator1_AthenaClientReturnsNoItems() {
        // Setup
        // Configure AthenaClient.listDataCatalogsPaginator(...).
        final ListDataCatalogsIterable mockListDataCatalogsIterable = mock(ListDataCatalogsIterable.class);
        when(mockAthenaClient.listDataCatalogsPaginator(ListDataCatalogsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(mockListDataCatalogsIterable);

        // Run the test
        myClassUnderTest.tryListDataCatalogsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListDataCatalogsPaginator1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listDataCatalogsPaginator(ListDataCatalogsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListDataCatalogsPaginator1());
    }

    @Test
    void testTryListDataCatalogsPaginator1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listDataCatalogsPaginator(ListDataCatalogsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListDataCatalogsPaginator1());
    }

    @Test
    void testTryListDataCatalogsPaginator1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listDataCatalogsPaginator(ListDataCatalogsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListDataCatalogsPaginator1());
    }

    @Test
    void testTryListDataCatalogsPaginator1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listDataCatalogsPaginator(ListDataCatalogsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListDataCatalogsPaginator1());
    }

    @Test
    void testTryListDataCatalogsPaginator1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listDataCatalogsPaginator(ListDataCatalogsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListDataCatalogsPaginator1());
    }

    @Test
    void testTryListDataCatalogsPaginator2() {
        // Setup
        // Configure AthenaClient.listDataCatalogsPaginator(...).
        final ListDataCatalogsIterable mockListDataCatalogsIterable = mock(ListDataCatalogsIterable.class);
        when(mockAthenaClient.listDataCatalogsPaginator(any(Consumer.class))).thenReturn(mockListDataCatalogsIterable);

        // Run the test
        myClassUnderTest.tryListDataCatalogsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListDataCatalogsPaginator2_AthenaClientReturnsNoItems() {
        // Setup
        // Configure AthenaClient.listDataCatalogsPaginator(...).
        final ListDataCatalogsIterable mockListDataCatalogsIterable = mock(ListDataCatalogsIterable.class);
        when(mockAthenaClient.listDataCatalogsPaginator(any(Consumer.class))).thenReturn(mockListDataCatalogsIterable);

        // Run the test
        myClassUnderTest.tryListDataCatalogsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListDataCatalogsPaginator2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listDataCatalogsPaginator(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListDataCatalogsPaginator2());
    }

    @Test
    void testTryListDataCatalogsPaginator2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listDataCatalogsPaginator(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListDataCatalogsPaginator2());
    }

    @Test
    void testTryListDataCatalogsPaginator2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listDataCatalogsPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListDataCatalogsPaginator2());
    }

    @Test
    void testTryListDataCatalogsPaginator2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listDataCatalogsPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListDataCatalogsPaginator2());
    }

    @Test
    void testTryListDataCatalogsPaginator2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listDataCatalogsPaginator(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListDataCatalogsPaginator2());
    }

    @Test
    void testTryListDatabases1() {
        // Setup
        // Configure AthenaClient.listDatabases(...).
        final ListDatabasesResponse listDatabasesResponse = ListDatabasesResponse.builder()
                .databaseList(Database.builder()
                        .name("name")
                        .description("description")
                        .parameters(new HashMap<>())
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockAthenaClient.listDatabases(ListDatabasesRequest.builder()
                .catalogName("catalogName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(listDatabasesResponse);

        // Run the test
        myClassUnderTest.tryListDatabases1();

        // Verify the results
    }

    @Test
    void testTryListDatabases1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listDatabases(ListDatabasesRequest.builder()
                .catalogName("catalogName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListDatabases1());
    }

    @Test
    void testTryListDatabases1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listDatabases(ListDatabasesRequest.builder()
                .catalogName("catalogName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListDatabases1());
    }

    @Test
    void testTryListDatabases1_AthenaClientThrowsMetadataException() {
        // Setup
        when(mockAthenaClient.listDatabases(ListDatabasesRequest.builder()
                .catalogName("catalogName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(MetadataException.class);

        // Run the test
        assertThrows(MetadataException.class, () -> myClassUnderTest.tryListDatabases1());
    }

    @Test
    void testTryListDatabases1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listDatabases(ListDatabasesRequest.builder()
                .catalogName("catalogName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListDatabases1());
    }

    @Test
    void testTryListDatabases1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listDatabases(ListDatabasesRequest.builder()
                .catalogName("catalogName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListDatabases1());
    }

    @Test
    void testTryListDatabases1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listDatabases(ListDatabasesRequest.builder()
                .catalogName("catalogName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListDatabases1());
    }

    @Test
    void testTryListDatabases2() {
        // Setup
        // Configure AthenaClient.listDatabases(...).
        final ListDatabasesResponse listDatabasesResponse = ListDatabasesResponse.builder()
                .databaseList(Database.builder()
                        .name("name")
                        .description("description")
                        .parameters(new HashMap<>())
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockAthenaClient.listDatabases(any(Consumer.class))).thenReturn(listDatabasesResponse);

        // Run the test
        myClassUnderTest.tryListDatabases2();

        // Verify the results
    }

    @Test
    void testTryListDatabases2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listDatabases(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListDatabases2());
    }

    @Test
    void testTryListDatabases2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listDatabases(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListDatabases2());
    }

    @Test
    void testTryListDatabases2_AthenaClientThrowsMetadataException() {
        // Setup
        when(mockAthenaClient.listDatabases(any(Consumer.class))).thenThrow(MetadataException.class);

        // Run the test
        assertThrows(MetadataException.class, () -> myClassUnderTest.tryListDatabases2());
    }

    @Test
    void testTryListDatabases2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listDatabases(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListDatabases2());
    }

    @Test
    void testTryListDatabases2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listDatabases(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListDatabases2());
    }

    @Test
    void testTryListDatabases2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listDatabases(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListDatabases2());
    }

    @Test
    void testTryListDatabasesPaginator1() {
        // Setup
        // Configure AthenaClient.listDatabasesPaginator(...).
        final ListDatabasesIterable mockListDatabasesIterable = mock(ListDatabasesIterable.class);
        when(mockAthenaClient.listDatabasesPaginator(ListDatabasesRequest.builder()
                .catalogName("catalogName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(mockListDatabasesIterable);

        // Run the test
        myClassUnderTest.tryListDatabasesPaginator1();

        // Verify the results
    }

    @Test
    void testTryListDatabasesPaginator1_AthenaClientReturnsNoItems() {
        // Setup
        // Configure AthenaClient.listDatabasesPaginator(...).
        final ListDatabasesIterable mockListDatabasesIterable = mock(ListDatabasesIterable.class);
        when(mockAthenaClient.listDatabasesPaginator(ListDatabasesRequest.builder()
                .catalogName("catalogName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(mockListDatabasesIterable);

        // Run the test
        myClassUnderTest.tryListDatabasesPaginator1();

        // Verify the results
    }

    @Test
    void testTryListDatabasesPaginator1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listDatabasesPaginator(ListDatabasesRequest.builder()
                .catalogName("catalogName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListDatabasesPaginator1());
    }

    @Test
    void testTryListDatabasesPaginator1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listDatabasesPaginator(ListDatabasesRequest.builder()
                .catalogName("catalogName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListDatabasesPaginator1());
    }

    @Test
    void testTryListDatabasesPaginator1_AthenaClientThrowsMetadataException() {
        // Setup
        when(mockAthenaClient.listDatabasesPaginator(ListDatabasesRequest.builder()
                .catalogName("catalogName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(MetadataException.class);

        // Run the test
        assertThrows(MetadataException.class, () -> myClassUnderTest.tryListDatabasesPaginator1());
    }

    @Test
    void testTryListDatabasesPaginator1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listDatabasesPaginator(ListDatabasesRequest.builder()
                .catalogName("catalogName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListDatabasesPaginator1());
    }

    @Test
    void testTryListDatabasesPaginator1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listDatabasesPaginator(ListDatabasesRequest.builder()
                .catalogName("catalogName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListDatabasesPaginator1());
    }

    @Test
    void testTryListDatabasesPaginator1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listDatabasesPaginator(ListDatabasesRequest.builder()
                .catalogName("catalogName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListDatabasesPaginator1());
    }

    @Test
    void testTryListDatabasesPaginator2() {
        // Setup
        // Configure AthenaClient.listDatabasesPaginator(...).
        final ListDatabasesIterable mockListDatabasesIterable = mock(ListDatabasesIterable.class);
        when(mockAthenaClient.listDatabasesPaginator(any(Consumer.class))).thenReturn(mockListDatabasesIterable);

        // Run the test
        myClassUnderTest.tryListDatabasesPaginator2();

        // Verify the results
    }

    @Test
    void testTryListDatabasesPaginator2_AthenaClientReturnsNoItems() {
        // Setup
        // Configure AthenaClient.listDatabasesPaginator(...).
        final ListDatabasesIterable mockListDatabasesIterable = mock(ListDatabasesIterable.class);
        when(mockAthenaClient.listDatabasesPaginator(any(Consumer.class))).thenReturn(mockListDatabasesIterable);

        // Run the test
        myClassUnderTest.tryListDatabasesPaginator2();

        // Verify the results
    }

    @Test
    void testTryListDatabasesPaginator2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listDatabasesPaginator(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListDatabasesPaginator2());
    }

    @Test
    void testTryListDatabasesPaginator2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listDatabasesPaginator(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListDatabasesPaginator2());
    }

    @Test
    void testTryListDatabasesPaginator2_AthenaClientThrowsMetadataException() {
        // Setup
        when(mockAthenaClient.listDatabasesPaginator(any(Consumer.class))).thenThrow(MetadataException.class);

        // Run the test
        assertThrows(MetadataException.class, () -> myClassUnderTest.tryListDatabasesPaginator2());
    }

    @Test
    void testTryListDatabasesPaginator2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listDatabasesPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListDatabasesPaginator2());
    }

    @Test
    void testTryListDatabasesPaginator2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listDatabasesPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListDatabasesPaginator2());
    }

    @Test
    void testTryListDatabasesPaginator2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listDatabasesPaginator(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListDatabasesPaginator2());
    }

    @Test
    void testTryListNamedQueries1() {
        // Setup
        // Configure AthenaClient.listNamedQueries(...).
        final ListNamedQueriesResponse listNamedQueriesResponse = ListNamedQueriesResponse.builder()
                .namedQueryIds("namedQueryIds")
                .nextToken("nextToken")
                .build();
        when(mockAthenaClient.listNamedQueries()).thenReturn(listNamedQueriesResponse);

        // Run the test
        myClassUnderTest.tryListNamedQueries1();

        // Verify the results
    }

    @Test
    void testTryListNamedQueries1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listNamedQueries()).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListNamedQueries1());
    }

    @Test
    void testTryListNamedQueries1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listNamedQueries()).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListNamedQueries1());
    }

    @Test
    void testTryListNamedQueries1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listNamedQueries()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListNamedQueries1());
    }

    @Test
    void testTryListNamedQueries1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listNamedQueries()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListNamedQueries1());
    }

    @Test
    void testTryListNamedQueries1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listNamedQueries()).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListNamedQueries1());
    }

    @Test
    void testTryListNamedQueries2() {
        // Setup
        // Configure AthenaClient.listNamedQueries(...).
        final ListNamedQueriesResponse listNamedQueriesResponse = ListNamedQueriesResponse.builder()
                .namedQueryIds("namedQueryIds")
                .nextToken("nextToken")
                .build();
        when(mockAthenaClient.listNamedQueries(ListNamedQueriesRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenReturn(listNamedQueriesResponse);

        // Run the test
        myClassUnderTest.tryListNamedQueries2();

        // Verify the results
    }

    @Test
    void testTryListNamedQueries2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listNamedQueries(ListNamedQueriesRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListNamedQueries2());
    }

    @Test
    void testTryListNamedQueries2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listNamedQueries(ListNamedQueriesRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListNamedQueries2());
    }

    @Test
    void testTryListNamedQueries2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listNamedQueries(ListNamedQueriesRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListNamedQueries2());
    }

    @Test
    void testTryListNamedQueries2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listNamedQueries(ListNamedQueriesRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListNamedQueries2());
    }

    @Test
    void testTryListNamedQueries2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listNamedQueries(ListNamedQueriesRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListNamedQueries2());
    }

    @Test
    void testTryListNamedQueries3() {
        // Setup
        // Configure AthenaClient.listNamedQueries(...).
        final ListNamedQueriesResponse listNamedQueriesResponse = ListNamedQueriesResponse.builder()
                .namedQueryIds("namedQueryIds")
                .nextToken("nextToken")
                .build();
        when(mockAthenaClient.listNamedQueries(any(Consumer.class))).thenReturn(listNamedQueriesResponse);

        // Run the test
        myClassUnderTest.tryListNamedQueries3();

        // Verify the results
    }

    @Test
    void testTryListNamedQueries3_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listNamedQueries(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListNamedQueries3());
    }

    @Test
    void testTryListNamedQueries3_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listNamedQueries(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListNamedQueries3());
    }

    @Test
    void testTryListNamedQueries3_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listNamedQueries(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListNamedQueries3());
    }

    @Test
    void testTryListNamedQueries3_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listNamedQueries(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListNamedQueries3());
    }

    @Test
    void testTryListNamedQueries3_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listNamedQueries(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListNamedQueries3());
    }

    @Test
    void testTryListNamedQueriesPaginator1() {
        // Setup
        // Configure AthenaClient.listNamedQueriesPaginator(...).
        final ListNamedQueriesIterable mockListNamedQueriesIterable = mock(ListNamedQueriesIterable.class);
        when(mockAthenaClient.listNamedQueriesPaginator()).thenReturn(mockListNamedQueriesIterable);

        // Run the test
        myClassUnderTest.tryListNamedQueriesPaginator1();

        // Verify the results
    }

    @Test
    void testTryListNamedQueriesPaginator1_AthenaClientReturnsNoItems() {
        // Setup
        // Configure AthenaClient.listNamedQueriesPaginator(...).
        final ListNamedQueriesIterable mockListNamedQueriesIterable = mock(ListNamedQueriesIterable.class);
        when(mockAthenaClient.listNamedQueriesPaginator()).thenReturn(mockListNamedQueriesIterable);

        // Run the test
        myClassUnderTest.tryListNamedQueriesPaginator1();

        // Verify the results
    }

    @Test
    void testTryListNamedQueriesPaginator1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listNamedQueriesPaginator()).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListNamedQueriesPaginator1());
    }

    @Test
    void testTryListNamedQueriesPaginator1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listNamedQueriesPaginator()).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListNamedQueriesPaginator1());
    }

    @Test
    void testTryListNamedQueriesPaginator1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listNamedQueriesPaginator()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListNamedQueriesPaginator1());
    }

    @Test
    void testTryListNamedQueriesPaginator1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listNamedQueriesPaginator()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListNamedQueriesPaginator1());
    }

    @Test
    void testTryListNamedQueriesPaginator1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listNamedQueriesPaginator()).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListNamedQueriesPaginator1());
    }

    @Test
    void testTryListNamedQueriesPaginator2() {
        // Setup
        // Configure AthenaClient.listNamedQueriesPaginator(...).
        final ListNamedQueriesIterable mockListNamedQueriesIterable = mock(ListNamedQueriesIterable.class);
        when(mockAthenaClient.listNamedQueriesPaginator(ListNamedQueriesRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenReturn(mockListNamedQueriesIterable);

        // Run the test
        myClassUnderTest.tryListNamedQueriesPaginator2();

        // Verify the results
    }

    @Test
    void testTryListNamedQueriesPaginator2_AthenaClientReturnsNoItems() {
        // Setup
        // Configure AthenaClient.listNamedQueriesPaginator(...).
        final ListNamedQueriesIterable mockListNamedQueriesIterable = mock(ListNamedQueriesIterable.class);
        when(mockAthenaClient.listNamedQueriesPaginator(ListNamedQueriesRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenReturn(mockListNamedQueriesIterable);

        // Run the test
        myClassUnderTest.tryListNamedQueriesPaginator2();

        // Verify the results
    }

    @Test
    void testTryListNamedQueriesPaginator2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listNamedQueriesPaginator(ListNamedQueriesRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListNamedQueriesPaginator2());
    }

    @Test
    void testTryListNamedQueriesPaginator2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listNamedQueriesPaginator(ListNamedQueriesRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListNamedQueriesPaginator2());
    }

    @Test
    void testTryListNamedQueriesPaginator2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listNamedQueriesPaginator(ListNamedQueriesRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListNamedQueriesPaginator2());
    }

    @Test
    void testTryListNamedQueriesPaginator2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listNamedQueriesPaginator(ListNamedQueriesRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListNamedQueriesPaginator2());
    }

    @Test
    void testTryListNamedQueriesPaginator2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listNamedQueriesPaginator(ListNamedQueriesRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListNamedQueriesPaginator2());
    }

    @Test
    void testTryListNamedQueriesPaginator3() {
        // Setup
        // Configure AthenaClient.listNamedQueriesPaginator(...).
        final ListNamedQueriesIterable mockListNamedQueriesIterable = mock(ListNamedQueriesIterable.class);
        when(mockAthenaClient.listNamedQueriesPaginator(any(Consumer.class))).thenReturn(mockListNamedQueriesIterable);

        // Run the test
        myClassUnderTest.tryListNamedQueriesPaginator3();

        // Verify the results
    }

    @Test
    void testTryListNamedQueriesPaginator3_AthenaClientReturnsNoItems() {
        // Setup
        // Configure AthenaClient.listNamedQueriesPaginator(...).
        final ListNamedQueriesIterable mockListNamedQueriesIterable = mock(ListNamedQueriesIterable.class);
        when(mockAthenaClient.listNamedQueriesPaginator(any(Consumer.class))).thenReturn(mockListNamedQueriesIterable);

        // Run the test
        myClassUnderTest.tryListNamedQueriesPaginator3();

        // Verify the results
    }

    @Test
    void testTryListNamedQueriesPaginator3_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listNamedQueriesPaginator(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListNamedQueriesPaginator3());
    }

    @Test
    void testTryListNamedQueriesPaginator3_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listNamedQueriesPaginator(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListNamedQueriesPaginator3());
    }

    @Test
    void testTryListNamedQueriesPaginator3_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listNamedQueriesPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListNamedQueriesPaginator3());
    }

    @Test
    void testTryListNamedQueriesPaginator3_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listNamedQueriesPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListNamedQueriesPaginator3());
    }

    @Test
    void testTryListNamedQueriesPaginator3_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listNamedQueriesPaginator(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListNamedQueriesPaginator3());
    }

    @Test
    void testTryListQueryExecutions1() {
        // Setup
        // Configure AthenaClient.listQueryExecutions(...).
        final ListQueryExecutionsResponse listQueryExecutionsResponse = ListQueryExecutionsResponse.builder()
                .queryExecutionIds("queryExecutionIds")
                .nextToken("nextToken")
                .build();
        when(mockAthenaClient.listQueryExecutions()).thenReturn(listQueryExecutionsResponse);

        // Run the test
        myClassUnderTest.tryListQueryExecutions1();

        // Verify the results
    }

    @Test
    void testTryListQueryExecutions1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listQueryExecutions()).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListQueryExecutions1());
    }

    @Test
    void testTryListQueryExecutions1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listQueryExecutions()).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListQueryExecutions1());
    }

    @Test
    void testTryListQueryExecutions1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listQueryExecutions()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListQueryExecutions1());
    }

    @Test
    void testTryListQueryExecutions1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listQueryExecutions()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListQueryExecutions1());
    }

    @Test
    void testTryListQueryExecutions1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listQueryExecutions()).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListQueryExecutions1());
    }

    @Test
    void testTryListQueryExecutions2() {
        // Setup
        // Configure AthenaClient.listQueryExecutions(...).
        final ListQueryExecutionsResponse listQueryExecutionsResponse = ListQueryExecutionsResponse.builder()
                .queryExecutionIds("queryExecutionIds")
                .nextToken("nextToken")
                .build();
        when(mockAthenaClient.listQueryExecutions(ListQueryExecutionsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenReturn(listQueryExecutionsResponse);

        // Run the test
        myClassUnderTest.tryListQueryExecutions2();

        // Verify the results
    }

    @Test
    void testTryListQueryExecutions2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listQueryExecutions(ListQueryExecutionsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListQueryExecutions2());
    }

    @Test
    void testTryListQueryExecutions2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listQueryExecutions(ListQueryExecutionsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListQueryExecutions2());
    }

    @Test
    void testTryListQueryExecutions2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listQueryExecutions(ListQueryExecutionsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListQueryExecutions2());
    }

    @Test
    void testTryListQueryExecutions2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listQueryExecutions(ListQueryExecutionsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListQueryExecutions2());
    }

    @Test
    void testTryListQueryExecutions2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listQueryExecutions(ListQueryExecutionsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListQueryExecutions2());
    }

    @Test
    void testTryListQueryExecutions3() {
        // Setup
        // Configure AthenaClient.listQueryExecutions(...).
        final ListQueryExecutionsResponse listQueryExecutionsResponse = ListQueryExecutionsResponse.builder()
                .queryExecutionIds("queryExecutionIds")
                .nextToken("nextToken")
                .build();
        when(mockAthenaClient.listQueryExecutions(any(Consumer.class))).thenReturn(listQueryExecutionsResponse);

        // Run the test
        myClassUnderTest.tryListQueryExecutions3();

        // Verify the results
    }

    @Test
    void testTryListQueryExecutions3_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listQueryExecutions(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListQueryExecutions3());
    }

    @Test
    void testTryListQueryExecutions3_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listQueryExecutions(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListQueryExecutions3());
    }

    @Test
    void testTryListQueryExecutions3_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listQueryExecutions(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListQueryExecutions3());
    }

    @Test
    void testTryListQueryExecutions3_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listQueryExecutions(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListQueryExecutions3());
    }

    @Test
    void testTryListQueryExecutions3_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listQueryExecutions(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListQueryExecutions3());
    }

    @Test
    void testTryListQueryExecutionsPaginator1() {
        // Setup
        // Configure AthenaClient.listQueryExecutionsPaginator(...).
        final ListQueryExecutionsIterable mockListQueryExecutionsIterable = mock(ListQueryExecutionsIterable.class);
        when(mockAthenaClient.listQueryExecutionsPaginator()).thenReturn(mockListQueryExecutionsIterable);

        // Run the test
        myClassUnderTest.tryListQueryExecutionsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListQueryExecutionsPaginator1_AthenaClientReturnsNoItems() {
        // Setup
        // Configure AthenaClient.listQueryExecutionsPaginator(...).
        final ListQueryExecutionsIterable mockListQueryExecutionsIterable = mock(ListQueryExecutionsIterable.class);
        when(mockAthenaClient.listQueryExecutionsPaginator()).thenReturn(mockListQueryExecutionsIterable);

        // Run the test
        myClassUnderTest.tryListQueryExecutionsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListQueryExecutionsPaginator1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listQueryExecutionsPaginator()).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListQueryExecutionsPaginator1());
    }

    @Test
    void testTryListQueryExecutionsPaginator1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listQueryExecutionsPaginator()).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListQueryExecutionsPaginator1());
    }

    @Test
    void testTryListQueryExecutionsPaginator1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listQueryExecutionsPaginator()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListQueryExecutionsPaginator1());
    }

    @Test
    void testTryListQueryExecutionsPaginator1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listQueryExecutionsPaginator()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListQueryExecutionsPaginator1());
    }

    @Test
    void testTryListQueryExecutionsPaginator1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listQueryExecutionsPaginator()).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListQueryExecutionsPaginator1());
    }

    @Test
    void testTryListQueryExecutionsPaginator2() {
        // Setup
        // Configure AthenaClient.listQueryExecutionsPaginator(...).
        final ListQueryExecutionsIterable mockListQueryExecutionsIterable = mock(ListQueryExecutionsIterable.class);
        when(mockAthenaClient.listQueryExecutionsPaginator(ListQueryExecutionsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenReturn(mockListQueryExecutionsIterable);

        // Run the test
        myClassUnderTest.tryListQueryExecutionsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListQueryExecutionsPaginator2_AthenaClientReturnsNoItems() {
        // Setup
        // Configure AthenaClient.listQueryExecutionsPaginator(...).
        final ListQueryExecutionsIterable mockListQueryExecutionsIterable = mock(ListQueryExecutionsIterable.class);
        when(mockAthenaClient.listQueryExecutionsPaginator(ListQueryExecutionsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenReturn(mockListQueryExecutionsIterable);

        // Run the test
        myClassUnderTest.tryListQueryExecutionsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListQueryExecutionsPaginator2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listQueryExecutionsPaginator(ListQueryExecutionsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListQueryExecutionsPaginator2());
    }

    @Test
    void testTryListQueryExecutionsPaginator2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listQueryExecutionsPaginator(ListQueryExecutionsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListQueryExecutionsPaginator2());
    }

    @Test
    void testTryListQueryExecutionsPaginator2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listQueryExecutionsPaginator(ListQueryExecutionsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListQueryExecutionsPaginator2());
    }

    @Test
    void testTryListQueryExecutionsPaginator2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listQueryExecutionsPaginator(ListQueryExecutionsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListQueryExecutionsPaginator2());
    }

    @Test
    void testTryListQueryExecutionsPaginator2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listQueryExecutionsPaginator(ListQueryExecutionsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .workGroup("workGroup")
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListQueryExecutionsPaginator2());
    }

    @Test
    void testTryListQueryExecutionsPaginator3() {
        // Setup
        // Configure AthenaClient.listQueryExecutionsPaginator(...).
        final ListQueryExecutionsIterable mockListQueryExecutionsIterable = mock(ListQueryExecutionsIterable.class);
        when(mockAthenaClient.listQueryExecutionsPaginator(any(Consumer.class)))
                .thenReturn(mockListQueryExecutionsIterable);

        // Run the test
        myClassUnderTest.tryListQueryExecutionsPaginator3();

        // Verify the results
    }

    @Test
    void testTryListQueryExecutionsPaginator3_AthenaClientReturnsNoItems() {
        // Setup
        // Configure AthenaClient.listQueryExecutionsPaginator(...).
        final ListQueryExecutionsIterable mockListQueryExecutionsIterable = mock(ListQueryExecutionsIterable.class);
        when(mockAthenaClient.listQueryExecutionsPaginator(any(Consumer.class)))
                .thenReturn(mockListQueryExecutionsIterable);

        // Run the test
        myClassUnderTest.tryListQueryExecutionsPaginator3();

        // Verify the results
    }

    @Test
    void testTryListQueryExecutionsPaginator3_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listQueryExecutionsPaginator(any(Consumer.class)))
                .thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListQueryExecutionsPaginator3());
    }

    @Test
    void testTryListQueryExecutionsPaginator3_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listQueryExecutionsPaginator(any(Consumer.class)))
                .thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListQueryExecutionsPaginator3());
    }

    @Test
    void testTryListQueryExecutionsPaginator3_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listQueryExecutionsPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListQueryExecutionsPaginator3());
    }

    @Test
    void testTryListQueryExecutionsPaginator3_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listQueryExecutionsPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListQueryExecutionsPaginator3());
    }

    @Test
    void testTryListQueryExecutionsPaginator3_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listQueryExecutionsPaginator(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListQueryExecutionsPaginator3());
    }

    @Test
    void testTryListTableMetadata1() {
        // Setup
        // Configure AthenaClient.listTableMetadata(...).
        final ListTableMetadataResponse listTableMetadataResponse = ListTableMetadataResponse.builder()
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
                        .parameters(new HashMap<>())
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockAthenaClient.listTableMetadata(ListTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .expression("expression")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(listTableMetadataResponse);

        // Run the test
        myClassUnderTest.tryListTableMetadata1();

        // Verify the results
    }

    @Test
    void testTryListTableMetadata1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listTableMetadata(ListTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .expression("expression")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListTableMetadata1());
    }

    @Test
    void testTryListTableMetadata1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listTableMetadata(ListTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .expression("expression")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListTableMetadata1());
    }

    @Test
    void testTryListTableMetadata1_AthenaClientThrowsMetadataException() {
        // Setup
        when(mockAthenaClient.listTableMetadata(ListTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .expression("expression")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(MetadataException.class);

        // Run the test
        assertThrows(MetadataException.class, () -> myClassUnderTest.tryListTableMetadata1());
    }

    @Test
    void testTryListTableMetadata1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listTableMetadata(ListTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .expression("expression")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTableMetadata1());
    }

    @Test
    void testTryListTableMetadata1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listTableMetadata(ListTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .expression("expression")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTableMetadata1());
    }

    @Test
    void testTryListTableMetadata1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listTableMetadata(ListTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .expression("expression")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListTableMetadata1());
    }

    @Test
    void testTryListTableMetadata2() {
        // Setup
        // Configure AthenaClient.listTableMetadata(...).
        final ListTableMetadataResponse listTableMetadataResponse = ListTableMetadataResponse.builder()
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
                        .parameters(new HashMap<>())
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockAthenaClient.listTableMetadata(any(Consumer.class))).thenReturn(listTableMetadataResponse);

        // Run the test
        myClassUnderTest.tryListTableMetadata2();

        // Verify the results
    }

    @Test
    void testTryListTableMetadata2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listTableMetadata(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListTableMetadata2());
    }

    @Test
    void testTryListTableMetadata2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listTableMetadata(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListTableMetadata2());
    }

    @Test
    void testTryListTableMetadata2_AthenaClientThrowsMetadataException() {
        // Setup
        when(mockAthenaClient.listTableMetadata(any(Consumer.class))).thenThrow(MetadataException.class);

        // Run the test
        assertThrows(MetadataException.class, () -> myClassUnderTest.tryListTableMetadata2());
    }

    @Test
    void testTryListTableMetadata2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listTableMetadata(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTableMetadata2());
    }

    @Test
    void testTryListTableMetadata2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listTableMetadata(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTableMetadata2());
    }

    @Test
    void testTryListTableMetadata2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listTableMetadata(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListTableMetadata2());
    }

    @Test
    void testTryListTableMetadataPaginator1() {
        // Setup
        // Configure AthenaClient.listTableMetadataPaginator(...).
        final ListTableMetadataIterable mockListTableMetadataIterable = mock(ListTableMetadataIterable.class);
        when(mockAthenaClient.listTableMetadataPaginator(ListTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .expression("expression")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(mockListTableMetadataIterable);

        // Run the test
        myClassUnderTest.tryListTableMetadataPaginator1();

        // Verify the results
    }

    @Test
    void testTryListTableMetadataPaginator1_AthenaClientReturnsNoItems() {
        // Setup
        // Configure AthenaClient.listTableMetadataPaginator(...).
        final ListTableMetadataIterable mockListTableMetadataIterable = mock(ListTableMetadataIterable.class);
        when(mockAthenaClient.listTableMetadataPaginator(ListTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .expression("expression")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(mockListTableMetadataIterable);

        // Run the test
        myClassUnderTest.tryListTableMetadataPaginator1();

        // Verify the results
    }

    @Test
    void testTryListTableMetadataPaginator1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listTableMetadataPaginator(ListTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .expression("expression")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListTableMetadataPaginator1());
    }

    @Test
    void testTryListTableMetadataPaginator1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listTableMetadataPaginator(ListTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .expression("expression")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListTableMetadataPaginator1());
    }

    @Test
    void testTryListTableMetadataPaginator1_AthenaClientThrowsMetadataException() {
        // Setup
        when(mockAthenaClient.listTableMetadataPaginator(ListTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .expression("expression")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(MetadataException.class);

        // Run the test
        assertThrows(MetadataException.class, () -> myClassUnderTest.tryListTableMetadataPaginator1());
    }

    @Test
    void testTryListTableMetadataPaginator1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listTableMetadataPaginator(ListTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .expression("expression")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTableMetadataPaginator1());
    }

    @Test
    void testTryListTableMetadataPaginator1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listTableMetadataPaginator(ListTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .expression("expression")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTableMetadataPaginator1());
    }

    @Test
    void testTryListTableMetadataPaginator1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listTableMetadataPaginator(ListTableMetadataRequest.builder()
                .catalogName("catalogName")
                .databaseName("databaseName")
                .expression("expression")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListTableMetadataPaginator1());
    }

    @Test
    void testTryListTableMetadataPaginator2() {
        // Setup
        // Configure AthenaClient.listTableMetadataPaginator(...).
        final ListTableMetadataIterable mockListTableMetadataIterable = mock(ListTableMetadataIterable.class);
        when(mockAthenaClient.listTableMetadataPaginator(any(Consumer.class)))
                .thenReturn(mockListTableMetadataIterable);

        // Run the test
        myClassUnderTest.tryListTableMetadataPaginator2();

        // Verify the results
    }

    @Test
    void testTryListTableMetadataPaginator2_AthenaClientReturnsNoItems() {
        // Setup
        // Configure AthenaClient.listTableMetadataPaginator(...).
        final ListTableMetadataIterable mockListTableMetadataIterable = mock(ListTableMetadataIterable.class);
        when(mockAthenaClient.listTableMetadataPaginator(any(Consumer.class)))
                .thenReturn(mockListTableMetadataIterable);

        // Run the test
        myClassUnderTest.tryListTableMetadataPaginator2();

        // Verify the results
    }

    @Test
    void testTryListTableMetadataPaginator2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listTableMetadataPaginator(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListTableMetadataPaginator2());
    }

    @Test
    void testTryListTableMetadataPaginator2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listTableMetadataPaginator(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListTableMetadataPaginator2());
    }

    @Test
    void testTryListTableMetadataPaginator2_AthenaClientThrowsMetadataException() {
        // Setup
        when(mockAthenaClient.listTableMetadataPaginator(any(Consumer.class))).thenThrow(MetadataException.class);

        // Run the test
        assertThrows(MetadataException.class, () -> myClassUnderTest.tryListTableMetadataPaginator2());
    }

    @Test
    void testTryListTableMetadataPaginator2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listTableMetadataPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTableMetadataPaginator2());
    }

    @Test
    void testTryListTableMetadataPaginator2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listTableMetadataPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTableMetadataPaginator2());
    }

    @Test
    void testTryListTableMetadataPaginator2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listTableMetadataPaginator(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListTableMetadataPaginator2());
    }

    @Test
    void testTryListTagsForResource1() {
        // Setup
        // Configure AthenaClient.listTagsForResource(...).
        final ListTagsForResourceResponse listTagsForResourceResponse = ListTagsForResourceResponse.builder()
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockAthenaClient.listTagsForResource(ListTagsForResourceRequest.builder()
                .resourceARN("resourceARN")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(listTagsForResourceResponse);

        // Run the test
        myClassUnderTest.tryListTagsForResource1();

        // Verify the results
    }

    @Test
    void testTryListTagsForResource1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listTagsForResource(ListTagsForResourceRequest.builder()
                .resourceARN("resourceARN")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListTagsForResource1());
    }

    @Test
    void testTryListTagsForResource1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listTagsForResource(ListTagsForResourceRequest.builder()
                .resourceARN("resourceARN")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListTagsForResource1());
    }

    @Test
    void testTryListTagsForResource1_AthenaClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAthenaClient.listTagsForResource(ListTagsForResourceRequest.builder()
                .resourceARN("resourceARN")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryListTagsForResource1());
    }

    @Test
    void testTryListTagsForResource1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listTagsForResource(ListTagsForResourceRequest.builder()
                .resourceARN("resourceARN")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTagsForResource1());
    }

    @Test
    void testTryListTagsForResource1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listTagsForResource(ListTagsForResourceRequest.builder()
                .resourceARN("resourceARN")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTagsForResource1());
    }

    @Test
    void testTryListTagsForResource1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listTagsForResource(ListTagsForResourceRequest.builder()
                .resourceARN("resourceARN")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListTagsForResource1());
    }

    @Test
    void testTryListTagsForResource2() {
        // Setup
        // Configure AthenaClient.listTagsForResource(...).
        final ListTagsForResourceResponse listTagsForResourceResponse = ListTagsForResourceResponse.builder()
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockAthenaClient.listTagsForResource(any(Consumer.class))).thenReturn(listTagsForResourceResponse);

        // Run the test
        myClassUnderTest.tryListTagsForResource2();

        // Verify the results
    }

    @Test
    void testTryListTagsForResource2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listTagsForResource(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListTagsForResource2());
    }

    @Test
    void testTryListTagsForResource2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listTagsForResource(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListTagsForResource2());
    }

    @Test
    void testTryListTagsForResource2_AthenaClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAthenaClient.listTagsForResource(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryListTagsForResource2());
    }

    @Test
    void testTryListTagsForResource2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listTagsForResource(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTagsForResource2());
    }

    @Test
    void testTryListTagsForResource2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listTagsForResource(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTagsForResource2());
    }

    @Test
    void testTryListTagsForResource2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listTagsForResource(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListTagsForResource2());
    }

    @Test
    void testTryListTagsForResourcePaginator1() {
        // Setup
        // Configure AthenaClient.listTagsForResourcePaginator(...).
        final ListTagsForResourceIterable mockListTagsForResourceIterable = mock(ListTagsForResourceIterable.class);
        when(mockAthenaClient.listTagsForResourcePaginator(ListTagsForResourceRequest.builder()
                .resourceARN("resourceARN")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(mockListTagsForResourceIterable);

        // Run the test
        myClassUnderTest.tryListTagsForResourcePaginator1();

        // Verify the results
    }

    @Test
    void testTryListTagsForResourcePaginator1_AthenaClientReturnsNoItems() {
        // Setup
        // Configure AthenaClient.listTagsForResourcePaginator(...).
        final ListTagsForResourceIterable mockListTagsForResourceIterable = mock(ListTagsForResourceIterable.class);
        when(mockAthenaClient.listTagsForResourcePaginator(ListTagsForResourceRequest.builder()
                .resourceARN("resourceARN")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(mockListTagsForResourceIterable);

        // Run the test
        myClassUnderTest.tryListTagsForResourcePaginator1();

        // Verify the results
    }

    @Test
    void testTryListTagsForResourcePaginator1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listTagsForResourcePaginator(ListTagsForResourceRequest.builder()
                .resourceARN("resourceARN")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListTagsForResourcePaginator1());
    }

    @Test
    void testTryListTagsForResourcePaginator1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listTagsForResourcePaginator(ListTagsForResourceRequest.builder()
                .resourceARN("resourceARN")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListTagsForResourcePaginator1());
    }

    @Test
    void testTryListTagsForResourcePaginator1_AthenaClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAthenaClient.listTagsForResourcePaginator(ListTagsForResourceRequest.builder()
                .resourceARN("resourceARN")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryListTagsForResourcePaginator1());
    }

    @Test
    void testTryListTagsForResourcePaginator1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listTagsForResourcePaginator(ListTagsForResourceRequest.builder()
                .resourceARN("resourceARN")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTagsForResourcePaginator1());
    }

    @Test
    void testTryListTagsForResourcePaginator1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listTagsForResourcePaginator(ListTagsForResourceRequest.builder()
                .resourceARN("resourceARN")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTagsForResourcePaginator1());
    }

    @Test
    void testTryListTagsForResourcePaginator1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listTagsForResourcePaginator(ListTagsForResourceRequest.builder()
                .resourceARN("resourceARN")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListTagsForResourcePaginator1());
    }

    @Test
    void testTryListTagsForResourcePaginator2() {
        // Setup
        // Configure AthenaClient.listTagsForResourcePaginator(...).
        final ListTagsForResourceIterable mockListTagsForResourceIterable = mock(ListTagsForResourceIterable.class);
        when(mockAthenaClient.listTagsForResourcePaginator(any(Consumer.class)))
                .thenReturn(mockListTagsForResourceIterable);

        // Run the test
        myClassUnderTest.tryListTagsForResourcePaginator2();

        // Verify the results
    }

    @Test
    void testTryListTagsForResourcePaginator2_AthenaClientReturnsNoItems() {
        // Setup
        // Configure AthenaClient.listTagsForResourcePaginator(...).
        final ListTagsForResourceIterable mockListTagsForResourceIterable = mock(ListTagsForResourceIterable.class);
        when(mockAthenaClient.listTagsForResourcePaginator(any(Consumer.class)))
                .thenReturn(mockListTagsForResourceIterable);

        // Run the test
        myClassUnderTest.tryListTagsForResourcePaginator2();

        // Verify the results
    }

    @Test
    void testTryListTagsForResourcePaginator2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listTagsForResourcePaginator(any(Consumer.class)))
                .thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListTagsForResourcePaginator2());
    }

    @Test
    void testTryListTagsForResourcePaginator2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listTagsForResourcePaginator(any(Consumer.class)))
                .thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListTagsForResourcePaginator2());
    }

    @Test
    void testTryListTagsForResourcePaginator2_AthenaClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAthenaClient.listTagsForResourcePaginator(any(Consumer.class)))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryListTagsForResourcePaginator2());
    }

    @Test
    void testTryListTagsForResourcePaginator2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listTagsForResourcePaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTagsForResourcePaginator2());
    }

    @Test
    void testTryListTagsForResourcePaginator2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listTagsForResourcePaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTagsForResourcePaginator2());
    }

    @Test
    void testTryListTagsForResourcePaginator2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listTagsForResourcePaginator(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListTagsForResourcePaginator2());
    }

    @Test
    void testTryListWorkGroups1() {
        // Setup
        // Configure AthenaClient.listWorkGroups(...).
        final ListWorkGroupsResponse listWorkGroupsResponse = ListWorkGroupsResponse.builder()
                .workGroups(WorkGroupSummary.builder()
                        .name("name")
                        .state(WorkGroupState.ENABLED)
                        .description("description")
                        .creationTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockAthenaClient.listWorkGroups(ListWorkGroupsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(listWorkGroupsResponse);

        // Run the test
        myClassUnderTest.tryListWorkGroups1();

        // Verify the results
    }

    @Test
    void testTryListWorkGroups1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listWorkGroups(ListWorkGroupsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListWorkGroups1());
    }

    @Test
    void testTryListWorkGroups1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listWorkGroups(ListWorkGroupsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListWorkGroups1());
    }

    @Test
    void testTryListWorkGroups1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listWorkGroups(ListWorkGroupsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListWorkGroups1());
    }

    @Test
    void testTryListWorkGroups1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listWorkGroups(ListWorkGroupsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListWorkGroups1());
    }

    @Test
    void testTryListWorkGroups1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listWorkGroups(ListWorkGroupsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListWorkGroups1());
    }

    @Test
    void testTryListWorkGroups2() {
        // Setup
        // Configure AthenaClient.listWorkGroups(...).
        final ListWorkGroupsResponse listWorkGroupsResponse = ListWorkGroupsResponse.builder()
                .workGroups(WorkGroupSummary.builder()
                        .name("name")
                        .state(WorkGroupState.ENABLED)
                        .description("description")
                        .creationTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockAthenaClient.listWorkGroups(any(Consumer.class))).thenReturn(listWorkGroupsResponse);

        // Run the test
        myClassUnderTest.tryListWorkGroups2();

        // Verify the results
    }

    @Test
    void testTryListWorkGroups2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listWorkGroups(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListWorkGroups2());
    }

    @Test
    void testTryListWorkGroups2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listWorkGroups(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListWorkGroups2());
    }

    @Test
    void testTryListWorkGroups2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listWorkGroups(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListWorkGroups2());
    }

    @Test
    void testTryListWorkGroups2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listWorkGroups(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListWorkGroups2());
    }

    @Test
    void testTryListWorkGroups2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listWorkGroups(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListWorkGroups2());
    }

    @Test
    void testTryListWorkGroupsPaginator1() {
        // Setup
        // Configure AthenaClient.listWorkGroupsPaginator(...).
        final ListWorkGroupsIterable mockListWorkGroupsIterable = mock(ListWorkGroupsIterable.class);
        when(mockAthenaClient.listWorkGroupsPaginator(ListWorkGroupsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(mockListWorkGroupsIterable);

        // Run the test
        myClassUnderTest.tryListWorkGroupsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListWorkGroupsPaginator1_AthenaClientReturnsNoItems() {
        // Setup
        // Configure AthenaClient.listWorkGroupsPaginator(...).
        final ListWorkGroupsIterable mockListWorkGroupsIterable = mock(ListWorkGroupsIterable.class);
        when(mockAthenaClient.listWorkGroupsPaginator(ListWorkGroupsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(mockListWorkGroupsIterable);

        // Run the test
        myClassUnderTest.tryListWorkGroupsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListWorkGroupsPaginator1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listWorkGroupsPaginator(ListWorkGroupsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListWorkGroupsPaginator1());
    }

    @Test
    void testTryListWorkGroupsPaginator1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listWorkGroupsPaginator(ListWorkGroupsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListWorkGroupsPaginator1());
    }

    @Test
    void testTryListWorkGroupsPaginator1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listWorkGroupsPaginator(ListWorkGroupsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListWorkGroupsPaginator1());
    }

    @Test
    void testTryListWorkGroupsPaginator1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listWorkGroupsPaginator(ListWorkGroupsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListWorkGroupsPaginator1());
    }

    @Test
    void testTryListWorkGroupsPaginator1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listWorkGroupsPaginator(ListWorkGroupsRequest.builder()
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListWorkGroupsPaginator1());
    }

    @Test
    void testTryListWorkGroupsPaginator2() {
        // Setup
        // Configure AthenaClient.listWorkGroupsPaginator(...).
        final ListWorkGroupsIterable mockListWorkGroupsIterable = mock(ListWorkGroupsIterable.class);
        when(mockAthenaClient.listWorkGroupsPaginator(any(Consumer.class))).thenReturn(mockListWorkGroupsIterable);

        // Run the test
        myClassUnderTest.tryListWorkGroupsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListWorkGroupsPaginator2_AthenaClientReturnsNoItems() {
        // Setup
        // Configure AthenaClient.listWorkGroupsPaginator(...).
        final ListWorkGroupsIterable mockListWorkGroupsIterable = mock(ListWorkGroupsIterable.class);
        when(mockAthenaClient.listWorkGroupsPaginator(any(Consumer.class))).thenReturn(mockListWorkGroupsIterable);

        // Run the test
        myClassUnderTest.tryListWorkGroupsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListWorkGroupsPaginator2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.listWorkGroupsPaginator(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryListWorkGroupsPaginator2());
    }

    @Test
    void testTryListWorkGroupsPaginator2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.listWorkGroupsPaginator(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryListWorkGroupsPaginator2());
    }

    @Test
    void testTryListWorkGroupsPaginator2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.listWorkGroupsPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListWorkGroupsPaginator2());
    }

    @Test
    void testTryListWorkGroupsPaginator2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.listWorkGroupsPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListWorkGroupsPaginator2());
    }

    @Test
    void testTryListWorkGroupsPaginator2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.listWorkGroupsPaginator(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryListWorkGroupsPaginator2());
    }

    @Test
    void testTryStartQueryExecution1() {
        // Setup
        // Configure AthenaClient.startQueryExecution(...).
        final StartQueryExecutionResponse startQueryExecutionResponse = StartQueryExecutionResponse.builder()
                .queryExecutionId("queryExecutionId")
                .build();
        when(mockAthenaClient.startQueryExecution(StartQueryExecutionRequest.builder()
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
                .build())).thenReturn(startQueryExecutionResponse);

        // Run the test
        myClassUnderTest.tryStartQueryExecution1();

        // Verify the results
    }

    @Test
    void testTryStartQueryExecution1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.startQueryExecution(StartQueryExecutionRequest.builder()
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
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryStartQueryExecution1());
    }

    @Test
    void testTryStartQueryExecution1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.startQueryExecution(StartQueryExecutionRequest.builder()
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
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryStartQueryExecution1());
    }

    @Test
    void testTryStartQueryExecution1_AthenaClientThrowsTooManyRequestsException() {
        // Setup
        when(mockAthenaClient.startQueryExecution(StartQueryExecutionRequest.builder()
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
                .build())).thenThrow(TooManyRequestsException.class);

        // Run the test
        assertThrows(TooManyRequestsException.class, () -> myClassUnderTest.tryStartQueryExecution1());
    }

    @Test
    void testTryStartQueryExecution1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.startQueryExecution(StartQueryExecutionRequest.builder()
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
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryStartQueryExecution1());
    }

    @Test
    void testTryStartQueryExecution1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.startQueryExecution(StartQueryExecutionRequest.builder()
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
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryStartQueryExecution1());
    }

    @Test
    void testTryStartQueryExecution1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.startQueryExecution(StartQueryExecutionRequest.builder()
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
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryStartQueryExecution1());
    }

    @Test
    void testTryStartQueryExecution2() {
        // Setup
        // Configure AthenaClient.startQueryExecution(...).
        final StartQueryExecutionResponse startQueryExecutionResponse = StartQueryExecutionResponse.builder()
                .queryExecutionId("queryExecutionId")
                .build();
        when(mockAthenaClient.startQueryExecution(any(Consumer.class))).thenReturn(startQueryExecutionResponse);

        // Run the test
        myClassUnderTest.tryStartQueryExecution2();

        // Verify the results
    }

    @Test
    void testTryStartQueryExecution2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.startQueryExecution(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryStartQueryExecution2());
    }

    @Test
    void testTryStartQueryExecution2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.startQueryExecution(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryStartQueryExecution2());
    }

    @Test
    void testTryStartQueryExecution2_AthenaClientThrowsTooManyRequestsException() {
        // Setup
        when(mockAthenaClient.startQueryExecution(any(Consumer.class))).thenThrow(TooManyRequestsException.class);

        // Run the test
        assertThrows(TooManyRequestsException.class, () -> myClassUnderTest.tryStartQueryExecution2());
    }

    @Test
    void testTryStartQueryExecution2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.startQueryExecution(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryStartQueryExecution2());
    }

    @Test
    void testTryStartQueryExecution2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.startQueryExecution(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryStartQueryExecution2());
    }

    @Test
    void testTryStartQueryExecution2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.startQueryExecution(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryStartQueryExecution2());
    }

    @Test
    void testTryStopQueryExecution1() {
        // Setup
        // Configure AthenaClient.stopQueryExecution(...).
        final StopQueryExecutionResponse stopQueryExecutionResponse = StopQueryExecutionResponse.builder().build();
        when(mockAthenaClient.stopQueryExecution(StopQueryExecutionRequest.builder()
                .queryExecutionId("queryExecutionId")
                .build())).thenReturn(stopQueryExecutionResponse);

        // Run the test
        myClassUnderTest.tryStopQueryExecution1();

        // Verify the results
    }

    @Test
    void testTryStopQueryExecution1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.stopQueryExecution(StopQueryExecutionRequest.builder()
                .queryExecutionId("queryExecutionId")
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryStopQueryExecution1());
    }

    @Test
    void testTryStopQueryExecution1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.stopQueryExecution(StopQueryExecutionRequest.builder()
                .queryExecutionId("queryExecutionId")
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryStopQueryExecution1());
    }

    @Test
    void testTryStopQueryExecution1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.stopQueryExecution(StopQueryExecutionRequest.builder()
                .queryExecutionId("queryExecutionId")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryStopQueryExecution1());
    }

    @Test
    void testTryStopQueryExecution1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.stopQueryExecution(StopQueryExecutionRequest.builder()
                .queryExecutionId("queryExecutionId")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryStopQueryExecution1());
    }

    @Test
    void testTryStopQueryExecution1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.stopQueryExecution(StopQueryExecutionRequest.builder()
                .queryExecutionId("queryExecutionId")
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryStopQueryExecution1());
    }

    @Test
    void testTryStopQueryExecution2() {
        // Setup
        // Configure AthenaClient.stopQueryExecution(...).
        final StopQueryExecutionResponse stopQueryExecutionResponse = StopQueryExecutionResponse.builder().build();
        when(mockAthenaClient.stopQueryExecution(any(Consumer.class))).thenReturn(stopQueryExecutionResponse);

        // Run the test
        myClassUnderTest.tryStopQueryExecution2();

        // Verify the results
    }

    @Test
    void testTryStopQueryExecution2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.stopQueryExecution(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryStopQueryExecution2());
    }

    @Test
    void testTryStopQueryExecution2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.stopQueryExecution(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryStopQueryExecution2());
    }

    @Test
    void testTryStopQueryExecution2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.stopQueryExecution(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryStopQueryExecution2());
    }

    @Test
    void testTryStopQueryExecution2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.stopQueryExecution(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryStopQueryExecution2());
    }

    @Test
    void testTryStopQueryExecution2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.stopQueryExecution(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryStopQueryExecution2());
    }

    @Test
    void testTryTagResource1() {
        // Setup
        when(mockAthenaClient.tagResource(TagResourceRequest.builder()
                .resourceARN("resourceARN")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenReturn(TagResourceResponse.builder().build());

        // Run the test
        myClassUnderTest.tryTagResource1();

        // Verify the results
    }

    @Test
    void testTryTagResource1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.tagResource(TagResourceRequest.builder()
                .resourceARN("resourceARN")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.tagResource(TagResourceRequest.builder()
                .resourceARN("resourceARN")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_AthenaClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAthenaClient.tagResource(TagResourceRequest.builder()
                .resourceARN("resourceARN")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.tagResource(TagResourceRequest.builder()
                .resourceARN("resourceARN")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.tagResource(TagResourceRequest.builder()
                .resourceARN("resourceARN")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.tagResource(TagResourceRequest.builder()
                .resourceARN("resourceARN")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource2() {
        // Setup
        when(mockAthenaClient.tagResource(any(Consumer.class))).thenReturn(TagResourceResponse.builder().build());

        // Run the test
        myClassUnderTest.tryTagResource2();

        // Verify the results
    }

    @Test
    void testTryTagResource2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.tagResource(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.tagResource(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_AthenaClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAthenaClient.tagResource(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.tagResource(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.tagResource(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.tagResource(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryUntagResource1() {
        // Setup
        when(mockAthenaClient.untagResource(UntagResourceRequest.builder()
                .resourceARN("resourceARN")
                .tagKeys("tagKeys")
                .build())).thenReturn(UntagResourceResponse.builder().build());

        // Run the test
        myClassUnderTest.tryUntagResource1();

        // Verify the results
    }

    @Test
    void testTryUntagResource1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.untagResource(UntagResourceRequest.builder()
                .resourceARN("resourceARN")
                .tagKeys("tagKeys")
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.untagResource(UntagResourceRequest.builder()
                .resourceARN("resourceARN")
                .tagKeys("tagKeys")
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_AthenaClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAthenaClient.untagResource(UntagResourceRequest.builder()
                .resourceARN("resourceARN")
                .tagKeys("tagKeys")
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.untagResource(UntagResourceRequest.builder()
                .resourceARN("resourceARN")
                .tagKeys("tagKeys")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.untagResource(UntagResourceRequest.builder()
                .resourceARN("resourceARN")
                .tagKeys("tagKeys")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.untagResource(UntagResourceRequest.builder()
                .resourceARN("resourceARN")
                .tagKeys("tagKeys")
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource2() {
        // Setup
        when(mockAthenaClient.untagResource(any(Consumer.class))).thenReturn(UntagResourceResponse.builder().build());

        // Run the test
        myClassUnderTest.tryUntagResource2();

        // Verify the results
    }

    @Test
    void testTryUntagResource2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.untagResource(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.untagResource(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_AthenaClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAthenaClient.untagResource(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.untagResource(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.untagResource(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.untagResource(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUpdateDataCatalog1() {
        // Setup
        // Configure AthenaClient.updateDataCatalog(...).
        final UpdateDataCatalogResponse updateDataCatalogResponse = UpdateDataCatalogResponse.builder().build();
        when(mockAthenaClient.updateDataCatalog(UpdateDataCatalogRequest.builder()
                .name("name")
                .type(DataCatalogType.LAMBDA)
                .description("description")
                .parameters(new HashMap<>())
                .build())).thenReturn(updateDataCatalogResponse);

        // Run the test
        myClassUnderTest.tryUpdateDataCatalog1();

        // Verify the results
    }

    @Test
    void testTryUpdateDataCatalog1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.updateDataCatalog(UpdateDataCatalogRequest.builder()
                .name("name")
                .type(DataCatalogType.LAMBDA)
                .description("description")
                .parameters(new HashMap<>())
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryUpdateDataCatalog1());
    }

    @Test
    void testTryUpdateDataCatalog1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.updateDataCatalog(UpdateDataCatalogRequest.builder()
                .name("name")
                .type(DataCatalogType.LAMBDA)
                .description("description")
                .parameters(new HashMap<>())
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryUpdateDataCatalog1());
    }

    @Test
    void testTryUpdateDataCatalog1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.updateDataCatalog(UpdateDataCatalogRequest.builder()
                .name("name")
                .type(DataCatalogType.LAMBDA)
                .description("description")
                .parameters(new HashMap<>())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateDataCatalog1());
    }

    @Test
    void testTryUpdateDataCatalog1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.updateDataCatalog(UpdateDataCatalogRequest.builder()
                .name("name")
                .type(DataCatalogType.LAMBDA)
                .description("description")
                .parameters(new HashMap<>())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateDataCatalog1());
    }

    @Test
    void testTryUpdateDataCatalog1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.updateDataCatalog(UpdateDataCatalogRequest.builder()
                .name("name")
                .type(DataCatalogType.LAMBDA)
                .description("description")
                .parameters(new HashMap<>())
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryUpdateDataCatalog1());
    }

    @Test
    void testTryUpdateDataCatalog2() {
        // Setup
        // Configure AthenaClient.updateDataCatalog(...).
        final UpdateDataCatalogResponse updateDataCatalogResponse = UpdateDataCatalogResponse.builder().build();
        when(mockAthenaClient.updateDataCatalog(any(Consumer.class))).thenReturn(updateDataCatalogResponse);

        // Run the test
        myClassUnderTest.tryUpdateDataCatalog2();

        // Verify the results
    }

    @Test
    void testTryUpdateDataCatalog2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.updateDataCatalog(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryUpdateDataCatalog2());
    }

    @Test
    void testTryUpdateDataCatalog2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.updateDataCatalog(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryUpdateDataCatalog2());
    }

    @Test
    void testTryUpdateDataCatalog2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.updateDataCatalog(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateDataCatalog2());
    }

    @Test
    void testTryUpdateDataCatalog2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.updateDataCatalog(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateDataCatalog2());
    }

    @Test
    void testTryUpdateDataCatalog2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.updateDataCatalog(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryUpdateDataCatalog2());
    }

    @Test
    void testTryUpdateWorkGroup1() {
        // Setup
        // Configure AthenaClient.updateWorkGroup(...).
        final UpdateWorkGroupResponse updateWorkGroupResponse = UpdateWorkGroupResponse.builder().build();
        when(mockAthenaClient.updateWorkGroup(UpdateWorkGroupRequest.builder()
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
                .build())).thenReturn(updateWorkGroupResponse);

        // Run the test
        myClassUnderTest.tryUpdateWorkGroup1();

        // Verify the results
    }

    @Test
    void testTryUpdateWorkGroup1_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.updateWorkGroup(UpdateWorkGroupRequest.builder()
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
                .build())).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryUpdateWorkGroup1());
    }

    @Test
    void testTryUpdateWorkGroup1_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.updateWorkGroup(UpdateWorkGroupRequest.builder()
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
                .build())).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryUpdateWorkGroup1());
    }

    @Test
    void testTryUpdateWorkGroup1_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.updateWorkGroup(UpdateWorkGroupRequest.builder()
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
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateWorkGroup1());
    }

    @Test
    void testTryUpdateWorkGroup1_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.updateWorkGroup(UpdateWorkGroupRequest.builder()
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
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateWorkGroup1());
    }

    @Test
    void testTryUpdateWorkGroup1_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.updateWorkGroup(UpdateWorkGroupRequest.builder()
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
                .build())).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryUpdateWorkGroup1());
    }

    @Test
    void testTryUpdateWorkGroup2() {
        // Setup
        // Configure AthenaClient.updateWorkGroup(...).
        final UpdateWorkGroupResponse updateWorkGroupResponse = UpdateWorkGroupResponse.builder().build();
        when(mockAthenaClient.updateWorkGroup(any(Consumer.class))).thenReturn(updateWorkGroupResponse);

        // Run the test
        myClassUnderTest.tryUpdateWorkGroup2();

        // Verify the results
    }

    @Test
    void testTryUpdateWorkGroup2_AthenaClientThrowsInternalServerException() {
        // Setup
        when(mockAthenaClient.updateWorkGroup(any(Consumer.class))).thenThrow(InternalServerException.class);

        // Run the test
        assertThrows(InternalServerException.class, () -> myClassUnderTest.tryUpdateWorkGroup2());
    }

    @Test
    void testTryUpdateWorkGroup2_AthenaClientThrowsInvalidRequestException() {
        // Setup
        when(mockAthenaClient.updateWorkGroup(any(Consumer.class))).thenThrow(InvalidRequestException.class);

        // Run the test
        assertThrows(InvalidRequestException.class, () -> myClassUnderTest.tryUpdateWorkGroup2());
    }

    @Test
    void testTryUpdateWorkGroup2_AthenaClientThrowsAwsServiceException() {
        // Setup
        when(mockAthenaClient.updateWorkGroup(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateWorkGroup2());
    }

    @Test
    void testTryUpdateWorkGroup2_AthenaClientThrowsSdkClientException() {
        // Setup
        when(mockAthenaClient.updateWorkGroup(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateWorkGroup2());
    }

    @Test
    void testTryUpdateWorkGroup2_AthenaClientThrowsAthenaException() {
        // Setup
        when(mockAthenaClient.updateWorkGroup(any(Consumer.class))).thenThrow(AthenaException.class);

        // Run the test
        assertThrows(AthenaException.class, () -> myClassUnderTest.tryUpdateWorkGroup2());
    }
}
