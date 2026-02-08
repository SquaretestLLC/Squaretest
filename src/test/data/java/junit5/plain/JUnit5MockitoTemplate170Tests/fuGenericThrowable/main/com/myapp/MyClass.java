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

public class MyClass<T, E extends Throwable> {
    private final FooService<T> fooService;
    private final ExceptionService<E> exceptionService;
    private final MetricService metricService;

    public MyClass(final FooService<T> fooService, final ExceptionService<E> exceptionService,
                   final MetricService metricService) {
        this.fooService = fooService;
        this.exceptionService = exceptionService;
        this.metricService = metricService;
    }

    public T getFoo1(final String id) throws E {
        final T ret = fooService.getFoo1(id);
        if(ret == null) {
            throw exceptionService.createException1(id);
        }
        return ret;
    }

    public T safeGetFoo1(final String id) {
        try {
            final T ret = getFooHelper1(id);
            return ret;
        } catch(final Throwable e) {
            metricService.recordException(id, e);
            return null;
        }
    }

    public void storeFoo1(final String id, final T theFoo) throws FooAlreadyExistsException {
        final T existingFoo = fooService.getFoo1(id);
        if(existingFoo != null) {
            throw new FooAlreadyExistsException(id);
        }
        fooService.storeFoo1(theFoo);
    }

    private T getFooHelper1(final String id) throws E {
        final T ret = fooService.getFoo1(id);
        if(ret == null) {
            throw exceptionService.createException1(id);
        }
        return ret;
    }

    public void throwThEx1(final String id) throws E {
        throw exceptionService.createException1(id);
    }

    public void doSomething1(final String id) throws E {
        throw exceptionService.createException1(id);
    }

    public void doSomething2(final String id) throws E {
        final E ex = exceptionService.createException1(id);
        metricService.recordException(id, ex);
        throw ex;
    }
}
