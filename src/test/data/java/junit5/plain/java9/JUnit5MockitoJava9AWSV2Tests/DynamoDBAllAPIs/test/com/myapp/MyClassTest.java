package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.paginators.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private DynamoDbClient mockDynamoDbClient;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockDynamoDbClient);
    }

    @Test
    void testTryBatchGetItem1() {
        // Setup
        // Configure DynamoDbClient.batchGetItem(...).
        final BatchGetItemResponse batchGetItemResponse = BatchGetItemResponse.builder()
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
        when(mockDynamoDbClient.batchGetItem(BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build())).thenReturn(batchGetItemResponse);

        // Run the test
        myClassUnderTest.tryBatchGetItem1();

        // Verify the results
    }

    @Test
    void testTryBatchGetItem1_DynamoDbClientReturnsNoItems() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build())).thenReturn(BatchGetItemResponse.builder().build());

        // Run the test
        myClassUnderTest.tryBatchGetItem1();

        // Verify the results
    }

    @Test
    void testTryBatchGetItem1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryBatchGetItem1());
    }

    @Test
    void testTryBatchGetItem1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryBatchGetItem1());
    }

    @Test
    void testTryBatchGetItem1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryBatchGetItem1());
    }

    @Test
    void testTryBatchGetItem1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryBatchGetItem1());
    }

    @Test
    void testTryBatchGetItem1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryBatchGetItem1());
    }

    @Test
    void testTryBatchGetItem1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryBatchGetItem1());
    }

    @Test
    void testTryBatchGetItem1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryBatchGetItem1());
    }

    @Test
    void testTryBatchGetItem2() {
        // Setup
        // Configure DynamoDbClient.batchGetItem(...).
        final BatchGetItemResponse batchGetItemResponse = BatchGetItemResponse.builder()
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
        when(mockDynamoDbClient.batchGetItem(any(Consumer.class))).thenReturn(batchGetItemResponse);

        // Run the test
        myClassUnderTest.tryBatchGetItem2();

        // Verify the results
    }

    @Test
    void testTryBatchGetItem2_DynamoDbClientReturnsNoItems() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(any(Consumer.class))).thenReturn(BatchGetItemResponse.builder().build());

        // Run the test
        myClassUnderTest.tryBatchGetItem2();

        // Verify the results
    }

    @Test
    void testTryBatchGetItem2_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(any(Consumer.class)))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryBatchGetItem2());
    }

    @Test
    void testTryBatchGetItem2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryBatchGetItem2());
    }

    @Test
    void testTryBatchGetItem2_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryBatchGetItem2());
    }

    @Test
    void testTryBatchGetItem2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryBatchGetItem2());
    }

    @Test
    void testTryBatchGetItem2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryBatchGetItem2());
    }

    @Test
    void testTryBatchGetItem2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryBatchGetItem2());
    }

    @Test
    void testTryBatchGetItem2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryBatchGetItem2());
    }

    @Test
    void testTryBatchGetItemPaginator1() {
        // Setup
        // Configure DynamoDbClient.batchGetItemPaginator(...).
        final BatchGetItemIterable mockBatchGetItemIterable = mock(BatchGetItemIterable.class);
        when(mockDynamoDbClient.batchGetItemPaginator(BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build())).thenReturn(mockBatchGetItemIterable);

        // Run the test
        myClassUnderTest.tryBatchGetItemPaginator1();

        // Verify the results
    }

    @Test
    void testTryBatchGetItemPaginator1_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.batchGetItemPaginator(...).
        final BatchGetItemIterable mockBatchGetItemIterable = mock(BatchGetItemIterable.class);
        when(mockDynamoDbClient.batchGetItemPaginator(BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build())).thenReturn(mockBatchGetItemIterable);

        // Run the test
        myClassUnderTest.tryBatchGetItemPaginator1();

        // Verify the results
    }

    @Test
    void testTryBatchGetItemPaginator1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryBatchGetItemPaginator1());
    }

    @Test
    void testTryBatchGetItemPaginator1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryBatchGetItemPaginator1());
    }

    @Test
    void testTryBatchGetItemPaginator1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryBatchGetItemPaginator1());
    }

    @Test
    void testTryBatchGetItemPaginator1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryBatchGetItemPaginator1());
    }

    @Test
    void testTryBatchGetItemPaginator1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryBatchGetItemPaginator1());
    }

    @Test
    void testTryBatchGetItemPaginator1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryBatchGetItemPaginator1());
    }

    @Test
    void testTryBatchGetItemPaginator1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(BatchGetItemRequest.builder()
                .requestItems(Map.of(
                        "TableName1", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build()
                        )).build(),
                        "TableName2", KeysAndAttributes.builder().keys(Map.of(
                                "PrimaryKey2", AttributeValue.builder().s("PrimaryKeyValue2").build()
                        )).build()
                )).build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryBatchGetItemPaginator1());
    }

    @Test
    void testTryBatchGetItemPaginator2() {
        // Setup
        // Configure DynamoDbClient.batchGetItemPaginator(...).
        final BatchGetItemIterable mockBatchGetItemIterable = mock(BatchGetItemIterable.class);
        when(mockDynamoDbClient.batchGetItemPaginator(any(Consumer.class))).thenReturn(mockBatchGetItemIterable);

        // Run the test
        myClassUnderTest.tryBatchGetItemPaginator2();

        // Verify the results
    }

    @Test
    void testTryBatchGetItemPaginator2_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.batchGetItemPaginator(...).
        final BatchGetItemIterable mockBatchGetItemIterable = mock(BatchGetItemIterable.class);
        when(mockDynamoDbClient.batchGetItemPaginator(any(Consumer.class))).thenReturn(mockBatchGetItemIterable);

        // Run the test
        myClassUnderTest.tryBatchGetItemPaginator2();

        // Verify the results
    }

    @Test
    void testTryBatchGetItemPaginator2_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(any(Consumer.class)))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryBatchGetItemPaginator2());
    }

    @Test
    void testTryBatchGetItemPaginator2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryBatchGetItemPaginator2());
    }

    @Test
    void testTryBatchGetItemPaginator2_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(any(Consumer.class)))
                .thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryBatchGetItemPaginator2());
    }

    @Test
    void testTryBatchGetItemPaginator2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryBatchGetItemPaginator2());
    }

    @Test
    void testTryBatchGetItemPaginator2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryBatchGetItemPaginator2());
    }

    @Test
    void testTryBatchGetItemPaginator2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryBatchGetItemPaginator2());
    }

    @Test
    void testTryBatchGetItemPaginator2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryBatchGetItemPaginator2());
    }

    @Test
    void testTryBatchWriteItem1() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
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
                )).build())).thenReturn(BatchWriteItemResponse.builder().build());

        // Run the test
        myClassUnderTest.tryBatchWriteItem1();

        // Verify the results
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientReturnsFailure() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
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
                )).build())).thenReturn(BatchWriteItemResponse.builder().unprocessedItems(
                Map.of("TableName", List.of(WriteRequest.builder().build()))).build());

        // Run the test
        myClassUnderTest.tryBatchWriteItem1();

        // Verify the results
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
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
                )).build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
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
                )).build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
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
                )).build())).thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
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
                )).build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
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
                )).build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
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
                )).build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
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
                )).build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
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
                )).build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem2() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class)))
                .thenReturn(BatchWriteItemResponse.builder().build());

        // Run the test
        myClassUnderTest.tryBatchWriteItem2();

        // Verify the results
    }

    @Test
    void testTryBatchWriteItem2_DynamoDbClientReturnsFailure() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class)))
                .thenReturn(BatchWriteItemResponse.builder().unprocessedItems(
                        Map.of("TableName", List.of(WriteRequest.builder().build()))).build());

        // Run the test
        myClassUnderTest.tryBatchWriteItem2();

        // Verify the results
    }

    @Test
    void testTryBatchWriteItem2_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class)))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryBatchWriteItem2());
    }

    @Test
    void testTryBatchWriteItem2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryBatchWriteItem2());
    }

    @Test
    void testTryBatchWriteItem2_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class)))
                .thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.tryBatchWriteItem2());
    }

    @Test
    void testTryBatchWriteItem2_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryBatchWriteItem2());
    }

    @Test
    void testTryBatchWriteItem2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryBatchWriteItem2());
    }

    @Test
    void testTryBatchWriteItem2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryBatchWriteItem2());
    }

    @Test
    void testTryBatchWriteItem2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryBatchWriteItem2());
    }

    @Test
    void testTryBatchWriteItem2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryBatchWriteItem2());
    }

    @Test
    void testTryCreateBackup1() {
        // Setup
        // Configure DynamoDbClient.createBackup(...).
        final CreateBackupResponse createBackupResponse = CreateBackupResponse.builder()
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
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("tableName")
                .backupName("backupName")
                .build())).thenReturn(createBackupResponse);

        // Run the test
        myClassUnderTest.tryCreateBackup1();

        // Verify the results
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("tableName")
                .backupName("backupName")
                .build())).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryCreateBackup1());
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsTableInUseException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("tableName")
                .backupName("backupName")
                .build())).thenThrow(TableInUseException.class);

        // Run the test
        assertThrows(TableInUseException.class, () -> myClassUnderTest.tryCreateBackup1());
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsContinuousBackupsUnavailableException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("tableName")
                .backupName("backupName")
                .build())).thenThrow(ContinuousBackupsUnavailableException.class);

        // Run the test
        assertThrows(ContinuousBackupsUnavailableException.class, () -> myClassUnderTest.tryCreateBackup1());
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsBackupInUseException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("tableName")
                .backupName("backupName")
                .build())).thenThrow(BackupInUseException.class);

        // Run the test
        assertThrows(BackupInUseException.class, () -> myClassUnderTest.tryCreateBackup1());
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("tableName")
                .backupName("backupName")
                .build())).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateBackup1());
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("tableName")
                .backupName("backupName")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryCreateBackup1());
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("tableName")
                .backupName("backupName")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateBackup1());
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("tableName")
                .backupName("backupName")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateBackup1());
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("tableName")
                .backupName("backupName")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryCreateBackup1());
    }

    @Test
    void testTryCreateBackup2() {
        // Setup
        // Configure DynamoDbClient.createBackup(...).
        final CreateBackupResponse createBackupResponse = CreateBackupResponse.builder()
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
        when(mockDynamoDbClient.createBackup(any(Consumer.class))).thenReturn(createBackupResponse);

        // Run the test
        myClassUnderTest.tryCreateBackup2();

        // Verify the results
    }

    @Test
    void testTryCreateBackup2_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class))).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryCreateBackup2());
    }

    @Test
    void testTryCreateBackup2_DynamoDbClientThrowsTableInUseException() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class))).thenThrow(TableInUseException.class);

        // Run the test
        assertThrows(TableInUseException.class, () -> myClassUnderTest.tryCreateBackup2());
    }

    @Test
    void testTryCreateBackup2_DynamoDbClientThrowsContinuousBackupsUnavailableException() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class)))
                .thenThrow(ContinuousBackupsUnavailableException.class);

        // Run the test
        assertThrows(ContinuousBackupsUnavailableException.class, () -> myClassUnderTest.tryCreateBackup2());
    }

    @Test
    void testTryCreateBackup2_DynamoDbClientThrowsBackupInUseException() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class))).thenThrow(BackupInUseException.class);

        // Run the test
        assertThrows(BackupInUseException.class, () -> myClassUnderTest.tryCreateBackup2());
    }

    @Test
    void testTryCreateBackup2_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateBackup2());
    }

    @Test
    void testTryCreateBackup2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryCreateBackup2());
    }

    @Test
    void testTryCreateBackup2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateBackup2());
    }

    @Test
    void testTryCreateBackup2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateBackup2());
    }

    @Test
    void testTryCreateBackup2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryCreateBackup2());
    }

    @Test
    void testTryCreateGlobalTable1() {
        // Setup
        // Configure DynamoDbClient.createGlobalTable(...).
        final CreateGlobalTableResponse createGlobalTableResponse = CreateGlobalTableResponse.builder()
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
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicationGroup(Replica.builder()
                        .regionName("regionName")
                        .build())
                .build())).thenReturn(createGlobalTableResponse);

        // Run the test
        myClassUnderTest.tryCreateGlobalTable1();

        // Verify the results
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicationGroup(Replica.builder()
                        .regionName("regionName")
                        .build())
                .build())).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateGlobalTable1());
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicationGroup(Replica.builder()
                        .regionName("regionName")
                        .build())
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryCreateGlobalTable1());
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsGlobalTableAlreadyExistsException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicationGroup(Replica.builder()
                        .regionName("regionName")
                        .build())
                .build())).thenThrow(GlobalTableAlreadyExistsException.class);

        // Run the test
        assertThrows(GlobalTableAlreadyExistsException.class, () -> myClassUnderTest.tryCreateGlobalTable1());
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicationGroup(Replica.builder()
                        .regionName("regionName")
                        .build())
                .build())).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryCreateGlobalTable1());
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicationGroup(Replica.builder()
                        .regionName("regionName")
                        .build())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateGlobalTable1());
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicationGroup(Replica.builder()
                        .regionName("regionName")
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateGlobalTable1());
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicationGroup(Replica.builder()
                        .regionName("regionName")
                        .build())
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryCreateGlobalTable1());
    }

    @Test
    void testTryCreateGlobalTable2() {
        // Setup
        // Configure DynamoDbClient.createGlobalTable(...).
        final CreateGlobalTableResponse createGlobalTableResponse = CreateGlobalTableResponse.builder()
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
        when(mockDynamoDbClient.createGlobalTable(any(Consumer.class))).thenReturn(createGlobalTableResponse);

        // Run the test
        myClassUnderTest.tryCreateGlobalTable2();

        // Verify the results
    }

    @Test
    void testTryCreateGlobalTable2_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateGlobalTable2());
    }

    @Test
    void testTryCreateGlobalTable2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryCreateGlobalTable2());
    }

    @Test
    void testTryCreateGlobalTable2_DynamoDbClientThrowsGlobalTableAlreadyExistsException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(any(Consumer.class)))
                .thenThrow(GlobalTableAlreadyExistsException.class);

        // Run the test
        assertThrows(GlobalTableAlreadyExistsException.class, () -> myClassUnderTest.tryCreateGlobalTable2());
    }

    @Test
    void testTryCreateGlobalTable2_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(any(Consumer.class))).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryCreateGlobalTable2());
    }

    @Test
    void testTryCreateGlobalTable2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateGlobalTable2());
    }

    @Test
    void testTryCreateGlobalTable2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateGlobalTable2());
    }

    @Test
    void testTryCreateGlobalTable2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryCreateGlobalTable2());
    }

    @Test
    void testTryCreateTable1() {
        // Setup
        // Configure DynamoDbClient.createTable(...).
        final CreateTableResponse createTableResponse = CreateTableResponse.builder()
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
        when(mockDynamoDbClient.createTable(CreateTableRequest.builder()
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
                        .value("value")
                        .build())
                .build())).thenReturn(createTableResponse);

        // Run the test
        myClassUnderTest.tryCreateTable1();

        // Verify the results
    }

    @Test
    void testTryCreateTable1_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.createTable(CreateTableRequest.builder()
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
                        .value("value")
                        .build())
                .build())).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryCreateTable1());
    }

    @Test
    void testTryCreateTable1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.createTable(CreateTableRequest.builder()
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
                        .value("value")
                        .build())
                .build())).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateTable1());
    }

    @Test
    void testTryCreateTable1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.createTable(CreateTableRequest.builder()
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
                        .value("value")
                        .build())
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryCreateTable1());
    }

    @Test
    void testTryCreateTable1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.createTable(CreateTableRequest.builder()
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
                        .value("value")
                        .build())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateTable1());
    }

    @Test
    void testTryCreateTable1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.createTable(CreateTableRequest.builder()
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
                        .value("value")
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateTable1());
    }

    @Test
    void testTryCreateTable1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.createTable(CreateTableRequest.builder()
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
                        .value("value")
                        .build())
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryCreateTable1());
    }

    @Test
    void testTryCreateTable2() {
        // Setup
        // Configure DynamoDbClient.createTable(...).
        final CreateTableResponse createTableResponse = CreateTableResponse.builder()
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
        when(mockDynamoDbClient.createTable(any(Consumer.class))).thenReturn(createTableResponse);

        // Run the test
        myClassUnderTest.tryCreateTable2();

        // Verify the results
    }

    @Test
    void testTryCreateTable2_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.createTable(any(Consumer.class))).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryCreateTable2());
    }

    @Test
    void testTryCreateTable2_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.createTable(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateTable2());
    }

    @Test
    void testTryCreateTable2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.createTable(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryCreateTable2());
    }

    @Test
    void testTryCreateTable2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.createTable(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateTable2());
    }

    @Test
    void testTryCreateTable2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.createTable(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateTable2());
    }

    @Test
    void testTryCreateTable2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.createTable(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryCreateTable2());
    }

    @Test
    void testTryDeleteBackup1() {
        // Setup
        // Configure DynamoDbClient.deleteBackup(...).
        final DeleteBackupResponse deleteBackupResponse = DeleteBackupResponse.builder()
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
        when(mockDynamoDbClient.deleteBackup(DeleteBackupRequest.builder()
                .backupArn("backupArn")
                .build())).thenReturn(deleteBackupResponse);

        // Run the test
        myClassUnderTest.tryDeleteBackup1();

        // Verify the results
    }

    @Test
    void testTryDeleteBackup1_DynamoDbClientThrowsBackupNotFoundException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(DeleteBackupRequest.builder()
                .backupArn("backupArn")
                .build())).thenThrow(BackupNotFoundException.class);

        // Run the test
        assertThrows(BackupNotFoundException.class, () -> myClassUnderTest.tryDeleteBackup1());
    }

    @Test
    void testTryDeleteBackup1_DynamoDbClientThrowsBackupInUseException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(DeleteBackupRequest.builder()
                .backupArn("backupArn")
                .build())).thenThrow(BackupInUseException.class);

        // Run the test
        assertThrows(BackupInUseException.class, () -> myClassUnderTest.tryDeleteBackup1());
    }

    @Test
    void testTryDeleteBackup1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(DeleteBackupRequest.builder()
                .backupArn("backupArn")
                .build())).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryDeleteBackup1());
    }

    @Test
    void testTryDeleteBackup1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(DeleteBackupRequest.builder()
                .backupArn("backupArn")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDeleteBackup1());
    }

    @Test
    void testTryDeleteBackup1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(DeleteBackupRequest.builder()
                .backupArn("backupArn")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBackup1());
    }

    @Test
    void testTryDeleteBackup1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(DeleteBackupRequest.builder()
                .backupArn("backupArn")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBackup1());
    }

    @Test
    void testTryDeleteBackup1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(DeleteBackupRequest.builder()
                .backupArn("backupArn")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDeleteBackup1());
    }

    @Test
    void testTryDeleteBackup2() {
        // Setup
        // Configure DynamoDbClient.deleteBackup(...).
        final DeleteBackupResponse deleteBackupResponse = DeleteBackupResponse.builder()
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
        when(mockDynamoDbClient.deleteBackup(any(Consumer.class))).thenReturn(deleteBackupResponse);

        // Run the test
        myClassUnderTest.tryDeleteBackup2();

        // Verify the results
    }

    @Test
    void testTryDeleteBackup2_DynamoDbClientThrowsBackupNotFoundException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(any(Consumer.class))).thenThrow(BackupNotFoundException.class);

        // Run the test
        assertThrows(BackupNotFoundException.class, () -> myClassUnderTest.tryDeleteBackup2());
    }

    @Test
    void testTryDeleteBackup2_DynamoDbClientThrowsBackupInUseException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(any(Consumer.class))).thenThrow(BackupInUseException.class);

        // Run the test
        assertThrows(BackupInUseException.class, () -> myClassUnderTest.tryDeleteBackup2());
    }

    @Test
    void testTryDeleteBackup2_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryDeleteBackup2());
    }

    @Test
    void testTryDeleteBackup2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDeleteBackup2());
    }

    @Test
    void testTryDeleteBackup2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBackup2());
    }

    @Test
    void testTryDeleteBackup2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBackup2());
    }

    @Test
    void testTryDeleteBackup2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDeleteBackup2());
    }

    @Test
    void testTryDeleteItem1() {
        // Setup
        // Configure DynamoDbClient.deleteItem(...).
        final DeleteItemResponse deleteItemResponse = DeleteItemResponse.builder()
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
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(Map.of(
                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                )).build())).thenReturn(deleteItemResponse);

        // Run the test
        myClassUnderTest.tryDeleteItem1();

        // Verify the results
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(Map.of(
                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                )).build())).thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(Map.of(
                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                )).build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(Map.of(
                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                )).build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(Map.of(
                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                )).build())).thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsTransactionConflictException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(Map.of(
                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                )).build())).thenThrow(TransactionConflictException.class);

        // Run the test
        assertThrows(TransactionConflictException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(Map.of(
                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                )).build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(Map.of(
                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                )).build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(Map.of(
                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                )).build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(Map.of(
                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                )).build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(Map.of(
                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                )).build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem2() {
        // Setup
        // Configure DynamoDbClient.deleteItem(...).
        final DeleteItemResponse deleteItemResponse = DeleteItemResponse.builder()
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
        when(mockDynamoDbClient.deleteItem(any(Consumer.class))).thenReturn(deleteItemResponse);

        // Run the test
        myClassUnderTest.tryDeleteItem2();

        // Verify the results
    }

    @Test
    void testTryDeleteItem2_DynamoDbClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class))).thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, () -> myClassUnderTest.tryDeleteItem2());
    }

    @Test
    void testTryDeleteItem2_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class)))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryDeleteItem2());
    }

    @Test
    void testTryDeleteItem2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDeleteItem2());
    }

    @Test
    void testTryDeleteItem2_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class)))
                .thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.tryDeleteItem2());
    }

    @Test
    void testTryDeleteItem2_DynamoDbClientThrowsTransactionConflictException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class))).thenThrow(TransactionConflictException.class);

        // Run the test
        assertThrows(TransactionConflictException.class, () -> myClassUnderTest.tryDeleteItem2());
    }

    @Test
    void testTryDeleteItem2_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryDeleteItem2());
    }

    @Test
    void testTryDeleteItem2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDeleteItem2());
    }

    @Test
    void testTryDeleteItem2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteItem2());
    }

    @Test
    void testTryDeleteItem2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteItem2());
    }

    @Test
    void testTryDeleteItem2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDeleteItem2());
    }

    @Test
    void testTryDeleteTable1() {
        // Setup
        // Configure DynamoDbClient.deleteTable(...).
        final DeleteTableResponse deleteTableResponse = DeleteTableResponse.builder()
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
        when(mockDynamoDbClient.deleteTable(DeleteTableRequest.builder()
                .tableName("tableName")
                .build())).thenReturn(deleteTableResponse);

        // Run the test
        myClassUnderTest.tryDeleteTable1();

        // Verify the results
    }

    @Test
    void testTryDeleteTable1_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(DeleteTableRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryDeleteTable1());
    }

    @Test
    void testTryDeleteTable1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(DeleteTableRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDeleteTable1());
    }

    @Test
    void testTryDeleteTable1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(DeleteTableRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryDeleteTable1());
    }

    @Test
    void testTryDeleteTable1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(DeleteTableRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDeleteTable1());
    }

    @Test
    void testTryDeleteTable1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(DeleteTableRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteTable1());
    }

    @Test
    void testTryDeleteTable1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(DeleteTableRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteTable1());
    }

    @Test
    void testTryDeleteTable1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(DeleteTableRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDeleteTable1());
    }

    @Test
    void testTryDeleteTable2() {
        // Setup
        // Configure DynamoDbClient.deleteTable(...).
        final DeleteTableResponse deleteTableResponse = DeleteTableResponse.builder()
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
        when(mockDynamoDbClient.deleteTable(any(Consumer.class))).thenReturn(deleteTableResponse);

        // Run the test
        myClassUnderTest.tryDeleteTable2();

        // Verify the results
    }

    @Test
    void testTryDeleteTable2_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(any(Consumer.class))).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryDeleteTable2());
    }

    @Test
    void testTryDeleteTable2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDeleteTable2());
    }

    @Test
    void testTryDeleteTable2_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryDeleteTable2());
    }

    @Test
    void testTryDeleteTable2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDeleteTable2());
    }

    @Test
    void testTryDeleteTable2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteTable2());
    }

    @Test
    void testTryDeleteTable2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteTable2());
    }

    @Test
    void testTryDeleteTable2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDeleteTable2());
    }

    @Test
    void testTryDescribeBackup1() {
        // Setup
        // Configure DynamoDbClient.describeBackup(...).
        final DescribeBackupResponse describeBackupResponse = DescribeBackupResponse.builder()
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
        when(mockDynamoDbClient.describeBackup(DescribeBackupRequest.builder()
                .backupArn("backupArn")
                .build())).thenReturn(describeBackupResponse);

        // Run the test
        myClassUnderTest.tryDescribeBackup1();

        // Verify the results
    }

    @Test
    void testTryDescribeBackup1_DynamoDbClientThrowsBackupNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(DescribeBackupRequest.builder()
                .backupArn("backupArn")
                .build())).thenThrow(BackupNotFoundException.class);

        // Run the test
        assertThrows(BackupNotFoundException.class, () -> myClassUnderTest.tryDescribeBackup1());
    }

    @Test
    void testTryDescribeBackup1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(DescribeBackupRequest.builder()
                .backupArn("backupArn")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeBackup1());
    }

    @Test
    void testTryDescribeBackup1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(DescribeBackupRequest.builder()
                .backupArn("backupArn")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeBackup1());
    }

    @Test
    void testTryDescribeBackup1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(DescribeBackupRequest.builder()
                .backupArn("backupArn")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeBackup1());
    }

    @Test
    void testTryDescribeBackup1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(DescribeBackupRequest.builder()
                .backupArn("backupArn")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeBackup1());
    }

    @Test
    void testTryDescribeBackup2() {
        // Setup
        // Configure DynamoDbClient.describeBackup(...).
        final DescribeBackupResponse describeBackupResponse = DescribeBackupResponse.builder()
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
        when(mockDynamoDbClient.describeBackup(any(Consumer.class))).thenReturn(describeBackupResponse);

        // Run the test
        myClassUnderTest.tryDescribeBackup2();

        // Verify the results
    }

    @Test
    void testTryDescribeBackup2_DynamoDbClientThrowsBackupNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(any(Consumer.class))).thenThrow(BackupNotFoundException.class);

        // Run the test
        assertThrows(BackupNotFoundException.class, () -> myClassUnderTest.tryDescribeBackup2());
    }

    @Test
    void testTryDescribeBackup2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeBackup2());
    }

    @Test
    void testTryDescribeBackup2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeBackup2());
    }

    @Test
    void testTryDescribeBackup2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeBackup2());
    }

    @Test
    void testTryDescribeBackup2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeBackup2());
    }

    @Test
    void testTryDescribeContinuousBackups1() {
        // Setup
        // Configure DynamoDbClient.describeContinuousBackups(...).
        final DescribeContinuousBackupsResponse describeContinuousBackupsResponse = DescribeContinuousBackupsResponse.builder()
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
        when(mockDynamoDbClient.describeContinuousBackups(DescribeContinuousBackupsRequest.builder()
                .tableName("tableName")
                .build())).thenReturn(describeContinuousBackupsResponse);

        // Run the test
        myClassUnderTest.tryDescribeContinuousBackups1();

        // Verify the results
    }

    @Test
    void testTryDescribeContinuousBackups1_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(DescribeContinuousBackupsRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryDescribeContinuousBackups1());
    }

    @Test
    void testTryDescribeContinuousBackups1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(DescribeContinuousBackupsRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeContinuousBackups1());
    }

    @Test
    void testTryDescribeContinuousBackups1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(DescribeContinuousBackupsRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeContinuousBackups1());
    }

    @Test
    void testTryDescribeContinuousBackups1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(DescribeContinuousBackupsRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeContinuousBackups1());
    }

    @Test
    void testTryDescribeContinuousBackups1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(DescribeContinuousBackupsRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeContinuousBackups1());
    }

    @Test
    void testTryDescribeContinuousBackups2() {
        // Setup
        // Configure DynamoDbClient.describeContinuousBackups(...).
        final DescribeContinuousBackupsResponse describeContinuousBackupsResponse = DescribeContinuousBackupsResponse.builder()
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
        when(mockDynamoDbClient.describeContinuousBackups(any(Consumer.class)))
                .thenReturn(describeContinuousBackupsResponse);

        // Run the test
        myClassUnderTest.tryDescribeContinuousBackups2();

        // Verify the results
    }

    @Test
    void testTryDescribeContinuousBackups2_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(any(Consumer.class))).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryDescribeContinuousBackups2());
    }

    @Test
    void testTryDescribeContinuousBackups2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeContinuousBackups2());
    }

    @Test
    void testTryDescribeContinuousBackups2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeContinuousBackups2());
    }

    @Test
    void testTryDescribeContinuousBackups2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeContinuousBackups2());
    }

    @Test
    void testTryDescribeContinuousBackups2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeContinuousBackups2());
    }

    @Test
    void testTryDescribeContributorInsights1() {
        // Setup
        // Configure DynamoDbClient.describeContributorInsights(...).
        final DescribeContributorInsightsResponse describeContributorInsightsResponse = DescribeContributorInsightsResponse.builder()
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
        when(mockDynamoDbClient.describeContributorInsights(DescribeContributorInsightsRequest.builder()
                .tableName("tableName")
                .indexName("indexName")
                .build())).thenReturn(describeContributorInsightsResponse);

        // Run the test
        myClassUnderTest.tryDescribeContributorInsights1();

        // Verify the results
    }

    @Test
    void testTryDescribeContributorInsights1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeContributorInsights(DescribeContributorInsightsRequest.builder()
                .tableName("tableName")
                .indexName("indexName")
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDescribeContributorInsights1());
    }

    @Test
    void testTryDescribeContributorInsights1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeContributorInsights(DescribeContributorInsightsRequest.builder()
                .tableName("tableName")
                .indexName("indexName")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeContributorInsights1());
    }

    @Test
    void testTryDescribeContributorInsights1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeContributorInsights(DescribeContributorInsightsRequest.builder()
                .tableName("tableName")
                .indexName("indexName")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeContributorInsights1());
    }

    @Test
    void testTryDescribeContributorInsights1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeContributorInsights(DescribeContributorInsightsRequest.builder()
                .tableName("tableName")
                .indexName("indexName")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeContributorInsights1());
    }

    @Test
    void testTryDescribeContributorInsights1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeContributorInsights(DescribeContributorInsightsRequest.builder()
                .tableName("tableName")
                .indexName("indexName")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeContributorInsights1());
    }

    @Test
    void testTryDescribeContributorInsights2() {
        // Setup
        // Configure DynamoDbClient.describeContributorInsights(...).
        final DescribeContributorInsightsResponse describeContributorInsightsResponse = DescribeContributorInsightsResponse.builder()
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
        when(mockDynamoDbClient.describeContributorInsights(any(Consumer.class)))
                .thenReturn(describeContributorInsightsResponse);

        // Run the test
        myClassUnderTest.tryDescribeContributorInsights2();

        // Verify the results
    }

    @Test
    void testTryDescribeContributorInsights2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeContributorInsights(any(Consumer.class)))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDescribeContributorInsights2());
    }

    @Test
    void testTryDescribeContributorInsights2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeContributorInsights(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeContributorInsights2());
    }

    @Test
    void testTryDescribeContributorInsights2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeContributorInsights(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeContributorInsights2());
    }

    @Test
    void testTryDescribeContributorInsights2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeContributorInsights(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeContributorInsights2());
    }

    @Test
    void testTryDescribeContributorInsights2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeContributorInsights(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeContributorInsights2());
    }

    @Test
    void testTryDescribeEndpoints1() {
        // Setup
        // Configure DynamoDbClient.describeEndpoints(...).
        final DescribeEndpointsResponse describeEndpointsResponse = DescribeEndpointsResponse.builder()
                .endpoints(Endpoint.builder()
                        .address("address")
                        .cachePeriodInMinutes(0L)
                        .build())
                .build();
        when(mockDynamoDbClient.describeEndpoints()).thenReturn(describeEndpointsResponse);

        // Run the test
        myClassUnderTest.tryDescribeEndpoints1();

        // Verify the results
    }

    @Test
    void testTryDescribeEndpoints1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeEndpoints()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeEndpoints1());
    }

    @Test
    void testTryDescribeEndpoints1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeEndpoints()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeEndpoints1());
    }

    @Test
    void testTryDescribeEndpoints1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeEndpoints()).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeEndpoints1());
    }

    @Test
    void testTryDescribeEndpoints2() {
        // Setup
        // Configure DynamoDbClient.describeEndpoints(...).
        final DescribeEndpointsResponse describeEndpointsResponse = DescribeEndpointsResponse.builder()
                .endpoints(Endpoint.builder()
                        .address("address")
                        .cachePeriodInMinutes(0L)
                        .build())
                .build();
        when(mockDynamoDbClient.describeEndpoints(DescribeEndpointsRequest.builder().build()))
                .thenReturn(describeEndpointsResponse);

        // Run the test
        myClassUnderTest.tryDescribeEndpoints2();

        // Verify the results
    }

    @Test
    void testTryDescribeEndpoints2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeEndpoints(DescribeEndpointsRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeEndpoints2());
    }

    @Test
    void testTryDescribeEndpoints2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeEndpoints(DescribeEndpointsRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeEndpoints2());
    }

    @Test
    void testTryDescribeEndpoints2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeEndpoints(DescribeEndpointsRequest.builder().build()))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeEndpoints2());
    }

    @Test
    void testTryDescribeEndpoints3() {
        // Setup
        // Configure DynamoDbClient.describeEndpoints(...).
        final DescribeEndpointsResponse describeEndpointsResponse = DescribeEndpointsResponse.builder()
                .endpoints(Endpoint.builder()
                        .address("address")
                        .cachePeriodInMinutes(0L)
                        .build())
                .build();
        when(mockDynamoDbClient.describeEndpoints(any(Consumer.class))).thenReturn(describeEndpointsResponse);

        // Run the test
        myClassUnderTest.tryDescribeEndpoints3();

        // Verify the results
    }

    @Test
    void testTryDescribeEndpoints3_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeEndpoints(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeEndpoints3());
    }

    @Test
    void testTryDescribeEndpoints3_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeEndpoints(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeEndpoints3());
    }

    @Test
    void testTryDescribeEndpoints3_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeEndpoints(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeEndpoints3());
    }

    @Test
    void testTryDescribeGlobalTable1() {
        // Setup
        // Configure DynamoDbClient.describeGlobalTable(...).
        final DescribeGlobalTableResponse describeGlobalTableResponse = DescribeGlobalTableResponse.builder()
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
        when(mockDynamoDbClient.describeGlobalTable(DescribeGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .build())).thenReturn(describeGlobalTableResponse);

        // Run the test
        myClassUnderTest.tryDescribeGlobalTable1();

        // Verify the results
    }

    @Test
    void testTryDescribeGlobalTable1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(DescribeGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeGlobalTable1());
    }

    @Test
    void testTryDescribeGlobalTable1_DynamoDbClientThrowsGlobalTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(DescribeGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .build())).thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, () -> myClassUnderTest.tryDescribeGlobalTable1());
    }

    @Test
    void testTryDescribeGlobalTable1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(DescribeGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeGlobalTable1());
    }

    @Test
    void testTryDescribeGlobalTable1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(DescribeGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeGlobalTable1());
    }

    @Test
    void testTryDescribeGlobalTable1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(DescribeGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeGlobalTable1());
    }

    @Test
    void testTryDescribeGlobalTable2() {
        // Setup
        // Configure DynamoDbClient.describeGlobalTable(...).
        final DescribeGlobalTableResponse describeGlobalTableResponse = DescribeGlobalTableResponse.builder()
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
        when(mockDynamoDbClient.describeGlobalTable(any(Consumer.class))).thenReturn(describeGlobalTableResponse);

        // Run the test
        myClassUnderTest.tryDescribeGlobalTable2();

        // Verify the results
    }

    @Test
    void testTryDescribeGlobalTable2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeGlobalTable2());
    }

    @Test
    void testTryDescribeGlobalTable2_DynamoDbClientThrowsGlobalTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(any(Consumer.class))).thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, () -> myClassUnderTest.tryDescribeGlobalTable2());
    }

    @Test
    void testTryDescribeGlobalTable2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeGlobalTable2());
    }

    @Test
    void testTryDescribeGlobalTable2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeGlobalTable2());
    }

    @Test
    void testTryDescribeGlobalTable2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeGlobalTable2());
    }

    @Test
    void testTryDescribeGlobalTableSettings1() {
        // Setup
        // Configure DynamoDbClient.describeGlobalTableSettings(...).
        final DescribeGlobalTableSettingsResponse describeGlobalTableSettingsResponse = DescribeGlobalTableSettingsResponse.builder()
                .globalTableName("globalTableName")
                .replicaSettings(ReplicaSettingsDescription.builder().build())
                .build();
        when(mockDynamoDbClient.describeGlobalTableSettings(DescribeGlobalTableSettingsRequest.builder()
                .globalTableName("globalTableName")
                .build())).thenReturn(describeGlobalTableSettingsResponse);

        // Run the test
        myClassUnderTest.tryDescribeGlobalTableSettings1();

        // Verify the results
    }

    @Test
    void testTryDescribeGlobalTableSettings1_DynamoDbClientThrowsGlobalTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(DescribeGlobalTableSettingsRequest.builder()
                .globalTableName("globalTableName")
                .build())).thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings1());
    }

    @Test
    void testTryDescribeGlobalTableSettings1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(DescribeGlobalTableSettingsRequest.builder()
                .globalTableName("globalTableName")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings1());
    }

    @Test
    void testTryDescribeGlobalTableSettings1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(DescribeGlobalTableSettingsRequest.builder()
                .globalTableName("globalTableName")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings1());
    }

    @Test
    void testTryDescribeGlobalTableSettings1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(DescribeGlobalTableSettingsRequest.builder()
                .globalTableName("globalTableName")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings1());
    }

    @Test
    void testTryDescribeGlobalTableSettings1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(DescribeGlobalTableSettingsRequest.builder()
                .globalTableName("globalTableName")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings1());
    }

    @Test
    void testTryDescribeGlobalTableSettings2() {
        // Setup
        // Configure DynamoDbClient.describeGlobalTableSettings(...).
        final DescribeGlobalTableSettingsResponse describeGlobalTableSettingsResponse = DescribeGlobalTableSettingsResponse.builder()
                .globalTableName("globalTableName")
                .replicaSettings(ReplicaSettingsDescription.builder().build())
                .build();
        when(mockDynamoDbClient.describeGlobalTableSettings(any(Consumer.class)))
                .thenReturn(describeGlobalTableSettingsResponse);

        // Run the test
        myClassUnderTest.tryDescribeGlobalTableSettings2();

        // Verify the results
    }

    @Test
    void testTryDescribeGlobalTableSettings2_DynamoDbClientThrowsGlobalTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(any(Consumer.class)))
                .thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings2());
    }

    @Test
    void testTryDescribeGlobalTableSettings2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings2());
    }

    @Test
    void testTryDescribeGlobalTableSettings2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings2());
    }

    @Test
    void testTryDescribeGlobalTableSettings2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings2());
    }

    @Test
    void testTryDescribeGlobalTableSettings2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings2());
    }

    @Test
    void testTryDescribeLimits1() {
        // Setup
        // Configure DynamoDbClient.describeLimits(...).
        final DescribeLimitsResponse describeLimitsResponse = DescribeLimitsResponse.builder()
                .accountMaxReadCapacityUnits(0L)
                .accountMaxWriteCapacityUnits(0L)
                .tableMaxReadCapacityUnits(0L)
                .tableMaxWriteCapacityUnits(0L)
                .build();
        when(mockDynamoDbClient.describeLimits()).thenReturn(describeLimitsResponse);

        // Run the test
        myClassUnderTest.tryDescribeLimits1();

        // Verify the results
    }

    @Test
    void testTryDescribeLimits1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeLimits()).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeLimits1());
    }

    @Test
    void testTryDescribeLimits1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeLimits()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeLimits1());
    }

    @Test
    void testTryDescribeLimits1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeLimits()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeLimits1());
    }

    @Test
    void testTryDescribeLimits1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeLimits()).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeLimits1());
    }

    @Test
    void testTryDescribeLimits2() {
        // Setup
        // Configure DynamoDbClient.describeLimits(...).
        final DescribeLimitsResponse describeLimitsResponse = DescribeLimitsResponse.builder()
                .accountMaxReadCapacityUnits(0L)
                .accountMaxWriteCapacityUnits(0L)
                .tableMaxReadCapacityUnits(0L)
                .tableMaxWriteCapacityUnits(0L)
                .build();
        when(mockDynamoDbClient.describeLimits(DescribeLimitsRequest.builder().build()))
                .thenReturn(describeLimitsResponse);

        // Run the test
        myClassUnderTest.tryDescribeLimits2();

        // Verify the results
    }

    @Test
    void testTryDescribeLimits2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeLimits(DescribeLimitsRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeLimits2());
    }

    @Test
    void testTryDescribeLimits2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeLimits(DescribeLimitsRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeLimits2());
    }

    @Test
    void testTryDescribeLimits2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeLimits(DescribeLimitsRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeLimits2());
    }

    @Test
    void testTryDescribeLimits2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeLimits(DescribeLimitsRequest.builder().build()))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeLimits2());
    }

    @Test
    void testTryDescribeLimits3() {
        // Setup
        // Configure DynamoDbClient.describeLimits(...).
        final DescribeLimitsResponse describeLimitsResponse = DescribeLimitsResponse.builder()
                .accountMaxReadCapacityUnits(0L)
                .accountMaxWriteCapacityUnits(0L)
                .tableMaxReadCapacityUnits(0L)
                .tableMaxWriteCapacityUnits(0L)
                .build();
        when(mockDynamoDbClient.describeLimits(any(Consumer.class))).thenReturn(describeLimitsResponse);

        // Run the test
        myClassUnderTest.tryDescribeLimits3();

        // Verify the results
    }

    @Test
    void testTryDescribeLimits3_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeLimits(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeLimits3());
    }

    @Test
    void testTryDescribeLimits3_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeLimits(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeLimits3());
    }

    @Test
    void testTryDescribeLimits3_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeLimits(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeLimits3());
    }

    @Test
    void testTryDescribeLimits3_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeLimits(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeLimits3());
    }

    @Test
    void testTryDescribeTable1() {
        // Setup
        // Configure DynamoDbClient.describeTable(...).
        final DescribeTableResponse describeTableResponse = DescribeTableResponse.builder()
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
        when(mockDynamoDbClient.describeTable(DescribeTableRequest.builder()
                .tableName("tableName")
                .build())).thenReturn(describeTableResponse);

        // Run the test
        myClassUnderTest.tryDescribeTable1();

        // Verify the results
    }

    @Test
    void testTryDescribeTable1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeTable(DescribeTableRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDescribeTable1());
    }

    @Test
    void testTryDescribeTable1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeTable(DescribeTableRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeTable1());
    }

    @Test
    void testTryDescribeTable1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeTable(DescribeTableRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeTable1());
    }

    @Test
    void testTryDescribeTable1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeTable(DescribeTableRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeTable1());
    }

    @Test
    void testTryDescribeTable1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeTable(DescribeTableRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeTable1());
    }

    @Test
    void testTryDescribeTable2() {
        // Setup
        // Configure DynamoDbClient.describeTable(...).
        final DescribeTableResponse describeTableResponse = DescribeTableResponse.builder()
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
        when(mockDynamoDbClient.describeTable(any(Consumer.class))).thenReturn(describeTableResponse);

        // Run the test
        myClassUnderTest.tryDescribeTable2();

        // Verify the results
    }

    @Test
    void testTryDescribeTable2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeTable(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDescribeTable2());
    }

    @Test
    void testTryDescribeTable2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeTable(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeTable2());
    }

    @Test
    void testTryDescribeTable2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeTable(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeTable2());
    }

    @Test
    void testTryDescribeTable2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeTable(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeTable2());
    }

    @Test
    void testTryDescribeTable2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeTable(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeTable2());
    }

    @Test
    void testTryDescribeTableReplicaAutoScaling1() {
        // Setup
        // Configure DynamoDbClient.describeTableReplicaAutoScaling(...).
        final DescribeTableReplicaAutoScalingResponse describeTableReplicaAutoScalingResponse = DescribeTableReplicaAutoScalingResponse.builder()
                .tableAutoScalingDescription(TableAutoScalingDescription.builder()
                        .tableName("tableName")
                        .tableStatus(TableStatus.CREATING)
                        .replicas(ReplicaAutoScalingDescription.builder().build())
                        .build())
                .build();
        when(mockDynamoDbClient.describeTableReplicaAutoScaling(DescribeTableReplicaAutoScalingRequest.builder()
                .tableName("tableName")
                .build())).thenReturn(describeTableReplicaAutoScalingResponse);

        // Run the test
        myClassUnderTest.tryDescribeTableReplicaAutoScaling1();

        // Verify the results
    }

    @Test
    void testTryDescribeTableReplicaAutoScaling1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeTableReplicaAutoScaling(DescribeTableReplicaAutoScalingRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDescribeTableReplicaAutoScaling1());
    }

    @Test
    void testTryDescribeTableReplicaAutoScaling1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeTableReplicaAutoScaling(DescribeTableReplicaAutoScalingRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeTableReplicaAutoScaling1());
    }

    @Test
    void testTryDescribeTableReplicaAutoScaling1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeTableReplicaAutoScaling(DescribeTableReplicaAutoScalingRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeTableReplicaAutoScaling1());
    }

    @Test
    void testTryDescribeTableReplicaAutoScaling1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeTableReplicaAutoScaling(DescribeTableReplicaAutoScalingRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeTableReplicaAutoScaling1());
    }

    @Test
    void testTryDescribeTableReplicaAutoScaling1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeTableReplicaAutoScaling(DescribeTableReplicaAutoScalingRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeTableReplicaAutoScaling1());
    }

    @Test
    void testTryDescribeTableReplicaAutoScaling2() {
        // Setup
        // Configure DynamoDbClient.describeTableReplicaAutoScaling(...).
        final DescribeTableReplicaAutoScalingResponse describeTableReplicaAutoScalingResponse = DescribeTableReplicaAutoScalingResponse.builder()
                .tableAutoScalingDescription(TableAutoScalingDescription.builder()
                        .tableName("tableName")
                        .tableStatus(TableStatus.CREATING)
                        .replicas(ReplicaAutoScalingDescription.builder().build())
                        .build())
                .build();
        when(mockDynamoDbClient.describeTableReplicaAutoScaling(any(Consumer.class)))
                .thenReturn(describeTableReplicaAutoScalingResponse);

        // Run the test
        myClassUnderTest.tryDescribeTableReplicaAutoScaling2();

        // Verify the results
    }

    @Test
    void testTryDescribeTableReplicaAutoScaling2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeTableReplicaAutoScaling(any(Consumer.class)))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDescribeTableReplicaAutoScaling2());
    }

    @Test
    void testTryDescribeTableReplicaAutoScaling2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeTableReplicaAutoScaling(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeTableReplicaAutoScaling2());
    }

    @Test
    void testTryDescribeTableReplicaAutoScaling2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeTableReplicaAutoScaling(any(Consumer.class)))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeTableReplicaAutoScaling2());
    }

    @Test
    void testTryDescribeTableReplicaAutoScaling2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeTableReplicaAutoScaling(any(Consumer.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeTableReplicaAutoScaling2());
    }

    @Test
    void testTryDescribeTableReplicaAutoScaling2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeTableReplicaAutoScaling(any(Consumer.class)))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeTableReplicaAutoScaling2());
    }

    @Test
    void testTryDescribeTimeToLive1() {
        // Setup
        // Configure DynamoDbClient.describeTimeToLive(...).
        final DescribeTimeToLiveResponse describeTimeToLiveResponse = DescribeTimeToLiveResponse.builder()
                .timeToLiveDescription(TimeToLiveDescription.builder()
                        .timeToLiveStatus(TimeToLiveStatus.ENABLING)
                        .attributeName("attributeName")
                        .build())
                .build();
        when(mockDynamoDbClient.describeTimeToLive(DescribeTimeToLiveRequest.builder()
                .tableName("tableName")
                .build())).thenReturn(describeTimeToLiveResponse);

        // Run the test
        myClassUnderTest.tryDescribeTimeToLive1();

        // Verify the results
    }

    @Test
    void testTryDescribeTimeToLive1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(DescribeTimeToLiveRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDescribeTimeToLive1());
    }

    @Test
    void testTryDescribeTimeToLive1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(DescribeTimeToLiveRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeTimeToLive1());
    }

    @Test
    void testTryDescribeTimeToLive1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(DescribeTimeToLiveRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeTimeToLive1());
    }

    @Test
    void testTryDescribeTimeToLive1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(DescribeTimeToLiveRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeTimeToLive1());
    }

    @Test
    void testTryDescribeTimeToLive1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(DescribeTimeToLiveRequest.builder()
                .tableName("tableName")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeTimeToLive1());
    }

    @Test
    void testTryDescribeTimeToLive2() {
        // Setup
        // Configure DynamoDbClient.describeTimeToLive(...).
        final DescribeTimeToLiveResponse describeTimeToLiveResponse = DescribeTimeToLiveResponse.builder()
                .timeToLiveDescription(TimeToLiveDescription.builder()
                        .timeToLiveStatus(TimeToLiveStatus.ENABLING)
                        .attributeName("attributeName")
                        .build())
                .build();
        when(mockDynamoDbClient.describeTimeToLive(any(Consumer.class))).thenReturn(describeTimeToLiveResponse);

        // Run the test
        myClassUnderTest.tryDescribeTimeToLive2();

        // Verify the results
    }

    @Test
    void testTryDescribeTimeToLive2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDescribeTimeToLive2());
    }

    @Test
    void testTryDescribeTimeToLive2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeTimeToLive2());
    }

    @Test
    void testTryDescribeTimeToLive2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeTimeToLive2());
    }

    @Test
    void testTryDescribeTimeToLive2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeTimeToLive2());
    }

    @Test
    void testTryDescribeTimeToLive2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeTimeToLive2());
    }

    @Test
    void testTryGetItem1() {
        // Setup
        // Configure DynamoDbClient.getItem(...).
        final GetItemResponse getItemResponse = GetItemResponse.builder()
                .item(Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherKeyValue").build())
                )).build();
        when(mockDynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("TableName")
                .key(Map.of("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()))
                .build())).thenReturn(getItemResponse);

        // Run the test
        myClassUnderTest.tryGetItem1();

        // Verify the results
    }

    @Test
    void testTryGetItem1_DynamoDbClientReturnsNoItem() {
        // Setup
        when(mockDynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("TableName")
                .key(Map.of("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()))
                .build())).thenReturn(GetItemResponse.builder().build());

        // Run the test
        myClassUnderTest.tryGetItem1();

        // Verify the results
    }

    @Test
    void testTryGetItem1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("TableName")
                .key(Map.of("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()))
                .build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryGetItem1());
    }

    @Test
    void testTryGetItem1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("TableName")
                .key(Map.of("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()))
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryGetItem1());
    }

    @Test
    void testTryGetItem1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("TableName")
                .key(Map.of("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()))
                .build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryGetItem1());
    }

    @Test
    void testTryGetItem1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("TableName")
                .key(Map.of("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()))
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryGetItem1());
    }

    @Test
    void testTryGetItem1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("TableName")
                .key(Map.of("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()))
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetItem1());
    }

    @Test
    void testTryGetItem1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("TableName")
                .key(Map.of("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()))
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetItem1());
    }

    @Test
    void testTryGetItem1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("TableName")
                .key(Map.of("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()))
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryGetItem1());
    }

    @Test
    void testTryGetItem2() {
        // Setup
        // Configure DynamoDbClient.getItem(...).
        final GetItemResponse getItemResponse = GetItemResponse.builder()
                .item(Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherKeyValue").build())
                )).build();
        when(mockDynamoDbClient.getItem(any(Consumer.class))).thenReturn(getItemResponse);

        // Run the test
        myClassUnderTest.tryGetItem2();

        // Verify the results
    }

    @Test
    void testTryGetItem2_DynamoDbClientReturnsNoItem() {
        // Setup
        when(mockDynamoDbClient.getItem(any(Consumer.class))).thenReturn(GetItemResponse.builder().build());

        // Run the test
        myClassUnderTest.tryGetItem2();

        // Verify the results
    }

    @Test
    void testTryGetItem2_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.getItem(any(Consumer.class))).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryGetItem2());
    }

    @Test
    void testTryGetItem2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.getItem(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryGetItem2());
    }

    @Test
    void testTryGetItem2_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.getItem(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryGetItem2());
    }

    @Test
    void testTryGetItem2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.getItem(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryGetItem2());
    }

    @Test
    void testTryGetItem2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.getItem(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetItem2());
    }

    @Test
    void testTryGetItem2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.getItem(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetItem2());
    }

    @Test
    void testTryGetItem2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.getItem(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryGetItem2());
    }

    @Test
    void testTryListBackups1() {
        // Setup
        // Configure DynamoDbClient.listBackups(...).
        final ListBackupsResponse listBackupsResponse = ListBackupsResponse.builder()
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
        when(mockDynamoDbClient.listBackups()).thenReturn(listBackupsResponse);

        // Run the test
        myClassUnderTest.tryListBackups1();

        // Verify the results
    }

    @Test
    void testTryListBackups1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listBackups()).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListBackups1());
    }

    @Test
    void testTryListBackups1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listBackups()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListBackups1());
    }

    @Test
    void testTryListBackups1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listBackups()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBackups1());
    }

    @Test
    void testTryListBackups1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listBackups()).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListBackups1());
    }

    @Test
    void testTryListBackups2() {
        // Setup
        // Configure DynamoDbClient.listBackups(...).
        final ListBackupsResponse listBackupsResponse = ListBackupsResponse.builder()
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
        when(mockDynamoDbClient.listBackups(ListBackupsRequest.builder()
                .tableName("tableName")
                .limit(0)
                .timeRangeLowerBound(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .timeRangeUpperBound(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .exclusiveStartBackupArn("exclusiveStartBackupArn")
                .backupType(BackupTypeFilter.USER)
                .build())).thenReturn(listBackupsResponse);

        // Run the test
        myClassUnderTest.tryListBackups2();

        // Verify the results
    }

    @Test
    void testTryListBackups2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listBackups(ListBackupsRequest.builder()
                .tableName("tableName")
                .limit(0)
                .timeRangeLowerBound(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .timeRangeUpperBound(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .exclusiveStartBackupArn("exclusiveStartBackupArn")
                .backupType(BackupTypeFilter.USER)
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListBackups2());
    }

    @Test
    void testTryListBackups2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listBackups(ListBackupsRequest.builder()
                .tableName("tableName")
                .limit(0)
                .timeRangeLowerBound(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .timeRangeUpperBound(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .exclusiveStartBackupArn("exclusiveStartBackupArn")
                .backupType(BackupTypeFilter.USER)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListBackups2());
    }

    @Test
    void testTryListBackups2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listBackups(ListBackupsRequest.builder()
                .tableName("tableName")
                .limit(0)
                .timeRangeLowerBound(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .timeRangeUpperBound(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .exclusiveStartBackupArn("exclusiveStartBackupArn")
                .backupType(BackupTypeFilter.USER)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBackups2());
    }

    @Test
    void testTryListBackups2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listBackups(ListBackupsRequest.builder()
                .tableName("tableName")
                .limit(0)
                .timeRangeLowerBound(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .timeRangeUpperBound(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .exclusiveStartBackupArn("exclusiveStartBackupArn")
                .backupType(BackupTypeFilter.USER)
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListBackups2());
    }

    @Test
    void testTryListBackups3() {
        // Setup
        // Configure DynamoDbClient.listBackups(...).
        final ListBackupsResponse listBackupsResponse = ListBackupsResponse.builder()
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
        when(mockDynamoDbClient.listBackups(any(Consumer.class))).thenReturn(listBackupsResponse);

        // Run the test
        myClassUnderTest.tryListBackups3();

        // Verify the results
    }

    @Test
    void testTryListBackups3_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listBackups(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListBackups3());
    }

    @Test
    void testTryListBackups3_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listBackups(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListBackups3());
    }

    @Test
    void testTryListBackups3_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listBackups(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBackups3());
    }

    @Test
    void testTryListBackups3_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listBackups(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListBackups3());
    }

    @Test
    void testTryListContributorInsights1() {
        // Setup
        // Configure DynamoDbClient.listContributorInsights(...).
        final ListContributorInsightsResponse listContributorInsightsResponse = ListContributorInsightsResponse.builder()
                .contributorInsightsSummaries(ContributorInsightsSummary.builder()
                        .tableName("tableName")
                        .indexName("indexName")
                        .contributorInsightsStatus(ContributorInsightsStatus.ENABLING)
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockDynamoDbClient.listContributorInsights(ListContributorInsightsRequest.builder()
                .tableName("tableName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(listContributorInsightsResponse);

        // Run the test
        myClassUnderTest.tryListContributorInsights1();

        // Verify the results
    }

    @Test
    void testTryListContributorInsights1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsights(ListContributorInsightsRequest.builder()
                .tableName("tableName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryListContributorInsights1());
    }

    @Test
    void testTryListContributorInsights1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsights(ListContributorInsightsRequest.builder()
                .tableName("tableName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListContributorInsights1());
    }

    @Test
    void testTryListContributorInsights1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsights(ListContributorInsightsRequest.builder()
                .tableName("tableName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListContributorInsights1());
    }

    @Test
    void testTryListContributorInsights1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsights(ListContributorInsightsRequest.builder()
                .tableName("tableName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListContributorInsights1());
    }

    @Test
    void testTryListContributorInsights1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsights(ListContributorInsightsRequest.builder()
                .tableName("tableName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListContributorInsights1());
    }

    @Test
    void testTryListContributorInsights2() {
        // Setup
        // Configure DynamoDbClient.listContributorInsights(...).
        final ListContributorInsightsResponse listContributorInsightsResponse = ListContributorInsightsResponse.builder()
                .contributorInsightsSummaries(ContributorInsightsSummary.builder()
                        .tableName("tableName")
                        .indexName("indexName")
                        .contributorInsightsStatus(ContributorInsightsStatus.ENABLING)
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockDynamoDbClient.listContributorInsights(any(Consumer.class)))
                .thenReturn(listContributorInsightsResponse);

        // Run the test
        myClassUnderTest.tryListContributorInsights2();

        // Verify the results
    }

    @Test
    void testTryListContributorInsights2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsights(any(Consumer.class)))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryListContributorInsights2());
    }

    @Test
    void testTryListContributorInsights2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsights(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListContributorInsights2());
    }

    @Test
    void testTryListContributorInsights2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsights(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListContributorInsights2());
    }

    @Test
    void testTryListContributorInsights2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsights(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListContributorInsights2());
    }

    @Test
    void testTryListContributorInsights2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsights(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListContributorInsights2());
    }

    @Test
    void testTryListContributorInsightsPaginator1() {
        // Setup
        // Configure DynamoDbClient.listContributorInsightsPaginator(...).
        final ListContributorInsightsIterable mockListContributorInsightsIterable = mock(
                ListContributorInsightsIterable.class);
        when(mockDynamoDbClient.listContributorInsightsPaginator(ListContributorInsightsRequest.builder()
                .tableName("tableName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(mockListContributorInsightsIterable);

        // Run the test
        myClassUnderTest.tryListContributorInsightsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListContributorInsightsPaginator1_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.listContributorInsightsPaginator(...).
        final ListContributorInsightsIterable mockListContributorInsightsIterable = mock(
                ListContributorInsightsIterable.class);
        when(mockDynamoDbClient.listContributorInsightsPaginator(ListContributorInsightsRequest.builder()
                .tableName("tableName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(mockListContributorInsightsIterable);

        // Run the test
        myClassUnderTest.tryListContributorInsightsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListContributorInsightsPaginator1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsightsPaginator(ListContributorInsightsRequest.builder()
                .tableName("tableName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryListContributorInsightsPaginator1());
    }

    @Test
    void testTryListContributorInsightsPaginator1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsightsPaginator(ListContributorInsightsRequest.builder()
                .tableName("tableName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListContributorInsightsPaginator1());
    }

    @Test
    void testTryListContributorInsightsPaginator1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsightsPaginator(ListContributorInsightsRequest.builder()
                .tableName("tableName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListContributorInsightsPaginator1());
    }

    @Test
    void testTryListContributorInsightsPaginator1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsightsPaginator(ListContributorInsightsRequest.builder()
                .tableName("tableName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListContributorInsightsPaginator1());
    }

    @Test
    void testTryListContributorInsightsPaginator1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsightsPaginator(ListContributorInsightsRequest.builder()
                .tableName("tableName")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListContributorInsightsPaginator1());
    }

    @Test
    void testTryListContributorInsightsPaginator2() {
        // Setup
        // Configure DynamoDbClient.listContributorInsightsPaginator(...).
        final ListContributorInsightsIterable mockListContributorInsightsIterable = mock(
                ListContributorInsightsIterable.class);
        when(mockDynamoDbClient.listContributorInsightsPaginator(any(Consumer.class)))
                .thenReturn(mockListContributorInsightsIterable);

        // Run the test
        myClassUnderTest.tryListContributorInsightsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListContributorInsightsPaginator2_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.listContributorInsightsPaginator(...).
        final ListContributorInsightsIterable mockListContributorInsightsIterable = mock(
                ListContributorInsightsIterable.class);
        when(mockDynamoDbClient.listContributorInsightsPaginator(any(Consumer.class)))
                .thenReturn(mockListContributorInsightsIterable);

        // Run the test
        myClassUnderTest.tryListContributorInsightsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListContributorInsightsPaginator2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsightsPaginator(any(Consumer.class)))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryListContributorInsightsPaginator2());
    }

    @Test
    void testTryListContributorInsightsPaginator2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsightsPaginator(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListContributorInsightsPaginator2());
    }

    @Test
    void testTryListContributorInsightsPaginator2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsightsPaginator(any(Consumer.class)))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListContributorInsightsPaginator2());
    }

    @Test
    void testTryListContributorInsightsPaginator2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsightsPaginator(any(Consumer.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListContributorInsightsPaginator2());
    }

    @Test
    void testTryListContributorInsightsPaginator2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listContributorInsightsPaginator(any(Consumer.class)))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListContributorInsightsPaginator2());
    }

    @Test
    void testTryListGlobalTables1() {
        // Setup
        // Configure DynamoDbClient.listGlobalTables(...).
        final ListGlobalTablesResponse listGlobalTablesResponse = ListGlobalTablesResponse.builder()
                .globalTables(GlobalTable.builder()
                        .globalTableName("globalTableName")
                        .replicationGroup(Replica.builder()
                                .regionName("regionName")
                                .build())
                        .build())
                .lastEvaluatedGlobalTableName("lastEvaluatedGlobalTableName")
                .build();
        when(mockDynamoDbClient.listGlobalTables()).thenReturn(listGlobalTablesResponse);

        // Run the test
        myClassUnderTest.tryListGlobalTables1();

        // Verify the results
    }

    @Test
    void testTryListGlobalTables1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables()).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListGlobalTables1());
    }

    @Test
    void testTryListGlobalTables1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListGlobalTables1());
    }

    @Test
    void testTryListGlobalTables1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListGlobalTables1());
    }

    @Test
    void testTryListGlobalTables1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables()).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListGlobalTables1());
    }

    @Test
    void testTryListGlobalTables2() {
        // Setup
        // Configure DynamoDbClient.listGlobalTables(...).
        final ListGlobalTablesResponse listGlobalTablesResponse = ListGlobalTablesResponse.builder()
                .globalTables(GlobalTable.builder()
                        .globalTableName("globalTableName")
                        .replicationGroup(Replica.builder()
                                .regionName("regionName")
                                .build())
                        .build())
                .lastEvaluatedGlobalTableName("lastEvaluatedGlobalTableName")
                .build();
        when(mockDynamoDbClient.listGlobalTables(ListGlobalTablesRequest.builder()
                .exclusiveStartGlobalTableName("exclusiveStartGlobalTableName")
                .limit(0)
                .regionName("regionName")
                .build())).thenReturn(listGlobalTablesResponse);

        // Run the test
        myClassUnderTest.tryListGlobalTables2();

        // Verify the results
    }

    @Test
    void testTryListGlobalTables2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables(ListGlobalTablesRequest.builder()
                .exclusiveStartGlobalTableName("exclusiveStartGlobalTableName")
                .limit(0)
                .regionName("regionName")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListGlobalTables2());
    }

    @Test
    void testTryListGlobalTables2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables(ListGlobalTablesRequest.builder()
                .exclusiveStartGlobalTableName("exclusiveStartGlobalTableName")
                .limit(0)
                .regionName("regionName")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListGlobalTables2());
    }

    @Test
    void testTryListGlobalTables2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables(ListGlobalTablesRequest.builder()
                .exclusiveStartGlobalTableName("exclusiveStartGlobalTableName")
                .limit(0)
                .regionName("regionName")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListGlobalTables2());
    }

    @Test
    void testTryListGlobalTables2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables(ListGlobalTablesRequest.builder()
                .exclusiveStartGlobalTableName("exclusiveStartGlobalTableName")
                .limit(0)
                .regionName("regionName")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListGlobalTables2());
    }

    @Test
    void testTryListGlobalTables3() {
        // Setup
        // Configure DynamoDbClient.listGlobalTables(...).
        final ListGlobalTablesResponse listGlobalTablesResponse = ListGlobalTablesResponse.builder()
                .globalTables(GlobalTable.builder()
                        .globalTableName("globalTableName")
                        .replicationGroup(Replica.builder()
                                .regionName("regionName")
                                .build())
                        .build())
                .lastEvaluatedGlobalTableName("lastEvaluatedGlobalTableName")
                .build();
        when(mockDynamoDbClient.listGlobalTables(any(Consumer.class))).thenReturn(listGlobalTablesResponse);

        // Run the test
        myClassUnderTest.tryListGlobalTables3();

        // Verify the results
    }

    @Test
    void testTryListGlobalTables3_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListGlobalTables3());
    }

    @Test
    void testTryListGlobalTables3_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListGlobalTables3());
    }

    @Test
    void testTryListGlobalTables3_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListGlobalTables3());
    }

    @Test
    void testTryListGlobalTables3_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListGlobalTables3());
    }

    @Test
    void testTryListTables1() {
        // Setup
        // Configure DynamoDbClient.listTables(...).
        final ListTablesResponse listTablesResponse = ListTablesResponse.builder()
                .tableNames("tableNames")
                .lastEvaluatedTableName("lastEvaluatedTableName")
                .build();
        when(mockDynamoDbClient.listTables()).thenReturn(listTablesResponse);

        // Run the test
        myClassUnderTest.tryListTables1();

        // Verify the results
    }

    @Test
    void testTryListTables1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listTables()).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListTables1());
    }

    @Test
    void testTryListTables1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listTables()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTables1());
    }

    @Test
    void testTryListTables1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listTables()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTables1());
    }

    @Test
    void testTryListTables1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listTables()).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListTables1());
    }

    @Test
    void testTryListTables2() {
        // Setup
        // Configure DynamoDbClient.listTables(...).
        final ListTablesResponse listTablesResponse = ListTablesResponse.builder()
                .tableNames("tableNames")
                .lastEvaluatedTableName("lastEvaluatedTableName")
                .build();
        when(mockDynamoDbClient.listTables(ListTablesRequest.builder()
                .exclusiveStartTableName("exclusiveStartTableName")
                .limit(0)
                .build())).thenReturn(listTablesResponse);

        // Run the test
        myClassUnderTest.tryListTables2();

        // Verify the results
    }

    @Test
    void testTryListTables2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listTables(ListTablesRequest.builder()
                .exclusiveStartTableName("exclusiveStartTableName")
                .limit(0)
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListTables2());
    }

    @Test
    void testTryListTables2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listTables(ListTablesRequest.builder()
                .exclusiveStartTableName("exclusiveStartTableName")
                .limit(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTables2());
    }

    @Test
    void testTryListTables2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listTables(ListTablesRequest.builder()
                .exclusiveStartTableName("exclusiveStartTableName")
                .limit(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTables2());
    }

    @Test
    void testTryListTables2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listTables(ListTablesRequest.builder()
                .exclusiveStartTableName("exclusiveStartTableName")
                .limit(0)
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListTables2());
    }

    @Test
    void testTryListTables3() {
        // Setup
        // Configure DynamoDbClient.listTables(...).
        final ListTablesResponse listTablesResponse = ListTablesResponse.builder()
                .tableNames("tableNames")
                .lastEvaluatedTableName("lastEvaluatedTableName")
                .build();
        when(mockDynamoDbClient.listTables(any(Consumer.class))).thenReturn(listTablesResponse);

        // Run the test
        myClassUnderTest.tryListTables3();

        // Verify the results
    }

    @Test
    void testTryListTables3_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listTables(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListTables3());
    }

    @Test
    void testTryListTables3_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listTables(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTables3());
    }

    @Test
    void testTryListTables3_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listTables(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTables3());
    }

    @Test
    void testTryListTables3_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listTables(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListTables3());
    }

    @Test
    void testTryListTablesPaginator1() {
        // Setup
        // Configure DynamoDbClient.listTablesPaginator(...).
        final ListTablesIterable mockListTablesIterable = mock(ListTablesIterable.class);
        when(mockDynamoDbClient.listTablesPaginator()).thenReturn(mockListTablesIterable);

        // Run the test
        myClassUnderTest.tryListTablesPaginator1();

        // Verify the results
    }

    @Test
    void testTryListTablesPaginator1_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.listTablesPaginator(...).
        final ListTablesIterable mockListTablesIterable = mock(ListTablesIterable.class);
        when(mockDynamoDbClient.listTablesPaginator()).thenReturn(mockListTablesIterable);

        // Run the test
        myClassUnderTest.tryListTablesPaginator1();

        // Verify the results
    }

    @Test
    void testTryListTablesPaginator1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator()).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListTablesPaginator1());
    }

    @Test
    void testTryListTablesPaginator1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTablesPaginator1());
    }

    @Test
    void testTryListTablesPaginator1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTablesPaginator1());
    }

    @Test
    void testTryListTablesPaginator1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator()).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListTablesPaginator1());
    }

    @Test
    void testTryListTablesPaginator2() {
        // Setup
        // Configure DynamoDbClient.listTablesPaginator(...).
        final ListTablesIterable mockListTablesIterable = mock(ListTablesIterable.class);
        when(mockDynamoDbClient.listTablesPaginator(ListTablesRequest.builder()
                .exclusiveStartTableName("exclusiveStartTableName")
                .limit(0)
                .build())).thenReturn(mockListTablesIterable);

        // Run the test
        myClassUnderTest.tryListTablesPaginator2();

        // Verify the results
    }

    @Test
    void testTryListTablesPaginator2_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.listTablesPaginator(...).
        final ListTablesIterable mockListTablesIterable = mock(ListTablesIterable.class);
        when(mockDynamoDbClient.listTablesPaginator(ListTablesRequest.builder()
                .exclusiveStartTableName("exclusiveStartTableName")
                .limit(0)
                .build())).thenReturn(mockListTablesIterable);

        // Run the test
        myClassUnderTest.tryListTablesPaginator2();

        // Verify the results
    }

    @Test
    void testTryListTablesPaginator2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator(ListTablesRequest.builder()
                .exclusiveStartTableName("exclusiveStartTableName")
                .limit(0)
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListTablesPaginator2());
    }

    @Test
    void testTryListTablesPaginator2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator(ListTablesRequest.builder()
                .exclusiveStartTableName("exclusiveStartTableName")
                .limit(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTablesPaginator2());
    }

    @Test
    void testTryListTablesPaginator2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator(ListTablesRequest.builder()
                .exclusiveStartTableName("exclusiveStartTableName")
                .limit(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTablesPaginator2());
    }

    @Test
    void testTryListTablesPaginator2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator(ListTablesRequest.builder()
                .exclusiveStartTableName("exclusiveStartTableName")
                .limit(0)
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListTablesPaginator2());
    }

    @Test
    void testTryListTablesPaginator3() {
        // Setup
        // Configure DynamoDbClient.listTablesPaginator(...).
        final ListTablesIterable mockListTablesIterable = mock(ListTablesIterable.class);
        when(mockDynamoDbClient.listTablesPaginator(any(Consumer.class))).thenReturn(mockListTablesIterable);

        // Run the test
        myClassUnderTest.tryListTablesPaginator3();

        // Verify the results
    }

    @Test
    void testTryListTablesPaginator3_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.listTablesPaginator(...).
        final ListTablesIterable mockListTablesIterable = mock(ListTablesIterable.class);
        when(mockDynamoDbClient.listTablesPaginator(any(Consumer.class))).thenReturn(mockListTablesIterable);

        // Run the test
        myClassUnderTest.tryListTablesPaginator3();

        // Verify the results
    }

    @Test
    void testTryListTablesPaginator3_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListTablesPaginator3());
    }

    @Test
    void testTryListTablesPaginator3_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTablesPaginator3());
    }

    @Test
    void testTryListTablesPaginator3_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTablesPaginator3());
    }

    @Test
    void testTryListTablesPaginator3_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListTablesPaginator3());
    }

    @Test
    void testTryListTagsOfResource1() {
        // Setup
        // Configure DynamoDbClient.listTagsOfResource(...).
        final ListTagsOfResourceResponse listTagsOfResourceResponse = ListTagsOfResourceResponse.builder()
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockDynamoDbClient.listTagsOfResource(ListTagsOfResourceRequest.builder()
                .resourceArn("resourceArn")
                .nextToken("nextToken")
                .build())).thenReturn(listTagsOfResourceResponse);

        // Run the test
        myClassUnderTest.tryListTagsOfResource1();

        // Verify the results
    }

    @Test
    void testTryListTagsOfResource1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(ListTagsOfResourceRequest.builder()
                .resourceArn("resourceArn")
                .nextToken("nextToken")
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryListTagsOfResource1());
    }

    @Test
    void testTryListTagsOfResource1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(ListTagsOfResourceRequest.builder()
                .resourceArn("resourceArn")
                .nextToken("nextToken")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListTagsOfResource1());
    }

    @Test
    void testTryListTagsOfResource1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(ListTagsOfResourceRequest.builder()
                .resourceArn("resourceArn")
                .nextToken("nextToken")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTagsOfResource1());
    }

    @Test
    void testTryListTagsOfResource1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(ListTagsOfResourceRequest.builder()
                .resourceArn("resourceArn")
                .nextToken("nextToken")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTagsOfResource1());
    }

    @Test
    void testTryListTagsOfResource1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(ListTagsOfResourceRequest.builder()
                .resourceArn("resourceArn")
                .nextToken("nextToken")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListTagsOfResource1());
    }

    @Test
    void testTryListTagsOfResource2() {
        // Setup
        // Configure DynamoDbClient.listTagsOfResource(...).
        final ListTagsOfResourceResponse listTagsOfResourceResponse = ListTagsOfResourceResponse.builder()
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockDynamoDbClient.listTagsOfResource(any(Consumer.class))).thenReturn(listTagsOfResourceResponse);

        // Run the test
        myClassUnderTest.tryListTagsOfResource2();

        // Verify the results
    }

    @Test
    void testTryListTagsOfResource2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryListTagsOfResource2());
    }

    @Test
    void testTryListTagsOfResource2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListTagsOfResource2());
    }

    @Test
    void testTryListTagsOfResource2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTagsOfResource2());
    }

    @Test
    void testTryListTagsOfResource2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTagsOfResource2());
    }

    @Test
    void testTryListTagsOfResource2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListTagsOfResource2());
    }

    @Test
    void testTryPutItem1() {
        // Setup
        // Configure DynamoDbClient.putItem(...).
        final PutItemResponse putItemResponse = PutItemResponse.builder()
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
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherValue").build())))
                .build())).thenReturn(putItemResponse);

        // Run the test
        myClassUnderTest.tryPutItem1();

        // Verify the results
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherValue").build())))
                .build())).thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherValue").build())))
                .build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherValue").build())))
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherValue").build())))
                .build())).thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsTransactionConflictException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherValue").build())))
                .build())).thenThrow(TransactionConflictException.class);

        // Run the test
        assertThrows(TransactionConflictException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherValue").build())))
                .build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherValue").build())))
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherValue").build())))
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherValue").build())))
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherValue").build())))
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem2() {
        // Setup
        // Configure DynamoDbClient.putItem(...).
        final PutItemResponse putItemResponse = PutItemResponse.builder()
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
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenReturn(putItemResponse);

        // Run the test
        myClassUnderTest.tryPutItem2();

        // Verify the results
    }

    @Test
    void testTryPutItem2_DynamoDbClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, () -> myClassUnderTest.tryPutItem2());
    }

    @Test
    void testTryPutItem2_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryPutItem2());
    }

    @Test
    void testTryPutItem2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryPutItem2());
    }

    @Test
    void testTryPutItem2_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.tryPutItem2());
    }

    @Test
    void testTryPutItem2_DynamoDbClientThrowsTransactionConflictException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(TransactionConflictException.class);

        // Run the test
        assertThrows(TransactionConflictException.class, () -> myClassUnderTest.tryPutItem2());
    }

    @Test
    void testTryPutItem2_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryPutItem2());
    }

    @Test
    void testTryPutItem2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryPutItem2());
    }

    @Test
    void testTryPutItem2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutItem2());
    }

    @Test
    void testTryPutItem2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutItem2());
    }

    @Test
    void testTryPutItem2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryPutItem2());
    }

    @Test
    void testTryQuery1() {
        // Setup
        // Configure DynamoDbClient.query(...).
        final QueryResponse queryResponse = QueryResponse.builder().items(
                Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue1").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherKeyValue1").build())
                ),
                Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue2").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherKeyValue2").build())
                )
        ).build();
        when(mockDynamoDbClient.query(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenReturn(queryResponse);

        // Run the test
        myClassUnderTest.tryQuery1();

        // Verify the results
    }

    @Test
    void testTryQuery1_DynamoDbClientReturnsNoItems() {
        // Setup
        when(mockDynamoDbClient.query(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenReturn(QueryResponse.builder().build());

        // Run the test
        myClassUnderTest.tryQuery1();

        // Verify the results
    }

    @Test
    void testTryQuery1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.query(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryQuery1());
    }

    @Test
    void testTryQuery1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.query(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryQuery1());
    }

    @Test
    void testTryQuery1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.query(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryQuery1());
    }

    @Test
    void testTryQuery1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.query(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryQuery1());
    }

    @Test
    void testTryQuery1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.query(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryQuery1());
    }

    @Test
    void testTryQuery1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.query(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryQuery1());
    }

    @Test
    void testTryQuery1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.query(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryQuery1());
    }

    @Test
    void testTryQuery2() {
        // Setup
        // Configure DynamoDbClient.query(...).
        final QueryResponse queryResponse = QueryResponse.builder().items(
                Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue1").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherKeyValue1").build())
                ),
                Map.ofEntries(
                        Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue2").build()),
                        Map.entry("OtherKeyName", AttributeValue.builder().s("OtherKeyValue2").build())
                )
        ).build();
        when(mockDynamoDbClient.query(any(Consumer.class))).thenReturn(queryResponse);

        // Run the test
        myClassUnderTest.tryQuery2();

        // Verify the results
    }

    @Test
    void testTryQuery2_DynamoDbClientReturnsNoItems() {
        // Setup
        when(mockDynamoDbClient.query(any(Consumer.class))).thenReturn(QueryResponse.builder().build());

        // Run the test
        myClassUnderTest.tryQuery2();

        // Verify the results
    }

    @Test
    void testTryQuery2_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.query(any(Consumer.class))).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryQuery2());
    }

    @Test
    void testTryQuery2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.query(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryQuery2());
    }

    @Test
    void testTryQuery2_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.query(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryQuery2());
    }

    @Test
    void testTryQuery2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.query(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryQuery2());
    }

    @Test
    void testTryQuery2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.query(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryQuery2());
    }

    @Test
    void testTryQuery2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.query(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryQuery2());
    }

    @Test
    void testTryQuery2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.query(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryQuery2());
    }

    @Test
    void testTryQueryPaginator1() {
        // Setup
        // Configure DynamoDbClient.queryPaginator(...).
        final QueryIterable mockQueryIterable = mock(QueryIterable.class);
        when(mockDynamoDbClient.queryPaginator(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenReturn(mockQueryIterable);

        // Run the test
        myClassUnderTest.tryQueryPaginator1();

        // Verify the results
    }

    @Test
    void testTryQueryPaginator1_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.queryPaginator(...).
        final QueryIterable mockQueryIterable = mock(QueryIterable.class);
        when(mockDynamoDbClient.queryPaginator(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenReturn(mockQueryIterable);

        // Run the test
        myClassUnderTest.tryQueryPaginator1();

        // Verify the results
    }

    @Test
    void testTryQueryPaginator1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryQueryPaginator1());
    }

    @Test
    void testTryQueryPaginator1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryQueryPaginator1());
    }

    @Test
    void testTryQueryPaginator1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryQueryPaginator1());
    }

    @Test
    void testTryQueryPaginator1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryQueryPaginator1());
    }

    @Test
    void testTryQueryPaginator1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryQueryPaginator1());
    }

    @Test
    void testTryQueryPaginator1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryQueryPaginator1());
    }

    @Test
    void testTryQueryPaginator1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryQueryPaginator1());
    }

    @Test
    void testTryQueryPaginator2() {
        // Setup
        // Configure DynamoDbClient.queryPaginator(...).
        final QueryIterable mockQueryIterable = mock(QueryIterable.class);
        when(mockDynamoDbClient.queryPaginator(any(Consumer.class))).thenReturn(mockQueryIterable);

        // Run the test
        myClassUnderTest.tryQueryPaginator2();

        // Verify the results
    }

    @Test
    void testTryQueryPaginator2_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.queryPaginator(...).
        final QueryIterable mockQueryIterable = mock(QueryIterable.class);
        when(mockDynamoDbClient.queryPaginator(any(Consumer.class))).thenReturn(mockQueryIterable);

        // Run the test
        myClassUnderTest.tryQueryPaginator2();

        // Verify the results
    }

    @Test
    void testTryQueryPaginator2_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(any(Consumer.class)))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryQueryPaginator2());
    }

    @Test
    void testTryQueryPaginator2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryQueryPaginator2());
    }

    @Test
    void testTryQueryPaginator2_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryQueryPaginator2());
    }

    @Test
    void testTryQueryPaginator2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryQueryPaginator2());
    }

    @Test
    void testTryQueryPaginator2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryQueryPaginator2());
    }

    @Test
    void testTryQueryPaginator2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryQueryPaginator2());
    }

    @Test
    void testTryQueryPaginator2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryQueryPaginator2());
    }

    @Test
    void testTryRestoreTableFromBackup1() {
        // Setup
        // Configure DynamoDbClient.restoreTableFromBackup(...).
        final RestoreTableFromBackupResponse restoreTableFromBackupResponse = RestoreTableFromBackupResponse.builder()
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
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder()
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
                .build())).thenReturn(restoreTableFromBackupResponse);

        // Run the test
        myClassUnderTest.tryRestoreTableFromBackup1();

        // Verify the results
    }

    @Test
    void testTryRestoreTableFromBackup1_DynamoDbClientThrowsTableAlreadyExistsException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder()
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
                .build())).thenThrow(TableAlreadyExistsException.class);

        // Run the test
        assertThrows(TableAlreadyExistsException.class, () -> myClassUnderTest.tryRestoreTableFromBackup1());
    }

    @Test
    void testTryRestoreTableFromBackup1_DynamoDbClientThrowsTableInUseException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder()
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
                .build())).thenThrow(TableInUseException.class);

        // Run the test
        assertThrows(TableInUseException.class, () -> myClassUnderTest.tryRestoreTableFromBackup1());
    }

    @Test
    void testTryRestoreTableFromBackup1_DynamoDbClientThrowsBackupNotFoundException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder()
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
                .build())).thenThrow(BackupNotFoundException.class);

        // Run the test
        assertThrows(BackupNotFoundException.class, () -> myClassUnderTest.tryRestoreTableFromBackup1());
    }

    @Test
    void testTryRestoreTableFromBackup1_DynamoDbClientThrowsBackupInUseException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder()
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
                .build())).thenThrow(BackupInUseException.class);

        // Run the test
        assertThrows(BackupInUseException.class, () -> myClassUnderTest.tryRestoreTableFromBackup1());
    }

    @Test
    void testTryRestoreTableFromBackup1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder()
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
                .build())).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryRestoreTableFromBackup1());
    }

    @Test
    void testTryRestoreTableFromBackup1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder()
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
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryRestoreTableFromBackup1());
    }

    @Test
    void testTryRestoreTableFromBackup1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder()
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
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryRestoreTableFromBackup1());
    }

    @Test
    void testTryRestoreTableFromBackup1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder()
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
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryRestoreTableFromBackup1());
    }

    @Test
    void testTryRestoreTableFromBackup1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder()
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
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryRestoreTableFromBackup1());
    }

    @Test
    void testTryRestoreTableFromBackup2() {
        // Setup
        // Configure DynamoDbClient.restoreTableFromBackup(...).
        final RestoreTableFromBackupResponse restoreTableFromBackupResponse = RestoreTableFromBackupResponse.builder()
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
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class))).thenReturn(restoreTableFromBackupResponse);

        // Run the test
        myClassUnderTest.tryRestoreTableFromBackup2();

        // Verify the results
    }

    @Test
    void testTryRestoreTableFromBackup2_DynamoDbClientThrowsTableAlreadyExistsException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class)))
                .thenThrow(TableAlreadyExistsException.class);

        // Run the test
        assertThrows(TableAlreadyExistsException.class, () -> myClassUnderTest.tryRestoreTableFromBackup2());
    }

    @Test
    void testTryRestoreTableFromBackup2_DynamoDbClientThrowsTableInUseException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class))).thenThrow(TableInUseException.class);

        // Run the test
        assertThrows(TableInUseException.class, () -> myClassUnderTest.tryRestoreTableFromBackup2());
    }

    @Test
    void testTryRestoreTableFromBackup2_DynamoDbClientThrowsBackupNotFoundException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class))).thenThrow(BackupNotFoundException.class);

        // Run the test
        assertThrows(BackupNotFoundException.class, () -> myClassUnderTest.tryRestoreTableFromBackup2());
    }

    @Test
    void testTryRestoreTableFromBackup2_DynamoDbClientThrowsBackupInUseException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class))).thenThrow(BackupInUseException.class);

        // Run the test
        assertThrows(BackupInUseException.class, () -> myClassUnderTest.tryRestoreTableFromBackup2());
    }

    @Test
    void testTryRestoreTableFromBackup2_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryRestoreTableFromBackup2());
    }

    @Test
    void testTryRestoreTableFromBackup2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryRestoreTableFromBackup2());
    }

    @Test
    void testTryRestoreTableFromBackup2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryRestoreTableFromBackup2());
    }

    @Test
    void testTryRestoreTableFromBackup2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryRestoreTableFromBackup2());
    }

    @Test
    void testTryRestoreTableFromBackup2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryRestoreTableFromBackup2());
    }

    @Test
    void testTryRestoreTableToPointInTime1() {
        // Setup
        // Configure DynamoDbClient.restoreTableToPointInTime(...).
        final RestoreTableToPointInTimeResponse restoreTableToPointInTimeResponse = RestoreTableToPointInTimeResponse.builder()
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
        when(mockDynamoDbClient.restoreTableToPointInTime(RestoreTableToPointInTimeRequest.builder()
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
                .build())).thenReturn(restoreTableToPointInTimeResponse);

        // Run the test
        myClassUnderTest.tryRestoreTableToPointInTime1();

        // Verify the results
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsTableAlreadyExistsException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(RestoreTableToPointInTimeRequest.builder()
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
                .build())).thenThrow(TableAlreadyExistsException.class);

        // Run the test
        assertThrows(TableAlreadyExistsException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(RestoreTableToPointInTimeRequest.builder()
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
                .build())).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsTableInUseException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(RestoreTableToPointInTimeRequest.builder()
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
                .build())).thenThrow(TableInUseException.class);

        // Run the test
        assertThrows(TableInUseException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(RestoreTableToPointInTimeRequest.builder()
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
                .build())).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsInvalidRestoreTimeException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(RestoreTableToPointInTimeRequest.builder()
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
                .build())).thenThrow(InvalidRestoreTimeException.class);

        // Run the test
        assertThrows(InvalidRestoreTimeException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsPointInTimeRecoveryUnavailableException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(RestoreTableToPointInTimeRequest.builder()
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
                .build())).thenThrow(PointInTimeRecoveryUnavailableException.class);

        // Run the test
        assertThrows(PointInTimeRecoveryUnavailableException.class,
                () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(RestoreTableToPointInTimeRequest.builder()
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
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(RestoreTableToPointInTimeRequest.builder()
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
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(RestoreTableToPointInTimeRequest.builder()
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
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(RestoreTableToPointInTimeRequest.builder()
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
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime2() {
        // Setup
        // Configure DynamoDbClient.restoreTableToPointInTime(...).
        final RestoreTableToPointInTimeResponse restoreTableToPointInTimeResponse = RestoreTableToPointInTimeResponse.builder()
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
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class)))
                .thenReturn(restoreTableToPointInTimeResponse);

        // Run the test
        myClassUnderTest.tryRestoreTableToPointInTime2();

        // Verify the results
    }

    @Test
    void testTryRestoreTableToPointInTime2_DynamoDbClientThrowsTableAlreadyExistsException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class)))
                .thenThrow(TableAlreadyExistsException.class);

        // Run the test
        assertThrows(TableAlreadyExistsException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime2());
    }

    @Test
    void testTryRestoreTableToPointInTime2_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class))).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime2());
    }

    @Test
    void testTryRestoreTableToPointInTime2_DynamoDbClientThrowsTableInUseException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class))).thenThrow(TableInUseException.class);

        // Run the test
        assertThrows(TableInUseException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime2());
    }

    @Test
    void testTryRestoreTableToPointInTime2_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime2());
    }

    @Test
    void testTryRestoreTableToPointInTime2_DynamoDbClientThrowsInvalidRestoreTimeException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class)))
                .thenThrow(InvalidRestoreTimeException.class);

        // Run the test
        assertThrows(InvalidRestoreTimeException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime2());
    }

    @Test
    void testTryRestoreTableToPointInTime2_DynamoDbClientThrowsPointInTimeRecoveryUnavailableException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class)))
                .thenThrow(PointInTimeRecoveryUnavailableException.class);

        // Run the test
        assertThrows(PointInTimeRecoveryUnavailableException.class,
                () -> myClassUnderTest.tryRestoreTableToPointInTime2());
    }

    @Test
    void testTryRestoreTableToPointInTime2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime2());
    }

    @Test
    void testTryRestoreTableToPointInTime2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime2());
    }

    @Test
    void testTryRestoreTableToPointInTime2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime2());
    }

    @Test
    void testTryRestoreTableToPointInTime2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime2());
    }

    @Test
    void testTryScan1() {
        // Setup
        // Configure DynamoDbClient.scan(...).
        final ScanResponse scanResponse = ScanResponse.builder()
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
        when(mockDynamoDbClient.scan(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenReturn(scanResponse);

        // Run the test
        myClassUnderTest.tryScan1();

        // Verify the results
    }

    @Test
    void testTryScan1_DynamoDbClientReturnsNoItems() {
        // Setup
        when(mockDynamoDbClient.scan(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenReturn(ScanResponse.builder().build());

        // Run the test
        myClassUnderTest.tryScan1();

        // Verify the results
    }

    @Test
    void testTryScan1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.scan(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryScan1());
    }

    @Test
    void testTryScan1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.scan(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryScan1());
    }

    @Test
    void testTryScan1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.scan(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryScan1());
    }

    @Test
    void testTryScan1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.scan(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryScan1());
    }

    @Test
    void testTryScan1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.scan(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryScan1());
    }

    @Test
    void testTryScan1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.scan(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryScan1());
    }

    @Test
    void testTryScan1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.scan(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryScan1());
    }

    @Test
    void testTryScan2() {
        // Setup
        // Configure DynamoDbClient.scan(...).
        final ScanResponse scanResponse = ScanResponse.builder()
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
        when(mockDynamoDbClient.scan(any(Consumer.class))).thenReturn(scanResponse);

        // Run the test
        myClassUnderTest.tryScan2();

        // Verify the results
    }

    @Test
    void testTryScan2_DynamoDbClientReturnsNoItems() {
        // Setup
        when(mockDynamoDbClient.scan(any(Consumer.class))).thenReturn(ScanResponse.builder().build());

        // Run the test
        myClassUnderTest.tryScan2();

        // Verify the results
    }

    @Test
    void testTryScan2_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.scan(any(Consumer.class))).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryScan2());
    }

    @Test
    void testTryScan2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.scan(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryScan2());
    }

    @Test
    void testTryScan2_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.scan(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryScan2());
    }

    @Test
    void testTryScan2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.scan(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryScan2());
    }

    @Test
    void testTryScan2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.scan(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryScan2());
    }

    @Test
    void testTryScan2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.scan(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryScan2());
    }

    @Test
    void testTryScan2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.scan(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryScan2());
    }

    @Test
    void testTryScanPaginator1() {
        // Setup
        // Configure DynamoDbClient.scanPaginator(...).
        final ScanIterable mockScanIterable = mock(ScanIterable.class);
        when(mockDynamoDbClient.scanPaginator(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenReturn(mockScanIterable);

        // Run the test
        myClassUnderTest.tryScanPaginator1();

        // Verify the results
    }

    @Test
    void testTryScanPaginator1_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.scanPaginator(...).
        final ScanIterable mockScanIterable = mock(ScanIterable.class);
        when(mockDynamoDbClient.scanPaginator(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenReturn(mockScanIterable);

        // Run the test
        myClassUnderTest.tryScanPaginator1();

        // Verify the results
    }

    @Test
    void testTryScanPaginator1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryScanPaginator1());
    }

    @Test
    void testTryScanPaginator1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryScanPaginator1());
    }

    @Test
    void testTryScanPaginator1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryScanPaginator1());
    }

    @Test
    void testTryScanPaginator1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryScanPaginator1());
    }

    @Test
    void testTryScanPaginator1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryScanPaginator1());
    }

    @Test
    void testTryScanPaginator1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryScanPaginator1());
    }

    @Test
    void testTryScanPaginator1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryScanPaginator1());
    }

    @Test
    void testTryScanPaginator2() {
        // Setup
        // Configure DynamoDbClient.scanPaginator(...).
        final ScanIterable mockScanIterable = mock(ScanIterable.class);
        when(mockDynamoDbClient.scanPaginator(any(Consumer.class))).thenReturn(mockScanIterable);

        // Run the test
        myClassUnderTest.tryScanPaginator2();

        // Verify the results
    }

    @Test
    void testTryScanPaginator2_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.scanPaginator(...).
        final ScanIterable mockScanIterable = mock(ScanIterable.class);
        when(mockDynamoDbClient.scanPaginator(any(Consumer.class))).thenReturn(mockScanIterable);

        // Run the test
        myClassUnderTest.tryScanPaginator2();

        // Verify the results
    }

    @Test
    void testTryScanPaginator2_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(any(Consumer.class)))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryScanPaginator2());
    }

    @Test
    void testTryScanPaginator2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryScanPaginator2());
    }

    @Test
    void testTryScanPaginator2_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryScanPaginator2());
    }

    @Test
    void testTryScanPaginator2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryScanPaginator2());
    }

    @Test
    void testTryScanPaginator2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryScanPaginator2());
    }

    @Test
    void testTryScanPaginator2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryScanPaginator2());
    }

    @Test
    void testTryScanPaginator2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryScanPaginator2());
    }

    @Test
    void testTryTagResource1() {
        // Setup
        when(mockDynamoDbClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
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
    void testTryTagResource1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource2() {
        // Setup
        when(mockDynamoDbClient.tagResource(any(Consumer.class))).thenReturn(TagResourceResponse.builder().build());

        // Run the test
        myClassUnderTest.tryTagResource2();

        // Verify the results
    }

    @Test
    void testTryTagResource2_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.tagResource(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.tagResource(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.tagResource(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.tagResource(any(Consumer.class))).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.tagResource(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.tagResource(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.tagResource(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTransactGetItems1() {
        // Setup
        // Configure DynamoDbClient.transactGetItems(...).
        final TransactGetItemsResponse transactGetItemsResponse = TransactGetItemsResponse.builder().responses(
                ItemResponse.builder()
                        .item(Map.ofEntries(
                                Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build())
                        )).build(),
                ItemResponse.builder()
                        .item(Map.ofEntries(
                                Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue2").build())
                        )).build()
        ).build();
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(TransactGetItem.builder().get(
                        Get.builder()
                                .tableName("TableName")
                                .key(Map.of(
                                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                                )).build()).build()).build())).thenReturn(transactGetItemsResponse);

        // Run the test
        myClassUnderTest.tryTransactGetItems1();

        // Verify the results
    }

    @Test
    void testTryTransactGetItems1_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.transactGetItems(...).
        final TransactGetItemsResponse transactGetItemsResponse = TransactGetItemsResponse.builder().build();
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(TransactGetItem.builder().get(
                        Get.builder()
                                .tableName("TableName")
                                .key(Map.of(
                                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                                )).build()).build()).build())).thenReturn(transactGetItemsResponse);

        // Run the test
        myClassUnderTest.tryTransactGetItems1();

        // Verify the results
    }

    @Test
    void testTryTransactGetItems1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(TransactGetItem.builder().get(
                        Get.builder()
                                .tableName("TableName")
                                .key(Map.of(
                                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                                )).build()).build()).build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryTransactGetItems1());
    }

    @Test
    void testTryTransactGetItems1_DynamoDbClientThrowsTransactionCanceledException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(TransactGetItem.builder().get(
                        Get.builder()
                                .tableName("TableName")
                                .key(Map.of(
                                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                                )).build()).build()).build())).thenThrow(TransactionCanceledException.class);

        // Run the test
        assertThrows(TransactionCanceledException.class, () -> myClassUnderTest.tryTransactGetItems1());
    }

    @Test
    void testTryTransactGetItems1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(TransactGetItem.builder().get(
                        Get.builder()
                                .tableName("TableName")
                                .key(Map.of(
                                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                                )).build()).build()).build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryTransactGetItems1());
    }

    @Test
    void testTryTransactGetItems1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(TransactGetItem.builder().get(
                        Get.builder()
                                .tableName("TableName")
                                .key(Map.of(
                                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                                )).build()).build()).build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryTransactGetItems1());
    }

    @Test
    void testTryTransactGetItems1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(TransactGetItem.builder().get(
                        Get.builder()
                                .tableName("TableName")
                                .key(Map.of(
                                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                                )).build()).build()).build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryTransactGetItems1());
    }

    @Test
    void testTryTransactGetItems1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(TransactGetItem.builder().get(
                        Get.builder()
                                .tableName("TableName")
                                .key(Map.of(
                                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                                )).build()).build()).build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryTransactGetItems1());
    }

    @Test
    void testTryTransactGetItems1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(TransactGetItem.builder().get(
                        Get.builder()
                                .tableName("TableName")
                                .key(Map.of(
                                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                                )).build()).build()).build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryTransactGetItems1());
    }

    @Test
    void testTryTransactGetItems1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(TransactGetItem.builder().get(
                        Get.builder()
                                .tableName("TableName")
                                .key(Map.of(
                                        "PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build()
                                )).build()).build()).build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryTransactGetItems1());
    }

    @Test
    void testTryTransactGetItems2() {
        // Setup
        // Configure DynamoDbClient.transactGetItems(...).
        final TransactGetItemsResponse transactGetItemsResponse = TransactGetItemsResponse.builder().responses(
                ItemResponse.builder()
                        .item(Map.ofEntries(
                                Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build())
                        )).build(),
                ItemResponse.builder()
                        .item(Map.ofEntries(
                                Map.entry("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue2").build())
                        )).build()
        ).build();
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class))).thenReturn(transactGetItemsResponse);

        // Run the test
        myClassUnderTest.tryTransactGetItems2();

        // Verify the results
    }

    @Test
    void testTryTransactGetItems2_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.transactGetItems(...).
        final TransactGetItemsResponse transactGetItemsResponse = TransactGetItemsResponse.builder().build();
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class))).thenReturn(transactGetItemsResponse);

        // Run the test
        myClassUnderTest.tryTransactGetItems2();

        // Verify the results
    }

    @Test
    void testTryTransactGetItems2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryTransactGetItems2());
    }

    @Test
    void testTryTransactGetItems2_DynamoDbClientThrowsTransactionCanceledException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class))).thenThrow(TransactionCanceledException.class);

        // Run the test
        assertThrows(TransactionCanceledException.class, () -> myClassUnderTest.tryTransactGetItems2());
    }

    @Test
    void testTryTransactGetItems2_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class)))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryTransactGetItems2());
    }

    @Test
    void testTryTransactGetItems2_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryTransactGetItems2());
    }

    @Test
    void testTryTransactGetItems2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryTransactGetItems2());
    }

    @Test
    void testTryTransactGetItems2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryTransactGetItems2());
    }

    @Test
    void testTryTransactGetItems2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryTransactGetItems2());
    }

    @Test
    void testTryTransactGetItems2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryTransactGetItems2());
    }

    @Test
    void testTryTransactWriteItems1() {
        // Setup
        // Configure DynamoDbClient.transactWriteItems(...).
        final TransactWriteItemsResponse transactWriteItemsResponse = TransactWriteItemsResponse.builder()
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
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder().transactItems(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(Map.ofEntries(
                                                Map.entry("PrimaryKeyName",
                                                        AttributeValue.builder().s("PrimaryKeyValue").build())
                                        )).build())
                                .build())
                .build())).thenReturn(transactWriteItemsResponse);

        // Run the test
        myClassUnderTest.tryTransactWriteItems1();

        // Verify the results
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder().transactItems(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(Map.ofEntries(
                                                Map.entry("PrimaryKeyName",
                                                        AttributeValue.builder().s("PrimaryKeyValue").build())
                                        )).build())
                                .build())
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsTransactionCanceledException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder().transactItems(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(Map.ofEntries(
                                                Map.entry("PrimaryKeyName",
                                                        AttributeValue.builder().s("PrimaryKeyValue").build())
                                        )).build())
                                .build())
                .build())).thenThrow(TransactionCanceledException.class);

        // Run the test
        assertThrows(TransactionCanceledException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsTransactionInProgressException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder().transactItems(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(Map.ofEntries(
                                                Map.entry("PrimaryKeyName",
                                                        AttributeValue.builder().s("PrimaryKeyValue").build())
                                        )).build())
                                .build())
                .build())).thenThrow(TransactionInProgressException.class);

        // Run the test
        assertThrows(TransactionInProgressException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsIdempotentParameterMismatchException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder().transactItems(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(Map.ofEntries(
                                                Map.entry("PrimaryKeyName",
                                                        AttributeValue.builder().s("PrimaryKeyValue").build())
                                        )).build())
                                .build())
                .build())).thenThrow(IdempotentParameterMismatchException.class);

        // Run the test
        assertThrows(IdempotentParameterMismatchException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder().transactItems(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(Map.ofEntries(
                                                Map.entry("PrimaryKeyName",
                                                        AttributeValue.builder().s("PrimaryKeyValue").build())
                                        )).build())
                                .build())
                .build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder().transactItems(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(Map.ofEntries(
                                                Map.entry("PrimaryKeyName",
                                                        AttributeValue.builder().s("PrimaryKeyValue").build())
                                        )).build())
                                .build())
                .build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder().transactItems(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(Map.ofEntries(
                                                Map.entry("PrimaryKeyName",
                                                        AttributeValue.builder().s("PrimaryKeyValue").build())
                                        )).build())
                                .build())
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder().transactItems(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(Map.ofEntries(
                                                Map.entry("PrimaryKeyName",
                                                        AttributeValue.builder().s("PrimaryKeyValue").build())
                                        )).build())
                                .build())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder().transactItems(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(Map.ofEntries(
                                                Map.entry("PrimaryKeyName",
                                                        AttributeValue.builder().s("PrimaryKeyValue").build())
                                        )).build())
                                .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder().transactItems(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(Map.ofEntries(
                                                Map.entry("PrimaryKeyName",
                                                        AttributeValue.builder().s("PrimaryKeyValue").build())
                                        )).build())
                                .build())
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems2() {
        // Setup
        // Configure DynamoDbClient.transactWriteItems(...).
        final TransactWriteItemsResponse transactWriteItemsResponse = TransactWriteItemsResponse.builder()
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
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class))).thenReturn(transactWriteItemsResponse);

        // Run the test
        myClassUnderTest.tryTransactWriteItems2();

        // Verify the results
    }

    @Test
    void testTryTransactWriteItems2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryTransactWriteItems2());
    }

    @Test
    void testTryTransactWriteItems2_DynamoDbClientThrowsTransactionCanceledException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class))).thenThrow(TransactionCanceledException.class);

        // Run the test
        assertThrows(TransactionCanceledException.class, () -> myClassUnderTest.tryTransactWriteItems2());
    }

    @Test
    void testTryTransactWriteItems2_DynamoDbClientThrowsTransactionInProgressException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class)))
                .thenThrow(TransactionInProgressException.class);

        // Run the test
        assertThrows(TransactionInProgressException.class, () -> myClassUnderTest.tryTransactWriteItems2());
    }

    @Test
    void testTryTransactWriteItems2_DynamoDbClientThrowsIdempotentParameterMismatchException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class)))
                .thenThrow(IdempotentParameterMismatchException.class);

        // Run the test
        assertThrows(IdempotentParameterMismatchException.class, () -> myClassUnderTest.tryTransactWriteItems2());
    }

    @Test
    void testTryTransactWriteItems2_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class)))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryTransactWriteItems2());
    }

    @Test
    void testTryTransactWriteItems2_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryTransactWriteItems2());
    }

    @Test
    void testTryTransactWriteItems2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryTransactWriteItems2());
    }

    @Test
    void testTryTransactWriteItems2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryTransactWriteItems2());
    }

    @Test
    void testTryTransactWriteItems2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryTransactWriteItems2());
    }

    @Test
    void testTryTransactWriteItems2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryTransactWriteItems2());
    }

    @Test
    void testTryUntagResource1() {
        // Setup
        when(mockDynamoDbClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenReturn(UntagResourceResponse.builder().build());

        // Run the test
        myClassUnderTest.tryUntagResource1();

        // Verify the results
    }

    @Test
    void testTryUntagResource1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource2() {
        // Setup
        when(mockDynamoDbClient.untagResource(any(Consumer.class))).thenReturn(UntagResourceResponse.builder().build());

        // Run the test
        myClassUnderTest.tryUntagResource2();

        // Verify the results
    }

    @Test
    void testTryUntagResource2_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.untagResource(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.untagResource(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.untagResource(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.untagResource(any(Consumer.class))).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.untagResource(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.untagResource(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.untagResource(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUpdateContinuousBackups1() {
        // Setup
        // Configure DynamoDbClient.updateContinuousBackups(...).
        final UpdateContinuousBackupsResponse updateContinuousBackupsResponse = UpdateContinuousBackupsResponse.builder()
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
        when(mockDynamoDbClient.updateContinuousBackups(UpdateContinuousBackupsRequest.builder()
                .tableName("tableName")
                .pointInTimeRecoverySpecification(PointInTimeRecoverySpecification.builder()
                        .pointInTimeRecoveryEnabled(false)
                        .build())
                .build())).thenReturn(updateContinuousBackupsResponse);

        // Run the test
        myClassUnderTest.tryUpdateContinuousBackups1();

        // Verify the results
    }

    @Test
    void testTryUpdateContinuousBackups1_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(UpdateContinuousBackupsRequest.builder()
                .tableName("tableName")
                .pointInTimeRecoverySpecification(PointInTimeRecoverySpecification.builder()
                        .pointInTimeRecoveryEnabled(false)
                        .build())
                .build())).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryUpdateContinuousBackups1());
    }

    @Test
    void testTryUpdateContinuousBackups1_DynamoDbClientThrowsContinuousBackupsUnavailableException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(UpdateContinuousBackupsRequest.builder()
                .tableName("tableName")
                .pointInTimeRecoverySpecification(PointInTimeRecoverySpecification.builder()
                        .pointInTimeRecoveryEnabled(false)
                        .build())
                .build())).thenThrow(ContinuousBackupsUnavailableException.class);

        // Run the test
        assertThrows(ContinuousBackupsUnavailableException.class, () -> myClassUnderTest.tryUpdateContinuousBackups1());
    }

    @Test
    void testTryUpdateContinuousBackups1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(UpdateContinuousBackupsRequest.builder()
                .tableName("tableName")
                .pointInTimeRecoverySpecification(PointInTimeRecoverySpecification.builder()
                        .pointInTimeRecoveryEnabled(false)
                        .build())
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateContinuousBackups1());
    }

    @Test
    void testTryUpdateContinuousBackups1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(UpdateContinuousBackupsRequest.builder()
                .tableName("tableName")
                .pointInTimeRecoverySpecification(PointInTimeRecoverySpecification.builder()
                        .pointInTimeRecoveryEnabled(false)
                        .build())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateContinuousBackups1());
    }

    @Test
    void testTryUpdateContinuousBackups1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(UpdateContinuousBackupsRequest.builder()
                .tableName("tableName")
                .pointInTimeRecoverySpecification(PointInTimeRecoverySpecification.builder()
                        .pointInTimeRecoveryEnabled(false)
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateContinuousBackups1());
    }

    @Test
    void testTryUpdateContinuousBackups1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(UpdateContinuousBackupsRequest.builder()
                .tableName("tableName")
                .pointInTimeRecoverySpecification(PointInTimeRecoverySpecification.builder()
                        .pointInTimeRecoveryEnabled(false)
                        .build())
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateContinuousBackups1());
    }

    @Test
    void testTryUpdateContinuousBackups2() {
        // Setup
        // Configure DynamoDbClient.updateContinuousBackups(...).
        final UpdateContinuousBackupsResponse updateContinuousBackupsResponse = UpdateContinuousBackupsResponse.builder()
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
        when(mockDynamoDbClient.updateContinuousBackups(any(Consumer.class)))
                .thenReturn(updateContinuousBackupsResponse);

        // Run the test
        myClassUnderTest.tryUpdateContinuousBackups2();

        // Verify the results
    }

    @Test
    void testTryUpdateContinuousBackups2_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(any(Consumer.class))).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryUpdateContinuousBackups2());
    }

    @Test
    void testTryUpdateContinuousBackups2_DynamoDbClientThrowsContinuousBackupsUnavailableException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(any(Consumer.class)))
                .thenThrow(ContinuousBackupsUnavailableException.class);

        // Run the test
        assertThrows(ContinuousBackupsUnavailableException.class, () -> myClassUnderTest.tryUpdateContinuousBackups2());
    }

    @Test
    void testTryUpdateContinuousBackups2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateContinuousBackups2());
    }

    @Test
    void testTryUpdateContinuousBackups2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateContinuousBackups2());
    }

    @Test
    void testTryUpdateContinuousBackups2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateContinuousBackups2());
    }

    @Test
    void testTryUpdateContinuousBackups2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateContinuousBackups2());
    }

    @Test
    void testTryUpdateContributorInsights1() {
        // Setup
        // Configure DynamoDbClient.updateContributorInsights(...).
        final UpdateContributorInsightsResponse updateContributorInsightsResponse = UpdateContributorInsightsResponse.builder()
                .tableName("tableName")
                .indexName("indexName")
                .contributorInsightsStatus(ContributorInsightsStatus.ENABLING)
                .build();
        when(mockDynamoDbClient.updateContributorInsights(UpdateContributorInsightsRequest.builder()
                .tableName("tableName")
                .indexName("indexName")
                .contributorInsightsAction(ContributorInsightsAction.ENABLE)
                .build())).thenReturn(updateContributorInsightsResponse);

        // Run the test
        myClassUnderTest.tryUpdateContributorInsights1();

        // Verify the results
    }

    @Test
    void testTryUpdateContributorInsights1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateContributorInsights(UpdateContributorInsightsRequest.builder()
                .tableName("tableName")
                .indexName("indexName")
                .contributorInsightsAction(ContributorInsightsAction.ENABLE)
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUpdateContributorInsights1());
    }

    @Test
    void testTryUpdateContributorInsights1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateContributorInsights(UpdateContributorInsightsRequest.builder()
                .tableName("tableName")
                .indexName("indexName")
                .contributorInsightsAction(ContributorInsightsAction.ENABLE)
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateContributorInsights1());
    }

    @Test
    void testTryUpdateContributorInsights1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateContributorInsights(UpdateContributorInsightsRequest.builder()
                .tableName("tableName")
                .indexName("indexName")
                .contributorInsightsAction(ContributorInsightsAction.ENABLE)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateContributorInsights1());
    }

    @Test
    void testTryUpdateContributorInsights1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateContributorInsights(UpdateContributorInsightsRequest.builder()
                .tableName("tableName")
                .indexName("indexName")
                .contributorInsightsAction(ContributorInsightsAction.ENABLE)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateContributorInsights1());
    }

    @Test
    void testTryUpdateContributorInsights1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateContributorInsights(UpdateContributorInsightsRequest.builder()
                .tableName("tableName")
                .indexName("indexName")
                .contributorInsightsAction(ContributorInsightsAction.ENABLE)
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateContributorInsights1());
    }

    @Test
    void testTryUpdateContributorInsights2() {
        // Setup
        // Configure DynamoDbClient.updateContributorInsights(...).
        final UpdateContributorInsightsResponse updateContributorInsightsResponse = UpdateContributorInsightsResponse.builder()
                .tableName("tableName")
                .indexName("indexName")
                .contributorInsightsStatus(ContributorInsightsStatus.ENABLING)
                .build();
        when(mockDynamoDbClient.updateContributorInsights(any(Consumer.class)))
                .thenReturn(updateContributorInsightsResponse);

        // Run the test
        myClassUnderTest.tryUpdateContributorInsights2();

        // Verify the results
    }

    @Test
    void testTryUpdateContributorInsights2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateContributorInsights(any(Consumer.class)))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUpdateContributorInsights2());
    }

    @Test
    void testTryUpdateContributorInsights2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateContributorInsights(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateContributorInsights2());
    }

    @Test
    void testTryUpdateContributorInsights2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateContributorInsights(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateContributorInsights2());
    }

    @Test
    void testTryUpdateContributorInsights2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateContributorInsights(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateContributorInsights2());
    }

    @Test
    void testTryUpdateContributorInsights2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateContributorInsights(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateContributorInsights2());
    }

    @Test
    void testTryUpdateGlobalTable1() {
        // Setup
        // Configure DynamoDbClient.updateGlobalTable(...).
        final UpdateGlobalTableResponse updateGlobalTableResponse = UpdateGlobalTableResponse.builder()
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
        when(mockDynamoDbClient.updateGlobalTable(UpdateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicaUpdates(ReplicaUpdate.builder()
                        .create(CreateReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .delete(DeleteReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .build())
                .build())).thenReturn(updateGlobalTableResponse);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTable1();

        // Verify the results
    }

    @Test
    void testTryUpdateGlobalTable1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(UpdateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicaUpdates(ReplicaUpdate.builder()
                        .create(CreateReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .delete(DeleteReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .build())
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateGlobalTable1());
    }

    @Test
    void testTryUpdateGlobalTable1_DynamoDbClientThrowsGlobalTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(UpdateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicaUpdates(ReplicaUpdate.builder()
                        .create(CreateReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .delete(DeleteReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .build())
                .build())).thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTable1());
    }

    @Test
    void testTryUpdateGlobalTable1_DynamoDbClientThrowsReplicaAlreadyExistsException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(UpdateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicaUpdates(ReplicaUpdate.builder()
                        .create(CreateReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .delete(DeleteReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .build())
                .build())).thenThrow(ReplicaAlreadyExistsException.class);

        // Run the test
        assertThrows(ReplicaAlreadyExistsException.class, () -> myClassUnderTest.tryUpdateGlobalTable1());
    }

    @Test
    void testTryUpdateGlobalTable1_DynamoDbClientThrowsReplicaNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(UpdateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicaUpdates(ReplicaUpdate.builder()
                        .create(CreateReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .delete(DeleteReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .build())
                .build())).thenThrow(ReplicaNotFoundException.class);

        // Run the test
        assertThrows(ReplicaNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTable1());
    }

    @Test
    void testTryUpdateGlobalTable1_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(UpdateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicaUpdates(ReplicaUpdate.builder()
                        .create(CreateReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .delete(DeleteReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .build())
                .build())).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTable1());
    }

    @Test
    void testTryUpdateGlobalTable1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(UpdateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicaUpdates(ReplicaUpdate.builder()
                        .create(CreateReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .delete(DeleteReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .build())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateGlobalTable1());
    }

    @Test
    void testTryUpdateGlobalTable1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(UpdateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicaUpdates(ReplicaUpdate.builder()
                        .create(CreateReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .delete(DeleteReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateGlobalTable1());
    }

    @Test
    void testTryUpdateGlobalTable1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(UpdateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicaUpdates(ReplicaUpdate.builder()
                        .create(CreateReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .delete(DeleteReplicaAction.builder()
                                .regionName("regionName")
                                .build())
                        .build())
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateGlobalTable1());
    }

    @Test
    void testTryUpdateGlobalTable2() {
        // Setup
        // Configure DynamoDbClient.updateGlobalTable(...).
        final UpdateGlobalTableResponse updateGlobalTableResponse = UpdateGlobalTableResponse.builder()
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
        when(mockDynamoDbClient.updateGlobalTable(any(Consumer.class))).thenReturn(updateGlobalTableResponse);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTable2();

        // Verify the results
    }

    @Test
    void testTryUpdateGlobalTable2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateGlobalTable2());
    }

    @Test
    void testTryUpdateGlobalTable2_DynamoDbClientThrowsGlobalTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(any(Consumer.class))).thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTable2());
    }

    @Test
    void testTryUpdateGlobalTable2_DynamoDbClientThrowsReplicaAlreadyExistsException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(any(Consumer.class))).thenThrow(ReplicaAlreadyExistsException.class);

        // Run the test
        assertThrows(ReplicaAlreadyExistsException.class, () -> myClassUnderTest.tryUpdateGlobalTable2());
    }

    @Test
    void testTryUpdateGlobalTable2_DynamoDbClientThrowsReplicaNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(any(Consumer.class))).thenThrow(ReplicaNotFoundException.class);

        // Run the test
        assertThrows(ReplicaNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTable2());
    }

    @Test
    void testTryUpdateGlobalTable2_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(any(Consumer.class))).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTable2());
    }

    @Test
    void testTryUpdateGlobalTable2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateGlobalTable2());
    }

    @Test
    void testTryUpdateGlobalTable2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateGlobalTable2());
    }

    @Test
    void testTryUpdateGlobalTable2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateGlobalTable2());
    }

    @Test
    void testTryUpdateGlobalTableSettings1() {
        // Setup
        // Configure DynamoDbClient.updateGlobalTableSettings(...).
        final UpdateGlobalTableSettingsResponse updateGlobalTableSettingsResponse = UpdateGlobalTableSettingsResponse.builder()
                .globalTableName("globalTableName")
                .replicaSettings(ReplicaSettingsDescription.builder().build())
                .build();
        when(mockDynamoDbClient.updateGlobalTableSettings(UpdateGlobalTableSettingsRequest.builder()
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
                .build())).thenReturn(updateGlobalTableSettingsResponse);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTableSettings1();

        // Verify the results
    }

    @Test
    void testTryUpdateGlobalTableSettings1_DynamoDbClientThrowsGlobalTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(UpdateGlobalTableSettingsRequest.builder()
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
                .build())).thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings1());
    }

    @Test
    void testTryUpdateGlobalTableSettings1_DynamoDbClientThrowsReplicaNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(UpdateGlobalTableSettingsRequest.builder()
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
                .build())).thenThrow(ReplicaNotFoundException.class);

        // Run the test
        assertThrows(ReplicaNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings1());
    }

    @Test
    void testTryUpdateGlobalTableSettings1_DynamoDbClientThrowsIndexNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(UpdateGlobalTableSettingsRequest.builder()
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
                .build())).thenThrow(IndexNotFoundException.class);

        // Run the test
        assertThrows(IndexNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings1());
    }

    @Test
    void testTryUpdateGlobalTableSettings1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(UpdateGlobalTableSettingsRequest.builder()
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
                .build())).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings1());
    }

    @Test
    void testTryUpdateGlobalTableSettings1_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(UpdateGlobalTableSettingsRequest.builder()
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
                .build())).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings1());
    }

    @Test
    void testTryUpdateGlobalTableSettings1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(UpdateGlobalTableSettingsRequest.builder()
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
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings1());
    }

    @Test
    void testTryUpdateGlobalTableSettings1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(UpdateGlobalTableSettingsRequest.builder()
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
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings1());
    }

    @Test
    void testTryUpdateGlobalTableSettings1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(UpdateGlobalTableSettingsRequest.builder()
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
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings1());
    }

    @Test
    void testTryUpdateGlobalTableSettings1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(UpdateGlobalTableSettingsRequest.builder()
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
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings1());
    }

    @Test
    void testTryUpdateGlobalTableSettings2() {
        // Setup
        // Configure DynamoDbClient.updateGlobalTableSettings(...).
        final UpdateGlobalTableSettingsResponse updateGlobalTableSettingsResponse = UpdateGlobalTableSettingsResponse.builder()
                .globalTableName("globalTableName")
                .replicaSettings(ReplicaSettingsDescription.builder().build())
                .build();
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class)))
                .thenReturn(updateGlobalTableSettingsResponse);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTableSettings2();

        // Verify the results
    }

    @Test
    void testTryUpdateGlobalTableSettings2_DynamoDbClientThrowsGlobalTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class)))
                .thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings2());
    }

    @Test
    void testTryUpdateGlobalTableSettings2_DynamoDbClientThrowsReplicaNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class)))
                .thenThrow(ReplicaNotFoundException.class);

        // Run the test
        assertThrows(ReplicaNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings2());
    }

    @Test
    void testTryUpdateGlobalTableSettings2_DynamoDbClientThrowsIndexNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class))).thenThrow(IndexNotFoundException.class);

        // Run the test
        assertThrows(IndexNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings2());
    }

    @Test
    void testTryUpdateGlobalTableSettings2_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings2());
    }

    @Test
    void testTryUpdateGlobalTableSettings2_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class))).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings2());
    }

    @Test
    void testTryUpdateGlobalTableSettings2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings2());
    }

    @Test
    void testTryUpdateGlobalTableSettings2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings2());
    }

    @Test
    void testTryUpdateGlobalTableSettings2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings2());
    }

    @Test
    void testTryUpdateGlobalTableSettings2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings2());
    }

    @Test
    void testTryUpdateItem1() {
        // Setup
        // Configure DynamoDbClient.updateItem(...).
        final UpdateItemResponse updateItemResponse = UpdateItemResponse.builder()
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
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder()
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
                .build())).thenReturn(updateItemResponse);

        // Run the test
        myClassUnderTest.tryUpdateItem1();

        // Verify the results
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder()
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
                .build())).thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder()
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
                .build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder()
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
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder()
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
                .build())).thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsTransactionConflictException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder()
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
                .build())).thenThrow(TransactionConflictException.class);

        // Run the test
        assertThrows(TransactionConflictException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder()
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
                .build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder()
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
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder()
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
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder()
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
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder()
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
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem2() {
        // Setup
        // Configure DynamoDbClient.updateItem(...).
        final UpdateItemResponse updateItemResponse = UpdateItemResponse.builder()
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
        when(mockDynamoDbClient.updateItem(any(Consumer.class))).thenReturn(updateItemResponse);

        // Run the test
        myClassUnderTest.tryUpdateItem2();

        // Verify the results
    }

    @Test
    void testTryUpdateItem2_DynamoDbClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class))).thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, () -> myClassUnderTest.tryUpdateItem2());
    }

    @Test
    void testTryUpdateItem2_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class)))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryUpdateItem2());
    }

    @Test
    void testTryUpdateItem2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUpdateItem2());
    }

    @Test
    void testTryUpdateItem2_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class)))
                .thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.tryUpdateItem2());
    }

    @Test
    void testTryUpdateItem2_DynamoDbClientThrowsTransactionConflictException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class))).thenThrow(TransactionConflictException.class);

        // Run the test
        assertThrows(TransactionConflictException.class, () -> myClassUnderTest.tryUpdateItem2());
    }

    @Test
    void testTryUpdateItem2_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryUpdateItem2());
    }

    @Test
    void testTryUpdateItem2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateItem2());
    }

    @Test
    void testTryUpdateItem2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateItem2());
    }

    @Test
    void testTryUpdateItem2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateItem2());
    }

    @Test
    void testTryUpdateItem2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateItem2());
    }

    @Test
    void testTryUpdateTable1() {
        // Setup
        // Configure DynamoDbClient.updateTable(...).
        final UpdateTableResponse updateTableResponse = UpdateTableResponse.builder()
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
        when(mockDynamoDbClient.updateTable(UpdateTableRequest.builder()
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
                .build())).thenReturn(updateTableResponse);

        // Run the test
        myClassUnderTest.tryUpdateTable1();

        // Verify the results
    }

    @Test
    void testTryUpdateTable1_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.updateTable(UpdateTableRequest.builder()
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
                .build())).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryUpdateTable1());
    }

    @Test
    void testTryUpdateTable1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateTable(UpdateTableRequest.builder()
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
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUpdateTable1());
    }

    @Test
    void testTryUpdateTable1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateTable(UpdateTableRequest.builder()
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
                .build())).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUpdateTable1());
    }

    @Test
    void testTryUpdateTable1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateTable(UpdateTableRequest.builder()
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
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateTable1());
    }

    @Test
    void testTryUpdateTable1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateTable(UpdateTableRequest.builder()
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
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateTable1());
    }

    @Test
    void testTryUpdateTable1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateTable(UpdateTableRequest.builder()
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
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateTable1());
    }

    @Test
    void testTryUpdateTable1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateTable(UpdateTableRequest.builder()
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
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateTable1());
    }

    @Test
    void testTryUpdateTable2() {
        // Setup
        // Configure DynamoDbClient.updateTable(...).
        final UpdateTableResponse updateTableResponse = UpdateTableResponse.builder()
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
        when(mockDynamoDbClient.updateTable(any(Consumer.class))).thenReturn(updateTableResponse);

        // Run the test
        myClassUnderTest.tryUpdateTable2();

        // Verify the results
    }

    @Test
    void testTryUpdateTable2_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.updateTable(any(Consumer.class))).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryUpdateTable2());
    }

    @Test
    void testTryUpdateTable2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateTable(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUpdateTable2());
    }

    @Test
    void testTryUpdateTable2_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateTable(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUpdateTable2());
    }

    @Test
    void testTryUpdateTable2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateTable(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateTable2());
    }

    @Test
    void testTryUpdateTable2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateTable(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateTable2());
    }

    @Test
    void testTryUpdateTable2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateTable(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateTable2());
    }

    @Test
    void testTryUpdateTable2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateTable(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateTable2());
    }

    @Test
    void testTryUpdateTableReplicaAutoScaling1() {
        // Setup
        // Configure DynamoDbClient.updateTableReplicaAutoScaling(...).
        final UpdateTableReplicaAutoScalingResponse updateTableReplicaAutoScalingResponse = UpdateTableReplicaAutoScalingResponse.builder()
                .tableAutoScalingDescription(TableAutoScalingDescription.builder()
                        .tableName("tableName")
                        .tableStatus(TableStatus.CREATING)
                        .replicas(ReplicaAutoScalingDescription.builder().build())
                        .build())
                .build();
        when(mockDynamoDbClient.updateTableReplicaAutoScaling(UpdateTableReplicaAutoScalingRequest.builder()
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
                .build())).thenReturn(updateTableReplicaAutoScalingResponse);

        // Run the test
        myClassUnderTest.tryUpdateTableReplicaAutoScaling1();

        // Verify the results
    }

    @Test
    void testTryUpdateTableReplicaAutoScaling1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateTableReplicaAutoScaling(UpdateTableReplicaAutoScalingRequest.builder()
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
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUpdateTableReplicaAutoScaling1());
    }

    @Test
    void testTryUpdateTableReplicaAutoScaling1_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.updateTableReplicaAutoScaling(UpdateTableReplicaAutoScalingRequest.builder()
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
                .build())).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryUpdateTableReplicaAutoScaling1());
    }

    @Test
    void testTryUpdateTableReplicaAutoScaling1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateTableReplicaAutoScaling(UpdateTableReplicaAutoScalingRequest.builder()
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
                .build())).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUpdateTableReplicaAutoScaling1());
    }

    @Test
    void testTryUpdateTableReplicaAutoScaling1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateTableReplicaAutoScaling(UpdateTableReplicaAutoScalingRequest.builder()
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
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateTableReplicaAutoScaling1());
    }

    @Test
    void testTryUpdateTableReplicaAutoScaling1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateTableReplicaAutoScaling(UpdateTableReplicaAutoScalingRequest.builder()
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
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateTableReplicaAutoScaling1());
    }

    @Test
    void testTryUpdateTableReplicaAutoScaling1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateTableReplicaAutoScaling(UpdateTableReplicaAutoScalingRequest.builder()
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
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateTableReplicaAutoScaling1());
    }

    @Test
    void testTryUpdateTableReplicaAutoScaling1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateTableReplicaAutoScaling(UpdateTableReplicaAutoScalingRequest.builder()
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
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateTableReplicaAutoScaling1());
    }

    @Test
    void testTryUpdateTableReplicaAutoScaling2() {
        // Setup
        // Configure DynamoDbClient.updateTableReplicaAutoScaling(...).
        final UpdateTableReplicaAutoScalingResponse updateTableReplicaAutoScalingResponse = UpdateTableReplicaAutoScalingResponse.builder()
                .tableAutoScalingDescription(TableAutoScalingDescription.builder()
                        .tableName("tableName")
                        .tableStatus(TableStatus.CREATING)
                        .replicas(ReplicaAutoScalingDescription.builder().build())
                        .build())
                .build();
        when(mockDynamoDbClient.updateTableReplicaAutoScaling(any(Consumer.class)))
                .thenReturn(updateTableReplicaAutoScalingResponse);

        // Run the test
        myClassUnderTest.tryUpdateTableReplicaAutoScaling2();

        // Verify the results
    }

    @Test
    void testTryUpdateTableReplicaAutoScaling2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateTableReplicaAutoScaling(any(Consumer.class)))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUpdateTableReplicaAutoScaling2());
    }

    @Test
    void testTryUpdateTableReplicaAutoScaling2_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.updateTableReplicaAutoScaling(any(Consumer.class)))
                .thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryUpdateTableReplicaAutoScaling2());
    }

    @Test
    void testTryUpdateTableReplicaAutoScaling2_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateTableReplicaAutoScaling(any(Consumer.class)))
                .thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUpdateTableReplicaAutoScaling2());
    }

    @Test
    void testTryUpdateTableReplicaAutoScaling2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateTableReplicaAutoScaling(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateTableReplicaAutoScaling2());
    }

    @Test
    void testTryUpdateTableReplicaAutoScaling2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateTableReplicaAutoScaling(any(Consumer.class)))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateTableReplicaAutoScaling2());
    }

    @Test
    void testTryUpdateTableReplicaAutoScaling2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateTableReplicaAutoScaling(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateTableReplicaAutoScaling2());
    }

    @Test
    void testTryUpdateTableReplicaAutoScaling2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateTableReplicaAutoScaling(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateTableReplicaAutoScaling2());
    }

    @Test
    void testTryUpdateTimeToLive1() {
        // Setup
        // Configure DynamoDbClient.updateTimeToLive(...).
        final UpdateTimeToLiveResponse updateTimeToLiveResponse = UpdateTimeToLiveResponse.builder()
                .timeToLiveSpecification(TimeToLiveSpecification.builder()
                        .enabled(false)
                        .attributeName("attributeName")
                        .build())
                .build();
        when(mockDynamoDbClient.updateTimeToLive(UpdateTimeToLiveRequest.builder()
                .tableName("tableName")
                .timeToLiveSpecification(TimeToLiveSpecification.builder()
                        .enabled(false)
                        .attributeName("attributeName")
                        .build())
                .build())).thenReturn(updateTimeToLiveResponse);

        // Run the test
        myClassUnderTest.tryUpdateTimeToLive1();

        // Verify the results
    }

    @Test
    void testTryUpdateTimeToLive1_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(UpdateTimeToLiveRequest.builder()
                .tableName("tableName")
                .timeToLiveSpecification(TimeToLiveSpecification.builder()
                        .enabled(false)
                        .attributeName("attributeName")
                        .build())
                .build())).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryUpdateTimeToLive1());
    }

    @Test
    void testTryUpdateTimeToLive1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(UpdateTimeToLiveRequest.builder()
                .tableName("tableName")
                .timeToLiveSpecification(TimeToLiveSpecification.builder()
                        .enabled(false)
                        .attributeName("attributeName")
                        .build())
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUpdateTimeToLive1());
    }

    @Test
    void testTryUpdateTimeToLive1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(UpdateTimeToLiveRequest.builder()
                .tableName("tableName")
                .timeToLiveSpecification(TimeToLiveSpecification.builder()
                        .enabled(false)
                        .attributeName("attributeName")
                        .build())
                .build())).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUpdateTimeToLive1());
    }

    @Test
    void testTryUpdateTimeToLive1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(UpdateTimeToLiveRequest.builder()
                .tableName("tableName")
                .timeToLiveSpecification(TimeToLiveSpecification.builder()
                        .enabled(false)
                        .attributeName("attributeName")
                        .build())
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateTimeToLive1());
    }

    @Test
    void testTryUpdateTimeToLive1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(UpdateTimeToLiveRequest.builder()
                .tableName("tableName")
                .timeToLiveSpecification(TimeToLiveSpecification.builder()
                        .enabled(false)
                        .attributeName("attributeName")
                        .build())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateTimeToLive1());
    }

    @Test
    void testTryUpdateTimeToLive1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(UpdateTimeToLiveRequest.builder()
                .tableName("tableName")
                .timeToLiveSpecification(TimeToLiveSpecification.builder()
                        .enabled(false)
                        .attributeName("attributeName")
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateTimeToLive1());
    }

    @Test
    void testTryUpdateTimeToLive1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(UpdateTimeToLiveRequest.builder()
                .tableName("tableName")
                .timeToLiveSpecification(TimeToLiveSpecification.builder()
                        .enabled(false)
                        .attributeName("attributeName")
                        .build())
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateTimeToLive1());
    }

    @Test
    void testTryUpdateTimeToLive2() {
        // Setup
        // Configure DynamoDbClient.updateTimeToLive(...).
        final UpdateTimeToLiveResponse updateTimeToLiveResponse = UpdateTimeToLiveResponse.builder()
                .timeToLiveSpecification(TimeToLiveSpecification.builder()
                        .enabled(false)
                        .attributeName("attributeName")
                        .build())
                .build();
        when(mockDynamoDbClient.updateTimeToLive(any(Consumer.class))).thenReturn(updateTimeToLiveResponse);

        // Run the test
        myClassUnderTest.tryUpdateTimeToLive2();

        // Verify the results
    }

    @Test
    void testTryUpdateTimeToLive2_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(any(Consumer.class))).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryUpdateTimeToLive2());
    }

    @Test
    void testTryUpdateTimeToLive2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUpdateTimeToLive2());
    }

    @Test
    void testTryUpdateTimeToLive2_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUpdateTimeToLive2());
    }

    @Test
    void testTryUpdateTimeToLive2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateTimeToLive2());
    }

    @Test
    void testTryUpdateTimeToLive2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateTimeToLive2());
    }

    @Test
    void testTryUpdateTimeToLive2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateTimeToLive2());
    }

    @Test
    void testTryUpdateTimeToLive2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateTimeToLive2());
    }

    @Test
    void testTryWaiter() {
        // Setup
        // Run the test
        myClassUnderTest.tryWaiter();

        // Verify the results
    }
}
