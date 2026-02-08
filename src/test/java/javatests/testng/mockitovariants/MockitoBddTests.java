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
package javatests.testng.mockitovariants;

import basetests.QuickSettingsTestBase;
import com.intellij.testFramework.LightProjectDescriptor;
import com.squaretest.generation.UnitTestGenerator;
import com.squaretest.generation.runconfig.ConfigInfo;
import helpers.*;
import org.jetbrains.annotations.NotNull;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@RunWith(Parameterized.class)
public class MockitoBddTests extends QuickSettingsTestBase {

    private static final String TestDataDir = "src/test/data/java/testng/mockitovariants/MockitoBdd";

    public MockitoBddTests(@NotNull final QuickSettingsTestInfo quickSettingsTestInfo) {
        super(quickSettingsTestInfo);
    }

    @NotNull
    @Override
    protected UnitTestGenerator createUnitTestGenerator(final ConfigInfo configInfo) {
        return UnitTestGeneratorCreator.createJavaUnitTestGenerator(myFixture.getProject(), myFixture.getModule(), configInfo);
    }

    @Override
    protected String getTestDataPath() {
        return TestDataDir;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<QuickSettingsTestInfo> testParameters() {
        final File rootTestDir = new File(TestDataDir);
        final List<QuickSettingsTestInfo> quickSettingsTestInfos = new LinkedList<>();
        for (final File testGroupDir : rootTestDir.listFiles()) {
            quickSettingsTestInfos.addAll(
                    QuickSettingsTestInfoCreator.createTestsForGroup(
                            TestDataDir, testGroupDir, "MyClassTest.java"));
        }
        return quickSettingsTestInfos;
    }

    @NotNull
    @Override
    protected LightProjectDescriptor getProjectDescriptor() {
        return new SquaretestProjectDescriptor(TestLibs.CommonLibs, Arrays.asList(TestLibs.TestNG6_8, TestLibs.Mockito3_0, TestLibs.Guava));
    }
}
