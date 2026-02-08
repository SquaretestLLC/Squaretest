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

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.squaretest.confirmsettings.v1.TemplateUpdateRequiredDialog;
import com.squaretest.confirmsettings.v1.TemplateValidator;
import com.squaretest.di.NewTestHandlerComponent;
import com.squaretest.generation.UnitTestGenerator;
import com.squaretest.generation.errorui.CreatePackageErrorDialog;
import com.squaretest.generation.errorui.RenderingFailedDialog;
import com.squaretest.generation.filetemplateutil.CannotCreatePackageException;
import com.squaretest.generation.filetemplateutil.DirectoryNotWritableException;
import com.squaretest.generation.filetemplateutil.FileAlreadyExistsException;
import com.squaretest.generation.filetemplateutil.TemplateRenderingException;
import com.squaretest.generation.runconfig.ConfigInfo;
import com.squaretest.generation.runconfig.ConfigurationObtainer;
import com.squaretest.generation.runconfig.DetermineConfigResult;
import com.squaretest.notifications.NotificationDisplayer;
import com.squaretest.template.api.Api;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NewTestHandlerAskToConfirmMocks extends BaseNewTestHandler {

    private NewTestHandlerComponent newTestHandlerComponent;
    private final NewTestHandlerComponent.Factory dependencyFactory;

    public NewTestHandlerAskToConfirmMocks(
            final NewTestHandlerComponent.Factory factory) {
        this.dependencyFactory = factory;
    }

    @Override
    public void invoke(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile psiFile) {

        // Construct the dependency graph.
        // Also, the psiFile must be a PsiJavaFile; the NewTestGeneratorAction will not run (invoke the handler) if it is not.
        newTestHandlerComponent = dependencyFactory.createComponent(project, (PsiJavaFile) psiFile, getEditorWindow());

        // Show an error notification and return if IntelliJ is updating indices.
        final DumbService dumbService = newTestHandlerComponent.getDumbService();
        if(dumbService.isDumb()) {
            dumbService.showDumbModeNotification("Squaretest 'Generate Test' action is not available while IDEA is updating indices");
            return;
        }

        // Store references to fields needed later.
        final NotificationDisplayer notificationDisplayer = newTestHandlerComponent.getNotificationDisplayer();
        final EditorOpener editorOpener = newTestHandlerComponent.getEditorOpener();

        final Module module = newTestHandlerComponent.getSourceFileModule();
        if(module == null) {
            // This happens when you open a java file outside of the current IntelliJ project (via file->open), and then
            // try to generate a test for it.
            notificationDisplayer.showModuleNotDeterminedNotification();
            return;
        }

        // Determine config info to use to generate the test.
        final ConfigurationObtainer configurationObtainer = newTestHandlerComponent.getConfigurationObtainer();
        final DetermineConfigResult determineConfigResult = configurationObtainer.determineConfig(project, module, psiFile);
        if(!determineConfigResult.hasValidConfig()) {
            // info is null if the customer canceled out of the just-in-time config dialog prompt.
            return;
        }

        // Determine if the template has code necessary to show the dialog and confirm which variables should be mocked.
        // If the template does not, meaning the user created it before this feature was built, show a dialog explaining
        // that he/she needs to add the code to the template.
        final TemplateValidator templateValidator = newTestHandlerComponent.getConfirmSettingsTemplateValidator();
        if(!templateValidator.templateContainsCodeToConfirmMocks(determineConfigResult.configInfo().template())) {
            showTemplateUpdateRequiredDialog(determineConfigResult.configInfo());
            return;
        }

        // Construct the generator.
        final UnitTestGenerator generator = newTestHandlerComponent.createUnitTestGenerator(determineConfigResult.configInfo(), true);

        // Run the template without the shouldAskUserToConfirmSettings flag to see if the test class already exists.
        // If it does, open it and return.
        VirtualFile existingTestClass = generator.runTemplateAndReturnExistingTestClass();
        if(existingTestClass != null) {
            editorOpener.openEditor(existingTestClass);
            return;
        }

        // Generate the test.
        try {
            final VirtualFile generatedFile = generator.generate();
            editorOpener.openEditor(generatedFile);
        } catch(final FileAlreadyExistsException e) {
            editorOpener.openEditor(e.getFile());
        } catch(final DirectoryNotWritableException e) {
            notificationDisplayer.showDirectoryNotWritable(e.getFile());
        } catch(final TemplateRenderingException e) {
            showRenderingFailedDialog(e);
        } catch(final CannotCreatePackageException e) {
            showCannotCreatePackageDialog(e);
        } catch(final Api.UserCancelledGenerationException ignored) {
        }
    }

    private void showTemplateUpdateRequiredDialog(final ConfigInfo configInfo) {
        final TemplateUpdateRequiredDialog dialog = newTestHandlerComponent.createTemplateUpdateRequiredDialog(configInfo);
        dialog.showAndGet();
    }

    private void showRenderingFailedDialog(final TemplateRenderingException templateRenderingException) {
        final RenderingFailedDialog dialog = newTestHandlerComponent.createRenderingFailedDialog(templateRenderingException);
        dialog.showAndGet();
    }

    private void showCannotCreatePackageDialog(final CannotCreatePackageException cannotCreatePackageException) {
        final CreatePackageErrorDialog createPackageErrorDialog = newTestHandlerComponent.createPackageErrorDialog(cannotCreatePackageException);
        createPackageErrorDialog.showAndGet();
    }

    @Nullable
    public PsiElement getElementToMakeWritable(@NotNull final PsiFile currentFile) {
        return null;
    }

    @Override
    public boolean startInWriteAction() {
        return false;
    }

}
