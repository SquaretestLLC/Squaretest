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
package com.squaretest.generation.filetemplateutil;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.psi.PsiClassOwner;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;
import com.intellij.refactoring.PackageWrapper;
import com.intellij.util.CommonJavaRefactoringUtil;
import com.intellij.util.IncorrectOperationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PackageDirectoryCreator {

    private static final Pattern CannotCreatePackageErrorRegex = Pattern.compile("^(Cannot create package )(.*)( in source folder )(.*)");

    public static PsiDirectory createDirectory(
            final Project project, final PsiClassOwner javaOrGroovyFile,
            final PsiDirectory testSourcesRoot) throws CannotCreatePackageException {

        try {
            return WriteCommandAction.runWriteCommandAction(project, (ThrowableComputable<PsiDirectory, IncorrectOperationException>) () -> {
                final PackageWrapper targetPackage = new PackageWrapper(PsiManager.getInstance(project), javaOrGroovyFile.getPackageName());
                return CommonJavaRefactoringUtil.createPackageDirectoryInSourceRoot(targetPackage, testSourcesRoot.getVirtualFile());
            });
        } catch(final IncorrectOperationException ex) {
            final String message = ex.getMessage();
            final Matcher matcher = CannotCreatePackageErrorRegex.matcher(message);
            if(matcher.find() && matcher.groupCount() == 4) {
                final String packageName = matcher.group(2);
                final String sourceDirectory = matcher.group(4);
                throw new CannotCreatePackageException(packageName, sourceDirectory, ex);
            }
            throw ex;
        }
    }
}
