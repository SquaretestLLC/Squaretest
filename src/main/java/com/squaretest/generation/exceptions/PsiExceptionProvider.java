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
package com.squaretest.generation.exceptions;

import com.intellij.codeInsight.javadoc.JavaDocUtil;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiReferenceList;
import com.intellij.psi.PsiType;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.javadoc.PsiDocTag;
import com.intellij.util.ArrayUtil;
import com.squaretest.generation.CompiledUtils;
import com.squaretest.generation.SQExceptionTagInfo;
import com.squaretest.generation.defaulttypes.DefaultDiInfoHolder;
import com.squaretest.generation.defaulttypes.TypeCreatorUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PsiExceptionProvider {

    private final Project project;
    private final JavaPsiFacade psiFacade;
    private final DefaultDiInfoHolder defaultDiInfoHolder;
    private final Map<PsiMethod, ExceptionInfo> cachedValues;

    public PsiExceptionProvider(
            final Project project,
            final JavaPsiFacade psiFacade, final DefaultDiInfoHolder defaultDiInfoHolder) {
        this.project = project;
        this.psiFacade = psiFacade;
        this.defaultDiInfoHolder = defaultDiInfoHolder;
        this.cachedValues = new IdentityHashMap<>();
    }

    public List<PsiType> getDeclaredExceptions(final PsiMethod method) {
        return getOrComputeExceptionInfo(method).getDeclaredExceptions();
    }

    public List<PsiType> getJavadocExceptions(final PsiMethod method) {
        return getOrComputeExceptionInfo(method).getJavadocExceptions();
    }

    public List<PsiType> getUndeclaredExceptions(final PsiMethod method) {
        return getOrComputeExceptionInfo(method).getUndeclaredExceptions();
    }

    public List<PsiType> getAllExceptions(final PsiMethod method) {
        return getOrComputeExceptionInfo(method).getAllExceptions();
    }

    @NotNull
    private ExceptionInfo getOrComputeExceptionInfo(final PsiMethod method) {
        return cachedValues.computeIfAbsent(method,
                psiMethod -> new ExceptionInfo(getDeclaredExceptionsImpl(psiMethod),
                        getJavadocExceptionsImpl(psiMethod),
                        getUndeclaredExceptionsImpl(psiMethod)));
    }

    private List<PsiType> getDeclaredExceptionsImpl(final PsiMethod method) {
        final PsiReferenceList throwsList = method.getThrowsList();
        final List<PsiType> declaredExceptionsRet = new ArrayList<>(throwsList.getReferencedTypes().length);
        declaredExceptionsRet.addAll(Arrays.asList(throwsList.getReferencedTypes()));
        return declaredExceptionsRet;
    }

    private List<PsiType> getJavadocExceptionsImpl(final PsiMethod method) {
        final List<PsiMethod> methodAndSupers = getMethodAndSupersWithSources(method);
        for(final PsiMethod methodToUse : methodAndSupers) {
            final PsiDocComment psiDocComment = methodToUse.getDocComment();
            if(psiDocComment == null) {
                continue;
            }
            final PsiDocTag[] throwsTags = getThrowsTags(psiDocComment);
            if(throwsTags.length != 0) {
                return getJavadocExceptionsInThrowsTags(throwsTags);
            }
        }
        return Collections.emptyList();
    }

    private List<PsiType> getUndeclaredExceptionsImpl(final PsiMethod method) {
        final String canonicalKey = TypeCreatorUtil.getCanonicalKey(method);
        final List<String> undeclaredExceptions = defaultDiInfoHolder.getUndeclaredExceptions(canonicalKey);
        if(undeclaredExceptions == null || undeclaredExceptions.isEmpty()) {
            return Collections.emptyList();
        }
        final PsiElementFactory elementFactory = JavaPsiFacade.getInstance(project).getElementFactory();
        final List<PsiType> ret = new ArrayList<>(undeclaredExceptions.size());
        for(final String exceptionCanonicalName : undeclaredExceptions) {
            final PsiClass exceptionClass = psiFacade.findClass(exceptionCanonicalName, method.getResolveScope());
            if(exceptionClass == null || exceptionClass.getQualifiedName() == null || exceptionClass.getName() == null) {
                continue;
            }
            final PsiClassType exceptionType = elementFactory.createType(exceptionClass);
            ret.add(exceptionType);
        }
        return ret;
    }

    private List<PsiType> getJavadocExceptionsInThrowsTags(final PsiDocTag[] throwsTags) {
        final Map<String, PsiType> canonicalNameToExceptionsMap = new LinkedHashMap<>();
        for(final PsiDocTag throwsTag : throwsTags) {
            final PsiClass throwsClass = JavaDocUtil.resolveClassInTagValue(throwsTag.getValueElement());
            if(throwsClass != null && throwsClass.getQualifiedName() != null) {
                final PsiElementFactory elementFactory = JavaPsiFacade.getInstance(project).getElementFactory();
                final PsiClassType throwsType = elementFactory.createType(throwsClass);
                if(!canonicalNameToExceptionsMap.containsKey(throwsClass.getQualifiedName())) {
                    if(SQExceptionTagInfo.isBroken(throwsTag.getValueElement())) {
                        continue;
                    }
                    canonicalNameToExceptionsMap.put(throwsClass.getQualifiedName(), throwsType);
                }
            }
        }
        return new ArrayList<>(canonicalNameToExceptionsMap.values());
    }

    private List<PsiMethod> getMethodAndSupersWithSources(final PsiMethod method) {
        final List<PsiMethod> ret = new ArrayList<>();
        ret.add(CompiledUtils.getMethodWithSourceCode(method));
        final PsiMethod[] supers = ret.get(0).findSuperMethods();
        // Add the classes first. These should be in the order they're encountered.
        for(final PsiMethod superMethod : supers) {
            final PsiClass containingClass = superMethod.getContainingClass();
            if(containingClass == null) {
                // This shouldn't happen.
                continue;
            }
            if(!containingClass.isInterface()) {
                ret.add(CompiledUtils.getMethodWithSourceCode(superMethod));
            }
        }

        // Collect the super interfaces.
        List<PsiMethod> superInterfaces = new ArrayList<>();
        for(final PsiMethod superMethod : supers) {
            final PsiClass containingClass = superMethod.getContainingClass();
            if(containingClass == null) {
                // This shouldn't happen.
                continue;
            }
            if(containingClass.isInterface()) {
                superInterfaces.add(CompiledUtils.getMethodWithSourceCode(superMethod));
            }
        }
        // Sort them alphabetically to ensure we have deterministic output.
        superInterfaces.sort(Comparator.comparing(x -> {
            final PsiClass containingClass = x.getContainingClass();
            if(containingClass == null) {
                return x.getName();
            }
            return containingClass.getName();
        }));

        // Add the interfaces.
        ret.addAll(superInterfaces);
        return ret;
    }

    private static PsiDocTag[] getThrowsTags(PsiDocComment comment) {
        if(comment == null) {
            return PsiDocTag.EMPTY_ARRAY;
        }
        final PsiDocTag[] tags1 = comment.findTagsByName("throws");
        final PsiDocTag[] tags2 = comment.findTagsByName("exception");
        return ArrayUtil.mergeArrays(tags1, tags2);
    }
}
