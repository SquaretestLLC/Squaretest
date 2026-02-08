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

import java.util.SequencedCollection;

public class MyClass {
    private final FooService fooService;

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    public FooData getFoo1(final String id) {
        return fooService.getFoos1(id).getFirst();
    }
    public FooData getFoo2(final String id) {
        return fooService.getFoos1(id).getLast();
    }
    public FooData getFoo3(final String id) {
        return fooService.getFoos1(id).removeFirst();
    }
    public FooData getFoo4(final String id) {
        return fooService.getFoos1(id).removeLast();
    }

    public SequencedCollection<FooData> getFoos1(final String id) {
        return fooService.getFoos1(id);
    }
    public SequencedCollection<FooData> getFoos2(final String id) {
        return fooService.getFoos1(id).reversed();
    }
}
