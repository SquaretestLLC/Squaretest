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
package com.myapp.multi;

public class FooCreator {
    public CloseableIterable<String> makeTheStrings(final String key) {
        return CloseableIterable.empty();
    }

    public CloseableIterableWithStaticCreatorMethod<String> makeTheStrings1(final String key) {
        return CloseableIterableWithStaticCreatorMethod.of(key);
    }

    public CloseableIterable<SimpleBean> makeTheBeans(final String key) {
        return CloseableIterable.empty();
    }

    public CloseableIterableWithStaticCreatorMethod<SimpleBean> makeTheBeans1(final String key) {
        return CloseableIterableWithStaticCreatorMethod.of(new SimpleBean());
    }
}
