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
import com.squaretest.generation.dependencyinteraction.followup.BooleanConversionMethod;
import com.squaretest.generation.dependencyinteraction.followup.RValueInfo;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.getElementsAfterElementThatContainsStartingElement;

public class SpringStreamableProcessor implements MethodCallProcessor {
    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithArgThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final PsiElement originalStartingElement,
            @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!isSpringStreamable(containingClass)) {
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
                return NextStep.Continue;
            } else {
                if(InheritanceUtil.isInheritor(args[0].getType(), JavaNames.JavaLangIterable)
                        || InheritanceUtil.isInheritor(args[0].getType(), JavaNames.JavaSupplier)) {
                    // If the arg is iterable or supplier, preserve the status.
                    return NextStep.Continue;
                }
                // We're not being passed to the overload that takes in an Iterable or Supplier.
                // Use ReturnOutcome=Other.
                ret.withReturnOutcome(ReturnOutcome.Other);
                return NextStep.Continue;
            }
        }
        if(StringUtils.equalsAny(methodName, "filter")) {
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
        if(StringUtils.equalsAny(methodName, "map")) {
            ret.withReturnOutcome(ReturnOutcome.Other);
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "flatMap")) {
            if(ret.getReturnOutcome() != ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.Other);
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "forEach")) {
            ret.withReturnOutcome(ReturnOutcome.Void);
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
        if(!isSpringStreamable(containingClass)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
        if(StringUtils.equalsAny(methodName, "filter", "map", "flatMap")) {
            if(currentReturnOutcome != ReturnOutcome.Empty) {
                ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "toList", "stream", "toSet", "get")) {
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "forEach")) {
            if(currentReturnOutcome != ReturnOutcome.Empty) {
                ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
            }
            ret.withReturnOutcome(ReturnOutcome.Void);
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "isEmpty")) {
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withBooleanConversionMethod(BooleanConversionMethod.CollectionMethod);
                ret.withReturnOutcome(ReturnOutcome.True);
            } else if(currentReturnOutcome == ReturnOutcome.Other) {
                ret.withBooleanConversionMethod(BooleanConversionMethod.CollectionMethod);
                ret.withReturnOutcome(ReturnOutcome.False);
            }
            return NextStep.Continue;
        }

        ret.withReturnOutcome(ReturnOutcome.Unknown);
        return NextStep.Continue;
    }

    private boolean isSpringStreamable(final PsiClass containingClass) {
        return InheritanceUtil.isInheritor(containingClass, JavaNames.SpringStreamable);
    }
}
