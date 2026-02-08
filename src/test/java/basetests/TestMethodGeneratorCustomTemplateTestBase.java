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
package basetests;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassOwner;
import com.intellij.psi.PsiConstantEvaluationHelper;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.impl.JavaPsiFacadeEx;
import com.intellij.testFramework.LightProjectDescriptor;
import com.squaretest.annotations.AnnotationCreator;
import com.squaretest.annotations.IPropertyResolver;
import com.squaretest.annotations.MainPropertyResolver;
import com.squaretest.annotations.PropertyResolver;
import com.squaretest.annotations.SpringReferenceResolver;
import com.squaretest.completion.frameworkdetection.FrameworkInferencer;
import com.squaretest.generation.*;
import com.squaretest.generation.abstractmethods.AbstractClassInfoProvider;
import com.squaretest.generation.cleanup.CodeFormatterUtil;
import com.squaretest.generation.cleanup.RedundantThrowsRemover;
import com.squaretest.generation.cleanup.UnnecessarySemicolonRemover;
import com.squaretest.generation.dataflow.DataflowAnalyzer;
import com.squaretest.generation.dataflow.usedmethods.CalledMethodsInfoProvider;
import com.squaretest.generation.dataflow.usedmethods.UsedPropertyInfoProvider;
import com.squaretest.generation.defaulttypes.AltIOExpressionProvider;
import com.squaretest.generation.defaulttypes.AltIoExpressionPopulator;
import com.squaretest.generation.defaulttypes.DefaultDiInfoHolder;
import com.squaretest.generation.defaulttypes.DefaultTypesHolder;
import com.squaretest.generation.defaulttypes.EnumValueProvider;
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
import com.squaretest.generation.filetemplateutil.TemplateRenderingException;
import com.squaretest.generation.setters.DefaultInitSetterDecider;
import com.squaretest.generation.simpleexit.ConstantInitExpressionValueProvider;
import com.squaretest.generation.simpleexit.SimpleExitInfoProvider;
import com.squaretest.generation.singletons.SingletonDetector;
import com.squaretest.generation.sourcevars.BeanSourceVariableProvider;
import com.squaretest.generation.sourcevars.ConstructorSourceVariableProvider;
import com.squaretest.generation.sourcevars.ISourceVariableProvider;
import com.squaretest.generation.sourcevars.SourceVariableProvider;
import com.squaretest.generation.sourcevars.builders.AWSV2BuilderSourceVariableProvider;
import com.squaretest.generation.sourcevars.builders.LombokBuilderSourceVariableProvider;
import com.squaretest.generation.sourcevars.builders.Protobuf3BuilderSourceVariableProvider;
import com.squaretest.multipletests.TestMethodGenerator;
import com.squaretest.multipletests.imports.ImportUtil;
import com.squaretest.settings.store.SettingsProvider;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.ClassUtilsImpl;
import com.squaretest.template.impl.CodeStyleUtilsImpl;
import helpers.SquaretestProjectDescriptor;
import helpers.TestLibs;
import helpers.TestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableObject;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static basetests.CustomTemplateTestBase.getSingleTemplateInDirectory;
import static helpers.TestUtils.safeLoadFile;
import static helpers.TestUtils.writeFile;

@RunWith(Parameterized.class)
public abstract class TestMethodGeneratorCustomTemplateTestBase extends SQTestBase {

    private static final List<String> TestCasesWithZeroMethods = Arrays.asList("enumSimple", "defaultTypes", "noTestsForGettersOrSetters");
    private final String testCaseDirName;

    /**
     * Constructs the {@link TestMethodGeneratorTestBase}
     *
     * @param testCaseDirName the directory in TestMethodProvider/ containing the files to use for this test.
     */
    public TestMethodGeneratorCustomTemplateTestBase(@NotNull final String testCaseDirName) {
        super();
        this.testCaseDirName = testCaseDirName;
    }

