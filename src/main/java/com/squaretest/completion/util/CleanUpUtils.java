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
package com.squaretest.completion.util;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.squaretest.generation.cleanup.RedundantThrowsRemover;
import com.squaretest.generation.cleanup.UnnecessarySemicolonRemover;

import java.util.List;

public class CleanUpUtils {
    public static void removeRedundantThrows(final List<PsiElement> elements) {
        final RedundantThrowsRemover redundantThrowsRemover = new RedundantThrowsRemover();
        for(final PsiElement element : elements) {
            if(element instanceof PsiMethod) {
                redundantThrowsRemover.removeRedundantThrowsFromMethod((PsiMethod) element);
            }
        }
    }

    public static void removeSemicolons(List<PsiElement> elements) {
        final UnnecessarySemicolonRemover semicolonRemover = new UnnecessarySemicolonRemover();
        for(final PsiElement element : elements) {
            if(element instanceof PsiMethod) {
                semicolonRemover.removeRedundantSemicolonsFromMethod((PsiMethod) element);
            }
        }
    }
}
