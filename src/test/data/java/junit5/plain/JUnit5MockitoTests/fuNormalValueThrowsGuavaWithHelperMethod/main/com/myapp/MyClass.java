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
import com.google.common.base.Optional;

public class MyClass {
    private final AuthService authService;
    private final UserInfoService userInfoService;
    private final FooService fooService;
    private final OldService oldService;

    public MyClass(final AuthService authService, final UserInfoService userInfoService, final FooService fooService, final OldService oldService) {
        this.authService = authService;
        this.userInfoService = userInfoService;
        this.fooService = fooService;
        this.oldService = oldService;
    }

    public FooData storeFoo1(final FooData fooData) throws FooAlreadyExistsException, FooStoreException {
        final String fooId = fooData.getId();
        final Optional<FooData> existingFoo = getExistingFoo1(fooId);
        if(existingFoo.isPresent()) {
            throw new FooAlreadyExistsException(fooId);
        }
        return fooService.storeFoo1(fooData);
    }

    public FooData storeFoo2(final String userId, final FooData fooData) throws FooAlreadyExistsException, FooStoreException, UserNotAuthorizedException {
        if(Collections.disjoint(authService.getAllowedGroupIds1("StoreFoo"), userInfoService.getUsersAuthGroups(userId))) {
            throw new UserNotAuthorizedException(userId);
        }
        final String fooId = fooData.getId();
        final Optional<FooData> existingFoo = getExistingFoo1(fooId);
        if(existingFoo.isPresent()) {
            throw new FooAlreadyExistsException(fooId);
        }
        return fooService.storeFoo1(fooData);
    }

    public FooData storeFoo3(final FooData fooData) throws FooAlreadyExistsException, FooStoreException {
        final String fooId = fooData.getId();
        final Optional<FooData> existingFoo = getExistingFoo2(fooId);
        if(existingFoo.isPresent()) {
            throw new FooAlreadyExistsException(fooId);
        }
        return fooService.storeFoo1(fooData);
    }

    public FooData storeFoo4(final String userId, final FooData fooData) throws FooAlreadyExistsException, FooStoreException, UserNotAuthorizedException {
        if(Collections.disjoint(authService.getAllowedGroupIds1("StoreFoo"), userInfoService.getUsersAuthGroups(userId))) {
            throw new UserNotAuthorizedException(userId);
        }
        final String fooId = fooData.getId();
        final Optional<FooData> existingFoo = getExistingFoo2(fooId);
        if(existingFoo.isPresent()) {
            throw new FooAlreadyExistsException(fooId);
        }
        return fooService.storeFoo1(fooData);
    }

    public FooData storeFoo5(final FooData fooData) throws FooAlreadyExistsException, FooStoreException {
        final String fooId = fooData.getId();
        final Optional<FooData> existingFoo = getExistingFoo3(fooId);
        if(existingFoo.isPresent()) {
            throw new FooAlreadyExistsException(fooId);
        }
        return fooService.storeFoo1(fooData);
    }

    public FooData storeFoo6(final String userId, final FooData fooData) throws FooAlreadyExistsException, FooStoreException, UserNotAuthorizedException {
        if(Collections.disjoint(authService.getAllowedGroupIds1("StoreFoo"), userInfoService.getUsersAuthGroups(userId))) {
            throw new UserNotAuthorizedException(userId);
        }
        final String fooId = fooData.getId();
        final Optional<FooData> existingFoo = getExistingFoo3(fooId);
        if(existingFoo.isPresent()) {
            throw new FooAlreadyExistsException(fooId);
        }
        return fooService.storeFoo1(fooData);
    }

    public FooData storeFoo7(final FooData fooData) throws FooAlreadyExistsException, FooStoreException {
        final String fooId = fooData.getId();
        final Optional<FooData> existingFoo = getExistingFoo4(fooId);
        if(existingFoo.isPresent()) {
            throw new FooAlreadyExistsException(fooId);
        }
        return fooService.storeFoo1(fooData);
    }

    public FooData storeFoo8(final String userId, final FooData fooData) throws FooAlreadyExistsException, FooStoreException, UserNotAuthorizedException {
        if(Collections.disjoint(authService.getAllowedGroupIds1("StoreFoo"), userInfoService.getUsersAuthGroups(userId))) {
            throw new UserNotAuthorizedException(userId);
        }
        final String fooId = fooData.getId();
        final Optional<FooData> existingFoo = getExistingFoo4(fooId);
        if(existingFoo.isPresent()) {
            throw new FooAlreadyExistsException(fooId);
        }
        return fooService.storeFoo1(fooData);
    }

    private Optional<FooData> getExistingFoo2(final String fooId) {
        try {
            return Optional.of(convert(fooService.getFooData2(fooId)));
        } catch (FooNotFoundException e) {
            try {
                return Optional.of(convert(oldService.getFooData2(fooId)));
            } catch (FooNotFoundException ex) {
                return Optional.absent();
            }
        }
    }

    private Optional<FooData> getExistingFoo3(final String fooId) {
        return getFooFromServiceHelper1(fooId).or(getFooFromOldServiceHelper1(fooId));
    }

    private Optional<FooData> getExistingFoo4(final String fooId) {
        final Optional<FooData> fooData = getFooFromServiceHelper1(fooId);
        if(fooData.isPresent()) {
            return fooData;
        }
        return getFooFromOldServiceHelper1(fooId);
    }

    private Optional<FooData> getFooFromServiceHelper1(final String id) {
        try {
            return Optional.of(convert(fooService.getFooData2(id)));
        } catch (FooNotFoundException e) {
            return Optional.absent();
        }
    }

    private Optional<FooData> getFooFromOldServiceHelper1(final String id) {
        try {
            return Optional.of(convert(oldService.getFooData2(id)));
        } catch (FooNotFoundException e) {
            return Optional.absent();
        }
    }

    private FooData convert(final FooData fooData) {
        fooData.setName("PREFIX" + fooData.getName());
        return fooData;
    }

    private Optional<FooData> getExistingFoo1(final String id) {
        return fooService.getFooData1(id).or(oldService.getFooData1(id));
    }
}
