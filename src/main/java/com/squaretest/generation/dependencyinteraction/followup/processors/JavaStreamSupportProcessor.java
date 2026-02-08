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
package com.squaretest.generation.dependencyinteraction.followup.processors;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.squaretest.generation.dependencyinteraction.followup.RValueInfo;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.getArgsThatDoNotContainElement;
import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.getIndexOfElementThatContainsElement;

public class JavaStreamSupportProcessor implements MethodCallProcessor {
    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithArgThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final PsiElement originalStartingElement,
            @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!isStreamSupport(containingClass)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        final PsiExpression[] args = currentMethodCall.getArgumentList().getExpressions();
        final int index = getIndexOfElementThatContainsElement(args, originalStartingElement);
        if(StringUtils.equalsAny(methodName, "stream", "intStream", "longStream", "doubleStream")) {
            if(index != 0) {
                return null;
            }
            final List<PsiExpression> otherArgs = getArgsThatDoNotContainElement(args, originalStartingElement);
            ret.addElementsContainingDisHit(otherArgs);
            return NextStep.Continue;
        }
        return null;
    }

    public static boolean isStreamSupport(final PsiClass containingClass) {
        if(containingClass == null) {
            return false;
        }
        return StringUtils.equals(containingClass.getQualifiedName(), "java.util.stream.StreamSupport");
    }
}
