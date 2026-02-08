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
package com.squaretest.generation.dataflow.usedmethods;

import com.intellij.psi.PsiCall;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodReferenceExpression;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CalledMethodInfo {
    @NotNull
    private final PsiMethod calledMethod;
    @NotNull
    private final List<PsiCall> methodCalls;
    @NotNull
    private final List<PsiMethodReferenceExpression> methodReferenceExpressions;

    public CalledMethodInfo(
            @NotNull final PsiMethod calledMethod,
            @NotNull final List<PsiCall> methodCalls,
            @NotNull final List<PsiMethodReferenceExpression> methodReferenceExpressions) {
        this.calledMethod = calledMethod;
        this.methodCalls = methodCalls;
        this.methodReferenceExpressions = methodReferenceExpressions;
    }

    @NotNull
    public PsiMethod getCalledMethod() {
        return calledMethod;
    }

    @NotNull
    public List<PsiCall> getMethodCallExpressions() {
        return methodCalls;
    }

    @NotNull
    public List<PsiMethodReferenceExpression> getMethodReferenceExpressions() {
        return methodReferenceExpressions;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final CalledMethodInfo that = (CalledMethodInfo) o;
        return calledMethod == that.calledMethod;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(calledMethod);
    }

    public void addCallExpression(final PsiCall expression) {
        methodCalls.add(expression);
    }

    public void addReferenceExpression(final PsiMethodReferenceExpression expression) {
        methodReferenceExpressions.add(expression);
    }
}
