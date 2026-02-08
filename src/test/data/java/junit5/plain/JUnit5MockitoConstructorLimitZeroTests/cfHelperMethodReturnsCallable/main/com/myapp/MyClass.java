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

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MyClass {
    private final ExecutorService executorService;
    private final OrderService orderService;
    private final MetricService metricService;

    public MyClass(
            final ExecutorService executorService,
            final OrderService orderService, final MetricService metricService) {
        this.executorService = executorService;
        this.orderService = orderService;
        this.metricService = metricService;
    }

    public Future<Order> getOrderWithId1(final String orderId) throws NetworkException, ServiceException {
        return executorService.submit(getOrderCallable1(orderId));
    }
    public Future<Order> getOrderWithId2(final String orderId) throws NetworkException, ServiceException {
        return executorService.submit(getOrderCallable2(orderId));
    }
    public Future<Order> getOrderWithId3(final String orderId) throws NetworkException, ServiceException {
        return executorService.submit(getOrderCallable3(orderId));
    }
    public Future<Order> getOrderWithId4(final String orderId) throws NetworkException, ServiceException {
        return executorService.submit(getOrderCallable4(orderId));
    }

    public Callable<Order> getOrderCallable1(final String orderId) throws NetworkException, ServiceException {
        return new Callable<Order>() {
            @Override
            public Order call() throws Exception {
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
        };
    }

    public Callable<Order> getOrderCallable2(final String orderId) throws NetworkException, ServiceException {
        return () -> {
            try {
                return orderService.getOrderWithId(orderId);
            } catch (final NetworkException e) {
                metricService.recordOrderNetworkException(e);
                throw e;
            } catch (final ServiceException e) {
                metricService.recordOrderServiceException(e);
                throw e;
            }
        };
    }

    public Callable<Order> getOrderCallable3(final String orderId) throws NetworkException, ServiceException {
        return () -> {
            return getOrderCallableHelper3(orderId);
        };
    }

    public Callable<Order> getOrderCallable4(final String orderId) throws NetworkException, ServiceException {
        return () -> getOrderCallableHelper3(orderId);
    }

    private Order getOrderCallableHelper3(final String orderId) {
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
}
