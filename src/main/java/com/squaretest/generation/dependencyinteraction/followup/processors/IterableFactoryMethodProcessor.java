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

import com.intellij.psi.PsiArrayType;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypeParameter;
import com.intellij.psi.PsiTypeParameterList;
import com.intellij.psi.util.InheritanceUtil;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.dependencyinteraction.followup.RValueInfo;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IterableFactoryMethodProcessor implements MethodCallProcessor {
    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithArgThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final PsiElement originalStartingElement,
            @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!psiMethod.hasModifierProperty(PsiModifier.STATIC)) {
            return null;
        }
        final PsiTypeParameterList typeParamsList = psiMethod.getTypeParameterList();
        if(typeParamsList == null) {
            return null;
        }
        final PsiTypeParameter[] typeParams = typeParamsList.getTypeParameters();
        if(typeParams.length == 0) {
            return null;
        }
        final PsiParameter[] formalParams = psiMethod.getParameterList().getParameters();
        if(formalParams.length != 1) {
            return null;
        }
        NextStep result = tryProcessIterableFactoryWithIterableParam(psiMethod, currentMethodCall);
        if(result != null) {
            return result;
        }

        result = tryProcessIterableFactoryWithItemParam(psiMethod, currentMethodCall, ret);
        return result;
    }

    /**
     * Detect and handle cases like the following.
     * <code>
     * Set.copyOf(fooList);
     * Arrays.asList(fooArray);
     * </code>
     */
    private NextStep tryProcessIterableFactoryWithIterableParam(
            final PsiMethod psiMethod, final PsiMethodCallExpression currentMethodCall) {
        final PsiParameter[] formalParams = psiMethod.getParameterList().getParameters();
        if(formalParams.length != 1) {
            return null;
        }
        final PsiExpression[] argExpressions = currentMethodCall.getArgumentList().getExpressions();
        if(argExpressions.length != 1) {
            return null;
        }
        final PsiParameter formalParam = formalParams[0];
        final PsiType formalParamType = formalParam.getType();
        if(!(formalParamType instanceof PsiArrayType) && !InheritanceUtil.isInheritor(formalParamType, JavaNames.JavaLangIterable)) {
            return null;
        }
        final PsiExpression actualParam = argExpressions[0];
        final PsiType actualParamType = actualParam.getType();
        if(!(actualParamType instanceof PsiArrayType) && !InheritanceUtil.isInheritor(actualParamType, JavaNames.JavaLangIterable)) {
            return null;
        }
        if(!InheritanceUtil.isInheritor(psiMethod.getReturnType(), JavaNames.JavaLangIterable)) {
            return null;
        }
        // Keep the current value and continue.
        return NextStep.Continue;
    }

    /**
     * Detect and handle cases like the following.
     * <code>
     * Arrays.asList(fooItem);
     * List.of(fooItem);
     * </code>
     */
    private NextStep tryProcessIterableFactoryWithItemParam(
            final PsiMethod psiMethod, final PsiMethodCallExpression currentMethodCall,
            final RValueInfo ret) {
        final PsiParameter[] formalParams = psiMethod.getParameterList().getParameters();
        if(formalParams.length != 1) {
            return null;
        }
        final PsiExpression[] argExpressions = currentMethodCall.getArgumentList().getExpressions();
        if(argExpressions.length != 1) {
            return null;
        }
        final PsiParameter formalParam = formalParams[0];
        final PsiType formalParamType = formalParam.getType();
        if(InheritanceUtil.isInheritor(formalParamType, JavaNames.JavaLangIterable)) {
            return null;
        }
        if(formalParam instanceof PsiArrayType && !formalParam.isVarArgs()) {
            return null;
        }

        final PsiExpression actualParam = argExpressions[0];
        final PsiType actualParamType = actualParam.getType();
        if((actualParamType instanceof PsiArrayType) || InheritanceUtil.isInheritor(actualParamType, JavaNames.JavaLangIterable)) {
            return null;
        }
        if(!InheritanceUtil.isInheritor(psiMethod.getReturnType(), JavaNames.JavaLangIterable)) {
            return null;
        }
        ret.withReturnOutcome(ReturnOutcome.Other);
        return NextStep.Continue;
    }
}
