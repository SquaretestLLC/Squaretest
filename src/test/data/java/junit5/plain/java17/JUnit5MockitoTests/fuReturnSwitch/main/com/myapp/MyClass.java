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

import java.util.function.Supplier;

public class MyClass {
    private final FooService fooService;

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    public FooData getFooData1(final String id, final FooType fooType) {
        return switch (fooType) {
            case Type1 -> fooService.getFooData1(id).orElse(null);
            case Type2 -> fooService.getFooData2(id).orElse(null);
            case Type3 -> fooService.getFooData3(id).orElse(null);
        };
    }

    public FooData getFooData2(final String id, final FooType fooType) {
        return new Supplier<FooData>() {
            @Override
            public FooData get() {
                return switch (fooType) {
                    case Type1 -> fooService.getFooData1(id).orElse(null);
                    case Type2 -> fooService.getFooData2(id).orElse(null);
                    case Type3 -> fooService.getFooData3(id).orElse(null);
                };
            }
        }.get();
    }

    public FooData getFooData3(final String id, final FooType fooType) {
        return ((Supplier<FooData>) () -> switch (fooType) {
            case Type1 -> fooService.getFooData1(id).orElse(null);
            case Type2 -> fooService.getFooData2(id).orElse(null);
            case Type3 -> fooService.getFooData3(id).orElse(null);
        }).get();
    }

    public void validateFooData1(final String id, final FooType fooType) {
        if (switch (fooType) {
            case Type1 -> fooService.getFooData1(id).orElse(null);
            case Type2 -> fooService.getFooData2(id).orElse(null);
            case Type3 -> fooService.getFooData3(id).orElse(null);
        } == null) {
            throw new RuntimeException("Foo not found " + id);
        }
    }

    public void validateFooData2(final String id, final FooType fooType) {
        if (switch (fooType) {
            case Type1 -> fooService.getFooData1(id).orElse(null);
            case Type2 -> fooService.getFooData2(id).orElse(null);
            case Type3 -> fooService.getFooData3(id).orElse(null);
        } != null) {
            return;
        }
        fooService.getFooData4(id).orElseThrow();
    }
}
