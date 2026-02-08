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

import java.util.*;

public class MyClass {
    private final AuthService authService;
    private final UserService userService;
    private final FooService fooService;

    public MyClass(final AuthService authService, final UserService userService, final FooService fooService) {
        this.authService = authService;
        this.userService = userService;
        this.fooService = fooService;
    }

    public List<FooData> getFoos1(final String userId, final String searchCriteria) throws UserNotAuthorizedException {
        final List<String> myAuthGroups = userService.getAuthGroups1(userId);
        final List<String> allowedAuthGroups = authService.getAllowedAuthGroupIds1();
        if(Collections.disjoint(myAuthGroups, allowedAuthGroups)) {
            throw new UserNotAuthorizedException(userId);
        }
        return fooService.getFoos1(searchCriteria);
    }
    public List<FooData> getFoos2(final String userId, final String searchCriteria) throws UserNotAuthorizedException {
        final List<String> myAuthGroups = userService.getAuthGroups1(userId);
        final List<String> allowedAuthGroups = authService.getAllowedAuthGroupIds1();
        if(!Collections.disjoint(myAuthGroups, allowedAuthGroups)) {
            return fooService.getFoos1(searchCriteria);
        }
        throw new UserNotAuthorizedException(userId);
    }
    public List<FooData> getFoos3(final String userId, final String searchCriteria) throws UserNotAuthorizedException {
        final String myAuthGroup = userService.getAuthGroup(userId);
        final List<String> allowedAuthGroups = authService.getAllowedAuthGroupIds1();
        if(!allowedAuthGroups.contains(myAuthGroup)) {
            throw new UserNotAuthorizedException(userId);
        }
        return fooService.getFoos1(searchCriteria);
    }
    public List<FooData> getFoos4(final String userId, final String searchCriteria) throws UserNotAuthorizedException {
        final String myAuthGroup = userService.getAuthGroup(userId);
        final List<String> allowedAuthGroups = authService.getAllowedAuthGroupIds1();
        if(allowedAuthGroups.contains(myAuthGroup)) {
            return fooService.getFoos1(searchCriteria);
        }
        throw new UserNotAuthorizedException(userId);
    }
    public List<FooData> getFoos5(final String userId, final String searchCriteria) throws UserNotAuthorizedException {
        final List<String> myAuthGroups = Collections.unmodifiableList(userService.getAuthGroups1(userId));
        final List<String> allowedAuthGroups = Collections.unmodifiableList(authService.getAllowedAuthGroupIds1());
        if(Collections.disjoint(myAuthGroups, allowedAuthGroups)) {
            throw new UserNotAuthorizedException(userId);
        }
        return Collections.unmodifiableList(fooService.getFoos1(searchCriteria));
    }
    public Set<FooData> getFoos6(final String userId, final String searchCriteria) throws UserNotAuthorizedException {
        final Set<String> myAuthGroups = Set.copyOf(userService.getAuthGroups1(userId));
        final Set<String> allowedAuthGroups = Set.copyOf(authService.getAllowedAuthGroupIds1());
        if(Collections.disjoint(myAuthGroups, allowedAuthGroups)) {
            throw new UserNotAuthorizedException(userId);
        }
        return Set.copyOf(fooService.getFoos1(searchCriteria));
    }
    public Set<FooData> getFoos7(final String userId, final String searchCriteria) throws UserNotAuthorizedException {
        final Set<String> myAuthGroups = Set.of(userService.getAuthGroups2(userId));
        final Set<String> allowedAuthGroups = Set.of(authService.getAllowedAuthGroupIds2());
        if(Collections.disjoint(myAuthGroups, allowedAuthGroups)) {
            throw new UserNotAuthorizedException(userId);
        }
        return Set.of(fooService.getFoos2(searchCriteria));
    }
    public List<FooData> getFoos8(final String userId, final String searchCriteria) throws UserNotAuthorizedException {
        final List<String> myAuthGroups = userService.getAuthGroups1(userId);
        final List<String> allowedAuthGroups = authService.getAllowedAuthGroupIds1();
        if(Collections.disjoint(myAuthGroups, allowedAuthGroups)) {
            throw new UserNotAuthorizedException(userId);
        }
        return List.of(fooService.getFoos3(searchCriteria));
    }
    public List<FooData> getFoos9(final String userId, final String searchCriteria) throws UserNotAuthorizedException {
        final List<String> myAuthGroups = userService.getAuthGroups1(userId);
        final List<String> allowedAuthGroups = authService.getAllowedAuthGroupIds1();
        if(Collections.disjoint(myAuthGroups, allowedAuthGroups)) {
            throw new UserNotAuthorizedException(userId);
        }
        final FooData fooData = fooService.getFoos3(searchCriteria);
        return List.of(fooData);
    }

