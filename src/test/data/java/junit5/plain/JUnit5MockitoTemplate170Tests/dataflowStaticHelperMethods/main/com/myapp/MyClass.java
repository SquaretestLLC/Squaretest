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

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MyClass {

    private final FooService fooService;

    public MyClass(FooService fooService) {
        this.fooService = fooService;
    }

    /**
     * Service response with constructor call to DTO constructor call.
     */
    public ServiceDTO1 myClassGetFoo1(final String myClassGetFoo1Id) {
        final ServiceResponse1 serviceResponse1 = fooService.getFoo1(myClassGetFoo1Id);
        return ServiceDTO1.fromResponse(serviceResponse1);
    }

    /**
     * Service response with data transferred via DTO setter methods.
     */
    public ServiceDTO2 myClassGetFoo2(final String myClassGetFoo2Id) {
        final ServiceResponse2 serviceResponse2 = fooService.getFoo2(myClassGetFoo2Id);
        return ServiceDTO2.fromResponse(serviceResponse2);
    }

    /**
     * Service returns a bean; the bean props stored in local vars, the local vars are validated (non-null, etc), then passed to constructor call.
     */
    public ServiceDTO3 myClassGetFoo3(final String myClassGetFoo3Id) {
        final ServiceResponse3 serviceResponse3 = fooService.getFoo3(myClassGetFoo3Id);
        return ServiceDTO3.fromResponse(serviceResponse3);
    }

    /**
     * Service returns list of data objects, bean props stored in local vars, the local vars are validated (non-null, etc), then passed to setter methods.
     */
    public List<ServiceDTOItem> myClassGetFoo4(final String myClassGetFoo4Id) {
        final List<ServiceResponseDataItem> serviceResponseDataItems = fooService.getFoo4(myClassGetFoo4Id);
        return serviceResponseDataItems.stream().map(ServiceDTOItemHelpers::convert).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * Service returns list of data objects, bean props stored in local vars, the local vars are validated (non-null, etc), then passed to setter methods.
     * This test ensures the DFA used in static methods outside the source class is the "light" version.
     * Some of the "same value types" should not be found, because finding them requires the full DFA algorithm.
     * We don't want to perform the full algorithm on static methods outside the source classes, because we don't want to risk inadvertently spidering across the entire project.
     */
    public List<ServiceDTOItem2> myClassGetFoo5(final String myClassGetFoo4Id) {
        final List<ServiceResponseDataItem2> serviceResponseDataItems = fooService.getFoo5(myClassGetFoo4Id);
        return serviceResponseDataItems.stream().map(ServiceDTOItemHelpers2::convert).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
