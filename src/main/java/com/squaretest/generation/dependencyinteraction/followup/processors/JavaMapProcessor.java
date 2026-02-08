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

import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.getArgsThatDoNotContainElement;
import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.getIndexOfElementThatContainsElement;

public class JavaMapProcessor implements MethodCallProcessor {
    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithArgThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final PsiElement originalStartingElement,
            @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!InheritanceUtil.isInheritor(containingClass, JavaNames.JavaUtilMap)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        final PsiExpression[] args = currentMethodCall.getArgumentList().getExpressions();
        if(StringUtils.equalsAny(methodName, "compute", "computeIfAbsent", "computeIfPresent")) {
            final int index = getIndexOfElementThatContainsElement(args, originalStartingElement);
            if(index == 1) {
                final List<PsiExpression> otherArgs = getArgsThatDoNotContainElement(args, originalStartingElement);
                ret.addElementsContainingDisHit(otherArgs);
                return NextStep.Continue;
            }
        }
        return null;
    }

    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithQualifierThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!InheritanceUtil.isInheritor(containingClass, JavaNames.JavaUtilMap)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
        if(methodName.equals("size")) {
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.Zero);
            }
            return NextStep.Continue;
        }
        if(methodName.equals("isEmpty")) {
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withBooleanConversionMethod(BooleanConversionMethod.CollectionMethod);
                ret.withReturnOutcome(ReturnOutcome.True);
            } else if(currentReturnOutcome == ReturnOutcome.Other) {
                ret.withBooleanConversionMethod(BooleanConversionMethod.CollectionMethod);
                ret.withReturnOutcome(ReturnOutcome.False);
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "entrySet", "keySet", "values", "reversed", "sequencedKeySet", "sequencedValues", "sequencedEntrySet")) {
            // Preserve the current value.
            return NextStep.Continue;
        }
        return null;
    }
}
