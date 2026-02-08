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

import com.squaretest.template.api.Api;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;

public class OldTestCaseFactoryUtils {
    public static boolean determineExpectedValueAbsent(
            final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs,
            final Api.DependencyInteraction diToReturnAbsent) throws InvocationTargetException, IllegalAccessException {
        final Api.FluentList<Api.DependencyInteraction> otherDIs = mockedDIs.filterOutItem(diToReturnAbsent).filterOut("returnValueIgnored").filterOut("method.returnType", (Object) null).filterOutItemsWithRegex("field.type.name", ".*([Mm]apper[s]*|[Cc]onverter[s]*|[Tt]ransformer[s]*])$");
        final Api.Type diReturnType = diToReturnAbsent.getMethod().getReturnType();
        final Api.Type sourceMethodReturnType = sourceMethod.getReturnType();
        if(sourceMethodReturnType == null) {
            return false;
        }
        if(!sourceMethod.getReturnTypeCanBeAbsent()) {
            return false;
        }
        if(sourceMethodReturnType.getAbsentInitExpression() == null) {
            return false;
        }
        if(diReturnType == null) {
            return false;
        }
        if(otherDIs.isEmpty()) {
            return true;
        }
        final String sourceMethodReturnTypeCanonicalText = sourceMethodReturnType.getCanonicalText();
        final String diReturnTypeCanonicalText = diReturnType.getCanonicalText();
        final Api.Type diReturnTypeParam = diReturnType.getParameters().first();
        String diReturnTypeParamCanonicalText = diReturnTypeParam != null ? diReturnTypeParam.getCanonicalText() : null;
        boolean sourceMethodAndDIReturnSameType = false;
        if(sourceMethod.isInferredNullable()) {
            sourceMethodAndDIReturnSameType = StringUtils.equals(sourceMethodReturnTypeCanonicalText, diReturnTypeCanonicalText)
                    || StringUtils.equals(sourceMethodReturnTypeCanonicalText, diReturnTypeParamCanonicalText);
            if(!sourceMethodAndDIReturnSameType) {
                // If the dependency method does not return the same type as the source method, assume it is not the
                // method that causes the source method to return null.
                return false;
            }
            if(otherDIs.containsAnyWith("method.returnType.canonicalText", sourceMethodReturnTypeCanonicalText)
                    || otherDIs.containsAnyWith("method.returnType.parameters.first.canonicalText", sourceMethodReturnTypeCanonicalText)) {
                return false;
            }
            return true;
        }

        // The source method returns a type with an absent variant; e.g. Optional<T>.
        // Determine if the DI returns the same type as the source method -- either Optional<T> or T.
        final Api.Type methodReturnTypeParam = sourceMethodReturnType.getParameters().first();
        if(StringUtils.equals(sourceMethodReturnTypeCanonicalText, diReturnTypeCanonicalText)) {
            sourceMethodAndDIReturnSameType = true;
        } else {
            if(methodReturnTypeParam != null) {
                if(StringUtils.equals(diReturnTypeCanonicalText, methodReturnTypeParam.getCanonicalText())) {
                    sourceMethodAndDIReturnSameType = true;
                } else if(diReturnTypeParam != null) {
                    sourceMethodAndDIReturnSameType = StringUtils.equals(diReturnTypeParamCanonicalText, methodReturnTypeParam.getCanonicalText());
                } else {
                    sourceMethodAndDIReturnSameType = false;
                }
            } else if(diReturnTypeParam != null) {
                sourceMethodAndDIReturnSameType = StringUtils.equals(diReturnTypeParamCanonicalText, sourceMethodReturnTypeCanonicalText);
            }
        }
        if(!sourceMethodAndDIReturnSameType) {
            return false;
        }

        // Determine if other DIs return the same type as the source method.
        if(otherDIs.containsAnyWith("method.returnType.canonicalText", sourceMethodReturnTypeCanonicalText)
                || otherDIs.containsAnyWith("method.returnType.parameters.first.canonicalText", sourceMethodReturnTypeCanonicalText)) {
            return false;
        }
        if(methodReturnTypeParam != null) {
            if(otherDIs.containsAnyWith("method.returnType.canonicalText", methodReturnTypeParam.getCanonicalText())
                    || otherDIs.containsAnyWith("method.returnType.parameters.first.canonicalText", methodReturnTypeParam.getCanonicalText())) {
                return false;
            }
        }
        return true;
    }

    public static boolean determineExpectedValueEmpty(
            final Api.Method sourceMethod, final Api.FluentList<Api.DependencyInteraction> mockedDIs,
            final Api.DependencyInteraction diToReturnEmpty) throws InvocationTargetException, IllegalAccessException {
        final Api.Type sourceMethodReturnType = sourceMethod.getReturnType();
        if(sourceMethodReturnType == null || sourceMethodReturnType.getEmptyInitExpression() == null) {
            return false;
        }
        final String sourceMethodReturnTypeCanonicalText = sourceMethodReturnType.getCanonicalText();
        final Api.Type diReturnType = diToReturnEmpty.getMethod().getReturnType();
        final String diReturnTypeCanonicalText = diReturnType != null ? diReturnType.getCanonicalText() : null;
        final Api.FluentList<Api.DependencyInteraction> otherDIs = mockedDIs.filterOutItem(diToReturnEmpty).filterOut("returnValueIgnored").filterOut("method.returnType", (Object) null).filterOutItemsWithRegex("field.type.name", ".*([Mm]apper[s]*|[Cc]onverter[s]*|[Tt]ransformer[s]*])$");
        final boolean sourceMethodReturnsSameValue = StringUtils.equals(sourceMethodReturnTypeCanonicalText, diReturnTypeCanonicalText);
        return (sourceMethodReturnsSameValue && !otherDIs.containsAnyWith("method.returnType.canonicalText", diReturnTypeCanonicalText))
                || !otherDIs.containsAnyWithNonNull("method.returnType.emptyInitExpression");
    }
}
