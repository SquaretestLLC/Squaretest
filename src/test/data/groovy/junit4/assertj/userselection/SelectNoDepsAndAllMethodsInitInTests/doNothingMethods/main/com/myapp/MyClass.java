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

import java.io.IOException;

public class MyClass implements BaseInterface {
    @Override
    public void store1(final FooData fooData) {
    }
    
    public void store2(final String fooData) throws IOException {
    }

    public void store3(final SearchParams searchParams, final FooData fooData) {
    }

    public void store4(final ISearchParams searchParams, final FooData fooData) {
    }

    public static void store5(final FooData fooData) {
    }
    
    public static void store6(final String fooData) {
    }

    public static void store7(final SearchParams searchParams, final FooData fooData) {
    }

    public static void store8(final ISearchParams searchParams, final FooData fooData) {
    }
}
