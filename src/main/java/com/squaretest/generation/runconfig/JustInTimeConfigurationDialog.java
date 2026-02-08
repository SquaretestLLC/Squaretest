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
package com.squaretest.generation.runconfig;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;
import com.squaretest.TemplateProvider;
import com.squaretest.generation.runconfig.infoprovider.JustInTimeUIConfigInfo;
import com.squaretest.generation.runconfig.infoprovider.LanguageAndTemplate;
import com.squaretest.generation.runconfig.infoprovider.TestRootInfo;
import com.squaretest.settings.ModuleConfigComponent;
import com.squaretest.settings.SQHelpIcon;
import com.squaretest.settings.ToolTipLoader;
import com.squaretest.settings.UiUtils;
import com.squaretest.settings.displaywrappers.DisplayModuleAutoConfigSetting;
import com.squaretest.settings.displaywrappers.DisplayTemplate;
import com.squaretest.settings.displaywrappers.DisplayTemplateLanguage;
import com.squaretest.settings.editor.JITEditorProvider;
import com.squaretest.settings.notification.NewTemplateNotifier;
import com.squaretest.settings.store.ModuleAutoConfigSetting;
import com.squaretest.settings.store.ModuleSettings;
import com.squaretest.settings.store.ProjectSettings;
import com.squaretest.settings.store.SettingsProvider;
import com.squaretest.settings.viewdataproviders.TemplateSettingsViewProvider;
import com.squaretest.utils.PathUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import javax.swing.*;

public class JustInTimeConfigurationDialog extends DialogWrapper {

    private SettingsProvider settingsProvider;
    private TemplateProvider templateProvider;
    private TemplateSettingsViewProvider templateSettingsViewProvider;
    private final Module module;
    private final JustInTimeUIConfigInfo justInTimeUIConfigInfo;

    private ModuleConfigComponent moduleConfigComponent;
    private JCheckBox promoteTemplateSettingsToCheckBox;
    private JComboBox<DisplayModuleAutoConfigSetting> selectModuleAutoConfiguration;
    private JPanel rootPanel;
    private JLabel lblModuleConfiguration;
    private SQHelpIcon lblModuleAutoconfigHelp;
    private SQHelpIcon lblPromoteToProjectHelp;
    private JLabel lblNote;

    public JustInTimeConfigurationDialog(
            @NotNull final Module module,
            @NotNull final JustInTimeUIConfigInfo justInTimeUIConfigInfo) {
        super(module.getProject());
        this.module = module;
        this.justInTimeUIConfigInfo = justInTimeUIConfigInfo;
        // createUiComponents() is called here by the IntelliJ-injected bytecode.
        init();
        setTitle(String.format("Configure Squaretest Settings For Module - %s", module.getName()));
        setInitialLocationCallback(new JitInitialLocationCallback());
        this.moduleConfigComponent.initUiComponentsRequiringJPane();
    }

