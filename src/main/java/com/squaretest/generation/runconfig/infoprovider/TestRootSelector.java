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
package com.squaretest.generation.runconfig.infoprovider;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.squaretest.ModuleTestSourcesRootFinder;
import com.squaretest.SuggestedRootInfo;
import com.squaretest.settings.store.TemplateLanguage;
import com.squaretest.utils.PathUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestRootSelector {

    @Nullable
    public TestRootInfo suggestTestRootForModule(@NotNull final Module module, @Nullable final TemplateLanguage projectTemplateLanguage) {
        final VirtualFile contentRoot = getOnlyContentRoot(module);
        final SuggestedRootInfo suggestedRootInfo = ModuleTestSourcesRootFinder.suggestTestRootsForModule(module);
        List<VirtualFile> currentWorkingSet = suggestedRootInfo.testRoots();
        if(suggestedRootInfo.includesDependentModules()) {
            // If we have roots under the module, select those.
            if(contentRoot != null) {
                final String contentRootPath = contentRoot.getPath();
                final List<VirtualFile> rootsUnderTheModule = currentWorkingSet.stream().filter(x -> x.getPath().startsWith(contentRootPath)).collect(Collectors.toList());
                if(!rootsUnderTheModule.isEmpty()) {
                    currentWorkingSet = rootsUnderTheModule;
                }
            }
        }

        // Filter out integration tests.
        currentWorkingSet = PathUtils.removeDuplicates(PathUtils.filterOutIntegrationTestRoots(currentWorkingSet));

        if(projectTemplateLanguage == TemplateLanguage.GROOVY) {
            // If the project language is Groovy, check for Groovy test source roots first.
            List<VirtualFile> confirmedRoots = PathUtils.findConfirmedGroovyRoots(currentWorkingSet);
            if(!confirmedRoots.isEmpty()) {
                final VirtualFile testSourcesRootDir = selectPreferredTestRoot(confirmedRoots);
                return new TestRootInfo(TemplateLanguage.GROOVY, testSourcesRootDir);
            }
            confirmedRoots = PathUtils.findConfirmedJavaRoots(currentWorkingSet);
            if(!confirmedRoots.isEmpty()) {
                final VirtualFile testSourcesRootDir = selectPreferredTestRoot(confirmedRoots);
                return new TestRootInfo(TemplateLanguage.JAVA, testSourcesRootDir);
            }
        } else {
            // Otherwise, check for Java test source roots first.
            List<VirtualFile> confirmedRoots = PathUtils.findConfirmedJavaRoots(currentWorkingSet);
            if(!confirmedRoots.isEmpty()) {
                final VirtualFile testSourcesRootDir = selectPreferredTestRoot(confirmedRoots);
                return new TestRootInfo(TemplateLanguage.JAVA, testSourcesRootDir);
            }
            confirmedRoots = PathUtils.findConfirmedGroovyRoots(currentWorkingSet);
            if(!confirmedRoots.isEmpty()) {
                final VirtualFile testSourcesRootDir = selectPreferredTestRoot(confirmedRoots);
                return new TestRootInfo(TemplateLanguage.GROOVY, testSourcesRootDir);
            }
        }

        // None of the roots are named java/, groovy/ or contain .java or .groovy files.
        // If there is a single test root, just return it with null test language.
        // If there are multiple (empty) roots, return null, indicating we cannot recommend a test sources root to use
        // for the default selection and to run the inferencing logic to select a template for the user to use as a
        // starting point.
        if(currentWorkingSet.size() == 1) {
            return new TestRootInfo(null, currentWorkingSet.get(0));
        }

        // There are either no roots or multiple roots that do not contain java or groovy files.
        // The user needs to select the correct one to use.
        return null;
    }

    public static VirtualFile getOnlyContentRoot(@NotNull final Module module) {
        final VirtualFile[] contentRoots = ModuleRootManager.getInstance(module).getContentRoots();
        if(contentRoots.length == 1) {
            return contentRoots[0];
        }
        return null;
    }

    private VirtualFile selectPreferredTestRoot(final List<VirtualFile> javaOrGroovyTestRoots) {
        if(javaOrGroovyTestRoots.isEmpty()) {
            throw new IllegalArgumentException("selectRootWithMostTests cannot be called with an empty list");
        }
        if(javaOrGroovyTestRoots.size() == 1) {
            return javaOrGroovyTestRoots.get(0);
        }
        // Select the path with the standard name if there is one.
        final Optional<VirtualFile> pathWithStandardName = javaOrGroovyTestRoots.stream().filter(x -> StringUtils.endsWithAny(x.getPath(), "test/java", "test/groovy")).findAny();
        if(pathWithStandardName.isPresent()) {
            return pathWithStandardName.get();
        }
        long mostTestsInDirectory = -1;
        VirtualFile directoryWithTheMostTests = javaOrGroovyTestRoots.get(0);
        for(final VirtualFile currentTestRoot : javaOrGroovyTestRoots) {
            final long numberOfTestsInCurrentTestRoot = countTestsInTestRoot(currentTestRoot);
            if(numberOfTestsInCurrentTestRoot > mostTestsInDirectory) {
                mostTestsInDirectory = numberOfTestsInCurrentTestRoot;
                directoryWithTheMostTests = currentTestRoot;
            }
        }

        return directoryWithTheMostTests;
    }

    private long countTestsInTestRoot(final VirtualFile testRoot) {
        final Path testRootPath = Paths.get(testRoot.getPath());
        final BiPredicate<Path, BasicFileAttributes> unitTestMatcher = (path, basicFileAttributes) -> !Files.isDirectory(path);

        try(final Stream<Path> testFilesStream = Files.find(testRootPath, 100, unitTestMatcher)) {
            return testFilesStream.count();
        } catch(final IOException e) {
            return 0L;
        }
    }
}
