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
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.JavaRecursiveElementWalkingVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiParserFacade;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.squaretest.generation.filecreation.JavaMockitoCallUtils;
import com.squaretest.generation.filecreation.LineBreakInfo;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.squaretest.generation.cleanup.CommonFormatterUtils.addSyncFormatKeyToDocument;
import static com.squaretest.generation.cleanup.CommonFormatterUtils.commitDocument;

public class JavaFormatterUtil {
    private static final Logger Log = Logger.getInstance(JavaFormatterUtil.class);

    public static void cleanUpElements(final Project project, final PsiFile containingFile, final PsiElement[] elements) {
        if(elements.length == 0) {
            return;
        }
        reformatElements(project, containingFile, elements);
    }

    private static void reformatElements(final Project project, final PsiFile containingFile, final PsiElement[] elements) {
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
            final boolean wasChanged = fixInappropriateLineBreaks(project, elements);
            if(!wasChanged) {
                break;
            } else {
                commitDocument(project, containingFile);
                invokeIntelliJReformatter(project, containingFile, range);
            }
        }
    }

    private static boolean fixInappropriateLineBreaks(final Project project, final PsiElement[] elements) {
        final List<LineBreakInfo> mockitoThenCallInfos = new ArrayList<>();
        for(final PsiElement element : elements) {
            element.accept(new JavaRecursiveElementWalkingVisitor() {
                @Override
                public void visitMethodCallExpression(@NotNull final PsiMethodCallExpression methodCallExpression) {
                    super.visitMethodCallExpression(methodCallExpression);
                    LineBreakInfo lineBreakInfo = JavaMockitoCallUtils.tryGetMockitoThenCallInfo(methodCallExpression);
                    if(lineBreakInfo == null) {
                        lineBreakInfo = JavaMockitoCallUtils.tryGetMockitoMatcherCallInfo(methodCallExpression);
                    }
                    if(lineBreakInfo == null) {
                        lineBreakInfo = JavaMockitoCallUtils.tryGetAssertJCallInfo(methodCallExpression);
                    }
                    if(lineBreakInfo != null) {
                        mockitoThenCallInfos.add(lineBreakInfo);
                    }
                }
            });
        }
        final PsiParserFacade psiParserFacade = PsiParserFacade.getInstance(project);
        for(final LineBreakInfo mockitoCallInfo : mockitoThenCallInfos) {
            final PsiElement whitespaceElementToRemove = mockitoCallInfo.whitespaceElementToRemove();
            final String whitespaceText = whitespaceElementToRemove.getText();
            whitespaceElementToRemove.delete();
            final PsiElement anchorElement = mockitoCallInfo.elementToPutNewlineBefore();
            final PsiElement anchorParent = anchorElement.getParent();
            final PsiElement newWhitespaceElement = psiParserFacade.createWhiteSpaceFromText(whitespaceText);
            anchorParent.addBefore(newWhitespaceElement, anchorElement);
        }
        return !mockitoThenCallInfos.isEmpty();
    }

    private static void invokeIntelliJReformatter(final Project project, final PsiFile generatedFile, final TextRange range) {
        final CodeStyleSettings settings = CodeStyle.getSettings(generatedFile);
        final CommonCodeStyleSettings commonSettings = settings.getCommonSettings(JavaLanguage.INSTANCE);
        final int rightMargin = settings.getRightMargin(JavaLanguage.INSTANCE);
        // We need to turn these options on even if the user's settings have them turned off.
        final int oldParamWrapSetting = commonSettings.CALL_PARAMETERS_WRAP;
        final boolean oldKeepLineBreaksSetting = commonSettings.KEEP_LINE_BREAKS;
        final boolean oldKeepSimpleClassesInOneLineSetting = commonSettings.KEEP_SIMPLE_CLASSES_IN_ONE_LINE;
        try {
            if(rightMargin >= 80) {
                if(commonSettings.CALL_PARAMETERS_WRAP == CommonCodeStyleSettings.DO_NOT_WRAP) {
                    commonSettings.CALL_PARAMETERS_WRAP = CommonCodeStyleSettings.WRAP_AS_NEEDED;
                }
            }
            commonSettings.KEEP_LINE_BREAKS = true;
            commonSettings.KEEP_SIMPLE_CLASSES_IN_ONE_LINE = true;
            invokeFormatter(project, generatedFile, range);
        } finally {
            // Revert the changes we made to the user's settings.
            commonSettings.CALL_PARAMETERS_WRAP = oldParamWrapSetting;
            commonSettings.KEEP_LINE_BREAKS = oldKeepLineBreaksSetting;
            commonSettings.KEEP_SIMPLE_CLASSES_IN_ONE_LINE = oldKeepSimpleClassesInOneLineSetting;
        }
    }

    private static void invokeFormatter(final Project project, final PsiFile generatedFile, final TextRange range) {
        Log.debug("Start reformat text: " + LocalDateTime.now());
        CodeStyleManager.getInstance(project).reformatText(generatedFile, range.getStartOffset(), range.getEndOffset());
        Log.debug("Finish reformat text: " + LocalDateTime.now());
    }
}
