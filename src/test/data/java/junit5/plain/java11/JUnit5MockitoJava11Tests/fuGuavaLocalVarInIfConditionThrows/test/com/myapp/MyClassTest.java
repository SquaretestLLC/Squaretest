package com.myapp;

import com.google.common.base.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void testGetOrder1() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Optional<Order> orderOptional = Optional.of(new Order("orderId", "description"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(orderOptional);

        // Run the test
        final Order result = myClassUnderTest.getOrder1("orderId");

        // Verify the results
        verify(mockMetricService).recordOrPresent("orderId");
        verify(mockMetricService).recordOutsideIfCheck("orderId");
    }

    @Test
    void testGetOrder1_OrderServiceReturnsAbsent() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(Optional.absent());

        // Run the test
        assertThrows(OrderNotFoundException.class, () -> myClassUnderTest.getOrder1("orderId"));
    }

    @Test
    void testGetOrder2() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Optional<Order> orderOptional = Optional.of(new Order("orderId", "description"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(orderOptional);

        // Run the test
        final Order result = myClassUnderTest.getOrder2("orderId");

        // Verify the results
        verify(mockMetricService).recordOrPresent("orderId");
    }

    @Test
    void testGetOrder2_OrderServiceReturnsAbsent() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(Optional.absent());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getOrder2("orderId"));
    }

    @Test
    void testGetOrder3() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Optional<Order> orderOptional = Optional.of(new Order("orderId", "description"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(orderOptional);

        // Run the test
        final Order result = myClassUnderTest.getOrder3("orderId");

        // Verify the results
        verify(mockMetricService).recordOrPresent("orderId");
    }

    @Test
    void testGetOrder3_OrderServiceReturnsAbsent() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(Optional.absent());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getOrder3("orderId"));
    }

    @Test
    void testGetOrder4() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Optional<Order> orderOptional = Optional.of(new Order("orderId", "description"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(orderOptional);

        // Run the test
        final Order result = myClassUnderTest.getOrder4("orderId");

        // Verify the results
        verify(mockMetricService).recordOrPresent("orderId");
    }

    @Test
    void testGetOrder4_OrderServiceReturnsAbsent() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(Optional.absent());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getOrder4("orderId"));
    }

    @Test
    void testGetOrder5() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Optional<Order> orderOptional = Optional.of(new Order("orderId", "description"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(orderOptional);

        // Run the test
        final Order result = myClassUnderTest.getOrder5("orderId");

        // Verify the results
        verify(mockMetricService).recordOrPresent("orderId");
    }

    @Test
    void testGetOrder5_OrderServiceReturnsAbsent() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(Optional.absent());

        // Run the test
        assertThrows(IllegalStateException.class, () -> myClassUnderTest.getOrder5("orderId"));
    }

    @Test
    void testGetOrder6() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Optional<Order> orderOptional = Optional.of(new Order("orderId", "description"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(orderOptional);

        // Run the test
        final Order result = myClassUnderTest.getOrder6("orderId");

        // Verify the results
        verify(mockMetricService).recordOrPresent("orderId");
    }

    @Test
    void testGetOrder6_OrderServiceReturnsAbsent() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(Optional.absent());

        // Run the test
        assertThrows(IllegalStateException.class, () -> myClassUnderTest.getOrder6("orderId"));
    }

    @Test
    void testGetOrder7() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Optional<Order> orderOptional = Optional.of(new Order("orderId", "description"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(orderOptional);

        // Run the test
        final Order result = myClassUnderTest.getOrder7("orderId");

        // Verify the results
        verify(mockMetricService).recordOrPresent("orderId");
    }

    @Test
    void testGetOrder7_OrderServiceReturnsAbsent() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(Optional.absent());

        // Run the test
        assertThrows(IllegalStateException.class, () -> myClassUnderTest.getOrder7("orderId"));
    }

    @Test
    void testGetOrder8() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Optional<Order> orderOptional = Optional.of(new Order("orderId", "description"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(orderOptional);

        // Run the test
        final Order result = myClassUnderTest.getOrder8("orderId");

        // Verify the results
        verify(mockMetricService).recordOrPresent("orderId");
    }

    @Test
    void testGetOrder8_OrderServiceReturnsAbsent() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(Optional.absent());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getOrder8("orderId"));
    }
}
