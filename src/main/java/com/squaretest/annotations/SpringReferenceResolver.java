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
package com.squaretest.annotations;

import com.intellij.lang.properties.references.PropertyReferenceBase;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.PsiReference;
import com.intellij.psi.ResolveResult;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import static com.intellij.psi.util.PsiLiteralUtil.getStringLiteralContent;

public class SpringReferenceResolver {
    @NotNull
    private final PropertyResolver propertyResolver;

    public SpringReferenceResolver(@NotNull final PropertyResolver propertyResolver) {
        this.propertyResolver = propertyResolver;
    }

    @Nullable
    public String getStringContentWithPropertiesFilledIn(final PsiLiteralExpression psiLiteralExpression) {
        String valueText = getStringLiteralContent(psiLiteralExpression);
        if(valueText == null) {
            // This is not a String literal.
            return null;
        }
        if(StringUtils.contains(valueText, "#{")) {
            // This contains Spring Expression Language (SpEL). We do not support that.
            return valueText;
        }
        if(!StringUtils.containsAny(valueText, "${")) {
            // There are no references to fill in.
            return valueText;
        }
        for(final PsiReference reference : psiLiteralExpression.getReferences()) {
            if(!(reference instanceof final PropertyReferenceBase propertyReferenceBase)) {
                continue;
            }

            final ResolveResult[] resolveResults;
            try {
                // If IntelliJ does not have the result in the cache this call will fail with an exception.
                // It should normally have the result in the cache. Just swallow the exception and continue.
                resolveResults = propertyReferenceBase.multiResolve(false);
            } catch (final Throwable ignored) {
                continue;
            }

            for(final ResolveResult resolveResult : resolveResults) {
                if(!resolveResult.isValidResult()) {
                    continue;
                }
                final PsiElement resolveResultElement = resolveResult.getElement();
                final Map.Entry<String, String> keyValuePair = propertyResolver.getKeyValuePair(resolveResultElement);
                if(keyValuePair == null) {
                    continue;
                }
                final String propertyKey = keyValuePair.getKey();
                final String placeholderKey = "${" + propertyKey + "}";
                String placeholderValue = keyValuePair.getValue();
                placeholderValue = StringUtil.escapeStringCharacters(placeholderValue);
                valueText = valueText.replace(placeholderKey, placeholderValue);
            }
        }
        return valueText;
    }
}
