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
package com.squaretest.generation.dependencyinteraction;

import com.intellij.psi.JavaRecursiveElementWalkingVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiMethodReferenceExpression;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MethodCallsProvider {
    private final Map<PsiElement, List<PsiElement>> cache;
    private final Map<PsiElement, Integer> elementToOffset;

    public MethodCallsProvider() {
        this.elementToOffset = new IdentityHashMap<>();
        this.cache = new IdentityHashMap<>();
    }

    public List<PsiElement> getMethodCallsOrRefsInElement(final PsiElement rootElement) {
        return cache.computeIfAbsent(rootElement, this::getMethodCallsOrRefsInElementImpl);
    }

    @NotNull
    private List<PsiElement> getMethodCallsOrRefsInElementImpl(final PsiElement rootElement) {
        final List<WrappedElement> ret = new ArrayList<>();
        rootElement.accept(new JavaRecursiveElementWalkingVisitor() {
            @Override
            public void visitMethodCallExpression(@NotNull final PsiMethodCallExpression expression) {
                super.visitMethodCallExpression(expression);
                ret.add(new WrappedElement(expression, computeOffset(expression)));
            }

            @Override
            public void visitMethodReferenceExpression(@NotNull final PsiMethodReferenceExpression expression) {
                super.visitMethodReferenceExpression(expression);
                ret.add(new WrappedElement(expression, computeOffset(expression)));
            }
        });
        ret.sort(Comparator.comparing(WrappedElement::offsetToUse));
        return ret.stream().map(WrappedElement::element).collect(Collectors.toList());
    }

    private int computeOffset(final PsiElement psiElement) {
        return elementToOffset.computeIfAbsent(psiElement, this::computeOffsetImpl);
    }

    private int computeOffsetImpl(final PsiElement psiElement) {
        if(psiElement instanceof final PsiMethodCallExpression psiMethodCallExpression) {
            return psiMethodCallExpression.getArgumentList().getTextOffset();
        }
        return psiElement.getTextOffset();
    }

    private record WrappedElement(@NotNull PsiElement element, int offsetToUse) {
    }
}