    @Test
    public void testRenderTestClassToInMemoryFile() throws Throwable {
        // We need to call runTestRunnable to run the test from the AWT Dispatch thread; otherwise,
        // this fails with "Write access is allowed from event dispatch thread only".
        runTestRunnable(() -> {
            // Setup
            // Copy the test files and find the source class and existing test class.
            final String testDataPath = getTestDataPath();
            final boolean isGroovy = testDataPath.contains("/groovy/");
            final String expectedFileSuffix = isGroovy ? "groovy" : "java";
            final Project project = myFixture.getProject();
            myFixture.copyDirectoryToProject(testCaseDirName + "/main/", "");
            final String expectedOutputFilePath = String.format("%s/%s/test/com/myapp/MyClassTest.%s", testDataPath, testCaseDirName, expectedFileSuffix);
            final String expectedOutput = safeLoadFile(expectedOutputFilePath);
            final PsiClass sourceClass = myFixture.findClass("com.myapp.MyClass");
            final PsiClass existingTestClass = myFixture.findClass("com.myapp.MyClassTest");
            final PsiJavaFile psiJavaFile = (PsiJavaFile) sourceClass.getContainingFile();
            final String sourceClassPackage = psiJavaFile.getPackageName();
            final JavaCodeStyleManager javaCodeStyleManager = JavaCodeStyleManager.getInstance(project);
            final TestMethodGenerator testMethodGenerator = createTestMethodGenerator(project, sourceClassPackage, sourceClass, existingTestClass);

            // Set the template to use for the test.
            final FileTemplate fileTemplate = getSingleTemplateInDirectory(getTestDataPath());

            // Run the test.
            final List<PsiMethod> generatedMethods;
            try {
                generatedMethods = testMethodGenerator.runTemplateAndReturnTestMethods(fileTemplate);
            } catch(TemplateRenderingException e) {
                throw new RuntimeException(e);
            }
            if(generatedMethods.isEmpty()) {
                assertTrue(getTestCasesWithZeroMethods().contains(testCaseDirName));
                return;
            }

            final PsiClass generatedJavaClass = generatedMethods.get(0).getContainingClass();

            // Verify the results.
            // First, we need to put the generated test class (currently in-memory) into an actual, physical Java file.
            // The reason is: we need to run the code formatter before we compare the generated test class to the
            // expected test class; the code formatter can only be run on physical files; otherwise,
            // CodeStyleManagerImpl.reformatText() fails with an assertion failure.

            // The easiest way to put the generated test class into a physical file is to just overwrite the existing
            // test class (the one copied into the test project at the beginning of the test, not the actual file
            // checked into version control).

            // Store a pointer to the inserted test class so that we can compare it to the expected test class.
            final MutableObject<PsiElement> insertedGeneratedTestClass = new MutableObject<>();

            // Replace the test class and reformat the code. This must be done in a command, because methods that make
            // significant changes to a Java class must be run inside undo/redo-aware actions.
            CommandProcessor.getInstance().executeCommand(project, () -> ApplicationManager.getApplication().runWriteAction(() -> {

                ImportUtil.addRequiredImports(generatedJavaClass.getContainingFile(), existingTestClass.getContainingFile());

                // Replace the test class in the existing test class with the generated one.
                final PsiElement insertedElement = existingTestClass.replace(generatedJavaClass);
                insertedGeneratedTestClass.setValue(insertedElement);
                // Clean up the generated file
                reformatCode(project, javaCodeStyleManager, insertedElement.getContainingFile());
                ImportUtil.optimizeImports(insertedElement.getContainingFile());
                final PsiClass topLevelClass = ((PsiClassOwner) insertedElement.getContainingFile()).getClasses()[0];
                if(isGroovy) {
                    final UnnecessarySemicolonRemover unnecessarySemicolonRemover = new UnnecessarySemicolonRemover();
                    unnecessarySemicolonRemover.removeRedundantSemicolonsFromFile(topLevelClass.getContainingFile());
                } else {
                    final RedundantThrowsRemover redundantThrowsRemover = new RedundantThrowsRemover();
                    redundantThrowsRemover.removeRedundantThrowsFromClass(topLevelClass);
                }
            }), "Create from template", null);
            final String generatedFileText = insertedGeneratedTestClass.getValue().getContainingFile().getText().trim();
            // Verify the results.
            if(!StringUtils.equals(expectedOutput, generatedFileText)) {
                if(TestUtils.ShouldUpdateExistingTests) {
                    updateExpectedTestClass(expectedOutputFilePath, generatedFileText);
                }
            }
            assertEquals(expectedOutput, generatedFileText);
            // Ensure the generated test class does not contain unescaped Velocity.
            assertFalse("Test class contains $. This is likely unescaped Velocity!", generatedFileText.contains("$"));
        });
    }

