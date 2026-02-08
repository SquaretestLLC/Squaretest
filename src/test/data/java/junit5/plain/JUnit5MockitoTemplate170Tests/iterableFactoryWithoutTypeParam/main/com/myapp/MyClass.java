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
import java.util.stream.Collectors;

public class MyClass {
    private final FooService fooService;

    public MyClass(FooService fooService) {
        this.fooService = fooService;
    }

    public List<String> getNames1(final String id) {
        Result<List<FooData>> foos = fooService.getAllFooData1(id);
        return getNamesImpl(foos);
    }

    private List<String> getNamesImpl(Result<List<FooData>> foos) {
        return foos.getTheValue().stream().map(FooData::getName).collect(Collectors.toList());
    }
}
