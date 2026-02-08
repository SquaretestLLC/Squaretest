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
package com.squaretest.completion.frameworkdetection;

import com.intellij.codeInsight.AnnotationUtil;
import com.intellij.codeInsight.MetaAnnotationUtil;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassObjectAccessExpression;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.PsiTypeElement;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.psi.util.InheritanceUtil;
import com.intellij.psi.util.PsiClassUtil;
import com.intellij.psi.util.PsiModificationTracker;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.jetbrains.annotations.NotNull;

/**
 * This is mostly copied from com.intellij.execution.junit.JUnitUtil.
 */
public class JUnitUtil {
    private static final String TEST4_ANNOTATION = "org.junit.Test";
    private static final String TEST5_ANNOTATION = "org.junit.jupiter.api.Test";
    private static final String CUSTOM_TESTABLE_ANNOTATION = "org.junit.platform.commons.annotation.Testable";
    private static final String TEST5_FACTORY_ANNOTATION = "org.junit.jupiter.api.TestFactory";
    private static final String RUN_WITH = "org.junit.runner.RunWith";

    private static final String BEFORE_ANNOTATION_NAME = "org.junit.Before";
    private static final String AFTER_ANNOTATION_NAME = "org.junit.After";

    private static final String BEFORE_EACH_ANNOTATION_NAME = "org.junit.jupiter.api.BeforeEach";
    private static final String AFTER_EACH_ANNOTATION_NAME = "org.junit.jupiter.api.AfterEach";

    private static final String PARAMETRIZED_PARAMETERS_ANNOTATION_NAME = "org.junit.runners.Parameterized.Parameters";
    private static final String PARAMETRIZED_PARAMETER_ANNOTATION_NAME = "org.junit.runners.Parameterized.Parameter";

    private static final String PARAMETERIZED_5_TEST = "org.junit.jupiter.api.ParameterizedTest";
    private static final String REPEATED_5_TEST = "org.junit.jupiter.api.RepeatedTest";

    private static final String AFTER_CLASS_ANNOTATION_NAME = "org.junit.AfterClass";
    private static final String BEFORE_CLASS_ANNOTATION_NAME = "org.junit.BeforeClass";

    private static final String BEFORE_ALL_ANNOTATION_NAME = "org.junit.jupiter.api.BeforeAll";
    private static final String AFTER_ALL_ANNOTATION_NAME = "org.junit.jupiter.api.AfterAll";

    private static final Collection<String> TEST5_ANNOTATIONS = java.util.List.of(TEST5_ANNOTATION, TEST5_FACTORY_ANNOTATION, CUSTOM_TESTABLE_ANNOTATION);

    private static final Collection<String> ALL_JUNIT4_METHOD_ANNOTATIONS = Arrays.asList(TEST4_ANNOTATION,
            BEFORE_ANNOTATION_NAME, AFTER_ANNOTATION_NAME,
            BEFORE_CLASS_ANNOTATION_NAME, AFTER_CLASS_ANNOTATION_NAME, PARAMETRIZED_PARAMETERS_ANNOTATION_NAME, PARAMETRIZED_PARAMETER_ANNOTATION_NAME);

    private static final Collection<String> ALL_JUNIT5_METHOD_ANNOTATIONS = Arrays.asList(
            TEST5_ANNOTATION, PARAMETERIZED_5_TEST, BEFORE_EACH_ANNOTATION_NAME, AFTER_EACH_ANNOTATION_NAME, REPEATED_5_TEST,
            BEFORE_ALL_ANNOTATION_NAME, AFTER_ALL_ANNOTATION_NAME);


    private static final String JUNIT5_NESTED = "org.junit.jupiter.api.Nested";

    private static final String[] RUNNERS_UNAWARE_OF_INNER_CLASSES = {
            "org.junit.runners.Parameterized",
            "org.junit.runners.BlockJUnit4ClassRunner",
            "org.junit.runners.JUnit4",
            "org.junit.internal.runners.JUnit38ClassRunner",
            "org.junit.internal.runners.JUnit4ClassRunner",
            "org.junit.runners.Suite"
    };

