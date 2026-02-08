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
package com.squaretest.completion;

import com.intellij.openapi.module.Module;
import com.squaretest.TemplateProvider;
import com.squaretest.settings.store.ModuleSettings;
import com.squaretest.settings.store.ProjectSettings;
import com.squaretest.settings.store.SettingsProvider;
import com.squaretest.settings.store.Template;
import org.jetbrains.annotations.Nullable;

public class TemplateSettingsFinder {
    private final SettingsProvider settingsProvider;
    private final TemplateProvider templateProvider;

    public TemplateSettingsFinder(
            final SettingsProvider settingsProvider, final TemplateProvider templateProvider) {
        this.settingsProvider = settingsProvider;
        this.templateProvider = templateProvider;
    }

    @Nullable
    public TemplateInfo getTemplateInfo(final Module sourceClassModule) {
        // Determine which template would be used to generate a test class for a source class in the given module.
        Template template;
        final ModuleSettings moduleSettings = settingsProvider.getModuleSettings(sourceClassModule.getProject().getName(), sourceClassModule.getName());
        // If the module settings are null, or they exist but don't contain a template, check the project settings.
        if(moduleSettings == null || moduleSettings.getTemplate() == null) {
            // Try the project settings
            final ProjectSettings projectSettings = settingsProvider.getProjectSettings(sourceClassModule.getProject().getName());
            if(projectSettings != null) {
                template = projectSettings.getTemplate();
            } else {
                template = null;
            }
        } else {
            template = moduleSettings.getTemplate();
        }

        if(template == null) {
            return null;
        }
        return new TemplateInfo(templateProvider, template);
    }
}
