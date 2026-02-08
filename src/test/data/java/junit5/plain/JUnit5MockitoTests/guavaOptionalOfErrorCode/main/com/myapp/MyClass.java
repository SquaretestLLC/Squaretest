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

import com.google.common.base.Optional;

public class MyClass {
    private final FooService fooService;

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    public void putFoo1(final FooData fooData) {
        final Optional<ErrorCode> errorCode = fooService.putFoo1(fooData);
        if(errorCode.isPresent()) {
            throw new FooStoreException(errorCode.toString());
        }
    }

    public FooData putFoo2(final FooData fooData) {
        final Optional<ErrorCode> errorCode = fooService.putFoo1(fooData);
        if(errorCode.isPresent()) {
            throw new FooStoreException(errorCode.toString());
        }
        return fooService.getFoo(fooData.getId());
    }

    public FooData putFoo3(final FooData fooData) {
        final Optional<ErrorCode> errorCode = fooService.putFoo1(fooData);
        if(errorCode.isPresent()) {
            return fooService.getFoo(fooData.getId());
        }
        // This makes no sense, but is needed to test the pathfinding.
        throw new FooStoreException(fooData.getId());
    }

    public void putFoo4(final FooData fooData) {
        // Ignore the return value.
        fooService.putFoo1(fooData);
    }

    public FooData putFoo5(final FooData fooData) {
        // Ignore the return value.
        fooService.putFoo1(fooData);
        return fooService.getFoo(fooData.getId());
    }

    public void putFoo6(final FooData fooData) {
        final Optional<ErrorInfo> errorInfo = fooService.putFoo2(fooData);
        if(errorInfo.isPresent()) {
            throw new FooStoreException(errorInfo.toString());
        }
    }

    public FooData putFoo7(final FooData fooData) {
        final Optional<ErrorInfo> errorCode = fooService.putFoo2(fooData);
        if(errorCode.isPresent()) {
            throw new FooStoreException(errorCode.get().getCode() + " " + errorCode.get().getDescription());
        }
        return fooService.getFoo(fooData.getId());
    }

    public FooData putFoo8(final FooData fooData) {
        final Optional<ErrorInfo> errorCode = fooService.putFoo2(fooData);
        if(errorCode.isPresent()) {
            return fooService.getFoo(fooData.getId());
        }
        // This makes no sense, but is needed to test the pathfinding.
        throw new FooStoreException(fooData.getId());
    }

    public void putFoo9(final FooData fooData) {
        // Ignore the return value.
        fooService.putFoo2(fooData);
    }

    public FooData putFoo10(final FooData fooData) {
        // Ignore the return value.
        fooService.putFoo2(fooData);
        return fooService.getFoo(fooData.getId());
    }

    public void putFoo11(final FooData fooData) {
        final Optional<Error> errorInfo = fooService.putFoo3(fooData);
        if(errorInfo.isPresent()) {
            throw new FooStoreException(errorInfo.toString());
        }
    }

    public FooData putFoo12(final FooData fooData) {
        final Optional<Error> errorCode = fooService.putFoo3(fooData);
        if(errorCode.isPresent()) {
            throw new FooStoreException(errorCode.toString());
        }
        return fooService.getFoo(fooData.getId());
    }

    public FooData putFoo13(final FooData fooData) {
        final Optional<Error> errorCode = fooService.putFoo3(fooData);
        if(errorCode.isPresent()) {
            return fooService.getFoo(fooData.getId());
        }
        // This makes no sense, but is needed to test the pathfinding.
        throw new FooStoreException(fooData.getId());
    }

    public void putFoo14(final FooData fooData) {
        // Ignore the return value.
        fooService.putFoo3(fooData);
    }

    public FooData putFoo15(final FooData fooData) {
        // Ignore the return value.
        fooService.putFoo3(fooData);
        return fooService.getFoo(fooData.getId());
    }

    public void putFoo16(final FooData fooData) {
        final Optional<FailureInfo> errorInfo = fooService.putFoo4(fooData);
        if(errorInfo.isPresent()) {
            throw new FooStoreException(errorInfo.toString());
        }
    }

    public FooData putFoo17(final FooData fooData) {
        final Optional<FailureInfo> errorCode = fooService.putFoo4(fooData);
        if(errorCode.isPresent()) {
            throw new FooStoreException(errorCode.get().getErrorInfos().toString());
        }
        return fooService.getFoo(fooData.getId());
    }

    public FooData putFoo18(final FooData fooData) {
        final Optional<FailureInfo> errorCode = fooService.putFoo4(fooData);
        if(errorCode.isPresent()) {
            return fooService.getFoo(fooData.getId());
        }
        // This makes no sense, but is needed to test the pathfinding.
        throw new FooStoreException(fooData.getId());
    }

    public void putFoo19(final FooData fooData) {
        // Ignore the return value.
        fooService.putFoo4(fooData);
    }

    public FooData putFoo20(final FooData fooData) {
        // Ignore the return value.
        fooService.putFoo4(fooData);
        return fooService.getFoo(fooData.getId());
    }
    public void putFoo21(final FooData fooData) {
        final Optional<FooServiceException> errorInfo = fooService.putFoo5(fooData);
        if(errorInfo.isPresent()) {
            throw new FooStoreException(errorInfo.toString());
        }
    }

    public FooData putFoo22(final FooData fooData) {
        final Optional<FooServiceException> errorCode = fooService.putFoo5(fooData);
        if(errorCode.isPresent()) {
            throw new FooStoreException(errorCode.get().toString());
        }
        return fooService.getFoo(fooData.getId());
    }

    public FooData putFoo23(final FooData fooData) {
        final Optional<FooServiceException> errorCode = fooService.putFoo5(fooData);
        if(errorCode.isPresent()) {
            return fooService.getFoo(fooData.getId());
        }
        // This makes no sense, but is needed to test the pathfinding.
        throw new FooStoreException(fooData.getId());
    }

    public void putFoo24(final FooData fooData) {
        // Ignore the return value.
        fooService.putFoo5(fooData);
    }

    public FooData putFoo25(final FooData fooData) {
        // Ignore the return value.
        fooService.putFoo5(fooData);
        return fooService.getFoo(fooData.getId());
    }
    public void putFoo26(final FooData fooData) {
        final Optional<Throwable> errorInfo = fooService.putFoo6(fooData);
        if(errorInfo.isPresent()) {
            throw new FooStoreException(errorInfo.toString());
        }
    }
}
