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

import com.myapp.optionalThrowIfPresent.FooData;

import java.util.NoSuchElementException;

public class MyClass {
    private final FooService fooService;

    public MyClass(FooService fooService) {
        this.fooService = fooService;
    }

    public FooData getFoo1(final String id) {
        return fooService.getFoo1(id);
    }

    public FooData getFoo2(final String id) {
        return fooService.getFoo2(id);
    }

    public FooData getFoo3(final String id) {
        try {
            return fooService.getFoo2(id);
        } catch (final NoSuchElementException e) {
            return null;
        }
    }
}
