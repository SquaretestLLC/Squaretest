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
package com.squaretest.generation.defaulttypes.beans;

import com.intellij.psi.*;
import com.intellij.psi.impl.light.LightElement;
import com.intellij.psi.impl.light.LightRecordField;
import com.intellij.psi.impl.light.LightRecordMethod;
import com.intellij.psi.util.InheritanceUtil;
import com.intellij.util.ThreeState;
import com.squaretest.generation.CompiledUtils;
import com.squaretest.generation.InitStrategy;
import com.squaretest.generation.VisibilityInfoProvider;
import com.squaretest.generation.dataflow.ValidationMethodUtils;
import com.squaretest.generation.defaulttypes.DefaultTypeInfo;
import com.squaretest.generation.defaulttypes.DefaultTypesHolder;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.defaulttypes.SQAnnotationUtil;
import com.squaretest.generation.defaulttypes.TypeCreatorUtil;
import com.squaretest.generation.sourcevars.builders.LombokBuilderSourceVariableProvider;
import com.squaretest.utils.IdentityMultimapWithIdentitySets;
import com.squaretest.utils.MultimapWithIdentitySets;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.siyeh.ig.psiutils.MethodUtils.isCopyConstructor;


public class BeanInfoProvider {

    private static final String AsStringSuffix = "AsString";
    private static final String[] Protobuf3GetterSuffixes = new String[]{"Value", "Bytes", "Map", "OrBuilderList", "List", "OrBuilder"};
    @NotNull
    private final DefaultTypesHolder defaultTypesHolder;
    @NotNull
    private final VisibilityInfoProvider visibilityInfoProvider;
    private final Map<String, BeanInfo> canonicalNamesToBeanInfoMap;
    private final Map<PsiMethod, ConfirmedInfo> methodToConfirmedSetterInfoMap;
    private final Map<PsiMethod, ConfirmedInfo> methodToConfirmedGetterInfoMap;

    public BeanInfoProvider(
            @NotNull final DefaultTypesHolder defaultTypesHolder,
            @NotNull final VisibilityInfoProvider visibilityInfoProvider) {
        this.defaultTypesHolder = defaultTypesHolder;
        this.visibilityInfoProvider = visibilityInfoProvider;
        this.canonicalNamesToBeanInfoMap = new HashMap<>();
        this.methodToConfirmedSetterInfoMap = new IdentityHashMap<>();
        this.methodToConfirmedGetterInfoMap = new IdentityHashMap<>();
    }

    public boolean isBean(@Nullable PsiClass psiClass, final InitStrategy initStrategy) {
        psiClass = CompiledUtils.getClassWithSourceCode(psiClass);
        if(psiClass == null) {
            return false;
        }
        final String qualifiedName = psiClass.getQualifiedName();
        if(qualifiedName == null) {
            return false;
        }

        // Determine if the class is a DTO bean using the default logic.
        final boolean isDtoBean = determineIfDtoBean(psiClass, qualifiedName);
        if(isDtoBean) {
            return true;
        }

        if(isSpecialClassThatIsNotBean(psiClass)) {
            return false;
        }

        // Determine if the class is a bean according to the provided initStrategy.
        if(initStrategy.ordinal() >= InitStrategy.DataBeans.ordinal()) {
            if(isDataBean(psiClass, qualifiedName)) {
                return true;
            }
        }
        if(initStrategy.ordinal() >= InitStrategy.GeneralBeans.ordinal()) {
            return isGeneralBean(psiClass, qualifiedName);
        }
        return false;
    }

    private boolean isSpecialClassThatIsNotBean(final PsiClass psiClass) {
        final String qualifiedName = psiClass.getQualifiedName();
        if(StringUtils.startsWith(qualifiedName, "com.amazonaws")) {
            // If this is an AWS V1 class that has a constructor with args, it is not a bean.
            return Arrays.stream(psiClass.getConstructors()).anyMatch(x -> visibilityInfoProvider.isVisibleToTestClass(x) && x.getParameterList().getParameters().length > 0);
        }
        // If this is a class similar to the AWS AttributeValue class it is also not a bean.
        final boolean isAttributeValueName = StringUtils.endsWith(qualifiedName, ".AttributeValue");
        return isAttributeValueName && Arrays.stream(psiClass.getConstructors()).anyMatch(x -> visibilityInfoProvider.isVisibleToTestClass(x) && x.getParameterList().getParameters().length == 1);
    }

    private boolean determineIfDtoBean(@NotNull final PsiClass psiClass, final String qualifiedName) {
        // Check to see if we've already determined if this is a DTO bean.
        BeanInfo info = canonicalNamesToBeanInfoMap.get(qualifiedName);
        if(info != null) {
            return info.isDtoBean();
        }

        // Check to see if this is a default type.
        final DefaultTypeInfo defaultTypeInfo = defaultTypesHolder.get(qualifiedName);
        if(defaultTypeInfo != null) {
            canonicalNamesToBeanInfoMap.put(qualifiedName, new BeanInfo(defaultTypeInfo.isBean(), defaultTypeInfo.isBeanWithInputIOProperty()));
            return defaultTypeInfo.isBean();
        }

        // Perform a fast check to see if we can confirm this is not a DTO bean.
        if(!hasBeanSignatureAndConstructors(psiClass) || hasLombokBuilderSignatureAnnotations(psiClass)) {
            canonicalNamesToBeanInfoMap.put(qualifiedName, new BeanInfo(false, false));
            return false;
        }

        // We need to compute the full info to determine if this is a DTO bean.
        info = computeFullGetterSetterBeanInfo(qualifiedName, psiClass);
        canonicalNamesToBeanInfoMap.put(qualifiedName, info);
        return info.isDtoBean();
    }