    public static boolean isJUnit4TestClass(final PsiClass psiClass, boolean checkAbstract) {
        final PsiModifierList modifierList = psiClass.getModifierList();
        if(modifierList == null) {
            return false;
        }
        final PsiClass topLevelClass = PsiTreeUtil.getTopmostParentOfType(modifierList, PsiClass.class);
        if(topLevelClass != null) {
            if(AnnotationUtil.isAnnotated(topLevelClass, RUN_WITH, AnnotationUtil.CHECK_HIERARCHY)) {
                PsiAnnotation annotation = getRunWithAnnotation(topLevelClass);
                if(topLevelClass == psiClass) {
                    return true;
                }

                //default runners do not implicitly run inner classes
                if(annotation != null && !isInheritorOrSelfRunner(annotation, RUNNERS_UNAWARE_OF_INNER_CLASSES)) {
                    return true;
                }
            }
        }

        if(!PsiClassUtil.isRunnableClass(psiClass, true, checkAbstract)) {
            return false;
        }

        for(final PsiMethod method : psiClass.getAllMethods()) {
            ProgressManager.checkCanceled();
            if(isJUnit4TestAnnotated(method)) {
                return true;
            }
            if(hasAnyJUnit4MethodAnnotation(method)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isJUnit5TestClass(@NotNull final PsiClass psiClass, boolean checkAbstract) {
        final PsiModifierList modifierList = psiClass.getModifierList();
        if(modifierList == null) {
            return false;
        }

        if(psiClass.getContainingClass() != null && AnnotationUtil.isAnnotated(psiClass, JUNIT5_NESTED, 0)) {
            return true;
        }

        if(MetaAnnotationUtil.isMetaAnnotated(psiClass, Collections.singleton(CUSTOM_TESTABLE_ANNOTATION))) {
            return true;
        }

        if(!PsiClassUtil.isRunnableClass(psiClass, false, checkAbstract)) {
            return false;
        }

        Module module = ModuleUtilCore.findModuleForPsiElement(psiClass);
        if(module != null) {
            return CachedValuesManager.getCachedValue(psiClass, () -> {
                boolean hasAnnotation = false;
                for(final PsiMethod method : psiClass.getAllMethods()) {
                    ProgressManager.checkCanceled();
                    if(MetaAnnotationUtil.isMetaAnnotated(method, TEST5_ANNOTATIONS)) {
                        hasAnnotation = true;
                        break;
                    }
                    if(hasAnyJUnit5MethodAnnotation(method)) {
                        hasAnnotation = true;
                        break;
                    }
                }

                if(!hasAnnotation) {
                    for(PsiClass aClass : psiClass.getAllInnerClasses()) {
                        if(MetaAnnotationUtil.isMetaAnnotated(aClass, Collections.singleton(JUNIT5_NESTED))) {
                            hasAnnotation = true;
                            break;
                        }
                    }
                }
                return CachedValueProvider.Result.create(hasAnnotation, PsiModificationTracker.MODIFICATION_COUNT);
            });
        }

        return false;
    }

    private static boolean isJUnit4TestAnnotated(final PsiMethod method) {
        if(AnnotationUtil.isAnnotated(method, TEST4_ANNOTATION, 0)) {
            return true;
        }

        return MetaAnnotationUtil.isMetaAnnotated(method, Collections.singletonList(TEST4_ANNOTATION));
    }

    private static boolean hasAnyJUnit4MethodAnnotation(final PsiMethod method) {
        return AnnotationUtil.isAnnotated(method, ALL_JUNIT4_METHOD_ANNOTATIONS, 0);
    }

    private static boolean hasAnyJUnit5MethodAnnotation(final PsiMethod method) {
        return AnnotationUtil.isAnnotated(method, ALL_JUNIT5_METHOD_ANNOTATIONS, 0);
    }

    private static PsiAnnotation getRunWithAnnotation(PsiClass aClass) {
        return AnnotationUtil.findAnnotationInHierarchy(aClass, Collections.singleton(RUN_WITH));
    }

    private static boolean isInheritorOrSelfRunner(PsiAnnotation annotation, String... runners) {
        final PsiAnnotationMemberValue value = annotation.findAttributeValue(PsiAnnotation.DEFAULT_REFERENCED_METHOD_NAME);
        if(value instanceof PsiClassObjectAccessExpression) {
            final PsiTypeElement operand = ((PsiClassObjectAccessExpression) value).getOperand();
            final PsiClass psiClass = PsiUtil.resolveClassInClassTypeOnly(operand.getType());
            return psiClass != null && Arrays.stream(runners).anyMatch(runner -> InheritanceUtil.isInheritor(psiClass, runner));
        }
        return false;
    }
}