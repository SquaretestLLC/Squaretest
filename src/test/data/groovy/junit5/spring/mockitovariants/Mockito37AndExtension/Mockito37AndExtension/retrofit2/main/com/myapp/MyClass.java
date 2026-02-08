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

import com.myapp.retro.Foo;
import com.myapp.retro.FooService;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MyClass {
    private final FooService fooService;

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    public List<Foo> listFoos(final String prefix) throws IOException {
        return fooService.listFoos(prefix).execute().body();
    }

    @Nullable
    public List<Foo> safeListFoos(final String prefix) {
        try {
            return listFoos(prefix);
        } catch(final IOException e) {
            return null;
        }
    }

    public Foo getFooWithId(final String fooId) throws IOException {
        return fooService.getFooWithId(fooId).execute().body();
    }

    @Nullable
    public Foo safeGetFooWithId(final String fooId) {
        try {
            return fooService.getFooWithId(fooId).execute().body();
        } catch (IOException e) {
            return null;
        }
    }

    public Optional<Foo> getOptionalFooWithId(final String fooId) throws IOException {
        return fooService.getOptionalFooWithId(fooId).execute().body();
    }

    public Optional<Foo> safeGetOptionalFooWithId(final String fooId) {
        try {
            return fooService.getOptionalFooWithId(fooId).execute().body();
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
