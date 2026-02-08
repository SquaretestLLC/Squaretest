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

import java.util.List;

public class MyClass {
    private final GroupService groupService;
    private final PermissionService permissionService;
    private final DataService dataService;

    public MyClass(final GroupService groupService, final PermissionService permissionService, final DataService dataService) {
        this.groupService = groupService;
        this.permissionService = permissionService;
        this.dataService = dataService;
    }

    public String getData1(final String userId) {
        final String groupId = groupService.getGroupIdForUser(userId);
        if(!permissionService.getAllowedGroups().contains(groupId)) {
            throw new NotAuthorizedException();
        }
        return dataService.getData();
    }

    public String getData2(final String userId) {
        final String groupId = groupService.getGroupIdForUser(userId);
        final List<String> allowedGroups = permissionService.getAllowedGroups();
        if(!allowedGroups.contains(groupId)) {
            throw new NotAuthorizedException();
        }
        return dataService.getData();
    }

    public String getData3(final String userId) {
        final String groupId = groupService.getGroupIdForUser(userId);
        final List<String> allowedGroups = permissionService.getAllowedGroups();
        if(allowedGroups.contains(groupId)) {
            return dataService.getData();
        }
        throw new NotAuthorizedException();
    }

    public String getData4(final String userId) {
        final String groupId = groupService.getGroupIdForUser(userId);
        final List<String> allowedGroups = permissionService.getAllowedGroups();
        if(allowedGroups.contains(groupId)) {
            return dataService.getData();
        } else {
            throw new NotAuthorizedException();
        }
    }

    public String getData5(final String userId) {
        final String groupId = groupService.getGroupIdForUser(userId);
        final List<String> allowedGroups = permissionService.getAllowedGroups();
        if(!allowedGroups.contains(groupId)) {
            throw new NotAuthorizedException();
        } else {
            return dataService.getData();
        }
    }

    public String getData6(final String userId) {
        if(!permissionService.getAllowedGroups().contains(groupService.getGroupIdForUser(userId))) {
            throw new NotAuthorizedException();
        }
        return dataService.getData();
    }
}
