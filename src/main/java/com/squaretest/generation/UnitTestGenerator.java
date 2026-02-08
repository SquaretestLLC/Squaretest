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
package com.squaretest.generation;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiNameValuePair;
import com.squaretest.generation.defaulttypes.DefaultTypesHolder;
import com.squaretest.generation.dependencyinteraction.DependencyFlowInfoProvider;
import com.squaretest.generation.filecreation.FileCreator;
import com.squaretest.generation.filetemplateutil.CannotCreatePackageException;
import com.squaretest.generation.filetemplateutil.DirectoryNotWritableException;
import com.squaretest.generation.filetemplateutil.FileAlreadyExistsException;
import com.squaretest.generation.filetemplateutil.SQFileTemplateUtil;
import com.squaretest.generation.filetemplateutil.TemplateRenderingException;
import com.squaretest.generation.runconfig.ConfigInfo;
import com.squaretest.template.api.Api;
import com.squaretest.template.api.TestInfoFactoryImpl;
import com.squaretest.template.impl.ClassUtilsImpl;
import com.squaretest.template.impl.CodeStyleUtilsImpl;
import com.squaretest.template.impl.TemplateData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Map;

public class UnitTestGenerator {
    @NotNull
    private final PsiToTemplateDataConverter psiToTemplateDataConverter;
    @NotNull
    private final DependencyFlowInfoProvider dependencyFlowInfoProvider;
    @NotNull
    private final Project project;
    @NotNull
    private final JavaPsiFacade javaPsiFacade;
    @NotNull
    private final ConfigInfo configInfo;
    @NotNull
    private final CreateTestFileChecker createTestFileChecker;
    @NotNull
    private final FileCreator fileCreator;
    @NotNull
    private final ExceptionCreator exceptionCreator;
    @NotNull
    private final Api.UiUtils uiUtils;
    @NotNull
    private final Api.CodeStyleUtils codeStyleUtils;
    private final boolean shouldAskToConfirmOptions;

    public UnitTestGenerator(
            @NotNull final PsiToTemplateDataConverter psiToTemplateDataConverter,
            @NotNull final DependencyFlowInfoProvider dependencyFlowInfoProvider,
            @NotNull final Project project, @NotNull final JavaPsiFacade javaPsiFacade,
            @NotNull final ConfigInfo configInfo, @NotNull final CreateTestFileChecker createTestFileChecker,
            @NotNull final FileCreator fileCreator, @NotNull final ExceptionCreator exceptionCreator,
            @NotNull final Api.UiUtils uiUtils, @NotNull final Api.CodeStyleUtils codeStyleUtils,
            final boolean shouldAskToConfirmOptions) {
        this.psiToTemplateDataConverter = psiToTemplateDataConverter;
        this.dependencyFlowInfoProvider = dependencyFlowInfoProvider;
        this.project = project;
        this.javaPsiFacade = javaPsiFacade;
        this.configInfo = configInfo;
        this.createTestFileChecker = createTestFileChecker;
        this.fileCreator = fileCreator;
        this.exceptionCreator = exceptionCreator;
        this.uiUtils = uiUtils;
        this.codeStyleUtils = codeStyleUtils;
        this.shouldAskToConfirmOptions = shouldAskToConfirmOptions;
    }

    public VirtualFile generate() throws TemplateRenderingException, DirectoryNotWritableException, FileAlreadyExistsException, CannotCreatePackageException, Api.UserCancelledGenerationException {
        // Store the template and sources root in local variables.
        final FileTemplate template = configInfo.template();
        final PsiDirectory testSourcesRoot = configInfo.testSourcesDir();
        // Convert the provided Java file into the template data to use in the velocity template.
        final TemplateData templateData = psiToTemplateDataConverter.convertToTemplate(template, (PsiJavaFile) this.configInfo.sourceFile());
        final boolean springWebMvcTestIncludesSpringExtension = determineIfWebMvcTestIncludesSpringExtension(javaPsiFacade, testSourcesRoot);

        final OutcomeProvider outcomeProvider = new OutcomeProvider(dependencyFlowInfoProvider, templateData.getMainSourceClassPsiToTemplateVarsMapper(), templateData.getClassUnderTest());
        ((CodeStyleUtilsImpl) codeStyleUtils).clearNameConflictMaps();
        final Map<String, Object> templateVars = TemplateVariableMapUtil.createMapForTemplate(
                templateData,
                new TestInfoFactoryImpl(outcomeProvider, exceptionCreator),
                codeStyleUtils,
                new ClassUtilsImpl(testSourcesRoot, javaPsiFacade, psiToTemplateDataConverter, DefaultTypesHolder.getInstance()),
                uiUtils,
                shouldAskToConfirmOptions, springWebMvcTestIncludesSpringExtension);
        // Generate the test class.
        return fileCreator.createTestClass(project, template, testSourcesRoot, templateVars);
    }

    @Nullable
    public VirtualFile runTemplateAndReturnExistingTestClass() {
        // Select the template to use based on user settings.
        final FileTemplate template = configInfo.template();
        final PsiDirectory testSourcesRoot = configInfo.testSourcesDir();

        // Populate the velocity template variables.
        final TemplateData templateData = psiToTemplateDataConverter.convertToTemplate(template, (PsiJavaFile) configInfo.sourceFile());
        final OutcomeProvider outcomeProvider = new OutcomeProvider(dependencyFlowInfoProvider, templateData.getMainSourceClassPsiToTemplateVarsMapper(), templateData.getClassUnderTest());
        ((CodeStyleUtilsImpl) codeStyleUtils).clearNameConflictMaps();
        final JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(project);
        final boolean springWebMvcTestIncludesSpringExtension = determineIfWebMvcTestIncludesSpringExtension(javaPsiFacade, testSourcesRoot);
        final Map<String, Object> templateVars = TemplateVariableMapUtil.createMapForTemplate(
                templateData,
                new TestInfoFactoryImpl(outcomeProvider, exceptionCreator),
                codeStyleUtils,
                new ClassUtilsImpl(testSourcesRoot, JavaPsiFacade.getInstance(project), psiToTemplateDataConverter, DefaultTypesHolder.getInstance()),
                uiUtils,
                false, springWebMvcTestIncludesSpringExtension);
        return SQFileTemplateUtil.runTemplateAndReturnExistingFile(template, templateVars, testSourcesRoot, createTestFileChecker);
    }

    public static boolean determineIfWebMvcTestIncludesSpringExtension(final JavaPsiFacade javaPsiFacade, final PsiDirectory testSourcesRoot) {
        final PsiClass psiClass = javaPsiFacade.findClass("org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest", testSourcesRoot.getResolveScope());
        if(psiClass == null) {
            // Assume the user is using a modern version of Spring where WebMvcTest includes the SpringExtension.
            return true;
        }
        final PsiAnnotation[] annotations = psiClass.getAnnotations();
        return Arrays.stream(annotations).anyMatch(x -> {
            if(!x.hasQualifiedName("org.junit.jupiter.api.extension.ExtendWith")) {
                return false;
            }
            final PsiNameValuePair[] attributes = x.getParameterList().getAttributes();
            return Arrays.stream(attributes).anyMatch(attr -> attr.getText().contains("SpringExtension.class"));
        });
    }
}
