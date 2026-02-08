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
import org.apache.commons.collections4.SetUtils;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

public class TestInfoImpl extends PropsHolderImpl implements Api.TestInfo {

    private Api.Method sourceMethod;
    private Api.Exception expectedException;
    private boolean expectedValueAbsent;
    private boolean expectedValueNull;
    private boolean expectedValueEmpty;
    private boolean expectedValueTrue;
    private Api.FluentList<Api.DependencyInteraction> mockedDIs;
    private Api.Variable paramWithEmptyIo;
    private Api.Variable paramWithBrokenIo;
    private final Set<Api.DependencyInteraction> disToReturnAbsent = SetUtils.newIdentityHashSet();
    private final Set<Api.DependencyInteraction> disToReturnEmpty = SetUtils.newIdentityHashSet();
    private final Set<Api.DependencyInteraction> disToReturnEmptyIo = SetUtils.newIdentityHashSet();
    private final Set<Api.DependencyInteraction> disToReturnBrokenIo = SetUtils.newIdentityHashSet();
    private final Set<Api.DependencyInteraction> disToReturnFailure = SetUtils.newIdentityHashSet();
    private Api.DependencyInteraction subjectDi;
    private final Map<Object, Object> keyValuePairs = new HashMap<>();
    private final Map<Api.DependencyInteraction, Api.Exception> diToException;

    public TestInfoImpl() {
        this.mockedDIs = new FluentListImpl<>();
        this.diToException = new IdentityHashMap<>();
    }

    @Override
    public Api.Method getMethod() {
        return sourceMethod;
    }

    @Override
    public Api.TestInfo withMethod(final Api.Method sourceMethod) {
        this.sourceMethod = sourceMethod;
        return this;
    }

    @Override
    public boolean getExpectedValueAbsent() {
        return expectedValueAbsent || expectedValueNull;
    }

    @Override
    public Api.TestInfo withExpectedValueAbsent(final boolean expectedValueAbsent) {
        this.expectedValueAbsent = expectedValueAbsent;
        return this;
    }

    @Override
    public boolean getExpectedValueNull() {
        return expectedValueNull;
    }

    @Override
    public Api.TestInfo withExpectedValueNull(final boolean expectedValueNull) {
        this.expectedValueNull = expectedValueNull;
        return this;
    }

    @Override
    public boolean getExpectedValueEmpty() {
        return expectedValueEmpty;
    }

    @Override
    public Api.TestInfo withExpectedValueEmpty(final boolean expectedValueEmpty) {
        this.expectedValueEmpty = expectedValueEmpty;
        return this;
    }

    @Override
    public boolean getExpectedValueTrue() {
        return expectedValueTrue;
    }

    @Override
    public TestInfoImpl withExpectedValueTrue(final boolean expectedValueTrue) {
        this.expectedValueTrue = expectedValueTrue;
        return this;
    }

    @Override
    public Api.FluentList<Api.DependencyInteraction> getMockedDIs() {
        return mockedDIs;
    }

    @Override
    public Api.TestInfo withMockedDIs(final Api.FluentList<Api.DependencyInteraction> mockedDIs) {
        this.mockedDIs = mockedDIs;
        return this;
    }

    @Override
    public Api.TestInfo addMockedDi(final Api.DependencyInteraction mockedDI) {
        this.mockedDIs.add(mockedDI);
        return this;
    }

    @Override
    public Api.Exception getExpectedException() {
        return expectedException;
    }

    @Override
    public Api.TestInfo withExpectedException(final Api.Exception expectedException) {
        this.expectedException = expectedException;
        return this;
    }

    @Override
    public Api.Variable getParamWithEmptyIo() {
        return paramWithEmptyIo;
    }

    @Override
    public Api.TestInfo withParamWithEmptyIo(final Api.Variable paramWithEmptyIo) {
        this.paramWithEmptyIo = paramWithEmptyIo;
        return this;
    }

