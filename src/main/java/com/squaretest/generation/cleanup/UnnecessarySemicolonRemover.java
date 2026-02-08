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
package com.squaretest.generation.cleanup;

import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.codeInspection.QuickFix;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaRecursiveElementWalkingVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.groovy.codeInspection.style.GrUnnecessarySemicolonInspection;

import java.util.List;

import static com.squaretest.generation.cleanup.CommonFormatterUtils.commitDocument;

public class UnnecessarySemicolonRemover {

    public void removeRedundantSemicolonsFromFile(final PsiFile psiFile) {
        final Project project = psiFile.getProject();
        InspectionManager inspectionManager = InspectionManager.getInstance(project);
        final GrUnnecessarySemicolonInspection localInspection = new GrUnnecessarySemicolonInspection();
        final ProblemsHolder holder = new ProblemsHolder(inspectionManager, psiFile, false);
        final PsiElementVisitor visitor = localInspection.buildVisitor(holder, false);
        psiFile.accept(new JavaRecursiveElementWalkingVisitor() {
            @Override
            public void visitElement(@NotNull final PsiElement element) {
                super.visitElement(element);
                visitor.visitElement(element);
            }
        });
        final List<ProblemDescriptor> problemDescriptors = holder.getResults();
        fixAll(project, psiFile, problemDescriptors);
    }

    public void removeRedundantSemicolonsFromMethod(final PsiMethod psiMethod) {
        if(!psiMethod.isPhysical()) {
            return;
        }
        final PsiFile psiFile = psiMethod.getContainingFile();
        if(psiFile == null) {
            return;
        }
        final Project project = psiMethod.getProject();
        InspectionManager inspectionManager = InspectionManager.getInstance(project);
        final GrUnnecessarySemicolonInspection localInspection = new GrUnnecessarySemicolonInspection();
        final ProblemsHolder holder = new ProblemsHolder(inspectionManager, psiMethod.getContainingFile(), false);
        final PsiElementVisitor visitor = localInspection.buildVisitor(holder, false);
        psiMethod.accept(new JavaRecursiveElementWalkingVisitor() {
            @Override
            public void visitElement(@NotNull final PsiElement element) {
                super.visitElement(element);
                visitor.visitElement(element);
            }
        });
        final List<ProblemDescriptor> problemDescriptors = holder.getResults();
        fixAll(project, psiFile, problemDescriptors);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void fixAll(final Project project, final PsiFile psiFile, final List<ProblemDescriptor> problems) {
        if(problems == null || problems.isEmpty()) {
            return;
        }
        commitDocument(project, psiFile);
        for(final ProblemDescriptor problem : problems) {
            if(problem.getFixes() != null) {
                for(final QuickFix fix : problem.getFixes()) {
                    fix.applyFix(project, problem);
                }
            }
        }
    }
}
