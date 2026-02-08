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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class MyClass {
    private final FooService mainFooService;

    public MyClass(FooService mainFooService) {
        this.mainFooService = mainFooService;
    }

    public FooData getFooData1(final String id) {
        if (id.startsWith("A")) {
            return mainFooService.getFooData(FooType.Normal);
        }
        return mainFooService.getFooData(id);
    }

    public Optional<FooData> getFooData2(final String id) {
        if (id.startsWith("A")) {
            return mainFooService.getFooDataOpt(FooType.Normal);
        }
        return mainFooService.getFooDataOpt(id);
    }

    public List<FooData> getFooData3(final String id) {
        if (id.startsWith("A")) {
            return mainFooService.getFooDataList(FooType.Normal);
        }
        return mainFooService.getFooDataList(id);
    }

    public CompletableFuture<FooData> getFooData4(final String id) {
        if (id.startsWith("A")) {
            return mainFooService.getFooDataFuture(FooType.Normal);
        }
        return mainFooService.getFooDataFuture(id);
    }

    public String getFooData(final String id) throws IOException {
        if (id.startsWith("A")) {
            InputStream inputStream = mainFooService.getFooDataStream(FooType.Normal);
            return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).toString();
        }
        return new String(mainFooService.getFooDataStream(id).readAllBytes());
    }
}
