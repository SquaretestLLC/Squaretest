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

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.annotations.MapAnnotation;
import com.intellij.util.xmlb.annotations.Property;
import com.squaretest.utils.MathUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@State(
        name = "SquaretestSettings",
        storages = {
                @Storage("SquaretestSettings.xml")
        }
)
public class SettingsState implements PersistentStateComponent<SettingsState> {
    private static final int DefaultDataConstructorLimitWhenBeanOptionAvailable = 5;
    private static final int DefaultConstructorLimitWhenBeanOptionAvailable = 5;
    private static final int DefaultConstructorLimitWhenOtherOptionAvailable = 300;
    private static final int DefaultConstructorLimitFinal = 300;
    private static final int DefaultMaxNumberOfBuilderMethodsToCall = 70;
    @NotNull
    @Property
    @MapAnnotation
    private final Map<String, ProjectSettings> projectNameToSettingsMap;
    @NotNull
    @Property
    @MapAnnotation
    private final Map<String, ModuleSettings> projectNameModuleNameToSettingsMap;
    @NotNull
    @Property
    private OpenTestFilePreference openTestFilePreference;
    @Property
    private Integer constructorMaxLength;
    @Property
    private Integer constructorLimitWhenBeanOptionAvailable;
    @Property
    private Integer constructorLimitWhenOtherOptionAvailable;
    @Property
    private Integer constructorLimitFinal;
    @Property
    private Integer maxNumberOfBuilderMethodsToCall;
    @Property
    private Boolean enableCompletionSuggestionsForTestMethods;

    private static SettingsState instance;

    public SettingsState() {
        this.projectNameToSettingsMap = new HashMap<>();
        this.projectNameModuleNameToSettingsMap = new HashMap<>();
        this.openTestFilePreference = OpenTestFilePreference.NEXT_EDITOR_IF_AVAILABLE;
        this.constructorMaxLength = DefaultDataConstructorLimitWhenBeanOptionAvailable;
        this.constructorLimitWhenBeanOptionAvailable = DefaultConstructorLimitWhenBeanOptionAvailable;
        this.constructorLimitWhenOtherOptionAvailable = DefaultConstructorLimitWhenOtherOptionAvailable;
        this.constructorLimitFinal = DefaultConstructorLimitFinal;
        this.maxNumberOfBuilderMethodsToCall = DefaultMaxNumberOfBuilderMethodsToCall;
        this.enableCompletionSuggestionsForTestMethods = true;
    }

    @Nullable
    public ProjectSettings getProjectSettings(@NotNull final String projectName) {
        return projectNameToSettingsMap.get(projectName);
    }

    @Nullable
    public ModuleSettings getModuleSettings(@NotNull final String projectName, @NotNull final String moduleName) {
        return projectNameModuleNameToSettingsMap.get(key(projectName, moduleName));
    }

    @NotNull
    public OpenTestFilePreference getOpenTestFilePreference() {
        return openTestFilePreference;
    }

    public void setOpenTestFilePreference(@NotNull final OpenTestFilePreference openTestFilePreference) {
        this.openTestFilePreference = openTestFilePreference;
    }

    public int getConstructorMaxLength() {
        return constructorMaxLength;
    }

    public void setConstructorMaxLength(final int constructorMaxLength) {
        this.constructorMaxLength = constructorMaxLength;
    }

    public Integer getConstructorLimitWhenBeanOptionAvailable() {
        return constructorLimitWhenBeanOptionAvailable;
    }

    public void setConstructorLimitWhenBeanOptionAvailable(final Integer constructorLimitWhenBeanOptionAvailable) {
        this.constructorLimitWhenBeanOptionAvailable = constructorLimitWhenBeanOptionAvailable;
    }

    public Integer getConstructorLimitWhenOtherOptionAvailable() {
        return constructorLimitWhenOtherOptionAvailable;
    }

    public void setConstructorLimitWhenOtherOptionAvailable(final Integer constructorLimitWhenOtherOptionAvailable) {
        this.constructorLimitWhenOtherOptionAvailable = constructorLimitWhenOtherOptionAvailable;
    }

