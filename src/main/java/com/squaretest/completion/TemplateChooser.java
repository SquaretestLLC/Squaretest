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

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiImportList;
import com.intellij.psi.PsiImportStatementBase;
import com.intellij.psi.PsiJavaFile;
import com.squaretest.TemplateProvider;
import com.squaretest.completion.frameworkdetection.FrameworkInferencer;
import com.squaretest.completion.frameworkdetection.JUnitUtil;
import com.squaretest.completion.frameworkdetection.TestNGUtil;
import com.squaretest.generation.existingtest.main.MemberFieldPopulator;
import com.squaretest.generation.runconfig.infoprovider.FrameworkInfo;
import com.squaretest.settings.store.Template;
import com.squaretest.settings.store.TemplateLanguage;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.groovy.GroovyFileType;
import org.jetbrains.plugins.groovy.lang.psi.GroovyFile;
import org.jetbrains.plugins.groovy.lang.psi.api.toplevel.imports.GrImportStatement;

import java.util.Arrays;

public class TemplateChooser {
    private static final String[] SpringMemberCanonicalNames = new String[]{"org.springframework.test.web.servlet.MockMvc", "org.springframework.boot.test.web.client.TestRestTemplate"};
    private final TemplateSettingsFinder templateSettingsFinder;
    private final FrameworkInferencer frameworkInferencer;
    private final TemplateProvider templateProvider;

    public TemplateChooser(
            final TemplateSettingsFinder templateSettingsFinder, final FrameworkInferencer frameworkInferencer,
            final TemplateProvider templateProvider) {
        this.templateSettingsFinder = templateSettingsFinder;
        this.frameworkInferencer = frameworkInferencer;
        this.templateProvider = templateProvider;
    }

    @NotNull
    public TemplateInfo selectTemplateToUse(final PsiClass testClass, final Module moduleContainingSourceClass) {

        final TemplateInfo templateInfoFromSettings = templateSettingsFinder.getTemplateInfo(moduleContainingSourceClass);
        if(templateInfoFromSettings == null) {
            return chooseTemplateBasedOnFileContents(testClass);
        }

        final Template template = templateInfoFromSettings.getTemplate();
        if(template.getTemplateLanguage() == TemplateLanguage.JAVA) {
            if(testClass.getContainingFile().getFileType() == JavaFileType.INSTANCE) {
                // We have a Java template and a Java file.
                // Check to see if the test framework matches.
                return chooseAppropriateJavaTemplate(templateInfoFromSettings, testClass);
            } else {
                // We have a Java template and a Groovy file. Select the best Groovy template to use based on file contents.
                return chooseTemplateBasedOnFileContents(testClass);
            }
        } else {
            if(testClass.getContainingFile().getFileType() == GroovyFileType.GROOVY_FILE_TYPE) {
                // We have a Groovy file type and a groovy template.
                // Check to see if the test framework matches.
                return chooseAppropriateGroovyTemplate(templateInfoFromSettings, testClass);
            } else {
                // We have a Java template and a Groovy file. Select the best template to use based on the file contents.
                return chooseTemplateBasedOnFileContents(testClass);
            }
        }
    }

    private TemplateInfo chooseAppropriateGroovyTemplate(final TemplateInfo templateFromSettings, final PsiClass testClass) {
        final FrameworkInfo frameworkInfoFromTemplate = frameworkInferencer.determineFrameworkInfoFromTemplate(templateFromSettings);
        if(frameworkInfoFromTemplate.hasJUnit5()) {
            if(JUnitUtil.isJUnit5TestClass(testClass, false)) {
                return templateFromSettings;
            } else if(JUnitUtil.isJUnit4TestClass(testClass, false)) {
                return chooseAppropriateGroovyJUnit4Template(testClass);
            } else if(TestNGUtil.isTestNGClass(testClass)) {
                return new TemplateInfo(templateProvider, TemplateProvider.getTestNGGroovyTemplate());
            } else {
                return templateFromSettings;
            }
        } else if(frameworkInfoFromTemplate.hasJUnit4()) {
            if(JUnitUtil.isJUnit4TestClass(testClass, false)) {
                return templateFromSettings;
            } else if(JUnitUtil.isJUnit5TestClass(testClass, false)) {
                return chooseAppropriateGroovyJUnit5Template(testClass);
            } else if(TestNGUtil.isTestNGClass(testClass)) {
                return new TemplateInfo(templateProvider, TemplateProvider.getTestNGGroovyTemplate());
            } else {
                return templateFromSettings;
            }
        } else if(frameworkInfoFromTemplate.hasTestNG()) {
            if(TestNGUtil.isTestNGClass(testClass)) {
                return templateFromSettings;
            } else if(JUnitUtil.isJUnit5TestClass(testClass, false)) {
                return chooseAppropriateGroovyJUnit5Template(testClass);
            } else if(JUnitUtil.isJUnit4TestClass(testClass, false)) {
                return chooseAppropriateGroovyJUnit4Template(testClass);
            } else {
                return templateFromSettings;
            }
        } else {
            return templateFromSettings;
        }
    }

