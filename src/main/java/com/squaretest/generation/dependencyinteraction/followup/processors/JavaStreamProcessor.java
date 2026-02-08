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

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.util.InheritanceUtil;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.dependencyinteraction.followup.RValueInfo;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.getElementsAfterElementThatContainsStartingElement;
import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.isJavaOptional;

public class JavaStreamProcessor implements MethodCallProcessor {
    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithArgThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final PsiElement originalStartingElement,
            @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!isJavaStream(containingClass)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        // We're the params of a method call.
        if(StringUtils.equals(methodName, "of")) {
            final PsiExpression[] args = currentMethodCall.getArgumentList().getExpressions();
            if(args.length != 1) {
                final List<PsiExpression> argsAfter = getElementsAfterElementThatContainsStartingElement(
                        args, originalStartingElement);
                ret.addElementsContainingDisHit(argsAfter);
                ret.withReturnOutcome(ReturnOutcome.Other);
            }
            if(ret.getReturnOutcome() == ReturnOutcome.Null) {
                // This is likely a bug that will cause a NullPointerException.
                // Assume the developer meant to call ofNullable(..).
                ret.withReturnOutcome(ReturnOutcome.Empty);
                return NextStep.Continue;
            }
            return NextStep.Continue;
        }
        if(methodName.equals("ofNullable")) {
            if(ret.getReturnOutcome() == ReturnOutcome.Null) {
                ret.withReturnOutcome(ReturnOutcome.Empty);
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "filter", "takeWhile")) {
            final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
            if(currentReturnOutcome == ReturnOutcome.False) {
                ret.withReturnOutcome(ReturnOutcome.Empty);
                return NextStep.Continue;
            } else if(currentReturnOutcome == ReturnOutcome.True) {
                ret.withReturnOutcome(ReturnOutcome.Other);
                return NextStep.Continue;
            } else {
                ret.withReturnOutcome(ReturnOutcome.Unknown);
                return NextStep.Continue;
            }
        }
        if(StringUtils.equalsAny(methodName, "dropWhile")) {
            final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
            if(currentReturnOutcome == ReturnOutcome.False) {
                ret.withReturnOutcome(ReturnOutcome.Other);
                return NextStep.Continue;
            } else if(currentReturnOutcome == ReturnOutcome.True) {
                ret.withReturnOutcome(ReturnOutcome.Empty);
                return NextStep.Continue;
            } else {
                ret.withReturnOutcome(ReturnOutcome.Unknown);
                return NextStep.Continue;
            }
        }
        if(StringUtils.equalsAny(methodName, "map", "mapToObj", "mapToInt", "mapToLong", "mapToDouble", "sorted")) {
            ret.withReturnOutcome(ReturnOutcome.Other);
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "toArray", "flatMap", "flatMapToInt", "flatMapToLong", "flatMapToDouble")) {
            if(ret.getReturnOutcome() != ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.Other);
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "mapMulti", "mapMultiToDouble", "mapMultiToInt", "mapMultiToLong")) {
            ret.withReturnOutcome(ReturnOutcome.Unknown);
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "peek")) {
            ret.withReturnOutcome(ReturnOutcome.Other);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "limit")) {
            final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
            if(currentReturnOutcome == ReturnOutcome.Zero) {
                ret.withReturnOutcome(ReturnOutcome.Empty);
            } else {
                ret.withReturnOutcome(ReturnOutcome.Other);
            }
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "skip")) {
            ret.withReturnOutcome(ReturnOutcome.Unknown);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "forEach", "forEachOrdered")) {
            ret.withReturnOutcome(ReturnOutcome.Void);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "reduce", "collect")) {
            final List<PsiExpression> argsAfter = getElementsAfterElementThatContainsStartingElement(
                    currentMethodCall.getArgumentList().getExpressions(), originalStartingElement);
            ret.addElementsContainingDisHit(argsAfter);
            ret.withReturnOutcome(ReturnOutcome.Other);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "min", "max")) {
            ret.withReturnOutcome(ReturnOutcome.Other);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "anyMatch", "allMatch")) {
            final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
            // If the value is true or false, allow it to propagate. Otherwise, set it to Unknown.
            if(currentReturnOutcome != ReturnOutcome.True && currentReturnOutcome != ReturnOutcome.False) {
                ret.withReturnOutcome(ReturnOutcome.Unknown);
            }
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "noneMatch")) {
            final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
            // If the value is true or false, allow it to propagate. Otherwise, set it to Unknown.
            if(currentReturnOutcome == ReturnOutcome.True) {
                ret.withReturnOutcome(ReturnOutcome.False);
            } else if(currentReturnOutcome == ReturnOutcome.False) {
                ret.withReturnOutcome(ReturnOutcome.True);
            } else {
                ret.withReturnOutcome(ReturnOutcome.Unknown);
            }
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "iterate", "generate")) {
            final List<PsiExpression> argsAfter = getElementsAfterElementThatContainsStartingElement(
                    currentMethodCall.getArgumentList().getExpressions(), originalStartingElement);
            ret.addElementsContainingDisHit(argsAfter);
            ret.withReturnOutcome(ReturnOutcome.Other);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "concat")) {
            final List<PsiExpression> argsAfter = getElementsAfterElementThatContainsStartingElement(
                    currentMethodCall.getArgumentList().getExpressions(), originalStartingElement);
            ret.addElementsContainingDisHit(argsAfter);
            ret.withReturnOutcome(ReturnOutcome.Other);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "onClose")) {
            ret.withReturnOutcome(ReturnOutcome.Unknown);
            return NextStep.Continue;
        }
        ret.withReturnOutcome(ReturnOutcome.Unknown);
        return NextStep.Continue;
    }

    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithQualifierThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!isJavaStream(containingClass)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
        if(StringUtils.equalsAny(methodName, "filter", "map", "mapToObj", "mapToInt", "mapToLong", "mapToDouble",
                "mapMulti", "mapMultiToDouble", "mapMultiToInt", "mapMultiToLong",
                "flatMap", "flatMapToInt", "flatMapToLong", "flatMapToDouble")) {
            if(currentReturnOutcome != ReturnOutcome.Empty) {
                ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "estimateSize", "getExactSizeIfKnown")) {
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.Zero);
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "toArray", "limit", "skip")) {
            ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "toList")) {
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "boxed")) {
            if(currentMethodCall.getArgumentList().isEmpty()) {
                return NextStep.Continue;
            } else {
                // If the method has args, it is a new overload that was added after this code was written.
                return null;
            }
        }
        if(StringUtils.equalsAny(methodName, "min", "max", "average")) {
            if(currentMethodCall.getArgumentList().isEmpty()) {
                if(currentReturnOutcome == ReturnOutcome.Empty) {
                    ret.withReturnOutcome(ReturnOutcome.Absent);
                }
                return NextStep.Continue;
            } else {
                // If the method has args, it is a new overload that was added after this code was written.
                return null;
            }
        }
        if(StringUtils.equalsAny(methodName, "sum")) {
            if(currentMethodCall.getArgumentList().isEmpty()) {
                if(currentReturnOutcome == ReturnOutcome.Empty) {
                    ret.withReturnOutcome(ReturnOutcome.Zero);
                }
                return NextStep.Continue;
            } else {
                // If the method has args, it is a new overload that was added after this code was written.
                return null;
            }
        }
        if(StringUtils.equalsAny(methodName, "sorted", "peek", "takeWhile", "dropWhile")) {
            if(currentReturnOutcome != ReturnOutcome.Empty) {
                ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "forEach", "forEachOrdered")) {
            if(currentReturnOutcome != ReturnOutcome.Empty) {
                ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
            }
            ret.withReturnOutcome(ReturnOutcome.Void);
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "distinct", "iterator", "spliterator", "parallel", "unordered", "sequential")) {
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "reduce")) {
            if(currentReturnOutcome != ReturnOutcome.Empty) {
                ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
                return NextStep.Continue;
            }
            final PsiExpression[] args = currentMethodCall.getArgumentList().getExpressions();
            if(args.length > 1) {
                // The first argument is the identity. The others aren't hit, because there are no items in the stream.
                ret.addElementContainingDisHit(args[0]);
                ret.withReturnOutcome(ReturnOutcome.Unknown);
                return NextStep.Continue;
            }

            // There is one arg. It is not invoked.
            // If the return type is optional, return absent.
            if(isJavaOptional(currentMethodCall.getType())) {
                ret.withReturnOutcome(ReturnOutcome.Absent);
                return NextStep.Continue;
            }

            ret.withReturnOutcome(ReturnOutcome.Unknown);
            ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "collect")) {
            if(currentReturnOutcome != ReturnOutcome.Empty) {
                ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
                return NextStep.Continue;
            }
            final PsiExpression[] args = currentMethodCall.getArgumentList().getExpressions();
            if(args.length > 1) {
                // The first argument is the supplier. The others aren't hit, because there are no items in the stream.
                ret.addElementContainingDisHit(args[0]);
                // Maintain returnOutcome = ReturnOutcome.Empty and continue.
                return NextStep.Continue;
            }

            // There is only one arg. It is invoked.
            ret.addElementContainingDisHit(args[0]);
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "min", "max")) {
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.Absent);
                return NextStep.Continue;
            }
            ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "count")) {
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.Zero);
                return NextStep.Continue;
            }
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "anyMatch", "allMatch")) {
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.False);
                return NextStep.Continue;
            }
            ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "noneMatch")) {
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.True);
                return NextStep.Continue;
            }
            ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "findFirst", "findAny")) {
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.Absent);
                return NextStep.Continue;
            }
            ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
            return NextStep.Continue;
        }

        ret.withReturnOutcome(ReturnOutcome.Unknown);
        return NextStep.Continue;
    }

    private boolean isJavaStream(final PsiClass containingClass) {
        // Use java.util.stream.BaseStream instead of Stream to handle IntStream, DoubleStream, LongStream, etc.
        return InheritanceUtil.isInheritor(containingClass, JavaNames.JavaUtilBaseStream);
    }
}
