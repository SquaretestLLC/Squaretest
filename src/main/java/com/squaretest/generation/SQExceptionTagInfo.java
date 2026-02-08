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

import com.intellij.psi.CommonClassNames;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaCodeReferenceElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.javadoc.PsiDocTagValue;
import com.intellij.psi.util.PsiTreeUtil;

import static com.intellij.codeInsight.javadoc.JavaDocUtil.resolveClassInTagValue;

/**
 * Copied from com.intellij.psi.impl.source.javadoc.ExceptionTagInfo.
 */
public class SQExceptionTagInfo {

    public enum Error {
        NotThrowable,
        CheckedExceptionNotDeclared,
        InvalidJavadocTag
    }

    public static boolean isBroken(PsiDocTagValue value) {
        final Error error = checkForJavadocErrors(value);
        return error == Error.CheckedExceptionNotDeclared || error == Error.NotThrowable;
    }

    /**
     * Copied from com.intellij.psi.impl.source.javadoc.ClassReferenceTagInfo#checkTagValue(com.intellij.psi.javadoc.PsiDocTagValue).
     * Modified to return Error enum instead of String.
     */
    private static Error superCheckTagValue(PsiDocTagValue value) {
        PsiElement refHolder = value != null ? value.getFirstChild() : null;
        if(refHolder == null) {
            return Error.InvalidJavadocTag;
        }

        PsiElement refElement = refHolder.getFirstChild();
        if(!(refElement instanceof PsiJavaCodeReferenceElement)) {
            return Error.InvalidJavadocTag;
        }
        return null;
    }

    /**
     * Copied from com.intellij.psi.impl.source.javadoc.ExceptionTagInfo#checkTagValue(com.intellij.psi.javadoc.PsiDocTagValue).
     * Modified to return Error enum instead of String.
     */
    private static Error checkForJavadocErrors(PsiDocTagValue value) {
        Error result = superCheckTagValue(value);
        if(result != null) {
            return result;
        }
        PsiClass exceptionClass = resolveClassInTagValue(value);
        if(exceptionClass == null) {
            return null;
        }

        PsiClass throwable = JavaPsiFacade.getInstance(value.getProject()).findClass(CommonClassNames.JAVA_LANG_THROWABLE, value.getResolveScope());
        if(throwable != null && !exceptionClass.equals(throwable) && !exceptionClass.isInheritor(throwable, true)) {
            // Class in @throws is not Throwable.
            return Error.NotThrowable;
        }

        PsiClass runtimeException = JavaPsiFacade.getInstance(value.getProject()).findClass(CommonClassNames.JAVA_LANG_RUNTIME_EXCEPTION, value.getResolveScope());
        if(runtimeException != null && (exceptionClass.isInheritor(runtimeException, true) || exceptionClass.equals(runtimeException))) {
            return null;
        }

        PsiClass errorException = JavaPsiFacade.getInstance(value.getProject()).findClass(CommonClassNames.JAVA_LANG_ERROR, value.getResolveScope());
        if(errorException != null && (exceptionClass.isInheritor(errorException, true) || exceptionClass.equals(errorException))) {
            return null;
        }

        PsiMethod method = PsiTreeUtil.getParentOfType(value, PsiMethod.class);
        if(method == null) {
            return null;
        }

        for(PsiClassType reference : method.getThrowsList().getReferencedTypes()) {
            PsiClass psiClass = reference.resolve();
            if(psiClass != null && (exceptionClass.isInheritor(psiClass, true) || exceptionClass.equals(psiClass))) {
                return null;
            }
        }

        return Error.CheckedExceptionNotDeclared;
    }
}
