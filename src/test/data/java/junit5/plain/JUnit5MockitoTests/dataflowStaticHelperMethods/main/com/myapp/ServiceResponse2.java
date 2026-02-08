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
import java.util.List;

public class ServiceResponse2 {

    public static class ServiceResponse2Part {
        private final String serviceResponse2PartId;
        private final String serviceResponse2PartName;

        public ServiceResponse2Part(String serviceResponse2PartId, String serviceResponse2PartName) {
            this.serviceResponse2PartId = serviceResponse2PartId;
            this.serviceResponse2PartName = serviceResponse2PartName;
        }

        public String getServiceResponse2PartId() {
            return serviceResponse2PartId;
        }

        public String getServiceResponse2PartName() {
            return serviceResponse2PartName;
        }
    }

    private final String serviceResponse2Id;
    private final String serviceResponse2Name;
    private final List<ServiceResponse2Part> serviceResponse2Parts;
    private final Date serviceResponse2CreatedDate;
    private final long serviceResponse2LongValue;

    public ServiceResponse2(String serviceResponse2Id, String serviceResponse2Name, List<ServiceResponse2Part> serviceResponse2Parts, Date serviceResponse2CreatedDate, long serviceResponse2LongValue) {
        this.serviceResponse2Id = serviceResponse2Id;
        this.serviceResponse2Name = serviceResponse2Name;
        this.serviceResponse2Parts = serviceResponse2Parts;
        this.serviceResponse2CreatedDate = serviceResponse2CreatedDate;
        this.serviceResponse2LongValue = serviceResponse2LongValue;
    }

    public String getServiceResponse2Id() {
        return serviceResponse2Id;
    }

    public String getServiceResponse2Name() {
        return serviceResponse2Name;
    }

    public List<ServiceResponse2Part> getServiceResponse2Parts() {
        return serviceResponse2Parts;
    }

    public Date getServiceResponse2CreatedDate() {
        return serviceResponse2CreatedDate;
    }

    public long getServiceResponse2LongValue() {
        return serviceResponse2LongValue;
    }
}
