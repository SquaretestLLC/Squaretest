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
package javatests.junit4.spring.userselection;

import basetests.DefaultTemplateTestBase;
import com.intellij.testFramework.LightProjectDescriptor;
import com.squaretest.generation.UnitTestGenerator;
import com.squaretest.generation.runconfig.ConfigInfo;
import helpers.DefaultTemplateTestInfo;
import helpers.SquaretestProjectDescriptor;
import helpers.TestLibs;
import helpers.UnitTestGeneratorCreator;
import helpers.uiUtils.SelectAllDepsUiUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class SelectAllDepsAndFieldsTests extends DefaultTemplateTestBase {

    private static final String TestDataDir = "src/test/data/java/junit4/spring/userselection/SelectAllDepsAndFields";
    private static final String TemplateName = "SQTJ-JUnit4MockitoSpring.java";

    public SelectAllDepsAndFieldsTests(@NotNull final DefaultTemplateTestInfo defaultTemplateTestInfo) {
        super(defaultTemplateTestInfo);
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<DefaultTemplateTestInfo> testParameters() {
        final String[] files = new File(TestDataDir).list();
        final List<DefaultTemplateTestInfo> testCaseDirList = new ArrayList<>();
        for (final String testCaseDir : files) {
            testCaseDirList.add(new DefaultTemplateTestInfo(testCaseDir, "MyClassTest.java"));
        }
        return testCaseDirList;
    }

    @NotNull
    @Override
    protected UnitTestGenerator createUnitTestGenerator(final ConfigInfo configInfo) {
        return UnitTestGeneratorCreator.createJavaUnitTestGenerator(myFixture.getProject(), myFixture.getModule(), configInfo, new SelectAllDepsUiUtils());
    }

    @Override
    protected String getTestDataDir() {
        return TestDataDir;
    }

    @Override
    protected String getInternalTemplateName() {
        return TemplateName;
    }

    @NotNull
    @Override
    protected LightProjectDescriptor getProjectDescriptor() {
        return new SquaretestProjectDescriptor(TestLibs.CommonLibs, TestLibs.SpringFrameworkLibs, TestLibs.JakartaValidationLibs, TestLibs.JacksonLibs, Arrays.asList(TestLibs.SquaretestSuperTypes, TestLibs.Mockito3_0, TestLibs.JUnit4_12, TestLibs.Guava));
    }
}
