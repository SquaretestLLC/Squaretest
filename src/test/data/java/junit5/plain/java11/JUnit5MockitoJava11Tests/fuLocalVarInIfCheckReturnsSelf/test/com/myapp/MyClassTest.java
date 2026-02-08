package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNull;
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
    void testDoSomething() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));
        when(mockOrderService.create(any(Order.class))).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.doSomething(order);

        // Verify the results
        verify(mockMetricService).recordCreateSuccess("orderId");
    }

    @Test
    void testDoSomething_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.doSomething(order);

        // Verify the results
        assertNull(result);
    }
}
