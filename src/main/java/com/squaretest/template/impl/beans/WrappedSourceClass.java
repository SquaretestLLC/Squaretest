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
package com.squaretest.template.impl.beans;

import com.squaretest.template.api.Api;
import com.squaretest.utils.SetterMethodSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WrappedSourceClass {
    private final Api.SourceClass sourceClass;
    private int numberOfBeanSettersCalled;
    private final boolean isRecognized;
    @Nullable
    private InitSetterIncludedDecider lastInitSetterDecider;
    private final SetterMethodSet calledMethodOverloads;

    public WrappedSourceClass(
            @NotNull final Api.SourceClass sourceClass, final int numberOfBeanSettersCalled,
            final boolean isRecognized, final SetterMethodSet calledMethodOverloads,
            @Nullable final InitSetterIncludedDecider initSetterIncludedDecider) {
        this.sourceClass = sourceClass;
        this.numberOfBeanSettersCalled = numberOfBeanSettersCalled;
        this.isRecognized = isRecognized;
        this.calledMethodOverloads = calledMethodOverloads;
        this.lastInitSetterDecider = initSetterIncludedDecider;
    }

    public Api.SourceClass getSourceClass() {
        return sourceClass;
    }

    public int getNumberOfBeanSettersCalled() {
        return numberOfBeanSettersCalled;
    }

    public void setNumberOfBeanSettersCalled(final int numberOfBeanSettersCalled) {
        this.numberOfBeanSettersCalled = numberOfBeanSettersCalled;
    }

    public boolean isRecognized() {
        return isRecognized;
    }

    public SetterMethodSet getCalledMethodOverloads() {
        return calledMethodOverloads;
    }

    @Nullable
    public InitSetterIncludedDecider getLastInitSetterDecider() {
        return lastInitSetterDecider;
    }

    public void setLastInitSetterDecider(@Nullable final InitSetterIncludedDecider lastInitSetterDecider) {
        this.lastInitSetterDecider = lastInitSetterDecider;
    }

    public void addCalledSetters(final SetterMethodSet methodSet) {
        calledMethodOverloads.addAll(methodSet);
    }
}