    private static boolean hasLombokBuilderSignatureAnnotations(@NotNull final PsiClass psiClass) {
        final PsiMethod builderFactoryMethod = LombokBuilderSourceVariableProvider.getBuilderFactoryMethod(psiClass);
        if(builderFactoryMethod == null) {
            // If we do not have a builder factory method, we can treat this as a DTO Bean.
            return false;
        }
        if(builderFactoryMethod.hasModifierProperty(PsiModifier.PRIVATE)) {
            // If the factory method is private, we can treat this as a DTOBean.
            return false;
        }

        if(SQAnnotationUtil.hasAnnotation(psiClass, JavaNames.LombokSuperBuilders)) {
            return true;
        }
        // We have a @Builder annotation and everything is valid.
        // If we inherit from a @Data class this can be a DTO Bean. The reason is: this builder will not allow us
        // to set properties that are used in the super class. It is better to treat this as a bean in that case.
        if(hasNoPropertiesAndExtendsFromDataClassWithProperties(psiClass)) {
            return false;
        }
        return true;
    }

    private static boolean hasNoPropertiesAndExtendsFromDataClassWithProperties(@NotNull final PsiClass psiClass) {
        final PsiClass superClass = psiClass.getSuperClass();
        if(superClass == null) {
            return false;
        }
        return psiClass.getFields().length == 0
                && Arrays.stream(superClass.getAnnotations()).anyMatch(x -> StringUtils.startsWithAny(x.getQualifiedName(), "lombok"))
                && superClass.getFields().length != 0;
    }

    public boolean isDtoBeanWithInputIoProperty(@Nullable PsiClass psiClass) {
        psiClass = CompiledUtils.getClassWithSourceCode(psiClass);
        if(psiClass == null) {
            return false;
        }
        final String qualifiedName = psiClass.getQualifiedName();
        if(qualifiedName == null) {
            return false;
        }
        if(!isBean(psiClass, InitStrategy.Default)) {
            return false;
        }

        BeanInfo info = canonicalNamesToBeanInfoMap.get(qualifiedName);
        return info != null && info.isDtoBeanWithInputIoProperty();
    }

    public boolean isSetter(@Nullable PsiMethod psiMethod) {
        psiMethod = CompiledUtils.getMethodWithSourceCode(psiMethod);
        if(psiMethod == null) {
            return false;
        }
        final PsiClass containingClass = CompiledUtils.getClassWithSourceCode(psiMethod.getContainingClass());
        if(containingClass == null) {
            return false;
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName == null) {
            return false;
        }
        final BeanInfo info = getOrComputeFullInfo(qualifiedName, containingClass);
        return info.getBeanMethodsInfo().isSetter(psiMethod);
    }

    @NotNull
    public ThreeState getConfirmedSetterStatusByCode(@Nullable PsiMethod psiMethod) {
        return getSetterConfirmedByCodeInfo(psiMethod).state();
    }

    private ConfirmedInfo getSetterConfirmedByCodeInfo(@Nullable PsiMethod psiMethod) {
        psiMethod = CompiledUtils.getMethodWithSourceCode(psiMethod);
        if(psiMethod == null) {
            return ConfirmedInfo.No;
        }
        if(psiMethod instanceof LightElement) {
            return ConfirmedInfo.Unknown;
        }
        return methodToConfirmedSetterInfoMap.computeIfAbsent(psiMethod, this::getSetterConfirmedInfoImpl);
    }

    private ConfirmedInfo getSetterConfirmedInfoImpl(@NotNull final PsiMethod psiMethod) {
        // If the method has no body, return unsure.
        // If the method has a body, inspect it and determine if it is a setter and has a target field.
        final PsiCodeBlock body = psiMethod.getBody();
        if(body == null) {
            return ConfirmedInfo.Unknown;
        }
        final PsiParameter[] params = psiMethod.getParameterList().getParameters();
        if(params.length != 1) {
            return ConfirmedInfo.No;
        }
        final PsiStatement[] statements = body.getStatements();
        if(statements.length == 0) {
            return ConfirmedInfo.No;
        }

        PsiField targetField = null;
        boolean hasReturnsThisStatement = false;
        for(final PsiStatement statement : statements) {
            final PsiField tempField = tryGetFieldAssignment(psiMethod, statement);
            if(tempField != null) {
                if(targetField == null) {
                    targetField = tempField;
                    continue;
                } else {
                    return ConfirmedInfo.No;
                }
            }
            // This is not a field assignment statement.
            // Determine if this is an allowed statement.
            if(isReturnsThis(statement)) {
                hasReturnsThisStatement = true;
                continue;
            }
            if(isValidationCall(statement) || isLogStatement(statement) || isPrintStatement(statement)) {
                continue;
            }
            return ConfirmedInfo.No;
        }

        if(targetField == null) {
            return ConfirmedInfo.No;
        }
        final String methodName = psiMethod.getName();
        if(methodName.startsWith("with") && methodName.length() > 4 && hasReturnsThisStatement) {
            return ConfirmedInfo.No;
        }
        return new ConfirmedInfo(ThreeState.YES, targetField);
    }

    @NotNull
    public ThreeState getConfirmedGetterStatusByCode(@Nullable PsiMethod psiMethod) {
        return getGetterConfirmedByCodeInfo(psiMethod).state();
    }

    private ConfirmedInfo getGetterConfirmedByCodeInfo(@Nullable PsiMethod psiMethod) {
        psiMethod = CompiledUtils.getMethodWithSourceCode(psiMethod);
        if(psiMethod == null) {
            return ConfirmedInfo.No;
        }
        if(psiMethod instanceof LightElement) {
            return ConfirmedInfo.Unknown;
        }
        return methodToConfirmedGetterInfoMap.computeIfAbsent(psiMethod, this::getGetterConfirmedInfoImpl);
    }

