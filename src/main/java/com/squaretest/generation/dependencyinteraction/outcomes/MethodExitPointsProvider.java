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
package com.squaretest.generation.dependencyinteraction.outcomes;

import com.intellij.psi.JavaRecursiveElementWalkingVisitor;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiCompiledElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLambdaExpression;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiReturnStatement;
import com.intellij.psi.PsiThrowStatement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import static com.squaretest.generation.dependencyinteraction.outcomes.OutcomePopulator.getParentClosure;

public class MethodExitPointsProvider {
    @NotNull
    private final Map<PsiElement, List<PsiElement>> sourceMethodToReturnOrThrowStatements;

    public MethodExitPointsProvider() {
        this.sourceMethodToReturnOrThrowStatements = new IdentityHashMap<>();
    }

    public List<PsiElement> getReturnOrThrowStatements(final PsiMethod sourceMethod) {
        if(sourceMethod instanceof PsiCompiledElement || sourceMethod.getBody() == null) {
            return Collections.emptyList();
        }
        return sourceMethodToReturnOrThrowStatements.computeIfAbsent(sourceMethod, this::getReturnOrThrowStatementsImpl);
    }

    public List<PsiElement> getReturnOrThrowStatements(final PsiLambdaExpression psiLambdaExpression) {
        final PsiElement body = psiLambdaExpression.getBody();
        if(!(body instanceof PsiCodeBlock)) {
            return Collections.emptyList();
        }
        return sourceMethodToReturnOrThrowStatements.computeIfAbsent(psiLambdaExpression, this::getReturnOrThrowStatementsImpl);
    }

    private List<PsiElement> getReturnOrThrowStatementsImpl(final PsiElement closure) {
        final List<PsiElement> ret = new ArrayList<>();
        closure.accept(new JavaRecursiveElementWalkingVisitor() {
            @Override
            public void visitReturnStatement(@NotNull final PsiReturnStatement statement) {
                super.visitReturnStatement(statement);
                final PsiElement parentClosure = getParentClosure(statement);
                if(parentClosure == null) {
                    return;
                }
                if(parentClosure == closure) {
                    ret.add(statement);
                }
            }

            @Override
            public void visitThrowStatement(@NotNull final PsiThrowStatement statement) {
                super.visitThrowStatement(statement);
                ret.add(statement);
            }
        });
        return ret;
    }
}
