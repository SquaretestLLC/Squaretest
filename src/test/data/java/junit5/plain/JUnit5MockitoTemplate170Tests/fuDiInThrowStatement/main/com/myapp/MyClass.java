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

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    public String getFoo1(final String id) throws FooServiceException {
        final String mainFoo = fooService.getFooAsString1(id);
        if(mainFoo == null) {
            throw fooService.createException1(id);
        }
        return mainFoo;
    }
    public String getFoo2(final String id) throws FooServiceException {
        final String mainFoo = fooService.getFooAsString1(id);
        if(mainFoo == null) {
            throw new FooServiceException(fooService.getExceptionMessage1(id));
        }
        return mainFoo;
    }
    public String getFoo3(final String id) throws FooServiceException {
        final String mainFoo = Optional.ofNullable(fooService.getFooAsString1(id)).orElseThrow(() -> fooService.createException1(id));
        return mainFoo;
    }
    public String getFoo4(final String id) throws FooServiceException {
        final String mainFoo = Optional.ofNullable(fooService.getFooAsString1(id)).orElseThrow(() -> fooService.createException2());
        return mainFoo;
    }
    public String getFoo5(final String id) throws FooServiceException {
        final String mainFoo = Optional.ofNullable(fooService.getFooAsString1(id)).orElseThrow(fooService::createException2);
        return mainFoo;
    }
    public String getFoo6(final String id) throws FooServiceException {
        final String mainFoo = Optional.ofNullable(fooService.getFooAsString1(id)).orElseThrow(() -> new FooServiceException(fooService.getExceptionMessage1(id)));
        return mainFoo;
    }
    public String getFoo7(final String id) throws FooServiceException {
        final String mainFoo = fooService.getFooAsString1(id);
        if(mainFoo == null) {
            throw new FooServiceException(Optional.ofNullable(fooService.getExceptionMessage1(id)).orElseThrow());
        }
        return mainFoo;
    }
    public String getFoo8(final String id) throws FooServiceRuntimeException {
        final String mainFoo = Optional.ofNullable(fooService.getFooAsString1(id)).orElseThrow((Supplier<FooServiceRuntimeException>) () -> {
            throw new FooServiceRuntimeException(fooService.getExceptionMessage1(id));
        });
        return mainFoo;
    }
    public String getFoo9(final String id) throws FooServiceRuntimeException {
        final String mainFoo = Optional.ofNullable(fooService.getFooAsString1(id)).orElseGet(() -> {
            throw new FooServiceRuntimeException(fooService.getExceptionMessage1(id));
        });
        return mainFoo;
    }
    public String getFoo10(final String id) throws FooServiceException {
        final String mainFoo = Optional.ofNullable(fooService.getFooAsString1(id)).orElseThrow(new Supplier<FooServiceException>() {
            @Override
            public FooServiceException get() {
                return fooService.createException3(id).orElseThrow();
            }
        });
        return mainFoo;
    }
    public String getFoo11(final String id) throws FooServiceException {
        final String mainFoo = Optional.ofNullable(fooService.getFooAsString1(id)).orElseThrow(() -> fooService.createException3(id).orElseThrow());
        return mainFoo;
    }
}
