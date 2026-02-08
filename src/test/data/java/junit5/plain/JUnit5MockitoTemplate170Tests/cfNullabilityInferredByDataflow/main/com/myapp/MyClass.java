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

import java.util.Optional;

public class MyClass {
    private final OrderService orderService;

    public MyClass(final OrderService orderService) {
        this.orderService = orderService;
    }

    public String getOrderDescription1(final String orderId) {
        // Nullability should NOT be inferred.
        final Order order = orderService.getOrderWithId1(orderId);
        final String orderValue = convert(order);
        if(orderValue == null) {
            return "placeholder";
        }
        return orderValue;
    }

    public String getOrderDescription2(final String orderId) {
        final Order order;
        if(orderId == null) {
            // Nullability should NOT be inferred.
            order = orderService.getOrderWithId1(orderId);
        } else {
            order = null;
        }
        if(order != null) {
            return order.getDescription();
        }
        return "placeholder";
    }

    public String getOrderDescription3(final String orderId) {
        // Nullability for orderService.getOrderWithId1(..) should not be inferred.
        // There are two branches that can return null.
        final Order order = safeGetOrderHelper(orderId);
        if(order != null) {
            return order.getDescription();
        }
        return "placeholder";
    }

    public String getOrderDescription4(final String orderId) {
        // Nullability for orderService.getOrderWithId1(..) should be inferred.
        // There is only one branch through the internal helper method, and it leads to the DI call.
        final Order order = getOrderHelper1(orderId);
        if(order != null) {
            return order.getDescription();
        }
        return "placeholder";
    }

    public String getOrderDescription5(final String orderId) {
        // Nullability for orderService.getOrderWithId1(..) should be inferred.
        // There is only one branch through the internal helper method, and it leads to the DI call.
        return Optional.ofNullable(getOrderHelper2(orderId)).map(Order::getDescription).orElse("placeholder");
    }

    public String getOrderDescription6(final String orderId) {
        // Nullability for orderService.getOrderWithId1(..) should be inferred.
        // There is only one branch through the internal helper method, and it leads to the DI call.
        final Order order = getOrderHelper3(orderId);
        return Optional.ofNullable(order).map(Order::getDescription).orElse("placeholder");
    }

    public String getOrderDescription7(final String orderId) {
        // Nullability for orderService.getOrderWithId1(..) should be inferred.
        // There is only one branch through the internal helper method, and it leads to the DI call.
        final Order order = getOrderHelper4(orderId);
        return Optional.ofNullable(order).map(Order::getDescription).orElse("placeholder");
    }

    public String getOrderDescription8(final String orderId) {
        // Nullability for orderService.getOrderWithId1(..) should be inferred.
        // There is only one branch through the internal helper method, and it leads to the DI call.
        final Order firstOrder = getOrderHelper5(orderId);
        final Order order = firstOrder;
        return Optional.ofNullable(order).map(Order::getDescription).orElse("placeholder");
    }

    public String getOrderDescription9(final String orderId) {
        // Nullability for orderService.getOrderWithId1(..) should not be inferred.
        // Squaretest doesn't handle assignments within null checks; it should not crash, though.
        final Order order;
        return (order = getOrderHelper6(orderId)) != null ? order.getDescription() : "placeholder";
    }

    public String getOrderDescription10(final String orderId) {
        // Nullability for orderService.getOrderWithId1(..) should be inferred.
        // It is inffered by the call to getOrderHelper1(..) in getOrderDescription4(..).
        final Order order;
        return (order = getOrderHelper1(orderId)) != null ? order.getDescription() : "placeholder";
    }

    private Order safeGetOrderHelper(final String orderId) {
        try {
            return orderService.getOrderWithId1(orderId);
        } catch (final Exception ex) {
            return null;
        }
    }

    private Order getOrderHelper1(final String orderId) {
        if(orderId == null) {
            throw new NullPointerException("orderId");
        }
        return orderService.getOrderWithId2(orderId);
    }

    private Order getOrderHelper2(final String orderId) {
        if(orderId == null) {
            throw new NullPointerException("orderId");
        }
        return orderService.getOrderWithId2(orderId);
    }
    private Order getOrderHelper3(final String orderId) {
        if(orderId == null) {
            throw new NullPointerException("orderId");
        }
        return orderService.getOrderWithId2(orderId);
    }
    private Order getOrderHelper4(final String orderId) {
        if(orderId == null) {
            throw new NullPointerException("orderId");
        }
        return orderService.getOrderWithId2(orderId);
    }
    private Order getOrderHelper5(final String orderId) {
        if(orderId == null) {
            throw new NullPointerException("orderId");
        }
        return orderService.getOrderWithId2(orderId);
    }

    private Order getOrderHelper6(final String orderId) {
        if(orderId == null) {
            throw new NullPointerException("orderId");
        }
        return orderService.getOrderWithId2(orderId);
    }

    private String convert(final Order order) {
        if(order == null) {
            return null;
        }
        return order.getDescription();
    }
}
