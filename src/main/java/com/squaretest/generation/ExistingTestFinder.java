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

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.GlobalSearchScopesCore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Searches for an existing test-file with ether the same-name or similar-name to the one the user is trying to create.
 */
public class ExistingTestFinder {
    @NotNull
    private final Project project;

    public ExistingTestFinder(@NotNull final Project project) {
        this.project = project;
    }

    /**
     * Finds the existing test (if there is one) with a name that either matches or is similar to (+/- s) the
     * generatedTestClassName.
     *
     * @param testClassCanonicalName the test class to look for
     * @return the existing test or null if one could not be found.
     */
    @Nullable
    public VirtualFile findExistingTest(final String testClassCanonicalName) {
        // Determine the similar name to search for ( testClassCanonicalName +/- 's' ).
        final String similarCanonicalName = addOrRemoveS(testClassCanonicalName);
        final JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(project);
        final GlobalSearchScope searchScope = GlobalSearchScopesCore.projectTestScope(project);

        PsiClass foundTestClass = javaPsiFacade.findClass(testClassCanonicalName, searchScope);
        if(foundTestClass != null) {
            return foundTestClass.getContainingFile().getVirtualFile();
        }
        foundTestClass = javaPsiFacade.findClass(similarCanonicalName, searchScope);
        if(foundTestClass != null) {
            return foundTestClass.getContainingFile().getVirtualFile();
        }
        return null;
    }

    private static String addOrRemoveS(final String canonicalName) {
        // This should never happen, but you never know.
        if(canonicalName.length() <= 1) {
            return canonicalName;
        }
        if(canonicalName.endsWith("s")) {
            return canonicalName.substring(0, canonicalName.length() - 1);
        } else {
            return canonicalName + "s";
        }
    }
}
