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
package com.squaretest.generation.dependencyinteraction.outcomes;

import com.intellij.psi.*;
import com.intellij.psi.util.InheritanceUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtil;
import com.squaretest.generation.NullabilityDecider;
import com.squaretest.generation.NullabilityStatus;
import com.squaretest.generation.defaulttypes.DefaultTypeInfo;
import com.squaretest.generation.defaulttypes.DefaultTypesHolder;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.defaulttypes.TypeCreatorUtil;
import com.squaretest.generation.dependencyinteraction.DependencyInteractionCollectorUtils;
import com.squaretest.generation.dependencyinteraction.DependencyInteractionSet;
import com.squaretest.generation.dependencyinteraction.DiAndNode;
import com.squaretest.generation.dependencyinteraction.DiAndNodeSet;
import com.squaretest.generation.dependencyinteraction.InternalDependencyInteraction;
import com.squaretest.generation.dependencyinteraction.Node;
import com.squaretest.generation.dependencyinteraction.RegexUtils;
import com.squaretest.generation.dependencyinteraction.followup.FollowupInfoProvider;
import com.squaretest.generation.dependencyinteraction.followup.RValueInfo;
import com.squaretest.generation.dependencyinteraction.followup.StatementStepInfo;
import com.squaretest.generation.exceptions.PsiExceptionProvider;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;

import static com.squaretest.generation.defaulttypes.AltIoExpressionPopulator.CloseableTypeCanonicalNames;
import static com.squaretest.generation.defaulttypes.AltIoExpressionPopulator.IOTypeCanonicalNames;
import static com.squaretest.generation.dependencyinteraction.DependencyInteractionCollectorUtils.isAsyncType;
import static com.squaretest.generation.dependencyinteraction.followup.FollowupInfoProvider.getNextSequentialStatement;
import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.*;
import static com.squaretest.generation.dependencyinteraction.outcomes.OutcomeKeyUtils.convertToDiKey;

public class OutcomePopulator {

    private static final Set<String> AbsentMethodNames = Set.of("absent", "empty");

    private static final Set<String> AsyncClasses = Set.of(
            "java.util.concurrent.Executor",
            "java.util.concurrent.CompletableFuture",
            "java.util.concurrent.CompletionStage",
            "com.google.common.util.concurrent.ListenableFuture",
            // If we have a subclass of Future that takes in a lambda or anon inner class, assume it is async.
            // This will handle any forks of ListenableFuture; e.g. com.google.api.core.ApiFuture.
            "java.util.concurrent.Future",
            "io.reactivex.Scheduler",
            "io.reactivex.rxjava3.core.Scheduler",
            "io.reactivex.ObservableSource",
            "io.reactivex.rxjava3.core.ObservableSource",
            "org.reactivestreams.Publisher"
    );
    private static final Set<ReturnOutcome> RefinedValues = EnumSet.of(
            ReturnOutcome.Absent, ReturnOutcome.Empty, ReturnOutcome.Null,
            ReturnOutcome.True, ReturnOutcome.False, ReturnOutcome.Zero, ReturnOutcome.FutureWithNull,
            ReturnOutcome.Failure);
    private static final Set<String> FailureFactoryMethodNames = Set.of("failedFuture", "failedStage");
    private static final Set<String> ClassesWithSupportedFailureValues = Set.of("software.amazon.awssdk.enhanced.dynamodb.model.BatchWriteResult", "software.amazon.awssdk.services.dynamodb.model.BatchWriteItemResponse", "com.amazonaws.services.dynamodbv2.model.BatchWriteItemResult");

    private static final List<Pattern> MethodNamesWhereReturnTrueIsNormalRegexes = List.of(
            // isValid
            // isAllowed
            // isAuthorized
            // isAuthorised
            // isAble
            // isValidFooData
            // isAllowedToConnect
            // isAuthorizedToCreate
            // isAuthorisedToCreate
            // isAbleToContinue
            Pattern.compile("^(is)(Valid|Allowed|Authori[zs]ed|Able).*$"),

            // hasPermission
            // hasPermissionToCreate
            // hasPermissions
            // hasAccess
            // hasValid
            // hasValidCredentials
            Pattern.compile("^(has)(Permission|Access|Valid).*$"));

    @NotNull
    private final PsiExceptionProvider psiExceptionProvider;
    @NotNull
    private final FollowupInfoProvider followupInfoProvider;
    @NotNull
    private final NullabilityDecider nullabilityDecider;
    @NotNull
    private final DefaultTypesHolder defaultTypesHolder;
    @NotNull
    private final JavaPsiFacade javaPsiFacade;
    @NotNull
    private final PsiClass sourceClass;
    @NotNull
    private final Map<PsiMethod, DependencyInteractionSet> sourceMethodToDisMap;
    @NotNull
    private final MethodPathsInfoProvider methodPathsInfoProvider;
    @Nullable
    private final DepConfig depConfig;
    @NotNull
    private final Map<PsiMethod, DiAndNodeSet> sourceMethodToFlatDisMap;
    @NotNull
    private final Set<PsiMethod> allSourceClassMethods;
    @NotNull
    private final Map<CacheKey, CacheValue> cachedValues;
    @NotNull
    private final Map<PathKey, NodeStep> nodePathMapWithPreserveUnknownFalse;
    @NotNull
    private final Map<PathKey, NodeStep> nodePathMapWithPreserveUnknownBoth;
    @NotNull
    private final Map<DiAndNode, NodeStep> nodeToPathThatDoesNotThrowWithPreserveUnknownFalseMap;
    @NotNull
    private final Map<DiAndNode, NodeStep> nodeToPathThatDoesNotThrowWithPreserveUnknownBothMap;
    @NotNull
    private final Map<DiAndNode, NodeStep> nodeToPathThatDoesNotReturnAltValueWithPreserveUnknownFalseMap;
    @NotNull
    private final Map<DiAndNode, NodeStep> nodeToPathThatDoesNotReturnAltValueWithPreserveUnknownBothMap;
    @NotNull
    private final Map<DiAndNode, BeforeInfo> nodeToBeforeInfoCache;
    @NotNull
    private final Map<MethodAndDi, BooleanInfo> booleanInfoMap;

    public OutcomePopulator(
            @NotNull final PsiExceptionProvider psiExceptionProvider,
            @NotNull final FollowupInfoProvider followupInfoProvider,
            @NotNull final NullabilityDecider nullabilityDecider,
            @NotNull final DefaultTypesHolder defaultTypesHolder,
            @NotNull final JavaPsiFacade javaPsiFacade,
            @NotNull final PsiClass sourceClass,
            @NotNull final Map<PsiMethod, DependencyInteractionSet> sourceMethodToDisMap,
            @NotNull final MethodPathsInfoProvider methodPathsInfoProvider,
            @Nullable final DepConfig depConfig) {
        this.psiExceptionProvider = psiExceptionProvider;
        this.followupInfoProvider = followupInfoProvider;
        this.nullabilityDecider = nullabilityDecider;
        this.defaultTypesHolder = defaultTypesHolder;
        this.javaPsiFacade = javaPsiFacade;
        this.sourceClass = sourceClass;
        this.sourceMethodToDisMap = sourceMethodToDisMap;
        this.methodPathsInfoProvider = methodPathsInfoProvider;
        this.depConfig = depConfig;
        this.allSourceClassMethods = SetUtils.newIdentityHashSet();
        this.allSourceClassMethods.addAll(Arrays.asList(this.sourceClass.getAllMethods()));
        this.cachedValues = new HashMap<>();
        this.sourceMethodToFlatDisMap = DependencyInteractionCollectorUtils.computeFlatDisMap(sourceMethodToDisMap);
        this.nodePathMapWithPreserveUnknownFalse = new HashMap<>();
        this.nodePathMapWithPreserveUnknownBoth = new HashMap<>();
        this.nodeToPathThatDoesNotThrowWithPreserveUnknownBothMap = new HashMap<>();
        this.nodeToPathThatDoesNotThrowWithPreserveUnknownFalseMap = new HashMap<>();
        this.nodeToPathThatDoesNotReturnAltValueWithPreserveUnknownFalseMap = new HashMap<>();
        this.nodeToPathThatDoesNotReturnAltValueWithPreserveUnknownBothMap = new HashMap<>();
        this.nodeToBeforeInfoCache = new HashMap<>();
        this.booleanInfoMap = new HashMap<>();
    }

    public Map<MethodAndDi, BooleanInfo> computeBooleanInfo() {
        precomputeBooleanInfo();
        return booleanInfoMap;
    }

    public OutcomeInfo createOutcomeInfo() {
        final Map<String, AltFlowInfo> exceptionAltFlowMap = new HashMap<>();
        final Map<String, AltFlowInfo> valueAltFlowMap = new HashMap<>();
        final Map<String, UnfilteredPrimaryFlowInfo> unfilteredPrimaryFlowInfoMap = new HashMap<>();
        final Map<String, FilteredPrimaryFlowInfo> filteredPrimaryFlowInfoMap = new HashMap<>();
        final Map<String, FilteredPrimaryFlowInfo> filteredPrimaryFlowWithoutDisInfoMap = new HashMap<>();
        // Precompute which boolean values are Normal for all DIs that return booleans.
        precomputeBooleanInfo();

        for(final Map.Entry<PsiMethod, DependencyInteractionSet> entry : sourceMethodToDisMap.entrySet()) {
            final PsiMethod sourceMethod = entry.getKey();
            if(!allSourceClassMethods.contains(sourceMethod)) {
                continue;
            }
            // Compute primary flow DIs for entry.method.
            addPrimaryFlowEntries(unfilteredPrimaryFlowInfoMap, filteredPrimaryFlowInfoMap, filteredPrimaryFlowWithoutDisInfoMap, sourceMethod);
            for(final InternalDependencyInteraction di : entry.getValue()) {
                final List<Node> callstacks = di.getCallstacks();
                if(callstacks.isEmpty()) {
                    // This shouldn't happen.
                    continue;
                }
                final BooleanInfo booleanInfo = booleanInfoMap.get(new MethodAndDi(sourceMethod, di));
                final Node firstNode = callstacks.get(0);
                final PsiMethod diMethod = di.getPsiMethod();
                final DiAndNode diAndNode = new DiAndNode(di, firstNode);
                final List<PsiType> exceptions = psiExceptionProvider.getAllExceptions(diMethod);
                for(final PsiType exception : exceptions) {
                    final String finalMapKey = OutcomeKeyUtils.computeFinalMapKey(sourceMethod, di, exception);
                    if(finalMapKey == null) {
                        continue;
                    }
                    final NodeDecision nodeDecision = new NodeDecision(diAndNode, null, exception);
                    final FilteredDIsHitAfterInfo filteredDisHitAfter = computeFilteredDisAfter(nodeDecision);
                    final BeforeInfo beforeInfo = computeBeforeInfo(diAndNode);
                    final AltFlowInfo finalMapValue = convertToAltFlowInfo(beforeInfo, filteredDisHitAfter);
                    exceptionAltFlowMap.put(finalMapKey, finalMapValue);
                }

                // Compute the followup info for the normal case.
                final ReturnOutcome defaultOutcome = getDefaultOutcomeForDiMethod(diMethod);
                final String defaultValueFinalMapKey = computeFinalMapKey(sourceMethod, di, defaultOutcome);
                if(defaultValueFinalMapKey == null) {
                    continue;
                }
                final NodeDecision defaultNodeDecision = new NodeDecision(diAndNode, defaultOutcome, null);
                final BeforeInfo defaultBeforeInfo = computeBeforeInfo(diAndNode);
                final FilteredDIsHitAfterInfo defaultFilteredDisHitAfter = computeFilteredDisAfter(defaultNodeDecision);
                final AltFlowInfo defaultFinalMapValue = convertToAltFlowInfo(defaultBeforeInfo, defaultFilteredDisHitAfter);
                valueAltFlowMap.put(defaultValueFinalMapKey, defaultFinalMapValue);

                // Determine the DI-returns-absent info.
                final PsiType diMethodReturnType = diMethod.getReturnType();
                if(isNullOrVoid(diMethodReturnType)) {
                    continue;
                }
                final PsiType actualReturnType = di.getInternalMethodCallExpression().type();
                if(shouldComputePathForAbsentCase(diMethodReturnType, actualReturnType)) {
                    final String finalMapKey = computeFinalMapKey(sourceMethod, di, ReturnOutcome.Absent);
                    if(finalMapKey == null) {
                        continue;
                    }
                    final NodeDecision nodeDecision = new NodeDecision(diAndNode, ReturnOutcome.Absent, null);
                    final BeforeInfo beforeInfo = computeBeforeInfo(diAndNode);
                    final FilteredDIsHitAfterInfo filteredDisHitAfter = computeFilteredDisAfter(nodeDecision);
                    final AltFlowInfo finalMapValue = convertToAltFlowInfo(beforeInfo, filteredDisHitAfter);
                    valueAltFlowMap.put(finalMapKey, finalMapValue);
                } else {
                    final NullabilityStatus nullabilityStatus = nullabilityDecider.determineIfNullable(
                            diMethod, diMethod.findSuperMethods(), di.getInternalMethodCallExpression().methodCallExpression());
                    if(nullabilityStatus == NullabilityStatus.NULLABLE) {
                        final String finalMapKey = computeFinalMapKey(sourceMethod, di, ReturnOutcome.Null);
                        if(finalMapKey == null) {
                            continue;
                        }
                        final NodeDecision nodeDecision = new NodeDecision(diAndNode, ReturnOutcome.Null, null);
                        final BeforeInfo beforeInfo = computeBeforeInfo(diAndNode);
                        final FilteredDIsHitAfterInfo filteredDisHitAfter = computeFilteredDisAfter(nodeDecision);
                        final AltFlowInfo finalMapValue = convertToAltFlowInfo(beforeInfo, filteredDisHitAfter);
                        valueAltFlowMap.put(finalMapKey, finalMapValue);
                    }
                }

                // Determine the DI-returns-empty info.
                if(shouldComputePathForEmptyCase(diMethodReturnType, actualReturnType)) {
                    final String finalMapKey = computeFinalMapKey(sourceMethod, di, ReturnOutcome.Empty);
                    if(finalMapKey == null) {
                        continue;
                    }
                    final NodeDecision nodeDecision = new NodeDecision(diAndNode, ReturnOutcome.Empty, null);
                    final BeforeInfo beforeInfo = computeBeforeInfo(diAndNode);
                    final FilteredDIsHitAfterInfo filteredDisHitAfter = computeFilteredDisAfter(nodeDecision);
                    final AltFlowInfo finalMapValue = convertToAltFlowInfo(beforeInfo, filteredDisHitAfter);
                    valueAltFlowMap.put(finalMapKey, finalMapValue);
                }

                // Determine the DI-returns-broken-IO info.
                final boolean shouldComputeIOEntries = shouldComputeBrokenIOFlowEntry(diMethodReturnType);
                if(shouldComputeIOEntries) {
                    final String finalMapKey = computeFinalMapKey(sourceMethod, di, ReturnOutcome.BrokenIO);
                    if(finalMapKey == null) {
                        continue;
                    }
                    final NodeDecision nodeDecision = new NodeDecision(diAndNode, ReturnOutcome.BrokenIO, null);
                    final BeforeInfo beforeInfo = computeBeforeInfo(diAndNode);
                    final FilteredDIsHitAfterInfo filteredDisHitAfter = computeFilteredDisAfter(nodeDecision);
                    final AltFlowInfo finalMapValue = convertToAltFlowInfo(beforeInfo, filteredDisHitAfter);
                    valueAltFlowMap.put(finalMapKey, finalMapValue);
                }

                if(shouldComputeIOEntries) {
                    final String finalMapKey = computeFinalMapKey(sourceMethod, di, ReturnOutcome.EmptyIO);
                    if(finalMapKey == null) {
                        continue;
                    }
                    final NodeDecision nodeDecision = new NodeDecision(diAndNode, ReturnOutcome.EmptyIO, null);
                    final BeforeInfo beforeInfo = computeBeforeInfo(diAndNode);
                    final FilteredDIsHitAfterInfo filteredDisHitAfter = computeFilteredDisAfter(nodeDecision);
                    final AltFlowInfo finalMapValue = convertToAltFlowInfo(beforeInfo, filteredDisHitAfter);
                    valueAltFlowMap.put(finalMapKey, finalMapValue);
                }
                if(booleanInfo != null) {
                    if(booleanInfo == BooleanInfo.TrueIsNormal) {
                        // Compute alt flow for false case.
                        final String finalMapKey = computeFinalMapKey(sourceMethod, di, ReturnOutcome.False);
                        if(finalMapKey == null) {
                            continue;
                        }
                        final NodeDecision nodeDecision = new NodeDecision(diAndNode, ReturnOutcome.False, null);
                        final BeforeInfo beforeInfo = computeBeforeInfo(diAndNode);
                        final FilteredDIsHitAfterInfo filteredDisHitAfter = computeFilteredDisAfter(nodeDecision);
                        final AltFlowInfo finalMapValue = convertToAltFlowInfo(beforeInfo, filteredDisHitAfter);
                        valueAltFlowMap.put(finalMapKey, finalMapValue);
                    } else if(booleanInfo == BooleanInfo.FalseIsNormal) {
                        // Compute alt flow for true case.
                        final String finalMapKey = computeFinalMapKey(sourceMethod, di, ReturnOutcome.True);
                        if(finalMapKey == null) {
                            continue;
                        }
                        final NodeDecision nodeDecision = new NodeDecision(diAndNode, ReturnOutcome.True, null);
                        final BeforeInfo beforeInfo = computeBeforeInfo(diAndNode);
                        final FilteredDIsHitAfterInfo filteredDisHitAfter = computeFilteredDisAfter(nodeDecision);
                        final AltFlowInfo finalMapValue = convertToAltFlowInfo(beforeInfo, filteredDisHitAfter);
                        valueAltFlowMap.put(finalMapKey, finalMapValue);
                    }
                }
                if(shouldComputePathForFailureCase(diMethodReturnType)) {
                    final String finalMapKey = computeFinalMapKey(sourceMethod, di, ReturnOutcome.Failure);
                    if(finalMapKey == null) {
                        continue;
                    }
                    final NodeDecision nodeDecision = new NodeDecision(diAndNode, ReturnOutcome.Failure, null);
                    final BeforeInfo beforeInfo = computeBeforeInfo(diAndNode);
                    final FilteredDIsHitAfterInfo filteredDisHitAfter = computeFilteredDisAfter(nodeDecision);
                    final AltFlowInfo finalMapValue = convertToAltFlowInfo(beforeInfo, filteredDisHitAfter);
                    valueAltFlowMap.put(finalMapKey, finalMapValue);
                }
            }
        }
        return new OutcomeInfo(unfilteredPrimaryFlowInfoMap, filteredPrimaryFlowInfoMap, filteredPrimaryFlowWithoutDisInfoMap, exceptionAltFlowMap, valueAltFlowMap);
    }

