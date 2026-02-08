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

import java.util.Collections;
import java.util.Optional;

public class MyClass {
    private final AuthService authService;
    private final UserInfoService userInfoService;
    private final FooService fooService;
    private final OldService oldService;
    private final MetricService metricService;

    public MyClass(final AuthService authService, final UserInfoService userInfoService, final FooService fooService, final OldService oldService, final MetricService metricService) {
        this.authService = authService;
        this.userInfoService = userInfoService;
        this.fooService = fooService;
        this.oldService = oldService;
        this.metricService = metricService;
    }

    public FooData storeFoo1(final FooData fooData) throws FooAlreadyExistsException, FooStoreException {
        final String fooId = fooData.getId();
        metricService.recordStoreFooCalled(fooId);
        final Optional<FooData> existingFoo = getExistingFoo1(fooId);
        metricService.recordExistingFooCallReturned(fooId);
        if(existingFoo.isPresent()) {
            metricService.recordStoreFooExistingFooFound(fooId);
            throw new FooAlreadyExistsException(fooId);
        }
        metricService.recordActuallyStoringFoo(fooId);
        return fooService.storeFoo1(fooData);
    }

    public FooData storeFoo2(final String userId, final FooData fooData) throws FooAlreadyExistsException, FooStoreException, UserNotAuthorizedException {
        metricService.recordStoreFooCalled(fooData.getId());
        if(Collections.disjoint(authService.getAllowedGroupIds1("StoreFoo"), userInfoService.getUsersAuthGroups(userId))) {
            metricService.recordNotAuthorized(fooData.getId());
            throw new UserNotAuthorizedException(userId);
        }
        metricService.recordAuthorized(fooData.getId());
        final String fooId = fooData.getId();
        final Optional<FooData> existingFoo = getExistingFoo1(fooId);
        metricService.recordExistingFooCallReturned(fooId);
        if(existingFoo.isPresent()) {
            metricService.recordStoreFooExistingFooFound(fooId);
            throw new FooAlreadyExistsException(fooId);
        }
        metricService.recordActuallyStoringFoo(fooId);
        return fooService.storeFoo1(fooData);
    }

    public FooData storeFoo3(final FooData fooData) throws FooAlreadyExistsException, FooStoreException {
        final String fooId = fooData.getId();
        metricService.recordStoreFooCalled(fooId);
        final Optional<FooData> existingFoo = getExistingFoo2(fooId);
        metricService.recordExistingFooCallReturned(fooId);
        if(existingFoo.isPresent()) {
            metricService.recordStoreFooExistingFooFound(fooId);
            throw new FooAlreadyExistsException(fooId);
        }
        metricService.recordActuallyStoringFoo(fooId);
        return fooService.storeFoo1(fooData);
    }

    public FooData storeFoo4(final String userId, final FooData fooData) throws FooAlreadyExistsException, FooStoreException, UserNotAuthorizedException {
        metricService.recordStoreFooCalled(fooData.getId());
        if(Collections.disjoint(authService.getAllowedGroupIds1("StoreFoo"), userInfoService.getUsersAuthGroups(userId))) {
            metricService.recordNotAuthorized(fooData.getId());
            throw new UserNotAuthorizedException(userId);
        }
        metricService.recordAuthorized(fooData.getId());
        final String fooId = fooData.getId();
        final Optional<FooData> existingFoo = getExistingFoo2(fooId);
        metricService.recordExistingFooCallReturned(fooId);
        if(existingFoo.isPresent()) {
            metricService.recordStoreFooExistingFooFound(fooId);
            throw new FooAlreadyExistsException(fooId);
        }
        metricService.recordActuallyStoringFoo(fooId);
        return fooService.storeFoo1(fooData);
    }

    public FooData storeFoo5(final FooData fooData) throws FooAlreadyExistsException, FooStoreException {
        final String fooId = fooData.getId();
        metricService.recordStoreFooCalled(fooId);
        final Optional<FooData> existingFoo = getExistingFoo3(fooId);
        metricService.recordExistingFooCallReturned(fooId);
        if(existingFoo.isPresent()) {
            metricService.recordStoreFooExistingFooFound(fooId);
            throw new FooAlreadyExistsException(fooId);
        }
        metricService.recordActuallyStoringFoo(fooId);
        return fooService.storeFoo1(fooData);
    }

