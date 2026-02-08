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

import java.util.List;
import java.util.Optional;

public interface FooService {
    Optional<FooData> getFooData1(final String fooId);
    Optional<FooData> getFooData2(final String fooId);
    FooData getFooData3(final String fooId);
    FooData getFooData4(final String fooId);
    List<FooData> getFoos1(final String id);
    List<FooData> getFoos2(final FooType fooType);
    void deleteFoos1(final String id);
    boolean canDelete1(final String id);
    String deleteFoos2(final String id);
    void deleteFoos3(final String id) throws FooServiceException;
    FooDataResponse getFoos2(final String id);
}
