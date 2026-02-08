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

import java.util.Collections;
import java.util.List;

public class Foo {

    public static class Bar {
    }
    public GenericFooWithConstructor<Bar> createGenericFooWithNonBeanSubtype() {
        return new GenericFooWithConstructor<>(new Bar());
    }

    public GenericFooWithConstructor<SimpleBean> createGenericFooWithBeanSubtype() {
        return new GenericFooWithConstructor<>(new SimpleBean());
    }

    public GenericFooWithVargsConstructor<Bar> createGenericFooWithVargsConstructor() {
        return new GenericFooWithVargsConstructor<>(new Bar());
    }

    public GenericFooWithVargsConstructor<SimpleBean> createGenericFooWithVargsConstructorAndBean() {
        return new GenericFooWithVargsConstructor<>(new SimpleBean());
    }

    public GenericFooWithMultiArgConstructor<Bar> createGenericFooWithMultiArgConstructor() {
        return new GenericFooWithMultiArgConstructor<>(new Bar(), new Bar());
    }

    public GenericFooWithMultiArgConstructor<SimpleBean> createGenericFooWithMultiArgConstructorWithBean() {
        return new GenericFooWithMultiArgConstructor<>(new SimpleBean(), new SimpleBean());
    }

    public GenericFooWithIntConstructor<Bar> createGenericFooWithIntConstructor() {
        return new GenericFooWithIntConstructor<>(10);
    }

    public GenericFooWithIntConstructor<SimpleBean> createGenericFooWithIntConstructorWithBean() {
        return new GenericFooWithIntConstructor<>(10);
    }

    public GenericFooWithIntConstructor<List<Bar>> createGenericFooWithIntConstructorAndListOfType() {
        return new GenericFooWithIntConstructor<>(10);
    }

    public GenericFooWithIntConstructor<List<SimpleBean>> createGenericFooWithIntConstructorWithListOfBean() {
        return new GenericFooWithIntConstructor<>(10);
    }

    public GenericFooWithDefaultConstructor<Bar> createGenericFooWithDefaultConstructor() {
        return new GenericFooWithDefaultConstructor<>();
    }

    public GenericFooWithDefaultConstructor<SimpleBean> createGenericFooWithDefaultConstructorWithBean() {
        return new GenericFooWithDefaultConstructor<>();
    }

    public GenericFooWithDefaultConstructor<List<Bar>> createGenericFooWithDefaultConstructorAndListOfType() {
        return new GenericFooWithDefaultConstructor<>();
    }

    public GenericFooWithDefaultConstructor<List<SimpleBean>> createGenericFooWithDefaultConstructorWithBeanWithListOfBean() {
        return new GenericFooWithDefaultConstructor<>();
    }

    public GenericFooWithStaticInit<Bar> createFooWithStaticInitAndNonBeanSubtype() {
        return GenericFooWithStaticInit.of(new Bar());
    }

    public GenericFooWithStaticInit<SimpleBean> createFooWithStaticInitAndBeanSubtype() {
        return GenericFooWithStaticInit.of(new SimpleBean());
    }

    public GenericFooWithStaticInit<List<SimpleBean>> createFooWithStaticInitAndListOfBeanSubtype() {
        return GenericFooWithStaticInit.of(Collections.singletonList(new SimpleBean()));
    }

    public GenericFooWithStaticInitVargs<Bar> createFooWithStaticInitAndVargs() {
        return GenericFooWithStaticInitVargs.of(new Bar());
    }

    public GenericFooWithStaticInitVargs<SimpleBean> createFooWithStaticInitAndVargsAndBean() {
        return GenericFooWithStaticInitVargs.of(new SimpleBean());
    }

    public GenericFooWithListConstructor<Bar> createGenericFooWithListConstructor() {
        return new GenericFooWithListConstructor<>(Collections.singletonList(new Bar()));
    }

    public GenericFooWithListConstructor<SimpleBean> createGenericFooWithListConstructorAndBean() {
        return new GenericFooWithListConstructor<>(Collections.singletonList(new SimpleBean()));
    }

}