    private ConfirmedInfo getGetterConfirmedInfoImpl(@NotNull final PsiMethod psiMethod) {
        // If the method has no body, return unsure.
        // If the method has a body, inspect it and determine if it is a setter and has a target field.
        final PsiCodeBlock body = psiMethod.getBody();
        if(body == null) {
            return ConfirmedInfo.Unknown;
        }
        final PsiParameter[] params = psiMethod.getParameterList().getParameters();
        if(params.length != 0) {
            return ConfirmedInfo.No;
        }
        final PsiStatement[] statements = body.getStatements();
        if(statements.length == 0) {
            return ConfirmedInfo.No;
        }

        PsiField targetField = null;
        for(final PsiStatement statement : statements) {
            final PsiField tempField = tryGetFieldReturn(psiMethod, statement);
            if(tempField != null) {
                if(targetField == null) {
                    targetField = tempField;
                    continue;
                } else {
                    return ConfirmedInfo.No;
                }
            }
            // This is not a field assignment statement.
            // Determine if this is an allowed statement.
            if(isLogStatement(statement) || isPrintStatement(statement)) {
                continue;
            }
            return ConfirmedInfo.No;
        }

        if(targetField == null) {
            return ConfirmedInfo.No;
        }
        return new ConfirmedInfo(ThreeState.YES, targetField);
    }

    private PsiField tryGetFieldReturn(final PsiMethod psiMethod, final PsiStatement statement) {
        if(!(statement instanceof final PsiReturnStatement returnStatement)) {
            return null;
        }
        PsiExpression returnValue = returnStatement.getReturnValue();
        if(returnValue == null) {
            return null;
        }
        returnValue = removeExtras(returnValue);
        if(!(returnValue instanceof PsiReferenceExpression)) {
            return null;
        }
        final PsiElement refTarget = ((PsiReferenceExpression) returnValue).resolve();
        if(!(refTarget instanceof final PsiField targetField)) {
            return null;
        }
        final PsiClass methodContainingClass = psiMethod.getContainingClass();
        final PsiClass fieldContainingClass = targetField.getContainingClass();
        if(methodContainingClass != fieldContainingClass) {
            return null;
        }
        if(targetField.hasModifierProperty(PsiModifier.STATIC)) {
            return null;
        }
        return targetField;
    }

    private boolean isLogStatement(final PsiStatement statement) {
        if(!(statement instanceof final PsiExpressionStatement psiExpressionStatement)) {
            return false;
        }
        final PsiExpression expression = psiExpressionStatement.getExpression();
        if(!(expression instanceof final PsiMethodCallExpression psiMethodCallExpression)) {
            return false;
        }
        final PsiMethod psiMethod = psiMethodCallExpression.resolveMethod();
        if(psiMethod == null) {
            return false;
        }
        final PsiExpression qualifierExpression = psiMethodCallExpression.getMethodExpression().getQualifierExpression();
        if(!(qualifierExpression instanceof PsiReferenceExpression)) {
            return false;
        }
        final PsiElement qualifierTarget = ((PsiReferenceExpression) qualifierExpression).resolve();
        if(!(qualifierTarget instanceof final PsiField qualifierField)) {
            return false;
        }
        // At least one of the following must be true:
        // Has a "Logger" name, is static, or is final.
        if(!qualifierField.hasModifierProperty(PsiModifier.STATIC) && !hasLogName(qualifierField) && !qualifierField.hasModifierProperty(PsiModifier.FINAL)) {
            return false;
        }
        final PsiClass containingClass = psiMethod.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        if(StringUtils.equalsAny(containingClass.getName(), "Log", "Logger")) {
            return true;
        }
        return false;
    }

    private boolean hasLogName(final PsiField qualifierField) {
        return StringUtils.equalsAny(qualifierField.getName(), "Log", "Logger", "_Log", "_Logger", "ourLogger", "myLogger");
    }

    private boolean isPrintStatement(final PsiStatement statement) {
        if(!(statement instanceof final PsiExpressionStatement psiExpressionStatement)) {
            return false;
        }
        final PsiExpression expression = psiExpressionStatement.getExpression();
        if(!(expression instanceof final PsiMethodCallExpression psiMethodCallExpression)) {
            return false;
        }
        final PsiMethod psiMethod = psiMethodCallExpression.resolveMethod();
        if(psiMethod == null) {
            return false;
        }
        final PsiClass containingClass = psiMethod.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        final PsiExpression qualifierExpression = psiMethodCallExpression.getMethodExpression().getQualifierExpression();
        if(!(qualifierExpression instanceof PsiReferenceExpression)) {
            return false;
        }
        final PsiElement qualifierTarget = ((PsiReferenceExpression) qualifierExpression).resolve();
        if(!(qualifierTarget instanceof final PsiField qualifierField)) {
            return false;
        }
        // System.out and System.err are both final.
        if(!qualifierField.hasModifierProperty(PsiModifier.STATIC)) {
            return false;
        }
        final PsiReferenceExpression methodExpression = psiMethodCallExpression.getMethodExpression();
        return StringUtils.startsWithAny(methodExpression.getText(), "System.out", "System.err");
    }

    private boolean isValidationCall(final PsiStatement statement) {
        if(!(statement instanceof PsiExpressionStatement)) {
            return false;
        }
        final PsiExpression expression = ((PsiExpressionStatement) statement).getExpression();
        final PsiExpression expressionWithinValidationMethod = ValidationMethodUtils.getExpressionWithinValidationMethod(expression);
        return expressionWithinValidationMethod != null;
    }

