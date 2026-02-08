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

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.squaretest.velocity.lexer.SQVtlLexer;
import com.squaretest.velocity.psi.SQVtlElementTypes;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class SQVtlSyntaxHighlighter extends SyntaxHighlighterBase {

    private static final TextAttributesKey VelocityDot = TextAttributesKey.createTextAttributesKey("VelocityDot", DefaultLanguageHighlighterColors.DOT);
    private static final TextAttributesKey VelocityParens = TextAttributesKey.createTextAttributesKey("VelocityParens", DefaultLanguageHighlighterColors.PARENTHESES);
    private static final TextAttributesKey VelocityBrackets = TextAttributesKey.createTextAttributesKey("VelocityBrackets", DefaultLanguageHighlighterColors.BRACKETS);
    private static final TextAttributesKey VelocityBraces = TextAttributesKey.createTextAttributesKey("VelocityBraces", DefaultLanguageHighlighterColors.BRACES);
    private static final TextAttributesKey VelocityOperationSign = TextAttributesKey.createTextAttributesKey("VelocityOperationSign", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    private static final TextAttributesKey VelocityString = TextAttributesKey.createTextAttributesKey("VelocityString", DefaultLanguageHighlighterColors.STRING);
    private static final TextAttributesKey VelocityEscapeChar = TextAttributesKey.createTextAttributesKey("VelocityEscapeChar", DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE);
    private static final TextAttributesKey VelocityNumber = TextAttributesKey.createTextAttributesKey("VelocityNumber", DefaultLanguageHighlighterColors.NUMBER);
    private static final TextAttributesKey VelocityKeyword = TextAttributesKey.createTextAttributesKey("VelocityKeyword", DefaultLanguageHighlighterColors.KEYWORD);
    private static final TextAttributesKey VelocityComma = TextAttributesKey.createTextAttributesKey("VelocityComma", DefaultLanguageHighlighterColors.COMMA);
    private static final TextAttributesKey VelocitySemicolon = TextAttributesKey.createTextAttributesKey("VelocitySemicolon", DefaultLanguageHighlighterColors.SEMICOLON);
    private static final TextAttributesKey VelocityDirective = TextAttributesKey.createTextAttributesKey("VelocityDirective", DefaultLanguageHighlighterColors.KEYWORD);
    private static final TextAttributesKey VelocityReference = TextAttributesKey.createTextAttributesKey("VelocityReference", DefaultLanguageHighlighterColors.MARKUP_ATTRIBUTE);
    private static final TextAttributesKey VelocityComment = TextAttributesKey.createTextAttributesKey("VelocityComment", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    private static final TextAttributesKey VelocityBadCharacter = TextAttributesKey.createTextAttributesKey("VelocityBadCharacter", HighlighterColors.BAD_CHARACTER);
    private static final TextAttributesKey VelocityDefaultTokenText = TextAttributesKey.createTextAttributesKey("VelocityDefaultTokenText", DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR);

    private static final Map<IElementType, TextAttributesKey> ElementsToTextAttributesMap;

    static {
        ElementsToTextAttributesMap = new HashMap<>();
        SyntaxHighlighterBase.fillMap(ElementsToTextAttributesMap, VelocityDot, SQVtlElementTypes.Dot, SQVtlElementTypes.JavaDot);
        SyntaxHighlighterBase.fillMap(ElementsToTextAttributesMap, VelocityParens, SQVtlElementTypes.LeftParen, SQVtlElementTypes.RightParen);
        SyntaxHighlighterBase.fillMap(ElementsToTextAttributesMap, VelocityBrackets, SQVtlElementTypes.LeftBracket, SQVtlElementTypes.RightBracket);
        SyntaxHighlighterBase.fillMap(ElementsToTextAttributesMap, VelocityBraces, SQVtlElementTypes.LeftBraceInExpression, SQVtlElementTypes.RightBraceInExpression);
        SyntaxHighlighterBase.fillMap(ElementsToTextAttributesMap, VelocityOperationSign, SQVtlElementTypes.Plus, SQVtlElementTypes.Minus, SQVtlElementTypes.Asterisk, SQVtlElementTypes.Divide, SQVtlElementTypes.Percent, SQVtlElementTypes.And, SQVtlElementTypes.Or, SQVtlElementTypes.Bang, SQVtlElementTypes.Question, SQVtlElementTypes.Range);
        SyntaxHighlighterBase.fillMap(ElementsToTextAttributesMap, VelocityString, SQVtlElementTypes.StringText, SQVtlElementTypes.DoubleQuote, SQVtlElementTypes.SingleQuote);
        SyntaxHighlighterBase.fillMap(ElementsToTextAttributesMap, VelocityEscapeChar, SQVtlElementTypes.EscapeChar);
        SyntaxHighlighterBase.fillMap(ElementsToTextAttributesMap, VelocityNumber, SQVtlElementTypes.Integer, SQVtlElementTypes.Double);
        SyntaxHighlighterBase.fillMap(ElementsToTextAttributesMap, VelocityKeyword, SQVtlElementTypes.Boolean, SQVtlElementTypes.ForEachIn);
        SyntaxHighlighterBase.fillMap(ElementsToTextAttributesMap, VelocityComma, SQVtlElementTypes.Comma);
        SyntaxHighlighterBase.fillMap(ElementsToTextAttributesMap, VelocitySemicolon, SQVtlElementTypes.Semicolon);
        SyntaxHighlighterBase.fillMap(ElementsToTextAttributesMap, VelocityDirective, SQVtlElementTypes.DirectiveElse, SQVtlElementTypes.DirectiveEnd, SQVtlElementTypes.DirectiveStop, SQVtlElementTypes.DirectiveBreak, SQVtlElementTypes.DirectiveMacroCall, SQVtlElementTypes.DirectiveReferenceStart, SQVtlElementTypes.DirectiveReferenceStartFormal, SQVtlElementTypes.LeftBrace, SQVtlElementTypes.RightBrace);
        SyntaxHighlighterBase.fillMap(ElementsToTextAttributesMap, VelocityDirective, SQVtlElementTypes.DirectiveStartTokens.getTypes());
        SyntaxHighlighterBase.fillMap(ElementsToTextAttributesMap, VelocityReference, SQVtlElementTypes.Identifier);
        SyntaxHighlighterBase.fillMap(ElementsToTextAttributesMap, VelocityComment, SQVtlElementTypes.CommentTokens.getTypes());
        SyntaxHighlighterBase.fillMap(ElementsToTextAttributesMap, VelocityBadCharacter, TokenType.BAD_CHARACTER);
    }

    @NotNull
    public Lexer getHighlightingLexer() {
        return new SQVtlLexer();
    }

    @NotNull
    public TextAttributesKey[] getTokenHighlights(final IElementType tokenType) {
        return SyntaxHighlighterBase.pack(ElementsToTextAttributesMap.get(tokenType), VelocityDefaultTokenText);
    }
}
