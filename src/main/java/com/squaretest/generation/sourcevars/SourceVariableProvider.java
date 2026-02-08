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
import com.intellij.psi.PsiCompiledElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiVariable;
import com.squaretest.generation.CompiledUtils;
import org.apache.commons.collections4.SetUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class SourceVariableProvider {
    @NotNull
    private final List<ISourceVariableProvider> sourceVariableProviders;

    public SourceVariableProvider(@NotNull final List<ISourceVariableProvider> sourceVariableProviders) {
        this.sourceVariableProviders = sourceVariableProviders;
    }

    @NotNull
    public Set<PsiVariable> getSourceVariables(
            @NotNull PsiClass startingClass, @NotNull PsiField targetField) {
        startingClass = CompiledUtils.getClassWithSourceCode(startingClass);
        targetField = CompiledUtils.getFieldWithSourceCode(targetField);
        final Set<PsiVariable> ret = SetUtils.newIdentityHashSet();
        for(final ISourceVariableProvider sourceVariableProvider : sourceVariableProviders) {
            ret.addAll(sourceVariableProvider.getSourceVariables(startingClass, targetField));
        }
        return ret;
    }

    @NotNull
    public Set<PsiVariable> getSourceVariables(@NotNull PsiField targetField) {
        targetField = CompiledUtils.getFieldWithSourceCode(targetField);
        final PsiClass startingClass = CompiledUtils.getClassWithSourceCode(targetField.getContainingClass());
        if(startingClass == null) {
            return Collections.emptySet();
        }
        return getSourceVariables(startingClass, targetField);
    }

    @NotNull
    public Set<PsiVariable> getSourceVariables(@NotNull PsiMethod psiMethod, @Nullable PsiParameter param) {
        if(psiMethod instanceof PsiCompiledElement) {
            psiMethod = CompiledUtils.getMethodWithSourceCode(psiMethod);
            if(param != null) {
                param = findParamWithName(psiMethod, param);
            }
        }
        final PsiField targetField = getTargetField(psiMethod, param);
        if(targetField == null) {
            return Collections.emptySet();
        }
        final PsiClass containingClass;
        if(psiMethod.isConstructor()) {
            containingClass = psiMethod.getContainingClass();
        } else {
            containingClass = targetField.getContainingClass();
        }
        if(containingClass == null) {
            return Collections.emptySet();
        }
        final Set<PsiVariable> ret = SetUtils.newIdentityHashSet();
        for(final ISourceVariableProvider sourceVariableProvider : sourceVariableProviders) {
            ret.addAll(sourceVariableProvider.getSourceVariables(containingClass, targetField));
        }
        return ret;
    }

    private PsiParameter findParamWithName(final PsiMethod psiMethod, final PsiParameter param) {
        for(final PsiParameter methodParam : psiMethod.getParameterList().getParameters()) {
            if(methodParam.getName().equals(param.getName())) {
                return methodParam;
            }
        }
        return param;
    }

    @Nullable
    public PsiField getTargetField(@NotNull PsiMethod psiMethod, @Nullable PsiParameter param) {
        if(psiMethod instanceof PsiCompiledElement) {
            psiMethod = CompiledUtils.getMethodWithSourceCode(psiMethod);
            if(param != null) {
                param = findParamWithName(psiMethod, param);
            }
        }
        for(final ISourceVariableProvider sourceVariableProvider : sourceVariableProviders) {
            final PsiField field = sourceVariableProvider.getTargetField(psiMethod, param);
            if(field != null) {
                return field;
            }
        }
        return null;
    }
}
