package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription1("orderId");

        // Verify the results
        assertEquals("description", result);
    }

    @Test
    void testGetOrderDescription1_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrderDescription1("orderId"));
    }

    @Test
    void testGetOrderDescription1_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrderDescription1("orderId"));
    }

    @Test
    void testGetOrderDescription2() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription2("orderId");

        // Verify the results
        assertEquals("description", result);
    }

    @Test
    void testGetOrderDescription2_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrderDescription2("orderId"));
    }

    @Test
    void testGetOrderDescription2_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrderDescription2("orderId"));
    }

    @Test
    void testGetOrderDescription3() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription3("orderId");

        // Verify the results
        assertEquals("description", result);
    }

    @Test
    void testGetOrderDescription3_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenThrow(NetworkException.class);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription3("orderId");

        // Verify the results
        assertEquals("description", result);
    }

    @Test
    void testGetOrderDescription3_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenThrow(ServiceException.class);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription3("orderId");

        // Verify the results
        assertEquals("description", result);
    }

    @Test
    void testGetOrderDescription4() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription4("orderId");

        // Verify the results
        assertEquals("description", result);
    }

    @Test
    void testGetOrderDescription4_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription4("orderId");

        // Verify the results
        assertEquals("description", result);
    }

    @Test
    void testGetOrderDescription5() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription5("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderDescription5_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription5("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderDescription6() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription6("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderDescription6_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription6("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderDescription7() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription7("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderDescription7_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription7("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderDescription8() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription8("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderDescription8_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription8("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderDescription9() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription9("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderDescription10() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription10("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderDescription10_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription10("orderId");

        // Verify the results
        assertEquals("result", result);
    }
}
