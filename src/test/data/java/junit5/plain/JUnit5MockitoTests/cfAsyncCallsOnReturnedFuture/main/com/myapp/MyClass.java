/*
 * Copyright 2026 Squaretest LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.myapp;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;
import java.util.function.Supplier;

public class MyClass {
    private final ExecutorService executorService;
    private final OrderServiceAsync orderService;
    private final CustomerService customerService;
    private final AddressService addressService;

    public MyClass(
            final ExecutorService executorService, final OrderServiceAsync orderService, final CustomerService customerService,
            final AddressService addressService) {
        this.executorService = executorService;
        this.orderService = orderService;
        this.customerService = customerService;
        this.addressService = addressService;
    }

    public CustomerAndOrderData getOrderInfoParallel1(final String orderId) throws ComponentException {
        final CompletableFuture<Order> orderFuture = orderService.getOrderWithId(orderId);
        final CompletableFuture<Customer> customerFuture = orderFuture.thenApplyAsync(new Function<Order, Customer>() {
            @Override
            public Customer apply(final Order order) {
                try {
                    return customerService.getCustomerWithId(order.getCustomerId());
                } catch (CustomerServiceException e) {
                    throw new CompletionException(e);
                }
            }
        }, executorService);
        final CompletableFuture<Address> addressFuture = orderFuture.thenApplyAsync(new Function<Order, Address>() {
            @Override
            public Address apply(final Order order) {
                try {
                    return addressService.getAddressWithId(order.getAddressId());
                } catch (AddressServiceException e) {
                    throw new CompletionException(e);
                }
            }
        }, executorService);
        try {
            return new CustomerAndOrderData(customerFuture.get(), addressFuture.get(), orderFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new ComponentException(e);
        }
    }

    public CustomerAndOrderData getOrderInfoParallel2(final String orderId) throws ComponentException {
        final CompletableFuture<Order> orderFuture = orderService.getOrderWithId(orderId);
        final CompletableFuture<Customer> customerFuture = orderFuture.thenApplyAsync(order -> {
            try {
                return customerService.getCustomerWithId(order.getCustomerId());
            } catch (CustomerServiceException e) {
                throw new CompletionException(e);
            }
        }, executorService);
        final CompletableFuture<Address> addressFuture = orderFuture.thenApplyAsync(order -> {
            try {
                return addressService.getAddressWithId(order.getAddressId());
            } catch (AddressServiceException e) {
                throw new CompletionException(e);
            }
        }, executorService);
        try {
            return new CustomerAndOrderData(customerFuture.get(), addressFuture.get(), orderFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new ComponentException(e);
        }
    }

    public CustomerAndOrderData getOrderInfoParallel3(final String orderId) throws ComponentException {
        final CompletableFuture<Order> orderFuture = orderService.getOrderWithId(orderId);
        final CompletableFuture<Customer> customerFuture = orderFuture.thenApplyAsync(order -> {
            return getCustomerHelper(order.getCustomerId());
        }, executorService);
        final CompletableFuture<Address> addressFuture = orderFuture.thenApplyAsync(order -> {
            return getAddressHelper(order.getAddressId());
        }, executorService);
        try {
            return new CustomerAndOrderData(customerFuture.get(), addressFuture.get(), orderFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new ComponentException(e);
        }
    }

    public CustomerAndOrderData getOrderInfoParallel4(final String orderId) throws ComponentException {
        final CompletableFuture<Order> orderFuture = orderService.getOrderWithId(orderId);
        final CompletableFuture<Customer> customerFuture = orderFuture.thenApplyAsync(order -> getCustomerHelper(order.getCustomerId()), executorService);
        final CompletableFuture<Address> addressFuture = orderFuture.thenApplyAsync(order -> getAddressHelper(order.getAddressId()), executorService);
        try {
            return new CustomerAndOrderData(customerFuture.get(), addressFuture.get(), orderFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new ComponentException(e);
        }
    }

    public CustomerAndOrderData getOrderInfoParallel5(final String orderId) throws ComponentException {
        final CompletableFuture<Order> orderFuture = orderService.getOrderWithId(orderId);
        // Ensure this avoids generating duplicate doAnswer() statements.
        final CompletableFuture<Object> otherFuture = CompletableFuture.supplyAsync(() -> "Unnecessary task", executorService);
        final CompletableFuture<Customer> customerFuture = orderFuture.thenApplyAsync(order -> getCustomerHelper(order.getCustomerId()), executorService);
        final CompletableFuture<Address> addressFuture = orderFuture.thenApplyAsync(order -> getAddressHelper(order.getAddressId()), executorService);
        try {
            return new CustomerAndOrderData(customerFuture.get(), addressFuture.get(), orderFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new ComponentException(e);
        }
    }

    private Customer getCustomerHelper(final String customerId) {
        try {
            return customerService.getCustomerWithId(customerId);
        } catch (CustomerServiceException e) {
            throw new CompletionException(e);
        }
    }

    private Address getAddressHelper(final String addressId) {
        try {
            return addressService.getAddressWithId(addressId);
        } catch (AddressServiceException e) {
            throw new CompletionException(e);
        }
    }

}