    private PsiField tryGetFieldAssignment(@NotNull final PsiMethod psiMethod, @NotNull final PsiStatement statement) {
        if(!(statement instanceof final PsiExpressionStatement psiExpressionStatement)) {
            return null;
        }
        final PsiExpression expression = psiExpressionStatement.getExpression();
        if(!(expression instanceof final PsiAssignmentExpression psiAssignmentExpression)) {
            return null;
        }
        final PsiExpression left = psiAssignmentExpression.getLExpression();
        if(!(left instanceof PsiReferenceExpression)) {
            return null;
        }
        final PsiElement leftTarget = ((PsiReferenceExpression) left).resolve();
        if(!(leftTarget instanceof final PsiField leftField)) {
            return null;
        }
        if(leftField.getContainingClass() != psiMethod.getContainingClass()) {
            return null;
        }

        PsiExpression right = psiAssignmentExpression.getRExpression();
        if(right == null) {
            return null;
        }
        right = removeExtras(right);

        if(!(right instanceof final PsiReferenceExpression rightRef)) {
            return null;
        }
        final PsiElement rightTarget = rightRef.resolve();
        if(!(rightTarget instanceof final PsiParameter rightParam)) {
            return null;
        }
        final PsiElement rightParamDeclarationScope = rightParam.getDeclarationScope();
        if(rightParamDeclarationScope != psiMethod) {
            // Ensure we have a param of the provided method.
            return null;
        }
        return leftField;
    }

    private boolean isReturnsThis(final PsiStatement statement) {
        if(!(statement instanceof final PsiReturnStatement returnStatement)) {
            return false;
        }
        PsiExpression returnValue = returnStatement.getReturnValue();
        if(returnValue == null) {
            return false;
        }
        // PsiThisExpression.
        returnValue = removeExtras(returnValue);
        if(!(returnValue instanceof PsiThisExpression)) {
            return false;
        }
        return true;
    }

    @Nullable
    private static PsiExpression removeExtras(PsiExpression expression) {
        while(true) {
            if(expression instanceof PsiParenthesizedExpression) {
                expression = ((PsiParenthesizedExpression) expression).getExpression();
                continue;
            }
            if(expression instanceof PsiTypeCastExpression) {
                expression = ((PsiTypeCastExpression) expression).getOperand();
                continue;
            }
            final PsiExpression expressionInValidationMethod = ValidationMethodUtils.getExpressionWithinValidationMethod(expression);
            if(expressionInValidationMethod != null) {
                expression = expressionInValidationMethod;
                continue;
            }
            return expression;
        }
    }

    public boolean isGetter(@Nullable PsiMethod psiMethod) {
        psiMethod = CompiledUtils.getMethodWithSourceCode(psiMethod);
        if(psiMethod == null) {
            return false;
        }
        final PsiClass containingClass = CompiledUtils.getClassWithSourceCode(psiMethod.getContainingClass());
        if(containingClass == null) {
            return false;
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName == null) {
            return false;
        }
        final BeanInfo info = getOrComputeFullInfo(qualifiedName, containingClass);
        return info.getBeanMethodsInfo().isGetter(psiMethod);
    }

    public boolean isJAXBGetter(PsiMethod psiMethod) {
        psiMethod = CompiledUtils.getMethodWithSourceCode(psiMethod);
        if(psiMethod == null) {
            return false;
        }
        final PsiClass containingClass = CompiledUtils.getClassWithSourceCode(psiMethod.getContainingClass());
        if(containingClass == null) {
            return false;
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName == null) {
            return false;
        }
        final BeanInfo info = getOrComputeFullInfo(qualifiedName, containingClass);
        return info.getBeanMethodsInfo().isJaxBGetter(psiMethod);
    }

    public PsiField getFieldForGetter(PsiMethod psiMethod) {
        psiMethod = CompiledUtils.getMethodWithSourceCode(psiMethod);
        if(psiMethod == null) {
            return null;
        }
        final PsiClass containingClass = CompiledUtils.getClassWithSourceCode(psiMethod.getContainingClass());
        if(containingClass == null) {
            return null;
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName == null) {
            return null;
        }
        if(psiMethod instanceof LightRecordMethod) {
            final PsiRecordComponent recordComponent = ((LightRecordMethod) psiMethod).getRecordComponent();
            final String recordName = recordComponent.getName();
            return containingClass.findFieldByName(recordName, false);
        }
        final BeanInfo beanInfo = getOrComputeFullInfo(qualifiedName, containingClass);
        final BeanMethodsInfo beanMethodsInfo = beanInfo.getBeanMethodsInfo();
        return beanMethodsInfo.getFieldForGetter(psiMethod);
    }

    public PsiField getFieldForSetter(PsiMethod psiMethod) {
        psiMethod = CompiledUtils.getMethodWithSourceCode(psiMethod);
        if(psiMethod == null) {
            return null;
        }
        final PsiClass containingClass = CompiledUtils.getClassWithSourceCode(psiMethod.getContainingClass());
        if(containingClass == null) {
            return null;
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName == null) {
            return null;
        }
        if(psiMethod instanceof LightRecordMethod) {
            return null;
        }
        final BeanInfo beanInfo = getOrComputeFullInfo(qualifiedName, containingClass);
        final BeanMethodsInfo beanMethodsInfo = beanInfo.getBeanMethodsInfo();
        return beanMethodsInfo.getFieldForSetter(psiMethod);
    }

    public PsiField getFieldForWithMethod(PsiMethod psiMethod) {
        psiMethod = CompiledUtils.getMethodWithSourceCode(psiMethod);
        if(psiMethod == null) {
            return null;
        }
        final PsiClass containingClass = CompiledUtils.getClassWithSourceCode(psiMethod.getContainingClass());
        if(containingClass == null) {
            return null;
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName == null) {
            return null;
        }
        if(psiMethod instanceof LightRecordMethod) {
            return null;
        }
        final BeanInfo beanInfo = getOrComputeFullInfo(qualifiedName, containingClass);
        final BeanMethodsInfo beanMethodsInfo = beanInfo.getBeanMethodsInfo();
        return beanMethodsInfo.getFieldForWithMethod(psiMethod);
    }

    public Set<PsiMethod> getSettersForField(PsiField targetField) {
        targetField = CompiledUtils.getFieldWithSourceCode(targetField);
        if(targetField == null) {
            return Collections.emptySet();
        }
        final PsiClass containingClass = CompiledUtils.getClassWithSourceCode(targetField.getContainingClass());
        if(containingClass == null) {
            return Collections.emptySet();
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName == null) {
            return Collections.emptySet();
        }
        if(targetField instanceof LightRecordField) {
            return Collections.emptySet();
        }
        final BeanInfo beanInfo = getOrComputeFullInfo(qualifiedName, containingClass);
        final BeanMethodsInfo beanMethodsInfo = beanInfo.getBeanMethodsInfo();
        return beanMethodsInfo.getSettersForField(targetField);
    }

