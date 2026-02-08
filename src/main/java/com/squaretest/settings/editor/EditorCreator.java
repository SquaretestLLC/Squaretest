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

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.EditorSettings;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.squaretest.settings.store.TemplateLanguage;
import org.jetbrains.annotations.NotNull;

public class EditorCreator {

    public static final String SQTempFileNameWithoutExtension = "__SquaretestTempFile";
    public static final String SQTempFileNameFormat = SQTempFileNameWithoutExtension + ".%s.ft";

    @NotNull
    private final Project project;
    @NotNull
    private final VelocityEditorHighlighterProvider velocityEditorHighlighterProvider;
    @NotNull
    private final PlatformInfoProvider platformInfoProvider;

    public EditorCreator(
            @NotNull final Project project, @NotNull final VelocityEditorHighlighterProvider velocityEditorHighlighterProvider,
            @NotNull final PlatformInfoProvider platformInfoProvider) {
        this.project = project;
        this.velocityEditorHighlighterProvider = velocityEditorHighlighterProvider;
        this.platformInfoProvider = platformInfoProvider;
    }

    public EditorEx createEditor(
            @NotNull final TemplateLanguage selectedLang,
            @NotNull final String templateText) {

        Document doc;
        if(platformInfoProvider.hasBuiltInVelocityEditor()) {
            doc = createDocFromTempFile(selectedLang, templateText);
        } else {
            doc = createEmptyDoc(templateText);
        }

        EditorFactory editorFactory = EditorFactory.getInstance();
        final EditorEx editor = (EditorEx) editorFactory.createEditor(doc, this.project);
        final EditorSettings editorSettings = editor.getSettings();

        editorSettings.setLineMarkerAreaShown(false);
        editor.setHighlighter(
                velocityEditorHighlighterProvider
                        .provideEditorHighlighter(selectedLang));
        return editor;
    }

    private Document createEmptyDoc(@NotNull final String templateText) {
        EditorFactory editorFactory = EditorFactory.getInstance();
        return editorFactory.createDocument(templateText);
    }

    private Document createDocFromTempFile(
            @NotNull final TemplateLanguage selectedLang,
            @NotNull final String templateText) {
        final FileType velocityType = FileTypeManager.getInstance().getFileTypeByExtension("ft");
        final PsiFile file = PsiFileFactory.getInstance(this.project).createFileFromText(
                String.format(SQTempFileNameFormat, selectedLang.getFileExtension()), velocityType, templateText,
                0, true);
        return PsiDocumentManager.getInstance(this.project).getDocument(file);
    }

}
