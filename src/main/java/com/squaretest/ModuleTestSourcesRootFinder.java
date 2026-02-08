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

import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.roots.JavaProjectRootsUtil;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.SourceFolder;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.testIntegration.createTest.CreateTestAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.JavaSourceRootType;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModuleTestSourcesRootFinder {

    /**
     * Suggests Test Sources Roots for the module.
     *
     * @param module the module for which to suggest Test Sources Roots.
     * @return the suggested Test Sources Roots.
     */
    public static SuggestedRootInfo suggestTestRootsForModule(@NotNull final Module module) {
        try {
            return ReadAction.compute((ThrowableComputable<SuggestedRootInfo, Exception>) () -> computeTestRoots(module));
        } catch(final Exception e) {
            return new SuggestedRootInfo(Collections.emptyList(), false);
        }
    }

    /**
     * This is based on {@link CreateTestAction#computeTestRoots(Module)}.
     * The only reason I'm not calling {@link CreateTestAction#computeTestRoots(Module)} directly is because it
     * has package-local access.
     *
     * @param mainModule the module for which to suggest test-roots.
     * @return the suggested test-roots for the module.
     */
    private static SuggestedRootInfo computeTestRoots(@NotNull Module mainModule) {
        if(!computeSuitableTestRootUrls(mainModule).isEmpty()) {
            return new SuggestedRootInfo(suitableTestSourceFolders(mainModule)
                    .map(SourceFolder::getFile)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList()), false);
        }

        // Find the modules that depend on mainModule and suggest their Test Sources Roots.
        final HashSet<Module> modules = new HashSet<>();
        ModuleUtilCore.collectModulesDependsOn(mainModule, modules);
        return new SuggestedRootInfo(modules.stream()
                .flatMap(ModuleTestSourcesRootFinder::suitableTestSourceFolders)
                .map(SourceFolder::getFile)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()), true);
    }

    private static List<String> computeSuitableTestRootUrls(@NotNull Module module) {
        return suitableTestSourceFolders(module).map(SourceFolder::getUrl).collect(Collectors.toList());
    }

    private static Stream<SourceFolder> suitableTestSourceFolders(@NotNull Module module) {
        Predicate<SourceFolder> forGeneratedSources = JavaProjectRootsUtil::isForGeneratedSources;
        return Arrays.stream(ModuleRootManager.getInstance(module).getContentEntries())
                .flatMap(entry -> entry.getSourceFolders(JavaSourceRootType.TEST_SOURCE).stream())
                .filter(forGeneratedSources.negate());
    }
}
