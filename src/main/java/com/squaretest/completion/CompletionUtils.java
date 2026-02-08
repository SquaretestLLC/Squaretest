/*
 * Copyright 2026 Squaretest LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squaretest.completion;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiImportList;
import com.intellij.psi.PsiImportStatement;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Equator;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.plugins.groovy.lang.psi.GroovyFile;
import org.jetbrains.plugins.groovy.lang.psi.api.toplevel.imports.GrImportStatement;
import org.jetbrains.plugins.groovy.lang.psi.impl.synthetic.GrLightMethodBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CompletionUtils {

    private static final List<String> NonTestMethodNames = Arrays.asList(
            "setUp", "before", "beforeClass", "after", "afterClass");

    private static final Equator<PsiElement> ImportEquator = new Equator<>() {
        @Override
        public boolean equate(final PsiElement o1, final PsiElement o2) {
            return o1.getText().equals(o2.getText());
        }

        @Override
        public int hash(final PsiElement o) {
            return Objects.hash(o.getText());
        }
    };

    public static boolean isLikelyTestMethod(final PsiMethod psiMethod) {
        if(psiMethod instanceof GrLightMethodBuilder || psiMethod.getName().equals("invokeMethod")) {
            return false;
        }
        return !CompletionUtils.isSetupOrTearDownMethod(psiMethod);
    }

    public static boolean isSetupOrTearDownMethod(final PsiMethod testMethod) {
        final String testMethodName = testMethod.getName();
        for(final String nonTestName : NonTestMethodNames) {
            if(nonTestName.equalsIgnoreCase(testMethodName)) {
                return true;
            }
        }
        return false;
    }

    public static void renameTestMethodToAvoidCollisions(final PsiClass testClass, final PsiMethod testMethod) {
        final String originalMethodName = testMethod.getName();
        String newMethodName = testMethod.getName();
        int count = 1;
        while(testClass.findMethodsByName(newMethodName, false).length > 0 && count < 10) {
            newMethodName = originalMethodName + count++;
        }
        if(!newMethodName.equals(originalMethodName)) {
            testMethod.setName(newMethodName);
        }
    }

    static Collection<GrImportStatement> determineImportsToAddToGroovyClass(
            final GroovyFile generatedTestClass, final GroovyFile existingTestClass) {
        final List<GrImportStatement> generatedImportStatements = getImportListForGroovyFile(generatedTestClass);
        final List<GrImportStatement> existingImportStatements = getImportListForGroovyFile(existingTestClass);

        // Remove existing import statements from the generated import statements.
        return CollectionUtils.removeAll(generatedImportStatements, existingImportStatements, ImportEquator);
    }

    static Collection<PsiImportStatement> determineImportsToAddToJavaClass(
            final PsiJavaFile generatedTestClass, final PsiJavaFile existingTestClass) {
        final List<PsiImportStatement> generatedImportStatements = getImportListForJavaClass(generatedTestClass);
        final List<PsiImportStatement> existingImportStatements = getImportListForJavaClass(existingTestClass);

        // Remove existing import statements from the generated import statements.
        return CollectionUtils.removeAll(generatedImportStatements, existingImportStatements, ImportEquator);
    }

    private static List<PsiImportStatement> getImportListForJavaClass(final PsiJavaFile javaFile) {
        PsiImportList importList = javaFile.getImportList();
        if(importList == null) {
            return Collections.emptyList();
        }
        // Do not simply return Arrays.asList(...); we need to copy the references into a new list, because
        // want to add/remove items from the new list.
        return new ArrayList<>(Arrays.asList(importList.getImportStatements()));

    }

    private static List<GrImportStatement> getImportListForGroovyFile(final GroovyFile groovyFile) {
        final GrImportStatement[] importList = groovyFile.getImportStatements();
        if(importList == null) {
            return Collections.emptyList();
        }
        // Do not simply return Arrays.asList(...); we need to copy the references into a new list, because
        // want to add/remove items from the new list.
        return new ArrayList<>(Arrays.asList(importList));
    }

    public static List<PsiMethod> sortMethodsInDisplayOrder(final List<PsiMethod> methods) {
        // Use map to create List<Pair<MethodSortKey, PsiMethod>>.
        // Sort the List<Pair<MethodSortKey, PsiMethod>> by the MethodSortKey.
        // Use map to take the right item of each pair (the PsiMethod).
        // Return a list containing the result.
        return methods.stream().map(x -> Pair.of(computeSortKey(x), x)).sorted(Comparator.comparing(Pair::getLeft)).map(Pair::getRight).collect(Collectors.toList());
    }

    public static String computeSortKey(final PsiMethod method) {
        // Replace "_" with "<". The "<" character cannot appear in a Java method name. We want "_" to be treated as
        // "<" for sorting purposes, because "<" comes after the numbers (0-9), but before the letters (A-Z and a-z).
        // This way, method overloads will be grouped together with their alt-flows, because they all start with:
        // {methodName}{overloadSuffix}.
        // Also, methods with similar prefixes will be grouped with their alt flows, and not intermixed with one another.
        // For example: without this, you'd have methods in the following order: testGetData(), testGetDataWithName(), testGetData_ThrowsIOException().
        // With the change, you have order: testGetData(), testGetData_ThrowsIOException(), testGetDataWithName().
        return method.getName().replace("_", "<") + "()";
    }
}
