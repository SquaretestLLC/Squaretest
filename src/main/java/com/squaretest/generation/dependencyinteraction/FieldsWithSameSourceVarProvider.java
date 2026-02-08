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

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiVariable;
import com.squaretest.utils.IdentityMultimapWithIdentitySets;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class FieldsWithSameSourceVarProvider {
    @NotNull
    private final FieldToSourceVariableCollector fieldToSourceVariableCollector;
    @NotNull
    private final PsiClass sourceClass;
    @NotNull
    private final IdentityMultimapWithIdentitySets<PsiField, PsiField> fieldToFieldsWithSameSourceVarMap;
    private boolean isInitialized;

    public FieldsWithSameSourceVarProvider(@NotNull final FieldToSourceVariableCollector fieldToSourceVariableCollector, @NotNull final PsiClass sourceClass) {
        this.fieldToSourceVariableCollector = fieldToSourceVariableCollector;
        this.sourceClass = sourceClass;
        this.fieldToFieldsWithSameSourceVarMap = new IdentityMultimapWithIdentitySets<>();
        this.isInitialized = false;
    }

    public Set<PsiField> getFieldsWithSameSourceVar(final PsiField psiField) {
        initializeIfNeeded();
        if(psiField == null) {
            return Collections.emptySet();
        }
        return fieldToFieldsWithSameSourceVarMap.get(psiField);
    }

    private void initializeIfNeeded() {
        if(!isInitialized) {
            final Map<PsiField, Collection<PsiVariable>> fieldToSourceVars = fieldToSourceVariableCollector.computeSourceVariablesForMembers(sourceClass).asMap();
            for(final Map.Entry<PsiField, Collection<PsiVariable>> entry : fieldToSourceVars.entrySet()) {
                final PsiField field = entry.getKey();
                fieldToFieldsWithSameSourceVarMap.put(field, field);
                for(final PsiVariable sourceVar : entry.getValue()) {
                    if(sourceVar instanceof PsiParameter) {
                        final Set<PsiField> fieldsForVariable = fieldToSourceVariableCollector.getFieldsForVariable(sourceClass, (PsiParameter) sourceVar);
                        fieldToFieldsWithSameSourceVarMap.putAll(field, fieldsForVariable);
                    }
                }
            }
            isInitialized = true;
        }
    }
}
