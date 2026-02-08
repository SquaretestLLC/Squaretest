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

import com.intellij.psi.*;
import com.intellij.psi.util.PsiUtil;
import org.jetbrains.annotations.Nullable;

public class SQExpressionUtils {

    public static boolean isReturnValueIgnored(final PsiMethod method, final PsiMethodCallExpression callExpression) {
        return isReturnValueIgnoredInternal(method, callExpression);
    }

    public static boolean isReturnValueIgnored(final PsiMethod method, final PsiMethodReferenceExpression referenceExpression) {
        return isReturnValueIgnoredInternal(method, referenceExpression);
    }

    private static boolean isReturnValueIgnoredInternal(final PsiMethod method, final PsiExpression callOrMethodRefExpression) {
        final PsiType returnType = method.getReturnType();
        if(returnType == null || PsiTypes.voidType().equals(returnType)) {
            return false;
        }
        return isVoidContext(callOrMethodRefExpression);
    }

    public static boolean isNullLiteral(PsiExpression expression) {
        expression = PsiUtil.deparenthesizeExpression(expression);
        return expression instanceof PsiLiteralExpression && ((PsiLiteralExpression) expression).getValue() == null;
    }

    public static boolean isZeroLiteral(PsiExpression expression) {
        expression = PsiUtil.deparenthesizeExpression(expression);
        if(!(expression instanceof PsiLiteralExpression)) {
            return false;
        }
        final Object value = ((PsiLiteralExpression) expression).getValue();
        if(value instanceof Integer) {
            return ((Integer) value) == 0;
        }
        return false;
    }

    public static boolean isShortType(@Nullable PsiType type) {
        type = uncapture(type);
        return PsiTypes.shortType().equals(type) || PsiTypes.shortType().equals(PsiPrimitiveType.getUnboxedType(type));
    }

    public static boolean isByteType(@Nullable PsiType type) {
        type = uncapture(type);
        return PsiTypes.byteType().equals(type) || PsiTypes.byteType().equals(PsiPrimitiveType.getUnboxedType(type));
    }

    public static boolean isCharType(@Nullable PsiType type) {
        type = uncapture(type);
        return PsiTypes.charType().equals(type) || PsiTypes.charType().equals(PsiPrimitiveType.getUnboxedType(type));
    }

    private static PsiType uncapture(PsiType type) {
        while(type instanceof PsiCapturedWildcardType) {
            type = ((PsiCapturedWildcardType) type).getUpperBound();
        }
        return type;
    }

    public static boolean isTrueLiteral(@Nullable PsiExpression expression) {
        expression = PsiUtil.skipParenthesizedExprDown(expression);
        if(expression == null) {
            return false;
        }
        return PsiKeyword.TRUE.equals(expression.getText());
    }

    public static boolean isFalseLiteral(@Nullable PsiExpression expression) {
        expression = PsiUtil.skipParenthesizedExprDown(expression);
        if(expression == null) {
            return false;
        }
        return PsiKeyword.FALSE.equals(expression.getText());
    }

    /**
     * Copied from com.siyeh.ig.psiutils.ExpressionUtils#isVoidContext(com.intellij.psi.PsiExpression).
     *
     * @param expression the PsiMethodCallExpression or PsiMethodReferenceExpression.
     * @return true if the expression is evaluated in a void context.
     */
    public static boolean isVoidContext(PsiExpression expression) {
        PsiElement element = PsiUtil.skipParenthesizedExprUp(expression.getParent());
        if(element instanceof PsiExpressionStatement) {
            if(element.getParent() instanceof PsiSwitchLabeledRuleStatement) {
                PsiSwitchBlock block = ((PsiSwitchLabeledRuleStatement) element.getParent()).getEnclosingSwitchBlock();
                return !(block instanceof PsiSwitchExpression);
            }
            return true;
        }
        if(element instanceof PsiExpressionList && element.getParent() instanceof PsiExpressionListStatement) {
            return true;
        }
        if(element instanceof PsiLambdaExpression) {
            if(PsiTypes.voidType().equals(LambdaUtil.getFunctionalInterfaceReturnType((PsiLambdaExpression) element))) {
                return true;
            }
        }
        return false;
    }
}