    private TemplateInfo chooseAppropriateJavaTemplate(
            final TemplateInfo templateFromSettings, final PsiClass testClass) {
        final FrameworkInfo frameworkInfoFromTemplate = frameworkInferencer.determineFrameworkInfoFromTemplate(templateFromSettings);
        // Check to see if the existing test classes matches the framework from the template.
        // Note: The order in which we do these checks matters. In each if-block, we need to check to see if the frameworkInfoFromTemplate is detected
        // in the existing test class first. The reason is: a user could have a JUnit5 test that imports certain items from JUnit4 packages;
        // in this scenario, both JUnitUtil.isJUnit5TestClass(...) and JUnitUtil.isJUnit4TestClass(...) will return true. We want to use JUnit5,
        // though.
        if(frameworkInfoFromTemplate.hasJUnit5()) {
            if(JUnitUtil.isJUnit5TestClass(testClass, false)) {
                return templateFromSettings;
            } else if(JUnitUtil.isJUnit4TestClass(testClass, false)) {
                return chooseAppropriateJavaJUnit4Template(testClass);
            } else if(TestNGUtil.isTestNGClass(testClass)) {
                return new TemplateInfo(templateProvider, TemplateProvider.getTestNGJavaTemplate());
            } else {
                // Return the template from settings.
                return templateFromSettings;
            }
        } else if(frameworkInfoFromTemplate.hasJUnit4()) {
            if(JUnitUtil.isJUnit4TestClass(testClass, false)) {
                return templateFromSettings;
            } else if(JUnitUtil.isJUnit5TestClass(testClass, false)) {
                return chooseAppropriateJavaJUnit5Template(testClass);
            } else if(TestNGUtil.isTestNGClass(testClass)) {
                return new TemplateInfo(templateProvider, TemplateProvider.getTestNGJavaTemplate());
            } else {
                // Return the template from settings.
                return templateFromSettings;
            }
        } else if(frameworkInfoFromTemplate.hasTestNG()) {
            if(TestNGUtil.isTestNGClass(testClass)) {
                return templateFromSettings;
            } else if(JUnitUtil.isJUnit5TestClass(testClass, false)) {
                return chooseAppropriateJavaJUnit5Template(testClass);
            } else if(JUnitUtil.isJUnit4TestClass(testClass, false)) {
                return chooseAppropriateJavaJUnit4Template(testClass);
            } else {
                // Return the template from settings.
                return templateFromSettings;
            }
        } else {
            // Return the template from settings.
            return templateFromSettings;
        }
    }

    @NotNull
    private TemplateInfo chooseAppropriateJavaJUnit5Template(final PsiClass testClass) {
        final boolean hasAssertJ = hasAssertJ(testClass);
        final boolean hasSpring = hasSpring(testClass);

        // If the test class uses Spring, choose the best Spring template.
        if(hasSpring) {
            if(hasAssertJ) {
                return new TemplateInfo(templateProvider, TemplateProvider.getJUnit5SpringAssertJJavaTemplate());
            } else {
                return new TemplateInfo(templateProvider, TemplateProvider.getJUnit5SpringJavaTemplate());
            }
        }

        // Otherwise, choose the best template.
        if(hasAssertJ(testClass)) {
            return new TemplateInfo(templateProvider, TemplateProvider.getJUnit5AssertJJavaTemplate());
        } else {
            return new TemplateInfo(templateProvider, TemplateProvider.getJUnit5JavaTemplate());
        }
    }

    @NotNull
    private TemplateInfo chooseAppropriateJavaJUnit4Template(final PsiClass testClass) {
        final boolean hasAssertJ = hasAssertJ(testClass);
        final boolean hasSpring = hasSpring(testClass);

        // If the test class uses Spring, choose the best Spring template.
        if(hasSpring) {
            if(hasAssertJ) {
                return new TemplateInfo(templateProvider, TemplateProvider.getJUnit4SpringAssertJJavaTemplate());
            } else {
                return new TemplateInfo(templateProvider, TemplateProvider.getJUnit4SpringJavaTemplate());
            }
        }

        // Otherwise, choose the best template.
        if(hasAssertJ(testClass)) {
            return new TemplateInfo(templateProvider, TemplateProvider.getJUnit4AssertJJavaTemplate());
        } else {
            return new TemplateInfo(templateProvider, TemplateProvider.getJUnit4JavaTemplate());
        }
    }

