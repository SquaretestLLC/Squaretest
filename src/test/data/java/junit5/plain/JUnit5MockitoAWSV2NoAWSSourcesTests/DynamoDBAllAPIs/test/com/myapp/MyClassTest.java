package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.paginators.BatchGetItemIterable;
import software.amazon.awssdk.services.dynamodb.paginators.ListTablesIterable;
import software.amazon.awssdk.services.dynamodb.paginators.QueryIterable;
import software.amazon.awssdk.services.dynamodb.paginators.ScanIterable;

import java.util.*;
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
    void testTryBatchGetItem() {
        // Setup
        // Configure DynamoDbClient.batchGetItem(...).
        final BatchGetItemResponse batchGetItemResponse = BatchGetItemResponse.builder()
                .responses(new HashMap<String, List<Map<String, AttributeValue>>>() {{
                    put("TableName1", Arrays.asList(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                put("KeyName2", AttributeValue.builder().s("Value2").build());
                            }}
                    ));
                }}).build();
        when(mockDynamoDbClient.batchGetItem(BatchGetItemRequest.builder()
                .requestItems(
                        new HashMap<String, KeysAndAttributes>() {{
                            put("TableName1", KeysAndAttributes.builder()
                                    .keys(Arrays.asList(new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                    }})).build());
                        }}).build())).thenReturn(batchGetItemResponse);

        // Run the test
        myClassUnderTest.tryBatchGetItem();

        // Verify the results
    }

    @Test
    void testTryBatchGetItem_DynamoDbClientReturnsNoItems() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(BatchGetItemRequest.builder()
                .requestItems(
                        new HashMap<String, KeysAndAttributes>() {{
                            put("TableName1", KeysAndAttributes.builder()
                                    .keys(Arrays.asList(new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                    }})).build());
                        }}).build())).thenReturn(BatchGetItemResponse.builder().build());

        // Run the test
        myClassUnderTest.tryBatchGetItem();

        // Verify the results
    }

    @Test
    void testTryBatchGetItem_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(BatchGetItemRequest.builder()
                .requestItems(
                        new HashMap<String, KeysAndAttributes>() {{
                            put("TableName1", KeysAndAttributes.builder()
                                    .keys(Arrays.asList(new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                    }})).build());
                        }}).build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryBatchGetItem());
    }

    @Test
    void testTryBatchGetItem_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(BatchGetItemRequest.builder()
                .requestItems(
                        new HashMap<String, KeysAndAttributes>() {{
                            put("TableName1", KeysAndAttributes.builder()
                                    .keys(Arrays.asList(new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                    }})).build());
                        }}).build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryBatchGetItem());
    }

    @Test
    void testTryBatchGetItem_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(BatchGetItemRequest.builder()
                .requestItems(
                        new HashMap<String, KeysAndAttributes>() {{
                            put("TableName1", KeysAndAttributes.builder()
                                    .keys(Arrays.asList(new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                    }})).build());
                        }}).build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryBatchGetItem());
    }

    @Test
    void testTryBatchGetItem_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(BatchGetItemRequest.builder()
                .requestItems(
                        new HashMap<String, KeysAndAttributes>() {{
                            put("TableName1", KeysAndAttributes.builder()
                                    .keys(Arrays.asList(new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                    }})).build());
                        }}).build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryBatchGetItem());
    }

    @Test
    void testTryBatchGetItem_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(BatchGetItemRequest.builder()
                .requestItems(
                        new HashMap<String, KeysAndAttributes>() {{
                            put("TableName1", KeysAndAttributes.builder()
                                    .keys(Arrays.asList(new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                    }})).build());
                        }}).build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryBatchGetItem());
    }

    @Test
    void testTryBatchGetItem_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(BatchGetItemRequest.builder()
                .requestItems(
                        new HashMap<String, KeysAndAttributes>() {{
                            put("TableName1", KeysAndAttributes.builder()
                                    .keys(Arrays.asList(new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                    }})).build());
                        }}).build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryBatchGetItem());
    }

    @Test
    void testTryBatchGetItem_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(BatchGetItemRequest.builder()
                .requestItems(
                        new HashMap<String, KeysAndAttributes>() {{
                            put("TableName1", KeysAndAttributes.builder()
                                    .keys(Arrays.asList(new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                    }})).build());
                        }}).build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryBatchGetItem());
    }

    @Test
    void testTryBatchGetItem1() {
        // Setup
        // Configure DynamoDbClient.batchGetItem(...).
        final BatchGetItemResponse batchGetItemResponse = BatchGetItemResponse.builder()
                .responses(new HashMap<String, List<Map<String, AttributeValue>>>() {{
                    put("TableName1", Arrays.asList(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                put("KeyName2", AttributeValue.builder().s("Value2").build());
                            }}
                    ));
                }}).build();
        when(mockDynamoDbClient.batchGetItem(any(Consumer.class))).thenReturn(batchGetItemResponse);

        // Run the test
        myClassUnderTest.tryBatchGetItem1();

        // Verify the results
    }

    @Test
    void testTryBatchGetItem1_DynamoDbClientReturnsNoItems() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(any(Consumer.class))).thenReturn(BatchGetItemResponse.builder().build());

        // Run the test
        myClassUnderTest.tryBatchGetItem1();

        // Verify the results
    }

    @Test
    void testTryBatchGetItem1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(any(Consumer.class)))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryBatchGetItem1());
    }

    @Test
    void testTryBatchGetItem1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryBatchGetItem1());
    }

    @Test
    void testTryBatchGetItem1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryBatchGetItem1());
    }

    @Test
    void testTryBatchGetItem1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryBatchGetItem1());
    }

    @Test
    void testTryBatchGetItem1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryBatchGetItem1());
    }

    @Test
    void testTryBatchGetItem1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryBatchGetItem1());
    }

    @Test
    void testTryBatchGetItem1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.batchGetItem(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryBatchGetItem1());
    }

    @Test
    void testTryBatchGetItemPaginator() {
        // Setup
        // Configure DynamoDbClient.batchGetItemPaginator(...).
        final BatchGetItemIterable mockBatchGetItemIterable = mock(BatchGetItemIterable.class);
        when(mockDynamoDbClient.batchGetItemPaginator(BatchGetItemRequest.builder()
                .requestItems(
                        new HashMap<String, KeysAndAttributes>() {{
                            put("TableName1", KeysAndAttributes.builder()
                                    .keys(Arrays.asList(new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                    }})).build());
                        }}).build())).thenReturn(mockBatchGetItemIterable);

        // Run the test
        myClassUnderTest.tryBatchGetItemPaginator();

        // Verify the results
    }

    @Test
    void testTryBatchGetItemPaginator_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.batchGetItemPaginator(...).
        final BatchGetItemIterable mockBatchGetItemIterable = mock(BatchGetItemIterable.class);
        when(mockDynamoDbClient.batchGetItemPaginator(BatchGetItemRequest.builder()
                .requestItems(
                        new HashMap<String, KeysAndAttributes>() {{
                            put("TableName1", KeysAndAttributes.builder()
                                    .keys(Arrays.asList(new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                    }})).build());
                        }}).build())).thenReturn(mockBatchGetItemIterable);

        // Run the test
        myClassUnderTest.tryBatchGetItemPaginator();

        // Verify the results
    }

    @Test
    void testTryBatchGetItemPaginator_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(BatchGetItemRequest.builder()
                .requestItems(
                        new HashMap<String, KeysAndAttributes>() {{
                            put("TableName1", KeysAndAttributes.builder()
                                    .keys(Arrays.asList(new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                    }})).build());
                        }}).build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryBatchGetItemPaginator());
    }

    @Test
    void testTryBatchGetItemPaginator_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(BatchGetItemRequest.builder()
                .requestItems(
                        new HashMap<String, KeysAndAttributes>() {{
                            put("TableName1", KeysAndAttributes.builder()
                                    .keys(Arrays.asList(new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                    }})).build());
                        }}).build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryBatchGetItemPaginator());
    }

    @Test
    void testTryBatchGetItemPaginator_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(BatchGetItemRequest.builder()
                .requestItems(
                        new HashMap<String, KeysAndAttributes>() {{
                            put("TableName1", KeysAndAttributes.builder()
                                    .keys(Arrays.asList(new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                    }})).build());
                        }}).build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryBatchGetItemPaginator());
    }

    @Test
    void testTryBatchGetItemPaginator_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(BatchGetItemRequest.builder()
                .requestItems(
                        new HashMap<String, KeysAndAttributes>() {{
                            put("TableName1", KeysAndAttributes.builder()
                                    .keys(Arrays.asList(new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                    }})).build());
                        }}).build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryBatchGetItemPaginator());
    }

    @Test
    void testTryBatchGetItemPaginator_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(BatchGetItemRequest.builder()
                .requestItems(
                        new HashMap<String, KeysAndAttributes>() {{
                            put("TableName1", KeysAndAttributes.builder()
                                    .keys(Arrays.asList(new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                    }})).build());
                        }}).build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryBatchGetItemPaginator());
    }

    @Test
    void testTryBatchGetItemPaginator_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(BatchGetItemRequest.builder()
                .requestItems(
                        new HashMap<String, KeysAndAttributes>() {{
                            put("TableName1", KeysAndAttributes.builder()
                                    .keys(Arrays.asList(new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                    }})).build());
                        }}).build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryBatchGetItemPaginator());
    }

    @Test
    void testTryBatchGetItemPaginator_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(BatchGetItemRequest.builder()
                .requestItems(
                        new HashMap<String, KeysAndAttributes>() {{
                            put("TableName1", KeysAndAttributes.builder()
                                    .keys(Arrays.asList(new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKey", AttributeValue.builder().s("PrimaryKeyValue").build());
                                    }})).build());
                        }}).build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryBatchGetItemPaginator());
    }

    @Test
    void testTryBatchGetItemPaginator1() {
        // Setup
        // Configure DynamoDbClient.batchGetItemPaginator(...).
        final BatchGetItemIterable mockBatchGetItemIterable = mock(BatchGetItemIterable.class);
        when(mockDynamoDbClient.batchGetItemPaginator(any(Consumer.class))).thenReturn(mockBatchGetItemIterable);

        // Run the test
        myClassUnderTest.tryBatchGetItemPaginator1();

        // Verify the results
    }

    @Test
    void testTryBatchGetItemPaginator1_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.batchGetItemPaginator(...).
        final BatchGetItemIterable mockBatchGetItemIterable = mock(BatchGetItemIterable.class);
        when(mockDynamoDbClient.batchGetItemPaginator(any(Consumer.class))).thenReturn(mockBatchGetItemIterable);

        // Run the test
        myClassUnderTest.tryBatchGetItemPaginator1();

        // Verify the results
    }

    @Test
    void testTryBatchGetItemPaginator1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(any(Consumer.class)))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryBatchGetItemPaginator1());
    }

    @Test
    void testTryBatchGetItemPaginator1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryBatchGetItemPaginator1());
    }

    @Test
    void testTryBatchGetItemPaginator1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(any(Consumer.class)))
                .thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryBatchGetItemPaginator1());
    }

    @Test
    void testTryBatchGetItemPaginator1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryBatchGetItemPaginator1());
    }

    @Test
    void testTryBatchGetItemPaginator1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryBatchGetItemPaginator1());
    }

    @Test
    void testTryBatchGetItemPaginator1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryBatchGetItemPaginator1());
    }

    @Test
    void testTryBatchGetItemPaginator1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.batchGetItemPaginator(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryBatchGetItemPaginator1());
    }

    @Test
    void testTryBatchWriteItem() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenReturn(BatchWriteItemResponse.builder().build());

        // Run the test
        myClassUnderTest.tryBatchWriteItem();

        // Verify the results
    }

    @Test
    void testTryBatchWriteItem_DynamoDbClientReturnsFailure() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenReturn(BatchWriteItemResponse.builder().unprocessedItems(new HashMap<>()).build());

        // Run the test
        myClassUnderTest.tryBatchWriteItem();

        // Verify the results
    }

    @Test
    void testTryBatchWriteItem_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryBatchWriteItem());
    }

    @Test
    void testTryBatchWriteItem_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryBatchWriteItem());
    }

    @Test
    void testTryBatchWriteItem_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.tryBatchWriteItem());
    }

    @Test
    void testTryBatchWriteItem_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryBatchWriteItem());
    }

    @Test
    void testTryBatchWriteItem_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryBatchWriteItem());
    }

    @Test
    void testTryBatchWriteItem_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryBatchWriteItem());
    }

    @Test
    void testTryBatchWriteItem_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryBatchWriteItem());
    }

    @Test
    void testTryBatchWriteItem_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryBatchWriteItem());
    }

    @Test
    void testTryBatchWriteItem1() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class)))
                .thenReturn(BatchWriteItemResponse.builder().build());

        // Run the test
        myClassUnderTest.tryBatchWriteItem1();

        // Verify the results
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientReturnsFailure() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class)))
                .thenReturn(BatchWriteItemResponse.builder().unprocessedItems(new HashMap<>()).build());

        // Run the test
        myClassUnderTest.tryBatchWriteItem1();

        // Verify the results
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class)))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class)))
                .thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryCreateBackup() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder().build()))
                .thenReturn(CreateBackupResponse.builder().build());

        // Run the test
        myClassUnderTest.tryCreateBackup();

        // Verify the results
    }

    @Test
    void testTryCreateBackup_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder().build()))
                .thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryCreateBackup());
    }

    @Test
    void testTryCreateBackup_DynamoDbClientThrowsTableInUseException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder().build()))
                .thenThrow(TableInUseException.class);

        // Run the test
        assertThrows(TableInUseException.class, () -> myClassUnderTest.tryCreateBackup());
    }

    @Test
    void testTryCreateBackup_DynamoDbClientThrowsContinuousBackupsUnavailableException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder().build()))
                .thenThrow(ContinuousBackupsUnavailableException.class);

        // Run the test
        assertThrows(ContinuousBackupsUnavailableException.class, () -> myClassUnderTest.tryCreateBackup());
    }

    @Test
    void testTryCreateBackup_DynamoDbClientThrowsBackupInUseException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder().build()))
                .thenThrow(BackupInUseException.class);

        // Run the test
        assertThrows(BackupInUseException.class, () -> myClassUnderTest.tryCreateBackup());
    }

    @Test
    void testTryCreateBackup_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder().build()))
                .thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateBackup());
    }

    @Test
    void testTryCreateBackup_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryCreateBackup());
    }

    @Test
    void testTryCreateBackup_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateBackup());
    }

    @Test
    void testTryCreateBackup_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateBackup());
    }

    @Test
    void testTryCreateBackup_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder().build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryCreateBackup());
    }

    @Test
    void testTryCreateBackup1() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class))).thenReturn(CreateBackupResponse.builder().build());

        // Run the test
        myClassUnderTest.tryCreateBackup1();

        // Verify the results
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class))).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryCreateBackup1());
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsTableInUseException() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class))).thenThrow(TableInUseException.class);

        // Run the test
        assertThrows(TableInUseException.class, () -> myClassUnderTest.tryCreateBackup1());
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsContinuousBackupsUnavailableException() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class)))
                .thenThrow(ContinuousBackupsUnavailableException.class);

        // Run the test
        assertThrows(ContinuousBackupsUnavailableException.class, () -> myClassUnderTest.tryCreateBackup1());
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsBackupInUseException() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class))).thenThrow(BackupInUseException.class);

        // Run the test
        assertThrows(BackupInUseException.class, () -> myClassUnderTest.tryCreateBackup1());
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateBackup1());
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryCreateBackup1());
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateBackup1());
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateBackup1());
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.createBackup(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryCreateBackup1());
    }

    @Test
    void testTryCreateGlobalTable() {
        // Setup
        // Configure DynamoDbClient.createGlobalTable(...).
        final CreateGlobalTableResponse createGlobalTableResponse = CreateGlobalTableResponse.builder().build();
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder().build()))
                .thenReturn(createGlobalTableResponse);

        // Run the test
        myClassUnderTest.tryCreateGlobalTable();

        // Verify the results
    }

    @Test
    void testTryCreateGlobalTable_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder().build()))
                .thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateGlobalTable());
    }

    @Test
    void testTryCreateGlobalTable_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryCreateGlobalTable());
    }

    @Test
    void testTryCreateGlobalTable_DynamoDbClientThrowsGlobalTableAlreadyExistsException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder().build()))
                .thenThrow(GlobalTableAlreadyExistsException.class);

        // Run the test
        assertThrows(GlobalTableAlreadyExistsException.class, () -> myClassUnderTest.tryCreateGlobalTable());
    }

    @Test
    void testTryCreateGlobalTable_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder().build()))
                .thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryCreateGlobalTable());
    }

    @Test
    void testTryCreateGlobalTable_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateGlobalTable());
    }

    @Test
    void testTryCreateGlobalTable_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateGlobalTable());
    }

    @Test
    void testTryCreateGlobalTable_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder().build()))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryCreateGlobalTable());
    }

    @Test
    void testTryCreateGlobalTable1() {
        // Setup
        // Configure DynamoDbClient.createGlobalTable(...).
        final CreateGlobalTableResponse createGlobalTableResponse = CreateGlobalTableResponse.builder().build();
        when(mockDynamoDbClient.createGlobalTable(any(Consumer.class))).thenReturn(createGlobalTableResponse);

        // Run the test
        myClassUnderTest.tryCreateGlobalTable1();

        // Verify the results
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateGlobalTable1());
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryCreateGlobalTable1());
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsGlobalTableAlreadyExistsException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(any(Consumer.class)))
                .thenThrow(GlobalTableAlreadyExistsException.class);

        // Run the test
        assertThrows(GlobalTableAlreadyExistsException.class, () -> myClassUnderTest.tryCreateGlobalTable1());
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(any(Consumer.class))).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryCreateGlobalTable1());
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateGlobalTable1());
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateGlobalTable1());
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryCreateGlobalTable1());
    }

    @Test
    void testTryCreateTable() {
        // Setup
        when(mockDynamoDbClient.createTable(CreateTableRequest.builder().build()))
                .thenReturn(CreateTableResponse.builder().build());

        // Run the test
        myClassUnderTest.tryCreateTable();

        // Verify the results
    }

    @Test
    void testTryCreateTable_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.createTable(CreateTableRequest.builder().build()))
                .thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryCreateTable());
    }

    @Test
    void testTryCreateTable_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.createTable(CreateTableRequest.builder().build()))
                .thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateTable());
    }

    @Test
    void testTryCreateTable_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.createTable(CreateTableRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryCreateTable());
    }

    @Test
    void testTryCreateTable_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.createTable(CreateTableRequest.builder().build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateTable());
    }

    @Test
    void testTryCreateTable_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.createTable(CreateTableRequest.builder().build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateTable());
    }

    @Test
    void testTryCreateTable_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.createTable(CreateTableRequest.builder().build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryCreateTable());
    }

    @Test
    void testTryCreateTable1() {
        // Setup
        when(mockDynamoDbClient.createTable(any(Consumer.class))).thenReturn(CreateTableResponse.builder().build());

        // Run the test
        myClassUnderTest.tryCreateTable1();

        // Verify the results
    }

    @Test
    void testTryCreateTable1_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.createTable(any(Consumer.class))).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryCreateTable1());
    }

    @Test
    void testTryCreateTable1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.createTable(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateTable1());
    }

    @Test
    void testTryCreateTable1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.createTable(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryCreateTable1());
    }

    @Test
    void testTryCreateTable1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.createTable(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateTable1());
    }

    @Test
    void testTryCreateTable1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.createTable(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateTable1());
    }

    @Test
    void testTryCreateTable1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.createTable(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryCreateTable1());
    }

    @Test
    void testTryDeleteBackup() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(DeleteBackupRequest.builder().build()))
                .thenReturn(DeleteBackupResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDeleteBackup();

        // Verify the results
    }

    @Test
    void testTryDeleteBackup_DynamoDbClientThrowsBackupNotFoundException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(DeleteBackupRequest.builder().build()))
                .thenThrow(BackupNotFoundException.class);

        // Run the test
        assertThrows(BackupNotFoundException.class, () -> myClassUnderTest.tryDeleteBackup());
    }

    @Test
    void testTryDeleteBackup_DynamoDbClientThrowsBackupInUseException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(DeleteBackupRequest.builder().build()))
                .thenThrow(BackupInUseException.class);

        // Run the test
        assertThrows(BackupInUseException.class, () -> myClassUnderTest.tryDeleteBackup());
    }

    @Test
    void testTryDeleteBackup_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(DeleteBackupRequest.builder().build()))
                .thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryDeleteBackup());
    }

    @Test
    void testTryDeleteBackup_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(DeleteBackupRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDeleteBackup());
    }

    @Test
    void testTryDeleteBackup_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(DeleteBackupRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBackup());
    }

    @Test
    void testTryDeleteBackup_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(DeleteBackupRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBackup());
    }

    @Test
    void testTryDeleteBackup_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(DeleteBackupRequest.builder().build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDeleteBackup());
    }

    @Test
    void testTryDeleteBackup1() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(any(Consumer.class))).thenReturn(DeleteBackupResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDeleteBackup1();

        // Verify the results
    }

    @Test
    void testTryDeleteBackup1_DynamoDbClientThrowsBackupNotFoundException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(any(Consumer.class))).thenThrow(BackupNotFoundException.class);

        // Run the test
        assertThrows(BackupNotFoundException.class, () -> myClassUnderTest.tryDeleteBackup1());
    }

    @Test
    void testTryDeleteBackup1_DynamoDbClientThrowsBackupInUseException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(any(Consumer.class))).thenThrow(BackupInUseException.class);

        // Run the test
        assertThrows(BackupInUseException.class, () -> myClassUnderTest.tryDeleteBackup1());
    }

    @Test
    void testTryDeleteBackup1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryDeleteBackup1());
    }

    @Test
    void testTryDeleteBackup1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDeleteBackup1());
    }

    @Test
    void testTryDeleteBackup1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBackup1());
    }

    @Test
    void testTryDeleteBackup1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBackup1());
    }

    @Test
    void testTryDeleteBackup1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.deleteBackup(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDeleteBackup1());
    }

    @Test
    void testTryDeleteItem() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenReturn(DeleteItemResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDeleteItem();

        // Verify the results
    }

    @Test
    void testTryDeleteItem_DynamoDbClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, () -> myClassUnderTest.tryDeleteItem());
    }

    @Test
    void testTryDeleteItem_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryDeleteItem());
    }

    @Test
    void testTryDeleteItem_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDeleteItem());
    }

    @Test
    void testTryDeleteItem_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.tryDeleteItem());
    }

    @Test
    void testTryDeleteItem_DynamoDbClientThrowsTransactionConflictException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenThrow(TransactionConflictException.class);

        // Run the test
        assertThrows(TransactionConflictException.class, () -> myClassUnderTest.tryDeleteItem());
    }

    @Test
    void testTryDeleteItem_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryDeleteItem());
    }

    @Test
    void testTryDeleteItem_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDeleteItem());
    }

    @Test
    void testTryDeleteItem_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteItem());
    }

    @Test
    void testTryDeleteItem_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteItem());
    }

    @Test
    void testTryDeleteItem_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDeleteItem());
    }

    @Test
    void testTryDeleteItem1() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class))).thenReturn(DeleteItemResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDeleteItem1();

        // Verify the results
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class))).thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class)))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class)))
                .thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsTransactionConflictException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class))).thenThrow(TransactionConflictException.class);

        // Run the test
        assertThrows(TransactionConflictException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.deleteItem(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteTable() {
        // Setup
        when(mockDynamoDbClient.deleteTable(DeleteTableRequest.builder().build()))
                .thenReturn(DeleteTableResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDeleteTable();

        // Verify the results
    }

    @Test
    void testTryDeleteTable_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(DeleteTableRequest.builder().build()))
                .thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryDeleteTable());
    }

    @Test
    void testTryDeleteTable_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(DeleteTableRequest.builder().build()))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDeleteTable());
    }

    @Test
    void testTryDeleteTable_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(DeleteTableRequest.builder().build()))
                .thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryDeleteTable());
    }

    @Test
    void testTryDeleteTable_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(DeleteTableRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDeleteTable());
    }

    @Test
    void testTryDeleteTable_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(DeleteTableRequest.builder().build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteTable());
    }

    @Test
    void testTryDeleteTable_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(DeleteTableRequest.builder().build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteTable());
    }

    @Test
    void testTryDeleteTable_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(DeleteTableRequest.builder().build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDeleteTable());
    }

    @Test
    void testTryDeleteTable1() {
        // Setup
        when(mockDynamoDbClient.deleteTable(any(Consumer.class))).thenReturn(DeleteTableResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDeleteTable1();

        // Verify the results
    }

    @Test
    void testTryDeleteTable1_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(any(Consumer.class))).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryDeleteTable1());
    }

    @Test
    void testTryDeleteTable1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDeleteTable1());
    }

    @Test
    void testTryDeleteTable1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryDeleteTable1());
    }

    @Test
    void testTryDeleteTable1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDeleteTable1());
    }

    @Test
    void testTryDeleteTable1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteTable1());
    }

    @Test
    void testTryDeleteTable1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteTable1());
    }

    @Test
    void testTryDeleteTable1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.deleteTable(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDeleteTable1());
    }

    @Test
    void testTryDescribeBackup() {
        // Setup
        when(mockDynamoDbClient.describeBackup(DescribeBackupRequest.builder().build()))
                .thenReturn(DescribeBackupResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDescribeBackup();

        // Verify the results
    }

    @Test
    void testTryDescribeBackup_DynamoDbClientThrowsBackupNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(DescribeBackupRequest.builder().build()))
                .thenThrow(BackupNotFoundException.class);

        // Run the test
        assertThrows(BackupNotFoundException.class, () -> myClassUnderTest.tryDescribeBackup());
    }

    @Test
    void testTryDescribeBackup_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(DescribeBackupRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeBackup());
    }

    @Test
    void testTryDescribeBackup_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(DescribeBackupRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeBackup());
    }

    @Test
    void testTryDescribeBackup_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(DescribeBackupRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeBackup());
    }

    @Test
    void testTryDescribeBackup_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(DescribeBackupRequest.builder().build()))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeBackup());
    }

    @Test
    void testTryDescribeBackup1() {
        // Setup
        when(mockDynamoDbClient.describeBackup(any(Consumer.class)))
                .thenReturn(DescribeBackupResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDescribeBackup1();

        // Verify the results
    }

    @Test
    void testTryDescribeBackup1_DynamoDbClientThrowsBackupNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(any(Consumer.class))).thenThrow(BackupNotFoundException.class);

        // Run the test
        assertThrows(BackupNotFoundException.class, () -> myClassUnderTest.tryDescribeBackup1());
    }

    @Test
    void testTryDescribeBackup1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeBackup1());
    }

    @Test
    void testTryDescribeBackup1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeBackup1());
    }

    @Test
    void testTryDescribeBackup1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeBackup1());
    }

    @Test
    void testTryDescribeBackup1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeBackup(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeBackup1());
    }

    @Test
    void testTryDescribeContinuousBackups() {
        // Setup
        // Configure DynamoDbClient.describeContinuousBackups(...).
        final DescribeContinuousBackupsResponse describeContinuousBackupsResponse = DescribeContinuousBackupsResponse.builder().build();
        when(mockDynamoDbClient.describeContinuousBackups(
                DescribeContinuousBackupsRequest.builder().build())).thenReturn(describeContinuousBackupsResponse);

        // Run the test
        myClassUnderTest.tryDescribeContinuousBackups();

        // Verify the results
    }

    @Test
    void testTryDescribeContinuousBackups_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(
                DescribeContinuousBackupsRequest.builder().build())).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryDescribeContinuousBackups());
    }

    @Test
    void testTryDescribeContinuousBackups_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(
                DescribeContinuousBackupsRequest.builder().build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeContinuousBackups());
    }

    @Test
    void testTryDescribeContinuousBackups_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(
                DescribeContinuousBackupsRequest.builder().build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeContinuousBackups());
    }

    @Test
    void testTryDescribeContinuousBackups_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(
                DescribeContinuousBackupsRequest.builder().build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeContinuousBackups());
    }

    @Test
    void testTryDescribeContinuousBackups_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(
                DescribeContinuousBackupsRequest.builder().build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeContinuousBackups());
    }

    @Test
    void testTryDescribeContinuousBackups1() {
        // Setup
        // Configure DynamoDbClient.describeContinuousBackups(...).
        final DescribeContinuousBackupsResponse describeContinuousBackupsResponse = DescribeContinuousBackupsResponse.builder().build();
        when(mockDynamoDbClient.describeContinuousBackups(any(Consumer.class)))
                .thenReturn(describeContinuousBackupsResponse);

        // Run the test
        myClassUnderTest.tryDescribeContinuousBackups1();

        // Verify the results
    }

    @Test
    void testTryDescribeContinuousBackups1_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(any(Consumer.class))).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryDescribeContinuousBackups1());
    }

    @Test
    void testTryDescribeContinuousBackups1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeContinuousBackups1());
    }

    @Test
    void testTryDescribeContinuousBackups1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeContinuousBackups1());
    }

    @Test
    void testTryDescribeContinuousBackups1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeContinuousBackups1());
    }

    @Test
    void testTryDescribeContinuousBackups1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeContinuousBackups(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeContinuousBackups1());
    }

    @Test
    void testTryDescribeEndpoints() {
        // Setup
        // Configure DynamoDbClient.describeEndpoints(...).
        final DescribeEndpointsResponse describeEndpointsResponse = DescribeEndpointsResponse.builder().build();
        when(mockDynamoDbClient.describeEndpoints()).thenReturn(describeEndpointsResponse);

        // Run the test
        myClassUnderTest.tryDescribeEndpoints();

        // Verify the results
    }

    @Test
    void testTryDescribeEndpoints_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeEndpoints()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeEndpoints());
    }

    @Test
    void testTryDescribeEndpoints_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeEndpoints()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeEndpoints());
    }

    @Test
    void testTryDescribeEndpoints_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeEndpoints()).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeEndpoints());
    }

    @Test
    void testTryDescribeEndpoints1() {
        // Setup
        // Configure DynamoDbClient.describeEndpoints(...).
        final DescribeEndpointsResponse describeEndpointsResponse = DescribeEndpointsResponse.builder().build();
        when(mockDynamoDbClient.describeEndpoints(DescribeEndpointsRequest.builder().build()))
                .thenReturn(describeEndpointsResponse);

        // Run the test
        myClassUnderTest.tryDescribeEndpoints1();

        // Verify the results
    }

    @Test
    void testTryDescribeEndpoints1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeEndpoints(DescribeEndpointsRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeEndpoints1());
    }

    @Test
    void testTryDescribeEndpoints1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeEndpoints(DescribeEndpointsRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeEndpoints1());
    }

    @Test
    void testTryDescribeEndpoints1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeEndpoints(DescribeEndpointsRequest.builder().build()))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeEndpoints1());
    }

    @Test
    void testTryDescribeEndpoints2() {
        // Setup
        // Configure DynamoDbClient.describeEndpoints(...).
        final DescribeEndpointsResponse describeEndpointsResponse = DescribeEndpointsResponse.builder().build();
        when(mockDynamoDbClient.describeEndpoints(any(Consumer.class))).thenReturn(describeEndpointsResponse);

        // Run the test
        myClassUnderTest.tryDescribeEndpoints2();

        // Verify the results
    }

    @Test
    void testTryDescribeEndpoints2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeEndpoints(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeEndpoints2());
    }

    @Test
    void testTryDescribeEndpoints2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeEndpoints(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeEndpoints2());
    }

    @Test
    void testTryDescribeEndpoints2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeEndpoints(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeEndpoints2());
    }

    @Test
    void testTryDescribeGlobalTable() {
        // Setup
        // Configure DynamoDbClient.describeGlobalTable(...).
        final DescribeGlobalTableResponse describeGlobalTableResponse = DescribeGlobalTableResponse.builder().build();
        when(mockDynamoDbClient.describeGlobalTable(DescribeGlobalTableRequest.builder().build()))
                .thenReturn(describeGlobalTableResponse);

        // Run the test
        myClassUnderTest.tryDescribeGlobalTable();

        // Verify the results
    }

    @Test
    void testTryDescribeGlobalTable_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(DescribeGlobalTableRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeGlobalTable());
    }

    @Test
    void testTryDescribeGlobalTable_DynamoDbClientThrowsGlobalTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(DescribeGlobalTableRequest.builder().build()))
                .thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, () -> myClassUnderTest.tryDescribeGlobalTable());
    }

    @Test
    void testTryDescribeGlobalTable_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(DescribeGlobalTableRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeGlobalTable());
    }

    @Test
    void testTryDescribeGlobalTable_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(DescribeGlobalTableRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeGlobalTable());
    }

    @Test
    void testTryDescribeGlobalTable_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(DescribeGlobalTableRequest.builder().build()))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeGlobalTable());
    }

    @Test
    void testTryDescribeGlobalTable1() {
        // Setup
        // Configure DynamoDbClient.describeGlobalTable(...).
        final DescribeGlobalTableResponse describeGlobalTableResponse = DescribeGlobalTableResponse.builder().build();
        when(mockDynamoDbClient.describeGlobalTable(any(Consumer.class))).thenReturn(describeGlobalTableResponse);

        // Run the test
        myClassUnderTest.tryDescribeGlobalTable1();

        // Verify the results
    }

    @Test
    void testTryDescribeGlobalTable1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeGlobalTable1());
    }

    @Test
    void testTryDescribeGlobalTable1_DynamoDbClientThrowsGlobalTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(any(Consumer.class))).thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, () -> myClassUnderTest.tryDescribeGlobalTable1());
    }

    @Test
    void testTryDescribeGlobalTable1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeGlobalTable1());
    }

    @Test
    void testTryDescribeGlobalTable1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeGlobalTable1());
    }

    @Test
    void testTryDescribeGlobalTable1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTable(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeGlobalTable1());
    }

    @Test
    void testTryDescribeGlobalTableSettings() {
        // Setup
        // Configure DynamoDbClient.describeGlobalTableSettings(...).
        final DescribeGlobalTableSettingsResponse describeGlobalTableSettingsResponse = DescribeGlobalTableSettingsResponse.builder().build();
        when(mockDynamoDbClient.describeGlobalTableSettings(
                DescribeGlobalTableSettingsRequest.builder().build())).thenReturn(describeGlobalTableSettingsResponse);

        // Run the test
        myClassUnderTest.tryDescribeGlobalTableSettings();

        // Verify the results
    }

    @Test
    void testTryDescribeGlobalTableSettings_DynamoDbClientThrowsGlobalTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(
                DescribeGlobalTableSettingsRequest.builder().build())).thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings());
    }

    @Test
    void testTryDescribeGlobalTableSettings_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(
                DescribeGlobalTableSettingsRequest.builder().build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings());
    }

    @Test
    void testTryDescribeGlobalTableSettings_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(
                DescribeGlobalTableSettingsRequest.builder().build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings());
    }

    @Test
    void testTryDescribeGlobalTableSettings_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(
                DescribeGlobalTableSettingsRequest.builder().build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings());
    }

    @Test
    void testTryDescribeGlobalTableSettings_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(
                DescribeGlobalTableSettingsRequest.builder().build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings());
    }

    @Test
    void testTryDescribeGlobalTableSettings1() {
        // Setup
        // Configure DynamoDbClient.describeGlobalTableSettings(...).
        final DescribeGlobalTableSettingsResponse describeGlobalTableSettingsResponse = DescribeGlobalTableSettingsResponse.builder().build();
        when(mockDynamoDbClient.describeGlobalTableSettings(any(Consumer.class)))
                .thenReturn(describeGlobalTableSettingsResponse);

        // Run the test
        myClassUnderTest.tryDescribeGlobalTableSettings1();

        // Verify the results
    }

    @Test
    void testTryDescribeGlobalTableSettings1_DynamoDbClientThrowsGlobalTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(any(Consumer.class)))
                .thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings1());
    }

    @Test
    void testTryDescribeGlobalTableSettings1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings1());
    }

    @Test
    void testTryDescribeGlobalTableSettings1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings1());
    }

    @Test
    void testTryDescribeGlobalTableSettings1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings1());
    }

    @Test
    void testTryDescribeGlobalTableSettings1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeGlobalTableSettings(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeGlobalTableSettings1());
    }

    @Test
    void testTryDescribeLimits() {
        // Setup
        when(mockDynamoDbClient.describeLimits()).thenReturn(DescribeLimitsResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDescribeLimits();

        // Verify the results
    }

    @Test
    void testTryDescribeLimits_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeLimits()).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeLimits());
    }

    @Test
    void testTryDescribeLimits_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeLimits()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeLimits());
    }

    @Test
    void testTryDescribeLimits_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeLimits()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeLimits());
    }

    @Test
    void testTryDescribeLimits_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeLimits()).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeLimits());
    }

    @Test
    void testTryDescribeLimits1() {
        // Setup
        when(mockDynamoDbClient.describeLimits(DescribeLimitsRequest.builder().build()))
                .thenReturn(DescribeLimitsResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDescribeLimits1();

        // Verify the results
    }

    @Test
    void testTryDescribeLimits1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeLimits(DescribeLimitsRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeLimits1());
    }

    @Test
    void testTryDescribeLimits1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeLimits(DescribeLimitsRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeLimits1());
    }

    @Test
    void testTryDescribeLimits1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeLimits(DescribeLimitsRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeLimits1());
    }

    @Test
    void testTryDescribeLimits1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeLimits(DescribeLimitsRequest.builder().build()))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeLimits1());
    }

    @Test
    void testTryDescribeLimits2() {
        // Setup
        when(mockDynamoDbClient.describeLimits(any(Consumer.class)))
                .thenReturn(DescribeLimitsResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDescribeLimits2();

        // Verify the results
    }

    @Test
    void testTryDescribeLimits2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeLimits(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeLimits2());
    }

    @Test
    void testTryDescribeLimits2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeLimits(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeLimits2());
    }

    @Test
    void testTryDescribeLimits2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeLimits(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeLimits2());
    }

    @Test
    void testTryDescribeLimits2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeLimits(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeLimits2());
    }

    @Test
    void testTryDescribeTable() {
        // Setup
        when(mockDynamoDbClient.describeTable(DescribeTableRequest.builder().build()))
                .thenReturn(DescribeTableResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDescribeTable();

        // Verify the results
    }

    @Test
    void testTryDescribeTable_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeTable(DescribeTableRequest.builder().build()))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDescribeTable());
    }

    @Test
    void testTryDescribeTable_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeTable(DescribeTableRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeTable());
    }

    @Test
    void testTryDescribeTable_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeTable(DescribeTableRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeTable());
    }

    @Test
    void testTryDescribeTable_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeTable(DescribeTableRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeTable());
    }

    @Test
    void testTryDescribeTable_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeTable(DescribeTableRequest.builder().build()))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeTable());
    }

    @Test
    void testTryDescribeTable1() {
        // Setup
        when(mockDynamoDbClient.describeTable(any(Consumer.class))).thenReturn(DescribeTableResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDescribeTable1();

        // Verify the results
    }

    @Test
    void testTryDescribeTable1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeTable(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDescribeTable1());
    }

    @Test
    void testTryDescribeTable1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeTable(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeTable1());
    }

    @Test
    void testTryDescribeTable1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeTable(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeTable1());
    }

    @Test
    void testTryDescribeTable1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeTable(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeTable1());
    }

    @Test
    void testTryDescribeTable1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeTable(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeTable1());
    }

    @Test
    void testTryDescribeTimeToLive() {
        // Setup
        // Configure DynamoDbClient.describeTimeToLive(...).
        final DescribeTimeToLiveResponse describeTimeToLiveResponse = DescribeTimeToLiveResponse.builder().build();
        when(mockDynamoDbClient.describeTimeToLive(DescribeTimeToLiveRequest.builder().build()))
                .thenReturn(describeTimeToLiveResponse);

        // Run the test
        myClassUnderTest.tryDescribeTimeToLive();

        // Verify the results
    }

    @Test
    void testTryDescribeTimeToLive_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(DescribeTimeToLiveRequest.builder().build()))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDescribeTimeToLive());
    }

    @Test
    void testTryDescribeTimeToLive_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(DescribeTimeToLiveRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeTimeToLive());
    }

    @Test
    void testTryDescribeTimeToLive_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(DescribeTimeToLiveRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeTimeToLive());
    }

    @Test
    void testTryDescribeTimeToLive_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(DescribeTimeToLiveRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeTimeToLive());
    }

    @Test
    void testTryDescribeTimeToLive_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(DescribeTimeToLiveRequest.builder().build()))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeTimeToLive());
    }

    @Test
    void testTryDescribeTimeToLive1() {
        // Setup
        // Configure DynamoDbClient.describeTimeToLive(...).
        final DescribeTimeToLiveResponse describeTimeToLiveResponse = DescribeTimeToLiveResponse.builder().build();
        when(mockDynamoDbClient.describeTimeToLive(any(Consumer.class))).thenReturn(describeTimeToLiveResponse);

        // Run the test
        myClassUnderTest.tryDescribeTimeToLive1();

        // Verify the results
    }

    @Test
    void testTryDescribeTimeToLive1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDescribeTimeToLive1());
    }

    @Test
    void testTryDescribeTimeToLive1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDescribeTimeToLive1());
    }

    @Test
    void testTryDescribeTimeToLive1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDescribeTimeToLive1());
    }

    @Test
    void testTryDescribeTimeToLive1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDescribeTimeToLive1());
    }

    @Test
    void testTryDescribeTimeToLive1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.describeTimeToLive(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDescribeTimeToLive1());
    }

    @Test
    void testTryGetItem() {
        // Setup
        // Configure DynamoDbClient.getItem(...).
        final GetItemResponse getItemResponse = GetItemResponse.builder()
                .item(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                    put("OtherKeyName", AttributeValue.builder().s("OtherKeyValue").build());
                }}).build();
        when(mockDynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenReturn(getItemResponse);

        // Run the test
        myClassUnderTest.tryGetItem();

        // Verify the results
    }

    @Test
    void testTryGetItem_DynamoDbClientReturnsNoItem() {
        // Setup
        when(mockDynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenReturn(GetItemResponse.builder().build());

        // Run the test
        myClassUnderTest.tryGetItem();

        // Verify the results
    }

    @Test
    void testTryGetItem_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryGetItem());
    }

    @Test
    void testTryGetItem_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryGetItem());
    }

    @Test
    void testTryGetItem_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryGetItem());
    }

    @Test
    void testTryGetItem_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryGetItem());
    }

    @Test
    void testTryGetItem_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetItem());
    }

    @Test
    void testTryGetItem_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetItem());
    }

    @Test
    void testTryGetItem_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("TableName")
                .key(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                }}).build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryGetItem());
    }

    @Test
    void testTryGetItem1() {
        // Setup
        // Configure DynamoDbClient.getItem(...).
        final GetItemResponse getItemResponse = GetItemResponse.builder()
                .item(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                    put("OtherKeyName", AttributeValue.builder().s("OtherKeyValue").build());
                }}).build();
        when(mockDynamoDbClient.getItem(any(Consumer.class))).thenReturn(getItemResponse);

        // Run the test
        myClassUnderTest.tryGetItem1();

        // Verify the results
    }

    @Test
    void testTryGetItem1_DynamoDbClientReturnsNoItem() {
        // Setup
        when(mockDynamoDbClient.getItem(any(Consumer.class))).thenReturn(GetItemResponse.builder().build());

        // Run the test
        myClassUnderTest.tryGetItem1();

        // Verify the results
    }

    @Test
    void testTryGetItem1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.getItem(any(Consumer.class))).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryGetItem1());
    }

    @Test
    void testTryGetItem1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.getItem(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryGetItem1());
    }

    @Test
    void testTryGetItem1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.getItem(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryGetItem1());
    }

    @Test
    void testTryGetItem1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.getItem(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryGetItem1());
    }

    @Test
    void testTryGetItem1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.getItem(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetItem1());
    }

    @Test
    void testTryGetItem1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.getItem(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetItem1());
    }

    @Test
    void testTryGetItem1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.getItem(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryGetItem1());
    }

    @Test
    void testTryListBackups() {
        // Setup
        when(mockDynamoDbClient.listBackups()).thenReturn(ListBackupsResponse.builder().build());

        // Run the test
        myClassUnderTest.tryListBackups();

        // Verify the results
    }

    @Test
    void testTryListBackups_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listBackups()).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListBackups());
    }

    @Test
    void testTryListBackups_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listBackups()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListBackups());
    }

    @Test
    void testTryListBackups_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listBackups()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBackups());
    }

    @Test
    void testTryListBackups_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listBackups()).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListBackups());
    }

    @Test
    void testTryListBackups1() {
        // Setup
        when(mockDynamoDbClient.listBackups(ListBackupsRequest.builder().build()))
                .thenReturn(ListBackupsResponse.builder().build());

        // Run the test
        myClassUnderTest.tryListBackups1();

        // Verify the results
    }

    @Test
    void testTryListBackups1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listBackups(ListBackupsRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListBackups1());
    }

    @Test
    void testTryListBackups1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listBackups(ListBackupsRequest.builder().build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListBackups1());
    }

    @Test
    void testTryListBackups1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listBackups(ListBackupsRequest.builder().build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBackups1());
    }

    @Test
    void testTryListBackups1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listBackups(ListBackupsRequest.builder().build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListBackups1());
    }

    @Test
    void testTryListBackups2() {
        // Setup
        when(mockDynamoDbClient.listBackups(any(Consumer.class))).thenReturn(ListBackupsResponse.builder().build());

        // Run the test
        myClassUnderTest.tryListBackups2();

        // Verify the results
    }

    @Test
    void testTryListBackups2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listBackups(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListBackups2());
    }

    @Test
    void testTryListBackups2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listBackups(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListBackups2());
    }

    @Test
    void testTryListBackups2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listBackups(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBackups2());
    }

    @Test
    void testTryListBackups2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listBackups(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListBackups2());
    }

    @Test
    void testTryListGlobalTables() {
        // Setup
        // Configure DynamoDbClient.listGlobalTables(...).
        final ListGlobalTablesResponse listGlobalTablesResponse = ListGlobalTablesResponse.builder().build();
        when(mockDynamoDbClient.listGlobalTables()).thenReturn(listGlobalTablesResponse);

        // Run the test
        myClassUnderTest.tryListGlobalTables();

        // Verify the results
    }

    @Test
    void testTryListGlobalTables_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables()).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListGlobalTables());
    }

    @Test
    void testTryListGlobalTables_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListGlobalTables());
    }

    @Test
    void testTryListGlobalTables_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListGlobalTables());
    }

    @Test
    void testTryListGlobalTables_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables()).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListGlobalTables());
    }

    @Test
    void testTryListGlobalTables1() {
        // Setup
        // Configure DynamoDbClient.listGlobalTables(...).
        final ListGlobalTablesResponse listGlobalTablesResponse = ListGlobalTablesResponse.builder().build();
        when(mockDynamoDbClient.listGlobalTables(ListGlobalTablesRequest.builder().build()))
                .thenReturn(listGlobalTablesResponse);

        // Run the test
        myClassUnderTest.tryListGlobalTables1();

        // Verify the results
    }

    @Test
    void testTryListGlobalTables1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables(ListGlobalTablesRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListGlobalTables1());
    }

    @Test
    void testTryListGlobalTables1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables(ListGlobalTablesRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListGlobalTables1());
    }

    @Test
    void testTryListGlobalTables1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables(ListGlobalTablesRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListGlobalTables1());
    }

    @Test
    void testTryListGlobalTables1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables(ListGlobalTablesRequest.builder().build()))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListGlobalTables1());
    }

    @Test
    void testTryListGlobalTables2() {
        // Setup
        // Configure DynamoDbClient.listGlobalTables(...).
        final ListGlobalTablesResponse listGlobalTablesResponse = ListGlobalTablesResponse.builder().build();
        when(mockDynamoDbClient.listGlobalTables(any(Consumer.class))).thenReturn(listGlobalTablesResponse);

        // Run the test
        myClassUnderTest.tryListGlobalTables2();

        // Verify the results
    }

    @Test
    void testTryListGlobalTables2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListGlobalTables2());
    }

    @Test
    void testTryListGlobalTables2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListGlobalTables2());
    }

    @Test
    void testTryListGlobalTables2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListGlobalTables2());
    }

    @Test
    void testTryListGlobalTables2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listGlobalTables(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListGlobalTables2());
    }

    @Test
    void testTryListTables() {
        // Setup
        when(mockDynamoDbClient.listTables()).thenReturn(ListTablesResponse.builder().build());

        // Run the test
        myClassUnderTest.tryListTables();

        // Verify the results
    }

    @Test
    void testTryListTables_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listTables()).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListTables());
    }

    @Test
    void testTryListTables_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listTables()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTables());
    }

    @Test
    void testTryListTables_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listTables()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTables());
    }

    @Test
    void testTryListTables_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listTables()).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListTables());
    }

    @Test
    void testTryListTables1() {
        // Setup
        when(mockDynamoDbClient.listTables(ListTablesRequest.builder().build()))
                .thenReturn(ListTablesResponse.builder().build());

        // Run the test
        myClassUnderTest.tryListTables1();

        // Verify the results
    }

    @Test
    void testTryListTables1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listTables(ListTablesRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListTables1());
    }

    @Test
    void testTryListTables1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listTables(ListTablesRequest.builder().build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTables1());
    }

    @Test
    void testTryListTables1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listTables(ListTablesRequest.builder().build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTables1());
    }

    @Test
    void testTryListTables1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listTables(ListTablesRequest.builder().build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListTables1());
    }

    @Test
    void testTryListTables2() {
        // Setup
        when(mockDynamoDbClient.listTables(any(Consumer.class))).thenReturn(ListTablesResponse.builder().build());

        // Run the test
        myClassUnderTest.tryListTables2();

        // Verify the results
    }

    @Test
    void testTryListTables2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listTables(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListTables2());
    }

    @Test
    void testTryListTables2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listTables(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTables2());
    }

    @Test
    void testTryListTables2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listTables(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTables2());
    }

    @Test
    void testTryListTables2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listTables(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListTables2());
    }

    @Test
    void testTryListTablesPaginator() {
        // Setup
        // Configure DynamoDbClient.listTablesPaginator(...).
        final ListTablesIterable mockListTablesIterable = mock(ListTablesIterable.class);
        when(mockDynamoDbClient.listTablesPaginator()).thenReturn(mockListTablesIterable);

        // Run the test
        myClassUnderTest.tryListTablesPaginator();

        // Verify the results
    }

    @Test
    void testTryListTablesPaginator_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.listTablesPaginator(...).
        final ListTablesIterable mockListTablesIterable = mock(ListTablesIterable.class);
        when(mockDynamoDbClient.listTablesPaginator()).thenReturn(mockListTablesIterable);

        // Run the test
        myClassUnderTest.tryListTablesPaginator();

        // Verify the results
    }

    @Test
    void testTryListTablesPaginator_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator()).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListTablesPaginator());
    }

    @Test
    void testTryListTablesPaginator_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTablesPaginator());
    }

    @Test
    void testTryListTablesPaginator_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTablesPaginator());
    }

    @Test
    void testTryListTablesPaginator_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator()).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListTablesPaginator());
    }

    @Test
    void testTryListTablesPaginator1() {
        // Setup
        // Configure DynamoDbClient.listTablesPaginator(...).
        final ListTablesIterable mockListTablesIterable = mock(ListTablesIterable.class);
        when(mockDynamoDbClient.listTablesPaginator(ListTablesRequest.builder().build()))
                .thenReturn(mockListTablesIterable);

        // Run the test
        myClassUnderTest.tryListTablesPaginator1();

        // Verify the results
    }

    @Test
    void testTryListTablesPaginator1_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.listTablesPaginator(...).
        final ListTablesIterable mockListTablesIterable = mock(ListTablesIterable.class);
        when(mockDynamoDbClient.listTablesPaginator(ListTablesRequest.builder().build()))
                .thenReturn(mockListTablesIterable);

        // Run the test
        myClassUnderTest.tryListTablesPaginator1();

        // Verify the results
    }

    @Test
    void testTryListTablesPaginator1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator(ListTablesRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListTablesPaginator1());
    }

    @Test
    void testTryListTablesPaginator1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator(ListTablesRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTablesPaginator1());
    }

    @Test
    void testTryListTablesPaginator1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator(ListTablesRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTablesPaginator1());
    }

    @Test
    void testTryListTablesPaginator1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator(ListTablesRequest.builder().build()))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListTablesPaginator1());
    }

    @Test
    void testTryListTablesPaginator2() {
        // Setup
        // Configure DynamoDbClient.listTablesPaginator(...).
        final ListTablesIterable mockListTablesIterable = mock(ListTablesIterable.class);
        when(mockDynamoDbClient.listTablesPaginator(any(Consumer.class))).thenReturn(mockListTablesIterable);

        // Run the test
        myClassUnderTest.tryListTablesPaginator2();

        // Verify the results
    }

    @Test
    void testTryListTablesPaginator2_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.listTablesPaginator(...).
        final ListTablesIterable mockListTablesIterable = mock(ListTablesIterable.class);
        when(mockDynamoDbClient.listTablesPaginator(any(Consumer.class))).thenReturn(mockListTablesIterable);

        // Run the test
        myClassUnderTest.tryListTablesPaginator2();

        // Verify the results
    }

    @Test
    void testTryListTablesPaginator2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListTablesPaginator2());
    }

    @Test
    void testTryListTablesPaginator2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTablesPaginator2());
    }

    @Test
    void testTryListTablesPaginator2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTablesPaginator2());
    }

    @Test
    void testTryListTablesPaginator2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listTablesPaginator(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListTablesPaginator2());
    }

    @Test
    void testTryListTagsOfResource() {
        // Setup
        // Configure DynamoDbClient.listTagsOfResource(...).
        final ListTagsOfResourceResponse listTagsOfResourceResponse = ListTagsOfResourceResponse.builder().build();
        when(mockDynamoDbClient.listTagsOfResource(ListTagsOfResourceRequest.builder().build()))
                .thenReturn(listTagsOfResourceResponse);

        // Run the test
        myClassUnderTest.tryListTagsOfResource();

        // Verify the results
    }

    @Test
    void testTryListTagsOfResource_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(ListTagsOfResourceRequest.builder().build()))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryListTagsOfResource());
    }

    @Test
    void testTryListTagsOfResource_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(ListTagsOfResourceRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListTagsOfResource());
    }

    @Test
    void testTryListTagsOfResource_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(ListTagsOfResourceRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTagsOfResource());
    }

    @Test
    void testTryListTagsOfResource_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(ListTagsOfResourceRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTagsOfResource());
    }

    @Test
    void testTryListTagsOfResource_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(ListTagsOfResourceRequest.builder().build()))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListTagsOfResource());
    }

    @Test
    void testTryListTagsOfResource1() {
        // Setup
        // Configure DynamoDbClient.listTagsOfResource(...).
        final ListTagsOfResourceResponse listTagsOfResourceResponse = ListTagsOfResourceResponse.builder().build();
        when(mockDynamoDbClient.listTagsOfResource(any(Consumer.class))).thenReturn(listTagsOfResourceResponse);

        // Run the test
        myClassUnderTest.tryListTagsOfResource1();

        // Verify the results
    }

    @Test
    void testTryListTagsOfResource1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryListTagsOfResource1());
    }

    @Test
    void testTryListTagsOfResource1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryListTagsOfResource1());
    }

    @Test
    void testTryListTagsOfResource1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTagsOfResource1());
    }

    @Test
    void testTryListTagsOfResource1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTagsOfResource1());
    }

    @Test
    void testTryListTagsOfResource1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.listTagsOfResource(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryListTagsOfResource1());
    }

    @Test
    void testTryPutItem() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                    put("OtherKeyName", AttributeValue.builder().s("OtherValue").build());
                }}).build())).thenReturn(PutItemResponse.builder().build());

        // Run the test
        myClassUnderTest.tryPutItem();

        // Verify the results
    }

    @Test
    void testTryPutItem_DynamoDbClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                    put("OtherKeyName", AttributeValue.builder().s("OtherValue").build());
                }}).build())).thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, () -> myClassUnderTest.tryPutItem());
    }

    @Test
    void testTryPutItem_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                    put("OtherKeyName", AttributeValue.builder().s("OtherValue").build());
                }}).build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryPutItem());
    }

    @Test
    void testTryPutItem_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                    put("OtherKeyName", AttributeValue.builder().s("OtherValue").build());
                }}).build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryPutItem());
    }

    @Test
    void testTryPutItem_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                    put("OtherKeyName", AttributeValue.builder().s("OtherValue").build());
                }}).build())).thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.tryPutItem());
    }

    @Test
    void testTryPutItem_DynamoDbClientThrowsTransactionConflictException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                    put("OtherKeyName", AttributeValue.builder().s("OtherValue").build());
                }}).build())).thenThrow(TransactionConflictException.class);

        // Run the test
        assertThrows(TransactionConflictException.class, () -> myClassUnderTest.tryPutItem());
    }

    @Test
    void testTryPutItem_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                    put("OtherKeyName", AttributeValue.builder().s("OtherValue").build());
                }}).build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryPutItem());
    }

    @Test
    void testTryPutItem_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                    put("OtherKeyName", AttributeValue.builder().s("OtherValue").build());
                }}).build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryPutItem());
    }

    @Test
    void testTryPutItem_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                    put("OtherKeyName", AttributeValue.builder().s("OtherValue").build());
                }}).build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutItem());
    }

    @Test
    void testTryPutItem_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                    put("OtherKeyName", AttributeValue.builder().s("OtherValue").build());
                }}).build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutItem());
    }

    @Test
    void testTryPutItem_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("TableName")
                .item(new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                    put("OtherKeyName", AttributeValue.builder().s("OtherValue").build());
                }}).build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryPutItem());
    }

    @Test
    void testTryPutItem1() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenReturn(PutItemResponse.builder().build());

        // Run the test
        myClassUnderTest.tryPutItem1();

        // Verify the results
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsTransactionConflictException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(TransactionConflictException.class);

        // Run the test
        assertThrows(TransactionConflictException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.putItem(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryQuery() {
        // Setup
        // Configure DynamoDbClient.query(...).
        final QueryResponse queryResponse = QueryResponse.builder()
                .items(Arrays.asList(
                        new HashMap<String, AttributeValue>() {{
                            put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                            put("KeyName2", AttributeValue.builder().s("Value2").build());
                        }}
                )).build();
        when(mockDynamoDbClient.query(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenReturn(queryResponse);

        // Run the test
        myClassUnderTest.tryQuery();

        // Verify the results
    }

    @Test
    void testTryQuery_DynamoDbClientReturnsNoItems() {
        // Setup
        when(mockDynamoDbClient.query(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenReturn(QueryResponse.builder().build());

        // Run the test
        myClassUnderTest.tryQuery();

        // Verify the results
    }

    @Test
    void testTryQuery_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.query(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryQuery());
    }

    @Test
    void testTryQuery_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.query(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryQuery());
    }

    @Test
    void testTryQuery_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.query(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryQuery());
    }

    @Test
    void testTryQuery_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.query(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryQuery());
    }

    @Test
    void testTryQuery_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.query(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryQuery());
    }

    @Test
    void testTryQuery_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.query(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryQuery());
    }

    @Test
    void testTryQuery_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.query(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryQuery());
    }

    @Test
    void testTryQuery1() {
        // Setup
        // Configure DynamoDbClient.query(...).
        final QueryResponse queryResponse = QueryResponse.builder()
                .items(Arrays.asList(
                        new HashMap<String, AttributeValue>() {{
                            put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                            put("KeyName2", AttributeValue.builder().s("Value2").build());
                        }}
                )).build();
        when(mockDynamoDbClient.query(any(Consumer.class))).thenReturn(queryResponse);

        // Run the test
        myClassUnderTest.tryQuery1();

        // Verify the results
    }

    @Test
    void testTryQuery1_DynamoDbClientReturnsNoItems() {
        // Setup
        when(mockDynamoDbClient.query(any(Consumer.class))).thenReturn(QueryResponse.builder().build());

        // Run the test
        myClassUnderTest.tryQuery1();

        // Verify the results
    }

    @Test
    void testTryQuery1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.query(any(Consumer.class))).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryQuery1());
    }

    @Test
    void testTryQuery1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.query(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryQuery1());
    }

    @Test
    void testTryQuery1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.query(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryQuery1());
    }

    @Test
    void testTryQuery1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.query(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryQuery1());
    }

    @Test
    void testTryQuery1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.query(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryQuery1());
    }

    @Test
    void testTryQuery1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.query(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryQuery1());
    }

    @Test
    void testTryQuery1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.query(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryQuery1());
    }

    @Test
    void testTryQueryPaginator() {
        // Setup
        // Configure DynamoDbClient.queryPaginator(...).
        final QueryIterable mockQueryIterable = mock(QueryIterable.class);
        when(mockDynamoDbClient.queryPaginator(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenReturn(mockQueryIterable);

        // Run the test
        myClassUnderTest.tryQueryPaginator();

        // Verify the results
    }

    @Test
    void testTryQueryPaginator_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.queryPaginator(...).
        final QueryIterable mockQueryIterable = mock(QueryIterable.class);
        when(mockDynamoDbClient.queryPaginator(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenReturn(mockQueryIterable);

        // Run the test
        myClassUnderTest.tryQueryPaginator();

        // Verify the results
    }

    @Test
    void testTryQueryPaginator_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryQueryPaginator());
    }

    @Test
    void testTryQueryPaginator_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryQueryPaginator());
    }

    @Test
    void testTryQueryPaginator_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryQueryPaginator());
    }

    @Test
    void testTryQueryPaginator_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryQueryPaginator());
    }

    @Test
    void testTryQueryPaginator_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryQueryPaginator());
    }

    @Test
    void testTryQueryPaginator_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryQueryPaginator());
    }

    @Test
    void testTryQueryPaginator_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(QueryRequest.builder()
                .tableName("TableName")
                .keyConditionExpression("PrimaryKeyName = :PrimaryKeyValue")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryQueryPaginator());
    }

    @Test
    void testTryQueryPaginator1() {
        // Setup
        // Configure DynamoDbClient.queryPaginator(...).
        final QueryIterable mockQueryIterable = mock(QueryIterable.class);
        when(mockDynamoDbClient.queryPaginator(any(Consumer.class))).thenReturn(mockQueryIterable);

        // Run the test
        myClassUnderTest.tryQueryPaginator1();

        // Verify the results
    }

    @Test
    void testTryQueryPaginator1_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.queryPaginator(...).
        final QueryIterable mockQueryIterable = mock(QueryIterable.class);
        when(mockDynamoDbClient.queryPaginator(any(Consumer.class))).thenReturn(mockQueryIterable);

        // Run the test
        myClassUnderTest.tryQueryPaginator1();

        // Verify the results
    }

    @Test
    void testTryQueryPaginator1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(any(Consumer.class)))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryQueryPaginator1());
    }

    @Test
    void testTryQueryPaginator1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryQueryPaginator1());
    }

    @Test
    void testTryQueryPaginator1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryQueryPaginator1());
    }

    @Test
    void testTryQueryPaginator1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryQueryPaginator1());
    }

    @Test
    void testTryQueryPaginator1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryQueryPaginator1());
    }

    @Test
    void testTryQueryPaginator1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryQueryPaginator1());
    }

    @Test
    void testTryQueryPaginator1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.queryPaginator(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryQueryPaginator1());
    }

    @Test
    void testTryRestoreTableFromBackup() {
        // Setup
        // Configure DynamoDbClient.restoreTableFromBackup(...).
        final RestoreTableFromBackupResponse restoreTableFromBackupResponse = RestoreTableFromBackupResponse.builder().build();
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder().build()))
                .thenReturn(restoreTableFromBackupResponse);

        // Run the test
        myClassUnderTest.tryRestoreTableFromBackup();

        // Verify the results
    }

    @Test
    void testTryRestoreTableFromBackup_DynamoDbClientThrowsTableAlreadyExistsException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder().build()))
                .thenThrow(TableAlreadyExistsException.class);

        // Run the test
        assertThrows(TableAlreadyExistsException.class, () -> myClassUnderTest.tryRestoreTableFromBackup());
    }

    @Test
    void testTryRestoreTableFromBackup_DynamoDbClientThrowsTableInUseException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder().build()))
                .thenThrow(TableInUseException.class);

        // Run the test
        assertThrows(TableInUseException.class, () -> myClassUnderTest.tryRestoreTableFromBackup());
    }

    @Test
    void testTryRestoreTableFromBackup_DynamoDbClientThrowsBackupNotFoundException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder().build()))
                .thenThrow(BackupNotFoundException.class);

        // Run the test
        assertThrows(BackupNotFoundException.class, () -> myClassUnderTest.tryRestoreTableFromBackup());
    }

    @Test
    void testTryRestoreTableFromBackup_DynamoDbClientThrowsBackupInUseException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder().build()))
                .thenThrow(BackupInUseException.class);

        // Run the test
        assertThrows(BackupInUseException.class, () -> myClassUnderTest.tryRestoreTableFromBackup());
    }

    @Test
    void testTryRestoreTableFromBackup_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder().build()))
                .thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryRestoreTableFromBackup());
    }

    @Test
    void testTryRestoreTableFromBackup_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryRestoreTableFromBackup());
    }

    @Test
    void testTryRestoreTableFromBackup_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryRestoreTableFromBackup());
    }

    @Test
    void testTryRestoreTableFromBackup_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryRestoreTableFromBackup());
    }

    @Test
    void testTryRestoreTableFromBackup_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(RestoreTableFromBackupRequest.builder().build()))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryRestoreTableFromBackup());
    }

    @Test
    void testTryRestoreTableFromBackup1() {
        // Setup
        // Configure DynamoDbClient.restoreTableFromBackup(...).
        final RestoreTableFromBackupResponse restoreTableFromBackupResponse = RestoreTableFromBackupResponse.builder().build();
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class))).thenReturn(restoreTableFromBackupResponse);

        // Run the test
        myClassUnderTest.tryRestoreTableFromBackup1();

        // Verify the results
    }

    @Test
    void testTryRestoreTableFromBackup1_DynamoDbClientThrowsTableAlreadyExistsException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class)))
                .thenThrow(TableAlreadyExistsException.class);

        // Run the test
        assertThrows(TableAlreadyExistsException.class, () -> myClassUnderTest.tryRestoreTableFromBackup1());
    }

    @Test
    void testTryRestoreTableFromBackup1_DynamoDbClientThrowsTableInUseException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class))).thenThrow(TableInUseException.class);

        // Run the test
        assertThrows(TableInUseException.class, () -> myClassUnderTest.tryRestoreTableFromBackup1());
    }

    @Test
    void testTryRestoreTableFromBackup1_DynamoDbClientThrowsBackupNotFoundException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class))).thenThrow(BackupNotFoundException.class);

        // Run the test
        assertThrows(BackupNotFoundException.class, () -> myClassUnderTest.tryRestoreTableFromBackup1());
    }

    @Test
    void testTryRestoreTableFromBackup1_DynamoDbClientThrowsBackupInUseException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class))).thenThrow(BackupInUseException.class);

        // Run the test
        assertThrows(BackupInUseException.class, () -> myClassUnderTest.tryRestoreTableFromBackup1());
    }

    @Test
    void testTryRestoreTableFromBackup1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryRestoreTableFromBackup1());
    }

    @Test
    void testTryRestoreTableFromBackup1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryRestoreTableFromBackup1());
    }

    @Test
    void testTryRestoreTableFromBackup1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryRestoreTableFromBackup1());
    }

    @Test
    void testTryRestoreTableFromBackup1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryRestoreTableFromBackup1());
    }

    @Test
    void testTryRestoreTableFromBackup1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.restoreTableFromBackup(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryRestoreTableFromBackup1());
    }

    @Test
    void testTryRestoreTableToPointInTime() {
        // Setup
        // Configure DynamoDbClient.restoreTableToPointInTime(...).
        final RestoreTableToPointInTimeResponse restoreTableToPointInTimeResponse = RestoreTableToPointInTimeResponse.builder().build();
        when(mockDynamoDbClient.restoreTableToPointInTime(
                RestoreTableToPointInTimeRequest.builder().build())).thenReturn(restoreTableToPointInTimeResponse);

        // Run the test
        myClassUnderTest.tryRestoreTableToPointInTime();

        // Verify the results
    }

    @Test
    void testTryRestoreTableToPointInTime_DynamoDbClientThrowsTableAlreadyExistsException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(
                RestoreTableToPointInTimeRequest.builder().build())).thenThrow(TableAlreadyExistsException.class);

        // Run the test
        assertThrows(TableAlreadyExistsException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime());
    }

    @Test
    void testTryRestoreTableToPointInTime_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(
                RestoreTableToPointInTimeRequest.builder().build())).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime());
    }

    @Test
    void testTryRestoreTableToPointInTime_DynamoDbClientThrowsTableInUseException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(
                RestoreTableToPointInTimeRequest.builder().build())).thenThrow(TableInUseException.class);

        // Run the test
        assertThrows(TableInUseException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime());
    }

    @Test
    void testTryRestoreTableToPointInTime_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(
                RestoreTableToPointInTimeRequest.builder().build())).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime());
    }

    @Test
    void testTryRestoreTableToPointInTime_DynamoDbClientThrowsInvalidRestoreTimeException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(
                RestoreTableToPointInTimeRequest.builder().build())).thenThrow(InvalidRestoreTimeException.class);

        // Run the test
        assertThrows(InvalidRestoreTimeException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime());
    }

    @Test
    void testTryRestoreTableToPointInTime_DynamoDbClientThrowsPointInTimeRecoveryUnavailableException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(
                RestoreTableToPointInTimeRequest.builder().build()))
                .thenThrow(PointInTimeRecoveryUnavailableException.class);

        // Run the test
        assertThrows(PointInTimeRecoveryUnavailableException.class,
                () -> myClassUnderTest.tryRestoreTableToPointInTime());
    }

    @Test
    void testTryRestoreTableToPointInTime_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(
                RestoreTableToPointInTimeRequest.builder().build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime());
    }

    @Test
    void testTryRestoreTableToPointInTime_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(
                RestoreTableToPointInTimeRequest.builder().build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime());
    }

    @Test
    void testTryRestoreTableToPointInTime_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(
                RestoreTableToPointInTimeRequest.builder().build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime());
    }

    @Test
    void testTryRestoreTableToPointInTime_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(
                RestoreTableToPointInTimeRequest.builder().build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime());
    }

    @Test
    void testTryRestoreTableToPointInTime1() {
        // Setup
        // Configure DynamoDbClient.restoreTableToPointInTime(...).
        final RestoreTableToPointInTimeResponse restoreTableToPointInTimeResponse = RestoreTableToPointInTimeResponse.builder().build();
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class)))
                .thenReturn(restoreTableToPointInTimeResponse);

        // Run the test
        myClassUnderTest.tryRestoreTableToPointInTime1();

        // Verify the results
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsTableAlreadyExistsException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class)))
                .thenThrow(TableAlreadyExistsException.class);

        // Run the test
        assertThrows(TableAlreadyExistsException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class))).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsTableInUseException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class))).thenThrow(TableInUseException.class);

        // Run the test
        assertThrows(TableInUseException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsInvalidRestoreTimeException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class)))
                .thenThrow(InvalidRestoreTimeException.class);

        // Run the test
        assertThrows(InvalidRestoreTimeException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsPointInTimeRecoveryUnavailableException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class)))
                .thenThrow(PointInTimeRecoveryUnavailableException.class);

        // Run the test
        assertThrows(PointInTimeRecoveryUnavailableException.class,
                () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryRestoreTableToPointInTime1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.restoreTableToPointInTime(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryRestoreTableToPointInTime1());
    }

    @Test
    void testTryScan() {
        // Setup
        // Configure DynamoDbClient.scan(...).
        final ScanResponse scanResponse = ScanResponse.builder()
                .items(Arrays.asList(
                        new HashMap<String, AttributeValue>() {{
                            put("KeyName1", AttributeValue.builder().s("Value1").build());
                            put("KeyName2", AttributeValue.builder().s("Value2").build());
                        }}
                )).build();
        when(mockDynamoDbClient.scan(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenReturn(scanResponse);

        // Run the test
        myClassUnderTest.tryScan();

        // Verify the results
    }

    @Test
    void testTryScan_DynamoDbClientReturnsNoItems() {
        // Setup
        when(mockDynamoDbClient.scan(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenReturn(ScanResponse.builder().build());

        // Run the test
        myClassUnderTest.tryScan();

        // Verify the results
    }

    @Test
    void testTryScan_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.scan(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryScan());
    }

    @Test
    void testTryScan_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.scan(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryScan());
    }

    @Test
    void testTryScan_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.scan(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryScan());
    }

    @Test
    void testTryScan_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.scan(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryScan());
    }

    @Test
    void testTryScan_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.scan(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryScan());
    }

    @Test
    void testTryScan_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.scan(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryScan());
    }

    @Test
    void testTryScan_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.scan(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryScan());
    }

    @Test
    void testTryScan1() {
        // Setup
        // Configure DynamoDbClient.scan(...).
        final ScanResponse scanResponse = ScanResponse.builder()
                .items(Arrays.asList(
                        new HashMap<String, AttributeValue>() {{
                            put("KeyName1", AttributeValue.builder().s("Value1").build());
                            put("KeyName2", AttributeValue.builder().s("Value2").build());
                        }}
                )).build();
        when(mockDynamoDbClient.scan(any(Consumer.class))).thenReturn(scanResponse);

        // Run the test
        myClassUnderTest.tryScan1();

        // Verify the results
    }

    @Test
    void testTryScan1_DynamoDbClientReturnsNoItems() {
        // Setup
        when(mockDynamoDbClient.scan(any(Consumer.class))).thenReturn(ScanResponse.builder().build());

        // Run the test
        myClassUnderTest.tryScan1();

        // Verify the results
    }

    @Test
    void testTryScan1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.scan(any(Consumer.class))).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryScan1());
    }

    @Test
    void testTryScan1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.scan(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryScan1());
    }

    @Test
    void testTryScan1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.scan(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryScan1());
    }

    @Test
    void testTryScan1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.scan(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryScan1());
    }

    @Test
    void testTryScan1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.scan(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryScan1());
    }

    @Test
    void testTryScan1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.scan(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryScan1());
    }

    @Test
    void testTryScan1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.scan(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryScan1());
    }

    @Test
    void testTryScanPaginator() {
        // Setup
        // Configure DynamoDbClient.scanPaginator(...).
        final ScanIterable mockScanIterable = mock(ScanIterable.class);
        when(mockDynamoDbClient.scanPaginator(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenReturn(mockScanIterable);

        // Run the test
        myClassUnderTest.tryScanPaginator();

        // Verify the results
    }

    @Test
    void testTryScanPaginator_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.scanPaginator(...).
        final ScanIterable mockScanIterable = mock(ScanIterable.class);
        when(mockDynamoDbClient.scanPaginator(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenReturn(mockScanIterable);

        // Run the test
        myClassUnderTest.tryScanPaginator();

        // Verify the results
    }

    @Test
    void testTryScanPaginator_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryScanPaginator());
    }

    @Test
    void testTryScanPaginator_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryScanPaginator());
    }

    @Test
    void testTryScanPaginator_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryScanPaginator());
    }

    @Test
    void testTryScanPaginator_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryScanPaginator());
    }

    @Test
    void testTryScanPaginator_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryScanPaginator());
    }

    @Test
    void testTryScanPaginator_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryScanPaginator());
    }

    @Test
    void testTryScanPaginator_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(ScanRequest.builder()
                .tableName("TableName")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryScanPaginator());
    }

    @Test
    void testTryScanPaginator1() {
        // Setup
        // Configure DynamoDbClient.scanPaginator(...).
        final ScanIterable mockScanIterable = mock(ScanIterable.class);
        when(mockDynamoDbClient.scanPaginator(any(Consumer.class))).thenReturn(mockScanIterable);

        // Run the test
        myClassUnderTest.tryScanPaginator1();

        // Verify the results
    }

    @Test
    void testTryScanPaginator1_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.scanPaginator(...).
        final ScanIterable mockScanIterable = mock(ScanIterable.class);
        when(mockDynamoDbClient.scanPaginator(any(Consumer.class))).thenReturn(mockScanIterable);

        // Run the test
        myClassUnderTest.tryScanPaginator1();

        // Verify the results
    }

    @Test
    void testTryScanPaginator1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(any(Consumer.class)))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryScanPaginator1());
    }

    @Test
    void testTryScanPaginator1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryScanPaginator1());
    }

    @Test
    void testTryScanPaginator1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryScanPaginator1());
    }

    @Test
    void testTryScanPaginator1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryScanPaginator1());
    }

    @Test
    void testTryScanPaginator1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryScanPaginator1());
    }

    @Test
    void testTryScanPaginator1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryScanPaginator1());
    }

    @Test
    void testTryScanPaginator1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.scanPaginator(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryScanPaginator1());
    }

    @Test
    void testTryTagResource() {
        // Setup
        when(mockDynamoDbClient.tagResource(TagResourceRequest.builder().build()))
                .thenReturn(TagResourceResponse.builder().build());

        // Run the test
        myClassUnderTest.tryTagResource();

        // Verify the results
    }

    @Test
    void testTryTagResource_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.tagResource(TagResourceRequest.builder().build()))
                .thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryTagResource());
    }

    @Test
    void testTryTagResource_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.tagResource(TagResourceRequest.builder().build()))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryTagResource());
    }

    @Test
    void testTryTagResource_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.tagResource(TagResourceRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryTagResource());
    }

    @Test
    void testTryTagResource_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.tagResource(TagResourceRequest.builder().build()))
                .thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryTagResource());
    }

    @Test
    void testTryTagResource_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.tagResource(TagResourceRequest.builder().build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryTagResource());
    }

    @Test
    void testTryTagResource_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.tagResource(TagResourceRequest.builder().build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryTagResource());
    }

    @Test
    void testTryTagResource_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.tagResource(TagResourceRequest.builder().build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryTagResource());
    }

    @Test
    void testTryTagResource1() {
        // Setup
        when(mockDynamoDbClient.tagResource(any(Consumer.class))).thenReturn(TagResourceResponse.builder().build());

        // Run the test
        myClassUnderTest.tryTagResource1();

        // Verify the results
    }

    @Test
    void testTryTagResource1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.tagResource(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.tagResource(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.tagResource(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.tagResource(any(Consumer.class))).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.tagResource(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.tagResource(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.tagResource(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTransactGetItems() {
        // Setup
        // Configure DynamoDbClient.transactGetItems(...).
        final TransactGetItemsResponse transactGetItemsResponse = TransactGetItemsResponse.builder()
                .responses(Arrays.asList(
                        ItemResponse.builder()
                                .item(new HashMap<String, AttributeValue>() {{
                                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                                }}).build()
                )).build();
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactGetItem.builder().get(Get.builder()
                                .tableName("TableName")
                                .key(new HashMap<String, AttributeValue>() {{
                                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                                }})
                                .build()).build()))
                .build())).thenReturn(transactGetItemsResponse);

        // Run the test
        myClassUnderTest.tryTransactGetItems();

        // Verify the results
    }

    @Test
    void testTryTransactGetItems_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.transactGetItems(...).
        final TransactGetItemsResponse transactGetItemsResponse = TransactGetItemsResponse.builder().build();
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactGetItem.builder().get(Get.builder()
                                .tableName("TableName")
                                .key(new HashMap<String, AttributeValue>() {{
                                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                                }})
                                .build()).build()))
                .build())).thenReturn(transactGetItemsResponse);

        // Run the test
        myClassUnderTest.tryTransactGetItems();

        // Verify the results
    }

    @Test
    void testTryTransactGetItems_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactGetItem.builder().get(Get.builder()
                                .tableName("TableName")
                                .key(new HashMap<String, AttributeValue>() {{
                                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                                }})
                                .build()).build()))
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryTransactGetItems());
    }

    @Test
    void testTryTransactGetItems_DynamoDbClientThrowsTransactionCanceledException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactGetItem.builder().get(Get.builder()
                                .tableName("TableName")
                                .key(new HashMap<String, AttributeValue>() {{
                                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                                }})
                                .build()).build()))
                .build())).thenThrow(TransactionCanceledException.class);

        // Run the test
        assertThrows(TransactionCanceledException.class, () -> myClassUnderTest.tryTransactGetItems());
    }

    @Test
    void testTryTransactGetItems_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactGetItem.builder().get(Get.builder()
                                .tableName("TableName")
                                .key(new HashMap<String, AttributeValue>() {{
                                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                                }})
                                .build()).build()))
                .build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryTransactGetItems());
    }

    @Test
    void testTryTransactGetItems_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactGetItem.builder().get(Get.builder()
                                .tableName("TableName")
                                .key(new HashMap<String, AttributeValue>() {{
                                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                                }})
                                .build()).build()))
                .build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryTransactGetItems());
    }

    @Test
    void testTryTransactGetItems_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactGetItem.builder().get(Get.builder()
                                .tableName("TableName")
                                .key(new HashMap<String, AttributeValue>() {{
                                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                                }})
                                .build()).build()))
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryTransactGetItems());
    }

    @Test
    void testTryTransactGetItems_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactGetItem.builder().get(Get.builder()
                                .tableName("TableName")
                                .key(new HashMap<String, AttributeValue>() {{
                                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                                }})
                                .build()).build()))
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryTransactGetItems());
    }

    @Test
    void testTryTransactGetItems_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactGetItem.builder().get(Get.builder()
                                .tableName("TableName")
                                .key(new HashMap<String, AttributeValue>() {{
                                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                                }})
                                .build()).build()))
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryTransactGetItems());
    }

    @Test
    void testTryTransactGetItems_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(TransactGetItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactGetItem.builder().get(Get.builder()
                                .tableName("TableName")
                                .key(new HashMap<String, AttributeValue>() {{
                                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                                }})
                                .build()).build()))
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryTransactGetItems());
    }

    @Test
    void testTryTransactGetItems1() {
        // Setup
        // Configure DynamoDbClient.transactGetItems(...).
        final TransactGetItemsResponse transactGetItemsResponse = TransactGetItemsResponse.builder()
                .responses(Arrays.asList(
                        ItemResponse.builder()
                                .item(new HashMap<String, AttributeValue>() {{
                                    put("PrimaryKeyName", AttributeValue.builder().s("PrimaryKeyValue").build());
                                }}).build()
                )).build();
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class))).thenReturn(transactGetItemsResponse);

        // Run the test
        myClassUnderTest.tryTransactGetItems1();

        // Verify the results
    }

    @Test
    void testTryTransactGetItems1_DynamoDbClientReturnsNoItems() {
        // Setup
        // Configure DynamoDbClient.transactGetItems(...).
        final TransactGetItemsResponse transactGetItemsResponse = TransactGetItemsResponse.builder().build();
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class))).thenReturn(transactGetItemsResponse);

        // Run the test
        myClassUnderTest.tryTransactGetItems1();

        // Verify the results
    }

    @Test
    void testTryTransactGetItems1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryTransactGetItems1());
    }

    @Test
    void testTryTransactGetItems1_DynamoDbClientThrowsTransactionCanceledException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class))).thenThrow(TransactionCanceledException.class);

        // Run the test
        assertThrows(TransactionCanceledException.class, () -> myClassUnderTest.tryTransactGetItems1());
    }

    @Test
    void testTryTransactGetItems1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class)))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryTransactGetItems1());
    }

    @Test
    void testTryTransactGetItems1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryTransactGetItems1());
    }

    @Test
    void testTryTransactGetItems1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryTransactGetItems1());
    }

    @Test
    void testTryTransactGetItems1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryTransactGetItems1());
    }

    @Test
    void testTryTransactGetItems1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryTransactGetItems1());
    }

    @Test
    void testTryTransactGetItems1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.transactGetItems(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryTransactGetItems1());
    }

    @Test
    void testTryTransactWriteItems() {
        // Setup
        // Configure DynamoDbClient.transactWriteItems(...).
        final TransactWriteItemsResponse transactWriteItemsResponse = TransactWriteItemsResponse.builder().build();
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(new HashMap<String, AttributeValue>() {{
                                            put("PrimaryKeyName",
                                                    AttributeValue.builder().s("PrimaryKeyValue").build());
                                        }})
                                        .build())
                                .build()))
                .build())).thenReturn(transactWriteItemsResponse);

        // Run the test
        myClassUnderTest.tryTransactWriteItems();

        // Verify the results
    }

    @Test
    void testTryTransactWriteItems_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(new HashMap<String, AttributeValue>() {{
                                            put("PrimaryKeyName",
                                                    AttributeValue.builder().s("PrimaryKeyValue").build());
                                        }})
                                        .build())
                                .build()))
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryTransactWriteItems());
    }

    @Test
    void testTryTransactWriteItems_DynamoDbClientThrowsTransactionCanceledException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(new HashMap<String, AttributeValue>() {{
                                            put("PrimaryKeyName",
                                                    AttributeValue.builder().s("PrimaryKeyValue").build());
                                        }})
                                        .build())
                                .build()))
                .build())).thenThrow(TransactionCanceledException.class);

        // Run the test
        assertThrows(TransactionCanceledException.class, () -> myClassUnderTest.tryTransactWriteItems());
    }

    @Test
    void testTryTransactWriteItems_DynamoDbClientThrowsTransactionInProgressException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(new HashMap<String, AttributeValue>() {{
                                            put("PrimaryKeyName",
                                                    AttributeValue.builder().s("PrimaryKeyValue").build());
                                        }})
                                        .build())
                                .build()))
                .build())).thenThrow(TransactionInProgressException.class);

        // Run the test
        assertThrows(TransactionInProgressException.class, () -> myClassUnderTest.tryTransactWriteItems());
    }

    @Test
    void testTryTransactWriteItems_DynamoDbClientThrowsIdempotentParameterMismatchException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(new HashMap<String, AttributeValue>() {{
                                            put("PrimaryKeyName",
                                                    AttributeValue.builder().s("PrimaryKeyValue").build());
                                        }})
                                        .build())
                                .build()))
                .build())).thenThrow(IdempotentParameterMismatchException.class);

        // Run the test
        assertThrows(IdempotentParameterMismatchException.class, () -> myClassUnderTest.tryTransactWriteItems());
    }

    @Test
    void testTryTransactWriteItems_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(new HashMap<String, AttributeValue>() {{
                                            put("PrimaryKeyName",
                                                    AttributeValue.builder().s("PrimaryKeyValue").build());
                                        }})
                                        .build())
                                .build()))
                .build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryTransactWriteItems());
    }

    @Test
    void testTryTransactWriteItems_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(new HashMap<String, AttributeValue>() {{
                                            put("PrimaryKeyName",
                                                    AttributeValue.builder().s("PrimaryKeyValue").build());
                                        }})
                                        .build())
                                .build()))
                .build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryTransactWriteItems());
    }

    @Test
    void testTryTransactWriteItems_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(new HashMap<String, AttributeValue>() {{
                                            put("PrimaryKeyName",
                                                    AttributeValue.builder().s("PrimaryKeyValue").build());
                                        }})
                                        .build())
                                .build()))
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryTransactWriteItems());
    }

    @Test
    void testTryTransactWriteItems_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(new HashMap<String, AttributeValue>() {{
                                            put("PrimaryKeyName",
                                                    AttributeValue.builder().s("PrimaryKeyValue").build());
                                        }})
                                        .build())
                                .build()))
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryTransactWriteItems());
    }

    @Test
    void testTryTransactWriteItems_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(new HashMap<String, AttributeValue>() {{
                                            put("PrimaryKeyName",
                                                    AttributeValue.builder().s("PrimaryKeyValue").build());
                                        }})
                                        .build())
                                .build()))
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryTransactWriteItems());
    }

    @Test
    void testTryTransactWriteItems_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder()
                .transactItems(Arrays.asList(
                        TransactWriteItem.builder()
                                .put(Put.builder()
                                        .tableName("TableName")
                                        .item(new HashMap<String, AttributeValue>() {{
                                            put("PrimaryKeyName",
                                                    AttributeValue.builder().s("PrimaryKeyValue").build());
                                        }})
                                        .build())
                                .build()))
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryTransactWriteItems());
    }

    @Test
    void testTryTransactWriteItems1() {
        // Setup
        // Configure DynamoDbClient.transactWriteItems(...).
        final TransactWriteItemsResponse transactWriteItemsResponse = TransactWriteItemsResponse.builder().build();
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class))).thenReturn(transactWriteItemsResponse);

        // Run the test
        myClassUnderTest.tryTransactWriteItems1();

        // Verify the results
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsTransactionCanceledException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class))).thenThrow(TransactionCanceledException.class);

        // Run the test
        assertThrows(TransactionCanceledException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsTransactionInProgressException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class)))
                .thenThrow(TransactionInProgressException.class);

        // Run the test
        assertThrows(TransactionInProgressException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsIdempotentParameterMismatchException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class)))
                .thenThrow(IdempotentParameterMismatchException.class);

        // Run the test
        assertThrows(IdempotentParameterMismatchException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class)))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryTransactWriteItems1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.transactWriteItems(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryTransactWriteItems1());
    }

    @Test
    void testTryUntagResource() {
        // Setup
        when(mockDynamoDbClient.untagResource(UntagResourceRequest.builder().build()))
                .thenReturn(UntagResourceResponse.builder().build());

        // Run the test
        myClassUnderTest.tryUntagResource();

        // Verify the results
    }

    @Test
    void testTryUntagResource_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.untagResource(UntagResourceRequest.builder().build()))
                .thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUntagResource());
    }

    @Test
    void testTryUntagResource_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.untagResource(UntagResourceRequest.builder().build()))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUntagResource());
    }

    @Test
    void testTryUntagResource_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.untagResource(UntagResourceRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUntagResource());
    }

    @Test
    void testTryUntagResource_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.untagResource(UntagResourceRequest.builder().build()))
                .thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryUntagResource());
    }

    @Test
    void testTryUntagResource_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.untagResource(UntagResourceRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUntagResource());
    }

    @Test
    void testTryUntagResource_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.untagResource(UntagResourceRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUntagResource());
    }

    @Test
    void testTryUntagResource_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.untagResource(UntagResourceRequest.builder().build()))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUntagResource());
    }

    @Test
    void testTryUntagResource1() {
        // Setup
        when(mockDynamoDbClient.untagResource(any(Consumer.class))).thenReturn(UntagResourceResponse.builder().build());

        // Run the test
        myClassUnderTest.tryUntagResource1();

        // Verify the results
    }

    @Test
    void testTryUntagResource1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.untagResource(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.untagResource(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.untagResource(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.untagResource(any(Consumer.class))).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.untagResource(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.untagResource(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.untagResource(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUpdateContinuousBackups() {
        // Setup
        // Configure DynamoDbClient.updateContinuousBackups(...).
        final UpdateContinuousBackupsResponse updateContinuousBackupsResponse = UpdateContinuousBackupsResponse.builder().build();
        when(mockDynamoDbClient.updateContinuousBackups(UpdateContinuousBackupsRequest.builder().build()))
                .thenReturn(updateContinuousBackupsResponse);

        // Run the test
        myClassUnderTest.tryUpdateContinuousBackups();

        // Verify the results
    }

    @Test
    void testTryUpdateContinuousBackups_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(UpdateContinuousBackupsRequest.builder().build()))
                .thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryUpdateContinuousBackups());
    }

    @Test
    void testTryUpdateContinuousBackups_DynamoDbClientThrowsContinuousBackupsUnavailableException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(UpdateContinuousBackupsRequest.builder().build()))
                .thenThrow(ContinuousBackupsUnavailableException.class);

        // Run the test
        assertThrows(ContinuousBackupsUnavailableException.class, () -> myClassUnderTest.tryUpdateContinuousBackups());
    }

    @Test
    void testTryUpdateContinuousBackups_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(UpdateContinuousBackupsRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateContinuousBackups());
    }

    @Test
    void testTryUpdateContinuousBackups_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(UpdateContinuousBackupsRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateContinuousBackups());
    }

    @Test
    void testTryUpdateContinuousBackups_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(UpdateContinuousBackupsRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateContinuousBackups());
    }

    @Test
    void testTryUpdateContinuousBackups_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(UpdateContinuousBackupsRequest.builder().build()))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateContinuousBackups());
    }

    @Test
    void testTryUpdateContinuousBackups1() {
        // Setup
        // Configure DynamoDbClient.updateContinuousBackups(...).
        final UpdateContinuousBackupsResponse updateContinuousBackupsResponse = UpdateContinuousBackupsResponse.builder().build();
        when(mockDynamoDbClient.updateContinuousBackups(any(Consumer.class)))
                .thenReturn(updateContinuousBackupsResponse);

        // Run the test
        myClassUnderTest.tryUpdateContinuousBackups1();

        // Verify the results
    }

    @Test
    void testTryUpdateContinuousBackups1_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(any(Consumer.class))).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryUpdateContinuousBackups1());
    }

    @Test
    void testTryUpdateContinuousBackups1_DynamoDbClientThrowsContinuousBackupsUnavailableException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(any(Consumer.class)))
                .thenThrow(ContinuousBackupsUnavailableException.class);

        // Run the test
        assertThrows(ContinuousBackupsUnavailableException.class, () -> myClassUnderTest.tryUpdateContinuousBackups1());
    }

    @Test
    void testTryUpdateContinuousBackups1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateContinuousBackups1());
    }

    @Test
    void testTryUpdateContinuousBackups1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateContinuousBackups1());
    }

    @Test
    void testTryUpdateContinuousBackups1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateContinuousBackups1());
    }

    @Test
    void testTryUpdateContinuousBackups1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateContinuousBackups(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateContinuousBackups1());
    }

    @Test
    void testTryUpdateGlobalTable() {
        // Setup
        // Configure DynamoDbClient.updateGlobalTable(...).
        final UpdateGlobalTableResponse updateGlobalTableResponse = UpdateGlobalTableResponse.builder().build();
        when(mockDynamoDbClient.updateGlobalTable(UpdateGlobalTableRequest.builder().build()))
                .thenReturn(updateGlobalTableResponse);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTable();

        // Verify the results
    }

    @Test
    void testTryUpdateGlobalTable_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(UpdateGlobalTableRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateGlobalTable());
    }

    @Test
    void testTryUpdateGlobalTable_DynamoDbClientThrowsGlobalTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(UpdateGlobalTableRequest.builder().build()))
                .thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTable());
    }

    @Test
    void testTryUpdateGlobalTable_DynamoDbClientThrowsReplicaAlreadyExistsException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(UpdateGlobalTableRequest.builder().build()))
                .thenThrow(ReplicaAlreadyExistsException.class);

        // Run the test
        assertThrows(ReplicaAlreadyExistsException.class, () -> myClassUnderTest.tryUpdateGlobalTable());
    }

    @Test
    void testTryUpdateGlobalTable_DynamoDbClientThrowsReplicaNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(UpdateGlobalTableRequest.builder().build()))
                .thenThrow(ReplicaNotFoundException.class);

        // Run the test
        assertThrows(ReplicaNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTable());
    }

    @Test
    void testTryUpdateGlobalTable_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(UpdateGlobalTableRequest.builder().build()))
                .thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTable());
    }

    @Test
    void testTryUpdateGlobalTable_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(UpdateGlobalTableRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateGlobalTable());
    }

    @Test
    void testTryUpdateGlobalTable_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(UpdateGlobalTableRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateGlobalTable());
    }

    @Test
    void testTryUpdateGlobalTable_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(UpdateGlobalTableRequest.builder().build()))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateGlobalTable());
    }

    @Test
    void testTryUpdateGlobalTable1() {
        // Setup
        // Configure DynamoDbClient.updateGlobalTable(...).
        final UpdateGlobalTableResponse updateGlobalTableResponse = UpdateGlobalTableResponse.builder().build();
        when(mockDynamoDbClient.updateGlobalTable(any(Consumer.class))).thenReturn(updateGlobalTableResponse);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTable1();

        // Verify the results
    }

    @Test
    void testTryUpdateGlobalTable1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateGlobalTable1());
    }

    @Test
    void testTryUpdateGlobalTable1_DynamoDbClientThrowsGlobalTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(any(Consumer.class))).thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTable1());
    }

    @Test
    void testTryUpdateGlobalTable1_DynamoDbClientThrowsReplicaAlreadyExistsException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(any(Consumer.class))).thenThrow(ReplicaAlreadyExistsException.class);

        // Run the test
        assertThrows(ReplicaAlreadyExistsException.class, () -> myClassUnderTest.tryUpdateGlobalTable1());
    }

    @Test
    void testTryUpdateGlobalTable1_DynamoDbClientThrowsReplicaNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(any(Consumer.class))).thenThrow(ReplicaNotFoundException.class);

        // Run the test
        assertThrows(ReplicaNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTable1());
    }

    @Test
    void testTryUpdateGlobalTable1_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(any(Consumer.class))).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTable1());
    }

    @Test
    void testTryUpdateGlobalTable1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateGlobalTable1());
    }

    @Test
    void testTryUpdateGlobalTable1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateGlobalTable1());
    }

    @Test
    void testTryUpdateGlobalTable1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTable(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateGlobalTable1());
    }

    @Test
    void testTryUpdateGlobalTableSettings() {
        // Setup
        // Configure DynamoDbClient.updateGlobalTableSettings(...).
        final UpdateGlobalTableSettingsResponse updateGlobalTableSettingsResponse = UpdateGlobalTableSettingsResponse.builder().build();
        when(mockDynamoDbClient.updateGlobalTableSettings(
                UpdateGlobalTableSettingsRequest.builder().build())).thenReturn(updateGlobalTableSettingsResponse);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTableSettings();

        // Verify the results
    }

    @Test
    void testTryUpdateGlobalTableSettings_DynamoDbClientThrowsGlobalTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(
                UpdateGlobalTableSettingsRequest.builder().build())).thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings());
    }

    @Test
    void testTryUpdateGlobalTableSettings_DynamoDbClientThrowsReplicaNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(
                UpdateGlobalTableSettingsRequest.builder().build())).thenThrow(ReplicaNotFoundException.class);

        // Run the test
        assertThrows(ReplicaNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings());
    }

    @Test
    void testTryUpdateGlobalTableSettings_DynamoDbClientThrowsIndexNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(
                UpdateGlobalTableSettingsRequest.builder().build())).thenThrow(IndexNotFoundException.class);

        // Run the test
        assertThrows(IndexNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings());
    }

    @Test
    void testTryUpdateGlobalTableSettings_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(
                UpdateGlobalTableSettingsRequest.builder().build())).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings());
    }

    @Test
    void testTryUpdateGlobalTableSettings_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(
                UpdateGlobalTableSettingsRequest.builder().build())).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings());
    }

    @Test
    void testTryUpdateGlobalTableSettings_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(
                UpdateGlobalTableSettingsRequest.builder().build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings());
    }

    @Test
    void testTryUpdateGlobalTableSettings_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(
                UpdateGlobalTableSettingsRequest.builder().build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings());
    }

    @Test
    void testTryUpdateGlobalTableSettings_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(
                UpdateGlobalTableSettingsRequest.builder().build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings());
    }

    @Test
    void testTryUpdateGlobalTableSettings_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(
                UpdateGlobalTableSettingsRequest.builder().build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings());
    }

    @Test
    void testTryUpdateGlobalTableSettings1() {
        // Setup
        // Configure DynamoDbClient.updateGlobalTableSettings(...).
        final UpdateGlobalTableSettingsResponse updateGlobalTableSettingsResponse = UpdateGlobalTableSettingsResponse.builder().build();
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class)))
                .thenReturn(updateGlobalTableSettingsResponse);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTableSettings1();

        // Verify the results
    }

    @Test
    void testTryUpdateGlobalTableSettings1_DynamoDbClientThrowsGlobalTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class)))
                .thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings1());
    }

    @Test
    void testTryUpdateGlobalTableSettings1_DynamoDbClientThrowsReplicaNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class)))
                .thenThrow(ReplicaNotFoundException.class);

        // Run the test
        assertThrows(ReplicaNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings1());
    }

    @Test
    void testTryUpdateGlobalTableSettings1_DynamoDbClientThrowsIndexNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class))).thenThrow(IndexNotFoundException.class);

        // Run the test
        assertThrows(IndexNotFoundException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings1());
    }

    @Test
    void testTryUpdateGlobalTableSettings1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings1());
    }

    @Test
    void testTryUpdateGlobalTableSettings1_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class))).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings1());
    }

    @Test
    void testTryUpdateGlobalTableSettings1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings1());
    }

    @Test
    void testTryUpdateGlobalTableSettings1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings1());
    }

    @Test
    void testTryUpdateGlobalTableSettings1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings1());
    }

    @Test
    void testTryUpdateGlobalTableSettings1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateGlobalTableSettings(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateGlobalTableSettings1());
    }

    @Test
    void testTryUpdateItem() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder().build()))
                .thenReturn(UpdateItemResponse.builder().build());

        // Run the test
        myClassUnderTest.tryUpdateItem();

        // Verify the results
    }

    @Test
    void testTryUpdateItem_DynamoDbClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder().build()))
                .thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, () -> myClassUnderTest.tryUpdateItem());
    }

    @Test
    void testTryUpdateItem_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder().build()))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryUpdateItem());
    }

    @Test
    void testTryUpdateItem_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder().build()))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUpdateItem());
    }

    @Test
    void testTryUpdateItem_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder().build()))
                .thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.tryUpdateItem());
    }

    @Test
    void testTryUpdateItem_DynamoDbClientThrowsTransactionConflictException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder().build()))
                .thenThrow(TransactionConflictException.class);

        // Run the test
        assertThrows(TransactionConflictException.class, () -> myClassUnderTest.tryUpdateItem());
    }

    @Test
    void testTryUpdateItem_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder().build()))
                .thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryUpdateItem());
    }

    @Test
    void testTryUpdateItem_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateItem());
    }

    @Test
    void testTryUpdateItem_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder().build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateItem());
    }

    @Test
    void testTryUpdateItem_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder().build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateItem());
    }

    @Test
    void testTryUpdateItem_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateItem(UpdateItemRequest.builder().build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateItem());
    }

    @Test
    void testTryUpdateItem1() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class))).thenReturn(UpdateItemResponse.builder().build());

        // Run the test
        myClassUnderTest.tryUpdateItem1();

        // Verify the results
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class))).thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class)))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class)))
                .thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsTransactionConflictException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class))).thenThrow(TransactionConflictException.class);

        // Run the test
        assertThrows(TransactionConflictException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateItem(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateTable() {
        // Setup
        when(mockDynamoDbClient.updateTable(UpdateTableRequest.builder().build()))
                .thenReturn(UpdateTableResponse.builder().build());

        // Run the test
        myClassUnderTest.tryUpdateTable();

        // Verify the results
    }

    @Test
    void testTryUpdateTable_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.updateTable(UpdateTableRequest.builder().build()))
                .thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryUpdateTable());
    }

    @Test
    void testTryUpdateTable_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateTable(UpdateTableRequest.builder().build()))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUpdateTable());
    }

    @Test
    void testTryUpdateTable_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateTable(UpdateTableRequest.builder().build()))
                .thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUpdateTable());
    }

    @Test
    void testTryUpdateTable_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateTable(UpdateTableRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateTable());
    }

    @Test
    void testTryUpdateTable_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateTable(UpdateTableRequest.builder().build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateTable());
    }

    @Test
    void testTryUpdateTable_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateTable(UpdateTableRequest.builder().build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateTable());
    }

    @Test
    void testTryUpdateTable_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateTable(UpdateTableRequest.builder().build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateTable());
    }

    @Test
    void testTryUpdateTable1() {
        // Setup
        when(mockDynamoDbClient.updateTable(any(Consumer.class))).thenReturn(UpdateTableResponse.builder().build());

        // Run the test
        myClassUnderTest.tryUpdateTable1();

        // Verify the results
    }

    @Test
    void testTryUpdateTable1_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.updateTable(any(Consumer.class))).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryUpdateTable1());
    }

    @Test
    void testTryUpdateTable1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateTable(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUpdateTable1());
    }

    @Test
    void testTryUpdateTable1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateTable(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUpdateTable1());
    }

    @Test
    void testTryUpdateTable1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateTable(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateTable1());
    }

    @Test
    void testTryUpdateTable1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateTable(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateTable1());
    }

    @Test
    void testTryUpdateTable1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateTable(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateTable1());
    }

    @Test
    void testTryUpdateTable1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateTable(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateTable1());
    }

    @Test
    void testTryUpdateTimeToLive() {
        // Setup
        // Configure DynamoDbClient.updateTimeToLive(...).
        final UpdateTimeToLiveResponse updateTimeToLiveResponse = UpdateTimeToLiveResponse.builder().build();
        when(mockDynamoDbClient.updateTimeToLive(UpdateTimeToLiveRequest.builder().build()))
                .thenReturn(updateTimeToLiveResponse);

        // Run the test
        myClassUnderTest.tryUpdateTimeToLive();

        // Verify the results
    }

    @Test
    void testTryUpdateTimeToLive_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(UpdateTimeToLiveRequest.builder().build()))
                .thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryUpdateTimeToLive());
    }

    @Test
    void testTryUpdateTimeToLive_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(UpdateTimeToLiveRequest.builder().build()))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUpdateTimeToLive());
    }

    @Test
    void testTryUpdateTimeToLive_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(UpdateTimeToLiveRequest.builder().build()))
                .thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUpdateTimeToLive());
    }

    @Test
    void testTryUpdateTimeToLive_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(UpdateTimeToLiveRequest.builder().build()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateTimeToLive());
    }

    @Test
    void testTryUpdateTimeToLive_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(UpdateTimeToLiveRequest.builder().build()))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateTimeToLive());
    }

    @Test
    void testTryUpdateTimeToLive_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(UpdateTimeToLiveRequest.builder().build()))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateTimeToLive());
    }

    @Test
    void testTryUpdateTimeToLive_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(UpdateTimeToLiveRequest.builder().build()))
                .thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateTimeToLive());
    }

    @Test
    void testTryUpdateTimeToLive1() {
        // Setup
        // Configure DynamoDbClient.updateTimeToLive(...).
        final UpdateTimeToLiveResponse updateTimeToLiveResponse = UpdateTimeToLiveResponse.builder().build();
        when(mockDynamoDbClient.updateTimeToLive(any(Consumer.class))).thenReturn(updateTimeToLiveResponse);

        // Run the test
        myClassUnderTest.tryUpdateTimeToLive1();

        // Verify the results
    }

    @Test
    void testTryUpdateTimeToLive1_DynamoDbClientThrowsResourceInUseException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(any(Consumer.class))).thenThrow(ResourceInUseException.class);

        // Run the test
        assertThrows(ResourceInUseException.class, () -> myClassUnderTest.tryUpdateTimeToLive1());
    }

    @Test
    void testTryUpdateTimeToLive1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUpdateTimeToLive1());
    }

    @Test
    void testTryUpdateTimeToLive1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(any(Consumer.class))).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUpdateTimeToLive1());
    }

    @Test
    void testTryUpdateTimeToLive1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(any(Consumer.class))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryUpdateTimeToLive1());
    }

    @Test
    void testTryUpdateTimeToLive1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUpdateTimeToLive1());
    }

    @Test
    void testTryUpdateTimeToLive1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateTimeToLive1());
    }

    @Test
    void testTryUpdateTimeToLive1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.updateTimeToLive(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateTimeToLive1());
    }
}
