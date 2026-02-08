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

import java.util.*;

public class MyClass {

    private FooCreator fooCreator;

    public MyClass(FooCreator fooCreator) {
        this.fooCreator = fooCreator;
    }

    public Optional<List<Foo<FooData>>> createNonBeanFoos() {
        return fooCreator.createNonBeanFoos();
    }

    public Optional<List<FooWithEquals<FooDataWithEquals>>> createNonBeanFooWithEquals() {
        return fooCreator.createNonBeanFooWithEquals();
    }

    public Optional<List<Foo<Bean>>> createBeanFoos() {
        return fooCreator.createBeanFoos();
    }

    public Optional<List<FooWithEquals<BeanWithEquals>>> createBeanFooWithEquals() {
        return fooCreator.createBeanFooWithEquals();
    }

    public Optional<List<FooWithEquals<Bean>>> createBeanFooWithoutEquals() {
        return fooCreator.createBeanFooWithoutEquals();
    }

    public List<List<Bean>> createBeanInsideNestedLists() {
        return fooCreator.createBeanInsideNestedLists();
    }

    public List<List<BeanWithEquals>> createBeanWithEqualsInsideNestedLists1() {
        return Arrays.asList(Arrays.asList(new BeanWithEquals()));
    }

    public List<List<BeanWithEquals>> createBeanWithEqualsInsideNestedLists2() {
        return Collections.emptyList();
    }

    public Map<String, Map<String, FooWithEquals<BeanWithEquals>>> getNestedTypesThatOverrideEquals() {
        return new HashMap<>();
    }

    public Map<String, Map<String, FooWithEquals<Bean>>> getNestedTypesThatDoNotOverrideEquals() {
        return new HashMap<>();
    }
}
