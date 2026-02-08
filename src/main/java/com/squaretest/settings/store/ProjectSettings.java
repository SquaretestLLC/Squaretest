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

import com.intellij.util.xmlb.annotations.Property;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProjectSettings {
    // The fields can't be final, because they need to be set after the class's construction by IntelliJ's
    // persistence code.
    @Property
    private String projectName;
    @Nullable
    @Property
    private TemplateLanguage templateLanguage;
    @Nullable
    @Property
    private Template template;
    @Nullable
    @Property
    private ModuleAutoConfigSetting moduleAutoConfigSetting;

    /**
     * This exists to work with IntelliJ's XML serializer that it uses for persistence.
     */
    public ProjectSettings() {
    }

    public ProjectSettings(
            @NotNull final String projectName, @Nullable final TemplateLanguage templateLanguage,
            @Nullable final Template template, @Nullable final ModuleAutoConfigSetting moduleAutoConfigSetting) {
        this.projectName = projectName;
        this.templateLanguage = templateLanguage;
        this.template = template;
        this.moduleAutoConfigSetting = moduleAutoConfigSetting;
    }

    @NotNull
    public String getProjectName() {
        return projectName;
    }

    @Nullable
    public TemplateLanguage getTemplateLanguage() {
        return templateLanguage;
    }

    @Nullable
    public Template getTemplate() {
        return template;
    }

    void setTemplate(@Nullable final Template template) {
        this.template = template;
    }

    @Nullable
    public ModuleAutoConfigSetting getModuleAutoConfigSetting() {
        return moduleAutoConfigSetting;
    }
}
