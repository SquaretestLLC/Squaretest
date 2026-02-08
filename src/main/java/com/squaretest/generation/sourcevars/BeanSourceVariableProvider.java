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
import com.squaretest.generation.defaulttypes.beans.BeanInfoProvider;
import org.apache.commons.collections4.SetUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Set;

public class BeanSourceVariableProvider implements ISourceVariableProvider {
    @NotNull
    private final BeanInfoProvider beanInfoProvider;

    public BeanSourceVariableProvider(@NotNull final BeanInfoProvider beanInfoProvider) {
        this.beanInfoProvider = beanInfoProvider;
    }

    @Override
    @Nullable
    public PsiField getTargetField(@NotNull final PsiMethod psiMethod, @Nullable final PsiParameter param) {
        if(psiMethod.isConstructor()) {
            return null;
        }
        PsiField field = beanInfoProvider.getFieldForGetter(psiMethod);
        if(field != null) {
            return field;
        }
        field = beanInfoProvider.getFieldForSetter(psiMethod);
        if(field != null) {
            return field;
        }
        return beanInfoProvider.getFieldForWithMethod(psiMethod);
    }

    @Override
    @NotNull
    public Set<PsiVariable> getSourceVariables(@NotNull final PsiClass startingClass, @NotNull final PsiField targetField) {
        final Set<PsiMethod> setterMethods = beanInfoProvider.getSettersForField(targetField);
        final Set<PsiMethod> withMethods = beanInfoProvider.getWithMethodsForField(targetField);
        if(setterMethods.isEmpty() && withMethods.isEmpty()) {
            return Collections.emptySet();
        }
        final Set<PsiVariable> ret = SetUtils.newIdentityHashSet();
        addFirstParams(setterMethods, ret);
        addFirstParams(withMethods, ret);
        return ret;
    }

    private static void addFirstParams(final Set<PsiMethod> setterMethods, final Set<PsiVariable> ret) {
        for(final PsiMethod setter : setterMethods) {
            final PsiParameter[] params = setter.getParameterList().getParameters();
            if(params.length == 1) {
                ret.add(params[0]);
            }
        }
    }
}
