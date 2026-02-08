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

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.completion.CompletionUtil;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.generation.GenerateMembersUtil;
import com.intellij.codeInsight.generation.GenerationInfo;
import com.intellij.codeInsight.generation.TemplateGenerationInfo;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PatternCondition;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiCompiledElement;
import com.intellij.psi.PsiConstantEvaluationHelper;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.PsiNameHelper;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiSubstitutor;
import com.intellij.psi.PsiType;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import com.squaretest.TemplateProvider;
import com.squaretest.annotations.AnnotationCreator;
import com.squaretest.annotations.IPropertyResolver;
import com.squaretest.annotations.MainPropertyResolver;
import com.squaretest.annotations.PropertyResolver;
import com.squaretest.annotations.SpringReferenceResolver;
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
import com.squaretest.multipletests.TemplateUpgradeReminderManager;
import com.squaretest.multipletests.TestMethodGenerator;
import com.squaretest.settings.store.SettingsProvider;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.ClassUtilsImpl;
import com.squaretest.template.impl.CodeStyleUtilsImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.groovy.actions.generate.GroovyGenerationInfo;
import org.jetbrains.plugins.groovy.editor.GroovyImportOptimizer;
import org.jetbrains.plugins.groovy.lang.psi.GroovyFile;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.GrField;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.typedef.GrTypeDefinition;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.typedef.GrTypeDefinitionBody;
import org.jetbrains.plugins.groovy.lang.psi.api.toplevel.imports.GrImportStatement;
import org.jetbrains.plugins.groovy.lang.psi.api.types.GrTypeElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.swing.*;

import static com.intellij.patterns.PlatformPatterns.psiComment;
import static com.intellij.patterns.PlatformPatterns.psiElement;
import static com.squaretest.completion.CompletionUtils.determineImportsToAddToGroovyClass;

/**
 * This class largely follows {@link JavaGenerateTestMethodCompletionContributor}'s implementation.
 */
public class GroovyGenerateTestMethodCompletionContributor {

    private static final Key<Boolean> GENERATE_ELEMENT = Key.create("GENERATE_ELEMENT");

    private static final ElementPattern<PsiElement> PLACE = psiElement().andOr(psiElement().withParent(GrTypeDefinitionBody.class), psiElement().withParent(GrField.class)).with(new PatternCondition<>("Not in extends/implements clause of inner class") {
        @Override
        public boolean accepts(@NotNull PsiElement element, ProcessingContext context) {
            final GrTypeDefinition innerDefinition = PsiTreeUtil.getPrevSiblingOfType(element, GrTypeDefinition.class);
            return innerDefinition == null || innerDefinition.getContainingClass() == null || innerDefinition.getBody() != null;
        }
    }).andNot(psiComment());

    static void fillCompletionVariants(@NotNull final CompletionParameters parameters, CompletionResultSet result) {
        if(parameters.getCompletionType() != CompletionType.BASIC && parameters.getCompletionType() != CompletionType.SMART) {
            return;
        }

        PsiElement position = parameters.getPosition();
        if(PLACE.accepts(position)) {
            PsiElement prevLeaf = PsiTreeUtil.prevVisibleLeaf(position);
            PsiModifierList modifierList = PsiTreeUtil.getParentOfType(prevLeaf, PsiModifierList.class);

            // We couldn't find the modifier list and the previous element is a type parameter other than void; this is not a test method.
            final GrTypeElement previousLeafTypeElement = PsiTreeUtil.getParentOfType(prevLeaf, GrTypeElement.class);
            if(modifierList == null && prevLeaf.getParent() != null && (previousLeafTypeElement != null && !prevLeaf.getText().equals("void"))) {
                return;
            }

            if(modifierList == null && prevLeaf.getParent() != null && prevLeaf.getText().equals("void")) {
                modifierList = PsiTreeUtil.getPrevSiblingOfType(prevLeaf.getParent(), PsiModifierList.class);
            }
            if(modifierList != null) {
                final String prefixToMatch = position.getContainingFile().getText().substring(modifierList.getTextRange().getStartOffset(), parameters.getOffset());
                result = result.withPrefixMatcher(prefixToMatch);
            }
            suggestGeneratedMethods(result, position);
        }
    }

