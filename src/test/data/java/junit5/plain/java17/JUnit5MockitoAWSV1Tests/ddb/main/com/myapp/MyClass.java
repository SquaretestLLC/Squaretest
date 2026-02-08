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

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyClass {
    private final AmazonDynamoDB amazonDynamoDB;
    private final MetricService metricService;

    public MyClass(final AmazonDynamoDB amazonDynamoDB, final MetricService metricService) {
        this.amazonDynamoDB = amazonDynamoDB;
        this.metricService = metricService;
    }

    public List<Order> getOrders1(final String criteria) {
        final List<Order> ret = new ArrayList<>();
        final ScanResult scanResult = amazonDynamoDB.scan(new ScanRequest("Orders").withFilterExpression(criteria));
        for (final Map<String, AttributeValue> item : scanResult.getItems()) {
            final Order parsedOrder = convert(item);
            metricService.recordOrder(parsedOrder.getId());
            ret.add(parsedOrder);
        }
        return ret;
    }

    private Order convert(final Map<String, AttributeValue> item) {
        final Order ret = new Order();
        ret.setId(item.get("id").getS());
        ret.setShipAddress(item.get("shipAddress").getS());
        ret.setShipDate(LocalDateTime.parse(item.get("shipDate").getS()));
        ret.setOtherId(item.get("otherId").getS());
        return ret;
    }
}
