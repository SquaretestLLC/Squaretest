package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private DynamoDbClient mockDynamoDbClient;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockDynamoDbClient, mockMetricService);
    }

    @Test
    void testBatchWrite1() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        myClassUnderTest.batchWrite1(orders);

        // Verify the results
    }

    @Test
    void testBatchWrite1_DynamoDbClientReturnsFailure() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(BatchPutException.class, () -> myClassUnderTest.batchWrite1(orders));
    }

    @Test
    void testBatchWrite1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.batchWrite1(orders));
    }

    @Test
    void testBatchWrite1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.batchWrite1(orders));
    }

    @Test
    void testBatchWrite1_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.batchWrite1(orders));
    }

    @Test
    void testBatchWrite1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.batchWrite1(orders));
    }

    @Test
    void testBatchWrite1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.batchWrite1(orders));
    }

    @Test
    void testBatchWrite1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.batchWrite1(orders));
    }

    @Test
    void testBatchWrite1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(SdkClientException.class, () -> myClassUnderTest.batchWrite1(orders));
    }

    @Test
    void testBatchWrite1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.batchWrite1(orders));
    }

    @Test
    void testBatchWrite2() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        myClassUnderTest.batchWrite2(orders);

        // Verify the results
    }

    @Test
    void testBatchWrite2_DynamoDbClientReturnsFailure() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(BatchPutException.class, () -> myClassUnderTest.batchWrite2(orders));
    }

    @Test
    void testBatchWrite2_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.batchWrite2(orders));
    }

    @Test
    void testBatchWrite2_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.batchWrite2(orders));
    }

    @Test
    void testBatchWrite2_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.batchWrite2(orders));
    }

    @Test
    void testBatchWrite2_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.batchWrite2(orders));
    }

    @Test
    void testBatchWrite2_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.batchWrite2(orders));
    }

    @Test
    void testBatchWrite2_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.batchWrite2(orders));
    }

    @Test
    void testBatchWrite2_DynamoDbClientThrowsSdkClientException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(SdkClientException.class, () -> myClassUnderTest.batchWrite2(orders));
    }

    @Test
    void testBatchWrite2_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.batchWrite2(orders));
    }

    @Test
    void testBatchWrite3() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        myClassUnderTest.batchWrite3(orders);

        // Verify the results
    }

    @Test
    void testBatchWrite3_DynamoDbClientReturnsFailure() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        myClassUnderTest.batchWrite3(orders);

        // Verify the results
        verify(mockMetricService).recordFailedWrite("orderId");
    }

    @Test
    void testBatchWrite3_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.batchWrite3(orders));
    }

    @Test
    void testBatchWrite3_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.batchWrite3(orders));
    }

    @Test
    void testBatchWrite3_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.batchWrite3(orders));
    }

    @Test
    void testBatchWrite3_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.batchWrite3(orders));
    }

    @Test
    void testBatchWrite3_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.batchWrite3(orders));
    }

    @Test
    void testBatchWrite3_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.batchWrite3(orders));
    }

    @Test
    void testBatchWrite3_DynamoDbClientThrowsSdkClientException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(SdkClientException.class, () -> myClassUnderTest.batchWrite3(orders));
    }

    @Test
    void testBatchWrite3_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.batchWrite3(orders));
    }

    @Test
    void testBatchWrite4() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        myClassUnderTest.batchWrite4(orders);

        // Verify the results
    }

    @Test
    void testBatchWrite4_DynamoDbClientReturnsFailure() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        myClassUnderTest.batchWrite4(orders);

        // Verify the results
        verify(mockMetricService).recordFailedWrite("orderId");
    }

    @Test
    void testBatchWrite4_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.batchWrite4(orders));
    }

    @Test
    void testBatchWrite4_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.batchWrite4(orders));
    }

    @Test
    void testBatchWrite4_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.batchWrite4(orders));
    }

    @Test
    void testBatchWrite4_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.batchWrite4(orders));
    }

    @Test
    void testBatchWrite4_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.batchWrite4(orders));
    }

    @Test
    void testBatchWrite4_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.batchWrite4(orders));
    }

    @Test
    void testBatchWrite4_DynamoDbClientThrowsSdkClientException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(SdkClientException.class, () -> myClassUnderTest.batchWrite4(orders));
    }

    @Test
    void testBatchWrite4_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.batchWrite4(orders));
    }

    @Test
    void testBatchWrite5() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        myClassUnderTest.batchWrite5(orders);

        // Verify the results
    }

    @Test
    void testBatchWrite5_DynamoDbClientReturnsFailure() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        myClassUnderTest.batchWrite5(orders);

        // Verify the results
        verify(mockMetricService).recordFailedWrite("orderId");
    }

    @Test
    void testBatchWrite5_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.batchWrite5(orders));
    }

    @Test
    void testBatchWrite5_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.batchWrite5(orders));
    }

    @Test
    void testBatchWrite5_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.batchWrite5(orders));
    }

    @Test
    void testBatchWrite5_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.batchWrite5(orders));
    }

    @Test
    void testBatchWrite5_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.batchWrite5(orders));
    }

    @Test
    void testBatchWrite5_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.batchWrite5(orders));
    }

    @Test
    void testBatchWrite5_DynamoDbClientThrowsSdkClientException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(SdkClientException.class, () -> myClassUnderTest.batchWrite5(orders));
    }

    @Test
    void testBatchWrite5_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
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
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.batchWrite5(orders));
    }
}
