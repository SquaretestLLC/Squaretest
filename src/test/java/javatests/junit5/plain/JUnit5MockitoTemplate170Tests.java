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
package javatests.junit5.plain;

import basetests.CustomTemplateTestBase;
import com.intellij.testFramework.LightProjectDescriptor;
import com.squaretest.generation.UnitTestGenerator;
import com.squaretest.generation.runconfig.ConfigInfo;
import helpers.CustomTemplateTestInfo;
import helpers.SquaretestProjectDescriptor;
import helpers.TestLibs;
import helpers.UnitTestGeneratorCreator;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.junit.runners.Parameterized;

public class JUnit5MockitoTemplate170Tests extends CustomTemplateTestBase {

    private static final String TestDataDir = "src/test/data/java/junit5/plain/JUnit5MockitoTemplate170Tests";

    public JUnit5MockitoTemplate170Tests(@NotNull final CustomTemplateTestInfo defaultTemplateTestInfo) {
        super(defaultTemplateTestInfo);
    }

    @Parameterized.Parameters (name = "{0}")
    public static Collection<CustomTemplateTestInfo> testParameters() {
        final String[] files = new File(TestDataDir).list();
        final List<CustomTemplateTestInfo> testCaseDirList = new ArrayList<>();
        for (final String testCaseDir : files) {
            if(!testCaseDir.endsWith(".ft")) {
                testCaseDirList.add(new CustomTemplateTestInfo(TestDataDir, testCaseDir, "MyClassTest.java"));
            }
        }
        return testCaseDirList;
    }

    @NotNull
    @Override
    protected UnitTestGenerator createUnitTestGenerator(final ConfigInfo configInfo) {
        return UnitTestGeneratorCreator.createJavaUnitTestGenerator(myFixture.getProject(), myFixture.getModule(), configInfo);
    }

    @Override
    protected String getTestDataDir() {
        return TestDataDir;
    }

    @NotNull
    @Override
    protected LightProjectDescriptor getProjectDescriptor() {
        return new SquaretestProjectDescriptor(TestLibs.CommonLibs, TestLibs.JacksonLibs,
                TestLibs.AWSV1Libs, TestLibs.JakartaWSAPILibs,
                TestLibs.JavaXWSAPILibs, TestLibs.JJWT,
                Arrays.asList(TestLibs.JodaTime, TestLibs.SwaggerAnnotationsV1, TestLibs.SquaretestSuperTypes,
                        TestLibs.Guava), Arrays.asList(TestLibs.Mockito3_0, TestLibs.JUnit5_7));
    }
}