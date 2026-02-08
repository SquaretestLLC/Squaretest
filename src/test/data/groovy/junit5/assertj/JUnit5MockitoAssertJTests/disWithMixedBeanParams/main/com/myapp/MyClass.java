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
    private final FooService mainFooService;

    public MyClass(FooService mainFooService) {
        this.mainFooService = mainFooService;
    }
    public void storeFooData1(final FooData fooData) {
        mainFooService.storeFoo1(new SearchParamsImpl(), fooData);
    }
    public void storeFooData2(final List<FooData> fooData) {
        mainFooService.storeFoo2(new SearchParamsImpl(), fooData);
    }

    public FooData storeFooData3(final FooData fooData) {
        return mainFooService.storeFoo1(new SearchParamsImpl(), fooData);
    }
    public List<FooData> storeFooData4(final List<FooData> fooData) {
        return mainFooService.storeFoo2(new SearchParamsImpl(), fooData);
    }

    public void storeFooData5(final FooData fooData) {
        mainFooService.storeFooDataOpt(new SearchParamsImpl(), fooData);
    }

    public Optional<FooData> storeFooData6(final FooData fooData) {
        return mainFooService.storeFooDataOpt(new SearchParamsImpl(), fooData);
    }

    public void storeFooData7(final FooData fooData) {
        mainFooService.storeFooDataFuture(new SearchParamsImpl(), fooData);
    }

    public CompletableFuture<FooData> storeFooData8(final FooData fooData) {
        return mainFooService.storeFooDataFuture(new SearchParamsImpl(), fooData);
    }

    public void storeFooData9(final FooData fooData) {
        mainFooService.storeFooDataStream(new SearchParamsImpl(), fooData);
    }

    public String storeFooData10(final FooData fooData) {
        return mainFooService.storeFooDataStream(new SearchParamsImpl(), fooData).toString();
    }
}
