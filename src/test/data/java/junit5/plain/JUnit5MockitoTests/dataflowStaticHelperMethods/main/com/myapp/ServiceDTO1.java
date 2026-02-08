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
import java.util.stream.Collectors;

public class ServiceDTO1 {
    public static class ServiceDTO1Part {
        private final String serviceDTO1PartId;
        private final String serviceDTO1PartName;

        public ServiceDTO1Part(String ServiceDTO1PartId, String ServiceDTO1PartName) {
            this.serviceDTO1PartId = ServiceDTO1PartId;
            this.serviceDTO1PartName = ServiceDTO1PartName;
        }

        public String getServiceDTO1PartId() {
            return serviceDTO1PartId;
        }

        public String getServiceDTO1PartName() {
            return serviceDTO1PartName;
        }
    }

    private final String ServiceDTO1Id;
    private final String ServiceDTO1Name;
    private final List<ServiceDTO1Part> ServiceDTO1Parts;
    private final Date ServiceDTO1CreatedDate;
    private final long ServiceDTO1LongValue;

    public ServiceDTO1(String serviceDTO1Id, String serviceDTO1Name, List<ServiceDTO1Part> serviceDTO1Parts, Date serviceDTO1CreatedDate, long serviceDTO1LongValue) {
        ServiceDTO1Id = serviceDTO1Id;
        ServiceDTO1Name = serviceDTO1Name;
        ServiceDTO1Parts = serviceDTO1Parts;
        ServiceDTO1CreatedDate = serviceDTO1CreatedDate;
        ServiceDTO1LongValue = serviceDTO1LongValue;
    }

    public static ServiceDTO1 fromResponse(final ServiceResponse1 serviceResponse1) {
        final List<ServiceDTO1.ServiceDTO1Part> parts = createDtoPartList1(serviceResponse1.getServiceResponse1Parts());
        return new ServiceDTO1(serviceResponse1.getServiceResponse1Id(), serviceResponse1.getServiceResponse1Name(), parts, serviceResponse1.getServiceResponse1CreatedDate(), serviceResponse1.getServiceResponse1LongValue());
    }

    private static List<ServiceDTO1.ServiceDTO1Part> createDtoPartList1(List<ServiceResponse1.ServiceResponse1Part> serviceResponse1Parts) {
        return serviceResponse1Parts.stream().map(x -> new ServiceDTO1.ServiceDTO1Part(x.getServiceResponse1PartId(), x.getServiceResponse1PartName())).collect(Collectors.toList());
    }

    public String getServiceDTO1Id() {
        return ServiceDTO1Id;
    }

    public String getServiceDTO1Name() {
        return ServiceDTO1Name;
    }

    public List<ServiceDTO1Part> getServiceDTO1Parts() {
        return ServiceDTO1Parts;
    }

    public Date getServiceDTO1CreatedDate() {
        return ServiceDTO1CreatedDate;
    }

    public long getServiceDTO1LongValue() {
        return ServiceDTO1LongValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceDTO1 that = (ServiceDTO1) o;
        return ServiceDTO1LongValue == that.ServiceDTO1LongValue && Objects.equals(ServiceDTO1Id, that.ServiceDTO1Id) && Objects.equals(ServiceDTO1Name, that.ServiceDTO1Name) && Objects.equals(ServiceDTO1Parts, that.ServiceDTO1Parts) && Objects.equals(ServiceDTO1CreatedDate, that.ServiceDTO1CreatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ServiceDTO1Id, ServiceDTO1Name, ServiceDTO1Parts, ServiceDTO1CreatedDate, ServiceDTO1LongValue);
    }
}