    protected TestMethodGenerator createTestMethodGenerator(final Project project, final String sourceClassPackage, final PsiClass sourceClass, final PsiClass existingTestClass) {
        final VisibilityInfoProvider visibilityInfoProvider = new VisibilityInfoProvider(sourceClassPackage);
        final MemberFieldPrefixRemover memberFieldPrefixRemover = new MemberFieldPrefixRemover();
        final SuggestedNameProvider suggestedNameProvider =
                new SuggestedNameProvider(JavaCodeStyleManager.getInstance(project), memberFieldPrefixRemover);
        final JavaPsiFacadeEx javaPsiFacade = myFixture.getJavaFacade();
        final TestDependencyInfoProvider testDependencyInfoProvider = new TestDependencyInfoProvider(javaPsiFacade, myFixture.getModule());
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
        final JavaLibraryReferenceProvider javaLibraryReferenceProvider = new JavaLibraryReferenceProvider(dataflowAnalyzer);
        final StringAndUUIDValueProvider stringAndUUIDValueProvider = new StringAndUUIDValueProvider(dataflowAnalyzer, beanInfoProvider, memberFieldPrefixRemover);
        final PrimitiveValueProvider primitiveValuesProvider = new PrimitiveValueProvider(dataflowAnalyzer);
        final EnumValueProvider enumValueProvider = new EnumValueProvider(dataflowAnalyzer);
        final SettingsProvider settingsProvider = new SettingsProvider();
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
        final PsiToTemplateDataConverter psiToTemplateDataConverter = new PsiToTemplateDataConverter(sourceClass, new ImportListGenerator(), typeCreator, new SingletonDetector(), new AbstractClassInfoProvider(), javaPsiFacade,
                suggestedNameProvider,
                new DefaultInitSetterDecider(), dependencyFlowInfoProvider, fieldToSourceVariableCollector, beanInfoProvider, fieldsWithSameSourceVarProvider, fieldToVariableConverter, methodToMethodConverter, visibilityInfoProvider, annotationCreator, typeSubstitutorProvider, boxedInitExpressionUpdater, psiConstantEvaluationHelper);
        final JavaCodeStyleManager javaCodeStyleManager = JavaCodeStyleManager.getInstance(project);
        final Api.CodeStyleUtils codeStyleUtils = new CodeStyleUtilsImpl(javaCodeStyleManager);
        final CreateTestFileChecker createTestFileChecker = new CreateTestFileChecker(new ExistingTestFinder(project), new DirectoryChecker());
        return new TestMethodGenerator(
                project, javaPsiFacade, psiToTemplateDataConverter, dependencyFlowInfoProvider, exceptionCreator, codeStyleUtils,
                new ClassUtilsImpl(existingTestClass, JavaPsiFacade.getInstance(project), psiToTemplateDataConverter, defaultTypesHolder),
                createTestFileChecker, (PsiJavaFile) sourceClass.getContainingFile(), existingTestClass);
    }

    /**
     * Writes the generated test class's contents to the expected test class file; i.e. sets the expected result to the actual
     * result.
     * WARNING: This only exists to facilitate updating large numbers of automated tests at once.
     * Each change (diff between the expected test class and actual test class) should be verified manually before using this.
     * Each change should also be reviewed manually using "git diff" before pushing the changes.
     */
    private void updateExpectedTestClass(final String expectedOutputFilePath, final String generatedFileText) {
        writeFile(expectedOutputFilePath, generatedFileText);
    }

    protected List<String> getTestCasesWithZeroMethods() {
        return TestCasesWithZeroMethods;
    }

    @NotNull
    @Override
    protected LightProjectDescriptor getProjectDescriptor() {
        return new SquaretestProjectDescriptor(TestLibs.CommonLibs, List.of(TestLibs.Guava));
    }

    private void reformatCode(
            final Project project, final JavaCodeStyleManager javaCodeStyleManager, final PsiElement insertedElement) {
        javaCodeStyleManager.shortenClassReferences(insertedElement);
        CodeFormatterUtil.cleanUpElements(project, new PsiElement[]{insertedElement});
    }
}
