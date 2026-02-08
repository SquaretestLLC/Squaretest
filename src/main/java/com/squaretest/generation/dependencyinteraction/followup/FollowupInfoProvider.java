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
package com.squaretest.generation.dependencyinteraction.followup;

import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.squaretest.generation.dependencyinteraction.DependencyInteractionCollectorUtils;
import com.squaretest.generation.dependencyinteraction.followup.processors.MethodCallProcessor;
import com.squaretest.generation.dependencyinteraction.followup.processors.NextStep;
import com.squaretest.generation.dependencyinteraction.outcomes.MethodPathsInfoProvider;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;
import com.squaretest.generation.dependencyinteraction.outcomes.ThrowInfo;
import com.squaretest.utils.LazyIdentitySetBuilder;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.squaretest.generation.dependencyinteraction.DependencyInteractionCollectorUtils.getArgsToProcessLast;
import static com.squaretest.generation.dependencyinteraction.SQExpressionUtils.*;
import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.*;
import static com.squaretest.generation.dependencyinteraction.outcomes.OutcomePopulator.*;

public class FollowupInfoProvider {

    private final JavaPsiFacade javaPsiFacade;
    private final MethodPathsInfoProvider methodPathsInfoProvider;
    private final PsiClass sourceClass;
    private final List<MethodCallProcessor> methodCallProcessors;
    private final Map<PsiElement, List<SpecialExit>> specialExitInfoCache;
    private final Map<PsiExpression, List<PsiVariableAndRef>> refsInElementCache;
    private final Map<PsiElement, Set<PsiLocalVariable>> elementToReassignedLocalVars;
    private final Map<PsiElement, Set<PsiLocalVariable>> varsWithAddCallsWithinElementCache;

    public FollowupInfoProvider(
            final JavaPsiFacade javaPsiFacade, final MethodPathsInfoProvider methodPathsInfoProvider,
            final PsiClass sourceClass, final List<MethodCallProcessor> methodCallProcessors) {
        this.javaPsiFacade = javaPsiFacade;
        this.methodPathsInfoProvider = methodPathsInfoProvider;
        this.sourceClass = sourceClass;
        this.methodCallProcessors = methodCallProcessors;
        this.specialExitInfoCache = new IdentityHashMap<>();
        this.refsInElementCache = new IdentityHashMap<>();
        this.elementToReassignedLocalVars = new IdentityHashMap<>();
        this.varsWithAddCallsWithinElementCache = new IdentityHashMap<>();
    }

    public RValueInfo getFollowupInfoForExpression(
            @NotNull final PsiMethod callstackMethod,
            @NotNull final PsiElement psiMethodCallExpression,
            @NotNull final ReturnOutcome startingReturnOutcome,
            final boolean preserveUnknown) {
        return getFollowupInfoForExpression(callstackMethod, psiMethodCallExpression, startingReturnOutcome, true, preserveUnknown);
    }

    public RValueInfo getFollowupInfoForExpression(
            @NotNull final PsiMethod callstackMethod,
            @NotNull final PsiElement psiMethodCallExpression,
            @NotNull final ReturnOutcome startingReturnOutcome,
            final boolean includeAsyncParamsInFirstMethodCall, final boolean preserveUnknown) {
        PsiElement nextImportantParent = getNextImportantParent(psiMethodCallExpression);
        PsiElement currentExpression = psiMethodCallExpression;
        ReturnOutcome currentReturnOutcome = startingReturnOutcome;
        final List<RValueInfo> closureValues = new ArrayList<>();
        while(nextImportantParent != null) {
            final List<PsiExpression> asyncParams = getAsyncParamsIfNeeded(currentExpression, includeAsyncParamsInFirstMethodCall);
            final RValueInfo closureValue = getFollowupInfoImpl(nextImportantParent, currentExpression, currentReturnOutcome, preserveUnknown);
            closureValue.addElementsContainingDisHit(asyncParams);
            closureValues.add(closureValue);
            if(closureValue.getExceptionThrown() != null) {
                return combineClosureValues(closureValues);
            }
            if(nextImportantParent == callstackMethod) {
                return combineClosureValues(closureValues);
            }
            currentExpression = nextImportantParent;
            currentReturnOutcome = closureValue.getReturnOutcome();
            nextImportantParent = getNextImportantParent(nextImportantParent);
        }
        return combineClosureValues(closureValues);
    }

    private List<PsiExpression> getAsyncParamsIfNeeded(final PsiElement startingElement, final boolean processAsyncParams) {
        if(!processAsyncParams) {
            return Collections.emptyList();
        }
        if(!(startingElement instanceof PsiMethodCallExpression)) {
            return Collections.emptyList();
        }
        return getArgsToProcessLast((PsiMethodCallExpression) startingElement);
    }

    public RValueInfo getFollowupInfoForStatement(
            @NotNull final PsiMethod callstackMethod,
            @NotNull final PsiStatement startingStatement, final boolean preserveUnknown) {
        PsiElement nextImportantParent = getNextImportantParent(startingStatement);
        PsiElement currentElement = startingStatement;
        ReturnOutcome currentReturnOutcome = ReturnOutcome.Unknown;
        final List<RValueInfo> closureValues = new ArrayList<>();
        while(nextImportantParent != null) {
            final RValueInfo closureValue;
            final List<PsiExpression> asyncParams = getAsyncParamsIfNeeded(currentElement, true);
            if(currentElement instanceof PsiStatement) {
                closureValue = getFollowupInfoForStatementImpl(nextImportantParent, (PsiStatement) currentElement, preserveUnknown);
            } else {
                closureValue = getFollowupInfoImpl(nextImportantParent, currentElement, currentReturnOutcome, preserveUnknown);
            }
            closureValue.addElementsContainingDisHit(asyncParams);
            closureValues.add(closureValue);
            if(closureValue.getExceptionThrown() != null) {
                return combineClosureValues(closureValues);
            }
            if(nextImportantParent == callstackMethod) {
                return combineClosureValues(closureValues);
            }
            currentElement = nextImportantParent;
            currentReturnOutcome = closureValue.getReturnOutcome();
            nextImportantParent = getNextImportantParent(nextImportantParent);
        }
        return combineClosureValues(closureValues);
    }

    public RValueInfo getFollowupInfoForHitCatchSection(
            @NotNull final PsiMethod callstackMethod,
            @NotNull final PsiCatchSection catchSection,
            @NotNull final PsiType currentException, final boolean preserveUnknown) {
        final PsiElement catchBlockContainingClosure = getNextImportantParent(catchSection);
        if(catchBlockContainingClosure != callstackMethod) {
            // There is a closure between us and the callstack method.
            final StatementStepInfo nextStatementInfo = getNextStatementForCatch(catchBlockContainingClosure, catchSection);
            final PsiStatement nextSequentialStatement = nextStatementInfo.statement();
            if(nextSequentialStatement == null) {
                // The catch block is empty, the exception is swallowed, and there are no more statements after us and
                // inside of the same closure.
                final ReturnOutcome defaultOutcome = getDefaultOutcome(catchBlockContainingClosure);
                final RValueInfo followupInfoForExpression = getFollowupInfoForExpression(callstackMethod, catchBlockContainingClosure, defaultOutcome, preserveUnknown);
                followupInfoForExpression.addElementsContainingDisHit(getExitedDoWhileConditions(nextStatementInfo));
                return followupInfoForExpression;
            }
            final RValueInfo outcome = getFollowupInfoForStatement(callstackMethod, nextSequentialStatement, preserveUnknown);
            if(outcome.getExceptionThrown() instanceof PsiDisjunctionType) {
                outcome.withExceptionThrown(outcome.getElementThatThrows(), currentException);
            }
            outcome.addElementsContainingDisHit(getExitedDoWhileConditions(nextStatementInfo));
            return outcome;
        }

        // There are no closures between the catch block and the callstack method.
        final StatementStepInfo nextStatementInfo = getNextStatementForCatch(callstackMethod, catchSection);
        final PsiStatement nextSequentialStatement = nextStatementInfo.statement();
        if(nextSequentialStatement == null) {
            // The catch block is empty, and there are no more statements in this method.
            final ReturnOutcome defaultOutcome = getDefaultOutcome(callstackMethod);
            return new RValueInfo().withReturnOutcome(defaultOutcome).addElementsContainingDisHit(getExitedDoWhileConditions(nextStatementInfo));
        }

        // The nextSequentialStatement is either the first statement in the catch block, finally block or outside
        //  the try block.
        final RValueInfo outcome = getFollowupInfoForStatement(callstackMethod, nextSequentialStatement, preserveUnknown);
        outcome.addElementsContainingDisHit(getExitedDoWhileConditions(nextStatementInfo));
        if(outcome.getExceptionThrown() instanceof PsiDisjunctionType) {
            outcome.withExceptionThrown(outcome.getElementThatThrows(), currentException);
        }
        return outcome;
    }

    private RValueInfo combineClosureValues(final List<RValueInfo> closureValues) {
        if(closureValues.isEmpty()) {
            return new RValueInfo().withUnknownReturnValue();
        }
        if(closureValues.size() == 1) {
            return closureValues.get(0);
        }
        final RValueInfo ret = new RValueInfo();
        for(final RValueInfo rValueInfo : closureValues) {
            ret.addElementsContainingDisHit(rValueInfo.getElementsContainingDisHit());
            ret.addMethodCallElements(rValueInfo.getMethodCallElements());
            final PsiType exceptionThrown = rValueInfo.getExceptionThrown();
            if(exceptionThrown != null) {
                ret.withExceptionThrown(rValueInfo.getElementThatThrows(), exceptionThrown);
                continue;
            }
            final ReturnOutcome returnOutcome = rValueInfo.getReturnOutcome();
            if(returnOutcome != null) {
                ret.withReturnOutcome(returnOutcome);
            }
        }
        return ret;
    }

    private PsiElement getNextImportantParent(final PsiElement startingElement) {
        if(startingElement == null) {
            return null;
        }
        PsiElement currentElement = startingElement.getParent();
        while(currentElement != null) {
            if(currentElement instanceof PsiMethod || currentElement instanceof PsiSwitchExpression
                    || currentElement instanceof PsiClassInitializer || currentElement instanceof PsiField) {
                return currentElement;
            }
            if(currentElement instanceof final PsiLambdaExpression psiLambdaExpression) {
                final PsiElement body = psiLambdaExpression.getBody();
                if(body instanceof PsiCodeBlock) {
                    // We have a complex lambda (lambda with a body that contains multiple statements).
                    // Return this.
                    return currentElement;
                }
            }
            currentElement = currentElement.getParent();
        }
        return null;
    }

    private RValueInfo getFollowupInfoForStatementImpl(
            @NotNull final PsiElement callstackMethod,
            @NotNull final PsiStatement startingStatement, final boolean preserveUnknown) {
        return doForwardScan(callstackMethod, startingStatement, SetUtils.newIdentityHashSet(), SetUtils.newIdentityHashSet(), new ArrayList<>(), new LocalVariablesInfo(), preserveUnknown);
    }

    private RValueInfo getFollowupInfoImpl(
            @NotNull final PsiElement callstackMethod,
            @NotNull final PsiElement startingExpression,
            @NotNull final ReturnOutcome startingReturnOutcome,
            final boolean preserveUnknown) {
        final ReturnOutcome defaultOutcome = getDefaultOutcome(callstackMethod);
        RValueInfo returnInfo = tryGetClassMemberInitInfo(callstackMethod, startingExpression, startingReturnOutcome, preserveUnknown);
        if(returnInfo != null) {
            return returnInfo;
        }

        returnInfo = tryGetImmediateReturnInfo(callstackMethod, startingExpression, startingReturnOutcome, preserveUnknown);
        if(returnInfo != null) {
            return returnInfo;
        }

        returnInfo = tryGetImmediateThrowInfo(callstackMethod, startingExpression, startingReturnOutcome, preserveUnknown);
        if(returnInfo != null) {
            return returnInfo;
        }

        returnInfo = tryGetSimpleSwitchLabeledRuleInfo(callstackMethod, startingExpression, startingReturnOutcome, preserveUnknown);
        if(returnInfo != null) {
            return returnInfo;
        }

        returnInfo = tryGetImmediateYieldInfo(callstackMethod, startingExpression, startingReturnOutcome, preserveUnknown);
        if(returnInfo != null) {
            return returnInfo;
        }

        returnInfo = tryGetSwitchExpressionWithConditionThatContainsElement(callstackMethod, startingExpression, startingReturnOutcome, preserveUnknown);
        if(returnInfo != null) {
            return returnInfo;
        }

        StartingInfo detectedPattern = tryGetSwitchStatementWithConditionThatContainsElement(callstackMethod, startingExpression, startingReturnOutcome, preserveUnknown);
        if(detectedPattern != null) {
            return processPattern(detectedPattern, preserveUnknown);
        }

        detectedPattern = tryGetIfWithConditionThatContainsMethodCall(callstackMethod, startingExpression, startingReturnOutcome, preserveUnknown);
        if(detectedPattern != null) {
            return processPattern(detectedPattern, preserveUnknown);
        }

        // Get containing foreach loop.
        // Example:
        // for(final Foo foo : fooService.getFoos(..)) {
        //   ...loop body with other DIs...
        // }
        detectedPattern = tryGetForeachWithIteratedValueThatContainsMethodCall(callstackMethod, startingExpression, startingReturnOutcome, preserveUnknown);
        if(detectedPattern != null) {
            return processPattern(detectedPattern, preserveUnknown);
        }

        detectedPattern = tryGetAssertStatementThatContainsMethodCall(callstackMethod, startingExpression, startingReturnOutcome, preserveUnknown);
        if(detectedPattern != null) {
            return processPattern(detectedPattern, preserveUnknown);
        }

        // Get the do-while-loop-condition that contains the method call.
        detectedPattern = tryGetDoWhileWithConditionThatContainsMethodCall(callstackMethod, startingExpression, startingReturnOutcome, preserveUnknown);
        if(detectedPattern != null) {
            return processPattern(detectedPattern, preserveUnknown);
        }
        detectedPattern = tryGetForLoopWithHeaderThatContainsMethodCall(callstackMethod, startingExpression, startingReturnOutcome, preserveUnknown);
        if(detectedPattern != null) {
            return processPattern(detectedPattern, preserveUnknown);
        }

        detectedPattern = tryGetSynchronizedWithLockThatContainsMethodCall(callstackMethod, startingExpression);
        if(detectedPattern != null) {
            return processPattern(detectedPattern, preserveUnknown);
        }

        detectedPattern = tryGetWhileWithConditionThatContainsMethodCall(callstackMethod, startingExpression, startingReturnOutcome, preserveUnknown);
        if(detectedPattern != null) {
            return processPattern(detectedPattern, preserveUnknown);
        }

        detectedPattern = tryGetTryWithResourceThatContainsMethodCall(callstackMethod, startingExpression, startingReturnOutcome, preserveUnknown);
        if(detectedPattern != null) {
            return processPattern(detectedPattern, preserveUnknown);
        }
        detectedPattern = tryGetVariableDecThatContainsMethodCall(callstackMethod, startingExpression, startingReturnOutcome, preserveUnknown);
        if(detectedPattern != null) {
            return processPattern(detectedPattern, preserveUnknown);
        }
        detectedPattern = tryGetAssignmentThatContainsMethodCall(callstackMethod, startingExpression, startingReturnOutcome, preserveUnknown);
        if(detectedPattern != null) {
            return processPattern(detectedPattern, preserveUnknown);
        }

        detectedPattern = tryGetContainingStatementWithMethodCall(callstackMethod, startingExpression, startingReturnOutcome, preserveUnknown);
        if(detectedPattern != null) {
            return processPattern(detectedPattern, preserveUnknown);
        }

        // If fallthrough is null, bail out.
        // The only way this can happen is if we have incomplete code.
        return new RValueInfo().addElementContainingDisHit(startingExpression).withReturnOutcome(defaultOutcome);
    }

    private RValueInfo tryGetClassMemberInitInfo(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression,
            final ReturnOutcome startingReturnOutcome, final boolean preserveUnknown) {
        if(!(callstackMethod instanceof final PsiField psiField)) {
            return null;
        }
        final PsiExpression initializer = psiField.getInitializer();
        if(initializer == null) {
            // This shouldn't happen. The only place we could have come from is the field initializer.
            return null;
        }
        if(!PsiTreeUtil.isAncestor(initializer, psiMethodCallExpression, false)) {
            // This shouldn't happen. The only place we could have come from is the field initializer.
            // The starting point should be in the field initializer.
            return null;
        }
        return computeRValueInfo(initializer, psiMethodCallExpression, startingReturnOutcome, preserveUnknown);
    }

    private StartingInfo tryGetContainingStatementWithMethodCall(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression,
            final ReturnOutcome startingReturnOutcome, final boolean preserveUnknown) {
        // Get the containing PsiStatement.
        // Get the highest expression between the methodCallExpression and the PsiStatement.
        // Compute the RValue for the highest expression.
        // Process the RValue (if it throws, bail out, etc).
        // Do forward scan starting at the next statement.
        final PsiStatement containingStatement = SQPsiUtil.getParentOfType(psiMethodCallExpression, PsiStatement.class, callstackMethod);
        if(containingStatement == null) {
            return null;
        }
        if(isDeclaringLocalClass(containingStatement)) {
            final RValueInfo rValueInfo = computeRValueInfoForLocalClassDeclaration(
                    (PsiDeclarationStatement) containingStatement,
                    psiMethodCallExpression);
            final StatementStepInfo nextSequentialStatement = getNextSequentialStatement(callstackMethod, containingStatement);
            final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
            elementsContainingDisHit.addAll(getExitedDoWhileConditions(nextSequentialStatement));
            final PsiStatement nextStatement = nextSequentialStatement.statement();
            return new StartingInfo(callstackMethod, rValueInfo, nextStatement, elementsContainingDisHit, Collections.emptyList(), new LocalVariablesInfo());
        }

        final PsiExpression highestExpression = getHighestExpressionBetween(containingStatement, psiMethodCallExpression);
        if(highestExpression == null) {
            return null;
        }
        final RValueInfo expressionValue = computeRValueInfo(highestExpression, psiMethodCallExpression, startingReturnOutcome, preserveUnknown);
        if(expressionValue.getExceptionThrown() != null) {
            return new StartingInfo(callstackMethod, expressionValue, null, Collections.emptySet(), Collections.emptyList(), new LocalVariablesInfo());
        }

        final StatementStepInfo nextSequentialStatement = getNextSequentialStatement(callstackMethod, containingStatement);
        final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
        elementsContainingDisHit.addAll(getExitedDoWhileConditions(nextSequentialStatement));
        final PsiStatement startingStatement = nextSequentialStatement.statement();
        return new StartingInfo(callstackMethod, expressionValue, startingStatement,
                elementsContainingDisHit, Collections.emptyList(), new LocalVariablesInfo());
    }

