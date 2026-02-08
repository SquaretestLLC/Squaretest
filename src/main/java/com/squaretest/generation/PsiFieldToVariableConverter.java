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

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiVariable;
import com.squaretest.annotations.AnnotationCreator;
import com.squaretest.generation.defaulttypes.TypeCreator;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.TypeImpl;
import com.squaretest.template.impl.VariableImpl;
import org.jetbrains.annotations.NotNull;

public class PsiFieldToVariableConverter {

    private final MemberFieldPrefixRemover memberFieldPrefixRemover;
    private final SuggestedNameProvider suggestedNameProvider;
    private final TypeCreator typeCreator;
    private final AnnotationCreator annotationCreator;

    public PsiFieldToVariableConverter(
            final MemberFieldPrefixRemover memberFieldPrefixRemover, final SuggestedNameProvider suggestedNameProvider,
            final TypeCreator typeCreator, final AnnotationCreator annotationCreator) {
        this.memberFieldPrefixRemover = memberFieldPrefixRemover;
        this.suggestedNameProvider = suggestedNameProvider;
        this.typeCreator = typeCreator;
        this.annotationCreator = annotationCreator;
    }

    public VariableImpl createFieldFromPsiVariable(
            @NotNull final PsiVariable fieldOrParameter, final PsiElement source,
            final String parameterName, boolean isUsed, final InitStrategy initStrategy) {
        final PsiType parameterType = fieldOrParameter.getType();
        final boolean isFinal = fieldOrParameter.hasModifierProperty(PsiModifier.FINAL);
        final boolean isStatic = fieldOrParameter.hasModifierProperty(PsiModifier.STATIC);
        // parameterName will always be non-null; it is a formal parameter in a method declaration.
        final String declaredNameWithoutPrefix = memberFieldPrefixRemover.removePrefix(parameterName);

        // Set the member name and local field names.
        final String testClassLocalFieldName = suggestedNameProvider.suggestLocalFieldName(fieldOrParameter);
        final String testClassMemberName = suggestedNameProvider.suggestMemberFieldName(fieldOrParameter);

        // Create the type.
        final TypeImpl type = typeCreator.createTypeForFormalParameter(
                parameterName, parameterType, initStrategy, source);

        // Default to storing args in references (dependencies, method parameters, etc).
        boolean shouldStoreInReference = true;
        if(type.isSimple()) {
            shouldStoreInReference = false;
        }
        final Api.FluentList<Api.Annotation> annotationCanonicalNames = annotationCreator.createAnnotations(fieldOrParameter);
        // Return the field.
        return new VariableImpl(type,
                parameterName,
                declaredNameWithoutPrefix, isUsed, annotationCanonicalNames, testClassMemberName, testClassLocalFieldName, isFinal, isStatic, shouldStoreInReference);
    }
}
