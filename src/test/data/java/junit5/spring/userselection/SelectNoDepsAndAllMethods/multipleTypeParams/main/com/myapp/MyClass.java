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

import java.util.Map;

public class MyClass<K, V> implements Map.Entry<K, V> {

    private K left;
    private V right;

    public MyClass() {
    }

    @Override
    public K getKey() {
        return left;
    }

    public void setKey(final K setKeyParam) {
        this.left = setKeyParam;
    }

    @Override
    public V getValue() {
        return right;
    }

    @Override
    public V setValue(final V setValueParam) {
        this.right = setValueParam;
        return this.right;
    }
}
