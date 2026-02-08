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
package com.squaretest.generation.existingtest.main;

import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiVariable;
import com.squaretest.generation.existingtest.common.Constants;

public class Utils {
    static boolean isMocked(final PsiVariable psiLocalVariable) {
        // Check the initializer to see if it is mock(Foo.class).
        final PsiExpression initializer = psiLocalVariable.getInitializer();
        if(initializer == null) {
            return false;
        }
        if(!(initializer instanceof final PsiMethodCallExpression methodCallExpression)) {
            return false;
        }
        final PsiMethod calledMethod = methodCallExpression.resolveMethod();
        if(calledMethod == null) {
            return false;
        }
        return calledMethod.hasModifierProperty(PsiModifier.STATIC) && calledMethod.getName().equals(Constants.MockMethodName);
    }
}
