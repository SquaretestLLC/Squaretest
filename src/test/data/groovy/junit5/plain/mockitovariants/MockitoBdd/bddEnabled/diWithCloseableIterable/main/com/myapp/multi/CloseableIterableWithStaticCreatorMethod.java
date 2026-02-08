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

import javax.annotation.Nonnull;
import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public interface CloseableIterableWithStaticCreatorMethod<T> extends Iterable<T>, Closeable  {

    static <T> CloseableIterableWithStaticCreatorMethod<T> of(T... theValues) {
        return new CloseableIterableWithStaticCreatorMethod<T>() {
            @Override
            public void close() throws IOException {
            }

            @Override
            public Iterator<T> iterator() {
                return Arrays.asList(theValues).iterator();
            }
        };
    }

    /**
     * Empty iterator.
     */
    class Empty<T> implements CloseableIterableWithStaticCreatorMethod<T> {

        private Empty() {
        }

        @Override
        public void close() throws IOException {

        }

        @Nonnull
        @Override
        public Iterator<T> iterator() {
            return Collections.emptyIterator();
        }
    }

    /**
     * Returns an empty iterator.
     */
    static <T> CloseableIterableWithStaticCreatorMethod<T> empty() {
        return new CloseableIterableWithStaticCreatorMethod.Empty<>();
    }
}
