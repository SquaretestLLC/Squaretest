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

public class MyClass {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final AddressService addressService;
    private final MetricService metricService;

    public MyClass(
            final OrderService orderService, final CustomerService customerService,
            final AddressService addressService, final MetricService metricService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.addressService = addressService;
        this.metricService = metricService;
    }

    public CustomerAndOrderData getOrderInfo1(final String orderId) throws ComponentException {
        try {
            final Order order = orderService.getOrderWithId(orderId);
            final Customer customer = customerService.getCustomerWithId(order.getCustomerId());
            final Address address = addressService.getAddressWithId(order.getAddressId());
            return new CustomerAndOrderData(customer, address, order);
        } catch(final OrderServiceException | AddressServiceException | CustomerServiceException e) {
            throw new ComponentException(e);
        } finally {
            metricService.recordSomething(orderId);
        }
    }

    public CustomerAndOrderData safeGetOrderInfo1(final String orderId) {
        try {
            return getOrderInfo1(orderId);
        } catch (final Exception e) {
            return null;
        }
    }

    public CustomerAndOrderData getOrderInfo2(final String orderId) throws CustomerServiceException, OrderServiceException, AddressServiceException {
        try {
            final Order order = orderService.getOrderWithId(orderId);
            final Customer customer = customerService.getCustomerWithId(order.getCustomerId());
            final Address address = addressService.getAddressWithId(order.getAddressId());
            return new CustomerAndOrderData(customer, address, order);
        } finally {
            metricService.recordSomething(orderId);
        }
    }

    public CustomerAndOrderData safeGetOrderInfo2(final String orderId) {
        try {
            return getOrderInfo2(orderId);
        } catch (final Exception e) {
            return null;
        } finally {
            metricService.recordSomethingElse(orderId);
        }
    }
}
