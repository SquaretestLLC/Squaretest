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
package com.squaretest.generation.defaulttypes.beans;

import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.squaretest.utils.IdentityMultimapWithIdentitySets;

import java.util.IdentityHashMap;
import java.util.Set;

public class BeanMethodsInfo {
    private final IdentityHashMap<PsiMethod, PsiField> getterMethodToFieldMap;
    private final IdentityHashMap<PsiMethod, PsiField> setterMethodToFieldMap;
    private final IdentityHashMap<PsiMethod, PsiField> withMethodToFieldMap;
    private final IdentityMultimapWithIdentitySets<PsiField, PsiMethod> fieldToGettersMultimap;
    private final IdentityMultimapWithIdentitySets<PsiField, PsiMethod> fieldToSettersMultimap;
    private final IdentityMultimapWithIdentitySets<PsiField, PsiMethod> fieldToWithMethodsMultimap;
    private final Set<PsiMethod> jaxBGetters;

    public BeanMethodsInfo(
            final IdentityHashMap<PsiMethod, PsiField> getterMethodToFieldMap,
            final IdentityHashMap<PsiMethod, PsiField> setterMethodToFieldMap,
            final IdentityHashMap<PsiMethod, PsiField> withMethodToFieldMap,
            final IdentityMultimapWithIdentitySets<PsiField, PsiMethod> fieldToGettersMultimap,
            final IdentityMultimapWithIdentitySets<PsiField, PsiMethod> fieldToSettersMultimap,
            final IdentityMultimapWithIdentitySets<PsiField, PsiMethod> fieldToWithMethodsMultimap,
            final Set<PsiMethod> jaxBGetters) {
        this.getterMethodToFieldMap = getterMethodToFieldMap;
        this.setterMethodToFieldMap = setterMethodToFieldMap;
        this.withMethodToFieldMap = withMethodToFieldMap;
        this.fieldToGettersMultimap = fieldToGettersMultimap;
        this.fieldToSettersMultimap = fieldToSettersMultimap;
        this.fieldToWithMethodsMultimap = fieldToWithMethodsMultimap;
        this.jaxBGetters = jaxBGetters;
    }

    public boolean isSetter(final PsiMethod method) {
        return setterMethodToFieldMap.containsKey(method);
    }

    public boolean isGetter(final PsiMethod method) {
        return getterMethodToFieldMap.containsKey(method);
    }

    public PsiField getFieldForGetter(final PsiMethod method) {
        return getterMethodToFieldMap.get(method);
    }

    public PsiField getFieldForSetter(final PsiMethod method) {
        return setterMethodToFieldMap.get(method);
    }

    public PsiField getFieldForWithMethod(final PsiMethod method) {
        return withMethodToFieldMap.get(method);
    }

    public Set<PsiMethod> getSettersForField(final PsiField field) {
        return fieldToSettersMultimap.get(field);
    }

    public Set<PsiMethod> getGettersForField(final PsiField field) {
        return fieldToGettersMultimap.get(field);
    }

    public Set<PsiMethod> getWithMethodsForField(final PsiField field) {
        return fieldToWithMethodsMultimap.get(field);
    }

    public boolean isJaxBGetter(final PsiMethod method) {
        return jaxBGetters.contains(method);
    }
}