    @Override
    public Api.Variable getParamWithBrokenIo() {
        return paramWithBrokenIo;
    }

    @Override
    public Api.TestInfo withParamWithBrokenIo(final Api.Variable paramWithBrokenIo) {
        this.paramWithBrokenIo = paramWithBrokenIo;
        return this;
    }

    @Override
    public Api.DependencyInteraction getSubjectDi() {
        return subjectDi;
    }

    @Override
    public Api.TestInfo withSubjectDi(final Api.DependencyInteraction subjectDi) {
        this.subjectDi = subjectDi;
        return this;
    }

    @Override
    public Api.TestInfo withDiToReturnAbsent(final Api.DependencyInteraction diToReturnAbsent) {
        this.disToReturnAbsent.add(diToReturnAbsent);
        return this;
    }

    @Override
    public boolean getSubjectDiReturnsEmpty() {
        return subjectDi != null && disToReturnEmpty.contains(subjectDi);
    }

    @Override
    public boolean getSubjectDiReturnsAbsent() {
        return subjectDi != null && disToReturnAbsent.contains(subjectDi);
    }

    @Override
    public boolean getSubjectDiReturnsEmptyIo() {
        return subjectDi != null && disToReturnEmptyIo.contains(subjectDi);
    }

    @Override
    public boolean getSubjectDiReturnsBrokenIo() {
        return subjectDi != null && disToReturnBrokenIo.contains(subjectDi);
    }

    @Override
    public boolean getSubjectDiReturnsFailure() {
        return subjectDi != null && disToReturnFailure.contains(subjectDi);
    }

    @Override
    public Api.Exception getSubjectDiExceptionToThrow() {
        if(subjectDi == null) {
            return null;
        }
        return diToException.get(subjectDi);
    }

    @Override
    public Api.TestInfo withDiToReturnEmpty(final Api.DependencyInteraction diToReturnEmpty) {
        this.disToReturnEmpty.add(diToReturnEmpty);
        return this;
    }

    @Override
    public Api.TestInfo withDiToReturnEmptyIo(final Api.DependencyInteraction diToReturnEmptyIo) {
        this.disToReturnEmptyIo.add(diToReturnEmptyIo);
        return this;
    }

    @Override
    public Api.TestInfo withDiToReturnBrokenIo(final Api.DependencyInteraction diToReturnBrokenIo) {
        this.disToReturnBrokenIo.add(diToReturnBrokenIo);
        return this;
    }

    @Override
    public Api.TestInfo withDiToReturnFailure(final Api.DependencyInteraction diToReturnFailure) {
        this.disToReturnFailure.add(diToReturnFailure);
        return this;
    }

    @Override
    public Api.TestInfo withDiThatThrows(final Api.DependencyInteraction diThatThrows, final Api.Exception diExceptionToThrow) {
        diToException.put(diThatThrows, diExceptionToThrow);
        return this;
    }

    @Override
    public boolean shouldReturnAbsent(final Api.DependencyInteraction di) {
        return disToReturnAbsent.contains(di);
    }

    @Override
    public boolean shouldReturnEmpty(final Api.DependencyInteraction di) {
        return disToReturnEmpty.contains(di);
    }

    @Override
    public boolean shouldReturnEmptyIo(final Api.DependencyInteraction di) {
        return disToReturnEmptyIo.contains(di);
    }

    @Override
    public boolean shouldReturnBrokenIo(final Api.DependencyInteraction di) {
        return disToReturnBrokenIo.contains(di);
    }

    @Override
    public boolean shouldThrowException(final Api.DependencyInteraction di) {
        return di != null && diToException.containsKey(di);
    }

    @Override
    public Api.Exception getExceptionToThrow(final Api.DependencyInteraction di) {
        return diToException.get(di);
    }

    @Override
    public boolean shouldReturnFailure(final Api.DependencyInteraction di) {
        return disToReturnFailure.contains(di);
    }

    @Override
    public Api.TestInfo withKeyValuePair(final Object key, final Object value) {
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
