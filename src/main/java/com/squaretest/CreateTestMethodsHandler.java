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
package com.squaretest;

import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.generation.GenerateMembersHandlerBase;
import com.intellij.codeInsight.generation.GenerateMembersUtil;
import com.intellij.codeInsight.generation.GenerationInfo;
import com.intellij.codeInsight.generation.TemplateGenerationInfo;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassOwner;
import com.intellij.psi.PsiConstantEvaluationHelper;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.util.containers.ContainerUtil;
import com.squaretest.annotations.AnnotationCreator;
import com.squaretest.annotations.IPropertyResolver;
import com.squaretest.annotations.MainPropertyResolver;
import com.squaretest.annotations.PropertyResolver;
import com.squaretest.annotations.SpringReferenceResolver;
import com.squaretest.completion.ProjectSearcher;
import com.squaretest.completion.SourceClassFinder;
import com.squaretest.completion.TemplateChooser;
import com.squaretest.completion.TemplateInfo;
import com.squaretest.completion.TemplateSettingsFinder;
import com.squaretest.completion.frameworkdetection.FrameworkInferencer;
import com.squaretest.completion.util.CleanUpUtils;
import com.squaretest.confirmsettings.v1.TemplateValidator;
import com.squaretest.generation.*;
import com.squaretest.generation.abstractmethods.AbstractClassInfoProvider;
import com.squaretest.generation.cleanup.CodeFormatterUtil;
import com.squaretest.generation.dataflow.DataflowAnalyzer;
import com.squaretest.generation.dataflow.usedmethods.CalledMethodsInfoProvider;
import com.squaretest.generation.dataflow.usedmethods.UsedPropertyInfoProvider;
import com.squaretest.generation.defaulttypes.AltIOExpressionProvider;
import com.squaretest.generation.defaulttypes.AltIoExpressionPopulator;
import com.squaretest.generation.defaulttypes.DefaultDiInfoHolder;
import com.squaretest.generation.defaulttypes.DefaultTypesHolder;
import com.squaretest.generation.defaulttypes.EnumValueProvider;
import com.squaretest.generation.defaulttypes.GroovyTypeCreator;
import com.squaretest.generation.defaulttypes.JavaGroovyCommonTypesCreator;
import com.squaretest.generation.defaulttypes.JavaLibraryReferenceProvider;
import com.squaretest.generation.defaulttypes.JavaTypeCreator;
import com.squaretest.generation.defaulttypes.PrimitiveValueProvider;
import com.squaretest.generation.defaulttypes.StringAndUUIDValueProvider;
import com.squaretest.generation.defaulttypes.SuperTypesProvider;
import com.squaretest.generation.defaulttypes.TestDependencyInfoProvider;
import com.squaretest.generation.defaulttypes.beans.BeanInfoProvider;
import com.squaretest.generation.defaulttypes.builders.AWSV2BuilderInitInfoProvider;
import com.squaretest.generation.defaulttypes.builders.BuilderInfoProvider;
import com.squaretest.generation.defaulttypes.builders.LombokBuilderInitInfoProvider;
import com.squaretest.generation.defaulttypes.builders.Protobuf3BuilderInitInfoProvider;
import com.squaretest.generation.dependencyinteraction.DependencyFlowInfoProvider;
import com.squaretest.generation.dependencyinteraction.DependencyInteractionCollector;
import com.squaretest.generation.dependencyinteraction.FieldToSourceVariableCollector;
import com.squaretest.generation.dependencyinteraction.FieldsWithSameSourceVarProvider;
import com.squaretest.generation.dependencyinteraction.MethodCallsProvider;
import com.squaretest.generation.dependencyinteraction.followup.FollowupInfoProvider;
import com.squaretest.generation.dependencyinteraction.followup.processors.MethodCallProcessor;
import com.squaretest.generation.dependencyinteraction.followup.processors.MethodCallProcessorFactory;
import com.squaretest.generation.dependencyinteraction.outcomes.MethodExitPointsProvider;
import com.squaretest.generation.dependencyinteraction.outcomes.MethodPathsInfoProvider;
import com.squaretest.generation.exceptions.PsiExceptionProvider;
import com.squaretest.generation.filetemplateutil.DirectoryChecker;
import com.squaretest.generation.generationinfo.GenerationInfoUtils;
import com.squaretest.generation.setters.DefaultInitSetterDecider;
import com.squaretest.generation.simpleexit.ConstantInitExpressionValueProvider;
import com.squaretest.generation.simpleexit.GroovyConstantInitExpressionValueProvider;
import com.squaretest.generation.simpleexit.GroovySimpleExitInfoProvider;
import com.squaretest.generation.simpleexit.SimpleExitInfoProvider;
import com.squaretest.generation.singletons.SingletonDetector;
import com.squaretest.generation.sourcevars.BeanSourceVariableProvider;
import com.squaretest.generation.sourcevars.ConstructorSourceVariableProvider;
import com.squaretest.generation.sourcevars.ISourceVariableProvider;
import com.squaretest.generation.sourcevars.SourceVariableProvider;
import com.squaretest.generation.sourcevars.builders.AWSV2BuilderSourceVariableProvider;
import com.squaretest.generation.sourcevars.builders.LombokBuilderSourceVariableProvider;
import com.squaretest.generation.sourcevars.builders.Protobuf3BuilderSourceVariableProvider;
import com.squaretest.multipletests.ErrorDialog;
import com.squaretest.multipletests.SelectMethodsDialog;
import com.squaretest.multipletests.SelectMethodsResult;
import com.squaretest.multipletests.TemplateUpgradeReminderManager;
import com.squaretest.multipletests.TestMethodGenerator;
import com.squaretest.multipletests.imports.ImportUtil;
import com.squaretest.settings.store.SettingsProvider;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.ClassUtilsImpl;
import com.squaretest.template.impl.CodeStyleUtilsImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.squaretest.TemplateProvider.computePresentationNameFromTemplate;

