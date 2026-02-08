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
package javatests;

import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiParameterList;
import com.intellij.testFramework.LightProjectDescriptor;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import com.squaretest.generation.TypeSubstitutorProvider;
import com.squaretest.generation.dependencyinteraction.DependencyInteractionCollectorImpl;
import com.squaretest.generation.dependencyinteraction.DependencyInteractionSet;
import com.squaretest.generation.dependencyinteraction.InternalDependencyInteraction;
import com.squaretest.generation.dependencyinteraction.MethodCallsProvider;
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
import java.util.Map;

import static helpers.TestUtils.safeLoadFile;

@RunWith(Parameterized.class)
public class DependencyInteractionCollectorImplTests extends LightJavaCodeInsightFixtureTestCase {

    private static final String TestDataDir = "src/test/data/java/DependencyInteractions";

    private final String testCaseDirName;

    public DependencyInteractionCollectorImplTests(@NotNull final String testCaseDirName) {
        super();
        this.testCaseDirName = testCaseDirName;
    }

    @Test
    public void testComputeMethodToPsiMap() throws Throwable {
        // We need to call runTestRunnable to run the test from the AWT Dispatch thread; otherwise,
        // this fails with "Write access is allowed from event dispatch thread only".
        runTestRunnable(() -> {
            // Setup.
            myFixture.copyDirectoryToProject(testCaseDirName + "/main/", "");
            final String expectedOutputFilePath = String.format("%s/%s/test/expectedOutput.txt", getTestDataPath(), testCaseDirName);
            final String expectedOutput = safeLoadFile(expectedOutputFilePath);

            // Run the test.
            final PsiClass psiClass = myFixture.findClass("com.myapp.MyClass");
            final JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(psiClass.getProject());
            final DependencyInteractionCollectorImpl dependencyFlowInfoProvider = new DependencyInteractionCollectorImpl(psiClass, new TypeSubstitutorProvider(javaPsiFacade, psiClass), new MethodCallsProvider());
            final Map<PsiMethod, DependencyInteractionSet> dependencyInteractions = dependencyFlowInfoProvider.computeMethodToPsiMap();
            final String actualOutput = serialize(dependencyInteractions);

            // Verify the results.
            assertEquals(expectedOutput, actualOutput);
        });
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<String> testParameters() {
        final String[] files = new File(TestDataDir).list();
        return Arrays.asList(files);
    }

    private static String serialize(final Map<PsiMethod, DependencyInteractionSet> dependencyInteractions) {
        // Sort keys alphabetically to ensure deterministic output.
        final List<PsiMethod> sortedKeys = dependencyInteractions.keySet().stream().sorted((method1, method2) -> {
            if(!method1.getName().equals(method2.getName())) {
                return method1.getName().compareTo(method2.getName());
            } else {
                return Integer.compare(method1.getParameterList().getParametersCount(), method2.getParameterList().getParametersCount());
            }
        }).toList();

        final StringBuilder builder = new StringBuilder();
        for(final PsiMethod key : sortedKeys) {
            final DependencyInteractionSet value = dependencyInteractions.get(key);

            final String serializedMethod = String.format("%s(%s)", key.getName(), serializeParamsList(key));
            builder.append(serializedMethod).append("\n");
            for(final InternalDependencyInteraction interaction : value) {
                builder.append(" ").append(interaction).append("\n");
            }
            builder.append("\n");
        }
        return builder.toString().trim();
    }

    private static String serializeParamsList(final PsiMethod psiMethod) {
        final StringBuilder ret = new StringBuilder();
        final PsiParameterList paramsList = psiMethod.getParameterList();
        for(final PsiParameter param : paramsList.getParameters()) {
            ret.append(param.getName()).append(", ");
        }
        final String returnString = ret.toString();
        if(returnString.endsWith(", ")) {
            return returnString.substring(0, returnString.length() - 2);
        }
        return returnString;
    }

    @Override
    protected String getTestDataPath() {
        return TestDataDir;
    }

    @NotNull
    @Override
    protected LightProjectDescriptor getProjectDescriptor() {
        return new SquaretestProjectDescriptor(TestLibs.CommonLibs, List.of(TestLibs.Guava));
    }
}
