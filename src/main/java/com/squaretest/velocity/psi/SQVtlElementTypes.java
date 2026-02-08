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
package com.squaretest.velocity.psi;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.TokenSet;

public interface SQVtlElementTypes extends TokenType {

    // Directives
    SQVtlTokenType DirectiveIf = new SQVtlCompositeStarterTokenType("#if");
    SQVtlTokenType DirectiveElseIf = new SQVtlCompositeStarterTokenType("#elseif");
    SQVtlTokenType DirectiveElse = new SQVtlCompositeStarterTokenType("#else");
    SQVtlTokenType DirectiveEnd = new SQVtlTokenType("#end");
    SQVtlTokenType DirectiveForEach = new SQVtlCompositeStarterTokenType("#foreach");
    SQVtlTokenType DirectiveBreak = new SQVtlCompositeStarterTokenType("#break");
    SQVtlTokenType DirectiveSet = new SQVtlCompositeStarterTokenType("#set");
    SQVtlTokenType DirectiveInclude = new SQVtlCompositeStarterTokenType("#include");
    SQVtlTokenType DirectiveParse = new SQVtlCompositeStarterTokenType("#parse");
    SQVtlTokenType DirectiveEvaluate = new SQVtlCompositeStarterTokenType("#evaluate");
    SQVtlTokenType DirectiveMacroCall = new SQVtlCompositeStarterTokenType("#macro_call");
    SQVtlTokenType DirectiveMacroDeclaration = new SQVtlCompositeStarterTokenType("#macro_declaration");
    SQVtlTokenType DirectiveStop = new SQVtlTokenType("#stop");
    SQVtlTokenType DirectiveLiteral = new SQVtlCompositeStarterTokenType("#literal");
    SQVtlTokenType DirectiveDefine = new SQVtlCompositeStarterTokenType("#define");
    SQVtlTokenType DirectiveReferenceStart = new SQVtlCompositeStarterTokenType("$");
    SQVtlTokenType DirectiveReferenceStartFormal = new SQVtlCompositeStarterTokenType("${");

    // Comments
    SQVtlTokenType MultilineComment = new SQVtlCommentType("MultilineComment", 2, 2);
    SQVtlTokenType DocsComment = new SQVtlCommentType("DocsComment", 3, 2);
    SQVtlTokenType Comment = new SQVtlCommentType("Comment", 2, 0);

    // Misc. tokens.
    SQVtlTokenType ForEachIn = new SQVtlTokenType("ForEachIn");
    SQVtlTokenType Comma = new SQVtlTokenType(",");
    SQVtlTokenType Colon = new SQVtlTokenType(":");
    SQVtlTokenType Semicolon = new SQVtlTokenType(";");
    SQVtlTokenType JavaDot = new SQVtlTokenType("JavaDot");
    SQVtlTokenType Range = new SQVtlTokenType("Range");
    SQVtlTokenType Bang = new SQVtlOperatorTokenType("!", SQVtlExpressionTypeCalculator.BooleanCalculator);
    SQVtlTokenType Question = new SQVtlTokenType("?");
    SQVtlTokenType Dot = new SQVtlTokenType(".");
    SQVtlTokenType SingleQuote = new SQVtlTokenType("'");
    SQVtlTokenType DoubleQuote = new SQVtlTokenType("\"");

    // Binary operators.
    SQVtlTokenType Or = new SQVtlOperatorTokenType("Or", SQVtlExpressionTypeCalculator.BooleanCalculator);
    SQVtlTokenType And = new SQVtlOperatorTokenType("And", SQVtlExpressionTypeCalculator.BooleanCalculator);
    SQVtlTokenType Equal = new SQVtlOperatorTokenType("Equal", SQVtlExpressionTypeCalculator.BooleanCalculator);
    SQVtlTokenType NotEqual = new SQVtlOperatorTokenType("NotEqual", SQVtlExpressionTypeCalculator.BooleanCalculator);
    SQVtlTokenType LessThan = new SQVtlOperatorTokenType("LessThan", SQVtlExpressionTypeCalculator.BooleanCalculator);
    SQVtlTokenType LessThanOrEqual = new SQVtlOperatorTokenType("LessThanOrEqual", SQVtlExpressionTypeCalculator.BooleanCalculator);
    SQVtlTokenType GreaterThan = new SQVtlOperatorTokenType("GreaterThan", SQVtlExpressionTypeCalculator.BooleanCalculator);
    SQVtlTokenType GreaterThanOrEqual = new SQVtlOperatorTokenType("GreaterThanOrEqual", SQVtlExpressionTypeCalculator.BooleanCalculator);
    SQVtlTokenType Assign = new SQVtlTokenType("=");
    SQVtlTokenType Plus = new SQVtlOperatorTokenType("+", SQVtlExpressionTypeCalculator.PlusCalculator);
    SQVtlTokenType Minus = new SQVtlOperatorTokenType("-", SQVtlExpressionTypeCalculator.MinusCalculator);
    SQVtlTokenType Asterisk = new SQVtlOperatorTokenType("*", SQVtlExpressionTypeCalculator.MultiplicationCalculator);
    SQVtlTokenType Divide = new SQVtlOperatorTokenType("/", SQVtlExpressionTypeCalculator.MultiplicationCalculator);
    SQVtlTokenType Percent = new SQVtlOperatorTokenType("%", SQVtlExpressionTypeCalculator.MultiplicationCalculator);

    // Braces and parens.
    SQVtlTokenType LeftBracket = new SQVtlTokenType("[");
    SQVtlTokenType RightBracket = new SQVtlTokenType("]");
    SQVtlTokenType LeftBrace = new SQVtlTokenType("{");
    SQVtlTokenType RightBrace = new SQVtlTokenType("}");
    SQVtlTokenType LeftParen = new SQVtlTokenType("(");
    SQVtlTokenType RightParen = new SQVtlTokenType(")");
    SQVtlTokenType LeftBraceInExpression = new SQVtlTokenType("{");
    SQVtlTokenType RightBraceInExpression = new SQVtlTokenType("}");

    // Simple types
    SQVtlTokenType Boolean = new SQVtlTokenType("Boolean");
    SQVtlTokenType Integer = new SQVtlTokenType("Integer");
    SQVtlTokenType Double = new SQVtlTokenType("Double");
    SQVtlTokenType Identifier = new SQVtlTokenType("identifier");
    SQVtlTokenType StringText = new SQVtlTokenType("QuotedText");
    SQVtlTokenType EscapeChar = new SQVtlTokenType("EscapeChar");
    SQVtlTokenType TemplateText = new SQVtlTokenType("TemplateText");

    // Token groups.
    TokenSet DirectiveStartTokens = TokenSet.create(DirectiveSet, DirectiveIf, DirectiveForEach, DirectiveElseIf, DirectiveInclude, DirectiveMacroDeclaration, DirectiveParse, DirectiveLiteral, DirectiveEvaluate, DirectiveDefine);
    TokenSet CommentTokens = TokenSet.create(MultilineComment, Comment, DocsComment);
}
