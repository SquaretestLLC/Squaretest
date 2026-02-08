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

import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiSubstitutor;
import com.intellij.psi.PsiType;
import com.intellij.psi.util.InheritanceUtil;
import com.squaretest.generation.defaulttypes.TypeCreator;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.ExceptionImpl;
import com.squaretest.template.impl.FluentListImpl;
import com.squaretest.template.impl.TypeImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExceptionCreator {
    @NotNull
    private final PsiClass sourceClass;
    @NotNull
    private final JavaPsiFacade javaPsiFacade;
    @NotNull
    private final TypeCreator typeCreator;

    public ExceptionCreator(
            @NotNull final PsiClass sourceClass,
            @NotNull final JavaPsiFacade javaPsiFacade,
            @NotNull final TypeCreator typeCreator) {
        this.sourceClass = sourceClass;
        this.javaPsiFacade = javaPsiFacade;
        this.typeCreator = typeCreator;
    }

    public Api.FluentList<Api.Exception> createExceptionTypes(@NotNull final List<PsiType> psiTypes) {
        final Api.FluentList<Api.Exception> ret = new FluentListImpl<>(psiTypes.size());
        for(final PsiType psiType : psiTypes) {
            ret.add(createExceptionType(psiType));
        }
        return ret;
    }

    public Api.Exception createExceptionType(@NotNull final PsiType psiType) {
        final TypeImpl ourType = this.typeCreator.createTypeForFormalParameter("value", psiType, InitStrategy.Default, null);
        final boolean isChecked = !InheritanceUtil.isInheritor(psiType, "java.lang.RuntimeException");
        return new ExceptionImpl(ourType, isChecked);
    }

    @Nullable
    public Api.Exception createExceptionType(final String exceptionCanonicalName) {
        // Resolve the class.
        // Use com.squaretest.generation.PsiToTemplateDataConverter.createTypeForClass.
        final PsiClass exceptionClass = CompiledUtils.getClassWithSourceCode(javaPsiFacade.findClass(exceptionCanonicalName, sourceClass.getResolveScope()));
        if(exceptionClass == null) {
            return null;
        }
        final PsiType exceptionType = javaPsiFacade.getElementFactory().createType(exceptionClass, PsiSubstitutor.EMPTY);
        return createExceptionType(exceptionType);
    }
}
