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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyClass {
    private final OrderService orderService;

    public MyClass(final OrderService orderService) {
        this.orderService = orderService;
    }

    public List<Order> getOrders1(final String searchCriteria) {
        final List<Order> orders = orderService.getOrders1(searchCriteria);
        // For now this will do the right thing, because of the fallback logic.
        // Eventually, we should have the computeRValue(..) algorithm recognize calls to Collections.* methods.
        return Collections.unmodifiableList(orders);
    }

    public List<Order> getOrders2(final String searchCriteria) {
        final List<Order> orders = orderService.getOrders1(searchCriteria);
        // For now this will do the right thing, because of the fallback logic.
        // Eventually, we should have the computeRValue(..) algorithm recognize calls to known list constructors.
        return new ArrayList<>(orders);
    }
}
