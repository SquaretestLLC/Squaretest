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
package com.myapp.suppliers;

import java.util.function.*;

public abstract class FooService {
    public abstract Supplier<Foo> getFoosLazy(final String fooId);
    public abstract Supplier<String> getStringsLazy(final String fooId);
    public abstract IntSupplier getIntsLazy(final String fooId);
    public abstract BooleanSupplier getBoolsLazy(final String fooId);
    public abstract DoubleSupplier getDoublesLazy(final String fooId);
    public abstract LongSupplier getLongsLazy(final String fooId);
}
