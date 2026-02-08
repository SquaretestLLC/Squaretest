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

import java.util.HashMap;
import java.util.Optional;

public class MyClass {
    private final ActionService actionService;
    private final OrderService orderService;
    private final OrderService altOrderService;
    private final MetricService metricService;

    public MyClass(
            final ActionService actionService,
            final OrderService orderService,
            final OrderService altOrderService,
            final MetricService metricService) {
        this.actionService = actionService;
        this.orderService = orderService;
        this.altOrderService = altOrderService;
        this.metricService = metricService;
    }

    public void transferOrder1(final String orderId) {
        new Action(actionService.getActionId1(orderId).orElse(orderId), actionService.getActionGroup1(orderId).orElse(orderId), new HashMap<>(){
            {
                put("OrderIdKey", actionService.getKeyForOrder1(orderId).orElse(orderId));
            }
        }) {
            @Override
            protected void doAction() {
                final Order order = orderService.getOrderWithId1(orderId);
                if(order != null) {
                    altOrderService.putOrder(order);
                }
            }
        }.perform();
    }
    public void transferOrder2(final String orderId) {
        new Action(actionService.getActionId1(orderId).orElseThrow(), actionService.getActionGroup1(orderId).orElseThrow(), new HashMap<>(){
            {
                put("OrderIdKey", actionService.getKeyForOrder1(orderId).orElseThrow());
            }
        }) {
            @Override
            protected void doAction() {
                final Order order = orderService.getOrderWithId1(orderId);
                if(order != null) {
                    altOrderService.putOrder(order);
                }
            }
        }.perform();
    }

    public void transferOrder3(final String orderId) {
        new Action(actionService.getActionId1(orderId).orElse(orderId), actionService.getActionGroup1(orderId).orElse(orderId), new HashMap<>(){
            {
                final Optional<String> keyForOrderOpt = actionService.getKeyForOrder1(orderId);
                if(keyForOrderOpt.isPresent()) {
                    put("OrderIdKey", keyForOrderOpt.get());
                    metricService.recordActionServiceKeyForOrderFound1(orderId);
                }
            }
        }) {
            @Override
            protected void doAction() {
                final Order order = orderService.getOrderWithId1(orderId);
                if(order != null) {
                    altOrderService.putOrder(order);
                }
            }
        }.perform();
    }

    public void transferOrder4(final String orderId) {
        new Action(actionService.getActionId1(orderId).orElse(orderId), actionService.getActionGroup1(orderId).orElse(orderId), new HashMap<>(){
            // Member within anon inner class inside the param, in a constructor call of an anon inner class.
            private final Optional<String> baseId = actionService.getBaseIdForAction1(orderId);
            private final String altBaseId = actionService.getAltBaseIdForAction1(orderId);
            // Init block within anon inner class inside the param, in a constructor call of an anon inner class.
            {
                final Optional<String> keyForOrderOpt = actionService.getKeyForOrder1(orderId);
                if(keyForOrderOpt.isPresent()) {
                    put("OrderIdKey", keyForOrderOpt.get());
                    metricService.recordActionServiceKeyForOrderFound1(orderId);
                    if(baseId.isPresent()) {
                        // Note: this won't be excluded correctly in the absent case in the generated tests.
                        metricService.recordActionBaseKeyForOrder1(baseId.get());
                    } else {
                        metricService.recordAltBaseIdForOrder1(altBaseId);
                    }
                }
            }
            // Test more local vars and init blocks after local vars and init blocks.
            private final String thirdBaseId = actionService.getThirdBaseIdForAction1(orderId);
            {
                metricService.recordThirdBaseIdForOrder(thirdBaseId);
            }
        }) {
            private final Optional<String> baseId = actionService.getBaseIdForAction2(orderId);
            private final String altBaseId = actionService.getAltBaseIdForAction2(orderId);
            {
                final Optional<String> keyForOrderOpt = actionService.getKeyForOrder2(orderId);
                if(keyForOrderOpt.isPresent()) {
                    params.put("OrderIdKey2", keyForOrderOpt.get());
                    metricService.recordActionServiceKeyForOrderFound2(orderId);
                    if(baseId.isPresent()) {
                        // Note: this won't be excluded correctly in the absent case in the generated tests.
                        metricService.recordActionBaseKeyForOrder2(baseId.get());
                    } else {
                        metricService.recordAltBaseIdForOrder2(altBaseId);
                    }
                }
            }
            @Override
            protected void doAction() {
                final Order order = orderService.getOrderWithId1(orderId);
                if(order != null) {
                    altOrderService.putOrder(order);
                }
            }

            @Override
            protected void cleanUp() {
                final Order order = altOrderService.getOrderWithId1(orderId);
                if(order == null) {
                    metricService.recordNullOrderFromAltService(orderId);
                }
            }
        }.perform();
    }

