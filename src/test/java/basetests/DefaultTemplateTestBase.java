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

import com.intellij.codeInsight.CodeInsightBundle;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiManager;
import com.intellij.testFramework.LightPlatformTestCase;
import com.squaretest.generation.UnitTestGenerator;
import com.squaretest.generation.runconfig.ConfigInfo;
import helpers.DefaultTemplateTestInfo;
import helpers.TestUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

/**
 * Base class for default template tests.
 * <p>
 * I strongly dislike using inheritance for these tests, but I believe it is the best choice, because
 * the subclass needs to provide a static method that provides the test parameters, and
 * I don't know of a good way to do that with composition. Eventually we should update the tests to use
 * JUnit5 to reduce the amount of boilerplace code required in the subclasses.
 * <p>
 * Design Note: I chose to create separate test classes for each template so that
 * you can easily run tests for a single template.
 * <p>
 * Subclasses must do the following
 * <ol>
 * <li>
 * Implement the abstract methods.
 * </li>
 * <li>
 * Implement an {@link Parameterized.Parameters} annotated method that returns a
 * {@link Collection Collection<DefaultTemplateTestInfo>}.
 * </li>
 * </ol>
 */
@RunWith(Parameterized.class)
public abstract class DefaultTemplateTestBase extends SQTestBase {

    private final DefaultTemplateTestInfo defaultTemplateTestInfo;

    /**
     * Constructs the {@link DefaultTemplateTestBase}
     *
     * @param defaultTemplateTestInfo the directory in generatorTestData/ containing the files to use for this test.
     */
    public DefaultTemplateTestBase(@NotNull final DefaultTemplateTestInfo defaultTemplateTestInfo) {
        super();
        this.defaultTemplateTestInfo = defaultTemplateTestInfo;
    }

    @Test
    public void testGenerateTests() throws Throwable {
        // We need to call runTestRunnable to run the test from the AWT Dispatch thread; otherwise,
        // this fails with "Write access is allowed from event dispatch thread only".
        runTestRunnable(() -> {
            myFixture.copyDirectoryToProject(defaultTemplateTestInfo.directoryName() + "/main/", "");
            final PsiClass psiClass = myFixture.findClass("com.myapp.MyClass");
            final PsiJavaFile psiJavaFile = (PsiJavaFile) psiClass.getContainingFile();

            final FileTemplateManager manager = FileTemplateManager.getDefaultInstance();
            final FileTemplate template = manager.getInternalTemplate(getInternalTemplateName());
            final ConfigInfo configInfo = new ConfigInfo(
                    template,
                    PsiManager.getInstance(myFixture.getProject()).findDirectory(LightPlatformTestCase.getSourceRoot()), psiJavaFile);
            final UnitTestGenerator generator = createUnitTestGenerator(configInfo);

            CommandProcessor.getInstance().executeCommand(myFixture.getProject(), () -> {
                try {
                    generator.generate();
                } catch(Exception e) {
                    throw new RuntimeException(e);
                }
            }, CodeInsightBundle.message("intention.create.test"), this);

            // Verify the generated file matches the expected file.
            final String actualGeneratedClassName = String.format("com/myapp/%s", defaultTemplateTestInfo.testClassName());
            final String expectedTestClassPath = String.format("%s/test/com/myapp/%s", defaultTemplateTestInfo.directoryName(), defaultTemplateTestInfo.testClassName());
            try {
                myFixture.checkResultByFile(actualGeneratedClassName, expectedTestClassPath, true);
            } catch(final Throwable failure) {
                if(TestUtils.ShouldUpdateExistingTests) {
                    updateExpectedTestClass(expectedTestClassPath);
                }
                throw failure;
            }
            // Ensure the generated test class does not contain unescaped Velocity.
            assertFalse("Test class contains $. This is likely unescaped Velocity!", myFixture.findClass("com.myapp.MyClassTest").getContainingFile().getText().contains("$"));
        });
    }

    /**
     * Writes the generated test class's contents to the expected test class file; i.e. sets the expected result to the actual
     * result.
     * WARNING: This only exists to facilitate updating large numbers of automated tests at once.
     * Each change (diff between the expected test class and actual test class) should be verified manually before using this.
     * Each change should also be reviewed manually using "git diff" before pushing the changes.
     *
     * @param pathToExpectedClassFile the path to the expected test class file.
     */
    private void updateExpectedTestClass(final String pathToExpectedClassFile) {
        final String fullPathToExpectedFile = getTestDataPath() + "/" + pathToExpectedClassFile;
        final String actualFileContents = myFixture.findClass("com.myapp.MyClassTest").getContainingFile().getText();
        TestUtils.writeFile(fullPathToExpectedFile, actualFileContents);
    }

    @NotNull
    protected abstract UnitTestGenerator createUnitTestGenerator(final ConfigInfo configInfo);

    @Override
    protected String getTestDataPath() {
        return getTestDataDir();
    }

    /**
     * @return the test data path; this is the same as {@link #getTestDataPath()}, just
     * formally required to avoid forgetting to implement it.
     */
    protected abstract String getTestDataDir();

    protected abstract String getInternalTemplateName();
}
