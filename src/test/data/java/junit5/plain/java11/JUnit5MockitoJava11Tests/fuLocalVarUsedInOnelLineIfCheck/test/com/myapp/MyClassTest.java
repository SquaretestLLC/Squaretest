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
    void testTryCreate5_ThrowsOrderAlreadyExistsException() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        assertThrows(OrderAlreadyExistsException.class, () -> myClassUnderTest.tryCreate5(order));
    }

    @Test
    void testTryCreate5_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.create(any(Order.class))).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.tryCreate5(order);

        // Verify the results
        verify(mockMetricService).recordCreateSuccess("orderId");
    }

    @Test
    void testTryCreate6_ThrowsOrderAlreadyExistsException() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        assertThrows(OrderAlreadyExistsException.class, () -> myClassUnderTest.tryCreate6(order));
    }

    @Test
    void testTryCreate6_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.create(any(Order.class))).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.tryCreate6(order);

        // Verify the results
        verify(mockMetricService).recordCreateSuccess("orderId");
    }

    @Test
    void testTryCreate7_ThrowsOrderAlreadyExistsException() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        assertThrows(OrderAlreadyExistsException.class, () -> myClassUnderTest.tryCreate7(order));
    }

    @Test
    void testTryCreate7_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.create(any(Order.class))).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.tryCreate7(order);

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
    }

    @Test
    void testUpdateOrder5() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));
        when(mockOrderService.update(any(Order.class))).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.updateOrder5(order);

        // Verify the results
        verify(mockMetricService).recordUpdateSuccess("orderId");
    }

    @Test
    void testUpdateOrder5_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(OrderNotFoundException.class, () -> myClassUnderTest.updateOrder5(order));
    }

    @Test
    void testUpdateOrder6() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));
        when(mockOrderService.update(any(Order.class))).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.updateOrder6(order);

        // Verify the results
        verify(mockMetricService).recordUpdateSuccess("orderId");
    }

    @Test
    void testUpdateOrder6_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(OrderNotFoundException.class, () -> myClassUnderTest.updateOrder6(order));
    }

    @Test
    void testUpdateOrder7() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));
        when(mockOrderService.update(any(Order.class))).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.updateOrder7(order);

        // Verify the results
        verify(mockMetricService).recordUpdateSuccess("orderId");
    }

    @Test
    void testUpdateOrder7_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(OrderNotFoundException.class, () -> myClassUnderTest.updateOrder7(order));
    }

    @Test
    void testUpdateOrder8() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));
        when(mockOrderService.update(any(Order.class))).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.updateOrder8(order);

        // Verify the results
        verify(mockMetricService).recordUpdateSuccess("orderId");
    }

    @Test
    void testUpdateOrder8_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        final Order order = new Order("orderId", "description");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(OrderNotFoundException.class, () -> myClassUnderTest.updateOrder8(order));
    }

    @Test
    void testGetOrderOrDefault1() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderOrDefault1("orderId");

        // Verify the results
        verify(mockMetricService).recordFixOrderCalled("orderId");
    }

    @Test
    void testGetOrderOrDefault1_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrderOrDefault1("orderId");

        // Verify the results
        verify(mockMetricService).recordGetDefaultOrderCalled("orderId");
    }

    @Test
    void testGetOrderOrDefault2() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderOrDefault2("orderId");

        // Verify the results
        verify(mockMetricService).recordFixOrderCalled("orderId");
    }

    @Test
    void testGetOrderOrDefault2_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrderOrDefault2("orderId");

        // Verify the results
        verify(mockMetricService).recordGetDefaultOrderCalled("orderId");
    }

    @Test
    void testGetOrderOrDefault3() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderOrDefault3("orderId");

        // Verify the results
        verify(mockMetricService).recordFixOrderCalled("orderId");
    }

    @Test
    void testGetOrderOrDefault3_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrderOrDefault3("orderId");

        // Verify the results
        verify(mockMetricService).recordGetDefaultOrderCalled("orderId");
    }

    @Test
    void testGetOrderOrDefault4() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderOrDefault4("orderId");

        // Verify the results
        verify(mockMetricService).recordFixOrderCalled("orderId");
    }

    @Test
    void testGetOrderOrDefault4_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrderOrDefault4("orderId");

        // Verify the results
        verify(mockMetricService).recordGetDefaultOrderCalled("orderId");
    }
}
