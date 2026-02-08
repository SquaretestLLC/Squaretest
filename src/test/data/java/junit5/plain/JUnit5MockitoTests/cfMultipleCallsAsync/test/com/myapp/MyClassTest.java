package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.concurrent.CompletableFuture;

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
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), mockOrderService, mockCustomerService,
                mockAddressService, mockMetricService);
    }

    @Test
    void testGetOrderInfoSync1() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.getOrderInfoSync1("orderId");

        // Verify the results
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfoSync1_OrderServiceThrowsOrderServiceException() throws Exception {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(OrderServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoSync1("orderId"));
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfoSync1_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenThrow(CustomerServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoSync1("orderId"));
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfoSync1_AddressServiceThrowsAddressServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoSync1("orderId"));
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfoAsync1() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync1("orderId");

        // Verify the results
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfoAsync1_OrderServiceThrowsOrderServiceException() throws Exception {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(OrderServiceException.class);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync1("orderId");

        // Verify the results
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfoAsync1_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenThrow(CustomerServiceException.class);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync1("orderId");

        // Verify the results
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfoAsync1_AddressServiceThrowsAddressServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync1("orderId");

        // Verify the results
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfoSync2() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.getOrderInfoSync2("orderId");

        // Verify the results
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfoSync2_OrderServiceThrowsOrderServiceException() throws Exception {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(OrderServiceException.class);

        // Run the test
        assertThrows(OrderServiceException.class, () -> myClassUnderTest.getOrderInfoSync2("orderId"));
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfoSync2_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenThrow(CustomerServiceException.class);

        // Run the test
        assertThrows(CustomerServiceException.class, () -> myClassUnderTest.getOrderInfoSync2("orderId"));
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfoSync2_AddressServiceThrowsAddressServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        assertThrows(AddressServiceException.class, () -> myClassUnderTest.getOrderInfoSync2("orderId"));
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfoAsync2() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync2("orderId");

        // Verify the results
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfoAsync2_OrderServiceThrowsOrderServiceException() throws Exception {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(OrderServiceException.class);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync2("orderId");

        // Verify the results
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfoAsync2_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenThrow(CustomerServiceException.class);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync2("orderId");

        // Verify the results
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfoAsync2_AddressServiceThrowsAddressServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync2("orderId");

        // Verify the results
        verify(mockMetricService).recordSomething("orderId");
    }

    @Test
    void testGetOrderInfoAsync3() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync3("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderInfoAsync3_OrderServiceThrowsOrderServiceException() throws Exception {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(OrderServiceException.class);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync3("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderInfoAsync3_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenThrow(CustomerServiceException.class);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync3("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderInfoAsync3_AddressServiceThrowsAddressServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync3("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderInfoAsync4() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync4("orderId");

        // Verify the results
        verify(mockMetricService).recordSomething("orderId");
        verify(mockMetricService).recordAfterAsyncCall("orderId");
    }

    @Test
    void testGetOrderInfoAsync4_OrderServiceThrowsOrderServiceException() throws Exception {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(OrderServiceException.class);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync4("orderId");

        // Verify the results
        verify(mockMetricService).recordSomething("orderId");
        verify(mockMetricService).recordAfterAsyncCall("orderId");
    }

    @Test
    void testGetOrderInfoAsync4_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenThrow(CustomerServiceException.class);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync4("orderId");

        // Verify the results
        verify(mockMetricService).recordSomething("orderId");
        verify(mockMetricService).recordAfterAsyncCall("orderId");
    }

    @Test
    void testGetOrderInfoAsync4_AddressServiceThrowsAddressServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync4("orderId");

        // Verify the results
        verify(mockMetricService).recordSomething("orderId");
        verify(mockMetricService).recordAfterAsyncCall("orderId");
    }

    @Test
    void testGetOrderInfoAsync5() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync5("orderId");

        // Verify the results
        verify(mockMetricService).recordAfterAsyncCall("orderId");
    }

    @Test
    void testGetOrderInfoAsync5_OrderServiceThrowsOrderServiceException() throws Exception {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(OrderServiceException.class);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync5("orderId");

        // Verify the results
        verify(mockMetricService).recordAfterAsyncCall("orderId");
    }

    @Test
    void testGetOrderInfoAsync5_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenThrow(CustomerServiceException.class);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync5("orderId");

        // Verify the results
        verify(mockMetricService).recordAfterAsyncCall("orderId");
    }

    @Test
    void testGetOrderInfoAsync5_AddressServiceThrowsAddressServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        final CompletableFuture<CustomerAndOrderData> result = myClassUnderTest.getOrderInfoAsync5("orderId");

        // Verify the results
        verify(mockMetricService).recordAfterAsyncCall("orderId");
    }

    @Test
    void testGetOrderInfoParallel1() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.getOrderInfoParallel1("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderInfoParallel1_OrderServiceThrowsOrderServiceException() throws Exception {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(OrderServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel1("orderId"));
    }

    @Test
    void testGetOrderInfoParallel1_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenThrow(CustomerServiceException.class);

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel1("orderId"));
    }

    @Test
    void testGetOrderInfoParallel1_AddressServiceThrowsAddressServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel1("orderId"));
    }

    @Test
    void testGetOrderInfoParallel2() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.getOrderInfoParallel2("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderInfoParallel2_OrderServiceThrowsOrderServiceException() throws Exception {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(OrderServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel2("orderId"));
    }

    @Test
    void testGetOrderInfoParallel2_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenThrow(CustomerServiceException.class);

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel2("orderId"));
    }

    @Test
    void testGetOrderInfoParallel2_AddressServiceThrowsAddressServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel2("orderId"));
    }

    @Test
    void testGetOrderInfoParallel3() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.getOrderInfoParallel3("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderInfoParallel3_OrderServiceThrowsOrderServiceException() throws Exception {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(OrderServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel3("orderId"));
    }

    @Test
    void testGetOrderInfoParallel3_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenThrow(CustomerServiceException.class);

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel3("orderId"));
    }

    @Test
    void testGetOrderInfoParallel3_AddressServiceThrowsAddressServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel3("orderId"));
    }

    @Test
    void testGetOrderInfoParallel4() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.getOrderInfoParallel4("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderInfoParallel4_OrderServiceThrowsOrderServiceException() throws Exception {
        // Setup
        when(mockOrderService.getOrderWithId("orderId")).thenThrow(OrderServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel4("orderId"));
    }

    @Test
    void testGetOrderInfoParallel4_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenThrow(CustomerServiceException.class);

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel4("orderId"));
    }

    @Test
    void testGetOrderInfoParallel4_AddressServiceThrowsAddressServiceException() throws Exception {
        // Setup
        // Configure OrderService.getOrderWithId(...).
        final Order order = new Order("orderId", "description", "customerId", "addressId");
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(order);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel4("orderId"));
    }
}
