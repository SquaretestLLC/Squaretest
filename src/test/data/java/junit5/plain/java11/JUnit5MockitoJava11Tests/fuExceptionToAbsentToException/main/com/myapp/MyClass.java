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
    private final MetricService metricService;

    public MyClass(final OrderService orderService, final MetricService metricService) {
        this.orderService = orderService;
        this.metricService = metricService;
    }

    public Order getOrderWithId1(final String orderId) {
        final Order order = safeGetOrderWithId1(orderId);
        if(order == null) {
            metricService.recordNullOrder(orderId);
            throw new OrderAdapterException(orderId);
        }
        return order;
    }

    public Order getOrderWithId2(final String orderId) {
        final Order order = safeGetOrderWithId1(orderId);
        if(order != null) {
            return order;
        }
        metricService.recordNullOrder(orderId);
        throw new OrderAdapterException(orderId);
    }

    public Order getOrderWithId3(final String orderId, final boolean shouldReturnDefault) {
        final Order order = safeGetOrderWithId1(orderId);
        if(order == null) {
            metricService.recordNullOrder(orderId);
            if(shouldReturnDefault) {
                // Squaretest should detect that this returns and the "throws statement" is the fallthru (alternate) case.
                metricService.recordUsingDefaultOrder(orderId);
                return new Order("defaultOrderId", "defaultDescription");
            }
            throw new OrderAdapterException(orderId);
        }
        return order;
    }

    public Order safeGetOrderWithId1(final String orderId) {
        try {
            return getOrderWithId1Helper(orderId);
        } catch(final NetworkException e) {
            metricService.recordOrderNetworkException(e);
            return null;
        } catch(final ServiceException e) {
            metricService.recordOrderServiceException(e);
            return null;
        }
    }

    private Order getOrderWithId1Helper(final String orderId) {
        return orderService.getOrderWithId(orderId);
    }
}
