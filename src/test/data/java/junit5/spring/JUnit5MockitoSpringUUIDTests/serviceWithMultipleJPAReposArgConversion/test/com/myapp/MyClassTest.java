package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private OrderRepository mockOrderRepository;
    @Mock
    private CustomerRepository mockCustomerRepository;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockOrderRepository, mockCustomerRepository);
    }

    @Test
    void testGetOrderAndCustomerInfo() {
        // Setup
        // Configure OrderRepository.findById(...).
        final Order order1 = new Order();
        order1.setOrderId("orderId");
        order1.setOrderFirstValue("orderFirstValue");
        order1.setOrderSecondValue("orderSecondValue");
        final Optional<Order> order = Optional.of(order1);
        when(mockOrderRepository.findById("55f2fc6f-b6ab-4f0e-95eb-73022dbc8d9c")).thenReturn(order);

        // Configure CustomerRepository.findById(...).
        final Customer customer1 = new Customer();
        customer1.setCustomerId("customerId");
        customer1.setCustomerFirstValue("customerFirstValue");
        customer1.setCustomerSecondValue("customerSecondValue");
        final Optional<Customer> customer = Optional.of(customer1);
        when(mockCustomerRepository.findById("f5e6d820-707b-4b39-83b6-f445d1f98283")).thenReturn(customer);

        // Run the test
        final OrderAndCustomerInfo result = myClassUnderTest.getOrderAndCustomerInfo(
                UUID.fromString("55f2fc6f-b6ab-4f0e-95eb-73022dbc8d9c"),
                UUID.fromString("f5e6d820-707b-4b39-83b6-f445d1f98283"));

        // Verify the results
    }

    @Test
    void testGetOrderAndCustomerInfo_OrderRepositoryReturnsAbsent() {
        // Setup
        when(mockOrderRepository.findById("55f2fc6f-b6ab-4f0e-95eb-73022dbc8d9c")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class,
                () -> myClassUnderTest.getOrderAndCustomerInfo(UUID.fromString("55f2fc6f-b6ab-4f0e-95eb-73022dbc8d9c"),
                        UUID.fromString("f5e6d820-707b-4b39-83b6-f445d1f98283")));
    }

    @Test
    void testGetOrderAndCustomerInfo_CustomerRepositoryReturnsAbsent() {
        // Setup
        // Configure OrderRepository.findById(...).
        final Order order1 = new Order();
        order1.setOrderId("orderId");
        order1.setOrderFirstValue("orderFirstValue");
        order1.setOrderSecondValue("orderSecondValue");
        final Optional<Order> order = Optional.of(order1);
        when(mockOrderRepository.findById("55f2fc6f-b6ab-4f0e-95eb-73022dbc8d9c")).thenReturn(order);

        when(mockCustomerRepository.findById("f5e6d820-707b-4b39-83b6-f445d1f98283")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class,
                () -> myClassUnderTest.getOrderAndCustomerInfo(UUID.fromString("55f2fc6f-b6ab-4f0e-95eb-73022dbc8d9c"),
                        UUID.fromString("f5e6d820-707b-4b39-83b6-f445d1f98283")));
    }
}
