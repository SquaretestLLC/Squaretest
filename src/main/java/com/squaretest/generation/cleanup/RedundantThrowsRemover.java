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
import com.intellij.codeInspection.unneededThrows.RedundantThrowsDeclarationLocalInspection;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;

import java.util.List;

import static com.squaretest.generation.cleanup.CommonFormatterUtils.commitDocument;

public class RedundantThrowsRemover {

    public void removeRedundantThrowsFromClass(final PsiClass psiClass) {
        final Project project = psiClass.getProject();
        final PsiFile containingFile = psiClass.getContainingFile();
        if(containingFile == null) {
            return;
        }
        InspectionManager inspectionManager = InspectionManager.getInstance(project);
        final RedundantThrowsDeclarationLocalInspection localInspection = new RedundantThrowsDeclarationLocalInspection();
        ProblemsHolder problemsHolder = new ProblemsHolder(inspectionManager, containingFile, false);
        final PsiElementVisitor visitor = localInspection.buildVisitor(problemsHolder, false);
        if(!(visitor instanceof final JavaElementVisitor javaElementVisitor)) {
            return;
        }

        for(final PsiMethod method : psiClass.getMethods()) {
            if(!method.isPhysical()) {
                continue;
            }
            javaElementVisitor.visitMethod(method);
        }
        fixAll(project, containingFile, problemsHolder.getResults());
    }

    public void removeRedundantThrowsFromMethod(final PsiMethod psiMethod) {
        if(!psiMethod.isPhysical()) {
            return;
        }
        final PsiFile containingFile = psiMethod.getContainingFile();
        if(containingFile == null) {
            return;
        }
        final Project project = psiMethod.getProject();
        InspectionManager inspectionManager = InspectionManager.getInstance(project);
        final RedundantThrowsDeclarationLocalInspection localInspection = new RedundantThrowsDeclarationLocalInspection();
        ProblemsHolder problemsHolder = new ProblemsHolder(inspectionManager, containingFile, false);
        final PsiElementVisitor visitor = localInspection.buildVisitor(problemsHolder, false);
        if(!(visitor instanceof final JavaElementVisitor javaElementVisitor)) {
            return;
        }
        javaElementVisitor.visitMethod(psiMethod);
        fixAll(project, containingFile, problemsHolder.getResults());
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void fixAll(final Project project, final PsiFile psiFile, final List<ProblemDescriptor> problems) {
        if(problems == null || problems.isEmpty()) {
            return;
        }
        for(final ProblemDescriptor problem : problems) {
            if(problem.getFixes() != null) {
                for(final QuickFix fix : problem.getFixes()) {
                    commitDocument(project, psiFile);
                    fix.applyFix(project, problem);
                }
            }
        }
    }
}