    private static void suggestGeneratedMethods(
            CompletionResultSet result, PsiElement position) {
        PsiClass parent = CompletionUtil.getOriginalElement(Objects.requireNonNull(PsiTreeUtil.getParentOfType(position, PsiClass.class)));
        if(parent != null) {
            addTestMethodElements(result, parent);
        }
    }

    private static void addTestMethodElements(
            CompletionResultSet result, PsiClass existingTestClass) {
        // Find the source class corresponding to this test class.
        final Project project = existingTestClass.getProject();
        final JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(project);
        final SourceClassFinder sourceClassFinder = new SourceClassFinder(new ProjectSearcher(javaPsiFacade, PsiShortNamesCache.getInstance(project), project));
        final PsiClass sourceClass = sourceClassFinder.findSourceClassForTestClass(existingTestClass);
        if(sourceClass == null || (!(sourceClass.getContainingFile() instanceof final PsiJavaFile psiJavaFile)) || (sourceClass instanceof PsiCompiledElement)) {
            return;
        }

        // Bootstrap the dependencies for test generation.
        final String sourceClassPackage = psiJavaFile.getPackageName();
        final VisibilityInfoProvider visibilityInfoProvider = new VisibilityInfoProvider(sourceClassPackage);
        final Module moduleContainingTestSourcesDir = ModuleUtil.findModuleForFile(existingTestClass.getContainingFile().getVirtualFile(), project);
        final TestDependencyInfoProvider testDependencyInfoProvider = new TestDependencyInfoProvider(JavaPsiFacade.getInstance(project), moduleContainingTestSourcesDir);
        final MemberFieldPrefixRemover memberFieldPrefixRemover = new MemberFieldPrefixRemover();
        final SuggestedNameProvider suggestedNameProvider = new SuggestedNameProvider(JavaCodeStyleManager.getInstance(project), memberFieldPrefixRemover);
        final SuperTypesProvider superTypesProvider = new SuperTypesProvider();
        final DefaultTypesHolder defaultTypesHolder = DefaultTypesHolder.getInstance();
        final BeanInfoProvider beanInfoProvider = new BeanInfoProvider(defaultTypesHolder, visibilityInfoProvider);
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
        final ConstantInitExpressionValueProvider constantInitExpressionValueProvider = new GroovyConstantInitExpressionValueProvider(psiConstantEvaluationHelper);
        final DataflowAnalyzer dataflowAnalyzer = new DataflowAnalyzer(dependencyFlowInfoProvider, beanInfoProvider, sourceVariableProvider, calledMethodsInfoProvider, sourceClass, psiConstantEvaluationHelper, constantInitExpressionValueProvider, springReferenceResolver);
        final StringAndUUIDValueProvider stringAndUUIDValueProvider = new StringAndUUIDValueProvider(dataflowAnalyzer, beanInfoProvider, memberFieldPrefixRemover);
        final PrimitiveValueProvider primitiveValuesProvider = new PrimitiveValueProvider(dataflowAnalyzer);
        final EnumValueProvider enumValueProvider = new EnumValueProvider(dataflowAnalyzer);
        final JavaLibraryReferenceProvider javaLibraryReferenceProvider = new JavaLibraryReferenceProvider(dataflowAnalyzer);
        final SettingsProvider settingsProvider = new SettingsProvider();
        final BuilderInfoProvider builderInfoProvider = new BuilderInfoProvider(List.of(
                new LombokBuilderInitInfoProvider(lombokBuilderSourceVariableProvider, usedPropertyInfoProvider),
                new Protobuf3BuilderInitInfoProvider(usedPropertyInfoProvider),
                new AWSV2BuilderInitInfoProvider(usedPropertyInfoProvider)));
        final WildcardInfoProvider wildcardInfoProvider = new WildcardInfoProvider(sourceClass);
        final GroovyTypeCreator typeCreator = new GroovyTypeCreator(new JavaGroovyCommonTypesCreator(testDependencyInfoProvider, stringAndUUIDValueProvider, primitiveValuesProvider),
                beanInfoProvider, suggestedNameProvider,
                new AltIoExpressionPopulator(new AltIOExpressionProvider(testDependencyInfoProvider)),
                testDependencyInfoProvider,
                superTypesProvider, fieldToSourceVariableCollector, settingsProvider, builderInfoProvider, calledMethodsInfoProvider, typeSubstitutorProvider, wildcardInfoProvider, enumValueProvider, javaLibraryReferenceProvider, visibilityInfoProvider);
        final PsiFieldToVariableConverter fieldToVariableConverter = new PsiFieldToVariableConverter(memberFieldPrefixRemover, suggestedNameProvider, typeCreator, annotationCreator);
        final ExceptionCreator exceptionCreator = new ExceptionCreator(sourceClass, javaPsiFacade, typeCreator);
        final SimpleExitInfoProvider simpleExitInfoProvider = new GroovySimpleExitInfoProvider(psiConstantEvaluationHelper, exceptionCreator);
        final PsiMethodToMethodConverter methodToMethodConverter = new PsiMethodToMethodConverter(sourceClass, typeCreator, psiExceptionProvider, exceptionCreator, fieldToVariableConverter,
                annotationCreator, beanInfoProvider, nullabilityDecider, usedPropertyInfoProvider, simpleExitInfoProvider, methodPathsInfoProvider, visibilityInfoProvider);
        final FieldsWithSameSourceVarProvider fieldsWithSameSourceVarProvider = new FieldsWithSameSourceVarProvider(fieldToSourceVariableCollector, sourceClass);
        final FrameworkInferencer frameworkInferencer = new FrameworkInferencer();
        final BoxedInitExpressionUpdater boxedInitExpressionUpdater = new BoxedInitExpressionUpdater(frameworkInferencer, testDependencyInfoProvider);
        final PsiToTemplateDataConverter psiToTemplateDataConverter = new PsiToTemplateDataConverter(sourceClass, new ImportListGenerator(), typeCreator, new SingletonDetector(), new AbstractClassInfoProvider(), javaPsiFacade, suggestedNameProvider,
                new DefaultInitSetterDecider(), dependencyFlowInfoProvider, fieldToSourceVariableCollector, beanInfoProvider, fieldsWithSameSourceVarProvider, fieldToVariableConverter, methodToMethodConverter, visibilityInfoProvider, annotationCreator, typeSubstitutorProvider, boxedInitExpressionUpdater, psiConstantEvaluationHelper);
        final JavaCodeStyleManager javaCodeStyleManager = JavaCodeStyleManager.getInstance(project);
        final Api.CodeStyleUtils codeStyleUtils = new CodeStyleUtilsImpl(javaCodeStyleManager);
        final CreateTestFileChecker createTestFileChecker = new CreateTestFileChecker(new ExistingTestFinder(project), new DirectoryChecker());
        final TestMethodGenerator testMethodGenerator = new TestMethodGenerator(
                project, javaPsiFacade, psiToTemplateDataConverter, dependencyFlowInfoProvider, exceptionCreator, codeStyleUtils,
                new ClassUtilsImpl(existingTestClass, JavaPsiFacade.getInstance(project), psiToTemplateDataConverter, defaultTypesHolder),
                createTestFileChecker, psiJavaFile, existingTestClass);

        // Determine which template to use.
        final Module moduleContainingSourceClassDir = ModuleUtil.findModuleForFile(sourceClass.getContainingFile().getVirtualFile(), project);
        final TemplateProvider templateProvider = new TemplateProvider();
        final TemplateSettingsFinder templateSettingsFinder = new TemplateSettingsFinder(settingsProvider, templateProvider);
        final TemplateChooser templateChooser = new TemplateChooser(templateSettingsFinder, frameworkInferencer, templateProvider);
        final TemplateInfo templateInfo = templateChooser.selectTemplateToUse(existingTestClass, moduleContainingSourceClassDir);

        // Render the in-memory test class.
        final List<PsiMethod> generatedMethods = CompletionUtils.sortMethodsInDisplayOrder(
                testMethodGenerator.safeRunTemplateAndReturnTestMethods(templateInfo.getFileTemplate()));
        if(generatedMethods.isEmpty()) {
            return;
        }

        // Determine if we should show the template upgrade reminder notification.
        final TemplateValidator templateValidator = new TemplateValidator();
        final boolean templateUpgradeRecommended = !templateValidator.templateContainsCodeToConfirmMocks(templateInfo.getFileTemplate());
        final TemplateUpgradeReminderManager upgradeReminderManager = TemplateUpgradeReminderManager.getInstance();
        final Collection<GrImportStatement> importsToAdd = determineImportsToAddToGroovyClass((GroovyFile) generatedMethods.get(0).getContainingFile(), (GroovyFile) existingTestClass.getContainingFile());
        int count = 0;
        for(final PsiMethod generatedMethod : generatedMethods) {
            Icon icon = generatedMethod.getIcon(Iconable.ICON_FLAG_VISIBILITY);
            result.addElement(createGenerateMethodElement(generatedMethod, icon, (context, item) -> {
                removeLookupString(context);
                insertGenerationInfos(context, Collections.singletonList(new GroovyGenerationInfo<>(generatedMethod)), importsToAdd);
                if(templateUpgradeRecommended) {
                    upgradeReminderManager.showTemplateUpgradeNotificationIfNeeded(project, templateInfo.getTemplate().getPresentationName());
                }
            }));
            if(count++ > 200) {
                return;
            }
        }
    }

