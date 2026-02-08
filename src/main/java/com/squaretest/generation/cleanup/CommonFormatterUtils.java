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

import com.intellij.formatting.service.AsyncDocumentFormattingService;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

public class CommonFormatterUtils {

    public static void addSyncFormatKeyToDocument(final Project project, final PsiFile psiFile) {
        final PsiDocumentManager documentManager = PsiDocumentManager.getInstance(project);
        final Document document = documentManager.getDocument(psiFile);
        if(document == null) {
            return;
        }
        document.putUserData(AsyncDocumentFormattingService.FORMAT_DOCUMENT_SYNCHRONOUSLY, Boolean.TRUE);
    }

    public static void commitDocument(final Project project, final PsiFile psiFile) {
        final PsiDocumentManager documentManager = PsiDocumentManager.getInstance(project);
        final Document document = documentManager.getDocument(psiFile);
        if(document == null) {
            return;
        }
        documentManager.doPostponedOperationsAndUnblockDocument(document);
    }

    static TextRange getRange(final PsiElement[] elements) {
        int minOffset = elements[0].getTextRange().getStartOffset();
        int maxOffset = elements[0].getTextRange().getEndOffset();
        for(final PsiElement element : elements) {
            final TextRange textRange = element.getTextRange();
            final int startOffset = textRange.getStartOffset();
            final int endOffset = textRange.getEndOffset();
            if(startOffset < minOffset) {
                minOffset = startOffset;
            }
            if(endOffset > maxOffset) {
                maxOffset = endOffset;
            }
        }
        return new TextRange(minOffset, maxOffset);
    }
}
