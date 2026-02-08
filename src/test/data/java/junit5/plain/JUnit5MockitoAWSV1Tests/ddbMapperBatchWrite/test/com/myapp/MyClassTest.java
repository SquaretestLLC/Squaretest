package com.myapp;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private DynamoDBMapper mockDynamoDBMapper;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockDynamoDBMapper);
    }

    @Test
    void testPutOrders1() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        product.setId("id");
        order.setProducts(Arrays.asList(product));
        final List<Order> orders = Arrays.asList(order);

        // Configure DynamoDBMapper.batchWrite(...).
        final Order order1 = new Order();
        order1.setId("id");
        order1.setShipAddress("shipAddress");
        order1.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product1 = new Product();
        product1.setId("id");
        order1.setProducts(Arrays.asList(product1));
        final List<Order> objectsToWrite = Arrays.asList(order1);
        when(mockDynamoDBMapper.batchWrite(objectsToWrite, Arrays.asList("value"))).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.putOrders1(orders);

        // Verify the results
    }

    @Test
    void testPutOrders1_DynamoDBMapperReturnsFailure() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        product.setId("id");
        order.setProducts(Arrays.asList(product));
        final List<Order> orders = Arrays.asList(order);

        // Configure DynamoDBMapper.batchWrite(...).
        final DynamoDBMapper.FailedBatch failedBatch = new DynamoDBMapper.FailedBatch();
        final List<DynamoDBMapper.FailedBatch> failedBatches = Arrays.asList(failedBatch);
        final Order order1 = new Order();
        order1.setId("id");
        order1.setShipAddress("shipAddress");
        order1.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product1 = new Product();
        product1.setId("id");
        order1.setProducts(Arrays.asList(product1));
        final List<Order> objectsToWrite = Arrays.asList(order1);
        when(mockDynamoDBMapper.batchWrite(objectsToWrite, Arrays.asList("value"))).thenReturn(failedBatches);

        // Run the test
        assertThrows(BatchWriteException.class, () -> myClassUnderTest.putOrders1(orders));
    }

    @Test
    void testPutOrders1_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        product.setId("id");
        order.setProducts(Arrays.asList(product));
        final List<Order> orders = Arrays.asList(order);

        // Configure DynamoDBMapper.batchWrite(...).
        final Order order1 = new Order();
        order1.setId("id");
        order1.setShipAddress("shipAddress");
        order1.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product1 = new Product();
        product1.setId("id");
        order1.setProducts(Arrays.asList(product1));
        final List<Order> objectsToWrite = Arrays.asList(order1);
        when(mockDynamoDBMapper.batchWrite(objectsToWrite, Arrays.asList("value")))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.putOrders1(orders));
    }

    @Test
    void testPutOrders2() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        product.setId("id");
        order.setProducts(Arrays.asList(product));
        final List<Order> orders = Arrays.asList(order);

        // Configure DynamoDBMapper.batchSave(...).
        final Order order1 = new Order();
        order1.setId("id");
        order1.setShipAddress("shipAddress");
        order1.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product1 = new Product();
        product1.setId("id");
        order1.setProducts(Arrays.asList(product1));
        final List<Order> objectsToSave = Arrays.asList(order1);
        when(mockDynamoDBMapper.batchSave(objectsToSave)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.putOrders2(orders);

        // Verify the results
    }

    @Test
    void testPutOrders2_DynamoDBMapperReturnsFailure() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        product.setId("id");
        order.setProducts(Arrays.asList(product));
        final List<Order> orders = Arrays.asList(order);

        // Configure DynamoDBMapper.batchSave(...).
        final DynamoDBMapper.FailedBatch failedBatch = new DynamoDBMapper.FailedBatch();
        final List<DynamoDBMapper.FailedBatch> failedBatches = Arrays.asList(failedBatch);
        final Order order1 = new Order();
        order1.setId("id");
        order1.setShipAddress("shipAddress");
        order1.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product1 = new Product();
        product1.setId("id");
        order1.setProducts(Arrays.asList(product1));
        final List<Order> objectsToSave = Arrays.asList(order1);
        when(mockDynamoDBMapper.batchSave(objectsToSave)).thenReturn(failedBatches);

        // Run the test
        assertThrows(BatchWriteException.class, () -> myClassUnderTest.putOrders2(orders));
    }

    @Test
    void testPutOrders2_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        product.setId("id");
        order.setProducts(Arrays.asList(product));
        final List<Order> orders = Arrays.asList(order);

        // Configure DynamoDBMapper.batchSave(...).
        final Order order1 = new Order();
        order1.setId("id");
        order1.setShipAddress("shipAddress");
        order1.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product1 = new Product();
        product1.setId("id");
        order1.setProducts(Arrays.asList(product1));
        final List<Order> objectsToSave = Arrays.asList(order1);
        when(mockDynamoDBMapper.batchSave(objectsToSave)).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.putOrders2(orders));
    }

    @Test
    void testPutOrders3() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        product.setId("id");
        order.setProducts(Arrays.asList(product));

        // Configure DynamoDBMapper.batchSave(...).
        final Order objectsToSave = new Order();
        objectsToSave.setId("id");
        objectsToSave.setShipAddress("shipAddress");
        objectsToSave.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product1 = new Product();
        product1.setId("id");
        objectsToSave.setProducts(Arrays.asList(product1));
        when(mockDynamoDBMapper.batchSave(objectsToSave)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.putOrders3(order);

        // Verify the results
    }

    @Test
    void testPutOrders3_DynamoDBMapperReturnsFailure() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        product.setId("id");
        order.setProducts(Arrays.asList(product));

        // Configure DynamoDBMapper.batchSave(...).
        final DynamoDBMapper.FailedBatch failedBatch = new DynamoDBMapper.FailedBatch();
        final List<DynamoDBMapper.FailedBatch> failedBatches = Arrays.asList(failedBatch);
        final Order objectsToSave = new Order();
        objectsToSave.setId("id");
        objectsToSave.setShipAddress("shipAddress");
        objectsToSave.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product1 = new Product();
        product1.setId("id");
        objectsToSave.setProducts(Arrays.asList(product1));
        when(mockDynamoDBMapper.batchSave(objectsToSave)).thenReturn(failedBatches);

        // Run the test
        assertThrows(BatchWriteException.class, () -> myClassUnderTest.putOrders3(order));
    }

    @Test
    void testPutOrders3_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        product.setId("id");
        order.setProducts(Arrays.asList(product));

        // Configure DynamoDBMapper.batchSave(...).
        final Order objectsToSave = new Order();
        objectsToSave.setId("id");
        objectsToSave.setShipAddress("shipAddress");
        objectsToSave.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product1 = new Product();
        product1.setId("id");
        objectsToSave.setProducts(Arrays.asList(product1));
        when(mockDynamoDBMapper.batchSave(objectsToSave)).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.putOrders3(order));
    }

    @Test
    void testPutOrders4() {
        // Setup
        final Order orders = new Order();
        orders.setId("id");
        orders.setShipAddress("shipAddress");
        orders.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        product.setId("id");
        orders.setProducts(Arrays.asList(product));

        when(mockDynamoDBMapper.batchSave(any(Object.class))).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.putOrders4(orders);

        // Verify the results
    }

    @Test
    void testPutOrders4_DynamoDBMapperReturnsFailure() {
        // Setup
        final Order orders = new Order();
        orders.setId("id");
        orders.setShipAddress("shipAddress");
        orders.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        product.setId("id");
        orders.setProducts(Arrays.asList(product));

        // Configure DynamoDBMapper.batchSave(...).
        final DynamoDBMapper.FailedBatch failedBatch = new DynamoDBMapper.FailedBatch();
        final List<DynamoDBMapper.FailedBatch> failedBatches = Arrays.asList(failedBatch);
        when(mockDynamoDBMapper.batchSave(any(Object.class))).thenReturn(failedBatches);

        // Run the test
        assertThrows(BatchWriteException.class, () -> myClassUnderTest.putOrders4(orders));
    }

    @Test
    void testPutOrders4_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        final Order orders = new Order();
        orders.setId("id");
        orders.setShipAddress("shipAddress");
        orders.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        product.setId("id");
        orders.setProducts(Arrays.asList(product));

        when(mockDynamoDBMapper.batchSave(any(Object.class))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.putOrders4(orders));
    }
}
