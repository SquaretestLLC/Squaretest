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

import org.apache.commons.lang.Validate;

import java.util.List;

public class MyClass {
    private final FooService fooService;
    private final ErrorMessageProvider errorMessageProvider;


    public MyClass(final FooService fooService, final ErrorMessageProvider errorMessageProvider) {
        this.fooService = fooService;
        this.errorMessageProvider = errorMessageProvider;
    }
    public void storeFoo1(final FooData fooData) {
        // Ensure no existing foos.
        Validate.isTrue(fooService.getFoos1(fooData.getId()).isEmpty());
        fooService.storeFoo(fooData);
    }
    public void storeFoo2(final FooData fooData) {
        // Ensure no existing foos.
        Validate.isTrue(fooService.getFoos1(fooData.getId()).isEmpty(), errorMessageProvider.getAlreadyExistsMessage(fooData.getId()));
        fooService.storeFoo(fooData);
    }

    public FooData getFoo1(final String id) {
        final FooData theFoo = fooService.getFoo1(id);
        Validate.notNull(theFoo);
        return theFoo;
    }

    public FooData getFoo2(final String id) {
        final FooData theFoo = fooService.getFoo1(id);
        Validate.notNull(theFoo, errorMessageProvider.getFooNotFoundMessage(id));
        return theFoo;
    }

    public List<FooData> getFoos2(final String id) {
        final List<FooData> foos = fooService.getFoos2(id);
        Validate.notEmpty(foos);
        return foos;
    }
}