    private void precomputeFirstCallstackNodeStatus() {
        // First set di.canBeFirstCallstackNode = true.
        for(final Map.Entry<PsiMethod, DependencyInteractionSet> entry : sourceMethodToDisMap.entrySet()) {
            for(final InternalDependencyInteraction di : entry.getValue()) {
                di.setCanBeFirstCallstackNode(true);
            }
        }

        if(depConfig == null) {
            return;
        }

        final Map<PsiField, PsiElement> fieldToSourceVarMap = depConfig.fieldToSourceVarMap();
        // Create (sourceVar, Method) tuples.
        final Set<ElementAndMethod> sourceVarDiMethod = new HashSet<>();
        for(final Map.Entry<PsiMethod, DependencyInteractionSet> entry : sourceMethodToDisMap.entrySet()) {
            sourceVarDiMethod.clear();
            final PsiMethod sourceMethod = entry.getKey();
            if(!allSourceClassMethods.contains(sourceMethod)) {
                continue;
            }

            for(final InternalDependencyInteraction di : entry.getValue()) {
                final PsiMethod diMethod = di.getPsiMethod();
                final PsiField diField = di.getPsiField();
                final PsiElement sourceVar = fieldToSourceVarMap.get(diField);
                if(sourceVar == null) {
                    continue;
                }
                final Set<ElementAndMethod> newSourceVarTuples = createPairs(sourceVar, diMethod);
                if(!Collections.disjoint(sourceVarDiMethod, newSourceVarTuples)) {
                    di.setCanBeFirstCallstackNode(false);
                }
                sourceVarDiMethod.addAll(newSourceVarTuples);
            }
        }
    }

    private Set<ElementAndMethod> createPairs(final PsiElement sourceVar, final PsiMethod diMethod) {
        final PsiMethod[] superMethods = diMethod.findSuperMethods();
        if(superMethods.length == 0) {
            return Collections.singleton(new ElementAndMethod(sourceVar, diMethod));
        }
        final Set<ElementAndMethod> ret = new HashSet<>();
        ret.add(new ElementAndMethod(sourceVar, diMethod));
        for(final PsiMethod superMethod : superMethods) {
            ret.add(new ElementAndMethod(sourceVar, superMethod));
        }
        return ret;
    }

    private void precomputeBooleanInfo() {
        precomputeFirstCallstackNodeStatus();
        for(final Map.Entry<PsiMethod, DependencyInteractionSet> entry : sourceMethodToDisMap.entrySet()) {
            final PsiMethod sourceMethod = entry.getKey();
            if(!allSourceClassMethods.contains(sourceMethod)) {
                continue;
            }
            for(final InternalDependencyInteraction di : entry.getValue()) {
                final List<Node> callstacks = di.getCallstacks();
                if(callstacks.isEmpty()) {
                    // This shouldn't happen.
                    continue;
                }
                if(!returnsBoolean(di)) {
                    continue;
                }
                final PsiMethod diMethod = di.getPsiMethod();
                final MethodAndDi methodAndDi = new MethodAndDi(sourceMethod, di);
                if(booleanInfoMap.containsKey(methodAndDi)) {
                    continue;
                }
                final Node firstNode = callstacks.get(0);
                final DiAndNode diAndNode = new DiAndNode(di, firstNode);
                final NodeDecision trueDecision = new NodeDecision(diAndNode, ReturnOutcome.True, null);
                final CacheValue trueOutcome = computeValue(trueDecision);
                final NodeDecision falseDecision = new NodeDecision(diAndNode, ReturnOutcome.False, null);
                final CacheValue falseOutcome = computeValue(falseDecision);
                if(trueOutcome.hasException() && !falseOutcome.hasException()) {
                    // The false outcome is the normal value. The true outcome is the failure value.
                    booleanInfoMap.put(methodAndDi, BooleanInfo.FalseIsNormal);
                    continue;
                }
                if(falseOutcome.hasException() && !trueOutcome.hasException()) {
                    // The true outcome is the normal value. The false outcome is the failure value.
                    booleanInfoMap.put(methodAndDi, BooleanInfo.TrueIsNormal);
                    continue;
                }
                // If the two sets of disHitAfter are the same, (and possibly returnOUtcomes are similar) use returnOutcome=DoesNotMatter.
                // Otherwise, try to use name matching to differentiate between the two.
                // Otherwise, just pick one.
                if(haveDifferentOutcomes(trueOutcome, falseOutcome) || !trueOutcome.disHitAfter().equals(falseOutcome.disHitAfter())) {
                    // We need to pick one to be the normal flow and one to be the alt flow.
                    if(RegexUtils.matchesAny(MethodNamesWhereReturnTrueIsNormalRegexes, diMethod.getName())) {
                        booleanInfoMap.put(methodAndDi, BooleanInfo.TrueIsNormal);
                        continue;
                    }
                    booleanInfoMap.put(methodAndDi, BooleanInfo.FalseIsNormal);
                    continue;
                }
                booleanInfoMap.put(methodAndDi, BooleanInfo.DoesNotMatter);
            }
        }
    }

    private boolean haveDifferentOutcomes(final CacheValue left, final CacheValue right) {
        final PsiType leftException = left.expectedException();
        final PsiType rightException = right.expectedException();
        if(leftException != null) {
            if(rightException == null) {
                return true;
            }
            return !leftException.getCanonicalText().equals(rightException.getCanonicalText());
        }
        if(rightException != null) {
            return true;
        }
        return areConfirmedDifferent(left.returnOutcome(), right.returnOutcome());
    }

    private boolean areConfirmedDifferent(final ReturnOutcome left, final ReturnOutcome right) {
        if(left == right) {
            return false;
        }
        if(left == ReturnOutcome.Unknown || right == ReturnOutcome.Unknown) {
            // One is known and the other is not.
            return false;
        }
        return true;
    }

    private boolean returnsBoolean(final InternalDependencyInteraction di) {
        final PsiMethod psiMethod = di.getPsiMethod();
        final PsiType returnType = psiMethod.getReturnType();
        if(isNullOrVoid(returnType)) {
            return false;
        }
        if(returnType instanceof PsiPrimitiveType) {
            return StringUtils.equals("boolean", returnType.getCanonicalText());
        }
        final PsiClass psiClass = PsiUtil.resolveClassInType(returnType);
        if(isBoxedBoolean(psiClass)) {
            return true;
        }

        final PsiType actualType = di.getInternalMethodCallExpression().type();
        if(actualType == null) {
            return false;
        }
        if(actualType instanceof PsiPrimitiveType) {
            return StringUtils.equals("boolean", actualType.getCanonicalText());
        }
        final PsiClass actualTypeClass = PsiUtil.resolveClassInType(actualType);
        if(isBoxedBoolean(actualTypeClass)) {
            return true;
        }
        return false;
    }

    private boolean isBoxedBoolean(final PsiClass psiClass) {
        if(psiClass == null) {
            return false;
        }
        return StringUtils.equals(psiClass.getQualifiedName(), JavaNames.JavaLangBoolean);
    }


    @Nullable
    private String computeFinalMapKey(final PsiMethod sourceMethod, final InternalDependencyInteraction di, final ReturnOutcome returnOutcome) {
        final ReturnOutcome returnOutcomeToUse = getSwappedReturnOutcome(sourceMethod, di, returnOutcome);
        return OutcomeKeyUtils.computeFinalMapKeyForStartingOutcome(sourceMethod, di, returnOutcomeToUse);
    }

    private boolean shouldComputePathForFailureCase(final DiAndNode diAndNode) {
        final InternalDependencyInteraction internalDependencyInteraction = diAndNode.internalDependencyInteraction();
        final PsiType formalType = internalDependencyInteraction.getPsiMethod().getReturnType();
        return shouldComputePathForFailureCase(formalType);
    }

    private boolean shouldComputePathForFailureCase(final PsiType diMethodReturnType) {
        final boolean isFutureOrStage = InheritanceUtil.isInheritor(diMethodReturnType, JavaNames.Future)
                || InheritanceUtil.isInheritor(diMethodReturnType, JavaNames.CompletionStage);
        if(isFutureOrStage) {
            return true;
        }
        final PsiClass returnClass = PsiUtil.resolveClassInType(diMethodReturnType);
        if(returnClass == null) {
            return false;
        }
        final String qualifiedName = returnClass.getQualifiedName();
        if(qualifiedName == null) {
            return false;
        }
        return ClassesWithSupportedFailureValues.contains(qualifiedName);
    }

    private ReturnOutcome getDefaultOutcomeForDiMethod(final PsiMethod diMethod) {
        final PsiType returnType = diMethod.getReturnType();
        if(returnType == null) {
            return ReturnOutcome.Void;
        }
        if(PsiTypes.voidType().equals(returnType)) {
            return ReturnOutcome.Void;
        }
        return ReturnOutcome.Other;
    }

    private BeforeInfo computeBeforeInfo(final DiAndNode targetDiAndNode) {
        return nodeToBeforeInfoCache.computeIfAbsent(targetDiAndNode, this::computeBeforeInfoImpl);
    }

    private BeforeInfo computeBeforeInfoImpl(final DiAndNode targetDiAndNode) {
        final List<NodeDecision> decisionList = computeBeforeInfoDecisionList(targetDiAndNode);
        return convertToBeforeInfo(decisionList);
    }

    private BeforeInfo convertToBeforeInfo(final List<NodeDecision> nodeDecisions) {
        if(nodeDecisions.isEmpty()) {
            return BeforeInfo.emptySet();
        }
        final DiEntrySet entrySet = new DiEntrySet();
        for(final NodeDecision nodeDecision : nodeDecisions) {
            final String diKey = convertToDiKey(nodeDecision.diAndNode().internalDependencyInteraction());
            if(diKey == null) {
                continue;
            }
            final PsiType expectedException = nodeDecision.exceptionToThrow();
            final String expectedExceptionName = expectedException != null ? expectedException.getCanonicalText() : null;
            final ReturnOutcome returnOutcome = getSwappedReturnOutcome(nodeDecision);
            entrySet.add(new DiEntry(diKey, returnOutcome, expectedExceptionName));
        }
        return new BeforeInfo(entrySet);
    }

    private ReturnOutcome getSwappedReturnOutcome(final NodeDecision nodeDecision) {
        final DiAndNode diAndNode = nodeDecision.diAndNode();
        final PsiMethod sourceMethod = diAndNode.node().getSourceMethodContainingElement();
        return getSwappedReturnOutcome(sourceMethod, diAndNode.internalDependencyInteraction(), nodeDecision.returnOutcome());
    }