    public Set<PsiMethod> getGettersForField(PsiField targetField) {
        targetField = CompiledUtils.getFieldWithSourceCode(targetField);
        if(targetField == null) {
            return Collections.emptySet();
        }
        final PsiClass containingClass = CompiledUtils.getClassWithSourceCode(targetField.getContainingClass());
        if(containingClass == null) {
            return Collections.emptySet();
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName == null) {
            return Collections.emptySet();
        }
        final BeanInfo beanInfo = getOrComputeFullInfo(qualifiedName, containingClass);
        final BeanMethodsInfo beanMethodsInfo = beanInfo.getBeanMethodsInfo();
        return beanMethodsInfo.getGettersForField(targetField);
    }

    public Set<PsiMethod> getWithMethodsForField(PsiField targetField) {
        targetField = CompiledUtils.getFieldWithSourceCode(targetField);
        if(targetField == null) {
            return Collections.emptySet();
        }
        final PsiClass containingClass = CompiledUtils.getClassWithSourceCode(targetField.getContainingClass());
        if(containingClass == null) {
            return Collections.emptySet();
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName == null) {
            return Collections.emptySet();
        }
        final BeanInfo beanInfo = getOrComputeFullInfo(qualifiedName, containingClass);
        final BeanMethodsInfo beanMethodsInfo = beanInfo.getBeanMethodsInfo();
        return beanMethodsInfo.getWithMethodsForField(targetField);
    }

    @NotNull
    private BeanInfo getOrComputeFullInfo(@NotNull final String qualifiedName, final PsiClass psiClass) {
        BeanInfo info = canonicalNamesToBeanInfoMap.get(qualifiedName);
        if(info == null || !info.hasBeanMethodsInfo()) {
            info = computeFullGetterSetterBeanInfo(qualifiedName, psiClass);
            canonicalNamesToBeanInfoMap.put(qualifiedName, info);
        }
        return info;
    }

