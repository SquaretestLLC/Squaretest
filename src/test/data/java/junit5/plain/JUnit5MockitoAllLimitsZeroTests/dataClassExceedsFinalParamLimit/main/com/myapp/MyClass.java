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

public class MyClass {
    private final FooService fooService;

    public MyClass(FooService fooService) {
        this.fooService = fooService;
    }

    public BigDataNoOptions getBigData1(String id) {
        return fooService.getBigData1(id);
    }

    public List<BigDataNoOptions> getBigData2(String id) {
        return fooService.getBigData2(id);
    }

    public Optional<BigDataNoOptions> getBigData3(String id) {
        return fooService.getBigData3(id);
    }

    public Optional<List<BigDataNoOptions>> getBigData4(String id) {
        return fooService.getBigData4(id);
    }

    public BigDataOtherOption getBigData5(String id) {
        return fooService.getBigData5(id);
    }

    public List<BigDataOtherOption> getBigData6(String id) {
        return fooService.getBigData6(id);
    }

    public Optional<BigDataOtherOption> getBigData7(String id) {
        return fooService.getBigData7(id);
    }

    public Optional<List<BigDataOtherOption>> getBigData8(String id) {
        return fooService.getBigData8(id);
    }

    public CompletableFuture<BigDataNoOptions> getBigData9(String id) {
        return fooService.getBigData9(id);
    }

    public CompletableFuture<List<BigDataNoOptions>> getBigData10(String id) {
        return fooService.getBigData10(id);
    }
}
