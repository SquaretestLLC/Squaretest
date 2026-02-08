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
package com.squaretest.settings.viewdataproviders;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.squaretest.settings.store.ModuleAutoConfigSetting;
import com.squaretest.settings.store.ModuleSettings;
import com.squaretest.settings.store.ProjectSettings;
import com.squaretest.settings.store.SettingsProvider;
import com.squaretest.settings.store.Template;
import com.squaretest.settings.store.TemplateLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Provides components to view and update the settings data model.
 */
public class TemplateSettingsViewProvider {
    @NotNull
    private final SettingsProvider settingsProvider;

    public TemplateSettingsViewProvider(@NotNull final SettingsProvider settingsProvider) {
        this.settingsProvider = settingsProvider;
    }

    public TemplateSettingsView newProjectSettingsView(@NotNull final Project project) {
        return new TemplateSettingsView() {
            @Nullable
            @Override
            public TemplateLanguage getTemplateLanguage() {
                final ProjectSettings projectSettings = settingsProvider.getProjectSettings(project.getName());
                return projectSettings == null ? null : projectSettings.getTemplateLanguage();
            }

            @Nullable
            @Override
            public Template getTemplate() {
                final ProjectSettings projectSettings = settingsProvider.getProjectSettings(project.getName());
                return projectSettings == null ? null : projectSettings.getTemplate();
            }

            @Override
            public void saveTemplateSettings(
                    @Nullable final TemplateLanguage templateLanguage, @Nullable final Template template) {
                final ProjectSettings projectSettings = settingsProvider.getProjectSettings(project.getName());
                ModuleAutoConfigSetting moduleAutoConfigSetting;
                if(projectSettings != null) {
                    moduleAutoConfigSetting = projectSettings.getModuleAutoConfigSetting();
                } else {
                    moduleAutoConfigSetting = ModuleAutoConfigSetting.AUTOMATICALLY_CONFIGURE_WHEN_POSSIBLE;
                }
                final ProjectSettings newProjectSettings = new ProjectSettings(
                        project.getName(),
                        templateLanguage,
                        template, moduleAutoConfigSetting);
                settingsProvider.putProjectSettings(newProjectSettings);
            }
        };
    }

    /**
     * Provides a view into the settings for a given module.
     *
     * @param project the project
     * @param module  the module
     * @return the {@link TemplateSettingsView} that can be used to retrieve and update the template info associated
     * with the module.
     */
    public TemplateSettingsView newModuleSettingsView(
            @NotNull final Project project,
            @NotNull final Module module) {

        // Implementation Note: we retrieve the ModuleSettings from the SettingsProvider everytime instead of just
        // the first time when this view is created. This avoids cases like: a component with the settings-view calls
        // saveTemplateSettings, which constructs a new ModuleSettings based on those stored when the view was created;
        // this would revert any changes to the ModuleSettings made by other components between when this view was
        // created and when saveTemplateSettings was called.
        return new TemplateSettingsView() {
            @Nullable
            @Override
            public TemplateLanguage getTemplateLanguage() {
                final ModuleSettings moduleSettings = getModuleSettings(project, module);
                return moduleSettings == null ? null : moduleSettings.getTemplateLanguage();
            }

            @Nullable
            @Override
            public Template getTemplate() {
                final ModuleSettings existingModuleSettings = getModuleSettings(project, module);
                return existingModuleSettings == null ? null : existingModuleSettings.getTemplate();
            }

            @Override
            public void saveTemplateSettings(@Nullable final TemplateLanguage templateLanguage,
                                             @Nullable final Template template) {
                final ModuleSettings existingModuleSettings = getModuleSettings(project, module);
                ModuleSettings moduleSettingsToStore;
                if(existingModuleSettings == null) {
                    moduleSettingsToStore = new ModuleSettings(
                            module.getName(),
                            templateLanguage,
                            template,
                            null);
                } else {
                    moduleSettingsToStore = new ModuleSettings(
                            existingModuleSettings.getName(),
                            templateLanguage,
                            template,
                            existingModuleSettings.getTestDirectoryRoot());
                }
                settingsProvider.putModuleSettings(project.getName(), moduleSettingsToStore);
            }
        };
    }

    private ModuleSettings getModuleSettings(@NotNull final Project project, @NotNull final Module module) {
        return settingsProvider.getModuleSettings(
                project.getName(),
                module.getName());
    }
}
