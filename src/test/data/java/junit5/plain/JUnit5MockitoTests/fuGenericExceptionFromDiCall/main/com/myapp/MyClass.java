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

public class MyClass<T, E extends FooServiceException> {
    private final FooService<T, E> fooService;
    private final AltFooService<T, E> altFooService;
    private final MetricService metricService;

    public MyClass(final FooService<T, E> fooService,
                   final AltFooService<T, E> altFooService, final MetricService metricService) {
        this.fooService = fooService;
        this.altFooService = altFooService;
        this.metricService = metricService;
    }

    public T getFoo1(final String id) throws E {
        return fooService.getFoo1(id);
    }

    public T getFoo2(final String id) throws E {
        try {
            return fooService.getFoo1(id);
        } catch (final Exception e) {
            metricService.recordException(id, e);
            throw e;
        }
    }

    public T getFoo3(final String id) throws E {
        try {
            return fooService.getFoo1(id);
        } catch (final Exception e) {
            metricService.recordException(id, e);
            return null;
        }
    }

    public T getFoo4(final String id) throws E {
        return altFooService.getFoo1(id);
    }
}
