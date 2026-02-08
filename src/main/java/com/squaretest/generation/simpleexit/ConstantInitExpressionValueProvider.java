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
package com.squaretest.generation.simpleexit;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassObjectAccessExpression;
import com.intellij.psi.PsiConstantEvaluationHelper;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiEnumConstant;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceExpression;
import com.intellij.psi.PsiType;
import com.intellij.psi.util.TypeConversionUtil;
import com.squaretest.generation.dependencyinteraction.SQExpressionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.squaretest.generation.dataflow.DataflowUtils.quote;
import static com.squaretest.generation.dependencyinteraction.SQExpressionUtils.isNullLiteral;

public class ConstantInitExpressionValueProvider {

    public static final String[] JdkOrLibraryPrefixes = new String[]{
            "android.",
            "androidx.",
            "com.amazonaws.",
            "com.google.api.",
            "com.google.cloud.",
            "com.google.common.",
            "gnu.trove.",
            "io.quarkus.",
            "io.reactivex.",
            "java.",
            "javax.",
            "org.apache.",
            "org.joda.time.",
            "org.reactivestreams.",
            "org.spring.",
            "org.springframework.",
            "software.amazon.awssdk."
    };

    @NotNull
    private final PsiConstantEvaluationHelper constantEvaluationHelper;

    public ConstantInitExpressionValueProvider(
            @NotNull final PsiConstantEvaluationHelper constantEvaluationHelper) {
        this.constantEvaluationHelper = constantEvaluationHelper;
    }

    @Nullable
    public String getInitExpressionForMethodParam(final PsiType paramType, final PsiExpression returnExpression) {
        if(returnExpression == null) {
            return null;
        }
        String ret = getEnumRefValue(returnExpression);
        if(ret != null) {
            return ret;
        }
        if(isNullLiteral(returnExpression)) {
            return null;
        }
        if(returnExpression instanceof PsiClassObjectAccessExpression) {
            return null;
        }
        // Determine if this is a reference to a Java constant within a known library.
        // In this case, set the value to a reference to the same constant field.
        ret = getReferenceToKnownLibraryJavaConstant(paramType, returnExpression);
        if(ret != null) {
            return ret;
        }
        ret = getJavaConstant(paramType, returnExpression);
        if(ret != null) {
            return ret;
        }

        // Do not check for ref to a constant field.
        // ret = getRefToConstantField(returnExpression);
        return ret;
    }

    @Nullable
    static String getEnumRefValue(final PsiExpression expression) {
        if(expression instanceof PsiReference) {
            final PsiElement target = ((PsiReference) expression).resolve();
            if(target instanceof final PsiEnumConstant enumConstant) {
                final PsiClass containingClass = enumConstant.getContainingClass();
                if(containingClass == null) {
                    return null;
                }
                final String qualifiedName = containingClass.getQualifiedName();
                if(qualifiedName == null) {
                    return null;
                }
                return qualifiedName + "." + enumConstant.getName();
            }
        }
        return null;
    }

    public static boolean isJdkOrKnownLibrary(final String qualifiedName) {
        return StringUtils.startsWithAny(qualifiedName, JdkOrLibraryPrefixes);
    }

    @Nullable
    static String getReferenceToKnownLibraryJavaConstant(
            final PsiType methodReturnType, final PsiExpression returnExpression) {
        if(!(returnExpression instanceof final PsiReferenceExpression referenceExpression)) {
            return null;
        }
        final PsiElement target = referenceExpression.resolve();
        if(!(target instanceof final PsiField psiField)) {
            return null;
        }
        final PsiClass containingClass = psiField.getContainingClass();
        if(containingClass == null) {
            return null;
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName == null) {
            return null;
        }
        if(!isJdkOrKnownLibrary(qualifiedName)) {
            return null;
        }
        final PsiType returnExpressionType = returnExpression.getType();
        if(!isPrimitiveOrBoxed(returnExpressionType)) {
            return null;
        }
        if(!isConstantField(psiField)) {
            return null;
        }
        String initExpression = qualifiedName + "." + psiField.getName();
        if(!TypeConversionUtil.isAssignable(methodReturnType, returnExpressionType)) {
            // Cast to the method return type.
            initExpression = "(" + methodReturnType.getCanonicalText() + ")" + initExpression;
        }
        return initExpression;
    }

    static boolean isPrimitiveOrBoxed(final PsiType type) {
        return TypeConversionUtil.isPrimitiveAndNotNull(type) || TypeConversionUtil.isPrimitiveWrapper(type);
    }

    private static boolean isConstantField(final PsiField psiField) {
        return psiField.hasModifierProperty(PsiModifier.PUBLIC) && psiField.hasModifierProperty(PsiModifier.STATIC)
                && psiField.hasModifierProperty(PsiModifier.FINAL);
    }

    @Nullable
    private String getJavaConstant(final PsiType methodReturnType, final PsiExpression returnExpression) {
        boolean isShort = SQExpressionUtils.isShortType(methodReturnType);
        boolean isByte = SQExpressionUtils.isByteType(methodReturnType);
        boolean isLong = TypeConversionUtil.isLongType(methodReturnType);
        boolean isChar = SQExpressionUtils.isCharType(methodReturnType);
        final Object constantExp = constantEvaluationHelper.computeConstantExpression(returnExpression);
        if(constantExp instanceof final String strConstant) {
            final String escapedStrConstant = StringUtil.escapeStringCharacters(strConstant);
            return quote(escapedStrConstant);
        }
        if(constantExp instanceof final Double doubleExp) {
            if(doubleExp.isNaN()) {
                return "java.lang.Double.NaN";
            }
            if(doubleExp == Double.POSITIVE_INFINITY) {
                return "java.lang.Double.POSITIVE_INFINITY";
            }
            if(doubleExp == Double.NEGATIVE_INFINITY) {
                return "java.lang.Double.NEGATIVE_INFINITY";
            }
            return doubleExp + getDoubleSuffix();
        }
        if(constantExp instanceof final Float floatExp) {
            if(floatExp.isNaN()) {
                return "java.lang.Float.NaN";
            }
            if(floatExp == Float.POSITIVE_INFINITY) {
                return "java.lang.Float.POSITIVE_INFINITY";
            }
            if(floatExp == Float.NEGATIVE_INFINITY) {
                return "java.lang.Float.NEGATIVE_INFINITY";
            }
            return floatExp + "f";
        }
        if(constantExp instanceof Integer) {
            String prefix = "";
            String suffix = "";
            if(isShort) {
                prefix = "(short)";
            } else if(isByte) {
                prefix = "(byte)";
            } else if(isLong) {
                suffix = "L";
            } else if(isChar) {
                prefix = "(char)";
            }
            return prefix + constantExp + suffix;
        }
        if(constantExp instanceof Boolean) {
            return constantExp.toString();
        }
        if(constantExp instanceof Byte) {
            return "(byte)" + constantExp;
        }
        if(constantExp instanceof Short) {
            return "(short)" + constantExp;
        }
        if(constantExp instanceof Long) {
            return constantExp + "L";
        }
        if(constantExp instanceof Character) {
            String strConstant = constantExp.toString();
            strConstant = StringUtil.escapeCharCharacters(strConstant);
            return StringUtils.wrap(strConstant, "'");
        }
        return null;
    }

    public String getDoubleSuffix() {
        return "";
    }
}