    private ReturnOutcome getSwappedReturnOutcome(final PsiMethod sourceMethod, final InternalDependencyInteraction di, final ReturnOutcome returnOutcome) {
        if(returnOutcome == null) {
            return null;
        }
        final PsiMethod diMethod = di.getPsiMethod();
        if(methodPathsInfoProvider.returnsListOfErrors(diMethod)) {
            if(returnOutcome == ReturnOutcome.Empty) {
                return ReturnOutcome.Other;
            }
            if(returnOutcome == ReturnOutcome.Other) {
                return ReturnOutcome.Failure;
            }
            return returnOutcome;
        }

        if(methodPathsInfoProvider.returnsOptionalError(diMethod)) {
            if(returnOutcome == ReturnOutcome.Absent) {
                return ReturnOutcome.Other;
            }
            if(returnOutcome == ReturnOutcome.Other) {
                return ReturnOutcome.Failure;
            }
            return returnOutcome;
        }

        final MethodAndDi methodAndDi = new MethodAndDi(sourceMethod, di);
        final BooleanInfo booleanInfo = booleanInfoMap.get(methodAndDi);
        if(booleanInfo != null) {
            if(booleanInfo == BooleanInfo.TrueIsNormal) {
                if(returnOutcome == ReturnOutcome.True) {
                    return ReturnOutcome.Other;
                }
                if(returnOutcome == ReturnOutcome.False) {
                    return ReturnOutcome.Failure;
                }
                return returnOutcome;
            }
            if(booleanInfo == BooleanInfo.FalseIsNormal) {
                if(returnOutcome == ReturnOutcome.False) {
                    return ReturnOutcome.Other;
                }
                if(returnOutcome == ReturnOutcome.True) {
                    return ReturnOutcome.Failure;
                }
                return returnOutcome;
            }
            if(returnOutcome == ReturnOutcome.False || returnOutcome == ReturnOutcome.True) {
                return ReturnOutcome.Other;
            }
            return returnOutcome;
        }
        return returnOutcome;
    }

    private List<NodeDecision> computeBeforeInfoDecisionList(final DiAndNode targetDiAndNode) {
        final Node targetNode = targetDiAndNode.node();
        final PsiMethod sourceMethod = targetNode.getSourceMethodContainingElement();
        final DiAndNodeSet diAndNodes = sourceMethodToFlatDisMap.get(sourceMethod);
        final DiAndNodeSet disBefore = diAndNodes.itemsBefore(targetNode);
        Iterator<DiAndNode> currentWorkingSet = disBefore.iterator();
        final List<NodeDecision> ret = new ArrayList<>();
        DiAndNodeSet firstSetThatIncludesTarget = null;
        while(currentWorkingSet.hasNext()) {
            DiAndNode currentNode = currentWorkingSet.next();
            if(currentNode.equals(targetDiAndNode)) {
                return ret;
            }
            if(firstSetThatIncludesTarget != null) {
                if(!firstSetThatIncludesTarget.contains(currentNode)) {
                    continue;
                }
            }
            final NodeStep nodeStep = determineDiAndOutcome(currentNode, targetDiAndNode);
            if(nodeStep != null) {
                final NodeDecision nodeDecision = nodeStep.nodeDecision();
                ret.add(nodeDecision);
                final CacheValue cacheValue = computeValue(nodeStep);
                final DiAndNodeSet disHitAfter = cacheValue.disHitAfter();
                currentWorkingSet = disHitAfter.iterator();
                if(firstSetThatIncludesTarget == null) {
                    if(disHitAfter.contains(targetDiAndNode)) {
                        firstSetThatIncludesTarget = disHitAfter;
                    }
                }
            }
        }
        return ret;
    }

    @Nullable
    private NodeStep determineDiAndOutcome(final DiAndNode startingNode, final DiAndNode targetDiAndNode) {
        final NodeStep nodeStep = tryDetermineDiAndOutcomeImpl(startingNode, targetDiAndNode, nodePathMapWithPreserveUnknownFalse, this::getAllStepsWithPreserveUnknownFalse);
        if(nodeStep != null) {
            return nodeStep;
        }
        return tryDetermineDiAndOutcomeImpl(startingNode, targetDiAndNode, nodePathMapWithPreserveUnknownBoth, this::getAllSteps);
    }

    private NodeStep tryDetermineDiAndOutcomeImpl(
            final DiAndNode startingNode, final DiAndNode targetDiAndNode,
            final Map<PathKey, NodeStep> memoizationMap,
            final Function<DiAndNode, List<NodeStep>> stepProvider) {
        final PathKey pathKey = new PathKey(startingNode, targetDiAndNode);
        if(memoizationMap.containsKey(pathKey)) {
            return memoizationMap.get(pathKey);
        }

        // Search for an alt flow with a direct path to the target node.
        final List<NodeStep> nodeSteps = stepProvider.apply(startingNode);
        for(final NodeStep nodeStep : nodeSteps) {
            final CacheValue cacheValue = computeValue(nodeStep);
            if(cacheValue.disHitAfter().contains(targetDiAndNode)) {
                memoizationMap.put(pathKey, nodeStep);
                return nodeStep;
            }
        }

        // There is no direct path to the node. Search for an indirect path recursively.
        final int targetSequenceId = targetDiAndNode.node().getSequenceId();
        final Deque<StackFrame> stack = new LinkedList<>();
        final StackFrame firstFrame = new StackFrame(startingNode, stepProvider.apply(startingNode), this::computeValue);
        stack.push(firstFrame);
        while(!stack.isEmpty()) {
            final StackFrame currentFrame = stack.peek();
            if(currentFrame.getCurrentDisHitAfter().contains(targetDiAndNode)) {
                // The stack contains the solution. break.
                break;
            }

            if(currentFrame.hasNextDiHitAfter()) {
                final DiAndNode nextDi = currentFrame.getNextDiHitAfter();
                if(nextDi.node().getSequenceId() < targetSequenceId) {
                    // Check memoization map for an existing path from the current node to the target.
                    final PathKey currentPathKey = new PathKey(nextDi, targetDiAndNode);
                    if(memoizationMap.containsKey(currentPathKey)) {
                        if(memoizationMap.get(currentPathKey) == null) {
                            // We have already determined that there is no path to the targetDi that includes this node.
                            continue;
                        } else {
                            // There is a path from the current node to the target. The path to the current node is
                            // in the stack. Break.
                            break;
                        }
                    }
                    final StackFrame nextFrame = new StackFrame(nextDi, stepProvider.apply(nextDi), this::computeValue);
                    stack.push(nextFrame);
                    continue;
                }
            }

            // We have exhausted the current nodes. Move to the next decision.
            if(currentFrame.hasNextDecision()) {
                currentFrame.advanceToNextDecision();
                continue;
            }

            // There are no more decisions.
            memoizationMap.put(new PathKey(currentFrame.getDiAndNode(), targetDiAndNode), null);
            stack.pop();
        }

        while(!stack.isEmpty()) {
            final StackFrame currentFrame = stack.pop();
            memoizationMap.put(new PathKey(currentFrame.getDiAndNode(), targetDiAndNode), currentFrame.getCurrentNodeStep());
        }
        return memoizationMap.get(pathKey);
    }

    private List<NodeStep> getAllStepsWithPreserveUnknownFalse(final DiAndNode startingNode) {
        final List<NodeDecision> allDecisions = getAllDecisions(startingNode);
        final List<NodeStep> ret = new ArrayList<>(allDecisions.size());
        for(final NodeDecision decision : allDecisions) {
            ret.add(new NodeStep(decision, false));
        }
        return ret;
    }

    private List<NodeStep> getAllSteps(final DiAndNode startingNode) {
        final List<NodeDecision> allDecisions = getAllDecisions(startingNode);
        final List<NodeStep> ret = new ArrayList<>(allDecisions.size() * 2);
        for(final NodeDecision decision : allDecisions) {
            ret.add(new NodeStep(decision, false));
        }
        for(final NodeDecision decision : allDecisions) {
            ret.add(new NodeStep(decision, true));
        }
        return ret;
    }

    private List<NodeDecision> getAllDecisions(final DiAndNode startingNode) {
        final List<NodeDecision> ret = getValueDecisions(startingNode);
        if(shouldComputePathForBrokenIO(startingNode)) {
            ret.add(new NodeDecision(startingNode, ReturnOutcome.BrokenIO, null));
        }
        final List<PsiType> pathExceptions = getPathExceptionsForNode(startingNode);
        for(final PsiType pathException : pathExceptions) {
            ret.add(new NodeDecision(startingNode, null, pathException));
        }
        return ret;
    }

    @NotNull
    private List<NodeDecision> getValueDecisions(final DiAndNode startingNode) {
        if(methodPathsInfoProvider.returnsListOfErrors(startingNode)) {
            return getValueDecisionsForListOfFailureValues(startingNode);
        }
        if(methodPathsInfoProvider.returnsOptionalError(startingNode)) {
            return getValueDecisionsForOptionalError(startingNode);
        }
        final BooleanInfo booleanInfo = getBooleanInfo(startingNode);
        if(booleanInfo != null) {
            return getValueDecisionsForBoolean(startingNode, booleanInfo);
        }
        final List<NodeDecision> ret = new ArrayList<>();
        ret.add(getDefaultNodeDecision(startingNode));
        if(shouldComputePathForAbsentCase(startingNode)) {
            ret.add(new NodeDecision(startingNode, ReturnOutcome.Absent, null));
        } else if(shouldComputePathForNullCase(startingNode)) {
            ret.add(new NodeDecision(startingNode, ReturnOutcome.Null, null));
        }
        if(shouldComputePathForEmptyCase(startingNode)) {
            ret.add(new NodeDecision(startingNode, ReturnOutcome.Empty, null));
        }
        if(shouldComputePathForFailureCase(startingNode)) {
            ret.add(new NodeDecision(startingNode, ReturnOutcome.Failure, null));
        }
        return ret;
    }

    private List<NodeDecision> getValueDecisionsForBoolean(final DiAndNode startingNode, final BooleanInfo booleanInfo) {
        final List<NodeDecision> ret = new ArrayList<>();
        if(booleanInfo == BooleanInfo.TrueIsNormal) {
            ret.add(new NodeDecision(startingNode, ReturnOutcome.True, null));
            ret.add(new NodeDecision(startingNode, ReturnOutcome.False, null));
            if(shouldComputePathForNullCase(startingNode)) {
                ret.add(new NodeDecision(startingNode, ReturnOutcome.Null, null));
            }
            return ret;
        }

        if(booleanInfo == BooleanInfo.FalseIsNormal) {
            ret.add(new NodeDecision(startingNode, ReturnOutcome.False, null));
            ret.add(new NodeDecision(startingNode, ReturnOutcome.True, null));
            if(shouldComputePathForNullCase(startingNode)) {
                ret.add(new NodeDecision(startingNode, ReturnOutcome.Null, null));
            }
            return ret;
        }

        ret.add(new NodeDecision(startingNode, ReturnOutcome.False, null));
        ret.add(new NodeDecision(startingNode, ReturnOutcome.True, null));
        if(shouldComputePathForNullCase(startingNode)) {
            ret.add(new NodeDecision(startingNode, ReturnOutcome.Null, null));
        }
        return ret;
    }

    private BooleanInfo getBooleanInfo(final DiAndNode startingNode) {
        final MethodAndDi key = new MethodAndDi(startingNode.node().getSourceMethodContainingElement(), startingNode.internalDependencyInteraction());
        return booleanInfoMap.get(key);
    }

    private List<NodeDecision> getValueDecisionsForListOfFailureValues(final DiAndNode startingNode) {
        final List<NodeDecision> ret = new ArrayList<>();
        ret.add(new NodeDecision(startingNode, ReturnOutcome.Empty, null));
        if(shouldComputePathForNullCase(startingNode)) {
            ret.add(new NodeDecision(startingNode, ReturnOutcome.Null, null));
        }
        ret.add(new NodeDecision(startingNode, ReturnOutcome.Other, null));
        return ret;
    }

    private List<NodeDecision> getValueDecisionsForOptionalError(final DiAndNode startingNode) {
        final List<NodeDecision> ret = new ArrayList<>(2);
        ret.add(new NodeDecision(startingNode, ReturnOutcome.Absent, null));
        ret.add(new NodeDecision(startingNode, ReturnOutcome.Other, null));
        return ret;
    }

    private boolean shouldComputePathForAbsentCase(final DiAndNode startingNode) {
        final InternalDependencyInteraction internalDependencyInteraction = startingNode.internalDependencyInteraction();
        final PsiType diReturnType = internalDependencyInteraction.getPsiMethod().getReturnType();
        final PsiType actualType = internalDependencyInteraction.getInternalMethodCallExpression().type();
        return shouldComputePathForAbsentCase(diReturnType, actualType);
    }

    private boolean shouldComputePathForAbsentCase(final PsiType diMethodReturnType, final PsiType actualReturnType) {
        return isJavaOrGuavaOptional(diMethodReturnType) || isJavaOrGuavaOptional(actualReturnType)
                || isRecognizedTypeWithSpecialAbsentInitExpression(diMethodReturnType)
                || isRecognizedTypeWithSpecialAbsentInitExpression(actualReturnType);
    }

    private boolean shouldComputePathForBrokenIO(final DiAndNode startingNode) {
        final PsiMethod psiMethod = startingNode.internalDependencyInteraction().getPsiMethod();
        return shouldComputeBrokenIOFlowEntry(psiMethod.getReturnType());
    }

    private boolean shouldComputeBrokenIOFlowEntry(final PsiType diMethodReturnType) {
        final PsiClass diReturnTypeClass = PsiUtil.resolveClassInType(diMethodReturnType);
        if(diReturnTypeClass == null) {
            return false;
        }
        final String qualifiedName = diReturnTypeClass.getQualifiedName();
        if(qualifiedName == null) {
            return false;
        }
        final DefaultTypeInfo defaultTypeInfo = defaultTypesHolder.get(qualifiedName);
        if(defaultTypeInfo != null) {
            if(defaultTypeInfo.isBeanWithInputIOProperty()) {
                return true;
            }
        }

        final List<String> allNames = new ArrayList<>();
        allNames.add(qualifiedName);
        allNames.addAll(InheritanceUtil.getSuperClasses(diReturnTypeClass).stream().map(PsiClass::getQualifiedName).filter(Objects::nonNull).toList());
        return containsAny(allNames, IOTypeCanonicalNames) && containsAny(allNames, CloseableTypeCanonicalNames);
    }

