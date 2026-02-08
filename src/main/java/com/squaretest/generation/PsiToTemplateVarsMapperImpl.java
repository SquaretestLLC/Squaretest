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

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiVariable;
import com.squaretest.generation.keys.ClassAndInitStrategy;
import com.squaretest.generation.keys.MethodAndInitStrategy;
import com.squaretest.generation.keys.VariableAndInitStrategy;
import com.squaretest.template.api.Api;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class PsiToTemplateVarsMapperImpl implements PsiToTemplateVarsMapper {

    private final Map<ClassAndInitStrategy, Api.SourceClass> psiClassSourceClassMap;
    private final Map<VariableAndInitStrategy, Api.Variable> psiToVariableMap;
    private final Map<MethodAndInitStrategy, Api.Method> psiToMethodMap;
    private final Map<Api.Method, PsiMethod> apiToPsiMethodMap;
    private final Map<Api.Variable, PsiVariable> apiToPsiVariableMap;

    public PsiToTemplateVarsMapperImpl() {
        this.psiToMethodMap = new HashMap<>();
        this.psiToVariableMap = new HashMap<>();
        this.psiClassSourceClassMap = new HashMap<>();
        this.apiToPsiMethodMap = new HashMap<>();
        this.apiToPsiVariableMap = new HashMap<>();
    }

    @Override
    public void putMethod(@NotNull final PsiMethod psiMethod, @NotNull final Api.Method method, final InitStrategy initStrategy) {
        psiToMethodMap.put(new MethodAndInitStrategy(psiMethod, initStrategy), method);
        apiToPsiMethodMap.put(method, psiMethod);
    }

    @Override
    public Api.Method getMethod(@NotNull final PsiMethod psiMethod, final InitStrategy initStrategy) {
        return psiToMethodMap.get(new MethodAndInitStrategy(psiMethod, initStrategy));
    }

    @Override
    public PsiMethod getPsiMethod(@NotNull final Api.Method apiMethod) {
        return apiToPsiMethodMap.get(apiMethod);
    }

    @Override
    public void putVariable(@NotNull final PsiVariable psiVariable, @NotNull final Api.Variable variable, final InitStrategy initStrategy) {
        psiToVariableMap.put(new VariableAndInitStrategy(psiVariable, initStrategy), variable);
        apiToPsiVariableMap.put(variable, psiVariable);
    }

    @Override
    public Api.Variable getVariable(@NotNull final PsiVariable variable, final InitStrategy initStrategy) {
        return this.psiToVariableMap.get(new VariableAndInitStrategy(variable, initStrategy));
    }

    @Override
    public PsiVariable getPsiVariable(final Api.Variable apiVariable) {
        return apiToPsiVariableMap.get(apiVariable);
    }

    @Override
    public Api.SourceClass getSourceClass(@NotNull final PsiClass psiClass, final InitStrategy initStrategy) {
        return this.psiClassSourceClassMap.get(new ClassAndInitStrategy(psiClass, initStrategy));
    }

    @Override
    public void putSourceClass(@NotNull final PsiClass psiClass, @NotNull final Api.SourceClass sourceClass, final InitStrategy initStrategy) {
        this.psiClassSourceClassMap.put(new ClassAndInitStrategy(psiClass, initStrategy), sourceClass);
    }
}
