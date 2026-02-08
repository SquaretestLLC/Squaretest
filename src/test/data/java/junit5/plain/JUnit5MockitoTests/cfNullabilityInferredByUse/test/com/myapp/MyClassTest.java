package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
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
    void testGetOrderDescription1_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription1("orderId");

        // Verify the results
        assertNull(result);
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
    void testGetOrderDescription2_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription2("orderId");

        // Verify the results
        assertNull(result);
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
    void testGetOrderDescription3_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription3("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrderDescription3_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrderDescription3("orderId"));
    }

    @Test
    void testGetOrderDescription3_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrderDescription3("orderId"));
    }

    @Test
    void testGetOrderDescription4() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription4("orderId");

        // Verify the results
        assertEquals("description", result);
    }

    @Test
    void testGetOrderDescription4_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenThrow(NetworkException.class);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription4("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrderDescription4_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenThrow(ServiceException.class);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription4("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrderDescription5() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription5("orderId");

        // Verify the results
        assertEquals("description", result);
    }

    @Test
    void testGetOrderDescription5_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription5("orderId");

        // Verify the results
        assertEquals("description", result);
    }

    @Test
    void testGetOrderDescription5_OrderServiceGetOrderWithId1ThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrderDescription5("orderId"));
    }

    @Test
    void testGetOrderDescription5_OrderServiceGetOrderWithId1ThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrderDescription5("orderId"));
    }

    @Test
    void testGetOrderDescription5_OrderServiceGetOrderWithId2ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription5("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrderDescription5_OrderServiceGetOrderWithId2ThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrderDescription5("orderId"));
    }

    @Test
    void testGetOrderDescription5_OrderServiceGetOrderWithId2ThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrderDescription5("orderId"));
    }

    @Test
    void testGetOrderDescription6() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription6("orderId");

        // Verify the results
        assertEquals("description", result);
    }

    @Test
    void testGetOrderDescription6_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getOrderDescription6("orderId"));
    }

    @Test
    void testGetOrderDescription6_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrderDescription6("orderId"));
    }

    @Test
    void testGetOrderDescription6_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrderDescription6("orderId"));
    }

    @Test
    void testGetOrder1() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrder1("orderId");

        // Verify the results
    }

    @Test
    void testGetOrder1_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getOrder1("orderId"));
    }

    @Test
    void testGetOrder1_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrder1("orderId"));
    }

    @Test
    void testGetOrder1_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrder1("orderId"));
    }

    @Test
    void testGetOrder2() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrder2("orderId");

        // Verify the results
    }

    @Test
    void testGetOrder2_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getOrder2("orderId"));
    }

    @Test
    void testGetOrder2_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrder2("orderId"));
    }

    @Test
    void testGetOrder2_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrder2("orderId"));
    }
}
