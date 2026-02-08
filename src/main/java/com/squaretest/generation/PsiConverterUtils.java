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

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiModifierListOwner;
import com.squaretest.template.AccessModifier;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Set;

public class PsiConverterUtils {
    public static AccessModifier modifierFromPsi(@NotNull final PsiModifierListOwner owner) {
        if(owner.hasModifierProperty(PsiModifier.PACKAGE_LOCAL)) {
            return AccessModifier.PackageLocal;
        } else if(owner.hasModifierProperty(PsiModifier.PUBLIC)) {
            return AccessModifier.Public;
        } else if(owner.hasModifierProperty(PsiModifier.PROTECTED)) {
            return AccessModifier.Protected;
        } else {
            return AccessModifier.Private;
        }
    }

    public static boolean isOverload(@NotNull final PsiMethod psiMethod) {
        final PsiClass containingClass = psiMethod.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        return containingClass.findMethodsByName(psiMethod.getName(), false).length > 1;
    }

    static boolean isSpringController(final Set<PsiClass> sourceAndSupers) {
        for(final PsiClass psiClass : sourceAndSupers) {
            if(Arrays.stream(psiClass.getAnnotations()).anyMatch(x -> StringUtils.equalsAny(x.getQualifiedName(), "org.springframework.stereotype.Controller", "org.springframework.web.bind.annotation.RestController"))) {
                return true;
            }
        }
        return false;
    }
}
