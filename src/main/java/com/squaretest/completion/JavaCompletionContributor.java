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
package com.squaretest.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.completion.JavaCompletionSorting;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilCore;
import com.squaretest.settings.store.SettingsProvider;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class JavaCompletionContributor extends CompletionContributor {

    @Override
    public void fillCompletionVariants(
            @NotNull final CompletionParameters parameters, @NotNull final CompletionResultSet _result) {

        if(parameters.getCompletionType() != CompletionType.BASIC) {
            return;
        }
        final SettingsProvider settingsProvider = new SettingsProvider();
        if(!settingsProvider.getEnableCompletionSuggestionsForTestMethods()) {
            return;
        }

        final PsiElement position = parameters.getPosition();
        if(!isInJavaTestClass(position)) {
            return;
        }
        final CompletionResultSet result = JavaCompletionSorting.addJavaSorting(parameters, _result);
        if(position instanceof PsiIdentifier) {
            JavaGenerateTestMethodCompletionContributor.fillCompletionVariants(parameters, result);
        }
    }

    public static boolean hasTestClassName(final String name) {
        return StringUtils.endsWithAny(name, "Test", "Tests", "IT") || SourceClassFinder.hasPrefix(name, "Test");
    }

    public static boolean isInJavaTestClass(PsiElement position) {
        if(!PsiUtilCore.findLanguageFromElement(position).isKindOf(JavaLanguage.INSTANCE)) {
            return false;
        }

        // Find the class containing the element at the cursor; this may be an inner-class.
        final PsiClass containingClass = PsiTreeUtil.getParentOfType(position, PsiClass.class, false);
        if(containingClass == null) {
            return false;
        }

        final String className = containingClass.getName();
        return hasTestClassName(className) && isUnderTestSources(position);
    }

    private static boolean isUnderTestSources(PsiElement clazz) {
        // Note: clazz.getContainingFile().getVirtualFile() always returns null.
        // Use PsiUtilCore to find the actual virtual file.
        final VirtualFile vFile = PsiUtilCore.getVirtualFile(clazz);
        if(vFile == null) {
            return false;
        }
        return ProjectRootManager.getInstance(clazz.getProject()).getFileIndex().isInTestSourceContent(vFile);
    }
}
