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
import java.util.Objects;

public class ServiceDTO2 {
    public static class ServiceDTO2Part {
        private String serviceDTO2PartId;
        private String serviceDTO2PartName;

        public String getServiceDTO2PartId() {
            return serviceDTO2PartId;
        }

        public void setServiceDTO2PartId(String serviceDTO2PartId) {
            this.serviceDTO2PartId = serviceDTO2PartId;
        }

        public String getServiceDTO2PartName() {
            return serviceDTO2PartName;
        }

        public void setServiceDTO2PartName(String serviceDTO2PartName) {
            this.serviceDTO2PartName = serviceDTO2PartName;
        }
    }

    private String ServiceDTO2Id;
    private String ServiceDTO2Name;
    private List<ServiceDTO2Part> ServiceDTO2Parts;
    private Date ServiceDTO2CreatedDate;
    private long ServiceDTO2LongValue;

    public String getServiceDTO2Id() {
        return ServiceDTO2Id;
    }

    public void setServiceDTO2Id(String serviceDTO2Id) {
        ServiceDTO2Id = serviceDTO2Id;
    }

    public String getServiceDTO2Name() {
        return ServiceDTO2Name;
    }

    public void setServiceDTO2Name(String serviceDTO2Name) {
        ServiceDTO2Name = serviceDTO2Name;
    }

    public List<ServiceDTO2Part> getServiceDTO2Parts() {
        return ServiceDTO2Parts;
    }

    public void setServiceDTO2Parts(List<ServiceDTO2Part> serviceDTO2Parts) {
        ServiceDTO2Parts = serviceDTO2Parts;
    }

    public Date getServiceDTO2CreatedDate() {
        return ServiceDTO2CreatedDate;
    }

    public void setServiceDTO2CreatedDate(Date serviceDTO2CreatedDate) {
        ServiceDTO2CreatedDate = serviceDTO2CreatedDate;
    }

    public long getServiceDTO2LongValue() {
        return ServiceDTO2LongValue;
    }

    public void setServiceDTO2LongValue(long serviceDTO2LongValue) {
        ServiceDTO2LongValue = serviceDTO2LongValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceDTO2 that = (ServiceDTO2) o;
        return ServiceDTO2LongValue == that.ServiceDTO2LongValue && Objects.equals(ServiceDTO2Id, that.ServiceDTO2Id) && Objects.equals(ServiceDTO2Name, that.ServiceDTO2Name) && Objects.equals(ServiceDTO2Parts, that.ServiceDTO2Parts) && Objects.equals(ServiceDTO2CreatedDate, that.ServiceDTO2CreatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ServiceDTO2Id, ServiceDTO2Name, ServiceDTO2Parts, ServiceDTO2CreatedDate, ServiceDTO2LongValue);
    }
}