    private RValueInfo computeRValueInfoForLocalClassDeclaration(
            final PsiDeclarationStatement containingStatement, final PsiElement startingElement) {
        final RValueInfo ret = new RValueInfo();
        PsiElement currentElement = startingElement;
        while(currentElement != null) {
            if(currentElement == containingStatement) {
                return ret;
            }
            if(currentElement instanceof final PsiClass currentClass) {
                final PsiField[] fields = currentClass.getFields();
                final PsiClassInitializer initBlockContainingDi = getElementThatContainsChild(currentClass.getInitializers(), startingElement);
                if(initBlockContainingDi != null) {
                    ret.addElementsContainingDisHit(getSiblingsAfter(initBlockContainingDi));
                    currentElement = currentElement.getParent();
                    continue;
                }

                final PsiMethod[] constructors = currentClass.getConstructors();
                final PsiMethod[] methods = Arrays.stream(currentClass.getMethods()).filter(x -> !x.isConstructor()).toArray(PsiMethod[]::new);
                final PsiMethod constructor = getElementThatContainsChild(constructors, startingElement);
                if(constructor != null) {
                    ret.addElementsContainingDisHit(getSiblingsAfter(constructor));
                    currentElement = currentElement.getParent();
                    continue;
                }

                final PsiMethod method = getElementThatContainsChild(methods, startingElement);
                if(method != null) {
                    ret.addElementsContainingDisHit(getSiblingsAfter(method));
                    currentElement = currentElement.getParent();
                    continue;
                }

                final PsiField field = getElementThatContainsChild(fields, startingElement);
                if(field != null) {
                    ret.addElementsContainingDisHit(getSiblingsAfter(field));
                    currentElement = currentElement.getParent();
                    continue;
                }

                final PsiClass[] innerClasses = currentClass.getInnerClasses();
                PsiClass innerClass = getElementThatContainsChild(innerClasses, startingElement);
                if(innerClass != null) {
                    // Add all elements that come after the inner class.
                    ret.addElementsContainingDisHit(getSiblingsAfter(innerClass));
                    currentElement = currentElement.getParent();
                    continue;
                }

                // We don't know how we arrived at the current class.
                // This shouldn't happen. Just add the current class.
                ret.addElementContainingDisHit(currentClass);
                currentElement = currentElement.getParent();
                continue;
            }
            currentElement = currentElement.getParent();
        }
        return ret;
    }

    private static boolean isDeclaringLocalClass(final PsiStatement containingStatement) {
        if(!(containingStatement instanceof final PsiDeclarationStatement psiDeclarationStatement)) {
            return false;
        }
        final PsiElement[] declaredElements = psiDeclarationStatement.getDeclaredElements();
        if(declaredElements.length != 1) {
            return false;
        }
        return declaredElements[0] instanceof PsiClass;
    }

    private StartingInfo tryGetSynchronizedWithLockThatContainsMethodCall(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression) {
        final PsiSynchronizedStatement synchronizedStatement = getSynchronizedStatementWithLockThatContainsCall(callstackMethod, psiMethodCallExpression);
        if(synchronizedStatement == null) {
            return null;
        }
        return new StartingInfo(
                callstackMethod, new RValueInfo().withUnknownReturnValue(),
                synchronizedStatement,
                Collections.emptySet(),
                Collections.emptyList(), new LocalVariablesInfo());
    }

    private StartingInfo tryGetForLoopWithHeaderThatContainsMethodCall(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression, final ReturnOutcome returnOutcome, final boolean preserveUnknown) {
        final ForLoopInfo forInfo = getForLoopWithHeaderThatContainsMethodCall(callstackMethod, psiMethodCallExpression);
        if(forInfo == null) {
            return null;
        }
        final PsiForStatement forStatement = forInfo.forStatement();
        final PsiElement elementContainingMethodCall = forInfo.elementContainingMethodCall();
        final PsiExpression highestExpression = getHighestExpressionBetween(elementContainingMethodCall, psiMethodCallExpression);
        if(highestExpression == null) {
            // This shouldn't happen. If it does, the for loop has broken code.
            return new StartingInfo(
                    callstackMethod, new RValueInfo().withUnknownReturnValue(),
                    forInfo.forStatement(),
                    Collections.emptySet(),
                    Collections.emptyList(), new LocalVariablesInfo());
        }
        final RValueInfo expressionValue = computeRValueInfo(highestExpression, psiMethodCallExpression, returnOutcome, preserveUnknown);
        if(expressionValue.getExceptionThrown() != null) {
            // The element throws. Do not add the other parts of the for loop.
            // Return the starting with the RValue.
            return new StartingInfo(
                    callstackMethod, expressionValue,
                    null,
                    Collections.emptySet(),
                    Collections.emptyList(), new LocalVariablesInfo());
        }
        final PsiStatement initSection = forStatement.getInitialization();
        final PsiExpression condition = forStatement.getCondition();
        final PsiStatement update = forStatement.getUpdate();
        final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
        if(elementContainingMethodCall == initSection) {
            if(condition != null) {
                elementsContainingDisHit.add(condition);
            }
            if(update != null) {
                elementsContainingDisHit.add(update);
            }
        } else if(elementContainingMethodCall == condition) {
            if(update != null) {
                elementsContainingDisHit.add(update);
            }
        }
        final PsiStatement body = forStatement.getBody();
        if(body != null) {
            elementsContainingDisHit.add(body);
        }
        final List<SpecialExit> specialExitPoints = new ArrayList<>(getSpecialExitPoints(body));
        final StatementStepInfo statementStepInfo = getNextSequentialStatement(callstackMethod, forStatement);
        elementsContainingDisHit.addAll(getExitedDoWhileConditions(statementStepInfo));
        return new StartingInfo(
                callstackMethod, expressionValue,
                statementStepInfo.statement(),
                elementsContainingDisHit,
                specialExitPoints, new LocalVariablesInfo());
    }

    private StartingInfo tryGetDoWhileWithConditionThatContainsMethodCall(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression,
            final ReturnOutcome startingReturnOutcome, final boolean preserveUnknown) {
        final PsiDoWhileStatement containingDoWhile = getDoWhileWithConditionThatContainsMethodCall(callstackMethod, psiMethodCallExpression);
        if(containingDoWhile == null) {
            return null;
        }
        final PsiExpression condition = containingDoWhile.getCondition();
        if(condition == null) {
            // This shouldn't happen.
            return null;
        }
        final RValueInfo expressionValue = computeRValueInfo(condition, psiMethodCallExpression, startingReturnOutcome, preserveUnknown);
        if(expressionValue.getExceptionThrown() != null) {
            return new StartingInfo(callstackMethod, expressionValue, null, Collections.emptySet(), Collections.emptyList(), new LocalVariablesInfo());
        }
        final StatementStepInfo nextSequentialStatement = getNextSequentialStatement(callstackMethod, containingDoWhile);
        final PsiStatement startingStatement = nextSequentialStatement.statement();
        final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
        final List<PsiExpression> exitedDoWhileConditionsExcludingOurs = nextSequentialStatement.exitedElements().stream()
                .filter(x -> x instanceof PsiDoWhileStatement && x != containingDoWhile)
                .map(x -> ((PsiDoWhileStatement) x).getCondition())
                .filter(Objects::nonNull).toList();
        elementsContainingDisHit.addAll(exitedDoWhileConditionsExcludingOurs);
        return new StartingInfo(callstackMethod, expressionValue, startingStatement,
                elementsContainingDisHit, Collections.emptyList(), new LocalVariablesInfo());
    }

    private StartingInfo tryGetAssertStatementThatContainsMethodCall(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression,
            final ReturnOutcome startingReturnOutcome, final boolean preserveUnknown) {
        final PsiAssertStatement assertStatement = getAssertExpressionWithConditionThatContainsMethodCall(callstackMethod, psiMethodCallExpression);
        if(assertStatement == null) {
            return null;
        }
        final PsiExpression assertCondition = assertStatement.getAssertCondition();
        if(assertCondition == null) {
            // This shouldn't happen.
            return null;
        }
        final RValueInfo expressionValue = computeRValueInfo(assertCondition, psiMethodCallExpression, startingReturnOutcome, preserveUnknown);
        if(expressionValue.getExceptionThrown() != null) {
            return new StartingInfo(callstackMethod, expressionValue, null, Collections.emptySet(), Collections.emptyList(), new LocalVariablesInfo());
        }
        if(expressionValue.getReturnOutcome() == ReturnOutcome.False) {
            final PsiType thrownException = getAssertErrorType();
            expressionValue.withExceptionThrown(assertStatement, thrownException);
            return new StartingInfo(callstackMethod, expressionValue, null, Collections.emptySet(), Collections.emptyList(), new LocalVariablesInfo());
        }
        final StatementStepInfo nextSequentialStatement = getNextSequentialStatement(callstackMethod, assertStatement);
        final PsiStatement startingStatement = nextSequentialStatement.statement();
        final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
        elementsContainingDisHit.addAll(getExitedDoWhileConditions(nextSequentialStatement));
        return new StartingInfo(callstackMethod, expressionValue, startingStatement, elementsContainingDisHit, Collections.emptyList(), new LocalVariablesInfo());
    }

    @Nullable
    private StartingInfo tryGetForeachWithIteratedValueThatContainsMethodCall(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression,
            final ReturnOutcome startingReturnOutcome, final boolean preserveUnknown) {
        final PsiForeachStatement containingForeach = getForeachWithIteratedValueThatContainsMethodCall(callstackMethod, psiMethodCallExpression);
        if(containingForeach == null) {
            return null;
        }
        final PsiExpression iteratedValueExpression = containingForeach.getIteratedValue();
        if(iteratedValueExpression == null) {
            // This shouldn't happen.
            return null;
        }
        final RValueInfo expressionValue = computeRValueInfo(iteratedValueExpression, psiMethodCallExpression, startingReturnOutcome, preserveUnknown);
        if(expressionValue.getExceptionThrown() != null) {
            return new StartingInfo(callstackMethod, expressionValue, null, Collections.emptySet(), Collections.emptyList(), new LocalVariablesInfo());
        }
        if(expressionValue.getReturnOutcome() == ReturnOutcome.Empty) {
            final StatementStepInfo nextSequentialStatement = getNextSequentialStatement(callstackMethod, containingForeach);
            final PsiStatement startingStatement = nextSequentialStatement.statement();
            final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
            elementsContainingDisHit.addAll(getExitedDoWhileConditions(nextSequentialStatement));
            return new StartingInfo(callstackMethod, expressionValue, startingStatement, elementsContainingDisHit, Collections.emptyList(), new LocalVariablesInfo());
        }
        final PsiStatement foreachBody = containingForeach.getBody();
        final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
        if(foreachBody != null) {
            elementsContainingDisHit.add(foreachBody);
        }
        final List<SpecialExit> specialExitPoints = getSpecialExitPoints(foreachBody);
        final StatementStepInfo nextSequentialStatement = getNextSequentialStatement(callstackMethod, containingForeach);
        final PsiStatement startingStatement = nextSequentialStatement.statement();
        elementsContainingDisHit.addAll(getExitedDoWhileConditions(nextSequentialStatement));
        return new StartingInfo(callstackMethod, expressionValue, startingStatement, elementsContainingDisHit, specialExitPoints, new LocalVariablesInfo());
    }

    private RValueInfo processPattern(final StartingInfo startingInfo, final boolean preserveUnknown) {
        final RValueInfo expressionValue = startingInfo.expressionValue();
        if(expressionValue.getExceptionThrown() != null) {
            return expressionValue;
        }
        final PsiStatement startingStatement = startingInfo.startingStatement();
        if(startingStatement == null) {
            final RValueInfo ret = new RValueInfo()
                    .addElementsContainingDisHit(expressionValue.getElementsContainingDisHit())
                    .addElementsContainingDisHit(startingInfo.elementsContainingDisThatAreHit())
                    .addMethodCallElements(expressionValue.getMethodCallElements())
                    .withReturnOutcome(ReturnOutcome.Unknown);
            return ret;
        }
        // Forward scan the starting statement.
        final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
        elementsContainingDisHit.addAll(expressionValue.getElementsContainingDisHit());
        elementsContainingDisHit.addAll(startingInfo.elementsContainingDisThatAreHit());
        final List<SpecialExit> specialExitPoints = new ArrayList<>();
        specialExitPoints.addAll(startingInfo.specialExitPoints());
        Set<PsiMethodCallExpression> methodCallElements = SetUtils.newIdentityHashSet();
        methodCallElements.addAll(expressionValue.getMethodCallElements());
        return doForwardScan(startingInfo.callstackMethod(), startingStatement, elementsContainingDisHit, methodCallElements, specialExitPoints, startingInfo.localVariablesInfo(), preserveUnknown);
    }

