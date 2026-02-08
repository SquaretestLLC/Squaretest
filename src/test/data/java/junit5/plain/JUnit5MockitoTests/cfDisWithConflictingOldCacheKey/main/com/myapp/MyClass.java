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

import java.util.HashMap;
import java.util.Map;

public class MyClass {
    private final OrderService orderService;
    private final CustomerService customerService;

    public MyClass(final OrderService orderService, final CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    public Map<String, Object> getData1(final String orderId) {
        final Map<String, Object> ret = new HashMap<>();
        addOrderInfo(ret, orderId);
        final CustomerData customerData = customerService.getCustomerForOrder1(orderId);
        if(customerData == null) {
            throw new RuntimeException("Missing customer data for " + orderId);
        }
        ret.put("customer", customerData);
        return ret;
    }

    private void addOrderInfo(final Map<String, Object> ret, final String orderId) {
        ret.put(orderId, getOrder(orderId));
        final String oldOrderId = "OLD" + orderId;
        ret.put(oldOrderId, getOrder(oldOrderId));
        final String newOrderId = "NEW" + orderId;
        ret.put(newOrderId, getOrder(newOrderId));
    }

    private Object getOrder(final String orderId) {
        return orderService.getOrderWithId(orderId);
    }
}
