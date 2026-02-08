package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private DynamoDbTable<Order> mockOrdersTable;
    @Mock
    private MetricService mockMetricService;
    @Mock
    private OtherService mockOtherService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockOrdersTable, mockMetricService, mockOtherService);
    }

    @Test
    void testGetOrder1() throws Exception {
        // Setup
        // Configure DynamoDbTable.getItem(...).
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        order.setOtherId("otherId");
        order.setOtherData("otherData");
        when(mockOrdersTable.getItem(Key.builder()
                .partitionValue("orderId")
                .build())).thenReturn(order);

        // Run the test
        final Order result = myClassUnderTest.getOrder1("orderId");

        // Verify the results
    }

    @Test
    void testGetOrder1_DynamoDbTableReturnsNull() {
        // Setup
        when(mockOrdersTable.getItem(Key.builder()
                .partitionValue("orderId")
                .build())).thenReturn(null);

        // Run the test
        assertThrows(OrderStoreException.class, () -> myClassUnderTest.getOrder1("orderId"));
    }

    @Test
    void testGetOrder1_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockOrdersTable.getItem(Key.builder()
                .partitionValue("orderId")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(OrderStoreException.class, () -> myClassUnderTest.getOrder1("orderId"));
    }

    @Test
    void testGetOrder1_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockOrdersTable.getItem(Key.builder()
                .partitionValue("orderId")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(OrderStoreException.class, () -> myClassUnderTest.getOrder1("orderId"));
    }

    @Test
    void testGetOrders1() throws Exception {
        // Setup
        // Configure DynamoDbTable.scan(...).
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        order.setOtherId("otherId");
        order.setOtherData("otherData");
        final PageIterable<Order> pages = PageIterable.create(() -> List.of(Page.create(List.of(order))).iterator());
        when(mockOrdersTable.scan()).thenReturn(pages);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders1("orderId");

        // Verify the results
    }

    @Test
    void testGetOrders1_DynamoDbTableReturnsNoItems() throws Exception {
        // Setup
        when(mockOrdersTable.scan()).thenReturn(Collections::emptyIterator);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders1("orderId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders1_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockOrdersTable.scan()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(OrderStoreException.class, () -> myClassUnderTest.getOrders1("orderId"));
    }

    @Test
    void testGetOrders1_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockOrdersTable.scan()).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(OrderStoreException.class, () -> myClassUnderTest.getOrders1("orderId"));
    }

    @Test
    void testGetOrders2() throws Exception {
        // Setup
        // Configure DynamoDbTable.scan(...).
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        order.setOtherId("otherId");
        order.setOtherData("otherData");
        final PageIterable<Order> pages = PageIterable.create(() -> List.of(Page.create(List.of(order))).iterator());
        when(mockOrdersTable.scan()).thenReturn(pages);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders2("orderId");

        // Verify the results
        verify(mockMetricService).recordOrder("id");
    }

    @Test
    void testGetOrders2_DynamoDbTableReturnsNoItems() throws Exception {
        // Setup
        when(mockOrdersTable.scan()).thenReturn(Collections::emptyIterator);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders2("orderId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders2_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockOrdersTable.scan()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(OrderStoreException.class, () -> myClassUnderTest.getOrders2("orderId"));
    }

    @Test
    void testGetOrders2_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockOrdersTable.scan()).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(OrderStoreException.class, () -> myClassUnderTest.getOrders2("orderId"));
    }

    @Test
    void testGetOrders3() throws Exception {
        // Setup
        // Configure DynamoDbTable.scan(...).
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        order.setOtherId("otherId");
        order.setOtherData("otherData");
        final PageIterable<Order> pages = PageIterable.create(() -> List.of(Page.create(List.of(order))).iterator());
        when(mockOrdersTable.scan()).thenReturn(pages);

        when(mockOtherService.getOtherData("otherId")).thenReturn("otherData");

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders3("orderId");

        // Verify the results
        verify(mockMetricService).recordOrder("id");
    }

    @Test
    void testGetOrders3_DynamoDbTableReturnsNoItems() throws Exception {
        // Setup
        when(mockOrdersTable.scan()).thenReturn(Collections::emptyIterator);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders3("orderId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders3_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockOrdersTable.scan()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(OrderStoreException.class, () -> myClassUnderTest.getOrders3("orderId"));
    }

    @Test
    void testGetOrders3_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockOrdersTable.scan()).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(OrderStoreException.class, () -> myClassUnderTest.getOrders3("orderId"));
    }

    @Test
    void testGetOrders3_OtherServiceReturnsNull() throws Exception {
        // Setup
        // Configure DynamoDbTable.scan(...).
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        order.setOtherId("otherId");
        order.setOtherData("otherData");
        final PageIterable<Order> pages = PageIterable.create(() -> List.of(Page.create(List.of(order))).iterator());
        when(mockOrdersTable.scan()).thenReturn(pages);

        when(mockOtherService.getOtherData("otherId")).thenReturn(null);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders3("orderId");

        // Verify the results
        verify(mockMetricService).recordOrder("id");
    }

    @Test
    void testGetOrder2() throws Exception {
        // Setup
        // Configure DynamoDbTable.scan(...).
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        order.setOtherId("otherId");
        order.setOtherData("otherData");
        final PageIterable<Order> pages = PageIterable.create(() -> List.of(Page.create(List.of(order))).iterator());
        when(mockOrdersTable.scan()).thenReturn(pages);

        when(mockOtherService.getOtherData("otherId")).thenReturn("otherData");

        // Run the test
        final Order result = myClassUnderTest.getOrder2("orderId");

        // Verify the results
    }

    @Test
    void testGetOrder2_DynamoDbTableReturnsNoItems() throws Exception {
        // Setup
        when(mockOrdersTable.scan()).thenReturn(Collections::emptyIterator);

        // Run the test
        final Order result = myClassUnderTest.getOrder2("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordNoOrderFound("orderId");
    }

    @Test
    void testGetOrder2_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockOrdersTable.scan()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(OrderStoreException.class, () -> myClassUnderTest.getOrder2("orderId"));
    }

    @Test
    void testGetOrder2_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockOrdersTable.scan()).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(OrderStoreException.class, () -> myClassUnderTest.getOrder2("orderId"));
    }

    @Test
    void testGetOrder2_OtherServiceReturnsNull() throws Exception {
        // Setup
        // Configure DynamoDbTable.scan(...).
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        order.setOtherId("otherId");
        order.setOtherData("otherData");
        final PageIterable<Order> pages = PageIterable.create(() -> List.of(Page.create(List.of(order))).iterator());
        when(mockOrdersTable.scan()).thenReturn(pages);

        when(mockOtherService.getOtherData("otherId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder2("orderId");

        // Verify the results
        verify(mockMetricService).recordNoOrderFound("orderId");
    }
}
