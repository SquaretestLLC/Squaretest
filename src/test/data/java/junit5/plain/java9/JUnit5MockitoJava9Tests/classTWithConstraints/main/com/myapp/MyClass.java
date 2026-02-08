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

import java.util.Set;
import java.util.stream.Collectors;

public class MyClass {
    private final FooService fooService;

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    public String serialize1(final Class<? extends FooData> theClass, final String input) {
        return theClass.getCanonicalName() + input;
    }

    public String serialize2(final Set<Class<? extends FooData>> theClasses, final String input) {
        return theClasses.stream().map(Class::getCanonicalName).collect(Collectors.joining(", ")) + input;
    }

    public Set<Class<? extends FooData>> getFoos1(final String id) {
        return fooService.getFoos1(id);
    }
}
