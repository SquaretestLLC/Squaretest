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

public class ServiceDTOItemHelpers2 {
    @Nullable
    public static ServiceDTOItem2 convert(final ServiceResponseDataItem2 serviceResponseDataItem) {
        final String serviceResponseDataItemIdTemp = parse(serviceResponseDataItem.getServiceResponseDataItem2Id());
        final String serviceResponseDataItemNameTemp = parse(serviceResponseDataItem.getServiceResponseDataItem2Name());
        final Date serviceResponseDataItemCreatedDateTemp = serviceResponseDataItem.getServiceResponseDataItem2CreatedDate();
        final long serviceResponseDataItemLongValueTemp = serviceResponseDataItem.getServiceResponseDataItem2LongValue();
        if(serviceResponseDataItemIdTemp == null || serviceResponseDataItemNameTemp == null || serviceResponseDataItemCreatedDateTemp == null) {
            return null;
        }
        final ServiceDTOItem2 ret = new ServiceDTOItem2();
        ret.setServiceDTOItem2Id(serviceResponseDataItemIdTemp);
        ret.setServiceDTOItem2CreatedDate(serviceResponseDataItemCreatedDateTemp);
        ret.setServiceDTOItem2Name(serviceResponseDataItemNameTemp);
        ret.setServiceDTOItem2LongValue(serviceResponseDataItemLongValueTemp);
        return ret;
    }

    private static String parse(final String str) {
        return str;
    }
}