    private RValueInfo doForwardScan(
            @NotNull final PsiElement callstackMethod,
            @NotNull final PsiStatement startingStatement, @NotNull final Set<PsiElement> elementsContainingDisHit,
            @NotNull final Set<PsiMethodCallExpression> methodCallElements,
            @NotNull final List<SpecialExit> specialExitPoints, final LocalVariablesInfo localVariables,
            final boolean preserveUnknown) {
        final ReturnOutcome defaultOutcome = getDefaultOutcome(callstackMethod);
        StatementStepInfo currentStatementInfo = new StatementStepInfo(startingStatement, Collections.emptyList());
        boolean isInCatchBlock = isInCatchBlock(callstackMethod, startingStatement);
        while(true) {
            final PsiStatement currentStatement = currentStatementInfo.statement();
            // Handle the case where we just forward scanned out of a catch block.
            if(currentStatementInfo.exitedElements().stream().anyMatch(x -> x instanceof PsiCatchSection)) {
                final PsiThrowStatement throwStatement = getLastThrowStatementForCallstackMethod(callstackMethod, specialExitPoints);
                if(throwStatement != null) {
                    return new RValueInfo()
                            .addElementsContainingDisHit(elementsContainingDisHit)
                            .addMethodCallElements(methodCallElements)
                            .withExceptionThrown(throwStatement, throwStatement.getException().getType());
                }
                // If we passed a return or yield statement (within an if/else block or other structure), assume the
                // last one we passed is taken.
                final PsiStatement returnOrYield = getLastReturnOrYieldStatementForCallstackMethod(callstackMethod, specialExitPoints);
                if(returnOrYield != null) {
                    final List<PsiElement> finallyBlocks = getHitFinallyBlocksBetween(callstackMethod, returnOrYield);
                    return new RValueInfo()
                            .addElementsContainingDisHit(elementsContainingDisHit)
                            .addElementsContainingDisHit(finallyBlocks)
                            .addMethodCallElements(methodCallElements)
                            .withReturnOutcome(ReturnOutcome.Unknown);
                }
                isInCatchBlock = isInCatchBlock(callstackMethod, currentStatement);
            }
            // Handle the case where we forward scanned out of at least one doWhile(..) statement.
            // In this case, we need to add DIs in the doWhile(..) condition.
            final List<PsiDoWhileStatement> exitedDoWhileStatements = currentStatementInfo.exitedElements().stream()
                    .filter(x -> x instanceof PsiDoWhileStatement)
                    .map(x -> (PsiDoWhileStatement) x).toList();
            for(final PsiDoWhileStatement exitedDoWhile : exitedDoWhileStatements) {
                final PsiExpression exitedDoWhileCondition = exitedDoWhile.getCondition();
                elementsContainingDisHit.add(exitedDoWhileCondition);
                updateReassignmentStatusIfNeeded(localVariables, exitedDoWhileCondition);
            }
            // Handle the case where we've reached the end.
            if(currentStatement == null) {
                break;
            }
            if(currentStatement instanceof final PsiIfStatement ifStatement) {
                final PsiExpression ifCondition = ifStatement.getCondition();
                final RValueInfo conditionValue = determineRValueForConditionImpl(ifCondition, localVariables, preserveUnknown);
                if(conditionValue.getExceptionThrown() != null) {
                    // Process the exception.
                    final ThrowInfo throwInfo = processThrowingElement(callstackMethod, currentStatement,
                            defaultOutcome, conditionValue, elementsContainingDisHit, methodCallElements, specialExitPoints);
                    if(throwInfo.nextStep() == NextStep.Continue) {
                        currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                        continue;
                    }
                    return throwInfo.finalReturnValue();
                }
                updateReassignmentStatusIfNeeded(localVariables, ifCondition);
                final ReturnOutcome conditionOutcome = conditionValue.getReturnOutcome();
                if(conditionOutcome == ReturnOutcome.True) {
                    elementsContainingDisHit.addAll(conditionValue.getElementsContainingDisHit());
                    methodCallElements.addAll(conditionValue.getMethodCallElements());
                    currentStatementInfo = getFirstStatementForTrueCase(callstackMethod, ifStatement);
                    continue;
                } else if(conditionOutcome == ReturnOutcome.False) {
                    elementsContainingDisHit.addAll(conditionValue.getElementsContainingDisHit());
                    methodCallElements.addAll(conditionValue.getMethodCallElements());
                    currentStatementInfo = getFirstStatementForFalseCase(callstackMethod, ifStatement);
                    continue;
                }
                elementsContainingDisHit.addAll(conditionValue.getElementsContainingDisHit());
                methodCallElements.addAll(conditionValue.getMethodCallElements());
                processScannedOverElement(ifStatement.getThenBranch(), elementsContainingDisHit, specialExitPoints, localVariables);
                processScannedOverElement(ifStatement.getElseBranch(), elementsContainingDisHit, specialExitPoints, localVariables);
                currentStatementInfo = getNextSequentialStatement(callstackMethod, ifStatement);
                continue;
            }

            if(currentStatement instanceof final PsiLabeledStatement psiLabeledStatement) {
                final PsiStatement subjectStatement = psiLabeledStatement.getStatement();
                if(subjectStatement == null) {
                    processScannedOverElement(currentStatement, elementsContainingDisHit, specialExitPoints, localVariables);
                    currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                    continue;
                }
                currentStatementInfo = new StatementStepInfo(subjectStatement, Collections.emptyList());
                continue;
            }

            if(currentStatement instanceof final PsiForeachStatement psiForeachStatement) {
                final PsiExpression iterationExpression = psiForeachStatement.getIteratedValue();
                final RValueInfo iteratedValue = determineRValueForIterationValue(iterationExpression, localVariables, preserveUnknown);
                if(iteratedValue.getExceptionThrown() != null) {
                    // Process the exception.
                    final ThrowInfo throwInfo = processThrowingElement(callstackMethod, currentStatement,
                            defaultOutcome, iteratedValue, elementsContainingDisHit, methodCallElements, specialExitPoints);
                    if(throwInfo.nextStep() == NextStep.Continue) {
                        currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                        continue;
                    }
                    return throwInfo.finalReturnValue();
                }
                if(iteratedValue.getReturnOutcome() == ReturnOutcome.Empty) {
                    updateReassignmentStatusIfNeeded(localVariables, iterationExpression);
                    elementsContainingDisHit.addAll(iteratedValue.getElementsContainingDisHit());
                    methodCallElements.addAll(iteratedValue.getMethodCallElements());
                    currentStatementInfo = getNextSequentialStatement(callstackMethod, psiForeachStatement);
                    continue;
                } else {
                    updateReassignmentStatusIfNeeded(localVariables, psiForeachStatement);
                    elementsContainingDisHit.addAll(iteratedValue.getElementsContainingDisHit());
                    methodCallElements.addAll(iteratedValue.getMethodCallElements());
                    processScannedOverElement(psiForeachStatement.getBody(), elementsContainingDisHit, specialExitPoints, localVariables);
                    currentStatementInfo = getNextSequentialStatement(callstackMethod, psiForeachStatement);
                    continue;
                }
            }

            if(currentStatement instanceof final PsiDeclarationStatement psiDeclarationStatement) {
                final PsiElement[] declaredElements = psiDeclarationStatement.getDeclaredElements();
                if(!Arrays.stream(declaredElements).allMatch(x -> x instanceof PsiLocalVariable)) {
                    // We're declaring something other than local variables.
                    processScannedOverElement(psiDeclarationStatement, elementsContainingDisHit, specialExitPoints, localVariables);
                    currentStatementInfo = getNextSequentialStatement(callstackMethod, psiDeclarationStatement);
                    continue;
                }
                for(final PsiElement declaredElement : declaredElements) {
                    final PsiLocalVariable declaredVariable = (PsiLocalVariable) declaredElement;
                    final PsiExpression initializerExpression = declaredVariable.getInitializer();
                    final RValueInfo declaredVariableValue = determineRValueForDeclarationInitValue(initializerExpression, localVariables, preserveUnknown);
                    if(declaredVariableValue.getExceptionThrown() != null) {
                        // We have something like: bar = fooOptional.orElseThrow(..).
                        final ThrowInfo throwInfo = processThrowingElement(callstackMethod, currentStatement,
                                defaultOutcome, declaredVariableValue, elementsContainingDisHit, methodCallElements, specialExitPoints);
                        if(throwInfo.nextStep() == NextStep.Continue) {
                            continue;
                        }
                        return throwInfo.finalReturnValue();
                    }
                    localVariables.putVariable(declaredVariable, declaredVariableValue.getReturnOutcome());
                    updateReassignmentStatusIfNeeded(localVariables, initializerExpression);
                    elementsContainingDisHit.addAll(declaredVariableValue.getElementsContainingDisHit());
                    methodCallElements.addAll(declaredVariableValue.getMethodCallElements());
                }
                currentStatementInfo = getNextSequentialStatement(callstackMethod, psiDeclarationStatement);
                continue;
            }

            if(currentStatement instanceof final PsiAssertStatement psiAssertStatement) {
                final PsiExpression assertExpression = psiAssertStatement.getAssertCondition();
                final RValueInfo assertValue = determineRValueForAssertExpression(assertExpression, localVariables, preserveUnknown);
                if(assertValue.getExceptionThrown() != null) {
                    // Process the exception.
                    final ThrowInfo throwInfo = processThrowingElement(callstackMethod, currentStatement,
                            defaultOutcome, assertValue, elementsContainingDisHit, methodCallElements, specialExitPoints);
                    if(throwInfo.nextStep() == NextStep.Continue) {
                        currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                        continue;
                    }
                    return throwInfo.finalReturnValue();
                }
                elementsContainingDisHit.addAll(assertValue.getElementsContainingDisHit());
                methodCallElements.addAll(assertValue.getMethodCallElements());
                currentStatementInfo = getNextSequentialStatement(callstackMethod, psiAssertStatement);
                continue;
            }

            if(currentStatement instanceof final PsiContinueStatement psiContinueStatement) {
                final PsiStatement continuedStatement = psiContinueStatement.findContinuedStatement();
                if(continuedStatement != null) {
                    final List<PsiElement> hitFinallyBlocks = getHitFinallyBlocksBetween(continuedStatement, psiContinueStatement);
                    specialExitPoints.addAll(getSpecialExitPoints(hitFinallyBlocks));
                    elementsContainingDisHit.addAll(hitFinallyBlocks);
                    currentStatementInfo = getNextSequentialStatement(callstackMethod, continuedStatement);
                    if(continuedStatement instanceof PsiDoWhileStatement) {
                        elementsContainingDisHit.add(((PsiDoWhileStatement) continuedStatement).getCondition());
                    }
                } else {
                    // If we don't know which statement this applies to, just ignore it.
                    currentStatementInfo = getNextSequentialStatement(callstackMethod, psiContinueStatement);
                }
                continue;
            }

            if(currentStatement instanceof final PsiBreakStatement psiBreakStatement) {
                final PsiStatement exitedStatement = psiBreakStatement.findExitedStatement();
                if(exitedStatement != null) {
                    final List<PsiElement> hitFinallyBlocks = getHitFinallyBlocksBetween(exitedStatement, psiBreakStatement);
                    specialExitPoints.addAll(getSpecialExitPoints(hitFinallyBlocks));
                    elementsContainingDisHit.addAll(hitFinallyBlocks);
                    currentStatementInfo = getNextSequentialStatement(callstackMethod, exitedStatement);
                } else {
                    // If we don't know which statement this applies to, just ignore it.
                    currentStatementInfo = getNextSequentialStatement(callstackMethod, psiBreakStatement);
                }
                continue;
            }

            if(currentStatement instanceof PsiDoWhileStatement) {
                processScannedOverElement(currentStatement, elementsContainingDisHit, specialExitPoints, localVariables);
                currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                continue;
            }

            if(currentStatement instanceof PsiEmptyStatement) {
                currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                continue;
            }

            if(currentStatement instanceof PsiExpressionListStatement) {
                // It should not be possible to forward scan into a PsiExpressionListStatement.
                // These are only used in the top-part of for-loops. If it does happen, just add it and continue.
                processScannedOverElement(currentStatement, elementsContainingDisHit, specialExitPoints, localVariables);
                currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                continue;
            }

            if(currentStatement instanceof final PsiExpressionStatement psiExpressionStatement) {
                final PsiExpression psiExpression = psiExpressionStatement.getExpression();
                if(psiExpression instanceof final PsiAssignmentExpression psiAssignmentExpression) {
                    final PsiLocalVariable leftVariable = getLeftVariable(psiAssignmentExpression);
                    if(leftVariable != null) {
                        // We are assigning a local variable.
                        final RValueInfo assignmentValue = determineRValueForAssignedLocalVariable(psiAssignmentExpression.getRExpression(), localVariables, preserveUnknown);
                        if(assignmentValue.getExceptionThrown() != null) {
                            // Process the exception.
                            final ThrowInfo throwInfo = processThrowingElement(callstackMethod, currentStatement,
                                    defaultOutcome, assignmentValue, elementsContainingDisHit, methodCallElements, specialExitPoints);
                            if(throwInfo.nextStep() == NextStep.Continue) {
                                currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                                continue;
                            }
                            return throwInfo.finalReturnValue();
                        }
                        if(localVariables.isPrimaryLocalVariable(leftVariable) && assignmentValue.getReturnOutcome() == ReturnOutcome.Unknown) {
                            assignmentValue.withReturnOutcome(ReturnOutcome.Other);
                        }
                        localVariables.putVariable(leftVariable, assignmentValue.getReturnOutcome());
                        elementsContainingDisHit.addAll(assignmentValue.getElementsContainingDisHit());
                        methodCallElements.addAll(assignmentValue.getMethodCallElements());
                        currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                        continue;
                    }

                    // We could not find a variable in the LHS of the assignment expression.
                    // Compute the RValue for the RHS.
                    final RValueInfo assignmentValue = determineRValueForAssignedLocalVariable(psiAssignmentExpression.getRExpression(), localVariables, preserveUnknown);
                    if(assignmentValue.getExceptionThrown() != null) {
                        // Process the exception.
                        final ThrowInfo throwInfo = processThrowingElement(callstackMethod, currentStatement,
                                defaultOutcome, assignmentValue, elementsContainingDisHit, methodCallElements, specialExitPoints);
                        if(throwInfo.nextStep() == NextStep.Continue) {
                            currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                            continue;
                        }
                        return throwInfo.finalReturnValue();
                    }
                    // If we do not throw, just continue on.
                    elementsContainingDisHit.addAll(assignmentValue.getElementsContainingDisHit());
                    methodCallElements.addAll(assignmentValue.getMethodCallElements());
                    currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                    continue;
                }

                final RValueInfo expressionValue = determineRValueForExpressionStatement(psiExpression, localVariables, preserveUnknown);
                if(expressionValue.getExceptionThrown() != null) {
                    // Process the exception.
                    final ThrowInfo throwInfo = processThrowingElement(callstackMethod, currentStatement,
                            defaultOutcome, expressionValue, elementsContainingDisHit, methodCallElements, specialExitPoints);
                    if(throwInfo.nextStep() == NextStep.Continue) {
                        currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                        continue;
                    }
                    return throwInfo.finalReturnValue();
                }
                updateReassignmentStatusIfNeeded(localVariables, psiExpression);
                elementsContainingDisHit.addAll(expressionValue.getElementsContainingDisHit());
                methodCallElements.addAll(expressionValue.getMethodCallElements());
                currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                continue;
            }

            if(currentStatement instanceof PsiForStatement) {
                processScannedOverElement(currentStatement, elementsContainingDisHit, specialExitPoints, localVariables);
                currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                continue;
            }

            if(currentStatement instanceof PsiReturnStatement) {
                final PsiExpression returnExpression = ((PsiReturnStatement) currentStatement).getReturnValue();
                if(containsBreakOrContinueForParent(callstackMethod, currentStatement, specialExitPoints)) {
                    // We may have broken out of one of our parent elements.
                    // We can't assume this return statement is hit. Just add any DIs here and continue on.
                    elementsContainingDisHit.add(currentStatement);
                    specialExitPoints.add(new SpecialExit(currentStatement, callstackMethod, SpecialExitType.Return));
                    currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                    continue;
                }
                if(containsReturnForParent(callstackMethod, currentStatement, specialExitPoints)) {
                    // We can't infer the return outcome, because we may have taken a previous return statement.
                    // We do know that no DIs are hit after this point though.
                    return new RValueInfo().withReturnOutcome(defaultOutcome)
                            .addElementsContainingDisHit(elementsContainingDisHit)
                            .addElementContainingDisHit(currentStatement)
                            .addElementsContainingDisHit(getHitFinallyBlocksBetween(callstackMethod, currentStatement))
                            .addMethodCallElements(methodCallElements);
                }
                // We can assume the return is hit. Determine the value and exit.
                final RValueInfo returnExpressionValue = determineRValueForReturnValueExpression(returnExpression, localVariables, defaultOutcome, preserveUnknown);
                if(returnExpressionValue.getExceptionThrown() != null) {
                    return createFinalThrowingRValue(returnExpressionValue, elementsContainingDisHit, methodCallElements);
                }
                return processTakenReturnStatement(callstackMethod, currentStatement, returnExpressionValue, elementsContainingDisHit, methodCallElements);
            }
            if(currentStatement instanceof PsiYieldStatement) {
                final PsiExpression yieldExpression = ((PsiYieldStatement) currentStatement).getExpression();
                if(containsBreakOrContinueForParent(callstackMethod, currentStatement, specialExitPoints)) {
                    // We may have broken out of one of our parent elements.
                    // We can't assume this return statement is hit. Just add any DIs here and continue on.
                    elementsContainingDisHit.add(currentStatement);
                    specialExitPoints.add(new SpecialExit(currentStatement, callstackMethod, SpecialExitType.Return));
                    currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                    continue;
                }
                if(containsYieldForParent(callstackMethod, currentStatement, specialExitPoints)) {
                    // We can't infer the return outcome, because we may have taken a previous yield statement.
                    // We do know that no DIs are hit after this point though.
                    return new RValueInfo().withReturnOutcome(defaultOutcome)
                            .addElementsContainingDisHit(elementsContainingDisHit)
                            .addElementContainingDisHit(currentStatement)
                            .addElementsContainingDisHit(getHitFinallyBlocksBetween(callstackMethod, currentStatement))
                            .addMethodCallElements(methodCallElements);
                }
                // We can assume the return is hit. Determine the value and exit.
                final RValueInfo returnExpressionValue = determineRValueForReturnValueExpression(yieldExpression, localVariables, defaultOutcome, preserveUnknown);
                if(returnExpressionValue.getExceptionThrown() != null) {
                    return createFinalThrowingRValue(returnExpressionValue, elementsContainingDisHit, methodCallElements);
                }
                return processTakenReturnStatement(callstackMethod, currentStatement, returnExpressionValue, elementsContainingDisHit, methodCallElements);
            }

            if(currentStatement instanceof PsiSwitchLabelStatement) {
                currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                continue;
            }

            if(currentStatement instanceof PsiSwitchLabeledRuleStatement) {
                // We were scanning statements inside a code block that is a child of a PsiSwitchLabeledRuleStatement.
                // We reached the next PsiSwitchLabeledRuleStatement statement. This statement and subsequent
                // PsiSwitchLabeledRuleStatements cannot be hit. Just keep scanning over the subsequent rule statements
                // until we reach the end of the switch -- either the PsiSwitchExpression or the PsiSwitchStatement.
                currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                continue;
            }

            if(currentStatement instanceof PsiSwitchStatement) {
                processScannedOverElement(currentStatement, elementsContainingDisHit, specialExitPoints, localVariables);
                currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                continue;
            }

            if(currentStatement instanceof final PsiTryStatement psiTryStatement) {
                final PsiResourceList resourceList = psiTryStatement.getResourceList();
                if(resourceList != null) {
                    elementsContainingDisHit.add(resourceList);
                }
                currentStatementInfo = getNextStatementForTry(callstackMethod, psiTryStatement);
                continue;
            }

            if(currentStatement instanceof final PsiSynchronizedStatement psiSynchronizedStatement) {
                final PsiExpression lockExpression = psiSynchronizedStatement.getLockExpression();
                if(lockExpression != null) {
                    elementsContainingDisHit.add(lockExpression);
                }
                currentStatementInfo = getNextStatementForSynchronized(callstackMethod, psiSynchronizedStatement);
                continue;
            }

            if(currentStatement instanceof final PsiThrowStatement psiThrowStatement) {
                if(isInCatchBlock) {
                    // If we're in a catch block, assume the throw is taken.
                    final PsiExpression thrownException = psiThrowStatement.getException();
                    if(thrownException != null) {
                        final PsiType exceptionType = thrownException.getType();
                        if(exceptionType != null) {
                            return new RValueInfo()
                                    .addElementsContainingDisHit(elementsContainingDisHit)
                                    .addElementContainingDisHit(psiThrowStatement)
                                    .addMethodCallElements(methodCallElements)
                                    .withExceptionThrown(psiThrowStatement, exceptionType);
                        }
                    }
                    // The throw statement is incomplete. Bail out here with unknown return value.
                    return new RValueInfo().withReturnOutcome(defaultOutcome)
                            .addElementsContainingDisHit(elementsContainingDisHit)
                            .addMethodCallElements(methodCallElements)
                            .addElementContainingDisHit(psiThrowStatement);
                }
                if(containsBreakOrContinueForParent(callstackMethod, currentStatement, specialExitPoints)) {
                    // We could have broken out of one of our parent elements.
                    // We can't assume this throw statement is hit. Just add any DIs here and continue on.
                    elementsContainingDisHit.add(psiThrowStatement);
                    specialExitPoints.add(new SpecialExit(psiThrowStatement, callstackMethod, SpecialExitType.Throw));
                    currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                    continue;
                }
                if(containsSpecialExitForParent(callstackMethod, currentStatement, specialExitPoints, SpecialExitType.Return, SpecialExitType.Yield)) {
                    // We can't infer the throw happens, but we can infer that no elements after this point are hit.
                    // Either the return(s) we found earlier were hit or this throw statement is hit.
                    // Either way, no elements after this point in the forward scan are hit.
                    elementsContainingDisHit.add(psiThrowStatement);
                    return new RValueInfo().withReturnOutcome(defaultOutcome)
                            .addElementsContainingDisHit(elementsContainingDisHit)
                            .addMethodCallElements(methodCallElements);
                }
                // We can infer the throw is hit. Return the exception.
                final PsiExpression thrownException = psiThrowStatement.getException();
                if(thrownException != null) {
                    final PsiType exceptionType = thrownException.getType();
                    if(exceptionType != null) {
                        return new RValueInfo().addElementsContainingDisHit(elementsContainingDisHit)
                                .addElementContainingDisHit(psiThrowStatement)
                                .addMethodCallElements(methodCallElements)
                                .withExceptionThrown(psiThrowStatement, exceptionType);
                    }
                }
                // If the throw statement is incomplete, bail out here with unknown return value.
                return new RValueInfo().withReturnOutcome(defaultOutcome)
                        .addElementsContainingDisHit(elementsContainingDisHit)
                        .addMethodCallElements(methodCallElements)
                        .addElementContainingDisHit(psiThrowStatement);
            }

            if(currentStatement instanceof final PsiWhileStatement psiWhileStatement) {
                final PsiExpression condition = psiWhileStatement.getCondition();
                final RValueInfo conditionValue = determineRValueForConditionImpl(condition, localVariables, preserveUnknown);
                if(conditionValue.getExceptionThrown() != null) {
                    // Process the exception.
                    final ThrowInfo throwInfo = processThrowingElement(callstackMethod, currentStatement,
                            defaultOutcome, conditionValue, elementsContainingDisHit, methodCallElements, specialExitPoints);
                    if(throwInfo.nextStep() == NextStep.Continue) {
                        currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                        continue;
                    }
                    return throwInfo.finalReturnValue();
                }
                if(conditionValue.getReturnOutcome() == ReturnOutcome.False) {
                    updateReassignmentStatusIfNeeded(localVariables, condition);
                    elementsContainingDisHit.addAll(conditionValue.getElementsContainingDisHit());
                    methodCallElements.addAll(conditionValue.getMethodCallElements());
                    currentStatementInfo = getNextSequentialStatement(callstackMethod, psiWhileStatement);
                    continue;
                } else {
                    updateReassignmentStatusIfNeeded(localVariables, psiWhileStatement);
                    elementsContainingDisHit.addAll(conditionValue.getElementsContainingDisHit());
                    methodCallElements.addAll(conditionValue.getMethodCallElements());
                    processScannedOverElement(psiWhileStatement.getBody(), elementsContainingDisHit, specialExitPoints, localVariables);
                    currentStatementInfo = getNextSequentialStatement(callstackMethod, psiWhileStatement);
                    continue;
                }
            }

            if(currentStatement instanceof final PsiBlockStatement psiBlockStatement) {
                final PsiStatement[] innerStatements = psiBlockStatement.getCodeBlock().getStatements();
                if(innerStatements.length != 0) {
                    currentStatementInfo = new StatementStepInfo(innerStatements[0], Collections.emptyList());
                } else {
                    currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
                }
                continue;
            }

            // Default behavior: Just scan over the statement and continue.
            processScannedOverElement(currentStatement, elementsContainingDisHit, specialExitPoints, localVariables);
            currentStatementInfo = getNextSequentialStatement(callstackMethod, currentStatement);
        }
        // We've reached the end.
        // Otherwise, return an RValue with the defaultValue and elementsContainingDisHit.
        return new RValueInfo().addElementsContainingDisHit(elementsContainingDisHit)
                .addMethodCallElements(methodCallElements).withReturnOutcome(defaultOutcome);
    }

    private PsiLocalVariable getLeftVariable(final PsiAssignmentExpression psiAssignmentExpression) {
        final PsiElement leftExpression = psiAssignmentExpression.getLExpression();
        if(!(leftExpression instanceof final PsiReferenceExpression leftRefExpression)) {
            return null;
        }
        final PsiElement resolveTarget = leftRefExpression.resolve();
        if(resolveTarget instanceof PsiLocalVariable) {
            return (PsiLocalVariable) resolveTarget;
        }
        return null;
    }

