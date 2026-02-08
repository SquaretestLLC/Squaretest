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
    private final OrderService altOrderService;
    private final MetricService metricService;

    public MyClass(
            final OrderService orderService,
            final OrderService altOrderService,
            final MetricService metricService) {
        this.orderService = orderService;
        this.altOrderService = altOrderService;
        this.metricService = metricService;
    }

    public void transferOrder1(final String orderId) {
        new Action() {
            @Override
            protected void doAction() {
                final Order order = orderService.getOrderWithId1(orderId);
                if(order != null)
                    altOrderService.putOrder(order);
            }
        }.perform();
    }

    public void transferOrder2(final String orderId) {
        new Action() {
            @Override
            protected void doAction() {
                final Order order = orderService.getOrderWithId1(orderId);
                if(order == null) {
                    return;
                }
                altOrderService.putOrder(order);
            }
        }.perform();
    }

    public void transferOrder3(final String orderId) {
        new Action() {
            @Override
            protected void doAction() {
                final Order order = orderService.getOrderWithId1(orderId);
                if(order == null) {
                    throw new OrderNotFoundException(orderId);
                }
                altOrderService.putOrder(order);
            }
        }.perform();
    }

}
