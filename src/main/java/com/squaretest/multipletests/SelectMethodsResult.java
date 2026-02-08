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
package com.squaretest.multipletests;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.psi.PsiMethod;

import java.util.List;

public class SelectMethodsResult {
    final List<PsiMethod> methodsToCreate;
    private final boolean cancelled;
    private final FileTemplate templateUsed;

    public SelectMethodsResult(
            final List<PsiMethod> methodsToCreate, final boolean wasCancelled, final FileTemplate templateUsed) {
        this.methodsToCreate = methodsToCreate;
        this.cancelled = wasCancelled;
        this.templateUsed = templateUsed;
    }

    public boolean wasCancelled() {
        return this.cancelled;
    }

    public List<PsiMethod> getMethodsToCreate() {
        return methodsToCreate;
    }

    public FileTemplate getTemplateUsed() {
        return templateUsed;
    }
}
