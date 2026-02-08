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

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * This tests cases where the Squaretest analysis code determines the source methods return empty/absent values,
 * but Squaretest cannot find empty/absent initExpressions for the return types. We need to ensure the expected
 * return value is "null" instead of "$method.returnType.emptyInitExpression".
 */
public class MyClass {
    private final FooService fooService;
    private final FooSerializer fooSerializer;

    public MyClass(final FooService fooService, final FooSerializer fooSerializer) {
        this.fooService = fooService;
        this.fooSerializer = fooSerializer;
    }

    public Optional<Boolean> exists(final String searchCriteria) {
        try {
            return Optional.of(fooService.getFoos1(searchCriteria).isEmpty());
        } catch (FooServiceException e) {
            return Optional.empty();
        }
    }

    public MyList<FooData> getFoos1(final String searchCriteria) {
        try {
            return new MyArrayList<>(fooService.getFoos1(searchCriteria));
        } catch (FooServiceException e) {
            return new MyArrayList<>();
        }
    }

    public MyList<FooData> getFoos2(final String searchCriteria) {
        return new MyArrayList<>();
    }

    public MyList<FooData> getFoos3(final String fooId) {
        final Optional<FooData> theFoo = fooService.getFoo1(fooId);
        if (theFoo.isPresent()) {
            final MyArrayList<FooData> ret = new MyArrayList<>();
            ret.add(theFoo.get());
            return ret;
        }
        return new MyArrayList<>();
    }

    public MyList<FooData> getFoos4(final String searchCriteria) {
        try (InputStream fooStream = fooService.getFoos2(searchCriteria)) {
            final MyList<FooData> fooData = fooSerializer.readFoos(fooStream);
            return fooData;
        } catch (IOException e) {
            return new MyArrayList<>();
        }
    }
    public MyList<FooData> getFoos5(final String searchCriteria) {
        try (InputStream fooStream = fooService.getFoos2(searchCriteria)) {
            return new MyArrayList<>();
        } catch (IOException e) {
            return new MyArrayList<>();
        }
    }
    public MyList<FooData> getFoos6(final String searchCriteria) {
        try {
            return new MyArrayList<>(fooService.getFoosAsync1(searchCriteria).get());
        } catch (ExecutionException | InterruptedException e) {
            return new MyArrayList<>();
        }
    }
}
