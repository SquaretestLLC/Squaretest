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

public interface FooService {
    boolean isValid(final String data);

    boolean exists(String data);

    void createFoo1(String data) throws FooServiceException;

    boolean shouldThrowInvalidFoo(String data);

    boolean shouldRecordCreate(String data);

    String getFoo1(String id) throws FooNotFoundException;

    String getFoo2(String id);

    boolean shouldThrow(String id, FooNotFoundException e);

    boolean shouldRecordOldFoo(String id);

    boolean isValidId(String id);

    String getFooWithValidId1(String id) throws FooServiceException;
}
