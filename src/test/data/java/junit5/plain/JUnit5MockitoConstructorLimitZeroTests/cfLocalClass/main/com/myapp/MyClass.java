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
        class InnerClass {
            Order getOrderImpl(final String orderId) {
                return orderService.getOrderWithId(orderId);
            }
        }
        return new InnerClass().getOrderImpl(orderId);
    }

    public Order getOrderWithId2(final String orderId) {
        class InnerClass {
            Order getOrderImpl(final String orderId) {
                return getOrderWithIdHelper2(orderId);
            }
        }
        return new InnerClass().getOrderImpl(orderId);
    }

    private Order getOrderWithIdHelper2(final String orderId) {
        return orderService.getOrderWithId(orderId);
    }

    public Order getOrderWithId3(final String orderId) {
        class InnerClass {
            Order getOrderImpl(final String orderId) {
                return getOrderWithIdInnerClassHelper(orderId);
            }

            private Order getOrderWithIdInnerClassHelper(final String orderId) {
                return orderService.getOrderWithId(orderId);
            }
        }
        return new InnerClass().getOrderImpl(orderId);
    }

    public Order getOrderWithId4(final String orderId) {
        class InnerClass {
            Order getOrderImpl(final String orderId) {
                try {
                    final Order orderWithId = orderService.getOrderWithId(orderId);
                    // Note: The generated test will incorrectly include a verify statement for this in the exception case.
                    // The reason is: the DIs come before the callstack node that leads to this method.
                    metricService.recordSuccess(orderId);
                    return orderWithId;
                } catch (final Exception e) {
                    metricService.recordOrderException(e);
                    return null;
                }
            }
        }
        return new InnerClass().getOrderImpl(orderId);
    }

    public Order getOrderWithId5(final String orderId) {
        class InnerClass {
            Order getOrderImpl(final String orderId) {
                return orderService.getOrderWithId(orderId);
            }
        }
        try {
            final Order order = new InnerClass().getOrderImpl(orderId);
            metricService.recordSuccess(orderId);
            return order;
        } catch (final Exception e) {
            metricService.recordOrderException(e);
            return null;
        }
    }

    public Order getOrderWithId6(final String orderId) {
        try {
            class InnerClass {
                Order getOrderImpl(final String orderId) {
                    return orderService.getOrderWithId(orderId);
                }
            }
            final Order order = new InnerClass().getOrderImpl(orderId);
            metricService.recordSuccess(orderId);
            return order;
        } catch (final Exception e) {
            metricService.recordOrderException(e);
            return null;
        }
    }

    public Order getOrderWithId7(final String orderId) {
        try {
            class InnerClass {
                Order getOrderImpl(final String orderId) {
                    return orderService.getOrderWithId(orderId);
                }
            }
            final Order order = new InnerClass().getOrderImpl(orderId);
            metricService.recordSuccess(orderId);
            return order;
        } catch (final RuntimeException e) {
            metricService.recordOrderException(e);
            throw e;
        }
    }
}
