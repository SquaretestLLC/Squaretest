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
package com.squaretest.generation.abstractmethods;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Objects;

public class AbstractClassInfo {

    @NotNull
    private final Collection<String> importsRequiredForTypesInMethods;
    @Nullable
    private final String classBodyWithStubMethodImplementations;
    private final boolean isAbstract;

    public AbstractClassInfo(
            @NotNull final Collection<String> importsRequiredForTypesInMethods,
            @Nullable final String classBodyWithStubMethodImplementations, final boolean isAbstract) {
        this.importsRequiredForTypesInMethods = importsRequiredForTypesInMethods;
        this.classBodyWithStubMethodImplementations = classBodyWithStubMethodImplementations;
        this.isAbstract = isAbstract;
    }

    @NotNull
    public Collection<String> getImportsRequiredForStubMethodImplementations() {
        return importsRequiredForTypesInMethods;
    }

    @Nullable
    public String getClassBodyWithStubMethodImplementations() {
        return classBodyWithStubMethodImplementations;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final AbstractClassInfo that = (AbstractClassInfo) o;
        return isAbstract == that.isAbstract &&
                Objects.equals(importsRequiredForTypesInMethods, that.importsRequiredForTypesInMethods) &&
                Objects.equals(classBodyWithStubMethodImplementations, that.classBodyWithStubMethodImplementations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(importsRequiredForTypesInMethods, classBodyWithStubMethodImplementations, isAbstract);
    }
}
