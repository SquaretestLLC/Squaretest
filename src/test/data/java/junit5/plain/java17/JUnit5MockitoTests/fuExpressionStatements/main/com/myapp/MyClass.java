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

import java.util.Optional;
import java.util.function.Supplier;

public class MyClass {
    private final FooService fooService;
    private final MetricService metricService;

    public MyClass(final FooService fooService, final MetricService metricService) {
        this.fooService = fooService;
        this.metricService = metricService;
    }

    public String getFoos1(final String id) {
        final Optional<Integer> startingValue = fooService.getStartingValue2(id);
        startingValue.orElseThrow();
        return startingValue.get().toString();
    }
    public String getFoos2(final String id) {
        final Optional<Integer> startingValue = fooService.getStartingValue2(id);
        startingValue.orElseThrow();
        fooService.getNextValue2(id).orElseThrow();
        return startingValue.get().toString();
    }
    public void validateFoo1(final String id) {
        fooService.getNextValue2(id).orElseThrow();
    }

    public void validateFoo2(final String id) throws FooServiceException {
        if(id.startsWith("FU")) {
            new Supplier<String>() {
                @Override
                public String get() {
                    final Optional<Integer> startingValue = fooService.getStartingValue2(id);
                    startingValue.orElseThrow();
                    return startingValue.get().toString();
                }
            };
            metricService.recordAfterSupplier(id);
        } else {
            metricService.recordStartOfElseBlock(id);
            fooService.getStartingValue4(id).orElseThrow();
            metricService.recordEndOfElseBlock(id);
        }
        metricService.recordEndOfMethod(id);
    }
}
