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
import java.util.concurrent.CompletableFuture;

public interface FooService {
    BigDataNoOptions getBigData1(final String id);
    List<BigDataNoOptions> getBigData2(final String id);
    Optional<BigDataNoOptions> getBigData3(final String id);
    Optional<List<BigDataNoOptions>> getBigData4(final String id);
    BigDataOtherOption getBigData5(final String id);
    List<BigDataOtherOption> getBigData6(final String id);
    Optional<BigDataOtherOption> getBigData7(final String id);
    Optional<List<BigDataOtherOption>> getBigData8(final String id);

    CompletableFuture<BigDataNoOptions> getBigData9(final String id);
    CompletableFuture<List<BigDataNoOptions>> getBigData10(final String id);
}
