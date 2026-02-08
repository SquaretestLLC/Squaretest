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

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;
import java.util.Map;

public class MyClass {
    private static final ParameterizedTypeReference<FooData> FooDataTypeRef = new ParameterizedTypeReference<>() {};
    private static final TypeReference<FooData> JacksonTypeRef = new TypeReference<>() {};

    private final FooService fooService;
    private final CustomObjectMapper customObjectMapper;

    public MyClass(FooService fooService, CustomObjectMapper customObjectMapper) {
        this.fooService = fooService;
        this.customObjectMapper = customObjectMapper;
    }

    public FooData getData1(final String id) {
        return fooService.getData1(id, FooDataTypeRef);
    }

    public FooData getData2(final String id) {
        return fooService.getData1(id, new ParameterizedTypeReference<FooData>() {});
    }

    public FooData getData3(final String id) {
        return fooService.getData1(id, new ParameterizedTypeReference<>() {});
    }

    public List<FooData> getData4(final String id) {
        return fooService.getData1(id, new ParameterizedTypeReference<>() {});
    }

    public Map<String, List<FooData>> getData5(final String id) {
        return fooService.getData1(id, new ParameterizedTypeReference<>() {});
    }

    public FooData getData6(final String id) {
        var foo = fooService.getData1(id, new ParameterizedTypeReference<>() {});
        // I know this is broken. I'm testing the edge case.
        return (FooData) foo;
    }

    public <T> T getData7(final String id, final ParameterizedTypeReference<T> typeRef) {
        return fooService.getData1(id, typeRef);
    }

    public FooData getData9(final String dataJson) {
        return customObjectMapper.convertValue(dataJson, JacksonTypeRef);
    }

    public FooData getData10(final String dataJson) {
        return customObjectMapper.convertValue(dataJson, new TypeReference<FooData>() {});
    }

    public FooData getData11(final String dataJson) {
        return customObjectMapper.convertValue(dataJson, new TypeReference<>() {});
    }

    public List<FooData> getData12(final String dataJson) {
        return customObjectMapper.convertValue(dataJson, new TypeReference<>() {});
    }

    public <T> T getData13(final String id, final TypeReference<T> typeRef) {
        return customObjectMapper.convertValue(id, typeRef);
    }
}
