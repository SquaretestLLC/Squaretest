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
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class MyClass {
    private final AmazonDynamoDB amazonDynamoDB;

    public MyClass(final AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }

    public void putOrders1(final List<Order> orders) {
        final BatchWriteItemRequest batchWriteItemRequest = new BatchWriteItemRequest();
        final List<WriteRequest> requests = new ArrayList<>();
        for(final Order order : orders) {
            requests.add(new WriteRequest(new PutRequest().withItem(convertToMap(order))));
        }
        batchWriteItemRequest.addRequestItemsEntry("Orders", requests);
        final BatchWriteItemResult result = amazonDynamoDB.batchWriteItem(batchWriteItemRequest);
        if(!result.getUnprocessedItems().isEmpty()) {
            throw new BatchWriteException(orders.get(0).getId());
        }
    }

    private Map<String, AttributeValue> convertToMap(final Order order) {
        final Map<String, AttributeValue> ret = new HashMap<>();
        ret.put("id", new AttributeValue(order.getId()));
        ret.put("shipAddress", new AttributeValue(order.getShipAddress()));
        ret.put("shipDate", new AttributeValue(order.getShipDate().format(ISO_LOCAL_DATE)));
        ret.put("otherId", new AttributeValue(order.getOtherId()));
        return ret;
    }
}
