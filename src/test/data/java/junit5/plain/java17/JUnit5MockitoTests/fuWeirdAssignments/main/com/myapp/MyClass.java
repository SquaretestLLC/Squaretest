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

import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class MyClass {
    private static final String MultilineConstant = """
            This
            Is
            A
            Multiline
            String!
            """;
    private final FooService fooService;
    private final OtherService otherService;
    private final MetricService metricService;
    private FooData currentFoo;
    private String currentFooId;
    private String currentFooName;

    public MyClass(final FooService fooService, final OtherService otherService, final MetricService metricService) {
        this.fooService = fooService;
        this.otherService = otherService;
        this.metricService = metricService;
    }

    public FooData getBestFoo1(final String id) {
        final Optional<FooData> foo1, foo2;
        foo1 = foo2 = fooService.getFooData1(id);
        if(foo1.isPresent()) {
            metricService.recordFooPresent(id);
            return foo1.get();
        }
        if(foo2.isPresent()) {
            return foo2.get();
        }
        metricService.recordFooAbsent(id);
        return null;
    }
    public FooData getBestFoo2(final String id) {
        final FooData foo1, foo2;
        foo1 = foo2 = fooService.getFooData1(id).orElseThrow();
        metricService.recordFooPresent(id);
        if(foo1.getId().equals(id)) {
            metricService.recordFooIdMatch(id);
            return foo1;
        }
        return foo2;
    }
    public FooData getBestFoo3(final String id) {
        final FooData foo1, foo2;
        foo1 = foo2 = fooService.getFooData1(id).orElseThrow();
        metricService.recordFooPresent(id);
        if(foo2.getId().equals(id)) {
            metricService.recordFooIdMatch(id);
            return foo1;
        }
        return foo2;
    }

    public FooData getBestFoo4(final String id) {
        final Optional<FooData> foo1;
        boolean isPresent = (foo1 = fooService.getFooData1(id)).isPresent();
        if(isPresent) {
            return foo1.get();
        }
        return fooService.getFooData3(id);
    }
    public FooData getBestFoo5(final String id) {
        final Optional<FooData> foo1;
        boolean isAbsent = (foo1 = fooService.getFooData1(id)).isEmpty();
        if(isAbsent) {
            return fooService.getFooData3(id);
        }
        return foo1.get();
    }
    public FooData getBestFoo6(final String id) {
        final Optional<FooData> foo1;
        boolean isAbsent = !(foo1 = fooService.getFooData1(id)).isPresent();
        if(isAbsent) {
            return fooService.getFooData3(id);
        }
        return foo1.get();
    }

    public String getBestOtherData1(final String id) {
        final FooData fooData;
        Optional<byte[]> otherData = switch((fooData = fooService.getFooData3(id)).getFooType()) {
            case Type1 -> otherService.getOtherData1(fooData.getId());
            case Type2 -> otherService.getOtherData2(fooData.getId());
            case Type3 -> otherService.getOtherData3(fooData.getId());
        };
        metricService.recordOtherDataCallComplete(id);
        return otherData.map(bytes -> new String(bytes, StandardCharsets.UTF_8)).orElse(null);
    }
    public String getBestOtherData2(final String id) {
        final FooData fooData;
        Optional<byte[]> otherData = switch((fooData = fooService.getFooData1(id).orElseThrow()).getFooType()) {
            case Type1 -> otherService.getOtherData1(fooData.getId());
            case Type2 -> otherService.getOtherData2(fooData.getId());
            case Type3 -> otherService.getOtherData3(fooData.getId());
        };
        metricService.recordOtherDataCallComplete(id);
        return otherData.map(bytes -> new String(bytes, StandardCharsets.UTF_8)).orElse(null);
    }

    public String getBestOtherData3(final String id) {
        Optional<byte[]> otherData = switch((currentFoo = fooService.getFooData3(id)).getFooType()) {
            case Type1 -> otherService.getOtherData1(currentFoo.getId());
            case Type2 -> otherService.getOtherData2(currentFoo.getId());
            case Type3 -> otherService.getOtherData3(currentFoo.getId());
        };
        metricService.recordOtherDataCallComplete(id);
        return otherData.map(bytes -> new String(bytes, StandardCharsets.UTF_8)).orElse(null);
    }
    public String getBestOtherData4(final String id) {
        Optional<byte[]> otherData = switch((currentFoo = fooService.getFooData1(id).orElseThrow()).getFooType()) {
            case Type1 -> otherService.getOtherData1(currentFoo.getId());
            case Type2 -> otherService.getOtherData2(currentFoo.getId());
            case Type3 -> otherService.getOtherData3(currentFoo.getId());
        };
        metricService.recordOtherDataCallComplete(id);
        return otherData.map(bytes -> new String(bytes, StandardCharsets.UTF_8)).orElse(null);
    }
    public String getIdOrName1(final String id) {
        final Optional<FooData> foo1 = fooService.getFooData1(id);
        String fooId, fooName;
        fooId = fooName = foo1.map(this::getIdImpl).orElse(null);
        return fooId != null ? fooId : fooName;
    }
    public String getIdOrName2(final String id) {
        final Optional<FooData> foo1 = fooService.getFooData1(id);
        currentFooId = currentFooName = foo1.map(this::getIdImpl).orElse(null);
        return currentFooId != null ? currentFooId : currentFooName;
    }

    public static String getMultilineConstant() {
        return MultilineConstant;
    }

    private String getIdImpl(final FooData fooData) {
        final String id = fooData.getId();
        metricService.recordFooId(id);
        return id;
    }
}
