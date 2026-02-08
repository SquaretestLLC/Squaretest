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
package com.squaretest.generation.dependencyinteraction.followup.processors;

import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiExpressionList;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiType;
import com.intellij.psi.util.InheritanceUtil;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.dependencyinteraction.followup.RValueInfo;
import com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.getArgsThatDoNotContainElement;
import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.getIndexOfElementThatContainsElement;

public class JavaCompletableFutureProcessor implements MethodCallProcessor {

    @NotNull
    private final JavaPsiFacade javaPsiFacade;
    @NotNull
    private final PsiClass sourceClass;

    public JavaCompletableFutureProcessor(@NotNull final JavaPsiFacade javaPsiFacade, @NotNull final PsiClass sourceClass) {
        this.javaPsiFacade = javaPsiFacade;
        this.sourceClass = sourceClass;
    }

    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithArgThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final PsiElement originalStartingElement,
            @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!isFutureOrCompletionStage(containingClass)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        final PsiExpression[] args = currentMethodCall.getArgumentList().getExpressions();
        final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
        if(StringUtils.equalsAny(methodName, "acceptEither", "acceptEitherAsync", "applyToEither", "applyToEitherAsync")) {
            final List<PsiExpression> otherArgs = getArgsThatDoNotContainElement(args, originalStartingElement);
            ret.addElementsContainingDisHit(otherArgs);
            ret.addMethodCallElement(currentMethodCall);
            ret.withReturnOutcome(ReturnOutcome.Unknown);
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "cancel", "complete", "completeExceptionally")) {
            ret.withReturnOutcome(ReturnOutcome.Unknown);
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "completeAsync")) {
            final int index = getIndexOfElementThatContainsElement(args, originalStartingElement);
            final List<PsiExpression> otherArgs = getArgsThatDoNotContainElement(args, originalStartingElement);
            ret.addMethodCallElement(currentMethodCall);
            ret.addElementsContainingDisHit(otherArgs);
            if(index != 0 && index != -1) {
                ret.withReturnOutcome(ReturnOutcome.Unknown);
            } else {
                if(currentReturnOutcome == ReturnOutcome.Null) {
                    ret.withReturnOutcome(ReturnOutcome.FutureWithNull);
                }
            }
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "completedFuture", "completedStage")) {
            if(currentReturnOutcome == ReturnOutcome.Null) {
                ret.withReturnOutcome(ReturnOutcome.FutureWithNull);
                return NextStep.Continue;
            }
        }

        if(StringUtils.equalsAny(methodName, "failedFuture", "failedStage")) {
            ret.withReturnOutcome(ReturnOutcome.Failure);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "allOf")) {
            final List<PsiExpression> otherArgs = getArgsThatDoNotContainElement(args, originalStartingElement);
            ret.addElementsContainingDisHit(otherArgs);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "thenCombine", "thenCombineAsync")) {
            final int index = getIndexOfElementThatContainsElement(args, originalStartingElement);
            if(index == 0 && currentReturnOutcome == ReturnOutcome.Failure) {
                ret.addElementContainingDisHit(SQPsiUtil.getArgAtIndex(currentMethodCall, 2));
                return NextStep.Continue;
            }
            if(index == 1) {
                // We are the bifunction.
                if(currentReturnOutcome == ReturnOutcome.Null) {
                    ret.withReturnOutcome(ReturnOutcome.FutureWithNull);
                }
            } else {
                ret.withReturnOutcome(ReturnOutcome.Unknown);
            }
            final List<PsiExpression> otherArgs = getArgsThatDoNotContainElement(args, originalStartingElement);
            ret.addElementsContainingDisHit(otherArgs);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "thenAcceptBoth", "thenAcceptBothAsync", "runAfterBoth", "runAfterBothAsync")) {
            final int index = getIndexOfElementThatContainsElement(args, originalStartingElement);
            if(index == 0 && currentReturnOutcome == ReturnOutcome.Failure) {
                return NextStep.Continue;
            }
            ret.withReturnOutcome(ReturnOutcome.Unknown);
            final List<PsiExpression> otherArgs = getArgsThatDoNotContainElement(args, originalStartingElement);
            ret.addElementsContainingDisHit(otherArgs);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "thenCompose", "thenComposeAsync")) {
            final List<PsiExpression> otherArgs = getArgsThatDoNotContainElement(args, originalStartingElement);
            ret.addElementsContainingDisHit(otherArgs);
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                return NextStep.Continue;
            }
            final int index = getIndexOfElementThatContainsElement(args, originalStartingElement);
            if(index == 0) {
                // We are the function.
                if(currentReturnOutcome == ReturnOutcome.Null) {
                    ret.withReturnOutcome(ReturnOutcome.FutureWithNull);
                }
            } else {
                ret.withReturnOutcome(ReturnOutcome.Unknown);
            }
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "exceptionally", "exceptionallyAsync", "exceptionallyCompose", "exceptionallyComposeAsync")) {
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                final List<PsiExpression> otherArgs = getArgsThatDoNotContainElement(args, originalStartingElement);
                ret.addElementsContainingDisHit(otherArgs);
                ret.addMethodCallElement(currentMethodCall);
                return NextStep.Continue;
            } else {
                final int index = getIndexOfElementThatContainsElement(args, originalStartingElement);
                if(index == 0) {
                    // We are the function.
                    if(currentReturnOutcome == ReturnOutcome.Null) {
                        ret.withReturnOutcome(ReturnOutcome.FutureWithNull);
                    }
                } else {
                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                }
                final List<PsiExpression> otherArgs = getArgsThatDoNotContainElement(args, originalStartingElement);
                ret.addElementsContainingDisHit(otherArgs);
                ret.addMethodCallElement(currentMethodCall);
                return NextStep.Continue;
            }
        }

        final List<PsiExpression> otherArgs = getArgsThatDoNotContainElement(args, originalStartingElement);
        ret.addElementsContainingDisHit(otherArgs);
        ret.addMethodCallElement(currentMethodCall);
        ret.withReturnOutcome(ReturnOutcome.Unknown);
        return NextStep.Continue;
    }

    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithQualifierThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!isFutureOrCompletionStage(containingClass)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        final PsiExpressionList argList = currentMethodCall.getArgumentList();
        final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
        if(StringUtils.equalsAny(methodName, "get")) {
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                ret.withExceptionThrown(currentMethodCall, getExecutionException());
                ret.addElementContainingDisHit(argList);
                ret.addMethodCallElement(currentMethodCall);
                return NextStep.Return;
            }
            if(currentReturnOutcome == ReturnOutcome.FutureWithNull) {
                ret.withReturnOutcome(ReturnOutcome.Null);
            }
            ret.addElementContainingDisHit(argList);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "getNow", "join")) {
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                ret.withExceptionThrown(currentMethodCall, getCompletionException());
                ret.addElementContainingDisHit(argList);
                ret.addMethodCallElement(currentMethodCall);
                return NextStep.Return;
            }
            if(currentReturnOutcome == ReturnOutcome.FutureWithNull) {
                ret.withReturnOutcome(ReturnOutcome.Null);
            }
            ret.addElementContainingDisHit(argList);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "isDone")) {
            ret.withReturnOutcome(ReturnOutcome.True);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "isCompletedExceptionally")) {
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                ret.withReturnOutcome(ReturnOutcome.True);
            } else {
                ret.withReturnOutcome(ReturnOutcome.False);
            }
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "runAfterBoth")) {
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                ret.addElementContainingDisHit(SQPsiUtil.getArgAtIndex(currentMethodCall, 0));
                return NextStep.Continue;
            }
            ret.addElementContainingDisHit(argList);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "runAfterBothAsync")) {
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                ret.addElementContainingDisHit(SQPsiUtil.getArgAtIndex(currentMethodCall, 0));
                ret.addElementContainingDisHit(SQPsiUtil.getArgAtIndex(currentMethodCall, 2));
                return NextStep.Continue;
            }
            ret.addElementContainingDisHit(argList);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "thenAccept", "thenAcceptAsync")) {
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                return NextStep.Continue;
            }
            ret.addElementContainingDisHit(argList);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "thenAcceptBoth")) {
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                ret.addElementContainingDisHit(SQPsiUtil.getArgAtIndex(currentMethodCall, 0));
                return NextStep.Continue;
            }
            ret.addElementContainingDisHit(argList);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "thenAcceptBothAsync")) {
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                ret.addElementContainingDisHit(SQPsiUtil.getArgAtIndex(currentMethodCall, 0));
                ret.addElementContainingDisHit(SQPsiUtil.getArgAtIndex(currentMethodCall, 2));
                return NextStep.Continue;
            }
            ret.addElementContainingDisHit(argList);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "thenApply")) {
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                return NextStep.Continue;
            }
            ret.withReturnOutcome(ReturnOutcome.Unknown);
            ret.addElementContainingDisHit(argList);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "thenApplyAsync")) {
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                ret.addElementContainingDisHit(SQPsiUtil.getArgAtIndex(currentMethodCall, 1));
                return NextStep.Continue;
            }
            ret.withReturnOutcome(ReturnOutcome.Unknown);
            ret.addElementContainingDisHit(argList);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "thenCombine")) {
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                ret.addElementContainingDisHit(SQPsiUtil.getArgAtIndex(currentMethodCall, 0));
                return NextStep.Continue;
            }
            ret.withReturnOutcome(ReturnOutcome.Unknown);
            ret.addElementContainingDisHit(argList);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "thenCombineAsync")) {
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                ret.addElementContainingDisHit(SQPsiUtil.getArgAtIndex(currentMethodCall, 0));
                ret.addElementContainingDisHit(SQPsiUtil.getArgAtIndex(currentMethodCall, 2));
                return NextStep.Continue;
            }
            ret.withReturnOutcome(ReturnOutcome.Unknown);
            ret.addElementContainingDisHit(argList);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "thenCompose")) {
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                return NextStep.Continue;
            }
            ret.withReturnOutcome(ReturnOutcome.Unknown);
            ret.addElementContainingDisHit(argList);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "thenComposeAsync")) {
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                ret.addElementContainingDisHit(SQPsiUtil.getArgAtIndex(currentMethodCall, 1));
                return NextStep.Continue;
            }
            ret.withReturnOutcome(ReturnOutcome.Unknown);
            ret.addElementContainingDisHit(argList);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "thenRun")) {
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                return NextStep.Continue;
            }
            ret.addElementContainingDisHit(argList);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "thenRunAsync")) {
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                ret.addElementContainingDisHit(SQPsiUtil.getArgAtIndex(currentMethodCall, 1));
                return NextStep.Continue;
            }
            ret.addElementContainingDisHit(argList);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "minimalCompletionStage", "toCompletableFuture", "copy")) {
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "orTimeout", "completeOnTimeout")) {
            ret.addElementContainingDisHit(argList);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "whenComplete", "whenCompleteAsync")) {
            ret.addElementContainingDisHit(argList);
            ret.addMethodCallElement(currentMethodCall);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "exceptionally", "exceptionallyAsync", "exceptionallyCompose", "exceptionallyComposeAsync")) {
            if(currentReturnOutcome == ReturnOutcome.Failure) {
                ret.addElementContainingDisHit(argList);
                ret.addMethodCallElement(currentMethodCall);
                ret.withReturnOutcome(ReturnOutcome.Unknown);
                return NextStep.Continue;
            }
            return NextStep.Continue;
        }

        // Use the default behavior as a fallback.
        ret.addMethodCallElement(currentMethodCall);
        ret.addElementContainingDisHit(argList);
        ret.withReturnOutcome(ReturnOutcome.Unknown);
        return NextStep.Continue;
    }

    private PsiType getCompletionException() {
        final PsiElementFactory elementFactory = javaPsiFacade.getElementFactory();
        final PsiClass exceptionClass = javaPsiFacade.findClass("java.util.concurrent.CompletionException", sourceClass.getResolveScope());
        if(exceptionClass == null) {
            return null;
        }
        return elementFactory.createType(exceptionClass);
    }

    private PsiType getExecutionException() {
        final PsiElementFactory elementFactory = javaPsiFacade.getElementFactory();
        final PsiClass exceptionClass = javaPsiFacade.findClass("java.util.concurrent.ExecutionException", sourceClass.getResolveScope());
        if(exceptionClass == null) {
            return null;
        }
        return elementFactory.createType(exceptionClass);
    }

    private boolean isFutureOrCompletionStage(final PsiClass containingClass) {
        if(containingClass == null) {
            return false;
        }
        return InheritanceUtil.isInheritor(containingClass, JavaNames.Future) || InheritanceUtil.isInheritor(containingClass, JavaNames.CompletionStage);
    }
}
