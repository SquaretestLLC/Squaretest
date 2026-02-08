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

import com.intellij.psi.PsiField;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiVariable;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.codeStyle.SuggestedNameInfo;
import com.intellij.psi.codeStyle.VariableKind;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class SuggestedNameProvider {
    @NotNull
    private final JavaCodeStyleManager javaCodeStyleManager;
    @NotNull
    private final MemberFieldPrefixRemover memberFieldPrefixRemover;

    public SuggestedNameProvider(
            @NotNull final JavaCodeStyleManager javaCodeStyleManager,
            @NotNull final MemberFieldPrefixRemover memberFieldPrefixRemover) {
        this.javaCodeStyleManager = javaCodeStyleManager;
        this.memberFieldPrefixRemover = memberFieldPrefixRemover;
    }

    public String suggestLocalFieldName(@NotNull final PsiVariable psiVariable) {
        if(psiVariable instanceof PsiField) {
            // This is a field. we need to get the local variable equivalent.
            final SuggestedNameInfo suggestedNameInfo = javaCodeStyleManager.suggestVariableName(VariableKind.LOCAL_VARIABLE, psiVariable.getName(), null, null);
            if(suggestedNameInfo.names.length > 0) {
                return cleanUpSuggestedName(suggestedNameInfo.names[0]);
            }
            return memberFieldPrefixRemover.removePrefix(psiVariable.getName());
        } else if(psiVariable instanceof PsiParameter) {
            // We need a local variable for the parameter; just use its name.
            return psiVariable.getName();
        }
        return psiVariable.getName();
    }

    public String suggestMemberFieldName(@NotNull final PsiVariable psiVariable) {
        if(psiVariable instanceof PsiField) {
            // The field is already a member; just return it.
            return psiVariable.getName();
        } else if(psiVariable instanceof PsiParameter) {
            // This is a parameter. We need to get the field equivalent.
            final SuggestedNameInfo suggestedNameInfo = javaCodeStyleManager.suggestVariableName(VariableKind.FIELD, psiVariable.getName(), null, null);
            if(suggestedNameInfo.names.length > 0) {
                return cleanUpSuggestedName(suggestedNameInfo.names[0]);
            }
            return psiVariable.getName();
        }
        return psiVariable.getName();
    }

    public String suggestMemberFieldName(@NotNull final String className) {
        final SuggestedNameInfo suggestedNameInfo = javaCodeStyleManager.suggestVariableName(VariableKind.FIELD, className, null, null);
        if(suggestedNameInfo.names.length > 0) {
            // Some of the AWS types have suggested names like: getObjectResponseResponseBytes.
            return cleanUpSuggestedName(suggestedNameInfo.names[0]);
        }
        return StringUtils.uncapitalize(className);
    }

    public String suggestLocalFieldName(final String className) {
        final SuggestedNameInfo suggestedNameInfo = javaCodeStyleManager.suggestVariableName(VariableKind.LOCAL_VARIABLE, className, null, null);
        if(suggestedNameInfo.names.length > 0) {
            return cleanUpSuggestedName(suggestedNameInfo.names[0]);
        }
        return StringUtils.uncapitalize(className);
    }

    public String suggestMemberFieldName(final String name, @NotNull final PsiType psiType) {
        final SuggestedNameInfo suggestedNameInfo = javaCodeStyleManager.suggestVariableName(VariableKind.FIELD, null, null, psiType);
        if(suggestedNameInfo.names.length > 0) {
            return cleanUpSuggestedName(suggestedNameInfo.names[0]);
        }
        return StringUtils.uncapitalize(name);
    }

    public String suggestLocalFieldName(final String name, @NotNull final PsiType psiType) {
        final SuggestedNameInfo suggestedNameInfo = javaCodeStyleManager.suggestVariableName(VariableKind.LOCAL_VARIABLE, null, null, psiType);
        if(suggestedNameInfo.names.length > 0) {
            return cleanUpSuggestedName(suggestedNameInfo.names[0]);
        }
        return StringUtils.uncapitalize(name);
    }

    public static String cleanUpSuggestedName(final String name) {
        return StringUtils.replace(name, "ResponseResponse", "Response");
    }
}
