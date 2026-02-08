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

public class ServiceDTO3 {
    public static class ServiceDTO3Part {
        private String serviceDTO3PartId;
        private String serviceDTO3PartName;

        public String getServiceDTO3PartId() {
            return serviceDTO3PartId;
        }

        public void setServiceDTO3PartId(String serviceDTO3PartId) {
            this.serviceDTO3PartId = serviceDTO3PartId;
        }

        public String getServiceDTO3PartName() {
            return serviceDTO3PartName;
        }

        public void setServiceDTO3PartName(String serviceDTO3PartName) {
            this.serviceDTO3PartName = serviceDTO3PartName;
        }
    }

    private String ServiceDTO3Id;
    private String ServiceDTO3Name;
    private List<ServiceDTO3.ServiceDTO3Part> ServiceDTO3Parts;
    private Date ServiceDTO3CreatedDate;
    private long ServiceDTO3LongValue;

    public String getServiceDTO3Id() {
        return ServiceDTO3Id;
    }

    public void setServiceDTO3Id(String serviceDTO3Id) {
        ServiceDTO3Id = serviceDTO3Id;
    }

    public String getServiceDTO3Name() {
        return ServiceDTO3Name;
    }

    public void setServiceDTO3Name(String serviceDTO3Name) {
        ServiceDTO3Name = serviceDTO3Name;
    }

    public List<ServiceDTO3Part> getServiceDTO3Parts() {
        return ServiceDTO3Parts;
    }

    public void setServiceDTO3Parts(List<ServiceDTO3Part> serviceDTO3Parts) {
        ServiceDTO3Parts = serviceDTO3Parts;
    }

    public Date getServiceDTO3CreatedDate() {
        return ServiceDTO3CreatedDate;
    }

    public void setServiceDTO3CreatedDate(Date serviceDTO3CreatedDate) {
        ServiceDTO3CreatedDate = serviceDTO3CreatedDate;
    }

    public long getServiceDTO3LongValue() {
        return ServiceDTO3LongValue;
    }

    public void setServiceDTO3LongValue(long serviceDTO3LongValue) {
        ServiceDTO3LongValue = serviceDTO3LongValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceDTO3 that = (ServiceDTO3) o;
        return ServiceDTO3LongValue == that.ServiceDTO3LongValue && Objects.equals(ServiceDTO3Id, that.ServiceDTO3Id) && Objects.equals(ServiceDTO3Name, that.ServiceDTO3Name) && Objects.equals(ServiceDTO3Parts, that.ServiceDTO3Parts) && Objects.equals(ServiceDTO3CreatedDate, that.ServiceDTO3CreatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ServiceDTO3Id, ServiceDTO3Name, ServiceDTO3Parts, ServiceDTO3CreatedDate, ServiceDTO3LongValue);
    }
}
