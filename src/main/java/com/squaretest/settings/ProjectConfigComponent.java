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
package com.squaretest.settings;

import com.google.common.base.Supplier;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.squaretest.TemplateProvider;
import com.squaretest.settings.displaywrappers.DisplayModuleAutoConfigSetting;
import com.squaretest.settings.editor.EditorCreator;
import com.squaretest.settings.editor.PlatformInfoProvider;
import com.squaretest.settings.editor.SettingsEditorProvider;
import com.squaretest.settings.editor.VelocityEditorHighlighterProvider;
import com.squaretest.settings.notification.NewTemplateNotifier;
import com.squaretest.settings.store.ModuleAutoConfigSetting;
import com.squaretest.settings.store.ModuleSettings;
import com.squaretest.settings.store.ProjectSettings;
import com.squaretest.settings.store.SettingsProvider;
import com.squaretest.settings.viewdataproviders.TemplateSettingsView;
import com.squaretest.settings.viewdataproviders.TemplateSettingsViewProvider;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ProjectConfigComponent implements TemplateSelectionChangedListener, ProjectConfigUi {
    private static final ModuleAutoConfigSetting DefaultModuleAutoConfigSetting = ModuleAutoConfigSetting.AUTOMATICALLY_CONFIGURE_WHEN_POSSIBLE;
    private static final String DefaultModuleTemplateComboBoxText = "Not Specified (inherits from project settings)";
    private static final int MaxModulesToShowByDefault = 20;
    private JComboBox<DisplayModuleAutoConfigSetting> selectModuleAutoSettingsConfig;
    private TemplateConfigComponent templateConfigComponent;
    private JPanel moduleSettingsContainerPanel;
    private JPanel rootPanel;
    private JPanel projectSettingsPanel;
    private JLabel lblModuleSetup;
    private SQHelpIcon lblModuleAutoconfigHelp;

    private List<ModuleConfigComponent> moduleConfigComponents;

    @NotNull
    private final TemplateProvider templateProvider;
    @NotNull
    private final Project project;
    @NotNull
    private final TemplateSettingsViewProvider templateSettingsViewProvider;
    @NotNull
    private final SettingsProvider settingsProvider;
    @NotNull
    private NewTemplateNotifier newTemplateNotifier;

    public ProjectConfigComponent(
            @NotNull final TemplateProvider templateProvider,
            @NotNull final Project project,
            @NotNull final TemplateSettingsViewProvider templateSettingsViewProvider,
            @NotNull final SettingsProvider settingsProvider) {
        this.templateProvider = templateProvider;
        this.project = project;
        this.templateSettingsViewProvider = templateSettingsViewProvider;
        this.settingsProvider = settingsProvider;
    }

    private void createUIComponents() {
        this.newTemplateNotifier = new NewTemplateNotifier();
        final TemplateSettingsView templateSettingsView = templateSettingsViewProvider.newProjectSettingsView(project);
        final String defaultTemplateSaveName = project.getName();
        final PlatformInfoProvider platformInfoProvider = new PlatformInfoProvider();
        final EditorCreator editorCreator = new EditorCreator(project, new VelocityEditorHighlighterProvider(project,
                platformInfoProvider), platformInfoProvider);
        final Supplier<JPanel> editorPanelSupplier = new SettingsEditorProvider();
        templateConfigComponent = new TemplateConfigComponent(
                templateProvider,
                templateSettingsView,
                defaultTemplateSaveName,
                this.project,
                editorCreator, editorPanelSupplier,
                newTemplateNotifier);

        // Determine which modules to use.
        Module[] modules = ModuleManager.getInstance(this.project).getModules();
        modules = Arrays.stream(modules).filter(this::shouldInclude).toArray(Module[]::new);
        if(modules.length > MaxModulesToShowByDefault) {
            modules = Arrays.stream(modules).filter(this::hasSquaretestConfig).toArray(Module[]::new);
        }
        Arrays.sort(modules, Comparator.comparing(Module::getName, String.CASE_INSENSITIVE_ORDER));
        moduleConfigComponents = new ArrayList<>(modules.length);
        for(final Module module : modules) {
            final ModuleConfigComponent moduleConfigComponent = new ModuleConfigComponent(
                    this.templateProvider,
                    this.project,
                    module,
                    this.templateSettingsViewProvider, settingsProvider,
                    newTemplateNotifier, editorPanelSupplier, DefaultModuleTemplateComboBoxText,
                    DefaultModuleTemplateComboBoxText,
                    String.format("%s-%s", project.getName(), module.getName()),
                    null);
            moduleConfigComponents.add(moduleConfigComponent);
        }
        this.moduleSettingsContainerPanel = new JPanel();
        this.moduleSettingsContainerPanel.setLayout(new BoxLayout(this.moduleSettingsContainerPanel, BoxLayout.Y_AXIS));
        lblModuleAutoconfigHelp = new SQHelpIcon(ToolTipLoader.getInstance().getModuleAutoConfigToolTip());
    }

    private boolean shouldInclude(final Module module) {
        final ModuleRootManager rootManager = ModuleRootManager.getInstance(module);
        final VirtualFile[] prodSources = rootManager.getSourceRoots(false);
        if(prodSources.length != 0) {
            return true;
        }
        // There are no prod sources. Only include the module if we have settings for it.
        // This will only happen when the user generates a test for a class in the test module, and then
        // goes through the JustInTimeConfigurationDialog menu.
        return hasSquaretestConfig(module);
    }

    private boolean hasSquaretestConfig(final Module module) {
        final ModuleSettings moduleSettings = settingsProvider.getModuleSettings(module.getProject().getName(), module.getName());
        return moduleSettings != null;
    }

    @Override
    public void initUiComponentsRequiringAWTThread() {

        templateConfigComponent.initUiComponentsRequiringAWTThread();

        // Add the module config UI to the JPanel.
        for(final ModuleConfigComponent moduleConfigComponent : moduleConfigComponents) {
            moduleConfigComponent.initUiComponentsRequiringAWTThread();
            this.moduleSettingsContainerPanel.add(moduleConfigComponent.getRootPanel());
        }
        // Set the selectModuleAutoConfig settings drop-down.
        this.selectModuleAutoSettingsConfig.addItem(DisplayModuleAutoConfigSetting.fromModuleAutoConfigSetting(ModuleAutoConfigSetting.AUTOMATICALLY_CONFIGURE_WHEN_POSSIBLE));
        this.selectModuleAutoSettingsConfig.addItem(DisplayModuleAutoConfigSetting.fromModuleAutoConfigSetting(ModuleAutoConfigSetting.ALWAYS_SHOW_CONFIG));
        final ProjectSettings projectSettings = this.settingsProvider.getProjectSettings(project.getName());
        final ModuleAutoConfigSetting storedModuleAutoConfigSetting = projectSettings == null ? null : projectSettings.getModuleAutoConfigSetting();
        if(storedModuleAutoConfigSetting != null) {
            this.selectModuleAutoSettingsConfig.setSelectedItem(DisplayModuleAutoConfigSetting.fromModuleAutoConfigSetting(storedModuleAutoConfigSetting));
        } else {
            this.selectModuleAutoSettingsConfig.setSelectedItem(DisplayModuleAutoConfigSetting.fromModuleAutoConfigSetting(ModuleAutoConfigSetting.AUTOMATICALLY_CONFIGURE_WHEN_POSSIBLE));
        }

        // Enable or disable auto-config drop-down based on whether a project-level template is specified.
        templateConfigComponent.addTemplateSelectionChangedListener(this);
        this.selectModuleAutoSettingsConfig.setEnabled(templateConfigComponent.isTemplateSelected());

        // Set the JPanel border.
        final String title = String.format("Project Settings - %s", project.getName());
        final TitledBorder border = new TitledBorder(title);
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        this.projectSettingsPanel.setBorder(border);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    @Override
    public boolean isModified() {
        if(templateConfigComponent.isModified()) {
            return true;
        }
        for(final ModuleConfigComponent component : moduleConfigComponents) {
            if(component.isModified()) {
                return true;
            }
        }

        // Check to see if selectModuleAutoSettingsConfig is modified as well.
        final DisplayModuleAutoConfigSetting selectedAutoConfigSettings = (DisplayModuleAutoConfigSetting) this.selectModuleAutoSettingsConfig.getSelectedItem();
        final ProjectSettings projectSettings = this.settingsProvider.getProjectSettings(project.getName());
        final ModuleAutoConfigSetting storedModuleAutoConfigSetting = projectSettings == null ? null : projectSettings.getModuleAutoConfigSetting();
        if(storedModuleAutoConfigSetting != null) {
            return storedModuleAutoConfigSetting != selectedAutoConfigSettings.getModuleAutoConfigSetting();
        } else {
            return selectedAutoConfigSettings.getModuleAutoConfigSetting() != DefaultModuleAutoConfigSetting;
        }
    }

    @Override
    public void reset() {
        if(!isModified()) {
            return;
        }

        templateConfigComponent.reset();
        for(final ModuleConfigComponent component : moduleConfigComponents) {
            component.reset();
        }

        // Set the auto-config setting back to the stored value (if present) or the default value.
        final ProjectSettings projectSettings = this.settingsProvider.getProjectSettings(project.getName());
        final ModuleAutoConfigSetting storedModuleAutoConfigSetting = projectSettings == null ? null : projectSettings.getModuleAutoConfigSetting();
        if(storedModuleAutoConfigSetting == null) {
            this.selectModuleAutoSettingsConfig.setSelectedItem(DisplayModuleAutoConfigSetting.fromModuleAutoConfigSetting(DefaultModuleAutoConfigSetting));
        } else {
            this.selectModuleAutoSettingsConfig.setSelectedItem(DisplayModuleAutoConfigSetting.fromModuleAutoConfigSetting(storedModuleAutoConfigSetting));
        }
    }

    @Override
    public void apply() {
        for(final ModuleConfigComponent component : moduleConfigComponents) {
            if(component.isModified()) {
                component.apply();
            }
        }
        // Call this before setting the module-auto-config settings; this guarantees the project settings will have
        // been created.
        this.templateConfigComponent.apply();
        final ModuleAutoConfigSetting selectedAutoConfSettings = ((DisplayModuleAutoConfigSetting) this.selectModuleAutoSettingsConfig.getSelectedItem()).getModuleAutoConfigSetting();
        final ProjectSettings existingProjectSettings = this.settingsProvider.getProjectSettings(project.getName());
        if(existingProjectSettings != null) {
            final ProjectSettings newProjectSettings = new ProjectSettings(
                    existingProjectSettings.getProjectName(),
                    existingProjectSettings.getTemplateLanguage(),
                    existingProjectSettings.getTemplate(),
                    selectedAutoConfSettings);
            this.settingsProvider.putProjectSettings(newProjectSettings);
        }
    }

    @Override
    public void onTemplateSelectionChanged(final boolean templateSelected) {
        // Enable the auto-config setting only if a template is selected.
        this.selectModuleAutoSettingsConfig.setEnabled(templateSelected);
    }

    @Override
    public int getLongestLeftLabelSize() {

        int moduleConfigComponentsMaxLabelLength = 0;
        for(final ModuleConfigComponent moduleConfigComponent : this.moduleConfigComponents) {
            moduleConfigComponentsMaxLabelLength = Math.max(
                    moduleConfigComponent.getLongestLeftLabelWidth(),
                    moduleConfigComponentsMaxLabelLength);
        }

        return Math.max(
                Math.max(
                        UiUtils.computeJLabelWidth(lblModuleSetup),
                        this.templateConfigComponent.getLongestLeftLabelSize()),
                moduleConfigComponentsMaxLabelLength);
    }

    @Override
    public void setMinimumLeftLabelWidth(final int minWidth) {
        this.templateConfigComponent.setMinimumLeftLabelWidth(minWidth);
        for(final ModuleConfigComponent moduleConfigComponent : moduleConfigComponents) {
            moduleConfigComponent.setMinimumLeftLabelWidth(minWidth);
        }
        lblModuleSetup.setMinimumSize(new Dimension(minWidth, lblModuleSetup.getMinimumSize().height));
    }

    @Override
    public void disposeUIResources() {
        this.templateConfigComponent.disposeUIResources();
        for(final ModuleConfigComponent moduleConfigComponent : moduleConfigComponents) {
            moduleConfigComponent.disposeUIResources();
        }
    }

    @Override
    public void initUiComponentsRequiringJPane() {
        this.templateConfigComponent.initUiComponentsRequiringJPane();
        for(final ModuleConfigComponent moduleConfigComponent : moduleConfigComponents) {
            moduleConfigComponent.initUiComponentsRequiringJPane();
        }
    }

    @Override
    public void refreshTemplates() {
        this.templateConfigComponent.refreshTemplates();
        for(final ModuleConfigComponent moduleConfigComponent : moduleConfigComponents) {
            moduleConfigComponent.refreshTemplates();
        }
    }
}
