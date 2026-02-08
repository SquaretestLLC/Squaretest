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

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String orderId;
    private final String description;
    private final List<String> dataIds;
    private List<String> dataObjects;

    public Order(final String orderId, final String description, final List<String> dataIds) {
        this.orderId = orderId;
        this.description = description;
        this.dataIds = dataIds;
        this.dataObjects = new ArrayList<>();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getDataIds() {
        return dataIds;
    }

    public void addDataObject(final String dataObject) {
        dataObjects.add(dataObject);
    }

    public List<String> getDataObjects() {
        return dataObjects;
    }

    public void setDataObjects(final List<String> dataObjects) {
        this.dataObjects = dataObjects;
    }
}
