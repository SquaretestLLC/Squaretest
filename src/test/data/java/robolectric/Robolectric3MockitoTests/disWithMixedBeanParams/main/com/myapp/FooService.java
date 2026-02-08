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

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface FooService {
    FooData getFooData(final SearchParams searchParams, final String id) throws FooServiceException;
    FooData getFooData(final SearchParams searchParams, final FooType fooType) throws FooServiceException;
    Optional<FooData> storeFooDataOpt(final SearchParams searchParams, final FooData fooData) throws FooServiceException;
    CompletableFuture<FooData> storeFooDataFuture(final SearchParams searchParams, final FooData fooData) throws FooServiceException;
    InputStream storeFooDataStream(final SearchParams searchParams, final FooData id) throws FooServiceException;
    FooData storeFoo1(final SearchParams searchParams, FooData fooData) throws FooServiceException;
    List<FooData> storeFoo2(final SearchParams searchParams, List<FooData> fooData) throws FooServiceException;
}
