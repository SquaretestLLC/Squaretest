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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MyClass {

    public static String quote(final String str) {
        if(str == null) {
            return str;
        }
        return "\"" + str + "\"";
    }

    public static String quote2(final String str) {
        if(str != null) {
            return "\"" + str + "\"";
        }
        return str;
    }

    public static String quote3(final String str) {
        return str;
    }

    public static String quote4(final String str) {
        return cap(str);
    }

    public static String cap(final String theStr) {
        return theStr.toUpperCase();
    }

    private final FooService fooService;

    public MyClass(FooService fooService) {
        this.fooService = fooService;
    }

    /**
     * Service response with constructor call to DTO constructor call.
     */
    public ServiceDTO1 myClassGetFoo1(final String myClassGetFoo1Id) {
        final ServiceResponse1 serviceResponse1 = fooService.getFoo1(myClassGetFoo1Id);
        final List<ServiceDTO1.ServiceDTO1Part> parts = createDtoPartList1(serviceResponse1.getServiceResponse1Parts());
        return new ServiceDTO1(quote(serviceResponse1.getServiceResponse1Id()), quote(serviceResponse1.getServiceResponse1Name()), parts, serviceResponse1.getServiceResponse1CreatedDate(), serviceResponse1.getServiceResponse1LongValue());
    }

    /**
     * Service response with data transferred via DTO setter methods.
     */
    public ServiceDTO2 myClassGetFoo2(final String myClassGetFoo2Id) {
        final ServiceResponse2 serviceResponse2 = fooService.getFoo2(myClassGetFoo2Id);
        final List<ServiceDTO2.ServiceDTO2Part> parts = createDtoPartList2(serviceResponse2.getServiceResponse2Parts());
        final ServiceDTO2 ret = new ServiceDTO2();
        ret.setServiceDTO2CreatedDate(serviceResponse2.getServiceResponse2CreatedDate());
        ret.setServiceDTO2Id(quote2(serviceResponse2.getServiceResponse2Id()));
        ret.setServiceDTO2LongValue(serviceResponse2.getServiceResponse2LongValue());
        ret.setServiceDTO2Name(quote2(serviceResponse2.getServiceResponse2Name()));
        ret.setServiceDTO2Parts(parts);
        return ret;
    }

    /**
     * Service call with data object where data object params are source method params.
     */
    public boolean doesFooExist1(final String doesFooExistParamBucketName1, final String doesFooExistParamId1) {
        return fooService.fooExists1(new ServiceRequest1(doesFooExistParamBucketName1, doesFooExistParamId1));
    }

    /**
     * Service call with data object where data object params are source method DTO bean properties.
     */
    public boolean doesFooExist2(final FooInfo1 fooInfo1) {
        return fooService.fooExists2(new ServiceRequest2(fooInfo1.getFooInfo1BucketName(), fooInfo1.getFooInfo1Id()));
    }

    /**
     * Service call with data object where data object params are source method param getter calls, and param is constructed via constructor call.
     */
    public boolean doesFooExist3(final FooInfo2 fooInfo2) {
        final String fooInfo2BucketNameTemp = Objects.requireNonNull(fooInfo2.getFooInfo2BucketName());
        final String fooInfo2IdTemp = Objects.requireNonNull(fooInfo2.getFooInfo2Id());
        return fooService.fooExists3(new ServiceRequest3(fooInfo2BucketNameTemp, fooInfo2IdTemp));
    }

    private List<ServiceDTO1.ServiceDTO1Part> createDtoPartList1(List<ServiceResponse1.ServiceResponse1Part> serviceResponse1Parts) {
        return serviceResponse1Parts.stream().map(x -> new ServiceDTO1.ServiceDTO1Part(quote(x.getServiceResponse1PartId()), quote(x.getServiceResponse1PartName()))).collect(Collectors.toList());
    }

    private List<ServiceDTO2.ServiceDTO2Part> createDtoPartList2(List<ServiceResponse2.ServiceResponse2Part> serviceResponse2Parts) {
        return serviceResponse2Parts.stream().map(x -> {
            final ServiceDTO2.ServiceDTO2Part ret = new ServiceDTO2.ServiceDTO2Part();
            ret.setServiceDTO2PartId(x.getServiceResponse2PartId());
            ret.setServiceDTO2PartName(x.getServiceResponse2PartName());
            return ret;
        }).collect(Collectors.toList());
    }

    /**
     * Service returns a bean; the bean props stored in local vars, the local vars are validated (non-null, etc), then passed to constructor call.
     */
    public ServiceDTO3 myClassGetFoo3(final String myClassGetFoo3Id) {
        final ServiceResponse3 serviceResponse3 = fooService.getFoo3(myClassGetFoo3Id);
        final List<ServiceDTO3.ServiceDTO3Part> parts = createDtoPartList3(serviceResponse3.getServiceResponse3Parts());
        final ServiceDTO3 ret = new ServiceDTO3();
        ret.setServiceDTO3CreatedDate(serviceResponse3.getServiceResponse3CreatedDate());
        ret.setServiceDTO3Id(quote3(serviceResponse3.getServiceResponse3Id()));
        ret.setServiceDTO3LongValue(serviceResponse3.getServiceResponse3LongValue());
        ret.setServiceDTO3Name(quote3(serviceResponse3.getServiceResponse3Name()));
        ret.setServiceDTO3Parts(parts);
        return ret;
    }

    private List<ServiceDTO3.ServiceDTO3Part> createDtoPartList3(List<ServiceResponse3.ServiceResponse3Part> serviceResponse3Parts) {
        return serviceResponse3Parts.stream().map(x -> {
            final ServiceDTO3.ServiceDTO3Part ret = new ServiceDTO3.ServiceDTO3Part();
            final String serviceResponse3PartId = x.getServiceResponse3PartId();
            if(serviceResponse3PartId == null) {
                return null;
            }
            ret.setServiceDTO3PartId(quote4(serviceResponse3PartId));
            final String serviceResponse3PartName = x.getServiceResponse3PartName();
            if(serviceResponse3PartName == null) {
                return null;
            }
            ret.setServiceDTO3PartName(quote4(serviceResponse3PartName));
            return ret;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * Service returns list of data objects, bean props stored in local vars, the local vars are validated (non-null, etc), then passed to setter methods.
     */
    public List<ServiceDTOItem> myClassGetFoo4(final String myClassGetFoo4Id) {
        final List<ServiceResponseDataItem> serviceResponseDataItems = fooService.getFoo4(myClassGetFoo4Id);
        return serviceResponseDataItems.stream().map(MyClass::convert).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Nullable
    private static ServiceDTOItem convert(final ServiceResponseDataItem serviceResponseDataItem) {
        final String serviceResponseDataItemIdTemp = serviceResponseDataItem.getServiceResponseDataItemId();
        final String serviceResponseDataItemNameTemp = serviceResponseDataItem.getServiceResponseDataItemName();
        final Date serviceResponseDataItemCreatedDateTemp = serviceResponseDataItem.getServiceResponseDataItemCreatedDate();
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
