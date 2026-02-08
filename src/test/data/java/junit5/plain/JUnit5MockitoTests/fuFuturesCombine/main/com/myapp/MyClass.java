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

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MyClass {
    private final ExecutorService executorService;
    private final FooService fooService;
    private final FooService oldFooService;
    private final BarService barService;
    private final OtherService otherDataService;
    private final MetricsService metricsService;

    public MyClass(ExecutorService executorService, FooService fooService, FooService oldFooService, BarService barService, OtherService otherDataService, MetricsService metricsService) {
        this.executorService = executorService;
        this.fooService = fooService;
        this.oldFooService = oldFooService;
        this.barService = barService;
        this.otherDataService = otherDataService;
        this.metricsService = metricsService;
    }

    public OtherData getFooAndBar1(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<OtherData> otherDataFuture = fooFuture.thenCombine(barFuture,
                (fooData, barData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()));
        try {
            return otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    public OtherData getFooAndBar2(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<OtherData> otherDataFuture = barFuture.thenCombine(fooFuture,
                (barData, fooData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()));
        try {
            return otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    public OtherData getFooAndBar3(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<OtherData> otherDataFuture = fooFuture.thenCombineAsync(barFuture,
                (fooData, barData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()));
        try {
            return otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    public OtherData getFooAndBar4(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<OtherData> otherDataFuture = barFuture.thenCombineAsync(fooFuture,
                (barData, fooData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()));
        try {
            return otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public OtherData getFooAndBar5(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<OtherData> otherDataFuture = fooFuture.thenCombineAsync(barFuture,
                (fooData, barData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()), executorService);
        try {
            return otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    public OtherData getFooAndBar6(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<OtherData> otherDataFuture = barFuture.thenCombineAsync(fooFuture,
                (barData, fooData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()), executorService);
        try {
            return otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public OtherData getFooAndBar7(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        final CompletableFuture<OtherData> otherDataFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()))
                .thenCombineAsync(fooFuture, (barData, fooData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()), executorService);
        try {
            return otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    public OtherData getFooAndBar8(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId).exceptionally(ex -> oldFooService.getFooById1(fooId));
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<OtherData> otherDataFuture = barFuture.thenCombineAsync(fooFuture,
                (barData, fooData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()), executorService);
        try {
            return otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public OtherData getFooAndBar9(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId).exceptionallyAsync(ex -> oldFooService.getFooById1(fooId));
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<OtherData> otherDataFuture = barFuture.thenCombineAsync(fooFuture,
                (barData, fooData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()), executorService);
        try {
            return otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public OtherData getFooAndBar10(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId).exceptionallyAsync(ex -> oldFooService.getFooById1(fooId), executorService);
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<OtherData> otherDataFuture = barFuture.thenCombineAsync(fooFuture,
                (barData, fooData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()), executorService);
        try {
            return otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public OtherData getFooAndBar11(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId).exceptionallyCompose(ex -> oldFooService.getFooByIdAsync1(fooId));
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<OtherData> otherDataFuture = barFuture.thenCombineAsync(fooFuture,
                (barData, fooData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()), executorService);
        try {
            return otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public OtherData getFooAndBar12(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId).exceptionallyComposeAsync(ex -> oldFooService.getFooByIdAsync1(fooId));
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<OtherData> otherDataFuture = barFuture.thenCombineAsync(fooFuture,
                (barData, fooData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()), executorService);
        try {
            return otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public OtherData getFooAndBar13(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId).exceptionallyComposeAsync(ex -> oldFooService.getFooByIdAsync1(fooId), executorService);
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<OtherData> otherDataFuture = barFuture.thenCombineAsync(fooFuture,
                (barData, fooData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()), executorService);
        try {
            return otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public OtherData getFooAndBar14(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        final CompletableFuture<BarData> barFuture = fooFuture.applyToEither(oldFooService.getFooByIdAsync1(fooId), x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<OtherData> otherDataFuture = barFuture.thenCombineAsync(fooFuture,
                (barData, fooData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()), executorService);
        try {
            return otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public OtherData getFooAndBar15(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        final CompletableFuture<BarData> barFuture = fooFuture.applyToEitherAsync(oldFooService.getFooByIdAsync1(fooId), x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<OtherData> otherDataFuture = barFuture.thenCombineAsync(fooFuture,
                (barData, fooData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()), executorService);
        try {
            return otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public OtherData getFooAndBar16(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        final CompletableFuture<BarData> barFuture = fooFuture.applyToEitherAsync(oldFooService.getFooByIdAsync1(fooId), x -> barService.getBarForId1(x.getBarId()), executorService);
        final CompletableFuture<OtherData> otherDataFuture = barFuture.thenCombineAsync(fooFuture,
                (barData, fooData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()), executorService);
        try {
            return otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public FooData getFooAndBar17(final String fooId) {
        final CompletableFuture<FooData> fooFuture = new CompletableFuture<>();
        fooFuture.complete(fooService.getFooById1(fooId));
        try {
            return fooFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public FooData getFooAndBar18(final String fooId) {
        // The generated alt-flow test will not be correct.
        // We just need to ensure it doesn't crash.
        final CompletableFuture<FooData> fooFuture = new CompletableFuture<>();
        fooFuture.completeAsync(() -> fooService.getFooById1(fooId));
        try {
            return fooFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public FooData getFooAndBar19(final String fooId) {
        // The generated alt-flow test will not be correct.
        // We just need to ensure it doesn't crash.
        final CompletableFuture<FooData> fooFuture = new CompletableFuture<>();
        fooFuture.completeAsync(() -> fooService.getFooById1(fooId), executorService);
        try {
            return fooFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public FooData getFooAndBar20(final String fooId) {
        final CompletableFuture<FooData> fooFuture = new CompletableFuture<FooData>().completeAsync(() -> fooService.getFooById1(fooId), executorService);
        try {
            return fooFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateFooData1(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        CompletableFuture<Void> isValidFuture = fooFuture.acceptEither(oldFooService.getFooByIdAsync1(fooId), new Consumer<FooData>() {
            @Override
            public void accept(FooData fooData) {
                metricsService.recordValidFoo(fooId);
            }
        });
        try {
            isValidFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateFooData2(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        CompletableFuture<Void> isValidFuture = fooFuture.acceptEitherAsync(oldFooService.getFooByIdAsync1(fooId), fooData -> metricsService.recordValidFoo(fooId));
        try {
            isValidFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateFooData3(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        CompletableFuture<Void> isValidFuture = fooFuture.acceptEitherAsync(oldFooService.getFooByIdAsync1(fooId), fooData -> metricsService.recordValidFoo(fooId), executorService);
        try {
            isValidFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateFooData4(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        CompletableFuture<Void> isValidFuture = fooFuture.runAfterEither(oldFooService.getFooByIdAsync1(fooId), new Runnable() {
            @Override
            public void run() {
                metricsService.recordValidFoo(fooId);
            }
        });
        try {
            isValidFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateFooData5(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        CompletableFuture<Void> isValidFuture = fooFuture.runAfterEitherAsync(oldFooService.getFooByIdAsync1(fooId), () -> metricsService.recordValidFoo(fooId));
        try {
            isValidFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateFooData6(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        CompletableFuture<Void> isValidFuture = fooFuture.runAfterEitherAsync(oldFooService.getFooByIdAsync1(fooId), () -> metricsService.recordValidFoo(fooId), executorService);
        try {
            isValidFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateFooData7(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId).exceptionally(ex -> oldFooService.getFooById1(fooId));
        Future<Void> isValidFuture = fooFuture.thenAccept(new Consumer<FooData>() {
            @Override
            public void accept(FooData fooData) {
                metricsService.recordValidFoo(fooId);
            }
        });
        try {
            isValidFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateFooData8(final String fooId) {
        final Future<Void> isValidFuture = fooService.getFooByIdAsync1(fooId).exceptionally(ex -> oldFooService.getFooById1(fooId))
                .thenAccept(fooData -> metricsService.recordValidFoo(fooId));
        try {
            isValidFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateFooData9(final String fooId) {
        final Future<Void> isValidFuture = fooService.getFooByIdAsync1(fooId).exceptionally(ex -> oldFooService.getFooById1(fooId))
                .thenAcceptAsync(fooData -> metricsService.recordValidFoo(fooId));
        try {
            isValidFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateFooData10(final String fooId) {
        final Future<Void> isValidFuture = fooService.getFooByIdAsync1(fooId).exceptionally(ex -> oldFooService.getFooById1(fooId))
                .thenAcceptAsync(fooData -> metricsService.recordValidFoo(fooId), executorService);
        try {
            isValidFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateFooData11(final String fooId) {
        CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        CompletableFuture<Void> isValidFuture = fooFuture.acceptEither(fooService.getFooByIdAsync1(fooId), fooData -> metricsService.recordValidFoo(fooId));
        try {
            isValidFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateFooData12(final String fooId) {
        CompletableFuture<Void> isValidFuture = fooService.getFooByIdAsync1(fooId).acceptEither(fooService.getFooByIdAsync1(fooId), fooData -> metricsService.recordValidFoo(fooId));
        try {
            isValidFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateFooData13(final String fooId) {
        CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        CompletableFuture<Void> isValidFuture = fooFuture.acceptEitherAsync(fooService.getFooByIdAsync1(fooId), fooData -> metricsService.recordValidFoo(fooId));
        try {
            isValidFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateFooData14(final String fooId) {
        final Future<Void> isValidFuture = fooService.getFooByIdAsync1(fooId)
                .exceptionally(ex -> oldFooService.getFooById1(fooId))
                .thenRun(() -> metricsService.recordValidFoo(fooId));
        try {
            isValidFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateFooData15(final String fooId) {
        final Future<Void> isValidFuture = fooService.getFooByIdAsync1(fooId)
                .exceptionally(ex -> oldFooService.getFooById1(fooId))
                .thenRunAsync(() -> metricsService.recordValidFoo(fooId));
        try {
            isValidFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateFooData16(final String fooId) {
        final Future<Void> isValidFuture = fooService.getFooByIdAsync1(fooId)
                .exceptionally(ex -> oldFooService.getFooById1(fooId))
                .thenRunAsync(() -> metricsService.recordValidFoo(fooId), executorService);
        try {
            isValidFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateFooData17(final String fooId) {
        final Future<Void> isValidFuture = fooService.getFooByIdAsync1(fooId)
                .whenComplete((fooData, throwable) -> metricsService.recordTasksComplete(fooId))
                .thenRunAsync(() -> metricsService.recordValidFoo(fooId), executorService);
        try {
            isValidFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateFooData18(final String fooId) {
        final Future<Void> isValidFuture = fooService.getFooByIdAsync1(fooId)
                .whenCompleteAsync((fooData, throwable) -> metricsService.recordTasksComplete(fooId))
                .thenRunAsync(() -> metricsService.recordValidFoo(fooId), executorService);
        try {
            isValidFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void validateOtherData1(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<Void> otherDataFuture = fooFuture.thenAcceptBoth(barFuture,
                (fooData, barData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()));
        try {
            otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateOtherData2(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<Void> otherDataFuture = fooFuture.thenAcceptBothAsync(barFuture,
                (fooData, barData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()));
        try {
            otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateOtherData3(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<Void> otherDataFuture = fooFuture.thenAcceptBothAsync(barFuture,
                (fooData, barData) -> otherDataService.getData1(fooData.getId(), barData.getBarId()), executorService);
        try {
            otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateOtherData4(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<Void> otherDataFuture = fooFuture.runAfterBoth(barFuture,
                () -> {
                    try {
                        otherDataService.getData1(fooFuture.get().getId(), barFuture.get().getBarId());
                    } catch (InterruptedException e) {
                        metricsService.recordInterruptedException(fooId);
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        metricsService.recordExecutionException(fooId);
                        throw new RuntimeException(e);
                    }
                });
        try {
            otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateOtherData5(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<Void> otherDataFuture = fooFuture.runAfterBothAsync(barFuture,
                () -> {
                    try {
                        otherDataService.getData1(fooFuture.get().getId(), barFuture.get().getBarId());
                    } catch (InterruptedException e) {
                        metricsService.recordInterruptedException(fooId);
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        metricsService.recordExecutionException(fooId);
                        throw new RuntimeException(e);
                    }
                });
        try {
            otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateOtherData6(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()));
        final CompletableFuture<Void> otherDataFuture = fooFuture.runAfterBothAsync(barFuture,
                () -> {
                    try {
                        otherDataService.getData1(fooFuture.get().getId(), barFuture.get().getBarId());
                    } catch (InterruptedException e) {
                        metricsService.recordInterruptedException(fooId);
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        metricsService.recordExecutionException(fooId);
                        throw new RuntimeException(e);
                    }
                }, executorService);
        try {
            otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateOtherData7(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()))
                .completeOnTimeout(null, getDefaultTimeout(), TimeUnit.SECONDS);
        final CompletableFuture<Void> otherDataFuture = fooFuture.runAfterBothAsync(barFuture,
                () -> {
                    try {
                        otherDataService.getData1(fooFuture.get().getId(), barFuture.get().getBarId());
                    } catch (InterruptedException e) {
                        metricsService.recordInterruptedException(fooId);
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        metricsService.recordExecutionException(fooId);
                        throw new RuntimeException(e);
                    }
                }, executorService);
        try {
            otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateOtherData8(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        final CompletableFuture<BarData> barFuture = fooFuture.thenApply(x -> barService.getBarForId1(x.getBarId()))
                .orTimeout(getDefaultTimeout(), TimeUnit.SECONDS)
                .minimalCompletionStage()
                .toCompletableFuture();
        final CompletableFuture<Void> otherDataFuture = fooFuture.runAfterBothAsync(barFuture,
                () -> {
                    try {
                        otherDataService.getData1(fooFuture.get().getId(), barFuture.get().getBarId());
                    } catch (InterruptedException e) {
                        metricsService.recordInterruptedException(fooId);
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        metricsService.recordExecutionException(fooId);
                        throw new RuntimeException(e);
                    }
                }, executorService);
        try {
            otherDataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getFooData1(final String fooId) {
        final Future<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        return fooFuture.isDone();
    }

    public boolean getFooData2(final String fooId) {
        final CompletableFuture<FooData> fooFuture = fooService.getFooByIdAsync1(fooId);
        return fooFuture.isCompletedExceptionally();
    }

    private long getDefaultTimeout() {
        metricsService.recordGetDefaultTimeoutCalled();
        return 100;
    }
}
