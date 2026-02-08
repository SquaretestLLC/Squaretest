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
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiParserFacade;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.squaretest.generation.CreateTestFileChecker;
import com.squaretest.generation.cleanup.JavaFormatterUtil;
import com.squaretest.generation.cleanup.RedundantThrowsRemover;
import com.squaretest.generation.filetemplateutil.CannotCreatePackageException;
import com.squaretest.generation.filetemplateutil.DirectoryNotWritableException;
import com.squaretest.generation.filetemplateutil.FileAlreadyExistsException;
import com.squaretest.generation.filetemplateutil.SQFileTemplateUtil;
import com.squaretest.generation.filetemplateutil.TemplateRenderingException;
import com.squaretest.template.api.Api;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Map;

public class JavaFileCreator implements FileCreator {

    private static final Logger Log = Logger.getInstance(JavaFileCreator.class);

    @NotNull
    private final CreateTestFileChecker createTestFileChecker;

    public JavaFileCreator(@NotNull final CreateTestFileChecker createTestFileChecker) {
        this.createTestFileChecker = createTestFileChecker;
    }

    @NotNull
    @Override
    public VirtualFile createTestClass(@NotNull final Project project, @NotNull final FileTemplate template, @NotNull final PsiDirectory testClassDirectory, @NotNull final Map<String, Object> templateVars) throws FileAlreadyExistsException, DirectoryNotWritableException, TemplateRenderingException, CannotCreatePackageException, Api.UserCancelledGenerationException {
        // Invoke the Velocity engine.
        final PsiElement generatedElement = SQFileTemplateUtil.createFromTemplate(template, templateVars, testClassDirectory, createTestFileChecker);
        final PsiClass generatedClass = (PsiClass) generatedElement;

        // Clean up the generated code.
        final JavaCodeStyleManager codeStyleManager = JavaCodeStyleManager.getInstance(project);
        ApplicationManager.getApplication().runWriteAction(() -> {
            final PsiFile generatedFile = generatedClass.getContainingFile();
            Log.debug("Start optimize imports: " + LocalDateTime.now());
            codeStyleManager.optimizeImports(generatedFile);
            Log.debug("Finish optimize imports: " + LocalDateTime.now());

            // Invoke the reformatter.
            JavaFormatterUtil.cleanUpElements(project, generatedFile, new PsiElement[]{generatedFile});
            Log.debug("Start remove extra newlines: " + LocalDateTime.now());
            removeExtraNewlinesAtEndOfFile(project, generatedFile);
            Log.debug("Finish remove extra newlines: " + LocalDateTime.now());
            Log.debug("Start redundant throws remover: " + LocalDateTime.now());
            final RedundantThrowsRemover redundantThrowsRemover = new RedundantThrowsRemover();
            redundantThrowsRemover.removeRedundantThrowsFromClass(((PsiJavaFile) generatedFile).getClasses()[0]);
            Log.debug("Finish redundant throws remover: " + LocalDateTime.now());
        });

        final PsiFile file = generatedClass.getContainingFile();
        return file.getVirtualFile();
    }

    static void removeExtraNewlinesAtEndOfFile(
            final Project project, final PsiFile generatedFile) {
        // Remove all but 1 trailing newline at the end of the file.
        PsiElement lastChild = generatedFile.getLastChild();
        final String lastChildText = lastChild.getText();
        if(lastChild instanceof PsiWhiteSpace || StringUtils.isBlank(lastChildText)) {
            // PsiJavaFiles have a PsiWhiteSpace element at the end with text that looks like "\n\n\n";
            // Groovy files have a plain PsiElement with text that looks "\n\n\n"; we need to check for both.
            final PsiElement oneNewline = PsiParserFacade.getInstance(project).createWhiteSpaceFromText("\n");
            lastChild.replace(oneNewline);
        }
    }
}
