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

import com.myapp.wrapped.*;
import com.myapp.wrapped.OtherBean;

import java.util.Optional;

public class MyClass {
    private final FooService fooService;

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    public Wrapper<String> getString(final String id) {
        return fooService.getString(id);
    }

    public Wrapper<Foo> getFoo(final String id) {
        return fooService.getFoo(id);
    }

    public Wrapper<Optional<Foo>> getOptionalFoo(final String id) {
        return fooService.getOptionalFoo(id);
    }

    public WrapperBeanWithListProp<Foo> getWrapperBeanWithListProp(final String id) {
        return fooService.getWrapperBeanWithListProp(id);
    }

    public WrappedBean<OtherBean<Foo>> getTwoLevelWrapped(final String id) {
        return fooService.getTwoLevelWrapped(id);
    }

    public WrappedBeanUsingUnknownTypeWithGeneric<Foo> getWrappedBeanUsingUnknownTypeWithGeneric(final String id) {
        return fooService.getWrappedBeanUsingUnknownTypeWithGeneric(id);
    }
}