    private boolean shouldComputePathForEmptyCase(final DiAndNode startingNode) {
        final InternalDependencyInteraction internalDependencyInteraction = startingNode.internalDependencyInteraction();
        final PsiType formalType = internalDependencyInteraction.getPsiMethod().getReturnType();
        final PsiType actualType = internalDependencyInteraction.getInternalMethodCallExpression().type();
        return shouldComputePathForEmptyCase(formalType, actualType);
    }

    private boolean shouldComputePathForEmptyCase(final PsiType formalType, final PsiType actualType) {
        if(shouldComputePathForEmptyCaseImmediate(formalType)) {
            return true;
        }
        if(shouldComputePathForEmptyCaseImmediate(actualType)) {
            return true;
        }
        if(shouldComputePathForEmptyCaseRecursive(actualType)) {
            return true;
        }
        return false;
    }

    private boolean shouldComputePathForEmptyCaseRecursive(final PsiType psiType) {
        final PsiClassType.ClassResolveResult resolveResult = PsiUtil.resolveGenericsClassInType(psiType);
        final PsiClass psiClass = resolveResult.getElement();
        final PsiSubstitutor typeSubstitutor = resolveResult.getSubstitutor();
        if(psiClass == null) {
            return false;
        }
        final String canonicalName = psiClass.getQualifiedName();
        if(canonicalName == null) {
            return false;
        }
        if(psiClass.getTypeParameters().length != 1) {
            return false;
        }
        final PsiType substitutedType = typeSubstitutor.substitute(psiClass.getTypeParameters()[0]);
        if(substitutedType == null) {
            return false;
        }
        if(shouldComputePathForEmptyCaseImmediate(substitutedType)) {
            return true;
        }
        final HashSet<String> canonicalTextsInStack = new HashSet<>();
        canonicalTextsInStack.add(psiType.getCanonicalText());
        return shouldComputePathForEmptyCaseRecursiveImpl(substitutedType, canonicalTextsInStack, 1);
    }

    private boolean shouldComputePathForEmptyCaseRecursiveImpl(final PsiType psiType, final HashSet<String> canonicalTextsInStack, final int recursiveDepth) {
        final String canonicalText = psiType.getCanonicalText();
        if(recursiveDepth >= 10 || canonicalTextsInStack.contains(canonicalText)) {
            return false;
        }
        final PsiClassType.ClassResolveResult resolveResult = PsiUtil.resolveGenericsClassInType(psiType);
        final PsiClass psiClass = resolveResult.getElement();
        final PsiSubstitutor typeSubstitutor = resolveResult.getSubstitutor();
        if(psiClass == null) {
            return false;
        }
        final String canonicalName = psiClass.getQualifiedName();
        if(canonicalName == null) {
            return false;
        }
        if(psiClass.getTypeParameters().length != 1) {
            return false;
        }
        final PsiType substitutedType = typeSubstitutor.substitute(psiClass.getTypeParameters()[0]);
        if(substitutedType == null) {
            return false;
        }
        if(shouldComputePathForEmptyCaseImmediate(substitutedType)) {
            return true;
        }
        canonicalTextsInStack.add(psiType.getCanonicalText());
        return shouldComputePathForEmptyCaseRecursiveImpl(substitutedType, canonicalTextsInStack, recursiveDepth + 1);
    }

    private boolean shouldComputePathForEmptyCaseImmediate(final PsiType psiType) {
        if(psiType instanceof PsiArrayType) {
            return true;
        }
        final PsiClassType.ClassResolveResult resolveResult = PsiUtil.resolveGenericsClassInType(psiType);
        final PsiClass psiClass = resolveResult.getElement();
        if(psiClass == null) {
            return false;
        }
        final String canonicalName = psiClass.getQualifiedName();
        if(canonicalName == null) {
            return false;
        }
        final DefaultTypeInfo defaultTypeInfo = defaultTypesHolder.get(canonicalName);
        if(defaultTypeInfo == null) {
            if(InheritanceUtil.isInheritor(psiType, JavaNames.JavaLangIterable)) {
                return true;
            }
            return false;
        }
        if(defaultTypeInfo.getEmptyInitExpression() != null) {
            return true;
        }
        if(InheritanceUtil.isInheritor(psiType, JavaNames.JavaLangIterable)) {
            return true;
        }
        return false;
    }

    private boolean shouldComputePathForNullCase(final DiAndNode startingNode) {
        final InternalDependencyInteraction di = startingNode.internalDependencyInteraction();
        final PsiMethod diMethod = di.getPsiMethod();
        final NullabilityStatus nullabilityStatus = nullabilityDecider.determineIfNullable(
                diMethod, diMethod.findSuperMethods(), di.getInternalMethodCallExpression().methodCallExpression());
        return nullabilityStatus == NullabilityStatus.NULLABLE;
    }

    private List<PsiType> getPathExceptionsForNode(final DiAndNode startingNode) {
        final PsiMethod psiMethod = startingNode.internalDependencyInteraction().getPsiMethod();
        final List<PsiType> declaredExceptions = psiExceptionProvider.getDeclaredExceptions(psiMethod);
        if(!declaredExceptions.isEmpty()) {
            return declaredExceptions;
        }
        final List<PsiType> javadocExceptions = psiExceptionProvider.getJavadocExceptions(psiMethod).stream().filter(x -> {
            final String canonicalText = x.getCanonicalText();
            if(StringUtils.equalsAny(canonicalText, JavaNames.JavaLangRuntimeException, JavaNames.JavaUtilNoSuchElementException)) {
                return true;
            }
            return !canonicalText.startsWith("java.");
        }).toList();
        final List<PsiType> undeclaredExceptions = psiExceptionProvider.getUndeclaredExceptions(psiMethod);
        if(javadocExceptions.isEmpty() && undeclaredExceptions.isEmpty()) {
            return Collections.emptyList();
        }
        final Set<String> alreadyIncluded = new HashSet<>();
        final List<PsiType> ret = new ArrayList<>();
        for(final PsiType psiType : javadocExceptions) {
            if(alreadyIncluded.add(psiType.getCanonicalText())) {
                ret.add(psiType);
            }
        }
        for(final PsiType psiType : undeclaredExceptions) {
            if(alreadyIncluded.add(psiType.getCanonicalText())) {
                ret.add(psiType);
            }
        }
        return ret;
    }

    private NodeDecision getDefaultNodeDecision(final DiAndNode diAndNode) {
        final InternalDependencyInteraction internalDependencyInteraction = diAndNode.internalDependencyInteraction();
        final PsiType returnType = internalDependencyInteraction.getPsiMethod().getReturnType();
        final ReturnOutcome returnOutcome;
        if(isNullOrVoid(returnType)) {
            returnOutcome = ReturnOutcome.Void;
        } else {
            returnOutcome = ReturnOutcome.Other;
        }
        return new NodeDecision(diAndNode, returnOutcome, null);
    }

    private boolean isRecognizedTypeWithSpecialAbsentInitExpression(final PsiType psiType) {
        final PsiClass psiClass = PsiUtil.resolveClassInType(psiType);
        if(psiClass == null) {
            return false;
        }
        final String qualifiedName = psiClass.getQualifiedName();
        if(qualifiedName == null) {
            return false;
        }
        final DefaultTypeInfo defaultTypeInfo = defaultTypesHolder.get(qualifiedName);
        if(defaultTypeInfo == null) {
            return false;
        }
        if(defaultTypeInfo.isAbsentIfTypeParamsAreAbsent()) {
            return true;
        }
        final String absentInitExpression = defaultTypeInfo.getAbsentInitExpression();
        if(absentInitExpression == null) {
            return false;
        }
        if(absentInitExpression.equals("null")) {
            return false;
        }
        return true;
    }

    public static boolean isNullOrVoid(final PsiType diMethodReturnType) {
        if(diMethodReturnType == null) {
            return true;
        }
        return PsiTypes.voidType().equals(diMethodReturnType);
    }

    private static boolean containsAny(final List<String> allNames, final String[] ioTypeCanonicalNames) {
        for(final String str : ioTypeCanonicalNames) {
            if(allNames.contains(str)) {
                return true;
            }
        }
        return false;
    }

    private AltFlowInfo convertToAltFlowInfo(final BeforeInfo beforeInfo, final FilteredDIsHitAfterInfo filteredDisHitAfter) {
        final Set<String> disHitAfter = OutcomeKeyUtils.convertToDiKeys(filteredDisHitAfter.disHitAfter());
        final PsiType expectedException = filteredDisHitAfter.expectedException();
        final String expectedExceptionName = expectedException != null ? expectedException.getCanonicalText() : null;
        return new AltFlowInfo(beforeInfo, disHitAfter, expectedExceptionName, filteredDisHitAfter.returnOutcome());
    }

    private void addPrimaryFlowEntries(
            final Map<String, UnfilteredPrimaryFlowInfo> finalOutcomeMap,
            final Map<String, FilteredPrimaryFlowInfo> filteredPrimaryFlowInfoMap,
            final Map<String, FilteredPrimaryFlowInfo> filteredPrimaryFlowWithoutDisInfoMap,
            final PsiMethod sourceMethod) {
        final PsiClass sourceClassContainingMethod = sourceMethod.getContainingClass();
        if(sourceClassContainingMethod == null) {
            return;
        }
        final String sourceClassName = sourceClassContainingMethod.getName();
        if(sourceClassName == null) {
            return;
        }
        final boolean canOnlyReturnAltValueOrThrow = methodPathsInfoProvider.canReturnAltValue(sourceMethod) && !methodPathsInfoProvider.canReturnNormalValue(sourceMethod);
        final boolean sourceMethodAlwaysThrows = methodPathsInfoProvider.alwaysThrows(sourceMethod);
        final String alwaysThrownException = methodPathsInfoProvider.getAlwaysThrownException(sourceMethod);
        final ReturnOutcome alwaysReturnOutcome = determineOutcomeForUnfilteredPrimaryFlow(sourceMethod);
        final DiAndNodeSet internalDependencyInteractions = sourceMethodToFlatDisMap.get(sourceMethod);
        final DiAndNodeSet unfilteredPrimaryFlowDis = new DiAndNodeSet();
        for(final DiAndNode di : internalDependencyInteractions) {
            if(sourceMethodAlwaysThrows) {
                // Handle the case where the source always throws.
                unfilteredPrimaryFlowDis.add(di);
                continue;
            }
            if(canOnlyReturnAltValueOrThrow) {
                // Prefer the case where the source method returns an alt value.
                if(!passesThroughCatchBlock(di.node()) && !allPathsThrow(di)) {
                    unfilteredPrimaryFlowDis.add(di);
                }
                continue;
            }
            if(!passesThroughCatchBlock(di.node()) && !allPathsThrow(di)
                    && !allPathsReturnAltValue(di)) {
                // Handle the normal case.
                unfilteredPrimaryFlowDis.add(di);
            }
        }
        final LinkedHashSet<String> diKeys = OutcomeKeyUtils.convertToDiKeys(unfilteredPrimaryFlowDis);
        final String sourceMethodKey = sourceClassName + "_" + TypeCreatorUtil.getMethodKey(sourceMethod);
        finalOutcomeMap.put(sourceMethodKey, new UnfilteredPrimaryFlowInfo(diKeys, alwaysThrownException, alwaysReturnOutcome));

        // Iterate through each di and compute the primary flow using that as a starting point.
        for(final DiAndNode di : unfilteredPrimaryFlowDis) {
            final String diKey = convertToDiKey(di.internalDependencyInteraction());
            if(diKey == null) {
                // This shouldn't happen.
                continue;
            }
            final String filteredPrimaryFlowKey = sourceMethodKey + "_" + diKey;
            if(filteredPrimaryFlowInfoMap.containsKey(filteredPrimaryFlowKey)) {
                continue;
            }
            final InternalFilteredPrimaryFlowInfo internalFilteredPrimaryFlowInfo = computeFilteredPrimaryFlow(di);
            final Set<String> filteredPrimaryFlowDiKeys = OutcomeKeyUtils.convertToDiKeys(internalFilteredPrimaryFlowInfo.disHit());
            final PsiType expectedException = internalFilteredPrimaryFlowInfo.expectedException();
            final String expectedExceptionName = expectedException != null ? expectedException.getCanonicalText() : null;
            final FilteredPrimaryFlowInfo filteredPrimaryFlowInfo = new FilteredPrimaryFlowInfo(
                    filteredPrimaryFlowDiKeys,
                    expectedExceptionName,
                    internalFilteredPrimaryFlowInfo.returnOutcome());
            filteredPrimaryFlowInfoMap.put(filteredPrimaryFlowKey, filteredPrimaryFlowInfo);
        }

        // Compute the primary flow using no dependency as a starting point.
        // This will be used if the user does not mock any dependencies in this source method.
        if(canOnlyReturnAltValueOrThrow) {
            final ReturnOutcome firstAltValue = methodPathsInfoProvider.getFirstReturnedAltValue(sourceMethod);
            if(firstAltValue != null) {
                filteredPrimaryFlowWithoutDisInfoMap.put(sourceMethodKey, new FilteredPrimaryFlowInfo(Collections.emptySet(), null, firstAltValue));
            } else {
                // This shouldn't happen.
                filteredPrimaryFlowWithoutDisInfoMap.put(sourceMethodKey, new FilteredPrimaryFlowInfo(Collections.emptySet(), null, ReturnOutcome.Unknown));
            }
        } else if(sourceMethodAlwaysThrows) {
            if(alwaysThrownException != null) {
                filteredPrimaryFlowWithoutDisInfoMap.put(sourceMethodKey, new FilteredPrimaryFlowInfo(Collections.emptySet(), alwaysThrownException, null));
            } else {
                filteredPrimaryFlowWithoutDisInfoMap.put(sourceMethodKey, new FilteredPrimaryFlowInfo(Collections.emptySet(), null, ReturnOutcome.Unknown));
            }
        } else {
            filteredPrimaryFlowWithoutDisInfoMap.put(sourceMethodKey, new FilteredPrimaryFlowInfo(Collections.emptySet(), null, ReturnOutcome.Unknown));
        }
    }

    private ReturnOutcome determineOutcomeForUnfilteredPrimaryFlow(final PsiMethod sourceMethod) {
        final boolean canOnlyReturnAltValueOrThrow = methodPathsInfoProvider.canReturnAltValue(sourceMethod) && !methodPathsInfoProvider.canReturnNormalValue(sourceMethod);
        if(canOnlyReturnAltValueOrThrow) {
            return methodPathsInfoProvider.getFirstReturnedAltValue(sourceMethod);
        }
        final List<ReturnOutcome> returnOutcomes = methodPathsInfoProvider.getReturnOutcomes(sourceMethod);
        if(returnOutcomes.isEmpty()) {
            return null;
        }
        final EnumSet<ReturnOutcome> outcomeSet = EnumSet.noneOf(ReturnOutcome.class);
        outcomeSet.addAll(returnOutcomes);
        if(outcomeSet.size() == 1) {
            return outcomeSet.iterator().next();
        }
        return null;
    }

