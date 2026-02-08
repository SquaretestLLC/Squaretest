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

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;
import com.squaretest.ModuleTestSourcesRootFinder;
import com.squaretest.SuggestedRootInfo;
import com.squaretest.TemplateProvider;
import com.squaretest.completion.TemplateInfo;
import com.squaretest.completion.frameworkdetection.FrameworkInferencer;
import com.squaretest.generation.runconfig.infoprovider.FrameworkInfo;
import com.squaretest.generation.runconfig.infoprovider.TemplateSelector;
import com.squaretest.generation.runconfig.infoprovider.TestRootSelector;
import com.squaretest.settings.store.ModuleSettings;
import com.squaretest.settings.store.ProjectSettings;
import com.squaretest.settings.store.SettingsProvider;
import com.squaretest.settings.store.Template;
import com.squaretest.settings.store.TemplateLanguage;
import com.squaretest.utils.PathUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class ModuleSettingsAutoConfigurator {

    @NotNull
    private final SettingsProvider settingsProvider;
    @NotNull
    private final TemplateProvider templateProvider;
    @NotNull
    private final FrameworkInferencer frameworkInferencer;
    @NotNull
    private final TemplateSelector templateSelector;
    @NotNull
    private final PsiManager psiManager;

    public ModuleSettingsAutoConfigurator(
            @NotNull final SettingsProvider settingsProvider,
            @NotNull final TemplateProvider templateProvider,
            @NotNull final FrameworkInferencer frameworkInferencer,
            @NotNull final TemplateSelector templateSelector,
            @NotNull final PsiManager psiManager) {
        this.settingsProvider = settingsProvider;
        this.templateProvider = templateProvider;
        this.frameworkInferencer = frameworkInferencer;
        this.templateSelector = templateSelector;
        this.psiManager = psiManager;
    }

    /**
     * Tries to configure the module settings. Returns true if it was able to configure them; false otherwise.
     * If this returns true, the module will have settings with the test-sources-root set.
     *
     * @return true if the module settings were automatically configured.
     */
    public boolean configureModuleSettingsIfPossible(@NotNull final Module module) {
        final Project project = module.getProject();
        final ProjectSettings projectSettings = settingsProvider.getProjectSettings(project.getName());
        if(projectSettings == null) {
            // We can't configure the module settings if the project-settings are null.
            return false;
        }
        final Template projectTemplate = projectSettings.getTemplate();
        if(projectTemplate == null) {
            // We can't configure the module settings if project template is null; the module-settings can't inherit
            // the template if no template is specified.
            return false;
        }
        // This should not be necessary; you cannot have a ProjectSettings with template!=null and
        // TemplateLanguage == null; the UI stops you from doing this.
        final TemplateLanguage projectTemplateLanguage = projectSettings.getTemplateLanguage();
        if(projectTemplateLanguage == null) {
            return false;
        }

        final VirtualFile contentRoot = TestRootSelector.getOnlyContentRoot(module);
        final SuggestedRootInfo suggestedRootInfo = ModuleTestSourcesRootFinder.suggestTestRootsForModule(module);
        List<VirtualFile> currentWorkingSet = suggestedRootInfo.testRoots();
        if(suggestedRootInfo.includesDependentModules()) {
            // If we have roots under the module, select those.
            if(contentRoot != null) {
                final String contentRootPath = contentRoot.getPath();
                final List<VirtualFile> rootsUnderTheModule = currentWorkingSet.stream().filter(x -> x.getPath().startsWith(contentRootPath)).collect(Collectors.toList());
                if(!rootsUnderTheModule.isEmpty()) {
                    currentWorkingSet = rootsUnderTheModule;
                }
            }
        }

        // Filter out integration tests.
        currentWorkingSet = PathUtils.removeDuplicates(PathUtils.filterOutIntegrationTestRoots(currentWorkingSet));

        // Find test roots that are confirmed to be groovy-roots; confirmed means the folder is named groovy/ or
        // contains a subfolder(s) with groovy file(s).
        final List<VirtualFile> confirmedGroovyRoots = PathUtils.findConfirmedGroovyRoots(currentWorkingSet);
        if(!confirmedGroovyRoots.isEmpty()) {
            if(projectTemplateLanguage != TemplateLanguage.GROOVY) {
                // We have atleast 1 groovy root but the project template-language is java. The user is likely creating
                // groovy tests for this module. return false to allow him/her to select the template to use.
                return false;
            }
            if(confirmedGroovyRoots.size() == 1) {
                if(hasCompatibleTestFramework(projectSettings.getTemplate(), confirmedGroovyRoots.get(0))) {
                    configureModuleSettings(module, confirmedGroovyRoots.get(0));
                    return true;
                } else {
                    return false;
                }
            } else {
                // There is more than 1 groovy root; the user needs to pick the correct one to use.
                return false;
            }
        }

        // No confirmed groovy-roots. Look for confirmed java-roots.
        final List<VirtualFile> confirmedJavaRoots = PathUtils.findConfirmedJavaRoots(currentWorkingSet);
        if(!confirmedJavaRoots.isEmpty()) {
            if(projectTemplateLanguage != TemplateLanguage.JAVA) {
                // We have a java root but the project template-language is groovy. The user is likely creating
                // groovy tests for another module, but not for this one; return false to allow him/her to select the
                // template to use.
                return false;
            }
            if(confirmedJavaRoots.size() == 1) {
                if(hasCompatibleTestFramework(projectSettings.getTemplate(), confirmedJavaRoots.get(0))) {
                    configureModuleSettings(module, confirmedJavaRoots.get(0));
                    return true;
                } else {
                    return false;
                }
            } else {
                // There is more than 1 java root; the user needs to pick the correct one to use.
                return false;
            }
        }

        // No confirmed java or groovy roots. The module might have empty test-roots (newly created module).
        if(currentWorkingSet.size() == 1) {
            // If we have a single Test Sources Root with no files in it, configure the module to use it along with
            // the project-settings.
            configureModuleSettings(module, currentWorkingSet.get(0));
            return true;
        }

        return false;
    }

    private boolean hasCompatibleTestFramework(@NotNull final Template templateFromProjectSettings, @NotNull VirtualFile moduleTestSourcesRoot) {

        // Determine the frameworks used in the template.
        final TemplateInfo templateInfo = new TemplateInfo(templateProvider, templateFromProjectSettings);
        final FrameworkInfo templateFramework = frameworkInferencer.determineFrameworkInfoFromTemplate(templateInfo);

        // Determine the frameworks available on the test sources root.
        final PsiDirectory psiDirectory = psiManager.findDirectory(moduleTestSourcesRoot);
        if(psiDirectory == null) {
            return false;
        }
        final FrameworkInfo testSourcesRootFramework = templateSelector.determineFrameworkInfo(psiDirectory);

        return areCompatible(templateFramework, testSourcesRootFramework);
    }

    static boolean areCompatible(
            @NotNull final FrameworkInfo templateFramework,
            @NotNull final FrameworkInfo testSourcesRootFramework) {
        if(templateFramework.hasJUnit5()) {
            if(!testSourcesRootFramework.hasJUnit5()) {
                return false;
            }
            if(templateFramework.hasSpring() != testSourcesRootFramework.hasSpring()) {
                return false;
            }
            if(templateFramework.hasSpring()) {
                // If the template has assertJ, but the test sources root does not, return false.
                // This shouldn't happen, because SpringBootTest comes with AssertJ, but it's good to check.
                if(templateFramework.hasAssertJ() && !testSourcesRootFramework.hasAssertJ()) {
                    return false;
                }
                if(templateFramework.hasGoogleTruth() && !testSourcesRootFramework.hasGoogleTruth()) {
                    return false;
                }
                // Both have Spring. Inherit the AssertJ preference from the project template.
                return true;
            }
            if(templateFramework.hasAndroidJUnit() != testSourcesRootFramework.hasAndroidJUnit()) {
                return false;
            }
            if(templateFramework.hasRobolectric() != testSourcesRootFramework.hasRobolectric()) {
                return false;
            }
            if(templateFramework.hasGoogleTruth()) {
                if(!testSourcesRootFramework.hasGoogleTruth()) {
                    return false;
                }
                return true;
            }
            if(templateFramework.hasAssertJ() != testSourcesRootFramework.hasAssertJ()) {
                return false;
            }
            return true;
        }

        if(templateFramework.hasAndroidJUnit()) {
            if(!testSourcesRootFramework.hasAndroidJUnit()) {
                return false;
            }
            if(testSourcesRootFramework.hasJUnit5()) {
                return false;
            }
            if(testSourcesRootFramework.hasRobolectric()) {
                return false;
            }
            if(templateFramework.hasGoogleTruth()) {
                if(!testSourcesRootFramework.hasGoogleTruth()) {
                    return false;
                }
                return true;
            }
            if(templateFramework.hasAssertJ()) {
                return testSourcesRootFramework.hasAssertJ();
            }
            return true;
        }

        if(templateFramework.hasRobolectric()) {
            if(!testSourcesRootFramework.hasRobolectric()) {
                return false;
            }
            if(testSourcesRootFramework.hasJUnit5()) {
                return false;
            }
            if(testSourcesRootFramework.hasAndroidJUnit()) {
                return false;
            }
            if(templateFramework.hasGoogleTruth()) {
                if(!testSourcesRootFramework.hasGoogleTruth()) {
                    return false;
                }
                return true;
            }
            if(templateFramework.hasAssertJ()) {
                return testSourcesRootFramework.hasAssertJ();
            }
            return true;
        }

        if(templateFramework.hasTestNG()) {
            if(!testSourcesRootFramework.hasTestNG()) {
                return false;
            }
            if(testSourcesRootFramework.hasJUnit5() || testSourcesRootFramework.hasJUnit4()) {
                return false;
            }
            if(testSourcesRootFramework.hasSpring()) {
                return false;
            }
            if(testSourcesRootFramework.hasRobolectric() || testSourcesRootFramework.hasAndroidJUnit()) {
                return false;
            }
            if(templateFramework.hasGoogleTruth()) {
                if(!testSourcesRootFramework.hasGoogleTruth()) {
                    return false;
                }
                return true;
            }
            if(templateFramework.hasAssertJ()) {
                return testSourcesRootFramework.hasAssertJ();
            }
            return true;
        }

        if(templateFramework.hasJUnit4()) {
            if(!testSourcesRootFramework.hasJUnit4()) {
                return false;
            }
            if(testSourcesRootFramework.hasJUnit5() || testSourcesRootFramework.hasTestNG()) {
                return false;
            }
            if(templateFramework.hasSpring() != testSourcesRootFramework.hasSpring()) {
                return false;
            }
            if(testSourcesRootFramework.hasAndroidJUnit() || testSourcesRootFramework.hasRobolectric()) {
                return false;
            }
            if(templateFramework.hasSpring()) {
                // If the template has assertJ, but the test sources root does not, return false.
                // This shouldn't happen, because SpringBootTest comes with AssertJ, but it's good to check.
                if(templateFramework.hasAssertJ() && !testSourcesRootFramework.hasAssertJ()) {
                    return false;
                }
                if(templateFramework.hasGoogleTruth() && !testSourcesRootFramework.hasGoogleTruth()) {
                    return false;
                }
                // Both have Spring. Inherit the AssertJ or GoogleTruth preference from the project template.
                return true;
            }
            if(templateFramework.hasGoogleTruth()) {
                if(!testSourcesRootFramework.hasGoogleTruth()) {
                    return false;
                }
                return true;
            }
            if(templateFramework.hasAssertJ() != testSourcesRootFramework.hasAssertJ()) {
                return false;
            }
            return true;
        }
        return false;
    }

    private void configureModuleSettings(@NotNull final Module module, @NotNull final VirtualFile testSourcesRoot) {
        final Project project = module.getProject();
        ModuleSettings moduleSettings = settingsProvider.getModuleSettings(project.getName(), module.getName());
        final String relativeTestRootPathInProject = PathUtils.getRelativePathFromProjectRootIfInProject(
                project, testSourcesRoot.getPath());
        if(moduleSettings == null) {
            moduleSettings = new ModuleSettings(
                    module.getName(),
                    null,
                    null,
                    relativeTestRootPathInProject);
        } else {
            moduleSettings = new ModuleSettings(
                    moduleSettings.getName(),
                    moduleSettings.getTemplateLanguage(),
                    moduleSettings.getTemplate(),
                    relativeTestRootPathInProject);
        }
        this.settingsProvider.putModuleSettings(project.getName(), moduleSettings);
    }
}
