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
package com.squaretest.multipletests;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.squaretest.settings.UiUtils;
import com.squaretest.utils.SQIOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ErrorDialog extends DialogWrapper {

    private static final String SourceClassTemplatePlaceholder = "{{source_class}}";
    private final String resourceId;
    private final String sourceClassName;

    private JPanel rootPanel;
    private JTextPane textErrorMessage;

    public ErrorDialog(
            @Nullable final Project project, final String title, @NotNull final String resourceId, @Nullable final String sourceClassName) {
        super(project);
        this.sourceClassName = sourceClassName;
        this.resourceId = resourceId;
        init();
        setTitle(title);
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
        String errorMessage = SQIOUtils.safeLoadResource(resourceId);
        if(errorMessage.contains(SourceClassTemplatePlaceholder)) {
            errorMessage = errorMessage.replace(SourceClassTemplatePlaceholder, sourceClassName == null ? "null" : sourceClassName);
        }
        UiUtils.provisionHtmlTextPane(textErrorMessage);
        textErrorMessage.setText(errorMessage);
        return rootPanel;
    }
}
