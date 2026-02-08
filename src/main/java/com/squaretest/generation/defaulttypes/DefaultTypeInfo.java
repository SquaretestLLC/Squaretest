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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Stores info about known types described in the json file.
 */
public class DefaultTypeInfo {

    private static final String CanonicalNameKey = "canonicalName";
    private static final String InitExpressionKey = "initExpression";
    private static final String InitExpressionWithTypePlaceholderKey = "initExpressionWithTypePlaceholder";
    private static final String Java9InitExpressionKey = "java9InitExpression";
    private static final String Java9InitExpressionWithTypePlaceholderKey = "java9InitExpressionWithTypePlaceholder";
    private static final String AbsentInitExpressionKey = "absentInitExpression";
    private static final String EmptyInitExpressionKey = "emptyInitExpression";
    private static final String GroovyEmptyInitExpressionKey = "groovyEmptyInitExpression";
    private static final String FailureInitExpressionKey = "failureInitExpression";
    private static final String Java9FailureInitExpressionKey = "java9FailureInitExpression";
    private static final String FailureInitExpressionWithTypePlaceholderKey = "failureInitExpressionWithTypePlaceholder";
    private static final String GroovyFailureInitExpressionKey = "groovyFailureInitExpression";
    private static final String GroovyFailureInitExpressionWithTypePlaceholderKey = "groovyFailureInitExpressionWithTypePlaceholder";
    private static final String GroovyInitExpressionKey = "groovyInitExpression";
    private static final String GroovyInitExpressionWithTypePlaceholderKey = "groovyInitExpressionWithTypePlaceholder";
    private static final String ImportsRequiredKey = "importsRequired";
    private static final String IsMockableKey = "isMockable";
    private static final String ShouldBeMockedKey = "shouldBeMocked";
    private static final String OverridesEqualsKey = "overridesEquals";
    private static final String IsPrimitiveKey = "isPrimitive";
    private static final String IsBeanKey = "isBean";
    private static final String IsBeanWithInputIoPropertyKey = "isBeanWithInputIoProperty";
    private static final String IsSimpleKey = "isSimple";
    private static final String IsSimpleTypeIfParamsAreSimpleKey = "isSimpleIfTypeParamsAreSimple";
    private static final String IsAbsentIfParamsAreAbsentKey = "isAbsentIfTypeParamsAreAbsent";
    private static final String IsEmptyIfParamsAreEmptyKey = "isEmptyIfTypeParamsAreEmpty";
    private static final String IsOptionalKey = "isOptional";
    private static final String InitSettersKey = "initSetterKeys";
    private static final String CanonicalTextOverride = "canonicalTextOverride";

    @NotNull
    private final String canonicalName;
    @NotNull
    private final String initExpression;
    @Nullable
    private String initExpressionWithTypePlaceholder;
    @Nullable
    private final String java9InitExpression;
    @Nullable
    private final String java9InitExpressionWithTypePlaceholder;
    @Nullable
    private String absentInitExpression;
    @Nullable
    private String emptyInitExpression;
    @Nullable
    private final String groovyEmptyInitExpression;
    @Nullable
    private final String failureInitExpression;
    @Nullable
    private final String java9FailureInitExpression;
    @Nullable
    private final String failureInitExpressionWithTypePlaceholder;
    @Nullable
    private final String groovyFailureInitExpressionWithTypePlaceholder;
    @Nullable
    private final String groovyFailureInitExpression;
    @Nullable
    private String groovyInitExpression;
    @Nullable
    private final String groovyInitExpressionWithTypePlaceholder;
    @NotNull
    private final List<String> importsRequired;
    final boolean isMockable;
    private final boolean shouldBeMocked;
    private final boolean overridesEquals;
    private final boolean isPrimitive;
    private final boolean isBean;
    private final boolean isBeanWithInputIOProperty;
    private final boolean isSimple;
    private final boolean isSimpleIfTypeParamsAreSimple;
    private final boolean isAbsentIfTypeParamsAreAbsent;
    private boolean isEmptyIfTypeParamsAreEmpty;
    private boolean isOptional;
    private final List<String> initSetterKeys;
    private final String canonicalTextOverride;