    /**
     * This is called in the code of {@link #init()}, which is called in the constructor, after createUiComponents().
     *
     * @return the center panel.
     */
    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        initUiComponentsRequiringAWTThread();
        return rootPanel;
    }

    private void initUiComponentsRequiringAWTThread() {
        // Set the icon in the dialog to Squaretest icon.
        this.moduleConfigComponent.initUiComponentsRequiringAWTThread();
        this.moduleConfigComponent.removeBorder();
        this.selectModuleAutoConfiguration.addItem(DisplayModuleAutoConfigSetting.fromModuleAutoConfigSetting(ModuleAutoConfigSetting.AUTOMATICALLY_CONFIGURE_WHEN_POSSIBLE));
        this.selectModuleAutoConfiguration.addItem(DisplayModuleAutoConfigSetting.fromModuleAutoConfigSetting(ModuleAutoConfigSetting.ALWAYS_SHOW_CONFIG));
        this.selectModuleAutoConfiguration.setSelectedItem(this.justInTimeUIConfigInfo.getModuleConfigurationOptionSelected());
        this.promoteTemplateSettingsToCheckBox.setSelected(this.justInTimeUIConfigInfo.getDefaultPromoteSettingsToProjectState());
        // Align the left-side-labels in the child forms and their child forms.
        final int longestLeftLabelSize = getLongestLeftLabelSize();
        setMinimumLeftLabelSize(longestLeftLabelSize);
        // Select the inferred test language and template.
        final LanguageAndTemplate inferredLangAndTemplate = justInTimeUIConfigInfo.getInferredLanguageAndTemplate();
        if(inferredLangAndTemplate.templateLanguage() != null) {
            if(inferredLangAndTemplate.template() != null) {
                // We have both the language and the template.
                moduleConfigComponent.selectTemplateLanguage(inferredLangAndTemplate.templateLanguage());
                moduleConfigComponent.selectTemplate(inferredLangAndTemplate.template());
            } else {
                // We have the language, but no template.
                // This occurs when the user has a test sources root, but there are no test frameworks on the
                // root's classpath.
                moduleConfigComponent.selectTemplateLanguage(inferredLangAndTemplate.templateLanguage());
            }
        }
        moduleConfigComponent.addTemplateSelectionChangedListener(templateSelected -> {
            if(!templateSelected) {
                // Call pack when the user select "inherit" or "Not Specified" for the template.
                // This is needed to resize the JIT dialog after the template editor is removed.
                // Use invokeLater(..) to enqueue the pack() call on the AWT thread. If we call pack() now it won't
                // resize the dialog window.
                SwingUtilities.invokeLater(this::pack);
            }
        });
    }

    private void createUIComponents() {
        settingsProvider = new SettingsProvider();
        templateProvider = new TemplateProvider();
        templateSettingsViewProvider = new TemplateSettingsViewProvider(this.settingsProvider);
        final TestRootInfo defaultTestRootInfo = this.justInTimeUIConfigInfo.getDefaultTestSourcesRootToShow();
        final String testSourcesRootToSelectByDefault = defaultTestRootInfo == null ? null : defaultTestRootInfo.getTestSourceDirectoryString();
        moduleConfigComponent = new ModuleConfigComponent(
                templateProvider,
                module.getProject(),
                module,
                templateSettingsViewProvider,
                settingsProvider,
                new NewTemplateNotifier(), new JITEditorProvider(), this.justInTimeUIConfigInfo.getTemplateLanguageDefaultOptionText(),
                this.justInTimeUIConfigInfo.getTemplateDefaultOptionText(),
                this.justInTimeUIConfigInfo.getDefaultTemplateSaveAsName(),
                testSourcesRootToSelectByDefault);
        lblModuleAutoconfigHelp = new SQHelpIcon(ToolTipLoader.getInstance().getModuleAutoConfigToolTip());
        lblPromoteToProjectHelp = new SQHelpIcon(ToolTipLoader.getInstance().getPromoteToProjectToolTip());
        // Set the JCheckBox border to 0. On Mac, JCheckBox has left/right insets=7 by default, and this looks bad.
        promoteTemplateSettingsToCheckBox = new JCheckBox();
        promoteTemplateSettingsToCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }

    private int getLongestLeftLabelSize() {
        final int moduleConfigurationLabelWidth = UiUtils.computeJLabelWidth(lblModuleConfiguration);
        return Math.max(moduleConfigComponent.getLongestLeftLabelWidth(), moduleConfigurationLabelWidth);
    }

    private void setMinimumLeftLabelSize(final int minWidth) {
        moduleConfigComponent.setMinimumLeftLabelWidth(minWidth);
        lblModuleConfiguration.setMinimumSize(new Dimension(minWidth, lblModuleConfiguration.getMinimumSize().height));
        lblModuleConfiguration.setPreferredSize(new Dimension(minWidth, lblModuleConfiguration.getMinimumSize().height));
    }

    @Override
    protected void doOKAction() {

        // This is only called when the dialog is valid (doValidate() returns null).
        // Check to see if the template-text has been modified and click either the 'save' or 'save-as' button if it
        // has. If the user cancels out of the 'Save As' flow via the "Are you sure you want to overwrite existing
        // template" prompt, return.
        if(moduleConfigComponent.isTemplateTextModified()) {
            if(moduleConfigComponent.canClickTemplateTextSave()) {
                moduleConfigComponent.performTemplateTextSave();
            } else {
                final boolean templateSaved = moduleConfigComponent.performTemplateTextSaveAsWithPromptIfNeeded();
                if(!templateSaved) {
                    // If the template was not saved, the user canceled out of the overwrite-existing-template dialog.
                    // Return to leave the JIT dialog open.
                    return;
                }
            }
        }

        // Any unsaved changes to the template-text have been saved.
        final DisplayTemplateLanguage selectedTemplateLang = moduleConfigComponent.getSelectedTemplateLanguage();
        final DisplayTemplate selectedTemplate = moduleConfigComponent.getSelectedTemplate();
        final boolean promoteToProjectSettings = promoteTemplateSettingsToCheckBox.isSelected();
        final String selectedTestSourcesRoot = moduleConfigComponent.getSelectedModuleSourcesRoot();
        final DisplayModuleAutoConfigSetting moduleAutoConfigSetting = (DisplayModuleAutoConfigSetting) selectModuleAutoConfiguration.getSelectedItem();
        final String projectName = module.getProject().getName();
        // If an actual (non-placeholder) template is selected, save the settings to either the project settings
        // or module settings.
        if(selectedTemplate.isActualTemplate()) {
            if(promoteToProjectSettings) {
                // Save selected settings to project-settings and set the module template/lang to null;
                // also set the module-settings test-sources-root to the selected value.
                final ProjectSettings projectSettings = new ProjectSettings(
                        projectName,
                        selectedTemplateLang.getTemplateLanguage(),
                        selectedTemplate.getTemplate(),
                        moduleAutoConfigSetting.getModuleAutoConfigSetting());
                final ModuleSettings moduleSettings = new ModuleSettings(module.getName(), null, null, selectedTestSourcesRoot);
                settingsProvider.putProjectSettings(projectSettings);
                settingsProvider.putModuleSettings(projectName, moduleSettings);
            } else {
                // Save selected settings to module-settings.
                final ModuleSettings moduleSettings = new ModuleSettings(
                        module.getName(),
                        selectedTemplateLang.getTemplateLanguage(),
                        selectedTemplate.getTemplate(),
                        selectedTestSourcesRoot);
                settingsProvider.putModuleSettings(projectName, moduleSettings);
            }
        } else {
            // The selectedTemplate is a placeholder template, meaning that we will inherit the project-settings.
            // Set the module template/lang to null; set the module test-sources-root accordingly.
            // Also, update project-settings module-auto-config setting.
            final ModuleSettings moduleSettings = new ModuleSettings(
                    module.getName(),
                    null,
                    null,
                    selectedTestSourcesRoot);
            settingsProvider.putModuleSettings(projectName, moduleSettings);
            final ProjectSettings existingProjectSettings = settingsProvider.getProjectSettings(projectName);
            if(existingProjectSettings == null) {
                // This should not happen.
                // The user selected a place-holder template and the form let him/her reach this point.
                // If there are no project-settings, he/she should not have been able to select the placeholder
                // template, as the validation would fail.0
                throw new RuntimeException("Unable to retrieve project settings");
            }
            final ProjectSettings newProjectSettings = new ProjectSettings(
                    existingProjectSettings.getProjectName(),
                    existingProjectSettings.getTemplateLanguage(),
                    existingProjectSettings.getTemplate(),
                    moduleAutoConfigSetting.getModuleAutoConfigSetting());
            settingsProvider.putProjectSettings(newProjectSettings);
        }

        // Call this to close the dialog with OK.
        super.doOKAction();
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        // If we need to select a lang+template, check whether or not we actually selected a lang+template.
        if(this.justInTimeUIConfigInfo.isTemplateRequired()) {
            if(!this.moduleConfigComponent.isTemplateLanguageSelected()) {
                final String errorMessage = "Please select a test language and template";
                final JComponent errorComponent = this.moduleConfigComponent.getTemplateLanguageSelectComponent();
                return new ValidationInfo(errorMessage, errorComponent);
            } else if(!this.moduleConfigComponent.isTemplateSelected()) {
                final String errorMessage = "Please select a template";
                final JComponent errorComponent = this.moduleConfigComponent.getTemplateSelectComponent();
                return new ValidationInfo(errorMessage, errorComponent);
            }
        }

        // Validate the selected test sources root.
        // Ensure a test sources root is selected.
        final String selectedTestSourcesRoot = this.moduleConfigComponent.getSelectedTestSourcesRoot();
        if(selectedTestSourcesRoot == null) {
            final String errorMessage = "Please select a test sources root";
            final JComponent errorComponent = this.moduleConfigComponent.getSelectSourcesRootComponent().getComboBox();
            return new ValidationInfo(errorMessage, errorComponent);
        }

        // Ensure the selected test sources root exists on the file system.
        final String testSourcesDirAbsPath = PathUtils.getAbsolutePath(module.getProject(), selectedTestSourcesRoot);
        final VirtualFile testSourcesDirVirtualFile = LocalFileSystem.getInstance().findFileByPath(testSourcesDirAbsPath);
        if(testSourcesDirVirtualFile == null) {
            final String errorMessage = "The selected test sources root does not exist.";
            final JComponent errorComponent = this.moduleConfigComponent.getSelectSourcesRootComponent().getComboBox();
            return new ValidationInfo(errorMessage, errorComponent);
        }

        // Ensure the selected test sources root is a directory and also a sources root (has PSI available).
        final PsiManager psiManager = PsiManager.getInstance(module.getProject());
        final PsiDirectory psiDirectory = psiManager.findDirectory(testSourcesDirVirtualFile);
        if(psiDirectory == null) {
            final String errorMessage = "The selected test sources root is not a recognized test sources root directory.";
            final JComponent errorComponent = this.moduleConfigComponent.getSelectSourcesRootComponent().getComboBox();
            return new ValidationInfo(errorMessage, errorComponent);
        }

        // Ensure the selected test sources root is in an IntelliJ IDEA module.
        final Module testSourcesContainingModule = ModuleUtil.findModuleForFile(testSourcesDirVirtualFile, module.getProject());
        if(testSourcesContainingModule == null) {
            final String errorMessage = "The selected test sources root is not in an IntelliJ IDEA Module.";
            final JComponent errorComponent = this.moduleConfigComponent.getSelectSourcesRootComponent().getComboBox();
            return new ValidationInfo(errorMessage, errorComponent);
        }

        return null;
    }

    @Override
    protected void dispose() {
        this.moduleConfigComponent.disposeUIResources();
        super.dispose();
    }

    /**
     * Centers the dialog window vertically based on its expanded height (when a template is selected) rather than
     * its starting height.
     */
    private class JitInitialLocationCallback implements Computable<Point> {
        @Override
        public Point compute() {
            // This code is essentially the same as that in java.awt.Window#setLocationRelativeTo(c), but it centers
            // the window vertically based on the approximate expanded height (height when the editor is visible)
            // rather than the component's current height.
            // Initialize parameters to pass to the code copied from java.awt.Window#setLocationRelativeTo(c)
            final int approximateExpandedHeight = 815;
            final Window window = getWindow();
            final GraphicsConfiguration gc = window.getGraphicsConfiguration();
            final Rectangle gcBounds = gc.getBounds();
            final Dimension windowSize = getSize();
            int dx = 0, dy = 0;
            final Component c = getOwner();
            Dimension compSize = new Dimension(c.getWidth(), approximateExpandedHeight);

            // The following code is copied from java.awt.Window#setLocationRelativeTo(c).
            Point compLocation = c.getLocationOnScreen();
            dx = compLocation.x + ((compSize.width - windowSize.width) / 2);
            dy = compLocation.y + ((compSize.height - windowSize.height) / 2);

            // Adjust for bottom edge being offscreen
            if(dy + windowSize.height > gcBounds.y + gcBounds.height) {
                dy = gcBounds.y + gcBounds.height - windowSize.height;
                if(compLocation.x - gcBounds.x + compSize.width / 2 < gcBounds.width / 2) {
                    dx = compLocation.x + compSize.width;
                } else {
                    dx = compLocation.x - windowSize.width;
                }
            }
            // Avoid being placed off the edge of the screen:
            // bottom
            if(dy + windowSize.height > gcBounds.y + gcBounds.height) {
                dy = gcBounds.y + gcBounds.height - windowSize.height;
            }
            // top
            if(dy < gcBounds.y) {
                dy = gcBounds.y;
            }
            // right
            if(dx + windowSize.width > gcBounds.x + gcBounds.width) {
                dx = gcBounds.x + gcBounds.width - windowSize.width;
            }
            // left
            if(dx < gcBounds.x) {
                dx = gcBounds.x;
            }
            return new Point(dx, dy);
        }
    }

}
