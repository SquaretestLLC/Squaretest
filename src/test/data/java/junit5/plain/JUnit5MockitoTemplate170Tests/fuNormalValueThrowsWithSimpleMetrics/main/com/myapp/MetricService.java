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

public interface MetricService {
    void recordStoreFooCalled(String fooId);

    void recordStoreFooExistingFooFound(String fooId);

    void recordActuallyStoringFoo(String fooId);

    void recordNotAuthorized(String id);

    void recordAuthorized(String id);

    void recordExistingFooCallReturned(String fooId);

    void recordCallingFooService(String fooId);

    void recordCallingOldFooService(String fooId);

    void recordFooNotFound(String fooId);

    void recordFooNotFoundInFooService(String id);

    void recordFooNotFoundInOldService(String id);

    void recordFinishedCallingServiceHelper1(String fooId);

    void recordFinishedCallingOldServiceHelper1(String fooId);
}
