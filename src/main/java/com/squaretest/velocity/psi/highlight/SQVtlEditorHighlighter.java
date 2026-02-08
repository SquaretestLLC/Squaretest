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
package com.squaretest.velocity.psi.highlight;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.ex.util.LayerDescriptor;
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.PlainTextFileType;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.plugins.groovy.GroovyFileType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.squaretest.velocity.psi.SQVtlElementTypes.TemplateText;

public class SQVtlEditorHighlighter extends LayeredLexerEditorHighlighter {

    public SQVtlEditorHighlighter(@Nullable final Project project,
                                  @Nullable final VirtualFile virtualFile,
                                  @NotNull final EditorColorsScheme colors) {
        super(new SQVtlSyntaxHighlighter(), colors);
        final SyntaxHighlighter templateDataLanguageHighlighter = getHighlighterForTemplateDataLanguage(project, virtualFile);
        registerLayer(TemplateText, new LayerDescriptor(new SyntaxHighlighter() {
            @NotNull
            public Lexer getHighlightingLexer() {
                return templateDataLanguageHighlighter.getHighlightingLexer();
            }

            @NotNull
            public TextAttributesKey[] getTokenHighlights(final IElementType tokenType) {
                if(tokenType == BAD_CHARACTER) {
                    return new TextAttributesKey[0];
                }
                return templateDataLanguageHighlighter.getTokenHighlights(tokenType);
            }
        }, ""));
    }

    private static SyntaxHighlighter getHighlighterForTemplateDataLanguage(final Project project, final VirtualFile virtualFile) {
        if(virtualFile == null || project == null) {
            return null;
        }
        final FileType fileType = getFileType(virtualFile);
        return SyntaxHighlighterFactory.getSyntaxHighlighter(fileType, project, virtualFile);
    }

    private static FileType getFileType(final VirtualFile virtualFile) {
        final String fileName = virtualFile.getName();
        FileType fileType;
        if(fileName.contains(".java")) {
            fileType = JavaFileType.INSTANCE;
        } else if(fileName.contains(".groovy")) {
            fileType = GroovyFileType.GROOVY_FILE_TYPE;
        } else {
            fileType = PlainTextFileType.INSTANCE;
        }
        return fileType;
    }
}

