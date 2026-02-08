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

public class MyClass {
    private final FooService fooService;

    public MyClass(FooService fooService) {
        this.fooService = fooService;
    }

    public FooData storeFoo1(final FooData fooData) {
        final Optional<FooData> foo = fooService.getFoo1(fooData.getId());
        foo.ifPresent(fooData1 -> {
            throw new FooAlreadyExistsException(fooData1.getId());
        });
        return fooService.saveFoo1(fooData);
    }

    public FooData storeFoo2(final FooData fooData) {
        final Optional<FooData> foo = fooService.getFoo1(fooData.getId());
        foo.ifPresent(fooData1 -> {
            throwFooAlreadyExistsException1(fooData.getId());
        });
        return fooService.saveFoo1(fooData);
    }

    public FooData storeFoo3(final FooData fooData) {
        final Optional<FooData> foo = fooService.getFoo1(fooData.getId());
        foo.ifPresent(fooData1 -> throwFooAlreadyExistsException1(fooData.getId()));
        return fooService.saveFoo1(fooData);
    }

    public FooData storeFoo4(final FooData fooData) {
        final Optional<FooData> foo = fooService.getFoo1(fooData.getId());
        foo.ifPresent(this::throwFooAlreadyExistsException2);
        return fooService.saveFoo1(fooData);
    }

    public void storeFoo5(final FooData fooData) {
        final Optional<FooData> foo = fooService.getFoo1(fooData.getId());
        foo.ifPresentOrElse(this::throwFooAlreadyExistsException2, () -> fooService.saveFoo1(fooData));
    }

    public void storeFoo6(final FooData fooData) {
        final Optional<FooData> foo = fooService.getFoo1(fooData.getId());
        foo.ifPresentOrElse(fooData1 -> throwFooAlreadyExistsException1(fooData.getId()), () -> fooService.saveFoo1(fooData));
    }

    public void storeFoo7(final FooData fooData) {
        final Optional<FooData> foo = fooService.getFoo1(fooData.getId());
        foo.ifPresentOrElse(fooData1 -> {
            throw new FooAlreadyExistsException(fooData1.getId());
        }, () -> fooService.saveFoo1(fooData));
    }

    public void updateFoo1(final FooData fooData) {
        final Optional<FooData> foo = fooService.getFoo1(fooData.getId());
        foo.ifPresentOrElse(fooData1 -> {
            fooService.updateFoo1(fooData);
        }, () -> {
            throw new FooNotFoundException(fooData.getId());
        });
    }

    public void updateFoo2(final FooData fooData) {
        final Optional<FooData> foo = fooService.getFoo1(fooData.getId());
        foo.ifPresentOrElse(fooData1 -> {
            fooService.updateFoo1(fooData);
        }, this::throwFooNotFoundException);
    }

    public void updateFoo3(final FooData fooData) {
        final Optional<FooData> foo = fooService.getFoo1(fooData.getId());
        foo.ifPresentOrElse(fooData1 -> {
            fooService.updateFoo1(fooData);
        }, () -> throwFooNotFoundException(fooData.getId()));
    }

    public void updateFoo4(final FooData fooData) {
        final Optional<FooData> foo = fooService.getFoo1(fooData.getId());
        foo.ifPresentOrElse(fooData1 -> {
            fooService.updateFoo1(fooData);
        }, () -> {
            throwFooNotFoundException(fooData.getId());
        });
    }


    private void throwFooNotFoundException() {
        throw new FooNotFoundException();
    }

    private void throwFooNotFoundException(String id) {
        throw new FooNotFoundException(id);
    }

    private void throwFooAlreadyExistsException3() {
        throw new FooAlreadyExistsException();
    }

    private void throwFooAlreadyExistsException1(final String id) {
        throw new FooAlreadyExistsException(id);
    }

    private void throwFooAlreadyExistsException2(final FooData fooData) {
        throw new FooAlreadyExistsException(fooData.getId());
    }
}
