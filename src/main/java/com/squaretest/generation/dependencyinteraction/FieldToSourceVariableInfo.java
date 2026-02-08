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
package com.squaretest.generation.dependencyinteraction;

import com.intellij.psi.PsiField;
import com.intellij.psi.PsiVariable;
import com.squaretest.utils.IdentityMultimapWithIdentitySets;

public record FieldToSourceVariableInfo(
        IdentityMultimapWithIdentitySets<PsiField, PsiVariable> fieldToSourceVariablesMap,
        IdentityMultimapWithIdentitySets<PsiVariable, PsiField> sourceToFieldMap) {
}
