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

import com.squaretest.generation.InitStrategy;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.defaulttypes.TypeCreatorUtil;
import com.squaretest.template.AccessModifier;
import com.squaretest.template.api.Api;
import com.squaretest.template.api.Api.Type;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Stores information describing a java type.
 */
public class TypeImpl extends PropsHolderImpl implements Type {
    private static final String SquareBracketsRegex = "\\[|\\]";
    @Nullable
    private final String canonicalName;
    @NotNull
    private final Api.FluentList<String> canonicalNamesRequiredForType;
    @NotNull
    private final Api.FluentList<Type> params;
    private Api.FluentList<Api.Type> initExpressionBeans;
    private Api.FluentList<Api.Type> failureInitExpressionBeans;
    @NotNull
    private final String canonicalText;
    @NotNull
    private final String name;
    private final boolean canBeMocked;
    private final boolean isArray;
    private final boolean isPrimitive;
    private final boolean overridesEquals;
    private final boolean isClassT;
    private final boolean isGeneric;
    private boolean isDtoBean;
    private final boolean isDtoBeanWithInputIoProperty;
    private final boolean isClosable;
    private final boolean isEnum;
    private final boolean isInterface;
    private final boolean isAbstract;
    private boolean isSimple;
    private boolean isSpringSimple;
    private boolean isSimpleIfTypeParamsAreSimple;
    private final boolean isNested;
    private final boolean isStatic;
    private final AccessModifier accessModifier;
    @Nullable
    private final String initExpressionWithTypePlaceholder;
    @Nullable
    private final String failureInitExpressionWithTypePlaceholder;
    private String absentInitExpression;
    private boolean isOptional;
    private final int numberOfTypesInPlaceholderExpression;
    private final Api.FluentList<String> superTypeCanonicalNames;
    private final InitStrategy initStrategy;

    // Fields that are mutable only from Java code.
    private boolean isRecognizedType;
    @NotNull
    private String defaultInitExpression;
    private boolean allNestedTypeParamsOverrideEquals;
    @Nullable
    private Api.Type deepArrayComponentType;
    @Nullable
    private String emptyInitExpression;
    private boolean isEmptyIfTypeParamsAreEmpty;
    private boolean isAbsentIfTypeParamsAreAbsent;
    private String brokenIoInitExpression;
    private String emptyIoInitExpression;
    @Nullable
    private String failureInitExpression;
    @Nullable
    private String defaultFailureInitExpression;

    // Mutable fields
    private boolean shouldStoreInReference;
    private String initExpression;
    private boolean shouldBeMocked;
    private boolean shouldBeSpied;
    @NotNull
    private String testClassMemberName;
    @NotNull
    private String testClassLocalFieldName;
    private boolean wildcard;
    private boolean shouldOnlySetUsedProperties;
    private boolean initExpressionUsesAllTypeParams;

