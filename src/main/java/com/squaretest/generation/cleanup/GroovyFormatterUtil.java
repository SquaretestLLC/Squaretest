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

import com.intellij.application.options.CodeStyle;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiParserFacade;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.squaretest.generation.filecreation.GroovyMockitoCallUtils;
import com.squaretest.generation.filecreation.LineBreakInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.groovy.GroovyLanguage;
import org.jetbrains.plugins.groovy.lang.psi.GroovyPsiElement;
import org.jetbrains.plugins.groovy.lang.psi.GroovyRecursiveElementVisitor;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.expressions.path.GrMethodCallExpression;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.squaretest.generation.cleanup.CommonFormatterUtils.addSyncFormatKeyToDocument;
import static com.squaretest.generation.cleanup.CommonFormatterUtils.commitDocument;

public class GroovyFormatterUtil {

    private static final Logger Log = Logger.getInstance(GroovyFormatterUtil.class);

    public static void cleanUpElements(final Project project, final PsiFile containingFile, final PsiElement[] elements) {
        cleanUpElements1(project, containingFile, toGroovyElements(elements));
    }

    public static void cleanUpElements1(final Project project, final PsiFile containingFile, final GroovyPsiElement[] elements) {
        if(elements.length == 0) {
            return;
        }
        reformatElements(project, containingFile, elements);
    }

    private static void reformatElements(final Project project, final PsiFile containingFile, final GroovyPsiElement[] elements) {
        // Commit any pending changes to the document first.
        addSyncFormatKeyToDocument(project, containingFile);
        commitDocument(project, containingFile);
        // Invoke IntelliJ reformatter first.
        final TextRange range = CommonFormatterUtils.getRange(elements);
        invokeIntelliJReformatter(project, containingFile, range);
        // Fix inappropriate line breaks added by the IntelliJ reformatter; e.g. line breaks after "any(" or
        // "thenReturn(". We need to invoke the IntelliJ reformatter after fixing the line breaks, because the new line
        // could be longer than the limit. We then need to fix inappropriate line breaks added by the second call to
        // the IntelliJ reformatter. Repeat this process as needed, up to 4 times.
        for(int i = 0; i < 4; i++) {
            final boolean wasChanged = fixInappropriateLineBreaks(project, containingFile, elements);
            if(!wasChanged) {
                break;
            } else {
                commitDocument(project, containingFile);
                invokeIntelliJReformatter(project, containingFile, range);
            }
        }
    }

    private static boolean fixInappropriateLineBreaks(final Project project, final PsiFile generatedFile, final GroovyPsiElement[] elements) {
        final Document document = PsiDocumentManager.getInstance(project).getDocument(generatedFile);
        if(document == null) {
            return false;
        }
        final CodeStyleSettings settings = CodeStyle.getSettings(generatedFile);
        final int rightMargin = settings.getRightMargin(GroovyLanguage.INSTANCE);
        final List<LineBreakInfo> mockitoThenCallInfos = new ArrayList<>();
        for(final GroovyPsiElement element : elements) {
            element.accept(new GroovyRecursiveElementVisitor() {
                @Override
                public void visitMethodCallExpression(@NotNull final GrMethodCallExpression methodCallExpression) {
                    super.visitMethodCallExpression(methodCallExpression);
                    LineBreakInfo lineBreakInfo = GroovyMockitoCallUtils.tryGetMockitoThenCallInfo(methodCallExpression);
                    if(lineBreakInfo == null) {
                        lineBreakInfo = GroovyMockitoCallUtils.tryGetMockitoMatcherCallInfo(methodCallExpression);
                    }
                    if(lineBreakInfo == null) {
                        lineBreakInfo = GroovyMockitoCallUtils.tryGetAssertJCallInfo(methodCallExpression);
                    }
                    if(lineBreakInfo != null) {
                        mockitoThenCallInfos.add(lineBreakInfo);
                    }
                }
            });
        }
        final PsiParserFacade psiParserFacade = PsiParserFacade.getInstance(project);
        for(final LineBreakInfo mockitoCallInfo : mockitoThenCallInfos) {
            final PsiElement anchorElement = mockitoCallInfo.elementToPutNewlineBefore();
            final PsiElement anchorElementPreviousSibling = anchorElement.getPrevSibling();
            final PsiElement whitespaceElementToRemove = mockitoCallInfo.whitespaceElementToRemove();
            final String whitespaceText = whitespaceElementToRemove.getText();
            whitespaceElementToRemove.delete();
            final PsiElement anchorParent = anchorElement.getParent();
            final PsiElement newWhitespaceElement = psiParserFacade.createWhiteSpaceFromText(whitespaceText);
            if(anchorElementPreviousSibling instanceof LeafPsiElement) {
                if(anchorElementPreviousSibling.getText().contains("\n")) {
                    continue;
                }
            }
            final TextRange textRange = anchorElement.getTextRange();
            final int lineNumber = document.getLineNumber(textRange.getStartOffset());
            final int lineLength = document.getLineEndOffset(lineNumber) - document.getLineStartOffset(lineNumber);
            if(lineLength <= rightMargin) {
                continue;
            }
            anchorParent.addBefore(newWhitespaceElement, anchorElement);
        }
        return !mockitoThenCallInfos.isEmpty();
    }

    private static void invokeIntelliJReformatter(final Project project, final PsiFile generatedFile, final TextRange range) {
        final CodeStyleSettings settings = CodeStyle.getSettings(generatedFile);
        final CommonCodeStyleSettings commonSettings = settings.getCommonSettings(GroovyLanguage.INSTANCE);
        final int rightMargin = settings.getRightMargin(GroovyLanguage.INSTANCE);
        // We need to turn these options on even if the user's settings have them turned off.
        final int oldParamWrapSetting = commonSettings.CALL_PARAMETERS_WRAP;
        final boolean oldKeepLineBreaksSetting = commonSettings.KEEP_LINE_BREAKS;
        try {
            if(rightMargin >= 80) {
                if(commonSettings.CALL_PARAMETERS_WRAP == CommonCodeStyleSettings.DO_NOT_WRAP) {
                    commonSettings.CALL_PARAMETERS_WRAP = CommonCodeStyleSettings.WRAP_AS_NEEDED;
                }
            }
            commonSettings.KEEP_LINE_BREAKS = true;
            invokeFormatter(project, generatedFile, range);
        } finally {
            // Revert the changes we made to the user's settings.
            commonSettings.CALL_PARAMETERS_WRAP = oldParamWrapSetting;
            commonSettings.KEEP_LINE_BREAKS = oldKeepLineBreaksSetting;
        }
    }

    private static void invokeFormatter(final Project project, final PsiFile generatedFile, final TextRange range) {
        Log.debug("Start reformat text: " + LocalDateTime.now());
        CodeStyleManager.getInstance(project).reformatText(generatedFile, range.getStartOffset(), range.getEndOffset());
        Log.debug("Finish reformat text: " + LocalDateTime.now());
    }

    private static GroovyPsiElement[] toGroovyElements(final PsiElement[] elements) {
        final GroovyPsiElement[] groovyPsiElements = new GroovyPsiElement[elements.length];
        for(int i = 0; i < elements.length; i++) {
            groovyPsiElements[i] = (GroovyPsiElement) elements[i];
        }
        return groovyPsiElements;
    }
}
