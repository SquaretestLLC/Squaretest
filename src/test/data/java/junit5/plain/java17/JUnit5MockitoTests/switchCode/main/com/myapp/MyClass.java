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
import java.util.Optional;

public class MyClass {
    private final FooService fooService;
    private final OtherService otherService;

    public MyClass(final FooService fooService, final OtherService otherService) {
        this.fooService = fooService;
        this.otherService = otherService;
    }
    public FooData getFooData1(final String id, final FooType fooType) {
        final Optional<FooData> fooDataOpt = switch(fooType) {
            case Type1 -> fooService.getFooData1(id);
            case Type2 -> fooService.getFooData2(id);
            case Type3 -> fooService.getFooData3(id);
        };
        if(fooDataOpt.isPresent()) {
            final FooData fooData = fooDataOpt.get();
            final Optional<byte[]> otherData = otherService.getOtherData2(fooData.getOtherId());
            if(otherData.isPresent()) {
                fooData.setOtherData(otherData.get());
            }
            return fooData;
        }
        return null;
    }
    public FooData getFooData2(final String id, final FooType fooType) {
        final Optional<FooData> fooDataOpt = switch(fooType) {
            case Type1 -> {
                yield fooService.getFooData1(id);
            }
            case Type2 -> {
                yield fooService.getFooData2(id);
            }
            case Type3 -> {
                yield fooService.getFooData3(id);
            }
        };
        if(fooDataOpt.isPresent()) {
            final FooData fooData = fooDataOpt.get();
            final Optional<byte[]> otherData = otherService.getOtherData1(fooData.getOtherId());
            if(otherData.isPresent()) {
                fooData.setOtherData(otherData.get());
            }
            return fooData;
        }
        return null;
    }
    public FooData getFooData3(final String id, final FooType fooType) {
        final Optional<FooData> fooDataOpt = switch(fooType) {
            case Type1:
                yield fooService.getFooData1(id);
            case Type2:
                yield fooService.getFooData2(id);
            case Type3:
                yield fooService.getFooData3(id);

        };
        if(fooDataOpt.isPresent()) {
            final FooData fooData = fooDataOpt.get();
            final Optional<byte[]> otherData = otherService.getOtherData1(fooData.getOtherId());
            if(otherData.isPresent()) {
                fooData.setOtherData(otherData.get());
            }
            return fooData;
        }
        return null;
    }
    public FooData getFooData4(final String id) {
        final Optional<FooData> fooDataOpt = switch(fooService.getFooType1(id)) {
            case Type1 -> fooService.getFooData1(id);
            case Type2 -> fooService.getFooData2(id);
            case Type3 -> fooService.getFooData3(id);
        };
        if(fooDataOpt.isPresent()) {
            final FooData fooData = fooDataOpt.get();
            final Optional<byte[]> otherData = otherService.getOtherData2(fooData.getOtherId());
            if(otherData.isPresent()) {
                fooData.setOtherData(otherData.get());
            }
            return fooData;
        }
        return null;
    }
    public FooData getFooData5(final String id) {
        final Optional<FooData> fooDataOpt = switch(fooService.getFooType1(id)) {
            case Type1 -> {
                yield fooService.getFooData1(id);
            }
            case Type2 -> {
                yield fooService.getFooData2(id);
            }
            case Type3 -> {
                yield fooService.getFooData3(id);
            }
        };
        if(fooDataOpt.isPresent()) {
            final FooData fooData = fooDataOpt.get();
            final Optional<byte[]> otherData = otherService.getOtherData1(fooData.getOtherId());
            if(otherData.isPresent()) {
                fooData.setOtherData(otherData.get());
            }
            return fooData;
        }
        return null;
    }
    public FooData getFooData6(final String id) {
        final Optional<FooData> fooDataOpt = switch(fooService.getFooType1(id)) {
            case Type1:
                yield fooService.getFooData1(id);
            case Type2:
                yield fooService.getFooData2(id);
            case Type3:
                yield fooService.getFooData3(id);
        };
        if(fooDataOpt.isPresent()) {
            final FooData fooData = fooDataOpt.get();
            final Optional<byte[]> otherData = otherService.getOtherData1(fooData.getOtherId());
            if(otherData.isPresent()) {
                fooData.setOtherData(otherData.get());
            }
            return fooData;
        }
        return null;
    }
    public FooData getFooData7(final String id) {
        final Optional<FooData> fooDataOpt = switch(fooService.getFooType2(id)) {
            case Type1 -> fooService.getFooData1(id);
            case Type2 -> fooService.getFooData2(id);
            case Type3 -> fooService.getFooData3(id);
        };
        if(fooDataOpt.isPresent()) {
            final FooData fooData = fooDataOpt.get();
            final Optional<byte[]> otherData = otherService.getOtherData2(fooData.getOtherId());
            if(otherData.isPresent()) {
                fooData.setOtherData(otherData.get());
            }
            return fooData;
        }
        return null;
    }
    public FooData getFooData8(final String id) {
        final FooType fooType = fooService.getFooType1(id);
        final Optional<FooData> fooDataOpt;
        switch(fooType) {
            case Type1 -> fooDataOpt = fooService.getFooData1(id);
            case Type2 -> fooDataOpt = fooService.getFooData2(id);
            case Type3 -> fooDataOpt = fooService.getFooData3(id);
            default -> fooDataOpt = Optional.empty();
        }
        if(fooDataOpt.isPresent()) {
            final FooData fooData = fooDataOpt.get();
            final Optional<byte[]> otherData = otherService.getOtherData2(fooData.getOtherId());
            if(otherData.isPresent()) {
                fooData.setOtherData(otherData.get());
            }
            return fooData;
        }
        return null;
    }
    public FooData getFooData9(final String id) {
        final FooType fooType = fooService.getFooType1(id);
        final Optional<FooData> fooDataOpt;
        switch(fooType) {
            case Type1 -> {
                fooDataOpt = fooService.getFooData1(id);
            }
            case Type2 -> {
                fooDataOpt = fooService.getFooData2(id);
            }
            case Type3 -> {
                fooDataOpt = fooService.getFooData3(id);
            }
            default -> {
                fooDataOpt = Optional.empty();
            }
        }
        if(fooDataOpt.isPresent()) {
            final FooData fooData = fooDataOpt.get();
            final Optional<byte[]> otherData = otherService.getOtherData2(fooData.getOtherId());
            if(otherData.isPresent()) {
                fooData.setOtherData(otherData.get());
            }
            return fooData;
        }
        return null;
    }
    public FooData getFooData10(final String id) {
        final FooType fooType = fooService.getFooType1(id);
        final Optional<FooData> fooDataOpt;
        switch(fooType) {
            case Type1: fooDataOpt = fooService.getFooData1(id); break;
            case Type2: fooDataOpt = fooService.getFooData2(id); break;
            case Type3: fooDataOpt = fooService.getFooData3(id); break;
            default: fooDataOpt = Optional.empty(); break;
        }
        if(fooDataOpt.isPresent()) {
            final FooData fooData = fooDataOpt.get();
            final Optional<byte[]> otherData = otherService.getOtherData2(fooData.getOtherId());
            if(otherData.isPresent()) {
                fooData.setOtherData(otherData.get());
            }
            return fooData;
        }
        return null;
    }
    public FooData getFooData11(final String id) {
        final Optional<FooData> fooDataOpt;
        switch(fooService.getFooType1(id)) {
            case Type1: fooDataOpt = fooService.getFooData1(id); break;
            case Type2: fooDataOpt = fooService.getFooData2(id); break;
            case Type3: fooDataOpt = fooService.getFooData3(id); break;
            default: fooDataOpt = Optional.empty(); break;
        }
        if(fooDataOpt.isPresent()) {
            final FooData fooData = fooDataOpt.get();
            final Optional<byte[]> otherData = otherService.getOtherData2(fooData.getOtherId());
            if(otherData.isPresent()) {
                fooData.setOtherData(otherData.get());
            }
            return fooData;
        }
        return null;
    }
    public FooData getFooData12(final String id) {
        final Optional<FooData> fooDataOpt;
        switch(fooService.getFooType2(id)) {
            case Type1: fooDataOpt = fooService.getFooData1(id); break;
            case Type2: fooDataOpt = fooService.getFooData2(id); break;
            case Type3: fooDataOpt = fooService.getFooData3(id); break;
            default: fooDataOpt = Optional.empty(); break;
        }
        if(fooDataOpt.isPresent()) {
            final FooData fooData = fooDataOpt.get();
            final Optional<byte[]> otherData = otherService.getOtherData2(fooData.getOtherId());
            if(otherData.isPresent()) {
                fooData.setOtherData(otherData.get());
            }
            return fooData;
        }
        return null;
    }
    public FooData getFooData13(final String id) {
        final Optional<FooData> fooDataOpt;
        switch(Objects.requireNonNull(fooService.getFooType2(id))) {
            case Type1: fooDataOpt = fooService.getFooData1(id); break;
            case Type2: fooDataOpt = fooService.getFooData2(id); break;
            case Type3: fooDataOpt = fooService.getFooData3(id); break;
            default: fooDataOpt = Optional.empty(); break;
        }
        if(fooDataOpt.isPresent()) {
            final FooData fooData = fooDataOpt.get();
            final Optional<byte[]> otherData = otherService.getOtherData2(fooData.getOtherId());
            if(otherData.isPresent()) {
                fooData.setOtherData(otherData.get());
            }
            return fooData;
        }
        return null;
    }
}
