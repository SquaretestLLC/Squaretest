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
package com.squaretest.generation.defaulttypes;

import com.intellij.psi.PsiClass;
import com.intellij.psi.util.InheritanceUtil;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.FluentListImpl;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SuperTypesProvider {
    @NotNull
    private final Map<String, Api.FluentList<String>> canonicalNameToSuperTypeNamesMap;
    public static final Api.FluentList<String> DefaultSuperTypes = new FluentListImpl<>(List.of("java.lang.Object"));
    public static final Api.FluentList<String> NoSuperTypes = new FluentListImpl<>(0);

    public SuperTypesProvider() {
        canonicalNameToSuperTypeNamesMap = new HashMap<>();
    }

    public Api.FluentList<String> getSuperTypes(@Nullable final PsiClass psiClass) {
        if(psiClass == null || psiClass.getQualifiedName() == null) {
            return DefaultSuperTypes;
        }
        final String key = psiClass.getQualifiedName();
        return canonicalNameToSuperTypeNamesMap.computeIfAbsent(key, x -> computeSuperTypes(psiClass));
    }

    public boolean isAny(@Nullable final PsiClass psiClass, final String... typesToSearchFor) {
        if(typesToSearchFor == null || typesToSearchFor.length == 0) {
            return false;
        }
        return isAny(psiClass, Arrays.asList(typesToSearchFor));
    }

    public boolean isAny(final PsiClass psiClass, final List<String> typesToSearchFor) {
        if(psiClass == null || psiClass.getQualifiedName() == null) {
            return false;
        }

        if(equalsAny(psiClass.getQualifiedName(), typesToSearchFor)) {
            return true;
        }

        final Api.FluentList<String> superTypes = getSuperTypes(psiClass);
        return superTypes.stream().anyMatch(typesToSearchFor::contains);
    }

    private static Api.FluentList<String> computeSuperTypes(final PsiClass psiClass) {
        final Set<PsiClass> superClasses = new LinkedHashSet<>();
        InheritanceUtil.getSuperClasses(psiClass, superClasses, true);
        final FluentListImpl<String> ret = new FluentListImpl<>(superClasses.size());
        for(final PsiClass superType : superClasses) {
            String qualifiedName = superType.getQualifiedName();
            if(qualifiedName != null) {
                ret.add(qualifiedName);
            }
        }
        return ret;
    }

    public static boolean equalsAny(final CharSequence string, final List<String> searchStrings) {
        if(searchStrings == null) {
            return false;
        }
        for(final CharSequence next : searchStrings) {
            if(StringUtils.equals(string, next)) {
                return true;
            }
        }
        return false;
    }
}
