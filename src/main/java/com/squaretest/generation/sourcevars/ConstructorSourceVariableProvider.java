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
package com.squaretest.generation.sourcevars;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiVariable;
import com.squaretest.generation.dependencyinteraction.FieldToSourceVariableCollector;
import com.squaretest.utils.IdentityMultimapWithIdentitySets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class ConstructorSourceVariableProvider implements ISourceVariableProvider {

    @NotNull
    private final FieldToSourceVariableCollector fieldToSourceVariableCollector;

    public ConstructorSourceVariableProvider(@NotNull final FieldToSourceVariableCollector fieldToSourceVariableCollector) {
        this.fieldToSourceVariableCollector = fieldToSourceVariableCollector;
    }

    @Override
    @Nullable
    public PsiField getTargetField(@NotNull final PsiMethod psiMethod, @Nullable final PsiParameter param) {
        if(param == null) {
            return null;
        }
        if(!psiMethod.isConstructor()) {
            return null;
        }
        final PsiClass containingClass = psiMethod.getContainingClass();
        if(containingClass == null) {
            return null;
        }
        final Set<PsiField> fields = fieldToSourceVariableCollector.getFieldsForVariable(containingClass, param);
        if(fields.isEmpty()) {
            return null;
        }
        if(fields.size() == 1) {
            return fields.iterator().next();
        }
        for(final PsiField field : fields) {
            if(field.getContainingClass() == containingClass) {
                return field;
            }
        }
        return fields.iterator().next();
    }

    @Override
    @NotNull
    public Set<PsiVariable> getSourceVariables(@NotNull final PsiClass startingClass, @NotNull final PsiField targetField) {
        final IdentityMultimapWithIdentitySets<PsiField, PsiVariable> fieldToSourceMap = fieldToSourceVariableCollector.computeSourceVariablesForMembers(startingClass);
        return fieldToSourceMap.get(targetField);
    }
}
