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

import java.util.Optional;

public class MyClass {
    private final FooService fooService;
    private final MetricService metricService;

    public MyClass(final FooService fooService, final MetricService metricService) {
        this.fooService = fooService;
        this.metricService = metricService;
    }

    public FooData getBestFoo1(final String id) {
        final Optional<FooData> foo1 = fooService.getFooData1(id), foo2 = fooService.getFooData1(id);
        if(foo1.isPresent()) {
            return foo1.get();
        }
        if(foo2.isPresent()) {
            return foo2.get();
        }
        return null;
    }
    public FooData getBestFoo2(final String id) {
        final FooData foo1 = fooService.getFooData1(id).orElseThrow(), foo2 = fooService.getFooData1(id).orElseThrow();
        if(foo1.getId().equals(id)) {
            return foo1;
        }
        return foo2;
    }
    public FooData getBestFoo3(final String id) {
        final Optional<FooData> foo1 = fooService.getFooData1(id), foo2 = fooService.getFooData2(id);
        if(foo1.isPresent()) {
            return foo1.get();
        }
        if(foo2.isPresent()) {
            return foo2.get();
        }
        return null;
    }
    public FooData getBestFoo4(final String id) {
        final FooData foo1 = fooService.getFooData1(id).orElseThrow(), foo2 = fooService.getFooData2(id).orElseThrow();
        if(foo1.getId().equals(id)) {
            return foo1;
        }
        return foo2;
    }

    public String getIdOrName1(final String id) {
        final Optional<FooData> foo1 = fooService.getFooData1(id);
        final String fooId = foo1.map(this::getIdImpl).orElse(null), name = foo1.map(this::getNameImpl).orElse(null);
        return fooId != null ? fooId : name;
    }
    public String getIdOrName2(final String id) {
        final Optional<FooData> foo1 = fooService.getFooData1(id);
        final String fooId = foo1.map(this::getIdImpl).orElseThrow(), name = foo1.map(this::getNameImpl).orElse(null);
        return fooId + name;
    }
    public String getIdOrName3(final String id) {
        final Optional<FooData> foo1 = fooService.getFooData1(id);
        final String fooId = foo1.map(this::getIdImpl).orElseThrow(), name = fooService.getFooData3(id).getName();
        return fooId + name;
    }
    public String getIdOrName4(final String id) {
        final Optional<FooData> foo1 = fooService.getFooData1(id);
        final String fooId = foo1.map(this::getIdImpl).orElseThrow(), name = fooService.getFooData4(fooId).getName();
        return name;
    }

    private String getNameImpl(final FooData fooData) {
        final String name = fooData.getName();
        metricService.recordFooName(name);
        return name;
    }

    private String getIdImpl(final FooData fooData) {
        final String id = fooData.getId();
        metricService.recordFooId(id);
        return id;
    }

}
