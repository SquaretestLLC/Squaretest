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

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyClass {
    private final DynamoDbClient dynamoDbClient;
    private final MetricService metricService;

    public MyClass(
            final DynamoDbClient dynamoDbClient, final MetricService metricService) {
        this.dynamoDbClient = dynamoDbClient;
        this.metricService = metricService;
    }

    public void batchWrite1(final List<Order> orders) {
        final Map<String, List<WriteRequest>> requestMap = new HashMap<>();
        final List<WriteRequest> writeRequests = new ArrayList<>();
        for(final Order order : orders) {
            final WriteRequest writeRequest = WriteRequest.builder().putRequest(PutRequest.builder().item(convertToMap(order)).build()).build();
            writeRequests.add(writeRequest);
        }
        requestMap.put("Orders", writeRequests);
        final BatchWriteItemRequest request = BatchWriteItemRequest.builder().requestItems(requestMap).build();
        final BatchWriteItemResponse response = dynamoDbClient.batchWriteItem(request);
        if(response.hasUnprocessedItems()) {
            throw new BatchPutException(orders.get(0).getId());
        }
    }
    public void batchWrite2(final List<Order> orders) {
        final Map<String, List<WriteRequest>> requestMap = new HashMap<>();
        final List<WriteRequest> writeRequests = new ArrayList<>();
        for(final Order order : orders) {
            final WriteRequest writeRequest = WriteRequest.builder().putRequest(PutRequest.builder().item(convertToMap(order)).build()).build();
            writeRequests.add(writeRequest);
        }
        requestMap.put("Orders", writeRequests);
        final BatchWriteItemRequest request = BatchWriteItemRequest.builder().requestItems(requestMap).build();
        final BatchWriteItemResponse response = dynamoDbClient.batchWriteItem(request);
        if(!response.unprocessedItems().isEmpty()) {
            throw new BatchPutException(orders.get(0).getId());
        }
    }
    public void batchWrite3(final List<Order> orders) {
        final Map<String, List<WriteRequest>> requestMap = new HashMap<>();
        final List<WriteRequest> writeRequests = new ArrayList<>();
        for(final Order order : orders) {
            final WriteRequest writeRequest = WriteRequest.builder().putRequest(PutRequest.builder().item(convertToMap(order)).build()).build();
            writeRequests.add(writeRequest);
        }
        requestMap.put("Orders", writeRequests);
        final BatchWriteItemRequest request = BatchWriteItemRequest.builder().requestItems(requestMap).build();
        final BatchWriteItemResponse response = dynamoDbClient.batchWriteItem(request);
        for(final Map.Entry<String, List<WriteRequest>> entry : response.unprocessedItems().entrySet()) {
            metricService.recordFailedWrite(entry.getValue().get(0).putRequest().item().keySet().iterator().next());
        }
    }

    public void batchWrite4(final List<Order> orders) {
        final Map<String, List<WriteRequest>> requestMap = new HashMap<>();
        final List<WriteRequest> writeRequests = new ArrayList<>();
        for(final Order order : orders) {
            final WriteRequest writeRequest = WriteRequest.builder().putRequest(PutRequest.builder().item(convertToMap(order)).build()).build();
            writeRequests.add(writeRequest);
        }
        requestMap.put("Orders", writeRequests);
        final BatchWriteItemRequest request = BatchWriteItemRequest.builder().requestItems(requestMap).build();
        final BatchWriteItemResponse response = dynamoDbClient.batchWriteItem(request);
        for(final String entry : response.unprocessedItems().keySet()) {
            metricService.recordFailedWrite(entry);
        }
    }

    public void batchWrite5(final List<Order> orders) {
        final Map<String, List<WriteRequest>> requestMap = new HashMap<>();
        final List<WriteRequest> writeRequests = new ArrayList<>();
        for(final Order order : orders) {
            final WriteRequest writeRequest = WriteRequest.builder().putRequest(PutRequest.builder().item(convertToMap(order)).build()).build();
            writeRequests.add(writeRequest);
        }
        requestMap.put("Orders", writeRequests);
        final BatchWriteItemRequest request = BatchWriteItemRequest.builder().requestItems(requestMap).build();
        final BatchWriteItemResponse response = dynamoDbClient.batchWriteItem(request);
        for(final List<WriteRequest> entry : response.unprocessedItems().values()) {
            metricService.recordFailedWrite(entry.get(0).putRequest().item().keySet().iterator().next());
        }
    }

    private Map<String, AttributeValue> convertToMap(final Order order) {
        final Map<String, AttributeValue> ret = new HashMap<>();
        ret.put("id", AttributeValue.builder().s(order.getId()).build());
        ret.put("shipAddress", AttributeValue.builder().s(order.getShipAddress()).build());
        ret.put("shipDate", AttributeValue.builder().s(order.getShipDate().format(DateTimeFormatter.ISO_DATE)).build());
        ret.put("otherId", AttributeValue.builder().s(order.getOtherId()).build());
        return ret;
    }

}
