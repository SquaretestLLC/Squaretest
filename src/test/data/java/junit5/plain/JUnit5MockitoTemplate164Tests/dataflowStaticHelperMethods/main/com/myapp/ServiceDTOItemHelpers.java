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

import com.myapp.nullables.annotations.Nullable;
import java.util.Date;
import java.util.Objects;

public class ServiceDTOItemHelpers {
    @Nullable
    public static ServiceDTOItem convert(final ServiceResponseDataItem serviceResponseDataItem) {
        final String serviceResponseDataItemIdTemp = Objects.requireNonNull(serviceResponseDataItem.getServiceResponseDataItemId());
        final String serviceResponseDataItemNameTemp = Objects.requireNonNull(serviceResponseDataItem.getServiceResponseDataItemName());
        final Date serviceResponseDataItemCreatedDateTemp = Objects.requireNonNull(serviceResponseDataItem.getServiceResponseDataItemCreatedDate());
        final long serviceResponseDataItemLongValueTemp = serviceResponseDataItem.getServiceResponseDataItemLongValue();
        if(serviceResponseDataItemIdTemp == null || serviceResponseDataItemNameTemp == null || serviceResponseDataItemCreatedDateTemp == null) {
            return null;
        }
        final ServiceDTOItem ret = new ServiceDTOItem();
        ret.setServiceDTOItemId(serviceResponseDataItemIdTemp);
        ret.setServiceDTOItemCreatedDate(serviceResponseDataItemCreatedDateTemp);
        ret.setServiceDTOItemName(serviceResponseDataItemNameTemp);
        ret.setServiceDTOItemLongValue(serviceResponseDataItemLongValueTemp);
        return ret;
    }
}