    private BeanInfo computeFullGetterSetterBeanInfo(
            final String qualifiedName, final PsiClass psiClass) {
        final List<PsiField> instanceFields = Arrays.stream(psiClass.getFields()).filter(
                x -> !x.hasModifierProperty(PsiModifier.STATIC)).collect(Collectors.toList());
        final boolean isProtobufMessage = InheritanceUtil.isInheritor(psiClass, "com.google.protobuf.Message");
        final boolean isAwsV2Class = StringUtils.startsWith(psiClass.getQualifiedName(), "software.amazon.awssdk");
        // Compute primary and alternate names for fields.
        final String prefix = BeanDetectorUtils.determinePrefixIfExists(instanceFields);
        final MultimapWithIdentitySets<String, PsiField> primaryNameToFieldsMap = new MultimapWithIdentitySets<>();
        for(final PsiField field : instanceFields) {
            final String fieldName = field.getName();
            String primaryName = BeanDetectorUtils.removeSuffix(fieldName);
            if(prefix != null && primaryName.startsWith(prefix)) {
                primaryName = StringUtils.removeStart(primaryName, prefix);
            }
            primaryName = primaryName.toLowerCase();
            primaryNameToFieldsMap.put(primaryName, field);
            primaryNameToFieldsMap.put(fieldName.toLowerCase(), field);
            if(BeanDetectorUtils.nameHasPrefix(fieldName, "is")) {
                primaryNameToFieldsMap.put(StringUtils.removeStart(fieldName, "is").toLowerCase(), field);
            }
        }

        // Find the getters with traditional getter/setter names.
        final PsiMethod[] methods = psiClass.getMethods();
        final List<PsiMethod> publicInstanceMethods = Arrays.stream(methods).filter(x -> !x.hasModifierProperty(PsiModifier.STATIC) && visibilityInfoProvider.isVisibleToTestClass(x)).collect(Collectors.toList());

        final List<PsiMethod> possibleGetters = publicInstanceMethods.stream().filter(
                x -> hasGetterReturnTypeArgsAndQualifiers(x) && !StringUtils.equalsAny(x.getName(), BeanDetectorUtils.AllowedNonGetterMethods)).toList();
        // Compute names for getter properties.
        final MultimapWithIdentitySets<String, PsiMethod> nameToGettersMap = new MultimapWithIdentitySets<>();
        for(final PsiMethod getter : possibleGetters) {
            final String name = getter.getName();
            for(final String getterPrefix : BeanDetectorUtils.GetterPrefixes) {
                if(BeanDetectorUtils.nameHasPrefix(name, getterPrefix)) {
                    nameToGettersMap.put(StringUtils.removeStart(name, getterPrefix).toLowerCase(), getter);
                } else if(name.startsWith(getterPrefix)) {
                    // Handle the case where: fieldName = eTag and getter name is geteTag(). This seems broken, but it is the
                    // correct getter name per the Java bean spec; see https://intellij-support.jetbrains.com/hc/en-us/community/posts/206879995-bean-naming-conventions-getter-setter.
                    nameToGettersMap.put(StringUtils.removeStart(name, getterPrefix).toLowerCase(), getter);
                }
            }
            nameToGettersMap.put(name.toLowerCase(), getter);
            if(isProtobufMessage) {
                String nameWithoutPrefix = StringUtils.removeStart(name, "get");
                for(final String suffix : Protobuf3GetterSuffixes) {
                    if(StringUtils.endsWith(nameWithoutPrefix, suffix)) {
                        nameToGettersMap.put(StringUtils.removeEnd(nameWithoutPrefix, suffix).toLowerCase(), getter);
                        break;
                    }
                }
            }
            if(isAwsV2Class) {
                if(name.endsWith(AsStringSuffix) && name.length() > AsStringSuffix.length()) {
                    nameToGettersMap.put(StringUtils.removeEnd(name, AsStringSuffix).toLowerCase(), getter);
                }
            }
        }

        // Compute names for setter properties.
        final List<PsiMethod> possibleSetters = publicInstanceMethods.stream().filter(
                x -> hasSetterReturnTypeArgsAndQualifiers(x) && !StringUtils.equalsAny(x.getName(), BeanDetectorUtils.AllowedNonGetterMethods)).toList();
        final MultimapWithIdentitySets<String, PsiMethod> nameToSettersMap = new MultimapWithIdentitySets<>();
        for(final PsiMethod setter : possibleSetters) {
            final String name = setter.getName();
            for(final String setterPrefix : BeanDetectorUtils.SetterPrefixes) {
                if(BeanDetectorUtils.nameHasPrefix(name, setterPrefix)) {
                    nameToSettersMap.put(StringUtils.removeStart(name, setterPrefix).toLowerCase(), setter);
                } else if(name.startsWith(setterPrefix)) {
                    nameToSettersMap.put(StringUtils.removeStart(name, setterPrefix).toLowerCase(), setter);
                }
            }
            nameToSettersMap.put(name.toLowerCase(), setter);
        }

        // Compute the getters to fields map.
        final IdentityHashMap<PsiMethod, PsiField> getterToFieldMap = new IdentityHashMap<>();
        final IdentityMultimapWithIdentitySets<PsiField, PsiMethod> fieldToGettersMultimap = new IdentityMultimapWithIdentitySets<>();
        for(final String name : primaryNameToFieldsMap.keySet()) {
            final Set<PsiField> fieldsForName = primaryNameToFieldsMap.get(name);
            final Set<PsiMethod> gettersWithName = nameToGettersMap.get(name);
            BeanDetectorUtils.matchFieldsAndGetters(prefix, fieldToGettersMultimap, getterToFieldMap, fieldsForName, gettersWithName);
        }

        // Match lombok getters.
        for(final PsiMethod getter : possibleGetters) {
            if(StringUtils.contains(getter.getClass().getName(), JavaNames.LombokLight)) {
                final PsiElement targetElement = getter.getNavigationElement();
                if(targetElement instanceof final PsiField targetField) {
                    fieldToGettersMultimap.put(targetField, getter);
                    getterToFieldMap.put(getter, targetField);
                }
            }
        }

        // Match getters confirmed by code.
        for(final PsiMethod getter : possibleGetters) {
            final ConfirmedInfo confirmedByCodeInfo = getGetterConfirmedByCodeInfo(getter);
            final PsiField targetField = confirmedByCodeInfo.targetField();
            if(confirmedByCodeInfo.state() != ThreeState.YES || targetField == null) {
                continue;
            }
            fieldToGettersMultimap.put(targetField, getter);
            getterToFieldMap.put(getter, targetField);
        }

        // Compute the setters to fields map.
        final IdentityHashMap<PsiMethod, PsiField> setterToFieldMap = new IdentityHashMap<>();
        final IdentityMultimapWithIdentitySets<PsiField, PsiMethod> fieldToSettersMultimap = new IdentityMultimapWithIdentitySets<>();
        for(final String name : primaryNameToFieldsMap.keySet()) {
            final Set<PsiField> fieldsForName = primaryNameToFieldsMap.get(name);
            final Set<PsiMethod> settersWithName = nameToSettersMap.get(name);
            BeanDetectorUtils.matchFieldsAndSetters(prefix, fieldToSettersMultimap, setterToFieldMap, fieldsForName, settersWithName);
        }

        // Match lombok setters.
        if(!StringUtils.contains(psiClass.getClass().getName(), JavaNames.LombokLight)) {
            for(final PsiMethod setter : possibleSetters) {
                if(!StringUtils.contains(setter.getClass().getName(), JavaNames.LombokLight)) {
                    continue;
                }
                final PsiElement targetElement = setter.getNavigationElement();
                if(!(targetElement instanceof final PsiField targetField)) {
                    continue;
                }
                if(setter.getName().startsWith("with") && !targetField.getName().startsWith("with")) {
                    // This is a with method, not a setter.
                    continue;
                }
                fieldToSettersMultimap.put(targetField, setter);
                setterToFieldMap.put(setter, targetField);
            }
        }

        // Match setters confirmed by code.
        for(final PsiMethod setter : possibleSetters) {
            final ConfirmedInfo confirmedByCodeInfo = getSetterConfirmedByCodeInfo(setter);
            final PsiField targetField = confirmedByCodeInfo.targetField();
            if(confirmedByCodeInfo.state() != ThreeState.YES || targetField == null) {
                continue;
            }
            fieldToSettersMultimap.put(targetField, setter);
            setterToFieldMap.put(setter, targetField);
        }

        // Compute names for with properties.
        final List<PsiMethod> possibleWithMethods = publicInstanceMethods.stream().filter(
                x -> hasWithMethodReturnTypeArgsAndQualifiers(x) && !StringUtils.equalsAny(x.getName(), BeanDetectorUtils.AllowedNonGetterMethods)).toList();
        final MultimapWithIdentitySets<String, PsiMethod> nameToWithMethodsMap = new MultimapWithIdentitySets<>();
        for(final PsiMethod withMethod : possibleWithMethods) {
            final String name = withMethod.getName();
            for(final String withMethodPrefix : BeanDetectorUtils.WithMethodPrefixes) {
                if(BeanDetectorUtils.nameHasPrefix(name, withMethodPrefix)) {
                    nameToWithMethodsMap.put(StringUtils.removeStart(name, withMethodPrefix).toLowerCase(), withMethod);
                } else if(name.startsWith(withMethodPrefix)) {
                    // Handle the case where: fieldName = eTag and getter name is witheTag(). This seems broken, but it is the
                    // correct with name per the Java bean spec; see https://intellij-support.jetbrains.com/hc/en-us/community/posts/206879995-bean-naming-conventions-getter-setter.
                    nameToWithMethodsMap.put(StringUtils.removeStart(name, withMethodPrefix).toLowerCase(), withMethod);
                }
            }
            nameToWithMethodsMap.put(name.toLowerCase(), withMethod);
        }

        // Match with methods.
        final IdentityHashMap<PsiMethod, PsiField> withMethodToFieldMap = new IdentityHashMap<>();
        final IdentityMultimapWithIdentitySets<PsiField, PsiMethod> fieldToWithMethodsMultimap = new IdentityMultimapWithIdentitySets<>();
        for(final String name : primaryNameToFieldsMap.keySet()) {
            final Set<PsiField> fieldsForName = primaryNameToFieldsMap.get(name);
            final Set<PsiMethod> withMethodsWithName = nameToWithMethodsMap.get(name);
            BeanDetectorUtils.matchFieldsAndWithMethods(prefix, fieldToWithMethodsMultimap, withMethodToFieldMap, fieldsForName, withMethodsWithName);
        }

        // Match lombok with methods.
        if(!StringUtils.contains(psiClass.getClass().getName(), JavaNames.LombokLight)) {
            for(final PsiMethod withMethod : possibleWithMethods) {
                if(StringUtils.contains(withMethod.getClass().getName(), JavaNames.LombokLight)) {
                    final PsiElement targetElement = withMethod.getNavigationElement();
                    if(targetElement instanceof final PsiField targetField) {
                        fieldToWithMethodsMultimap.put(targetField, withMethod);
                        withMethodToFieldMap.put(withMethod, targetField);
                    }
                }
            }
        }

        // Determine the special, JaxBGetters.
        final Set<PsiMethod> jaxBGetters = determineJaxBGetters(instanceFields, fieldToGettersMultimap, fieldToSettersMultimap);
        final BeanMethodsInfo beanMethodsInfo = new BeanMethodsInfo(
                getterToFieldMap,
                setterToFieldMap, withMethodToFieldMap, fieldToGettersMultimap, fieldToSettersMultimap, fieldToWithMethodsMultimap, jaxBGetters);

        // Determine the DTO Bean status.
        boolean isBean = determineIfBean(qualifiedName, psiClass, instanceFields, publicInstanceMethods, getterToFieldMap, setterToFieldMap, fieldToGettersMultimap, fieldToSettersMultimap, jaxBGetters);

        // Return the full info.
        return new BeanInfo(isBean, false, beanMethodsInfo);
    }

