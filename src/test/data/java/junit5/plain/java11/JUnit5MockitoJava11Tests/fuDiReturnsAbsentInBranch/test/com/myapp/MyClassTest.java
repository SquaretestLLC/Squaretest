package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private OrderService mockOrderService;
    @Mock
    private OrderService mockAltOrderService;
    @Mock
    private OrderDataService mockOrderDataService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockOrderService, mockAltOrderService, mockOrderDataService, mockMetricService);
    }

    @Test
    void testGetOrder1() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", "data");
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Configure OrderService.getOrderWithId1(...).
        final Order order1 = new Order("orderId", "description", "data");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order1);

        // Run the test
        final Order result = myClassUnderTest.getOrder1("orderId", false);

        // Verify the results
    }

    @Test
    void testGetOrder1_AltOrderServiceReturnsNull() {
        // Setup
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder1("orderId", false);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrder1_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder1("orderId", false);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrder2() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", "data");
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Configure OrderService.getOrderWithId1(...).
        final Order order1 = new Order("orderId", "description", "data");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order1);

        // Run the test
        final Order result = myClassUnderTest.getOrder2("orderId", false);

        // Verify the results
        verify(mockMetricService).recordNullOrder("orderId");
    }

    @Test
    void testGetOrder2_AltOrderServiceReturnsNull() {
        // Setup
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder2("orderId", false);

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordNullOrder("orderId");
    }

    @Test
    void testGetOrder2_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder2("orderId", false);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrder3() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", "data");
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Run the test
        final Order result = myClassUnderTest.getOrder3("orderId", false);

        // Verify the results
        verify(mockMetricService).recordNullOrder("orderId");
    }

    @Test
    void testGetOrder3_AltOrderServiceReturnsNull() {
        // Setup
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", "data");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Run the test
        final Order result = myClassUnderTest.getOrder3("orderId", false);

        // Verify the results
    }

    @Test
    void testGetOrder3_OrderServiceReturnsNull() {
        // Setup
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder3("orderId", false);

        // Verify the results
        assertNull(result);
    }
}
