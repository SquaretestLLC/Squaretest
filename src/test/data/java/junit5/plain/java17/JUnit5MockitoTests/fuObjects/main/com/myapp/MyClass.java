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

import java.util.Objects;

public class MyClass {
    private final FooService fooService;
    private final MetricService metricService;

    public MyClass(final FooService fooService, final MetricService metricService) {
        this.fooService = fooService;
        this.metricService = metricService;
    }

    public String getFoo1(final String id) {
        return fooService.getFooAsString1(id);
    }
    public String getFoo2(final String id) {
        return Objects.requireNonNull(fooService.getFooAsString1(id), "foo string is null");
    }
    public String getFoo3(final String id) {
        return Objects.requireNonNullElse(fooService.getFooAsString1(id), "defaultFoo");
    }
    public String getFoo4(final String id) {
        return Objects.requireNonNullElse(fooService.getFooAsString1(id), fooService.getFooAsString1(id));
    }
    public String getFoo5(final String id) {
        return Objects.requireNonNullElse(fooService.getFooAsString1(id), fooService.getFooAsString2(id));
    }
    public String getFoo6(final String id) {
        return Objects.requireNonNullElseGet(fooService.getFooAsString1(id), () -> fooService.getFooAsString2(id));
    }
    public String getFoo7(final String id) {
        return Objects.requireNonNull(fooService.getFooAsString1(id), logAndGetMessage(id));
    }
    public String getFoo77(final String id) {
        return Objects.requireNonNull(fooService.getFooAsString1(id), () -> logAndGetMessage(id));
    }
    public String getFoo8(final String id) {
        final String foo = fooService.getFooAsString1(id);
        if(Objects.isNull(foo)) {
            throw new RuntimeException("Foo is null id=" + id);
        }
        return foo;
    }
    public String getFoo9(final String id) {
        final String foo = fooService.getFooAsString1(id);
        if (Objects.nonNull(foo)) {
            return foo;
        }
        throw new RuntimeException("Foo is null id=" + id);
    }
    private String logAndGetMessage(final String id) {
        metricService.recordNullFoo(id);
        return "foo was null " + id;
    }
}
