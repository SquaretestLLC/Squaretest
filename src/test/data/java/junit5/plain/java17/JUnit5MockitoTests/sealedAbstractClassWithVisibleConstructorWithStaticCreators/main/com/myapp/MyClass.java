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

public abstract sealed class MyClass permits MySub {
    private final FooProvider fooProvider;
    protected MyClass(final FooProvider fooProvider) {
        this.fooProvider = fooProvider;
    }

    public String getFoo(final String key) {
        return fooProvider.getFoo(key);
    }

    public static MyClass fromString(final String str) {
        return new MySub(new FooProvider());
    }
}
