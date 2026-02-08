package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.NoSuchElementException;

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
    void testGetOrderWithId1() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId1("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId1_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId1("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrderWithId2() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId2("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId2_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getOrderWithId2("orderId"));
    }

    @Test
    void testGetOrderWithId3() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId3("orderId");

        // Verify the results
        verify(mockMetricService).recordOrderWithIdPresentCase("orderId");
    }

    @Test
    void testGetOrderWithId3_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getOrderWithId3("orderId"));
    }

    @Test
    void testGetOrderWithId4() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId4("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId4_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId4("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId5() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId5("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId5_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId5("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId6() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId6("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId6_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId6("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId7() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId7("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId7_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getOrderWithId7("orderId"));
    }

    @Test
    void testGetOrderWithId8() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId8("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId8_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId8("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId9() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final OrderData result = myClassUnderTest.getOrderWithId9("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId9_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final OrderData result = myClassUnderTest.getOrderWithId9("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId10() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final List<Order> result = myClassUnderTest.getOrderWithId10("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId10_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrderWithId10("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId11() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId11("orderId");

        // Verify the results
        verify(mockMetricService).recordOrderWithIdPresentCase("orderId");
    }

    @Test
    void testGetOrderWithId11_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId11("orderId");

        // Verify the results
        verify(mockMetricService).recordOrderWithIdPresentCase("orderId");
    }

    @Test
    void testGetOrderWithId11_OrderServiceGetOrderWithId2ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getOrderWithId11("orderId"));
    }

    @Test
    void testGetOrderWithId12() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId12("orderId");

        // Verify the results
        verify(mockMetricService).recordOrderWithIdPresentCase("orderId");
    }

    @Test
    void testGetOrderWithId12_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId12("orderId");

        // Verify the results
        verify(mockMetricService).recordOrderWithIdPresentCase("orderId");
    }

    @Test
    void testGetOrderWithId12_OrderServiceGetOrderWithId2ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getOrderWithId12("orderId"));
    }

    @Test
    void testGetOrderWithId13() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId13("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId13_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId13("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId13_OrderServiceGetOrderWithId2ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId13("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId14() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId14("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId14_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId14("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId14_OrderServiceGetOrderWithId2ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId14("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId15() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId15("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId15_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId15("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId15_OrderServiceGetOrderWithId2ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId15("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId16() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId16("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId16_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId16("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId16_OrderServiceGetOrderWithId2ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getOrderWithId16("orderId"));
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId17() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId17("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId17_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId17("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId17_OrderServiceGetOrderWithId2ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId17("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId18() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId18("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId18_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId18("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
        verify(mockMetricService).recordCallingGetOrderHelper2("orderId");
    }

    @Test
    void testGetOrderWithId18_OrderServiceGetOrderWithId2ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId18("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
        verify(mockMetricService).recordCallingGetOrderHelper2("orderId");
    }

    @Test
    void testGetOrderWithId19() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId19("orderId");

        // Verify the results
        verify(mockMetricService).recordOrderWithIdPresentCase("orderId");
    }

    @Test
    void testGetOrderWithId19_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getOrderWithId19("orderId"));
        verify(mockMetricService).recordOrElseThrowBlock("orderId");
    }

    @Test
    void testGetOrderWithId20() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId20("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderWithId20_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId20("orderId");

        // Verify the results
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
        verify(mockMetricService).recordCallingGetOrderHelper3("orderId");
    }

    @Test
    void testGetOrderWithId20_OrderServiceGetOrderWithId2ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId20("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
        verify(mockMetricService).recordCallingGetOrderHelper3("orderId");
    }

    @Test
    void testGetOrderWithId21() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId21("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId21_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId21("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId21_OrderServiceGetOrderWithId2ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId21("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrderWithId22() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId22("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderWithId22_OrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId22("orderId");

        // Verify the results
        verify(mockMetricService).recordCallingGetOrderHelper2("orderId");
    }

    @Test
    void testGetOrderWithId22_OrderServiceGetOrderWithId2ReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockOrderService.getOrderWithId2("orderId")).thenReturn(null);

        // Run the test
        final Order result = myClassUnderTest.getOrderWithId22("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordCallingGetOrderHelper2("orderId");
    }

    @Test
    void testGetOrderDescription1() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription1("orderId");

        // Verify the results
        assertEquals("description", result);
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderDescription1_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription1("orderId");

        // Verify the results
        assertEquals("description", result);
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderDescription2() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription2("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderDescription2_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getOrderDescription2("orderId"));
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderDescription3() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription3("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderDescription3_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getOrderDescription3("orderId"));
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderDescription4() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription4("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderDescription4_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription4("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderDescription5() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription5("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderDescription5_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription5("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderDescription6() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription6("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderDescription6_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription6("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderDescription7() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription7("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderDescription7_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getOrderDescription7("orderId"));
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderDescription8() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription8("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderDescription8_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription8("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderDescription9() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription9("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordGetDescriptionHelper(any(Order.class));
    }

    @Test
    void testGetOrderDescription9_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getOrderDescription9("orderId"));
    }

    @Test
    void testGetOrderDescription10() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription10("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
        verify(mockMetricService).recordGetDescriptionHelper(any(Order.class));
    }

    @Test
    void testGetOrderDescription10_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getOrderDescription10("orderId"));
        verify(mockMetricService).recordMetricHitInBothCases("orderId");
    }

    @Test
    void testGetOrderDescription11() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription11("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordGetDescriptionHelper(any(Order.class));
    }

    @Test
    void testGetOrderDescription11_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription11("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrderDescription12() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription12("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordGetDescriptionHelper(any(Order.class));
    }

    @Test
    void testGetOrderDescription12_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription12("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderDescription13() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription13("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordGetDescriptionHelper(any(Order.class));
    }

    @Test
    void testGetOrderDescription13_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription13("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOrderDescription14() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription14("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordGetDescriptionHelper(any(Order.class));
    }

    @Test
    void testGetOrderDescription14_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getOrderDescription14("orderId"));
    }

    @Test
    void testGetOrderDescription15() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final String result = myClassUnderTest.getOrderDescription15("orderId");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordGetDescriptionHelper(any(Order.class));
    }

    @Test
    void testGetOrderDescription15_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOrderDescription15("orderId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testCanCreate1() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final boolean result = myClassUnderTest.canCreate1("orderId");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testCanCreate1_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final boolean result = myClassUnderTest.canCreate1("orderId");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testCanCreate2() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final boolean result = myClassUnderTest.canCreate2("orderId");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testCanCreate2_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final boolean result = myClassUnderTest.canCreate2("orderId");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testCanCreate3() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final boolean result = myClassUnderTest.canCreate3("orderId");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testCanCreate3_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final boolean result = myClassUnderTest.canCreate3("orderId");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testCanCreate4() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final boolean result = myClassUnderTest.canCreate4("orderId");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testCanCreate4_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final boolean result = myClassUnderTest.canCreate4("orderId");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testCanCreate5() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final boolean result = myClassUnderTest.canCreate5("orderId");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testCanCreate5_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final boolean result = myClassUnderTest.canCreate5("orderId");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testExists1() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(new Order("orderId", "description"));

        // Run the test
        final boolean result = myClassUnderTest.exists1("orderId");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testExists1_OrderServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        final boolean result = myClassUnderTest.exists1("orderId");

        // Verify the results
        assertFalse(result);
    }
}
