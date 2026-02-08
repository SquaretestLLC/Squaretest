package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

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
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), mockOrderService, mockMetricService);
    }

    @Test
    void testGetOrderWithId1() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Future<Order> result = myClassUnderTest.getOrderWithId1("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId1_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        final Future<Order> result = myClassUnderTest.getOrderWithId1("orderId");

        // Verify the results
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
    }

    @Test
    void testGetOrderWithId1_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        final Future<Order> result = myClassUnderTest.getOrderWithId1("orderId");

        // Verify the results
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
    }

    @Test
    void testGetOrderWithId2() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Future<Order> result = myClassUnderTest.getOrderWithId2("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId2_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        final Future<Order> result = myClassUnderTest.getOrderWithId2("orderId");

        // Verify the results
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
    }

    @Test
    void testGetOrderWithId2_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        final Future<Order> result = myClassUnderTest.getOrderWithId2("orderId");

        // Verify the results
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
    }

    @Test
    void testGetOrderWithId3() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Future<Order> result = myClassUnderTest.getOrderWithId3("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId3_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        final Future<Order> result = myClassUnderTest.getOrderWithId3("orderId");

        // Verify the results
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
    }

    @Test
    void testGetOrderWithId3_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        final Future<Order> result = myClassUnderTest.getOrderWithId3("orderId");

        // Verify the results
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
    }

    @Test
    void testGetOrderWithId4() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Future<Order> result = myClassUnderTest.getOrderWithId4("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId4_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        final Future<Order> result = myClassUnderTest.getOrderWithId4("orderId");

        // Verify the results
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
    }

    @Test
    void testGetOrderWithId4_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        final Future<Order> result = myClassUnderTest.getOrderWithId4("orderId");

        // Verify the results
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
    }

    @Test
    void testGetOrderCallable1() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Callable<Order> result = myClassUnderTest.getOrderCallable1("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderCallable1_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrderCallable1("orderId"));
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
    }

    @Test
    void testGetOrderCallable1_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrderCallable1("orderId"));
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
    }

    @Test
    void testGetOrderCallable2() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Callable<Order> result = myClassUnderTest.getOrderCallable2("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderCallable2_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrderCallable2("orderId"));
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
    }

    @Test
    void testGetOrderCallable2_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrderCallable2("orderId"));
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
    }

    @Test
    void testGetOrderCallable3() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Callable<Order> result = myClassUnderTest.getOrderCallable3("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderCallable3_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrderCallable3("orderId"));
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
    }

    @Test
    void testGetOrderCallable3_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrderCallable3("orderId"));
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
    }

    @Test
    void testGetOrderCallable4() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Callable<Order> result = myClassUnderTest.getOrderCallable4("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderCallable4_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrderCallable4("orderId"));
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
    }

    @Test
    void testGetOrderCallable4_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrderCallable4("orderId"));
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
    }
}