    @JsonCreator
    public DefaultTypeInfo(
            @JsonProperty(CanonicalNameKey) @NotNull final String canonicalName,
            @JsonProperty(InitExpressionKey) @NotNull final String initExpression,
            @JsonProperty(InitExpressionWithTypePlaceholderKey) @Nullable final String initExpressionWithTypePlaceholder,
            @JsonProperty(GroovyInitExpressionKey) @Nullable final String groovyInitExpression,
            @JsonProperty(GroovyInitExpressionWithTypePlaceholderKey) @Nullable final String groovyInitExpressionWithTypePlaceholder,
            @JsonProperty(Java9InitExpressionKey) @Nullable final String java9InitExpression,
            @JsonProperty(Java9InitExpressionWithTypePlaceholderKey) @Nullable final String java9InitExpressionWithTypePlaceholder,
            @JsonProperty(IsOptionalKey) final boolean isOptional,
            @JsonProperty(AbsentInitExpressionKey) @Nullable final String absentInitExpression,
            @JsonProperty(EmptyInitExpressionKey) @Nullable final String emptyInitExpression,
            @JsonProperty(GroovyEmptyInitExpressionKey) @Nullable final String groovyEmptyInitExpression,
            @JsonProperty(FailureInitExpressionKey) @Nullable final String failureInitExpression,
            @JsonProperty(FailureInitExpressionWithTypePlaceholderKey) @Nullable final String failureInitExpressionWithTypePlaceholder,
            @JsonProperty(GroovyFailureInitExpressionKey) @Nullable final String groovyFailureInitExpression,
            @JsonProperty(GroovyFailureInitExpressionWithTypePlaceholderKey) @Nullable final String groovyFailureInitExpressionWithTypePlaceholder,
            @JsonProperty(Java9FailureInitExpressionKey) @Nullable final String java9FailureInitExpression,
            @JsonProperty(ImportsRequiredKey) @NotNull final List<String> importsRequired,
            @JsonProperty(IsMockableKey) final boolean isMockable,
            @JsonProperty(ShouldBeMockedKey) final boolean shouldBeMocked,
            @JsonProperty(OverridesEqualsKey) final boolean overridesEquals,
            @JsonProperty(IsPrimitiveKey) final boolean isPrimitive,
            @JsonProperty(IsBeanKey) final boolean isBean,
            @JsonProperty(IsBeanWithInputIoPropertyKey) final boolean isBeanWithInputIOProperty,
            @JsonProperty(IsSimpleKey) final boolean isSimple,
            @JsonProperty(IsSimpleTypeIfParamsAreSimpleKey) final boolean isSimpleIfTypeParamsAreSimple,
            @JsonProperty(IsAbsentIfParamsAreAbsentKey) final boolean isAbsentIfTypeParamsAreAbsent,
            @JsonProperty(IsEmptyIfParamsAreEmptyKey) final boolean isEmptyIfTypeParamsAreEmpty,
            @JsonProperty(InitSettersKey) final List<String> initSetterKeys,
            @JsonProperty(CanonicalTextOverride) final String canonicalTextOverride) {
        this.canonicalName = canonicalName;
        this.initExpression = initExpression;
        this.groovyInitExpression = groovyInitExpression;
        this.initExpressionWithTypePlaceholder = initExpressionWithTypePlaceholder;
        this.groovyInitExpressionWithTypePlaceholder = groovyInitExpressionWithTypePlaceholder;
        this.java9InitExpression = java9InitExpression;
        this.java9InitExpressionWithTypePlaceholder = java9InitExpressionWithTypePlaceholder;
        this.isOptional = isOptional;
        this.emptyInitExpression = emptyInitExpression;
        this.groovyEmptyInitExpression = groovyEmptyInitExpression;
        this.failureInitExpression = failureInitExpression;
        this.failureInitExpressionWithTypePlaceholder = failureInitExpressionWithTypePlaceholder;
        this.groovyFailureInitExpressionWithTypePlaceholder = groovyFailureInitExpressionWithTypePlaceholder;
        this.groovyFailureInitExpression = groovyFailureInitExpression;
        this.java9FailureInitExpression = java9FailureInitExpression;
        this.importsRequired = importsRequired;
        this.isMockable = isMockable;
        this.shouldBeMocked = shouldBeMocked;
        this.overridesEquals = overridesEquals;
        this.isPrimitive = isPrimitive;
        this.isBean = isBean;
        this.isBeanWithInputIOProperty = isBeanWithInputIOProperty;
        this.isSimple = isSimple;
        this.isSimpleIfTypeParamsAreSimple = isSimpleIfTypeParamsAreSimple;
        this.isAbsentIfTypeParamsAreAbsent = isAbsentIfTypeParamsAreAbsent;
        this.isEmptyIfTypeParamsAreEmpty = isEmptyIfTypeParamsAreEmpty;
        this.initSetterKeys = initSetterKeys;
        this.absentInitExpression = absentInitExpression;
        this.canonicalTextOverride = canonicalTextOverride;
    }

    @NotNull
    @JsonProperty(CanonicalNameKey)
    public String getCanonicalName() {
        return canonicalName;
    }

    @Nullable
    @JsonProperty(GroovyInitExpressionKey)
    public String getGroovyInitExpression() {
        return groovyInitExpression;
    }

    @JsonProperty(GroovyInitExpressionKey)
    public void setGroovyInitExpression(@Nullable final String groovyInitExpression) {
        this.groovyInitExpression = groovyInitExpression;
    }

    @NotNull
    @JsonProperty(InitExpressionKey)
    public String getInitExpression() {
        return initExpression;
    }

    @Nullable
    @JsonProperty(InitExpressionWithTypePlaceholderKey)
    public String getInitExpressionWithTypePlaceholder() {
        return initExpressionWithTypePlaceholder;
    }