    public TypeImpl(
            @NotNull final String name, @Nullable final String canonicalName, @NotNull final String canonicalText,
            @NotNull final String defaultInitExpression, @Nullable final String initExpressionWithTypePlaceholder,
            final boolean isOptional, final String absentInitExpression, @Nullable final String emptyInitExpression,
            final String brokenIoInitExpression, final String emptyIoInitExpression,
            @Nullable final String failureInitExpression,
            @Nullable final String failureInitExpressionWithTypePlaceholder,
            @NotNull final Api.FluentList<String> canonicalNamesRequiredForType,
            Api.FluentList<String> superTypeCanonicalNames, boolean isNested, boolean isStatic,
            final AccessModifier accessModifier, final boolean canBeMocked, final boolean shouldBeMocked,
            final boolean isArray, final boolean isPrimitive, final boolean overridesEquals, final boolean isClassT,
            final boolean isGeneric, final boolean isDTOBean, final boolean isDtoBeanWithInputIoProperty,
            final boolean isEnum, final boolean isClosable, final boolean isInterface, final boolean isAbstract, final boolean isRecognizedType, final boolean isSimple,
            final boolean isSimpleIfTypeParamsAreSimple, final boolean isAbsentIfTypeParamsAreAbsent,
            final boolean isEmptyIfTypeParamsAreEmpty, final InitStrategy initStrategy) {
        this.name = name;
        this.canonicalText = canonicalText;
        this.canonicalName = canonicalName;
        this.defaultInitExpression = defaultInitExpression;
        this.isOptional = isOptional;
        this.absentInitExpression = absentInitExpression;
        this.emptyInitExpression = emptyInitExpression;
        this.brokenIoInitExpression = brokenIoInitExpression;
        this.emptyIoInitExpression = emptyIoInitExpression;
        this.failureInitExpression = failureInitExpression;
        this.failureInitExpressionWithTypePlaceholder = failureInitExpressionWithTypePlaceholder;
        this.canonicalNamesRequiredForType = canonicalNamesRequiredForType;
        this.superTypeCanonicalNames = superTypeCanonicalNames;
        this.isNested = isNested;
        this.isStatic = isStatic;
        this.accessModifier = accessModifier;
        this.canBeMocked = canBeMocked;
        this.isArray = isArray;
        this.isPrimitive = isPrimitive;
        this.overridesEquals = overridesEquals;
        this.isClassT = isClassT;
        this.isGeneric = isGeneric;
        this.isDtoBean = isDTOBean;
        this.isDtoBeanWithInputIoProperty = isDtoBeanWithInputIoProperty;
        this.isClosable = isClosable;
        this.isEnum = isEnum;
        this.isInterface = isInterface;
        this.isAbstract = isAbstract;
        this.isSimple = isSimple;
        this.isSimpleIfTypeParamsAreSimple = isSimpleIfTypeParamsAreSimple;
        this.isAbsentIfTypeParamsAreAbsent = isAbsentIfTypeParamsAreAbsent;
        this.isEmptyIfTypeParamsAreEmpty = isEmptyIfTypeParamsAreEmpty;
        this.isRecognizedType = isRecognizedType;
        this.initStrategy = initStrategy;
        this.shouldOnlySetUsedProperties = false;

        // Our initExpression contains (or will contain) a DTO bean (ourself).
        this.initExpressionBeans = new FluentListImpl<>();
        if(isDtoBean) {
            // Add a copy of ourself to avoid overwriting the bean's initExpression and defaultInitExpression whenever
            // ours is modified.
            // final TypeImpl copyOfUs = new TypeImpl(this);
            initExpressionBeans.add(this);
        }
        this.failureInitExpressionBeans = new FluentListImpl<>();
        // Mutable fields
        this.initExpression = defaultInitExpression;
        this.initExpressionWithTypePlaceholder = initExpressionWithTypePlaceholder;
        this.defaultFailureInitExpression = failureInitExpression;
        this.numberOfTypesInPlaceholderExpression = TypeCreatorUtil.determineNumberOfPlaceholdersInInitExpression(initExpressionWithTypePlaceholder);
        this.shouldBeMocked = shouldBeMocked;
        this.params = new FluentListImpl<>();
        this.testClassLocalFieldName = StringUtils.uncapitalize(name.replaceAll(SquareBracketsRegex, ""));
        this.testClassMemberName = StringUtils.uncapitalize(name.replaceAll(SquareBracketsRegex, ""));
        this.allNestedTypeParamsOverrideEquals = true;
        this.wildcard = false;
        this.shouldBeSpied = false;
        this.isSpringSimple = determineIfSpringSimple();
        this.initExpressionUsesAllTypeParams = false;
    }