    private Set<PsiMethod> determineJaxBGetters(final List<PsiField> instanceFields, final IdentityMultimapWithIdentitySets<PsiField, PsiMethod> fieldToGettersMultimap, final IdentityMultimapWithIdentitySets<PsiField, PsiMethod> fieldToSettersMultimap) {
        Set<PsiMethod> jaxBGetters = SetUtils.newIdentityHashSet();
        for(final PsiField field : instanceFields) {
            final Set<PsiMethod> getters = fieldToGettersMultimap.get(field);
            if(getters.isEmpty()) {
                continue;
            }
            final Set<PsiMethod> setters = fieldToSettersMultimap.get(field);
            if(setters.isEmpty()) {
                jaxBGetters.addAll(getters.stream().filter(x -> BeanDetectorUtils.isPossibleJAXBGetter(x, field)).toList());
            }
        }
        if(jaxBGetters.isEmpty()) {
            return Collections.emptySet();
        }
        return jaxBGetters;
    }

    private boolean determineIfBean(
            final String qualifiedName, final PsiClass psiClass, final List<PsiField> instanceFields, final List<PsiMethod> publicInstanceMethods, final IdentityHashMap<PsiMethod, PsiField> getterToFieldMap,
            final IdentityHashMap<PsiMethod, PsiField> setterToFieldMap, final IdentityMultimapWithIdentitySets<PsiField, PsiMethod> fieldToGettersMultimap,
            final IdentityMultimapWithIdentitySets<PsiField, PsiMethod> fieldToSettersMultimap, final Set<PsiMethod> jaxBGetters) {
        final DefaultTypeInfo defaultTypeInfo = defaultTypesHolder.get(qualifiedName);
        if(defaultTypeInfo != null) {
            return defaultTypeInfo.isBean();
        }
        if(!hasBeanSignatureAndConstructors(psiClass)) {
            return false;
        }

        if(hasLombokBuilderSignatureAnnotations(psiClass)) {
            return false;
        }

        // If the class is annotated with Lombok @Data, the getters/setters will be generated at compile time.
        if(SQAnnotationUtil.hasDataAnnotation(psiClass)) {
            return true;
        }
        if(BeanDetectorUtils.nameEndsWithDto(psiClass)) {
            return true;
        }
        // A class with a default constructor and no mutable instance fields should not be considered a DTO bean.
        if(instanceFields.isEmpty()) {
            return false;
        }

        // If we have 2 or more getters AND 2 or more setters, consider this object to be a DTO bean.
        final int numSetters = setterToFieldMap.size();
        final int numGetters = getterToFieldMap.size();
        if(numGetters >= 1 && numSetters >= 1) {
            return true;
        }

        // Ensure all fields have a getter and setter. If they do not have a setter, it still might be a bean, as long as the
        // getter is a JAXBGetter. See https://stackoverflow.com/a/25507187.
        for(final PsiField field : instanceFields) {
            if(field.hasModifierProperty(PsiModifier.TRANSIENT)) {
                continue;
            }
            final Set<PsiMethod> getters = fieldToGettersMultimap.get(field);
            if(getters.isEmpty()) {
                return false;
            }
            final Set<PsiMethod> setters = fieldToSettersMultimap.get(field);
            if(setters.isEmpty() && getters.stream().noneMatch(jaxBGetters::contains)) {
                return false;
            }
        }

        // Ensure each method is a getter or setter.
        for(final PsiMethod method : publicInstanceMethods) {
            if(method.isConstructor() || StringUtils.equalsAny(method.getName(), BeanDetectorUtils.AllowedNonGetterMethods) || BeanDetectorUtils.isWithMethod(method)) {
                continue;
            }
            if(!getterToFieldMap.containsKey(method) && !setterToFieldMap.containsKey(method)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasBeanSignatureAndConstructors(final PsiClass psiClass) {
        if(psiClass.isEnum() || psiClass.isInterface() || psiClass.hasModifierProperty(PsiModifier.ABSTRACT) || TypeCreatorUtil.isNonStaticInnerClass(psiClass)) {
            return false;
        }
        // If we have more than one constructor, it's not a bean.
        final PsiMethod[] allConstructors = psiClass.getConstructors();
        final List<PsiMethod> filteredConstructors = Arrays.stream(allConstructors).filter(x -> visibilityInfoProvider.isVisibleToTestClass(x) && !isCopyConstructor(x)).toList();
        if(filteredConstructors.size() > 1) {
            return false;
        }
        // If we have one constructor, and it has an argument, it's not a bean.
        if(filteredConstructors.size() == 1) {
            if(filteredConstructors.get(0).getParameterList().getParameters().length != 0) {
                // If the constructor has an arg, return false.
                return false;
            }
        }

        if(filteredConstructors.isEmpty() && allConstructors.length != 0) {
            // The class has at least one constructor, but it is not visible or is a copy constructor.
            return false;
        }

        // The class only has the default constructor generated by the Java compiler.
        // Check to see if it has static factory methods that will be used instead.
        if(TypeCreatorUtil.hasPublicStaticCreatorMethodThatUsesSingleTypeParam(psiClass)) {
            // The class has a no-args constructor.
            // If the class has a single type parameter and also a static creator method that uses the type parameter,
            // it is not a bean. It will be initialized using the static creator method that uses an instance of the
            // type parameter.
            return false;
        }
        return true;
    }

    private boolean isDataBean(final PsiClass psiClass, final String qualifiedName) {
        if(psiClass.isEnum() || psiClass.isInterface() || psiClass.hasModifierProperty(PsiModifier.ABSTRACT)
                || hasLombokBuilderSignatureAnnotations(psiClass)) {
            return false;
        }

        // If we recognize the type, use the bean status from our config entry.
        final DefaultTypeInfo defaultTypeInfo = defaultTypesHolder.get(qualifiedName);
        if(defaultTypeInfo != null) {
            return defaultTypeInfo.isBean();
        }

        if(!hasVisibleZeroArgConstructor(psiClass)) {
            return false;
        }

        if(!SQAnnotationUtil.hasDataAnnotation(psiClass)) {
            return false;
        }

        if(psiClass.getTypeParameters().length != 0) {
            // If the class has a type parameter, do not consider it to be a bean.
            // It's better to invoke the constructor or static creator method that uses the type param(s).
            // Also the class that has the constructor calls that exceeded the init strategy limit is likely one of the
            // type params, not this class.
            return false;
        }
        return true;
    }

    private boolean isGeneralBean(final PsiClass psiClass, final String qualifiedName) {
        if(psiClass.isEnum() || psiClass.isInterface() || psiClass.hasModifierProperty(PsiModifier.ABSTRACT)
                || hasLombokBuilderSignatureAnnotations(psiClass)) {
            return false;
        }

        // If we recognize the type, use the bean status from our config entry.
        final DefaultTypeInfo defaultTypeInfo = defaultTypesHolder.get(qualifiedName);
        if(defaultTypeInfo != null) {
            return defaultTypeInfo.isBean();
        }

        if(!hasVisibleZeroArgConstructor(psiClass)) {
            return false;
        }

        if(psiClass.getTypeParameters().length != 0) {
            // If the class has a type parameter, do not consider it to be a bean.
            // It's better to invoke the constructor or static creator method that uses the type param(s).
            // Also the class that has the constructor calls that exceeded the init strategy limit is likely one of the
            // type params, not this class.
            return false;
        }
        return true;
    }

    private boolean hasVisibleZeroArgConstructor(final PsiClass psiClass) {
        final PsiMethod[] constructors = psiClass.getConstructors();
        return Arrays.stream(constructors).anyMatch(x -> x.getParameterList().getParameters().length == 0 && visibilityInfoProvider.isVisibleToTestClass(x));
    }

    boolean hasWithMethodReturnTypeArgsAndQualifiers(final PsiMethod method) {
        final String methodName = method.getName();
        return !method.hasModifierProperty(PsiModifier.STATIC) && visibilityInfoProvider.isVisibleToTestClass(method) && method.getParameterList().getParameters().length == 1 && method.getReturnType() != null && !PsiTypes.voidType().equals(
                method.getReturnType()) && !method.isConstructor() && BeanDetectorUtils.hasWithPrefix(methodName) && BeanDetectorUtils.isSameType(method.getReturnType(), method.getContainingClass());
    }

    boolean hasSetterReturnTypeArgsAndQualifiers(final PsiMethod method) {
        return !method.hasModifierProperty(PsiModifier.STATIC) && visibilityInfoProvider.isVisibleToTestClass(method) && method.getParameterList().getParameters().length == 1 && method.getReturnType() != null && (PsiTypes.voidType().equals(
                method.getReturnType()) || BeanDetectorUtils.isSameType(method.getReturnType(), method.getContainingClass())) && !method.isConstructor();
    }

    boolean hasGetterReturnTypeArgsAndQualifiers(final PsiMethod method) {
        return !method.hasModifierProperty(PsiModifier.STATIC) && visibilityInfoProvider.isVisibleToTestClass(method) && method.getParameterList().getParameters().length == 0 && method.getReturnType() != null && !PsiTypes.voidType().equals(
                method.getReturnType()) && !method.isConstructor();
    }
}