public class CreateTestMethodsHandler implements CodeInsightActionHandler {
    @Override
    public void invoke(
            @NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile existingTestFile) {

        final DumbService dumbService = DumbService.getInstance(project);
        // Show an error notification and return if IntelliJ is updating indices.
        if(dumbService.isDumb()) {
            dumbService.showDumbModeNotification("Squaretest 'Generate Test' action is not available while IDEA is updating indices");
            return;
        }

        // Determine the source class (the class being tested) for this test class.
        // This is safe, because CreateTestMethodsAction.isValidForFile() ensures the file is a PsiClassOwner with at
        // least one class.
        final PsiClass existingTestClass = ((PsiClassOwner) existingTestFile).getClasses()[0];
        final JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(project);
        final SourceClassFinder sourceClassFinder = new SourceClassFinder(new ProjectSearcher(javaPsiFacade, PsiShortNamesCache.getInstance(project), project));
        final PsiClass sourceClass = sourceClassFinder.findSourceClassForTestClass(existingTestClass);

        // If the source class is null (not determined) show an error message and record a metric.
        if(sourceClass == null) {
            final ErrorDialog dialog = new ErrorDialog(project, "Unable To Determine Source Class", "/SourceClassNotDetermined.html", null);
            dialog.showAndGet();
            return;
        }

        // If the source class is not Java, show an error message.
        if(!(sourceClass.getContainingFile() instanceof final PsiJavaFile psiJavaFile)) {
            final ErrorDialog dialog = new ErrorDialog(project, "Invalid Source Class Type", "/SourceClassIsNotJava.html", sourceClass.getName());
            dialog.showAndGet();
            return;
        }

        // Bootstrap the dependencies.
        final String sourceClassPackage = psiJavaFile.getPackageName();
        final VisibilityInfoProvider visibilityInfoProvider = new VisibilityInfoProvider(sourceClassPackage);
        final Module moduleContainingTestSourcesDir = ModuleUtil.findModuleForFile(existingTestClass.getContainingFile().getVirtualFile(), project);
        final TestDependencyInfoProvider testDependencyInfoProvider = new TestDependencyInfoProvider(JavaPsiFacade.getInstance(project), moduleContainingTestSourcesDir);
        final MemberFieldPrefixRemover memberFieldPrefixRemover = new MemberFieldPrefixRemover();
        final SuggestedNameProvider suggestedNameProvider = new SuggestedNameProvider(JavaCodeStyleManager.getInstance(project), memberFieldPrefixRemover);
        final PsiToTemplateDataConverter psiToTemplateDataConverter;
        final SuperTypesProvider superTypesProvider = new SuperTypesProvider();
        final DefaultTypesHolder defaultTypesHolder = DefaultTypesHolder.getInstance();
        final BeanInfoProvider beanInfoProvider = new BeanInfoProvider(
                defaultTypesHolder, visibilityInfoProvider);
        final PsiExceptionProvider psiExceptionProvider = new PsiExceptionProvider(project, javaPsiFacade, DefaultDiInfoHolder.getInstance());
        final MethodExitPointsProvider methodExitPointsProvider = new MethodExitPointsProvider();
        final MethodPathsInfoProvider methodPathsInfoProvider = new MethodPathsInfoProvider(methodExitPointsProvider);
        final List<MethodCallProcessor> methodCallProcessors = MethodCallProcessorFactory.createProcessors(methodPathsInfoProvider, javaPsiFacade, sourceClass);
        final FollowupInfoProvider followupInfoProvider = new FollowupInfoProvider(javaPsiFacade, methodPathsInfoProvider, sourceClass, methodCallProcessors);
        final TypeSubstitutorProvider typeSubstitutorProvider = new TypeSubstitutorProvider(javaPsiFacade, sourceClass);
        final MethodCallsProvider methodCallsProvider = new MethodCallsProvider();
        final DependencyInteractionCollector dependencyInteractionCollector = new DependencyInteractionCollector(typeSubstitutorProvider, methodCallsProvider, sourceClass);
        final DataflowNullabilityDecider dataflowNullabilityDecider = new DataflowNullabilityDecider(dependencyInteractionCollector, sourceClass);
        final NullabilityDecider nullabilityDecider = new NullabilityDecider(DefaultDiInfoHolder.getInstance(), dataflowNullabilityDecider);
        final DependencyFlowInfoProvider dependencyFlowInfoProvider = new DependencyFlowInfoProvider(psiExceptionProvider, followupInfoProvider, nullabilityDecider, defaultTypesHolder, dependencyInteractionCollector, javaPsiFacade, methodPathsInfoProvider, sourceClass);
        final FieldToSourceVariableCollector fieldToSourceVariableCollector = new FieldToSourceVariableCollector(beanInfoProvider);
        final CalledMethodsInfoProvider calledMethodsInfoProvider = new CalledMethodsInfoProvider(sourceClass, defaultTypesHolder);
        final PsiConstantEvaluationHelper psiConstantEvaluationHelper = JavaPsiFacade.getInstance(sourceClass.getProject()).getConstantEvaluationHelper();
        final List<IPropertyResolver> propertyResolvers = new ArrayList<>(2);
        propertyResolvers.add(new MainPropertyResolver());
        final IPropertyResolver yamlPropertyResolver = ApplicationManager.getApplication().getService(IPropertyResolver.class);
        if(yamlPropertyResolver != null) {
            propertyResolvers.add(yamlPropertyResolver);
        }
        final PropertyResolver propertyResolver = new PropertyResolver(propertyResolvers);
        final SpringReferenceResolver springReferenceResolver = new SpringReferenceResolver(propertyResolver);
        final AnnotationCreator annotationCreator = new AnnotationCreator(psiConstantEvaluationHelper, springReferenceResolver);
        final ConstructorSourceVariableProvider constructorSourceVariableProvider = new ConstructorSourceVariableProvider(fieldToSourceVariableCollector);
        final LombokBuilderSourceVariableProvider lombokBuilderSourceVariableProvider = new LombokBuilderSourceVariableProvider(constructorSourceVariableProvider);
        final List<ISourceVariableProvider> sourceVariableProviders = List.of(
                lombokBuilderSourceVariableProvider,
                new AWSV2BuilderSourceVariableProvider(),
                new Protobuf3BuilderSourceVariableProvider(),
                constructorSourceVariableProvider,
                new BeanSourceVariableProvider(beanInfoProvider));
        final SourceVariableProvider sourceVariableProvider = new SourceVariableProvider(sourceVariableProviders);
        final UsedPropertyInfoProvider usedPropertyInfoProvider = new UsedPropertyInfoProvider(calledMethodsInfoProvider, sourceVariableProvider, beanInfoProvider);
        final ExceptionCreator exceptionCreator;
        final SettingsProvider settingsProvider = new SettingsProvider();
        final BuilderInfoProvider builderInfoProvider = new BuilderInfoProvider(List.of(
                new LombokBuilderInitInfoProvider(lombokBuilderSourceVariableProvider, usedPropertyInfoProvider),
                new Protobuf3BuilderInitInfoProvider(usedPropertyInfoProvider),
                new AWSV2BuilderInitInfoProvider(usedPropertyInfoProvider)));
        final WildcardInfoProvider wildcardInfoProvider = new WildcardInfoProvider(sourceClass);
        final FieldsWithSameSourceVarProvider fieldsWithSameSourceVarProvider = new FieldsWithSameSourceVarProvider(fieldToSourceVariableCollector, sourceClass);
        final FrameworkInferencer frameworkInferencer = new FrameworkInferencer();
        final BoxedInitExpressionUpdater boxedInitExpressionUpdater = new BoxedInitExpressionUpdater(frameworkInferencer, testDependencyInfoProvider);
        if(existingTestFile instanceof PsiJavaFile) {
            final ConstantInitExpressionValueProvider constantInitExpressionValueProvider = new ConstantInitExpressionValueProvider(psiConstantEvaluationHelper);
            final DataflowAnalyzer dataflowAnalyzer = new DataflowAnalyzer(dependencyFlowInfoProvider, beanInfoProvider, sourceVariableProvider, calledMethodsInfoProvider, sourceClass, psiConstantEvaluationHelper, constantInitExpressionValueProvider, springReferenceResolver);
            final JavaLibraryReferenceProvider javaLibraryReferenceProvider = new JavaLibraryReferenceProvider(dataflowAnalyzer);
            final StringAndUUIDValueProvider stringAndUUIDValueProvider = new StringAndUUIDValueProvider(dataflowAnalyzer, beanInfoProvider, memberFieldPrefixRemover);
            final PrimitiveValueProvider primitiveValuesProvider = new PrimitiveValueProvider(dataflowAnalyzer);
            final EnumValueProvider enumValueProvider = new EnumValueProvider(dataflowAnalyzer);
            final JavaTypeCreator typeCreator = new JavaTypeCreator(new JavaGroovyCommonTypesCreator(testDependencyInfoProvider, stringAndUUIDValueProvider, primitiveValuesProvider),
                    beanInfoProvider, suggestedNameProvider,
                    new AltIoExpressionPopulator(new AltIOExpressionProvider(testDependencyInfoProvider)),
                    testDependencyInfoProvider,
                    superTypesProvider, fieldToSourceVariableCollector, settingsProvider, builderInfoProvider, calledMethodsInfoProvider, typeSubstitutorProvider, wildcardInfoProvider, enumValueProvider, javaLibraryReferenceProvider, visibilityInfoProvider);
            final PsiFieldToVariableConverter fieldToVariableConverter = new PsiFieldToVariableConverter(memberFieldPrefixRemover, suggestedNameProvider, typeCreator, annotationCreator);
            exceptionCreator = new ExceptionCreator(sourceClass, javaPsiFacade, typeCreator);
            final SimpleExitInfoProvider simpleExitInfoProvider = new SimpleExitInfoProvider(psiConstantEvaluationHelper, exceptionCreator);
            final PsiMethodToMethodConverter methodToMethodConverter = new PsiMethodToMethodConverter(sourceClass, typeCreator, psiExceptionProvider, exceptionCreator, fieldToVariableConverter,
                    annotationCreator, beanInfoProvider, nullabilityDecider, usedPropertyInfoProvider, simpleExitInfoProvider, methodPathsInfoProvider, visibilityInfoProvider);
            psiToTemplateDataConverter = new PsiToTemplateDataConverter(sourceClass, new ImportListGenerator(), typeCreator, new SingletonDetector(), new AbstractClassInfoProvider(), javaPsiFacade, suggestedNameProvider,
                    new DefaultInitSetterDecider(), dependencyFlowInfoProvider, fieldToSourceVariableCollector, beanInfoProvider, fieldsWithSameSourceVarProvider, fieldToVariableConverter, methodToMethodConverter, visibilityInfoProvider, annotationCreator, typeSubstitutorProvider, boxedInitExpressionUpdater, psiConstantEvaluationHelper);
        } else {
            final ConstantInitExpressionValueProvider constantInitExpressionValueProvider = new GroovyConstantInitExpressionValueProvider(psiConstantEvaluationHelper);
            final DataflowAnalyzer dataflowAnalyzer = new DataflowAnalyzer(dependencyFlowInfoProvider, beanInfoProvider, sourceVariableProvider, calledMethodsInfoProvider, sourceClass, psiConstantEvaluationHelper, constantInitExpressionValueProvider, springReferenceResolver);
            final StringAndUUIDValueProvider stringAndUUIDValueProvider = new StringAndUUIDValueProvider(dataflowAnalyzer, beanInfoProvider, memberFieldPrefixRemover);
            final PrimitiveValueProvider primitiveValuesProvider = new PrimitiveValueProvider(dataflowAnalyzer);
            final EnumValueProvider enumValueProvider = new EnumValueProvider(dataflowAnalyzer);
            final JavaLibraryReferenceProvider javaLibraryReferenceProvider = new JavaLibraryReferenceProvider(dataflowAnalyzer);
            final GroovyTypeCreator typeCreator = new GroovyTypeCreator(
                    new JavaGroovyCommonTypesCreator(testDependencyInfoProvider, stringAndUUIDValueProvider, primitiveValuesProvider), beanInfoProvider,
                    suggestedNameProvider,
                    new AltIoExpressionPopulator(new AltIOExpressionProvider(testDependencyInfoProvider)),
                    testDependencyInfoProvider,
                    superTypesProvider, fieldToSourceVariableCollector, settingsProvider, builderInfoProvider, calledMethodsInfoProvider, typeSubstitutorProvider, wildcardInfoProvider, enumValueProvider, javaLibraryReferenceProvider, visibilityInfoProvider);
            final PsiFieldToVariableConverter fieldToVariableConverter = new PsiFieldToVariableConverter(memberFieldPrefixRemover, suggestedNameProvider, typeCreator, annotationCreator);
            exceptionCreator = new ExceptionCreator(sourceClass, javaPsiFacade, typeCreator);
            final SimpleExitInfoProvider simpleExitInfoProvider = new GroovySimpleExitInfoProvider(psiConstantEvaluationHelper, exceptionCreator);
            final PsiMethodToMethodConverter methodToMethodConverter = new PsiMethodToMethodConverter(sourceClass, typeCreator, psiExceptionProvider, exceptionCreator, fieldToVariableConverter,
                    annotationCreator, beanInfoProvider, nullabilityDecider, usedPropertyInfoProvider, simpleExitInfoProvider, methodPathsInfoProvider, visibilityInfoProvider);
            psiToTemplateDataConverter = new PsiToTemplateDataConverter(sourceClass, new ImportListGenerator(), typeCreator, new SingletonDetector(), new AbstractClassInfoProvider(), javaPsiFacade, suggestedNameProvider,
                    new DefaultInitSetterDecider(), dependencyFlowInfoProvider, fieldToSourceVariableCollector, beanInfoProvider, fieldsWithSameSourceVarProvider, fieldToVariableConverter, methodToMethodConverter, visibilityInfoProvider, annotationCreator, typeSubstitutorProvider, boxedInitExpressionUpdater, psiConstantEvaluationHelper);
        }
        final Module moduleContainingSourceClassDir = ModuleUtil.findModuleForFile(sourceClass.getContainingFile().getVirtualFile(), project);
        final TemplateProvider templateProvider = new TemplateProvider();
        final TemplateSettingsFinder templateSettingsFinder = new TemplateSettingsFinder(settingsProvider, templateProvider);
        final JavaCodeStyleManager javaCodeStyleManager = JavaCodeStyleManager.getInstance(project);
        final Api.CodeStyleUtils codeStyleUtils = new CodeStyleUtilsImpl(javaCodeStyleManager);
        final TestMethodGenerator testMethodsGenerator = new TestMethodGenerator(project, javaPsiFacade, psiToTemplateDataConverter, dependencyFlowInfoProvider, exceptionCreator, codeStyleUtils, new ClassUtilsImpl(existingTestClass, javaPsiFacade, psiToTemplateDataConverter, defaultTypesHolder), new CreateTestFileChecker(new ExistingTestFinder(project), new DirectoryChecker()), psiJavaFile, existingTestClass);

        // Determine which template to use.
        final TemplateChooser templateChooser = new TemplateChooser(templateSettingsFinder, frameworkInferencer, templateProvider);
        final TemplateInfo templateInfo = templateChooser.selectTemplateToUse(existingTestClass, moduleContainingSourceClassDir);

        // Show the dialog
        final SelectMethodsDialog dialog = new SelectMethodsDialog(project, testMethodsGenerator, templateInfo.getTemplate(), templateProvider);
        dialog.showAndGet();

        // Process the dialog result.
        final SelectMethodsResult result = dialog.getSelectMethodsResult();
        // If the user cancelled out of the dialog, return.
        if(result.wasCancelled()) {
            return;
        }

        // The user clicked OK, but didn't select any methods.
        final List<PsiMethod> methodsToInsert = result.getMethodsToCreate();
        if(methodsToInsert.isEmpty()) {
            return;
        }

        // Actually insert the methods.
        final PsiClass generatedClass = methodsToInsert.get(0).getContainingClass();
        final List<GenerationInfo> generationInfos = GenerationInfoUtils.wrap(methodsToInsert);
        ApplicationManager.getApplication().runWriteAction(() -> CommandProcessor.getInstance().executeCommand(project, () -> doGenerate(project, editor, existingTestClass, generatedClass, generationInfos), null, null));

        // Determine if we should show a notification reminding the user to update his/her template.
        final TemplateValidator templateValidator = new TemplateValidator();
        final boolean shouldShowTemplateUpdateReminder = !templateValidator.templateContainsCodeToConfirmMocks(result.getTemplateUsed());
        if(shouldShowTemplateUpdateReminder) {
            TemplateUpgradeReminderManager.getInstance().showTemplateUpgradeNotificationIfNeeded(project, computePresentationNameFromTemplate(result.getTemplateUsed()));
        }
    }