    @SuppressWarnings("IncompleteCopyConstructor")
    public TypeImpl(final TypeImpl other) {
        this.canonicalName = other.canonicalName;
        this.canonicalNamesRequiredForType = new FluentListImpl<>();
        this.canonicalNamesRequiredForType.addAll(other.canonicalNamesRequiredForType);
        this.params = new FluentListImpl<>();
        for(final Api.Type otherParam : other.params) {
            this.params.add(new TypeImpl((TypeImpl) otherParam));
        }
        this.initExpressionBeans = new FluentListImpl<>();
        for(final Api.Type otherBean : other.initExpressionBeans) {
            this.initExpressionBeans.add(new TypeImpl((TypeImpl) otherBean));
        }
        this.initExpressionBeans.addAll(other.initExpressionBeans);
        this.canonicalText = other.canonicalText;
        this.name = other.name;
        this.canBeMocked = other.canBeMocked;
        this.isArray = other.isArray;
        this.isPrimitive = other.isPrimitive;
        this.overridesEquals = other.overridesEquals;
        this.isClassT = other.isClassT;
        this.isGeneric = other.isGeneric;
        this.isDtoBean = other.isDtoBean;
        this.isDtoBeanWithInputIoProperty = other.isDtoBeanWithInputIoProperty;
        this.isClosable = other.isClosable;
        this.isEnum = other.isEnum;
        this.isInterface = other.isInterface;
        this.isAbstract = other.isAbstract;
        this.isSimple = other.isSimple;
        this.isSpringSimple = other.isSpringSimple;
        this.isSimpleIfTypeParamsAreSimple = other.isSimpleIfTypeParamsAreSimple;
        this.isNested = other.isNested;
        this.isStatic = other.isStatic;
        this.accessModifier = other.accessModifier;
        this.initExpressionWithTypePlaceholder = other.initExpressionWithTypePlaceholder;
        this.failureInitExpressionWithTypePlaceholder = other.failureInitExpressionWithTypePlaceholder;
        this.absentInitExpression = other.absentInitExpression;
        this.isOptional = other.isOptional;
        this.numberOfTypesInPlaceholderExpression = other.numberOfTypesInPlaceholderExpression;
        this.superTypeCanonicalNames = new FluentListImpl<>();
        this.superTypeCanonicalNames.addAll(other.superTypeCanonicalNames);
        this.initStrategy = other.initStrategy;
        this.isRecognizedType = other.isRecognizedType;
        this.defaultInitExpression = other.defaultInitExpression;
        this.allNestedTypeParamsOverrideEquals = other.allNestedTypeParamsOverrideEquals;
        this.deepArrayComponentType = other.deepArrayComponentType;
        this.emptyInitExpression = other.emptyInitExpression;
        this.isEmptyIfTypeParamsAreEmpty = other.isEmptyIfTypeParamsAreEmpty;
        this.isAbsentIfTypeParamsAreAbsent = other.isAbsentIfTypeParamsAreAbsent;
        this.brokenIoInitExpression = other.brokenIoInitExpression;
        this.emptyIoInitExpression = other.emptyIoInitExpression;
        this.failureInitExpression = other.failureInitExpression;
        this.shouldStoreInReference = other.shouldStoreInReference;
        this.initExpression = other.initExpression;
        this.shouldBeMocked = other.shouldBeMocked;
        this.shouldBeSpied = other.shouldBeSpied;
        this.testClassMemberName = other.testClassMemberName;
        this.testClassLocalFieldName = other.testClassLocalFieldName;
        this.wildcard = other.wildcard;
        this.shouldOnlySetUsedProperties = other.shouldOnlySetUsedProperties;
    }

    /**
     * Return whether or not this type is simple according to the Spring BeanUtils.isSimpleValueType.
     * See <a href="https://github.com/spring-projects/spring-framework/blob/master/spring-beans/src/main/java/org/springframework/beans/BeanUtils.java">BeanUtils.isSimpleValueType(Class)</a>.
     *
     * @return whether or not this type is simple according to the Spring BeanUtils.isSimpleValueType(Class).
     */
    private boolean determineIfSpringSimple() {
        if(isArray) {
            return false;
        }
        return isPrimitive || isEnum || isClassT ||
                StringUtils.equalsAny(canonicalName, "java.lang.String",
                        "void",
                        "java.lang.Void",
                        "java.lang.Boolean",
                        "java.lang.Byte",
                        "java.lang.Character",
                        "java.lang.Double",
                        "java.lang.Float",
                        "java.lang.Integer",
                        "java.lang.Long",
                        "java.lang.Short")
                || isAny("java.lang.CharSequence", "java.lang.Number", "java.util.Date", "java.time.temporal.Temporal", "java.net.URI", "java.net.URL", "java.util.Locale");
    }

    @Override
    public Type getType() {
        return this;
    }

    @Override
    @Nullable
    public String getCanonicalName() {
        return canonicalName;
    }

    @NotNull
    @Override
    public String getCanonicalNameOrName() {
        return canonicalName != null ? canonicalName : name;
    }

    @NotNull
    public Api.FluentList<String> getCanonicalNamesRequiredForType() {
        return canonicalNamesRequiredForType;
    }

    public Api.FluentList<String> getAllSuperTypeNames() {
        return this.superTypeCanonicalNames;
    }

    @NotNull
    @Override
    public Api.FluentList<Type> getParameters() {
        return params;
    }

    public void addParams(final List<Type> params) {
        this.params.addAll(params);

        // Set the array deep component type.
        if(isArray && params.size() == 1) {
            final Type innerType = params.get(0);
            if(innerType.isArray()) {
                this.deepArrayComponentType = innerType.getDeepArrayComponentType();
            } else {
                this.deepArrayComponentType = innerType;
            }
        }

        // Update allNestedTypeParamsOverrideEquals based on the new params.
        updateAllNestedTypeParamsOverrideEquals();

        // Update isSimple based on the new params.
        updateIsSimple();
    }

