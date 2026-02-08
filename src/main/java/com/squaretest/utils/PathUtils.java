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
package com.squaretest.utils;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.TERMINATE;

public class PathUtils {
    private static final List<String> IntegrationTestDirNames = Arrays.asList(
            "integration", "integrationTests", "integration-test", "testIntegration", "test-integration");
    private static final List<String> IntegrationTestPathNames = Arrays.asList(
            "/integration/", "/testIntegration/", "integrationTests/", "integration-tests/", "test-integration/", "/integ-tests/", "/test-integ/");
    private static final List<String> GroovyTestDirNames = Arrays.asList("groovy", "groovytests");
    private static final List<String> JavaTestDirNames = Collections.singletonList("java");

    private PathUtils() {
    }

    public static String getAbsolutePath(@NotNull final Project project, @NotNull final String relativeOrAbsPath) {
        final Path path = Paths.get(relativeOrAbsPath);
        if(path.isAbsolute()) {
            return relativeOrAbsPath;
        }
        final String projectRootPath = project.getBasePath();
        if(projectRootPath == null) {
            // This shouldn't happen.
            return relativeOrAbsPath;
        }
        return Paths.get(projectRootPath, relativeOrAbsPath).toString();
    }

    public static String getRelativePathFromProjectRootIfInProject(
            @NotNull final Project project, @NotNull final String absolutePath) {
        final String projectBasePath = project.getBasePath();

        // Project.getBasePath() only returns null for the "default" project; this should not happen here.
        if(projectBasePath == null) {
            return absolutePath;
        }

        final Path projectRoot = Paths.get(projectBasePath);
        final Path absPath = Paths.get(absolutePath);
        if(absPath.startsWith(projectRoot)) {
            final Path relPath = projectRoot.relativize(absPath);
            return relPath.toString();
        } else {
            // If the path is outside of the project-root, just return the abs path.
            return absolutePath;
        }
    }

    public static List<String> computeRelativePaths(final List<VirtualFile> virtualFiles, final Project project) {
        final List<String> ret = new ArrayList<>(virtualFiles.size());
        for(final VirtualFile virtualFile : virtualFiles) {
            final String path = virtualFile.getPath();
            final String relPath = PathUtils.getRelativePathFromProjectRootIfInProject(project, path);
            ret.add(relPath);
        }
        return ret;
    }

    public static boolean containsAtLeastOneFileWithExtension(
            @NotNull final String dir, @NotNull final String fileExtension) {
        final AtomicBoolean foundFile = new AtomicBoolean(false);
        final FileVisitor<Path> visitor = new SimpleFileVisitor<>() {

            private final PathMatcher PathMatcher = FileSystems.getDefault().getPathMatcher(String.format("glob:*%s", fileExtension));

            @Override
            public FileVisitResult visitFile(
                    final Path file,
                    @NotNull final BasicFileAttributes attr) {
                if(PathMatcher.matches(file.getFileName())) {
                    foundFile.set(true);
                    return TERMINATE;
                }
                return CONTINUE;
            }
        };

        try {
            Files.walkFileTree(Paths.get(dir), visitor);
        } catch(final IOException e) {
            return false;
        }
        return foundFile.get();
    }

    @NotNull
    public static List<VirtualFile> filterOutIntegrationTestRoots(final List<VirtualFile> potentialModuleTestSourceRoots) {
        final List<VirtualFile> ret = new ArrayList<>();
        for(final VirtualFile file : potentialModuleTestSourceRoots) {
            if(!isIntegrationTestRoot(file)) {
                ret.add(file);
            }
        }
        return ret;
    }

    private static boolean isIntegrationTestRoot(final VirtualFile file) {
        if(containsAnyIgnoreCase(file.getName(), IntegrationTestDirNames)) {
            return true;
        }
        final String path = file.getPath();
        if(containsAnyIgnoreCase(path, IntegrationTestPathNames)) {
            return true;
        }
        return false;

    }

    private static boolean containsAnyIgnoreCase(final String stringToSearch, final List<String> searchStrings) {
        for(final String searchString : searchStrings) {
            if(StringUtils.containsIgnoreCase(stringToSearch, searchString)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns test roots that either contain groovy files or are named "groovy" or "groovytests".
     *
     * @param potentialModuleTestSourceRoots the potential module test sources roots.
     * @return the groovy test sources roots.
     */
    public static List<VirtualFile> findConfirmedGroovyRoots(final List<VirtualFile> potentialModuleTestSourceRoots) {
        final List<VirtualFile> ret = new ArrayList<>();
        for(final VirtualFile file : potentialModuleTestSourceRoots) {
            final String fileNameLowerCase = file.getName().toLowerCase();
            if(GroovyTestDirNames.contains(fileNameLowerCase) || (!JavaTestDirNames.contains(fileNameLowerCase) && dirContainsGroovyTests(file))) {
                ret.add(file);
            }
        }
        return ret;
    }

    private static boolean dirContainsGroovyTests(final VirtualFile directory) {
        return containsAtLeastOneFileWithExtension(directory.getPath(), ".groovy");
    }

    public static List<VirtualFile> findConfirmedJavaRoots(final List<VirtualFile> potentialModuleTestSourceRoots) {
        final List<VirtualFile> ret = new ArrayList<>();
        for(final VirtualFile file : potentialModuleTestSourceRoots) {
            final String fileNameLowerCase = file.getName().toLowerCase();
            if(JavaTestDirNames.contains(fileNameLowerCase) || (!GroovyTestDirNames.contains(fileNameLowerCase) && dirContainsJavaTests(file))) {
                ret.add(file);
            }
        }
        return ret;
    }

    private static boolean dirContainsJavaTests(final VirtualFile directory) {
        return containsAtLeastOneFileWithExtension(directory.getPath(), ".java");
    }

    public static List<VirtualFile> removeDuplicates(final List<VirtualFile> roots) {
        final List<VirtualFile> ret = new ArrayList<>();
        final Set<String> paths = new HashSet<>();
        for(final VirtualFile file : roots) {
            if(paths.add(file.getPath())) {
                ret.add(file);
            }
        }
        return ret;
    }
}
