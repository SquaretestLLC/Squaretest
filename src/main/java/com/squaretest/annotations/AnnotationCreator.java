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

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiArrayInitializerMemberValue;
import com.intellij.psi.PsiConstantEvaluationHelper;
import com.intellij.psi.PsiJavaCodeReferenceElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.PsiModifierListOwner;
import com.intellij.psi.PsiNameValuePair;
import com.intellij.psi.PsiPolyadicExpression;
import com.intellij.psi.PsiReferenceExpression;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.AnnotationImpl;
import com.squaretest.template.impl.AnnotationParameterImpl;
import com.squaretest.template.impl.AnnotationValueImpl;
import com.squaretest.template.impl.FluentListImpl;
import com.squaretest.template.impl.NameUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AnnotationCreator {
    @NotNull
    private final PsiConstantEvaluationHelper constantEvaluationHelper;
    @NotNull
    private final SpringReferenceResolver springReferenceResolver;

    public AnnotationCreator(
            @NotNull final PsiConstantEvaluationHelper constantEvaluationHelper,
            @NotNull final SpringReferenceResolver springReferenceResolver) {
        this.constantEvaluationHelper = constantEvaluationHelper;
        this.springReferenceResolver = springReferenceResolver;
    }

    public Api.FluentList<Api.Annotation> createAnnotations(@NotNull final PsiModifierListOwner field) {
        final Api.FluentList<Api.Annotation> ret = new FluentListImpl<>();
        final PsiModifierList modifierList = field.getModifierList();
        if(modifierList == null) {
            return ret;
        }
        final PsiAnnotation[] annotations = modifierList.getAnnotations();
        for(final PsiAnnotation annotation : annotations) {
            // Determine the name.
            final String name = getAnnotationName(annotation);
            if(name == null) {
                continue;
            }
            final String qualifiedName = annotation.getQualifiedName();
            final Api.FluentList<Api.AnnotationParameter> parameters = new FluentListImpl<>();
            for(final PsiNameValuePair nameValuePair : annotation.getParameterList().getAttributes()) {
                final String attributeName = nameValuePair.getAttributeName();
                final PsiAnnotationMemberValue value = nameValuePair.getValue();
                if(value == null || value.getText() == null) {
                    continue;
                }
                final String firstStringValue = computeFirstStringValue(value);
                parameters.add(new AnnotationParameterImpl(attributeName, new AnnotationValueImpl(value.getText(), firstStringValue)));
            }
            ret.add(new AnnotationImpl(name, qualifiedName, parameters));
        }
        return ret;
    }

    private String computeFirstStringValue(final PsiAnnotationMemberValue value) {
        if(value instanceof final PsiArrayInitializerMemberValue psiArrayInitializerMemberValue) {
            final PsiAnnotationMemberValue[] initializers = psiArrayInitializerMemberValue.getInitializers();
            if(initializers.length == 0) {
                return "";
            } else {
                final PsiAnnotationMemberValue firstInitializerValue = initializers[0];
                if(firstInitializerValue instanceof PsiLiteralExpression) {
                    return computeValueForLiteralExpression((PsiLiteralExpression) firstInitializerValue);
                }
                final Object constantValue = constantEvaluationHelper.computeConstantExpression(firstInitializerValue, false);
                if(constantValue == null) {
                    return "";
                }
                if(constantValue instanceof String) {
                    return StringUtil.escapeStringCharacters((String) constantValue);
                }
                return constantValue.toString();
            }
        } else if(value instanceof PsiLiteralExpression) {
            return computeValueForLiteralExpression((PsiLiteralExpression) value);
        } else if(value instanceof PsiReferenceExpression || value instanceof PsiPolyadicExpression) {
            final Object constantValue = constantEvaluationHelper.computeConstantExpression(value, false);
            if(constantValue == null) {
                return "";
            }
            if(constantValue instanceof String) {
                return StringUtil.escapeStringCharacters((String) constantValue);
            }
            return constantValue.toString();
        }
        return "";
    }

    @NotNull
    private String computeValueForLiteralExpression(final PsiLiteralExpression psiLiteralExpression) {
        String textToUse = springReferenceResolver.getStringContentWithPropertiesFilledIn(psiLiteralExpression);
        if(textToUse == null) {
            // This only happens when the literal is not a String literal.
            textToUse = psiLiteralExpression.getText();
        }
        if(textToUse == null) {
            textToUse = "";
        }
        return textToUse;
    }

    @Nullable
    public static String getAnnotationName(final PsiAnnotation annotation) {
        String name = null;
        final PsiJavaCodeReferenceElement nameReferenceElement = annotation.getNameReferenceElement();
        if(nameReferenceElement != null) {
            final String refName = nameReferenceElement.getReferenceName();
            if(refName != null) {
                name = NameUtils.computeSimpleName(refName);
            }
        }
        return name;
    }
}
