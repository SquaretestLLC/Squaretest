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

import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.search.GlobalSearchScope;
import com.squaretest.TemplateProvider;
import com.squaretest.settings.store.Template;
import com.squaretest.settings.store.TemplateLanguage;
import org.jetbrains.annotations.Nullable;

public class TemplateSelector {
    private final JavaPsiFacade psiFacade;

    public TemplateSelector(final JavaPsiFacade psiFacade) {
        this.psiFacade = psiFacade;
    }

    public LanguageAndTemplate inferTemplate(final PsiDirectory testSourcesRoot, final TemplateLanguage templateLanguage) {
        if(testSourcesRoot == null || templateLanguage == null) {
            return new LanguageAndTemplate(templateLanguage, null);
        }
        final Template template;
        if(templateLanguage == TemplateLanguage.JAVA) {
            template = findBestJavaTemplate(testSourcesRoot);
        } else {
            template = findBestGroovyTemplate(testSourcesRoot);
        }
        return new LanguageAndTemplate(templateLanguage, template);
    }

    @Nullable
    private Template findBestJavaTemplate(final PsiDirectory testSourcesRoot) {
        final FrameworkInfo frameworkInfo = determineFrameworkInfo(testSourcesRoot);
        if(frameworkInfo.hasJUnit5()) {
            if(frameworkInfo.hasSpring()) {
                if(frameworkInfo.hasAssertJ()) {
                    return TemplateProvider.getJUnit5SpringAssertJJavaTemplate();
                } else {
                    return TemplateProvider.getJUnit5SpringJavaTemplate();
                }
            } else {
                if(frameworkInfo.hasAssertJ()) {
                    return TemplateProvider.getJUnit5AssertJJavaTemplate();
                } else {
                    return TemplateProvider.getJUnit5JavaTemplate();
                }
            }
        } else if(frameworkInfo.hasRobolectric()) {
            return TemplateProvider.getRobolectricJavaTemplate();
        } else if(frameworkInfo.hasAndroidJUnit()) {
            return TemplateProvider.getAndroidJUnit4JavaTemplate();
        } else if(frameworkInfo.hasTestNG()) {
            return TemplateProvider.getTestNGJavaTemplate();
        } else if(frameworkInfo.hasJUnit4()) {
            if(frameworkInfo.hasSpring()) {
                if(frameworkInfo.hasAssertJ()) {
                    return TemplateProvider.getJUnit4SpringAssertJJavaTemplate();
                } else {
                    return TemplateProvider.getJUnit4SpringJavaTemplate();
                }
            } else {
                if(frameworkInfo.hasAssertJ()) {
                    return TemplateProvider.getJUnit4AssertJJavaTemplate();
                } else {
                    return TemplateProvider.getJUnit4JavaTemplate();
                }
            }
        }
        return null;
    }

    @Nullable
    private Template findBestGroovyTemplate(final PsiDirectory testSourcesRoot) {
        final FrameworkInfo frameworkInfo = determineFrameworkInfo(testSourcesRoot);
        if(frameworkInfo.hasJUnit5()) {
            if(frameworkInfo.hasSpring()) {
                if(frameworkInfo.hasAssertJ()) {
                    return TemplateProvider.getJUnit5SpringAssertJGroovyTemplate();
                } else {
                    return TemplateProvider.getJUnit5SpringGroovyTemplate();
                }
            } else {
                if(frameworkInfo.hasAssertJ()) {
                    return TemplateProvider.getJUnit5AssertJGroovyTemplate();
                } else {
                    return TemplateProvider.getJUnit5GroovyTemplate();
                }
            }
        } else if(frameworkInfo.hasRobolectric()) {
            return TemplateProvider.getRobolectricGroovyTemplate();
        } else if(frameworkInfo.hasAndroidJUnit()) {
            return TemplateProvider.getAndroidJUnit4GroovyTemplate();
        } else if(frameworkInfo.hasTestNG()) {
            return TemplateProvider.getTestNGGroovyTemplate();
        } else if(frameworkInfo.hasJUnit4()) {
            if(frameworkInfo.hasSpring()) {
                if(frameworkInfo.hasAssertJ()) {
                    return TemplateProvider.getJUnit4SpringAssertJGroovyTemplate();
                } else {
                    return TemplateProvider.getJUnit4SpringGroovyTemplate();
                }
            } else {
                if(frameworkInfo.hasAssertJ()) {
                    return TemplateProvider.getJUnit4AssertJGroovyTemplate();
                } else {
                    return TemplateProvider.getJUnit4GroovyTemplate();
                }
            }
        }
        return null;
    }

    public FrameworkInfo determineFrameworkInfo(final PsiDirectory testSourcesRoot) {
        final GlobalSearchScope scope = testSourcesRoot.getResolveScope();
        final boolean hasAndroid = psiFacade.findClass("android.app.Activity", scope) != null;
        return new FrameworkInfo(
                psiFacade.findClass("org.junit.jupiter.api.Assertions", scope) != null,
                psiFacade.findClass("org.junit.runners.JUnit4", scope) != null,
                hasAndroid && (
                        psiFacade.findClass("androidx.test.ext.junit.runners.AndroidJUnit4", scope) != null
                                || psiFacade.findClass("androidx.test.runner.AndroidJUnit4", scope) != null
                                || psiFacade.findClass("android.support.test.runner.AndroidJUnit4", scope) != null),
                hasAndroid && psiFacade.findClass("org.robolectric.RobolectricTestRunner", scope) != null,
                psiFacade.findClass("org.testng.TestNG", scope) != null,
                psiFacade.findClass("org.springframework.context.ApplicationContext", scope) != null,
                psiFacade.findClass("org.assertj.core.api.Assertions", scope) != null,
                psiFacade.findClass("com.google.common.truth.Truth", scope) != null);
    }
}
