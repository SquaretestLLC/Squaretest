package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private OrderService mockOrderService;
    @Mock
    private CustomerService mockCustomerService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockOrderService, mockCustomerService);
    }

    @Test
    void testGetData1() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));

        // Configure CustomerService.getCustomerForOrder1(...).
        final CustomerData customerData = new CustomerData("id", "firstName", "lastName");
        when(mockCustomerService.getCustomerForOrder1("orderId")).thenReturn(customerData);

        // Run the test
        final Map<String, Object> result = myClassUnderTest.getData1("orderId");

        // Verify the results
    }

    @Test
    void testGetData1_OrderServiceThrowsNetworkException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(NetworkException.class);

        // Run the test
        assertThrows(NetworkException.class, () -> myClassUnderTest.getData1("orderId"));
    }

    @Test
    void testGetData1_OrderServiceThrowsServiceException() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(ServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getData1("orderId"));
    }

    @Test
    void testGetData1_CustomerServiceReturnsNull() {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(new Order("orderId", "description"));
        when(mockCustomerService.getCustomerForOrder1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getData1("orderId"));
    }
}
