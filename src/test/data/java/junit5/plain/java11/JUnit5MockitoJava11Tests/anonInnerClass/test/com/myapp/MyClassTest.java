package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private OrderService mockOrderService;
    @Mock
    private OrderService mockAltOrderService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockOrderService, mockAltOrderService, mockMetricService);
    }

    @Test
    void testTransferOrder1() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Run the test
        myClassUnderTest.transferOrder1("orderId");

        // Verify the results
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder1_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        myClassUnderTest.transferOrder1("orderId");

        // Verify the results
    }

    @Test
    void testTransferOrder2() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Run the test
        myClassUnderTest.transferOrder2("orderId");

        // Verify the results
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder2_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        myClassUnderTest.transferOrder2("orderId");

        // Verify the results
    }

    @Test
    void testTransferOrder3() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Run the test
        myClassUnderTest.transferOrder3("orderId");

        // Verify the results
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder3_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(OrderNotFoundException.class, () -> myClassUnderTest.transferOrder3("orderId"));
    }
}
