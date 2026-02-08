package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
    void testGetOrderWithId1() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId1("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId1_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(OrderAdapterException.class, () -> myClassUnderTest.getOrderWithId1("orderId"));
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
        verify(mockMetricService).recordNullOrder("orderId");
    }

    @Test
    void testGetOrderWithId1_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(OrderAdapterException.class, () -> myClassUnderTest.getOrderWithId1("orderId"));
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
        verify(mockMetricService).recordNullOrder("orderId");
    }

    @Test
    void testGetOrderWithId2() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId2("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId2_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(OrderAdapterException.class, () -> myClassUnderTest.getOrderWithId2("orderId"));
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
        verify(mockMetricService).recordNullOrder("orderId");
    }

    @Test
    void testGetOrderWithId2_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(OrderAdapterException.class, () -> myClassUnderTest.getOrderWithId2("orderId"));
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
        verify(mockMetricService).recordNullOrder("orderId");
    }

    @Test
    void testGetOrderWithId3() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId3("orderId", false);

        // Verify the results
    }

    @Test
    void testGetOrderWithId3_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId3("orderId", false);

        // Verify the results
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
        verify(mockMetricService).recordNullOrder("orderId");
        verify(mockMetricService).recordUsingDefaultOrder("orderId");
    }

    @Test
    void testGetOrderWithId3_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId3("orderId", false);

        // Verify the results
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
        verify(mockMetricService).recordNullOrder("orderId");
        verify(mockMetricService).recordUsingDefaultOrder("orderId");
    }

    @Test
    void testSafeGetOrderWithId1() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.safeGetOrderWithId1("orderId");

        // Verify the results
    }

    @Test
    void testSafeGetOrderWithId1_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        final Order result = myClassUnderTest.safeGetOrderWithId1("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
    }

    @Test
    void testSafeGetOrderWithId1_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        final Order result = myClassUnderTest.safeGetOrderWithId1("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
    }
}
