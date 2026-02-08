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
package com.myapp.wrappedlists;

import java.util.List;

public abstract class FooService {
    public abstract Wrap<List<Foo>> getShortWrapFoos(final String fooId);
    public abstract WrapperBean<List<Foo>> getFoos(final String fooId);
    public abstract WrapperWithMultipleArgsConstructor<List<Foo>> getFoosWithMultiArgsConstructor(final String fooId);
    public abstract WrapperWithSingleTypeParamConstructor<List<Foo>> getFoosWithSingleArgConstructor(final String fooId);

    public abstract WrapperBean<Foo> getFoo(final String fooId);
    public abstract WrapperWithMultipleArgsConstructor<Foo> getFooWithMultiArgsConstructor(final String fooId);
    public abstract WrapperWithSingleTypeParamConstructor<Foo> getFooWithSingleArgConstructor(final String fooId);
}
