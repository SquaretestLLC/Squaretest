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
package com.squaretest.generation.dataflow;

import com.intellij.psi.*;
import com.intellij.psi.impl.light.LightElement;
import com.intellij.psi.impl.light.LightParameter;
import com.intellij.psi.util.InheritanceUtil;
import com.intellij.psi.util.JavaPsiRecordUtil;
import com.intellij.psi.util.PsiUtil;
import com.squaretest.generation.CompiledUtils;
import com.squaretest.generation.defaulttypes.JavaNames;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import static com.intellij.psi.CommonClassNames.JAVA_LANG_THROWABLE;
import static com.squaretest.generation.defaulttypes.JavaGroovyCommonTypesCreator.isLikelyUUID;

public class DataflowUtils {
    public static final String UUIDFormatString = "java.util.UUID.fromString(\"%s\")";
    public static final String DefaultSpringDateParamValue = "\"2020-01-01\"";
    private static final Pattern UUIDPattern = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    private DataflowUtils() {
    }

    public static boolean isStringOrUUID(final String canonicalName) {
        return StringUtils.equalsAny(canonicalName, "java.util.UUID", "java.lang.String", "java.lang.CharSequence", "java.lang.Object");
    }

    static void putAll(
            final Map<PsiElement, ImmutableDataValue> typeToInitValues,
            final Set<Node> nodes, final ImmutableDataValue dataValue) {
        for(final Node node : nodes) {
            final PsiElement source = CompiledUtils.getElementWithSourceCode(node.source());
            typeToInitValues.put(source, dataValue);
        }
    }

    static boolean anyValueIsSpringDateParam(final Set<Node> nodes) {
        for(final Node node : nodes) {
            final PsiElement source = node.source();
            if(!(source instanceof final PsiParameter param)) {
                continue;
            }
            if(param.hasAnnotation("org.springframework.format.annotation.DateTimeFormat")) {
                return true;
            }
        }
        return false;
    }

    static boolean isUUIDTypeElement(@Nullable final PsiTypeElement typeElement) {
        if(typeElement == null) {
            return false;
        }
        return isUUIDType(typeElement.getType());
    }

    static boolean isUUIDType(@Nullable final PsiType type) {
        if(type == null) {
            return false;
        }
        final PsiClass resolvedClass = PsiUtil.resolveClassInType(type);
        if(resolvedClass == null) {
            return false;
        }
        return StringUtils.equals(resolvedClass.getQualifiedName(), "java.util.UUID");
    }

    static boolean isToStringCall(PsiMethodCallExpression methodCallExpression) {
        final PsiReferenceExpression methodExpression = methodCallExpression.getMethodExpression();
        final String methodName = methodExpression.getReferenceName();
        if(!"toString".equals(methodName)) {
            return false;
        }
        final PsiExpressionList argumentList = methodCallExpression.getArgumentList();
        final PsiExpression[] arguments = argumentList.getExpressions();
        return arguments.length == 0;
    }

    static boolean isCloneCall(PsiMethodCallExpression methodCallExpression) {
        final PsiReferenceExpression methodExpression = methodCallExpression.getMethodExpression();
        final String methodName = methodExpression.getReferenceName();
        if(!"clone".equals(methodName)) {
            return false;
        }
        final PsiExpressionList argumentList = methodCallExpression.getArgumentList();
        final PsiExpression[] arguments = argumentList.getExpressions();
        return arguments.length == 0;
    }

    static TypeInfo getTypeInfo(final PsiType psiType) {
        if(psiType == null) {
            return TypeInfo.NullTypeInfo;
        }
        final PsiClass actualParamClass = PsiUtil.resolveClassInType(psiType);
        if(actualParamClass == null) {
            return TypeInfo.NullTypeInfo;
        }
        final String actualParamCanonicalName = actualParamClass.getQualifiedName();
        if(actualParamCanonicalName == null) {
            return TypeInfo.NullTypeInfo;
        }
        final boolean actualParamTypeIsStringOrObject = DataflowUtils.isStringOrObject(actualParamCanonicalName);
        final boolean actualParamTypeIsUUID = StringUtils.equals(actualParamCanonicalName, "java.util.UUID");
        return new TypeInfo(actualParamTypeIsStringOrObject, actualParamTypeIsUUID);
    }

