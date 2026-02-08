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
package com.squaretest.settings.templatemanager;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.squaretest.TemplateProvider;
import com.squaretest.settings.store.SettingsProvider;
import com.squaretest.settings.store.Template;
import com.squaretest.settings.store.TemplateLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;

public class TemplateManagerDialog extends DialogWrapper {
    private JPanel rootPanel;
    private JLabel lblTopLabel;
    private JPanel userTemplatesContainerPanel;
    private JScrollPane scrollPane;
    private final TemplateRemovedListener templateRemovedListener;

    public TemplateManagerDialog(@Nullable final Project project) {
        super(project);
        // Initialize templateRemovedListener before calling init(). init() calls createCenterPanel(), which needs the
        // templateRemovedListener.
        templateRemovedListener = templateComponent -> {
            this.userTemplatesContainerPanel.remove(templateComponent.getRootPanel());
            if(this.userTemplatesContainerPanel.getComponents().length == 0) {
                lblTopLabel.setVisible(true);
            }
            rootPanel.revalidate();
        };
        init();
        setTitle("Template Manager");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        final TemplateProvider templateProvider = new TemplateProvider();
        final SettingsProvider settingsProvider = new SettingsProvider();
        final List<Template> userTemplates = templateProvider.getUserTemplates(TemplateLanguage.JAVA);
        userTemplates.addAll(templateProvider.getUserTemplates(TemplateLanguage.GROOVY));
        for(final Template template : userTemplates) {
            final TemplateComponent templateComponent = new TemplateComponent(
                    templateProvider,
                    settingsProvider,
                    templateRemovedListener,
                    template);
            userTemplatesContainerPanel.add(templateComponent.getRootPanel());
        }
        if(!userTemplates.isEmpty()) {
            lblTopLabel.setVisible(false);
        }
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(25);
        this.scrollPane.setPreferredSize(new Dimension(600, 600));
        return rootPanel;
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        return new Action[]{new CloseAction()};
    }

    private void createUIComponents() {
        this.userTemplatesContainerPanel = new JPanel();
        this.userTemplatesContainerPanel.setLayout(new BoxLayout(this.userTemplatesContainerPanel, BoxLayout.Y_AXIS));
    }

    protected class CloseAction extends DialogWrapperAction {
        CloseAction() {
            super("Close");
            putValue(DEFAULT_ACTION, Boolean.TRUE);
        }

        @Override
        protected void doAction(ActionEvent e) {
            doOKAction();
        }
    }
}
