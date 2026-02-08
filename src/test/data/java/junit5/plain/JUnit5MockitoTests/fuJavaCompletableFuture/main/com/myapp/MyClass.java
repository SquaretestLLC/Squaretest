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

public class MyClass {
    private final ExecutorService executorService;
    private final FooService fooService;
    private final BarService barService;
    private final FooExtraService fooExtraService;
    private final OtherService otherDataService;
    private final MetricsService metricsService;

    public MyClass(ExecutorService executorService, FooService fooService, BarService barService, FooExtraService fooExtraService, OtherService otherDataService, MetricsService metricsService) {
        this.executorService = executorService;
        this.fooService = fooService;
        this.barService = barService;
        this.fooExtraService = fooExtraService;
        this.otherDataService = otherDataService;
        this.metricsService = metricsService;
    }

    public FooAndBarData getFooAndBar1(final String fooId) {
        final CompletableFuture<FooData> fooDataFuture = CompletableFuture.supplyAsync(() -> fooService.getFooById(fooId));
        final CompletableFuture<FooAndBarData> barFuture = fooDataFuture.thenApplyAsync(x -> new FooAndBarData(x, barService.getBarForId(x.getBarId())));
        try {
            return barFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public FooAndBarData getFooAndBar2(final String fooId) {
        final CompletableFuture<FooData> fooDataFuture = CompletableFuture.supplyAsync(() -> fooService.getFooById(fooId), executorService);
        final CompletableFuture<FooAndBarData> barFuture = fooDataFuture.thenApplyAsync(x -> new FooAndBarData(x, barService.getBarForId(x.getBarId())), executorService);
        try {
            return barFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public FooAndBarData getFooAndBar3(final String fooId) {
        final CompletableFuture<FooData> fooDataFuture = CompletableFuture.supplyAsync(() -> fooService.getFooById(fooId), executorService);
        final CompletableFuture<BarData> barFuture = fooDataFuture.thenApplyAsync(x -> barService.getBarForId(x.getBarId()), executorService);
        try {
            return new FooAndBarData(fooDataFuture.get(), barFuture.get());
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public FooAndBarData getFooAndBar4(final String fooId) {
        try {
            return CompletableFuture.supplyAsync(() -> fooService.getFooById(fooId))
                    .thenApplyAsync(x -> new FooAndBarData(x, barService.getBarForId(x.getBarId())))
                    .get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public FooAndBarData getFooAndBar5(final String fooId) {
        final CompletableFuture<FooData> fooDataFuture = fooService.getFooByIdAsync(fooId);
        final CompletableFuture<FooAndBarData> barFuture = fooDataFuture.thenApplyAsync(x -> new FooAndBarData(x, barService.getBarForId(x.getBarId())));
        try {
            return barFuture.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public AllData getAllData1(final String fooId) {
        final CompletableFuture<FooData> fooDataFuture = CompletableFuture.supplyAsync(() -> fooService.getFooById(fooId));
        final CompletableFuture<FooExtraData> fooExtra = fooDataFuture.thenApply(x -> fooExtraService.getExtraData(x.getExtraDataId()));
        final CompletableFuture<BarData> barData = fooDataFuture.thenApply(x -> barService.getBarForId(x.getBarId()));
        final CompletableFuture<OtherData> otherData = barData.thenApply(x -> otherDataService.getData(x.getOtherId()));
        try {
            return new AllData(fooDataFuture.get(), fooExtra.get(), barData.get(), otherData.get());
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public AllData getAllData2(final String fooId) {
        final CompletableFuture<FooData> fooDataFuture = CompletableFuture.supplyAsync(() -> fooService.getFooById(fooId));
        final CompletableFuture<FooExtraData> fooExtra = fooDataFuture.thenApplyAsync(x -> fooExtraService.getExtraData(x.getExtraDataId()));
        final CompletableFuture<BarData> barData = fooDataFuture.thenApplyAsync(x -> barService.getBarForId(x.getBarId()));
        final CompletableFuture<OtherData> otherData = barData.thenApplyAsync(x -> otherDataService.getData(x.getOtherId()));
        try {
            return new AllData(fooDataFuture.get(), fooExtra.get(), barData.get(), otherData.get());
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public AllData getAllData3(final String fooId) {
        final CompletableFuture<FooData> fooDataFuture = CompletableFuture.supplyAsync(() -> fooService.getFooById(fooId), executorService);
        final CompletableFuture<FooExtraData> fooExtra = fooDataFuture.thenApplyAsync(x -> fooExtraService.getExtraData(x.getExtraDataId()), executorService);
        final CompletableFuture<BarData> barData = fooDataFuture.thenApplyAsync(x -> barService.getBarForId(x.getBarId()), executorService);
        final CompletableFuture<OtherData> otherData = barData.thenApplyAsync(x -> otherDataService.getData(x.getOtherId()), executorService);
        try {
            return new AllData(fooDataFuture.get(), fooExtra.get(), barData.get(), otherData.get());
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public AllData getAllData4(final String fooId) {
        final CompletableFuture<FooData> fooDataFuture = CompletableFuture.supplyAsync(() -> fooService.getFooById(fooId));
        final CompletableFuture<FooExtraData> fooExtra = fooDataFuture.thenCompose(x -> fooExtraService.getExtraDataAsync(x.getExtraDataId()));
        final CompletableFuture<BarData> barData = fooDataFuture.thenCompose(x -> barService.getBarForIdAsync(x.getBarId()));
        final CompletableFuture<OtherData> otherData = barData.thenCompose(x -> otherDataService.getDataAsync(x.getOtherId()));
        try {
            return new AllData(fooDataFuture.get(), fooExtra.get(), barData.get(), otherData.get());
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public AllData getAllData5(final String fooId) {
        final CompletableFuture<FooData> fooDataFuture = CompletableFuture.supplyAsync(() -> fooService.getFooById(fooId));
        final CompletableFuture<FooExtraData> fooExtra = fooDataFuture.thenComposeAsync(x -> fooExtraService.getExtraDataAsync(x.getExtraDataId()), executorService);
        final CompletableFuture<BarData> barData = fooDataFuture.thenCompose(x -> barService.getBarForIdAsync(x.getBarId()));
        final CompletableFuture<OtherData> otherData = barData.thenCompose(x -> otherDataService.getDataAsync(x.getOtherId()));
        try {
            return new AllData(fooDataFuture.get(), fooExtra.get(), barData.get(), otherData.get());
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public AllData getAllData6(final String fooId) {
        final CompletableFuture<FooData> fooDataFuture = CompletableFuture.supplyAsync(() -> fooService.getFooById(fooId), executorService);
        final CompletableFuture<FooExtraData> fooExtra = fooDataFuture.thenComposeAsync(x -> fooExtraService.getExtraDataAsync(x.getExtraDataId()), executorService);
        final CompletableFuture<BarData> barData = fooDataFuture.thenComposeAsync(x -> barService.getBarForIdAsync(x.getBarId()), executorService);
        final CompletableFuture<OtherData> otherData = barData.thenComposeAsync(x -> otherDataService.getDataAsync(x.getOtherId()), executorService);
        try {
            return new AllData(fooDataFuture.get(), fooExtra.get(), barData.get(), otherData.get());
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public AllData getAllData7(final String fooId) {
        final CompletableFuture<FooData> fooDataFuture = CompletableFuture.supplyAsync(() -> fooService.getFooById(fooId));
        final CompletableFuture<FooExtraData> fooExtra = fooDataFuture.thenComposeAsync(x -> fooExtraService.getExtraDataAsync(x.getExtraDataId()));
        final CompletableFuture<BarData> barData = fooDataFuture.thenComposeAsync(x -> barService.getBarForIdAsync(x.getBarId()));
        final CompletableFuture<OtherData> otherData = barData.thenComposeAsync(x -> otherDataService.getDataAsync(x.getOtherId()));
        try {
            return new AllData(fooDataFuture.get(), fooExtra.get(), barData.get(), otherData.get());
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<FooData> getFooFuture0(final String fooId) {
        return CompletableFuture.completedFuture(fooService.getFooById(fooId));
    }

    public CompletableFuture<FooData> getFooFuture1(final String fooId) {
        return CompletableFuture.completedFuture(fooService.getFooByIdNullable(fooId));
    }

    public CompletableFuture<FooData> getFooFuture2(final String fooId) {
        try {
            FooData foo = fooService.getFooByIdNullable(fooId);
            return CompletableFuture.completedFuture(foo);
        } catch (final Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<FooData> getFooFuture3(final String fooId) {
        return getFooFutureHelper1(fooId);
    }

    public FooData getFooFuture4(final String fooId) throws ExecutionException, InterruptedException {
        return getFooFutureHelper1(fooId).get();
    }

    private CompletableFuture<FooData> getFooFutureHelper1(String fooId) {
        try {
            FooData foo = fooService.getFooByIdNullable(fooId);
            return CompletableFuture.completedFuture(foo);
        } catch (final Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    public FooData getFooFuture5(final String fooId) throws ExecutionException, InterruptedException {
        return getFooFutureHelper2(fooId).get();
    }

    private CompletableFuture<FooData> getFooFutureHelper2(String fooId) {
        final CompletableFuture<FooData> ret = new CompletableFuture<>();
        try {
            FooData foo = fooService.getFooByIdNullable(fooId);
            ret.complete(foo);
            return ret;
        } catch (final Exception e) {
            ret.completeExceptionally(e);
            return ret;
        }
    }

    public void verifyAllData1(final String fooId) {
        final CompletableFuture<FooData> fooDataFuture = CompletableFuture.supplyAsync(() -> fooService.getFooById(fooId));
        final CompletableFuture<FooExtraData> fooExtra = fooDataFuture.thenApply(x -> fooExtraService.getExtraData(x.getExtraDataId()));
        final CompletableFuture<BarData> barData = fooDataFuture.thenApply(x -> barService.getBarForId(x.getBarId()));
        final CompletableFuture<OtherData> otherData = barData.thenApply(x -> otherDataService.getData(x.getOtherId()));
        try {
            CompletableFuture.allOf(fooDataFuture, fooExtra, barData, otherData).get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void verifyAllData2(final String fooId) {
        final CompletableFuture<FooData> fooDataFuture = CompletableFuture.supplyAsync(() -> fooService.getFooById(fooId));
        final CompletableFuture<FooExtraData> fooExtra = fooDataFuture.thenApply(x -> fooExtraService.getExtraData(x.getExtraDataId()));
        final CompletableFuture<BarData> barData = fooDataFuture.thenApply(x -> barService.getBarForId(x.getBarId()));
        final CompletableFuture<OtherData> otherData = barData.thenApply(x -> otherDataService.getData(x.getOtherId()));
        CompletableFuture<Void> allData = CompletableFuture.allOf(fooDataFuture, fooExtra, barData, otherData);
        try {
            allData.get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void verifyAllData3(final String fooId) {
        final CompletableFuture<FooData> fooDataFuture = CompletableFuture.supplyAsync(() -> fooService.getFooById(fooId));
        final CompletableFuture<FooExtraData> fooExtra = fooDataFuture.thenApply(x -> fooExtraService.getExtraData(x.getExtraDataId()));
        final CompletableFuture<BarData> barData = fooDataFuture.thenApply(x -> barService.getBarForId(x.getBarId()));
        final CompletableFuture<OtherData> otherData = barData.thenApply(x -> otherDataService.getData(x.getOtherId()));
        CompletableFuture<Void> allData = CompletableFuture.allOf(fooDataFuture, fooExtra, barData, otherData);
        try {
            allData.get(10, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            metricsService.recordTimeoutException(fooId);
            throw new RuntimeException(e);
        }
    }

    public void verifyAllData4(final String fooId) {
        final CompletableFuture<FooData> fooDataFuture = CompletableFuture.supplyAsync(() -> fooService.getFooById(fooId));
        final CompletableFuture<FooExtraData> fooExtra = fooDataFuture.thenApply(x -> fooExtraService.getExtraData(x.getExtraDataId()));
        final CompletableFuture<BarData> barData = fooDataFuture.thenApply(x -> barService.getBarForId(x.getBarId()));
        final CompletableFuture<OtherData> otherData = barData.thenApply(x -> otherDataService.getData(x.getOtherId()));
        CompletableFuture<Void> allData = CompletableFuture.allOf(fooDataFuture, fooExtra, barData, otherData);
        // TODO: Create test where valueIfAbsent is a helper-method-call with a metrics DI.
        allData.getNow(null);
    }

    public void verifyAllData5(final String fooId) {
        final CompletableFuture<FooData> fooDataFuture = CompletableFuture.supplyAsync(() -> fooService.getFooById(fooId));
        final CompletableFuture<FooExtraData> fooExtra = fooDataFuture.thenApply(x -> fooExtraService.getExtraData(x.getExtraDataId()));
        final CompletableFuture<BarData> barData = fooDataFuture.thenApply(x -> barService.getBarForId(x.getBarId()));
        final CompletableFuture<OtherData> otherData = barData.thenApply(x -> otherDataService.getData(x.getOtherId()));
        CompletableFuture<Void> allData = CompletableFuture.allOf(fooDataFuture, fooExtra, barData, otherData);
        allData.join();
    }
}
