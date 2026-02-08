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

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeRegistry;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.util.IncorrectOperationException;
import com.squaretest.generation.CreateTestFileChecker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.plugins.groovy.GroovyFileType;
import org.jetbrains.plugins.groovy.editor.GroovyImportOptimizer;
import org.jetbrains.plugins.groovy.lang.psi.GroovyFile;

import java.util.Properties;

/**
 * Handles {@link com.intellij.ide.fileTemplates.FileTemplateUtil#createFromTemplate(FileTemplate, String, Properties, PsiDirectory)}
 * calls for Squaretest Groovy templates.
 */
public class SQGroovyFileCreator implements SQFileCreator {

    private final CreateTestFileChecker createTestFileChecker;

    public SQGroovyFileCreator(final CreateTestFileChecker createTestFileChecker) {
        this.createTestFileChecker = createTestFileChecker;
    }

    @NotNull
    @Override
    public PsiElement saveGeneratedFile(
            final Project project, final PsiDirectory testSourcesRoot, final String generatedFileText)
            throws IncorrectOperationException, FileAlreadyExistsException, DirectoryNotWritableException, CannotCreatePackageException {

        // This is similar to the code in JavaCreateFromTemplateHandler.
        final String tempFileNameWithExtension = "MyClass.groovy";

        final FileType type = FileTypeRegistry.getInstance().getFileTypeByFileName(tempFileNameWithExtension);
        final PsiFile psiFile = PsiFileFactory.getInstance(project).createFileFromText(tempFileNameWithExtension, type, generatedFileText);

        // Look at the class in the GroovyFile to determine the class name; this assumes the groovy file will only
        // have one top-level class; if there are more than one, just pick the first one.
        // This is similar to JavaCreateFromTemplateHandler.createFromTemplate().
        final GroovyFile groovyFile = (GroovyFile) psiFile;
        final PsiClass[] classes = groovyFile.getClasses();
        if(classes.length == 0) {
            throw new IncorrectOperationException("This template did not produce a Groovy class\n" + psiFile.getText());
        }
        final String createdClassName = classes[0].getName();
        final String createdFileNameWithExtension = createdClassName + ".groovy";
        final GroovyFile renamedGroovyFile = (GroovyFile) groovyFile.setName(createdFileNameWithExtension);

        // If there is an existing-test with the same or similar name, don't create the test (or package-folders).
        // This will throw if a test with the same or similar name exists.
        createTestFileChecker.checkForExistingTest(renamedGroovyFile.getClasses()[0]);

        final JavaCodeStyleManager javaCodeStyleManager = JavaCodeStyleManager.getInstance(project);
        final GroovyImportOptimizer groovyImportOptimizer = new GroovyImportOptimizer();
        javaCodeStyleManager.shortenClassReferences(groovyFile);
        // Organize imports.
        groovyImportOptimizer.processFile(groovyFile).run();
        // Shorten references again. We need to do this, because the Groovy ReferenceAdjuster (what shortenClassReferences
        // API uses) will fail to shorten the last fully qualified name in the file.
        javaCodeStyleManager.shortenClassReferences(groovyFile);

        // Create package-folders matching the structure of the test-class's package if they don't already exist.
        final PsiDirectory testClassDirectory = PackageDirectoryCreator.createDirectory(project, renamedGroovyFile, testSourcesRoot);

        // Confirm the directory we will create the test-file in is writable.
        createTestFileChecker.checkCanCreateTestFileInDirectory(testClassDirectory, createdFileNameWithExtension);

        PsiElement addedElement = testClassDirectory.add(renamedGroovyFile);
        if(addedElement instanceof GroovyFile) {
            return addedElement;
        } else {
            PsiFile containingFile = addedElement.getContainingFile();
            throw new IncorrectOperationException("Selected class file name '" +
                    containingFile.getName() + "' mapped to not groovy file type '" +
                    containingFile.getFileType().getDescription() + "'");
        }
    }

    @NotNull
    @Override
    public PsiClass createInMemoryFile(
            final Project project, final String generatedFileText) {
        // This is similar to the code in JavaCreateFromTemplateHandler.
        final String tempFileNameWithExtension = "MyClass.groovy";

        final PsiFile psiFile = PsiFileFactory.getInstance(project).createFileFromText(tempFileNameWithExtension, GroovyFileType.GROOVY_FILE_TYPE, generatedFileText, 0, false);
        // Look at the class in the GroovyFile to determine the class name; this assumes the groovy file will only
        // have one top-level class; if there are more than one, just pick the first one.
        // This is similar to JavaCreateFromTemplateHandler.createFromTemplate().
        final GroovyFile groovyFile = (GroovyFile) psiFile;
        final PsiClass[] classes = groovyFile.getClasses();
        if(classes.length == 0) {
            throw new IncorrectOperationException("This template did not produce a Groovy class\n" + psiFile.getText());
        }
        final String createdClassName = classes[0].getName();
        final String createdFileNameWithExtension = createdClassName + ".groovy";
        final GroovyFile renamedGroovyFile = (GroovyFile) groovyFile.setName(createdFileNameWithExtension);

        return renamedGroovyFile.getClasses()[0];
    }

    @Override
    @Nullable
    public VirtualFile returnExistingTestClassIfExists(
            final Project project, final PsiDirectory directory, String generatedFileText) {
        // This is similar to the code in JavaCreateFromTemplateHandler.
        final String tempFileNameWithExtension = "MyClass.groovy";

        final FileType type = FileTypeRegistry.getInstance().getFileTypeByFileName(tempFileNameWithExtension);
        final PsiFile psiFile = PsiFileFactory.getInstance(project).createFileFromText(tempFileNameWithExtension, type, generatedFileText, 0, false);
        // Look at the class in the GroovyFile to determine the class name; this assumes the groovy file will only
        // have one top-level class; if there are more than one, just pick the first one.
        // This is similar to JavaCreateFromTemplateHandler.createFromTemplate().
        final GroovyFile groovyFile = (GroovyFile) psiFile;
        final PsiClass[] classes = groovyFile.getClasses();
        if(classes.length == 0) {
            throw new IncorrectOperationException("This template did not produce a Groovy class\n" + psiFile.getText());
        }
        final String createdClassName = classes[0].getName();
        final String createdFileNameWithExtension = createdClassName + ".groovy";
        final GroovyFile renamedGroovyFile = (GroovyFile) groovyFile.setName(createdFileNameWithExtension);

        return createTestFileChecker.getExistingTestIfExists(renamedGroovyFile.getClasses()[0]);
    }

}