    static TypeInfo getTypeInfoForParameter(final PsiParameter psiParameter) {
        if(psiParameter == null) {
            return TypeInfo.NullTypeInfo;
        }
        final PsiClass actualParamClass = PsiUtil.resolveClassInType(psiParameter.getType());
        if(actualParamClass == null) {
            return TypeInfo.NullTypeInfo;
        }
        final String actualParamCanonicalName = actualParamClass.getQualifiedName();
        if(actualParamCanonicalName == null) {
            return TypeInfo.NullTypeInfo;
        }
        final boolean actualParamTypeIsStringOrObject = DataflowUtils.isStringOrObject(actualParamCanonicalName);
        final boolean actualParamTypeIsUUID = StringUtils.equals(actualParamCanonicalName, "java.util.UUID") || isLikelyUUID(psiParameter.getName());
        return new TypeInfo(actualParamTypeIsStringOrObject, actualParamTypeIsUUID);
    }

    static TypeInfo getTypeInfoForExpression(final PsiExpression psiExpression) {
        if(psiExpression == null) {
            return TypeInfo.NullTypeInfo;
        }
        final PsiType expressionType = psiExpression.getType();
        return getTypeInfo(expressionType);
    }

    static TypeInfo getTypeInfoForLocalVariable(final PsiLocalVariable psiLocalVariable) {
        if(psiLocalVariable == null) {
            return TypeInfo.NullTypeInfo;
        }
        final PsiClass actualParamClass = PsiUtil.resolveClassInType(psiLocalVariable.getType());
        if(actualParamClass == null) {
            return TypeInfo.NullTypeInfo;
        }
        final String actualParamCanonicalName = actualParamClass.getQualifiedName();
        if(actualParamCanonicalName == null) {
            return TypeInfo.NullTypeInfo;
        }
        final boolean actualParamTypeIsStringOrObject = DataflowUtils.isStringOrObject(actualParamCanonicalName);
        final boolean actualParamTypeIsUUID = StringUtils.equals(actualParamCanonicalName, "java.util.UUID") || isLikelyUUID(psiLocalVariable.getName());
        return new TypeInfo(actualParamTypeIsStringOrObject, actualParamTypeIsUUID);
    }

    static boolean isUUIDString(final String expressionLiteralText) {
        if(expressionLiteralText == null) {
            return false;
        }
        return UUIDPattern.matcher(expressionLiteralText).matches();
    }

    static boolean isStringOrObject(final String formalParamCanonicalName) {
        return StringUtils.equalsAny(formalParamCanonicalName, "java.lang.String", "java.lang.CharSequence", "java.lang.Object");
    }

    public static PsiRecordComponent getComponentForCanonicalConstructorParameter(@NotNull PsiParameter parameter) {
        final PsiMethod containingMethod = getContainingMethodForParameter(parameter);
        if(containingMethod == null) {
            return null;
        }
        if(!containingMethod.isConstructor()) {
            return null;
        }
        final PsiClass containingClass = containingMethod.getContainingClass();
        if(containingClass == null || !containingClass.isRecord()) {
            return null;
        }

        return JavaPsiRecordUtil.getComponentForCanonicalConstructorParameter(parameter);
    }

    public static boolean isMethodParamOrPatternVariable(final PsiParameter param) {
        if(param instanceof PsiPatternVariable) {
            return true;
        }
        return getContainingMethodForParameter(param) != null;
    }

    @Nullable
    public static PsiMethod getContainingMethodForParameter(final PsiVariable variable) {
        final PsiMethod containingMethod = getContainingMethodForParamSimple(variable);
        if(containingMethod != null) {
            return containingMethod;
        }
        if(StringUtils.contains(variable.getClass().getName(), JavaNames.LombokLight)) {
            if(variable instanceof final LightParameter lightParameter && !(variable instanceof PsiField)) {
                final PsiElement declarationScope = lightParameter.getDeclarationScope();
                if(declarationScope instanceof PsiMethod) {
                    return (PsiMethod) declarationScope;
                }
            }
        }
        return null;
    }

    @Nullable
    private static PsiMethod getContainingMethodForParamSimple(final PsiVariable variable) {
        final PsiElement parent = variable.getParent();
        if(parent instanceof PsiParameterList) {
            final PsiElement containingMethod = parent.getParent();
            if(containingMethod instanceof PsiMethod) {
                return (PsiMethod) containingMethod;
            }
        }
        return null;
    }

    @Nullable
    public static String determineStringLiteralContent(PsiExpression expression) {
        if(!(expression instanceof PsiLiteralExpression)) {
            return null;
        }
        String text = expression.getText();
        int textLength = text.length();
        if(textLength > 1 && text.charAt(0) == '\"' && text.charAt(textLength - 1) == '\"') {
            return text.substring(1, textLength - 1);
        }
        return null;
    }

    /**
     * Wraps the provided String in quotes.
     * This is different from StringUtils.wrap(str, "\""), in that it allows wrapping empty strings. The StringUtils
     * method just returns empty strings instead of wrapping them.
     *
     * @param str the String to quote
     * @return the quoted String or null if the provided String is null.
     */
    public static String quote(final String str) {
        if(str == null) {
            return null;
        }
        return "\"" + str + "\"";
    }