    public Integer getConstructorLimitFinal() {
        return constructorLimitFinal;
    }

    public void setConstructorLimitFinal(final Integer constructorLimitFinal) {
        this.constructorLimitFinal = constructorLimitFinal;
    }

    public Integer getMaxNumberOfBuilderMethodsToCall() {
        return maxNumberOfBuilderMethodsToCall;
    }

    public void setMaxNumberOfBuilderMethodsToCall(final Integer maxNumberOfBuilderMethodsToCall) {
        this.maxNumberOfBuilderMethodsToCall = maxNumberOfBuilderMethodsToCall;
    }

    public boolean getEnableCompletionSuggestionsForTestMethods() {
        return enableCompletionSuggestionsForTestMethods;
    }

    public void setEnableCompletionSuggestionsForTestMethods(final boolean enableCompletionSuggestionsForTestMethods) {
        this.enableCompletionSuggestionsForTestMethods = enableCompletionSuggestionsForTestMethods;
    }

    public void addProjectSettings(@NotNull final ProjectSettings projectSettings) {
        projectNameToSettingsMap.put(projectSettings.getProjectName(), projectSettings);
    }

    public void addModuleSettings(
            @NotNull final String projectName,
            @NotNull final ModuleSettings moduleSettings) {
        projectNameModuleNameToSettingsMap.put(key(projectName, moduleSettings.getName()), moduleSettings);
    }

    public List<ProjectSettings> getProjectSettingsWithTemplate(final Template template) {
        final List<ProjectSettings> ret = new ArrayList<>();
        for(Map.Entry<String, ProjectSettings> projectSettingsEntry : projectNameToSettingsMap.entrySet()) {
            final ProjectSettings projectSettings = projectSettingsEntry.getValue();
            final Template projectTemplate = projectSettings.getTemplate();
            if(Objects.equals(template, projectTemplate)) {
                ret.add(projectSettings);
            }
        }
        return ret;
    }

    public Map<String, List<ModuleSettings>> getModuleSettingsWithTemplate(final Template template) {
        final Map<String, List<ModuleSettings>> ret = new HashMap<>();
        for(Map.Entry<String, ModuleSettings> entry : projectNameModuleNameToSettingsMap.entrySet()) {
            if(Objects.equals(template, entry.getValue().getTemplate())) {
                final String projectName = projectNameFromKey(entry.getKey(), entry.getValue());
                if(!ret.containsKey(projectName)) {
                    ret.put(projectName, new ArrayList<>());
                }
                ret.get(projectName).add(entry.getValue());
            }
        }
        return ret;
    }

    public void deleteTemplateFromAllSettings(final Template template) {
        final List<ProjectSettings> projectSettings = getProjectSettingsWithTemplate(template);
        for(final ProjectSettings settings : projectSettings) {
            settings.setTemplate(null);
        }
        final Map<String, List<ModuleSettings>> moduleSettingsUsingTemplate = getModuleSettingsWithTemplate(template);
        for(final List<ModuleSettings> moduleSettingsList : moduleSettingsUsingTemplate.values()) {
            for(final ModuleSettings moduleSettings : moduleSettingsList) {
                moduleSettings.setTemplate(null);
            }
        }
    }

    public void renameTemplateInAllSettings(
            @NotNull final Template existingTemplate,
            @NotNull final Template newTemplate) {
        // Project settings
        for(final ProjectSettings projectSettings : projectNameToSettingsMap.values()) {
            final Template projectTemplate = projectSettings.getTemplate();
            if(Objects.equals(existingTemplate, projectTemplate)) {
                projectSettings.setTemplate(newTemplate);
            }
        }
        // Module Settings
        for(final ModuleSettings moduleSettings : projectNameModuleNameToSettingsMap.values()) {
            final Template moduleTemplate = moduleSettings.getTemplate();
            if(Objects.equals(existingTemplate, moduleTemplate)) {
                moduleSettings.setTemplate(newTemplate);
            }
        }
    }

    private String key(final String projectName, final String moduleName) {
        return String.format(Locale.US, "%s_%s", projectName, moduleName);
    }

