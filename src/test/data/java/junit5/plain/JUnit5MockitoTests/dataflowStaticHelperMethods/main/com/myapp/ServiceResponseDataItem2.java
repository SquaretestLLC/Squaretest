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

public class ServiceResponseDataItem2 {
    private final String serviceResponseDataItem2Id;
    private final String serviceResponseDataItem2Name;
    private final Date serviceResponseDataItem2CreatedDate;
    private final long serviceResponseDataItem2LongValue;

    public ServiceResponseDataItem2(
            final String serviceResponseDataItem2Id,
            final String serviceResponseDataItem2Name,
            final Date serviceResponseDataItem2CreatedDate, final long serviceResponseDataItem2LongValue) {
        this.serviceResponseDataItem2Id = serviceResponseDataItem2Id;
        this.serviceResponseDataItem2Name = serviceResponseDataItem2Name;
        this.serviceResponseDataItem2CreatedDate = serviceResponseDataItem2CreatedDate;
        this.serviceResponseDataItem2LongValue = serviceResponseDataItem2LongValue;
    }

    public String getServiceResponseDataItem2Id() {
        return serviceResponseDataItem2Id;
    }

    public String getServiceResponseDataItem2Name() {
        return serviceResponseDataItem2Name;
    }

    public Date getServiceResponseDataItem2CreatedDate() {
        return serviceResponseDataItem2CreatedDate;
    }

    public long getServiceResponseDataItem2LongValue() {
        return serviceResponseDataItem2LongValue;
    }
}
