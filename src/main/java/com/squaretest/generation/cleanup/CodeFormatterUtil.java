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
package com.squaretest.generation.cleanup;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

public class CodeFormatterUtil {
    public static void cleanUpElements(final Project project, final PsiElement[] elements) {
        if(elements.length == 0) {
            return;
        }
        final PsiFile containingFile = elements[0].getContainingFile();
        final FileType fileType = containingFile.getFileType();
        if(fileType.getName().equals("Groovy")) {
            GroovyFormatterUtil.cleanUpElements(project, containingFile, elements);
        } else {
            JavaFormatterUtil.cleanUpElements(project, containingFile, elements);
        }
    }
}
