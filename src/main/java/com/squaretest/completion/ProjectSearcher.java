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

import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.GlobalSearchScopesCore;
import com.intellij.psi.search.PsiShortNamesCache;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProjectSearcher {
    @NotNull
    private final JavaPsiFacade javaPsiFacade;
    @NotNull
    private final GlobalSearchScope projectProductionSearchScope;
    @NotNull
    private final PsiShortNamesCache psiShortNamesCache;

    public ProjectSearcher(
            @NotNull final JavaPsiFacade javaPsiFacade,
            @NotNull final PsiShortNamesCache psiShortNamesCache,
            @NotNull final Project project) {
        this.javaPsiFacade = javaPsiFacade;
        this.projectProductionSearchScope = GlobalSearchScopesCore.projectProductionScope(project);
        this.psiShortNamesCache = psiShortNamesCache;
    }

    @Nullable
    public PsiClass findClassWithCanonicalName(final String qualifiedName) {
        return this.javaPsiFacade.findClass(qualifiedName, this.projectProductionSearchScope);
    }

    @Nullable
    public PsiClass findClassWithShortName(final String shortName) {
        final PsiClass[] shortNames = psiShortNamesCache.getClassesByName(shortName, this.projectProductionSearchScope);
        if(shortNames.length > 0) {
            return shortNames[0];
        } else {
            return null;
        }
    }
}
