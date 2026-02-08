package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private OrderService mockOrderService;
    @Mock
    private OrderService mockAltOrderService;
    @Mock
    private OrderDataService mockOrderDataService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockOrderService, mockAltOrderService, mockOrderDataService, mockMetricService);
    }

    @Test
    void testGetOrder1() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", "data");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("orderId")).thenReturn("data");

        // Run the test
        final Order result = myClassUnderTest.getOrder1("orderId", false);

        // Verify the results
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
        verify(mockMetricService).recordIncludeFullOrder("orderId");
    }

    @Test
    void testGetOrder1_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder1("orderId", false);

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
        verify(mockMetricService).recordNullOrder("orderId");
    }

    @Test
    void testGetOrder1_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", "data");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder1("orderId", false);

        // Verify the results
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
        verify(mockMetricService).recordIncludeFullOrder("orderId");
    }

    @Test
    void testGetOrder2() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", "data");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("orderId")).thenReturn("data");

        // Run the test
        final Order result = myClassUnderTest.getOrder2("orderId", false);

        // Verify the results
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
        verify(mockMetricService).recordIncludeFullOrder("orderId");
    }

    @Test
    void testGetOrder2_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder2("orderId", false);

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
    }

    @Test
    void testGetOrder2_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", "data");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder2("orderId", false);

        // Verify the results
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
        verify(mockMetricService).recordIncludeFullOrder("orderId");
    }

    @Test
    void testGetOrder3() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", "data");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("orderId")).thenReturn("data");

        // Run the test
        final Order result = myClassUnderTest.getOrder3("orderId", false);

        // Verify the results
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
        verify(mockMetricService).recordNullOrder("orderId");
        verify(mockMetricService).recordIncludeFullOrder("orderId");
    }

    @Test
    void testGetOrder3_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderDataService.getOrderData("orderId")).thenReturn("data");

        // Run the test
        final Order result = myClassUnderTest.getOrder3("orderId", false);

        // Verify the results
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
        verify(mockMetricService).recordNullOrder("orderId");
        verify(mockMetricService).recordIncludeFullOrder("orderId");
    }

    @Test
    void testGetOrder3_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", "data");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder3("orderId", false);

        // Verify the results
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
        verify(mockMetricService).recordIncludeFullOrder("orderId");
    }

    @Test
    void testGetOrder4() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", "data");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("orderId")).thenReturn("data");

        // Run the test
        final Order result = myClassUnderTest.getOrder4("orderId", false);

        // Verify the results
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
        verify(mockMetricService).recordOrderIdEndsWithFu("orderId");
        verify(mockMetricService).recordIncludeFullOrder("orderId");
    }

    @Test
    void testGetOrder4_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder4("orderId", false);

        // Verify the results
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
        verify(mockMetricService).recordOrderIdEndsWithFu("orderId");
        verify(mockMetricService).recordNullOrder("orderId");
    }

    @Test
    void testGetOrder4_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", "data");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder4("orderId", false);

        // Verify the results
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
        verify(mockMetricService).recordIncludeFullOrder("orderId");
    }

    @Test
    void testGetOrder5() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", "data");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("orderId")).thenReturn("data");

        // Run the test
        final Order result = myClassUnderTest.getOrder5("orderId", false);

        // Verify the results
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
    }

    @Test
    void testGetOrder5_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", "data");
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("orderId")).thenReturn("data");

        // Run the test
        final Order result = myClassUnderTest.getOrder5("orderId", false);

        // Verify the results
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
        verify(mockMetricService).recordFirstCallNull("orderId");
    }

    @Test
    void testGetOrder5_AltOrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder5("orderId", false);

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
        verify(mockMetricService).recordFirstCallNull("orderId");
    }

    @Test
    void testGetOrder5_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", "data");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder5("orderId", false);

        // Verify the results
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
    }

    @Test
    void testGetOrder6() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", "data");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("orderId")).thenReturn("data");

        // Run the test
        final Order result = myClassUnderTest.getOrder6("orderId", false);

        // Verify the results
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
    }

    @Test
    void testGetOrder6_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", "data");
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("orderId")).thenReturn("data");

        // Run the test
        final Order result = myClassUnderTest.getOrder6("orderId", false);

        // Verify the results
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
    }

    @Test
    void testGetOrder6_AltOrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder6("orderId", false);

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
        verify(mockMetricService).recordFirstCallNull("orderId");
    }

    @Test
    void testGetOrder6_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", "data");
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder6("orderId", false);

        // Verify the results
        verify(mockMetricService).recordFirstGetOrderCallSuccess("orderId");
    }
}
