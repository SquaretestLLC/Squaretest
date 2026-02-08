package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private OrderService mockOrderService;
    @Mock
    private DefaultOrderService mockDefaultOrderService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockOrderService, mockDefaultOrderService, mockMetricService);
    }

    @Test
    void testGetOrderWithId2() {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Optional<Order> order = Optional.of(new Order("orderId", "description"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        // Run the test
        final Optional<Order> result = myClassUnderTest.getOrderWithId2("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId2_OrderServiceReturnsAbsent() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(Optional.empty());

        // Run the test
        final Optional<Order> result = myClassUnderTest.getOrderWithId2("orderId");

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetOrderWithId2_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrderWithId2("orderId"));
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
    }

    @Test
    void testGetOrderWithId2_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Configure DefaultOrderService.getDefaultOrder(...).
        final Optional<Order> order = Optional.of(new Order("orderId", "description"));
        when(mockDefaultOrderService.getDefaultOrder("orderId")).thenReturn(order);

        // Run the test
        final Optional<Order> result = myClassUnderTest.getOrderWithId2("orderId");

        // Verify the results
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
    }

    @Test
    void testGetOrderWithId2_DefaultOrderServiceReturnsAbsent() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);
        when(mockDefaultOrderService.getDefaultOrder("orderId")).thenReturn(Optional.empty());

        // Run the test
        final Optional<Order> result = myClassUnderTest.getOrderWithId2("orderId");

        // Verify the results
        assertEquals(Optional.empty(), result);
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
    }
}
