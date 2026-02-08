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
package com.squaretest.velocityglobalvars;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * The classes in {@link com.squaretest.template.api} need to be available to the reference resolver used in the
 * velocity editor in the IntelliJ Ultimate Edition. This is problematic, because they are not available in any of
 * the classpaths in the user's project.
 * </p>
 * <p>
 * Create an in-memory, file containing the interfaces in {@link com.squaretest.template.api.Api}
 * to allow the reference resolver in the velocity editor to find them.
 * </p>
 */
public class InMemoryFileCreator {

    private static InMemoryFileCreator instance;

    private WeakReference<PsiFile> inMemoryFileReference;

    private InMemoryFileCreator() {
        inMemoryFileReference = null;
    }

    /**
     * This is a singleton so that we only have one in-memory file.
     *
     * @return the instance.
     */
    public static synchronized InMemoryFileCreator getInstance() {
        if(instance == null) {
            instance = new InMemoryFileCreator();
        }
        return instance;
    }

    /**
     * <p>
     * Loads the Api.java resource into an in-memory java file and returns it.
     * This API caches the in-memory file in a {@link WeakReference}.
     * </p>
     * <p>
     * Note: This is synchronized, because in testing I noticed the completion suggestion API is invoked from
     * multiple threads when editing a Velocity template in the Settings menu.
     * </p>
     *
     * @param project the project to use to get the instance of {@link PsiFileFactory}.
     * @return the {@link PsiFile} for Api.java.
     */
    public synchronized PsiFile getOrCreateInMemoryFileForProject(@NotNull final Project project) {
        PsiFile inMemoryFile = getExistingInMemoryFile();
        if(inMemoryFile != null && inMemoryFile.getProject() == project) {
            return inMemoryFile;
        }
        String fileText;
        try {
            fileText = IOUtils.toString(getClass().getResourceAsStream("/templateapi/Api.java"), StandardCharsets.UTF_8);
        } catch(IOException e) {
            throw new RuntimeException("Missing resource: templateapi/Api.java", e);
        }
        inMemoryFile = PsiFileFactory.getInstance(project).createFileFromText(
                "Api.java", JavaFileType.INSTANCE,
                fileText,
                0, false);
        freeInMemoryFileIfNeeded();
        inMemoryFileReference = new WeakReference<>(inMemoryFile);
        return inMemoryFile;
    }

    @Nullable
    private synchronized PsiFile getExistingInMemoryFile() {
        if(inMemoryFileReference != null) {
            return inMemoryFileReference.get();
        }
        return null;
    }

    private synchronized void freeInMemoryFileIfNeeded() {
        if(inMemoryFileReference != null) {
            inMemoryFileReference.clear();
            inMemoryFileReference = null;
        }
    }
}
