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

import com.squaretest.generation.abstractmethods.AbstractClassInfo;
import com.squaretest.template.api.Api;
import com.squaretest.template.api.Api.Constructor;
import com.squaretest.template.api.Api.Method;
import com.squaretest.template.api.Api.Variable;
import com.squaretest.template.impl.MethodImpl;
import com.squaretest.template.impl.SourceClassImpl;
import com.squaretest.template.impl.VariableImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImportListGenerator {

    /**
     * Collects all imports required by {@link SourceClassImpl} and its components (methods, fields, etc.).
     *
     * @param sourceClass       the class for which to collect import statements
     * @param abstractClassInfo the {@link AbstractClassInfo} for the corresponding class; this is a separate parameter,
     *                          because we don't want to expose the {@link SourceClassImpl ClassUnderTest's} {@link AbstractClassInfo}
     *                          member to the velocity template.
     * @return the {@link Set Set<String>} containing all import statements required.
     */
    public Set<String> createImportStatementListForAllDependencies(
            @NotNull final Api.SourceClass sourceClass,
            @NotNull final AbstractClassInfo abstractClassInfo) {
        // Store the names in a set to avoid duplicates.
        final Set<String> canonicalNameSet = new HashSet<>();

        // Add all canonical names from constructor fields.
        final List<Constructor> constructors = sourceClass.getConstructors();
        addCanonicalNamesOfTypesInMethods(constructors, canonicalNameSet);

        // Add all canonical names from method fields
        final List<Method> methods = new ArrayList<>(sourceClass.getPublicInstanceMethods());
        methods.addAll(sourceClass.getPackageLocalInstanceMethods());
        methods.addAll(sourceClass.getProtectedInstanceMethods());
        addCanonicalNamesOfTypesInMethods(methods, canonicalNameSet);
        addCanonicalNamesOfTypesInField(sourceClass.getFields(), canonicalNameSet);
        canonicalNameSet.addAll(abstractClassInfo.getImportsRequiredForStubMethodImplementations());
        return createImportStatementListFromCanonicalNameSet(canonicalNameSet);
    }

    private Set<String> createImportStatementListFromCanonicalNameSet(@NotNull final Set<String> canonicalNameSet) {
        final Set<String> importList = new HashSet<>();
        final String importFormatString = "import %s;";
        for(final String canonicalName : canonicalNameSet) {
            importList.add(String.format(importFormatString, canonicalName));
        }
        return importList;
    }

    private static void addCanonicalNamesOfTypesInMethods(
            final List<? extends Method> constructors, final Set<String> currentNameSet) {
        for(final Method method : constructors) {
            addCanonicalNamesOfTypesInField(method.getParameters(), currentNameSet);
            final List<String> methodReturnTypeNames = ((MethodImpl) method).getCanonicalNamesRequiredForReturnTypes()
                    .stream().filter(name -> !name.startsWith("java.lang")).toList();
            currentNameSet.addAll(methodReturnTypeNames);
        }
    }

    private static void addCanonicalNamesOfTypesInField(
            final List<? extends Variable> variables, final Set<String> currentNameSet) {
        for(final Variable variable : variables) {
            for(final String canonicalName : ((VariableImpl) variable).getCanonicalNamesRequiredForType()) {
                if(!canonicalName.startsWith("java.lang")) {
                    currentNameSet.add(canonicalName);
                }
            }
        }
    }
}
