package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    void testGetOrderDescription1() {
        assertEquals("description", myClassUnderTest.getOrderDescription1("orderId"));
    }

    @Test
    void testGetOrderDescription2() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription2("orderId");

        // Verify the results
        assertEquals("description", result);
    }

    @Test
    void testGetOrderDescription2_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription2("orderId");

        // Verify the results
        assertEquals("description", result);
    }

    @Test
    void testGetOrderDescription3() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription3("orderId");

        // Verify the results
        assertEquals("description", result);
    }

    @Test
    void testGetOrderDescription3_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription3("orderId");

        // Verify the results
        assertEquals("description", result);
    }

    @Test
    void testGetOrderDescription4() {
        assertEquals("description", myClassUnderTest.getOrderDescription4("orderId"));
    }

    @Test
    void testGetOrderDescription5() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderDescription5("orderId", 0);

        // Verify the results
    }

    @Test
    void testGetOrderDescription5_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrderDescription5("orderId", 0);

        // Verify the results
    }

    @Test
    void testGetOrderDescription6() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription6("orderId", 0);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderDescription6_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription6("orderId", 0);

        // Verify the results
        assertNull(result);
    }
}
