/*
 *
 *  * Copyright 2026 Squaretest LLC.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package helpers;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiConstantEvaluationHelper;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.squaretest.annotations.AnnotationCreator;
import com.squaretest.annotations.IPropertyResolver;
import com.squaretest.annotations.MainPropertyResolver;
import com.squaretest.annotations.PropertyResolver;
import com.squaretest.annotations.SpringReferenceResolver;
import com.squaretest.completion.frameworkdetection.FrameworkInferencer;
import com.squaretest.confirmsettings.DialogBasedSettingsBroker;
import com.squaretest.confirmsettings.v1.DialogBasedSettingsConfirmerV1;
import com.squaretest.confirmsettings.v2.DialogBasedSettingsConfirmerV2;
import com.squaretest.generation.*;
import com.squaretest.generation.abstractmethods.AbstractClassInfoProvider;
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
import com.squaretest.generation.filecreation.GroovyFileCreator;
import com.squaretest.generation.filecreation.JavaFileCreator;
import com.squaretest.generation.filetemplateutil.DirectoryChecker;
import com.squaretest.generation.runconfig.ConfigInfo;
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
import com.squaretest.settings.store.SettingsProvider;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.CodeStyleUtilsImpl;
import com.squaretest.template.impl.UiUtilsImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UnitTestGeneratorCreator {

    @NotNull
    public static UnitTestGenerator createJavaUnitTestGenerator(final Project project, final Module module, final ConfigInfo configInfo) {
        return createJavaUnitTestGenerator(project, module, configInfo, new SettingsProvider());
    }

    public static UnitTestGenerator createJavaUnitTestGenerator(final Project project, final Module module, final ConfigInfo configInfo, final SettingsProvider settingsProvider) {
        final CodeStyleUtilsImpl codeStyleUtils = new CodeStyleUtilsImpl(JavaCodeStyleManager.getInstance(project));
        final Api.UiUtils uiUtils = new UiUtilsImpl(
                new DialogBasedSettingsBroker(
                        new DialogBasedSettingsConfirmerV1(project, codeStyleUtils),
                        new DialogBasedSettingsConfirmerV2(project)));
        return createJavaUnitTestGenerator(project, module, configInfo, settingsProvider, codeStyleUtils, uiUtils, false);
    }

    public static UnitTestGenerator createJavaUnitTestGenerator(final Project project, final Module module, final ConfigInfo configInfo, final Api.UiUtils uiUtils) {
        final CodeStyleUtilsImpl codeStyleUtils = new CodeStyleUtilsImpl(JavaCodeStyleManager.getInstance(project));
        return createJavaUnitTestGenerator(project, module, configInfo, new SettingsProvider(), codeStyleUtils, uiUtils, true);
    }

    @NotNull
    public static UnitTestGenerator createJavaUnitTestGenerator(final Project project, final Module module, final ConfigInfo configInfo, final SettingsProvider settingsProvider, final Api.CodeStyleUtils codeStyleUtils, final Api.UiUtils uiUtils, final boolean shouldAskToConfirmOptions) {
        final JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(project);
        final CreateTestFileChecker createTestFileChecker = new CreateTestFileChecker(new ExistingTestFinder(project), new DirectoryChecker());
        final MemberFieldPrefixRemover memberFieldPrefixRemover = new MemberFieldPrefixRemover();
        final SuggestedNameProvider suggestedNameProvider = new SuggestedNameProvider(JavaCodeStyleManager.getInstance(project), memberFieldPrefixRemover);
        TestDependencyInfoProvider testDependencyInfoProvider = new TestDependencyInfoProvider(javaPsiFacade, module);
        final SuperTypesProvider superTypesProvider = new SuperTypesProvider();
        final DefaultTypesHolder defaultTypesHolder = DefaultTypesHolder.getInstance();
        final PsiClass sourceClass = ((PsiJavaFile) configInfo.sourceFile()).getClasses()[0];
        final PsiJavaFile psiJavaFile = (PsiJavaFile) sourceClass.getContainingFile();
        final String sourceClassPackage = psiJavaFile.getPackageName();
        final VisibilityInfoProvider visibilityInfoProvider = new VisibilityInfoProvider(sourceClassPackage);
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
        final FieldsWithSameSourceVarProvider fieldsWithSameSourceVarProvider = new FieldsWithSameSourceVarProvider(fieldToSourceVariableCollector, sourceClass);
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
        final ConstantInitExpressionValueProvider constantInitExpressionValueProvider = new ConstantInitExpressionValueProvider(psiConstantEvaluationHelper);
        final DataflowAnalyzer dataflowAnalyzer = new DataflowAnalyzer(dependencyFlowInfoProvider, beanInfoProvider, sourceVariableProvider, calledMethodsInfoProvider, sourceClass, psiConstantEvaluationHelper, constantInitExpressionValueProvider, springReferenceResolver);
        final StringAndUUIDValueProvider stringAndUUIDValueProvider = new StringAndUUIDValueProvider(dataflowAnalyzer, beanInfoProvider, memberFieldPrefixRemover);
        final PrimitiveValueProvider primitiveValuesProvider = new PrimitiveValueProvider(dataflowAnalyzer);
        final EnumValueProvider enumValueProvider = new EnumValueProvider(dataflowAnalyzer);
        final JavaLibraryReferenceProvider javaLibraryReferenceProvider = new JavaLibraryReferenceProvider(dataflowAnalyzer);
        final BuilderInfoProvider builderInfoProvider = new BuilderInfoProvider(List.of(
                new LombokBuilderInitInfoProvider(lombokBuilderSourceVariableProvider, usedPropertyInfoProvider),
                new Protobuf3BuilderInitInfoProvider(usedPropertyInfoProvider),
                new AWSV2BuilderInitInfoProvider(usedPropertyInfoProvider)));
        final WildcardInfoProvider wildcardInfoProvider = new WildcardInfoProvider(sourceClass);
        final JavaTypeCreator typeCreator = new JavaTypeCreator(new JavaGroovyCommonTypesCreator(testDependencyInfoProvider, stringAndUUIDValueProvider, primitiveValuesProvider),
                beanInfoProvider, suggestedNameProvider,
                new AltIoExpressionPopulator(new AltIOExpressionProvider(testDependencyInfoProvider)),
                testDependencyInfoProvider,
                superTypesProvider, fieldToSourceVariableCollector, settingsProvider, builderInfoProvider, calledMethodsInfoProvider, typeSubstitutorProvider, wildcardInfoProvider, enumValueProvider, javaLibraryReferenceProvider, visibilityInfoProvider);
        final PsiFieldToVariableConverter fieldToVariableConverter = new PsiFieldToVariableConverter(memberFieldPrefixRemover, suggestedNameProvider, typeCreator, annotationCreator);
        final ExceptionCreator exceptionCreator = new ExceptionCreator(sourceClass, javaPsiFacade, typeCreator);
        final SimpleExitInfoProvider simpleExitInfoProvider = new SimpleExitInfoProvider(psiConstantEvaluationHelper, exceptionCreator);
        final PsiMethodToMethodConverter methodToMethodConverter = new PsiMethodToMethodConverter(sourceClass, typeCreator, psiExceptionProvider, exceptionCreator, fieldToVariableConverter,
                annotationCreator, beanInfoProvider, nullabilityDecider, usedPropertyInfoProvider, simpleExitInfoProvider, methodPathsInfoProvider, visibilityInfoProvider);
        final BoxedInitExpressionUpdater boxedInitExpressionUpdater = new BoxedInitExpressionUpdater(new FrameworkInferencer(), testDependencyInfoProvider);
        return new UnitTestGenerator(new PsiToTemplateDataConverter(
                sourceClass, new ImportListGenerator(), typeCreator, new SingletonDetector(), new AbstractClassInfoProvider(), javaPsiFacade, suggestedNameProvider,
                new DefaultInitSetterDecider(), dependencyFlowInfoProvider, fieldToSourceVariableCollector, beanInfoProvider, fieldsWithSameSourceVarProvider, fieldToVariableConverter, methodToMethodConverter, visibilityInfoProvider, annotationCreator, typeSubstitutorProvider, boxedInitExpressionUpdater, psiConstantEvaluationHelper),
                dependencyFlowInfoProvider, project, javaPsiFacade, configInfo, createTestFileChecker, new JavaFileCreator(createTestFileChecker), exceptionCreator, uiUtils, codeStyleUtils, shouldAskToConfirmOptions);
    }

    @NotNull
    public static UnitTestGenerator createGroovyUnitTestGenerator(final Project project, final Module module, final ConfigInfo configInfo) {
        return createGroovyUnitTestGenerator(project, module, configInfo, new SettingsProvider());
    }

    public static UnitTestGenerator createGroovyUnitTestGenerator(final Project project, final Module module, final ConfigInfo configInfo, final SettingsProvider settingsProvider) {
        final CodeStyleUtilsImpl codeStyleUtils = new CodeStyleUtilsImpl(JavaCodeStyleManager.getInstance(project));
        final Api.UiUtils uiUtils = new UiUtilsImpl(
                new DialogBasedSettingsBroker(
                        new DialogBasedSettingsConfirmerV1(project, codeStyleUtils),
                        new DialogBasedSettingsConfirmerV2(project)));
        return createGroovyUnitTestGenerator(project, module, configInfo, settingsProvider, codeStyleUtils, uiUtils, false);
    }

    public static UnitTestGenerator createGroovyUnitTestGenerator(final Project project, final Module module, final ConfigInfo configInfo, final Api.UiUtils uiUtils) {
        final CodeStyleUtilsImpl codeStyleUtils = new CodeStyleUtilsImpl(JavaCodeStyleManager.getInstance(project));
        return createGroovyUnitTestGenerator(project, module, configInfo, new SettingsProvider(), codeStyleUtils, uiUtils, true);
    }

    @NotNull
    private static UnitTestGenerator createGroovyUnitTestGenerator(final Project project, final Module module, final ConfigInfo configInfo, final SettingsProvider settingsProvider, final CodeStyleUtilsImpl codeStyleUtils, final Api.UiUtils uiUtils, final boolean shouldAskToConfirmOptions) {
        final JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(project);
        final CreateTestFileChecker createTestFileChecker = new CreateTestFileChecker(new ExistingTestFinder(project), new DirectoryChecker());
        final MemberFieldPrefixRemover memberFieldPrefixRemover = new MemberFieldPrefixRemover();
        final SuggestedNameProvider suggestedNameProvider = new SuggestedNameProvider(JavaCodeStyleManager.getInstance(project), memberFieldPrefixRemover);
        TestDependencyInfoProvider testDependencyInfoProvider = new TestDependencyInfoProvider(javaPsiFacade, module);
        final SuperTypesProvider superTypesProvider = new SuperTypesProvider();
        final DefaultTypesHolder defaultTypesHolder = DefaultTypesHolder.getInstance();
        final PsiJavaFile psiJavaFile = (PsiJavaFile) configInfo.sourceFile();
        final String sourceClassPackage = psiJavaFile.getPackageName();
        final VisibilityInfoProvider visibilityInfoProvider = new VisibilityInfoProvider(sourceClassPackage);
        final BeanInfoProvider beanInfoProvider = new BeanInfoProvider(defaultTypesHolder, visibilityInfoProvider);
        final PsiExceptionProvider psiExceptionProvider = new PsiExceptionProvider(project, javaPsiFacade, DefaultDiInfoHolder.getInstance());
        final PsiClass sourceClass = psiJavaFile.getClasses()[0];
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
        final FieldsWithSameSourceVarProvider fieldsWithSameSourceVarProvider = new FieldsWithSameSourceVarProvider(fieldToSourceVariableCollector, sourceClass);
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
        final BoxedInitExpressionUpdater boxedInitExpressionUpdater = new BoxedInitExpressionUpdater(new FrameworkInferencer(), testDependencyInfoProvider);
        return new UnitTestGenerator(new PsiToTemplateDataConverter(sourceClass, new
                ImportListGenerator(), typeCreator, new SingletonDetector(), new AbstractClassInfoProvider(), javaPsiFacade, suggestedNameProvider,
                new DefaultInitSetterDecider(), dependencyFlowInfoProvider, fieldToSourceVariableCollector, beanInfoProvider, fieldsWithSameSourceVarProvider, fieldToVariableConverter, methodToMethodConverter, visibilityInfoProvider, annotationCreator, typeSubstitutorProvider, boxedInitExpressionUpdater, psiConstantEvaluationHelper),
                dependencyFlowInfoProvider, project, javaPsiFacade, configInfo, createTestFileChecker, new GroovyFileCreator(createTestFileChecker), exceptionCreator, uiUtils, codeStyleUtils, shouldAskToConfirmOptions);
    }
}