    private String projectNameFromKey(final String key, final ModuleSettings moduleSettings) {
        final String suffix = "_" + moduleSettings.getName();
        if(key.endsWith(suffix)) {
            return StringUtils.removeEnd(key, suffix);
        }
        return key.substring(0, key.indexOf("_"));
    }

    @Nullable
    @Override
    public SettingsState getState() {
        return this;
    }

    @Override
    public void loadState(final SettingsState state) {
        this.projectNameModuleNameToSettingsMap.clear();
        this.projectNameModuleNameToSettingsMap.putAll(state.projectNameModuleNameToSettingsMap);
        this.projectNameToSettingsMap.clear();
        this.projectNameToSettingsMap.putAll(state.projectNameToSettingsMap);
        this.openTestFilePreference = state.openTestFilePreference;
        if(state.constructorMaxLength == null) {
            this.constructorMaxLength = DefaultDataConstructorLimitWhenBeanOptionAvailable;
        } else {
            this.constructorMaxLength = MathUtils.clamp(state.constructorMaxLength, 0, 100_000);
        }
        if(state.constructorLimitWhenBeanOptionAvailable == null) {
            this.constructorLimitWhenBeanOptionAvailable = DefaultConstructorLimitWhenBeanOptionAvailable;
        } else {
            this.constructorLimitWhenBeanOptionAvailable = MathUtils.clamp(state.constructorLimitWhenBeanOptionAvailable, 0, 100_000);
        }
        if(state.constructorLimitWhenOtherOptionAvailable == null) {
            this.constructorLimitWhenOtherOptionAvailable = DefaultConstructorLimitWhenOtherOptionAvailable;
        } else {
            this.constructorLimitWhenOtherOptionAvailable = MathUtils.clamp(state.constructorLimitWhenOtherOptionAvailable, 0, 100_000);
        }
        if(state.constructorLimitFinal == null) {
            this.constructorLimitFinal = DefaultConstructorLimitFinal;
        } else {
            this.constructorLimitFinal = MathUtils.clamp(state.constructorLimitFinal, 0, 100_000);
        }
        if(state.maxNumberOfBuilderMethodsToCall == null) {
            this.maxNumberOfBuilderMethodsToCall = DefaultMaxNumberOfBuilderMethodsToCall;
        } else {
            this.maxNumberOfBuilderMethodsToCall = MathUtils.clamp(state.maxNumberOfBuilderMethodsToCall, 0, 100_000);
        }
        if(state.enableCompletionSuggestionsForTestMethods == null) {
            this.enableCompletionSuggestionsForTestMethods = true;
        } else {
            this.enableCompletionSuggestionsForTestMethods = state.enableCompletionSuggestionsForTestMethods;
        }
    }

    public static synchronized SettingsState getInstance() {
        if(instance == null) {
            instance = ApplicationManager.getApplication().getService(SettingsState.class);
        }
        return instance;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final SettingsState that = (SettingsState) o;
        return Objects.equals(projectNameToSettingsMap, that.projectNameToSettingsMap) && Objects.equals(projectNameModuleNameToSettingsMap, that.projectNameModuleNameToSettingsMap) && openTestFilePreference == that.openTestFilePreference && Objects.equals(constructorMaxLength, that.constructorMaxLength) && Objects.equals(constructorLimitWhenBeanOptionAvailable, that.constructorLimitWhenBeanOptionAvailable) && Objects.equals(constructorLimitWhenOtherOptionAvailable, that.constructorLimitWhenOtherOptionAvailable) && Objects.equals(constructorLimitFinal, that.constructorLimitFinal) && Objects.equals(maxNumberOfBuilderMethodsToCall, that.maxNumberOfBuilderMethodsToCall) && Objects.equals(enableCompletionSuggestionsForTestMethods, that.enableCompletionSuggestionsForTestMethods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectNameToSettingsMap, projectNameModuleNameToSettingsMap, openTestFilePreference, constructorMaxLength, constructorLimitWhenBeanOptionAvailable, constructorLimitWhenOtherOptionAvailable, constructorLimitFinal, maxNumberOfBuilderMethodsToCall, enableCompletionSuggestionsForTestMethods);
    }
}
