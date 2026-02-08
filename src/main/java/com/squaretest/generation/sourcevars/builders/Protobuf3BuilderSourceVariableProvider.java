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
import com.intellij.psi.PsiVariable;
import com.intellij.psi.util.InheritanceUtil;
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

import static com.squaretest.generation.defaulttypes.ProtobufUtils.findNewBuilderMethod;

public class Protobuf3BuilderSourceVariableProvider implements ISourceVariableProvider {

    private static final String[] BuilderMethodSuffixes = new String[]{"Bytes", "Value", "Count", "OrBuilderList", "BuilderList", "OrBuilder", "Map", "OrDefault", "OrThrow"};
    private static final String[] BuilderMethodPrefixes = new String[]{"addAll", "add", "set", "get", "remove", "contains", "putAll", "put"};

    private final Map<PsiClass, Map<PsiField, Set<PsiVariable>>> cache;

    public Protobuf3BuilderSourceVariableProvider() {
        cache = new IdentityHashMap<>();
    }

    @Override
    @Nullable
    public PsiField getTargetField(
            @NotNull final PsiMethod psiMethod, @Nullable final PsiParameter param) {
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

    private PsiClass getTargetClassForBuilder(final PsiClass builderClass) {
        if(!StringUtils.equals(builderClass.getName(), "Builder")) {
            return null;
        }
        if(!InheritanceUtil.isInheritor(builderClass, "com.google.protobuf.GeneratedMessageV3.Builder")) {
            return null;
        }
        return builderClass.getContainingClass();
    }

    @Override
    @NotNull
    public Set<PsiVariable> getSourceVariables(
            @NotNull final PsiClass startingClass,
            @NotNull final PsiField targetField) {
        final PsiClass containingClass = targetField.getContainingClass();
        if(containingClass == null) {
            return Collections.emptySet();
        }
        return cache.computeIfAbsent(containingClass, this::getSourceVariablesImpl).getOrDefault(targetField, Collections.emptySet());
    }

    private Map<PsiField, Set<PsiVariable>> getSourceVariablesImpl(@NotNull final PsiClass psiClass) {
        final PsiMethod builderFactoryMethod = findNewBuilderMethod(psiClass);
        if(builderFactoryMethod == null) {
            return Collections.emptyMap();
        }
        final PsiClass builderClass = PsiUtil.resolveClassInType(builderFactoryMethod.getReturnType());
        if(builderClass == null) {
            return Collections.emptyMap();
        }
        final Map<PsiField, Set<PsiVariable>> ret = new IdentityHashMap<>();
        for(final PsiMethod builderMethod : builderClass.getMethods()) {
            final PsiParameter[] params = builderMethod.getParameterList().getParameters();
            if(params.length == 0
                    || builderMethod.hasModifierProperty(PsiModifier.STATIC)
                    || !builderMethod.hasModifierProperty(PsiModifier.PUBLIC)
                    || !StringUtils.startsWithAny(builderMethod.getName(), "set", "add", "put")) {
                continue;
            }

            final PsiField targetField = getTargetField(psiClass, builderMethod);
            if(targetField == null) {
                continue;
            }
            // Determine the param to use for the source variable.
            final PsiParameter methodParam;
            if(params.length == 1) {
                methodParam = params[0];
            } else if(params.length == 2) {
                methodParam = params[1];
            } else {
                continue;
            }
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

    @NotNull
    private static List<String> getTargetFieldNames(@NotNull final PsiMethod psiMethod) {
        List<String> intermediateNamesToUse = removePrefixes(psiMethod.getName());
        intermediateNamesToUse = removeSuffixes(intermediateNamesToUse);

        final List<String> namesToUse = new ArrayList<>(intermediateNamesToUse.size());
        for(final String name : intermediateNamesToUse) {
            namesToUse.add(name + "_");
        }
        return namesToUse;
    }

    private static List<String> removeSuffixes(final List<String> namesToUse) {
        final List<String> ret = new ArrayList<>(4);
        for(final String methodName : namesToUse) {
            ret.add(methodName);
            for(final String suffix : BuilderMethodSuffixes) {
                if(methodName.endsWith(suffix) && methodName.length() > suffix.length()) {
                    ret.add(StringUtils.removeEnd(methodName, suffix));
                }
            }
        }
        return ret;
    }

    private static List<String> removePrefixes(final String methodName) {
        final List<String> ret = new ArrayList<>(2);
        for(final String prefix : BuilderMethodPrefixes) {
            if(methodName.startsWith(prefix)
                    && methodName.length() > prefix.length()
                    && Character.isUpperCase(methodName.charAt(prefix.length()))) {
                ret.add(StringUtils.uncapitalize(StringUtils.removeStart(methodName, prefix)));
            }
        }
        return ret;
    }
}
