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

import java.util.Map;

public class MyClass {
    private final FooService fooService;
    private final Map<String, String> cache;

    public MyClass(final FooService fooService, final Map<String, String> cache) {
        this.fooService = fooService;
        this.cache = cache;
    }

    public String getFoo1(final String fooId) {
        return cache.computeIfAbsent(fooId, this::getFoo1Impl);
    }

    public String getOtherFoo1(final String fooId) {
        final String fooData = cache.computeIfAbsent(fooId, this::getFoo1Impl);
        if(fooData != null) {
            return fooService.getOtherThing1(fooData).orElse(null);
        }
        return null;
    }

    private String getFoo1Impl(final String fooId) {
        return fooService.getFoo1(fooId);
    }
}
