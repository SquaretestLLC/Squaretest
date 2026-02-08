package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.model.BatchWriteItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.BatchWriteResult;
import software.amazon.awssdk.enhanced.dynamodb.model.WriteBatch;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private DynamoDbEnhancedClient mockDynamoDbEnhancedClient;
    @Mock
    private DynamoDbTable<Order> mockOrdersTable;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockDynamoDbEnhancedClient, mockOrdersTable);
    }

    @Test
    void testBatchWrite() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        product.setId("id");
        order.setProducts(Arrays.asList(product));

        // Configure DynamoDbEnhancedClient.batchWriteItem(...).
        final Order order1 = new Order();
        order1.setId("id");
        order1.setShipAddress("shipAddress");
        order1.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product1 = new Product();
        product1.setId("id");
        order1.setProducts(Arrays.asList(product1));
        final BatchWriteItemEnhancedRequest request = BatchWriteItemEnhancedRequest.builder()
                .writeBatches(WriteBatch.builder(Order.class)
                        .addPutItem(order1)
                        .build())
                .build();
        when(mockDynamoDbEnhancedClient.batchWriteItem(request)).thenReturn(BatchWriteResult.builder().build());

        // Run the test
        myClassUnderTest.batchWrite(order);

        // Verify the results
    }

    @Test
    void testBatchWrite_DynamoDbEnhancedClientReturnsFailure() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        product.setId("id");
        order.setProducts(Arrays.asList(product));

        // Configure DynamoDbEnhancedClient.batchWriteItem(...).
        final Order order1 = new Order();
        order1.setId("id");
        order1.setShipAddress("shipAddress");
        order1.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product1 = new Product();
        product1.setId("id");
        order1.setProducts(Arrays.asList(product1));
        final BatchWriteItemEnhancedRequest request = BatchWriteItemEnhancedRequest.builder()
                .writeBatches(WriteBatch.builder(Order.class)
                        .addPutItem(order1)
                        .build())
                .build();
        when(mockDynamoDbEnhancedClient.batchWriteItem(request)).thenReturn(BatchWriteResult.builder()
                .unprocessedRequests(new HashMap<>()).build());

        // Run the test
        assertThrows(BatchPutException.class, () -> myClassUnderTest.batchWrite(order));
    }
}
