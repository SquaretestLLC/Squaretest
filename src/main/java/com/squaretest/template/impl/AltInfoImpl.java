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

import java.util.HashMap;
import java.util.Map;

public class AltInfoImpl extends PropsHolderImpl implements Api.AltInfo {

    private Api.Exception expectedException;
    private Api.Variable paramWithEmptyIo;
    private Api.Variable paramWithBrokenIo;
    private Api.DependencyInteraction diToReturnAbsent;
    private Api.DependencyInteraction diToReturnEmpty;
    private Api.DependencyInteraction diToReturnEmptyIo;
    private Api.DependencyInteraction diToReturnBrokenIo;
    private Api.DependencyInteraction diToReturnFailure;
    private Api.DependencyInteraction diThatThrows;
    private Api.Exception diExceptionToThrow;
    private final Map<Object, Object> keyValuePairs = new HashMap<>();

    @Override
    public Api.Exception getExpectedException() {
        return expectedException;
    }

    @Override
    public Api.AltInfo withExpectedException(final Api.Exception expectedException) {
        this.expectedException = expectedException;
        return this;
    }

    @Override
    public void setExpectedException(final Api.Exception expectedException) {
        this.expectedException = expectedException;
    }

    @Override
    public Api.Variable getParamWithEmptyIo() {
        return paramWithEmptyIo;
    }

    @Override
    public Api.AltInfo withParamWithEmptyIo(final Api.Variable paramWithEmptyIo) {
        this.paramWithEmptyIo = paramWithEmptyIo;
        return this;
    }

    @Override
    public void setParamWithEmptyIo(final Api.Variable paramWithEmptyIo) {
        this.paramWithEmptyIo = paramWithEmptyIo;
    }

    @Override
    public Api.Variable getParamWithBrokenIo() {
        return paramWithBrokenIo;
    }

    @Override
    public Api.AltInfo withParamWithBrokenIo(final Api.Variable paramWithBrokenIo) {
        this.paramWithBrokenIo = paramWithBrokenIo;
        return this;
    }

    @Override
    public Api.DependencyInteraction getSubjectDi() {
        if(diToReturnAbsent != null) {
            return diToReturnAbsent;
        } else if(diToReturnEmpty != null) {
            return diToReturnEmpty;
        } else if(diToReturnEmptyIo != null) {
            return diToReturnEmptyIo;
        } else if(diToReturnBrokenIo != null) {
            return diToReturnBrokenIo;
        } else if(diToReturnFailure != null) {
            return diToReturnFailure;
        } else if(diThatThrows != null) {
            return diThatThrows;
        }
        return null;
    }

    @Override
    public void setParamWithBrokenIo(final Api.Variable paramWithBrokenIo) {
        this.paramWithBrokenIo = paramWithBrokenIo;
    }

    @Override
    public Api.DependencyInteraction getDiToReturnAbsent() {
        return diToReturnAbsent;
    }

    @Override
    public Api.AltInfo withDiToReturnAbsent(final Api.DependencyInteraction diToReturnAbsent) {
        this.diToReturnAbsent = diToReturnAbsent;
        return this;
    }

    @Override
    public void setDiToReturnAbsent(final Api.DependencyInteraction diToReturnAbsent) {
        this.diToReturnAbsent = diToReturnAbsent;
    }

    @Override
    public Api.DependencyInteraction getDiToReturnEmpty() {
        return diToReturnEmpty;
    }

    @Override
    public Api.AltInfo withDiToReturnEmpty(final Api.DependencyInteraction diToReturnEmpty) {
        this.diToReturnEmpty = diToReturnEmpty;
        return this;
    }

    @Override
    public void setDiToReturnEmpty(final Api.DependencyInteraction diToReturnEmpty) {
        this.diToReturnEmpty = diToReturnEmpty;
    }

    @Override
    public Api.DependencyInteraction getDiToReturnEmptyIo() {
        return diToReturnEmptyIo;
    }

    @Override
    public Api.AltInfo withDiToReturnEmptyIo(final Api.DependencyInteraction diToReturnEmptyIo) {
        this.diToReturnEmptyIo = diToReturnEmptyIo;
        return this;
    }

    @Override
    public void setDiToReturnEmptyIo(final Api.DependencyInteraction diToReturnEmptyIo) {
        this.diToReturnEmptyIo = diToReturnEmptyIo;
    }

    @Override
    public Api.DependencyInteraction getDiToReturnBrokenIo() {
        return diToReturnBrokenIo;
    }

    @Override
    public Api.AltInfo withDiToReturnBrokenIo(final Api.DependencyInteraction diToReturnBrokenIo) {
        this.diToReturnBrokenIo = diToReturnBrokenIo;
        return this;
    }

    @Override
    public void setDiToReturnBrokenIo(final Api.DependencyInteraction diToReturnBrokenIo) {
        this.diToReturnBrokenIo = diToReturnBrokenIo;
    }

    @Override
    public Api.DependencyInteraction getDiToReturnFailure() {
        return diToReturnFailure;
    }

    @Override
    public Api.AltInfo withDiToReturnFailure(final Api.DependencyInteraction diToReturnFailure) {
        this.diToReturnFailure = diToReturnFailure;
        return this;
    }

    @Override
    public void setDiToReturnFailure(final Api.DependencyInteraction diToReturnFailure) {
        this.diToReturnFailure = diToReturnFailure;
    }

    @Override
    public Api.DependencyInteraction getDiThatThrows() {
        return diThatThrows;
    }

    @Override
    public Api.Exception getDiExceptionToThrow() {
        return diExceptionToThrow;
    }

    @Override
    public Api.AltInfo withDiThatThrows(final Api.DependencyInteraction diThatThrows) {
        this.diThatThrows = diThatThrows;
        return this;
    }

    @Override
    public Api.AltInfo withDiThatThrows(final Api.DependencyInteraction diThatThrows, final Api.Exception diExceptionToThrow) {
        this.diThatThrows = diThatThrows;
        this.diExceptionToThrow = diExceptionToThrow;
        return this;
    }

    @Override
    public Api.AltInfo withDiThatThrows(final Api.DependencyInteraction diThatThrows, final Api.Exception diExceptionToThrow, final Api.Exception expectedException) {
        this.diThatThrows = diThatThrows;
        this.diExceptionToThrow = diExceptionToThrow;
        this.expectedException = expectedException;
        return this;
    }

    @Override
    public void setDiThatThrows(final Api.DependencyInteraction diThatThrows) {
        this.diThatThrows = diThatThrows;
    }

    @Override
    public Api.AltInfo withDiExceptionToThrow(final Api.Exception diExceptionToThrow) {
        this.diExceptionToThrow = diExceptionToThrow;
        return this;
    }

    @Override
    public void setDiExceptionToThrow(final Api.Exception diExceptionToThrow) {
        this.diExceptionToThrow = diExceptionToThrow;
    }

    @Override
    public Api.AltInfo withKeyValuePair(final Object key, final Object value) {
        this.keyValuePairs.put(key, value);
        return this;
    }

    @Override
    public Object get(final Object key) {
        return this.keyValuePairs.get(key);
    }

    @Override
    public Object put(final Object key, final Object value) {
        return this.keyValuePairs.put(key, value);
    }
}
