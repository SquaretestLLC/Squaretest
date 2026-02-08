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
package com.squaretest;

import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.TestFrameworks;
import com.intellij.codeInsight.generation.actions.BaseGenerateAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.impl.EditorWindow;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.JavaProjectRootsUtil;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiCompiledElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.util.PsiUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class NewTestGeneratorBaseAction extends BaseGenerateAction {
    @Override
    protected void update(@NotNull final Presentation presentation, @NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile file) {
        super.update(presentation, project, editor, file);
    }

    public NewTestGeneratorBaseAction(final CodeInsightActionHandler handler) {
        super(handler);
    }

    @Override
    public void actionPerformed(final AnActionEvent e) {
        // This is a hack to inject the EditorWindow into the handler. The invoke method called by the
        // BaseGenerateAction does not include the EditorWindow or a way to access it. The alternative to using a setter
        // to pass the dependency is to reimplement BaseGenerateAction and or CodeInsightAction to pass the EditorWindow
        // to the handler. That would require a lot of work and also require maintenance.
        final BaseNewTestHandler handler = (BaseNewTestHandler) getHandler();
        handler.setEditorWindow(e.getDataContext().getData(EditorWindow.DATA_KEY));
        // Call super.actionPerformed() which will validate that the action is valid for this file and call the handler
        // if needed.
        super.actionPerformed(e);
    }

    /**
     * Override this, because we only care about the top-level class in the file, not the target class determined
     * by {@link #getTargetClass(Editor, PsiFile)}. Also, {@link #getTargetClass(Editor, PsiFile)} will return null
     * if you invoke the action from outside the class in the file; e.g. after the closing brace at the end of the
     * file or from the javadocs on top of the class.
     *
     * @param project the project
     * @param editor  the editor
     * @param file    the file
     * @return boolean.
     */
    @Override
    protected boolean isValidForFile(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile file) {
        if(!(file instanceof final PsiJavaFile psiJavaFile)) {
            return false;
        }
        if(file instanceof PsiCompiledElement) {
            return false;
        }
        if(file.getContainingDirectory() == null || JavaProjectRootsUtil.isOutsideJavaSourceRoot(file)) {
            return false;
        }
        final PsiClass[] psiClasses = psiJavaFile.getClasses();
        if(psiClasses.length == 0) {
            return false;
        }
        final PsiClass psiClass = psiClasses[0];
        return isValidForClass(psiClass);
    }

    @Override
    protected boolean isValidForClass(final PsiClass targetClass) {
        // Check to see if we're in a test class. Use a similar approach as com.intellij.testIntegration.createTest.CreateTestAction.isAvailableForElement(..)
        // to ensure we do not introduce unnecessary lag when this check is performed.
        if(TestFrameworks.detectFramework(targetClass) != null) {
            return false;
        }

        if(!targetClass.isInterface()) {
            return true;
        }

        return isValidForInterface(targetClass);
    }

    private boolean isValidForInterface(final PsiClass targetClass) {
        final String qualifiedName = targetClass.getQualifiedName();
        if(qualifiedName == null) {
            return false;
        }
        return Arrays.stream(targetClass.getMethods()).anyMatch(
                x -> x.hasModifierProperty(PsiModifier.STATIC)
                        && !x.hasModifierProperty(PsiModifier.PRIVATE)
                        && hasSameReturnTypeAsClass(qualifiedName, x));
    }

    private boolean hasSameReturnTypeAsClass(final String targetClassQualifiedName, final PsiMethod psiMethod) {
        final PsiClass returnClass = PsiUtil.resolveClassInType(psiMethod.getReturnType());
        if(returnClass == null) {
            return false;
        }
        return Objects.equals(targetClassQualifiedName, returnClass.getQualifiedName());
    }
}
