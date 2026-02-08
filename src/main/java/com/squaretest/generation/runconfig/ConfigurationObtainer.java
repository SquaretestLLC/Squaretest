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

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.squaretest.TemplateProvider;
import com.squaretest.generation.runconfig.infoprovider.JustInTimeUIConfigInfo;
import com.squaretest.generation.runconfig.infoprovider.JustInTimeUIConfigInfoProvider;
import com.squaretest.settings.store.ModuleAutoConfigSetting;
import com.squaretest.settings.store.ModuleSettings;
import com.squaretest.settings.store.ProjectSettings;
import com.squaretest.settings.store.SettingsProvider;
import com.squaretest.settings.store.Template;
import com.squaretest.utils.PathUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigurationObtainer {
    @NotNull
    private final SettingsProvider settingsProvider;
    @NotNull
    private final ModuleSettingsAutoConfigurator moduleSettingsAutoConfigurator;
    @NotNull
    private final JustInTimeUIConfigInfoProvider justInTimeUIConfigInfoProvider;
    @NotNull
    private final TemplateProvider templateProvider;
    @NotNull
    private final PsiManager psiManager;

    public ConfigurationObtainer(
            @NotNull final SettingsProvider settingsProvider,
            @NotNull final ModuleSettingsAutoConfigurator moduleSettingsAutoConfigurator,
            @NotNull final JustInTimeUIConfigInfoProvider justInTimeUIConfigInfoProvider,
            @NotNull final TemplateProvider templateProvider, @NotNull final PsiManager psiManager) {
        this.settingsProvider = settingsProvider;
        this.moduleSettingsAutoConfigurator = moduleSettingsAutoConfigurator;
        this.justInTimeUIConfigInfoProvider = justInTimeUIConfigInfoProvider;
        this.templateProvider = templateProvider;
        this.psiManager = psiManager;
    }

    /**
     * Gets the {@link ConfigInfo} to use to generate the test.
     * This will show any UI necessary to collect the config from the user.
     *
     * @return the {@link ConfigInfo} or null if the user did not provide any.
     */
    @NotNull
    public DetermineConfigResult determineConfig(@NotNull final Project project, @NotNull final Module module, @NotNull final PsiFile sourceFile) {

        // Determine which template to use.
        final FileTemplate template = determineTemplate(module);
        if(template == null) {
            return showConfigUiAndReturnConfigInfo(module, sourceFile);
        }

        // Find the test sources root.
        ModuleSettings moduleSettings = settingsProvider.getModuleSettings(project.getName(), module.getName());
        String testRootFromModuleSettings = moduleSettings == null ? null : moduleSettings.getTestDirectoryRoot();
        if(testRootFromModuleSettings != null) {
            // We've previously configured this module's settings. We have the template
            // (either inherited from project settings or specified in module settings).
            // Ensure the "Test Sources Root" exists on the file system and is valid.
            final String testSourcesDirAbsPath = PathUtils.getAbsolutePath(project, testRootFromModuleSettings);
            final VirtualFile testSourcesDirVirtualFile = LocalFileSystem.getInstance().findFileByPath(testSourcesDirAbsPath);
            if(testSourcesDirVirtualFile == null) {
                return showConfigUiForMissingTestSourcesRoot(module, sourceFile);
            }
            final PsiDirectory psiDirectory = psiManager.findDirectory(testSourcesDirVirtualFile);
            if(psiDirectory == null) {
                return showConfigUiForMissingTestSourcesRoot(module, sourceFile);
            }

            // Ensure the "Test Sources Root" is in a Module.
            final Module testSourcesContainingModule = ModuleUtil.findModuleForFile(testSourcesDirVirtualFile, project);
            if(testSourcesContainingModule == null) {
                return showConfigUiForMissingTestSourcesRoot(module, sourceFile);
            }
            return new DetermineConfigResult(new ConfigInfo(template, psiDirectory, sourceFile));
        }

        if(provisionModuleSettingsAutomaticallyIfPossible(project, module)) {
            moduleSettings = settingsProvider.getModuleSettings(project.getName(), module.getName());
            testRootFromModuleSettings = moduleSettings == null ? null : moduleSettings.getTestDirectoryRoot();
            final String testSourcesDirAbsPath = PathUtils.getAbsolutePath(project, testRootFromModuleSettings);
            final VirtualFile testSourcesDirVirtualFile = LocalFileSystem.getInstance().findFileByPath(testSourcesDirAbsPath);
            final PsiDirectory psiDirectory = psiManager.findDirectory(testSourcesDirVirtualFile);
            return new DetermineConfigResult(new ConfigInfo(template, psiDirectory, sourceFile));
        } else {
            return showConfigUiAndReturnConfigInfo(module, sourceFile);
        }
    }

    /**
     * Shows the config UI and returns the config-info provided by the user or null if the user clicked cancel.
     *
     * @param module     the module for which we need to collect configuration info.
     * @param sourceFile the source file
     * @return the {@link ConfigInfo} provided by the user or null.
     */
    @NotNull
    private DetermineConfigResult showConfigUiAndReturnConfigInfo(final Module module, @NotNull final PsiFile sourceFile) {
        final JustInTimeUIConfigInfo justInTimeUIConfigInfo = justInTimeUIConfigInfoProvider.determineJustInTimeConfigInfo(module);
        final JustInTimeConfigurationDialog dialog = new JustInTimeConfigurationDialog(module, justInTimeUIConfigInfo);
        dialog.show();
        if(dialog.isOK()) {
            return new DetermineConfigResult(safeDetermineConfigInfoFromSettings(module, sourceFile));
        }
        return new DetermineConfigResult(null);
    }

    private DetermineConfigResult showConfigUiForMissingTestSourcesRoot(final Module module, final PsiFile sourceFile) {
        // We have a template to use. The problem is: the "Test Sources Root" specified in the module settings does not
        // exist.
        final JustInTimeUIConfigInfo justInTimeUIConfigInfo = justInTimeUIConfigInfoProvider.determineJustInTimeConfigInfoForMissingTestSourcesRoot(module);
        final JustInTimeConfigurationDialog dialog = new JustInTimeConfigurationDialog(module, justInTimeUIConfigInfo);
        dialog.show();
        if(dialog.isOK()) {
            return new DetermineConfigResult(safeDetermineConfigInfoFromSettings(module, sourceFile));
        }
        return new DetermineConfigResult(null);
    }

    /**
     * Reads the config info from settings. This expects all config info (template, test root) to be available in
     * the settings.
     *
     * @param module     the module for which to get the config info.
     * @param sourceFile the source file
     * @return the {@link ConfigInfo} to use.
     */
    @NotNull
    private ConfigInfo safeDetermineConfigInfoFromSettings(final Module module, @NotNull final PsiFile sourceFile) {
        final FileTemplate template = determineTemplate(module);
        final ModuleSettings moduleSettings = settingsProvider.getModuleSettings(
                module.getProject().getName(), module.getName());
        final String testDirectoryRoot = moduleSettings.getTestDirectoryRoot();
        final String testSourcesDirAbsPath = PathUtils.getAbsolutePath(module.getProject(), testDirectoryRoot);
        final VirtualFile testSourcesDirVirtualFile = LocalFileSystem.getInstance().findFileByPath(testSourcesDirAbsPath);
        final PsiDirectory psiDirectory = psiManager.findDirectory(testSourcesDirVirtualFile);
        return new ConfigInfo(template, psiDirectory, sourceFile);
    }

    private boolean provisionModuleSettingsAutomaticallyIfPossible(
            @NotNull final Project project, @NotNull final Module module) {

        final ProjectSettings projectSettings = settingsProvider.getProjectSettings(project.getName());
        if(projectSettings == null || projectSettings.getModuleAutoConfigSetting() == ModuleAutoConfigSetting.ALWAYS_SHOW_CONFIG) {
            return false;
        }

        // We might be able to configure the module settings.
        return moduleSettingsAutoConfigurator.configureModuleSettingsIfPossible(module);
    }


    /**
     * Determines which {@link Template} to use based on the {@link ProjectSettings} and {@link ModuleSettings}.
     * Order of precedence is:
     * 1. If we have {@link ModuleSettings} with a non-null template, use that.
     * 2. Else, if we have project settings with a non-null template, use that.
     * 3. Else, return null.
     *
     * @param module the module
     * @return the {@link Template} to use or null.
     */
    @Nullable
    private FileTemplate determineTemplate(@NotNull final Module module) {
        final Project project = module.getProject();
        final ProjectSettings projectSettings = settingsProvider.getProjectSettings(project.getName());
        final ModuleSettings moduleSettings = settingsProvider.getModuleSettings(project.getName(), module.getName());
        // If the module settings have a template, use that; otherwise, fallback to the project settings template.
        if(moduleSettings != null) {
            final Template templateFromModuleSettings = moduleSettings.getTemplate();
            if(templateFromModuleSettings != null) {
                return getFileTemplate(templateFromModuleSettings);
            } else {
                return projectSettings == null ? null : getFileTemplate(projectSettings.getTemplate());
            }
        } else {
            return projectSettings == null ? null : getFileTemplate(projectSettings.getTemplate());
        }
    }

    private FileTemplate getFileTemplate(@Nullable final Template template) {
        if(template == null) {
            return null;
        }
        return templateProvider.getFileTemplate(template);
    }

}
