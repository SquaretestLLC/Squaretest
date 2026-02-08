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
import com.intellij.psi.PsiModifier;
import com.squaretest.generation.dependencyinteraction.followup.RValueInfo;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.getArgsThatDoNotContainElement;
import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.getIndexOfElementThatContainsElement;

public class JavaCollectionsProcessor implements MethodCallProcessor {
    private static final Set<String> IterableFactoryMethodNames = Set.of("asLifoQueue", "checkedCollection", "checkedList",
            "checkedNavigableSet", "checkedQueue", "checkedSet", "checkedSortedSet", "enumeration", "list",
            "synchronizedCollection", "synchronizedList", "synchronizedNavigableSet", "synchronizedSet",
            "synchronizedSortedSet", "unmodifiableCollection", "unmodifiableList", "unmodifiableNavigableSet",
            "unmodifiableSet", "unmodifiableSortedSet");

    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithArgThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final PsiElement originalStartingElement,
            @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!isJavaCollections(containingClass) || !psiMethod.hasModifierProperty(PsiModifier.STATIC)) {
            return null;
        }
        final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
        final String methodName = psiMethod.getName();
        final PsiExpression[] args = currentMethodCall.getArgumentList().getExpressions();
        if(IterableFactoryMethodNames.contains(methodName)) {
            final int index = getIndexOfElementThatContainsElement(args, originalStartingElement);
            if(index == 0) {
                final List<PsiExpression> otherArgs = getArgsThatDoNotContainElement(args, originalStartingElement);
                ret.addElementsContainingDisHit(otherArgs);
                return NextStep.Continue;
            }
            return null;
        }

        if(StringUtils.equalsAny(methodName, "singleton", "singletonList")) {
            final List<PsiExpression> otherArgs = getArgsThatDoNotContainElement(args, originalStartingElement);
            ret.addElementsContainingDisHit(otherArgs);
            ret.withReturnOutcome(ReturnOutcome.Other);
            return NextStep.Continue;
        }

        if(StringUtils.equals(methodName, "disjoint")) {
            final List<PsiExpression> otherArgs = getArgsThatDoNotContainElement(args, originalStartingElement);
            ret.addElementsContainingDisHit(otherArgs);

            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.True);
            } else if(currentReturnOutcome == ReturnOutcome.Other) {
                ret.withReturnOutcome(ReturnOutcome.False);
            } else {
                ret.withReturnOutcome(ReturnOutcome.Unknown);
            }
            return NextStep.Continue;
        }

        return null;
    }

    private boolean isJavaCollections(final PsiClass containingClass) {
        return StringUtils.equals(containingClass.getQualifiedName(), "java.util.Collections");
    }
}
