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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyClass {
    private final OrderService orderService;
    private final MetricService metricService;

    public MyClass(final OrderService orderService, final MetricService metricService) {
        this.orderService = orderService;
        this.metricService = metricService;
    }

    public List<Order> getOrdersWithId1(final String customerId) {
        return orderService.getOrdersWithCustomerId(customerId);
    }

    public List<Order> getOrdersWithId2(final String customerId) {
        try {
            return orderService.getOrdersWithCustomerId(customerId);
        } catch(final NetworkException e) {
            metricService.recordOrderNetworkException(e);
            throw e;
        } catch(final ServiceException e) {
            metricService.recordOrderServiceException(e);
            throw e;
        }
    }

    public List<Order> getOrdersWithId3(final String customerId) {
        try {
            return orderService.getOrdersWithCustomerId(customerId);
        } catch(final NetworkException | ServiceException e) {
            metricService.recordOrderException(e);
            throw e;
        }
    }

    public List<Order> safeGetOrdersWithId1(final String customerId) {
        try {
            return getOrdersWithId1(customerId);
        } catch (final Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Order> safeGetOrdersWithId2(final String customerId) {
        try {
            return getOrdersWithId2(customerId);
        } catch (final Exception e) {
            return Collections.emptyList();
        }
    }

    public List<Order> safeGetOrdersWithId3(final String customerId) {
        try {
            return getOrdersWithId3(customerId);
        } catch (final Exception e) {
            return Collections.EMPTY_LIST;
        }
    }

    public List<Order> safeGetOrdersWithId4(final String customerId) {
        try {
            return getOrdersWithId3(customerId);
        } catch (final Exception e) {
            return (List<Order>) Collections.EMPTY_LIST;
        }
    }

    public List<Order> safeGetOrdersWithId5(final String customerId) {
        try {
            return getOrdersWithId3(customerId);
        } catch (final Exception e) {
            return List.of();
        }
    }

    public List<Order> safeGetOrdersWithId6(final String customerId) {
        try {
            return getOrdersWithId3(customerId);
        } catch (final Exception e) {
            return Arrays.asList();
        }
    }

    public List<Order> safeGetOrdersWithId7(final String customerId) {
        try {
            return getOrdersWithId3(customerId);
        } catch (final Exception e) {
            return new ListWithVarArgsConstructor<>();
        }
    }

    public List<Order> safeGetOrdersWithId8(final String customerId) {
        try {
            return getOrdersWithId3(customerId);
        } catch (final Exception e) {
            return Arrays.asList(new Order("defaultOrderId", "defaultDescription"));
        }
    }

    public List<Order> safeGetOrdersWithId9(final String customerId) {
        try {
            return getOrdersWithId3(customerId);
        } catch (final Exception e) {
            return List.of(new Order("defaultOrderId", "defaultDescription"));
        }
    }

    public List<Order> safeGetOrdersWithId10(final String customerId) {
        try {
            return getOrdersWithId3(customerId);
        } catch (final Exception e) {
            final List<Order> orders = new ArrayList<>();
            orders.add(new Order("defaultOrderId", "defaultDescription"));
            return orders;
        }
    }

    public List<Order> safeGetOrdersWithId11(final String customerId) {
        try {
            return getOrdersWithId3(customerId);
        } catch (final Exception e) {
            return createDefaultOrderInstanceMethod();
        }
    }

    public List<Order> safeGetOrdersWithId12(final String customerId) {
        try {
            return getOrdersWithId3(customerId);
        } catch (final Exception e) {
            return createDefaultOrderStaticMethodNoTypeParam();
        }
    }

    private List<Order> createDefaultOrderInstanceMethod() {
        final List<Order> orders = new ArrayList<>();
        orders.add(new Order("defaultOrderId", "defaultDescription"));
        return orders;
    }

    private static List<Order> createDefaultOrderStaticMethodNoTypeParam() {
        final List<Order> orders = new ArrayList<>();
        orders.add(new Order("defaultOrderId", "defaultDescription"));
        return orders;
    }
}