    private static void insertGenerationInfos(
            InsertionContext context, List<GroovyGenerationInfo<PsiMethod>> infos,
            final Collection<GrImportStatement> importLinesRequired) {
        final int startOffset = context.getStartOffset();
        List<GroovyGenerationInfo<PsiMethod>> newInfos = GenerateMembersUtil.insertMembersAtOffset(context.getFile(), startOffset, infos);
        if(newInfos.isEmpty()) {
            return;
        }
        final List<PsiElement> elements = new ArrayList<>();
        for(GenerationInfo member : newInfos) {
            if(!(member instanceof TemplateGenerationInfo)) {
                elements.add(member.getPsiMember());
            }
        }

        // Add imports required by the generated method if they're not already included.
        final GroovyFile groovyFile = (GroovyFile) context.getFile();
        GrImportStatement[] importList = groovyFile.getImportStatements();

        // Its unclear when the importList will actually be null.
        // The importList does not seem to be null, even when the file contains no imports.
        // Code in IntelliJ IDEA seems to ignore this case (just pretend it can't add any imports to the file).
        // I will do the same. This should be updated if there are cases where Squaretest does not add imports as expected.
        if(importList != null) {
            for(final GrImportStatement importLineRequired : importLinesRequired) {
                groovyFile.addImport(importLineRequired);
            }
        }
        CodeFormatterUtil.cleanUpElements(context.getProject(), elements.toArray(PsiElement.EMPTY_ARRAY));
        CleanUpUtils.removeSemicolons(elements);
        final GroovyImportOptimizer groovyImportOptimizer = new GroovyImportOptimizer();
        groovyImportOptimizer.processFile(context.getFile()).run();
        newInfos.get(0).positionCaret(context.getEditor(), false);
    }

