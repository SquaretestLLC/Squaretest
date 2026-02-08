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

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.util.IncorrectOperationException;
import com.squaretest.generation.CreateTestFileChecker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

/**
 * This class is based on {@link com.intellij.ide.fileTemplates.JavaCreateFromTemplateHandler}.
 */
public class SQJavaFileCreator implements SQFileCreator {

    private static final Logger Log = Logger.getInstance(SQJavaFileCreator.class);

    private final CreateTestFileChecker createTestFileChecker;
    private static final String JavaExtension = "java";
    private static final String TempFileName = "myClass" + "." + JavaExtension;

    SQJavaFileCreator(final CreateTestFileChecker createTestFileChecker) {
        this.createTestFileChecker = createTestFileChecker;
    }

    private PsiClass createClassOrInterface(Project project, PsiDirectory testSourcesRoot, String content)
            throws FileAlreadyExistsException, DirectoryNotWritableException, CannotCreatePackageException {
        final PsiFile psiFile = PsiFileFactory.getInstance(project).createFileFromText(TempFileName, JavaFileType.INSTANCE, content);
        if(!(psiFile instanceof final PsiJavaFile psiJavaFile)) {
            throw new IncorrectOperationException("This template did not produce a Java class or an interface\n" + psiFile.getText());
        }
        if(psiJavaFile.getClasses().length == 0) {
            throw new IncorrectOperationException("This template did not produce a Java class or an interface\n" + psiFile.getText());
        }
        PsiJavaFile renamedPsiJavaFile = (PsiJavaFile) psiJavaFile.setName(psiJavaFile.getClasses()[0].getName() + "." + JavaExtension);
        final PsiClass createdClass = renamedPsiJavaFile.getClasses()[0];
        String className = createdClass.getName();
        // If there is an existing test with the same or similar name, don't create the test (or package folders).
        // This will throw if a test with the same or similar name exists.
        createTestFileChecker.checkForExistingTest(createdClass);

        // Invoke shorten class refs on the in-memory Java file. This API call takes a long time, but is faster when
        // invoked on the in-memory file as opposed to the physical file.
        final JavaCodeStyleManager codeStyleManager = JavaCodeStyleManager.getInstance(project);
        Log.debug("Start shorten class refs: " + LocalDateTime.now());
        codeStyleManager.shortenClassReferences(renamedPsiJavaFile);
        Log.debug("Finish shorten class refs: " + LocalDateTime.now());

        // Create package folders matching the structure of the test class's package if they don't already exist.
        final PsiDirectory testClassDirectory = PackageDirectoryCreator.createDirectory(project, psiJavaFile, testSourcesRoot);

        // Confirm the directory we will create the test file in is writable.
        createTestFileChecker.checkCanCreateTestFileInDirectory(testClassDirectory, className + ".java");

        final PsiElement addedElement = testClassDirectory.add(renamedPsiJavaFile);
        if(addedElement instanceof PsiJavaFile) {
            renamedPsiJavaFile = (PsiJavaFile) addedElement;
            return renamedPsiJavaFile.getClasses()[0];
        } else {
            PsiFile containingFile = addedElement.getContainingFile();
            throw new IncorrectOperationException("Selected class file name '" +
                    containingFile.getName() + "' mapped to not java file type '" +
                    containingFile.getFileType().getDescription() + "'");
        }
    }

    @NotNull
    @Override
    public PsiClass createInMemoryFile(
            Project project, String generatedFileText) {
        final PsiFile psiFile = PsiFileFactory.getInstance(project).createFileFromText(TempFileName, JavaFileType.INSTANCE, generatedFileText, 0, false);
        if(!(psiFile instanceof final PsiJavaFile psiJavaFile)) {
            throw new IncorrectOperationException("This template did not produce a Java class or an interface\n" + psiFile.getText());
        }
        if(psiJavaFile.getClasses().length == 0) {
            throw new IncorrectOperationException("This template did not produce a Java class or an interface\n" + psiFile.getText());
        }
        PsiJavaFile renamedPsiJavaFile = (PsiJavaFile) psiJavaFile.setName(psiJavaFile.getClasses()[0].getName() + "." + JavaExtension);
        return renamedPsiJavaFile.getClasses()[0];
    }

    @NotNull
    @Override
    public PsiElement saveGeneratedFile(final Project project, final PsiDirectory directory, String generatedFileText) throws FileAlreadyExistsException, DirectoryNotWritableException, CannotCreatePackageException {
        return createClassOrInterface(project, directory, generatedFileText);
    }

    @Override
    @Nullable
    public VirtualFile returnExistingTestClassIfExists(
            final Project project, final PsiDirectory directory, String generatedFileText) {
        final PsiFile psiFile = PsiFileFactory.getInstance(project).createFileFromText(TempFileName, JavaFileType.INSTANCE, generatedFileText, 0, false);
        if(!(psiFile instanceof final PsiJavaFile psiJavaFile)) {
            throw new IncorrectOperationException("This template did not produce a Java class or an interface\n" + psiFile.getText());
        }
        if(psiJavaFile.getClasses().length == 0) {
            throw new IncorrectOperationException("This template did not produce a Java class or an interface\n" + psiFile.getText());
        }
        PsiJavaFile renamedPsiJavaFile = (PsiJavaFile) psiJavaFile.setName(psiJavaFile.getClasses()[0].getName() + "." + JavaExtension);
        final PsiClass createdClass = renamedPsiJavaFile.getClasses()[0];

        // If there is an existing-test with the same or similar name, don't create the test (or package-folders).
        // This will throw if a test with the same or similar name exists.
        return createTestFileChecker.getExistingTestIfExists(createdClass);
    }
}
