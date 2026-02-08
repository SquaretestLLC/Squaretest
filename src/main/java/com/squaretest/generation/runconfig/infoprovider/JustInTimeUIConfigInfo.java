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
package com.squaretest.generation.runconfig.infoprovider;

import com.squaretest.settings.displaywrappers.DisplayModuleAutoConfigSetting;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Contains info describing which fields must be collected in order to have sufficient settings to generate a test
 * for the given file. The info also contains placeholder values to use for the dropdown lists.
 */
public class JustInTimeUIConfigInfo {
    @NotNull
    private final String templateLanguageDefaultOptionText;
    @NotNull
    private final String templateDefaultOptionText;
    private final boolean templateRequired;
    private final boolean defaultPromoteSettingsToProjectCheckboxState;
    @NotNull
    private final DisplayModuleAutoConfigSetting moduleConfigurationOptionSelected;
    @Nullable
    private final TestRootInfo defaultTestSourcesRootInfo;
    @NotNull
    private final String defaultTemplateSaveAsName;
    @NotNull
    private final LanguageAndTemplate inferredLangaugeAndTemplate;

    public JustInTimeUIConfigInfo(
            @NotNull final String templateLanguageDefaultOptionText,
            @NotNull final String templateDefaultOptionText,
            @NotNull final String defaultTemplateSaveAsName,
            final boolean templateRequired,
            @Nullable final TestRootInfo defaultTestSourcesRootInfo,
            final boolean defaultPromoteSettingsToProjectCheckboxState,
            @NotNull final DisplayModuleAutoConfigSetting moduleConfigurationOptionSelected,
            @NotNull final LanguageAndTemplate inferredLangaugeAndTemplate) {
        this.templateLanguageDefaultOptionText = templateLanguageDefaultOptionText;
        this.templateDefaultOptionText = templateDefaultOptionText;
        this.templateRequired = templateRequired;
        this.defaultTestSourcesRootInfo = defaultTestSourcesRootInfo;
        this.defaultPromoteSettingsToProjectCheckboxState = defaultPromoteSettingsToProjectCheckboxState;
        this.moduleConfigurationOptionSelected = moduleConfigurationOptionSelected;
        this.defaultTemplateSaveAsName = defaultTemplateSaveAsName;
        this.inferredLangaugeAndTemplate = inferredLangaugeAndTemplate;
    }

    @NotNull
    public String getTemplateLanguageDefaultOptionText() {
        return templateLanguageDefaultOptionText;
    }

    @NotNull
    public String getTemplateDefaultOptionText() {
        return templateDefaultOptionText;
    }

    public boolean isTemplateRequired() {
        return templateRequired;
    }

    @Nullable
    public TestRootInfo getDefaultTestSourcesRootToShow() {
        return defaultTestSourcesRootInfo;
    }

    public boolean getDefaultPromoteSettingsToProjectState() {
        return defaultPromoteSettingsToProjectCheckboxState;
    }

    @NotNull
    public DisplayModuleAutoConfigSetting getModuleConfigurationOptionSelected() {
        return moduleConfigurationOptionSelected;
    }

    @NotNull
    public LanguageAndTemplate getInferredLanguageAndTemplate() {
        return inferredLangaugeAndTemplate;
    }

    @NotNull
    public String getDefaultTemplateSaveAsName() {
        return defaultTemplateSaveAsName;
    }
}
