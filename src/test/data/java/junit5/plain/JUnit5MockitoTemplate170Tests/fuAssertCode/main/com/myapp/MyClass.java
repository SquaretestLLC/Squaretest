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

public class MyClass {
    private final FooService fooService;

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }
    public FooData getFooData1(final String id) {
        final List<FooData> foos = fooService.getFoos1(id);
        assert !foos.isEmpty();
        return foos.get(0);
    }
    public FooData getFooData2(final String id) {
        final List<FooData> foos = fooService.getFoos1(id);
        assert !foos.isEmpty() : "Foos is empty.";
        return foos.get(0);
    }
    public FooData getFooData3(final String id) {
        assert !fooService.getFoos1(id).isEmpty();
        return new FooData("id", "name", "otherId");
    }
    public FooData getFooData4(final String id) {
        assert fooService.getFoos1(id).isEmpty();
        return new FooData("id", "name", "otherId");
    }
}
