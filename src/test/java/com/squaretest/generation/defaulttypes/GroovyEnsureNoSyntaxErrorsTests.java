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
package com.squaretest.generation.defaulttypes;

import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.codeInspection.JavaApiUsageInspection;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.PsiClass;
import com.intellij.testFramework.LightProjectDescriptor;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import com.intellij.testFramework.fixtures.impl.CodeInsightTestFixtureImpl;
import helpers.SquaretestProjectDescriptor;
import helpers.TestLibs;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(Parameterized.class)
public class GroovyEnsureNoSyntaxErrorsTests extends LightJavaCodeInsightFixtureTestCase {

    private static boolean hasGeneratedJavaFilesFromDefaultTypesMap = false;
    private static final String TestDataDir = "src/test/data/java/Generated/Groovy";
    private final String testCaseDirName;

    public GroovyEnsureNoSyntaxErrorsTests(@NotNull final String testCaseDirName) {
        super();
        this.testCaseDirName = testCaseDirName;
    }

    @Test
    public void testEnsureNoSyntaxErrorsInGeneratedFiles() throws Throwable {
        myFixture.enableInspections(new JavaApiUsageInspection());
        runTestRunnable(() -> {
            // Setup.
            myFixture.copyDirectoryToProject(testCaseDirName + "/generated", "generated");

            // Run the test.
            // Find the DefaultTypes class and open it.
            final PsiClass psiClass = myFixture.findClass("generated.DefaultTypes");
            myFixture.openFileInEditor(psiClass.getContainingFile().getVirtualFile());

            // Run the highlighters.
            final List<HighlightInfo> highlightInfos = CodeInsightTestFixtureImpl.instantiateAndRun(psiClass.getContainingFile(), getEditor(), new int[]{}, false);
            final List<HighlightInfo> errorInfos = highlightInfos.stream().filter(x -> x.getSeverity() == HighlightSeverity.ERROR).toList();

            // Check for syntax errors.
            if(!errorInfos.isEmpty()) {
                final String message = errorInfos.stream().map(HighlightInfo::toString).collect(Collectors.joining(", "));
                fail("Syntax errors reported in auto generated DefaultTypes file: " + message);
            }
        });
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<String> testParameters() {
        generateSourcesIfNeeded();
        final String[] files = new File(TestDataDir).list();
        return Arrays.asList(files);
    }

    private static synchronized void generateSourcesIfNeeded() {
        if(!hasGeneratedJavaFilesFromDefaultTypesMap) {
            final GroovySourcesGenerator groovySourcesGenerator = new GroovySourcesGenerator(
                    DefaultTypesHolder.getInstance().getCanonicalNameToDefaultTypeMap(),
                    "src/test/data/java/Generated/Groovy/4/generated");
            groovySourcesGenerator.generateAll();
            hasGeneratedJavaFilesFromDefaultTypesMap = true;
        }
    }

    @Override
    protected String getTestDataPath() {
        return TestDataDir;
    }

    @NotNull
    @Override
    protected LightProjectDescriptor getProjectDescriptor() {
        return new SquaretestProjectDescriptor(LanguageLevel.JDK_X, TestLibs.GRPCApi, TestLibs.AWSV1Libs, TestLibs.AWSV2Libs,
                TestLibs.AWSV1LambdaLibs, TestLibs.CommonLibs, TestLibs.JacksonLibs, TestLibs.ElasticSearchLibs,
                TestLibs.GoogleCloudLibs, TestLibs.SpringFrameworkLibs, TestLibs.JakartaWSAPILibs,
                TestLibs.JavaXWSAPILibs, TestLibs.ApacheHttpLibs, TestLibs.TencentLibs,
                Arrays.asList(TestLibs.CommonsLang2, TestLibs.JodaTime, TestLibs.Guava, TestLibs.OkHttp3, TestLibs.RxJava3, TestLibs.JavaXPersistence));
    }
}