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

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MyClass {
    private final ExecutorService executorService;
    private final ConfigProvider configProvider;
    private final FooService fooService;

    public MyClass(
            final ExecutorService executorService,
            final ConfigProvider configProvider, final FooService fooService) {
        this.executorService = executorService;
        this.configProvider = configProvider;
        this.fooService = fooService;
    }

    public Future<FooData> getFooAsync(final String fooId) {
        final Future<FooData> fooDataFuture = executorService.submit(new Callable<FooData>() {
            @Override
            public FooData call() throws Exception {
                return getFooHelper(fooId);
            }
        });
        return fooDataFuture;
    }

    public Future<FooData> getFooAsyncLambda(final String fooId) {
        final Future<FooData> fooDataFuture = executorService.submit(() -> getFooHelper(fooId));
        return fooDataFuture;
    }

    public Future<FooData> getFooAsyncLambdaHelper(final String fooId) {
        final Future<FooData> fooDataFuture = submitHelper(() -> getFooHelper(fooId));
        return fooDataFuture;
    }

    public Future<FooData> getFooAsyncLambdaDirect(final String fooId) {
        final Future<FooData> fooDataFuture = executorService.submit(() -> fooService.getFooById(fooId));
        return fooDataFuture;
    }

    public Future<FooData> getFooAsyncLambdaHelperDirect(final String fooId) {
        final Future<FooData> fooDataFuture = submitHelper(() -> fooService.getFooById(fooId));
        return fooDataFuture;
    }

    public Future<FooData> submitHelper(final Callable<FooData> callable) {
        return executorService.submit(callable);
    }

    public List<Future<FooData>> getMultipleFoosAsync(final String fooId, final String otherFooId) throws InterruptedException {
        return executorService.invokeAll(Arrays.asList(() -> getFooHelper(fooId), () -> getFooHelper(otherFooId)),
                configProvider.getTimeout(), TimeUnit.MILLISECONDS);
    }
    private FooData getFooHelper(final String fooId) {
        return fooService.getFooById(fooId);
    }
}
