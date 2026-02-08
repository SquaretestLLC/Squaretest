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

import java.util.List;

public class MyClass {
    private Foo foo;

    public MyClass(Foo foo) {
        this.foo = foo;
    }

    public GenericFooWithConstructor<Foo.Bar> createGenericFooWithNonBeanSubtype() {
        return foo.createGenericFooWithNonBeanSubtype();
    }

    public GenericFooWithConstructor<SimpleBean> createGenericFooWithBeanSubtype() {
        return foo.createGenericFooWithBeanSubtype();
    }

    public GenericFooWithVargsConstructor<Foo.Bar> createGenericFooWithVargsConstructor() {
        return foo.createGenericFooWithVargsConstructor();
    }

    public GenericFooWithVargsConstructor<SimpleBean> createGenericFooWithVargsConstructorAndBean() {
        return foo.createGenericFooWithVargsConstructorAndBean();
    }

    public GenericFooWithMultiArgConstructor<Foo.Bar> createGenericFooWithMultiArgConstructor() {
        return foo.createGenericFooWithMultiArgConstructor();
    }

    public GenericFooWithMultiArgConstructor<SimpleBean> createGenericFooWithMultiArgConstructorWithBean() {
        return foo.createGenericFooWithMultiArgConstructorWithBean();
    }

    public GenericFooWithIntConstructor<Foo.Bar> createGenericFooWithIntConstructor() {
        return foo.createGenericFooWithIntConstructor();
    }

    public GenericFooWithIntConstructor<SimpleBean> createGenericFooWithIntConstructorWithBean() {
        return foo.createGenericFooWithIntConstructorWithBean();
    }

    public GenericFooWithIntConstructor<List<Foo.Bar>> createGenericFooWithIntConstructorAndListOfType() {
        return foo.createGenericFooWithIntConstructorAndListOfType();
    }

    public GenericFooWithIntConstructor<List<SimpleBean>> createGenericFooWithIntConstructorWithListOfBean() {
        return foo.createGenericFooWithIntConstructorWithListOfBean();
    }

    public GenericFooWithDefaultConstructor<Foo.Bar> createGenericFooWithDefaultConstructor() {
        return foo.createGenericFooWithDefaultConstructor();
    }

    public GenericFooWithDefaultConstructor<SimpleBean> createGenericFooWithDefaultConstructorWithBean() {
        return foo.createGenericFooWithDefaultConstructorWithBean();
    }

    public GenericFooWithDefaultConstructor<List<Foo.Bar>> createGenericFooWithDefaultConstructorAndListOfType() {
        return foo.createGenericFooWithDefaultConstructorAndListOfType();
    }

    public GenericFooWithDefaultConstructor<List<SimpleBean>> createGenericFooWithDefaultConstructorWithBeanWithListOfBean() {
        return foo.createGenericFooWithDefaultConstructorWithBeanWithListOfBean();
    }

    public GenericFooWithStaticInit<Foo.Bar> createFooWithStaticInitAndNonBeanSubtype() {
        return foo.createFooWithStaticInitAndNonBeanSubtype();
    }

    public GenericFooWithStaticInit<SimpleBean> createFooWithStaticInitAndBeanSubtype() {
        return foo.createFooWithStaticInitAndBeanSubtype();
    }

    public GenericFooWithStaticInit<List<SimpleBean>> createFooWithStaticInitAndListOfBeanSubtype() {
        return foo.createFooWithStaticInitAndListOfBeanSubtype();
    }

    public GenericFooWithStaticInitVargs<Foo.Bar> createFooWithStaticInitAndVargs() {
        return foo.createFooWithStaticInitAndVargs();
    }

    public GenericFooWithStaticInitVargs<SimpleBean> createFooWithStaticInitAndVargsAndBean() {
        return foo.createFooWithStaticInitAndVargsAndBean();
    }

    public GenericFooWithListConstructor<Foo.Bar> createGenericFooWithListConstructor() {
        return foo.createGenericFooWithListConstructor();
    }

    public GenericFooWithListConstructor<SimpleBean> createGenericFooWithListConstructorAndBean() {
        return foo.createGenericFooWithListConstructorAndBean();
    }
}
