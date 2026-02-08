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
package com.squaretest.template.impl;

import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.codeStyle.SuggestedNameInfo;
import com.intellij.psi.codeStyle.VariableKind;
import com.squaretest.template.api.Api;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static com.squaretest.generation.SuggestedNameProvider.cleanUpSuggestedName;

public class CodeStyleUtilsImpl implements Api.CodeStyleUtils {

    @NotNull
    private final JavaCodeStyleManager javaCodeStyleManager;
    private final Map<String, Integer> suggestedNameToNumberOfLocalVariableInstancesInMethod;
    private final Map<String, Integer> suggestedNameToNumberOfMembers;

    public CodeStyleUtilsImpl(@NotNull final JavaCodeStyleManager javaCodeStyleManager) {
        this.javaCodeStyleManager = javaCodeStyleManager;
        this.suggestedNameToNumberOfLocalVariableInstancesInMethod = new HashMap<>();
        this.suggestedNameToNumberOfMembers = new HashMap<>();
    }

    @Override
    public void beginMethodScope() {
        this.suggestedNameToNumberOfLocalVariableInstancesInMethod.clear();
        this.suggestedNameToNumberOfLocalVariableInstancesInMethod.put("result", 1);
    }

    @Override
    public void endMethodScope() {
        this.suggestedNameToNumberOfLocalVariableInstancesInMethod.clear();
        this.suggestedNameToNumberOfLocalVariableInstancesInMethod.put("result", 1);
    }

    @Override
    @NotNull
    public String suggestMemberName(final String className) {
        if(className == null) {
            return randomVarName();
        }
        final SuggestedNameInfo suggestedNameInfo = javaCodeStyleManager.suggestVariableName(VariableKind.FIELD, className, null, null);
        if(suggestedNameInfo.names.length > 0) {
            return cleanUpSuggestedName(suggestedNameInfo.names[0]);
        }
        return StringUtils.uncapitalize(className);
    }

    @Override
    @NotNull
    public String suggestLocalFieldName(final String className) {
        if(className == null) {
            return randomVarName();
        }
        final SuggestedNameInfo suggestedNameInfo = javaCodeStyleManager.suggestVariableName(VariableKind.LOCAL_VARIABLE, className, null, null);
        if(suggestedNameInfo.names.length > 0) {
            return cleanUpSuggestedName(suggestedNameInfo.names[0]);
        }
        return StringUtils.uncapitalize(className);
    }

    private static String randomVarName() {
        return "var" + RandomStringUtils.secure().nextNumeric(8);
    }

    @NotNull
    private String updateLocalFieldNameWithMethodScope(@NotNull final String variableName) {
        final Integer counter = suggestedNameToNumberOfLocalVariableInstancesInMethod.get(variableName);
        if(counter == null) {
            suggestedNameToNumberOfLocalVariableInstancesInMethod.put(variableName, 1);
            return variableName;
        } else {
            final String newVariableName = variableName + counter;
            suggestedNameToNumberOfLocalVariableInstancesInMethod.put(variableName, counter + 1);
            return newVariableName;
        }
    }

    private String updateMemberFieldNameWithClassScope(final String variableName) {
        final Integer counter = suggestedNameToNumberOfMembers.get(variableName);
        if(counter == null) {
            suggestedNameToNumberOfMembers.put(variableName, 1);
            return variableName;
        } else {
            final String newVariableName = variableName + counter;
            suggestedNameToNumberOfMembers.put(variableName, counter + 1);
            return newVariableName;
        }
    }

    @Override
    public void updateLocalFieldNameWithMethodScope(final Api.Variable variable) {
        if(variable == null) {
            return;
        }
        final String baseName = getBaseName(variable);
        final String fieldNameWithoutNumberSuffix = getNameWithoutNumberSuffix(baseName, variable.getTestClassLocalFieldName());
        final String newFieldName = updateLocalFieldNameWithMethodScope(fieldNameWithoutNumberSuffix);
        variable.setTestClassLocalFieldName(newFieldName);
    }

    @Override
    public void updateMemberFieldNameWithClassScope(final Api.Variable variable) {
        if(variable == null) {
            return;
        }
        final String baseName = variable.getTestClassMemberName();
        final String fieldNameWithoutNumberSuffix = getNameWithoutNumberSuffix(baseName, variable.getTestClassMemberName());
        final String newFieldName = updateMemberFieldNameWithClassScope(fieldNameWithoutNumberSuffix);
        variable.setTestClassMemberName(newFieldName);
    }

    private String getBaseName(final Api.Variable variable) {
        final String defaultBaseName = variable.getDeclaredNameWithoutPrefix();
        final Api.Method containingMethod = variable.getContainingMethod();
        if(containingMethod == null) {
            return defaultBaseName;
        }
        if(!containingMethod.isSetter()) {
            return defaultBaseName;
        }
        final Api.ClassMember targetField = containingMethod.getTargetField();
        if(targetField == null) {
            return defaultBaseName;
        }
        return StringUtils.uncapitalize(targetField.getDeclaredNameWithoutPrefix());
    }

    @Override
    public void updateLocalFieldNameWithMethodScope(final Api.Type type) {
        if(type == null) {
            return;
        }
        final String fieldNameWithoutNumberSuffix = getNameWithoutNumberSuffix(type.getName(), type.getTestClassLocalFieldName());
        final String newFieldName = updateLocalFieldNameWithMethodScope(fieldNameWithoutNumberSuffix);
        type.setTestClassLocalFieldName(newFieldName);
    }

    @NotNull
    static String getNameWithoutNumberSuffix(final String typeNameOrDeclaredName, final String localFieldName) {
        final String numberAtEndOfLocalFieldName = getNumberAtEnd(localFieldName);
        if(numberAtEndOfLocalFieldName == null) {
            return localFieldName;
        }

        final String numberAtEndOfTypeName = getNumberAtEnd(typeNameOrDeclaredName);
        if(numberAtEndOfTypeName != null) {
            if(numberAtEndOfLocalFieldName.equals(numberAtEndOfTypeName)) {
                // The number at the end of the local field name is just the number from the type name. There is no suffix to
                // remove.
                return localFieldName;
            }
            // The type name includes a number.
            // typeNameOrDeclaredName = IEEE1337
            // numberAtEndOfTypeName  = 1337.
            // numberAtEndOfLocalFieldName = 13371
            if(numberAtEndOfLocalFieldName.startsWith(numberAtEndOfTypeName)) {
                final String numberSuffixToRemove = StringUtils.removeStart(numberAtEndOfLocalFieldName, numberAtEndOfTypeName);
                return StringUtils.removeEnd(localFieldName, numberSuffixToRemove);
            }
        }

        // Either the type name does not end with a number, or it does, but the localFieldName does not end with the number from
        // the type name. Just remove the number from the end of the local field name.
        return StringUtils.removeEnd(localFieldName, numberAtEndOfLocalFieldName);
    }

    @Nullable
    static String getNumberAtEnd(final String name) {
        int i = name.length();
        while(i > 0 && Character.isDigit(name.charAt(i - 1))) {
            i--;
        }
        final String numberAtEnd = name.substring(i);
        if(StringUtils.isNotBlank(numberAtEnd)) {
            return numberAtEnd;
        }
        return null;
    }

    public void clearNameConflictMaps() {
        suggestedNameToNumberOfMembers.clear();
        suggestedNameToNumberOfLocalVariableInstancesInMethod.clear();
    }
}
