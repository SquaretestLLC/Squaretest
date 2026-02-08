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

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MyClass {
    private final DynamoDBMapper dynamoDBMapper;
    private final MetricService metricService;
    private final OtherService otherService;

    public MyClass(final DynamoDBMapper dynamoDBMapper, final MetricService metricService, final OtherService otherService) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricService = metricService;
        this.otherService = otherService;
    }

    public List<Order> getOrders1(final String criteria) {
        final PaginatedParallelScanList<Order> orders = dynamoDBMapper.parallelScan(Order.class, new DynamoDBScanExpression().withFilterExpression(criteria), 10);
        return orders;
    }
    public List<OrderAndOther> getOrders2(final String criteria) {
        final PaginatedParallelScanList<Order> orders = dynamoDBMapper.parallelScan(Order.class, new DynamoDBScanExpression().withFilterExpression(criteria), 10);
        return orders.stream().mapMulti(new BiConsumer<Order, Consumer<OrderAndOther>>() {
            @Override
            public void accept(final Order order, final Consumer<OrderAndOther> outputConsumer) {
                final String otherData = otherService.getOtherData(order.getOtherId());
                if(otherData != null) {
                    outputConsumer.accept(new OrderAndOther(order, otherData));
                }
            }
        }).toList();
    }

    public List<OrderAndOther> getOrders3(final String criteria) {
        final PaginatedParallelScanList<Order> orders = dynamoDBMapper.parallelScan(Order.class, new DynamoDBScanExpression().withFilterExpression(criteria), 10);
        return orders.stream().mapMulti((BiConsumer<Order, Consumer<OrderAndOther>>) (order, outputConsumer) -> {
            final String otherData = otherService.getOtherData(order.getOtherId());
            if(otherData != null) {
                outputConsumer.accept(new OrderAndOther(order, otherData));
            }
        }).toList();
    }

    public List<OrderAndOther> getOrders4(final String criteria) {
        final PaginatedParallelScanList<Order> orders = dynamoDBMapper.parallelScan(Order.class, new DynamoDBScanExpression().withFilterExpression(criteria), 10);
        return orders.stream().map(order -> {
            final String otherData = otherService.getOtherData(order.getOtherId());
            if(otherData != null) {
                return new OrderAndOther(order, otherData);
            }
            return null;
        }).filter(Objects::nonNull).toList();
    }

    public List<OrderAndOther> getOrders5(final String criteria) {
        final PaginatedScanList<Order> orders = dynamoDBMapper.scan(Order.class, new DynamoDBScanExpression().withFilterExpression(criteria));
        return orders.stream().map(order -> {
            final String otherData = otherService.getOtherData(order.getOtherId());
            if(otherData != null) {
                return new OrderAndOther(order, otherData);
            }
            return null;
        }).filter(Objects::nonNull).toList();
    }
    public List<OrderAndOther> getOrders6(final String criteria) {
        final List<OrderAndOther> ret = new ArrayList<>();
        final DynamoDBScanExpression scanPageExpression = new DynamoDBScanExpression()
                .withFilterExpression(criteria)
                .withLimit(10);
        do {
            ScanResultPage<Order> scanPage = dynamoDBMapper.scanPage(Order.class, scanPageExpression);
            for(final Order order : scanPage.getResults()) {
                final String otherData = otherService.getOtherData(order.getOtherId());
                if(otherData != null) {
                    ret.add(new OrderAndOther(order, otherData));
                }
            }
            final Map<String, AttributeValue> lastEvaluatedKey = scanPage.getLastEvaluatedKey();
            if(lastEvaluatedKey != null) {
                metricService.recordPaginatedResults(criteria);
            }
            scanPageExpression.setExclusiveStartKey(lastEvaluatedKey);
        } while (scanPageExpression.getExclusiveStartKey() != null);
        return ret;
    }
}
