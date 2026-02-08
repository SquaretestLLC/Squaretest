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

import com.intellij.psi.PsiMethod;
import com.squaretest.completion.CompletionUtils;

public class WrappedMethod {
    private final PsiMethod psiMethod;
    private boolean selected;
    private final String displayName;
    private final String sortName;

    public WrappedMethod(final PsiMethod psiMethod) {
        this.psiMethod = psiMethod;
        this.selected = false;
        // Only compute the displayName once. Calling getName() appears to scan the AST to find a NAME element.
        // We need to sort the methods alphabetically by display name. We should avoid making the sorting algorithm
        // scan the AST.
        this.displayName = psiMethod.getName() + "()";
        this.sortName = CompletionUtils.computeSortKey(psiMethod);
    }

    public PsiMethod getPsiMethod() {
        return psiMethod;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSortName() {
        return sortName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(final boolean selected) {
        this.selected = selected;
    }
}
