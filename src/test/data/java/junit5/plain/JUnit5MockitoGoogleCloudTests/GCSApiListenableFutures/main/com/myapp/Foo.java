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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Foo {
    public ApiFuture<SimpleBean> createListenableFutureOfBean(final String key) {
        return SettableApiFuture.create();
    }
    public SettableApiFuture<SimpleBean> createSettableFutureOfBean(final String key) {
        return SettableApiFuture.create();
    }
    public SettableApiFuture<? extends SimpleBean> createSettableFutureOfBeanSubtype(final String key) {
        return SettableApiFuture.create();
    }
    public ApiFuture<List<SimpleBean>> createListenableFutureOfListOfBean(final String key) {
        return SettableApiFuture.create();
    }
    public SettableApiFuture<List<SimpleBean>> createSettableFutureOfListOfBean(final String key) {
        return SettableApiFuture.create();
    }
    public ApiFuture<ApiFuture<SimpleBean>> createNestedListenableFutureOfBean(final String key) {
        return SettableApiFuture.create();
    }
    public SettableApiFuture<SettableApiFuture<SimpleBean>> createNestedSettableFutureOfBean(final String key) {
        return SettableApiFuture.create();
    }
    public ApiFuture<Bar> createListenableFutureOfBar(final String key) {
        return SettableApiFuture.create();
    }
    public SettableApiFuture<Bar> createSettableFutureOfBar(final String key) {
        return SettableApiFuture.create();
    }
    public ApiFuture<?> createListenableFutureOfUnknown(final String key) {
        return SettableApiFuture.create();
    }
    public SettableApiFuture<?> createSettableFutureOfUnknown(final String key) {
        return SettableApiFuture.create();
    }
    public SettableApiFuture<List<?>> createSettableFutureOfListOfUnknown(final String key) {
        return SettableApiFuture.create();
    }
    public List<?> createListWithWildcard(final String key) {
        return Arrays.asList();
    }
    public Map<String, ?> createMapWithWildcard(final String key) {
        return new HashMap<>();
    }
    public ApiFuture<Integer> createListenableFutureOfInt(final String key) {
        return SettableApiFuture.create();
    }
    public SettableApiFuture<Integer> createSettableFutureOfInt(final String key) {
        return SettableApiFuture.create();
    }
    public ApiFuture createListenableFutureOfNoType(final String key) {
        return SettableApiFuture.create();
    }
    public SettableApiFuture createSettableFutureOfNoType(final String key) {
        return SettableApiFuture.create();
    }
    public ApiFuture<String[]> createListenableFutureOfStringArray(final String key) {
        return SettableApiFuture.create();
    }
    public SettableApiFuture<String[]> createSettableFutureOfStringArray(final String key) {
        return SettableApiFuture.create();
    }
}