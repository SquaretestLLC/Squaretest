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
package com.myapp.wrapped;

import java.util.Optional;

public abstract class FooService {
    public abstract Wrapper<String> getString(final String id);
    public abstract Wrapper<Foo> getFoo(final String id);
    public abstract Wrapper<Optional<Foo>> getOptionalFoo(final String id);
    public abstract WrapperBeanWithListProp<Foo> getWrapperBeanWithListProp(final String id);
    public abstract WrappedBean<OtherBean<Foo>> getTwoLevelWrapped(final String id);
    public abstract WrappedBeanUsingUnknownTypeWithGeneric<Foo> getWrappedBeanUsingUnknownTypeWithGeneric(final String id);
}
