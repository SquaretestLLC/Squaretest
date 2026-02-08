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
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.util.InheritanceUtil;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.dependencyinteraction.followup.RValueInfo;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JavaIteratorProcessor implements MethodCallProcessor {
    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithQualifierThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!InheritanceUtil.isInheritor(containingClass, JavaNames.JavaUtilIterator)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
        final PsiExpression[] args = currentMethodCall.getArgumentList().getExpressions();
        if(methodName.equals("hasNext") && args.length == 0) {
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.False);
                return NextStep.Continue;
            }
            if(currentReturnOutcome == ReturnOutcome.Other) {
                ret.withReturnOutcome(ReturnOutcome.True);
                return NextStep.Continue;
            }
            return null;
        }
        if(methodName.equals("hasPrevious") && args.length == 0) {
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.False);
                return NextStep.Continue;
            }
            return null;
        }
        if(methodName.equals("forEachRemaining")) {
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                return NextStep.Continue;
            }
            if(currentReturnOutcome == ReturnOutcome.Other) {
                ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
                return NextStep.Continue;
            }
            return null;
        }
        return null;
    }
}
