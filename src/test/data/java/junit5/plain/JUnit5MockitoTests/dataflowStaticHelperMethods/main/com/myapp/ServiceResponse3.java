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

public class ServiceResponse3 {

    public static class ServiceResponse3Part {
        private final String serviceResponse3PartId;
        private final String serviceResponse3PartName;

        public ServiceResponse3Part(String serviceResponse3PartId, String serviceResponse3PartName) {
            this.serviceResponse3PartId = serviceResponse3PartId;
            this.serviceResponse3PartName = serviceResponse3PartName;
        }

        public String getServiceResponse3PartId() {
            return serviceResponse3PartId;
        }

        public String getServiceResponse3PartName() {
            return serviceResponse3PartName;
        }
    }

    private final String serviceResponse3Id;
    private final String serviceResponse3Name;
    private final List<ServiceResponse3Part> serviceResponse3Parts;
    private final Date serviceResponse3CreatedDate;
    private final long serviceResponse3LongValue;

    public ServiceResponse3(String serviceResponse3Id, String serviceResponse3Name, List<ServiceResponse3Part> serviceResponse3Parts, Date serviceResponse3CreatedDate, long serviceResponse3LongValue) {
        this.serviceResponse3Id = serviceResponse3Id;
        this.serviceResponse3Name = serviceResponse3Name;
        this.serviceResponse3Parts = serviceResponse3Parts;
        this.serviceResponse3CreatedDate = serviceResponse3CreatedDate;
        this.serviceResponse3LongValue = serviceResponse3LongValue;
    }

    public String getServiceResponse3Id() {
        return serviceResponse3Id;
    }

    public String getServiceResponse3Name() {
        return serviceResponse3Name;
    }

    public List<ServiceResponse3Part> getServiceResponse3Parts() {
        return serviceResponse3Parts;
    }

    public Date getServiceResponse3CreatedDate() {
        return serviceResponse3CreatedDate;
    }

    public long getServiceResponse3LongValue() {
        return serviceResponse3LongValue;
    }
}
