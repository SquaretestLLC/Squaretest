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
package com.squaretest.confirmsettings.v1;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.BrowserHyperlinkListener;
import com.squaretest.settings.UiUtils;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import javax.swing.*;

public class TemplateUpdateRequiredDialog extends DialogWrapper {

    private final String templateName;
    private JPanel rootPanel;
    private JTextPane txtUpdateMessage;

    public TemplateUpdateRequiredDialog(final Project project, final String templateName) {
        super(project);
        this.templateName = templateName;
        init();
        setTitle("Template Upgrade Required");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        UiUtils.provisionHtmlTextPane(txtUpdateMessage);
        final String updateMessage = readTextFromResource("/TemplateUpgradeRequired.html").replace("{{TemplateName}}", templateName);
        txtUpdateMessage.setText(updateMessage);
        txtUpdateMessage.addHyperlinkListener(new BrowserHyperlinkListener());
        return rootPanel;
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        // Override this to remove the Cancel button.
        return new Action[]{getOKAction()};
    }

    private String readTextFromResource(@NotNull final String resourceName) {
        try {
            return IOUtils.toString(getClass().getResourceAsStream(resourceName), "UTF-8");
        } catch(IOException e) {
            throw new RuntimeException("Missing resource: " + resourceName, e);
        }
    }
}
