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
import com.intellij.psi.PsiVariable;
import com.squaretest.generation.InitStrategy;
import com.squaretest.generation.PsiToTemplateVarsMapper;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.ClassMemberImpl;
import com.squaretest.utils.IdentityMultimapWithIdentitySets;

import java.util.Map;

public class FieldToSourceVariablePopulator {

    private final PsiToTemplateVarsMapper psiToTemplateVarsMapper;
    private final FieldToSourceVariableCollector fieldToSourceVariableCollector;
    private final PsiClass topLevelClass;

    public FieldToSourceVariablePopulator(
            final PsiToTemplateVarsMapper psiToTemplateVarsMapper,
            final FieldToSourceVariableCollector fieldToSourceVariableCollector, final PsiClass topLevelClass) {
        this.psiToTemplateVarsMapper = psiToTemplateVarsMapper;
        this.fieldToSourceVariableCollector = fieldToSourceVariableCollector;
        this.topLevelClass = topLevelClass;
    }

    public void populateDependencies() {
        final IdentityMultimapWithIdentitySets<PsiField, PsiVariable> fieldToSourceVarsMap = fieldToSourceVariableCollector.computeSourceVariablesForMembers(topLevelClass);
        for(final Map.Entry<PsiField, PsiVariable> entry : fieldToSourceVarsMap.entries()) {
            final ClassMemberImpl templateClassMember = (ClassMemberImpl) psiToTemplateVarsMapper.getVariable(entry.getKey(), InitStrategy.Default);
            final Api.Variable templateVariable = psiToTemplateVarsMapper.getVariable(entry.getValue(), InitStrategy.Default);
            if(templateClassMember != null && templateVariable != null) {
                // These should never be null. Its always good to check though.
                templateClassMember.addPossibleSourceVariable(templateVariable);
            }
        }
    }
}
