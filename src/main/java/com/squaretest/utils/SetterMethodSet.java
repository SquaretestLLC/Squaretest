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
package com.squaretest.utils;

import com.squaretest.template.api.Api;

import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SetterMethodSet {

    private static final SetterMethodSet EmptySet = new SetterMethodSet(Collections.emptyMap(), Collections.emptyMap());

    private final Map<String, Api.Method> nameToOverloadMap;
    private final Map<Api.ClassMember, Api.Method> targetFieldToMethodMap;

    public SetterMethodSet() {
        this(new LinkedHashMap<>(), new IdentityHashMap<>());
    }

    private SetterMethodSet(final Map<String, Api.Method> nameToOverloadMap, final Map<Api.ClassMember, Api.Method> targetFieldToMethodMap) {
        this.nameToOverloadMap = nameToOverloadMap;
        this.targetFieldToMethodMap = targetFieldToMethodMap;
    }

    public Collection<Api.Method> values() {
        return nameToOverloadMap.values();
    }

    public Api.Method getMethodWithSameNameOrField(final Api.Method method) {
        final Api.Method existingMethod = nameToOverloadMap.get(method.getName());
        if(existingMethod != null) {
            return existingMethod;
        }
        final Api.ClassMember targetField = method.getTargetField();
        if(targetField != null) {
            return targetFieldToMethodMap.get(targetField);
        }
        return null;
    }

    public boolean contains(final Api.Method method) {
        return getMethodWithSameNameOrField(method) != null;
    }

    public void add(final Api.Method method) {
        // Remove the existing entries if they're present.
        final Api.Method existingMethod = getMethodWithSameNameOrField(method);
        if(existingMethod != null) {
            final Api.ClassMember targetField = existingMethod.getTargetField();
            if(targetField != null) {
                targetFieldToMethodMap.remove(targetField);
            }
            nameToOverloadMap.remove(existingMethod.getName());
        }

        // Add the new entry.
        final Api.ClassMember targetField = method.getTargetField();
        if(targetField != null) {
            targetFieldToMethodMap.put(targetField, method);
        }
        nameToOverloadMap.put(method.getName(), method);
    }

    public void addAll(final SetterMethodSet methodSet) {
        for(final Api.Method method : methodSet.values()) {
            add(method);
        }
    }

    public static SetterMethodSet emptySet() {
        return EmptySet;
    }
}