    private PsiThrowStatement getLastThrowStatementForCallstackMethod(final PsiElement callstackMethod, final List<SpecialExit> specialExitPoints) {
        for(int i = specialExitPoints.size() - 1; i >= 0; i--) {
            final SpecialExit specialExit = specialExitPoints.get(i);
            if(specialExit.exitType() == SpecialExitType.Throw && specialExit.jumpTarget() == callstackMethod) {
                return (PsiThrowStatement) specialExit.exitElement();
            }
        }
        return null;
    }

    private PsiStatement getLastReturnOrYieldStatementForCallstackMethod(final PsiElement callstackMethod, final List<SpecialExit> specialExitPoints) {
        for(int i = specialExitPoints.size() - 1; i >= 0; i--) {
            final SpecialExit specialExit = specialExitPoints.get(i);
            final SpecialExitType exitType = specialExit.exitType();
            if((exitType == SpecialExitType.Yield || exitType == SpecialExitType.Return) && specialExit.jumpTarget() == callstackMethod) {
                return (PsiStatement) specialExit.exitElement();
            }
        }
        return null;
    }

    private boolean isInCatchBlock(final PsiElement callstackMethod, final PsiStatement startingStatement) {
        return SQPsiUtil.getParentOfType(startingStatement, PsiCatchSection.class, callstackMethod) != null;
    }

    private RValueInfo processTakenReturnStatement(
            final PsiElement callstackMethod, final PsiStatement currentStatement,
            final RValueInfo returnExpressionValue, final Set<PsiElement> elementsContainingDisHit,
            final Set<PsiMethodCallExpression> methodCallElements) {
        final List<PsiElement> finallyBlocksHit = getHitFinallyBlocksBetween(callstackMethod, currentStatement);
        final RValueInfo ret = new RValueInfo()
                .addElementsContainingDisHit(elementsContainingDisHit)
                .addElementsContainingDisHit(returnExpressionValue.getElementsContainingDisHit())
                .addElementsContainingDisHit(finallyBlocksHit)
                .addMethodCallElements(methodCallElements)
                .addMethodCallElements(returnExpressionValue.getMethodCallElements())
                .withReturnOutcome(returnExpressionValue.getReturnOutcome());
        return ret;
    }

    private List<PsiElement> getHitFinallyBlocksBetween(final PsiElement callstackMethod, final PsiStatement currentStatement) {
        final List<PsiTryStatement> containingTryStatements = PsiTreeUtil.collectParents(currentStatement, PsiTryStatement.class, false, x -> x == callstackMethod || x == null);
        return containingTryStatements.stream().map(PsiTryStatement::getFinallyBlock)
                .filter(Objects::nonNull)
                .filter(x -> !PsiTreeUtil.isAncestor(x, currentStatement, false))
                .collect(Collectors.toList());
    }

    private void processScannedOverElement(
            final PsiStatement currentStatement, final Set<PsiElement> elementsContainingDisHit,
            final List<SpecialExit> specialExitPoints, final LocalVariablesInfo localVariablesInfo) {
        if(currentStatement == null) {
            return;
        }
        // Record special exit points.
        // Add elements to elementsContainingDisHit.
        // Update local variable assign status (if assigned within the block, set it to Unknown).
        specialExitPoints.addAll(getSpecialExitPoints(currentStatement));
        elementsContainingDisHit.add(currentStatement);
        updateReassignmentStatusIfNeeded(localVariablesInfo, currentStatement);
    }

    private ThrowInfo processThrowingElement(
            final PsiElement callstackMethod, final PsiStatement currentStatement,
            final ReturnOutcome defaultOutcome,
            final RValueInfo expressionValueThatThrows, final Set<PsiElement> elementsContainingDisHit,
            final Set<PsiMethodCallExpression> methodCallElements, final List<SpecialExit> specialExitPoints) {
        if(containsBreakOrContinueForParent(callstackMethod, currentStatement, specialExitPoints)) {
            // We could have broken out of one of our parent elements.
            // We can't assume this throwing statement is hit. Just add any DIs here and continue on.
            elementsContainingDisHit.addAll(expressionValueThatThrows.getElementsContainingDisHit());
            methodCallElements.addAll(expressionValueThatThrows.getMethodCallElements());
            return new ThrowInfo(NextStep.Continue, null);
        }
        if(containsSpecialExitForParent(callstackMethod, currentStatement, specialExitPoints, SpecialExitType.Return, SpecialExitType.Yield)) {
            // We can't infer the throw happens, but we can infer that no elements after this point are hit.
            // Either the return(s) we found earlier were hit or this throw statement is hit.
            // Either way, no elements after this point (in the forward scan) are hit.
            final RValueInfo finalOutcome = new RValueInfo().withReturnOutcome(defaultOutcome)
                    .addElementsContainingDisHit(elementsContainingDisHit)
                    .addElementsContainingDisHit(expressionValueThatThrows.getElementsContainingDisHit())
                    .addMethodCallElements(methodCallElements)
                    .addMethodCallElements(expressionValueThatThrows.getMethodCallElements());
            return new ThrowInfo(NextStep.Return, finalOutcome);
        }
        // We can infer the throw is hit. Return the exception.
        final RValueInfo finalOutcome = createFinalThrowingRValue(expressionValueThatThrows, elementsContainingDisHit, methodCallElements);
        return new ThrowInfo(NextStep.Return, finalOutcome);
    }

    private RValueInfo createFinalThrowingRValue(final RValueInfo expressionValueThatThrows, final Set<PsiElement> elementsContainingDisHit, @NotNull final Set<PsiMethodCallExpression> methodCallElements) {
        final RValueInfo ret = new RValueInfo()
                .addElementsContainingDisHit(elementsContainingDisHit)
                .addElementsContainingDisHit(expressionValueThatThrows.getElementsContainingDisHit())
                .addMethodCallElements(methodCallElements)
                .addMethodCallElements(expressionValueThatThrows.getMethodCallElements())
                .withExceptionThrown(expressionValueThatThrows.getElementThatThrows(), expressionValueThatThrows.getExceptionThrown());
        return ret;
    }

    private boolean containsBreakOrContinueForParent(final PsiElement callstackMethod, final PsiStatement currentStatement, final List<SpecialExit> specialExitPoints) {
        return containsSpecialExitForParent(callstackMethod, currentStatement, specialExitPoints, SpecialExitType.Break, SpecialExitType.Continue);
    }

    private boolean containsReturnForParent(final PsiElement callstackMethod, final PsiStatement currentStatement, final List<SpecialExit> specialExitPoints) {
        return containsSpecialExitForParent(callstackMethod, currentStatement, specialExitPoints, SpecialExitType.Return);
    }

    private boolean containsYieldForParent(final PsiElement callstackMethod, final PsiStatement currentStatement, final List<SpecialExit> specialExitPoints) {
        return containsSpecialExitForParent(callstackMethod, currentStatement, specialExitPoints, SpecialExitType.Yield);
    }

    private boolean containsSpecialExitForParent(final PsiElement callstackMethod, final PsiStatement currentStatement, final List<SpecialExit> specialExitPoints, final SpecialExitType... searchExits) {
        if(specialExitPoints.isEmpty()) {
            return false;
        }
        final Set<PsiElement> parentElements = getParentsBetweenInclusive(callstackMethod, currentStatement);
        for(final SpecialExit specialExit : specialExitPoints) {
            if(!ArrayUtils.contains(searchExits, specialExit.exitType())) {
                continue;
            }
            final PsiElement jumpTarget = specialExit.jumpTarget();
            if(jumpTarget == null) {
                continue;
            }
            if(parentElements.contains(jumpTarget)) {
                return true;
            }
        }
        return false;
    }

    private StatementStepInfo getFirstStatementForFalseCase(final PsiElement callstackMethod, final PsiIfStatement ifStatement) {
        final PsiStatement elseBranch = ifStatement.getElseBranch();
        if(elseBranch instanceof final PsiBlockStatement elseBlock) {
            final PsiStatement[] statements = elseBlock.getCodeBlock().getStatements();
            if(statements.length != 0) {
                return new StatementStepInfo(statements[0], Collections.emptyList());
            }
        }
        if(elseBranch != null) {
            return new StatementStepInfo(elseBranch, Collections.emptyList());
        }
        // The then-branch is empty. Get the next sequential statement after the if-statement.
        return getNextSequentialStatement(callstackMethod, ifStatement);
    }

    private StatementStepInfo getFirstStatementForTrueCase(
            final PsiElement callstackMethod, final PsiIfStatement ifStatement) {
        final PsiStatement thenBranch = ifStatement.getThenBranch();
        if(thenBranch instanceof final PsiBlockStatement thenBlock) {
            final PsiStatement[] statements = thenBlock.getCodeBlock().getStatements();
            if(statements.length != 0) {
                return new StatementStepInfo(statements[0], Collections.emptyList());
            }
        }
        if(thenBranch != null) {
            return new StatementStepInfo(thenBranch, Collections.emptyList());
        }
        // The then-branch is empty. Get the next sequential statement after the if-statement.
        return getNextSequentialStatement(callstackMethod, ifStatement);
    }

    public static ReturnOutcome getDefaultOutcome(@NotNull final PsiElement callstackElement) {
        if(callstackElement instanceof PsiClassInitializer) {
            return ReturnOutcome.Void;
        }
        if(callstackElement instanceof PsiField) {
            return ReturnOutcome.Unknown;
        }
        if(callstackElement instanceof final PsiMethod callstackMethod) {
            final PsiType returnType = callstackMethod.getReturnType();
            if(returnType == null) {
                if(callstackMethod.isConstructor()) {
                    return ReturnOutcome.Other;
                } else {
                    return ReturnOutcome.Unknown;
                }
            }
            if(PsiTypes.voidType().equals(returnType)) {
                return ReturnOutcome.Void;
            }
            return ReturnOutcome.Unknown;
        }
        if(callstackElement instanceof final PsiLambdaExpression psiLambdaExpression) {
            final PsiMethod samMethod = LambdaUtil.getFunctionalInterfaceMethod(psiLambdaExpression.getFunctionalInterfaceType());
            if(samMethod == null) {
                return ReturnOutcome.Unknown;
            }
            final PsiType returnType = samMethod.getReturnType();
            if(returnType == null) {
                // This shouldn't happen.
                return ReturnOutcome.Unknown;
            }
            if(PsiTypes.voidType().equals(returnType)) {
                return ReturnOutcome.Void;
            }
            return ReturnOutcome.Unknown;
        }
        return ReturnOutcome.Unknown;
    }

    private RValueInfo determineRValueForConditionImpl(
            final PsiExpression condition, final LocalVariablesInfo localVariablesInfo, final boolean preserveUnknown) {
        final RValueInfo ret = new RValueInfo();
        final LocalVariableAndRefs localVariableAndRefs = determineVariableToUse(condition, localVariablesInfo);
        if(localVariableAndRefs == null) {
            return ret.addElementContainingDisHit(condition).withUnknownReturnValue();
        }
        final ReturnOutcome startingOutcome = localVariableAndRefs.localVariable().getCurrentValue();
        final List<PsiReferenceExpression> startingElements = localVariableAndRefs.psiReferenceExpressions();
        if(startingElements.isEmpty()) {
            // The if statement does not use the local variable containing the DI.
            return ret.addElementContainingDisHit(condition).withUnknownReturnValue();
        }
        if(startingElements.size() == 1 || startingOutcome != ReturnOutcome.Empty) {
            final PsiReferenceExpression startingElement = startingElements.get(0);
            return computeRValueInfo(condition, startingElement, startingOutcome, preserveUnknown, true);
        }

        // We have multiple references to the local variable and the starting outcome is Empty.
        // The first reference is likely a null check.
        final List<RValueInfo> rValues = computeRValuesForStartingElements(condition, startingElements, startingOutcome, preserveUnknown);
        if(rValues.isEmpty()) {
            return ret.addElementContainingDisHit(condition).withUnknownReturnValue();
        }
        for(final RValueInfo rValue : rValues) {
            final ReturnOutcome returnOutcome = rValue.getReturnOutcome();
            if(rValue.getBooleanConversionMethod() == BooleanConversionMethod.CollectionMethod &&
                    (returnOutcome == ReturnOutcome.True || returnOutcome == ReturnOutcome.False)) {
                return rValue;
            }
        }
        return rValues.get(0);
    }

    @Nullable
    private LocalVariableAndRefs determineVariableToUse(final PsiExpression condition, final LocalVariablesInfo localVariablesInfo) {
        if(condition == null) {
            return null;
        }
        final LocalVariable primaryLocalVariable = localVariablesInfo.getPrimaryLocalVariable();
        if(primaryLocalVariable == null) {
            return null;
        }
        final ReturnOutcome currentValue = primaryLocalVariable.getCurrentValue();
        if(currentValue == null || currentValue == ReturnOutcome.Unknown) {
            return null;
        }
        final List<PsiVariableAndRef> refs = getReferencesInElement(condition);
        // If we have a reference to the primary local variable, use that.
        List<PsiReferenceExpression> refsToUse = getRefsToLocalVariable(refs, primaryLocalVariable);
        if(!refsToUse.isEmpty()) {
            return new LocalVariableAndRefs(primaryLocalVariable, refsToUse);
        }
        // We do not have a reference to the primary local variable. Iterate through the refs and choose the first one
        // that corresponds to a secondary local var. If the secondary local var is Unknown, bail out.
        for(final PsiVariableAndRef ref : refs) {
            final LocalVariable secondaryVariableToUse = localVariablesInfo.getVariable(ref.psiLocalVariable());
            if(secondaryVariableToUse == null) {
                continue;
            }
            final ReturnOutcome secondaryVariableValue = secondaryVariableToUse.getCurrentValue();
            if(secondaryVariableValue == null || secondaryVariableValue == ReturnOutcome.Unknown) {
                return null;
            }
            final List<PsiReferenceExpression> refsToSecondaryVariable = getRefsToLocalVariable(refs, secondaryVariableToUse);
            return new LocalVariableAndRefs(secondaryVariableToUse, refsToSecondaryVariable);
        }
        return null;
    }

    private List<PsiReferenceExpression> getRefsToLocalVariable(final List<PsiVariableAndRef> refs, final LocalVariable primaryLocalVariable) {
        final List<PsiReferenceExpression> ret = new ArrayList<>(2);
        for(final PsiVariableAndRef ref : refs) {
            if(ref.psiLocalVariable() == primaryLocalVariable.getPsiLocalVariable()) {
                ret.add(ref.psiReferenceExpression());
            }
        }
        return ret;
    }

    private List<PsiVariableAndRef> getReferencesInElement(final PsiExpression element) {
        if(element == null) {
            return Collections.emptyList();
        }
        return refsInElementCache.computeIfAbsent(element, this::getReferencesInElementImpl);
    }

    private List<PsiVariableAndRef> getReferencesInElementImpl(final PsiExpression element) {
        final List<PsiVariableAndRef> ret = new ArrayList<>();
        element.accept(new JavaRecursiveElementWalkingVisitor() {

            private final Set<PsiElement> complexScopesEntered = SetUtils.newIdentityHashSet();

            @Override
            public void visitClass(@NotNull final PsiClass theClass) {
                super.visitClass(theClass);
                complexScopesEntered.add(theClass);
            }

            @Override
            public void visitSwitchExpression(@NotNull final PsiSwitchExpression expression) {
                super.visitSwitchExpression(expression);
                complexScopesEntered.add(expression);
            }

            @Override
            public void visitClassInitializer(@NotNull final PsiClassInitializer initializer) {
                super.visitClassInitializer(initializer);
                complexScopesEntered.add(initializer);
            }

            @Override
            public void visitField(@NotNull final PsiField field) {
                super.visitField(field);
                complexScopesEntered.add(field);
            }

            @Override
            public void visitMethod(@NotNull final PsiMethod method) {
                super.visitMethod(method);
                complexScopesEntered.add(method);
            }

            @Override
            public void visitLambdaExpression(@NotNull final PsiLambdaExpression expression) {
                super.visitLambdaExpression(expression);
                complexScopesEntered.add(expression);
            }

            @Override
            protected void elementFinished(@NotNull final PsiElement element) {
                super.elementFinished(element);
                complexScopesEntered.remove(element);
            }

            @Override
            public void visitReferenceExpression(@NotNull final PsiReferenceExpression expression) {
                super.visitReferenceExpression(expression);
                if(!complexScopesEntered.isEmpty()) {
                    return;
                }
                final PsiElement target = expression.resolve();
                if(target instanceof PsiLocalVariable) {
                    ret.add(new PsiVariableAndRef((PsiLocalVariable) target, expression));
                }
            }
        });
        return ret;
    }

    private RValueInfo determineRValueForIterationValue(
            final PsiExpression iterationValueExpression, final LocalVariablesInfo localVariablesInfo, final boolean preserveUnknown) {
        final RValueInfo ret = new RValueInfo();
        final LocalVariableAndRefs localVariableAndRefs = determineVariableToUse(iterationValueExpression, localVariablesInfo);
        if(localVariableAndRefs == null) {
            return ret.addElementContainingDisHit(iterationValueExpression).withUnknownReturnValue();
        }
        final ReturnOutcome startingOutcome = localVariableAndRefs.localVariable().getCurrentValue();
        final List<PsiReferenceExpression> startingElements = localVariableAndRefs.psiReferenceExpressions();
        if(startingElements.isEmpty()) {
            // The if statement does not use the local variable containing the DI.
            return ret.addElementContainingDisHit(iterationValueExpression).withUnknownReturnValue();
        }
        final PsiReferenceExpression startingElement = startingElements.get(0);
        return computeRValueInfo(iterationValueExpression, startingElement, startingOutcome, preserveUnknown, true);
    }

    private RValueInfo determineRValueForDeclarationInitValue(
            final PsiExpression initExpression, final LocalVariablesInfo localVariablesInfo, final boolean preserveUnknown) {
        if(initExpression == null) {
            return new RValueInfo().withUnknownReturnValue();
        }
        final RValueInfo ret = new RValueInfo();
        final LocalVariableAndRefs localVariableAndRefs = determineVariableToUse(initExpression, localVariablesInfo);
        if(localVariableAndRefs == null || localVariableAndRefs.psiReferenceExpressions().isEmpty()) {
            if(isTrueLiteral(initExpression)) {
                return ret.withReturnOutcome(ReturnOutcome.True);
            }
            if(isFalseLiteral(initExpression)) {
                return ret.withReturnOutcome(ReturnOutcome.False);
            }
            if(isNullLiteral(initExpression)) {
                return ret.withReturnOutcome(ReturnOutcome.Null);
            }
            if(isFailedFutureInitExpression(initExpression)) {
                return ret.addElementContainingDisHit(initExpression).withReturnOutcome(ReturnOutcome.Failure);
            }
            if(isAbsentInitExpression(initExpression)) {
                return ret.addElementContainingDisHit(initExpression).withReturnOutcome(ReturnOutcome.Absent);
            }
            if(isEmptyInitExpression(initExpression)) {
                return ret.addElementContainingDisHit(initExpression).withReturnOutcome(ReturnOutcome.Empty);
            }
            return ret.addElementContainingDisHit(initExpression).withReturnOutcome(ReturnOutcome.Unknown);
        }
        final ReturnOutcome startingOutcome = localVariableAndRefs.localVariable().getCurrentValue();
        final List<PsiReferenceExpression> startingElements = localVariableAndRefs.psiReferenceExpressions();
        final PsiReferenceExpression startingElement = startingElements.get(0);
        return computeRValueInfo(initExpression, startingElement, startingOutcome, preserveUnknown, true);
    }

