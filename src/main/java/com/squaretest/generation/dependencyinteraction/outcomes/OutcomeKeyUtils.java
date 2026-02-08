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
package com.squaretest.generation.dependencyinteraction.outcomes;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiType;
import com.squaretest.generation.defaulttypes.TypeCreatorUtil;
import com.squaretest.generation.dependencyinteraction.DiAndNode;
import com.squaretest.generation.dependencyinteraction.DiAndNodeSet;
import com.squaretest.generation.dependencyinteraction.InternalDependencyInteraction;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.ClassMemberImpl;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class OutcomeKeyUtils {
    @Nullable
    public static String computeFinalMapKey(final PsiMethod sourceMethod, final InternalDependencyInteraction di, final PsiType exception) {
        final PsiClass sourceMethodClass = sourceMethod.getContainingClass();
        if(sourceMethodClass == null || sourceMethodClass.getName() == null) {
            return null;
        }
        final PsiClass diFieldClass = di.getPsiField().getContainingClass();
        if(diFieldClass == null || diFieldClass.getName() == null) {
            return null;
        }
        return sourceMethodClass.getName() + "_" +
                TypeCreatorUtil.getMethodKey(sourceMethod) + "_" +
                diFieldClass.getName() + "_" +
                di.getPsiField().getName() + "_" +
                TypeCreatorUtil.getMethodKey(di.getPsiMethod()) + "_" +
                exception.getCanonicalText();
    }

    @Nullable
    public static String computeFinalMapKey(final Api.Method sourceMethod, final Api.DependencyInteraction di, final Api.Exception exception) {
        if(sourceMethod == null || di == null || exception == null) {
            return null;
        }
        String canonicalNameOrName = exception.getCanonicalName();
        if(canonicalNameOrName == null) {
            canonicalNameOrName = exception.getType().getCanonicalText();
        }
        return computeFinalMapKey(sourceMethod, di, canonicalNameOrName);
    }

    @Nullable
    public static String computeFinalMapKey(final Api.Method sourceMethod, final Api.DependencyInteraction di, final String exceptionCanonicalName) {
        if(sourceMethod == null || di == null || exceptionCanonicalName == null) {
            return null;
        }
        final Api.SourceClass sourceMethodClass = sourceMethod.getContainingClass();
        if(sourceMethodClass == null) {
            return null;
        }
        final Api.SourceClass diFieldClass = ((ClassMemberImpl) di.getField()).getContainingClass();
        if(diFieldClass == null) {
            return null;
        }
        return sourceMethodClass.getName() + "_" +
                sourceMethod.getMethodKey() + "_" +
                diFieldClass.getName() + "_" +
                di.getField().getDeclaredName() + "_" +
                di.getMethod().getMethodKey() + "_" +
                exceptionCanonicalName;
    }

    @Nullable
    public static String computeFinalMapKeyForStartingOutcome(
            final PsiMethod sourceMethod, final InternalDependencyInteraction di, final ReturnOutcome startingOutcome) {
        final PsiClass sourceMethodClass = sourceMethod.getContainingClass();
        if(sourceMethodClass == null || sourceMethodClass.getName() == null) {
            return null;
        }
        final PsiClass diFieldClass = di.getPsiField().getContainingClass();
        if(diFieldClass == null || diFieldClass.getName() == null) {
            return null;
        }
        return sourceMethodClass.getName() + "_" +
                TypeCreatorUtil.getMethodKey(sourceMethod) + "_" +
                diFieldClass.getName() + "_" +
                di.getPsiField().getName() + "_" +
                TypeCreatorUtil.getMethodKey(di.getPsiMethod()) + "_" +
                startingOutcome;
    }

    @Nullable
    public static String computeFinalMapKeyForStartingOutcome(final Api.Method sourceMethod, final Api.DependencyInteraction di, final ReturnOutcome startingOutcome) {
        if(sourceMethod == null || di == null || startingOutcome == null) {
            return null;
        }
        final Api.SourceClass sourceMethodClass = sourceMethod.getContainingClass();
        if(sourceMethodClass == null) {
            return null;
        }
        final Api.SourceClass diFieldClass = ((ClassMemberImpl) di.getField()).getContainingClass();
        if(diFieldClass == null) {
            return null;
        }
        return sourceMethodClass.getName() + "_" +
                sourceMethod.getMethodKey() + "_" +
                diFieldClass.getName() + "_" +
                di.getField().getDeclaredName() + "_" +
                di.getMethod().getMethodKey() + "_" +
                startingOutcome;
    }

    public static LinkedHashSet<String> convertToDiKeys(final DiAndNodeSet disHitAfter) {
        final LinkedHashSet<String> ret = new LinkedHashSet<>();
        final Set<InternalDependencyInteraction> alreadyAddedNodes = new HashSet<>();
        for(final DiAndNode di : disHitAfter) {
            if(!alreadyAddedNodes.add(di.internalDependencyInteraction())) {
                continue;
            }
            final String diKey = convertToDiKey(di.internalDependencyInteraction());
            if(diKey == null) {
                continue;
            }
            ret.add(diKey);
        }
        return ret;
    }

    @Nullable
    public static String convertToDiKey(final InternalDependencyInteraction di) {
        final PsiField diField = di.getPsiField();
        return convertToDiKey(di, diField);
    }

    @Nullable
    private static String convertToDiKey(final InternalDependencyInteraction di, final PsiField diField) {
        final PsiClass containingClass = diField.getContainingClass();
        if(containingClass == null) {
            return null;
        }
        final String containingClassName = containingClass.getName();
        if(containingClassName == null) {
            return null;
        }
        return containingClassName + "_" +
                diField.getName() + "_" +
                TypeCreatorUtil.getMethodKey(di.getPsiMethod());
    }

    public static Set<String> convertToDiKeys(final InternalDependencyInteraction internalDependencyInteraction, final Set<PsiField> fieldsWithSameDep) {
        if(fieldsWithSameDep.size() <= 1) {
            final String diKey = convertToDiKey(internalDependencyInteraction);
            if(diKey != null) {
                return Set.of(diKey);
            } else {
                return Collections.emptySet();
            }
        }
        final Set<String> ret = new HashSet<>();
        for(final PsiField diField : fieldsWithSameDep) {
            ret.add(convertToDiKey(internalDependencyInteraction, diField));
        }
        return ret;
    }

    @Nullable
    public static String convertToDiKey(final Api.DependencyInteraction di) {
        final ClassMemberImpl diField = (ClassMemberImpl) di.getField();
        final Api.SourceClass containingClass = diField.getContainingClass();
        if(containingClass == null) {
            return null;
        }
        final String containingClassName = containingClass.getName();
        return containingClassName + "_" +
                diField.getDeclaredName() + "_" +
                di.getMethod().getMethodKey();
    }
}
