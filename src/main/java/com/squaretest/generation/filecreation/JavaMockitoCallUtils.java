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
package com.squaretest.generation.filecreation;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpressionList;
import com.intellij.psi.PsiJavaToken;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiReferenceExpression;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.util.PsiTreeUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class JavaMockitoCallUtils {
    static final Set<String> MockitoThenMethodNames = Set.of("thenReturn", "thenAnswer", "thenThrow", "then", "willReturn", "willAnswer", "willThrow", "will");
    static final Set<String> MockitoMatcherContainingClassName = Set.of("org.mockito.ArgumentMatchers", "org.mockito.AdditionalMatchers", "org.mockito.Matchers");
    static final Set<String> AssertJMethodNames = Set.of("isInstanceOf", "isEqualTo");
    static final String AssertJAPIPackagePrefix = "org.assertj.core.api";
    static final String MockitoPackagePrefix = "org.mockito";

    @Nullable
    public static LineBreakInfo tryGetMockitoThenCallInfo(final PsiMethodCallExpression methodCallExpression) {
        final PsiMethod method = methodCallExpression.resolveMethod();
        if(method == null) {
            return null;
        }
        final PsiClass containingClass = method.getContainingClass();
        if(containingClass == null) {
            return null;
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(!StringUtils.startsWith(qualifiedName, MockitoPackagePrefix)) {
            return null;
        }
        if(!MockitoThenMethodNames.contains(method.getName())) {
            return null;
        }
        final PsiReferenceExpression methodExpression = methodCallExpression.getMethodExpression();
        final PsiJavaToken dotElement = PsiTreeUtil.getChildOfType(methodExpression, PsiJavaToken.class);
        if(dotElement == null) {
            return null;
        }
        final PsiExpressionList psiExpressionList = PsiTreeUtil.getChildOfType(methodCallExpression, PsiExpressionList.class);
        if(psiExpressionList == null) {
            return null;
        }
        final PsiElement firstChild = psiExpressionList.getFirstChild();
        if(!(firstChild instanceof final PsiJavaToken firstChildToken)) {
            return null;
        }
        if(!firstChildToken.getText().equals("(")) {
            return null;
        }
        final PsiElement nextSibling = firstChildToken.getNextSibling();
        if(!(nextSibling instanceof final PsiWhiteSpace whitespaceElement)) {
            return null;
        }

        if(!whitespaceElement.getText().contains("\n")) {
            return null;
        }
        return new LineBreakInfo(dotElement, whitespaceElement);
    }

    @Nullable
    public static LineBreakInfo tryGetMockitoMatcherCallInfo(final PsiMethodCallExpression methodCallExpression) {
        final PsiMethod method = methodCallExpression.resolveMethod();
        if(method == null) {
            return null;
        }
        final PsiClass containingClass = method.getContainingClass();
        if(containingClass == null) {
            return null;
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName == null || !MockitoMatcherContainingClassName.contains(qualifiedName)) {
            return null;
        }
        if(!method.hasModifierProperty(PsiModifier.STATIC)) {
            return null;
        }
        final PsiExpressionList psiExpressionList = PsiTreeUtil.getChildOfType(methodCallExpression, PsiExpressionList.class);
        if(psiExpressionList == null) {
            return null;
        }
        final PsiElement firstChild = psiExpressionList.getFirstChild();
        if(!(firstChild instanceof final PsiJavaToken firstChildToken)) {
            return null;
        }
        if(!firstChildToken.getText().equals("(")) {
            return null;
        }
        final PsiElement nextSibling = firstChildToken.getNextSibling();
        if(!(nextSibling instanceof final PsiWhiteSpace whitespaceElement)) {
            return null;
        }

        if(!whitespaceElement.getText().contains("\n")) {
            return null;
        }
        return new LineBreakInfo(methodCallExpression, whitespaceElement);
    }

    @Nullable
    public static LineBreakInfo tryGetAssertJCallInfo(final PsiMethodCallExpression methodCallExpression) {
        final PsiMethod method = methodCallExpression.resolveMethod();
        if(method == null) {
            return null;
        }
        final PsiClass containingClass = method.getContainingClass();
        if(containingClass == null) {
            return null;
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(!StringUtils.startsWith(qualifiedName, AssertJAPIPackagePrefix)) {
            return null;
        }
        if(!AssertJMethodNames.contains(method.getName())) {
            return null;
        }
        final PsiReferenceExpression methodExpression = methodCallExpression.getMethodExpression();
        final PsiJavaToken dotElement = PsiTreeUtil.getChildOfType(methodExpression, PsiJavaToken.class);
        if(dotElement == null) {
            return null;
        }
        final PsiExpressionList psiExpressionList = PsiTreeUtil.getChildOfType(methodCallExpression, PsiExpressionList.class);
        if(psiExpressionList == null) {
            return null;
        }
        final PsiElement firstChild = psiExpressionList.getFirstChild();
        if(!(firstChild instanceof final PsiJavaToken firstChildToken)) {
            return null;
        }
        if(!firstChildToken.getText().equals("(")) {
            return null;
        }
        final PsiElement nextSibling = firstChildToken.getNextSibling();
        if(!(nextSibling instanceof final PsiWhiteSpace whitespaceElement)) {
            return null;
        }

        if(!whitespaceElement.getText().contains("\n")) {
            return null;
        }
        return new LineBreakInfo(dotElement, whitespaceElement);
    }

}
