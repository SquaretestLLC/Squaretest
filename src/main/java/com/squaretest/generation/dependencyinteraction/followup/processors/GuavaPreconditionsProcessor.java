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

import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiType;
import com.squaretest.generation.dependencyinteraction.followup.RValueInfo;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.getArgsThatDoNotContainElement;
import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.getIndexOfElementThatContainsElement;

public class GuavaPreconditionsProcessor implements MethodCallProcessor {

    @NotNull
    private final JavaPsiFacade javaPsiFacade;
    @NotNull
    private final PsiClass sourceClass;

    public GuavaPreconditionsProcessor(@NotNull final JavaPsiFacade javaPsiFacade, @NotNull final PsiClass sourceClass) {
        this.javaPsiFacade = javaPsiFacade;
        this.sourceClass = sourceClass;
    }

    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithArgThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final PsiElement originalStartingElement,
            @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!isPreconditionsClass(containingClass)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        final PsiExpression[] args = currentMethodCall.getArgumentList().getExpressions();
        final int index = getIndexOfElementThatContainsElement(args, originalStartingElement);
        final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
        if(methodName.equals("checkArgument")) {
            if(index != 0) {
                return null;
            }
            if(currentReturnOutcome == ReturnOutcome.False) {
                ret.addElementsContainingDisHit(getArgsThatDoNotContainElement(args, originalStartingElement));
                ret.withExceptionThrown(currentMethodCall, getIllegalArgumentException());
                return NextStep.Return;
            }
            ret.addElementsContainingDisHit(getArgsThatDoNotContainElement(args, originalStartingElement));
            ret.withReturnOutcome(ReturnOutcome.Void);
            return NextStep.Continue;
        }
        if(methodName.equals("checkNotNull")) {
            if(index != 0) {
                return null;
            }
            if(currentReturnOutcome == ReturnOutcome.Null) {
                ret.addElementsContainingDisHit(getArgsThatDoNotContainElement(args, originalStartingElement));
                ret.withExceptionThrown(currentMethodCall, getNullPointerException());
                return NextStep.Return;
            }
            ret.addElementsContainingDisHit(getArgsThatDoNotContainElement(args, originalStartingElement));
            return NextStep.Continue;
        }
        if(methodName.equals("checkState")) {
            if(index != 0) {
                return null;
            }
            if(currentReturnOutcome == ReturnOutcome.False) {
                ret.addElementsContainingDisHit(getArgsThatDoNotContainElement(args, originalStartingElement));
                ret.withExceptionThrown(currentMethodCall, getIllegalStateException());
                return NextStep.Return;
            }
            ret.addElementsContainingDisHit(getArgsThatDoNotContainElement(args, originalStartingElement));
            ret.withReturnOutcome(ReturnOutcome.Void);
            return NextStep.Continue;
        }
        return null;
    }

    private PsiType getIllegalStateException() {
        return getException("java.lang.IllegalStateException");
    }

    private PsiType getNullPointerException() {
        return getException("java.lang.NullPointerException");
    }

    private PsiType getIllegalArgumentException() {
        return getException("java.lang.IllegalArgumentException");
    }

    private PsiType getException(final String canonicalName) {
        final PsiElementFactory elementFactory = javaPsiFacade.getElementFactory();
        final PsiClass exceptionClass = javaPsiFacade.findClass(canonicalName, sourceClass.getResolveScope());
        if(exceptionClass == null) {
            return null;
        }
        return elementFactory.createType(exceptionClass);
    }

    private static boolean isPreconditionsClass(final PsiClass containingClass) {
        final String qualifiedName = containingClass.getQualifiedName();
        return StringUtils.equalsAny(qualifiedName, "com.google.common.base.Preconditions");
    }
}