    @NotNull
    private TemplateInfo chooseAppropriateGroovyJUnit5Template(final PsiClass testClass) {
        final boolean hasAssertJ = hasAssertJ(testClass);
        final boolean hasSpring = hasSpring(testClass);

        // If the test class uses Spring, choose the best Spring template.
        if(hasSpring) {
            if(hasAssertJ) {
                return new TemplateInfo(templateProvider, TemplateProvider.getJUnit5SpringAssertJGroovyTemplate());
            } else {
                return new TemplateInfo(templateProvider, TemplateProvider.getJUnit5SpringGroovyTemplate());
            }
        }

        // Otherwise, choose the best template.
        if(hasAssertJ(testClass)) {
            return new TemplateInfo(templateProvider, TemplateProvider.getJUnit5AssertJGroovyTemplate());
        } else {
            return new TemplateInfo(templateProvider, TemplateProvider.getJUnit5GroovyTemplate());
        }
    }

    @NotNull
    private TemplateInfo chooseAppropriateGroovyJUnit4Template(final PsiClass testClass) {
        final boolean hasAssertJ = hasAssertJ(testClass);
        final boolean hasSpring = hasSpring(testClass);

        // If the test class uses Spring, choose the best Spring template.
        if(hasSpring) {
            if(hasAssertJ) {
                return new TemplateInfo(templateProvider, TemplateProvider.getJUnit4SpringAssertJGroovyTemplate());
            } else {
                return new TemplateInfo(templateProvider, TemplateProvider.getJUnit4SpringGroovyTemplate());
            }
        }

        // Otherwise, choose the best template.
        if(hasAssertJ(testClass)) {
            return new TemplateInfo(templateProvider, TemplateProvider.getJUnit4AssertJGroovyTemplate());
        } else {
            return new TemplateInfo(templateProvider, TemplateProvider.getJUnit4GroovyTemplate());
        }
    }

    private static boolean hasSpring(final PsiClass testClass) {
        final PsiField[] fields = testClass.getAllFields();
        for(final PsiField field : fields) {
            final PsiClass psiClass = MemberFieldPopulator.getClassFromType(field.getType());
            if(psiClass != null && StringUtils.equalsAny(psiClass.getQualifiedName(), SpringMemberCanonicalNames)) {
                return true;
            }
        }
        return false;
    }

    private TemplateInfo chooseTemplateBasedOnFileContents(final PsiClass testClass) {
        if(testClass.getContainingFile().getFileType() == JavaFileType.INSTANCE) {
            if(JUnitUtil.isJUnit5TestClass(testClass, false)) {
                return chooseAppropriateJavaJUnit5Template(testClass);
            } else if(JUnitUtil.isJUnit4TestClass(testClass, false)) {
                return chooseAppropriateJavaJUnit4Template(testClass);
            } else if(TestNGUtil.isTestNGClass(testClass)) {
                return new TemplateInfo(templateProvider, TemplateProvider.getTestNGJavaTemplate());
            } else {
                return new TemplateInfo(templateProvider, TemplateProvider.getJUnit4JavaTemplate());
            }
        } else {
            if(JUnitUtil.isJUnit5TestClass(testClass, false)) {
                return chooseAppropriateGroovyJUnit5Template(testClass);
            } else if(JUnitUtil.isJUnit4TestClass(testClass, false)) {
                return chooseAppropriateGroovyJUnit4Template(testClass);
            } else if(TestNGUtil.isTestNGClass(testClass)) {
                return new TemplateInfo(templateProvider, TemplateProvider.getTestNGGroovyTemplate());
            } else {
                return new TemplateInfo(templateProvider, TemplateProvider.getJUnit4GroovyTemplate());
            }
        }
    }

    private static boolean hasAssertJ(@NotNull final PsiClass testClass) {
        final PsiFile containingFile = testClass.getContainingFile();
        if(containingFile instanceof final PsiJavaFile javaFile) {
            final PsiImportList importList = javaFile.getImportList();
            if(importList == null) {
                return false;
            }
            final PsiImportStatementBase[] importStatements = importList.getAllImportStatements();
            return Arrays.stream(importStatements).anyMatch(x -> x.getText().trim().contains("org.assertj."));
        } else if(containingFile instanceof final GroovyFile groovyFile) {
            final GrImportStatement[] importStatements = groovyFile.getImportStatements();
            if(importStatements == null) {
                return false;
            }
            return Arrays.stream(importStatements).anyMatch(x -> x.getText().trim().contains("org.assertj."));
        }
        return false;
    }
}
