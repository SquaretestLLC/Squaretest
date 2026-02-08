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
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.dependencyinteraction.followup.RValueInfo;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.*;

public class ObjectsProcessor implements MethodCallProcessor {

    @NotNull
    private final JavaPsiFacade javaPsiFacade;
    @NotNull
    private final PsiClass sourceClass;

    public ObjectsProcessor(@NotNull final JavaPsiFacade javaPsiFacade, @NotNull final PsiClass sourceClass) {
        this.javaPsiFacade = javaPsiFacade;
        this.sourceClass = sourceClass;
    }

    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithArgThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final PsiElement originalStartingElement,
            @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!StringUtils.equals(containingClass.getQualifiedName(), JavaNames.JavaUtilObjects)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
        final PsiParameter[] params = psiMethod.getParameterList().getParameters();
        final PsiExpression[] args = currentMethodCall.getArgumentList().getExpressions();
        final int index = getIndexOfElementThatContainsElement(args, originalStartingElement);
        if(StringUtils.equalsAny(methodName, "isNull")) {
            if(currentReturnOutcome == ReturnOutcome.Null) {
                ret.withReturnOutcome(ReturnOutcome.True);
                return NextStep.Continue;
            } else {
                if(currentReturnOutcome != ReturnOutcome.Unknown || !preserveUnknown) {
                    ret.withReturnOutcome(ReturnOutcome.False);
                }
                return NextStep.Continue;
            }
        }
        if(StringUtils.equalsAny(methodName, "nonNull")) {
            if(currentReturnOutcome == ReturnOutcome.Null) {
                ret.withReturnOutcome(ReturnOutcome.False);
                return NextStep.Continue;
            } else {
                if(currentReturnOutcome != ReturnOutcome.Unknown || !preserveUnknown) {
                    ret.withReturnOutcome(ReturnOutcome.True);
                }
                return NextStep.Continue;
            }
        }

        if(StringUtils.equalsAny(methodName, "requireNonNull")) {
            // If position is zero, apply the logic.
            // If position is 1 assume the call throws NPE.
            if(index == 0) {
                if(currentReturnOutcome == ReturnOutcome.Null) {
                    ret.addElementsContainingDisHit(getArgsThatDoNotContainElement(args, originalStartingElement));
                    ret.withExceptionThrown(currentMethodCall, getNullPointerExceptionType());
                    return NextStep.Return;
                } else {
                    ret.addElementsContainingDisHit(getNonSupplierArgsThatDoNotContainElement(params, args, originalStartingElement));
                    return NextStep.Continue;
                }
            }
            return null;
        }

        if(StringUtils.equalsAny(methodName, "requireNonNullElse")) {
            if(index == 0) {
                if(currentReturnOutcome == ReturnOutcome.Null) {
                    ret.addElementsContainingDisHit(getArgsThatDoNotContainElement(args, originalStartingElement));
                    ret.withReturnOutcome(ReturnOutcome.Other);
                    return NextStep.Continue;
                } else {
                    ret.addElementsContainingDisHit(getArgsThatDoNotContainElement(args, originalStartingElement));
                    return NextStep.Continue;
                }
            }
            if(currentReturnOutcome == ReturnOutcome.Null) {
                ret.withExceptionThrown(currentMethodCall, getNullPointerExceptionType());
                return NextStep.Return;
            } else {
                return NextStep.Continue;
            }
        }

        if(StringUtils.equalsAny(methodName, "requireNonNullElseGet")) {
            if(index == 0) {
                if(currentReturnOutcome == ReturnOutcome.Null) {
                    ret.addElementsContainingDisHit(getArgsThatDoNotContainElement(args, originalStartingElement));
                    ret.withReturnOutcome(ReturnOutcome.Other);
                    return NextStep.Continue;
                } else {
                    return NextStep.Continue;
                }
            }
            if(currentReturnOutcome == ReturnOutcome.Null) {
                ret.withExceptionThrown(currentMethodCall, getNullPointerExceptionType());
                return NextStep.Return;
            } else {
                return NextStep.Continue;
            }
        }
        return null;
    }

    private PsiType getNullPointerExceptionType() {
        final PsiElementFactory elementFactory = javaPsiFacade.getElementFactory();
        final PsiClass exceptionClass = javaPsiFacade.findClass("java.lang.NullPointerException", sourceClass.getResolveScope());
        if(exceptionClass == null) {
            return null;
        }
        return elementFactory.createType(exceptionClass);
    }
}
