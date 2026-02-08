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
package com.squaretest.template.api;

import com.squaretest.generation.ExceptionCreator;
import com.squaretest.generation.OutcomeProvider;
import com.squaretest.generation.dependencyinteraction.outcomes.AltFlowInfo;
import com.squaretest.generation.dependencyinteraction.outcomes.DiEntry;
import com.squaretest.generation.dependencyinteraction.outcomes.DiEntrySet;
import com.squaretest.generation.dependencyinteraction.outcomes.FilteredPrimaryFlowInfo;
import com.squaretest.generation.dependencyinteraction.outcomes.OutcomeInfo;
import com.squaretest.generation.dependencyinteraction.outcomes.OutcomeKeyUtils;
import com.squaretest.generation.dependencyinteraction.outcomes.PrimaryFlow;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;
import com.squaretest.generation.dependencyinteraction.outcomes.UnfilteredPrimaryFlowInfo;
import com.squaretest.template.impl.DependencyInteractionImpl;
import com.squaretest.template.impl.FluentListImpl;
import com.squaretest.template.impl.MethodImpl;
import com.squaretest.template.impl.OldTestCaseFactoryUtils;
import com.squaretest.template.impl.TestInfoImpl;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestInfoFactoryImpl implements Api.TestInfoFactory {
    private final OutcomeProvider outcomeProvider;
    private final ExceptionCreator exceptionCreator;
    private OutcomeInfo outcomeInfo;

    public TestInfoFactoryImpl(
            final OutcomeProvider outcomeProvider,
            final ExceptionCreator exceptionCreator) {
        this.outcomeProvider = outcomeProvider;
        this.exceptionCreator = exceptionCreator;
        this.outcomeInfo = null;
    }

    private void initializeIfNeeded() {
        if(outcomeInfo == null) {
            outcomeInfo = outcomeProvider.computeOutcomeInfo();
        }
    }

    @Override
    public Api.TestInfo newValue() {
        return new TestInfoImpl();
    }

    @Override
    public Api.TestInfo primaryFlow(final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs) {
        initializeIfNeeded();
        // Call determinePrimaryFlow(..).
        // If null, return new TestInfoImpl().withMethod(sourceMethod).withMockedDIs(mockedDIs);
        // Iterate through the mockedDIs.
        // If the DI is not in the sourceMethod, and we have not reached a DI in the source method (we're in disBefore),
        //   Add the DI if it is in the UnfilteredPrimaryFlow for its containing sourceMethod.
        // If the DI is not in the sourceMethod, and we have reached a DI in the source method (we're in disAfter),
        //   If the primaryFlow throws, do not add the DI here. Otherwise, add the DI if it is in the UnfilteredPrimaryFlow.

        // Alt:
        // Create disBefore = getDisBeforeFirstDiInSourceMethod(..).
        // Create disAfter = getDisAfterLastDiInSourceMethod(..).
        // Potential Problem: what if we have a non-source-method DI in the middle? Or a sourceMethodDi in disBefore?

        final PrimaryFlow primaryFlow = determinePrimaryFlow(sourceMethod, mockedDIs);
        if(primaryFlow == null) {
            // This shouldn't happen.
            return new TestInfoImpl().withMethod(sourceMethod).withMockedDIs(mockedDIs);
        }
        final Set<String> primaryFlowDiKeysHit = primaryFlow.diKeysHit();
        final String expectedExceptionCanonicalName = primaryFlow.expectedExceptionCanonicalName();
        boolean hasEncounteredSourceMethodDi = false;
        final Api.FluentList<Api.DependencyInteraction> disHit = new FluentListImpl<>();
        for(final Api.DependencyInteraction di : mockedDIs) {
            final DependencyInteractionImpl diImpl = (DependencyInteractionImpl) di;
            final Api.Method sourceMethodContainingDi = diImpl.getSourceMethodContainingDi();
            if(sourceMethodContainingDi == sourceMethod) {
                hasEncounteredSourceMethodDi = true;
                // Use the primary flow info to determine if the DI is hit.
                if(!Collections.disjoint(diImpl.getDiKeys(), primaryFlowDiKeysHit)) {
                    disHit.add(di);
                }
                continue;
            }
            // The di is not in the source method.
            if(!hasEncounteredSourceMethodDi) {
                // We're in the DIs that come before the source method.
                // Add the DI as long as its in the unfiltered primary flow for its source method.
                if(isInPrimaryFlow(di)) {
                    disHit.add(di);
                }
                continue;
            }
            // We're in DIs that come after the sourceMethod.
            if(expectedExceptionCanonicalName == null && isInPrimaryFlow(di)) {
                disHit.add(di);
            }
        }
        final Api.TestInfo ret = new TestInfoImpl().withMethod(sourceMethod).withMockedDIs(disHit);
        // Determine return value.
        if(expectedExceptionCanonicalName != null) {
            final Api.Exception expectedException = exceptionCreator.createExceptionType(expectedExceptionCanonicalName);
            return ret.withExpectedException(expectedException);
        }
        final ReturnOutcome returnOutcome = primaryFlow.returnOutcome();
        updateReturnOutcomeIfPossible(ret, returnOutcome);
        return ret;
    }

    @Nullable
    private PrimaryFlow determinePrimaryFlow(final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs) {
        final MethodImpl method = (MethodImpl) sourceMethod;
        final Api.SourceClass containingClass = method.getContainingClass();
        if(containingClass == null) {
            // This shouldn't happen.
            return null;
        }
        final String key = containingClass.getName() + "_" + sourceMethod.getMethodKey();
        final UnfilteredPrimaryFlowInfo unfilteredPrimaryFlowInfo = outcomeInfo.getUnfilteredPrimaryFlowInfo(key);
        if(unfilteredPrimaryFlowInfo == null) {
            return null;
        }
        final List<Api.DependencyInteraction> mockedDisInSourceMethod = mockedDIs.stream().filter(x -> ((DependencyInteractionImpl) x).getSourceMethodContainingDi() == sourceMethod).collect(Collectors.toList());

        // Determine the filtered primary flow.
        final FilteredPrimaryFlowInfo filteredPrimaryFlowInfo = getFilteredPrimaryFlow(sourceMethod, unfilteredPrimaryFlowInfo, mockedDisInSourceMethod);
        if(filteredPrimaryFlowInfo == null) {
            return new PrimaryFlow(unfilteredPrimaryFlowInfo.diKeys(), unfilteredPrimaryFlowInfo.alwaysReturnedOutcome(), unfilteredPrimaryFlowInfo.alwaysThrownException());
        }
        final Set<String> filteredPrimaryFlowDis = filteredPrimaryFlowInfo.diKeys();

        // Determine which DIs are hit in either the filtered primary flow or alt flows.
        final Set<String> disCoveredByTestCases = new HashSet<>();
        disCoveredByTestCases.addAll(filteredPrimaryFlowDis);
        disCoveredByTestCases.addAll(getDiKeysWithPrimaryFlowValuesInAltFlows(sourceMethod, mockedDisInSourceMethod));

        // Determine the mockedDis that are in the unfiltered primary flow.
        final List<Api.DependencyInteraction> mockedDisInUnfilteredPrimaryFlow = getPrimaryFlowDis(unfilteredPrimaryFlowInfo, mockedDisInSourceMethod);

        // Determine if all of the unfiltered primary flow DIs are accounted for in at least one of the test cases
        // (filtered primary flow or alt flow).
        for(final Api.DependencyInteraction di : mockedDisInUnfilteredPrimaryFlow) {
            if(Collections.disjoint(((DependencyInteractionImpl) di).getDiKeys(), disCoveredByTestCases)) {
                // We have a primary flow DI that is not covered by the filtered primary flow or an alt flow.
                // Use the unfiltered primary flow.
                return new PrimaryFlow(unfilteredPrimaryFlowInfo.diKeys(), unfilteredPrimaryFlowInfo.alwaysReturnedOutcome(), unfilteredPrimaryFlowInfo.alwaysThrownException());
            }
        }

        // All unfiltered primary flow DIs are accounted for in at least one test case. Use the filtered primary flow.
        return new PrimaryFlow(filteredPrimaryFlowDis, filteredPrimaryFlowInfo.returnOutcome(), filteredPrimaryFlowInfo.expectedExceptionCanonicalName());
    }

    @Nullable
    private FilteredPrimaryFlowInfo getFilteredPrimaryFlow(
            final Api.Method sourceMethod, final UnfilteredPrimaryFlowInfo unfilteredPrimaryFlowInfo,
            final List<Api.DependencyInteraction> mockedDisInSourceMethod) {
        final Api.SourceClass containingClass = sourceMethod.getContainingClass();
        if(containingClass == null) {
            return null;
        }
        final String sourceMethodKey = containingClass.getName() + "_" + sourceMethod.getMethodKey();
        if(mockedDisInSourceMethod.isEmpty()) {
            return outcomeInfo.getFilteredNonDiPrimaryFlowInfo(sourceMethodKey);
        }
        final String firstDiKey = getFirstDiKey(unfilteredPrimaryFlowInfo, mockedDisInSourceMethod);
        if(firstDiKey == null) {
            return null;
        }
        final String filteredPrimaryFlowKey = sourceMethodKey + "_" + firstDiKey;
        return outcomeInfo.getFilteredPrimaryFlowInfo(filteredPrimaryFlowKey);
    }

    private String getFirstDiKey(
            final UnfilteredPrimaryFlowInfo unfilteredPrimaryFlowInfo,
            final List<Api.DependencyInteraction> mockedDisInSourceMethod) {
        for(final String diKey : unfilteredPrimaryFlowInfo.diKeys()) {
            for(final Api.DependencyInteraction di : mockedDisInSourceMethod) {
                if(((DependencyInteractionImpl) di).getDiKeys().contains(diKey)) {
                    return diKey;
                }
            }
        }
        return null;
    }

    private List<Api.DependencyInteraction> getPrimaryFlowDis(final UnfilteredPrimaryFlowInfo unfilteredPrimaryFlowInfo, final List<Api.DependencyInteraction> mockedDisInSourceMethod) {
        final Set<String> diKeys = unfilteredPrimaryFlowInfo.diKeys();
        final List<Api.DependencyInteraction> ret = new ArrayList<>();
        for(final Api.DependencyInteraction di : mockedDisInSourceMethod) {
            if(!Collections.disjoint(((DependencyInteractionImpl) di).getDiKeys(), diKeys)) {
                ret.add(di);
            }
        }
        return ret;
    }

    private Set<String> getDiKeysWithPrimaryFlowValuesInAltFlows(
            final Api.Method sourceMethod, final List<Api.DependencyInteraction> mockedDisInSourceMethod) {
        final Set<String> ret = new HashSet<>();
        for(final Api.DependencyInteraction di : mockedDisInSourceMethod) {
            final Api.Method diMethod = di.getMethod();
            final Api.Type diMethodReturnType = diMethod.getReturnType();
            if(diMethodReturnType != null) {
                if(diMethod.getReturnTypeCanBeAbsent()) {
                    final ReturnOutcome absentOutcome = determineStartingOutcome(di);
                    final AltFlowInfo altFlowInfo = tryGetAltFlowInfo(di, absentOutcome);
                    if(altFlowInfo != null) {
                        ret.addAll(getDiKeysWithPrimaryFlowValues(altFlowInfo));
                    }
                }
                if(diMethodReturnType.getEmptyInitExpression() != null) {
                    final AltFlowInfo altFlowInfo = tryGetAltFlowInfo(di, ReturnOutcome.Empty);
                    if(altFlowInfo != null) {
                        ret.addAll(getDiKeysWithPrimaryFlowValues(altFlowInfo));
                    }
                }
                if(diMethodReturnType.getFailureInitExpression() != null) {
                    final AltFlowInfo altFlowInfo = tryGetAltFlowInfo(di, ReturnOutcome.Failure);
                    if(altFlowInfo != null) {
                        ret.addAll(getDiKeysWithPrimaryFlowValues(altFlowInfo));
                    }
                }
                if(diMethodReturnType.getBrokenIoInitExpression() != null || diMethodReturnType.isDtoBeanWithInputIoProperty()) {
                    final AltFlowInfo altFlowInfo = tryGetAltFlowInfo(di, ReturnOutcome.BrokenIO);
                    if(altFlowInfo != null) {
                        ret.addAll(getDiKeysWithPrimaryFlowValues(altFlowInfo));
                    }
                }
            }
            final List<Api.Exception> allExceptions = new ArrayList<>();
            allExceptions.addAll(diMethod.getDeclaredExceptions());
            allExceptions.addAll(diMethod.getJavadocExceptions());
            allExceptions.addAll(diMethod.getUndeclaredExceptions());
            for(final Api.Exception exception : allExceptions) {
                final String altFlowKey = OutcomeKeyUtils.computeFinalMapKey(sourceMethod, di, exception.getCanonicalName());
                if(altFlowKey == null) {
                    continue;
                }
                final AltFlowInfo altFlowInfo = outcomeInfo.getExceptionAltFlowInfo(altFlowKey);
                if(altFlowInfo != null) {
                    ret.addAll(getDiKeysWithPrimaryFlowValues(altFlowInfo));
                }
            }
        }
        return ret;
    }

    private Set<String> getDiKeysWithPrimaryFlowValues(final AltFlowInfo altFlowInfo) {
        final Set<String> ret = new HashSet<>();
        for(final DiEntry diEntry : altFlowInfo.beforeInfo().diEntries()) {
            final ReturnOutcome returnOutcome = diEntry.returnOutcome();
            if(returnOutcome == ReturnOutcome.Other || returnOutcome == ReturnOutcome.Void) {
                ret.add(diEntry.diKey());
            }
        }
        ret.addAll(altFlowInfo.disHitAfter());
        return ret;
    }

    @NotNull
    private Set<String> getPrimaryFlowDiKeysThatAreHit(final Api.FluentList<Api.DependencyInteraction> mockedDIs) {
        final Set<String> diKeysThatAreHit = new HashSet<>();
        for(final Api.DependencyInteraction di : mockedDIs) {
            final Api.Method diSourceMethod = ((DependencyInteractionImpl) di).getSourceMethodContainingDi();
            if(diSourceMethod == null) {
                // This shouldn't happen.
                continue;
            }
            final Api.SourceClass diSourceMethodContainingClass = diSourceMethod.getContainingClass();
            if(diSourceMethodContainingClass == null) {
                // This shouldn't happen.
                continue;
            }
            final String currentDiKey = diSourceMethodContainingClass.getName() + "_" + diSourceMethod.getMethodKey();
            final UnfilteredPrimaryFlowInfo currentPrimaryFlowInfo = outcomeInfo.getUnfilteredPrimaryFlowInfo(currentDiKey);
            if(currentPrimaryFlowInfo == null) {
                continue;
            }
            diKeysThatAreHit.addAll(currentPrimaryFlowInfo.diKeys());
        }
        return diKeysThatAreHit;
    }

    @Override
    public Api.TestInfo fromExpectedException(
            final Api.Method sourceMethod, final Api.Exception expectedException) {
        return new TestInfoImpl().withMethod(sourceMethod).withExpectedException(expectedException);
    }

    @Override
    public Api.TestInfo fromExpectedException(
            final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs,
            final Api.Exception expectedException) {
        return new TestInfoImpl().withMethod(sourceMethod).withMockedDIs(mockedDIs).withExpectedException(expectedException);
    }

    @Override
    public Api.TestInfo fromParamWithEmptyIo(
            final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs,
            final Api.Variable paramWithEmptyIo) {
        return new TestInfoImpl().withMethod(sourceMethod).withMockedDIs(mockedDIs).withParamWithEmptyIo(paramWithEmptyIo);
    }

    @Override
    public Api.TestInfo fromParamWithBrokenIo(
            final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs,
            final Api.Variable paramWithBrokenIo) throws InvocationTargetException, IllegalAccessException {
        Api.Exception ioExceptionToUse = sourceMethod.getDeclaredExceptions().filter("type.canonicalName", "java.io.IOException").first();
        if(ioExceptionToUse == null) {
            ioExceptionToUse = sourceMethod.getDeclaredExceptions().first();
        }
        return new TestInfoImpl().withMethod(sourceMethod).withMockedDIs(mockedDIs).withParamWithBrokenIo(paramWithBrokenIo).withExpectedException(ioExceptionToUse);
    }

    @Override
    public Api.TestInfo fromDiToReturnAbsent(
            final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs,
            final Api.DependencyInteraction targetDi) throws InvocationTargetException, IllegalAccessException {
        initializeIfNeeded();
        final ReturnOutcome startingOutcome = determineStartingOutcome(targetDi);
        updateOverloadSuffixes(mockedDIs);
        if(startingOutcome == null) {
            return fromDiToReturnAbsentWithFallbackLogic(sourceMethod, mockedDIs, targetDi);
        }
        // Look up the control flow info for this test case.
        final AltFlowDecisionInfo altFlowDecisionInfo = tryGetAltFlowInfoOrFallback(targetDi, startingOutcome, getDefaultOutcome(targetDi));
        if(altFlowDecisionInfo == null) {
            return fromDiToReturnAbsentWithFallbackLogic(sourceMethod, mockedDIs, targetDi);
        }
        final AltFlowInfo altFlowInfo = altFlowDecisionInfo.getAltFlowInfo();
        final Api.Method sourceMethodContainingTargetDi = ((DependencyInteractionImpl) targetDi).getSourceMethodContainingDi();

        // Compute the test info.
        final Api.TestInfo ret = new TestInfoImpl().withMethod(sourceMethod);
        final Set<String> targetDiKeys = ((DependencyInteractionImpl) targetDi).getDiKeys();
        final Set<String> diKeysAlreadyAdded = new HashSet<>();
        final DiEntrySet diEntriesBefore = altFlowInfo.beforeInfo().diEntries();
        final Api.FluentList<Api.DependencyInteraction> disBefore = mockedDIs.itemsBefore(targetDi);
        for(final Api.DependencyInteraction diBefore : disBefore) {
            final Api.Method sourceMethodContainingDiBefore = ((DependencyInteractionImpl) diBefore).getSourceMethodContainingDi();
            final Set<String> currentDiKeys = ((DependencyInteractionImpl) diBefore).getDiKeys();
            if(!Collections.disjoint(currentDiKeys, diKeysAlreadyAdded)) {
                continue;
            }
            if(sourceMethodContainingDiBefore == sourceMethodContainingTargetDi) {
                final DiEntry diEntry = diEntriesBefore.getFirstEntry(currentDiKeys);
                if(diEntry == null) {
                    continue;
                }
                diKeysAlreadyAdded.addAll(currentDiKeys);
                addDiEntry(ret, diBefore, diEntry);
            } else {
                if(!Collections.disjoint(currentDiKeys, targetDiKeys)) {
                    // This DI call is the same as the target di. We need to stub it to return the same value.
                    // Also this case shouldn't happen.
                    continue;
                }
                if(isInPrimaryFlow(diBefore)) {
                    diKeysAlreadyAdded.addAll(currentDiKeys);
                    ret.addMockedDi(diBefore);
                }
            }
        }

        if(Collections.disjoint(targetDiKeys, diKeysAlreadyAdded)) {
            // Add the targetDi.
            diKeysAlreadyAdded.addAll(targetDiKeys);
            ret.withSubjectDi(targetDi);
            ret.addMockedDi(targetDi);
            ret.withDiToReturnAbsent(targetDi);
        }

        // Iterate through all DIs and apply the disHitAfter logic.
        final String expectedExceptionCanonicalName = altFlowInfo.expectedExceptionCanonicalName();
        final boolean hasExpectedException = expectedExceptionCanonicalName != null;
        final Set<String> disHitAfter = altFlowInfo.disHitAfter();
        for(final Api.DependencyInteraction di : mockedDIs) {
            final Api.Method sourceMethodContainingDi = ((DependencyInteractionImpl) di).getSourceMethodContainingDi();
            final Set<String> currentDiKeys = ((DependencyInteractionImpl) di).getDiKeys();
            if(!Collections.disjoint(currentDiKeys, diKeysAlreadyAdded)) {
                // We added this di earlier.
                continue;
            }
            if(sourceMethodContainingDi == sourceMethodContainingTargetDi) {
                if(Collections.disjoint(disHitAfter, currentDiKeys)) {
                    // The DI is not hit after.
                    continue;
                }
                diKeysAlreadyAdded.addAll(currentDiKeys);
                ret.addMockedDi(di);
            } else {
                if(hasExpectedException) {
                    continue;
                }
                if(isInPrimaryFlow(di)) {
                    diKeysAlreadyAdded.addAll(currentDiKeys);
                    ret.addMockedDi(di);
                }
            }
        }

        // Determine return value.
        if(expectedExceptionCanonicalName != null) {
            final Api.Exception expectedException = exceptionCreator.createExceptionType(expectedExceptionCanonicalName);
            return ret.withExpectedException(expectedException);
        }

        // If the return outcome is unknown or this is our fallback alt flow, use the fallback logic to determine the
        // return outcome.
        final ReturnOutcome returnOutcome = altFlowInfo.returnOutcome();
        if(altFlowDecisionInfo.isFallbackChoice() || returnOutcome == ReturnOutcome.Unknown) {
            final boolean expectedValueAbsent = OldTestCaseFactoryUtils.determineExpectedValueAbsent(sourceMethod, ret.getMockedDIs(), targetDi);
            if(expectedValueAbsent) {
                if(sourceMethod.isInferredNullable()) {
                    return ret.withExpectedValueNull(true);
                } else {
                    return ret.withExpectedValueAbsent(true);
                }
            }
            return ret;
        }
        updateReturnOutcomeIfPossible(ret, returnOutcome);
        return ret;
    }

    private boolean isInPrimaryFlow(final Api.DependencyInteraction diBefore) {
        final Api.Method sourceMethodContainingDi = ((DependencyInteractionImpl) diBefore).getSourceMethodContainingDi();
        final Api.SourceClass containingClass = sourceMethodContainingDi.getContainingClass();
        if(containingClass == null) {
            // This shouldn't happen.
            return true;
        }
        final String key = containingClass.getName() + "_" + sourceMethodContainingDi.getMethodKey();
        final UnfilteredPrimaryFlowInfo primaryFlowInfo = outcomeInfo.getUnfilteredPrimaryFlowInfo(key);
        if(primaryFlowInfo == null) {
            // This shouldn't happen.
            return true;
        }
        final Set<String> diBeforeKeys = ((DependencyInteractionImpl) diBefore).getDiKeys();
        final Set<String> primaryFlowInfoDiKeys = primaryFlowInfo.diKeys();
        return !Collections.disjoint(diBeforeKeys, primaryFlowInfoDiKeys);
    }

    private void addDiEntry(final Api.TestInfo ret, final Api.DependencyInteraction diBefore, final DiEntry diEntry) {
        final String exceptionToThrow = diEntry.exceptionToThrow();
        if(exceptionToThrow != null) {
            final Api.Exception expectedException = exceptionCreator.createExceptionType(exceptionToThrow);
            ret.addMockedDi(diBefore);
            ret.withDiThatThrows(diBefore, expectedException);
            return;
        }
        final ReturnOutcome returnOutcome = diEntry.returnOutcome();
        if(returnOutcome != null) {
            ret.addMockedDi(diBefore);
            if(returnOutcome == ReturnOutcome.Null || returnOutcome == ReturnOutcome.Absent) {
                ret.withDiToReturnAbsent(diBefore);
                return;
            }
            if(returnOutcome == ReturnOutcome.Empty) {
                ret.withDiToReturnEmpty(diBefore);
                return;
            }
            if(returnOutcome == ReturnOutcome.EmptyIO) {
                ret.withDiToReturnEmptyIo(diBefore);
                return;
            }
            if(returnOutcome == ReturnOutcome.BrokenIO) {
                ret.withDiToReturnBrokenIo(diBefore);
                return;
            }
            if(returnOutcome == ReturnOutcome.Failure) {
                ret.withDiToReturnFailure(diBefore);
            }
        }
    }

    private ReturnOutcome determineStartingOutcome(final Api.DependencyInteraction diToReturnAbsent) {
        final Api.Type returnType = diToReturnAbsent.getMethod().getReturnType();
        if(returnType == null) {
            return null;
        }
        final String canonicalName = returnType.getCanonicalName();
        if(canonicalName == null) {
            return null;
        }
        if(StringUtils.equalsAny(canonicalName, "java.util.Optional", "com.google.common.base.Optional")) {
            return ReturnOutcome.Absent;
        }
        final String absentInitExpression = returnType.getAbsentInitExpression();
        if(absentInitExpression == null) {
            return null;
        }
        if(absentInitExpression.equals("null")) {
            return ReturnOutcome.Null;
        }
        return ReturnOutcome.Absent;
    }

    private Api.TestInfo fromDiToReturnAbsentWithFallbackLogic(final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs, final Api.DependencyInteraction diToReturnAbsent) throws InvocationTargetException, IllegalAccessException {
        final boolean expectedValueAbsent = OldTestCaseFactoryUtils.determineExpectedValueAbsent(sourceMethod, mockedDIs, diToReturnAbsent);
        final Api.TestInfo ret = new TestInfoImpl().withMethod(sourceMethod).withMockedDIs(mockedDIs).withDiToReturnAbsent(diToReturnAbsent).withSubjectDi(diToReturnAbsent);
        if(expectedValueAbsent) {
            if(sourceMethod.isInferredNullable()) {
                ret.withExpectedValueNull(true);
            } else {
                ret.withExpectedValueAbsent(true);
            }
        }

        // Determine which DIs are hit.
        final Set<String> diKeysThatAreHit = getPrimaryFlowDiKeysThatAreHit(mockedDIs);
        final Api.FluentList<Api.DependencyInteraction> disHit = new FluentListImpl<>();
        for(final Api.DependencyInteraction di : mockedDIs) {
            if(di == diToReturnAbsent) {
                disHit.add(di);
                continue;
            }
            final String diKey = OutcomeKeyUtils.convertToDiKey(di);
            if(diKeysThatAreHit.contains(diKey)) {
                disHit.add(di);
            }
        }
        return ret.withMockedDIs(disHit);
    }

    @Override
    public Api.TestInfo fromDiToReturnEmpty(
            final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs,
            final Api.DependencyInteraction targetDi) throws InvocationTargetException, IllegalAccessException {
        initializeIfNeeded();
        updateOverloadSuffixes(mockedDIs);
        final AltFlowDecisionInfo altFlowDecisionInfo = tryGetAltFlowInfoOrFallback(targetDi, ReturnOutcome.Empty, getDefaultOutcome(targetDi));
        if(altFlowDecisionInfo == null) {
            return fromDiToReturnEmptyWithFallbackLogic(sourceMethod, mockedDIs, targetDi);
        }
        final AltFlowInfo altFlowInfo = altFlowDecisionInfo.getAltFlowInfo();
        final Api.Method sourceMethodContainingTargetDi = ((DependencyInteractionImpl) targetDi).getSourceMethodContainingDi();
        final Api.TestInfo ret = new TestInfoImpl().withMethod(sourceMethod);
        final Set<String> targetDiKeys = ((DependencyInteractionImpl) targetDi).getDiKeys();
        final Set<String> diKeysAlreadyAdded = new HashSet<>();
        final DiEntrySet diEntriesBefore = altFlowInfo.beforeInfo().diEntries();
        final Api.FluentList<Api.DependencyInteraction> disBefore = mockedDIs.itemsBefore(targetDi);
        for(final Api.DependencyInteraction diBefore : disBefore) {
            final Api.Method sourceMethodContainingDiBefore = ((DependencyInteractionImpl) diBefore).getSourceMethodContainingDi();
            final Set<String> currentDiKeys = ((DependencyInteractionImpl) diBefore).getDiKeys();
            if(!Collections.disjoint(currentDiKeys, diKeysAlreadyAdded)) {
                continue;
            }
            if(sourceMethodContainingDiBefore == sourceMethodContainingTargetDi) {
                final DiEntry diEntry = diEntriesBefore.getFirstEntry(currentDiKeys);
                if(diEntry == null) {
                    continue;
                }
                diKeysAlreadyAdded.addAll(currentDiKeys);
                addDiEntry(ret, diBefore, diEntry);
            } else {
                if(!Collections.disjoint(currentDiKeys, targetDiKeys)) {
                    // This DI call is the same as the target di. We need to stub it to return the same value.
                    // Also this case shouldn't happen.
                    continue;
                }
                if(isInPrimaryFlow(diBefore)) {
                    diKeysAlreadyAdded.addAll(currentDiKeys);
                    ret.addMockedDi(diBefore);
                }
            }
        }

        if(Collections.disjoint(targetDiKeys, diKeysAlreadyAdded)) {
            // Add the targetDi.
            diKeysAlreadyAdded.addAll(targetDiKeys);
            ret.withSubjectDi(targetDi);
            ret.addMockedDi(targetDi);
            ret.withDiToReturnEmpty(targetDi);
        }

        // Iterate through all DIs and apply the disHitAfter logic.
        final String expectedExceptionCanonicalName = altFlowInfo.expectedExceptionCanonicalName();
        final boolean hasExpectedException = expectedExceptionCanonicalName != null;
        final Set<String> disHitAfter = altFlowInfo.disHitAfter();
        for(final Api.DependencyInteraction di : mockedDIs) {
            final Api.Method sourceMethodContainingDi = ((DependencyInteractionImpl) di).getSourceMethodContainingDi();
            final Set<String> currentDiKeys = ((DependencyInteractionImpl) di).getDiKeys();
            if(!Collections.disjoint(currentDiKeys, diKeysAlreadyAdded)) {
                // We added this di earlier.
                continue;
            }
            if(sourceMethodContainingDi == sourceMethodContainingTargetDi) {
                if(Collections.disjoint(disHitAfter, currentDiKeys)) {
                    // The DI is not hit after.
                    continue;
                }
                diKeysAlreadyAdded.addAll(currentDiKeys);
                ret.addMockedDi(di);
            } else {
                if(hasExpectedException) {
                    continue;
                }
                if(isInPrimaryFlow(di)) {
                    diKeysAlreadyAdded.addAll(currentDiKeys);
                    ret.addMockedDi(di);
                }
            }
        }

        // Determine return value.
        if(expectedExceptionCanonicalName != null) {
            final Api.Exception expectedException = exceptionCreator.createExceptionType(expectedExceptionCanonicalName);
            return ret.withExpectedException(expectedException);
        }
        final ReturnOutcome returnOutcome = altFlowInfo.returnOutcome();
        if(altFlowDecisionInfo.isFallbackChoice() || returnOutcome == ReturnOutcome.Unknown) {
            final boolean expectedValueEmpty = OldTestCaseFactoryUtils.determineExpectedValueEmpty(sourceMethod, ret.getMockedDIs(), targetDi);
            return ret.withExpectedValueEmpty(expectedValueEmpty);
        }
        updateReturnOutcomeIfPossible(ret, returnOutcome);
        return ret;
    }

    private Api.TestInfo fromDiToReturnEmptyWithFallbackLogic(final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs, final Api.DependencyInteraction diToReturnEmpty) throws InvocationTargetException, IllegalAccessException {
        // Construct the TestInfo using heuristics (the old way).
        final boolean expectedValueEmpty = OldTestCaseFactoryUtils.determineExpectedValueEmpty(sourceMethod, mockedDIs, diToReturnEmpty);
        final Api.TestInfo ret = new TestInfoImpl().withMethod(sourceMethod).withMockedDIs(mockedDIs).withDiToReturnEmpty(diToReturnEmpty).withSubjectDi(diToReturnEmpty).withExpectedValueEmpty(expectedValueEmpty);

        // Determine which DIs are hit using control flow analysis (the new way).
        final MethodImpl method = (MethodImpl) sourceMethod;
        final Api.SourceClass containingClass = method.getContainingClass();
        if(containingClass == null) {
            // This shouldn't happen.
            return ret;
        }
        final Set<String> diKeysThatAreHit = getPrimaryFlowDiKeysThatAreHit(mockedDIs);
        final Api.FluentList<Api.DependencyInteraction> disHit = new FluentListImpl<>();
        for(final Api.DependencyInteraction di : mockedDIs) {
            if(di == diToReturnEmpty) {
                disHit.add(di);
                continue;
            }
            final String diKey = OutcomeKeyUtils.convertToDiKey(di);
            if(diKeysThatAreHit.contains(diKey)) {
                disHit.add(di);
            }
        }
        return ret.withMockedDIs(disHit);
    }

    @Override
    public Api.TestInfo fromDiToReturnEmptyIo(
            final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs,
            final Api.DependencyInteraction targetDi) {
        initializeIfNeeded();
        updateOverloadSuffixes(mockedDIs);
        final AltFlowDecisionInfo altFlowDecisionInfo = tryGetAltFlowInfoOrFallback(targetDi, ReturnOutcome.EmptyIO, getDefaultOutcome(targetDi));
        if(altFlowDecisionInfo == null) {
            return fromDiToReturnEmptyIoWithFallbackLogic(sourceMethod, mockedDIs, targetDi);
        }
        final AltFlowInfo altFlowInfo = altFlowDecisionInfo.getAltFlowInfo();
        final Api.Method sourceMethodContainingTargetDi = ((DependencyInteractionImpl) targetDi).getSourceMethodContainingDi();
        final Api.TestInfo ret = new TestInfoImpl().withMethod(sourceMethod);
        final Set<String> targetDiKeys = ((DependencyInteractionImpl) targetDi).getDiKeys();
        final Set<String> diKeysAlreadyAdded = new HashSet<>();
        final DiEntrySet diEntriesBefore = altFlowInfo.beforeInfo().diEntries();
        final Api.FluentList<Api.DependencyInteraction> disBefore = mockedDIs.itemsBefore(targetDi);
        for(final Api.DependencyInteraction diBefore : disBefore) {
            final Api.Method sourceMethodContainingDiBefore = ((DependencyInteractionImpl) diBefore).getSourceMethodContainingDi();
            final Set<String> currentDiKeys = ((DependencyInteractionImpl) diBefore).getDiKeys();
            if(!Collections.disjoint(currentDiKeys, diKeysAlreadyAdded)) {
                continue;
            }
            if(sourceMethodContainingDiBefore == sourceMethodContainingTargetDi) {
                final DiEntry diEntry = diEntriesBefore.getFirstEntry(currentDiKeys);
                if(diEntry == null) {
                    continue;
                }
                diKeysAlreadyAdded.addAll(currentDiKeys);
                addDiEntry(ret, diBefore, diEntry);
            } else {
                if(!Collections.disjoint(currentDiKeys, targetDiKeys)) {
                    // This DI call is the same as the target di. We need to stub it to return the same value.
                    // Also this case shouldn't happen.
                    continue;
                }
                if(isInPrimaryFlow(diBefore)) {
                    diKeysAlreadyAdded.addAll(currentDiKeys);
                    ret.addMockedDi(diBefore);
                }
            }
        }

        if(Collections.disjoint(targetDiKeys, diKeysAlreadyAdded)) {
            // Add the targetDi.
            diKeysAlreadyAdded.addAll(targetDiKeys);
            ret.withSubjectDi(targetDi);
            ret.addMockedDi(targetDi);
            ret.withDiToReturnEmptyIo(targetDi);
        }

        // Iterate through all DIs and apply the disHitAfter logic.
        final String expectedExceptionCanonicalName = altFlowInfo.expectedExceptionCanonicalName();
        final boolean hasExpectedException = expectedExceptionCanonicalName != null;
        final Set<String> disHitAfter = altFlowInfo.disHitAfter();
        for(final Api.DependencyInteraction di : mockedDIs) {
            final Api.Method sourceMethodContainingDi = ((DependencyInteractionImpl) di).getSourceMethodContainingDi();
            final Set<String> currentDiKeys = ((DependencyInteractionImpl) di).getDiKeys();
            if(!Collections.disjoint(currentDiKeys, diKeysAlreadyAdded)) {
                // We added this di earlier.
                continue;
            }
            if(sourceMethodContainingDi == sourceMethodContainingTargetDi) {
                if(Collections.disjoint(disHitAfter, currentDiKeys)) {
                    // The DI is not hit after.
                    continue;
                }
                diKeysAlreadyAdded.addAll(currentDiKeys);
                ret.addMockedDi(di);
            } else {
                if(hasExpectedException) {
                    continue;
                }
                if(isInPrimaryFlow(di)) {
                    diKeysAlreadyAdded.addAll(currentDiKeys);
                    ret.addMockedDi(di);
                }
            }
        }

        // Determine return value.
        if(expectedExceptionCanonicalName != null) {
            final Api.Exception expectedException = exceptionCreator.createExceptionType(expectedExceptionCanonicalName);
            return ret.withExpectedException(expectedException);
        }
        final ReturnOutcome returnOutcome = altFlowInfo.returnOutcome();
        updateReturnOutcomeIfPossible(ret, returnOutcome);
        return ret;
    }

    private Api.TestInfo fromDiToReturnEmptyIoWithFallbackLogic(final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs, final Api.DependencyInteraction diToReturnEmptyIo) {
        return new TestInfoImpl().withMethod(sourceMethod).withMockedDIs(mockedDIs).withDiToReturnEmptyIo(diToReturnEmptyIo).withSubjectDi(diToReturnEmptyIo);
    }

    @Override
    public Api.TestInfo fromDiToReturnBrokenIo(
            final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs,
            final Api.DependencyInteraction targetDi) throws InvocationTargetException, IllegalAccessException {
        initializeIfNeeded();
        updateOverloadSuffixes(mockedDIs);
        final Api.Method sourceMethodContainingTargetDi = ((DependencyInteractionImpl) targetDi).getSourceMethodContainingDi();
        final String altFlowKey = OutcomeKeyUtils.computeFinalMapKeyForStartingOutcome(sourceMethodContainingTargetDi, targetDi, ReturnOutcome.BrokenIO);
        if(altFlowKey == null) {
            return fromDiToReturnBrokenIoWithFallbackLogic(sourceMethod, mockedDIs, targetDi);
        }
        final AltFlowInfo altFlowInfo = outcomeInfo.getValueAltFlowInfo(altFlowKey);
        if(altFlowInfo == null) {
            return fromDiToReturnBrokenIoWithFallbackLogic(sourceMethod, mockedDIs, targetDi);
        }
        final Api.TestInfo ret = new TestInfoImpl().withMethod(sourceMethod);
        final Set<String> targetDiKeys = ((DependencyInteractionImpl) targetDi).getDiKeys();
        final Set<String> diKeysAlreadyAdded = new HashSet<>();
        final DiEntrySet diEntriesBefore = altFlowInfo.beforeInfo().diEntries();
        final Api.FluentList<Api.DependencyInteraction> disBefore = mockedDIs.itemsBefore(targetDi);
        for(final Api.DependencyInteraction diBefore : disBefore) {
            final Api.Method sourceMethodContainingDiBefore = ((DependencyInteractionImpl) diBefore).getSourceMethodContainingDi();
            final Set<String> currentDiKeys = ((DependencyInteractionImpl) diBefore).getDiKeys();
            if(!Collections.disjoint(currentDiKeys, diKeysAlreadyAdded)) {
                continue;
            }
            if(sourceMethodContainingDiBefore == sourceMethodContainingTargetDi) {
                final DiEntry diEntry = diEntriesBefore.getFirstEntry(currentDiKeys);
                if(diEntry == null) {
                    continue;
                }
                diKeysAlreadyAdded.addAll(currentDiKeys);
                addDiEntry(ret, diBefore, diEntry);
            } else {
                if(!Collections.disjoint(currentDiKeys, targetDiKeys)) {
                    // This DI call is the same as the target di. We need to stub it to return the same value.
                    // Also this case shouldn't happen.
                    continue;
                }
                if(isInPrimaryFlow(diBefore)) {
                    diKeysAlreadyAdded.addAll(currentDiKeys);
                    ret.addMockedDi(diBefore);
                }
            }
        }

        if(Collections.disjoint(targetDiKeys, diKeysAlreadyAdded)) {
            // Add the targetDi.
            diKeysAlreadyAdded.addAll(targetDiKeys);
            ret.withSubjectDi(targetDi);
            ret.addMockedDi(targetDi);
            ret.withDiToReturnBrokenIo(targetDi);
        }

        // Iterate through all DIs and apply the disHitAfter logic.
        final String expectedExceptionCanonicalName = altFlowInfo.expectedExceptionCanonicalName();
        final boolean hasExpectedException = expectedExceptionCanonicalName != null;
        final Set<String> disHitAfter = altFlowInfo.disHitAfter();
        for(final Api.DependencyInteraction di : mockedDIs) {
            final Api.Method sourceMethodContainingDi = ((DependencyInteractionImpl) di).getSourceMethodContainingDi();
            final Set<String> currentDiKeys = ((DependencyInteractionImpl) di).getDiKeys();
            if(!Collections.disjoint(currentDiKeys, diKeysAlreadyAdded)) {
                // We added this di earlier.
                continue;
            }
            if(sourceMethodContainingDi == sourceMethodContainingTargetDi) {
                if(Collections.disjoint(disHitAfter, currentDiKeys)) {
                    // The DI is not hit after.
                    continue;
                }
                diKeysAlreadyAdded.addAll(currentDiKeys);
                ret.addMockedDi(di);
            } else {
                if(hasExpectedException) {
                    continue;
                }
                if(isInPrimaryFlow(di)) {
                    diKeysAlreadyAdded.addAll(currentDiKeys);
                    ret.addMockedDi(di);
                }
            }
        }

        // Determine return value.
        if(expectedExceptionCanonicalName != null) {
            final Api.Exception expectedException = exceptionCreator.createExceptionType(expectedExceptionCanonicalName);
            return ret.withExpectedException(expectedException);
        }
        final ReturnOutcome returnOutcome = altFlowInfo.returnOutcome();
        updateReturnOutcomeIfPossible(ret, returnOutcome);
        return ret;
    }

    private Api.TestInfo fromDiToReturnBrokenIoWithFallbackLogic(final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs, final Api.DependencyInteraction diToReturnBrokenIo) throws InvocationTargetException, IllegalAccessException {
        Api.Exception ioExceptionToUse = sourceMethod.getDeclaredExceptions().filter("type.canonicalName", "java.io.IOException").first();
        if(ioExceptionToUse == null) {
            ioExceptionToUse = sourceMethod.getDeclaredExceptions().first();
        }
        return new TestInfoImpl().withMethod(sourceMethod).withMockedDIs(mockedDIs).withDiToReturnBrokenIo(diToReturnBrokenIo).withExpectedException(ioExceptionToUse).withSubjectDi(diToReturnBrokenIo);
    }

    @Nullable
    private AltFlowInfo tryGetAltFlowInfo(final Api.DependencyInteraction targetDi, final ReturnOutcome returnOutcome) {
        final AltFlowDecisionInfo ret = tryGetAltFlowInfoOrFallback(targetDi, returnOutcome);
        if(ret == null) {
            return null;
        }
        return ret.getAltFlowInfo();
    }

    @Nullable
    private AltFlowDecisionInfo tryGetAltFlowInfoOrFallback(final Api.DependencyInteraction targetDi, final ReturnOutcome... returnOutcomes) {
        final Api.Method sourceMethodContainingTargetDi = ((DependencyInteractionImpl) targetDi).getSourceMethodContainingDi();
        for(int i = 0; i < returnOutcomes.length; i++) {
            final ReturnOutcome returnOutcome = returnOutcomes[i];
            final String altFlowKey = OutcomeKeyUtils.computeFinalMapKeyForStartingOutcome(sourceMethodContainingTargetDi, targetDi, returnOutcome);
            if(altFlowKey != null) {
                final AltFlowInfo altFlowInfo = outcomeInfo.getValueAltFlowInfo(altFlowKey);
                if(altFlowInfo != null) {
                    return new AltFlowDecisionInfo(altFlowInfo, i != 0);
                }
            }
        }
        return null;
    }

    @Override
    public Api.TestInfo fromDiToReturnFailure(
            final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs,
            final Api.DependencyInteraction targetDi) {
        initializeIfNeeded();
        updateOverloadSuffixes(mockedDIs);
        final AltFlowDecisionInfo altFlowDecisionInfo = tryGetAltFlowInfoOrFallback(targetDi, ReturnOutcome.Failure, getDefaultOutcome(targetDi));
        if(altFlowDecisionInfo == null) {
            return fromDiToReturnFailureWithFallbackLogic(sourceMethod, mockedDIs, targetDi);
        }
        final AltFlowInfo altFlowInfo = altFlowDecisionInfo.getAltFlowInfo();
        final Api.Method sourceMethodContainingTargetDi = ((DependencyInteractionImpl) targetDi).getSourceMethodContainingDi();
        final Api.TestInfo ret = new TestInfoImpl().withMethod(sourceMethod);
        final Set<String> targetDiKeys = ((DependencyInteractionImpl) targetDi).getDiKeys();
        final Set<String> diKeysAlreadyAdded = new HashSet<>();
        final DiEntrySet diEntriesBefore = altFlowInfo.beforeInfo().diEntries();
        final Api.FluentList<Api.DependencyInteraction> disBefore = mockedDIs.itemsBefore(targetDi);
        for(final Api.DependencyInteraction diBefore : disBefore) {
            final Api.Method sourceMethodContainingDiBefore = ((DependencyInteractionImpl) diBefore).getSourceMethodContainingDi();
            final Set<String> currentDiKeys = ((DependencyInteractionImpl) diBefore).getDiKeys();
            if(!Collections.disjoint(currentDiKeys, diKeysAlreadyAdded)) {
                continue;
            }
            if(sourceMethodContainingDiBefore == sourceMethodContainingTargetDi) {
                final DiEntry diEntry = diEntriesBefore.getFirstEntry(currentDiKeys);
                if(diEntry == null) {
                    continue;
                }
                diKeysAlreadyAdded.addAll(currentDiKeys);
                addDiEntry(ret, diBefore, diEntry);
            } else {
                if(!Collections.disjoint(currentDiKeys, targetDiKeys)) {
                    // This DI call is the same as the target di. We need to stub it to return the same value.
                    // Also this case shouldn't happen.
                    continue;
                }
                if(isInPrimaryFlow(diBefore)) {
                    diKeysAlreadyAdded.addAll(currentDiKeys);
                    ret.addMockedDi(diBefore);
                }
            }
        }

        if(Collections.disjoint(targetDiKeys, diKeysAlreadyAdded)) {
            // Add the targetDi.
            diKeysAlreadyAdded.addAll(targetDiKeys);
            ret.withSubjectDi(targetDi);
            ret.addMockedDi(targetDi);
            ret.withDiToReturnFailure(targetDi);
        }

        // Iterate through all DIs and apply the disHitAfter logic.
        final String expectedExceptionCanonicalName = altFlowInfo.expectedExceptionCanonicalName();
        final boolean hasExpectedException = expectedExceptionCanonicalName != null;
        final Set<String> disHitAfter = altFlowInfo.disHitAfter();
        for(final Api.DependencyInteraction di : mockedDIs) {
            final Api.Method sourceMethodContainingDi = ((DependencyInteractionImpl) di).getSourceMethodContainingDi();
            final Set<String> currentDiKeys = ((DependencyInteractionImpl) di).getDiKeys();
            if(!Collections.disjoint(currentDiKeys, diKeysAlreadyAdded)) {
                // We added this di earlier.
                continue;
            }
            if(sourceMethodContainingDi == sourceMethodContainingTargetDi) {
                if(Collections.disjoint(disHitAfter, currentDiKeys)) {
                    // The DI is not hit after.
                    continue;
                }
                diKeysAlreadyAdded.addAll(currentDiKeys);
                ret.addMockedDi(di);
            } else {
                if(hasExpectedException) {
                    continue;
                }
                if(isInPrimaryFlow(di)) {
                    diKeysAlreadyAdded.addAll(currentDiKeys);
                    ret.addMockedDi(di);
                }
            }
        }

        // Determine return value.
        if(expectedExceptionCanonicalName != null) {
            final Api.Exception expectedException = exceptionCreator.createExceptionType(expectedExceptionCanonicalName);
            return ret.withExpectedException(expectedException);
        }
        final ReturnOutcome returnOutcome = altFlowInfo.returnOutcome();
        updateReturnOutcomeIfPossible(ret, returnOutcome);
        return ret;
    }

    private static ReturnOutcome getDefaultOutcome(final Api.DependencyInteraction di) {
        return getDefaultOutcome(di.getMethod());
    }

    private static ReturnOutcome getDefaultOutcome(final Api.Method sourceMethodContainingTargetDi) {
        if(sourceMethodContainingTargetDi.getReturnType() == null) {
            return ReturnOutcome.Void;
        }
        return ReturnOutcome.Other;
    }

    private static Api.TestInfo fromDiToReturnFailureWithFallbackLogic(final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs, final Api.DependencyInteraction diToReturnFailure) {
        final Api.FluentList<Api.DependencyInteraction> mockedDIsThatAreHit = new FluentListImpl<>(mockedDIs.size());
        mockedDIsThatAreHit.addAll(mockedDIs.itemsBefore(diToReturnFailure));
        mockedDIsThatAreHit.add(diToReturnFailure);
        mockedDIsThatAreHit.addAll(mockedDIs.itemsAfter(diToReturnFailure).filterOutItemsWithSameSourceVar(diToReturnFailure));
        return new TestInfoImpl().withMethod(sourceMethod).withMockedDIs(mockedDIsThatAreHit).withDiToReturnFailure(diToReturnFailure).withSubjectDi(diToReturnFailure);
    }

    @Override
    public Api.TestInfo fromDiThatThrows(
            final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs,
            final Api.DependencyInteraction targetDi, final Api.Exception diExceptionToThrow) throws InvocationTargetException, IllegalAccessException {
        initializeIfNeeded();
        updateOverloadSuffixes(mockedDIs);
        final Api.Method sourceMethodContainingTargetDi = ((DependencyInteractionImpl) targetDi).getSourceMethodContainingDi();
        final String altFlowKey = OutcomeKeyUtils.computeFinalMapKey(sourceMethodContainingTargetDi, targetDi, diExceptionToThrow);
        if(altFlowKey == null) {
            return fromDiThatThrowsOldImpl(sourceMethod, mockedDIs, targetDi, diExceptionToThrow);
        }
        final AltFlowInfo altFlowInfo = outcomeInfo.getExceptionAltFlowInfo(altFlowKey);
        if(altFlowInfo == null) {
            return fromDiThatThrowsOldImpl(sourceMethod, mockedDIs, targetDi, diExceptionToThrow);
        }
        final Api.TestInfo ret = new TestInfoImpl().withMethod(sourceMethod);
        final Set<String> targetDiKeys = ((DependencyInteractionImpl) targetDi).getDiKeys();
        final Set<String> diKeysAlreadyAdded = new HashSet<>();
        final DiEntrySet diEntriesBefore = altFlowInfo.beforeInfo().diEntries();
        final Api.FluentList<Api.DependencyInteraction> disBefore = mockedDIs.itemsBefore(targetDi);
        for(final Api.DependencyInteraction diBefore : disBefore) {
            final Api.Method sourceMethodContainingDiBefore = ((DependencyInteractionImpl) diBefore).getSourceMethodContainingDi();
            final Set<String> currentDiKeys = ((DependencyInteractionImpl) diBefore).getDiKeys();
            if(!Collections.disjoint(currentDiKeys, diKeysAlreadyAdded)) {
                continue;
            }
            if(sourceMethodContainingDiBefore == sourceMethodContainingTargetDi) {
                final DiEntry diEntry = diEntriesBefore.getFirstEntry(currentDiKeys);
                if(diEntry == null) {
                    continue;
                }
                diKeysAlreadyAdded.addAll(currentDiKeys);
                addDiEntry(ret, diBefore, diEntry);
            } else {
                if(!Collections.disjoint(currentDiKeys, targetDiKeys)) {
                    // This DI call is the same as the target di. We need to stub it to return the same value.
                    // Also this case shouldn't happen.
                    continue;
                }
                if(isInPrimaryFlow(diBefore)) {
                    diKeysAlreadyAdded.addAll(currentDiKeys);
                    ret.addMockedDi(diBefore);
                }
            }
        }

        if(Collections.disjoint(targetDiKeys, diKeysAlreadyAdded)) {
            // Add the targetDi.
            diKeysAlreadyAdded.addAll(targetDiKeys);
            ret.withSubjectDi(targetDi);
            ret.addMockedDi(targetDi);
            ret.withDiThatThrows(targetDi, diExceptionToThrow);
        }

        // Iterate through all DIs and apply the disHitAfter logic.
        final String expectedExceptionCanonicalName = altFlowInfo.expectedExceptionCanonicalName();
        final boolean hasExpectedException = expectedExceptionCanonicalName != null;
        final Set<String> disHitAfter = altFlowInfo.disHitAfter();
        for(final Api.DependencyInteraction di : mockedDIs) {
            final Api.Method sourceMethodContainingDi = ((DependencyInteractionImpl) di).getSourceMethodContainingDi();
            final Set<String> currentDiKeys = ((DependencyInteractionImpl) di).getDiKeys();
            if(!Collections.disjoint(currentDiKeys, diKeysAlreadyAdded)) {
                // We added this di earlier.
                continue;
            }
            if(sourceMethodContainingDi == sourceMethodContainingTargetDi) {
                if(Collections.disjoint(disHitAfter, currentDiKeys)) {
                    // The DI is not hit after.
                    continue;
                }
                diKeysAlreadyAdded.addAll(currentDiKeys);
                ret.addMockedDi(di);
            } else {
                if(hasExpectedException) {
                    continue;
                }
                if(isInPrimaryFlow(di)) {
                    diKeysAlreadyAdded.addAll(currentDiKeys);
                    ret.addMockedDi(di);
                }
            }
        }

        // Determine return value.
        if(expectedExceptionCanonicalName != null) {
            final Api.Exception expectedException = exceptionCreator.createExceptionType(expectedExceptionCanonicalName);
            return ret.withExpectedException(expectedException);
        }
        final ReturnOutcome returnOutcome = altFlowInfo.returnOutcome();
        updateReturnOutcomeIfPossible(ret, returnOutcome);
        return ret;
    }

    @Override
    public void updateDependencies(final List<? extends Api.Variable> dependencies) {
        outcomeInfo = outcomeProvider.computeOutcomeInfo(dependencies);
    }

    private Api.TestInfo fromDiThatThrowsOldImpl(final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs, final Api.DependencyInteraction diThatThrows, final Api.Exception diExceptionToThrow) throws InvocationTargetException, IllegalAccessException {
        initializeIfNeeded();
        Api.Exception expectedException = null;
        final Api.FluentList<? extends Api.Exception> allSourceMethodExceptions = sourceMethod.getDeclaredExceptions().union(sourceMethod.getJavadocExceptions(), "type.canonicalName");
        if(allSourceMethodExceptions.containsAnyWithItem("type.canonicalName", diExceptionToThrow.getType().getAllNames())) {
            expectedException = diExceptionToThrow;
        }
        final Api.FluentList<Api.DependencyInteraction> mockedDIsThatAreHit = new FluentListImpl<>(mockedDIs.size());
        mockedDIsThatAreHit.addAll(mockedDIs.itemsBefore(diThatThrows));
        mockedDIsThatAreHit.add(diThatThrows);
        mockedDIsThatAreHit.addAll(mockedDIs.itemsAfter(diThatThrows).filterOutItemsWithSameSourceVar(diThatThrows));
        return new TestInfoImpl().withMethod(sourceMethod).withMockedDIs(mockedDIsThatAreHit).withDiThatThrows(diThatThrows, diExceptionToThrow).withExpectedException(expectedException).withSubjectDi(diThatThrows);
    }

    private void updateReturnOutcomeIfPossible(final Api.TestInfo ret, final ReturnOutcome returnOutcome) {
        final Api.Type sourceMethodReturnType = ret.getMethod().getReturnType();
        if(sourceMethodReturnType == null) {
            return;
        }
        if(returnOutcome != null) {
            if(returnOutcome == ReturnOutcome.Null) {
                ret.withExpectedValueNull(true);
                return;
            } else if(returnOutcome == ReturnOutcome.Absent) {
                if(sourceMethodReturnType.getAbsentInitExpression() != null) {
                    ret.withExpectedValueAbsent(true);
                }
                return;
            }
            if(returnOutcome == ReturnOutcome.Empty) {
                if(sourceMethodReturnType.getEmptyInitExpression() != null) {
                    ret.withExpectedValueEmpty(true);
                }
                return;
            }
            if(returnOutcome == ReturnOutcome.True) {
                if(sourceMethodReturnType.isAny("boolean", "java.lang.Boolean")) {
                    ret.withExpectedValueTrue(true);
                }
            }
        }
    }

    private void updateOverloadSuffixes(final Api.FluentList<Api.DependencyInteraction> mockedDIs) {
        for(final Api.DependencyInteraction mockedDi : mockedDIs) {
            ((DependencyInteractionImpl) mockedDi).setOverloadSuffix("");
        }
        final MultiValuedMap<ClassMemberAndMethodName, Api.DependencyInteraction> duplicateOverloadsMap = new ArrayListValuedHashMap<>();
        for(final Api.DependencyInteraction mockedDi : mockedDIs) {
            final ClassMemberAndMethodName key = new ClassMemberAndMethodName(mockedDi.getField(), mockedDi.getMethod().getName());
            duplicateOverloadsMap.put(key, mockedDi);
        }
        for(final ClassMemberAndMethodName key : duplicateOverloadsMap.keySet()) {
            final Collection<Api.DependencyInteraction> disWithSameKey = duplicateOverloadsMap.get(key);
            if(disWithSameKey.size() == 1) {
                continue;
            }
            int count = 1;
            for(final Api.DependencyInteraction di : disWithSameKey) {
                ((DependencyInteractionImpl) di).setOverloadSuffix(Integer.toString(count++));
            }
        }
    }
}