    static boolean isCharSet(final Node psiTypeInfo) {
        return StringUtils.equalsAny(psiTypeInfo.name(), "charsetName", "charset");
    }

    public static ExpressionInfo removeExtras(final PsiExpression psiExpression) {
        return removeExtras(psiExpression, false);
    }

    static ExpressionInfo removeExtras(final PsiExpression psiExpression, final boolean isUUID) {
        // If this is a UUID.fromString(arg) call, follow the arg.
        final PsiExpression expressionInUUIDCall = getExpressionInUUIDFromStringCall(psiExpression);
        if(expressionInUUIDCall != null) {
            return removeExtras(expressionInUUIDCall, true);
        }

        // If this is a call to Objects.requireNonNull(arg), follow the arg.
        final PsiExpression expressionInValidationMethod = ValidationMethodUtils.getExpressionWithinValidationMethod(psiExpression);
        if(expressionInValidationMethod != null) {
            return removeExtras(expressionInValidationMethod, isUUID);
        }

        // If this is a call like UUID.class.cast(arg), follow the arg.
        final ExpressionInfo classCastMethodCallInfo = getExpressionInClassCastMethodCall(psiExpression);
        if(classCastMethodCallInfo != null) {
            return removeExtras(classCastMethodCallInfo.getExpressionToUse(), isUUID || classCastMethodCallInfo.isUUID());
        }

        // If this is a call to arg.toString() or arg.clone(), follow the arg.
        if(psiExpression instanceof final PsiMethodCallExpression methodCallExpression) {
            final PsiExpression qualifier = methodCallExpression.getMethodExpression().getQualifierExpression();
            if(isToStringCall(methodCallExpression) || isCloneCall(methodCallExpression)) {
                if(qualifier == null) {
                    return new ExpressionInfo(psiExpression, getTypeInfoForExpression(psiExpression));
                }
                return removeExtras(qualifier, isUUID);
            }
            final TypeInfo typeInfo;
            if(isUUID) {
                typeInfo = new TypeInfo(false, true);
            } else {
                typeInfo = DataflowUtils.getTypeInfo(psiExpression.getType());
            }
            return new ExpressionInfo(psiExpression, typeInfo);
        }

        // If this is a cast expression like ((UUID) arg), follow the arg.
        if(psiExpression instanceof final PsiTypeCastExpression psiTypeCastExpression) {
            final PsiExpression operandExpression = psiTypeCastExpression.getOperand();
            if(operandExpression != null) {
                // Determine if we're casting to a UUID.
                final boolean isCastToUUID = isUUID || isUUIDTypeElement(psiTypeCastExpression.getCastType());
                return removeExtras(operandExpression, isCastToUUID);
            }
        }

        // Otherwise, assume we're at the final level.
        final TypeInfo typeInfo;
        if(isUUID) {
            typeInfo = new TypeInfo(false, true);
        } else {
            typeInfo = DataflowUtils.getTypeInfo(psiExpression.getType());
        }
        return new ExpressionInfo(psiExpression, typeInfo);
    }

    @Nullable
    private static PsiExpression getExpressionInUUIDFromStringCall(final PsiExpression expression) {
        if(!(expression instanceof final PsiMethodCallExpression psiMethodCallExpression)) {
            return null;
        }
        final PsiMethod calledMethod = psiMethodCallExpression.resolveMethod();
        if(calledMethod == null) {
            return null;
        }
        final PsiClass containingClass = calledMethod.getContainingClass();
        if(containingClass == null) {
            return null;
        }
        final String canonicalName = containingClass.getQualifiedName();
        if(canonicalName == null) {
            return null;
        }
        final boolean isFromStringCall = StringUtils.equals(canonicalName, "java.util.UUID")
                && calledMethod.getName().equals("fromString")
                && calledMethod.getParameterList().getParameters().length == 1;
        if(!isFromStringCall) {
            return null;
        }
        final PsiExpression[] argumentExpressions = psiMethodCallExpression.getArgumentList().getExpressions();
        if(argumentExpressions.length != 1) {
            // This can happen if the method is called, but the user does not pass in an argument
            // (a syntax error).
            return null;
        }
        // Determine what the argument in the UUID.fromString(argument) call is.
        return argumentExpressions[0];
    }

