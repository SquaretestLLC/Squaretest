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

import com.myapp.suppliers.Foo;
import com.myapp.suppliers.FooService;

public class MyClass {
    private final FooService fooService;

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    public Foo getFoosLazy(final String fooId) {
        return fooService.getFoosLazy(fooId).get();
    }

    public String getStringsLazy(final String fooId) {
        return fooService.getStringsLazy(fooId).get();
    }

    public int getIntsLazy(final String fooId) {
        return fooService.getIntsLazy(fooId).getAsInt();
    }

    public boolean getBoolsLazy(final String fooId) {
        return fooService.getBoolsLazy(fooId).getAsBoolean();
    }

    public double getDoublesLazy(final String fooId) {
        return fooService.getDoublesLazy(fooId).getAsDouble();
    }

    public long getLongsLazy(final String fooId) {
        return fooService.getLongsLazy(fooId).getAsLong();
    }
}
