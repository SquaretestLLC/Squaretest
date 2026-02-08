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

import com.google.api.core.ApiFuture;
import com.google.api.core.SettableApiFuture;

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

    public ApiFuture<SimpleBean> createListenableFutureOfBean(String key) {
        return foo.createListenableFutureOfBean(key);
    }

    public SettableApiFuture<SimpleBean> createSettableFutureOfBean(String key) {
        return foo.createSettableFutureOfBean(key);
    }

    public SettableApiFuture<? extends SimpleBean> createSettableFutureOfBeanSubtype(String key) {
        return foo.createSettableFutureOfBeanSubtype(key);
    }

    public ApiFuture<List<SimpleBean>> createListenableFutureOfListOfBean(String key) {
        return foo.createListenableFutureOfListOfBean(key);
    }

    public SettableApiFuture<List<SimpleBean>> createSettableFutureOfListOfBean(String key) {
        return foo.createSettableFutureOfListOfBean(key);
    }

    public ApiFuture<ApiFuture<SimpleBean>> createNestedListenableFutureOfBean(String key) {
        return foo.createNestedListenableFutureOfBean(key);
    }

    public SettableApiFuture<SettableApiFuture<SimpleBean>> createNestedSettableFutureOfBean(String key) {
        return foo.createNestedSettableFutureOfBean(key);
    }

    public ApiFuture<Bar> createListenableFutureOfBar(String key) {
        return foo.createListenableFutureOfBar(key);
    }

    public SettableApiFuture<Bar> createSettableFutureOfBar(String key) {
        return foo.createSettableFutureOfBar(key);
    }

    public ApiFuture<?> createListenableFutureOfUnknown(String key) {
        return foo.createListenableFutureOfUnknown(key);
    }

    public SettableApiFuture<?> createSettableFutureOfUnknown(String key) {
        return foo.createSettableFutureOfUnknown(key);
    }

    public SettableApiFuture<List<?>> createSettableFutureOfListOfUnknown(String key) {
        return foo.createSettableFutureOfListOfUnknown(key);
    }

    public List<?> createListWithWildcard(String key) {
        return foo.createListWithWildcard(key);
    }

    public Map<String, ?> createMapWithWildcard(String key) {
        return foo.createMapWithWildcard(key);
    }

    public ApiFuture<Integer> createListenableFutureOfInt(String key) {
        return foo.createListenableFutureOfInt(key);
    }

    public SettableApiFuture<Integer> createSettableFutureOfInt(String key) {
        return foo.createSettableFutureOfInt(key);
    }

    public ApiFuture createListenableFutureOfNoType(String key) {
        return foo.createListenableFutureOfNoType(key);
    }

    public SettableApiFuture createSettableFutureOfNoType(String key) {
        return foo.createSettableFutureOfNoType(key);
    }

    public ApiFuture<String[]> createListenableFutureOfStringArray(String key) {
        return foo.createListenableFutureOfStringArray(key);
    }

    public SettableApiFuture<String[]> createSettableFutureOfStringArray(String key) {
        return foo.createSettableFutureOfStringArray(key);
    }
}