    private InternalFilteredPrimaryFlowInfo computeFilteredPrimaryFlow(final DiAndNode startingDiAndNode) {
        final List<NodeDecision> startingDecisions = getAllDecisions(startingDiAndNode);
        final FilteredDIsHitAfterInfo filteredDIsHitAfterInfo = computeFilteredDisAfter(startingDecisions.get(0));
        final DiAndNodeSet disHit = new DiAndNodeSet();
        disHit.add(startingDiAndNode);
        disHit.addAll(filteredDIsHitAfterInfo.disHitAfter());
        return new InternalFilteredPrimaryFlowInfo(disHit, filteredDIsHitAfterInfo.expectedException(), filteredDIsHitAfterInfo.returnOutcome());
    }

    private boolean allPathsReturnAltValue(final DiAndNode di) {
        return findPathThatDoesNotReturnAltValueRecursively(di) == null;
    }

    private static boolean isAltValue(final ReturnOutcome returnOutcome) {
        return returnOutcome == ReturnOutcome.Absent || returnOutcome == ReturnOutcome.Null ||
                returnOutcome == ReturnOutcome.Empty || returnOutcome == ReturnOutcome.Failure;
    }

    private boolean allPathsThrow(final DiAndNode di) {
        return findPathThatDoesNotThrowRecursively(di) == null;
    }

    @Nullable
    private NodeStep findPathThatDoesNotThrowRecursively(final DiAndNode startingNode) {
        final NodeStep nodeStep = tryFindPathThatDoesNotThrowRecursivelyImpl(startingNode, nodeToPathThatDoesNotThrowWithPreserveUnknownFalseMap, this::getAllStepsWithPreserveUnknownFalse);
        if(nodeStep != null) {
            return nodeStep;
        }
        return tryFindPathThatDoesNotThrowRecursivelyImpl(startingNode, nodeToPathThatDoesNotThrowWithPreserveUnknownBothMap, this::getAllSteps);
    }

    @Nullable
    private NodeStep tryFindPathThatDoesNotThrowRecursivelyImpl(
            final DiAndNode startingNode, final Map<DiAndNode, NodeStep> memoizationMap,
            final Function<DiAndNode, List<NodeStep>> stepProvider) {
        if(memoizationMap.containsKey(startingNode)) {
            return memoizationMap.get(startingNode);
        }

        // Search for an alt flow that does not throw.
        final List<NodeStep> nodeSteps = stepProvider.apply(startingNode);
        for(final NodeStep nodeStep : nodeSteps) {
            final CacheValue cacheValue = computeValue(nodeStep);
            if(cacheValue.expectedException() == null) {
                memoizationMap.put(startingNode, nodeStep);
                return nodeStep;
            }
        }

        // There is no direct path to the target flow. Search for an indirect path recursively.
        final Deque<StackFrame> stack = new LinkedList<>();
        final StackFrame firstFrame = new StackFrame(startingNode, stepProvider.apply(startingNode), this::computeValue);
        stack.push(firstFrame);
        while(!stack.isEmpty()) {
            final StackFrame currentFrame = stack.peek();
            if(currentFrame.getCurrentCacheValue().expectedException() == null) {
                // The stack contains the solution. break.
                break;
            }

            if(currentFrame.hasNextDiHitAfter()) {
                final DiAndNode nextDi = currentFrame.getNextDiHitAfter();
                // Check memoization map for an existing path from the current node to the target flow.
                if(memoizationMap.containsKey(nextDi)) {
                    if(memoizationMap.get(nextDi) == null) {
                        // We have already determined that there is no path to the target flow that includes this node.
                        continue;
                    } else {
                        // There is a path from the current node to the target flow. The path to the current node is
                        // in the stack. Break.
                        break;
                    }
                }
                if(passesThroughFinallyBlock(nextDi.node())) {
                    continue;
                }
                if(allValuePathsDoNotThrow(nextDi)) {
                    // This DI being hit cancels the exception. This only happens in cases where we've lost context;
                    // i.e. the nextDi does not have enough context info to infer the exception is thrown.
                    continue;
                }
                final StackFrame nextFrame = new StackFrame(nextDi, stepProvider.apply(nextDi), this::computeValue);
                stack.push(nextFrame);
                continue;
            }

            // We have exhausted the current nodes. Move to the next decision.
            if(currentFrame.hasNextDecision()) {
                currentFrame.advanceToNextDecision();
                continue;
            }

            // There are no more decisions.
            memoizationMap.put(currentFrame.getDiAndNode(), null);
            stack.pop();
        }

        while(!stack.isEmpty()) {
            final StackFrame currentFrame = stack.pop();
            memoizationMap.put(currentFrame.getDiAndNode(), currentFrame.getCurrentNodeStep());
        }
        return memoizationMap.get(startingNode);
    }

    @Nullable
    private NodeStep findPathThatDoesNotReturnAltValueRecursively(final DiAndNode startingNode) {
        final NodeStep nodeStep = findPathThatDoesNotReturnAltValueRecursivelyImpl(
                startingNode, nodeToPathThatDoesNotReturnAltValueWithPreserveUnknownFalseMap, this::getAllStepsWithPreserveUnknownFalse);
        if(nodeStep != null) {
            return nodeStep;
        }
        return findPathThatDoesNotReturnAltValueRecursivelyImpl(
                startingNode, nodeToPathThatDoesNotReturnAltValueWithPreserveUnknownBothMap, this::getAllSteps);
    }

    @Nullable
    private NodeStep findPathThatDoesNotReturnAltValueRecursivelyImpl(
            final DiAndNode startingNode, final Map<DiAndNode, NodeStep> memoizationMap,
            final Function<DiAndNode, List<NodeStep>> stepProvider) {
        if(memoizationMap.containsKey(startingNode)) {
            return memoizationMap.get(startingNode);
        }

        // Search for an alt flow that does not throw.
        final List<NodeStep> nodeSteps = stepProvider.apply(startingNode);
        for(final NodeStep nodeStep : nodeSteps) {
            final CacheValue cacheValue = computeValue(nodeStep);
            if(cacheValue.expectedException() == null && !isAltValue(cacheValue.returnOutcome())) {
                memoizationMap.put(startingNode, nodeStep);
                return nodeStep;
            }
        }

        // There is no direct path to the target flow. Search for an indirect path recursively.
        final Deque<StackFrame> stack = new LinkedList<>();
        final StackFrame firstFrame = new StackFrame(startingNode, stepProvider.apply(startingNode), this::computeValue);
        stack.push(firstFrame);
        while(!stack.isEmpty()) {
            final StackFrame currentFrame = stack.peek();
            if(currentFrame.getCurrentCacheValue().expectedException() == null && !isAltValue(currentFrame.getCurrentCacheValue().returnOutcome())) {
                // The stack contains the solution. break.
                break;
            }

            if(currentFrame.hasNextDiHitAfter()) {
                final DiAndNode nextDi = currentFrame.getNextDiHitAfter();
                // Check memoization map for an existing path from the current node to the target flow.
                if(memoizationMap.containsKey(nextDi)) {
                    if(memoizationMap.get(nextDi) == null) {
                        // We have already determined that there is no path to the target flow that includes this node.
                        continue;
                    } else {
                        // There is a path from the current node to the target flow. The path to the current node is
                        // in the stack. Break.
                        break;
                    }
                }
                if(passesThroughFinallyBlock(nextDi.node())) {
                    continue;
                }
                final StackFrame nextFrame = new StackFrame(nextDi, stepProvider.apply(nextDi), this::computeValue);
                stack.push(nextFrame);
                continue;
            }

            // We have exhausted the current nodes. Move to the next decision.
            if(currentFrame.hasNextDecision()) {
                currentFrame.advanceToNextDecision();
                continue;
            }

            // There are no more decisions.
            memoizationMap.put(currentFrame.getDiAndNode(), null);
            stack.pop();
        }

        while(!stack.isEmpty()) {
            final StackFrame currentFrame = stack.pop();
            memoizationMap.put(currentFrame.getDiAndNode(), currentFrame.getCurrentNodeStep());
        }
        return memoizationMap.get(startingNode);
    }

    private boolean allValuePathsDoNotThrow(final DiAndNode nextDi) {
        final List<NodeDecision> valueDecisions = getValueDecisions(nextDi);
        for(final NodeDecision nodeDecision : valueDecisions) {
            final CacheValue cacheValue = computeValue(nodeDecision);
            if(cacheValue.expectedException() != null) {
                return false;
            }
        }
        return true;
    }

    private boolean passesThroughCatchBlock(final Node node) {
        if(node == null) {
            return false;
        }
        // If we've already computed the value for this node, return it.
        final Boolean passesThroughCatchBlock = node.getPassesThroughCatchBlock();
        if(passesThroughCatchBlock != null) {
            return passesThroughCatchBlock;
        }

        // If a node in the chain passes through a catch block, we do as well.
        // Set our value to true and return it.
        boolean nextNodePassesThroughCatchBlock = passesThroughCatchBlock(node.getNext());
        if(nextNodePassesThroughCatchBlock) {
            node.setPassesThroughCatchBlock(true);
            return true;
        }

        // The next nodes do not pass through catch blocks.
        // Determine if we pass through a catch block.
        if(isInCatchBlock(node.getElement())) {
            node.setPassesThroughCatchBlock(true);
            return true;
        }

        // We do not pass through a catch block. Store and return the result.
        node.setPassesThroughCatchBlock(false);
        return false;
    }

    private boolean passesThroughFinallyBlock(final Node node) {
        if(node == null) {
            return false;
        }
        // If we've already computed the value for this node, return it.
        final Boolean passesThroughFinallyBlock = node.getPassesThroughFinallyBlock();
        if(passesThroughFinallyBlock != null) {
            return passesThroughFinallyBlock;
        }

        // If a node in the chain passes through a finally block, we do as well.
        // Set our value to true and return it.
        boolean nextNodePassesThroughFinallyBlock = passesThroughFinallyBlock(node.getNext());
        if(nextNodePassesThroughFinallyBlock) {
            node.setPassesThroughFinallyBlock(true);
            return true;
        }

        // The next nodes do not pass through finally blocks.
        // Determine if we pass through a finally block.
        if(isInFinallyBlock(node.getElement())) {
            node.setPassesThroughFinallyBlock(true);
            return true;
        }

        // We do not pass through a finally block. Store and return the result.
        node.setPassesThroughFinallyBlock(false);
        return false;
    }

    private boolean isInCatchBlock(final PsiElement element) {
        return PsiTreeUtil.getParentOfType(element, PsiCatchSection.class, true, PsiClass.class) != null;
    }

    public static boolean isInFinallyBlock(@NotNull PsiElement element) {
        PsiElement currentElement = element;
        while(true) {
            final PsiTryStatement tryStatement = PsiTreeUtil.getParentOfType(currentElement, PsiTryStatement.class, true, PsiClass.class);
            if(tryStatement == null) {
                return false;
            }
            final PsiCodeBlock finallyBlock = tryStatement.getFinallyBlock();
            if(finallyBlock != null) {
                if(PsiTreeUtil.isAncestor(finallyBlock, currentElement, true)) {
                    return true;
                }
            }
            currentElement = tryStatement;
        }
    }

    private FilteredDIsHitAfterInfo computeFilteredDisAfter(final NodeDecision nodeDecision) {
        final CacheValue startingCacheValue = computeValue(nodeDecision);
        final DiAndNodeSet nodesRemovedByPreviousDecisions = computeNodesRemovedByDecision(nodeDecision);
        final DiAndNodeSet disHitAfter = new DiAndNodeSet();
        DiAndNodeSet selectedDisHitAfter = startingCacheValue.disHitAfter();
        MethodResult selectedMethodResult = new MethodResult(startingCacheValue.returnOutcome(), startingCacheValue.expectedException());
        Iterator<DiAndNode> currentWorkingDisHitAfter = selectedDisHitAfter.iterator();
        while(currentWorkingDisHitAfter.hasNext()) {
            final DiAndNode currentNode = currentWorkingDisHitAfter.next();
            if(selectedMethodResult.expectedException() == null && allPathsThrow(currentNode)) {
                // If we don't have an expected exception and all paths throw, don't add the node.
                continue;
            }

            if(!isMockedFirstCallstackNode(currentNode)) {
                disHitAfter.add(currentNode);
                continue;
            }

            final List<NodeDecision> allDecisionsForCurrentNode = getAllDecisions(currentNode);
            if(allDecisionsForCurrentNode.size() <= 1) {
                disHitAfter.add(currentNode);
                continue;
            }

            // Ensure all DIs after the current one are accounted for in at least one alternate flow for the current
            // node.
            final DiAndNodeSet itemsAfterCurrentNode = selectedDisHitAfter.itemsAfter(currentNode);
            final DiAndNodeSet allFollowupDisInAllPaths = new DiAndNodeSet();
            for(final NodeDecision possibleDecision : allDecisionsForCurrentNode) {
                final CacheValue decisionCacheValue = computeValue(currentNode.internalDependencyInteraction(), currentNode.node(), possibleDecision.exceptionToThrow(), possibleDecision.returnOutcome(), false);
                allFollowupDisInAllPaths.addAll(decisionCacheValue.disHitAfter());
            }

            if(!allDisAccountedFor(itemsAfterCurrentNode, allFollowupDisInAllPaths)) {
                // If this node is hit, some of the DIs will not be accounted for. Just add the node and continue on.
                disHitAfter.add(currentNode);
                continue;
            }

            // Ensure our current decision does not add back DIs that were eliminated by previous decisions.
            final NodeDecision currentValueDecision = allDecisionsForCurrentNode.get(0);
            final CacheValue decisionCacheValue = computeValue(currentNode.internalDependencyInteraction(), currentNode.node(), currentValueDecision.exceptionToThrow(), currentValueDecision.returnOutcome(), false);
            final DiAndNodeSet decisionDisHitAfter = decisionCacheValue.disHitAfter();
            if(decisionDisHitAfter.containsAny(nodesRemovedByPreviousDecisions)) {
                disHitAfter.add(currentNode);
                continue;
            }

            // Determine if we should use the new decision.
            final MethodResult decisionResult = new MethodResult(decisionCacheValue.returnOutcome(), decisionCacheValue.expectedException());
            final StepDecision nextStep = determineNextStep(currentValueDecision, selectedMethodResult, decisionResult);
            switch(nextStep) {
                case JustAddDi:
                    disHitAfter.add(currentNode);
                    continue;
                case UseNewDIsHitAfter:
                    disHitAfter.add(currentNode);
                    nodesRemovedByPreviousDecisions.addAll(computeNodesRemovedByDecision(currentValueDecision));
                    selectedDisHitAfter = decisionCacheValue.disHitAfter();
                    currentWorkingDisHitAfter = selectedDisHitAfter.iterator();
                    continue;
                default:
                    disHitAfter.add(currentNode);
                    nodesRemovedByPreviousDecisions.addAll(computeNodesRemovedByDecision(currentValueDecision));
                    selectedDisHitAfter = decisionCacheValue.disHitAfter();
                    currentWorkingDisHitAfter = selectedDisHitAfter.iterator();
                    selectedMethodResult = decisionResult;
            }
        }
        return new FilteredDIsHitAfterInfo(disHitAfter, selectedMethodResult.expectedException(), selectedMethodResult.returnOutcome());
    }

