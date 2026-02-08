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
package com.squaretest.template.impl;

import com.squaretest.template.api.Api;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Set;

public class DependencyInteractionImpl extends PropsHolderImpl implements Api.DependencyInteraction {
    private final Api.Method method;
    private final Api.ClassMember classMember;
    private final String canonicalKey;
    private final Set<String> diKeys;
    private final Api.MethodCallExpression methodCallExpression;
    private final boolean returnValueIgnored;
    private Api.Method sourceMethodContainingDi;
    private String overloadSuffix;

    public DependencyInteractionImpl(
            final Api.ClassMember classMember, final Api.Method method,
            final Set<String> diKeys, final Api.MethodCallExpression methodCallExpression, final String canonicalKey, final boolean returnValueIgnored) {
        this.method = method;
        this.classMember = classMember;
        this.diKeys = diKeys;
        this.methodCallExpression = methodCallExpression;
        this.canonicalKey = canonicalKey;
        this.returnValueIgnored = returnValueIgnored;
        this.overloadSuffix = "";
    }

    @Override
    public Api.Method getMethod() {
        return method;
    }

    @Override
    @NotNull
    public Api.MethodCallExpression getMethodCallExpression() {
        return methodCallExpression;
    }

    @Override
    public Api.ClassMember getField() {
        return classMember;
    }

    @Override
    public String getCanonicalKey() {
        return canonicalKey;
    }

    @Override
    public boolean isReturnValueIgnored() {
        return returnValueIgnored;
    }

    @Override
    public String getOverloadSuffix() {
        return overloadSuffix;
    }

    public void setOverloadSuffix(final String overloadSuffix) {
        this.overloadSuffix = overloadSuffix;
    }

    public Set<String> getDiKeys() {
        return diKeys;
    }

    public Api.Method getSourceMethodContainingDi() {
        return sourceMethodContainingDi;
    }

    public void setSourceMethodContainingDi(final Api.Method sourceMethodContainingDi) {
        this.sourceMethodContainingDi = sourceMethodContainingDi;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final DependencyInteractionImpl that = (DependencyInteractionImpl) o;
        return method == that.method && classMember == that.classMember;
    }

    @Override
    public int hashCode() {
        return Objects.hash(System.identityHashCode(method), System.identityHashCode(classMember));
    }
}
