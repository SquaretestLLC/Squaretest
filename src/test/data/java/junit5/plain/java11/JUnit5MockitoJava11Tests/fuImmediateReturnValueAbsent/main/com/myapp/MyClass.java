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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MyClass {
    private final OrderService orderService;
    private final MetricService metricService;

    public MyClass(final OrderService orderService, final MetricService metricService) {
        this.orderService = orderService;
        this.metricService = metricService;
    }

    public Order getOrderWithId1(final String orderId) {
        return orderService.getOrderWithId1(orderId);
    }

    public Order getOrderWithId2(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).get();
    }

    public Order getOrderWithId3(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).orElseThrow();
    }

    public Order getOrderWithId4(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).orElse(null);
    }

    public Order getOrderWithId5(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).orElseGet(() -> new Order("defaultOrderId", "defaultDescription"));
    }

    public Order getOrderWithId6(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).orElseGet(() -> null);
    }

    public Order getOrderWithId7(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).orElseThrow(() -> new RuntimeException("order not found"));
    }
    
    public Order getOrderWithId8(final String orderId) {
        return convertOrDefaultHelper1(orderService.getOrderWithId1(orderId));
    }

    public OrderData getOrderWithId9(final String orderId) {
        return new OrderData(orderService.getOrderWithId1(orderId));
    }

    public List<Order> getOrderWithId10(final String orderId) {
        return Arrays.asList(orderService.getOrderWithId1(orderId));
    }

    public Order getOrderWithId11(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).or(() -> Optional.ofNullable(orderService.getOrderWithId2(orderId))).get();
    }

    public Order getOrderWithId12(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).or(() -> Optional.ofNullable(orderService.getOrderWithId2(orderId))).orElseThrow();
    }

    public Order getOrderWithId13(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).or(() -> Optional.ofNullable(orderService.getOrderWithId2(orderId))).orElse(null);
    }

    public Order getOrderWithId14(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).or(() -> Optional.ofNullable(orderService.getOrderWithId2(orderId))).orElseGet(() -> new Order("defaultOrderId", "defaultDescription"));
    }

    public Order getOrderWithId15(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).or(() -> Optional.ofNullable(orderService.getOrderWithId2(orderId))).orElseGet(() -> null);
    }

    public Order getOrderWithId16(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).or(() -> Optional.ofNullable(orderService.getOrderWithId2(orderId))).orElseThrow(() -> new RuntimeException("order not found"));
    }

    public Order getOrderWithId17(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).orElseGet(() -> orderService.getOrderWithId2(orderId));
    }

    public Order getOrderWithId18(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).orElseGet(() -> getOrderHelper2(orderId));
    }

    public Order getOrderWithId19(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).orElseThrow(() -> {
            metricService.recordOrElseThrowBlock(orderId);
            return new RuntimeException("order not found");
        });
    }

    public Order getOrderWithId20(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).orElseGet(() -> {
            metricService.recordCallingGetOrderHelper3(orderId);
            return getOrderHelper3(orderId);
        });
    }

    public Order getOrderWithId21(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).orElseGet(() -> {
            return orderService.getOrderWithId2(orderId);
        });
    }

    public Order getOrderWithId22(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).orElseGet(() -> {
            return getOrderHelper2(orderId);
        });
    }

    private Order getOrderHelper2(final String orderId) {
        metricService.recordCallingGetOrderHelper2(orderId);
        return orderService.getOrderWithId2(orderId);
    }

    private Order getOrderHelper3(final String orderId) {
        return orderService.getOrderWithId2(orderId);
    }

    private Order convertOrDefaultHelper1(final Order order) {
        if(order == null) {
            return null;
        }
        return new Order(order.getOrderId() + "_Int", order.getDescription());
    }

    public String getOrderDescription1(final String orderId) {
        return orderService.getOrderWithId1(orderId).getDescription();
    }

    public String getOrderDescription2(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).map(Order::getDescription).get();
    }

    public String getOrderDescription3(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).map(Order::getDescription).orElseThrow();
    }

    public String getOrderDescription4(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).map(Order::getDescription).orElse(null);
    }

    public String getOrderDescription5(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).map(Order::getDescription).orElseGet(() -> "defaultDescription");
    }

    public String getOrderDescription6(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).map(Order::getDescription).orElseGet(() -> null);
    }

    public String getOrderDescription7(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).map(Order::getDescription).orElseThrow(() -> new RuntimeException("order not found"));
    }

    public String getOrderDescription8(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).map(Order::getDescription).orElse("defaultDescription");
    }

    public String getOrderDescription9(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).map(this::getDescriptionHelper).get();
    }

    public String getOrderDescription10(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).map(this::getDescriptionHelper).orElseThrow();
    }

    public String getOrderDescription11(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).map(this::getDescriptionHelper).orElse(null);
    }

    public String getOrderDescription12(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).map(this::getDescriptionHelper).orElseGet(() -> "defaultDescription");
    }

    public String getOrderDescription13(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).map(this::getDescriptionHelper).orElseGet(() -> null);
    }

    public String getOrderDescription14(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).map(this::getDescriptionHelper).orElseThrow(() -> new RuntimeException("order not found"));
    }

    public String getOrderDescription15(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).map(this::getDescriptionHelper).orElse("defaultDescription");
    }

    private String getDescriptionHelper(final Order order) {
        metricService.recordGetDescriptionHelper(order);
        return order.getDescription();
    }

    public boolean canCreate1(final String orderId) {
        return orderService.getOrderWithId1(orderId) == null;
    }

    public boolean canCreate2(final String orderId) {
        return !(orderService.getOrderWithId1(orderId) != null);
    }

    public boolean canCreate3(final String orderId) {
        return !Optional.ofNullable(orderService.getOrderWithId1(orderId)).isPresent();
    }

    public boolean canCreate4(final String orderId) {
        return Optional.ofNullable(orderService.getOrderWithId1(orderId)).isEmpty();
    }

    public boolean exists1(final String orderId) {
        return orderService.getOrderWithId1(orderId) != null;
    }
}
