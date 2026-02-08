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
package com.squaretest.generation.dependencyinteraction;

import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiParameterList;

public class SerializationUtils {

    static String serializeMethod(final PsiMethod psiMethod) {
        return String.format("%s(%s)", psiMethod.getName(), paramsList(psiMethod));
    }

    static String paramsList(final PsiMethod psiMethod) {
        final StringBuilder ret = new StringBuilder();
        final PsiParameterList paramsList = psiMethod.getParameterList();
        for(final PsiParameter param : paramsList.getParameters()) {
            ret.append(param.getName()).append(", ");
        }
        final String returnString = ret.toString();
        if(returnString.endsWith(", ")) {
            return returnString.substring(0, returnString.length() - 2);
        }
        return returnString;
    }
}
