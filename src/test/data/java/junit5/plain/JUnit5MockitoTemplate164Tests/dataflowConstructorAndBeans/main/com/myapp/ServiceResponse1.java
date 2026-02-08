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

public class ServiceResponse1 {

    public static class ServiceResponse1Part {
        private final String serviceResponse1PartId;
        private final String serviceResponse1PartName;

        public ServiceResponse1Part(String serviceResponse1PartId, String serviceResponse1PartName) {
            this.serviceResponse1PartId = serviceResponse1PartId;
            this.serviceResponse1PartName = serviceResponse1PartName;
        }

        public String getServiceResponse1PartId() {
            return serviceResponse1PartId;
        }

        public String getServiceResponse1PartName() {
            return serviceResponse1PartName;
        }
    }

    private final String serviceResponse1Id;
    private final String serviceResponse1Name;
    private final List<ServiceResponse1Part> serviceResponse1Parts;
    private final Date serviceResponse1CreatedDate;
    private final long serviceResponse1LongValue;

    public ServiceResponse1(String serviceResponse1Id, String serviceResponse1Name, List<ServiceResponse1Part> serviceResponse1Parts, Date serviceResponse1CreatedDate, long serviceResponse1LongValue) {
        this.serviceResponse1Id = serviceResponse1Id;
        this.serviceResponse1Name = serviceResponse1Name;
        this.serviceResponse1Parts = serviceResponse1Parts;
        this.serviceResponse1CreatedDate = serviceResponse1CreatedDate;
        this.serviceResponse1LongValue = serviceResponse1LongValue;
    }

    public String getServiceResponse1Id() {
        return serviceResponse1Id;
    }

    public String getServiceResponse1Name() {
        return serviceResponse1Name;
    }

    public List<ServiceResponse1Part> getServiceResponse1Parts() {
        return serviceResponse1Parts;
    }

    public Date getServiceResponse1CreatedDate() {
        return serviceResponse1CreatedDate;
    }

    public long getServiceResponse1LongValue() {
        return serviceResponse1LongValue;
    }
}
