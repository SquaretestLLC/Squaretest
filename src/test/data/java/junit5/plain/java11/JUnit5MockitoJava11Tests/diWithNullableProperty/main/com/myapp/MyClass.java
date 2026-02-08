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
    private final BigDataService bigDataService;

    public MyClass(FooService fooService, final BigDataService bigDataService) {
        this.fooService = fooService;
        this.bigDataService = bigDataService;
    }

    public FooData getFooWithId1(final String id) throws FooServiceException, BigDataServiceException {
        final FooData fooData = fooService.getFooData1(id);
        if(fooData.getBigData() == null) {
            fooData.setBigData(bigDataService.getBigData1(fooData.getId()));
        }
        return fooData;
    }

    public FooData getFooWithId2(final String id) throws FooServiceException, BigDataServiceException {
        final FooData fooData = fooService.getFooData1(id);
        if(extractBigData1(fooData) == null) {
            fooData.setBigData(bigDataService.getBigData1(fooData.getId()));
        }
        return fooData;
    }

    public FooData getFooWithId3(final String id) throws FooServiceException, BigDataServiceException {
        final FooData fooData = fooService.getFooData1(id);
        if(extractBigData2(fooData).isPresent()) {
            fooData.setBigData(bigDataService.getBigData1(fooData.getId()));
        }
        return fooData;
    }

    public FooData getFooWithId4(final String id) throws FooServiceException, BigDataServiceException {
        final FooData fooData = fooService.getFooData1(id);
        if(!extractBigData2(fooData).isEmpty()) {
            fooData.setBigData(bigDataService.getBigData1(fooData.getId()));
        }
        return fooData;
    }

    public FooData getFooWithId5(final String id) throws FooServiceException, BigDataServiceException {
        final FooData fooData = fooService.getFooData1(id);
        if(Objects.isNull(extractBigData1(fooData))) {
            fooData.setBigData(bigDataService.getBigData1(fooData.getId()));
        }
        return fooData;
    }

    public FooData getFooWithId6(final String id) throws FooServiceException, BigDataServiceException {
        final FooData fooData = fooService.getFooData1(id);
        if(!Objects.nonNull(extractBigData1(fooData))) {
            fooData.setBigData(bigDataService.getBigData1(fooData.getId()));
        }
        return fooData;
    }

    public FooData getFooWithId7(final String id) throws FooServiceException, BigDataServiceException {
        final FooData fooData = fooService.getFooData1(id);
        assert extractBigData1(fooData) == null;
        fooData.setBigData(bigDataService.getBigData1(fooData.getId()));
        return fooData;
    }

    public FooData getFooWithId8(final String id) throws FooServiceException, BigDataServiceException {
        final FooData fooData = fooService.getFooData1(id);
        assert extractBigData1(fooData) != null;
        fooData.setBigData(bigDataService.getBigData1(fooData.getId()));
        return fooData;
    }

    public FooData getFooWithId9(final String id) throws FooServiceException, BigDataServiceException {
        final FooData fooData = fooService.getFooData1(id);
        assert fooData.getBigData() == null;
        fooData.setBigData(bigDataService.getBigData1(fooData.getId()));
        return fooData;
    }

    public FooData getFooWithId10(final String id) throws FooServiceException, BigDataServiceException {
        final FooData fooData = fooService.getFooData1(id);
        assert fooData.getBigData() != null;
        fooData.setBigData(bigDataService.getBigData1(fooData.getId()));
        return fooData;
    }

    public FooData getFooWithId11(final String id) throws FooServiceException, BigDataServiceException {
        final FooData fooData = fooService.getFooData1(id);
        assert Optional.ofNullable(extractBigData1(fooData)).isPresent();
        fooData.setBigData(bigDataService.getBigData1(fooData.getId()));
        return fooData;
    }

    public FooData getFooWithId12(final String id) throws FooServiceException, BigDataServiceException {
        final FooData fooData = fooService.getFooData1(id);
        assert Optional.ofNullable(extractBigData1(fooData)).isEmpty();
        fooData.setBigData(bigDataService.getBigData1(fooData.getId()));
        return fooData;
    }

    public FooData getFooWithId13(final String id) throws FooServiceException, BigDataServiceException {
        if(fooService.getFooData1(id).getBigData() == null) {
            bigDataService.getBigData1(id);
        }
        return new FooData(id, "name");
    }

    public FooData getFooWithId14(final String id) throws FooServiceException, BigDataServiceException {
        if(fooService.getFooData1(id).getBigData() != null) {
            bigDataService.getBigData1(id);
        }
        return new FooData(id, "name");
    }

    public FooData getFooWithId15(final String id) throws FooServiceException, BigDataServiceException {
        if(Optional.ofNullable(fooService.getFooData1(id).getBigData()).isPresent()) {
            bigDataService.getBigData1(id);
        }
        return new FooData(id, "name");
    }

    public FooData getFooWithId16(final String id) throws FooServiceException, BigDataServiceException {
        if(Optional.ofNullable(fooService.getFooData1(id).getBigData()).isEmpty()) {
            bigDataService.getBigData1(id);
        }
        return new FooData(id, "name");
    }

    public String getFooWithId17(final String id) throws FooServiceException, BigDataServiceException {
        final FooData fooData = fooService.getFooData1(id);
        return fooData.getBigData() == null ? bigDataService.getBigData1(fooData.getId()) : fooData.getBigData();
    }

    public String getFooWithId18(final String id) throws FooServiceException, BigDataServiceException {
        final FooData fooData = fooService.getFooData1(id);
        return fooData.getBigData() != null ? bigDataService.getBigData1(fooData.getId()) : fooData.getBigData();
    }

    private static String extractBigData1(FooData fooData) {
        return fooData.getBigData();
    }

    private static Optional<String> extractBigData2(FooData fooData) {
        return Optional.ofNullable(fooData.getBigData());
    }
}
