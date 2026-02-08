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
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.util.InheritanceUtil;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.dependencyinteraction.followup.RValueInfo;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JavaSpliteratorProcessor implements MethodCallProcessor {
    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithQualifierThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!isJavaSpliterator(containingClass)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
        if(StringUtils.equals(methodName, "forEachRemaining")) {
            if(currentReturnOutcome != ReturnOutcome.Empty) {
                ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
            }
            ret.withReturnOutcome(ReturnOutcome.Void);
            return NextStep.Continue;
        }
        if(StringUtils.equals(methodName, "tryAdvance")) {
            if(currentReturnOutcome != ReturnOutcome.Empty) {
                ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
                ret.withReturnOutcome(ReturnOutcome.True);
            } else {
                ret.withReturnOutcome(ReturnOutcome.False);
            }
            return NextStep.Continue;
        }

        if(StringUtils.equals(methodName, "trySplit")) {
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "getExactSizeIfKnown", "estimateSize")) {
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.Zero);
            }
            return NextStep.Continue;
        }
        return null;
    }

    private boolean isJavaSpliterator(final PsiClass containingClass) {
        return InheritanceUtil.isInheritor(containingClass, JavaNames.JavaUtilSpliterator);
    }
}
