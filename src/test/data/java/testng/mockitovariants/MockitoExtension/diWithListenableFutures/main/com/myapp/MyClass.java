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

import java.util.Optional;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MyClass {
    private Foo foo;

    public MyClass(Foo foo) {
        this.foo = foo;
    }

    public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) {
        return coll.iterator().next();
    }

    public ListenableFuture<SimpleBean> createListenableFutureOfBean(String key) {
        return foo.createListenableFutureOfBean(key);
    }

    public SettableFuture<SimpleBean> createSettableFutureOfBean(String key) {
        return foo.createSettableFutureOfBean(key);
    }

    public SettableFuture<? extends SimpleBean> createSettableFutureOfBeanSubtype(String key) {
        return foo.createSettableFutureOfBeanSubtype(key);
    }

    public ListenableFuture<List<SimpleBean>> createListenableFutureOfListOfBean(String key) {
        return foo.createListenableFutureOfListOfBean(key);
    }

    public SettableFuture<List<SimpleBean>> createSettableFutureOfListOfBean(String key) {
        return foo.createSettableFutureOfListOfBean(key);
    }

    public ListenableFuture<ListenableFuture<SimpleBean>> createNestedListenableFutureOfBean(String key) {
        return foo.createNestedListenableFutureOfBean(key);
    }

    public SettableFuture<SettableFuture<SimpleBean>> createNestedSettableFutureOfBean(String key) {
        return foo.createNestedSettableFutureOfBean(key);
    }

    public ListenableFuture<Bar> createListenableFutureOfBar(String key) {
        return foo.createListenableFutureOfBar(key);
    }

    public SettableFuture<Bar> createSettableFutureOfBar(String key) {
        return foo.createSettableFutureOfBar(key);
    }

    public ListenableFuture<?> createListenableFutureOfUnknown(String key) {
        return foo.createListenableFutureOfUnknown(key);
    }

    public SettableFuture<?> createSettableFutureOfUnknown(String key) {
        return foo.createSettableFutureOfUnknown(key);
    }

    public SettableFuture<List<?>> createSettableFutureOfListOfUnknown(String key) {
        return foo.createSettableFutureOfListOfUnknown(key);
    }

    public List<?> createListWithWildcard(String key) {
        return foo.createListWithWildcard(key);
    }

    public Map<String, ?> createMapWithWildcard(String key) {
        return foo.createMapWithWildcard(key);
    }

    public ListenableFuture<Integer> createListenableFutureOfInt(String key) {
        return foo.createListenableFutureOfInt(key);
    }

    public SettableFuture<Integer> createSettableFutureOfInt(String key) {
        return foo.createSettableFutureOfInt(key);
    }

    public ListenableFuture createListenableFutureOfNoType(String key) {
        return foo.createListenableFutureOfNoType(key);
    }

    public SettableFuture createSettableFutureOfNoType(String key) {
        return foo.createSettableFutureOfNoType(key);
    }

    public ListenableFuture<String[]> createListenableFutureOfStringArray(String key) {
        return foo.createListenableFutureOfStringArray(key);
    }

    public SettableFuture<String[]> createSettableFutureOfStringArray(String key) {
        return foo.createSettableFutureOfStringArray(key);
    }

    public ListenableFuture<Optional<String>> createListenableFutureOfOptionalString(String key) {
        return foo.createListenableFutureOfOptionalString(key);
    }

    public SettableFuture<Optional<String>> createSettableFutureOfOptionalString(String key) {
        return foo.createSettableFutureOfOptionalString(key);
    }

    public ListenableFuture<Optional<SimpleBean>> createListenableFutureOfOptionalSimpleBean(String key) {
        return foo.createListenableFutureOfOptionalSimpleBean(key);
    }

    public SettableFuture<Optional<SimpleBean>> createSettableFutureOfOptionalSimpleBean(String key) {
        return foo.createSettableFutureOfOptionalSimpleBean(key);
    }
}
