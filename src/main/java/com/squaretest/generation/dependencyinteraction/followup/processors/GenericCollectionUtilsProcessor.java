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
import com.intellij.psi.PsiType;
import com.intellij.psi.util.InheritanceUtil;
import com.squaretest.generation.dependencyinteraction.followup.RValueInfo;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.getArgsThatDoNotContainElement;

public class GenericCollectionUtilsProcessor implements MethodCallProcessor {
    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithArgThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final PsiElement originalStartingElement,
            @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!isGenericCollectionUtils(containingClass)) {
            return null;
        }
        if(!psiMethod.hasModifierProperty(PsiModifier.STATIC)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        final PsiExpression[] args = currentMethodCall.getArgumentList().getExpressions();
        final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();

        if(StringUtils.equals(methodName, "containsAny")) {
            if(args.length != 2) {
                return null;
            }
            if(!allAreCollections(args)) {
                return null;
            }
            final List<PsiExpression> otherArgs = getArgsThatDoNotContainElement(args, originalStartingElement);
            ret.addElementsContainingDisHit(otherArgs);

            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.False);
            } else if(currentReturnOutcome == ReturnOutcome.Other) {
                ret.withReturnOutcome(ReturnOutcome.True);
            } else {
                ret.withReturnOutcome(ReturnOutcome.Unknown);
            }
            return NextStep.Continue;
        }

        if(StringUtils.equalsAny(methodName, "isEmpty", "isNullOrEmpty", "isEmptyOrNull")) {
            if(args.length != 1) {
                return null;
            }
            if(currentReturnOutcome == ReturnOutcome.Null || currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.True);
            } else if(currentReturnOutcome != ReturnOutcome.Unknown) {
                ret.withReturnOutcome(ReturnOutcome.False);
            }
            return NextStep.Continue;
        }

        if(StringUtils.equals(methodName, "isNotEmpty")) {
            if(args.length != 1) {
                return null;
            }
            if(currentReturnOutcome == ReturnOutcome.Null || currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.False);
            } else if(currentReturnOutcome != ReturnOutcome.Unknown) {
                ret.withReturnOutcome(ReturnOutcome.True);
            }
            return NextStep.Continue;
        }

        if(StringUtils.equals(methodName, "emptyIfNull")) {
            if(args.length != 1) {
                return null;
            }
            if(currentReturnOutcome == ReturnOutcome.Null) {
                ret.withReturnOutcome(ReturnOutcome.Empty);
            }
            return NextStep.Continue;
        }
        return null;
    }

    private boolean allAreCollections(final PsiExpression[] args) {
        for(final PsiExpression arg : args) {
            final PsiType type = arg.getType();
            if(!InheritanceUtil.isInheritor(type, "java.util.Collection")) {
                return false;
            }
        }
        return true;
    }


    private boolean isGenericCollectionUtils(final PsiClass containingClass) {
        if(containingClass == null) {
            return false;
        }
        return StringUtils.equalsAny(containingClass.getName(), "CollectionUtils", "CollectionsUtils", "IterableUtils", "IterablesUtils", "ListUtils", "ListsUtils");
    }
}
