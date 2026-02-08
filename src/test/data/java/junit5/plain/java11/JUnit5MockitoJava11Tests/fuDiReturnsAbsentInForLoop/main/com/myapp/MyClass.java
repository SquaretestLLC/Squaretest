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
    private final OrderDataService orderDataService;
    private final MetricService metricService;

    public MyClass(
            final OrderService orderService, final OrderDataService orderDataService,
            final MetricService metricService) {
        this.orderService = orderService;
        this.orderDataService = orderDataService;
        this.metricService = metricService;
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
        final Order order = orderService.getOrderWithId1(orderId);
        if(order == null) {
            return null;
        }
        for(final String dataId : order.getDataIds()) {
            final String dataObject = orderDataService.getOrderData(dataId);
            if(dataObject == null) {
                // Bail out.
                /*
                 * Test long comment.
                 */
                return null;
            }
            order.addDataObject(dataObject);
        }
        metricService.recordEndOfGetOrderMethod(orderId);
        return order;
    }

    public Order getOrder3(final String orderId) {
        final Order order = orderService.getOrderWithId1(orderId);
        if(order == null) {
            return null;
        }
        for(final String dataId : order.getDataIds()) {
            final String dataObject = orderDataService.getOrderData(dataId);
            if(dataObject == null) {
                break;
            }
            order.addDataObject(dataObject);
        }
        metricService.recordEndOfGetOrderMethod(orderId);
        return order;
    }

    public Order getOrder4(final String orderId) {
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

    public Order getOrder5(final String orderId) {
        final Order order = orderService.getOrderWithId1(orderId);
        if(order == null) {
            return null;
        }
        for(final String dataId : order.getDataIds()) {
            final String dataObject = orderDataService.getOrderData(dataId);
            if(dataObject != null) {
                order.addDataObject(dataObject);
                continue;
            }
            throw new OrderDataIntegrityException(String.format("Data not found for orderId=%s, dataId=%s", orderId, dataId));
        }
        metricService.recordEndOfGetOrderMethod(orderId);
        return order;
    }

    public Order getOrder6(final String orderId) {
        final Order order = orderService.getOrderWithId1(orderId);
        if(order == null) {
            return null;
        }
        for(final String dataId : order.getDataIds()) {
            final String dataObject = orderDataService.getOrderData(dataId);
            if(dataObject != null) {
                order.addDataObject(dataObject);
            } else {
                throw new OrderDataIntegrityException(String.format("Data not found for orderId=%s, dataId=%s", orderId, dataId));
            }
        }
        metricService.recordEndOfGetOrderMethod(orderId);
        return order;
    }

    public Order getOrder7(final String orderId) {
        final Order order = orderService.getOrderWithId1(orderId);
        if(order == null) {
            return null;
        }
        for(final String dataId : order.getDataIds()) {
            final String dataObject = orderDataService.getOrderData(dataId);
            if(dataObject != null) {
                order.addDataObject(dataObject);
            } else if(orderId.startsWith("FU")) {
                metricService.recordFUOrderWithoutData(orderId);
            } else {
                throw new OrderDataIntegrityException(String.format("Data not found for orderId=%s, dataId=%s", orderId, dataId));
            }
        }
        metricService.recordEndOfGetOrderMethod(orderId);
        return order;
    }

    public Order getOrder8(final String orderId) {
        final Order order = orderService.getOrderWithId1(orderId);
        if(order == null) {
            return null;
        }
        for(final String dataId : order.getDataIds()) {
            final String dataObject = orderDataService.getOrderData(dataId);
            order.addDataObject(dataObject);
        }
        metricService.recordEndOfGetOrderMethod(orderId);
        return order;
    }

    public Order getOrder9(final String orderId) {
        final Order order = orderService.getOrderWithId1(orderId);
        if(order == null) {
            return null;
        }
        for(final String dataId : order.getDataIds()) {
            order.addDataObject(orderDataService.getOrderData(dataId));
        }
        metricService.recordEndOfGetOrderMethod(orderId);
        return order;
    }
}
