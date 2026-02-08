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

    public Order getOrderWithId2(final String orderId) throws OrderServiceException {
        try {
            try {
                return orderService.getOrderWithId(orderId);
            } catch (final NetworkException e) {
                metricService.recordOrderNetworkException(e);
                throw e;
            } catch (final ServiceException e) {
                metricService.recordOrderServiceException(e);
                throw e;
            } finally {
                metricService.recordInnerTryMessage(orderId);
            }
        } catch(final Exception e) {
            throw new OrderServiceException(e);
        } finally {
            metricService.recordOuterTryMessage(orderId);
        }
    }

    public Order getOrderWithId3(final String orderId) throws OrderServiceException {
        try {
            try {
                return orderService.getOrderWithId(orderId);
            } catch (final NetworkException e) {
                metricService.recordOrderNetworkException(e);
                throw e;
            } catch (final ServiceException e) {
                metricService.recordOrderServiceException(e);
                throw e;
            } finally {
                metricService.recordInnerTryMessage(orderId);
            }
        } catch(final NetworkException e) {
            throw new OrderServiceException(e);
        } finally {
            metricService.recordOuterTryMessage(orderId);
        }
    }

    public Order safeGetOrderWithId2(final String orderId) {
        try {
            try {
                return orderService.getOrderWithId(orderId);
            } catch (final NetworkException e) {
                metricService.recordOrderNetworkException(e);
                throw new OrderServiceException(e);
            } catch (final ServiceException e) {
                metricService.recordOrderServiceException(e);
                throw new OrderServiceException(e);
            } finally {
                metricService.recordInnerTryMessage(orderId);
            }
        } catch(final Exception e) {
            return null;
        } finally {
            metricService.recordOuterTryMessage(orderId);
        }
    }

    public Order safeGetOrderWithId3(final String orderId) {
        try {
            try {
                return orderService.getOrderWithId(orderId);
            } catch (final NetworkException e) {
                metricService.recordOrderNetworkException(e);
                throw new OrderServiceException(e);
            } catch (final ServiceException e) {
                metricService.recordOrderServiceException(e);
                throw new OrderServiceException(e);
            } finally {
                metricService.recordInnerTryMessage(orderId);
            }
        } catch(final Throwable e) {
            return null;
        } finally {
            metricService.recordOuterTryMessage(orderId);
        }
    }

    public Order safeGetOrderWithId4(final String orderId) {
        for (;;) {
            try {
                try {
                    return orderService.getOrderWithId(orderId);
                } catch (final NetworkException e) {
                    metricService.recordOrderNetworkException(e);
                    throw new OrderServiceException(e);
                } catch (final ServiceException e) {
                    metricService.recordOrderServiceException(e);
                    throw new OrderServiceException(e);
                } finally {
                    metricService.recordInnerTryMessage(orderId);
                }
            } catch (final Throwable e) {
                return null;
            } finally {
                metricService.recordOuterTryMessage(orderId);
            }
        }
    }
}
