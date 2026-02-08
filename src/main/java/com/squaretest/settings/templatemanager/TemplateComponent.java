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

import com.intellij.openapi.ui.Messages;
import com.squaretest.Icons;
import com.squaretest.TemplateNameInvalidException;
import com.squaretest.TemplateProvider;
import com.squaretest.settings.TemplateMessages;
import com.squaretest.settings.store.ModuleSettings;
import com.squaretest.settings.store.ProjectSettings;
import com.squaretest.settings.store.SettingsProvider;
import com.squaretest.settings.store.Template;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TemplateComponent {
    private JPanel rootPanel;
    private JTextField txtTemplateName;
    private JButton btnUpdateName;
    private JButton btnDelete;
    private JLabel lblUsedIn;
    private JPanel usedInListPanel;

    @NotNull
    private final TemplateProvider templateProvider;
    @NotNull
    private final SettingsProvider settingsProvider;
    @NotNull
    private final TemplateRemovedListener templateRemovedListener;
    @NotNull
    private Template template;

    public TemplateComponent(
            @NotNull final TemplateProvider templateProvider,
            @NotNull final SettingsProvider settingsProvider,
            @NotNull final TemplateRemovedListener templateRemovedListener,
            @NotNull final Template template) {
        this.templateProvider = templateProvider;
        this.settingsProvider = settingsProvider;
        this.templateRemovedListener = templateRemovedListener;
        this.template = template;
    }

    private void createUIComponents() {
        usedInListPanel = new JPanel();
        usedInListPanel.setLayout(new BoxLayout(this.usedInListPanel, BoxLayout.Y_AXIS));
    }

    public JPanel getRootPanel() {
        btnDelete.setIcon(Icons.DeleteIcon);
        txtTemplateName.setText(template.getPresentationName());

        // Configure the update-name button.
        btnUpdateName.setEnabled(false);
        btnUpdateName.addActionListener(e -> {
            final String templateName = txtTemplateName.getText();
            if(validateNewTemplateNameAndShowErrorIfNeeded(templateName)) {
                // Rename the template.
                final Template newTemplate = templateProvider.renameTemplate(template, templateName);
                // Update all project and module settings that refer to the template.
                settingsProvider.renameTemplateInAllSettings(template, newTemplate);
                // Update our reference to point to the new template.
                template = newTemplate;
                // Disable the button.
                txtTemplateName.setText(template.getPresentationName());
                btnUpdateName.setEnabled(false);
            }
        });

        // Enable/Disable the update-name button based on the contents of the text input.
        txtTemplateName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(final DocumentEvent e) {
                onTemplateNameTextChanged();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                onTemplateNameTextChanged();
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
                onTemplateNameTextChanged();
            }
        });

        btnDelete.addActionListener(e -> {
            final int okOrCancelResultCode = Messages.showOkCancelDialog(
                    btnDelete,
                    String.format("Are you sure you want to delete %s?\nThis will delete the template from the file system.\nThis operation cannot be undone.", template.getPresentationName()),
                    "Confirm Delete",
                    "OK",
                    "Cancel",
                    Icons.Warning);
            if(okOrCancelResultCode == Messages.OK) {
                settingsProvider.deleteTemplateFromAllSettings(template);
                templateProvider.deleteTemplate(template);
                templateRemovedListener.onTemplateRemoved(this);
            }
        });

        // Set the template used-in list.
        setUsedInList();

        return rootPanel;
    }

    private void setUsedInList() {
        final List<JLabel> usageLabels = new ArrayList<>();
        final List<ProjectSettings> projectSettingsUsingTemplate = settingsProvider.getProjectSettingsUsingTemplate(template);
        final Map<String, List<ModuleSettings>> projectNameToModuleSettingsUsingTemplate = settingsProvider.getModuleSettingsUsingTemplate(template);
        for(final ProjectSettings projectSettings : projectSettingsUsingTemplate) {
            final String projectName = projectSettings.getProjectName();
            usageLabels.add(new JLabel(String.format("    %s", projectName)));
            final List<ModuleSettings> moduleSettingsForProject = projectNameToModuleSettingsUsingTemplate.get(projectName);
            if(moduleSettingsForProject != null) {
                for(final ModuleSettings moduleSettings : moduleSettingsForProject) {
                    usageLabels.add(new JLabel(String.format("    %s/%s", projectName, moduleSettings.getName())));
                }
                projectNameToModuleSettingsUsingTemplate.remove(projectName);
            }
        }

        // Add labels for the remaining modules.
        for(final Map.Entry<String, List<ModuleSettings>> projectNameToModuleSettingsEntry : projectNameToModuleSettingsUsingTemplate.entrySet()) {
            for(final ModuleSettings moduleSettings : projectNameToModuleSettingsEntry.getValue()) {
                usageLabels.add(new JLabel(String.format("    %s/%s", projectNameToModuleSettingsEntry.getKey(), moduleSettings.getName())));
            }
        }
        if(!usageLabels.isEmpty()) {
            lblUsedIn.setText("  Used In:");
            for(final JLabel label : usageLabels) {
                usedInListPanel.add(label);
            }
        } else {
            lblUsedIn.setText("  Unused");
        }
    }

    private void onTemplateNameTextChanged() {
        final String templateNameText = txtTemplateName.getText();
        if(!Objects.equals(template.getPresentationName(), templateNameText)) {
            btnUpdateName.setEnabled(true);
        } else {
            btnUpdateName.setEnabled(false);
        }
    }

    private boolean validateNewTemplateNameAndShowErrorIfNeeded(final String templateName) {
        try {
            templateProvider.validateUserTemplateName(template.getTemplateLanguage(), templateName);
        } catch(TemplateNameInvalidException e) {
            showErrorDialog(e.getUserErrorMessage());
            return false;
        }
        if(templateProvider.userTemplateExists(template.getTemplateLanguage(), templateName)) {
            showErrorDialog("A template with this name already exists.");
            return false;
        }
        return true;
    }

    private void showErrorDialog(final String message) {
        Messages.showErrorDialog(message, TemplateMessages.UnableToRenameTemplateMessageTitle);
    }
}
