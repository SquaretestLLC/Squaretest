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

import com.squaretest.generation.NullabilityStatus;
import com.squaretest.template.AccessModifier;
import com.squaretest.template.api.Api;
import com.squaretest.template.api.Api.Method;
import com.squaretest.template.api.Api.Variable;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class MethodImpl extends PropsHolderImpl implements Method {
    @NotNull
    private final Api.FluentList<Variable> parameters;
    @NotNull
    private final String name;
    @NotNull
    private final AccessModifier accessModifier;
    @Nullable
    private final Api.Type returnType;
    private final boolean isVisibleToTestClass;
    @NotNull
    private final Api.FluentList<Api.Exception> declaredExceptions;
    @NotNull
    private final Api.FluentList<Api.Exception> javadocExceptions;
    @NotNull
    private final Api.FluentList<Api.Exception> undeclaredExceptions;
    @NotNull
    private final Api.FluentList<Api.DependencyInteraction> dependencyInteractions;
    @NotNull
    private final Api.FluentList<Api.Method> superMethods;
    @NotNull
    private final Api.FluentList<Api.Annotation> annotations;
    @Nullable
    private final Api.ClassMember targetField;
    @NotNull
    private Api.SimpleExitInfo simpleExitInfo;
    @NotNull
    private final String methodKey;
    private final boolean jaxbListGetter;
    private boolean shouldUseSimpleTest;
    private final boolean isDeprecated;
    @Nullable
    private Api.SourceClass containingClass;

    private final boolean isStatic;
    private final boolean isAbstract;
    private final boolean isWritable;
    private final boolean isNative;
    private final boolean isGetter;
    private final boolean isSetter;
    private final boolean isConstructor;
    private final boolean isInMainSourceClass;
    private int overloadNumber;
    private final NullabilityStatus nullabilityStatus;
    private boolean isOverload;
    private final int indexInSourceClass;
    private final boolean alwaysThrows;
    private final boolean calledInSource;
    private final boolean propertyIsUsedInSource;
    private final boolean calledInStaticHelpers;
    private final boolean propertyIsUsedInStaticHelpers;
    private final boolean doesNothing;
    private final boolean isLombokSuperBuilderConstructor;
    private boolean shouldUseLastParam;
    private boolean shouldTest;

    public MethodImpl(
            @NotNull final String name, @NotNull final Api.FluentList<Variable> parameters,
            @NotNull final AccessModifier accessModifier, @Nullable final Api.Type returnType,
            final boolean isVisibleToTestClass, @NotNull final Api.FluentList<Api.Exception> declaredExceptions,
            @NotNull final Api.FluentList<Api.Exception> javadocExceptions,
            @NotNull final Api.FluentList<Api.Exception> undeclaredExceptions,
            @NotNull final Api.FluentList<Method> superMethods,
            @NotNull final Api.FluentList<Api.Annotation> annotations,
            @Nullable final Api.ClassMember targetField,
            @NotNull final Api.SimpleExitInfo simpleExitInfo,
            @NotNull final String methodKey, final NullabilityStatus nullabilityStatus, final boolean isOverload,
            final boolean isDeprecated, final boolean isStatic, final boolean isAbstract, final boolean isWritable, final boolean isNative,
            final boolean isGetter, final boolean isSetter,
            final boolean isConstructor, final boolean isInMainSourceClass, final int overloadNumber, final boolean shouldUseSimpleTest,
            final boolean isJaxbListGetter, final int indexInSourceClass, final boolean alwaysThrows, final boolean calledInSource,
            final boolean propertyIsUsedInSource, final boolean calledInStaticHelpers,
            final boolean propertyIsUsedInStaticHelpers, final boolean doesNothing,
            final boolean isLombokSuperBuilderConstructor, final boolean shouldUseLastParam) {
        this.parameters = parameters;
        this.name = name;
        this.accessModifier = accessModifier;
        this.returnType = returnType;
        this.isVisibleToTestClass = isVisibleToTestClass;
        this.declaredExceptions = declaredExceptions;
        this.javadocExceptions = javadocExceptions;
        this.undeclaredExceptions = undeclaredExceptions;
        this.superMethods = superMethods;
        this.annotations = annotations;
        this.targetField = targetField;
        this.simpleExitInfo = simpleExitInfo;
        this.methodKey = methodKey;
        this.nullabilityStatus = nullabilityStatus;
        this.isOverload = isOverload;
        this.isDeprecated = isDeprecated;
        this.isStatic = isStatic;
        this.isAbstract = isAbstract;
        this.isWritable = isWritable;
        this.isNative = isNative;
        this.isGetter = isGetter;
        this.isSetter = isSetter;
        this.isConstructor = isConstructor;
        this.isInMainSourceClass = isInMainSourceClass;
        this.overloadNumber = overloadNumber;
        this.shouldUseSimpleTest = shouldUseSimpleTest;
        this.indexInSourceClass = indexInSourceClass;
        this.alwaysThrows = alwaysThrows;
        this.calledInSource = calledInSource;
        this.propertyIsUsedInSource = propertyIsUsedInSource;
        this.calledInStaticHelpers = calledInStaticHelpers;
        this.propertyIsUsedInStaticHelpers = propertyIsUsedInStaticHelpers;
        this.doesNothing = doesNothing;
        this.isLombokSuperBuilderConstructor = isLombokSuperBuilderConstructor;
        this.shouldUseLastParam = shouldUseLastParam;
        this.dependencyInteractions = new FluentListImpl<>();
        this.jaxbListGetter = isJaxbListGetter;
        this.shouldTest = false;
        for(final Api.Variable param : parameters) {
            ((VariableImpl) param).setContainingMethod(this);
        }
    }

    @NotNull
    @Override
    public Api.FluentList<Variable> getParameters() {
        return parameters;
    }

    @Override
    public Api.FluentList<Variable> getSuperMethodParametersAtIndex(final int index) {
        final Api.FluentList<Variable> ret = new FluentListImpl<>();
        for(final Method superMethod : superMethods) {
            final Variable paramAtIndex = superMethod.getParameters().getOrNull(index);
            if(paramAtIndex != null) {
                ret.add(paramAtIndex);
            }
        }
        return ret;
    }

    @Override
    @NotNull
    public Api.FluentList<Api.Annotation> getAnnotations() {
        return annotations;
    }

    @Override
    @Nullable
    public Api.ClassMember getTargetField() {
        return targetField;
    }

    @Override
    @NotNull
    public Api.SimpleExitInfo getSimpleExitInfo() {
        return simpleExitInfo;
    }

    public void setSimpleExitInfo(final Api.SimpleExitInfo simpleExitInfo) {
        this.simpleExitInfo = simpleExitInfo;
    }

    @Override
    @NotNull
    public String getMethodKey() {
        return methodKey;
    }

    @Override
    public Api.FluentList<Method> getSuperMethods() {
        return superMethods;
    }

    @NotNull
    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isInferredNullable() {
        return nullabilityStatus == NullabilityStatus.NULLABLE;
    }

    @Override
    public boolean getReturnTypeCanBeAbsent() {
        if(isInferredNullable()) {
            return true;
        }
        // Return true if the returnType has a special (not "null") absentInitExpression. This item represents "no value".
        // For example: com.amazonaws.services.dynamodbv2.model.GetItemResult can return a result with no item property;
        // that is its "no value" variant.
        return returnType != null && returnType.getAbsentInitExpression() != null && !StringUtils.equals(returnType.getAbsentInitExpression(), "null");
    }

    private boolean isOptional() {
        return returnType != null && returnType.isOptional();
    }

    @Override
    public boolean isPublic() {
        return AccessModifier.Public == accessModifier;
    }

    @Override
    public boolean isProtected() {
        return AccessModifier.Protected == accessModifier;
    }

    @Override
    public boolean isPackageLocal() {
        return AccessModifier.PackageLocal == accessModifier;
    }

    @Override
    public boolean isPrivate() {
        return AccessModifier.Private == accessModifier;
    }

    @Override
    public String getAccessLevel() {
        return accessModifier.getAccessLevel();
    }

    @Override
    public boolean isStatic() {
        return isStatic;
    }

    @Override
    public boolean isDeprecated() {
        return isDeprecated;
    }

    @Override
    @Nullable
    public Api.SourceClass getContainingClass() {
        return containingClass;
    }

    public void setContainingClass(@Nullable final Api.SourceClass containingClass) {
        this.containingClass = containingClass;
    }

    @Override
    public boolean isAbstract() {
        return isAbstract;
    }

    @Override
    public boolean isWritable() {
        return isWritable;
    }

    @Override
    public boolean isNative() {
        return isNative;
    }

    @Override
    public boolean isSimpleGetterOrSetter() {
        return isGetter || isSetter;
    }

    @Override
    public boolean isGetterOrSetter() {
        return isGetter || isSetter;
    }

    @Override
    public boolean isGetter() {
        return isGetter;
    }

    @Override
    public boolean isSimpleGetter() {
        return isGetter;
    }

    @Override
    public boolean isSimpleSetter() {
        return isSetter;
    }

    @Override
    public boolean isSetter() {
        return isSetter;
    }

    @Override
    public boolean isJaxbListGetter() {
        return jaxbListGetter;
    }

    @Override
    public boolean isConstructor() {
        return isConstructor;
    }

    @Override
    public boolean isInMainSourceClass() {
        return isInMainSourceClass;
    }

    @Override
    @Nullable
    public Api.Type getReturnType() {
        return returnType;
    }

    @Override
    public boolean isVisibleToTestClass() {
        return isVisibleToTestClass;
    }

    @Override
    @NotNull
    public Api.FluentList<Api.Exception> getDeclaredExceptions() {
        return this.declaredExceptions;
    }

    @Override
    @NotNull
    public Api.FluentList<Api.Exception> getJavadocExceptions() {
        return javadocExceptions;
    }

    @Override
    @NotNull
    public Api.FluentList<Api.Exception> getUndeclaredExceptions() {
        return undeclaredExceptions;
    }

    @Override
    public boolean getShouldUseSimpleTest() {
        return shouldUseSimpleTest;
    }

    public int getIndexInSourceClass() {
        return indexInSourceClass;
    }

    @Override
    public boolean getAlwaysThrows() {
        return alwaysThrows;
    }

    @Override
    public void setShouldUseSimpleTest(final boolean shouldUseSimpleTest) {
        this.shouldUseSimpleTest = shouldUseSimpleTest;
    }

    public boolean isCalledInSource() {
        return calledInSource;
    }

    public boolean getPropertyIsUsedInSource() {
        return propertyIsUsedInSource;
    }

    public boolean isCalledInStaticHelpers() {
        return calledInStaticHelpers;
    }

    public boolean getPropertyIsUsedInStaticHelpers() {
        return propertyIsUsedInStaticHelpers;
    }

    @Override
    public boolean getShouldUseLastParam() {
        return shouldUseLastParam;
    }

    @Override
    public void setShouldUseLastParam(final boolean shouldUseLastParam) {
        this.shouldUseLastParam = shouldUseLastParam;
    }

    @Override
    public boolean getDoesNothing() {
        return this.doesNothing;
    }

    @Override
    public boolean isLombokSuperBuilderConstructor() {
        return this.isLombokSuperBuilderConstructor;
    }

    @NotNull
    public Api.FluentList<String> getCanonicalNamesRequiredForReturnTypes() {
        return returnType != null ? ((TypeImpl) returnType).getCanonicalNamesRequiredForType() : Api.FluentListFactory.emptyList();
    }

    @Override
    public boolean getThrowsException() {
        return !declaredExceptions.isEmpty() || !undeclaredExceptions.isEmpty();
    }

    @Override
    public boolean getThrowsCheckedException() {
        return declaredExceptions.stream().anyMatch(Api.Exception::isChecked);
    }

    public void setIsOverload(final boolean overload) {
        isOverload = overload;
    }

    public void setOverloadNumber(final int overloadNumber) {
        this.overloadNumber = overloadNumber;
    }

    @Override
    @NotNull
    public String getOverloadSuffix() {
        if(isOverload) {
            return Integer.toString(overloadNumber);
        }
        return "";
    }

    @Override
    public boolean getShouldTest() {
        return shouldTest;
    }

    @Override
    public void setShouldTest(final boolean shouldTest) {
        this.shouldTest = shouldTest;
    }

    @NotNull
    public Api.FluentList<Api.DependencyInteraction> getDependencyInteractions() {
        return dependencyInteractions;
    }

    public void addDependencyInteractions(final Set<Api.DependencyInteraction> dependencyInteractions) {
        for(final Api.DependencyInteraction di : dependencyInteractions) {
            ((DependencyInteractionImpl) di).setSourceMethodContainingDi(this);
            this.dependencyInteractions.add(di);
        }
    }
}
