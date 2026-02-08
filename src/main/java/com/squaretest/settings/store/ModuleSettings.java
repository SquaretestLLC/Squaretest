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

public class ModuleSettings {
    @Property
    private String moduleName;
    @Nullable
    @Property
    private TemplateLanguage templateLanguage;
    @Nullable
    @Property
    private Template template;
    @Nullable
    @Property
    private String testDirectoryRoot;

    /**
     * This is needed to support the IntelliJ persistence logic.
     */
    ModuleSettings() {
    }

    public ModuleSettings(
            @NotNull final String moduleName, @Nullable final TemplateLanguage templateLanguage,
            @Nullable final Template template, @Nullable final String testDirectoryRoot) {
        this.moduleName = moduleName;
        this.templateLanguage = templateLanguage;
        this.template = template;
        this.testDirectoryRoot = testDirectoryRoot;
    }

    public String getName() {
        return moduleName;
    }

    @Nullable
    public TemplateLanguage getTemplateLanguage() {
        return templateLanguage;
    }

    @Nullable
    public Template getTemplate() {
        return template;
    }

    public void setTemplate(@Nullable final Template template) {
        this.template = template;
    }

    @Nullable
    public String getTestDirectoryRoot() {
        return testDirectoryRoot;
    }
}
