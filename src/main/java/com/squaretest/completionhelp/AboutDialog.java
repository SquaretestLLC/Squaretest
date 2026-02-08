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
package com.squaretest.completionhelp;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.squaretest.metrics.VersionInfoProvider;
import com.squaretest.utils.SQIOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.ActionEvent;
import javax.swing.*;

public class AboutDialog extends DialogWrapper {
    private static final String VersionFormatString = "<html><b>%s</b></html>";
    private final VersionInfoProvider versionInfoProvider;
    private JPanel rootPanel;
    private JLabel txtSquaretestVersion;

    protected AboutDialog(
            @Nullable final Project project,
            final VersionInfoProvider versionInfoProvider) {
        super(project);
        this.versionInfoProvider = versionInfoProvider;
        // createUiComponents() is called here by the IntelliJ-injected bytecode.
        setTitle("About Squaretest");
        setResizable(false);
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        final String versionString = String.format(VersionFormatString, versionInfoProvider.getPluginVersion());
        txtSquaretestVersion.setText(versionString);
        return rootPanel;
    }

    @Override
    protected void createDefaultActions() {
        super.createDefaultActions();
        myOKAction = new OkAction() {
            {
                putValue(Action.NAME, "Close");
            }

            @Override
            protected void doAction(ActionEvent e) {
                close(OK_EXIT_CODE);
            }
        };
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        return new Action[]{getOKAction()};
    }

}