    private void updateIsSimple() {
        if(!isSimpleIfTypeParamsAreSimple || this.params.isEmpty() || isArray) {
            return;
        }

        // This type is simple if all of the type params are simple. This implementation considers types like
        // Optional<Optional<String>> to be simple as long as the innermost type is Simple.
        this.isSimple = this.params.stream().allMatch(Type::isSimple);
    }

    private void updateAllNestedTypeParamsOverrideEquals() {
        // Update allSubtypesOverrideEquals based on the new type parameters.
        if(Objects.equals(JavaNames.JavaLangClass, this.canonicalName)) {
            return;
        }
        for(final Type type : this.params) {
            if(type.isPrimitive()) {
                // If the parent type is an array, this type may be primitive.
                continue;
            }
            if(!type.getOverridesEquals() || !type.getAllNestedTypeParamsOverrideEquals()) {
                if(!Objects.equals(JavaNames.JavaLangClass, type.getCanonicalName())) {
                    // If the type does not override Object.equals() or the type has a subtype that does not override equals
                    // AND the type is not a Class<T> (Class types are treated as though they override equals), then set our
                    // allNestedTypeParamsOverrideEquals property to false.
                    this.allNestedTypeParamsOverrideEquals = false;
                    break;
                }
            }
        }
    }

    @Override
    public boolean isWildcard() {
        return wildcard;
    }

    public void setWildcard(final boolean wildcard) {
        this.wildcard = wildcard;
    }

    @Override
    public boolean getAllNestedTypeParamsOverrideEquals() {
        return this.allNestedTypeParamsOverrideEquals;
    }

    public void setAllNestedTypeParamsOverrideEquals(final boolean allNestedTypeParamsOverrideEquals) {
        this.allNestedTypeParamsOverrideEquals = allNestedTypeParamsOverrideEquals;
    }

    @Override
    public boolean isOrHasAnyNestedTypeParamWith(String... canonicalNames) {
        return isOrHasAnyNestedTypeParamWith(Arrays.asList(canonicalNames));
    }

    @Override
    public boolean isOrHasAnyNestedTypeParamWith(List<String> canonicalNames) {
        if(isAny(canonicalNames)) {
            return true;
        }
        if(this.isArray && this.deepArrayComponentType != null) {
            return deepArrayComponentType.isOrHasAnyNestedTypeParamWith(canonicalNames);
        }
        for(final Type type : params) {
            if(type.isAny(canonicalNames)) {
                return true;
            }
            if(type.isOrHasAnyNestedTypeParamWith(canonicalNames)) {
                return true;
            }
        }

        return false;
    }

    @Override
    @Nullable
    public Api.Type getDeepArrayComponentType() {
        return this.deepArrayComponentType;
    }

    @Nullable
    public String getInitExpressionWithTypePlaceholder() {
        return initExpressionWithTypePlaceholder;
    }

    @Nullable
    public String getFailureInitExpressionWithTypePlaceholder() {
        return failureInitExpressionWithTypePlaceholder;
    }

    public int getNumberOfTypesInPlaceholderExpression() {
        return numberOfTypesInPlaceholderExpression;
    }

    @Override
    @NotNull
    public String getDefaultInitExpression() {
        return defaultInitExpression;
    }

    @Override
    public void setDefaultInitExpression(@NotNull final String defaultInitExpression) {
        if(isInitializing()) {
            this.defaultInitExpression = defaultInitExpression;
            return;
        }
        this.initExpressionBeans.removeIf(bean -> !defaultInitExpression.contains(bean.getDefaultInitExpression()));
        if(initExpressionBeans.isEmpty()) {
            this.isDtoBean = false;
        }
        this.defaultInitExpression = defaultInitExpression;
    }

