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

import java.util.List;
import java.util.stream.Collectors;

public class MyClass {
    private final OrderService orderService;
    private final AddressService addressService;
    private final CustomerService customerService;

    public MyClass(
            final OrderService orderService, final AddressService addressService,
            final CustomerService customerService) {
        this.orderService = orderService;
        this.addressService = addressService;
        this.customerService = customerService;
    }

    public List<Order> getOrdersWithIds1(final List<String> orderIds) {
        return orderIds.stream().map(this::getOrderWithId1).collect(Collectors.toList());
    }

    public List<Order> getOrdersWithIds2(final List<String> orderIds) {
        return orderIds.stream().map(orderService::getOrderWithId).collect(Collectors.toList());
    }

    public List<CustomerAndOrderData> getOrdersWithIds3(final List<String> orderIds) {
        return orderIds.stream().map(orderService::getOrderWithId).map(order -> {
            try {
                return new CustomerAndOrderData(customerService.getCustomerWithId(order.getCustomerId()), addressService.getAddressWithId(order.getAddressId()), order);
            } catch (CustomerServiceException | AddressServiceException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    private Order getOrderWithId1(final String orderId) {
        return orderService.getOrderWithId(orderId);
    }
}
