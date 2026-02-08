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

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;

public class MyClass {
    private final RetryTemplate retryTemplate;
    private final OrderService orderService;
    private final MetricService metricService;

    public MyClass(
            final RetryTemplate retryTemplate,
            final OrderService orderService, final MetricService metricService) {
        this.retryTemplate = retryTemplate;
        this.orderService = orderService;
        this.metricService = metricService;
    }

    public Order getOrderWithId1(final String orderId) {
        return retryTemplate.execute(new RetryCallback<Order, RuntimeException>() {
            @Override
            public Order doWithRetry(final RetryContext context) throws RuntimeException {
                return orderService.getOrderWithId(orderId);
            }
        });
    }

    public Order getOrderWithId2(final String orderId) throws NetworkException, ServiceException {
        try {
            return retryTemplate.execute(context -> orderService.getOrderWithId(orderId));
        } catch (final NetworkException e) {
            metricService.recordOrderNetworkException(e);
            throw e;
        } catch (final ServiceException e) {
            metricService.recordOrderServiceException(e);
            throw e;
        }
    }

    public Order getOrderWithId3(final String orderId) throws NetworkException, ServiceException {
        try {
            return retryTemplate.execute(context -> orderService.getOrderWithId(orderId));
        } catch (final NetworkException | ServiceException e) {
            metricService.recordOrderException(e);
            throw e;
        }
    }

    public Order getOrderWithId4(final String orderId) {
        return retryTemplate.execute(context -> orderService.getOrderWithId(orderId));
    }

    public Order getOrderWithId5(final String orderId) {
        return retryTemplate.execute(new RetryCallback<Order, RuntimeException>() {
            @Override
            public Order doWithRetry(final RetryContext context) throws RuntimeException {
                try {
                    return orderService.getOrderWithId(orderId);
                } catch (final NetworkException e) {
                    metricService.recordOrderNetworkException(e);
                    throw e;
                } catch (final ServiceException e) {
                    metricService.recordOrderServiceException(e);
                    throw e;
                }
            }
        });
    }

    public Order getOrderWithId6(final String orderId) {
        return retryTemplate.execute(context -> {
            try {
                return orderService.getOrderWithId(orderId);
            } catch (final NetworkException e) {
                metricService.recordOrderNetworkException(e);
                throw e;
            } catch (final ServiceException e) {
                metricService.recordOrderServiceException(e);
                throw e;
            }
        });
    }

    public Order safeGetOrderWithId1(final String orderId) {
        try {
            return getOrderWithId1(orderId);
        } catch (final Exception e) {
            return null;
        }
    }

    public Order safeGetOrderWithId2(final String orderId) {
        try {
            return getOrderWithId2(orderId);
        } catch (final Exception e) {
            return null;
        }
    }

    public Order safeGetOrderWithId3(final String orderId) {
        try {
            return getOrderWithId3(orderId);
        } catch (final Exception e) {
            return null;
        }
    }
}
