package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private OrderService mockOrderService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockOrderService, mockMetricService);
    }

    @Test
    void testGetOrders1() {
        // Setup
        // Configure OrderService.getOrders1(...).
        final List<Order> orders = List.of(new Order("orderId", "description", List.of()));
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders1("searchCriteria");

        // Verify the results
    }

    @Test
    void testGetOrders1_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders1("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordGetDefaultOrdersCalled("searchCriteria");
    }

    @Test
    void testGetChildOrders1() {
        // Setup
        // Configure OrderService.getOrders1(...).
        final List<Order> orders = List.of(new Order("orderId", "description", List.of()));
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.getChildOrders1("searchCriteria");

        // Verify the results
    }

    @Test
    void testGetChildOrders1_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.getChildOrders1("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFirstChildOrder1() {
        // Setup
        // Configure OrderService.getOrder1(...).
        final Order order = new Order("orderId", "description", List.of());
        when(mockOrderService.getOrder1("orderId")).thenReturn(order);

        // Run the test
        final Order result = myClassUnderTest.getFirstChildOrder("orderId");

        // Verify the results
    }

    @Test
    void testGetFirstChildOrder1_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrder1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getFirstChildOrder("orderId");

        // Verify the results
        assertNull(result);
    }
}
