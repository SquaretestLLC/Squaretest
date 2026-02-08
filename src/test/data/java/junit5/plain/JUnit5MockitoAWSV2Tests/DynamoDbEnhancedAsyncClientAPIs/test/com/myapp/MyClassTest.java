package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import software.amazon.awssdk.enhanced.dynamodb.Document;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private DynamoDbEnhancedAsyncClient mockDynamoDbEnhancedAsyncClient;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockDynamoDbEnhancedAsyncClient);
    }

    @Test
    void testTryTable() {
        // Setup
        // Configure DynamoDbEnhancedAsyncClient.table(...).
        final DynamoDbAsyncTable<StoredOrder> mockDynamoDbAsyncTable = mock(DynamoDbAsyncTable.class);
        when(mockDynamoDbEnhancedAsyncClient.table(eq("tableName"), any(TableSchema.class)))
                .thenReturn(mockDynamoDbAsyncTable);

        // Run the test
        myClassUnderTest.tryTable();

        // Verify the results
    }

    @Test
    void testTryBatchGetItem() {
        // Setup
        // Configure DynamoDbEnhancedAsyncClient.batchGetItem(...).
        final BatchGetResultPagePublisher mockBatchGetResultPagePublisher = mock(BatchGetResultPagePublisher.class);
        when(mockDynamoDbEnhancedAsyncClient.batchGetItem(BatchGetItemEnhancedRequest.builder().build()))
                .thenReturn(mockBatchGetResultPagePublisher);

        // Run the test
        myClassUnderTest.tryBatchGetItem();

        // Verify the results
    }

    @Test
    void testTryBatchGetItem1() {
        // Setup
        // Configure DynamoDbEnhancedAsyncClient.batchGetItem(...).
        final BatchGetResultPagePublisher mockBatchGetResultPagePublisher = mock(BatchGetResultPagePublisher.class);
        when(mockDynamoDbEnhancedAsyncClient.batchGetItem(any(Consumer.class)))
                .thenReturn(mockBatchGetResultPagePublisher);

        // Run the test
        myClassUnderTest.tryBatchGetItem1();

        // Verify the results
    }

    @Test
    void testTryBatchWriteItem() {
        // Setup
        // Configure DynamoDbEnhancedAsyncClient.batchWriteItem(...).
        final CompletableFuture<BatchWriteResult> batchWriteResultCompletableFuture = CompletableFuture.completedFuture(
                BatchWriteResult.builder().build());
        when(mockDynamoDbEnhancedAsyncClient.batchWriteItem(
                BatchWriteItemEnhancedRequest.builder().build())).thenReturn(batchWriteResultCompletableFuture);

        // Run the test
        myClassUnderTest.tryBatchWriteItem();

        // Verify the results
    }

    @Test
    void testTryBatchWriteItem_DynamoDbEnhancedAsyncClientReturnsFailure() {
        // Setup
        // Configure DynamoDbEnhancedAsyncClient.batchWriteItem(...).
        final CompletableFuture<BatchWriteResult> batchWriteResultCompletableFuture = new CompletableFuture<>();
        batchWriteResultCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockDynamoDbEnhancedAsyncClient.batchWriteItem(
                BatchWriteItemEnhancedRequest.builder().build())).thenReturn(batchWriteResultCompletableFuture);

        // Run the test
        myClassUnderTest.tryBatchWriteItem();

        // Verify the results
    }

    @Test
    void testTryBatchWriteItem1() {
        // Setup
        // Configure DynamoDbEnhancedAsyncClient.batchWriteItem(...).
        final CompletableFuture<BatchWriteResult> batchWriteResultCompletableFuture = CompletableFuture.completedFuture(
                BatchWriteResult.builder().build());
        when(mockDynamoDbEnhancedAsyncClient.batchWriteItem(any(Consumer.class)))
                .thenReturn(batchWriteResultCompletableFuture);

        // Run the test
        myClassUnderTest.tryBatchWriteItem1();

        // Verify the results
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbEnhancedAsyncClientReturnsFailure() {
        // Setup
        // Configure DynamoDbEnhancedAsyncClient.batchWriteItem(...).
        final CompletableFuture<BatchWriteResult> batchWriteResultCompletableFuture = new CompletableFuture<>();
        batchWriteResultCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockDynamoDbEnhancedAsyncClient.batchWriteItem(any(Consumer.class)))
                .thenReturn(batchWriteResultCompletableFuture);

        // Run the test
        myClassUnderTest.tryBatchWriteItem1();

        // Verify the results
    }

    @Test
    void testTryTransactGetItems() {
        // Setup
        // Configure DynamoDbEnhancedAsyncClient.transactGetItems(...).
        final CompletableFuture<List<Document>> listCompletableFuture = CompletableFuture.completedFuture(
                Arrays.asList());
        when(mockDynamoDbEnhancedAsyncClient.transactGetItems(
                TransactGetItemsEnhancedRequest.builder().build())).thenReturn(listCompletableFuture);

        // Run the test
        myClassUnderTest.tryTransactGetItems();

        // Verify the results
    }

    @Test
    void testTryTransactGetItems_DynamoDbEnhancedAsyncClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbEnhancedAsyncClient.transactGetItems(...).
        final CompletableFuture<List<Document>> listCompletableFuture = CompletableFuture.completedFuture(
                Collections.emptyList());
        when(mockDynamoDbEnhancedAsyncClient.transactGetItems(
                TransactGetItemsEnhancedRequest.builder().build())).thenReturn(listCompletableFuture);

        // Run the test
        myClassUnderTest.tryTransactGetItems();

        // Verify the results
    }

    @Test
    void testTryTransactGetItems_DynamoDbEnhancedAsyncClientReturnsFailure() {
        // Setup
        // Configure DynamoDbEnhancedAsyncClient.transactGetItems(...).
        final CompletableFuture<List<Document>> listCompletableFuture = new CompletableFuture<>();
        listCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockDynamoDbEnhancedAsyncClient.transactGetItems(
                TransactGetItemsEnhancedRequest.builder().build())).thenReturn(listCompletableFuture);

        // Run the test
        myClassUnderTest.tryTransactGetItems();

        // Verify the results
    }

    @Test
    void testTryTransactGetItems1() {
        // Setup
        // Configure DynamoDbEnhancedAsyncClient.transactGetItems(...).
        final CompletableFuture<List<Document>> listCompletableFuture = CompletableFuture.completedFuture(
                Arrays.asList());
        when(mockDynamoDbEnhancedAsyncClient.transactGetItems(any(Consumer.class))).thenReturn(listCompletableFuture);

        // Run the test
        myClassUnderTest.tryTransactGetItems1();

        // Verify the results
    }

    @Test
    void testTryTransactGetItems1_DynamoDbEnhancedAsyncClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbEnhancedAsyncClient.transactGetItems(...).
        final CompletableFuture<List<Document>> listCompletableFuture = CompletableFuture.completedFuture(
                Collections.emptyList());
        when(mockDynamoDbEnhancedAsyncClient.transactGetItems(any(Consumer.class))).thenReturn(listCompletableFuture);

        // Run the test
        myClassUnderTest.tryTransactGetItems1();

        // Verify the results
    }

    @Test
    void testTryTransactGetItems1_DynamoDbEnhancedAsyncClientReturnsFailure() {
        // Setup
        // Configure DynamoDbEnhancedAsyncClient.transactGetItems(...).
        final CompletableFuture<List<Document>> listCompletableFuture = new CompletableFuture<>();
        listCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockDynamoDbEnhancedAsyncClient.transactGetItems(any(Consumer.class))).thenReturn(listCompletableFuture);

        // Run the test
        myClassUnderTest.tryTransactGetItems1();

        // Verify the results
    }

    @Test
    void testTryTransactWriteItems() {
        // Setup
        when(mockDynamoDbEnhancedAsyncClient.transactWriteItems(
                TransactWriteItemsEnhancedRequest.builder().build()))
                .thenReturn(CompletableFuture.completedFuture(null));

        // Run the test
        myClassUnderTest.tryTransactWriteItems();

        // Verify the results
    }

    @Test
    void testTryTransactWriteItems_DynamoDbEnhancedAsyncClientReturnsFailure() {
        // Setup
        // Configure DynamoDbEnhancedAsyncClient.transactWriteItems(...).
        final CompletableFuture<Void> voidCompletableFuture = new CompletableFuture<>();
        voidCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockDynamoDbEnhancedAsyncClient.transactWriteItems(
                TransactWriteItemsEnhancedRequest.builder().build())).thenReturn(voidCompletableFuture);

        // Run the test
        myClassUnderTest.tryTransactWriteItems();

        // Verify the results
    }

    @Test
    void testTryTransactWriteItems1() {
        // Setup
        when(mockDynamoDbEnhancedAsyncClient.transactWriteItems(any(Consumer.class)))
                .thenReturn(CompletableFuture.completedFuture(null));

        // Run the test
        myClassUnderTest.tryTransactWriteItems1();

        // Verify the results
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbEnhancedAsyncClientReturnsFailure() {
        // Setup
        // Configure DynamoDbEnhancedAsyncClient.transactWriteItems(...).
        final CompletableFuture<Void> voidCompletableFuture = new CompletableFuture<>();
        voidCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockDynamoDbEnhancedAsyncClient.transactWriteItems(any(Consumer.class))).thenReturn(voidCompletableFuture);

        // Run the test
        myClassUnderTest.tryTransactWriteItems1();

        // Verify the results
    }

    @Test
    void testTryEquals() {
        // Setup
        when(mockDynamoDbEnhancedAsyncClient.equals("o")).thenReturn(false);

        // Run the test
        myClassUnderTest.tryEquals();

        // Verify the results
    }

    @Test
    void testTryHashCode() {
        // Setup
        when(mockDynamoDbEnhancedAsyncClient.hashCode()).thenReturn(0);

        // Run the test
        myClassUnderTest.tryHashCode();

        // Verify the results
    }

    @Test
    void testTryBuilder() {
        // Setup
        // Run the test
        myClassUnderTest.tryBuilder();

        // Verify the results
    }
}
