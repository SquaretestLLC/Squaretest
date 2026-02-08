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
package com.squaretest.template.impl;

import com.squaretest.generation.PsiToTemplateVarsMapper;
import com.squaretest.generation.existingtest.common.TestClassInfo;
import com.squaretest.template.api.Api.SourceClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class TemplateData {

    private final Set<String> importLinesRequiredForDependencies;
    private final SourceClass sourceClassUnderTest;
    private final PsiToTemplateVarsMapper mainSourceClassPsiToTemplateVarsMapper;
    private final TestClassInfo testClassInfo;

    public TemplateData(
            @NotNull final Set<String> importLinesRequiredForDependencies,
            @NotNull final SourceClass sourceClassUnderTest,
            @NotNull final PsiToTemplateVarsMapper mainSourceClassPsiToTemplateVarsMapper,
            @Nullable final TestClassInfo testClassInfo) {
        this.importLinesRequiredForDependencies = importLinesRequiredForDependencies;
        this.sourceClassUnderTest = sourceClassUnderTest;
        this.mainSourceClassPsiToTemplateVarsMapper = mainSourceClassPsiToTemplateVarsMapper;
        this.testClassInfo = testClassInfo;
    }

    public Set<String> getImportLinesRequiredForDependencies() {
        return importLinesRequiredForDependencies;
    }

    public SourceClass getClassUnderTest() {
        return sourceClassUnderTest;
    }

    public PsiToTemplateVarsMapper getMainSourceClassPsiToTemplateVarsMapper() {
        return mainSourceClassPsiToTemplateVarsMapper;
    }

    public TestClassInfo getTestClassInfo() {
        return testClassInfo;
    }
}
