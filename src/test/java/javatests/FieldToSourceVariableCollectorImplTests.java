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

import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiParameterList;
import com.intellij.psi.PsiVariable;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.testFramework.LightProjectDescriptor;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import com.squaretest.generation.VisibilityInfoProvider;
import com.squaretest.generation.defaulttypes.DefaultTypesHolder;
import com.squaretest.generation.defaulttypes.beans.BeanInfoProvider;
import com.squaretest.generation.dependencyinteraction.FieldToSourceVariableCollectorImpl;
import com.squaretest.utils.IdentityMultimapWithIdentitySets;
import helpers.SquaretestProjectDescriptor;
import helpers.TestLibs;
import helpers.TestUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@RunWith(Parameterized.class)
public class FieldToSourceVariableCollectorImplTests extends LightJavaCodeInsightFixtureTestCase {

    private static final String TestDataDir = "src/test/data/java/FieldToSourceVariablePopulatorTests";
    private final String testCaseDirName;

    public FieldToSourceVariableCollectorImplTests(@NotNull final String testCaseDirName) {
        super();
        this.testCaseDirName = testCaseDirName;
    }

    @Test
    public void testComputeSourceVariablesForMembers() throws Throwable {
        // We need to call runTestRunnable to run the test from the AWT Dispatch thread; otherwise,
        // this fails with "Write access is allowed from event dispatch thread only".
        runTestRunnable(() -> {
            // Setup.
            myFixture.copyDirectoryToProject(testCaseDirName + "/main/", "");
            final String expectedOutputFilePath = String.format("%s/%s/test/expectedOutput.txt", getTestDataPath(), testCaseDirName);
            final String expectedOutput = TestUtils.safeLoadFile(expectedOutputFilePath);

            // Run the test.
            final PsiClass psiClass = myFixture.findClass("com.myapp.MyClass");
            final BeanInfoProvider beanInfoProvider = new BeanInfoProvider(DefaultTypesHolder.getInstance(), new VisibilityInfoProvider(((PsiJavaFile) psiClass.getContainingFile()).getPackageName()));
            final FieldToSourceVariableCollectorImpl fieldToSourceVariableCollector = new FieldToSourceVariableCollectorImpl(beanInfoProvider, psiClass);
            final IdentityMultimapWithIdentitySets<PsiField, PsiVariable> sourceVarsMap = fieldToSourceVariableCollector.computeSourceVariablesForMembers();
            final String actualOutput = serialize(sourceVarsMap);

            // Verify the results.
            assertEquals(expectedOutput, actualOutput);
        });
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<String> testParameters() {
        final String[] files = new File(TestDataDir).list();
        return Arrays.asList(files);
    }

    private static String serialize(final IdentityMultimapWithIdentitySets<PsiField, PsiVariable> sourceVarsMap) {
        // Sort keys alphabetically to ensure deterministic output.
        final List<PsiField> sortedKeys = sourceVarsMap.keySet().stream().sorted(Comparator.comparing(NavigationItem::getName)).toList();

        final StringBuilder builder = new StringBuilder();
        for(final PsiField key : sortedKeys) {
            // Sort the source-vars to ensure deterministic output.
            // Use the following ordering: fields first, them sort methods by name, then by number of parameters.
            final Collection<PsiVariable> sourceVariables = sourceVarsMap.get(key).stream().sorted((source1, source2) -> {
                if(source1 instanceof PsiField) {
                    if(source2 instanceof PsiField) {
                        return source1.getName().compareTo(source2.getName());
                    }
                    return -1;
                } else if(source2 instanceof PsiField) {
                    return 1;
                }

                final PsiMethod method1 = PsiTreeUtil.getParentOfType(source1, PsiMethod.class);
                final PsiMethod method2 = PsiTreeUtil.getParentOfType(source2, PsiMethod.class);

                if(!method1.getName().equals(method2.getName())) {
                    return method1.getName().compareTo(method2.getName());
                }

                return Integer.compare(method1.getParameterList().getParametersCount(), method2.getParameterList().getParametersCount());
            }).toList();

            builder.append(key.getName()).append("\n");
            for(final PsiVariable constructorVar : sourceVariables) {
                builder.append(" ").append(serializeConstructorVar(constructorVar)).append("\n");
            }
            builder.append("\n");
        }
        return builder.toString().trim();
    }

    private static String serializeConstructorVar(final PsiVariable constructorVar) {
        if(!(constructorVar instanceof final PsiParameter paramVar)) {
            return constructorVar.getName();
        }

        final PsiMethod method = PsiTreeUtil.getParentOfType(paramVar, PsiMethod.class);
        if(method == null) {
            throw new RuntimeException("Found PsiParameter without PsiMethod parent");
        }

        return String.format("%s(%s)", method.getName(), serializeParamsList(method, paramVar));
    }

    private static String serializeParamsList(final PsiMethod psiMethod, final PsiVariable paramToHighlight) {
        final StringBuilder ret = new StringBuilder();
        final PsiParameterList paramsList = psiMethod.getParameterList();
        for(final PsiParameter param : paramsList.getParameters()) {
            if(param == paramToHighlight) {
                ret.append(String.format("<<%s>>", param.getName()));
            } else {
                ret.append(param.getName());
            }
            ret.append(", ");
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
