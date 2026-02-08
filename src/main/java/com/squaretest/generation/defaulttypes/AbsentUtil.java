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

import com.intellij.psi.CommonClassNames;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypeParameterList;
import com.intellij.psi.util.InheritanceUtil;
import com.intellij.psi.util.PsiUtil;
import com.squaretest.generation.CompiledUtils;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.TypeImpl;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AbsentUtil {

    static void updateAbsentInitExpressionForUnknownTypeIfNeeded(final TypeImpl typeImpl, final PsiType parameterType) {
        final PsiClass typeClass = CompiledUtils.getClassWithSourceCode(PsiUtil.resolveClassInType(parameterType));
        if(typeClass == null) {
            return;
        }
        final String canonicalName = typeClass.getQualifiedName();
        if(canonicalName == null) {
            return;
        }
        if(TypeCreatorUtil.isNonStaticInnerClass(typeClass)) {
            return;
        }

        // If we don't have exactly one type param, return.
        final PsiTypeParameterList typeParams = typeClass.getTypeParameterList();
        if(typeParams == null || typeParams.getTypeParameters().length != 1) {
            return;
        }

        // If this is an Iterable, return.
        if(InheritanceUtil.isInheritor(typeClass, CommonClassNames.JAVA_LANG_ITERABLE)) {
            return;
        }

        final boolean isInterface = typeClass.isInterface();

        // We have exactly one type param. Search for no-args static creator methods.
        List<PsiMethod> candidateMethods = Arrays.stream(typeClass.getMethods())
                .filter(x -> (x.hasModifierProperty(PsiModifier.PUBLIC) || (isInterface && x.hasModifierProperty(PsiModifier.PACKAGE_LOCAL))) && x.hasModifierProperty(PsiModifier.STATIC)
                        && !x.isDeprecated() && x.getParameterList().getParameters().length == 0 && TypeCreatorUtil.hasSameType(typeClass, x.getReturnType()))
                .toList();
        if(!candidateMethods.isEmpty()) {
            Optional<PsiMethod> methodToUse = candidateMethods.stream().filter(x -> x.getName().equals("absent")).findAny();
            if(methodToUse.isPresent()) {
                typeImpl.setAbsentInitExpression(canonicalName + "." + methodToUse.get().getName() + "()");
                return;
            }

            methodToUse = candidateMethods.stream().filter(x -> x.getName().equals("empty")).findAny();
            if(methodToUse.isPresent()) {
                typeImpl.setAbsentInitExpression(canonicalName + "." + methodToUse.get().getName() + "()");
                return;
            }

            methodToUse = candidateMethods.stream().filter(x -> StringUtils.containsAny(x.getName(), "empty", "Empty", "absent", "Absent")).findAny();
            if(methodToUse.isPresent()) {
                typeImpl.setAbsentInitExpression(canonicalName + "." + methodToUse.get().getName() + "()");
                return;
            }

            typeImpl.setAbsentInitExpression(canonicalName + "." + candidateMethods.get(0).getName() + "()");
            return;
        }

        // We don't have a no-args static creator method.
        // Check to see if the following are all true:
        // 1. The class has 1 type parameter.
        // 2. The class has a static creator method that uses the type parameter.
        // 3. The type parameter has an absentInitExpression.
        // This covers cases like: CustomCompletableFuture<Optional<Foo>>.
        // In that case, the absentInitExpression should be: CustomCompletableFuture.completedFuture(Optional.absent()).
        final PsiMethod staticCreatorMethod = TypeCreatorUtil.findPublicStaticCreatorMethodThatUsesSingleTypeParam(typeClass);
        final Api.FluentList<Api.Type> sqTypeParams = typeImpl.getParameters();
        if(staticCreatorMethod != null && sqTypeParams.size() == 1) {
            final String firstTypeParamAbsentInitExpression = sqTypeParams.get(0).getAbsentInitExpression();
            if(firstTypeParamAbsentInitExpression != null && !StringUtils.equals("null", firstTypeParamAbsentInitExpression)) {
                typeImpl.setAbsentInitExpression(canonicalName + "." + staticCreatorMethod.getName() + "(" + firstTypeParamAbsentInitExpression + ")");
            }
        }
    }

    static void updateAbsentInitExpressionIfNeeded(@NotNull final TypeImpl typeImpl, final PsiType parameterType) {
        if(typeImpl.isRecognized()) {
            updateAbsentInitExpressionForKnownTypeIfNeeded(typeImpl);
            return;
        }
        updateAbsentInitExpressionForUnknownTypeIfNeeded(typeImpl, parameterType);
    }

    private static void updateAbsentInitExpressionForKnownTypeIfNeeded(@NotNull final TypeImpl typeImpl) {
        if(typeImpl.isAbsentIfTypeParamsAreAbsent() && typeImpl.getInitExpressionWithTypePlaceholder() != null
                && typeImpl.getNumberOfTypesInPlaceholderExpression() == typeImpl.getParameters().size()
                && typeImpl.getParameters().stream().anyMatch(x -> x.getAbsentInitExpression() != null && !StringUtils.equals("null", x.getAbsentInitExpression()))) {

            String newAbsentInitExpression = typeImpl.getInitExpressionWithTypePlaceholder();
            Api.FluentList<Api.Type> parameters = typeImpl.getParameters();
            for(int i = 0; i < parameters.size(); i++) {
                final Api.Type type = parameters.get(i);
                final String valueKey = String.format("{{VALUE%d}}", i + 1);
                // The type.getAbsentInitExpression() can't be null, because we check type.getAbsentInitExpression() != null
                // for all types in the if statement.
                newAbsentInitExpression = newAbsentInitExpression.replace(valueKey, type.getAbsentInitExpression());
                final String typeKey = String.format("{{TYPE%d}}", i + 1);
                newAbsentInitExpression = newAbsentInitExpression.replace(typeKey, type.getCanonicalNameOrName());
                final String typeTextKey = String.format("{{TYPETEXT%d}}", i + 1);
                newAbsentInitExpression = newAbsentInitExpression.replace(typeTextKey, type.getCanonicalText());
            }

            typeImpl.setAbsentInitExpression(newAbsentInitExpression);
        }
    }
}
