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
package com.squaretest.generation.filetemplateutil;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class DirectoryChecker {

    /**
     * Returns true if we can create the given file in the given directory.
     *
     * @param directory the directory in which to create the file.
     * @param fileName the fileName.
     * @throws FileAlreadyExistsException if a file with the same name or a similar name (+/- 's' or 'S') exists.
     * @throws DirectoryNotWritableException if {@link PsiDirectory#isWritable()} returns false.
     */
    public void checkCanCreateFile(final PsiDirectory directory, final String fileName) throws FileAlreadyExistsException, DirectoryNotWritableException {
        if (!directory.isWritable()) {
            throw new DirectoryNotWritableException(directory.getVirtualFile());
        }
        VirtualFile existingFile = directory.getVirtualFile().findChild(fileName);
        if (existingFile != null) {
            throw new FileAlreadyExistsException(existingFile);
        }

        final String similarFileName = addOrRemoveS(fileName);
        existingFile = directory.getVirtualFile().findChild(similarFileName);
        if (existingFile != null ) {
            throw new FileAlreadyExistsException(existingFile);
        }

    }
    private static String addOrRemoveS(final String fileName) {

        final String fileNameWithoutExtension  = FilenameUtils.removeExtension(fileName);
        final String extension = FilenameUtils.getExtension(fileName);

        String similarFileNameWithoutExtension;
        if (StringUtils.endsWithIgnoreCase(fileNameWithoutExtension, "s")) {
            similarFileNameWithoutExtension = fileNameWithoutExtension.substring(0, fileNameWithoutExtension.length() - 1);
        } else {
            similarFileNameWithoutExtension =  fileNameWithoutExtension + "s";
        }

        if (StringUtils.isEmpty(extension)) {
            return similarFileNameWithoutExtension;
        } else {
            return similarFileNameWithoutExtension + "." + extension;
        }
    }
}
