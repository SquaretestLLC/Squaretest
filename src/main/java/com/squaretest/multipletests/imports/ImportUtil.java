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
package com.squaretest.multipletests.imports;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiImportList;
import com.intellij.psi.PsiImportStatement;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Equator;
import org.jetbrains.plugins.groovy.editor.GroovyImportOptimizer;
import org.jetbrains.plugins.groovy.lang.psi.GroovyFile;
import org.jetbrains.plugins.groovy.lang.psi.api.toplevel.imports.GrImportStatement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ImportUtil {

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

    public static void addRequiredImports(final PsiFile generatedFile, final PsiFile existingFile) {
        if(generatedFile instanceof GroovyFile) {
            addImportsGroovy(generatedFile, existingFile);
        } else {
            addImportsJava(generatedFile, existingFile);
        }
    }

    public static void optimizeImports(final PsiFile theFile) {
        if(theFile instanceof GroovyFile) {
            optimizeImportsGroovy(theFile);
        } else {
            optimizeImportsJava(theFile);
        }
    }

    private static void optimizeImportsGroovy(final PsiFile theFile) {
        final GroovyImportOptimizer groovyImportOptimizer = new GroovyImportOptimizer();
        groovyImportOptimizer.processFile(theFile).run();
    }

    private static void optimizeImportsJava(final PsiFile theFile) {
        JavaCodeStyleManager.getInstance(theFile.getProject()).optimizeImports(theFile);
    }

    private static void addImportsJava(final PsiFile generatedFile, final PsiFile existingFile) {
        if(!(generatedFile instanceof PsiJavaFile && existingFile instanceof final PsiJavaFile existingJavaFile)) {
            return;
        }
        final Collection<PsiImportStatement> importsToAdd = determineImportsToAddToJavaClass((PsiJavaFile) generatedFile, existingJavaFile);

        // Add imports required by the generated method if they're not already included.
        final PsiImportList importList = existingJavaFile.getImportList();

        // Its unclear when the importList will actually be null.
        // The importList does not seem to be null, even when the file contains no imports.
        // Code in IntelliJ IDEA seems to ignore this case (just pretend it can't add any imports to the file).
        // I will do the same. This should be updated if there are cases where Squaretest does not add imports as expected.
        if(importList != null) {
            for(final PsiImportStatement importLineRequired : importsToAdd) {
                importList.add(importLineRequired);
            }
        }
    }

    private static void addImportsGroovy(final PsiFile generatedFile, final PsiFile existingFile) {
        final Collection<GrImportStatement> importsToAdd = determineImportsToAddToGroovyClass((GroovyFile) generatedFile, (GroovyFile) existingFile);
        // Add imports required by the generated method if they're not already included.
        final GroovyFile groovyFile = (GroovyFile) existingFile;
        GrImportStatement[] importList = groovyFile.getImportStatements();

        // Its unclear when the importList will actually be null.
        // The importList does not seem to be null, even when the file contains no imports.
        // Code in IntelliJ IDEA seems to ignore this case (just pretend it can't add any imports to the file).
        // I will do the same. This should be updated if there are cases where Squaretest does not add imports as expected.
        if(importList != null) {
            for(final GrImportStatement importLineRequired : importsToAdd) {
                groovyFile.addImport(importLineRequired);
            }
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
}
