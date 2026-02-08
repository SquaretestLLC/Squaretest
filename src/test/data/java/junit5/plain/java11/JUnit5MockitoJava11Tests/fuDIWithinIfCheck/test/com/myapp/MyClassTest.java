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
    void testTryCreate1_ThrowsOrderAlreadyExistsException() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        assertThrows(OrderAlreadyExistsException.class, () -> myClassUnderTest.tryCreate1(order));
        verify(mockMetricService).recordCreateFailed("orderId");
    }

    @Test
    void testTryCreate1_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.create(any(Order.class))).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.tryCreate1(order);

        // Verify the results
        verify(mockMetricService).recordCreateSuccess("orderId");
    }

    @Test
    void testTryCreate2_ThrowsOrderAlreadyExistsException() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        assertThrows(OrderAlreadyExistsException.class, () -> myClassUnderTest.tryCreate2(order));
        verify(mockMetricService).recordCreateFailed("orderId");
    }

    @Test
    void testTryCreate2_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.create(any(Order.class))).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.tryCreate2(order);

        // Verify the results
        verify(mockMetricService).recordCreateSuccess("orderId");
    }

    @Test
    void testTryCreate3_ThrowsOrderAlreadyExistsException() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        assertThrows(OrderAlreadyExistsException.class, () -> myClassUnderTest.tryCreate3(order));
        verify(mockMetricService).recordCreateFailed("orderId");
    }

    @Test
    void testTryCreate3_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.create(any(Order.class))).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.tryCreate3(order);

        // Verify the results
        verify(mockMetricService).recordCreateSuccess("orderId");
    }

    @Test
    void testTryCreate4_ThrowsOrderAlreadyExistsException() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        assertThrows(OrderAlreadyExistsException.class, () -> myClassUnderTest.tryCreate4(order));
        verify(mockMetricService).recordCreateFailed("orderId");
    }

    @Test
    void testTryCreate4_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.create(any(Order.class))).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.tryCreate4(order);

        // Verify the results
        verify(mockMetricService).recordCreateSuccess("orderId");
    }

    @Test
    void testUpdateOrder1() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));
        when(mockOrderService.update(any(Order.class))).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.updateOrder1(order);

        // Verify the results
        verify(mockMetricService).recordUpdateSuccess("orderId");
    }

    @Test
    void testUpdateOrder1_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(OrderNotFoundException.class, () -> myClassUnderTest.updateOrder1(order));
        verify(mockMetricService).recordUpdateFail("orderId");
    }

    @Test
    void testUpdateOrder2() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));
        when(mockOrderService.update(any(Order.class))).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.updateOrder2(order);

        // Verify the results
        verify(mockMetricService).recordUpdateSuccess("orderId");
    }

    @Test
    void testUpdateOrder2_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(OrderNotFoundException.class, () -> myClassUnderTest.updateOrder2(order));
        verify(mockMetricService).recordUpdateFail("orderId");
    }

    @Test
    void testUpdateOrder3() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));
        when(mockOrderService.update(any(Order.class))).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.updateOrder3(order);

        // Verify the results
        verify(mockMetricService).recordUpdateSuccess("orderId");
    }

    @Test
    void testUpdateOrder3_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(OrderNotFoundException.class, () -> myClassUnderTest.updateOrder3(order));
        verify(mockMetricService).recordUpdateFail("orderId");
    }

    @Test
    void testUpdateOrder4() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));
        when(mockOrderService.update(any(Order.class))).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.updateOrder4(order);

        // Verify the results
        verify(mockMetricService).recordUpdateSuccess("orderId");
    }

    @Test
    void testUpdateOrder4_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(OrderNotFoundException.class, () -> myClassUnderTest.updateOrder4(order));
        verify(mockMetricService).recordUpdateFail("orderId");
    }
}
