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
import com.intellij.psi.PsiRecordComponent;
import com.intellij.psi.PsiVariable;
import com.squaretest.generation.dataflow.DataflowUtils;
import com.squaretest.generation.defaulttypes.beans.BeanInfoProvider;
import com.squaretest.utils.IdentityMultimapWithIdentitySets;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

public class FieldToSourceVariableCollector {

    private final BeanInfoProvider beanInfoProvider;
    private final Map<PsiClass, FieldToSourceVariableInfo> cachedValues;

    public FieldToSourceVariableCollector(final BeanInfoProvider beanInfoProvider) {
        this.beanInfoProvider = beanInfoProvider;
        this.cachedValues = new IdentityHashMap<>();
    }

    public IdentityMultimapWithIdentitySets<PsiField, PsiVariable> computeSourceVariablesForMembers(@NotNull final PsiClass psiClass) {
        return computeFieldToSourceVariableInfo(psiClass).fieldToSourceVariablesMap();
    }

    public Set<PsiField> getFieldsForVariable(final PsiClass containingClass, @NotNull final PsiParameter methodParam) {
        if(containingClass == null) {
            // If the containing class is null bail out.
            // Note: we need to take the containingClass as a parameter, because if methodParam is a param in a
            // lombok constructor, calling methodParam.getParent() returns null; there is no way to get to the
            // containingClass from the param.
            return Collections.emptySet();
        }
        if(containingClass.isRecord()) {
            final PsiRecordComponent recordComponent = DataflowUtils.getComponentForCanonicalConstructorParameter(methodParam);
            if(recordComponent != null) {
                final String recordComponentName = recordComponent.getName();
                final PsiField targetField = containingClass.findFieldByName(recordComponentName, false);
                if(targetField == null) {
                    return Collections.emptySet();
                }
                return Collections.singleton(targetField);
            }
            return Collections.emptySet();
        }
        return computeFieldToSourceVariableInfo(containingClass).sourceToFieldMap().get(methodParam);
    }

    @NotNull
    private FieldToSourceVariableInfo computeFieldToSourceVariableInfo(@NotNull final PsiClass psiClass) {
        return cachedValues.computeIfAbsent(psiClass, x -> {
            final IdentityMultimapWithIdentitySets<PsiField, PsiVariable> fieldToSourceVariables = new FieldToSourceVariableCollectorImpl(beanInfoProvider, x).computeSourceVariablesForMembers();
            final IdentityMultimapWithIdentitySets<PsiVariable, PsiField> sourceToFieldMap = reverse(fieldToSourceVariables);
            return new FieldToSourceVariableInfo(fieldToSourceVariables, sourceToFieldMap);
        });
    }

    private static IdentityMultimapWithIdentitySets<PsiVariable, PsiField> reverse(final IdentityMultimapWithIdentitySets<PsiField, PsiVariable> fieldToSourceVariables) {
        final IdentityMultimapWithIdentitySets<PsiVariable, PsiField> ret = new IdentityMultimapWithIdentitySets<>();
        for(final Map.Entry<PsiField, PsiVariable> entry : fieldToSourceVariables.entries()) {
            ret.put(entry.getValue(), entry.getKey());
        }
        return ret;
    }
}
