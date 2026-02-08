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

import basetests.dataflow.FileLocation;
import basetests.dataflow.UsedUUIDInfoProvider;
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
import junit.framework.AssertionFailedError;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(Parameterized.class)
public abstract class DefaultTemplateUUIDTestBase extends SQTestBase {

    private final DefaultTemplateTestInfo defaultTemplateTestInfo;

    /**
     * Constructs the {@link DefaultTemplateTestBase}
     *
     * @param defaultTemplateTestInfo the directory in generatorTestData/ containing the files to use for this test.
     */
    public DefaultTemplateUUIDTestBase(@NotNull final DefaultTemplateTestInfo defaultTemplateTestInfo) {
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

            // Run the test.
            CommandProcessor.getInstance().executeCommand(myFixture.getProject(), () -> {
                try {
                    generator.generate();
                } catch(Exception e) {
                    throw new RuntimeException(e);
                }
            }, CodeInsightBundle.message("intention.create.test"), this);

            // Verify the results.
            // Confirm we have UUIDs in the same places in both the expected test file and the generated test file.
            // Also confirm the UUIDs which are the same in one file are also the same in the other file.
            final String expectedTestClassPath = String.format("%s/%s/test/com/myapp/%s", getTestDataPath(), defaultTemplateTestInfo.directoryName(), defaultTemplateTestInfo.testClassName());
            final UsedUUIDInfoProvider usedUUIDInfoProvider = new UsedUUIDInfoProvider();
            final String expectedTestFileText = TestUtils.safeLoadFile(expectedTestClassPath);
            final String actualTestFileText = myFixture.findClass("com.myapp.MyClassTest").getContainingFile().getText();
            final List<List<FileLocation>> expectedSameUUIDSets = usedUUIDInfoProvider.getUUIDsWithSameValues(expectedTestFileText);
            final List<List<FileLocation>> actualSameUUIDSets = usedUUIDInfoProvider.getUUIDsWithSameValues(actualTestFileText);
            try {
                validateUUIDSets(expectedSameUUIDSets, actualSameUUIDSets);
            } catch(final AssertionFailedError failure) {
                // If the UUID sets are different, log the error message describing the differences, then invoke
                // checkResultByFile(..). This will enable IntelliJ to show the expected vs. actual test class in the
                // diff viewer. This shows whether the error is due to UUIDs being wrong or some other part of the test
                // being wrong. For example, if there is an extra import line in the actual test class, the all of the
                // UUID positions in the actual test class will be off by one line compared to the expected test class.
                // This will cause the test to fail, because "all of the UUID sets are wrong".
                System.err.println(failure.getMessage());
                final String actualGeneratedClassName = String.format("com/myapp/%s", defaultTemplateTestInfo.testClassName());
                final String expectedTestClassPathFromTestDataDir = String.format("%s/test/com/myapp/%s", defaultTemplateTestInfo.directoryName(), defaultTemplateTestInfo.testClassName());
                if(TestUtils.ShouldUpdateExistingTests) {
                    updateExpectedTestClass(expectedTestClassPathFromTestDataDir);
                }
                myFixture.checkResultByFile(actualGeneratedClassName, expectedTestClassPathFromTestDataDir, true);
            }
            // Ensure the generated test class does not contain unescaped Velocity.
            assertFalse("Test class contains $. This is likely unescaped Velocity!", actualTestFileText.contains("$"));
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

    private void validateUUIDSets(
            final List<List<FileLocation>> expectedSameUUIDSets,
            final List<List<FileLocation>> actualSameUUIDSets) {
        final List<String> errors = new ArrayList<>();
        for(int i = 0; i < expectedSameUUIDSets.size() && i < actualSameUUIDSets.size(); i++) {
            // The lists are already sorted. We just need to iterate through each item pair and see if any items are missing.
            final List<FileLocation> expectedUUIDList = expectedSameUUIDSets.get(i);
            final List<FileLocation> actualUUIDList = actualSameUUIDSets.get(i);
            if(!expectedUUIDList.equals(actualUUIDList)) {
                final String error = "Expected Same UUID List does not match the actual Same UUID List.\n" +
                        "Expected Lines with Same UUIDs: \n" +
                        expectedUUIDList.stream().map(FileLocation::lineText).collect(Collectors.joining("\n")) + "\n" +
                        "Actual Lines with Same UUIDs: \n" +
                        actualUUIDList.stream().map(FileLocation::lineText).collect(Collectors.joining("\n")) + "\n";
                errors.add(error);
            }
        }

        if(expectedSameUUIDSets.size() > actualSameUUIDSets.size()) {
            final StringBuilder error = new StringBuilder("Expected Same UUID List is longer than actual.\n");
            for(int i = actualSameUUIDSets.size(); i < expectedSameUUIDSets.size(); i++) {
                final List<FileLocation> expectedUUIDList = expectedSameUUIDSets.get(i);
                error.append("Expected Extra Items: \n");
                error.append(expectedUUIDList.stream().map(FileLocation::lineText).collect(Collectors.joining("\n"))).append("\n");
            }
            errors.add(error.toString());
        } else if(expectedSameUUIDSets.size() < actualSameUUIDSets.size()) {
            final StringBuilder error = new StringBuilder("Actual Same UUID List is longer than actual.\n");
            for(int i = expectedSameUUIDSets.size(); i < actualSameUUIDSets.size(); i++) {
                final List<FileLocation> actualUUIDList = actualSameUUIDSets.get(i);
                error.append("Actual Extra Items: \n");
                error.append(actualUUIDList.stream().map(FileLocation::lineText).collect(Collectors.joining("\n"))).append("\n");
            }
            errors.add(error.toString());
        }

        if(!errors.isEmpty()) {
            final String errorMessage = String.join("\n", errors);
            fail(errorMessage);
        }
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
