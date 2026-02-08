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
import com.intellij.psi.PsiExpressionList;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.util.InheritanceUtil;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.dependencyinteraction.followup.RValueInfo;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JavaIterableProcessor implements MethodCallProcessor {
    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithQualifierThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!InheritanceUtil.isInheritor(containingClass, JavaNames.JavaLangIterable)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        final PsiExpressionList argList = currentMethodCall.getArgumentList();
        final PsiExpression[] args = argList.getExpressions();
        // Note: stream is not part of java.lang.Iterable. It is needed to handle cases like
        // software.amazon.awssdk.core.pagination.sync.SdkIterable.stream(), where companies provide a stream method
        // to make it easier for developers to use their API.
        // Also handle parallelStream() and toArray(..) in case those are added in the future.
        if(StringUtils.equalsAny(methodName, "iterator", "spliterator", "stream", "parallelStream", "toArray")) {
            if(args.length != 0) {
                ret.addElementContainingDisHit(argList);
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "listIterator")) {
            if(args.length == 0) {
                return NextStep.Continue;
            }
            if(args.length == 1) {
                if(ret.getReturnOutcome() == ReturnOutcome.Empty) {
                    ret.addElementContainingDisHit(argList);
                    return NextStep.Continue;
                }
                return null;
            }
            return null;
        }
        if(StringUtils.equalsAny(methodName, "forEach")) {
            if(ret.getReturnOutcome() != ReturnOutcome.Empty) {
                ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
            }
            ret.withReturnOutcome(ReturnOutcome.Void);
            return NextStep.Continue;
        }
        return null;
    }
}
