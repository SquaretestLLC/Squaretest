package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    void testGetOrderWithId1() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderWithId1("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderWithId1_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderWithId1("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrderWithId2() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderWithId2("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderWithId2_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderWithId2("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrderWithId3() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderWithId3("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderWithId3_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderWithId3("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrderWithId4() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderWithId4("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderWithId4_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderWithId4("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrderWithId5() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderWithId5("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderWithId5_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderWithId5("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrderWithId6() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderWithId6("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordOrderPresent("orderId");
    }

    @Test
    void testGetOrderWithId6_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderWithId6("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrderWithId7() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderWithId7("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordOrderPresent("orderId");
    }

    @Test
    void testGetOrderWithId7_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderWithId7("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrderWithId8() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderWithId8("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordOrderPresent("orderId");
    }

    @Test
    void testGetOrderWithId8_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderWithId8("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrderWithId9() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderWithId9("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordOrderPresent("orderId");
    }

    @Test
    void testGetOrderWithId9_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderWithId9("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrderWithId10() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderWithId10("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordOrderPresent("orderId");
    }

    @Test
    void testGetOrderWithId10_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderWithId10("orderId");

        // Verify the results
        assertNull(result);
    }
}
