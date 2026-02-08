package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private OrderService mockOrderService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockOrderService);
    }

    @Test
    void testOrderExists() {
        // Setup
        // Configure OrderService.getOrders1(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders1("orderId")).thenReturn(orders);

        // Run the test
        final boolean result = myClassUnderTest.orderExists("orderId");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testOrderExists_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrders1("orderId")).thenReturn(null);

        // Run the test
        final boolean result = myClassUnderTest.orderExists("orderId");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testOrderExists_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders1("orderId")).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = myClassUnderTest.orderExists("orderId");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testCanCreate() {
        // Setup
        // Configure OrderService.getOrders1(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders1("orderId")).thenReturn(orders);

        // Run the test
        final boolean result = myClassUnderTest.canCreate("orderId");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testCanCreate_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrders1("orderId")).thenReturn(null);

        // Run the test
        final boolean result = myClassUnderTest.canCreate("orderId");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testCanCreate_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders1("orderId")).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = myClassUnderTest.canCreate("orderId");

        // Verify the results
        assertTrue(result);
    }
}
