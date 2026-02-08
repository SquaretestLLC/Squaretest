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
import java.util.Objects;

public class ServiceDTOItem2 {
    private String serviceDTOItem2Id;
    private String serviceDTOItem2Name;
    private Date serviceDTOItem2CreatedDate;
    private long serviceDTOItem2LongValue;

    public String getServiceDTOItem2Id() {
        return serviceDTOItem2Id;
    }

    public void setServiceDTOItem2Id(final String ServiceDTOItem2Id) {
        this.serviceDTOItem2Id = ServiceDTOItem2Id;
    }

    public String getServiceDTOItem2Name() {
        return serviceDTOItem2Name;
    }

    public void setServiceDTOItem2Name(final String ServiceDTOItem2Name) {
        this.serviceDTOItem2Name = ServiceDTOItem2Name;
    }

    public Date getServiceDTOItem2CreatedDate() {
        return serviceDTOItem2CreatedDate;
    }

    public void setServiceDTOItem2CreatedDate(final Date ServiceDTOItem2CreatedDate) {
        this.serviceDTOItem2CreatedDate = ServiceDTOItem2CreatedDate;
    }

    public long getServiceDTOItem2LongValue() {
        return serviceDTOItem2LongValue;
    }

    public void setServiceDTOItem2LongValue(final long ServiceDTOItem2LongValue) {
        this.serviceDTOItem2LongValue = ServiceDTOItem2LongValue;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ServiceDTOItem2 that = (ServiceDTOItem2) o;
        return serviceDTOItem2LongValue == that.serviceDTOItem2LongValue && Objects.equals(serviceDTOItem2Id, that.serviceDTOItem2Id) && Objects.equals(serviceDTOItem2Name, that.serviceDTOItem2Name) && Objects.equals(serviceDTOItem2CreatedDate, that.serviceDTOItem2CreatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceDTOItem2Id, serviceDTOItem2Name, serviceDTOItem2CreatedDate, serviceDTOItem2LongValue);
    }
}