    private RValueInfo determineRValueForAssignedLocalVariable(
            final PsiExpression rhsExpression, final LocalVariablesInfo localVariablesInfo, final boolean preserveUnknown) {
        if(rhsExpression == null) {
            return new RValueInfo().withUnknownReturnValue();
        }

        final RValueInfo ret = new RValueInfo();
        final LocalVariableAndRefs localVariableAndRefs = determineVariableToUse(rhsExpression, localVariablesInfo);
        if(localVariableAndRefs == null || localVariableAndRefs.psiReferenceExpressions().isEmpty()) {
            if(isTrueLiteral(rhsExpression)) {
                return ret.withReturnOutcome(ReturnOutcome.True);
            }
            if(isFalseLiteral(rhsExpression)) {
                return ret.withReturnOutcome(ReturnOutcome.False);
            }
            if(isNullLiteral(rhsExpression)) {
                return ret.withReturnOutcome(ReturnOutcome.Null);
            }
            if(isFailedFutureInitExpression(rhsExpression)) {
                return ret.addElementContainingDisHit(rhsExpression).withReturnOutcome(ReturnOutcome.Failure);
            }
            if(isAbsentInitExpression(rhsExpression)) {
                return ret.addElementContainingDisHit(rhsExpression).withReturnOutcome(ReturnOutcome.Absent);
            }
            if(isEmptyInitExpression(rhsExpression)) {
                return ret.addElementContainingDisHit(rhsExpression).withReturnOutcome(ReturnOutcome.Empty);
            }
            return ret.addElementContainingDisHit(rhsExpression).withReturnOutcome(ReturnOutcome.Unknown);
        }

        final ReturnOutcome startingOutcome = localVariableAndRefs.localVariable().getCurrentValue();
        final List<PsiReferenceExpression> startingElements = localVariableAndRefs.psiReferenceExpressions();
        final PsiReferenceExpression startingElement = startingElements.get(0);
        return computeRValueInfo(rhsExpression, startingElement, startingOutcome, preserveUnknown, true);
    }

    private RValueInfo determineRValueForExpressionStatement(
            final PsiExpression expression, final LocalVariablesInfo localVariablesInfo, final boolean preserveUnknown) {
        final RValueInfo ret = new RValueInfo();
        final PsiType alwaysThrownException = methodPathsInfoProvider.tryGetHelperMethodException(expression);
        if(alwaysThrownException != null) {
            ret.addElementContainingDisHit(expression);
            ret.withExceptionThrown(expression, alwaysThrownException);
            return ret;
        }
        final LocalVariableAndRefs localVariableAndRefs = determineVariableToUse(expression, localVariablesInfo);
        if(localVariableAndRefs == null || localVariableAndRefs.psiReferenceExpressions().isEmpty()) {
            return ret.addElementContainingDisHit(expression).withUnknownReturnValue();
        }
        final ReturnOutcome startingOutcome = localVariableAndRefs.localVariable().getCurrentValue();
        final List<PsiReferenceExpression> startingElements = localVariableAndRefs.psiReferenceExpressions();
        final PsiReferenceExpression startingElement = startingElements.get(0);
        return computeRValueInfo(expression, startingElement, startingOutcome, preserveUnknown, true);
    }

    private RValueInfo determineRValueForReturnValueExpression(
            final PsiExpression rhsExpression, final LocalVariablesInfo localVariablesInfo, final ReturnOutcome defaultOutcome, final boolean preserveUnknown) {
        if(rhsExpression == null) {
            return new RValueInfo().withReturnOutcome(defaultOutcome);
        }
        final RValueInfo ret = new RValueInfo();
        final LocalVariableAndRefs localVariableAndRefs = determineVariableToUse(rhsExpression, localVariablesInfo);
        if(localVariableAndRefs == null || localVariableAndRefs.psiReferenceExpressions().isEmpty()) {
            if(isTrueLiteral(rhsExpression)) {
                return ret.withReturnOutcome(ReturnOutcome.True);
            }
            if(isFalseLiteral(rhsExpression)) {
                return ret.withReturnOutcome(ReturnOutcome.False);
            }
            if(isNullLiteral(rhsExpression)) {
                return ret.withReturnOutcome(ReturnOutcome.Null);
            }
            if(isFailedFutureInitExpression(rhsExpression)) {
                return ret.addElementContainingDisHit(rhsExpression).withReturnOutcome(ReturnOutcome.Failure);
            }
            if(isAbsentInitExpression(rhsExpression)) {
                return ret.addElementContainingDisHit(rhsExpression).withReturnOutcome(ReturnOutcome.Absent);
            }
            if(isEmptyInitExpression(rhsExpression)) {
                return ret.addElementContainingDisHit(rhsExpression).withReturnOutcome(ReturnOutcome.Empty);
            }
            return ret.addElementContainingDisHit(rhsExpression).withReturnOutcome(defaultOutcome);
        }
        final ReturnOutcome startingOutcome = localVariableAndRefs.localVariable().getCurrentValue();
        final List<PsiReferenceExpression> startingElements = localVariableAndRefs.psiReferenceExpressions();
        final PsiReferenceExpression startingElement = startingElements.get(0);
        return computeRValueInfo(rhsExpression, startingElement, startingOutcome, preserveUnknown, true);
    }

    private RValueInfo determineRValueForAssertExpression(
            final PsiExpression conditionExpression, final LocalVariablesInfo localVariablesInfo, final boolean preserveUnknown) {

        final RValueInfo conditionValue = determineRValueForConditionImpl(conditionExpression, localVariablesInfo, preserveUnknown);
        if(conditionValue.getReturnOutcome() == ReturnOutcome.False) {
            conditionValue.withExceptionThrown(conditionExpression, getAssertErrorType());
        }
        return conditionValue;
    }