    private boolean isMockedFirstCallstackNode(final DiAndNode currentNode) {
        if(!currentNode.isFirstCallstackNodeForDi()) {
            return false;
        }
        final InternalDependencyInteraction di = currentNode.internalDependencyInteraction();
        if(!di.canBeFirstCallstackNode()) {
            return false;
        }
        if(depConfig == null) {
            return true;
        }
        return depConfig.mockedFields().contains(di.getPsiField());
    }

    private boolean allDisAccountedFor(final DiAndNodeSet itemsAfterCurrentNode, final DiAndNodeSet allFollowupDisInAllPaths) {
        for(final DiAndNode diAndNode : itemsAfterCurrentNode) {
            if(allFollowupDisInAllPaths.contains(diAndNode)) {
                continue;
            }
            if(allPathsThrow(diAndNode)) {
                continue;
            }
            if(allFollowupDisInAllPaths.contains(diAndNode.internalDependencyInteraction())) {
                continue;
            }
            return false;
        }
        return true;
    }

    private StepDecision determineNextStep(final NodeDecision currentValueDecision, final MethodResult currentResult, final MethodResult newResult) {
        final PsiType newException = newResult.expectedException();
        final PsiType currentException = currentResult.expectedException();
        if(currentException != null) {
            if(exceptionsSame(currentException, newException)) {
                return StepDecision.UseNewMethodResult;
            }
            return StepDecision.JustAddDi;
        }

        final ReturnOutcome currentOutcome = currentResult.returnOutcome();
        if(currentOutcome == ReturnOutcome.Unknown || currentOutcome == ReturnOutcome.Void) {
            return StepDecision.UseNewMethodResult;
        }

        final ReturnOutcome newOutcome = newResult.returnOutcome();
        if(newOutcome == ReturnOutcome.Unknown || newOutcome == ReturnOutcome.Void) {
            return StepDecision.UseNewDIsHitAfter;
        }

        // At this point, we're going from:
        // Value -> Value (possibly the same value)
        // If we're going from Value -> SameValue, use the new result.
        if(currentOutcome != null && currentOutcome == newOutcome) {
            return StepDecision.UseNewMethodResult;
        }

        if(currentOutcome == ReturnOutcome.Other) {
            if(newException != null) {
                return StepDecision.UseNewMethodResult;
            }
            if(RefinedValues.contains(newOutcome) && !allValueDecisionsReturnOutcome(currentValueDecision, newOutcome)) {
                return StepDecision.UseNewMethodResult;
            }
        }
        return StepDecision.JustAddDi;
    }

    private boolean allValueDecisionsReturnOutcome(final NodeDecision currentValueDecision, final ReturnOutcome newOutcome) {
        final List<NodeDecision> valueDecisions = getValueDecisions(currentValueDecision.diAndNode());
        for(final NodeDecision valueDecision : valueDecisions) {
            final CacheValue cacheValue = computeValue(valueDecision);
            if(cacheValue.returnOutcome() != newOutcome) {
                return false;
            }
        }
        return true;
    }

    private DiAndNodeSet computeNodesRemovedByDecision(final NodeDecision nodeDecision) {
        final List<NodeDecision> allPossibleDecisions = getAllDecisions(nodeDecision.diAndNode());
        final DiAndNodeSet allPossibleDisHitAfter = new DiAndNodeSet();
        for(final NodeDecision possibleDecision : allPossibleDecisions) {
            allPossibleDisHitAfter.addAll(computeValue(possibleDecision).disHitAfter());
        }
        final DiAndNodeSet ourDisHitAfter = computeValue(nodeDecision).disHitAfter();
        allPossibleDisHitAfter.removeAll(ourDisHitAfter);
        return allPossibleDisHitAfter;
    }

    @NotNull
    private CacheValue computeValue(final NodeStep nodeStep) {
        final NodeDecision nodeDecision = nodeStep.nodeDecision();
        final DiAndNode diAndNode = nodeDecision.diAndNode();
        return computeValue(diAndNode.internalDependencyInteraction(), diAndNode.node(), nodeDecision.exceptionToThrow(), nodeDecision.returnOutcome(), nodeStep.preserveUnknown());
    }

    @NotNull
    private CacheValue computeValue(final NodeDecision nodeDecision) {
        final DiAndNode diAndNode = nodeDecision.diAndNode();
        return computeValue(diAndNode.internalDependencyInteraction(), diAndNode.node(), nodeDecision.exceptionToThrow(), nodeDecision.returnOutcome(), false);
    }

    @NotNull
    private CacheValue computeValue(
            final InternalDependencyInteraction di,
            final Node currentNode, final PsiType exception, final ReturnOutcome startingOutcome,
            final boolean preserveUnknown) {
        // Check to see if we've computed the value before.
        final PsiElement currentNodeElement = currentNode.getElement();
        final String exceptionCanonicalText = exception != null ? exception.getCanonicalText() : null;
        final CacheKey cacheKey = new CacheKey(
                currentNode, di.getPsiField(),
                di.getPsiMethod(),
                exceptionCanonicalText, startingOutcome, preserveUnknown);
        CacheValue cacheValue = cachedValues.get(cacheKey);
        if(cacheValue != null) {
            return cacheValue;
        }
        // If this is a root node, compute the value and return.
        final Node nextNode = currentNode.getNext();
        if(nextNode == null) {
            cacheValue = computeValueForRootNode(di, currentNode, exception, startingOutcome, preserveUnknown);
            cachedValues.put(cacheKey, cacheValue);
            return cacheValue;
        }
        // This is not a root node. Compute the outcome based on the outcome from the next node in the callstack.
        final CacheValue nextNodeValue = computeValue(di, nextNode, exception, startingOutcome, preserveUnknown);
        final DiAndNodeSet allDis = sourceMethodToFlatDisMap.get(currentNode.getSourceMethodContainingElement());
        final DiAndNodeSet disAfter = allDis.itemsAfter(currentNode);
        final DiAndNodeSet disHitAfter = new DiAndNodeSet();
        // Add the DIs hit after in the next node.
        addDisThatWeRecognize(disHitAfter, currentNode, allDis, nextNodeValue.disHitAfter());
        PsiType currentException = nextNodeValue.expectedException();
        if(currentException == null) {
            // We do not have a propagating exception.
            // Compute the followup info.
            final RValueInfo followupInfo = followupInfoProvider.getFollowupInfoForExpression(
                    currentNode.getSourceMethodContainingElement(), currentNodeElement,
                    nextNodeValue.returnOutcome(), preserveUnknown);
            final PsiType exceptionThrown = followupInfo.getExceptionThrown();
            if(exceptionThrown == null) {
                final List<DiAndNode> disToAdd = getDisInBlocks(
                        disAfter, followupInfo.getElementsContainingDisHit(), followupInfo.getMethodCallElements());
                disHitAfter.addAll(disToAdd);
                cacheValue = new CacheValue(disHitAfter, null, followupInfo.getReturnOutcome());
                cachedValues.put(cacheKey, cacheValue);
                return cacheValue;
            }

            final List<DiAndNode> disToAdd = getDisInBlocks(disAfter, followupInfo.getElementsContainingDisHit(), followupInfo.getMethodCallElements());
            disHitAfter.addAll(disToAdd);
            cacheValue = computeValueForNode(currentNode, followupInfo.getElementThatThrows(), disAfter, disHitAfter, exceptionThrown, preserveUnknown);
            cachedValues.put(cacheKey, cacheValue);
            return cacheValue;
        }

        // We have a propagating exception.
        cacheValue = computeValueForNode(currentNode, currentNodeElement, disAfter, disHitAfter, currentException, preserveUnknown);
        cachedValues.put(cacheKey, cacheValue);
        return cacheValue;
    }

    private void addDisThatWeRecognize(
            final DiAndNodeSet existingDisHitAfter,
            final Node currentNode, final DiAndNodeSet allOurDis, final DiAndNodeSet newDis) {
        for(final DiAndNode newDi : newDis) {
            final Node nextNode = newDi.node();
            final Node possibleCurrentNode = new Node(
                    currentNode.getSourceMethodContainingElement(), currentNode.getElement(),
                    nextNode.getEndElement(), -1, nextNode);
            final DiAndNode possibleNewDi = new DiAndNode(newDi.internalDependencyInteraction(), possibleCurrentNode);
            final DiAndNode diToUse = allOurDis.get(possibleNewDi);
            if(diToUse != null) {
                existingDisHitAfter.add(diToUse);
            }
        }
    }

