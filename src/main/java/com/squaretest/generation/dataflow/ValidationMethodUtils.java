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
package com.squaretest.generation.dataflow;

import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypeParameter;
import com.intellij.psi.PsiTypeParameterList;
import com.intellij.psi.impl.source.PsiClassReferenceType;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class ValidationMethodUtils {

    private static final Set<String> ValidatorMethodNames = Set.of(
            // From Guava Preconditions.
            "checkNotNull",
            "checkNonNull",

            // From java.lang.Objects.
            "requireNotNull",
            "requireNonNull",
            "requireNonNullElse",
            "requireNonNullElseGet",

            // Similar names.
            "ensureNotNull",
            "ensureNonNull",

            // From Apache Commons Lang 3 org.apache.commons.lang3.Validate methods.
            "notNull",
            "nonNull",
            "notEmpty",
            "notBlank",

            // From AWS SDK Utils software.amazon.awssdk.utils.Validate methods.
            "paramNotNull",
            "paramNotBlank",
            "paramNonNull",
            "paramNonBlank",
            "getOrDefault",
            "validState"
    );

    @Nullable
    public static PsiExpression getExpressionWithinValidationMethod(final PsiExpression psiExpression) {
        if(!(psiExpression instanceof final PsiMethodCallExpression methodCallExpression)) {
            return null;
        }

        final PsiMethod method = methodCallExpression.resolveMethod();
        if(method == null) {
            return null;
        }
        // Determine if the method is static.
        if(!method.hasModifierProperty(PsiModifier.STATIC)) {
            return null;
        }

        // Determine if the method looks like the following.
        // public static <T> T requireNonNull(T obj, String message) {..}
        // Ensure the method has a single type parameter.
        final PsiTypeParameterList typeParameterList = method.getTypeParameterList();
        if(typeParameterList == null || typeParameterList.getTypeParameters().length != 1) {
            return null;
        }
        final PsiTypeParameter methodTypeParam = method.getTypeParameterList().getTypeParameters()[0];

        // Ensure the method has at least one regular parameter.
        if(method.getParameterList().getParameters().length == 0) {
            return null;
        }

        // Ensure the first parameter is a class reference type (Type T).
        final PsiType paramType = method.getParameterList().getParameters()[0].getType();
        if(!(paramType instanceof final PsiClassReferenceType paramTypeReference)) {
            return null;
        }

        // The first parameter is the same as the method type parameter -- <T>.
        if(paramTypeReference.resolve() != methodTypeParam) {
            return null;
        }

        // Ensure the return type exists and is a class reference type (T).
        final PsiType returnType = method.getReturnType();
        if(!(returnType instanceof final PsiClassReferenceType returnTypeReference)) {
            return null;
        }
        // Ensure teh return type is the same as the method type parameter -- <T>.
        if(returnTypeReference.resolve() != methodTypeParam) {
            return null;
        }

        if(!ValidatorMethodNames.contains(method.getName())) {
            return null;
        }

        final PsiExpression[] actualParams = methodCallExpression.getArgumentList().getExpressions();
        if(actualParams.length == 0) {
            return null;
        }
        return actualParams[0];
    }
}
