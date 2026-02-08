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

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.squaretest.generation.filetemplateutil.DirectoryChecker;
import com.squaretest.generation.filetemplateutil.DirectoryNotWritableException;
import com.squaretest.generation.filetemplateutil.FileAlreadyExistsException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Determines if we can create a given test file and should create the test file based on a number of factors.
 */
public class CreateTestFileChecker {

    @NotNull
    private final ExistingTestFinder existingTestFinder;
    @NotNull
    private final DirectoryChecker directoryChecker;

    public CreateTestFileChecker(
            @NotNull final ExistingTestFinder existingTestFinder,
            @NotNull final DirectoryChecker directoryChecker) {
        this.existingTestFinder = existingTestFinder;
        this.directoryChecker = directoryChecker;
    }

    @Nullable
    public VirtualFile getExistingTestIfExists(final PsiClass testClass) {
        return existingTestFinder.findExistingTest(testClass.getQualifiedName());
    }

    /**
     * Searches the project test sources for a class with the same or similar (+/- 's') canonical name as the testClass.
     *
     * @param testClass the testClass to search for
     * @throws FileAlreadyExistsException if a similar file exists.
     */
    public void checkForExistingTest(final PsiClass testClass) throws FileAlreadyExistsException {
        final String qualifiedName = testClass.getQualifiedName();
        final VirtualFile existingTest = existingTestFinder.findExistingTest(qualifiedName);
        if(existingTest != null) {
            throw new FileAlreadyExistsException(existingTest);
        }
    }

    /**
     * Determines if we can create the given test file in the given directory.
     * This throws {@link FileAlreadyExistsException} if a file already exists.
     */
    public void checkCanCreateTestFileInDirectory(
            final PsiDirectory testClassSaveDirectory,
            final String testClassFileName)
            throws FileAlreadyExistsException, DirectoryNotWritableException {
        directoryChecker.checkCanCreateFile(testClassSaveDirectory, testClassFileName);
    }
}
