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

    public void putFoo1(final FooData fooData) {
        if(!fooExists1(fooData)) {
            fooService.putFoo1(fooData);
        }
    }

    public void putFoo2(final FooData fooData) {
        // Squaretest won't work correctly for this case. It will omit the fooService.getFoo1(..).
        // Squaretest assumes that Optional.map(..) will return some non-absent returnOutcome, as long as
        // the qualifier is not Absent.
        if(!fooExists2(fooData)) {
            fooService.putFoo1(fooData);
        }
    }

    public void putFoo3(final FooData fooData) {
        if(!fooExists3(fooData)) {
            fooService.putFoo1(fooData);
        }
    }

    public void putFoo4(final FooData fooData) {
        if(!fooExists4(fooData)) {
            fooService.putFoo1(fooData);
        }
    }

    public FooData putFoo5(final FooData fooData) {
        if(!fooExists1(fooData)) {
            return null;
        }
        return fooService.putFoo2(fooData);
    }

    public FooData putFoo6(final FooData fooData) {
        if(!fooExists1(fooData)) {
            throw new FooAlreadyExistsException(fooData.getId());
        }
        return fooService.putFoo2(fooData);
    }

    public void putFoo7(final FooData fooData) {
        final Optional<FooData> cachedFoo = fooService.getCachedData1(fooData);
        if(cachedFoo.isPresent()) {
            return;
        }
        if(!fooExists1(fooData)) {
            fooService.putFoo1(fooData);
        }
    }

    public FooData putFoo8(final FooData fooData) {
        final Optional<FooData> cachedFoo = fooService.getCachedData1(fooData);
        if(cachedFoo.isPresent()) {
            return cachedFoo.get();
        }
        if(!fooExists1(fooData)) {
            return null;
        }
        return fooService.putFoo2(fooData);
    }

    public FooData putFoo9(final FooData fooData) {
        final Optional<FooData> cachedFoo = fooService.getCachedData1(fooData);
        if(cachedFoo.isPresent()) {
            return cachedFoo.get();
        }
        if(!fooExists1(fooData)) {
            throw new FooAlreadyExistsException(fooData.getId());
        }
        return fooService.putFoo2(fooData);
    }

    public void putFoo10(final FooData fooData) {
        if(!fooExists1(fooData)) {
            return;
        }
        final Optional<FooData> cachedData = fooService.getCachedData1(fooData);
        if(cachedData.isPresent()) {
            return;
        }
        fooService.putFoo1(fooData);
    }

    public void putFoo11(final FooData fooData) {
        if(!fooExists1(fooData)) {
            throw new FooAlreadyExistsException(fooData.getId());
        }
        final Optional<FooData> cachedData = fooService.getCachedData1(fooData);
        if(cachedData.isPresent()) {
            throw new CachedFooAlreadyExists(fooData.getId());
        }
        fooService.putFoo1(fooData);
    }

    public void putFoo12(final FooData fooData) {
        if(!fooExists1(fooData)) {
            throw new FooAlreadyExistsException(fooData.getId());
        }
        if(!fooExists1a(fooData)) {
            throw new FooAlreadyExistsException(fooData.getId());
        }
        final Optional<FooData> cachedData = fooService.getCachedData1(fooData);
        if(cachedData.isPresent()) {
            throw new CachedFooAlreadyExists(fooData.getId());
        }
        fooService.putFoo1(fooData);
    }

    public void putFoo13(final FooData fooData) {
        if(!fooExists1(fooData)) {
            throw new FooAlreadyExistsException(fooData.getId());
        }
        if(!fooExists1(fooData)) {
            throw new FooAlreadyExistsException(fooData.getId());
        }
        final Optional<FooData> cachedData = fooService.getCachedData1(fooData);
        if(cachedData.isPresent()) {
            throw new CachedFooAlreadyExists(fooData.getId());
        }
        fooService.putFoo1(fooData);
    }

    public FooData putFoo14(final FooData fooData) {
        if(!fooExists1(fooData)) {
            return null;
        }
        final Optional<FooData> cachedData = fooService.getCachedData1(fooData);
        if(cachedData.isPresent()) {
            return null;
        }
        return fooService.putFoo2(fooData);
    }

    public FooData putFoo15(final FooData fooData) {
        if(!fooExists1(fooData)) {
            return null;
        }
        if(!fooExists1a(fooData)) {
            return null;
        }
        final Optional<FooData> cachedData = fooService.getCachedData1(fooData);
        if(cachedData.isPresent()) {
            return null;
        }
        return fooService.putFoo2(fooData);
    }

    public FooData putFoo16(final FooData fooData) {
        if(!fooExists1(fooData)) {
            return null;
        }
        if(!fooExists1(fooData)) {
            return null;
        }
        final Optional<FooData> cachedData = fooService.getCachedData1(fooData);
        if(cachedData.isPresent()) {
            return null;
        }
        return fooService.putFoo2(fooData);
    }

    private boolean fooExists1(final FooData fooData) {
        final FooData existingFoo = fooService.getFoo1(fooData.getId());
        return existingFoo.getSubData().getOtherData() != null;
    }
    private boolean fooExists1a(final FooData fooData) {
        final FooData existingFoo = fooService.getFoo2(fooData.getId());
        return existingFoo.getSubData().getOtherData() != null;
    }

    private boolean fooExists2(final FooData fooData) {
        final FooData existingFoo = fooService.getFoo1(fooData.getId());
        return Optional.of(existingFoo).map(FooData::getSubData).map(SubData::getOtherData).isPresent();
    }

    private boolean fooExists3(final FooData fooData) {
        final FooData existingFoo = fooService.getFoo1(fooData.getId());
        return Optional.ofNullable(existingFoo).map(FooData::getSubData).map(SubData::getOtherData).isPresent();
    }

    private boolean fooExists4(final FooData fooData) {
        final FooData existingFoo = fooService.getFoo1(fooData.getId());
        return Optional.ofNullable(existingFoo.getSubData()).map(SubData::getOtherData).isPresent();
    }

}
