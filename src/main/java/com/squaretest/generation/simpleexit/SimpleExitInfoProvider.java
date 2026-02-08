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
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiConstantEvaluationHelper;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiReferenceExpression;
import com.intellij.psi.PsiReturnStatement;
import com.intellij.psi.PsiStatement;
import com.intellij.psi.PsiThrowStatement;
import com.intellij.psi.PsiType;
import com.intellij.psi.util.PsiUtil;
import com.intellij.psi.util.TypeConversionUtil;
import com.squaretest.generation.ExceptionCreator;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.dependencyinteraction.SQExpressionUtils;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.SimpleExitInfoImpl;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.squaretest.generation.dataflow.DataflowUtils.quote;
import static com.squaretest.generation.dependencyinteraction.SQExpressionUtils.isNullLiteral;
import static com.squaretest.generation.dependencyinteraction.outcomes.OutcomePopulator.isAbsentInitExpression;
import static com.squaretest.generation.dependencyinteraction.outcomes.OutcomePopulator.isEmptyInitExpression;
import static com.squaretest.generation.simpleexit.ConstantInitExpressionValueProvider.getEnumRefValue;
import static com.squaretest.generation.simpleexit.ConstantInitExpressionValueProvider.getReferenceToKnownLibraryJavaConstant;
import static com.squaretest.template.impl.SimpleExitInfoImpl.EmptyInfo;

public class SimpleExitInfoProvider {

    @NotNull
    private final PsiConstantEvaluationHelper constantEvaluationHelper;
    @NotNull
    private final ExceptionCreator exceptionCreator;

    public SimpleExitInfoProvider(
            @NotNull final PsiConstantEvaluationHelper constantEvaluationHelper,
            @NotNull final ExceptionCreator exceptionCreator) {
        this.constantEvaluationHelper = constantEvaluationHelper;
        this.exceptionCreator = exceptionCreator;
    }

    public Api.SimpleExitInfo computeSimpleExitInfo(final PsiMethod psiMethod, final Api.Type returnType) {
        final String constantReturnValue = getConstantForReturnValue(psiMethod, returnType);
        if(constantReturnValue != null) {
            return new SimpleExitInfoImpl(constantReturnValue, null);
        }
        final PsiThrowStatement throwsStatement = getSingleThrowsStatement(psiMethod);
        if(throwsStatement != null) {
            final PsiExpression thrownExceptionExpression = throwsStatement.getException();
            if(thrownExceptionExpression == null) {
                // The throws statement is incomplete.
                return EmptyInfo;
            }
            final PsiType thrownExceptionType = thrownExceptionExpression.getType();
            if(thrownExceptionType == null) {
                return EmptyInfo;
            }
            // Check for the case where we have an Android class that throws a RuntimeException.
            // Android SDK methods are configured to throw new RuntimeException("Stub!").
            // Avoid creating tests for that, because the behavior will be replaced with the actual implementation
            // when the app is run.
            if(StringUtils.equals(thrownExceptionType.getCanonicalText(), JavaNames.JavaLangRuntimeException)) {
                final PsiClass containingClass = psiMethod.getContainingClass();
                if(containingClass == null) {
                    return EmptyInfo;
                }
                if(StringUtils.startsWithAny(containingClass.getQualifiedName(), "android")) {
                    return EmptyInfo;
                }
            }
            // Create and return the exit info.
            final Api.Exception thrownException = exceptionCreator.createExceptionType(thrownExceptionType);
            return new SimpleExitInfoImpl(null, thrownException);
        }
        return EmptyInfo;
    }

    @Nullable
    private String getConstantForReturnValue(final PsiMethod psiMethod, final Api.Type apiReturnType) {
        final PsiExpression returnExpression = PsiUtil.deparenthesizeExpression(getSingleReturnValue(psiMethod));
        if(returnExpression == null) {
            return null;
        }
        String ret = getEnumRefValue(returnExpression);
        if(ret != null) {
            return ret;
        }
        if(isNullLiteral(returnExpression)) {
            return "null";
        }
        if(returnExpression instanceof PsiClassObjectAccessExpression) {
            final String typeText = ((PsiClassObjectAccessExpression) returnExpression).getOperand().getType().getCanonicalText();
            return typeText + ".class";
        }

        // Determine if this is a reference to a Java constant within a known library.
        // In this case, set the value to a reference to the same constant field.
        final PsiType methodReturnType = psiMethod.getReturnType();
        ret = getReferenceToKnownLibraryJavaConstant(methodReturnType, returnExpression);
        if(ret != null) {
            return ret;
        }

        ret = getJavaConstant(methodReturnType, returnExpression);
        if(ret != null) {
            return ret;
        }

        // Check for the empty case.
        if(isEmptyInitExpression(returnExpression) && apiReturnType.getOverridesEquals() && apiReturnType.getEmptyInitExpression() != null) {
            return apiReturnType.getEmptyInitExpression();
        }

        // Check for the absent case.
        if(isAbsentInitExpression(returnExpression) && apiReturnType.getOverridesEquals() && apiReturnType.getAbsentInitExpression() != null) {
            return apiReturnType.getAbsentInitExpression();
        }

        // Check for ref to a constant.
        ret = getRefToConstantField(returnExpression);
        return ret;
    }

    private String getRefToConstantField(final PsiExpression returnExpression) {
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
        if(isConstantField(psiField)) {
            return qualifiedName + "." + psiField.getName();
        }
        return null;
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

    public static PsiExpression getSingleReturnValue(@NotNull PsiMethod method) {
        final PsiCodeBlock body = method.getBody();
        if(body == null) {
            return null;
        }
        final PsiStatement[] statements = body.getStatements();
        final PsiStatement statement = statements.length != 1 ? null : statements[0];
        return statement instanceof PsiReturnStatement ? ((PsiReturnStatement) statement).getReturnValue() : null;
    }

    public static PsiThrowStatement getSingleThrowsStatement(@NotNull PsiMethod method) {
        final PsiCodeBlock body = method.getBody();
        if(body == null) {
            return null;
        }
        final PsiStatement[] statements = body.getStatements();
        final PsiStatement statement = statements.length != 1 ? null : statements[0];
        return statement instanceof PsiThrowStatement ? ((PsiThrowStatement) statement) : null;
    }
}
