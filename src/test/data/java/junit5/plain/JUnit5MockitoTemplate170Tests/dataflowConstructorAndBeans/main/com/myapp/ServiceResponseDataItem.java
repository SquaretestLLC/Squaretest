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

import java.util.Date;

public class ServiceResponseDataItem {
    private final String serviceResponseDataItemId;
    private final String serviceResponseDataItemName;
    private final Date serviceResponseDataItemCreatedDate;
    private final long serviceResponseDataItemLongValue;

    public ServiceResponseDataItem(
            final String serviceResponseDataItemId,
            final String serviceResponseDataItemName,
            final Date serviceResponseDataItemCreatedDate, final long serviceResponseDataItemLongValue) {
        this.serviceResponseDataItemId = serviceResponseDataItemId;
        this.serviceResponseDataItemName = serviceResponseDataItemName;
        this.serviceResponseDataItemCreatedDate = serviceResponseDataItemCreatedDate;
        this.serviceResponseDataItemLongValue = serviceResponseDataItemLongValue;
    }

    public String getServiceResponseDataItemId() {
        return serviceResponseDataItemId;
    }

    public String getServiceResponseDataItemName() {
        return serviceResponseDataItemName;
    }

    public Date getServiceResponseDataItemCreatedDate() {
        return serviceResponseDataItemCreatedDate;
    }

    public long getServiceResponseDataItemLongValue() {
        return serviceResponseDataItemLongValue;
    }
}
