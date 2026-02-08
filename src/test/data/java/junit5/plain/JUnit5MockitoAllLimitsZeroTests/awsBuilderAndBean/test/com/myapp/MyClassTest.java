package com.myapp;

import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private DynamoDbTable<Order> mockOrdersTable;
    @Mock
    private MetricsAdapter mockMetricsAdapter;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockOrdersTable, mockMetricsAdapter);
    }

    @Test
    void testGetOrder() throws Exception {
        // Setup
        // Configure DynamoDbTable.getItem(...).
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(new DateTime(2020, 1, 1, 1, 0, 0, 0));
        final Product product = new Product();
        product.setId("id");
        order.setProducts(Arrays.asList(product));
        when(mockOrdersTable.getItem(Key.builder().build())).thenReturn(order);

        // Run the test
        final Order result = myClassUnderTest.getOrder("orderId");

        // Verify the results
        verify(mockMetricsAdapter).recordOrderRetrieved("orderId");
    }

    @Test
    void testGetOrder_DynamoDbTableReturnsNull() {
        // Setup
        when(mockOrdersTable.getItem(Key.builder().build())).thenReturn(null);

        // Run the test
        assertThrows(OrderStoreException.class, () -> myClassUnderTest.getOrder("orderId"));
        verify(mockMetricsAdapter).recordOrderNotFound("orderId");
    }

    @Test
    void testGetOrder_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockOrdersTable.getItem(Key.builder().build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(OrderStoreException.class, () -> myClassUnderTest.getOrder("orderId"));
        verify(mockMetricsAdapter).recordOrderStoreException(any(Exception.class));
    }

    @Test
    void testGetOrder_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockOrdersTable.getItem(Key.builder().build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(OrderStoreException.class, () -> myClassUnderTest.getOrder("orderId"));
        verify(mockMetricsAdapter).recordOrderStoreException(any(Exception.class));
    }
}