    public void transferOrder5(final String orderId) {
        new Action(actionService.getActionId1(orderId).orElse(orderId), actionService.getActionGroup1(orderId).orElse(orderId), new HashMap<>(){
            // Member within anon inner class inside the param, in a constructor call of an anon inner class.
            private final String baseId = actionService.getBaseIdForAction1(orderId).orElseThrow();
            private final String altBaseId = actionService.getAltBaseIdForAction1(orderId);
            // Init block within anon inner class inside the param, in a constructor call of an anon inner class.
            {
                final String keyForOrderOpt = actionService.getKeyForOrder1(orderId).orElseThrow();
                put("OrderIdKey", keyForOrderOpt);
                metricService.recordActionServiceKeyForOrderFound1(orderId);
                metricService.recordActionBaseKeyForOrder1(baseId);
            }
            // Test more local vars and init blocks after local vars and init blocks.
            private final String thirdBaseId = actionService.getThirdBaseIdForAction1(orderId);
            {
                metricService.recordThirdBaseIdForOrder(thirdBaseId);
            }
        }) {
            private final String baseId = actionService.getBaseIdForAction2(orderId).orElseThrow();
            private final String altBaseId = actionService.getAltBaseIdForAction2(orderId);
            {
                final String keyForOrderOpt = actionService.getKeyForOrder2(orderId).orElseThrow();
                params.put("OrderIdKey2", keyForOrderOpt);
                metricService.recordActionServiceKeyForOrderFound2(orderId);
                // Note: this won't be excluded correctly in the absent case in the generated tests.
                metricService.recordActionBaseKeyForOrder2(baseId);
            }
            @Override
            protected void doAction() {
                final Order order = orderService.getOrderWithId1(orderId);
                if(order != null) {
                    altOrderService.putOrder(order);
                } else {
                    throw new OrderNotFoundException(orderId);
                }
            }

            @Override
            protected void cleanUp() {
                final Order order = altOrderService.getOrderWithId1(orderId);
                if(order == null) {
                    metricService.recordNullOrderFromAltService(orderId);
                    throw new OrderNotStoredException(orderId);
                }
                metricService.recordOrderStoredSuccessfully(orderId);
            }
        }.perform();
    }

    public void transferOrder6(final String orderId) {
        new Action(actionService.getActionId1(orderId).orElse(orderId), actionService.getActionGroup1(orderId).orElse(orderId), new HashMap<>(){
            // Member within anon inner class inside the param, in a constructor call of an anon inner class.
            private final Optional<String> baseId = actionService.getBaseIdForAction1(orderId);
            private final String altBaseId = actionService.getAltBaseIdForAction1(orderId);
            // Init block within anon inner class inside the param, in a constructor call of an anon inner class.
            {
                final Optional<String> keyForOrderOpt = actionService.getKeyForOrder1(orderId);
                if(keyForOrderOpt.isPresent()) {
                    throw new OrderKeyException1(orderId);
                }
                metricService.recordActionServiceKeyForOrderFound1(orderId);
                if(baseId.isPresent()) {
                    // Note: this won't be excluded correctly in the absent case in the generated tests.
                    metricService.recordActionBaseKeyForOrder1(baseId.get());
                } else {
                    metricService.recordAltBaseIdForOrder1(altBaseId);
                }
            }
            // Test more local vars and init blocks after local vars and init blocks.
            private final String thirdBaseId = actionService.getThirdBaseIdForAction1(orderId);
            {
                metricService.recordThirdBaseIdForOrder(thirdBaseId);
            }
        }) {
            private final Optional<String> baseId = actionService.getBaseIdForAction2(orderId);
            private final String altBaseId = actionService.getAltBaseIdForAction2(orderId);
            {
                final Optional<String> keyForOrderOpt = actionService.getKeyForOrder2(orderId);
                if(keyForOrderOpt.isPresent()) {
                    throw new OrderKeyException2(orderId);
                }
                metricService.recordActionServiceKeyForOrderFound2(orderId);
                if(baseId.isPresent()) {
                    // Note: this won't be excluded correctly in the absent case in the generated tests.
                    metricService.recordActionBaseKeyForOrder2(baseId.get());
                } else {
                    metricService.recordAltBaseIdForOrder2(altBaseId);
                }
            }
            @Override
            protected void doAction() {
                final Order order = orderService.getOrderWithId1(orderId);
                if(order != null) {
                    throw new OrderAlreadyExistsException(orderId);
                }
                altOrderService.putOrder(order);
            }

            @Override
            protected void cleanUp() {
                final Order order = altOrderService.getOrderWithId1(orderId);
                if(order != null) {
                    throw new OrderAlreadyExistsException(orderId);
                }
                metricService.recordNullOrderFromAltService(orderId);
            }
        }.perform();
    }
}
