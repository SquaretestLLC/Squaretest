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

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiJvmModifiersOwner;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiVariable;
import com.intellij.psi.util.PsiUtil;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.defaulttypes.SQAnnotationUtil;
import com.squaretest.generation.sourcevars.ConstructorSourceVariableProvider;
import com.squaretest.generation.sourcevars.ISourceVariableProvider;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import static com.squaretest.generation.dataflow.DataflowUtils.getContainingMethodForParameter;

public class LombokBuilderSourceVariableProvider implements ISourceVariableProvider {
    private final ConstructorSourceVariableProvider constructorSourceVariableProvider;
    private final Map<PsiClass, Map<PsiField, Set<PsiVariable>>> cache;

    public LombokBuilderSourceVariableProvider(final ConstructorSourceVariableProvider constructorSourceVariableProvider) {
        this.constructorSourceVariableProvider = constructorSourceVariableProvider;
        this.cache = new IdentityHashMap<>();
    }

    @Override
    @Nullable
    public PsiField getTargetField(@NotNull final PsiMethod psiMethod, @Nullable final PsiParameter param) {
        return getTargetField(psiMethod);
    }

    @Nullable
    public PsiField getTargetField(@NotNull final PsiMethod psiMethod) {
        if(psiMethod.isConstructor()) {
            return null;
        }
        if(psiMethod.getParameterList().getParameters().length == 0) {
            return null;
        }
        if(StringUtils.contains(psiMethod.getClass().getCanonicalName(), JavaNames.LombokLightMethodName)) {
            final PsiElement navTarget = psiMethod.getNavigationElement();
            if(navTarget instanceof final PsiParameter paramTarget) {
                // The nav target resolves to a constructor or method param.
                // This is likely a constructor with an @Builder annotation.
                final PsiMethod containingMethod = getContainingMethodForParameter(paramTarget);
                if(containingMethod == null || !containingMethod.isConstructor()) {
                    return null;
                }
                return constructorSourceVariableProvider.getTargetField(containingMethod, paramTarget);
            }
            if(!(navTarget instanceof final PsiField navField)) {
                return null;
            }
            final PsiClass navFieldClass = navField.getContainingClass();
            if(navFieldClass == null || navFieldClass == psiMethod.getContainingClass()) {
                // If the nav field is in the same class as the builder method, it is not a builder method.
                // It is likely a lombok-generated setter method or with* method.
                return null;
            }
            return navField;
        }
        return null;
    }

    @Override
    @NotNull
    public Set<PsiVariable> getSourceVariables(@NotNull final PsiClass startingClass, @NotNull final PsiField targetField) {
        final PsiClass containingClass = targetField.getContainingClass();
        if(containingClass == null) {
            return Collections.emptySet();
        }
        return cache.computeIfAbsent(containingClass, this::getSourceVariablesImpl).getOrDefault(targetField, Collections.emptySet());
    }

    @NotNull
    private Map<PsiField, Set<PsiVariable>> getSourceVariablesImpl(@NotNull final PsiClass psiClass) {
        final PsiMethod builderMethod = getBuilderFactoryMethod(psiClass);
        if(builderMethod == null) {
            return Collections.emptyMap();
        }
        final PsiClass builderClass = getBuilderClass(builderMethod);
        if(builderClass == null) {
            return Collections.emptyMap();
        }
        final Map<PsiField, Set<PsiVariable>> ret = new IdentityHashMap<>();
        for(final PsiMethod method : builderClass.getMethods()) {
            final PsiParameter[] params = method.getParameterList().getParameters();
            if(params.length != 1) {
                continue;
            }
            final PsiParameter methodParam = params[0];
            final PsiElement navTarget = method.getNavigationElement();
            if(!(navTarget instanceof final PsiField navTargetField)) {
                continue;
            }
            Set<PsiVariable> existingSet = ret.get(navTargetField);
            if(existingSet == null) {
                existingSet = SetUtils.newIdentityHashSet();
                existingSet.add(methodParam);
                ret.put(navTargetField, existingSet);
                continue;
            }
            existingSet.add(methodParam);
        }
        return ret;
    }

    @Nullable
    public static PsiClass getBuilderClass(final PsiMethod builderMethod) {
        return PsiUtil.resolveClassInType(builderMethod.getReturnType());
    }

    @Nullable
    public static PsiMethod getBuilderFactoryMethod(final PsiClass psiClass) {
        String builderMethodName = getBuilderFactoryMethodName(psiClass);
        if(builderMethodName != null) {
            return getMethodByName(psiClass, builderMethodName);
        }
        for(final PsiMethod constructor : psiClass.getConstructors()) {
            builderMethodName = getBuilderFactoryMethodName(constructor);
            if(builderMethodName != null) {
                return getMethodByName(psiClass, builderMethodName);
            }
        }
        return null;
    }

    @Nullable
    public static PsiMethod getBuildMethod(final PsiClass mainClass, final PsiClass builderClass) {
        String builderMethodName = getBuildMethodName(mainClass);
        if(builderMethodName != null) {
            return getMethodByName(builderClass, builderMethodName);
        }
        return null;
    }

    @Nullable
    private static PsiMethod getMethodByName(final PsiClass psiClass, final String builderMethodName) {
        final PsiMethod[] builderMethods = psiClass.findMethodsByName(builderMethodName, false);
        if(builderMethods.length == 0) {
            return null;
        }
        return builderMethods[0];
    }

    private static String getBuilderFactoryMethodName(final PsiJvmModifiersOwner annotationOwner) {
        final PsiAnnotation builderAnnotation = tryGetLombokBuilderAnnotation(annotationOwner);
        if(builderAnnotation == null) {
            return null;
        }
        return SQAnnotationUtil.getStringAnnotationValue(builderAnnotation, "builderMethodName", "builder");
    }

    private static String getBuildMethodName(final PsiClass psiClass) {
        PsiAnnotation builderAnnotation = tryGetLombokBuilderAnnotation(psiClass);
        if(builderAnnotation == null) {
            builderAnnotation = tryFindBuilderAnnotationOnConstructor(psiClass);
        }
        if(builderAnnotation != null) {
            return SQAnnotationUtil.getStringAnnotationValue(builderAnnotation, "buildMethodName", "build");
        }
        return null;
    }

    @Nullable
    private static PsiAnnotation tryFindBuilderAnnotationOnConstructor(final PsiClass psiClass) {
        for(final PsiMethod constructor : psiClass.getConstructors()) {
            final PsiAnnotation annotation = SQAnnotationUtil.getAnnotation(constructor, JavaNames.AllLombokBuilders);
            if(annotation != null) {
                return annotation;
            }
        }
        return null;
    }

    @Nullable
    private static PsiAnnotation tryGetLombokBuilderAnnotation(final PsiJvmModifiersOwner annotationOwner) {
        return SQAnnotationUtil.getAnnotation(annotationOwner, JavaNames.AllLombokBuilders);
    }
}