    public FooData storeFoo6(final String userId, final FooData fooData) throws FooAlreadyExistsException, FooStoreException, UserNotAuthorizedException {
        metricService.recordStoreFooCalled(fooData.getId());
        if(Collections.disjoint(authService.getAllowedGroupIds1("StoreFoo"), userInfoService.getUsersAuthGroups(userId))) {
            metricService.recordNotAuthorized(fooData.getId());
            throw new UserNotAuthorizedException(userId);
        }
        metricService.recordAuthorized(fooData.getId());
        final String fooId = fooData.getId();
        final Optional<FooData> existingFoo = getExistingFoo3(fooId);
        metricService.recordExistingFooCallReturned(fooId);
        if(existingFoo.isPresent()) {
            metricService.recordStoreFooExistingFooFound(fooId);
            throw new FooAlreadyExistsException(fooId);
        }
        metricService.recordActuallyStoringFoo(fooId);
        return fooService.storeFoo1(fooData);
    }

    public FooData storeFoo7(final FooData fooData) throws FooAlreadyExistsException, FooStoreException {
        final String fooId = fooData.getId();
        metricService.recordStoreFooCalled(fooId);
        final Optional<FooData> existingFoo = getExistingFoo4(fooId);
        metricService.recordExistingFooCallReturned(fooId);
        if(existingFoo.isPresent()) {
            metricService.recordStoreFooExistingFooFound(fooId);
            throw new FooAlreadyExistsException(fooId);
        }
        metricService.recordActuallyStoringFoo(fooId);
        return fooService.storeFoo1(fooData);
    }

    public FooData storeFoo8(final String userId, final FooData fooData) throws FooAlreadyExistsException, FooStoreException, UserNotAuthorizedException {
        metricService.recordStoreFooCalled(fooData.getId());
        if(Collections.disjoint(authService.getAllowedGroupIds1("StoreFoo"), userInfoService.getUsersAuthGroups(userId))) {
            metricService.recordNotAuthorized(fooData.getId());
            throw new UserNotAuthorizedException(userId);
        }
        metricService.recordAuthorized(fooData.getId());
        final String fooId = fooData.getId();
        final Optional<FooData> existingFoo = getExistingFoo4(fooId);
        metricService.recordExistingFooCallReturned(fooId);
        if(existingFoo.isPresent()) {
            metricService.recordStoreFooExistingFooFound(fooId);
            throw new FooAlreadyExistsException(fooId);
        }
        metricService.recordActuallyStoringFoo(fooId);
        return fooService.storeFoo1(fooData);
    }

    private Optional<FooData> getExistingFoo2(final String fooId) {
        try {
            metricService.recordCallingFooService(fooId);
            return Optional.of(fooService.getFooData2(fooId));
        } catch (FooNotFoundException e) {
            metricService.recordCallingOldFooService(fooId);
            try {
                return Optional.of(oldService.getFooData2(fooId));
            } catch (FooNotFoundException ex) {
                metricService.recordFooNotFound(fooId);
                return Optional.empty();
            }
        }
    }

    private Optional<FooData> getExistingFoo3(final String fooId) {
        return getFooFromServiceHelper1(fooId).or(() -> getFooFromOldServiceHelper1(fooId));
    }

    private Optional<FooData> getExistingFoo4(final String fooId) {
        final Optional<FooData> fooData = getFooFromServiceHelper1(fooId);
        metricService.recordFinishedCallingServiceHelper1(fooId);
        if(fooData.isPresent()) {
            return fooData;
        }
        final Optional<FooData> ret = getFooFromOldServiceHelper1(fooId);
        metricService.recordFinishedCallingOldServiceHelper1(fooId);
        return ret;
    }

    private Optional<FooData> getFooFromServiceHelper1(final String id) {
        try {
            metricService.recordCallingFooService(id);
            return Optional.of(fooService.getFooData2(id));
        } catch (FooNotFoundException e) {
            metricService.recordFooNotFoundInFooService(id);
            return Optional.empty();
        }
    }

    private Optional<FooData> getFooFromOldServiceHelper1(final String id) {
        try {
            metricService.recordCallingOldFooService(id);
            return Optional.of(oldService.getFooData2(id));
        } catch (FooNotFoundException e) {
            metricService.recordFooNotFoundInOldService(id);
            return Optional.empty();
        }
    }

    private Optional<FooData> getExistingFoo1(final String id) {
        return fooService.getFooData1(id).or(() -> oldService.getFooData1(id));
    }
}
