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
package com.squaretest.generation.generationinfo;

import com.intellij.codeInsight.generation.GenerationInfo;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;

import java.util.Collections;
import java.util.List;

public class GenerationInfoUtils {
    public static List<GenerationInfo> wrap(final List<PsiMethod> methodsToInsert) {
        if(methodsToInsert.isEmpty()) {
            return Collections.emptyList();
        }
        final PsiFile psiFile = methodsToInsert.get(0).getContainingFile();
        final FileType fileType = psiFile.getFileType();
        if(fileType.getName().equals("Groovy")) {
            return GroovyGenerationInfoUtils.wrap(methodsToInsert);
        } else {
            return JavaGenerationInfoUtils.wrap(methodsToInsert);
        }
    }
}
