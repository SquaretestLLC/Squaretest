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

import org.springframework.util.concurrent.ListenableFuture ;
import org.springframework.util.concurrent.SettableListenableFuture ;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Foo {
    public ListenableFuture<SimpleBean> createListenableFutureOfBean(final String key) {
        return new SettableListenableFuture<>();
    }
    public SettableListenableFuture<SimpleBean> createSettableFutureOfBean(final String key) {
        return new SettableListenableFuture<>();
    }
    public SettableListenableFuture<? extends SimpleBean> createSettableFutureOfBeanSubtype(final String key) {
        return new SettableListenableFuture<>();
    }
    public ListenableFuture<List<SimpleBean>> createListenableFutureOfListOfBean(final String key) {
        return new SettableListenableFuture<>();
    }
    public SettableListenableFuture<List<SimpleBean>> createSettableFutureOfListOfBean(final String key) {
        return new SettableListenableFuture<>();
    }
    public ListenableFuture<ListenableFuture<SimpleBean>> createNestedListenableFutureOfBean(final String key) {
        return new SettableListenableFuture<>();
    }
    public SettableListenableFuture<SettableListenableFuture<SimpleBean>> createNestedSettableFutureOfBean(final String key) {
        return new SettableListenableFuture<>();
    }
    public ListenableFuture<Bar> createListenableFutureOfBar(final String key) {
        return new SettableListenableFuture<>();
    }
    public SettableListenableFuture<Bar> createSettableFutureOfBar(final String key) {
        return new SettableListenableFuture<>();
    }
    public ListenableFuture<?> createListenableFutureOfUnknown(final String key) {
        return new SettableListenableFuture<>();
    }
    public SettableListenableFuture<?> createSettableFutureOfUnknown(final String key) {
        return new SettableListenableFuture<>();
    }
    public SettableListenableFuture<List<?>> createSettableFutureOfListOfUnknown(final String key) {
        return new SettableListenableFuture<>();
    }
    public List<?> createListWithWildcard(final String key) {
        return Arrays.asList();
    }
    public Map<String, ?> createMapWithWildcard(final String key) {
        return new HashMap<>();
    }
    public ListenableFuture<Integer> createListenableFutureOfInt(final String key) {
        return new SettableListenableFuture<>();
    }
    public SettableListenableFuture<Integer> createSettableFutureOfInt(final String key) {
        return new SettableListenableFuture<>();
    }
    public ListenableFuture createListenableFutureOfNoType(final String key) {
        return new SettableListenableFuture<>();
    }
    public SettableListenableFuture createSettableFutureOfNoType(final String key) {
        return new SettableListenableFuture<>();
    }
    public ListenableFuture<String[]> createListenableFutureOfStringArray(final String key) {
        return new SettableListenableFuture<>();
    }
    public SettableListenableFuture<String[]> createSettableFutureOfStringArray(final String key) {
        return new SettableListenableFuture<>();
    }
}