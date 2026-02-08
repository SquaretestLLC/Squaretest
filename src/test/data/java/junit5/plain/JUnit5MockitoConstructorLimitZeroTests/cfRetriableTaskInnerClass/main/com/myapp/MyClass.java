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
        return new OrderRetriableTask1(orderId).perform();
    }

    public Order getOrderWithId2(final String orderId) {
        try {
            return new OrderRetriableTask2(orderId).perform();
        } catch(final NetworkException e) {
            metricService.recordOrderNetworkException(e);
            throw e;
        } catch(final ServiceException e) {
            metricService.recordOrderServiceException(e);
            throw e;
        }
    }

    public Order getOrderWithId3(final String orderId) {
        try {
            return new OrderRetriableTask3(orderId).perform();
        } catch(final NetworkException | ServiceException e) {
            metricService.recordOrderException(e);
            throw e;
        }
    }

    public Order getOrderWithId4(final String orderId) {
        return new OrderRetriableTask4(orderId).perform();
    }

    public Order getOrderWithId5(final String orderId) {
        metricService.recordStartOfGetOrderMethodCall(orderId);
        final Order ret = new OrderRetriableTask5(orderId).perform();
        metricService.recordEndOfGetOrderMethodCall(orderId);
        return ret;
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

    private class OrderRetriableTask1 extends RetriableTask<Order> {
        private final String orderId;

        public OrderRetriableTask1(final String orderId) {
            super(10);
            this.orderId = orderId;
        }

        @Override
        protected Order doTask() {
            return orderService.getOrderWithId(orderId);
        }
    }

    private class OrderRetriableTask2 extends RetriableTask<Order> {
        private final String orderId;

        public OrderRetriableTask2(final String orderId) {
            super(10);
            this.orderId = orderId;
        }

        @Override
        protected Order doTask() {
            return orderService.getOrderWithId(orderId);
        }
    }

    private class OrderRetriableTask3 extends RetriableTask<Order> {
        private final String orderId;

        public OrderRetriableTask3(final String orderId) {
            super(10);
            this.orderId = orderId;
        }

        @Override
        protected Order doTask() {
            return orderService.getOrderWithId(orderId);
        }
    }

    private class OrderRetriableTask4 extends RetriableTask<Order> {
        private final String orderId;

        public OrderRetriableTask4(final String orderId) {
            super(10);
            this.orderId = orderId;
        }

        @Override
        protected Order doTask() {
            try {
                return orderService.getOrderWithId(orderId);
            } catch(final NetworkException e) {
                metricService.recordOrderNetworkException(e);
                throw e;
            } catch(final ServiceException e) {
                metricService.recordOrderServiceException(e);
                throw e;
            }
        }
    }

    private class OrderRetriableTask5 extends RetriableTask<Order> {
        private final String orderId;

        public OrderRetriableTask5(final String orderId) {
            super(10);
            this.orderId = orderId;
        }

        @Override
        protected Order doTask() {
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
}
