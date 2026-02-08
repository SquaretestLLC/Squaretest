package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private OrderService mockOrderService;
    @Mock
    private CustomerService mockCustomerService;
    @Mock
    private AddressService mockAddressService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockOrderService, mockCustomerService, mockAddressService, mockMetricService);
    }

    @Test
    void testGetOrderInfo1() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.getOrderInfo1("orderId");

        // Verify the results
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfo1_OrderServiceThrowsOrderServiceException() throws Exception {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(OrderServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfo1("orderId"));
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfo1_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenThrow(CustomerServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfo1("orderId"));
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfo1_AddressServiceThrowsAddressServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfo1("orderId"));
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testSafeGetOrderInfo1() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.safeGetOrderInfo1("orderId");

        // Verify the results
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testSafeGetOrderInfo1_OrderServiceThrowsOrderServiceException() throws Exception {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(OrderServiceException.class);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.safeGetOrderInfo1("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testSafeGetOrderInfo1_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenThrow(CustomerServiceException.class);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.safeGetOrderInfo1("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testSafeGetOrderInfo1_AddressServiceThrowsAddressServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.safeGetOrderInfo1("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfo2() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.getOrderInfo2("orderId");

        // Verify the results
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfo2_OrderServiceThrowsOrderServiceException() throws Exception {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(OrderServiceException.class);

        // Run the test
        assertThrows(OrderServiceException.class, () -> myClassUnderTest.getOrderInfo2("orderId"));
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfo2_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenThrow(CustomerServiceException.class);

        // Run the test
        assertThrows(CustomerServiceException.class, () -> myClassUnderTest.getOrderInfo2("orderId"));
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfo2_AddressServiceThrowsAddressServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        assertThrows(AddressServiceException.class, () -> myClassUnderTest.getOrderInfo2("orderId"));
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testSafeGetOrderInfo2() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.safeGetOrderInfo2("orderId");

        // Verify the results
        verify(mockMetricService).recordSomething("orderId");
        verify(mockMetricService).recordSomethingElse("orderId");
    }

    @Test
    void testSafeGetOrderInfo2_OrderServiceThrowsOrderServiceException() throws Exception {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(OrderServiceException.class);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.safeGetOrderInfo2("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordSomething("orderId");
        verify(mockMetricService).recordSomethingElse("orderId");
    }

    @Test
    void testSafeGetOrderInfo2_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenThrow(CustomerServiceException.class);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.safeGetOrderInfo2("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordSomething("orderId");
        verify(mockMetricService).recordSomethingElse("orderId");
    }

    @Test
    void testSafeGetOrderInfo2_AddressServiceThrowsAddressServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.safeGetOrderInfo2("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordSomething("orderId");
        verify(mockMetricService).recordSomethingElse("orderId");
    }
}
