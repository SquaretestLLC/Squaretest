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
package com.squaretest.settings.store;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class SettingsProvider {

    private final SettingsState settingsState;

    public SettingsProvider() {
        this.settingsState = SettingsState.getInstance();
    }

    public void renameTemplateInAllSettings(
            @NotNull final Template existingTemplate,
            @NotNull final Template newTemplate) {
        this.settingsState.renameTemplateInAllSettings(existingTemplate, newTemplate);
    }

    public List<ProjectSettings> getProjectSettingsUsingTemplate(final Template template) {
        return this.settingsState.getProjectSettingsWithTemplate(template);
    }

    public Map<String, List<ModuleSettings>> getModuleSettingsUsingTemplate(final Template template) {
        return this.settingsState.getModuleSettingsWithTemplate(template);
    }

    public void deleteTemplateFromAllSettings(final Template template) {
        this.settingsState.deleteTemplateFromAllSettings(template);
    }

    @Nullable
    public ProjectSettings getProjectSettings(@NotNull final String projectName) {
        return settingsState.getProjectSettings(projectName);
    }

    @Nullable
    public ModuleSettings getModuleSettings(@NotNull final String projectName, @NotNull final String moduleName) {
        return this.settingsState.getModuleSettings(projectName, moduleName);
    }

    public void putProjectSettings(@NotNull final ProjectSettings projectSettings) {
        this.settingsState.addProjectSettings(projectSettings);
    }

    public void putModuleSettings(@NotNull final String projectName,
                                  @NotNull final ModuleSettings moduleSettings) {
        this.settingsState.addModuleSettings(projectName, moduleSettings);
    }

    public OpenTestFilePreference getOpenTestFilePreference() {
        return this.settingsState.getOpenTestFilePreference();
    }

    public void putOpenTestFilePreference(@NotNull final OpenTestFilePreference openTestFilePreference) {
        this.settingsState.setOpenTestFilePreference(openTestFilePreference);
    }

    public int getConstructorMaxLength() {
        return this.settingsState.getConstructorMaxLength();
    }

    public void setConstructorMaxLength(final int maxLength) {
        this.settingsState.setConstructorMaxLength(maxLength);
    }

    public Integer getConstructorLimitWhenBeanOptionAvailable() {
        return settingsState.getConstructorLimitWhenBeanOptionAvailable();
    }

    public void setConstructorLimitWhenBeanOptionAvailable(final Integer constructorLimitWhenBeanOptionAvailable) {
        settingsState.setConstructorLimitWhenBeanOptionAvailable(constructorLimitWhenBeanOptionAvailable);
    }

    public Integer getConstructorLimitWhenOtherOptionAvailable() {
        return settingsState.getConstructorLimitWhenOtherOptionAvailable();
    }

    public void setConstructorLimitWhenOtherOptionAvailable(final Integer constructorLimitWhenOtherOptionAvailable) {
        settingsState.setConstructorLimitWhenOtherOptionAvailable(constructorLimitWhenOtherOptionAvailable);
    }

    public Integer getConstructorLimitFinal() {
        return settingsState.getConstructorLimitFinal();
    }

    public void setConstructorLimitFinal(final Integer constructorLimitFinal) {
        settingsState.setConstructorLimitFinal(constructorLimitFinal);
    }

    public Integer getMaxNumberOfBuilderMethodsToCall() {
        return settingsState.getMaxNumberOfBuilderMethodsToCall();
    }

    public void setMaxNumberOfBuilderMethodsToCall(final Integer maxNumberOfBuilderMethodsToCall) {
        settingsState.setMaxNumberOfBuilderMethodsToCall(maxNumberOfBuilderMethodsToCall);
    }

    public boolean getEnableCompletionSuggestionsForTestMethods() {
        return settingsState.getEnableCompletionSuggestionsForTestMethods();
    }

    public void setEnableCompletionSuggestionsForTestMethods(final boolean enableCompletionSuggestionsForTestMethods) {
        settingsState.setEnableCompletionSuggestionsForTestMethods(enableCompletionSuggestionsForTestMethods);
    }
}
