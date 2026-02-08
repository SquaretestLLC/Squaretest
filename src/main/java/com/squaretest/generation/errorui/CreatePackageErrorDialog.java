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
package com.squaretest.generation.errorui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.squaretest.generation.filetemplateutil.CannotCreatePackageException;
import com.squaretest.metrics.VersionInfoProvider;
import com.squaretest.settings.UiUtils;
import com.squaretest.utils.SQIOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CreatePackageErrorDialog extends DialogWrapper {

    private final CannotCreatePackageException cannotCreatePackageException;
    private final VersionInfoProvider versionInfoProvider;
    private JPanel rootPanel;
    private JTextPane textFailedToCreatePackageTextPane;

    public CreatePackageErrorDialog(
            @NotNull final Project project,
            @NotNull final CannotCreatePackageException cannotCreatePackageException,
            @NotNull final VersionInfoProvider versionInfoProvider) {
        super(project);
        this.cannotCreatePackageException = cannotCreatePackageException;
        this.versionInfoProvider = versionInfoProvider;
        // createUiComponents() is called here by the IntelliJ-injected bytecode.
        init();
        setTitle("Failed to Create Package Directories");
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        // Override this to remove the Cancel button.
        return new Action[]{getOKAction()};
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        initUi();
        return rootPanel;
    }

    private void initUi() {
        final String errorMessageTemplate = SQIOUtils.safeLoadResource("/CreatePackageError.html");
        String errorMessage = errorMessageTemplate.replace("{{package_name}}", cannotCreatePackageException.getPackageName())
                .replace("{{directory_name}}", cannotCreatePackageException.getSourceFolder())
                .replace("{{product_name}}", getIdeName());

        if(versionInfoProvider.isAndroidStudio()) {
            errorMessage = errorMessage.replace("{{extra_solution}}", "");
        } else {
            errorMessage = errorMessage.replace("{{extra_solution}}", "<li><b>Right-click the directory</b> | <b>Mark Directory As</b> | <b>Test Sources Root</b>.</li>");
        }
        UiUtils.provisionHtmlTextPane(textFailedToCreatePackageTextPane);
        textFailedToCreatePackageTextPane.setText(errorMessage);
    }

    private String getIdeName() {
        if(versionInfoProvider.isAndroidStudio()) {
            return "Android Studio";
        }
        return "IntelliJ IDEA";
    }
}
