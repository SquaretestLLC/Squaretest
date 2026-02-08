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
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.impl.EditorWindow;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;

public abstract class BaseNewTestHandler implements CodeInsightActionHandler {

    @Nullable
    private EditorWindow editorWindow;

    /**
     * This is a workaround to get access to the EditorWindow from the {@link AnActionEvent}'s DataContext.
     * The {@link CodeInsightActionHandler} does not provide this in {@link #invoke(Project, Editor, PsiFile)}.
     *
     * @param editorWindow the editorWindow from the {@link AnActionEvent}.
     */
    void setEditorWindow(@Nullable final EditorWindow editorWindow) {
        this.editorWindow = editorWindow;
    }

    @Nullable
    EditorWindow getEditorWindow() {
        return editorWindow;
    }

}
