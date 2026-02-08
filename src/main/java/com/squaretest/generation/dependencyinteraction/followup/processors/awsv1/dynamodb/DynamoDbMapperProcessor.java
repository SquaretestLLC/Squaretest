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
package com.squaretest.generation.dependencyinteraction.followup.processors.awsv1.dynamodb;

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

public class DynamoDbMapperProcessor implements MethodCallProcessor {
    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithQualifierThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!isDynamoDbResult(containingClass)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
        if(StringUtils.equalsAny(methodName, "getResults")) {
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "getLastEvaluatedKey")) {
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.Null);
            } else {
                ret.withReturnOutcome(ReturnOutcome.Other);
            }
            return NextStep.Continue;
        }
        return null;
    }

    private boolean isDynamoDbResult(@NotNull final PsiClass containingClass) {
        if(!StringUtils.startsWith(containingClass.getQualifiedName(), "com.amazonaws.services.dynamodbv2.datamodeling")) {
            return false;
        }
        return true;
    }
}
