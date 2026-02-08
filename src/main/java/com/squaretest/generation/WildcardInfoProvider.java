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

import com.intellij.psi.PsiCapturedWildcardType;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiWildcardType;
import com.intellij.psi.search.GlobalSearchScope;
import com.squaretest.generation.defaulttypes.WildcardInfo;
import org.jetbrains.annotations.NotNull;

import static com.intellij.psi.util.TypeConversionUtil.isNullType;

public class WildcardInfoProvider {
    private final PsiType stringType;

    public WildcardInfoProvider(final PsiClass topLevelSourceClass) {
        final GlobalSearchScope sourceClassResolveScope = topLevelSourceClass.getResolveScope();
        this.stringType = PsiType.getJavaLangString(topLevelSourceClass.getManager(), sourceClassResolveScope);
    }

    @NotNull
    public WildcardInfo getWildcardInfoWithReplacement(final PsiType paramValue) {
        if(paramValue instanceof PsiWildcardType) {
            // If the paramValue is type: "? extends XXX", set paramValue to type: XXX.
            return processWildcardType((PsiWildcardType) paramValue);
        } else if(paramValue instanceof PsiCapturedWildcardType) {
            return processCapturedWildcardType((PsiCapturedWildcardType) paramValue);
        } else {
            return new WildcardInfo(false, paramValue);
        }
    }

    private WildcardInfo processWildcardType(final PsiWildcardType wildcardType) {
        if(wildcardType.isExtends()) {
            return new WildcardInfo(true, wildcardType.getExtendsBound());
        }
        if(wildcardType.isSuper()) {
            return new WildcardInfo(true, wildcardType.getSuperBound());
        }
        if(!wildcardType.isBounded()) {
            return new WildcardInfo(true, stringType);
        }
        return new WildcardInfo(true, wildcardType);
    }

    private WildcardInfo processCapturedWildcardType(final PsiCapturedWildcardType paramValue) {
        final PsiType upperBound = paramValue.getUpperBound();
        final PsiType lowerBound = paramValue.getLowerBound();
        if(!isNullType(upperBound)) {
            return new WildcardInfo(true, upperBound);
        }
        if(!isNullType(lowerBound)) {
            return new WildcardInfo(true, lowerBound);
        }
        return processWildcardType(paramValue.getWildcard());
    }
}
