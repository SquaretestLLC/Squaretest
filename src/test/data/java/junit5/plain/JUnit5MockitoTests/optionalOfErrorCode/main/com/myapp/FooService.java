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

import java.util.Optional;

public interface FooService {
    Optional<ErrorCode> putFoo1(final FooData fooData);
    Optional<ErrorInfo> putFoo2(final FooData fooData);
    Optional<Error> putFoo3(final FooData fooData);
    Optional<FailureInfo> putFoo4(final FooData fooData);
    Optional<FooServiceException> putFoo5(final FooData fooData);
    Optional<Throwable> putFoo6(final FooData fooData);

    FooData getFoo(String id) throws FooServiceException;
}
