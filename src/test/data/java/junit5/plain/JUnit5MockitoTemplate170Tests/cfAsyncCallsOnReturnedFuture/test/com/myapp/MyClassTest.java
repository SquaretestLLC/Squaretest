package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private OrderServiceAsync mockOrderService;
    @Mock
    private CustomerService mockCustomerService;
    @Mock
    private AddressService mockAddressService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), mockOrderService, mockCustomerService,
                mockAddressService);
    }

    @Test
    void testGetOrderInfoParallel1() throws Exception {
        // Setup
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = CompletableFuture.completedFuture(
                new Order("orderId", "description", "customerId", "addressId"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.getOrderInfoParallel1("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderInfoParallel1_OrderServiceAsyncReturnsFailure() {
        // Setup
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = new CompletableFuture<>();
        orderCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel1("orderId"));
    }

    @Test
    void testGetOrderInfoParallel1_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = CompletableFuture.completedFuture(
                new Order("orderId", "description", "customerId", "addressId"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

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
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = CompletableFuture.completedFuture(
                new Order("orderId", "description", "customerId", "addressId"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel1("orderId"));
    }

    @Test
    void testGetOrderInfoParallel2() throws Exception {
        // Setup
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = CompletableFuture.completedFuture(
                new Order("orderId", "description", "customerId", "addressId"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.getOrderInfoParallel2("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderInfoParallel2_OrderServiceAsyncReturnsFailure() {
        // Setup
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = new CompletableFuture<>();
        orderCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel2("orderId"));
    }

    @Test
    void testGetOrderInfoParallel2_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = CompletableFuture.completedFuture(
                new Order("orderId", "description", "customerId", "addressId"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

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
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = CompletableFuture.completedFuture(
                new Order("orderId", "description", "customerId", "addressId"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel2("orderId"));
    }

    @Test
    void testGetOrderInfoParallel3() throws Exception {
        // Setup
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = CompletableFuture.completedFuture(
                new Order("orderId", "description", "customerId", "addressId"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.getOrderInfoParallel3("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderInfoParallel3_OrderServiceAsyncReturnsFailure() {
        // Setup
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = new CompletableFuture<>();
        orderCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel3("orderId"));
    }

    @Test
    void testGetOrderInfoParallel3_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = CompletableFuture.completedFuture(
                new Order("orderId", "description", "customerId", "addressId"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

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
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = CompletableFuture.completedFuture(
                new Order("orderId", "description", "customerId", "addressId"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel3("orderId"));
    }

    @Test
    void testGetOrderInfoParallel4() throws Exception {
        // Setup
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = CompletableFuture.completedFuture(
                new Order("orderId", "description", "customerId", "addressId"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.getOrderInfoParallel4("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderInfoParallel4_OrderServiceAsyncReturnsFailure() {
        // Setup
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = new CompletableFuture<>();
        orderCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel4("orderId"));
    }

    @Test
    void testGetOrderInfoParallel4_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = CompletableFuture.completedFuture(
                new Order("orderId", "description", "customerId", "addressId"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

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
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = CompletableFuture.completedFuture(
                new Order("orderId", "description", "customerId", "addressId"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel4("orderId"));
    }

    @Test
    void testGetOrderInfoParallel5() throws Exception {
        // Setup
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = CompletableFuture.completedFuture(
                new Order("orderId", "description", "customerId", "addressId"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        final CustomerAndOrderData result = myClassUnderTest.getOrderInfoParallel5("orderId");

        // Verify the results
    }

    @Test
    void testGetOrderInfoParallel5_OrderServiceAsyncReturnsFailure() {
        // Setup
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = new CompletableFuture<>();
        orderCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel5("orderId"));
    }

    @Test
    void testGetOrderInfoParallel5_CustomerServiceThrowsCustomerServiceException() throws Exception {
        // Setup
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = CompletableFuture.completedFuture(
                new Order("orderId", "description", "customerId", "addressId"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

        when(mockCustomerService.getCustomerWithId("customerId")).thenThrow(CustomerServiceException.class);

        // Configure AddressService.getAddressWithId(...).
        final Address address = new Address("addressId", "name", "line1", "line2", "zipcode");
        when(mockAddressService.getAddressWithId("addressId")).thenReturn(address);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel5("orderId"));
    }

    @Test
    void testGetOrderInfoParallel5_AddressServiceThrowsAddressServiceException() throws Exception {
        // Setup
        // Configure OrderServiceAsync.getOrderWithId(...).
        final CompletableFuture<Order> orderCompletableFuture = CompletableFuture.completedFuture(
                new Order("orderId", "description", "customerId", "addressId"));
        when(mockOrderService.getOrderWithId("orderId")).thenReturn(orderCompletableFuture);

        when(mockCustomerService.getCustomerWithId("customerId")).thenReturn(new Customer("customerId", "name"));
        when(mockAddressService.getAddressWithId("addressId")).thenThrow(AddressServiceException.class);

        // Run the test
        assertThrows(ComponentException.class, () -> myClassUnderTest.getOrderInfoParallel5("orderId"));
    }
}
