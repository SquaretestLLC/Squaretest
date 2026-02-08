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

public class MyClass {
    private final FooService fooService;
    private final MetricService metricService;

    public MyClass(FooService fooService, MetricService metricService) {
        this.fooService = fooService;
        this.metricService = metricService;
    }

    public FooData getFooData1(final String id) {
        return fooService.getFooData1(id).orElseThrow(() -> {
            return new FooServiceException(id);
        });
    }

    public FooData getFooData2(final String id) {
        return fooService.getFooData1(id).orElseThrow(() -> {
            metricService.recordOrderNotFound(id);
            return new FooServiceException(id);
        });
    }
}
