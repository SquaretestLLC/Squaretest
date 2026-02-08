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

public class ServiceDTOItem {
    private String serviceDTOItemId;
    private String serviceDTOItemName;
    private Date serviceDTOItemCreatedDate;
    private long serviceDTOItemLongValue;

    public String getServiceDTOItemId() {
        return serviceDTOItemId;
    }

    public void setServiceDTOItemId(final String serviceDTOItemId) {
        this.serviceDTOItemId = serviceDTOItemId;
    }

    public String getServiceDTOItemName() {
        return serviceDTOItemName;
    }

    public void setServiceDTOItemName(final String serviceDTOItemName) {
        this.serviceDTOItemName = serviceDTOItemName;
    }

    public Date getServiceDTOItemCreatedDate() {
        return serviceDTOItemCreatedDate;
    }

    public void setServiceDTOItemCreatedDate(final Date serviceDTOItemCreatedDate) {
        this.serviceDTOItemCreatedDate = serviceDTOItemCreatedDate;
    }

    public long getServiceDTOItemLongValue() {
        return serviceDTOItemLongValue;
    }

    public void setServiceDTOItemLongValue(final long serviceDTOItemLongValue) {
        this.serviceDTOItemLongValue = serviceDTOItemLongValue;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ServiceDTOItem that = (ServiceDTOItem) o;
        return serviceDTOItemLongValue == that.serviceDTOItemLongValue && Objects.equals(serviceDTOItemId, that.serviceDTOItemId) && Objects.equals(serviceDTOItemName, that.serviceDTOItemName) && Objects.equals(serviceDTOItemCreatedDate, that.serviceDTOItemCreatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceDTOItemId, serviceDTOItemName, serviceDTOItemCreatedDate, serviceDTOItemLongValue);
    }
}
