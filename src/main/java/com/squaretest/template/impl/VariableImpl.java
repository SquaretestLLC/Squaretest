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
import com.squaretest.template.api.Api.Variable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VariableImpl extends PropsHolderImpl implements Variable {
    @NotNull
    private TypeImpl type;
    @NotNull
    private final String declaredName;
    private final String declaredNameWithoutPrefix;
    private final boolean isFinal;
    private final boolean isStatic;
    private final boolean isUsed;
    protected final Api.FluentList<Api.Annotation> annotations;
    private String testClassMemberName;
    private String testClassLocalFieldName;
    private boolean shouldStoreReferenceInTestClass;
    private Api.Method containingMethod;

    public VariableImpl(
            @NotNull final TypeImpl type,
            @NotNull final String declaredName,
            @NotNull final String declaredNameWithoutPrefix,
            boolean isUsed, @NotNull final Api.FluentList<Api.Annotation> annotations,
            final String testClassMemberName, final String testClassLocalFieldName, final boolean isFinal,
            final boolean isStatic, final boolean shouldStoreReferenceInTestClass) {
        this.type = type;
        this.declaredName = declaredName;
        this.isUsed = isUsed;
        this.isFinal = isFinal;
        this.isStatic = isStatic;
        this.declaredNameWithoutPrefix = declaredNameWithoutPrefix;
        this.annotations = annotations;

        // Set mutable fields to their default values.
        this.testClassMemberName = testClassMemberName;
        this.testClassLocalFieldName = testClassLocalFieldName;
        this.shouldStoreReferenceInTestClass = shouldStoreReferenceInTestClass;
    }

    protected VariableImpl(final VariableImpl variable) {
        this(variable.type, variable.declaredName,
                variable.declaredNameWithoutPrefix, variable.isUsed, variable.annotations, variable.testClassMemberName, variable.testClassLocalFieldName,
                variable.isFinal, variable.isStatic, variable.shouldStoreReferenceInTestClass);
    }

    @Override
    @NotNull
    public String getDeclaredName() {
        return declaredName;
    }

    @Override
    @NotNull
    public String getTestClassMemberName() {
        return testClassMemberName;
    }

    @Override
    public void setTestClassMemberName(String testClassMemberName) {
        if(testClassMemberName == null) {
            this.testClassMemberName = "null";
        } else {
            this.testClassMemberName = testClassMemberName;
        }
    }

    @Override
    @NotNull
    public String getTestClassLocalFieldName() {
        return testClassLocalFieldName;
    }

    @Override
    public void setTestClassLocalFieldName(String testClassLocalFieldName) {
        if(testClassLocalFieldName == null) {
            this.testClassLocalFieldName = "null";
        } else {
            this.testClassLocalFieldName = testClassLocalFieldName;
        }
    }

    @Override
    @NotNull
    public String getDeclaredNameWithoutPrefix() {
        return declaredNameWithoutPrefix;
    }

    @Override
    public boolean isFinal() {
        return isFinal;
    }

    @Override
    public boolean isUsed() {
        return isUsed;
    }

    @Override
    @NotNull
    public TypeImpl getType() {
        return type;
    }

    public void setType(@NotNull TypeImpl type) {
        this.type = type;
    }

    // Type pass-through methods.
    @NotNull
    public String getCanonicalText() {
        return type.getCanonicalText();
    }

    @NotNull
    public List<String> getCanonicalNamesRequiredForType() {
        return type.getCanonicalNamesRequiredForType();
    }

    @Override
    @NotNull
    public String getDefaultInitExpression() {
        return type.getDefaultInitExpression();
    }

    @Override
    public void setDefaultInitExpression(final String initExpression) {
        if(initExpression == null) {
            this.type.setDefaultInitExpression("null");
        } else {
            this.type.setDefaultInitExpression(initExpression);
        }
    }

    @Override
    public boolean getShouldBeMocked() {
        return type.getShouldBeMocked();
    }

    @Override
    public void setShouldBeMocked(boolean shouldBeMocked) {
        this.type.setShouldBeMocked(shouldBeMocked);
    }

    @Override
    public boolean getShouldBeSpied() {
        return type.getShouldBeSpied();
    }

    @Override
    public void setShouldBeSpied(boolean shouldBeSpied) {
        this.type.setShouldBeSpied(shouldBeSpied);
    }

    @Override
    @NotNull
    public String getInitExpression() {
        return type.getInitExpression();
    }

    @Override
    public void setInitExpression(final String initExpression) {
        if(initExpression == null) {
            this.type.setInitExpression("null");
        } else {
            this.type.setInitExpression(initExpression);
        }
    }

    @Override
    public boolean isMember() {
        return this instanceof Api.ClassMember;
    }

    @Override
    public boolean getShouldStoreInReference() {
        return shouldStoreReferenceInTestClass;
    }

    @Override
    public void setShouldStoreInReference(final boolean shouldStoreReferenceInTestClass) {
        this.shouldStoreReferenceInTestClass = shouldStoreReferenceInTestClass;
    }

    @Override
    public Api.FluentList<Api.Annotation> getAnnotations() {
        return annotations;
    }

    @Override
    @Nullable
    public Api.Method getContainingMethod() {
        return containingMethod;
    }

    public void setContainingMethod(final Api.Method containingMethod) {
        this.containingMethod = containingMethod;
    }
}
