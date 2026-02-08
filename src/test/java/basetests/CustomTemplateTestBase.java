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
import helpers.CustomTemplateTestInfo;
import helpers.TestUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@RunWith(Parameterized.class)
public abstract class CustomTemplateTestBase extends SQTestBase {

    private final CustomTemplateTestInfo defaultTemplateTestInfo;

    public CustomTemplateTestBase(@NotNull final CustomTemplateTestInfo defaultTemplateTestInfo) {
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
            final FileTemplate template = getSingleTemplateInDirectory(defaultTemplateTestInfo.rootDirName());
            final ConfigInfo configInfo = new ConfigInfo(
                    template,
                    PsiManager.getInstance(myFixture.getProject()).findDirectory(LightPlatformTestCase.getSourceRoot()), psiJavaFile);
            final UnitTestGenerator generator = createUnitTestGenerator(configInfo);

            CommandProcessor.getInstance().executeCommand(myFixture.getProject(), new Runnable() {
                @Override
                public void run() {
                    try {
                        generator.generate();
                    } catch(Exception e) {
                        throw new RuntimeException(e);
                    }
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

    public static FileTemplate getSingleTemplateInDirectory(final String rootDirName) {
        final File dir = new File(rootDirName);
        final File[] templateFiles = dir.listFiles((dir1, filename) -> filename.endsWith(".ft"));
        if(templateFiles == null || templateFiles.length == 0) {
            throw new RuntimeException("Root dir: " + rootDirName + " does not contain a Velocity template.");
        }
        if(templateFiles.length > 1) {
            throw new RuntimeException("Root dir: " + rootDirName + " contains multiple Velocity templates.");
        }
        final File templateFile = templateFiles[0];
        String contents;
        try {
            contents = Files.readString(templateFile.toPath(), StandardCharsets.UTF_8);
        } catch(final IOException e) {
            throw new RuntimeException(e);
        }
        final FileTemplateManager templateManager = FileTemplateManager.getDefaultInstance();
        String extension = templateFile.getPath().endsWith("groovy.ft") ? "groovy" : "java";
        final FileTemplate fileTemplate = templateManager.addTemplate("UNIT_TEST_TEMPLATE", extension);
        fileTemplate.setText(contents);
        return fileTemplate;
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

}
