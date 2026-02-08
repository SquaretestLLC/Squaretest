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
package com.squaretest.velocity.lexer;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;

import static com.squaretest.velocity.psi.SQVtlElementTypes.MultilineComment;
import static com.squaretest.velocity.psi.SQVtlElementTypes.TemplateText;

/**
 * Adapted from <a href="https://github.com/consulo/consulo-apache-velocity">consulo-apache-velocity</a>.
 */
public class SQVtlLexer extends MergingLexerAdapter {
    private static final TokenSet TokensToMerge = TokenSet.create(MultilineComment, TemplateText);

    public SQVtlLexer() {
        super(new FlexAdapter(new _SQVtlLexer()), TokensToMerge);
    }
}
