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
package groovytests.junit4.assertj.mockitovariants;

import basetests.QuickSettingsTestBase;
import com.intellij.testFramework.LightProjectDescriptor;
import com.squaretest.generation.UnitTestGenerator;
import com.squaretest.generation.runconfig.ConfigInfo;
import helpers.QuickSettingsTestInfo;
import helpers.QuickSettingsTestInfoCreator;
import helpers.SquaretestProjectDescriptor;
import helpers.TestLibs;
import helpers.UnitTestGeneratorCreator;
import org.jetbrains.annotations.NotNull;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


@RunWith(Parameterized.class)
public class Mockito37RunnerDisabledTests extends QuickSettingsTestBase {

    private static final String TestDataDir = "src/test/data/groovy/junit4/assertj/mockitovariants/Mockito37RunnerDisabled";

    public Mockito37RunnerDisabledTests(@NotNull final QuickSettingsTestInfo quickSettingsTestInfo) {
        super(quickSettingsTestInfo);
    }

    @NotNull
    @Override
    protected UnitTestGenerator createUnitTestGenerator(final ConfigInfo configInfo) {
        return UnitTestGeneratorCreator.createGroovyUnitTestGenerator(myFixture.getProject(), myFixture.getModule(), configInfo);
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
                            TestDataDir, testGroupDir, "MyClassTest.groovy"));
        }
        return quickSettingsTestInfos;
    }

    @NotNull
    @Override
    protected LightProjectDescriptor getProjectDescriptor() {
        return new SquaretestProjectDescriptor(TestLibs.CommonLibs, Arrays.asList(TestLibs.JUnit4_12, TestLibs.Mockito3_7, TestLibs.Guava, TestLibs.AssertJ));
    }
}