    @NotNull
    private CacheValue computeValueForNode(
            final Node currentNode, final PsiElement startingElement, final DiAndNodeSet disAfter,
            final DiAndNodeSet disHitAfter, PsiType currentException, final boolean preserveUnknown) {
        final PsiMethod callstackMethod = currentNode.getSourceMethodContainingElement();
        ParentInfo parentInfo = getNextImportantParent(startingElement);
        while(parentInfo != null) {
            if(parentInfo instanceof final TryInfo tryInfo) {
                final TryElementType tryElementType = tryInfo.getTryElementType();
                if(tryElementType == TryElementType.FinallyBlock) {
                    // The throwing DI is in a finally block.
                    parentInfo = getNextImportantParent(tryInfo.getTryStatement());
                    continue;
                }
                if(tryElementType == TryElementType.TryResourcesStatement || tryElementType == TryElementType.TryBody || tryElementType == TryElementType.CatchBlock) {
                    // We're in the try-resources statement list.
                    if(currentException == null) {
                        parentInfo = getNextImportantParent(tryInfo.getTryStatement());
                        continue;
                    }
                    // If the throwing DI is NOT in a catch block, find the catch block that is hit.
                    // Determine exit flow (rethrown exception or return).
                    if(tryElementType != TryElementType.CatchBlock) {
                        final PsiCatchSection catchSection = getCatchSection(tryInfo.getTryStatement(), currentException);
                        if(catchSection != null) {
                            final RValueInfo followupInfoForHitCatchSection = followupInfoProvider.getFollowupInfoForHitCatchSection(callstackMethod, catchSection, currentException, preserveUnknown);
                            disHitAfter.addAll(getDisInBlocks(disAfter, followupInfoForHitCatchSection.getElementsContainingDisHit(), followupInfoForHitCatchSection.getMethodCallElements()));
                            final PsiType exceptionThrown = followupInfoForHitCatchSection.getExceptionThrown();
                            final PsiElement elementThatThrows = followupInfoForHitCatchSection.getElementThatThrows();
                            if(exceptionThrown != null) {
                                currentException = exceptionThrown;
                                if(PsiTreeUtil.isAncestor(catchSection, elementThatThrows, false)) {
                                    // The element that throws is inside the catch block. We are exiting the catch
                                    // block with this exception. Add dependency interactions in the finally block.
                                    // Check the finally block to see if it overrides the try outcome; i.e. has a
                                    // jump point like: return, continue, break, etc.
                                    final PsiCodeBlock finallyBlock = tryInfo.getTryStatement().getFinallyBlock();
                                    if(finallyBlock != null) {
                                        final PsiStatement jumpPoint = getJumpPoint(finallyBlock);
                                        if(jumpPoint != null) {
                                            // There is a jump point (return, break, continue, etc) inside the finally
                                            // block. This is very bad practice but still allowed by the Java language
                                            // spec.
                                            final List<PsiElement> statementsBeforeJumpPoint = getElementsBefore(finallyBlock.getStatements(), jumpPoint);
                                            final List<DiAndNode> disBeforeJumpPoint = getDisInBlocks(disAfter, statementsBeforeJumpPoint, Collections.emptyList());
                                            disHitAfter.addAll(disBeforeJumpPoint);
                                            final RValueInfo jumpPointOutcome = followupInfoProvider.getFollowupInfoForStatement(callstackMethod, jumpPoint, preserveUnknown);
                                            final PsiType jumpPointException = jumpPointOutcome.getExceptionThrown();
                                            final PsiElement jumpPointElementThatThrows = jumpPointOutcome.getElementThatThrows();
                                            disHitAfter.addAll(getDisInBlocks(disAfter, jumpPointOutcome.getElementsContainingDisHit(), jumpPointOutcome.getMethodCallElements()));
                                            if(jumpPointException != null) {
                                                currentException = jumpPointException;
                                                parentInfo = getNextImportantParent(jumpPointElementThatThrows);
                                                continue;
                                            }
                                            return new CacheValue(disHitAfter, null, jumpPointOutcome.getReturnOutcome());
                                        } else {
                                            disHitAfter.addAll(getDisInBlock(disAfter, finallyBlock));
                                        }
                                    }
                                    // There is either no finally block or the finally block just performs cleanup
                                    // tasks (like it should).
                                    parentInfo = getNextImportantParent(tryInfo.getTryStatement());
                                    continue;
                                }
                                // The element that throws is outside of the catch block.
                                parentInfo = getNextImportantParent(elementThatThrows);
                                continue;
                            }
                            // The exception was swallowed.
                            return new CacheValue(disHitAfter, null, followupInfoForHitCatchSection.getReturnOutcome());
                        }
                        // The exception is not caught. It is still propagating.
                        // Inspect the finally block to see if it overrides the exception.
                        // Then continue on.
                        final PsiCodeBlock finallyBlock = tryInfo.getTryStatement().getFinallyBlock();
                        if(finallyBlock != null) {
                            final PsiStatement jumpPoint = getJumpPoint(finallyBlock);
                            if(jumpPoint != null) {
                                // There is a jump point (return, break, continue, etc) inside the finally
                                // block. This is very bad practice but still allowed by the Java language
                                // spec.
                                final List<PsiElement> statementsBeforeJumpPoint = getElementsBefore(finallyBlock.getStatements(), jumpPoint);
                                final List<DiAndNode> disBeforeJumpPoint = getDisInBlocks(disAfter, statementsBeforeJumpPoint, Collections.emptyList());
                                disHitAfter.addAll(disBeforeJumpPoint);
                                final RValueInfo jumpPointOutcome = followupInfoProvider.getFollowupInfoForStatement(callstackMethod, jumpPoint, preserveUnknown);
                                final PsiType jumpPointException = jumpPointOutcome.getExceptionThrown();
                                final PsiElement jumpPointElementThatThrows = jumpPointOutcome.getElementThatThrows();
                                disHitAfter.addAll(getDisInBlocks(disAfter, jumpPointOutcome.getElementsContainingDisHit(), jumpPointOutcome.getMethodCallElements()));
                                if(jumpPointException != null) {
                                    currentException = jumpPointException;
                                    parentInfo = getNextImportantParent(jumpPointElementThatThrows);
                                    continue;
                                }
                                return new CacheValue(disHitAfter, null, jumpPointOutcome.getReturnOutcome());
                            } else {
                                disHitAfter.addAll(getDisInBlock(disAfter, finallyBlock));
                            }
                        }
                        // There is either no finally block or no jump point. The exception continues propagating.
                        parentInfo = getNextImportantParent(tryInfo.getTryStatement());
                        continue;
                    }

                    // The throwing element is in the catch block.
                    // Inspect the finally block to see if the exception is overridden.
                    final PsiCodeBlock finallyBlock = tryInfo.getTryStatement().getFinallyBlock();
                    if(finallyBlock != null) {
                        final PsiStatement jumpPoint = getJumpPoint(finallyBlock);
                        if(jumpPoint != null) {
                            // There is a jump point (return, break, continue, etc) inside the finally
                            // block. This is very bad practice but still allowed by the Java language
                            // spec.
                            final List<PsiElement> statementsBeforeJumpPoint = getElementsBefore(finallyBlock.getStatements(), jumpPoint);
                            final List<DiAndNode> disBeforeJumpPoint = getDisInBlocks(disAfter, statementsBeforeJumpPoint, Collections.emptyList());
                            disHitAfter.addAll(disBeforeJumpPoint);
                            final RValueInfo jumpPointOutcome = followupInfoProvider.getFollowupInfoForStatement(callstackMethod, jumpPoint, preserveUnknown);
                            final PsiType jumpPointException = jumpPointOutcome.getExceptionThrown();
                            final PsiElement jumpPointElementThatThrows = jumpPointOutcome.getElementThatThrows();
                            disHitAfter.addAll(getDisInBlocks(disAfter, jumpPointOutcome.getElementsContainingDisHit(), jumpPointOutcome.getMethodCallElements()));
                            if(jumpPointException != null) {
                                currentException = jumpPointException;
                                parentInfo = getNextImportantParent(jumpPointElementThatThrows);
                                continue;
                            }
                            return new CacheValue(disHitAfter, null, jumpPointOutcome.getReturnOutcome());
                        } else {
                            disHitAfter.addAll(getDisInBlock(disAfter, finallyBlock));
                        }
                    }
                    // There is either no finally block or no jump point. The exception continues propagating.
                    parentInfo = getNextImportantParent(tryInfo.getTryStatement());
                    continue;
                }
            }

            if(parentInfo instanceof MethodCallInfo) {
                final PsiMethodCallExpression psiMethodCallExpression = ((MethodCallInfo) parentInfo).getMethodCallExpression();
                final PsiMethod psiMethod = psiMethodCallExpression.resolveMethod();
                if(psiMethod == null) {
                    parentInfo = getNextImportantParent(psiMethodCallExpression);
                    continue;
                }
                final PsiExpression[] argExpressions = psiMethodCallExpression.getArgumentList().getExpressions();
                final int argIndex = getIndexOfElementThatContainsElement(argExpressions, startingElement);
                if(argIndex == -1) {
                    parentInfo = getNextImportantParent(psiMethodCallExpression);
                    continue;
                }
                final PsiParameter[] params = psiMethod.getParameterList().getParameters();
                if(argIndex >= params.length) {
                    parentInfo = getNextImportantParent(psiMethodCallExpression);
                    continue;
                }
                if(!isAsyncCall(psiMethodCallExpression)) {
                    parentInfo = getNextImportantParent(psiMethodCallExpression);
                    continue;
                }
                if(!isAsyncType(argExpressions[argIndex].getType()) && !isAsyncType(params[argIndex].getType())) {
                    // Either the formal param or the actual param must be an async type.
                    parentInfo = getNextImportantParent(psiMethodCallExpression);
                    continue;
                }

                // We are being passed to an async method.
                // Add async params after this one.
                final List<PsiExpression> asyncArgs = getAsyncArgsAfter(argExpressions, params, argIndex);
                disHitAfter.addAll(getDisInBlocks(disAfter, asyncArgs, Collections.emptyList()));
                // Compute the followup info.
                final RValueInfo followupInfo = followupInfoProvider.getFollowupInfoForExpression(callstackMethod, psiMethodCallExpression, ReturnOutcome.Failure, false, preserveUnknown);
                disHitAfter.addAll(getDisInBlocks(disAfter, followupInfo.getElementsContainingDisHit(), followupInfo.getMethodCallElements()));
                final PsiType exceptionThrown = followupInfo.getExceptionThrown();
                final PsiElement elementThatThrows = followupInfo.getElementThatThrows();
                if(exceptionThrown != null) {
                    currentException = exceptionThrown;
                    parentInfo = getNextImportantParent(elementThatThrows);
                    continue;
                }
                return new CacheValue(disHitAfter, null, followupInfo.getReturnOutcome());
            }

            if(parentInfo instanceof MethodInfo) {
                final PsiMethod parentMethod = ((MethodInfo) parentInfo).getMethod();
                if(parentMethod == callstackMethod) {
                    return new CacheValue(disHitAfter, currentException, ReturnOutcome.Other);
                }
                parentInfo = getNextImportantParent(parentMethod);
                continue;
            }
            parentInfo = getNextImportantParent(parentInfo.getElement());
        }
        return new CacheValue(disHitAfter, currentException, ReturnOutcome.Other);
    }

    private List<PsiExpression> getAsyncArgsAfter(final PsiExpression[] argExpressions, final PsiParameter[] params, final int argIndex) {
        if(argIndex == -1 || argIndex == argExpressions.length - 1) {
            return Collections.emptyList();
        }
        final List<PsiExpression> ret = new ArrayList<>();
        for(int i = argIndex + 1; i < argExpressions.length && i < params.length; i++) {
            if(isAsyncType(argExpressions[i].getType()) || isAsyncType(params[i].getType())) {
                ret.add(argExpressions[i]);
            }
        }
        return ret;
    }

    private PsiStatement getJumpPoint(final PsiCodeBlock finallyBlock) {
        if(finallyBlock == null) {
            return null;
        }
        for(final PsiStatement psiStatement : finallyBlock.getStatements()) {
            if(psiStatement instanceof PsiReturnStatement || psiStatement instanceof PsiBreakStatement
                    || psiStatement instanceof PsiContinueStatement || psiStatement instanceof PsiYieldStatement
                    || psiStatement instanceof PsiThrowStatement) {
                return psiStatement;
            }
        }
        return null;
    }

    public static StatementStepInfo getNextStatementForCatch(final PsiElement callstackMethod, final PsiCatchSection catchSection) {
        final PsiTryStatement tryStatementToEnter = catchSection.getTryStatement();
        PsiCodeBlock blockToUse = catchSection.getCatchBlock();
        if(blockToUse != null) {
            final PsiStatement[] statements = blockToUse.getStatements();
            if(statements.length != 0) {
                return new StatementStepInfo(statements[0], Collections.emptyList());
            }
        }

        // Try to get the first statement in the finally block.
        blockToUse = tryStatementToEnter.getFinallyBlock();
        if(blockToUse != null) {
            final PsiStatement[] statements = blockToUse.getStatements();
            if(statements.length != 0) {
                return new StatementStepInfo(statements[0], Collections.emptyList());
            }
        }

        // Use the next sequential statement after the try statement.
        return getNextSequentialStatement(callstackMethod, tryStatementToEnter);
    }

    private CacheValue computeValueForRootNode(
            final InternalDependencyInteraction di, final Node currentNode,
            final PsiType exception, final ReturnOutcome startingOutcome, final boolean preserveUnknown) {
        if(startingOutcome != null) {
            if(startingOutcome == ReturnOutcome.BrokenIO) {
                return computeValueForRootNodeWithBrokenIO(di, currentNode, preserveUnknown);
            } else {
                return computeValueForRootNodeWithStartingOutcome(currentNode, startingOutcome, preserveUnknown);
            }
        }
        return computeValueForRootNodeWithException(currentNode, exception, preserveUnknown);
    }

    private CacheValue computeValueForRootNodeWithBrokenIO(
            final InternalDependencyInteraction di, final Node currentNode, final boolean preserveUnknown) {
        // If we have a try-block whose body or resources statements contain currentNode,
        // Inspect the catch blocks.
        // If one of them catches an IOException, treat it as though this line throws.
        final PsiMethod callstackMethod = currentNode.getSourceMethodContainingElement();
        final PsiType ioException = getIOException();
        if(ioException == null) {
            // This shouldn't happen. IOException should always exist, because it is part of the JDK.
            return getDefaultCacheValueForUnknownOutcome(new DiAndNode(di, currentNode), callstackMethod);
        }
        final PsiTryStatement containingTry = getTryWithBodyOrResourceContaining(currentNode);
        if(containingTry != null) {
            // Search for catch block that catches IOException.
            final PsiCatchSection catchSection = getCatchSection(containingTry, ioException);
            if(catchSection != null) {
                // We have a catch block that handles the IOException. Treat the DI call as though it throws an
                // IOException.
                return computeValueWithIOException(currentNode, callstackMethod, ioException, preserveUnknown);
            }
        }

        // We do not have a try-block with a catch clause that handles an IOException.
        // If the method declares an IOException, treat the DI call as though it throws an IOException.
        if(canThrowException(callstackMethod, ioException)) {
            return computeValueWithIOException(currentNode, callstackMethod, ioException, preserveUnknown);
        }

        return computeValueForRootNodeWithStartingOutcome(currentNode, ReturnOutcome.BrokenIO, preserveUnknown);
        // Else, treat the DI as though it returns UNKNOWN. Perform the DI fallback logic (assume dis after are hit).
        // return getDefaultCacheValueForUnknownOutcome(di, callstackMethod);
    }

    @NotNull
    private CacheValue getDefaultCacheValueForUnknownOutcome(final DiAndNode di, final PsiMethod callstackMethod) {
        final DiAndNodeSet dis = sourceMethodToFlatDisMap.get(callstackMethod);
        final DiAndNodeSet disAfter = dis.itemsAfter(di.node());
        return new CacheValue(disAfter, null, ReturnOutcome.Unknown);
    }

