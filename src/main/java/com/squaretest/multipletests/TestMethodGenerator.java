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
package com.squaretest.multipletests;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import com.squaretest.completion.CompletionUtils;
import com.squaretest.confirmsettings.ExistingTestClassSettingsBroker;
import com.squaretest.confirmsettings.v1.ExistingTestClassSettingsConfirmerV1;
import com.squaretest.confirmsettings.v2.ExistingTestClassSettingsConfirmerV2;
import com.squaretest.generation.CreateTestFileChecker;
import com.squaretest.generation.ExceptionCreator;
import com.squaretest.generation.OutcomeProvider;
import com.squaretest.generation.PsiToTemplateDataConverter;
import com.squaretest.generation.TemplateVariableMapUtil;
import com.squaretest.generation.dependencyinteraction.DependencyFlowInfoProvider;
import com.squaretest.generation.filetemplateutil.SQFileTemplateUtil;
import com.squaretest.generation.filetemplateutil.TemplateRenderingException;
import com.squaretest.template.api.Api;
import com.squaretest.template.api.TestInfoFactoryImpl;
import com.squaretest.template.impl.TemplateData;
import com.squaretest.template.impl.UiUtilsImpl;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.squaretest.generation.UnitTestGenerator.determineIfWebMvcTestIncludesSpringExtension;

public class TestMethodGenerator {

    private final Project project;
    private final JavaPsiFacade javaPsiFacade;
    private final PsiToTemplateDataConverter psiToTemplateDataConverter;
    private final DependencyFlowInfoProvider dependencyFlowInfoProvider;
    private final ExceptionCreator exceptionCreator;
    private final Api.CodeStyleUtils codeStyleUtils;
    private final Api.ClassUtils classUtils;
    private final CreateTestFileChecker createTestFileChecker;
    private final PsiJavaFile sourceClass;
    private final PsiClass existingTestClass;

    public TestMethodGenerator(
            final Project project, final JavaPsiFacade javaPsiFacade, final PsiToTemplateDataConverter psiToTemplateDataConverter,
            final DependencyFlowInfoProvider dependencyFlowInfoProvider, final ExceptionCreator exceptionCreator, final Api.CodeStyleUtils codeStyleUtils, final Api.ClassUtils classUtils,
            final CreateTestFileChecker createTestFileChecker, final PsiJavaFile sourceClass,
            final PsiClass existingTestClass) {
        this.project = project;
        this.javaPsiFacade = javaPsiFacade;
        this.psiToTemplateDataConverter = psiToTemplateDataConverter;
        this.dependencyFlowInfoProvider = dependencyFlowInfoProvider;
        this.exceptionCreator = exceptionCreator;
        this.codeStyleUtils = codeStyleUtils;
        this.classUtils = classUtils;
        this.createTestFileChecker = createTestFileChecker;
        this.sourceClass = sourceClass;
        this.existingTestClass = existingTestClass;
    }

    public List<PsiMethod> safeRunTemplateAndReturnTestMethods(final FileTemplate fileTemplate) {
        try {
            return runTemplateAndReturnTestMethods(fileTemplate);
        } catch(TemplateRenderingException e) {
            return Collections.emptyList();
        }
    }

    public List<PsiMethod> runTemplateAndReturnTestMethods(
            final FileTemplate fileTemplate) throws TemplateRenderingException {
        final TemplateData templateData = psiToTemplateDataConverter.convertToTemplate(fileTemplate, sourceClass, existingTestClass);
        final PsiClass generatedClass = renderTestClassToInMemoryFile(templateData, fileTemplate, new UiUtilsImpl(
                new ExistingTestClassSettingsBroker(
                        new ExistingTestClassSettingsConfirmerV1(templateData.getClassUnderTest(), templateData.getTestClassInfo()),
                        new ExistingTestClassSettingsConfirmerV2(templateData.getClassUnderTest(), templateData.getTestClassInfo()))));
        final List<PsiMethod> generatedMethods = Arrays.stream(generatedClass.getMethods()).filter(CompletionUtils::isLikelyTestMethod).collect(Collectors.toList());
        for(final PsiMethod generatedMethod : generatedMethods) {
            CompletionUtils.renameTestMethodToAvoidCollisions(existingTestClass, generatedMethod);
        }
        return generatedMethods;
    }

    @Nullable
    private PsiClass renderTestClassToInMemoryFile(
            final TemplateData templateData, final FileTemplate fileTemplate,
            final Api.UiUtils uiUtils) throws TemplateRenderingException {
        final OutcomeProvider outcomeProvider = new OutcomeProvider(dependencyFlowInfoProvider, templateData.getMainSourceClassPsiToTemplateVarsMapper(), templateData.getClassUnderTest());
        final boolean springWebMvcTestIncludesSpringExtension = determineIfWebMvcTestIncludesSpringExtension(javaPsiFacade, existingTestClass.getContainingFile().getContainingDirectory());
        final Map<String, Object> templateMap = TemplateVariableMapUtil.createMapForTemplate(
                templateData, new TestInfoFactoryImpl(outcomeProvider, exceptionCreator),
                codeStyleUtils, classUtils, uiUtils, true, springWebMvcTestIncludesSpringExtension);
        return SQFileTemplateUtil.runTemplateAndReturnInMemoryFile(project, fileTemplate, templateMap, createTestFileChecker);
    }
}