    @Nullable
    private static ExpressionInfo getExpressionInClassCastMethodCall(final PsiExpression expression) {
        if(!(expression instanceof final PsiMethodCallExpression psiMethodCallExpression)) {
            return null;
        }
        final PsiMethod calledMethod = psiMethodCallExpression.resolveMethod();
        if(calledMethod == null) {
            return null;
        }
        final PsiClass containingClass = calledMethod.getContainingClass();
        if(containingClass == null) {
            return null;
        }
        final String canonicalName = containingClass.getQualifiedName();
        if(canonicalName == null) {
            return null;
        }
        final boolean isClassCastMethodCall = StringUtils.equals(canonicalName, "java.lang.Class")
                && calledMethod.getName().equals("cast")
                && calledMethod.getParameterList().getParameters().length == 1;
        if(!isClassCastMethodCall) {
            return null;
        }
        final PsiExpression[] argumentExpressions = psiMethodCallExpression.getArgumentList().getExpressions();
        if(argumentExpressions.length != 1) {
            // This can happen if the method is called, but the user does not pass in an argument
            // (a syntax error).
            return null;
        }
        // Determine what the argument in the UUID.fromString(argument) call is.
        final PsiExpression argument = argumentExpressions[0];

        final PsiExpression qualifier = psiMethodCallExpression.getMethodExpression().getQualifierExpression();
        boolean isUUID = false;
        if(qualifier instanceof final PsiClassObjectAccessExpression psiClassObjectAccessExpression) {
            isUUID = isUUIDTypeElement(psiClassObjectAccessExpression.getOperand());
        }
        final TypeInfo typeInfo;
        if(isUUID) {
            typeInfo = new TypeInfo(false, true);
        } else {
            typeInfo = DataflowUtils.getTypeInfoForExpression(argument);
        }
        return new ExpressionInfo(argument, typeInfo);
    }

    static InstanceOfPatternExpressionInfo getInstanceOfPatternExpressionInfo(final PsiElement element) {
        if(element instanceof final PsiPatternVariable patternVariable) {
            final PsiElement parent = patternVariable.getParent();
            if(parent instanceof final PsiTypeTestPattern psiTypeTestPattern) {
                final PsiElement instanceOfExpressionElement = psiTypeTestPattern.getParent();
                if(instanceOfExpressionElement instanceof final PsiInstanceOfExpression psiInstanceOfExpression) {
                    final PsiExpression newTarget = psiInstanceOfExpression.getOperand();
                    final boolean isUUID = isUUIDTypeElement(psiInstanceOfExpression.getCheckType());
                    return new InstanceOfPatternExpressionInfo(newTarget, isUUID);
                }
            }
        }
        return null;
    }

    static boolean isCommonMethod(final PsiMethod psiMethod) {
        final boolean isCommonConstructor = isCommonConstructor(psiMethod);
        if(isCommonConstructor) {
            return true;
        }

        final PsiClass containingClass = psiMethod.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        final String containingClassQualifiedName = containingClass.getQualifiedName();
        if(containingClassQualifiedName == null) {
            return false;
        }
        if(StringUtils.endsWithAny(containingClassQualifiedName, ".AttributeValue", ".AttributeValue.Builder")) {
            return true;
        }
        return false;
    }

    private static boolean isCommonConstructor(final PsiMethod psiMethod) {
        if(!psiMethod.isConstructor()) {
            return false;
        }
        final PsiClass containingClass = psiMethod.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        if(InheritanceUtil.isInheritor(containingClass, JAVA_LANG_THROWABLE)) {
            return true;
        }
        if(InheritanceUtil.isInheritor(containingClass, "com.amazonaws.services.dynamodbv2.model.AttributeValue")) {
            return true;
        }
        final String containingClassQualifiedName = containingClass.getQualifiedName();
        if(containingClassQualifiedName == null) {
            return false;
        }
        if(StringUtils.endsWithAny(containingClassQualifiedName, ".AttributeValue", ".AttributeValue.Builder")) {
            return true;
        }
        return false;
    }

    public static PsiParameter getOnlyParam(@NotNull final PsiMethod calledMethod) {
        final PsiParameter[] params = calledMethod.getParameterList().getParameters();
        if(params.length != 1) {
            return null;
        }
        return params[0];
    }

    @Nullable
    public static PsiVariable getLombokTargetVariable(final PsiMethod psiMethod) {
        if(psiMethod == null) {
            return null;
        }
        if(!StringUtils.contains(psiMethod.getClass().getCanonicalName(), JavaNames.LombokLightMethodName)) {
            return null;
        }
        final PsiElement navTarget = psiMethod.getNavigationElement();
        if(navTarget == null) {
            return null;
        }
        if(navTarget instanceof LightElement) {
            return null;
        }
        if(!(navTarget instanceof PsiVariable)) {
            return null;
        }
        return (PsiVariable) navTarget;
    }
}
