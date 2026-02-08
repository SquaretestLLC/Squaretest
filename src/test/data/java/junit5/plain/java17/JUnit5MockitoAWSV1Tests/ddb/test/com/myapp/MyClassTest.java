package com.myapp;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AmazonDynamoDB mockAmazonDynamoDB;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockAmazonDynamoDB, mockMetricService);
    }

    @Test
    void testGetOrders1() {
        // Setup
        final Order order = new Order();
        order.setId("s");
        order.setShipAddress("s");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(List.of(product));
        order.setOtherId("s");
        final List<Order> expectedResult = List.of(order);

        // Configure AmazonDynamoDB.scan(...).
        final ScanResult scanResult = new ScanResult().withItems(
                Map.ofEntries(
                        Map.entry("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue1")),
                        Map.entry("KeyName2", new AttributeValue().withS("Value2"))
                ),
                Map.ofEntries(
                        Map.entry("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue2")),
                        Map.entry("KeyName2", new AttributeValue().withS("Value2"))
                )
        );
        when(mockAmazonDynamoDB.scan(new ScanRequest("Orders"))).thenReturn(scanResult);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders1("criteria");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordOrder("s");
    }

    @Test
    void testGetOrders1_AmazonDynamoDBReturnsNoItems() {
        // Setup
        when(mockAmazonDynamoDB.scan(new ScanRequest("Orders"))).thenReturn(new ScanResult());

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders1("criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders1_AmazonDynamoDBThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDB.scan(new ScanRequest("Orders")))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.getOrders1("criteria"));
    }

    @Test
    void testGetOrders1_AmazonDynamoDBThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDB.scan(new ScanRequest("Orders"))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.getOrders1("criteria"));
    }

    @Test
    void testGetOrders1_AmazonDynamoDBThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDB.scan(new ScanRequest("Orders"))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.getOrders1("criteria"));
    }

    @Test
    void testGetOrders1_AmazonDynamoDBThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDB.scan(new ScanRequest("Orders"))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.getOrders1("criteria"));
    }
}
