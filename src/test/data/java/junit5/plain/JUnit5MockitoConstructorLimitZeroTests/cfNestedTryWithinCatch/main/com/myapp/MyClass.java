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
    private final OrderService primaryOrderService;
    private final OrderService alternateOrderService;
    private final MetricService metricService;

    public MyClass(final OrderService primaryOrderService, final OrderService alternateOrderService, final MetricService metricService) {
        this.primaryOrderService = primaryOrderService;
        this.alternateOrderService = alternateOrderService;
        this.metricService = metricService;
    }

    public Order getOrderWithId2(final String orderId) throws OrderServiceException {
        try {
            return primaryOrderService.getOrderWithId(orderId);
        } catch (final NetworkException e) {
            try {
                return primaryOrderService.getOrderWithId(orderId);
            } catch (final NetworkException e2) {
                metricService.recordOrderNetworkException(e2);
                throw e2;
            }
        } catch (final ServiceException e) {
            metricService.recordOrderServiceException(e);
            throw e;
        } finally {
            metricService.recordInnerTryMessage(orderId);
        }
    }

    public Order getOrderWithId3(final String orderId) throws OrderServiceException {
        try {
            return primaryOrderService.getOrderWithId(orderId);
        } catch (final NetworkException e) {
            try {
                return alternateOrderService.getOrderWithId(orderId);
            } catch (final NetworkException e2) {
                metricService.recordOrderNetworkException(e2);
                throw e2;
            }
        } catch (final ServiceException e) {
            metricService.recordOrderServiceException(e);
            throw e;
        } finally {
            metricService.recordInnerTryMessage(orderId);
        }
    }

    public Order getOrderWithId4(final String orderId) throws OrderServiceException {
        try {
            return primaryOrderService.getOrderWithId(orderId);
        } catch (final NetworkException e) {
            metricService.recordOrderNetworkException(e);
            throw e;
        } catch (final ServiceException e) {
            metricService.recordOrderServiceException(e);
            throw e;
        } finally {
            try {
                final Order alt = alternateOrderService.getOrderWithId(orderId);
            } catch (final NetworkException e) {
                metricService.recordOrderNetworkExceptionInFinally(e);
                throw e;
            }
            metricService.recordInnerTryMessage(orderId);
        }
    }
}
