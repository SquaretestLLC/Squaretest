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
import com.intellij.psi.PsiExpressionList;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiType;
import com.intellij.psi.util.InheritanceUtil;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.dependencyinteraction.followup.BooleanConversionMethod;
import com.squaretest.generation.dependencyinteraction.followup.RValueInfo;
import com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.*;

public class GuavaOptionalProcessor implements MethodCallProcessor {
    @NotNull
    private final JavaPsiFacade javaPsiFacade;
    @NotNull
    private final PsiClass sourceClass;

    public GuavaOptionalProcessor(@NotNull final JavaPsiFacade javaPsiFacade, @NotNull final PsiClass sourceClass) {
        this.javaPsiFacade = javaPsiFacade;
        this.sourceClass = sourceClass;
    }

    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithArgThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final PsiElement originalStartingElement,
            @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!isGuavaOptional(containingClass)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
        final PsiExpressionList argList = currentMethodCall.getArgumentList();
        final PsiExpression[] args = argList.getExpressions();
        if(StringUtils.equals(methodName, "of")) {
            if(currentReturnOutcome == ReturnOutcome.Null) {
                // This is likely a bug that will cause a NullPointerException.
                // Assume the developer meant to call fromNullable(..).
                ret.withReturnOutcome(ReturnOutcome.Absent);
                return NextStep.Continue;
            }
            if(ret.getReturnOutcome() == ReturnOutcome.Unknown) {
                final PsiExpression arg = getArgumentThatContainsElement(currentMethodCall, originalStartingElement);
                if(arg == null) {
                    return NextStep.Continue;
                }
                final PsiType argType = arg.getType();
                if(InheritanceUtil.isInheritor(argType, JavaNames.JavaLangIterable)) {
                    return NextStep.Continue;
                }
                ret.withReturnOutcome(ReturnOutcome.Other);
                return NextStep.Continue;
            }
            return NextStep.Continue;
        }
        if(methodName.equals("fromNullable")) {
            if(currentReturnOutcome == ReturnOutcome.Null) {
                ret.withReturnOutcome(ReturnOutcome.Absent);
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "fromJavaUtil", "toJavaUtil")) {
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "get", "isPresent", "orNull", "asSet")) {
            // These methods don't have args. We can't be passed to them.
            // If we hit th is point just continue on.
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "transform")) {
            if(currentReturnOutcome == ReturnOutcome.Null) {
                // This will throw a NullPointerException. Assume it's a bug and just continue
                // on as though this did not throw a NullPointerException.
                return NextStep.Continue;
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "or")) {
            // We only reach this point if we're a simple supplier passed to the or(..) method.
            // Let the return value and DIs propagate.
            return NextStep.Continue;
        }
        // If we reach this point we have a call like optional.equals() or optional.toString().
        // Just keep going.
        ret.addElementsContainingDisHit(getArgsThatDoNotContainElement(args, originalStartingElement));
        ret.withReturnOutcome(ReturnOutcome.Unknown);
        return NextStep.Continue;
    }

    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithQualifierThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!isGuavaOptional(containingClass)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
        final PsiExpressionList argList = currentMethodCall.getArgumentList();
        final PsiExpression[] args = argList.getExpressions();
        if(StringUtils.equalsAny(methodName, "get")) {
            // We are exiting the Optional call chain.
            if(currentReturnOutcome == ReturnOutcome.Absent) {
                ret.withExceptionThrown(currentMethodCall, getIllegalStateException());
                return NextStep.Return;
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "isPresent")) {
            ret.withBooleanConversionMethod(BooleanConversionMethod.Optional);
            if(currentReturnOutcome == ReturnOutcome.Absent || currentReturnOutcome == ReturnOutcome.Null) {
                ret.withReturnOutcome(ReturnOutcome.False);
            } else {
                if(currentReturnOutcome != ReturnOutcome.Unknown || !preserveUnknown) {
                    ret.withReturnOutcome(ReturnOutcome.True);
                }
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "transform")) {
            if(currentReturnOutcome != ReturnOutcome.Absent) {
                ret.addElementContainingDisHit(argList);
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "or")) {
            // This only runs if our DI returns absent.
            if(currentReturnOutcome == ReturnOutcome.Absent) {
                ret.addElementContainingDisHit(argList);
                if(SQPsiUtil.isCalledWithSingleNullArg(currentMethodCall)) {
                    ret.withReturnOutcome(ReturnOutcome.Null);
                } else if(SQPsiUtil.isSingleArgWithEmptyInitExpression(currentMethodCall)) {
                    ret.withReturnOutcome(ReturnOutcome.Empty);
                } else {
                    ret.withReturnOutcome(ReturnOutcome.Other);
                }
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "orNull")) {
            if(currentReturnOutcome == ReturnOutcome.Absent) {
                ret.withReturnOutcome(ReturnOutcome.Null);
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "toJavaUtil")) {
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "asSet")) {
            if(currentReturnOutcome == ReturnOutcome.Absent) {
                ret.withReturnOutcome(ReturnOutcome.Empty);
            }
            return NextStep.Continue;
        }
        if(args.length != 0) {
            ret.addElementContainingDisHit(argList);
        }
        ret.withReturnOutcome(ReturnOutcome.Unknown);
        return NextStep.Continue;
    }

    private PsiType getIllegalStateException() {
        final PsiElementFactory elementFactory = javaPsiFacade.getElementFactory();
        final PsiClass exceptionClass = javaPsiFacade.findClass("java.lang.IllegalStateException", sourceClass.getResolveScope());
        if(exceptionClass == null) {
            return null;
        }
        return elementFactory.createType(exceptionClass);
    }
}