    private boolean canThrowException(final PsiMethod psiMethod, final PsiType exception) {
        final PsiClassType[] referencedTypes = psiMethod.getThrowsList().getReferencedTypes();
        for(final PsiClassType referencedType : referencedTypes) {
            if(referencedType.isAssignableFrom(exception)) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    private CacheValue computeValueWithIOException(
            final Node currentNode,
            final PsiMethod callstackMethod, final PsiType ioException,
            final boolean preserveUnknown) {
        final DiAndNodeSet dis = sourceMethodToFlatDisMap.get(callstackMethod);
        final DiAndNodeSet disAfter = dis.itemsAfter(currentNode);
        final DiAndNodeSet disHitAfter = new DiAndNodeSet();
        // Treat the current node as though it throws an IOException. The one exception is: we need to add other DIs in
        // the try-with-resource's resource section. Treating the DI as though it throws an IOException will cause us to
        // omit those.
        final PsiElement currentNodeElement = currentNode.getElement();
        if(currentNodeElement instanceof PsiMethodCallExpression) {
            final PsiResourceList containingTryResourceList = getParentOfType(currentNodeElement, PsiResourceList.class, callstackMethod);
            if(containingTryResourceList != null) {
                disHitAfter.addAll(getDisInBlock(disAfter, containingTryResourceList));
            }
        }
        return computeValueForNode(currentNode, currentNode.getElement(), disAfter, disHitAfter, ioException, preserveUnknown);
    }

    private PsiType getIOException() {
        final PsiElementFactory elementFactory = javaPsiFacade.getElementFactory();
        final PsiClass exceptionClass = javaPsiFacade.findClass("java.io.IOException", sourceClass.getResolveScope());
        if(exceptionClass == null) {
            return null;
        }
        return elementFactory.createType(exceptionClass);
    }

    private PsiTryStatement getTryWithBodyOrResourceContaining(final Node currentNode) {
        final PsiElement stoppingElement = currentNode.getSourceMethodContainingElement();
        final PsiElement startingElement = currentNode.getElement();
        PsiElement currentElement = startingElement.getParent();
        while(currentElement != null) {
            if(currentElement == stoppingElement) {
                return null;
            }
            if(currentElement instanceof final PsiTryStatement psiTryStatement) {
                final PsiCodeBlock tryBlock = psiTryStatement.getTryBlock();
                final PsiResourceList resourceList = psiTryStatement.getResourceList();
                final boolean isInBlockOrResource = PsiTreeUtil.isAncestor(resourceList, startingElement, false) || PsiTreeUtil.isAncestor(tryBlock, startingElement, false);
                if(!isInBlockOrResource) {
                    // The DI is in a catch block or the finally block.
                    return null;
                }
                return psiTryStatement;
            }
            currentElement = currentElement.getParent();
        }
        return null;
    }

    @NotNull
    private CacheValue computeValueForRootNodeWithException(final Node currentNode, final PsiType exception, final boolean preserveUnknown) {
        final DiAndNodeSet dis = sourceMethodToFlatDisMap.get(currentNode.getSourceMethodContainingElement());
        final DiAndNodeSet disAfter = dis.itemsAfter(currentNode);
        final DiAndNodeSet disHitAfter = new DiAndNodeSet();
        return computeValueForNode(currentNode, currentNode.getElement(), disAfter, disHitAfter, exception, preserveUnknown);
    }

    private CacheValue computeValueForRootNodeWithStartingOutcome(
            final Node currentNode, final ReturnOutcome startingOutcome, final boolean preserveUnknown) {
        final DiAndNodeSet dis = sourceMethodToFlatDisMap.get(currentNode.getSourceMethodContainingElement());
        final DiAndNodeSet disAfter = dis.itemsAfter(currentNode);
        final DiAndNodeSet disHitAfter = new DiAndNodeSet();
        final PsiElement currentNodeElement = currentNode.getElement();
        final RValueInfo followupInfo = followupInfoProvider.getFollowupInfoForExpression(
                currentNode.getSourceMethodContainingElement(), currentNodeElement,
                startingOutcome, preserveUnknown);
        final PsiType exceptionThrown = followupInfo.getExceptionThrown();
        if(exceptionThrown == null) {
            final List<DiAndNode> disToAdd = getDisInBlocks(
                    disAfter, followupInfo.getElementsContainingDisHit(), followupInfo.getMethodCallElements());
            disHitAfter.addAll(disToAdd);
            return new CacheValue(disHitAfter, null, followupInfo.getReturnOutcome());
        }

        final List<DiAndNode> disToAdd = getDisInBlocks(disAfter, followupInfo.getElementsContainingDisHit(), followupInfo.getMethodCallElements());
        disHitAfter.addAll(disToAdd);
        return computeValueForNode(currentNode, followupInfo.getElementThatThrows(), disAfter, disHitAfter, exceptionThrown, preserveUnknown);
    }

    private boolean isAsyncCall(final PsiMethodCallExpression methodCallExpression) {
        if(methodCallExpression == null) {
            // This shouldn't happen.
            return false;
        }
        final PsiMethod calledMethod = methodCallExpression.resolveMethod();
        if(calledMethod == null) {
            // IntelliJ can't find the called method. Assume it is not async.
            return false;
        }
        // If the called method is inside a class that is a java.util.concurrent.Future, assume the method is async.
        final PsiClass containingClass = calledMethod.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        if(isAsyncClass(containingClass)) {
            return true;
        }

        // If the call expression's type is a subclass of java.util.concurrent.Future, assume the method is async.
        final PsiType methodCallExpressionType = methodCallExpression.getType();
        if(methodCallExpressionType == null) {
            return false;
        }
        final PsiClass methodCallExpressionClass = PsiUtil.resolveClassInType(methodCallExpressionType);
        if(methodCallExpressionClass == null) {
            return false;
        }
        if(isAsyncClass(methodCallExpressionClass)) {
            return true;
        }
        return false;
    }

    private boolean isAsyncClass(final PsiClass containingClass) {
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName != null) {
            if(AsyncClasses.contains(qualifiedName)) {
                return true;
            }
        }
        final LinkedHashSet<PsiClass> superClasses = InheritanceUtil.getSuperClasses(containingClass);
        for(final PsiClass superClass : superClasses) {
            final String superClassName = superClass.getQualifiedName();
            if(superClassName != null) {
                if(AsyncClasses.contains(superClassName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isAbsentInitExpression(final PsiExpression expression) {
        PsiExpression expressionToUse = removeExtrasFromReturn(expression);
        if(expressionToUse == null) {
            return false;
        }

        // Method Names: empty(), absent(), no args.
        // Containing class: Starts with Optional (covers OptionalInt, etc).
        if(expressionToUse instanceof PsiMethodCallExpression) {
            final PsiMethod psiMethod = ((PsiMethodCallExpression) expressionToUse).resolveMethod();
            if(psiMethod == null) {
                return false;
            }
            if(psiMethod.getParameterList().getParameters().length != 0) {
                return false;
            }
            if(!AbsentMethodNames.contains(psiMethod.getName())) {
                return false;
            }
            final PsiClass containingClass = psiMethod.getContainingClass();
            if(containingClass == null) {
                return false;
            }
            return StringUtils.startsWith(containingClass.getName(), "Optional");
        }
        return false;
    }

    public static boolean isListConstructorWithSingleIterableParam(final PsiMethod psiMethod) {
        if(!psiMethod.isConstructor()) {
            return false;
        }
        final PsiParameter[] methodParams = psiMethod.getParameterList().getParameters();
        if(methodParams.length != 1) {
            return false;
        }
        final PsiClass containingClass = psiMethod.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        if(!InheritanceUtil.isInheritor(containingClass, CommonClassNames.JAVA_LANG_ITERABLE)) {
            return false;
        }
        final PsiType paramType = methodParams[0].getType();
        if(!InheritanceUtil.isInheritor(paramType, CommonClassNames.JAVA_LANG_ITERABLE)) {
            return false;
        }
        return true;
    }

    public static boolean isEmptyInitExpression(final PsiExpression psiExpression) {
        PsiExpression expressionToUse = removeExtrasFromReturn(psiExpression);
        if(expressionToUse == null) {
            return false;
        }
        expressionToUse = removeOptionalOfCall(expressionToUse);
        final PsiType returnType = expressionToUse.getType();
        if(!InheritanceUtil.isInheritor(returnType, JavaNames.JavaLangIterable) && !InheritanceUtil.isInheritor(returnType, JavaNames.JavaUtilBaseStream)) {
            return false;
        }
        if(expressionToUse instanceof PsiReferenceExpression) {
            final PsiElement target = ((PsiReferenceExpression) expressionToUse).resolve();
            if(target instanceof PsiField) {
                final String fieldName = ((PsiField) target).getName();
                if(StringUtils.startsWithAny(fieldName, "EMPTY", "Empty")) {
                    return true;
                }
            }
            return false;
        }
        if(expressionToUse instanceof final PsiMethodCallExpression methodCallExpression) {
            final PsiMethod psiMethod = methodCallExpression.resolveMethod();
            if(psiMethod == null) {
                return false;
            }
            if(!psiMethod.hasModifierProperty(PsiModifier.STATIC)) {
                return false;
            }
            final PsiTypeParameterList typeParameterList = psiMethod.getTypeParameterList();
            if(typeParameterList == null) {
                return false;
            }
            if(typeParameterList.getTypeParameters().length == 0) {
                return false;
            }
            final PsiParameter[] formalParams = psiMethod.getParameterList().getParameters();
            if(formalParams.length == 0) {
                return true;
            }
            if(formalParams.length == 1) {
                if(formalParams[0].isVarArgs()) {
                    if(methodCallExpression.getArgumentList().getExpressions().length == 0) {
                        return true;
                    }
                }
            }
            return false;
        }

        if(expressionToUse instanceof final PsiNewExpression newExpression) {
            // If we have a constructor call with no args or a constructor call with an int or long return true.
            if(newExpression.getAnonymousClass() != null) {
                return false;
            }
            final PsiMethod psiMethod = newExpression.resolveConstructor();
            if(psiMethod == null) {
                return false;
            }
            final PsiParameter[] parameters = psiMethod.getParameterList().getParameters();
            if(parameters.length == 0) {
                return true;
            }
            if(parameters.length == 1) {
                if(isIntOrLong(parameters[0])) {
                    return true;
                }
                if(parameters[0].isVarArgs()) {
                    final PsiExpressionList actualParamArgsList = newExpression.getArgumentList();
                    if(actualParamArgsList == null) {
                        return true;
                    }
                    final PsiExpression[] actualParams = actualParamArgsList.getExpressions();
                    if(actualParams.length == 0) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    private static PsiExpression removeOptionalOfCall(final PsiExpression expressionToUse) {
        if(!(expressionToUse instanceof final PsiMethodCallExpression psiMethodCallExpression)) {
            return expressionToUse;
        }
        final PsiMethod psiMethod = psiMethodCallExpression.resolveMethod();
        if(psiMethod == null) {
            return expressionToUse;
        }
        final PsiClass containingClass = psiMethod.getContainingClass();
        if(containingClass == null) {
            return expressionToUse;
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName == null) {
            return expressionToUse;
        }
        if(!StringUtils.equalsAny(qualifiedName, "java.util.Optional", "com.google.common.base.Optional")) {
            return expressionToUse;
        }
        if(!StringUtils.equals(psiMethod.getName(), "of")) {
            return expressionToUse;
        }
        final PsiExpression[] args = psiMethodCallExpression.getArgumentList().getExpressions();
        if(args.length != 1) {
            return expressionToUse;
        }
        return removeExtrasFromReturn(args[0]);
    }

    public static boolean isFailedFutureInitExpression(final PsiExpression psiExpression) {
        PsiExpression expressionToUse = removeExtrasFromReturn(psiExpression);
        if(expressionToUse == null) {
            return false;
        }
        final PsiType returnType = expressionToUse.getType();
        if(expressionToUse instanceof final PsiMethodCallExpression methodCallExpression) {
            final PsiMethod psiMethod = methodCallExpression.resolveMethod();
            if(psiMethod == null) {
                return false;
            }
            if(!psiMethod.hasModifierProperty(PsiModifier.STATIC)) {
                return false;
            }
            final String methodName = psiMethod.getName();
            if(!FailureFactoryMethodNames.contains(methodName)) {
                return false;
            }
            if(!InheritanceUtil.isInheritor(returnType, JavaNames.CompletionStage)) {
                return false;
            }
            return true;
        }
        return false;
    }

    private static boolean isIntOrLong(final PsiParameter parameter) {
        final PsiType type = parameter.getType();
        final String canonicalText = type.getCanonicalText().trim();
        return StringUtils.equalsAny(canonicalText, "int", "long");
    }

    public static PsiExpression removeExtrasFromReturn(final PsiExpression returnValue) {
        if(returnValue instanceof PsiParenthesizedExpression) {
            return removeExtrasFromReturn(((PsiParenthesizedExpression) returnValue).getExpression());
        }
        if(returnValue instanceof PsiTypeCastExpression) {
            return removeExtrasFromReturn(((PsiTypeCastExpression) returnValue).getOperand());
        }
        return returnValue;
    }

    private List<DiAndNode> getDisInBlock(
            @NotNull final DiAndNodeSet disAfter,
            @NotNull final PsiElement block) {
        final List<DiAndNode> ret = new ArrayList<>();
        for(final DiAndNode di : disAfter) {
            if(isInBlock(di, block)) {
                ret.add(di);
            }
        }
        return ret;
    }

    private List<DiAndNode> getDisInBlocks(
            @NotNull final DiAndNodeSet disAfter,
            @NotNull final List<? extends PsiElement> blocks, final List<? extends PsiElement> methodCalls) {
        final List<DiAndNode> ret = new ArrayList<>();
        for(final DiAndNode di : disAfter) {
            for(final PsiElement block : blocks) {
                if(isInBlock(di, block)) {
                    ret.add(di);
                    break;
                }
            }
            for(final PsiElement methodCall : methodCalls) {
                if(isMethodCall(di, methodCall)) {
                    ret.add(di);
                    break;
                }
            }
        }
        return ret;
    }

    private boolean isMethodCall(final DiAndNode di, final PsiElement methodCall) {
        final boolean isBlockInCatchBlock = methodCall instanceof PsiCatchSection || isInCatchBlock(methodCall);
        final Node node = di.node();
        final PsiElement topOfCallStack = node.getElement();
        if(topOfCallStack == methodCall) {
            if(isBlockInCatchBlock) {
                final Node nextNode = node.getNext();
                if(!passesThroughCatchBlock(nextNode)) {
                    return true;
                }
            } else {
                if(!passesThroughCatchBlock(node)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isInBlock(final DiAndNode di, final PsiElement block) {
        final boolean isBlockInCatchBlock = block instanceof PsiCatchSection || isInCatchBlock(block);
        final Node node = di.node();
        final PsiElement topOfCallStack = node.getElement();
        if(PsiTreeUtil.isAncestor(block, topOfCallStack, false)) {
            if(isBlockInCatchBlock) {
                final Node nextNode = node.getNext();
                if(!passesThroughCatchBlock(nextNode)) {
                    return true;
                }
            } else {
                if(!passesThroughCatchBlock(node)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static PsiElement getParentClosure(final PsiElement psiElement) {
        return PsiTreeUtil.getParentOfType(psiElement, PsiMethod.class, PsiLambdaExpression.class);
    }

    public static PsiElement getParentSwitchExpression(final PsiElement psiElement) {
        return PsiTreeUtil.getParentOfType(psiElement, PsiSwitchExpression.class);
    }

    @Nullable
    private PsiCatchSection getCatchSection(@NotNull final PsiTryStatement tryStatement, @NotNull final PsiType currentException) {
        for(final PsiCatchSection catchSection : tryStatement.getCatchSections()) {
            final PsiType catchType = catchSection.getCatchType();
            if(catchType == null) {
                continue;
            }
            if(catchType.isAssignableFrom(currentException)) {
                return catchSection;
            }
        }
        return null;
    }

    @Nullable
    private ParentInfo getNextImportantParent(final PsiElement psiElement) {
        PsiElement parent = psiElement.getParent();
        while(parent != null) {
            if(parent instanceof final PsiCatchSection psiCatchSection) {
                return new TryInfo(psiCatchSection.getTryStatement(), psiCatchSection, TryElementType.CatchBlock);
            }
            if(parent instanceof final PsiTryStatement psiTryStatement) {
                // Determine if psiElement is in the try resources statement.
                final PsiResourceList resourceList = psiTryStatement.getResourceList();
                if(resourceList != null) {
                    if(PsiTreeUtil.isAncestor(resourceList, psiElement, false)) {
                        // This is a resource statement.
                        return new TryInfo(psiTryStatement, resourceList, TryElementType.TryResourcesStatement);
                    }
                }
                // Determine if psiElement is in the try body.
                final PsiCodeBlock tryBlock = psiTryStatement.getTryBlock();
                if(tryBlock != null) {
                    if(PsiTreeUtil.isAncestor(tryBlock, psiElement, false)) {
                        // This is a resource statement.
                        return new TryInfo(psiTryStatement, tryBlock, TryElementType.TryBody);
                    }
                }
                // Determine if psiElement is in the finally clause.
                final PsiCodeBlock finallyBlock = psiTryStatement.getFinallyBlock();
                if(finallyBlock != null) {
                    if(PsiTreeUtil.isAncestor(finallyBlock, psiElement, false)) {
                        // This is a resource statement.
                        return new TryInfo(psiTryStatement, finallyBlock, TryElementType.FinallyBlock);
                    }
                }
                // Do not check to see if we're in a catch block. That will have been hit earlier.
                // We shouldn't reach this point.
            }
            if(parent instanceof PsiMethodCallExpression) {
                return new MethodCallInfo((PsiMethodCallExpression) parent);
            }
            if(parent instanceof PsiMethod) {
                return new MethodInfo((PsiMethod) parent);
            }
            if(parent instanceof PsiClass) {
                return new ClassInfo((PsiClass) parent);
            }
            if(parent instanceof PsiLambdaExpression) {
                return new LambdaInfo((PsiLambdaExpression) parent);
            }
            parent = parent.getParent();
        }
        return null;
    }

    private static boolean exceptionsSame(final PsiType firstException, final PsiType secondException) {
        if(firstException == null) {
            return secondException == null;
        }
        if(secondException == null) {
            return false;
        }
        return firstException.getCanonicalText().equals(secondException.getCanonicalText());
    }
}
