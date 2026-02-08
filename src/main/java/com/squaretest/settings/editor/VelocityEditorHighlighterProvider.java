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
package com.squaretest.settings.editor;

import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.editor.highlighter.EditorHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.testFramework.LightVirtualFile;
import com.squaretest.settings.store.TemplateLanguage;
import com.squaretest.velocity.psi.highlight.SQVtlEditorHighlighter;
import org.jetbrains.annotations.NotNull;

/**
 * Provides {@link EditorHighlighter}s for components to use for velocity editors in the settings menu and other UI.
 */
public class VelocityEditorHighlighterProvider {
    @NotNull
    private final Project project;
    @NotNull
    private final PlatformInfoProvider platformInfoProvider;

    public VelocityEditorHighlighterProvider(
            @NotNull final Project project,
            @NotNull final PlatformInfoProvider platformInfoProvider) {
        this.project = project;
        this.platformInfoProvider = platformInfoProvider;
    }

    public EditorHighlighter provideEditorHighlighter(@NotNull final TemplateLanguage templateLanguage) {
        // If this is the IntelliJ Ultimate Edition, and we have a built-in velocity editor highlighter, return that.
        if(hasBuiltInVelocityEditor()) {
            return EditorHighlighterFactory.getInstance().createEditorHighlighter(
                    project, new LightVirtualFile(String.format("aaa.%s.ft", templateLanguage.getFileExtension())));
        }
        return new SQVtlEditorHighlighter(project,
                new LightVirtualFile(String.format("aaa.%s.ft", templateLanguage.getFileExtension())),
                EditorColorsManager.getInstance().getGlobalScheme());
    }

    private boolean hasBuiltInVelocityEditor() {
        return platformInfoProvider.hasBuiltInVelocityEditor();
    }
}