    private boolean isInitializing() {
        final boolean defaultInitIsNull = defaultInitExpression.equals("null");
        for(final Api.Type bean : initExpressionBeans) {
            if(defaultInitIsNull && bean.getDefaultInitExpression().equals("null")) {
                return true;
            }
            if(!defaultInitExpression.contains(bean.getDefaultInitExpression())) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    @Override
    public String getTestClassMemberName() {
        return testClassMemberName;
    }

    @Override
    public void setTestClassMemberName(final String testClassMemberName) {
        if(testClassMemberName == null) {
            this.testClassMemberName = "null";
        } else {
            this.testClassMemberName = testClassMemberName;
        }
    }

    @NotNull
    @Override
    public String getTestClassLocalFieldName() {
        return testClassLocalFieldName;
    }

    @Override
    public void setTestClassLocalFieldName(final String testClassLocalFieldName) {
        if(testClassLocalFieldName == null) {
            this.testClassLocalFieldName = "null";
        } else {
            this.testClassLocalFieldName = testClassLocalFieldName;
        }
    }

    @Override
    public Api.FluentList<Api.Type> getInitExpressionBeans() {
        return initExpressionBeans;
    }

    public void addInitExpressionBeans(final Collection<? extends Type> beans) {
        this.initExpressionBeans.addAll(beans);
    }

    public void setInitExpressionBeans(final Api.FluentList<Type> initExpressionBeans) {
        this.initExpressionBeans = initExpressionBeans;
    }

    @Override
    public Api.FluentList<Api.Type> getFailureInitExpressionBeans() {
        return failureInitExpressionBeans;
    }

    public void setFailureInitExpressionBeans(final Api.FluentList<Api.Type> failureInitExpressionBeans) {
        this.failureInitExpressionBeans = failureInitExpressionBeans;
    }

    @Override
    public boolean getShouldStoreInReference() {
        return shouldStoreInReference;
    }

    @Override
    public void setShouldStoreInReference(final boolean shouldStoreInReference) {
        this.shouldStoreInReference = shouldStoreInReference;
    }

    @Override
    public String createInitExpressionWithLocalFieldBeans(final List<Api.Type> localFieldBeans) {
        String newInitExpression = this.defaultInitExpression;
        for(final Api.Type beanType : localFieldBeans) {
            newInitExpression = StringUtils.replaceOnce(newInitExpression, beanType.getDefaultInitExpression(), beanType.getTestClassLocalFieldName());
        }

        // This is a hack to preserve whether or not the template updated the init expression to use a spy.
        if(initExpression.startsWith("Mockito.spy(") && !newInitExpression.startsWith("Mockito.spy(")) {
            newInitExpression = "Mockito.spy(" + newInitExpression + ")";
        } else if(initExpression.startsWith("spy(") && !newInitExpression.startsWith("spy(")) {
            newInitExpression = "spy(" + newInitExpression + ")";
        }

        return newInitExpression;
    }

    @Override
    public String createFailureInitExpressionWithLocalFieldBeans(final List<Api.Type> localFieldBeans) {
        String newInitExpression = this.defaultFailureInitExpression;
        for(final Api.Type beanType : localFieldBeans) {
            newInitExpression = StringUtils.replaceOnce(newInitExpression, beanType.getDefaultInitExpression(), beanType.getTestClassLocalFieldName());
        }
        return newInitExpression;
    }

    @Override
    public boolean isMockable() {
        return canBeMocked;
    }

    @Override
    @NotNull
    public String getInitExpression() {
        return initExpression;
    }

    @Override
    public String getAbsentInitExpression() {
        return absentInitExpression;
    }

    @Override
    public void setAbsentInitExpression(final String absentInitExpression) {
        this.absentInitExpression = absentInitExpression;
    }

    @Override
    @Nullable
    public String getEmptyInitExpression() {
        return emptyInitExpression;
    }

    @Override
    public void setEmptyInitExpression(@Nullable final String emptyInitExpression) {
        this.emptyInitExpression = emptyInitExpression;
    }

    @Override
    public String getBrokenIoInitExpression() {
        return brokenIoInitExpression;
    }

    @Override
    public void setBrokenIoInitExpression(final String brokenIoInitExpression) {
        this.brokenIoInitExpression = brokenIoInitExpression;
    }

    @Override
    public String getEmptyIoInitExpression() {
        return emptyIoInitExpression;
    }

    @Override
    public void setEmptyIoInitExpression(final String emptyIoInitExpression) {
        this.emptyIoInitExpression = emptyIoInitExpression;
    }

    @Override
    @Nullable
    public String getFailureInitExpression() {
        return failureInitExpression;
    }

    @Override
    public void setFailureInitExpression(@Nullable final String failureInitExpression) {
        this.failureInitExpression = failureInitExpression;
    }

    @Override
    public String getDefaultFailureInitExpression() {
        return defaultFailureInitExpression;
    }

    @Override
    public void setDefaultFailureInitExpression(@Nullable final String defaultFailureInitExpression) {
        this.defaultFailureInitExpression = defaultFailureInitExpression;
    }

    @Override
    public boolean getShouldBeMocked() {
        return shouldBeMocked;
    }

    @Override
    public boolean getShouldBeSpied() {
        return shouldBeSpied;
    }

    @Override
    public void setShouldBeSpied(final boolean shouldBeSpied) {
        this.shouldBeSpied = shouldBeSpied;
    }

    @Override
    public boolean isArray() {
        return isArray;
    }

    @Override
    public boolean isDtoBean() {
        return isDtoBean;
    }

    @Override
    public boolean isDtoBeanWithInputIoProperty() {
        return isDtoBeanWithInputIoProperty;
    }

    @Override
    public boolean isOptional() {
        return isOptional;
    }

    @Override
    public void setOptional(final boolean optional) {
        isOptional = optional;
    }

    /**
     * Returns true if Squaretest recognizes this type and has a default, hard coded initialization expression for it.
     * This is intended to give users a way to create templates that mimic the behavior in earlier versions of Squaretest;
     * i.e. before Squaretest set {@link Type#getInitExpression() type.initExpression} to a dynamically generated constructor call or builder() expression.
     * <p>
     * This is intentionally not exposed in Api.java. It only exists as a fail safe to cover the case where a large number of users
     * dislike the new init expressions and want to restore the previous behavior or Squaretest.
     *
     * @return true if this type is a known default type.
     */
    @Override
    public boolean isRecognized() {
        return isRecognizedType;
    }

    @Override
    public void setInitExpression(final String initExpression) {
        if(initExpression == null) {
            this.initExpression = "null";
        } else {
            this.initExpression = initExpression;
        }
    }

    @Override
    public void setShouldBeMocked(final boolean shouldBeMocked) {
        this.shouldBeMocked = shouldBeMocked;
    }

    public void setRecognizedType(final boolean recognizedType) {
        isRecognizedType = recognizedType;
    }

    @Override
    @NotNull
    public String getCanonicalText() {
        return canonicalText;
    }

    @Override
    @NotNull
    public String getName() {
        return name;
    }

    @Override
    public boolean isPrimitive() {
        return isPrimitive;
    }

    @Override
    public boolean getOverridesEquals() {
        return overridesEquals;
    }

    @Override
    public boolean isClassT() {
        return isClassT;
    }

    @Override
    public boolean isGeneric() {
        return isGeneric;
    }

    /**
     * This returns the same as {@link #isCloseable()}. Closable is a valid english word, and developers may end up misspelling
     * the API because of this. This is to allow typos to return the correct field.
     *
     * @return Same as {@link #isCloseable()}.
     */
    public boolean isClosable() {
        return isClosable;
    }

    @Override
    public boolean isCloseable() {
        return isClosable;
    }

    @Override
    public boolean isEnum() {
        return isEnum;
    }

    @Override
    public boolean isInterface() {
        return isInterface;
    }

    @Override
    public boolean isAbstract() {
        return isAbstract;
    }

    @Override
    public boolean isSimple() {
        return isSimple;
    }

    @Override
    public boolean isSpringSimple() {
        return isSpringSimple;
    }

    public void setIsSimpleIfTypeParamsAreSimple(final boolean isSimpleIfTypeParamsAreSimple) {
        boolean hasChanged = this.isSimpleIfTypeParamsAreSimple != isSimpleIfTypeParamsAreSimple;
        this.isSimpleIfTypeParamsAreSimple = isSimpleIfTypeParamsAreSimple;
        if(hasChanged && isSimpleIfTypeParamsAreSimple) {
            updateIsSimple();
        }
    }

    public boolean isEmptyIfTypeParamsAreEmpty() {
        return isEmptyIfTypeParamsAreEmpty;
    }

    public boolean isAbsentIfTypeParamsAreAbsent() {
        return isAbsentIfTypeParamsAreAbsent;
    }

    @Override
    public boolean isNested() {
        return isNested;
    }

    @Override
    public boolean isStatic() {
        return isStatic;
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
    public boolean getShouldOnlySetUsedProperties() {
        return shouldOnlySetUsedProperties;
    }

    @Override
    public void setShouldOnlySetUsedProperties(final boolean shouldOnlySetUsedProperties) {
        this.shouldOnlySetUsedProperties = shouldOnlySetUsedProperties;
    }

    public InitStrategy getInitStrategy() {
        return initStrategy;
    }

    public boolean initExpressionUsesAllTypeParams() {
        return initExpressionUsesAllTypeParams;
    }

    public void setInitExpressionUsesAllTypeParams(final boolean initExpressionUsesAllTypeParams) {
        this.initExpressionUsesAllTypeParams = initExpressionUsesAllTypeParams;
    }
}
