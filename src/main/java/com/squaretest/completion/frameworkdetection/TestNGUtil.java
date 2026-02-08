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
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDocCommentOwner;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiModifierListOwner;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.javadoc.PsiDocTag;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.psi.util.PsiClassUtil;
import com.intellij.psi.util.PsiModificationTracker;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

/**
 * Ported from the TestNGUtil class in the IntelliJ IDEA source code.
 */
public class TestNGUtil {

    @SuppressWarnings("StaticNonFinalField")
    private static final boolean hasDocTagsSupport = false;

    private static final String TEST_ANNOTATION_FQN = "org.testng.annotations.Test";
    private static final String FACTORY_ANNOTATION_FQN = "org.testng.annotations.Factory";
    private static final String[] CONFIG_ANNOTATIONS_FQN = {
            "org.testng.annotations.Configuration",
            "org.testng.annotations.Factory",
            "org.testng.annotations.ObjectFactory",
            "org.testng.annotations.DataProvider",
            "org.testng.annotations.BeforeClass",
            "org.testng.annotations.BeforeGroups",
            "org.testng.annotations.BeforeMethod",
            "org.testng.annotations.BeforeSuite",
            "org.testng.annotations.BeforeTest",
            "org.testng.annotations.AfterClass",
            "org.testng.annotations.AfterGroups",
            "org.testng.annotations.AfterMethod",
            "org.testng.annotations.AfterSuite",
            "org.testng.annotations.AfterTest"
    };

    @NonNls
    private static final String[] CONFIG_JAVADOC_TAGS = {
            "testng.configuration",
            "testng.before-class",
            "testng.before-groups",
            "testng.before-method",
            "testng.before-suite",
            "testng.before-test",
            "testng.after-class",
            "testng.after-groups",
            "testng.after-method",
            "testng.after-suite",
            "testng.after-test"
    };

    private static boolean hasConfig(PsiModifierListOwner element) {
        return hasConfig(element, CONFIG_ANNOTATIONS_FQN);
    }

    private static boolean hasConfig(
            PsiModifierListOwner element,
            String[] configAnnotationsFqn) {
        if(element instanceof PsiClass) {
            for(PsiMethod method : ((PsiClass) element).getAllMethods()) {
                if(isConfigMethod(method, configAnnotationsFqn)) {
                    return true;
                }
            }
        } else {
            if(!(element instanceof PsiMethod)) {
                return false;
            }
            return isConfigMethod((PsiMethod) element, configAnnotationsFqn);
        }
        return false;
    }

    private static boolean isConfigMethod(PsiMethod method, String[] configAnnotationsFqn) {
        for(String fqn : configAnnotationsFqn) {
            if(AnnotationUtil.isAnnotated(method, fqn, 0)) {
                return true;
            }
        }

        if(hasDocTagsSupport) {
            final PsiDocComment comment = method.getDocComment();
            if(comment != null) {
                for(String javadocTag : CONFIG_JAVADOC_TAGS) {
                    if(comment.findTagByName(javadocTag) != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean hasTest(PsiModifierListOwner element) {
        return CachedValuesManager.getCachedValue(element, () ->
                CachedValueProvider.Result.create(hasTest(element, true), PsiModificationTracker.MODIFICATION_COUNT));
    }

    private static boolean hasTest(PsiModifierListOwner element, boolean checkDisabled) {
        return hasTest(element, true, checkDisabled, hasDocTagsSupport);
    }

    private static boolean hasTest(
            PsiModifierListOwner element, boolean checkHierarchy, boolean checkDisabled, boolean checkJavadoc) {
        final PsiClass aClass;
        if(element instanceof PsiClass) {
            aClass = ((PsiClass) element);
        } else if(element instanceof PsiMethod) {
            aClass = ((PsiMethod) element).getContainingClass();
        } else {
            aClass = null;
        }
        if(aClass == null || !PsiClassUtil.isRunnableClass(aClass, true, false)) {
            return false;
        }

        //LanguageLevel effectiveLanguageLevel = element.getManager().getEffectiveLanguageLevel();
        //boolean is15 = effectiveLanguageLevel != LanguageLevel.JDK_1_4 && effectiveLanguageLevel != LanguageLevel.JDK_1_3;
        boolean hasAnnotation = AnnotationUtil.isAnnotated(element, TEST_ANNOTATION_FQN, checkHierarchy ? AnnotationUtil.CHECK_HIERARCHY : 0);
        if(hasAnnotation) {
            if(checkDisabled) {
                PsiAnnotation annotation = AnnotationUtil.findAnnotation(element, Collections.singleton(TEST_ANNOTATION_FQN), true);
                if(annotation != null) {
                    if(isDisabled(annotation)) {
                        return false;
                    }
                }
            }
            return true;
        }
        if(checkJavadoc && getTextJavaDoc((PsiDocCommentOwner) element) != null) {
            return true;
        }
        //now we check all methods for the test annotation
        if(element instanceof final PsiClass psiClass) {
            for(PsiMethod method : psiClass.getAllMethods()) {
                PsiAnnotation annotation = AnnotationUtil.findAnnotation(method, Collections.singleton(TEST_ANNOTATION_FQN), true);
                if(annotation != null) {
                    if(checkDisabled) {
                        if(isDisabled(annotation)) {
                            continue;
                        }
                    }
                    return true;
                }
                if(AnnotationUtil.isAnnotated(method, FACTORY_ANNOTATION_FQN, 0)) {
                    return true;
                }
                if(checkJavadoc && getTextJavaDoc(method) != null) {
                    return true;
                }
            }
            return false;
        } else {
            //even if it has a global test, we ignore non-public and static methods
            if(!element.hasModifierProperty(PsiModifier.PUBLIC) ||
                    element.hasModifierProperty(PsiModifier.STATIC)) {
                return false;
            }

            //if it's a method, we check if the class it's in has a global @Test annotation
            PsiClass psiClass = ((PsiMethod) element).getContainingClass();
            if(psiClass != null) {
                final PsiAnnotation annotation = checkHierarchy ? AnnotationUtil.findAnnotationInHierarchy(psiClass, Collections.singleton(TEST_ANNOTATION_FQN))
                        : AnnotationUtil.findAnnotation(psiClass, Collections.singleton(TEST_ANNOTATION_FQN), true);
                if(annotation != null) {
                    if(checkDisabled && isDisabled(annotation)) {
                        return false;
                    }
                    return !hasConfig(element);
                } else if(checkJavadoc && getTextJavaDoc(psiClass) != null) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isDisabled(PsiAnnotation annotation) {
        final PsiAnnotationMemberValue attributeValue = annotation.findDeclaredAttributeValue("enabled");
        return attributeValue != null && attributeValue.textMatches("false");
    }

    private static PsiDocTag getTextJavaDoc(@NotNull final PsiDocCommentOwner element) {
        final PsiDocComment docComment = element.getDocComment();
        if(docComment != null) {
            return docComment.findTagByName("testng.test");
        }
        return null;
    }

    public static boolean isTestNGClass(PsiClass psiClass) {
        return hasTest(psiClass);
    }
}