    @NotNull
    private StatementStepInfo getNextStatementForTry(
            final PsiElement callstackMethod, @NotNull final PsiTryStatement tryStatementToEnter) {
        // Try to get the first statement in the try body.
        PsiCodeBlock blockToUse = tryStatementToEnter.getTryBlock();
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

    @NotNull
    private StatementStepInfo getNextStatementForSynchronized(
            final PsiElement callstackMethod, @NotNull final PsiSynchronizedStatement psiSynchronizedStatement) {
        // Try to get the first statement in the synchronized-body-block.
        PsiCodeBlock blockToUse = psiSynchronizedStatement.getBody();
        if(blockToUse != null) {
            final PsiStatement[] statements = blockToUse.getStatements();
            if(statements.length != 0) {
                return new StatementStepInfo(statements[0], Collections.emptyList());
            }
        }

        // Use the next sequential statement after the synchronized statement.
        return getNextSequentialStatement(callstackMethod, psiSynchronizedStatement);
    }

    private List<RValueInfo> computeRValuesForStartingElements(
            final PsiExpression condition, final List<PsiReferenceExpression> startingElements,
            final ReturnOutcome startingOutcome, final boolean preserveUnknown) {
        final List<RValueInfo> ret = new ArrayList<>();
        for(final PsiReferenceExpression startingElement : startingElements) {
            ret.add(computeRValueInfo(condition, startingElement, startingOutcome, preserveUnknown, true));
        }
        return ret;
    }

    private void updateReassignmentStatusIfNeeded(final LocalVariablesInfo localVariablesInfo, final PsiElement currentElement) {
        if(currentElement == null) {
            return;
        }
        final Set<PsiLocalVariable> reassignedVars = determineReassignedLocalVarsWithin(currentElement);
        for(final PsiLocalVariable reassignedVar : reassignedVars) {
            final LocalVariable existingLocalVar = localVariablesInfo.getVariable(reassignedVar);
            if(existingLocalVar != null) {
                existingLocalVar.setCurrentValue(ReturnOutcome.Unknown);
            }
        }
        final Set<PsiLocalVariable> varsWithAddCalls = determineVarsWithAddCallsWithin(currentElement);
        for(final PsiLocalVariable varWithAddCall : varsWithAddCalls) {
            final LocalVariable existingLocalVar = localVariablesInfo.getVariable(varWithAddCall);
            if(existingLocalVar != null) {
                existingLocalVar.setCurrentValue(ReturnOutcome.Unknown);
            }
        }
    }

    private Set<PsiLocalVariable> determineVarsWithAddCallsWithin(final PsiElement currentElement) {
        return varsWithAddCallsWithinElementCache.computeIfAbsent(currentElement, this::determineVarsWithAddCallsWithinImpl);
    }

    private Set<PsiLocalVariable> determineVarsWithAddCallsWithinImpl(final PsiElement currentElement) {
        final LazyIdentitySetBuilder<PsiLocalVariable> ret = new LazyIdentitySetBuilder<>();
        currentElement.accept(new JavaRecursiveElementWalkingVisitor() {
            @Override
            public void visitMethodCallExpression(@NotNull final PsiMethodCallExpression expression) {
                super.visitMethodCallExpression(expression);
                final PsiMethod psiMethod = expression.resolveMethod();
                if(psiMethod == null) {
                    return;
                }
                if(!StringUtils.equalsAny(psiMethod.getName(), "add", "addAll", "addFirst", "addLast")) {
                    return;
                }
                final PsiReferenceExpression methodExpression = expression.getMethodExpression();
                final PsiExpression leftSideOfTheDot = methodExpression.getQualifierExpression();
                if(!(leftSideOfTheDot instanceof PsiReferenceExpression)) {
                    return;
                }
                final PsiElement resolveTarget = ((PsiReferenceExpression) leftSideOfTheDot).resolve();
                if(!(resolveTarget instanceof PsiLocalVariable)) {
                    return;
                }
                ret.add((PsiLocalVariable) resolveTarget);
            }
        });
        return ret.toSet();
    }

    private Set<PsiLocalVariable> determineReassignedLocalVarsWithin(final PsiElement currentElement) {
        return elementToReassignedLocalVars.computeIfAbsent(currentElement, this::determineReassignedLocalVarsWithinImpl);
    }

    private Set<PsiLocalVariable> determineReassignedLocalVarsWithinImpl(final PsiElement currentElement) {
        final LazyIdentitySetBuilder<PsiLocalVariable> ret = new LazyIdentitySetBuilder<>();
        currentElement.accept(new JavaRecursiveElementWalkingVisitor() {
            @Override
            public void visitAssignmentExpression(@NotNull final PsiAssignmentExpression expression) {
                super.visitAssignmentExpression(expression);
                final PsiExpression leftExpression = expression.getLExpression();
                if(!(leftExpression instanceof final PsiReferenceExpression leftRefExpression)) {
                    return;
                }
                final PsiElement resolveTarget = leftRefExpression.resolve();
                if(resolveTarget instanceof PsiLocalVariable) {
                    ret.add((PsiLocalVariable) resolveTarget);
                }
            }
        });
        return ret.toSet();
    }

    @Nullable
    private RValueInfo tryGetImmediateReturnInfo(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression,
            final ReturnOutcome startingOutcome, final boolean preserveUnknown) {
        final PsiReturnStatement returnStatement = SQPsiUtil.getContainingReturnStatement(callstackMethod, psiMethodCallExpression);
        if(returnStatement == null) {
            return null;
        }
        final RValueInfo ret = new RValueInfo();
        final PsiExpression returnExpression = returnStatement.getReturnValue();
        if(returnExpression == null) {
            // This happens when the method is void (return statement doesn't have a value).
            return ret.addElementContainingDisHit(returnStatement).withReturnOutcome(ReturnOutcome.Void);
        }
        final RValueInfo rValueInfo = computeRValueInfo(returnExpression, psiMethodCallExpression, startingOutcome, preserveUnknown);
        // Add containing finally blocks.
        final List<PsiElement> finallyBlocksHit = getHitFinallyBlocksBetween(callstackMethod, returnStatement);
        rValueInfo.addElementsContainingDisHit(finallyBlocksHit);
        return rValueInfo;
    }

    @Nullable
    private RValueInfo tryGetImmediateThrowInfo(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression,
            final ReturnOutcome startingOutcome, final boolean preserveUnknown) {
        final PsiThrowStatement throwStatement = SQPsiUtil.getContainingThrowStatement(callstackMethod, psiMethodCallExpression);
        if(throwStatement == null) {
            return null;
        }
        final RValueInfo ret = new RValueInfo();
        final PsiExpression throwExpression = throwStatement.getException();
        if(throwExpression == null) {
            // This happens when the throw is incomplete (broken code).
            return ret.addElementContainingDisHit(throwStatement).withReturnOutcome(ReturnOutcome.Unknown);
        }
        final RValueInfo rValueInfo = computeRValueInfo(throwExpression, psiMethodCallExpression, startingOutcome, preserveUnknown);
        if(rValueInfo.getExceptionThrown() != null) {
            return rValueInfo;
        }
        final PsiType throwExpressionType = throwExpression.getType();
        if(throwExpressionType == null) {
            // This shouldn't happen.
            return rValueInfo;
        }
        rValueInfo.withExceptionThrown(throwStatement, throwExpressionType);
        return rValueInfo;
    }

    @Nullable
    private RValueInfo tryGetImmediateYieldInfo(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression,
            final ReturnOutcome startingOutcome, final boolean preserveUnknown) {
        final PsiYieldStatement yieldStatement = SQPsiUtil.getContainingYieldStatement(callstackMethod, psiMethodCallExpression);
        if(yieldStatement == null) {
            return null;
        }
        final RValueInfo ret = new RValueInfo();
        final PsiExpression yieldExpression = yieldStatement.getExpression();
        if(yieldExpression == null) {
            // This happens with the method is void (return statement doesn't have a value).
            return ret.addElementContainingDisHit(yieldStatement).withReturnOutcome(ReturnOutcome.Unknown);
        }
        return computeRValueInfo(yieldExpression, psiMethodCallExpression, startingOutcome, preserveUnknown);
    }

    @Nullable
    private RValueInfo tryGetSimpleSwitchLabeledRuleInfo(final PsiElement callstackMethod, final PsiElement startingExpression, final ReturnOutcome startingReturnOutcome, final boolean preserveUnknown) {
        final PsiSwitchLabeledRuleStatement simpleSwitchLabeledRule = getContainingSimpleSwitchLabeledRule(callstackMethod, startingExpression);
        if(simpleSwitchLabeledRule == null) {
            return null;
        }
        final PsiStatement body = simpleSwitchLabeledRule.getBody();
        if(!(body instanceof PsiExpressionStatement)) {
            return null;
        }
        final PsiElement labeledRuleParent = simpleSwitchLabeledRule.getParent();
        if(labeledRuleParent == null) {
            return null;
        }
        if(!(labeledRuleParent.getParent() instanceof PsiSwitchExpression)) {
            return null;
        }

        final PsiExpression ruleExpression = ((PsiExpressionStatement) body).getExpression();
        return computeRValueInfo(ruleExpression, startingExpression, startingReturnOutcome, preserveUnknown);
    }

    @Nullable
    private RValueInfo tryGetSwitchExpressionWithConditionThatContainsElement(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression,
            final ReturnOutcome startingReturnOutcome, final boolean preserveUnknown) {
        final PsiSwitchExpression psiSwitchExpression = getSwitchExpressionWithConditionThatContainsMethodCall(
                callstackMethod, psiMethodCallExpression);
        if(psiSwitchExpression == null) {
            return null;
        }
        final PsiExpression switchCondition = psiSwitchExpression.getExpression();
        if(switchCondition == null) {
            return new RValueInfo().addElementContainingDisHit(psiSwitchExpression)
                    .withReturnOutcome(ReturnOutcome.Unknown);
        }
        final RValueInfo expressionValue = computeRValueInfo(switchCondition, psiMethodCallExpression, startingReturnOutcome, preserveUnknown);
        final PsiType exceptionThrown = expressionValue.getExceptionThrown();
        if(exceptionThrown != null) {
            return expressionValue;
        }
        final PsiCodeBlock switchExpressionBody = psiSwitchExpression.getBody();
        if(switchExpressionBody != null) {
            expressionValue.addElementContainingDisHit(switchExpressionBody);
            expressionValue.withReturnOutcome(ReturnOutcome.Unknown);
        }
        return expressionValue;
    }

    @Nullable
    private StartingInfo tryGetSwitchStatementWithConditionThatContainsElement(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression,
            final ReturnOutcome startingReturnOutcome, final boolean preserveUnknown) {
        final PsiSwitchStatement psiSwitchStatement = getSwitchStatementWithConditionThatContainsMethodCall(
                callstackMethod, psiMethodCallExpression);
        if(psiSwitchStatement == null) {
            return null;
        }
        final PsiCodeBlock body = psiSwitchStatement.getBody();
        if(body == null) {
            return null;
        }
        final PsiExpression switchCondition = psiSwitchStatement.getExpression();
        if(switchCondition == null) {
            return null;
        }
        final RValueInfo expressionValue = computeRValueInfo(switchCondition, psiMethodCallExpression, startingReturnOutcome, preserveUnknown);
        final PsiType exceptionThrown = expressionValue.getExceptionThrown();
        if(exceptionThrown != null) {
            return new StartingInfo(callstackMethod, expressionValue, null, Collections.emptySet(), Collections.emptyList(), new LocalVariablesInfo());
        }
        final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
        elementsContainingDisHit.add(body);
        final StatementStepInfo nextSequentialStatement = getNextSequentialStatement(callstackMethod, psiSwitchStatement);
        elementsContainingDisHit.addAll(getExitedDoWhileConditions(nextSequentialStatement));
        final PsiStatement startingStatement = nextSequentialStatement.statement();
        final List<SpecialExit> specialExitPoints = new ArrayList<>(getSpecialExitPoints(body));
        return new StartingInfo(callstackMethod, expressionValue, startingStatement, elementsContainingDisHit, specialExitPoints, new LocalVariablesInfo());
    }

    @Nullable
    private StartingInfo tryGetTryWithResourceThatContainsMethodCall(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression,
            final ReturnOutcome startingOutcome, final boolean preserveUnknown) {
        final PsiResourceVariable psiResourceVariable = getParentOfType(psiMethodCallExpression, PsiResourceVariable.class, callstackMethod);
        if(psiResourceVariable == null) {
            return null;
        }
        PsiElement parent = psiResourceVariable.getParent();
        if(!(parent instanceof final PsiResourceList psiResourceList)) {
            return null;
        }
        final PsiExpression rhsExpression = psiResourceVariable.getInitializer();
        if(rhsExpression == null) {
            return null;
        }

        parent = psiResourceList.getParent();
        if(!(parent instanceof final PsiTryStatement psiTryStatement)) {
            return null;
        }
        final RValueInfo expressionValue = computeRValueInfo(rhsExpression, psiMethodCallExpression, startingOutcome, preserveUnknown);
        final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
        elementsContainingDisHit.add(psiResourceList);
        final StatementStepInfo currentStatementInfo = getNextStatementForTry(callstackMethod, psiTryStatement);
        elementsContainingDisHit.addAll(getExitedDoWhileConditions(currentStatementInfo));
        final LocalVariable primaryLocalVariable = new LocalVariable(psiResourceVariable, expressionValue.getReturnOutcome());
        final LocalVariablesInfo localVariablesInfo = new LocalVariablesInfo(primaryLocalVariable);
        return new StartingInfo(callstackMethod, expressionValue, currentStatementInfo.statement(), elementsContainingDisHit, Collections.emptyList(), localVariablesInfo);
    }

    @Nullable
    private StartingInfo tryGetVariableDecThatContainsMethodCall(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression,
            final ReturnOutcome startingOutcome, final boolean preserveUnknown) {
        final PsiLocalVariable psiLocalVariable = getParentOfType(psiMethodCallExpression, PsiLocalVariable.class, callstackMethod);
        if(psiLocalVariable == null) {
            return null;
        }

        final PsiDeclarationStatement psiDeclarationStatement = getParentOfType(psiLocalVariable, PsiDeclarationStatement.class, callstackMethod);
        if(psiDeclarationStatement == null) {
            return null;
        }
        final PsiElement[] declaredElements = psiDeclarationStatement.getDeclaredElements();
        final int index = getIndexOfElementThatContainsElement(declaredElements, psiLocalVariable);
        if(index == -1) {
            return null;
        }
        final PsiExpression rhsExpression = psiLocalVariable.getInitializer();
        if(rhsExpression == null) {
            return null;
        }
        final RValueInfo expressionValue = computeRValueInfo(rhsExpression, psiMethodCallExpression, startingOutcome, preserveUnknown);
        if(expressionValue.getExceptionThrown() != null) {
            // Our declaration throws, so no declarations after this are hit.
            return new StartingInfo(callstackMethod, expressionValue, null, Collections.emptySet(), Collections.emptyList(), new LocalVariablesInfo());
        }
        final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
        // Add any declared elements that come after us.
        elementsContainingDisHit.addAll(Arrays.asList(declaredElements).subList(index + 1, declaredElements.length));
        // Determine the next statement after the declaration.
        final StatementStepInfo currentStatementInfo = getNextSequentialStatement(callstackMethod, psiDeclarationStatement);
        elementsContainingDisHit.addAll(getExitedDoWhileConditions(currentStatementInfo));
        final LocalVariable primaryLocalVariable = new LocalVariable(psiLocalVariable, expressionValue.getReturnOutcome());
        final LocalVariablesInfo localVariablesInfo = new LocalVariablesInfo(primaryLocalVariable);
        return new StartingInfo(callstackMethod, expressionValue, currentStatementInfo.statement(), elementsContainingDisHit, Collections.emptyList(), localVariablesInfo);
    }

    @Nullable
    private StartingInfo tryGetAssignmentThatContainsMethodCall(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression,
            final ReturnOutcome startingOutcome, final boolean preserveUnknown) {
        final PsiAssignmentExpression psiAssignmentExpression = getParentOfType(psiMethodCallExpression, PsiAssignmentExpression.class, callstackMethod);
        if(psiAssignmentExpression == null) {
            return null;
        }
        if(!JavaTokenType.EQ.equals(psiAssignmentExpression.getOperationTokenType())) {
            return null;
        }
        final PsiExpression leftExpression = psiAssignmentExpression.getLExpression();
        if(!(leftExpression instanceof PsiReferenceExpression)) {
            return null;
        }
        final PsiElement leftTarget = ((PsiReferenceExpression) leftExpression).resolve();
        if(!(leftTarget instanceof final PsiLocalVariable leftTargetLocalVariable)) {
            return null;
        }
        final PsiExpression rhsExpression = psiAssignmentExpression.getRExpression();
        if(rhsExpression == null) {
            return null;
        }
        final PsiStatement containingStatement = getParentOfType(psiAssignmentExpression, PsiStatement.class, callstackMethod);
        if(!(containingStatement instanceof final PsiExpressionStatement psiExpressionStatement)) {
            return null;
        }
        if(psiExpressionStatement.getExpression() != psiAssignmentExpression) {
            // The DI call is in an assignment expression that is inside another expression.
            // Example: foo = otherFoo = fooService.getFoo();
            // Just ignore these cases.
            return null;
        }
        final RValueInfo expressionValue = computeRValueInfo(rhsExpression, psiMethodCallExpression, startingOutcome, preserveUnknown);
        if(expressionValue.getExceptionThrown() != null) {
            // Our declaration throws, so no declarations after this are hit.
            return new StartingInfo(callstackMethod, expressionValue, null, Collections.emptySet(), Collections.emptyList(), new LocalVariablesInfo());
        }
        final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
        // Determine the next statement after the declaration.
        final StatementStepInfo currentStatementInfo = getNextSequentialStatement(callstackMethod, psiExpressionStatement);
        elementsContainingDisHit.addAll(getExitedDoWhileConditions(currentStatementInfo));
        final LocalVariable primaryLocalVariable = new LocalVariable(leftTargetLocalVariable, expressionValue.getReturnOutcome());
        final LocalVariablesInfo localVariablesInfo = new LocalVariablesInfo(primaryLocalVariable);
        return new StartingInfo(callstackMethod, expressionValue, currentStatementInfo.statement(), elementsContainingDisHit, Collections.emptyList(), localVariablesInfo);
    }

    private List<PsiElement> getExitedDoWhileConditions(final StatementStepInfo nextStatementInfo) {
        return nextStatementInfo.exitedElements().stream()
                .filter(x -> x instanceof PsiDoWhileStatement)
                .map(x -> ((PsiDoWhileStatement) x).getCondition())
                .filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Nullable
    private StartingInfo tryGetWhileWithConditionThatContainsMethodCall(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression,
            final ReturnOutcome startingOutcome, final boolean preserveUnknown) {
        final PsiWhileStatement containingWhile = SQPsiUtil.getWhileWithConditionThatContainsMethodCall(callstackMethod, psiMethodCallExpression);
        if(containingWhile == null) {
            return null;
        }
        final PsiExpression condition = containingWhile.getCondition();
        if(condition == null) {
            // This shouldn't happen.
            return null;
        }
        final RValueInfo conditionRValue = computeRValueInfo(condition, psiMethodCallExpression, startingOutcome, preserveUnknown);
        // If the condition throws, bail out.
        if(conditionRValue.getExceptionThrown() != null) {
            return new StartingInfo(callstackMethod, conditionRValue, null, Collections.emptySet(), Collections.emptyList(), new LocalVariablesInfo());
        }
        final ReturnOutcome conditionReturnOutcome = conditionRValue.getReturnOutcome();
        if(conditionReturnOutcome == ReturnOutcome.True) {
            final PsiStatement thenBranch = containingWhile.getBody();
            if(thenBranch == null) {
                // The code is incomplete. Skip over the if-statement entirely.
                final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
                final StatementStepInfo nextSequentialStatement = getNextSequentialStatement(callstackMethod, containingWhile);
                elementsContainingDisHit.addAll(getExitedDoWhileConditions(nextSequentialStatement));
                final PsiStatement startingStatement = nextSequentialStatement.statement();
                return new StartingInfo(callstackMethod, conditionRValue, startingStatement, elementsContainingDisHit, Collections.emptyList(), new LocalVariablesInfo());
            }
            if(thenBranch instanceof PsiBlockStatement) {
                final PsiCodeBlock codeBlock = ((PsiBlockStatement) thenBranch).getCodeBlock();
                final PsiStatement[] statements = codeBlock.getStatements();
                if(statements.length == 0) {
                    // The then-branch is empty.
                    // Get the next statement after the if-statement.
                    final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
                    final StatementStepInfo nextSequentialStatement = getNextSequentialStatement(callstackMethod, containingWhile);
                    elementsContainingDisHit.addAll(getExitedDoWhileConditions(nextSequentialStatement));
                    final PsiStatement startingStatement = nextSequentialStatement.statement();
                    return new StartingInfo(callstackMethod, conditionRValue, startingStatement, elementsContainingDisHit, Collections.emptyList(), new LocalVariablesInfo());
                } else {
                    return new StartingInfo(callstackMethod, conditionRValue, statements[0], Collections.emptySet(), Collections.emptyList(), new LocalVariablesInfo());
                }
            }
            // The then-branch is not a block statement.
            // we have something like:
            // while (fooService.getFoo(..).isPresent())
            //    return true;
            // Note that there are no braces, only a single return statement.
            return new StartingInfo(callstackMethod, conditionRValue, thenBranch, Collections.emptySet(), Collections.emptyList(), new LocalVariablesInfo());
        } else if(conditionReturnOutcome == ReturnOutcome.False) {
            final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
            final StatementStepInfo nextSequentialStatement = getNextSequentialStatement(callstackMethod, containingWhile);
            elementsContainingDisHit.addAll(getExitedDoWhileConditions(nextSequentialStatement));
            final PsiStatement startingStatement = nextSequentialStatement.statement();
            return new StartingInfo(callstackMethod, conditionRValue, startingStatement, elementsContainingDisHit, Collections.emptyList(), new LocalVariablesInfo());
        }

        // The returnOutcome is unknown.
        // Add the if-block and else-blocks to disContainingElementsHit.
        // Record special exit points in both.
        // Get the next statement after the if-block.
        final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
        final PsiStatement thenBranch = containingWhile.getBody();
        if(thenBranch != null) {
            elementsContainingDisHit.add(thenBranch);
        }
        final List<SpecialExit> specialExitPoints = getSpecialExitPoints(thenBranch);
        final StatementStepInfo nextSequentialStatement = getNextSequentialStatement(callstackMethod, containingWhile);
        elementsContainingDisHit.addAll(getExitedDoWhileConditions(nextSequentialStatement));
        final PsiStatement startingStatement = nextSequentialStatement.statement();
        return new StartingInfo(callstackMethod, conditionRValue, startingStatement, elementsContainingDisHit, specialExitPoints, new LocalVariablesInfo());
    }

    @Nullable
    private StartingInfo tryGetIfWithConditionThatContainsMethodCall(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression,
            final ReturnOutcome startingOutcome, final boolean preserveUnknown) {
        final PsiIfStatement containingIfStatement = SQPsiUtil.getIfWithConditionThatContainsMethodCall(callstackMethod, psiMethodCallExpression);
        if(containingIfStatement == null) {
            return null;
        }
        final PsiExpression condition = containingIfStatement.getCondition();
        if(condition == null) {
            // This shouldn't happen.
            return null;
        }
        final RValueInfo conditionRValue = computeRValueInfo(condition, psiMethodCallExpression, startingOutcome, preserveUnknown);
        // If the condition throws, bail out.
        if(conditionRValue.getExceptionThrown() != null) {
            return new StartingInfo(callstackMethod, conditionRValue, null, Collections.emptySet(), Collections.emptyList(), new LocalVariablesInfo());
        }
        final ReturnOutcome conditionReturnOutcome = conditionRValue.getReturnOutcome();
        if(conditionReturnOutcome == ReturnOutcome.True) {
            final PsiStatement thenBranch = containingIfStatement.getThenBranch();
            if(thenBranch == null) {
                // The code is incomplete. Skip over the if-statement entirely.
                final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
                final StatementStepInfo nextSequentialStatement = getNextSequentialStatement(callstackMethod, containingIfStatement);
                elementsContainingDisHit.addAll(getExitedDoWhileConditions(nextSequentialStatement));
                final PsiStatement startingStatement = nextSequentialStatement.statement();
                return new StartingInfo(callstackMethod, conditionRValue, startingStatement, elementsContainingDisHit, Collections.emptyList(), new LocalVariablesInfo());
            }
            if(thenBranch instanceof PsiBlockStatement) {
                final PsiCodeBlock codeBlock = ((PsiBlockStatement) thenBranch).getCodeBlock();
                final PsiStatement[] statements = codeBlock.getStatements();
                if(statements.length == 0) {
                    // The then-branch is empty.
                    // Get the next statement after the if-statement.
                    final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
                    final StatementStepInfo nextSequentialStatement = getNextSequentialStatement(callstackMethod, containingIfStatement);
                    elementsContainingDisHit.addAll(getExitedDoWhileConditions(nextSequentialStatement));
                    final PsiStatement startingStatement = nextSequentialStatement.statement();
                    return new StartingInfo(callstackMethod, conditionRValue, startingStatement, elementsContainingDisHit, Collections.emptyList(), new LocalVariablesInfo());
                } else {
                    return new StartingInfo(callstackMethod, conditionRValue, statements[0], Collections.emptySet(), Collections.emptyList(), new LocalVariablesInfo());
                }
            }
            // The then-branch is not a block statement.
            // we have something like:
            // if (fooService.getFoo(..).isPresent())
            //    return true;
            // Note that there are no braces, only a single return statement.
            return new StartingInfo(callstackMethod, conditionRValue, thenBranch, Collections.emptySet(), Collections.emptyList(), new LocalVariablesInfo());
        } else if(conditionReturnOutcome == ReturnOutcome.False) {
            final PsiStatement elseBranch = containingIfStatement.getElseBranch();
            if(elseBranch == null) {
                // The code has no else-branch or is incomplete. Skip over the if-statement entirely.
                final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
                final StatementStepInfo nextSequentialStatement = getNextSequentialStatement(callstackMethod, containingIfStatement);
                elementsContainingDisHit.addAll(getExitedDoWhileConditions(nextSequentialStatement));
                final PsiStatement startingStatement = nextSequentialStatement.statement();
                return new StartingInfo(callstackMethod, conditionRValue, startingStatement, elementsContainingDisHit, Collections.emptyList(), new LocalVariablesInfo());
            }
            if(elseBranch instanceof PsiBlockStatement) {
                final PsiCodeBlock codeBlock = ((PsiBlockStatement) elseBranch).getCodeBlock();
                final PsiStatement[] statements = codeBlock.getStatements();
                if(statements.length == 0) {
                    // The else-branch is empty.
                    // Get the next statement after the if-statement.
                    final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
                    final StatementStepInfo nextSequentialStatement = getNextSequentialStatement(callstackMethod, containingIfStatement);
                    elementsContainingDisHit.addAll(getExitedDoWhileConditions(nextSequentialStatement));
                    final PsiStatement startingStatement = nextSequentialStatement.statement();
                    return new StartingInfo(callstackMethod, conditionRValue, startingStatement, elementsContainingDisHit, Collections.emptyList(), new LocalVariablesInfo());
                } else {
                    return new StartingInfo(callstackMethod, conditionRValue, statements[0], Collections.emptySet(), Collections.emptyList(), new LocalVariablesInfo());
                }
            }
            return new StartingInfo(callstackMethod, conditionRValue, elseBranch, Collections.emptySet(), Collections.emptyList(), new LocalVariablesInfo());
        }

        // The returnOutcome is unknown.
        // Add the if-block and else-blocks to disContainingElementsHit.
        // Record special exit points in both.
        // Get the next statement after the if-block.
        final Set<PsiElement> elementsContainingDisHit = SetUtils.newIdentityHashSet();
        final PsiStatement thenBranch = containingIfStatement.getThenBranch();
        final PsiStatement elseBranch = containingIfStatement.getElseBranch();
        if(thenBranch != null) {
            elementsContainingDisHit.add(thenBranch);
        }
        if(elseBranch != null) {
            elementsContainingDisHit.add(elseBranch);
        }
        final List<SpecialExit> specialExitPoints = new ArrayList<>();
        specialExitPoints.addAll(getSpecialExitPoints(thenBranch));
        specialExitPoints.addAll(getSpecialExitPoints(elseBranch));
        final StatementStepInfo nextSequentialStatement = getNextSequentialStatement(callstackMethod, containingIfStatement);
        elementsContainingDisHit.addAll(getExitedDoWhileConditions(nextSequentialStatement));
        final PsiStatement startingStatement = nextSequentialStatement.statement();
        return new StartingInfo(callstackMethod, conditionRValue, startingStatement, elementsContainingDisHit, specialExitPoints, new LocalVariablesInfo());
    }

    @NotNull
    public static StatementStepInfo getNextSequentialStatement(
            final PsiElement callstackMethod, final PsiStatement currentStatement) {
        if(currentStatement == null) {
            return new StatementStepInfo(null, Collections.emptyList());
        }
        PsiStatement nextStatement = PsiTreeUtil.getNextSiblingOfType(currentStatement, PsiStatement.class);
        if(nextStatement != null) {
            return new StatementStepInfo(nextStatement, Collections.emptyList());
        }
        final List<PsiElement> exitedElements = new ArrayList<>();
        PsiElement currentParent = currentStatement.getParent();
        while(currentParent != null) {
            if(currentParent == callstackMethod) {
                return new StatementStepInfo(null, exitedElements);
            }
            if(!(currentParent instanceof PsiStatement)) {
                exitedElements.add(currentParent);
                currentParent = currentParent.getParent();
                continue;
            }
            if(currentParent instanceof PsiBlockStatement) {
                final PsiElement nextParent = currentParent.getParent();
                if(nextParent instanceof PsiCodeBlock) {
                    // If the next parent is a code block, this is either an unnecessary block statement or a
                    // non-standard block statement; e.g. a block statement whose sole purpose is lexical scoping.
                    // In these cases, try to get the next sibling of the block statement.
                    // This logic is based on code in com.siyeh.ig.style.UnnecessaryBlockStatementInspection.UnnecessaryBlockStatementVisitor.visitBlockStatement.
                    // We do not need to care about variables declared within the block or whether the block comes after
                    // a PsiSwitchLabelStatement. In both cases, we take the next sibling if there is one.
                    final PsiStatement possibleNextStatement = PsiTreeUtil.getNextSiblingOfType(currentParent, PsiStatement.class);
                    if(possibleNextStatement != null) {
                        exitedElements.add(currentParent);
                        return new StatementStepInfo(possibleNextStatement, exitedElements);
                    }
                }
                exitedElements.add(currentParent);
                currentParent = currentParent.getParent();
                continue;
            }
            if(currentParent instanceof final PsiTryStatement psiTryStatement) {
                // We can't be in the resource list, because we started this call with a PsiStatement.
                // If we're in the body, get the first statement in the finally block.
                // If we're in the finally block, try to get the sibling statement of the try-statement.
                // Else (we're in the catch), get the first statement in the finally block.
                // If the first statement is null, try to get the sibling statement of the Try-statement.
                // If that is null, set currentParent=getParent() and continue.
                final PsiCodeBlock finallyBlock = psiTryStatement.getFinallyBlock();
                if(PsiTreeUtil.isAncestor(psiTryStatement.getTryBlock(), currentStatement, false)) {
                    // We're in the try-body. Try to get the first statement in the finally block.
                    final PsiStatement finallyBlockStatement = getFirstStatementInBlock(finallyBlock);
                    if(finallyBlockStatement != null) {
                        return new StatementStepInfo(finallyBlockStatement, exitedElements);
                    }
                    // Try to get the next sibling statement of the try-statement.
                    final PsiStatement nextSibling = PsiTreeUtil.getNextSiblingOfType(currentParent, PsiStatement.class);
                    if(nextSibling != null) {
                        exitedElements.add(currentParent);
                        return new StatementStepInfo(nextSibling, exitedElements);
                    }
                    // Otherwise, call getParent() and continue on.
                    exitedElements.add(currentParent);
                    currentParent = currentParent.getParent();
                    continue;
                }
                if(PsiTreeUtil.isAncestor(psiTryStatement.getFinallyBlock(), currentStatement, false)) {
                    // We're in the finally-block.
                    // Try to get the next sibling statement of the try-statement.
                    final PsiStatement nextSibling = PsiTreeUtil.getNextSiblingOfType(currentParent, PsiStatement.class);
                    if(nextSibling != null) {
                        exitedElements.add(currentParent);
                        return new StatementStepInfo(nextSibling, exitedElements);
                    }
                    // Otherwise, call getParent() and continue on.
                    exitedElements.add(currentParent);
                    currentParent = currentParent.getParent();
                    continue;
                }
                // We're in a catch block. Try to get the first statement in the finally block.
                final PsiStatement finallyBlockStatement = getFirstStatementInBlock(finallyBlock);
                if(finallyBlockStatement != null) {
                    exitedElements.add(currentParent);
                    return new StatementStepInfo(finallyBlockStatement, exitedElements);
                }
                // Try to get the next sibling statement of the try-statement.
                final PsiStatement nextSibling = PsiTreeUtil.getNextSiblingOfType(currentParent, PsiStatement.class);
                if(nextSibling != null) {
                    exitedElements.add(currentParent);
                    return new StatementStepInfo(nextSibling, exitedElements);
                }
                // Otherwise, call getParent() and continue on.
                exitedElements.add(currentParent);
                currentParent = currentParent.getParent();
                continue;
            }

            // Fallthrough. Use the next sibling if one is available.
            final PsiStatement nextSibling = PsiTreeUtil.getNextSiblingOfType(currentParent, PsiStatement.class);
            if(nextSibling != null) {
                exitedElements.add(currentParent);
                return new StatementStepInfo(nextSibling, exitedElements);
            }
            // Otherwise, continue going up the tree.
            exitedElements.add(currentParent);
            currentParent = currentParent.getParent();
        }
        return new StatementStepInfo(null, exitedElements);
    }

    @Nullable
    public static PsiStatement getFirstStatementInBlock(final PsiCodeBlock finallyBlock) {
        if(finallyBlock == null) {
            return null;
        }
        final PsiStatement[] statements = finallyBlock.getStatements();
        if(statements.length == 0) {
            return null;
        }
        return statements[0];
    }

    private List<SpecialExit> getSpecialExitPoints(final List<PsiElement> elements) {
        if(elements.isEmpty()) {
            return Collections.emptyList();
        }
        final List<SpecialExit> ret = new ArrayList<>();
        for(final PsiElement element : elements) {
            ret.addAll(getSpecialExitPoints(element));
        }
        return ret;
    }

    private List<SpecialExit> getSpecialExitPoints(@Nullable final PsiElement containingElement) {
        if(containingElement == null) {
            return Collections.emptyList();
        }
        return specialExitInfoCache.computeIfAbsent(containingElement, this::computeSpecialExitPointsImpl);
    }

    private List<SpecialExit> computeSpecialExitPointsImpl(@NotNull final PsiElement containingElement) {
        final List<SpecialExit> ret = new ArrayList<>();
        containingElement.accept(new JavaRecursiveElementWalkingVisitor() {
            @Override
            public void visitBreakStatement(@NotNull final PsiBreakStatement statement) {
                super.visitBreakStatement(statement);
                final PsiStatement exitedStatement = statement.findExitedStatement();
                if(exitedStatement == null) {
                    return;
                }
                if(exitedStatement == containingElement) {
                    return;
                }
                if(!PsiTreeUtil.isAncestor(containingElement, exitedStatement, true)) {
                    // We are jumping to a point above the starting point.
                    // This is a special exit point.
                    ret.add(new SpecialExit(statement, exitedStatement, SpecialExitType.Break));
                }
            }

            @Override
            public void visitContinueStatement(@NotNull final PsiContinueStatement statement) {
                super.visitContinueStatement(statement);
                final PsiStatement continuedStatement = statement.findContinuedStatement();
                if(continuedStatement == null) {
                    return;
                }
                if(continuedStatement == containingElement) {
                    return;
                }
                if(!PsiTreeUtil.isAncestor(containingElement, continuedStatement, true)) {
                    // We are jumping to a point above the starting point.
                    // This is a special exit point.
                    ret.add(new SpecialExit(statement, continuedStatement, SpecialExitType.Continue));
                }
            }

            @Override
            public void visitReturnStatement(@NotNull final PsiReturnStatement statement) {
                super.visitReturnStatement(statement);
                // Get the target of this return statement.
                final PsiElement parentClosure = getParentClosure(statement);
                if(parentClosure == null) {
                    return;
                }
                if(parentClosure == containingElement) {
                    return;
                }
                if(!PsiTreeUtil.isAncestor(containingElement, parentClosure, true)) {
                    // We are jumping to a point above the starting point.
                    // This is a special exit point.
                    ret.add(new SpecialExit(statement, parentClosure, SpecialExitType.Return));
                }
            }

            @Override
            public void visitYieldStatement(@NotNull final PsiYieldStatement statement) {
                super.visitYieldStatement(statement);
                final PsiElement parentClosure = getParentSwitchExpression(statement);
                if(parentClosure == null) {
                    return;
                }
                if(parentClosure == containingElement) {
                    return;
                }
                if(!PsiTreeUtil.isAncestor(containingElement, parentClosure, true)) {
                    // We are jumping to a point above the starting point.
                    // This is a special exit point.
                    ret.add(new SpecialExit(statement, parentClosure, SpecialExitType.Yield));
                }
            }

            @Override
            public void visitThrowStatement(@NotNull final PsiThrowStatement statement) {
                super.visitThrowStatement(statement);
                final PsiElement parentClosure = getNextImportantParent(statement);
                ret.add(new SpecialExit(statement, parentClosure, SpecialExitType.Throw));
            }
        });
        return ret;
    }

    private RValueInfo computeRValueInfo(
            final PsiExpression topExpression, final PsiElement startingElement,
            final ReturnOutcome startingOutcome, final boolean preserveUnknown) {
        return computeRValueInfo(topExpression, startingElement, startingOutcome, preserveUnknown, false);
    }

    private RValueInfo computeRValueInfo(
            final PsiExpression topExpression, final PsiElement startingElement,
            final ReturnOutcome startingOutcome, final boolean preserveUnknown, final boolean includeMethodCallQualifiers) {
        final RValueInfo ret = new RValueInfo().withReturnOutcome(startingOutcome);
        if(startingElement == topExpression) {
            return ret;
        }
        final PsiLocalVariable startingElementLocalVariable = getStartingElementLocalVariable(startingElement);
        // Determine the followup info.
        final PsiElement stoppingElement = topExpression.getParent();
        CurrentMethodState currentMethodState = null;
        PsiElement currentElement = startingElement.getParent();
        if(includeMethodCallQualifiers) {
            addElementsWithDisHitBefore(ret, currentElement, stoppingElement);
        }
        while(currentElement != null) {
            if(currentElement == stoppingElement) {
                // We've reached the top. Return.
                return ret;
            }
            if(currentElement instanceof PsiExpressionList) {
                // We're inside of a method call.
                currentMethodState = CurrentMethodState.IsInMethodCall;
                currentElement = currentElement.getParent();
                continue;
            }
            if(currentElement instanceof PsiReferenceExpression) {
                // We're the qualifier of a method call.
                currentMethodState = CurrentMethodState.IsQualifierForChainedCall;
                currentElement = currentElement.getParent();
                continue;
            }
            if(currentElement instanceof PsiTypeCastExpression) {
                currentElement = currentElement.getParent();
                continue;
            }
            if(currentElement instanceof PsiArrayAccessExpression
                    || currentElement instanceof PsiArrayInitializerExpression
                    || currentElement instanceof PsiClassObjectAccessExpression) {
                currentElement = currentElement.getParent();
                continue;
            }
            if(currentElement instanceof final PsiNewExpression psiNewExpression) {
                final PsiMethod psiMethod = psiNewExpression.resolveConstructor();
                final PsiExpressionList argumentList = psiNewExpression.getArgumentList();
                final ReturnOutcome currentOutcome = ret.getReturnOutcome();
                if(argumentList == null) {
                    // This is an anon inner class, and we came up through the body.
                    currentElement = currentElement.getParent();
                    continue;
                }
                final PsiExpression[] argumentExpressions = argumentList.getExpressions();
                final PsiElement argThatContainsElement = getElementThatContainsChild(argumentExpressions, startingElement);
                if(argThatContainsElement == null) {
                    // This is an anon inner class, and we came up through the body.
                    currentElement = currentElement.getParent();
                    continue;
                }

                // We came up through an argument in the constructor call.
                ret.addElementsContainingDisHit(getElementsAfter(argumentExpressions, argThatContainsElement));
                if(psiMethod == null) {
                    if(currentOutcome == ReturnOutcome.Empty) {
                        ret.withReturnOutcome(ReturnOutcome.Unknown);
                    } else {
                        ret.withReturnOutcome(ReturnOutcome.Other);
                    }
                    currentElement = currentElement.getParent();
                    continue;
                }

                if(currentOutcome == ReturnOutcome.Zero) {
                    if(isEmptyInitExpression(psiNewExpression)) {
                        ret.withReturnOutcome(ReturnOutcome.Empty);
                    } else {
                        ret.withReturnOutcome(ReturnOutcome.Unknown);
                    }
                    currentElement = currentElement.getParent();
                    continue;
                }

                if(currentOutcome == ReturnOutcome.Empty) {
                    if(isListConstructorWithSingleIterableParam(psiMethod)) {
                        currentElement = currentElement.getParent();
                        continue;
                    } else {
                        ret.withReturnOutcome(ReturnOutcome.Unknown);
                        currentElement = currentElement.getParent();
                        continue;
                    }
                }
                ret.withReturnOutcome(ReturnOutcome.Other);
                currentElement = currentElement.getParent();
                continue;
            }
            if(currentElement instanceof final PsiAnonymousClass currentClass) {
                final PsiExpressionList argumentList = currentClass.getArgumentList();
                final PsiClassInitializer[] initializers = currentClass.getInitializers();
                final PsiMethod[] constructors = currentClass.getConstructors();
                final PsiMethod[] methods = Arrays.stream(currentClass.getMethods()).filter(x -> !x.isConstructor()).toArray(PsiMethod[]::new);
                final PsiField[] fields = currentClass.getFields();
                final PsiClass[] innerClasses = currentClass.getInnerClasses();
                if(PsiTreeUtil.isAncestor(argumentList, startingElement, false)) {
                    // We came from an argument passed to the constructor in the PsiNewExpression.
                    // We will add the args when we process the PsiNewExpression later (higher up the tree).
                    // Just add the anon inner class elements now.
                    ret.addElementsContainingDisHit(Arrays.asList(fields));
                    ret.addElementsContainingDisHit(Arrays.asList(initializers));
                    ret.addElementsContainingDisHit(Arrays.asList(constructors));
                    ret.addElementsContainingDisHit(Arrays.asList(methods));
                    ret.addElementsContainingDisHit(Arrays.asList(innerClasses));
                    currentElement = currentElement.getParent();
                    continue;
                }

                final PsiClassInitializer initBlockContainingDi = getElementThatContainsChild(initializers, startingElement);
                if(initBlockContainingDi != null) {
                    ret.addElementsContainingDisHit(getSiblingsAfter(initBlockContainingDi));
                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                    currentElement = currentElement.getParent();
                    continue;
                }

                final PsiMethod constructor = getElementThatContainsChild(constructors, startingElement);
                if(constructor != null) {
                    ret.addElementsContainingDisHit(getSiblingsAfter(constructor));
                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                    currentElement = currentElement.getParent();
                    continue;
                }

                final PsiField field = getElementThatContainsChild(fields, startingElement);
                if(field != null) {
                    ret.addElementsContainingDisHit(getSiblingsAfter(field));
                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                    currentElement = currentElement.getParent();
                    continue;
                }

                PsiClass innerClass = getElementThatContainsChild(innerClasses, startingElement);
                if(innerClass != null) {
                    // Add all elements that come after the inner class.
                    ret.addElementsContainingDisHit(getSiblingsAfter(innerClass));
                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                    currentElement = currentElement.getParent();
                    continue;
                }

                final PsiMethod method = getElementThatContainsChild(methods, startingElement);
                if(method != null) {
                    ret.addElementsContainingDisHit(getSiblingsAfter(method));
                    if(methods.length != 1) {
                        // If we have more than one method, don't propagate the return value.
                        ret.withReturnOutcome(ReturnOutcome.Unknown);
                    }
                    currentElement = currentElement.getParent();
                    continue;
                }

                // We don't know how we got to the anon inner class.
                ret.addElementContainingDisHit(currentClass);
                ret.withReturnOutcome(ReturnOutcome.Unknown);
                currentElement = currentElement.getParent();
                continue;
            }
            if(currentElement instanceof final PsiClass currentClass) {
                // We're in a named inner class within a local class or an anonymous inner class.
                final PsiClassInitializer[] initializers = currentClass.getInitializers();
                final PsiMethod[] constructors = currentClass.getConstructors();
                final PsiMethod[] methods = Arrays.stream(currentClass.getMethods()).filter(x -> !x.isConstructor()).toArray(PsiMethod[]::new);
                final PsiField[] fields = currentClass.getFields();
                final PsiClass[] innerClasses = currentClass.getInnerClasses();

                final PsiClassInitializer initBlockContainingDi = getElementThatContainsChild(initializers, startingElement);
                if(initBlockContainingDi != null) {
                    ret.addElementsContainingDisHit(getSiblingsAfter(initBlockContainingDi));
                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                    currentElement = currentElement.getParent();
                    continue;
                }

                final PsiMethod constructor = getElementThatContainsChild(constructors, startingElement);
                if(constructor != null) {
                    ret.addElementsContainingDisHit(getSiblingsAfter(constructor));
                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                    currentElement = currentElement.getParent();
                    continue;
                }

                final PsiField field = getElementThatContainsChild(fields, startingElement);
                if(field != null) {
                    ret.addElementsContainingDisHit(getSiblingsAfter(field));
                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                    currentElement = currentElement.getParent();
                    continue;
                }

                PsiClass innerClass = getElementThatContainsChild(innerClasses, startingElement);
                if(innerClass != null) {
                    // Add all elements that come after the inner class.
                    ret.addElementsContainingDisHit(getSiblingsAfter(innerClass));
                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                    currentElement = currentElement.getParent();
                    continue;
                }

                final PsiMethod method = getElementThatContainsChild(methods, startingElement);
                if(method != null) {
                    ret.addElementsContainingDisHit(getSiblingsAfter(method));
                    if(methods.length != 1) {
                        // If we have more than one method, don't propagate the return value.
                        ret.withReturnOutcome(ReturnOutcome.Unknown);
                    }
                    currentElement = currentElement.getParent();
                    continue;
                }

                // We don't know how we got to the anon inner class.
                ret.addElementContainingDisHit(currentClass);
                ret.withReturnOutcome(ReturnOutcome.Unknown);
                currentElement = currentElement.getParent();
                continue;
            }
            if(currentElement instanceof final PsiConditionalExpression conditionalExpression) {
                final PsiExpression condition = conditionalExpression.getCondition();
                if(PsiTreeUtil.isAncestor(condition, startingElement, false)) {
                    // We are in the condition.
                    // ret.addElementContainingDisHit(condition);
                    final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
                    if(currentReturnOutcome == ReturnOutcome.True) {
                        final PsiExpression selectedExpression = conditionalExpression.getThenExpression();
                        final Optional<PsiVariableAndRef> refToStartingLocalVar = getReferencesInElement(selectedExpression).stream().filter(x -> x.psiLocalVariable() == startingElementLocalVariable).findAny();
                        if(refToStartingLocalVar.isPresent()) {
                            final RValueInfo selectedRValue = computeRValueInfo(selectedExpression, refToStartingLocalVar.get().psiReferenceExpression(), startingOutcome, preserveUnknown, true);
                            ret.addElementsContainingDisHit(selectedRValue.getElementsContainingDisHit());
                            ret.addMethodCallElements(selectedRValue.getMethodCallElements());
                            ret.withReturnOutcome(selectedRValue.getReturnOutcome());
                            currentElement = currentElement.getParent();
                            continue;
                        }
                        if(isReferenceToLocalVariable(selectedExpression, startingElementLocalVariable)) {
                            ret.withReturnOutcome(startingOutcome);
                            currentElement = currentElement.getParent();
                            continue;
                        }
                        final ReturnOutcome hardcodedReturnOutcome = getHardcodedReturnOutcome(selectedExpression);
                        if(hardcodedReturnOutcome != null) {
                            ret.addElementContainingDisHit(selectedExpression);
                            ret.withReturnOutcome(hardcodedReturnOutcome);
                            currentElement = currentElement.getParent();
                            continue;
                        }
                        ret.addElementContainingDisHit(selectedExpression);
                        ret.withReturnOutcome(ReturnOutcome.Unknown);
                        currentElement = currentElement.getParent();
                        continue;
                    } else if(currentReturnOutcome == ReturnOutcome.False) {
                        final PsiExpression selectedExpression = conditionalExpression.getElseExpression();
                        final Optional<PsiVariableAndRef> refToStartingLocalVar = getReferencesInElement(selectedExpression).stream().filter(x -> x.psiLocalVariable() == startingElementLocalVariable).findAny();
                        if(refToStartingLocalVar.isPresent()) {
                            final RValueInfo selectedRValue = computeRValueInfo(selectedExpression, refToStartingLocalVar.get().psiReferenceExpression(), startingOutcome, preserveUnknown, true);
                            ret.addElementsContainingDisHit(selectedRValue.getElementsContainingDisHit());
                            ret.addMethodCallElements(selectedRValue.getMethodCallElements());
                            ret.withReturnOutcome(selectedRValue.getReturnOutcome());
                            currentElement = currentElement.getParent();
                            continue;
                        }
                        if(isReferenceToLocalVariable(selectedExpression, startingElementLocalVariable)) {
                            ret.withReturnOutcome(startingOutcome);
                            currentElement = currentElement.getParent();
                            continue;
                        }
                        final ReturnOutcome hardcodedReturnOutcome = getHardcodedReturnOutcome(selectedExpression);
                        if(hardcodedReturnOutcome != null) {
                            ret.addElementContainingDisHit(selectedExpression);
                            ret.withReturnOutcome(hardcodedReturnOutcome);
                            currentElement = currentElement.getParent();
                            continue;
                        }
                        ret.addElementContainingDisHit(selectedExpression);
                        ret.withReturnOutcome(ReturnOutcome.Unknown);
                        currentElement = currentElement.getParent();
                        continue;
                    }
                    // We can't infer the condition. Add all DIs in the two branches and continue walking.
                    ret.addElementContainingDisHit(conditionalExpression.getThenExpression());
                    ret.addElementContainingDisHit(conditionalExpression.getElseExpression());
                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                    currentElement = currentElement.getParent();
                    continue;
                } else {
                    // We came up through a branch.
                    if(startingElementLocalVariable != null) {
                        // We started with a local variable from a previous DI call.
                        // This is the first ref to it.
                        // The condition must be something that does not involve the DI.
                        // Add the condition and the other branch to disHitAfter.
                        final PsiExpression thenExpression = conditionalExpression.getThenExpression();
                        if(PsiTreeUtil.isAncestor(thenExpression, startingElement, false)) {
                            ret.addElementContainingDisHit(condition);
                            final PsiExpression otherExpression = conditionalExpression.getElseExpression();
                            final Optional<PsiVariableAndRef> refToStartingLocalVar = getReferencesInElement(otherExpression).stream().filter(x -> x.psiLocalVariable() == startingElementLocalVariable).findAny();
                            if(refToStartingLocalVar.isPresent()) {
                                final RValueInfo otherRValue = computeRValueInfo(otherExpression, refToStartingLocalVar.get().psiReferenceExpression(), startingOutcome, preserveUnknown, true);
                                ret.addElementsContainingDisHit(otherRValue.getElementsContainingDisHit());
                                ret.addMethodCallElements(otherRValue.getMethodCallElements());
                                if(ret.getReturnOutcome() != otherRValue.getReturnOutcome()) {
                                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                                }
                                currentElement = currentElement.getParent();
                                continue;
                            }
                            if(isReferenceToLocalVariable(otherExpression, startingElementLocalVariable)) {
                                if(ret.getReturnOutcome() != startingOutcome) {
                                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                                }
                                currentElement = currentElement.getParent();
                                continue;
                            }
                            final ReturnOutcome hardcodedReturnOutcome = getHardcodedReturnOutcome(otherExpression);
                            if(hardcodedReturnOutcome != null) {
                                if(hardcodedReturnOutcome != ret.getReturnOutcome()) {
                                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                                }
                                ret.addElementContainingDisHit(otherExpression);
                                currentElement = currentElement.getParent();
                                continue;
                            }
                            ret.addElementContainingDisHit(otherExpression);
                            ret.withReturnOutcome(ReturnOutcome.Unknown);
                            currentElement = currentElement.getParent();
                            continue;
                        } else {
                            ret.addElementContainingDisHit(condition);
                            final PsiExpression otherExpression = conditionalExpression.getThenExpression();
                            final Optional<PsiVariableAndRef> refToStartingLocalVar = getReferencesInElement(otherExpression).stream().filter(x -> x.psiLocalVariable() == startingElementLocalVariable).findAny();
                            if(refToStartingLocalVar.isPresent()) {
                                final RValueInfo otherRValue = computeRValueInfo(otherExpression, refToStartingLocalVar.get().psiReferenceExpression(), startingOutcome, preserveUnknown, true);
                                ret.addElementsContainingDisHit(otherRValue.getElementsContainingDisHit());
                                ret.addMethodCallElements(otherRValue.getMethodCallElements());
                                if(ret.getReturnOutcome() != otherRValue.getReturnOutcome()) {
                                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                                }
                                currentElement = currentElement.getParent();
                                continue;
                            }
                            if(isReferenceToLocalVariable(otherExpression, startingElementLocalVariable)) {
                                if(ret.getReturnOutcome() != startingOutcome) {
                                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                                }
                                currentElement = currentElement.getParent();
                                continue;
                            }
                            final ReturnOutcome hardcodedReturnOutcome = getHardcodedReturnOutcome(otherExpression);
                            if(hardcodedReturnOutcome != null) {
                                if(hardcodedReturnOutcome != ret.getReturnOutcome()) {
                                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                                }
                                ret.addElementContainingDisHit(otherExpression);
                                currentElement = currentElement.getParent();
                                continue;
                            }
                            ret.addElementContainingDisHit(otherExpression);
                            ret.withReturnOutcome(ReturnOutcome.Unknown);
                            currentElement = currentElement.getParent();
                            continue;
                        }
                    }
                }

                // We are one of the branches, not the condition.
                // We started at a DI call and not at a ref to a local var.
                // Just continue.
                currentElement = currentElement.getParent();
                continue;
            }
            if(currentElement instanceof final PsiBinaryExpression psiBinaryExpression) {
                final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
                if(currentReturnOutcome == ReturnOutcome.True || currentReturnOutcome == ReturnOutcome.False) {
                    currentElement = currentElement.getParent();
                    continue;
                }
                if(SQPsiUtil.isNotEqualNullCheck(psiBinaryExpression)) {
                    ret.withBooleanConversionMethod(BooleanConversionMethod.NullCheck);
                    if(currentReturnOutcome == ReturnOutcome.Null) {
                        ret.withReturnOutcome(ReturnOutcome.False);
                    } else {
                        if(currentReturnOutcome != ReturnOutcome.Unknown || !preserveUnknown) {
                            ret.withReturnOutcome(ReturnOutcome.True);
                        }
                    }
                } else if(SQPsiUtil.isEqualNullCheck(psiBinaryExpression)) {
                    ret.withBooleanConversionMethod(BooleanConversionMethod.NullCheck);
                    if(currentReturnOutcome == ReturnOutcome.Null) {
                        ret.withReturnOutcome(ReturnOutcome.True);
                    } else {
                        if(currentReturnOutcome != ReturnOutcome.Unknown || !preserveUnknown) {
                            ret.withReturnOutcome(ReturnOutcome.False);
                        }
                    }
                } else if(SQPsiUtil.isNotEqualZeroCheck(psiBinaryExpression)) {
                    if(currentReturnOutcome == ReturnOutcome.Zero) {
                        ret.withReturnOutcome(ReturnOutcome.False);
                        ret.withBooleanConversionMethod(BooleanConversionMethod.CollectionMethod);
                    } else if(currentReturnOutcome == ReturnOutcome.Other) {
                        ret.withReturnOutcome(ReturnOutcome.True);
                        ret.withBooleanConversionMethod(BooleanConversionMethod.CollectionMethod);
                    } else {
                        ret.addElementsContainingDisHit(getArgsThatDoNotContainElement(psiBinaryExpression.getOperands(), startingElement));
                        currentElement = currentElement.getParent();
                        ret.withReturnOutcome(ReturnOutcome.Unknown);
                        continue;
                    }
                } else if(SQPsiUtil.isEqualZeroCheck(psiBinaryExpression)) {
                    if(currentReturnOutcome == ReturnOutcome.Zero) {
                        ret.withReturnOutcome(ReturnOutcome.True);
                        ret.withBooleanConversionMethod(BooleanConversionMethod.CollectionMethod);
                    } else if(currentReturnOutcome == ReturnOutcome.Other) {
                        ret.withReturnOutcome(ReturnOutcome.False);
                        ret.withBooleanConversionMethod(BooleanConversionMethod.CollectionMethod);
                    } else {
                        ret.addElementsContainingDisHit(getArgsThatDoNotContainElement(psiBinaryExpression.getOperands(), startingElement));
                        currentElement = currentElement.getParent();
                        ret.withReturnOutcome(ReturnOutcome.Unknown);
                        continue;
                    }
                } else {
                    // Add the other operand as an element containing DIs that are hit. The other operand will be
                    // evaluated.
                    ret.addElementsContainingDisHit(getArgsThatDoNotContainElement(psiBinaryExpression.getOperands(), startingElement));
                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                    currentElement = currentElement.getParent();
                    continue;
                }
                currentElement = currentElement.getParent();
                continue;
            }
            if(currentElement instanceof final PsiAssignmentExpression psiAssignmentExpression) {
                final PsiElement lhs = psiAssignmentExpression.getLExpression();
                if(PsiTreeUtil.isAncestor(lhs, startingElement, false)) {
                    // We came up through the LHS of an assignment expression. The local variable is being reassigned.
                    // Set the status to unknown. Add the RHS to elementsContainingDisHit.
                    ret.addElementContainingDisHit(psiAssignmentExpression.getRExpression());
                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                }
                // In both cases (LHS and RHS), continue on.
                // If we come up through the RHS of the expression, we have an assignment expression inside something
                // that we're inspecting (computing the RValue for).
                // Example:
                // final S3Object s3Object;
                // if((s3Object = s3Client.getObject(new GetObjectRequest(bucketName, path))) != null) {
                // }
                // return null;
                // In this case, continue on, because the assignment expression evaluates to the RHS value.
                currentElement = currentElement.getParent();
                continue;
            }
            if(currentElement instanceof final PsiPrefixExpression psiPrefixExpression) {
                if(JavaTokenType.EXCL.equals(psiPrefixExpression.getOperationTokenType())) {
                    final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
                    if(currentReturnOutcome == ReturnOutcome.True) {
                        ret.withReturnOutcome(ReturnOutcome.False);
                    } else if(currentReturnOutcome == ReturnOutcome.False) {
                        ret.withReturnOutcome(ReturnOutcome.True);
                    } else {
                        // We don't know the truthiness of the expression. Set the current value to unknown and
                        // continue on.
                        ret.withReturnOutcome(ReturnOutcome.Unknown);
                        currentElement = currentElement.getParent();
                        continue;
                    }
                } else {
                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                    currentElement = currentElement.getParent();
                    continue;
                }
                currentElement = currentElement.getParent();
                continue;
            }
            if(currentElement instanceof final PsiPolyadicExpression psiPolyadicExpression) {
                if(SQPsiUtil.isPrimitiveBoolean(psiPolyadicExpression.getType())) {
                    final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
                    if(currentReturnOutcome == ReturnOutcome.True || currentReturnOutcome == ReturnOutcome.False) {
                        // Add the other clauses in the expression to elementsContainingDIsThatAreHit.
                        // Skip the operand that is a parent of startingElement.
                        ret.addElementsContainingDisHit(getArgsThatDoNotContainElement(psiPolyadicExpression.getOperands(), startingElement));
                        currentElement = currentElement.getParent();
                        continue;
                    }
                }
                ret.addElementsContainingDisHit(getArgsThatDoNotContainElement(psiPolyadicExpression.getOperands(), startingElement));
                ret.withReturnOutcome(ReturnOutcome.Unknown);
                currentElement = currentElement.getParent();
                continue;
            }
            if(currentElement instanceof PsiParenthesizedExpression) {
                currentElement = currentElement.getParent();
                continue;
            }
            if(currentElement instanceof PsiLambdaExpression) {
                currentElement = currentElement.getParent();
                continue;
            }
            if(currentElement instanceof final PsiMethodCallExpression currentMethodCall) {
                final NextStep nextStep = processMethodCall(currentMethodCall, currentMethodState, startingElement, ret, preserveUnknown);
                if(nextStep == NextStep.Continue) {
                    currentMethodState = null;
                    currentElement = currentElement.getParent();
                    continue;
                }
                return ret;
            }
            // Bail out. We encountered a PsiElement we weren't expecting.
            return ret.withUnknownReturnValue();
        }
        // This shouldn't happen. If we hit this, we've passed the callstackMethod. That can't happen.
        return ret.withUnknownReturnValue();
    }

    @Nullable
    private ReturnOutcome getHardcodedReturnOutcome(final PsiExpression expression) {
        if(expression == null) {
            return null;
        }
        if(isNullLiteral(expression)) {
            return ReturnOutcome.Null;
        } else if(isTrueLiteral(expression)) {
            return ReturnOutcome.True;
        } else if(isFalseLiteral(expression)) {
            return ReturnOutcome.False;
        } else if(isFailedFutureInitExpression(expression)) {
            return ReturnOutcome.Failure;
        } else if(isAbsentInitExpression(expression)) {
            return ReturnOutcome.Absent;
        } else if(isEmptyInitExpression(expression)) {
            return ReturnOutcome.Empty;
        } else {
            return null;
        }
    }

    @NotNull
    private NextStep processMethodCall(
            @NotNull final PsiMethodCallExpression currentMethodCall, final CurrentMethodState methodState,
            final PsiElement startingElement, final RValueInfo ret, final boolean preserveUnknown) {
        final PsiMethod psiMethod = currentMethodCall.resolveMethod();
        if(psiMethod == null) {
            return processUnknownMethodCall(currentMethodCall, methodState, startingElement, ret);
        }
        final PsiClass containingClass = psiMethod.getContainingClass();
        if(containingClass == null) {
            return processUnknownMethodCall(currentMethodCall, methodState, startingElement, ret);
        }
        if(methodState == null) {
            // This shouldn't happen. Treat it as an unknown method call.
            return processUnknownMethodCall(currentMethodCall, methodState, startingElement, ret);
        }
        NextStep nextStep;
        for(final MethodCallProcessor methodCallProcessor : methodCallProcessors) {
            if(methodState == CurrentMethodState.IsInMethodCall) {
                nextStep = methodCallProcessor.tryProcessMethodCallWithArgThatContainsElement(containingClass, psiMethod, currentMethodCall, startingElement, ret, preserveUnknown);
                if(nextStep != null) {
                    return nextStep;
                }
            } else if(methodState == CurrentMethodState.IsQualifierForChainedCall) {
                nextStep = methodCallProcessor.tryProcessMethodCallWithQualifierThatContainsElement(containingClass, psiMethod, currentMethodCall, ret, preserveUnknown);
                if(nextStep != null) {
                    return nextStep;
                }
            }
        }

        // We didn't recognize this method.
        return processUnknownMethodCall(currentMethodCall, methodState, startingElement, ret);
    }

    @NotNull
    private NextStep processUnknownMethodCall(
            final PsiMethodCallExpression currentMethodCall, @Nullable final CurrentMethodState methodState,
            final PsiElement startingElement, final RValueInfo ret) {
        if(methodState == CurrentMethodState.IsInMethodCall) {
            // Add args that do not contain the starting element. Add the call itself to methodCalls.
            // Set return outcome to unknown and continue.
            final PsiType alwaysThrownException = methodPathsInfoProvider.tryGetHelperMethodException(currentMethodCall);
            final List<PsiExpression> otherArgs = getArgsThatDoNotContainElement(currentMethodCall.getArgumentList().getExpressions(), startingElement);
            ret.addElementsContainingDisHit(otherArgs);
            ret.addMethodCallElement(currentMethodCall);
            if(alwaysThrownException != null) {
                ret.withExceptionThrown(currentMethodCall, alwaysThrownException);
                return NextStep.Return;
            }
            ret.withReturnOutcome(ReturnOutcome.Unknown);
            return NextStep.Continue;
        }

        // Add all args. Set return to unknown and continue.
        final PsiType alwaysThrownException = methodPathsInfoProvider.tryGetHelperMethodException(currentMethodCall);
        ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
        ret.addMethodCallElement(currentMethodCall);
        if(alwaysThrownException != null) {
            ret.withExceptionThrown(currentMethodCall, alwaysThrownException);
            return NextStep.Return;
        }
        ret.withReturnOutcome(ReturnOutcome.Unknown);
        return NextStep.Continue;
    }

    private void addElementsWithDisHitBefore(final RValueInfo ret, final PsiElement startingElement, final PsiElement stoppingElement) {
        PsiElement currentElement = startingElement;
        CurrentMethodState currentMethodState = null;
        while(currentElement != null) {
            if(currentElement == stoppingElement) {
                // We've reached the top. Return.
                return;
            }
            if(currentElement instanceof PsiExpressionList) {
                // We're inside of a method call.
                currentMethodState = CurrentMethodState.IsInMethodCall;
                currentElement = currentElement.getParent();
                continue;
            }
            if(currentElement instanceof PsiReferenceExpression) {
                // We're the qualifier of a method call.
                currentMethodState = CurrentMethodState.IsQualifierForChainedCall;
                currentElement = currentElement.getParent();
                continue;
            }
            if(currentElement instanceof final PsiMethodCallExpression currentMethodCall) {
                if(currentMethodState == CurrentMethodState.IsInMethodCall) {
                    currentMethodState = null;
                    // Add the qualifier.
                    ret.addElementContainingDisHit(currentMethodCall.getMethodExpression());
                    addArgsEvaluatedFirst(ret, currentMethodCall, startingElement);
                    currentElement = currentElement.getParent();
                    continue;
                } else {
                    currentMethodState = null;
                    currentElement = currentElement.getParent();
                    continue;
                }
            }
            if(currentElement instanceof final PsiPolyadicExpression psiPolyadicExpression) {
                addPolyadicComponentsContainingDisHit(ret, psiPolyadicExpression, startingElement);
                currentElement = currentElement.getParent();
                continue;
            }
            if(currentElement instanceof final PsiNewExpression psiNewExpression) {
                final PsiExpressionList argumentList = psiNewExpression.getArgumentList();
                if(argumentList == null) {
                    // This is an anon inner class, and we came up through the body.
                    // There are no arguments. Just continue.
                    currentElement = currentElement.getParent();
                    continue;
                }
                final PsiExpression[] argumentExpressions = argumentList.getExpressions();
                final PsiElement argThatContainsElement = getElementThatContainsChild(argumentExpressions, startingElement);
                if(argThatContainsElement == null) {
                    // This is an anon inner class, and we came up through the body.
                    // Add the params.
                    ret.addElementsContainingDisHit(Arrays.asList(argumentExpressions));
                    currentElement = currentElement.getParent();
                    continue;
                }
                // We came up through a parameter.
                // Add args that are evaluated first.
                addArgsEvaluatedFirst(ret, psiNewExpression, startingElement);
                currentElement = currentElement.getParent();
                continue;
            }

            // The default case is to just continue walking up the tree.
            currentElement = currentElement.getParent();
        }
    }

    private void addPolyadicComponentsContainingDisHit(
            final RValueInfo ret, final PsiPolyadicExpression psiPolyadicExpression, final PsiElement startingElement) {
        final PsiExpression[] expressions = psiPolyadicExpression.getOperands();
        final int index = getIndexOfElementThatContainsElement(expressions, startingElement);
        if(index == -1) {
            return;
        }
        for(int i = 0; i < index; i++) {
            ret.addElementContainingDisHit(expressions[i]);
        }
    }

    private void addArgsEvaluatedFirst(final RValueInfo ret, final PsiCallExpression currentMethodCall, final PsiElement startingElement) {
        final PsiMethod calledMethod = currentMethodCall.resolveMethod();
        if(calledMethod == null) {
            return;
        }
        final PsiExpressionList argumentList = currentMethodCall.getArgumentList();
        if(argumentList == null) {
            return;
        }
        final PsiExpression[] argumentExpressions = argumentList.getExpressions();
        final int index = getIndexOfElementThatContainsElement(argumentExpressions, startingElement);
        if(index == -1) {
            return;
        }
        final PsiParameter[] parameters = calledMethod.getParameterList().getParameters();
        if(index >= parameters.length) {
            // The method call expression contains too many parameters (is a syntax error). Bail out.
            return;
        }
        if(DependencyInteractionCollectorUtils.isParamCalledFirst(calledMethod, parameters[index].getType())) {
            for(int i = 0; i < index; i++) {
                ret.addElementContainingDisHit(argumentExpressions[i]);
            }
            return;
        }
        // The param is async. Add all sync params and the method call expression.
        for(int i = 0; i < argumentExpressions.length && i < parameters.length; i++) {
            if(DependencyInteractionCollectorUtils.isParamCalledFirst(calledMethod, parameters[i].getType())) {
                ret.addElementContainingDisHit(argumentExpressions[i]);
            }
        }
        ret.addElementContainingDisHit(currentMethodCall);
    }

    private boolean isReferenceToLocalVariable(final PsiExpression expression, final PsiLocalVariable theLocalVariable) {
        if(theLocalVariable == null || expression == null) {
            return false;
        }
        if(expression instanceof PsiReferenceExpression) {
            final PsiElement refTarget = ((PsiReferenceExpression) expression).resolve();
            if(refTarget == theLocalVariable) {
                return true;
            }
        }
        return false;
    }

    private PsiLocalVariable getStartingElementLocalVariable(final PsiElement startingElement) {
        if(startingElement instanceof PsiReferenceExpression) {
            final PsiElement target = ((PsiReferenceExpression) startingElement).resolve();
            if(target instanceof PsiLocalVariable) {
                return (PsiLocalVariable) target;
            }
        }
        return null;
    }

    private PsiType getAssertErrorType() {
        final PsiElementFactory elementFactory = javaPsiFacade.getElementFactory();
        final PsiClass exceptionClass = javaPsiFacade.findClass("java.lang.AssertionError", sourceClass.getResolveScope());
        if(exceptionClass == null) {
            return null;
        }
        return elementFactory.createType(exceptionClass);
    }
}