    private static void removeLookupString(InsertionContext context) {
        context.getDocument().deleteString(context.getStartOffset(), context.getTailOffset());
        context.commitDocument();
    }

    private static LookupElementBuilder createGenerateMethodElement(
            PsiMethod prototype, Icon icon, InsertHandler<LookupElement> insertHandler) {
        String methodName = prototype.getName();

        String modifiers = "";
        PsiType type = PsiSubstitutor.EMPTY.substitute(prototype.getReturnType());
        String signature = modifiers + (type == null ? "" : type.getPresentableText() + " ") + methodName;

        String parameters = "(" + StringUtil.join(prototype.getParameterList().getParameters(), p -> getShortParameterName(p) + " " + p.getName(), ", ") + ")";

        LookupElementBuilder element = LookupElementBuilder.create(prototype, signature).withLookupString(methodName).
                withLookupString(signature).withInsertHandler(insertHandler).
                appendTailText(parameters, false).appendTailText(" {...}", true).withTypeText("").withIcon(icon);
        element.putUserData(GENERATE_ELEMENT, true);
        return element;
    }

    @NotNull
    private static String getShortParameterName(final PsiParameter p) {
        return PsiNameHelper.getShortClassName(PsiSubstitutor.EMPTY.substitute(p.getType()).getPresentableText(false));
    }
}