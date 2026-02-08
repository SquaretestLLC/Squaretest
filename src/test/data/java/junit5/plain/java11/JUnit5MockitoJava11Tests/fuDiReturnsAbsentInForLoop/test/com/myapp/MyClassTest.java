package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private OrderService mockOrderService;
    @Mock
    private OrderDataService mockOrderDataService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockOrderService, mockOrderDataService, mockMetricService);
    }

    @Test
    void testGetOrder1() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final Order result = myClassUnderTest.getOrder1("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder1_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder1("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrder1_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder1("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder2() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final Order result = myClassUnderTest.getOrder2("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder2_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder2("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrder2_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder2("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrder3() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final Order result = myClassUnderTest.getOrder3("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder3_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder3("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrder3_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder3("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder4() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final Order result = myClassUnderTest.getOrder4("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder4_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder4("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrder4_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        assertThrows(OrderDataIntegrityException.class, () -> myClassUnderTest.getOrder4("orderId"));
    }

    @Test
    void testGetOrder5() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final Order result = myClassUnderTest.getOrder5("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder5_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder5("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrder5_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        assertThrows(OrderDataIntegrityException.class, () -> myClassUnderTest.getOrder5("orderId"));
    }

    @Test
    void testGetOrder6() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final Order result = myClassUnderTest.getOrder6("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder6_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder6("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrder6_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        assertThrows(OrderDataIntegrityException.class, () -> myClassUnderTest.getOrder6("orderId"));
    }

    @Test
    void testGetOrder7() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final Order result = myClassUnderTest.getOrder7("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder7_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder7("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrder7_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder7("orderId");

        // Verify the results
        verify(mockMetricService).recordFUOrderWithoutData("orderId");
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder8() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final Order result = myClassUnderTest.getOrder8("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder8_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder8("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrder8_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder8("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder9() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn("result");

        // Run the test
        final Order result = myClassUnderTest.getOrder9("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }

    @Test
    void testGetOrder9_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder9("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrder9_OrderDataServiceReturnsNull() {
        // Setup
        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", List.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockOrderDataService.getOrderData("dataId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrder9("orderId");

        // Verify the results
        verify(mockMetricService).recordEndOfGetOrderMethod("orderId");
    }
}
