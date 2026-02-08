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

import com.myapp.wrappedlists.*;

import java.util.List;

public class MyClass {
    private final FooService fooService;

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    public List<Foo> getShortWrapFoos(final String fooId) {
        return fooService.getShortWrapFoos(fooId).getValue();
    }

    public List<Foo> getFoos(final String fooId) {
        return fooService.getFoos(fooId).getPayload();
    }

    public List<Foo> getFoosWithMultiArgsConstructor(final String fooId) {
        return fooService.getFoosWithMultiArgsConstructor(fooId).getPayload();
    }

    public List<Foo> getFoosWithSingleArgConstructor(final String fooId) {
        return fooService.getFoosWithSingleArgConstructor(fooId).getValue();
    }

    public Foo getFoo(final String fooId) {
        return fooService.getFoo(fooId).getPayload();
    }

    public Foo getFooWithMultiArgsConstructor(final String fooId) {
        return fooService.getFooWithMultiArgsConstructor(fooId).getPayload();
    }

    public Foo getFooWithSingleArgConstructor(final String fooId) {
        return fooService.getFooWithSingleArgConstructor(fooId).getValue();
    }
}
