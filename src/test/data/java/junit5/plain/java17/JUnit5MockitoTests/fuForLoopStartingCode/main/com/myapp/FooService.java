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
    int getStartingValue1(final String id);
    int getNextValue1(final String id);
    int getEndingValue1(final String id);
    Optional<Integer> getStartingValue2(final String id);
    Optional<Integer> getNextValue2(final String id);
    Optional<Integer> getEndingValue2(final String id);
    int getStartingValue3(final String id) throws FooServiceException;
    int getNextValue3(final String id) throws FooServiceException;
    int getEndingValue3(final String id) throws FooServiceException;
    Optional<Integer> getStartingValue4(final String id) throws FooServiceException;
    Optional<Integer> getNextValue4(final String id) throws FooServiceException;
    Optional<Integer> getEndingValue4(final String id) throws FooServiceException;

    int getStartingValue5(final String id);
}
