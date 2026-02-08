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
package groovytests.junit4.assertj.java17;

import basetests.GroovyTestMethodGeneratorTestBase;
import basetests.TestMethodGeneratorTestBase;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.testFramework.LightProjectDescriptor;
import com.squaretest.TemplateProvider;
import com.squaretest.settings.store.Template;
import helpers.SquaretestProjectDescriptor;
import helpers.TestLibs;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class JUnit4MockitoAssertJTestMethodGeneratorTests extends GroovyTestMethodGeneratorTestBase {

    private static final String TestDataDir = "src/test/data/groovy/junit4/assertj/java17/JUnit4MockitoAssertJTestMethodGenerator";

    public JUnit4MockitoAssertJTestMethodGeneratorTests(@NotNull final String testCaseDirName) {
        super(testCaseDirName);
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<String> testParameters() {
        final String[] files = new File(TestDataDir).list();
        return Arrays.asList(files);
    }

    @Override
    protected Template getTemplate() {
        return TemplateProvider.getJUnit4AssertJGroovyTemplate();
    }

    @Override
    protected String getTestDataPath() {
        return TestDataDir;
    }

    @NotNull
    @Override
    protected LightProjectDescriptor getProjectDescriptor() {
        return new SquaretestProjectDescriptor(LanguageLevel.JDK_17, TestLibs.CommonLibs, TestLibs.SpringFrameworkLibs, TestLibs.JakartaValidationLibs, TestLibs.JacksonLibs, Arrays.asList(TestLibs.SquaretestSuperTypes, TestLibs.Mockito3_0, TestLibs.JUnit4_12, TestLibs.Guava, TestLibs.AssertJ));
    }
}