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

public class MyClass {
    private final FooService fooService;

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    public WrappedFoo getFoo1(final String id, boolean useFirstApi) {
        final FooData fooData = fooService.getFoo1(id);
        return useFirstApi ? wrapWithApiCall1(fooData) : wrapWithApiCall2(fooData);
    }

    private WrappedFoo wrapWithApiCall1(final FooData fooData) {
        final String otherData = fooService.getData1(fooData.getId());
        if(otherData == null) {
            throw new OtherDataNotFoundException(fooData.getId());
        }
        return new WrappedFoo(fooData, otherData);
    }

    private WrappedFoo wrapWithApiCall2(final FooData fooData) {
        final String otherData = fooService.getData2(fooData.getId());
        if(otherData == null) {
            throw new OtherDataNotFoundException(fooData.getId());
        }
        return new WrappedFoo(fooData, otherData);
    }
}
