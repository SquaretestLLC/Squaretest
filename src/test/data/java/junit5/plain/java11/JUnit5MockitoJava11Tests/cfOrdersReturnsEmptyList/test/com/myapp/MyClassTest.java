package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void testGetOrdersWithId1() {
        // Setup
        // Configure OrderService.getOrdersWithCustomerId(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrdersWithId1("customerId");

        // Verify the results
    }

    @Test
    void testGetOrdersWithId1_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.getOrdersWithId1("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrdersWithId1_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrdersWithId1("customerId"));
    }

    @Test
    void testGetOrdersWithId1_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrdersWithId1("customerId"));
    }

    @Test
    void testGetOrdersWithId2() {
        // Setup
        // Configure OrderService.getOrdersWithCustomerId(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrdersWithId2("customerId");

        // Verify the results
    }

    @Test
    void testGetOrdersWithId2_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.getOrdersWithId2("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrdersWithId2_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrdersWithId2("customerId"));
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
    }

    @Test
    void testGetOrdersWithId2_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrdersWithId2("customerId"));
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
    }

    @Test
    void testGetOrdersWithId3() {
        // Setup
        // Configure OrderService.getOrdersWithCustomerId(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrdersWithId3("customerId");

        // Verify the results
    }

    @Test
    void testGetOrdersWithId3_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.getOrdersWithId3("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrdersWithId3_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getOrdersWithId3("customerId"));
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testGetOrdersWithId3_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getOrdersWithId3("customerId"));
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId1() {
        // Setup
        // Configure OrderService.getOrdersWithCustomerId(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId1("customerId");

        // Verify the results
    }

    @Test
    void testSafeGetOrdersWithId1_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId1("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSafeGetOrdersWithId1_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(NetworkException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId1("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSafeGetOrdersWithId1_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(ServiceException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId1("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSafeGetOrdersWithId2() {
        // Setup
        // Configure OrderService.getOrdersWithCustomerId(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId2("customerId");

        // Verify the results
    }

    @Test
    void testSafeGetOrdersWithId2_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId2("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSafeGetOrdersWithId2_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(NetworkException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId2("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordOrderNetworkException(any(NetworkException.class));
    }

    @Test
    void testSafeGetOrdersWithId2_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(ServiceException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId2("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordOrderServiceException(any(ServiceException.class));
    }

    @Test
    void testSafeGetOrdersWithId3() {
        // Setup
        // Configure OrderService.getOrdersWithCustomerId(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId3("customerId");

        // Verify the results
    }

    @Test
    void testSafeGetOrdersWithId3_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId3("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSafeGetOrdersWithId3_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(NetworkException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId3("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId3_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(ServiceException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId3("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId4() {
        // Setup
        // Configure OrderService.getOrdersWithCustomerId(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId4("customerId");

        // Verify the results
    }

    @Test
    void testSafeGetOrdersWithId4_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId4("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSafeGetOrdersWithId4_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(NetworkException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId4("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId4_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(ServiceException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId4("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId5() {
        // Setup
        // Configure OrderService.getOrdersWithCustomerId(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId5("customerId");

        // Verify the results
    }

    @Test
    void testSafeGetOrdersWithId5_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId5("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSafeGetOrdersWithId5_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(NetworkException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId5("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId5_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(ServiceException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId5("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId6() {
        // Setup
        // Configure OrderService.getOrdersWithCustomerId(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId6("customerId");

        // Verify the results
    }

    @Test
    void testSafeGetOrdersWithId6_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId6("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSafeGetOrdersWithId6_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(NetworkException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId6("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId6_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(ServiceException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId6("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId7() {
        // Setup
        // Configure OrderService.getOrdersWithCustomerId(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId7("customerId");

        // Verify the results
    }

    @Test
    void testSafeGetOrdersWithId7_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId7("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSafeGetOrdersWithId7_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(NetworkException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId7("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId7_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(ServiceException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId7("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId8() {
        // Setup
        // Configure OrderService.getOrdersWithCustomerId(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId8("customerId");

        // Verify the results
    }

    @Test
    void testSafeGetOrdersWithId8_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId8("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSafeGetOrdersWithId8_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(NetworkException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId8("customerId");

        // Verify the results
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId8_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(ServiceException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId8("customerId");

        // Verify the results
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId9() {
        // Setup
        // Configure OrderService.getOrdersWithCustomerId(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId9("customerId");

        // Verify the results
    }

    @Test
    void testSafeGetOrdersWithId9_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId9("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSafeGetOrdersWithId9_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(NetworkException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId9("customerId");

        // Verify the results
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId9_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(ServiceException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId9("customerId");

        // Verify the results
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId10() {
        // Setup
        // Configure OrderService.getOrdersWithCustomerId(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId10("customerId");

        // Verify the results
    }

    @Test
    void testSafeGetOrdersWithId10_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId10("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSafeGetOrdersWithId10_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(NetworkException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId10("customerId");

        // Verify the results
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId10_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(ServiceException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId10("customerId");

        // Verify the results
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId11() {
        // Setup
        // Configure OrderService.getOrdersWithCustomerId(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId11("customerId");

        // Verify the results
    }

    @Test
    void testSafeGetOrdersWithId11_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId11("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSafeGetOrdersWithId11_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(NetworkException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId11("customerId");

        // Verify the results
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId11_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(ServiceException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId11("customerId");

        // Verify the results
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId12() {
        // Setup
        // Configure OrderService.getOrdersWithCustomerId(...).
        final List<Order> orders = List.of(new Order("orderId", "description"));
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(orders);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId12("customerId");

        // Verify the results
    }

    @Test
    void testSafeGetOrdersWithId12_OrderServiceReturnsNoItems() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId12("customerId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSafeGetOrdersWithId12_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(NetworkException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId12("customerId");

        // Verify the results
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }

    @Test
    void testSafeGetOrdersWithId12_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrdersWithCustomerId("customerId")).thenThrow(ServiceException.class);

        // Run the test
        final List<Order> result = myClassUnderTest.safeGetOrdersWithId12("customerId");

        // Verify the results
        verify(mockMetricService).recordOrderException(any(RuntimeException.class));
    }
}
