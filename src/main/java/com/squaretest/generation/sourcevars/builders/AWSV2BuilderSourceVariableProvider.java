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
package com.squaretest.generation.sourcevars.builders;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypes;
import com.intellij.psi.PsiVariable;
import com.intellij.psi.util.PsiUtil;
import com.squaretest.generation.sourcevars.ISourceVariableProvider;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AWSV2BuilderSourceVariableProvider implements ISourceVariableProvider {

    private final Map<PsiClass, Map<PsiField, Set<PsiVariable>>> cache;

    public AWSV2BuilderSourceVariableProvider() {
        cache = new IdentityHashMap<>();
    }

    @Override
    @Nullable
    public PsiField getTargetField(@NotNull final PsiMethod psiMethod, @Nullable final PsiParameter param) {
        final PsiClass containingClass = psiMethod.getContainingClass();
        if(containingClass == null) {
            return null;
        }
        final PsiClass targetClass = getTargetClassForBuilder(containingClass);
        if(targetClass == null) {
            return null;
        }

        final PsiField targetField = getTargetField(targetClass, psiMethod);
        if(targetField != null) {
            return targetField;
        }
        return null;
    }

    @Nullable
    public static PsiField getTargetField(@NotNull final PsiClass targetClass, @NotNull final PsiMethod builderMethod) {
        final List<String> targetFieldNames = getTargetFieldNames(builderMethod);
        for(final String targetFieldName : targetFieldNames) {
            final PsiField targetField = targetClass.findFieldByName(targetFieldName, true);
            if(targetField != null) {
                return targetField;
            }
        }
        return null;
    }

    @NotNull
    private static List<String> getTargetFieldNames(@NotNull final PsiMethod psiMethod) {
        final String methodName = psiMethod.getName();
        if(methodName.startsWith("set") && methodName.length() >= 4 && Character.isUpperCase(methodName.charAt(3))) {
            return List.of(methodName, StringUtils.uncapitalize(StringUtils.removeStart(methodName, "set")));
        }
        if(methodName.startsWith("add") && methodName.length() >= 4 && Character.isUpperCase(methodName.charAt(3))) {
            final List<String> altNames = new ArrayList<>(3);
            final String altName1 = StringUtils.uncapitalize(StringUtils.removeStart(methodName, "add"));
            altNames.add(altName1);
            if(!altName1.endsWith("s")) {
                altNames.add(altName1 + "s");
                altNames.add(altName1 + "es");
            }
            altNames.add(methodName);
            return altNames;
        }
        if(methodName.endsWith("WithStrings")) {
            return List.of(methodName, StringUtils.removeEnd(methodName, "WithStrings"));
        }
        return Collections.singletonList(methodName);
    }

    private PsiClass getTargetClassForBuilder(final PsiClass builderClass) {
        final String qualifiedName = builderClass.getQualifiedName();
        if(!StringUtils.startsWith(qualifiedName, "software.amazon.awssdk")) {
            return null;
        }
        if(!StringUtils.equals(builderClass.getName(), "Builder")) {
            return null;
        }
        return builderClass.getContainingClass();
    }

    @Override
    @NotNull
    public Set<PsiVariable> getSourceVariables(
            @NotNull final PsiClass startingClass, @NotNull final PsiField targetField) {
        final PsiClass containingClass = targetField.getContainingClass();
        if(containingClass == null) {
            return Collections.emptySet();
        }
        return cache.computeIfAbsent(containingClass, this::getSourceVariablesImpl).getOrDefault(targetField, Collections.emptySet());
    }

    @NotNull
    private Map<PsiField, Set<PsiVariable>> getSourceVariablesImpl(@NotNull final PsiClass psiClass) {
        final PsiMethod builderFactoryMethod = getBuilderFactoryMethod(psiClass);
        if(builderFactoryMethod == null) {
            return Collections.emptyMap();
        }
        final PsiClass builderClass = getBuilderClass(builderFactoryMethod);
        if(builderClass == null) {
            return Collections.emptyMap();
        }
        final Map<PsiField, Set<PsiVariable>> ret = new IdentityHashMap<>();
        for(final PsiMethod builderMethod : builderClass.getMethods()) {
            final PsiParameter[] params = builderMethod.getParameterList().getParameters();
            if(params.length != 1) {
                continue;
            }
            final PsiField targetField = getTargetField(psiClass, builderMethod);
            if(targetField == null) {
                continue;
            }
            final PsiParameter methodParam = params[0];
            Set<PsiVariable> existingSet = ret.get(targetField);
            if(existingSet == null) {
                existingSet = SetUtils.newIdentityHashSet();
                existingSet.add(methodParam);
                ret.put(targetField, existingSet);
                continue;
            }
            existingSet.add(methodParam);
        }
        return ret;
    }

    @Nullable
    public static PsiClass getBuilderClass(@NotNull final PsiMethod builderMethod) {
        final PsiClass psiClass = PsiUtil.resolveClassInType(builderMethod.getReturnType());
        if(psiClass == null) {
            return null;
        }
        return psiClass;
    }

    public static PsiMethod getBuilderFactoryMethod(@NotNull final PsiClass psiClass) {
        final PsiMethod[] builderMethods = psiClass.findMethodsByName("builder", false);
        for(final PsiMethod builderMethod : builderMethods) {
            if(isBuilderFactoryMethod(builderMethod)) {
                return builderMethod;
            }
        }
        return null;
    }

    private static boolean isBuilderFactoryMethod(final PsiMethod builderMethod) {
        if(!(builderMethod.hasModifierProperty(PsiModifier.PUBLIC) && builderMethod.hasModifierProperty(PsiModifier.STATIC))) {
            return false;
        }
        final PsiType returnType = builderMethod.getReturnType();
        if(returnType == null) {
            return false;
        }
        final PsiClass psiClass = PsiUtil.resolveClassInType(returnType);
        if(psiClass == null) {
            return false;
        }
        final String name = psiClass.getName();
        if(!StringUtils.equals(name, "Builder")) {
            return false;
        }
        return true;
    }

    @Nullable
    public static PsiMethod getBuildMethod(final PsiClass builderClass) {
        final PsiMethod[] buildMethods = builderClass.findMethodsByName("build", true);
        for(final PsiMethod buildMethod : buildMethods) {
            if(isBuildMethod(buildMethod)) {
                return buildMethod;
            }
        }
        return null;
    }

    private static boolean isBuildMethod(final PsiMethod buildMethod) {
        if(!buildMethod.hasModifierProperty(PsiModifier.PUBLIC)) {
            return false;
        }
        if(buildMethod.getParameterList().getParameters().length != 0) {
            return false;
        }
        final PsiType returnType = buildMethod.getReturnType();
        if(returnType == null || PsiTypes.voidType().equals(returnType)) {
            return false;
        }
        return true;
    }
}
