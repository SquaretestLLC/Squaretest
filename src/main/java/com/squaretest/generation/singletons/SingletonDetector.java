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
package com.squaretest.generation.singletons;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiEnumConstant;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiType;
import com.intellij.psi.util.PsiUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Detects if a class is a singleton.
 */
public class SingletonDetector {

    private static final String EnumSingletonAccessExpressionFormat = "%s.%s";
    private static final String TraditionalSingletonAccessExpressionFormat = "%s.%s()";

    private static final List<String> SingletonMethodNames = Arrays.asList("getInstance", "instance");

    public SingletonInfo getSingletonInfo(@NotNull final PsiClass theClass) {

        // Check for enum-singleton.
        if(theClass.isEnum()) {
            PsiField[] fields = theClass.getFields();
            final List<PsiField> enumConstants = Arrays.stream(fields).filter(x -> x instanceof PsiEnumConstant).toList();
            if(enumConstants.size() == 1) {
                final String initExpression = String.format(EnumSingletonAccessExpressionFormat, theClass.getName(), enumConstants.get(0).getName());
                return new SingletonInfo(true, initExpression);
            } else {
                new SingletonInfo(false, null);
            }
        }

        // Check for traditional singleton
        for(final PsiMethod method : theClass.getMethods()) {
            if(matchesTraditionalSingletonPattern(theClass, method)) {
                final String methodName = method.getName();
                final String initExpression = String.format(TraditionalSingletonAccessExpressionFormat, theClass.getName(), methodName);
                return new SingletonInfo(true, initExpression);
            }
        }
        return new SingletonInfo(false, null);
    }

    /**
     * Checks to see if this method matches the traditional singleton method; i.e. its name is in
     * {@link #SingletonMethodNames} and it is static and it is public or package-local.
     *
     * @param method the method to check
     * @return true if the method matches the traditional singleton pattern.
     */
    private boolean matchesTraditionalSingletonPattern(final PsiClass sourceClass, final PsiMethod method) {
        final String methodName = method.getName();
        final PsiType returnType = method.getReturnType();
        if(returnType == null) {
            return false;
        }
        final PsiClass returnClass = PsiUtil.resolveClassInType(returnType);
        if(returnClass == null) {
            return false;
        }
        return hasSingletonMethodName(methodName)
                && hasAppropriateAccessLevel(method)
                && isStatic(method)
                && StringUtils.equals(sourceClass.getQualifiedName(), returnClass.getQualifiedName())
                && method.getParameterList().getParameters().length == 0;
    }

    private boolean isStatic(@NotNull final PsiMethod method) {
        return method.hasModifierProperty(PsiModifier.STATIC);
    }

    private boolean hasAppropriateAccessLevel(@NotNull final PsiMethod method) {
        return method.hasModifierProperty(PsiModifier.PUBLIC)
                || method.hasModifierProperty(PsiModifier.PACKAGE_LOCAL)
                || method.hasModifierProperty(PsiModifier.PROTECTED);
    }

    private static boolean hasSingletonMethodName(@NotNull final String methodName) {
        return containsIgnoreCase(SingletonMethodNames, methodName);
    }

    private static boolean containsIgnoreCase(@NotNull final List<String> theList, @NotNull final String searchName) {
        for(final String name : theList) {
            if(name.equalsIgnoreCase(searchName)) {
                return true;
            }
        }
        return false;
    }
}
