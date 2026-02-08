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

import com.myapp.multi.CloseableIterable;
import com.myapp.multi.CloseableIterableWithStaticCreatorMethod;
import com.myapp.multi.FooCreator;
import com.myapp.multi.SimpleBean;

public class MyClass {
    private final FooCreator fooCreator;

    public MyClass(final FooCreator fooCreator) {
        this.fooCreator = fooCreator;
    }

    public CloseableIterable<String> makeTheStrings(final String key) {
        return fooCreator.makeTheStrings(key);
    }

    public CloseableIterableWithStaticCreatorMethod<String> makeTheStrings1(final String key) {
        return fooCreator.makeTheStrings1(key);
    }

    public CloseableIterable<SimpleBean> makeTheBeans(final String key) {
        return fooCreator.makeTheBeans(key);
    }

    public CloseableIterableWithStaticCreatorMethod<SimpleBean> makeTheBeans1(final String key) {
        return fooCreator.makeTheBeans1(key);
    }
}
