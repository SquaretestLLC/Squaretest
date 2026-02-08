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
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class MyClass {
    private final ExecutorService executorService;
    private final FooService fooService;
    private final BarService barService;
    private final OtherService otherDataService;
    private final MetricsService metricsService;

    public MyClass(ExecutorService executorService, FooService fooService, BarService barService, OtherService otherDataService, MetricsService metricsService) {
        this.executorService = executorService;
        this.fooService = fooService;
        this.barService = barService;
        this.otherDataService = otherDataService;
        this.metricsService = metricsService;
    }

    public FooAndBarData getFooAndBar1(final String fooId) {
        final CompletableFuture<Optional<FooData>> fooDataFuture = fooService.getFooByIdAsync2(fooId);
        final CompletableFuture<FooAndBarData> barFuture = fooDataFuture.thenApplyAsync(x -> x.map(fooData -> new FooAndBarData(fooData, barService.getBarForId1(fooData.getBarId()))).orElse(null), executorService);
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

    public List<FooAndBarData> getFooAndBar2(final String fooId) {
        final CompletableFuture<List<FooData>> fooDataFuture = fooService.getFooByIdAsync3(fooId);
        final CompletableFuture<List<FooAndBarData>> barFuture = fooDataFuture.thenApplyAsync(x -> x.stream().map(fooData -> new FooAndBarData(fooData, barService.getBarForId1(fooData.getBarId()))).collect(Collectors.toList()), executorService);
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

    public List<FooAndBarData> getFooAndBar3(final String fooId) {
        final CompletableFuture<List<FooData>> fooDataFuture = fooService.getFooByIdAsync3(fooId);
        final CompletableFuture<List<FooAndBarData>> barFuture = fooDataFuture.thenApplyAsync(x -> x.stream().map(fooData -> {
            BarData barData = barService.getBarForId2(fooData.getBarId());
            if(barData == null) {
                throw new NoSuchElementException("BarData not found with id=" + fooData.getBarId());
            }
            return new FooAndBarData(fooData, barData);
        }).collect(Collectors.toList()), executorService);
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

    public Optional<List<FooAndBarData>> getFooAndBar4(final String fooId) {
        final CompletableFuture<Optional<List<FooData>>> fooDataFuture = fooService.getFooByIdAsync4(fooId);
        final CompletableFuture<Optional<List<FooAndBarData>>> barFuture = fooDataFuture.thenApplyAsync(x -> {
            if(x.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(x.get().stream().map(fooData -> {
                BarData barData = barService.getBarForId2(fooData.getBarId());
                if(barData == null) {
                    throw new NoSuchElementException("BarData not found with id=" + fooData.getBarId());
                }
                return new FooAndBarData(fooData, barData);
            }).collect(Collectors.toList()));
        }, executorService);
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

    public Optional<List<FooAndBarData>> getFooAndBar5(final String fooId) {
        final CompletableFuture<Optional<List<FooAndBarData>>> barFuture = fooService.getFooByIdAsync4(fooId).thenApplyAsync(x -> {
            if(x.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(x.get().stream().map(fooData -> {
                BarData barData = barService.getBarForId2(fooData.getBarId());
                if(barData == null) {
                    throw new NoSuchElementException("BarData not found with id=" + fooData.getBarId());
                }
                return new FooAndBarData(fooData, barData);
            }).collect(Collectors.toList()));
        }, executorService);
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

    public BarData getFooAndBar6(final String fooId) {
        try {
            return fooService.getFooByIdAsync1(fooId)
                    .thenCompose(fooData -> barService.getBarForIdAsync(fooData.getBarId()))
                    .get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public OtherData getFooAndBar7(final String fooId) {
        try {
            return fooService.getFooByIdAsync1(fooId)
                    .thenCompose(fooData -> barService.getBarForIdAsync(fooData.getBarId()))
                    .thenCompose(barData -> otherDataService.getDataAsync(barData.getOtherId())).get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<OtherData> getFooAndBar8(final String fooId) {
        return fooService.getFooByIdAsync1(fooId)
                .thenCompose(fooData -> barService.getBarForIdAsync(fooData.getBarId()))
                .thenCompose(barData -> otherDataService.getDataAsync(barData.getOtherId()));
    }

    public OtherData getFooAndBar9(final String fooId) {
        return fooService.getFooById2(fooId)
                .flatMap(x -> barService.getBarForId3(x.getBarId()))
                .flatMap(x -> otherDataService.getData2(x.getOtherId())).orElse(null);
    }

    public OtherData getFooAndBar10(final String fooId) {
        return Optional.ofNullable(fooService.getFooByIdNullable(fooId))
                .flatMap(x -> Optional.ofNullable(barService.getBarForId2(x.getBarId())))
                .flatMap(x -> Optional.ofNullable(otherDataService.getData3(x.getOtherId())))
                .orElse(null);
    }

    public OtherData getFooAndBar11(final String fooId) {
        try {
            return fooService.getFooByIdAsync1(fooId)
                    .thenComposeAsync(fooData -> barService.getBarForIdAsync(fooData.getBarId()), executorService)
                    .thenComposeAsync(barData -> otherDataService.getDataAsync(barData.getOtherId()), executorService).get();
        } catch (InterruptedException e) {
            metricsService.recordInterruptedException(fooId);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            metricsService.recordExecutionException(fooId);
            throw new RuntimeException(e);
        }
    }
}
