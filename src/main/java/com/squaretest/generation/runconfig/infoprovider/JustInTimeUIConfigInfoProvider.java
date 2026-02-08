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

import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;
import com.squaretest.settings.displaywrappers.DisplayModuleAutoConfigSetting;
import com.squaretest.settings.store.ModuleAutoConfigSetting;
import com.squaretest.settings.store.ProjectSettings;
import com.squaretest.settings.store.SettingsProvider;
import com.squaretest.settings.store.Template;
import com.squaretest.settings.store.TemplateLanguage;
import org.jetbrains.annotations.NotNull;

public class JustInTimeUIConfigInfoProvider {
    @NotNull
    private final SettingsProvider settingsProvider;
    @NotNull
    private final TestRootSelector testRootSelector;
    @NotNull
    private final TemplateSelector templateSelector;
    @NotNull
    private final PsiManager psiManager;

    public JustInTimeUIConfigInfoProvider(
            @NotNull final SettingsProvider settingsProvider,
            @NotNull final TestRootSelector testRootSelector,
            @NotNull final TemplateSelector templateSelector,
            @NotNull final PsiManager psiManager) {
        this.settingsProvider = settingsProvider;
        this.testRootSelector = testRootSelector;
        this.templateSelector = templateSelector;
        this.psiManager = psiManager;
    }

    public JustInTimeUIConfigInfo determineJustInTimeConfigInfo(@NotNull final Module module) {
        final ProjectSettings projectSettings = settingsProvider.getProjectSettings(module.getProject().getName());
        final Template projectTemplate = projectSettings == null ? null : projectSettings.getTemplate();
        final TemplateLanguage templateLanguage = projectSettings == null ? null : projectSettings.getTemplateLanguage();
        final ModuleAutoConfigSetting projectModuleAutoConfigSetting = projectSettings == null ? null : projectSettings.getModuleAutoConfigSetting();
        final String testLanguageDefaultOption;
        final String templateDefaultOption;
        final boolean templateRequired;
        final boolean promoteTemplateSettingsToProject;
        final DisplayModuleAutoConfigSetting displayModuleAutoConfigSetting;
        final String defaultTemplateSaveAsName;
        if(projectTemplate == null) {
            testLanguageDefaultOption = "Not Specified (required)";
            templateDefaultOption = "Not Specified (required)";
            templateRequired = true;
            promoteTemplateSettingsToProject = true;
            displayModuleAutoConfigSetting = DisplayModuleAutoConfigSetting.fromModuleAutoConfigSetting(ModuleAutoConfigSetting.AUTOMATICALLY_CONFIGURE_WHEN_POSSIBLE);
            defaultTemplateSaveAsName = module.getProject().getName();
        } else {
            testLanguageDefaultOption = String.format("Inherit from project settings (%s)", templateLanguage);
            templateDefaultOption = String.format("Inherit from project settings (%s)", projectTemplate.getPresentationName());
            templateRequired = false;
            promoteTemplateSettingsToProject = false;
            displayModuleAutoConfigSetting = projectModuleAutoConfigSetting == null ?
                    DisplayModuleAutoConfigSetting.fromModuleAutoConfigSetting(ModuleAutoConfigSetting.AUTOMATICALLY_CONFIGURE_WHEN_POSSIBLE)
                    : DisplayModuleAutoConfigSetting.fromModuleAutoConfigSetting(projectModuleAutoConfigSetting);
            defaultTemplateSaveAsName = String.format("%s-%s", module.getProject().getName(), module.getName());
        }

        final TestRootInfo recommendedTestRootForModule = testRootSelector.suggestTestRootForModule(module, templateLanguage);
        // Infer the Language and Template to use based on the user's test sources root.
        final LanguageAndTemplate languageAndTemplate;
        if(recommendedTestRootForModule != null) {
            final PsiDirectory testSourcesRootDir = psiManager.findDirectory(recommendedTestRootForModule.testSourcesRootDirectory());
            languageAndTemplate = templateSelector.inferTemplate(testSourcesRootDir, recommendedTestRootForModule.confirmedTestRootLang());
        } else {
            languageAndTemplate = new LanguageAndTemplate(null, null);
        }

        return new JustInTimeUIConfigInfo(
                testLanguageDefaultOption,
                templateDefaultOption,
                defaultTemplateSaveAsName,
                templateRequired,
                recommendedTestRootForModule,
                promoteTemplateSettingsToProject,
                displayModuleAutoConfigSetting,
                languageAndTemplate);
    }

    public JustInTimeUIConfigInfo determineJustInTimeConfigInfoForMissingTestSourcesRoot(@NotNull final Module module) {
        final ProjectSettings projectSettings = settingsProvider.getProjectSettings(module.getProject().getName());
        final Template projectTemplate = projectSettings == null ? null : projectSettings.getTemplate();
        final TemplateLanguage templateLanguage = projectSettings == null ? null : projectSettings.getTemplateLanguage();
        final ModuleAutoConfigSetting projectModuleAutoConfigSetting = projectSettings == null ? null : projectSettings.getModuleAutoConfigSetting();
        final String testLanguageDefaultOption;
        final String templateDefaultOption;
        final boolean templateRequired;
        final boolean promoteTemplateSettingsToProject;
        final DisplayModuleAutoConfigSetting displayModuleAutoConfigSetting;
        final String defaultTemplateSaveAsName;
        if(projectTemplate == null) {
            testLanguageDefaultOption = "Not Specified (required)";
            templateDefaultOption = "Not Specified (required)";
            templateRequired = true;
            promoteTemplateSettingsToProject = true;
            displayModuleAutoConfigSetting = DisplayModuleAutoConfigSetting.fromModuleAutoConfigSetting(ModuleAutoConfigSetting.AUTOMATICALLY_CONFIGURE_WHEN_POSSIBLE);
            defaultTemplateSaveAsName = module.getProject().getName();
        } else {
            testLanguageDefaultOption = String.format("Inherit from project settings (%s)", templateLanguage);
            templateDefaultOption = String.format("Inherit from project settings (%s)", projectTemplate.getPresentationName());
            templateRequired = false;
            promoteTemplateSettingsToProject = false;
            displayModuleAutoConfigSetting = projectModuleAutoConfigSetting == null ?
                    DisplayModuleAutoConfigSetting.fromModuleAutoConfigSetting(ModuleAutoConfigSetting.AUTOMATICALLY_CONFIGURE_WHEN_POSSIBLE)
                    : DisplayModuleAutoConfigSetting.fromModuleAutoConfigSetting(projectModuleAutoConfigSetting);
            defaultTemplateSaveAsName = String.format("%s-%s", module.getProject().getName(), module.getName());
        }
        return new JustInTimeUIConfigInfo(
                testLanguageDefaultOption,
                templateDefaultOption,
                defaultTemplateSaveAsName,
                templateRequired,
                null,
                promoteTemplateSettingsToProject,
                displayModuleAutoConfigSetting,
                new LanguageAndTemplate(null, null));
    }
}
