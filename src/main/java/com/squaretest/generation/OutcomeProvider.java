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
package com.squaretest.generation;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiVariable;
import com.squaretest.generation.dependencyinteraction.DependencyFlowInfoProvider;
import com.squaretest.generation.dependencyinteraction.outcomes.DepConfig;
import com.squaretest.generation.dependencyinteraction.outcomes.OutcomeInfo;
import com.squaretest.template.api.Api;
import org.apache.commons.collections4.SetUtils;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OutcomeProvider {
    private final DependencyFlowInfoProvider dependencyFlowInfoProvider;
    private final PsiToTemplateVarsMapper psiToTemplateVarsMapper;
    private final Api.SourceClass sourceClass;

    public OutcomeProvider(
            final DependencyFlowInfoProvider dependencyFlowInfoProvider,
            final PsiToTemplateVarsMapper mainSourceClassPsiToTemplateVarsMapper,
            final Api.SourceClass mainSourceClass) {
        this.dependencyFlowInfoProvider = dependencyFlowInfoProvider;
        this.psiToTemplateVarsMapper = mainSourceClassPsiToTemplateVarsMapper;
        this.sourceClass = mainSourceClass;
    }

    public OutcomeInfo computeOutcomeInfo() {
        return dependencyFlowInfoProvider.computeOutcomeInfo();
    }

    public OutcomeInfo computeOutcomeInfo(final List<? extends Api.Variable> dependencies) {
        final Map<PsiField, PsiElement> fieldToSourceVarMap = new IdentityHashMap<>();
        final Set<PsiField> mockedFields = SetUtils.newIdentityHashSet();
        for(final Api.ClassMember member : sourceClass.getAllFields()) {
            final Api.Variable depThatSatisfiedMember = member.getPossibleSourceVariables().intersection(dependencies).first();
            if(depThatSatisfiedMember != null) {
                final PsiVariable psiSourceVar = psiToTemplateVarsMapper.getPsiVariable(depThatSatisfiedMember);
                final PsiVariable psiFieldVar = psiToTemplateVarsMapper.getPsiVariable(member);
                if(psiSourceVar == null || !(psiFieldVar instanceof final PsiField psiField)) {
                    continue;
                }
                fieldToSourceVarMap.put(psiField, psiSourceVar);
                if(depThatSatisfiedMember.getShouldBeMocked()) {
                    mockedFields.add(psiField);
                }
            }
        }
        final DepConfig depConfig = new DepConfig(fieldToSourceVarMap, mockedFields);
        return dependencyFlowInfoProvider.computeOutcomeInfo(depConfig);
    }
}
