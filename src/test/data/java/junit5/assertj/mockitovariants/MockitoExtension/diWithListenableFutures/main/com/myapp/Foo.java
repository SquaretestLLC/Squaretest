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

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Foo {
    public ListenableFuture<SimpleBean> createListenableFutureOfBean(final String key) {
        return SettableFuture.create();
    }
    public SettableFuture<SimpleBean> createSettableFutureOfBean(final String key) {
        return SettableFuture.create();
    }
    public SettableFuture<? extends SimpleBean> createSettableFutureOfBeanSubtype(final String key) {
        return SettableFuture.create();
    }
    public ListenableFuture<List<SimpleBean>> createListenableFutureOfListOfBean(final String key) {
        return SettableFuture.create();
    }
    public SettableFuture<List<SimpleBean>> createSettableFutureOfListOfBean(final String key) {
        return SettableFuture.create();
    }
    public ListenableFuture<ListenableFuture<SimpleBean>> createNestedListenableFutureOfBean(final String key) {
        return SettableFuture.create();
    }
    public SettableFuture<SettableFuture<SimpleBean>> createNestedSettableFutureOfBean(final String key) {
        return SettableFuture.create();
    }
    public ListenableFuture<Bar> createListenableFutureOfBar(final String key) {
        return SettableFuture.create();
    }
    public SettableFuture<Bar> createSettableFutureOfBar(final String key) {
        return SettableFuture.create();
    }
    public ListenableFuture<?> createListenableFutureOfUnknown(final String key) {
        return SettableFuture.create();
    }
    public SettableFuture<?> createSettableFutureOfUnknown(final String key) {
        return SettableFuture.create();
    }
    public SettableFuture<List<?>> createSettableFutureOfListOfUnknown(final String key) {
        return SettableFuture.create();
    }
    public List<?> createListWithWildcard(final String key) {
        return Arrays.asList();
    }
    public Map<String, ?> createMapWithWildcard(final String key) {
        return new HashMap<>();
    }
    public ListenableFuture<Integer> createListenableFutureOfInt(final String key) {
        return SettableFuture.create();
    }
    public SettableFuture<Integer> createSettableFutureOfInt(final String key) {
        return SettableFuture.create();
    }
    public ListenableFuture createListenableFutureOfNoType(final String key) {
        return SettableFuture.create();
    }
    public SettableFuture createSettableFutureOfNoType(final String key) {
        return SettableFuture.create();
    }
    public ListenableFuture<String[]> createListenableFutureOfStringArray(final String key) {
        return SettableFuture.create();
    }
    public SettableFuture<String[]> createSettableFutureOfStringArray(final String key) {
        return SettableFuture.create();
    }
}