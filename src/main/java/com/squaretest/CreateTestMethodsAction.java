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
package com.squaretest;

import com.intellij.codeInsight.generation.actions.BaseGenerateAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassOwner;
import com.intellij.psi.PsiCompiledElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.testIntegration.TestFramework;
import com.intellij.testIntegration.TestIntegrationUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CreateTestMethodsAction extends BaseGenerateAction {

    public CreateTestMethodsAction() {
        super(new CreateTestMethodsHandler());
    }

    @Override
    protected boolean isValidForFile(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile file) {
        if(file instanceof PsiCompiledElement) {
            return false;
        }
        if(!(file instanceof PsiClassOwner)) {
            return false;
        }
        final VirtualFile virtualFile = file.getVirtualFile();
        if(virtualFile == null) {
            return false;
        }
        return isJavaOrGroovyFile(file) && hasAtLeastOneClass(file) && hasClassWithTestFramework(file);
    }

    private static boolean hasClassWithTestFramework(final PsiFile psiFile) {
        final PsiClass firstClassInFile = getFirstClassInFile((PsiClassOwner) psiFile);
        if(firstClassInFile == null) {
            return false;
        }
        List<TestFramework> frameworks = TestIntegrationUtils.findSuitableFrameworks(firstClassInFile);
        return !frameworks.isEmpty();
    }

    private static boolean isJavaOrGroovyFile(@NotNull final PsiFile file) {
        if(file instanceof PsiJavaFile) {
            return true;
        }
        // Do not check to see if this is a GroovyFile, as the GroovyFile class won't exist if the Groovy plugin is disabled.
        final FileType fileType = file.getFileType();
        return fileType.getName().equals("Groovy");
    }

    private static boolean hasAtLeastOneClass(@NotNull final PsiFile file) {
        final PsiClass psiClass = getFirstClassInFile((PsiClassOwner) file);
        if(psiClass == null) {
            return false;
        }
        return !psiClass.isInterface() && !psiClass.isEnum();
    }

    @Nullable
    private static PsiClass getFirstClassInFile(final PsiClassOwner file) {
        final PsiClass[] psiClasses = file.getClasses();
        if(psiClasses.length == 0) {
            return null;
        }
        return psiClasses[0];
    }
}
