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

public interface FooService {
    MyList<FooData1> getFooData1(final String fooId);
    <K> K loadData1(final Class<K> theClass, final String theId);
    <K> List<K> loadDatas1(final Class<K> theClass, final String theId);
    <K> K loadData2(final Class<K> theClass, final String theId);
}
