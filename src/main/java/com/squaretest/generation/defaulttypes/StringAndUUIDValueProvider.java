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

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiRecordComponent;
import com.intellij.psi.PsiVariable;
import com.intellij.psi.impl.light.LightElement;
import com.squaretest.generation.CompiledUtils;
import com.squaretest.generation.MemberFieldPrefixRemover;
import com.squaretest.generation.dataflow.DataflowAnalyzer;
import com.squaretest.generation.dataflow.DataflowUtils;
import com.squaretest.generation.defaulttypes.beans.BeanDetectorUtils;
import com.squaretest.generation.defaulttypes.beans.BeanInfoProvider;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.UUID;

import static com.squaretest.generation.dataflow.DataflowUtils.*;
import static com.squaretest.generation.defaulttypes.JavaGroovyCommonTypesCreator.isLikelyUUID;

public class StringAndUUIDValueProvider {
    @NotNull
    private final DataflowAnalyzer dataflowAnalyzer;
    @NotNull
    private final BeanInfoProvider beanInfoProvider;
    @NotNull
    private final MemberFieldPrefixRemover memberFieldPrefixRemover;
    @NotNull
    private final Map<PsiElement, String> sourceToStringInitExpression;
    @NotNull
    private final Map<PsiElement, String> sourceToUUIDInitExpression;

    public StringAndUUIDValueProvider(
            @NotNull final DataflowAnalyzer dataflowAnalyzer,
            @NotNull final BeanInfoProvider beanInfoProvider,
            @NotNull final MemberFieldPrefixRemover memberFieldPrefixRemover) {
        this.dataflowAnalyzer = dataflowAnalyzer;
        this.beanInfoProvider = beanInfoProvider;
        this.memberFieldPrefixRemover = memberFieldPrefixRemover;
        sourceToStringInitExpression = new IdentityHashMap<>();
        sourceToUUIDInitExpression = new IdentityHashMap<>();
    }

    public String getStringInitExpression(
            @NotNull final String parameterName,
            @Nullable PsiElement source) {
        if(source == null) {
            return quote(parameterName);
        }
        // Determine the correct source to use for the lookup.
        final PsiElement sourceToUse = getSourceToUse(source);
        return sourceToStringInitExpression.computeIfAbsent(sourceToUse, x -> {
            final String dataValue = dataflowAnalyzer.getInitExpressionForString(x);
            if(dataValue != null) {
                return dataValue;
            }
            final String valueForSetterParam = getInitExpressionForValueParamSetter(sourceToUse);
            if(valueForSetterParam != null) {
                return valueForSetterParam;
            }
            final String valueForSpringDateParam = getValueForSpringDateParam(sourceToUse);
            if(valueForSpringDateParam != null) {
                return valueForSpringDateParam;
            }
            return chooseBestStringValue(parameterName);
        });
    }

    private String getValueForSpringDateParam(final PsiElement sourceToUse) {
        if(!(sourceToUse instanceof PsiParameter)) {
            return null;
        }
        if(((PsiParameter) sourceToUse).hasAnnotation("org.springframework.format.annotation.DateTimeFormat")) {
            return DefaultSpringDateParamValue;
        }
        return null;
    }

    @Nullable
    private String getInitExpressionForValueParamSetter(final PsiElement sourceToUse) {
        // Special case setter methods where the parameter is named "value".
        if(!(sourceToUse instanceof final PsiParameter param)) {
            return null;
        }
        if(!param.getName().equals("value")) {
            return null;
        }
        final PsiMethod containingMethod = getContainingMethodForParameter(param);
        PsiField field = beanInfoProvider.getFieldForSetter(containingMethod);
        if(field == null) {
            field = beanInfoProvider.getFieldForWithMethod(containingMethod);
        }
        if(field == null) {
            return null;
        }
        return quote(removePrefixAndSuffix(field));
    }

    private String removePrefixAndSuffix(final PsiField field) {
        String temp = memberFieldPrefixRemover.removePrefix(field.getName());
        return BeanDetectorUtils.removeSuffix(temp);
    }

    private static PsiElement getSourceToUse(@NotNull final PsiElement source) {
        PsiElement sourceToUse = CompiledUtils.getElementWithSourceCode(source);
        if(sourceToUse instanceof PsiParameter) {
            final PsiRecordComponent recordComponent = DataflowUtils.getComponentForCanonicalConstructorParameter((PsiParameter) sourceToUse);
            if(recordComponent != null) {
                return recordComponent;
            }
            if(sourceToUse instanceof LightElement && StringUtils.contains(sourceToUse.getClass().getCanonicalName(), JavaNames.LombokLight)) {
                final PsiElement declarationScope = ((PsiParameter) sourceToUse).getDeclarationScope();
                if(declarationScope instanceof final PsiMethod methodDeclarationScope && StringUtils.contains(declarationScope.getClass().getCanonicalName(), JavaNames.LombokLightMethodName)) {
                    if(!methodDeclarationScope.isConstructor()) {
                        final PsiElement navTarget = CompiledUtils.getElementWithSourceCode(declarationScope.getNavigationElement());
                        if(navTarget instanceof PsiVariable && !(navTarget instanceof LightElement)) {
                            return navTarget;
                        }
                    }
                }
            }
        }
        return sourceToUse;
    }

    private String chooseBestStringValue(@NotNull final String parameterName) {
        if(StringUtils.equalsAny(parameterName, "charsetName", "charset")) {
            return quote("UTF-8");
        } else if(isLikelyUUID(parameterName)) {
            return quote(UUID.randomUUID().toString());
        } else {
            return quote(parameterName);
        }
    }

    public String getUUIDInitExpression(
            @Nullable PsiElement source) {
        if(source == null) {
            return String.format(UUIDFormatString, UUID.randomUUID());
        }
        final PsiElement sourceToUse = CompiledUtils.getElementWithSourceCode(source);
        return sourceToUUIDInitExpression.computeIfAbsent(sourceToUse, x -> {
            final String dataValue = dataflowAnalyzer.getInitExpressionForUUID(x);
            if(dataValue != null) {
                return dataValue;
            }
            return String.format(UUIDFormatString, UUID.randomUUID());
        });
    }
}
