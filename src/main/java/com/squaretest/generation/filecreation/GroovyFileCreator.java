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
package com.squaretest.generation.filecreation;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.squaretest.generation.CreateTestFileChecker;
import com.squaretest.generation.cleanup.GroovyFormatterUtil;
import com.squaretest.generation.cleanup.UnnecessarySemicolonRemover;
import com.squaretest.generation.filetemplateutil.CannotCreatePackageException;
import com.squaretest.generation.filetemplateutil.DirectoryNotWritableException;
import com.squaretest.generation.filetemplateutil.FileAlreadyExistsException;
import com.squaretest.generation.filetemplateutil.SQFileTemplateUtil;
import com.squaretest.generation.filetemplateutil.TemplateRenderingException;
import com.squaretest.template.api.Api;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.groovy.lang.psi.GroovyFile;
import org.jetbrains.plugins.groovy.lang.psi.GroovyPsiElement;

import java.util.Map;

import static com.squaretest.generation.filecreation.JavaFileCreator.removeExtraNewlinesAtEndOfFile;

public class GroovyFileCreator implements FileCreator {

    @NotNull
    private final CreateTestFileChecker createTestFileChecker;

    public GroovyFileCreator(@NotNull final CreateTestFileChecker createTestFileChecker) {
        this.createTestFileChecker = createTestFileChecker;
    }

    @NotNull
    @Override
    public VirtualFile createTestClass(@NotNull final Project project, @NotNull final FileTemplate template, @NotNull final PsiDirectory testClassDirectory, @NotNull final Map<String, Object> templateVars) throws FileAlreadyExistsException, DirectoryNotWritableException, TemplateRenderingException, CannotCreatePackageException, Api.UserCancelledGenerationException {
        // Invoke the Velocity engine.
        final PsiElement generatedElement = SQFileTemplateUtil.createFromTemplate(template, templateVars, testClassDirectory, createTestFileChecker);
        final GroovyFile groovyFile = (GroovyFile) generatedElement;

        // Clean up the generated code.
        ApplicationManager.getApplication().runWriteAction(() -> {
            // Invoke the reformatter.
            GroovyFormatterUtil.cleanUpElements1(project, groovyFile, new GroovyPsiElement[]{groovyFile});
            removeExtraNewlinesAtEndOfFile(project, groovyFile);
            final UnnecessarySemicolonRemover unnecessarySemicolonRemover = new UnnecessarySemicolonRemover();
            unnecessarySemicolonRemover.removeRedundantSemicolonsFromFile(groovyFile);
        });
        return groovyFile.getVirtualFile();
    }
}