    public List<FooData> getFoos10(final String userId, final String searchCriteria) throws UserNotAuthorizedException {
        final List<String> myAuthGroups = userService.getAuthGroups1(userId);
        final List<String> allowedAuthGroups = authService.getAllowedAuthGroupIds1();
        if(Collections.disjoint(myAuthGroups, allowedAuthGroups)) {
            throw new UserNotAuthorizedException(userId);
        }
        final FooData[] fooData = fooService.getFoos2(searchCriteria);
        return Arrays.asList(fooData);
    }
    public List<FooData> getFoos11(final String userId, final String searchCriteria) throws UserNotAuthorizedException {
        final List<String> myAuthGroups = userService.getAuthGroups1(userId);
        final List<String> allowedAuthGroups = authService.getAllowedAuthGroupIds1();
        if(Collections.disjoint(myAuthGroups, allowedAuthGroups)) {
            throw new UserNotAuthorizedException(userId);
        }
        final List<OtherData> otherDatas = fooService.getFoos4(searchCriteria);
        final List<FooData> ret = new ArrayList<>();
        for(final OtherData otherData : otherDatas) {
            ret.add(new FooData(otherData.getId(), otherData.getName()));
        }
        return ret;
    }
    public List<FooData> getFoos12(final String userId, final String searchCriteria) throws UserNotAuthorizedException {
        final List<String> myAuthGroups = userService.getAuthGroups1(userId);
        final List<String> allowedAuthGroups = authService.getAllowedAuthGroupIds1();
        if(Collections.disjoint(myAuthGroups, allowedAuthGroups)) {
            throw new UserNotAuthorizedException(userId);
        }
        final List<OtherData> otherDatas = fooService.getFoos4(searchCriteria);
        final Iterator<OtherData> otherDataIterator = otherDatas.iterator();
        final List<FooData> ret = new ArrayList<>();
        while(otherDataIterator.hasNext()) {
            final OtherData otherData = otherDataIterator.next();
            ret.add(new FooData(otherData.getId(), otherData.getName()));
        }
        return ret;
    }
    public List<FooData> getFoos13(final String userId, final String searchCriteria) throws UserNotAuthorizedException {
        final List<String> myAuthGroups = userService.getAuthGroups1(userId);
        final List<String> allowedAuthGroups = authService.getAllowedAuthGroupIds1();
        if(Collections.disjoint(myAuthGroups, allowedAuthGroups)) {
            throw new UserNotAuthorizedException(userId);
        }
        final List<OtherData> otherDatas = fooService.getFoos4(searchCriteria);
        final Iterator<OtherData> otherDataIterator = otherDatas.listIterator();
        final List<FooData> ret = new ArrayList<>();
        while(otherDataIterator.hasNext()) {
            final OtherData otherData = otherDataIterator.next();
            ret.add(new FooData(otherData.getId(), otherData.getName()));
        }
        return ret;
    }
    public List<FooData> getFoos14(final String userId, final String searchCriteria) throws UserNotAuthorizedException {
        final List<String> myAuthGroups = userService.getAuthGroups1(userId);
        final List<String> allowedAuthGroups = authService.getAllowedAuthGroupIds1();
        if(Collections.disjoint(myAuthGroups, allowedAuthGroups)) {
            throw new UserNotAuthorizedException(userId);
        }
        final List<OtherData> otherDatas = fooService.getFoos4(searchCriteria);
        final Iterator<OtherData> otherDataIterator = otherDatas.listIterator(0);
        final List<FooData> ret = new ArrayList<>();
        while(otherDataIterator.hasNext()) {
            final OtherData otherData = otherDataIterator.next();
            ret.add(new FooData(otherData.getId(), otherData.getName()));
        }
        return ret;
    }
    public List<FooData> getFoos15(final String userId, final String searchCriteria) throws UserNotAuthorizedException {
        if(Collections.disjoint(userService.getAuthGroups1(userId), authService.getAllowedAuthGroupIds1())) {
            throw new UserNotAuthorizedException(userId);
        }
        return fooService.getFoos1(searchCriteria);
    }
}