    @JsonProperty(InitExpressionWithTypePlaceholderKey)
    public void setInitExpressionWithTypePlaceholder(@Nullable final String initExpressionWithTypePlaceholder) {
        this.initExpressionWithTypePlaceholder = initExpressionWithTypePlaceholder;
    }

    @Nullable
    public String getAbsentInitExpression() {
        return absentInitExpression;
    }

    public void setAbsentInitExpression(@Nullable final String absentInitExpression) {
        this.absentInitExpression = absentInitExpression;
    }

    @Nullable
    @JsonProperty(EmptyInitExpressionKey)
    public String getEmptyInitExpression() {
        return emptyInitExpression;
    }

    public void setEmptyInitExpression(@Nullable final String emptyInitExpression) {
        this.emptyInitExpression = emptyInitExpression;
    }

    @Nullable
    @JsonProperty(GroovyEmptyInitExpressionKey)
    public String getGroovyEmptyInitExpression() {
        return groovyEmptyInitExpression;
    }

    @Nullable
    @JsonProperty(FailureInitExpressionKey)
    public String getFailureInitExpression() {
        return failureInitExpression;
    }

    @Nullable
    @JsonProperty(Java9FailureInitExpressionKey)
    public String getJava9FailureInitExpression() {
        return java9FailureInitExpression;
    }

    @Nullable
    @JsonProperty(FailureInitExpressionWithTypePlaceholderKey)
    public String getFailureInitExpressionWithTypePlaceholder() {
        return failureInitExpressionWithTypePlaceholder;
    }

    @Nullable
    @JsonProperty(GroovyFailureInitExpressionWithTypePlaceholderKey)
    public String getGroovyFailureInitExpressionWithTypePlaceholder() {
        return groovyFailureInitExpressionWithTypePlaceholder;
    }

    @Nullable
    @JsonProperty(GroovyFailureInitExpressionKey)
    public String getGroovyFailureInitExpression() {
        return groovyFailureInitExpression;
    }

    @Nullable
    public String getGroovyInitExpressionWithTypePlaceholder() {
        return groovyInitExpressionWithTypePlaceholder;
    }

    @Nullable
    public String getJava9InitExpression() {
        return java9InitExpression;
    }

    @Nullable
    public String getJava9InitExpressionWithTypePlaceholder() {
        return java9InitExpressionWithTypePlaceholder;
    }

    @NotNull
    @JsonProperty(ImportsRequiredKey)
    public List<String> getImportsRequired() {
        return importsRequired;
    }

    @JsonProperty(IsMockableKey)
    public boolean isMockable() {
        return isMockable;
    }

    @JsonProperty(ShouldBeMockedKey)
    public boolean getShouldBeMocked() {
        return shouldBeMocked;
    }

    @JsonProperty(OverridesEqualsKey)
    public boolean overridesEquals() {
        return overridesEquals;
    }

    @JsonProperty(IsPrimitiveKey)
    public boolean isPrimitive() {
        return isPrimitive;
    }

    @JsonProperty(IsSimpleKey)
    public boolean isSimple() {
        return isSimple;
    }

    @JsonProperty(IsSimpleTypeIfParamsAreSimpleKey)
    public boolean isSimpleIfTypeParamsAreSimple() {
        return isSimpleIfTypeParamsAreSimple;
    }

    @JsonProperty(IsBeanKey)
    public boolean isBean() {
        return isBean;
    }

    @JsonProperty(InitSettersKey)
    public List<String> getInitSetterKeys() {
        return initSetterKeys;
    }

    @JsonProperty(IsOptionalKey)
    public boolean isOptional() {
        return isOptional;
    }

    public void setOptional(final boolean optional) {
        isOptional = optional;
    }

    @JsonProperty(IsBeanWithInputIoPropertyKey)
    public boolean isBeanWithInputIOProperty() {
        return isBeanWithInputIOProperty;
    }

    @JsonProperty(IsEmptyIfParamsAreEmptyKey)
    public boolean isEmptyIfTypeParamsAreEmpty() {
        return isEmptyIfTypeParamsAreEmpty;
    }

    @JsonProperty(IsEmptyIfParamsAreEmptyKey)
    public void setEmptyIfTypeParamsAreEmpty(final boolean emptyIfTypeParamsAreEmpty) {
        isEmptyIfTypeParamsAreEmpty = emptyIfTypeParamsAreEmpty;
    }

    @JsonProperty(IsAbsentIfParamsAreAbsentKey)
    public boolean isAbsentIfTypeParamsAreAbsent() {
        return isAbsentIfTypeParamsAreAbsent;
    }

    @JsonProperty(CanonicalTextOverride)
    public String getCanonicalTextOverride() {
        return canonicalTextOverride;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        DefaultTypeInfo that = (DefaultTypeInfo) o;

        return canonicalName.equals(that.canonicalName);
    }

    @Override
    public int hashCode() {
        return canonicalName.hashCode();
    }
}
