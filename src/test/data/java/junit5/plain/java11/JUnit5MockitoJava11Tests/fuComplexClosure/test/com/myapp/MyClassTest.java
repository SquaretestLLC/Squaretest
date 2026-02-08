package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private OrderService mockOrderService;
    @Mock
    private OrderDataService mockOrderDataService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockOrderService, mockOrderDataService, mockMetricService);
    }

    @Test
    void testGetOrderAsync1() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync1("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrderAsync1_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync1("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderAsync1_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync1("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrderAsync2() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync2("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrderAsync2_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync2("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderAsync2_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync2("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrderAsync3() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync3("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrderAsync3_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync3("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderAsync3_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync3("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrderAsync4() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync4("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrderAsync4_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync4("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderAsync4_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync4("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrderAsync5() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync5("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrderAsync5_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync5("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderAsync5_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync5("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderAsync6() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync6("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrderAsync6_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync6("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderAsync6_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync6("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderAsync7() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync7("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
        verify(mockMetricService).recordEndOfAsyncMethod("orderId");
    }

    @Test
    void testGetOrderAsync7_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync7("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfAsyncMethod("orderId");
    }

    @Test
    void testGetOrderAsync7_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync7("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
        verify(mockMetricService).recordEndOfAsyncMethod("orderId");
    }

    @Test
    void testGetOrderAsync8() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync8("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
        verify(mockMetricService).recordEndOfAsyncMethod("orderId");
    }

    @Test
    void testGetOrderAsync8_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync8("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfAsyncMethod("orderId");
    }

    @Test
    void testGetOrderAsync8_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync8("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
        verify(mockMetricService).recordEndOfAsyncMethod("orderId");
    }

    @Test
    void testGetOrderAsync9() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync9("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
        verify(mockMetricService).recordEndOfAsyncMethod("orderId");
    }

    @Test
    void testGetOrderAsync9_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync9("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfAsyncMethod("orderId");
    }

    @Test
    void testGetOrderAsync9_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync9("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
        verify(mockMetricService).recordEndOfAsyncMethod("orderId");
    }

    @Test
    void testGetOrderAsync10() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync10("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
        verify(mockMetricService).recordEndOfAsyncMethod("orderId");
    }

    @Test
    void testGetOrderAsync10_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync10("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfAsyncMethod("orderId");
    }

    @Test
    void testGetOrderAsync10_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync10("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
        verify(mockMetricService).recordEndOfAsyncMethod("orderId");
    }

    @Test
    void testGetOrderAsync11() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync11("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
        verify(mockMetricService).recordEndOfAsyncMethod("orderId");
    }

    @Test
    void testGetOrderAsync11_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync11("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfAsyncMethod("orderId");
    }

    @Test
    void testGetOrderAsync11_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync11("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfAsyncMethod("orderId");
    }

    @Test
    void testGetOrderAsync12() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync12("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
        verify(mockMetricService).recordEndOfAsyncMethod("orderId");
    }

    @Test
    void testGetOrderAsync12_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync12("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfAsyncMethod("orderId");
    }

    @Test
    void testGetOrderAsync12_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final CompletableFuture<Order> result = myClassUnderTest.getOrderAsync12("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfAsyncMethod("orderId");
    }

    @Test
    void testGetOrder1() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final Order result = myClassUnderTest.getOrder1("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder1_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder1("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrder1_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder1("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder2() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final Order result = myClassUnderTest.getOrder2("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder2_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final Order result = myClassUnderTest.getOrder2("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder2_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder2("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder3() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final Order result = myClassUnderTest.getOrder3("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder3_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder3("orderId");

        // Verify the results
    }

    @Test
    void testGetOrder3_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder3("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }
}
