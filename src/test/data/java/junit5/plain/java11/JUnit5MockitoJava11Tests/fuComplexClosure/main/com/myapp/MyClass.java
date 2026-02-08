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

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class MyClass {
    private final OrderService orderService;
    private final OrderDataService orderDataService;
    private final MetricService metricService;

    public MyClass(
            final OrderService orderService, final OrderDataService orderDataService,
            final MetricService metricService) {
        this.orderService = orderService;
        this.orderDataService = orderDataService;
        this.metricService = metricService;
    }

    public CompletableFuture<Order> getOrderAsync1(final String orderId) {
        return CompletableFuture.supplyAsync(new Supplier<Order>() {
            @Override
            public Order get() {
                return getOrder1(orderId);
            }
        });
    }

    public CompletableFuture<Order> getOrderAsync2(final String orderId) {
        return CompletableFuture.supplyAsync(() -> getOrder1(orderId));
    }

    public CompletableFuture<Order> getOrderAsync3(final String orderId) {
        return CompletableFuture.supplyAsync(new Supplier<Order>() {
            @Override
            public Order get() {
                final Order order = orderService.getOrderWithId1(orderId);
                if(order == null) {
                    return null;
                }
                for(final String dataId : order.getDataIds()) {
                    final String dataObject = orderDataService.getOrderData(dataId);
                    if(dataObject == null) {
                        continue;
                    }
                    order.addDataObject(dataObject);
                }
                metricService.recordEndOfGetOrderMethod(orderId);
                return order;
            }
        });
    }

    public CompletableFuture<Order> getOrderAsync4(final String orderId) {
        return CompletableFuture.supplyAsync(() -> {
            final Order order = orderService.getOrderWithId1(orderId);
            if(order == null) {
                return null;
            }
            for(final String dataId : order.getDataIds()) {
                final String dataObject = orderDataService.getOrderData(dataId);
                if(dataObject == null) {
                    continue;
                }
                order.addDataObject(dataObject);
            }
            metricService.recordEndOfGetOrderMethod(orderId);
            return order;
        });
    }

    public CompletableFuture<Order> getOrderAsync5(final String orderId) {
        return CompletableFuture.supplyAsync(new Supplier<Order>() {
            @Override
            public Order get() {
                final Order order = orderService.getOrderWithId1(orderId);
                if(order == null) {
                    return null;
                }
                for(final String dataId : order.getDataIds()) {
                    final String dataObject = orderDataService.getOrderData(dataId);
                    if(dataObject == null) {
                        throw new OrderDataIntegrityException(String.format("Data not found for orderId=%s, dataId=%s", orderId, dataId));
                    }
                    order.addDataObject(dataObject);
                }
                metricService.recordEndOfGetOrderMethod(orderId);
                return order;
            }
        });
    }

    public CompletableFuture<Order> getOrderAsync6(final String orderId) {
        return CompletableFuture.supplyAsync(() -> {
            final Order order = orderService.getOrderWithId1(orderId);
            if(order == null) {
                return null;
            }
            for(final String dataId : order.getDataIds()) {
                final String dataObject = orderDataService.getOrderData(dataId);
                if(dataObject == null) {
                    throw new OrderDataIntegrityException(String.format("Data not found for orderId=%s, dataId=%s", orderId, dataId));
                }
                order.addDataObject(dataObject);
            }
            metricService.recordEndOfGetOrderMethod(orderId);
            return order;
        });
    }

    public CompletableFuture<Order> getOrderAsync7(final String orderId) {
        final CompletableFuture<Order> future = CompletableFuture.supplyAsync(new Supplier<Order>() {
            @Override
            public Order get() {
                return getOrder1(orderId);
            }
        });
        metricService.recordEndOfAsyncMethod(orderId);
        return future;
    }

    public CompletableFuture<Order> getOrderAsync8(final String orderId) {
        final CompletableFuture<Order> future = CompletableFuture.supplyAsync(() -> getOrder1(orderId));
        metricService.recordEndOfAsyncMethod(orderId);
        return future;
    }

    public CompletableFuture<Order> getOrderAsync9(final String orderId) {
        final CompletableFuture<Order> future = CompletableFuture.supplyAsync(new Supplier<Order>() {
            @Override
            public Order get() {
                final Order order = orderService.getOrderWithId1(orderId);
                if (order == null) {
                    return null;
                }
                for (final String dataId : order.getDataIds()) {
                    final String dataObject = orderDataService.getOrderData(dataId);
                    if (dataObject == null) {
                        continue;
                    }
                    order.addDataObject(dataObject);
                }
                metricService.recordEndOfGetOrderMethod(orderId);
                return order;
            }
        });
        metricService.recordEndOfAsyncMethod(orderId);
        return future;
    }

    public CompletableFuture<Order> getOrderAsync10(final String orderId) {
        final CompletableFuture<Order> future = CompletableFuture.supplyAsync(() -> {
            final Order order = orderService.getOrderWithId1(orderId);
            if (order == null) {
                return null;
            }
            for (final String dataId : order.getDataIds()) {
                final String dataObject = orderDataService.getOrderData(dataId);
                if (dataObject == null) {
                    continue;
                }
                order.addDataObject(dataObject);
            }
            metricService.recordEndOfGetOrderMethod(orderId);
            return order;
        });
        metricService.recordEndOfAsyncMethod(orderId);
        return future;
    }

    public CompletableFuture<Order> getOrderAsync11(final String orderId) {
        final CompletableFuture<Order> future = CompletableFuture.supplyAsync(new Supplier<Order>() {
            @Override
            public Order get() {
                final Order order = orderService.getOrderWithId1(orderId);
                if (order == null) {
                    return null;
                }
                for (final String dataId : order.getDataIds()) {
                    final String dataObject = orderDataService.getOrderData(dataId);
                    if (dataObject == null) {
                        throw new OrderDataIntegrityException(String.format("Data not found for orderId=%s, dataId=%s", orderId, dataId));
                    }
                    order.addDataObject(dataObject);
                }
                metricService.recordEndOfGetOrderMethod(orderId);
                return order;
            }
        });
        metricService.recordEndOfAsyncMethod(orderId);
        return future;
    }

    public CompletableFuture<Order> getOrderAsync12(final String orderId) {
        final CompletableFuture<Order> future = CompletableFuture.supplyAsync(() -> {
            final Order order = orderService.getOrderWithId1(orderId);
            if (order == null) {
                return null;
            }
            for (final String dataId : order.getDataIds()) {
                final String dataObject = orderDataService.getOrderData(dataId);
                if (dataObject == null) {
                    throw new OrderDataIntegrityException(String.format("Data not found for orderId=%s, dataId=%s", orderId, dataId));
                }
                order.addDataObject(dataObject);
            }
            metricService.recordEndOfGetOrderMethod(orderId);
            return order;
        });
        metricService.recordEndOfAsyncMethod(orderId);
        return future;
    }

    public Order getOrder1(final String orderId) {
        final Order order = orderService.getOrderWithId1(orderId);
        if(order == null) {
            return null;
        }
        for(final String dataId : order.getDataIds()) {
            final String dataObject = orderDataService.getOrderData(dataId);
            if(dataObject == null) {
                continue;
            }
            order.addDataObject(dataObject);
        }
        metricService.recordEndOfGetOrderMethod(orderId);
        return order;
    }

    public Order getOrder2(final String orderId) {
        // The DIs are in a closure, but this works correctly, because IntelliJ correctly follows the call to get(),
        // to the anon inner class's get method. The get() method is the callstack method in the analysis.
        return new Supplier<Order>() {
            @Override
            public Order get() {
                final Order order = orderService.getOrderWithId1(orderId);
                if(order == null) {
                    return null;
                }
                for(final String dataId : order.getDataIds()) {
                    final String dataObject = orderDataService.getOrderData(dataId);
                    if(dataObject == null) {
                        continue;
                    }
                    order.addDataObject(dataObject);
                }
                metricService.recordEndOfGetOrderMethod(orderId);
                return order;
            }
        }.get();
    }

    public Order getOrder3(final String orderId) {
        // The algorithm bails out, because the DIs are in a closure.
        return ((Supplier<Order>) () -> {
            final Order order = orderService.getOrderWithId1(orderId);
            if (order == null) {
                return null;
            }
            for (final String dataId : order.getDataIds()) {
                final String dataObject = orderDataService.getOrderData(dataId);
                if (dataObject == null) {
                    continue;
                }
                order.addDataObject(dataObject);
            }
            metricService.recordEndOfGetOrderMethod(orderId);
            return order;
        }).get();
    }
}
