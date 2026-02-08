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
package groovytests.junit5.spring;

import basetests.GroovyTestMethodGeneratorCustomTemplateTestBase;
import basetests.TestMethodGeneratorCustomTemplateTestBase;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.testFramework.LightProjectDescriptor;
import helpers.SquaretestProjectDescriptor;
import helpers.TestLibs;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class JUnit5MockitoSpring172TestMethodGeneratorTests extends GroovyTestMethodGeneratorCustomTemplateTestBase {

    private static final String TestDataDir = "src/test/data/groovy/junit5/spring/JUnit5MockitoSpring172TestMethodGeneratorTests";

    public JUnit5MockitoSpring172TestMethodGeneratorTests(@NotNull final String testCaseDirName) {
        super(testCaseDirName);
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<String> testParameters() {
        final File testDataDir = new File(TestDataDir);
        final File[] testDirs = Objects.requireNonNull(testDataDir.listFiles(File::isDirectory));
        return Arrays.stream(testDirs).map(File::getName).collect(Collectors.toList());
    }

    @Override
    protected String getTestDataPath() {
        return TestDataDir;
    }

    @NotNull
    @Override
    protected LightProjectDescriptor getProjectDescriptor() {
        return new SquaretestProjectDescriptor(LanguageLevel.JDK_17, TestLibs.CommonLibs, TestLibs.SpringFrameworkLibs, TestLibs.JakartaValidationLibs, TestLibs.JacksonLibs, Arrays.asList(TestLibs.SquaretestSuperTypes, TestLibs.Mockito3_0, TestLibs.JUnit5_7, TestLibs.Guava));
    }
}