    @Override
    public boolean startInWriteAction() {
        return false;
    }

    /**
     * Copied from {@link GenerateMembersHandlerBase}.
     */
    private void doGenerate(
            final Project project, final Editor editor, final PsiClass existingTestClass, final PsiClass generatedTestClass,
            List<GenerationInfo> methodsToInsert) {
        if(methodsToInsert.isEmpty()) {
            return;
        }

        // Determine where to insert the methods.
        // This code is copied from  GenerateMembersHandlerBase.doGenerate(...).
        int offset = editor.getCaretModel().getOffset();
        int col = editor.getCaretModel().getLogicalPosition().column;
        int line = editor.getCaretModel().getLogicalPosition().line;
        final Document document = editor.getDocument();
        int lineStartOffset = document.getLineStartOffset(line);
        CharSequence docText = document.getCharsSequence();
        String textBeforeCaret = docText.subSequence(lineStartOffset, offset).toString();
        final String afterCaret = docText.subSequence(offset, document.getLineEndOffset(line)).toString();
        final PsiElement lBrace = existingTestClass.getLBrace();
        if(!textBeforeCaret.trim().isEmpty() && StringUtil.isEmptyOrSpaces(afterCaret) && (lBrace == null || lBrace.getTextOffset() < offset) && !editor.getSelectionModel().hasSelection()) {
            PsiDocumentManager.getInstance(project).commitDocument(document);
            offset = editor.getCaretModel().getOffset();
            col = editor.getCaretModel().getLogicalPosition().column;
            line = editor.getCaretModel().getLogicalPosition().line;
        }

        // Insert the selected methods.
        editor.getCaretModel().moveToLogicalPosition(new LogicalPosition(0, 0));
        int finalOffset = offset;
        List<? extends GenerationInfo> insertedMethods = WriteAction.compute(() -> GenerateMembersUtil.insertMembersAtOffset(existingTestClass.getContainingFile(), finalOffset, methodsToInsert));
        editor.getCaretModel().moveToLogicalPosition(new LogicalPosition(line, col));
        final List<PsiElement> elements = new ArrayList<>();
        for(GenerationInfo member : insertedMethods) {
            if(!(member instanceof TemplateGenerationInfo)) {
                ContainerUtil.addIfNotNull(elements, member.getPsiMember());
            }
        }

        ImportUtil.addRequiredImports(generatedTestClass.getContainingFile(), existingTestClass.getContainingFile());
        CodeFormatterUtil.cleanUpElements(project, elements.toArray(PsiElement.EMPTY_ARRAY));
        ImportUtil.optimizeImports(existingTestClass.getContainingFile());
        if(existingTestClass.getContainingFile() instanceof PsiJavaFile) {
            CleanUpUtils.removeRedundantThrows(elements);
        } else {
            CleanUpUtils.removeSemicolons(elements);
        }
        if(!insertedMethods.isEmpty()) {
            insertedMethods.get(0).positionCaret(editor, false);
        }
    }
}
