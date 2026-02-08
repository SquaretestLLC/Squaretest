package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    void testGetOrders1() {
        // Setup
        // Configure OrderService.getOrders1(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders1("searchCriteria");

        // Verify the results
    }

    @Test
    void testGetOrders1_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(null);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders1("searchCriteria");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrders1_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders1("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders2() {
        // Setup
        // Configure OrderService.getOrders1(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders2("searchCriteria");

        // Verify the results
    }

    @Test
    void testGetOrders2_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(null);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders2("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders2_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders2("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders3() {
        // Setup
        // Configure OrderService.getOrders1(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders3("searchCriteria");

        // Verify the results
    }

    @Test
    void testGetOrders3_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(null);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders3("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders3_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders3("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders4() {
        // Setup
        // Configure OrderService.getOrders1(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders4("searchCriteria");

        // Verify the results
    }

    @Test
    void testGetOrders4_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(null);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders4("searchCriteria");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrders4_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders4("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders5() {
        // Setup
        // Configure OrderService.getOrders1(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders5("searchCriteria");

        // Verify the results
        verify(mockMetricService).recordGetOrDefaultCalled();
    }

    @Test
    void testGetOrders5_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(null);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders5("searchCriteria");

        // Verify the results
        verify(mockMetricService).recordGetOrDefaultCalled();
    }

    @Test
    void testGetOrders5_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders5("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordGetOrDefaultCalled();
    }

    @Test
    void testGetOrders6() {
        // Setup
        // Configure OrderService.getOrders3(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders3("searchCriteria")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders6("searchCriteria");

        // Verify the results
        verify(mockMetricService).recordGetOrDefaultCalled();
    }

    @Test
    void testGetOrders6_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders3("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders6("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordGetOrDefaultCalled();
    }

    @Test
    void testGetOrders7() {
        // Setup
        // Configure OrderService.getOrders1(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders7("searchCriteria");

        // Verify the results
    }

    @Test
    void testGetOrders7_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(null);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders7("searchCriteria");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrders7_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders7("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders8() {
        // Setup
        // Configure OrderService.getOrders1(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders8("searchCriteria");

        // Verify the results
    }

    @Test
    void testGetOrders8_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(null);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders8("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders8_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders8("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders9() {
        // Setup
        // Configure OrderService.getOrders1(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders9("searchCriteria");

        // Verify the results
    }

    @Test
    void testGetOrders9_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(null);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders9("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders9_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders9("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders10() {
        // Setup
        // Configure OrderService.getOrders1(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders10("searchCriteria");

        // Verify the results
    }

    @Test
    void testGetOrders10_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(null);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders10("searchCriteria");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrders10_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders10("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders11() {
        // Setup
        // Configure OrderService.getOrders1(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders11("searchCriteria");

        // Verify the results
        verify(mockMetricService).recordGetOrDefaultCalled();
    }

    @Test
    void testGetOrders11_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(null);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders11("searchCriteria");

        // Verify the results
        verify(mockMetricService).recordGetOrDefaultCalled();
    }

    @Test
    void testGetOrders11_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders11("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordGetOrDefaultCalled();
    }

    @Test
    void testGetOrders12() {
        // Setup
        // Configure OrderService.getOrders2(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(orders);

        // Run the test
        final Order[] result = myClassUnderTest.getOrders12("searchCriteria");

        // Verify the results
    }

    @Test
    void testGetOrders12_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final Order[] result = myClassUnderTest.getOrders12("searchCriteria");

        // Verify the results
        assertArrayEquals(new Order[]{}, result);
    }

    @Test
    void testGetOrders13() {
        // Setup
        // Configure OrderService.getOrders2(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders13("searchCriteria");

        // Verify the results
        verify(mockMetricService).recordUpdatingDescription(any(Order.class));
    }

    @Test
    void testGetOrders13_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders13("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders14() {
        // Setup
        // Configure OrderService.getOrders2(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(orders);

        // Run the test
        final OrderData result = myClassUnderTest.getOrders14("searchCriteria");

        // Verify the results
    }

    @Test
    void testGetOrders14_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final OrderData result = myClassUnderTest.getOrders14("searchCriteria");

        // Verify the results
    }

    @Test
    void testGetOrders15() {
        // Setup
        // Configure OrderService.getOrders2(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(orders);

        // Run the test
        final List<String> result = myClassUnderTest.getOrders15("searchCriteria");

        // Verify the results
        assertEquals(List.of("value"), result);
    }

    @Test
    void testGetOrders15_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getOrders15("searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testCountOrders14() {
        // Setup
        // Configure OrderService.getOrders2(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(orders);

        // Run the test
        final int result = myClassUnderTest.countOrders14("searchCriteria");

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    void testCountOrders14_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final int result = myClassUnderTest.countOrders14("searchCriteria");

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    void testOrderExists1() {
        // Setup
        // Configure OrderService.getOrders1(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(orders);

        // Run the test
        final boolean result = myClassUnderTest.orderExists1("searchCriteria");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testOrderExists1_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(null);

        // Run the test
        final boolean result = myClassUnderTest.orderExists1("searchCriteria");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testOrderExists1_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = myClassUnderTest.orderExists1("searchCriteria");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testOrderExists2() {
        // Setup
        // Configure OrderService.getOrders1(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(orders);

        // Run the test
        final boolean result = myClassUnderTest.orderExists2("searchCriteria");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testOrderExists2_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(null);

        // Run the test
        final boolean result = myClassUnderTest.orderExists2("searchCriteria");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testOrderExists2_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = myClassUnderTest.orderExists2("searchCriteria");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testOrderExists3() {
        // Setup
        // Configure OrderService.getOrders2(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(orders);

        // Run the test
        final boolean result = myClassUnderTest.orderExists3("searchCriteria");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testOrderExists3_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = myClassUnderTest.orderExists3("searchCriteria");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testOrderExists4() {
        // Setup
        final Order orderToCheckFor = new Order("orderId", "description");

        // Configure OrderService.getOrders2(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(orders);

        // Run the test
        final boolean result = myClassUnderTest.orderExists4("searchCriteria", orderToCheckFor);

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testOrderExists4_OrderServiceReturnsNoItems() {
        // Setup
        final Order orderToCheckFor = new Order("orderId", "description");
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = myClassUnderTest.orderExists4("searchCriteria", orderToCheckFor);

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testOrderExists5() {
        // Setup
        // Configure OrderService.getOrders2(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(orders);

        // Run the test
        final boolean result = myClassUnderTest.orderExists5("searchCriteria");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testOrderExists5_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = myClassUnderTest.orderExists5("searchCriteria");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testOrderExists6() {
        // Setup
        // Configure OrderService.getOrders2(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(orders);

        // Run the test
        final boolean result = myClassUnderTest.orderExists6("searchCriteria");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testOrderExists6_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = myClassUnderTest.orderExists6("searchCriteria");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testOrderExists7() {
        // Setup
        // Configure OrderService.getOrders2(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(orders);

        // Run the test
        final boolean result = myClassUnderTest.orderExists7("searchCriteria");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testOrderExists7_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = myClassUnderTest.orderExists7("searchCriteria");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testOrderExists8() {
        // Setup
        // Configure OrderService.getOrders2(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(orders);

        // Run the test
        final boolean result = myClassUnderTest.orderExists8("searchCriteria");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testOrderExists8_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = myClassUnderTest.orderExists8("searchCriteria");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testCanCreate1() {
        // Setup
        // Configure OrderService.getOrders2(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(orders);

        // Run the test
        final boolean result = myClassUnderTest.canCreate1("searchCriteria");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testCanCreate1_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = myClassUnderTest.canCreate1("searchCriteria");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testCanCreate2() {
        // Setup
        final Order orderToCheckFor = new Order("orderId", "description");

        // Configure OrderService.getOrders2(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(orders);

        // Run the test
        final boolean result = myClassUnderTest.canCreate2("searchCriteria", orderToCheckFor);

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testCanCreate2_OrderServiceReturnsNoItems() {
        // Setup
        final Order orderToCheckFor = new Order("orderId", "description");
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = myClassUnderTest.canCreate2("searchCriteria", orderToCheckFor);

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testCanCreate3() {
        // Setup
        // Configure OrderService.getOrders2(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(orders);

        // Run the test
        final boolean result = myClassUnderTest.canCreate3("searchCriteria");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testCanCreate3_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrders2("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = myClassUnderTest.canCreate3("searchCriteria");

        // Verify the results
        assertTrue(result);
    }
}
