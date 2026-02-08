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
package com.squaretest.generation.dependencyinteraction.followup.processors.awsv2.s3;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.squaretest.generation.dependencyinteraction.followup.RValueInfo;
import com.squaretest.generation.dependencyinteraction.followup.processors.MethodCallProcessor;
import com.squaretest.generation.dependencyinteraction.followup.processors.NextStep;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class S3ClientResponseProcessor implements MethodCallProcessor {
    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithQualifierThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!isS3Response(containingClass)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        if(StringUtils.equalsAny(methodName, "contents",
                "analyticsConfigurationList", "metricsConfigurationList", "versions")) {
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "nextContinuationToken", "nextKeyMarker", "nextVersionIdMarker")) {
            if(ret.getReturnOutcome() == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.Null);
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "keyCount")) {
            if(ret.getReturnOutcome() == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.Zero);
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "isTruncated")) {
            if(ret.getReturnOutcome() == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.False);
            } else {
                ret.withReturnOutcome(ReturnOutcome.True);
            }
            return NextStep.Continue;
        }
        return null;
    }

    private static boolean isS3Response(final PsiClass containingClass) {
        if(!StringUtils.startsWith(containingClass.getQualifiedName(), "software.amazon.awssdk.services.s3.model")) {
            return false;
        }
        return true;
    }
}
