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
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiExpressionList;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiType;
import com.squaretest.generation.dependencyinteraction.followup.BooleanConversionMethod;
import com.squaretest.generation.dependencyinteraction.followup.RValueInfo;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.isJavaCollection;

public class JavaCollectionProcessor implements MethodCallProcessor {
    @NotNull
    private final JavaPsiFacade javaPsiFacade;
    @NotNull
    private final PsiClass sourceClass;

    public JavaCollectionProcessor(final @NotNull JavaPsiFacade javaPsiFacade, final @NotNull PsiClass sourceClass) {
        this.javaPsiFacade = javaPsiFacade;
        this.sourceClass = sourceClass;
    }

    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithQualifierThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!isJavaCollection(containingClass)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
        final PsiExpressionList argList = currentMethodCall.getArgumentList();
        if(methodName.equals("size")) {
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withReturnOutcome(ReturnOutcome.Zero);
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "getFirst", "getLast", "removeFirst", "removeLast")) {
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withExceptionThrown(currentMethodCall, getNoSuchElementExceptionType());
                return NextStep.Return;
            }
            ret.withReturnOutcome(ReturnOutcome.Other);
            return NextStep.Continue;
        }
        if(methodName.equals("isEmpty")) {
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withBooleanConversionMethod(BooleanConversionMethod.CollectionMethod);
                ret.withReturnOutcome(ReturnOutcome.True);
            } else if(currentReturnOutcome == ReturnOutcome.Other) {
                ret.withBooleanConversionMethod(BooleanConversionMethod.CollectionMethod);
                ret.withReturnOutcome(ReturnOutcome.False);
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "contains", "containsAll")) {
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                ret.withBooleanConversionMethod(BooleanConversionMethod.CollectionMethod);
                ret.withReturnOutcome(ReturnOutcome.False);
            } else if(currentReturnOutcome == ReturnOutcome.Other) {
                ret.withBooleanConversionMethod(BooleanConversionMethod.CollectionMethod);
                ret.withReturnOutcome(ReturnOutcome.True);
            }
            ret.addElementContainingDisHit(argList);
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "iterator", "toArray")) {
            ret.addElementContainingDisHit(argList);
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "spliterator", "stream", "parallelStream", "reversed")) {
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "subList")) {
            ret.addElementContainingDisHit(argList);
            if(currentReturnOutcome == ReturnOutcome.Empty) {
                return NextStep.Continue;
            }
            ret.withReturnOutcome(ReturnOutcome.Unknown);
            return NextStep.Continue;
        }
        return null;
    }

    private PsiType getNoSuchElementExceptionType() {
        final PsiElementFactory elementFactory = javaPsiFacade.getElementFactory();
        final PsiClass exceptionClass = javaPsiFacade.findClass("java.util.NoSuchElementException", sourceClass.getResolveScope());
        if(exceptionClass == null) {
            return null;
        }
        return elementFactory.createType(exceptionClass);
    }
}
