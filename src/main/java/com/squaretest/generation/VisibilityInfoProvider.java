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
package com.squaretest.generation;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiJvmMember;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import org.jetbrains.annotations.NotNull;

public class VisibilityInfoProvider {
    @NotNull
    private final String sourceClassPackage;

    public VisibilityInfoProvider(@NotNull final String sourceClassPackage) {
        this.sourceClassPackage = sourceClassPackage;
    }

    public boolean isVisibleToTestClass(final PsiField psiField) {
        return isVisibleToTestClassImpl(psiField);
    }

    public boolean isVisibleToTestClass(final PsiMethod method) {
        return isVisibleToTestClassImpl(method);
    }

    private boolean isVisibleToTestClassImpl(final PsiJvmMember method) {
        if(method.hasModifierProperty(PsiModifier.PUBLIC)) {
            return true;
        }
        if(method.hasModifierProperty(PsiModifier.PRIVATE)) {
            return false;
        }
        final PsiClass containingClass = method.getContainingClass();
        if(containingClass == null) {
            return true;
        }
        if(containingClass.isInterface()) {
            return true;
        }
        final PsiFile containingFile = containingClass.getContainingFile();
        if(!(containingFile instanceof PsiJavaFile)) {
            return true;
        }
        final String packageName = ((PsiJavaFile) containingFile).getPackageName();
        return packageName.equals(sourceClassPackage);
    }

    public boolean isPublic(final PsiMethod method) {
        if(method.hasModifierProperty(PsiModifier.PUBLIC)) {
            return true;
        }
        if(method.hasModifierProperty(PsiModifier.PRIVATE)) {
            return false;
        }
        final PsiClass containingClass = method.getContainingClass();
        if(containingClass == null) {
            return true;
        }
        return containingClass.isInterface();
    }
}
