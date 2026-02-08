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
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.expressions.GrExpression;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.expressions.GrReferenceExpression;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.expressions.path.GrMethodCallExpression;

import static com.squaretest.generation.filecreation.JavaMockitoCallUtils.*;

public class GroovyMockitoCallUtils {
    @Nullable
    public static LineBreakInfo tryGetMockitoThenCallInfo(final GrMethodCallExpression methodCallExpression) {
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
        final GrExpression invokedExpression = methodCallExpression.getInvokedExpression();
        if(!(invokedExpression instanceof final GrReferenceExpression methodExpression)) {
            return null;
        }
        final PsiElement dotElement = methodExpression.getDotToken();
        if(dotElement == null) {
            return null;
        }
        final PsiExpressionList psiExpressionList = PsiTreeUtil.getChildOfType(methodCallExpression, PsiExpressionList.class);
        if(psiExpressionList == null) {
            return null;
        }
        final PsiElement firstChild = psiExpressionList.getFirstChild();
        if(!(firstChild instanceof LeafPsiElement)) {
            return null;
        }
        if(!firstChild.getText().trim().equals("(")) {
            return null;
        }
        final PsiElement nextSibling = firstChild.getNextSibling();
        if(!(nextSibling instanceof final LeafPsiElement whitespaceElement)) {
            return null;
        }
        final String whitespaceText = whitespaceElement.getText();
        if(!StringUtils.isBlank(whitespaceText)) {
            return null;
        }
        if(!whitespaceText.contains("\n")) {
            return null;
        }
        return new LineBreakInfo(dotElement, whitespaceElement);
    }

    @Nullable
    public static LineBreakInfo tryGetMockitoMatcherCallInfo(final GrMethodCallExpression methodCallExpression) {
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
        if(!(firstChild instanceof LeafPsiElement)) {
            return null;
        }
        if(!firstChild.getText().trim().equals("(")) {
            return null;
        }
        final PsiElement nextSibling = firstChild.getNextSibling();
        if(!(nextSibling instanceof final LeafPsiElement whitespaceElement)) {
            return null;
        }
        final String whitespaceText = whitespaceElement.getText();
        if(!StringUtils.isBlank(whitespaceText)) {
            return null;
        }
        if(!whitespaceText.contains("\n")) {
            return null;
        }
        return new LineBreakInfo(methodCallExpression, whitespaceElement);
    }

    @Nullable
    public static LineBreakInfo tryGetAssertJCallInfo(final GrMethodCallExpression methodCallExpression) {
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
        final GrExpression invokedExpression = methodCallExpression.getInvokedExpression();
        if(!(invokedExpression instanceof final GrReferenceExpression methodExpression)) {
            return null;
        }
        final PsiElement dotElement = methodExpression.getDotToken();
        if(dotElement == null) {
            return null;
        }
        final PsiExpressionList psiExpressionList = PsiTreeUtil.getChildOfType(methodCallExpression, PsiExpressionList.class);
        if(psiExpressionList == null) {
            return null;
        }
        final PsiElement firstChild = psiExpressionList.getFirstChild();
        if(!(firstChild instanceof LeafPsiElement)) {
            return null;
        }
        if(!firstChild.getText().trim().equals("(")) {
            return null;
        }
        final PsiElement nextSibling = firstChild.getNextSibling();
        if(!(nextSibling instanceof final LeafPsiElement whitespaceElement)) {
            return null;
        }
        final String whitespaceText = whitespaceElement.getText();
        if(!StringUtils.isBlank(whitespaceText)) {
            return null;
        }
        if(!whitespaceText.contains("\n")) {
            return null;
        }
        return new LineBreakInfo(dotElement, whitespaceElement);
    }
}
