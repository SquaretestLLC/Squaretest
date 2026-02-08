package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private OrderService mockPrimaryOrderService;
    @Mock
    private OrderService mockAlternateOrderService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockPrimaryOrderService, mockAlternateOrderService, mockMetricService);
    }

    @Test
    void testGetOrderWithId2() throws Exception {
        // Setup
        when(mockPrimaryOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId2("orderId");

        // Verify the results
        verify(mockMetricService).recordInnerTryMessage("orderId");
    }

    @Test
    void testGetOrderWithId2_OrderServiceThrowsNetworkException() throws Exception {
        // Setup
        when(mockPrimaryOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId2("orderId");

        // Verify the results
        verify(mockMetricService).recordInnerTryMessage("orderId");
    }

    @Test
    void testGetOrderWithId2_OrderServiceThrowsServiceException() {
        // Setup
        when(mockPrimaryOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrderWithId2("orderId"));
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
        verify(mockMetricService).recordInnerTryMessage("orderId");
    }

    @Test
    void testGetOrderWithId3() throws Exception {
        // Setup
        when(mockPrimaryOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId3("orderId");

        // Verify the results
        verify(mockMetricService).recordInnerTryMessage("orderId");
    }

    @Test
    void testGetOrderWithId3_PrimaryOrderServiceThrowsNetworkException() throws Exception {
        // Setup
        when(mockPrimaryOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);
        when(mockAlternateOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId3("orderId");

        // Verify the results
        verify(mockMetricService).recordInnerTryMessage("orderId");
    }

    @Test
    void testGetOrderWithId3_PrimaryOrderServiceThrowsServiceException() {
        // Setup
        when(mockPrimaryOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrderWithId3("orderId"));
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
        verify(mockMetricService).recordInnerTryMessage("orderId");
    }

    @Test
    void testGetOrderWithId3_AlternateOrderServiceThrowsNetworkException() {
        // Setup
        when(mockPrimaryOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);
        when(mockAlternateOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrderWithId3("orderId"));
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
        verify(mockMetricService).recordInnerTryMessage("orderId");
    }

    @Test
    void testGetOrderWithId3_AlternateOrderServiceThrowsServiceException() {
        // Setup
        when(mockPrimaryOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);
        when(mockAlternateOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrderWithId3("orderId"));
        verify(mockMetricService).recordInnerTryMessage("orderId");
    }

    @Test
    void testGetOrderWithId4() throws Exception {
        // Setup
        when(mockPrimaryOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));
        when(mockAlternateOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId4("orderId");

        // Verify the results
        verify(mockMetricService).recordInnerTryMessage("orderId");
    }

    @Test
    void testGetOrderWithId4_PrimaryOrderServiceThrowsNetworkException() {
        // Setup
        when(mockPrimaryOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);
        when(mockAlternateOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrderWithId4("orderId"));
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
        verify(mockMetricService).recordInnerTryMessage("orderId");
    }

    @Test
    void testGetOrderWithId4_PrimaryOrderServiceThrowsServiceException() {
        // Setup
        when(mockPrimaryOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);
        when(mockAlternateOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrderWithId4("orderId"));
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
        verify(mockMetricService).recordInnerTryMessage("orderId");
    }

    @Test
    void testGetOrderWithId4_AlternateOrderServiceThrowsNetworkException() {
        // Setup
        when(mockPrimaryOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));
        when(mockAlternateOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrderWithId4("orderId"));
        verify(mockMetricService).recordOrderNetworkExceptionInFinally(any(NetworkException.class));
    }

    @Test
    void testGetOrderWithId4_AlternateOrderServiceThrowsServiceException() {
        // Setup
        when(mockPrimaryOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));
        when(mockAlternateOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrderWithId4("orderId"));
    }
}
