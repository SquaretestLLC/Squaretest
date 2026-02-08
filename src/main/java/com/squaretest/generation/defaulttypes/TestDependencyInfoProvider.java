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
package com.squaretest.generation.defaulttypes;

import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.module.LanguageLevelUtil;
import com.intellij.openapi.module.Module;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides info about classes on the test classpath.
 */
public class TestDependencyInfoProvider {

    @NotNull
    private final JavaPsiFacade javaPsiFacade;
    @NotNull
    private final Module moduleContainingTheTestDirectory;
    @Nullable
    private LanguageLevel languageLevel;
    private final Map<String, Boolean> classNameToIsPresentCache;

    /**
     * Constructs the {@link TestDependencyInfoProvider}.
     *
     * @param javaPsiFacade                    the java PSI Facade (constructed for the project containing the class for which to generate the test).
     * @param moduleContainingTheTestDirectory the module containing the Test Sources Root. This is not necessarily the same module as the module for which the test is being generated.
     *                                         The user may have a separate module just for tests.
     */
    public TestDependencyInfoProvider(
            @NotNull final JavaPsiFacade javaPsiFacade,
            @NotNull final Module moduleContainingTheTestDirectory) {
        this.javaPsiFacade = javaPsiFacade;
        this.moduleContainingTheTestDirectory = moduleContainingTheTestDirectory;
        this.classNameToIsPresentCache = new HashMap<>();
    }

    /**
     * Returns whether the canonicalName is on the module's classpath.
     *
     * @param canonicalName the canonicalName to search for
     * @return boolean
     */
    public boolean testPathContainsClass(@NotNull final String canonicalName) {
        return this.classNameToIsPresentCache.computeIfAbsent(canonicalName, key -> {
            final GlobalSearchScope testSearchScope = this.moduleContainingTheTestDirectory.getModuleWithDependenciesAndLibrariesScope(true);
            final PsiClass psiClass = javaPsiFacade.findClass(key, testSearchScope);
            return psiClass != null;
        });
    }

    public PsiClass getPsiClass(@NotNull final String canonicalName) {
        final GlobalSearchScope testSearchScope = this.moduleContainingTheTestDirectory.getModuleWithDependenciesAndLibrariesScope(true);
        return javaPsiFacade.findClass(canonicalName, testSearchScope);
    }

    public boolean testPathIsAtLeastJava9() {
        if(languageLevel == null) {
            languageLevel = determineLanguageLevel();
        }
        return languageLevel.isAtLeast(LanguageLevel.JDK_1_9);
    }

    public boolean testPathIsAtLeastJava11() {
        if(languageLevel == null) {
            languageLevel = determineLanguageLevel();
        }
        return languageLevel.isAtLeast(LanguageLevel.JDK_11);
    }

    private LanguageLevel determineLanguageLevel() {
        return ReadAction.compute(() ->
                LanguageLevelUtil.getEffectiveLanguageLevel(moduleContainingTheTestDirectory));
    }
}
