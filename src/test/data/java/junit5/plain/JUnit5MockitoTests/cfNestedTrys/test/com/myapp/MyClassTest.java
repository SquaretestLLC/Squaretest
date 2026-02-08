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
    void testGetOrderWithId2() throws Exception {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId2("orderId");

        // Verify the results
        verify(mockMetricService).recordInnerTryMessage("orderId");
        verify(mockMetricService).recordOuterTryMessage("orderId");
    }

    @Test
    void testGetOrderWithId2_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(OrderServiceException.class, () -> myClassUnderTest.getOrderWithId2("orderId"));
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
        verify(mockMetricService).recordInnerTryMessage("orderId");
        verify(mockMetricService).recordOuterTryMessage("orderId");
    }

    @Test
    void testGetOrderWithId2_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(OrderServiceException.class, () -> myClassUnderTest.getOrderWithId2("orderId"));
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
        verify(mockMetricService).recordInnerTryMessage("orderId");
        verify(mockMetricService).recordOuterTryMessage("orderId");
    }

    @Test
    void testGetOrderWithId3() throws Exception {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId3("orderId");

        // Verify the results
        verify(mockMetricService).recordInnerTryMessage("orderId");
        verify(mockMetricService).recordOuterTryMessage("orderId");
    }

    @Test
    void testGetOrderWithId3_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(OrderServiceException.class, () -> myClassUnderTest.getOrderWithId3("orderId"));
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
        verify(mockMetricService).recordInnerTryMessage("orderId");
        verify(mockMetricService).recordOuterTryMessage("orderId");
    }

    @Test
    void testGetOrderWithId3_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrderWithId3("orderId"));
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
        verify(mockMetricService).recordInnerTryMessage("orderId");
        verify(mockMetricService).recordOuterTryMessage("orderId");
    }

    @Test
    void testSafeGetOrderWithId2() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.safeGetOrderWithId2("orderId");

        // Verify the results
        verify(mockMetricService).recordInnerTryMessage("orderId");
        verify(mockMetricService).recordOuterTryMessage("orderId");
    }

    @Test
    void testSafeGetOrderWithId2_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        final Order result = myClassUnderTest.safeGetOrderWithId2("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
        verify(mockMetricService).recordInnerTryMessage("orderId");
        verify(mockMetricService).recordOuterTryMessage("orderId");
    }

    @Test
    void testSafeGetOrderWithId2_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        final Order result = myClassUnderTest.safeGetOrderWithId2("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
        verify(mockMetricService).recordInnerTryMessage("orderId");
        verify(mockMetricService).recordOuterTryMessage("orderId");
    }

    @Test
    void testSafeGetOrderWithId3() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.safeGetOrderWithId3("orderId");

        // Verify the results
        verify(mockMetricService).recordInnerTryMessage("orderId");
        verify(mockMetricService).recordOuterTryMessage("orderId");
    }

    @Test
    void testSafeGetOrderWithId3_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        final Order result = myClassUnderTest.safeGetOrderWithId3("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
        verify(mockMetricService).recordInnerTryMessage("orderId");
        verify(mockMetricService).recordOuterTryMessage("orderId");
    }

    @Test
    void testSafeGetOrderWithId3_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        final Order result = myClassUnderTest.safeGetOrderWithId3("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
        verify(mockMetricService).recordInnerTryMessage("orderId");
        verify(mockMetricService).recordOuterTryMessage("orderId");
    }

    @Test
    void testSafeGetOrderWithId4() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.safeGetOrderWithId4("orderId");

        // Verify the results
        verify(mockMetricService).recordInnerTryMessage("orderId");
        verify(mockMetricService).recordOuterTryMessage("orderId");
    }

    @Test
    void testSafeGetOrderWithId4_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        final Order result = myClassUnderTest.safeGetOrderWithId4("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
        verify(mockMetricService).recordInnerTryMessage("orderId");
        verify(mockMetricService).recordOuterTryMessage("orderId");
    }

    @Test
    void testSafeGetOrderWithId4_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        final Order result = myClassUnderTest.safeGetOrderWithId4("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
        verify(mockMetricService).recordInnerTryMessage("orderId");
        verify(mockMetricService).recordOuterTryMessage("orderId");
    }
}
