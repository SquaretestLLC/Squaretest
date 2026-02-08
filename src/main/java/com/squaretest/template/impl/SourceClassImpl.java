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

import com.squaretest.generation.abstractmethods.AbstractClassInfo;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.singletons.SingletonInfo;
import com.squaretest.template.EnumInfo;
import com.squaretest.template.api.Api;
import com.squaretest.template.api.Api.ClassMember;
import com.squaretest.template.api.Api.Constructor;
import com.squaretest.template.api.Api.Method;
import com.squaretest.template.api.Api.SourceClass;
import com.squaretest.template.api.Api.Type;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Describes the class for which you're generating the test.
 */
public class SourceClassImpl extends PropsHolderImpl implements SourceClass {

    @NotNull
    private final String name;
    @Nullable
    private final String canonicalName;
    @NotNull
    private final String packageName;
    @NotNull
    private final Api.FluentList<Constructor> constructors;
    @NotNull
    private final Api.FluentList<Method> methods;
    @NotNull
    private final Api.FluentList<ClassMember> fields;
    @NotNull
    private final SingletonInfo singletonInfo;
    @NotNull
    private final EnumInfo enumInfo;
    @NotNull
    private final AbstractClassInfo abstractClassInfo;
    @NotNull
    private final Type type;
    private final boolean hasGenerics;
    private final boolean dtoBean;
    private final boolean dtoBeanWithInputIoProperty;
    private final boolean sealed;
    @NotNull
    private final Api.FluentList<Api.Annotation> annotations;
    @NotNull
    private final Api.FluentList<SourceClass> superClasses;

    @NotNull
    private String testClassMemberName;
    @NotNull
    private String testClassLocalFieldName;
    @Nullable
    private Constructor preferredConstructor;
    @NotNull
    private Api.FluentList<Api.Method> preferredInitSetters;
    @NotNull
    private Api.FluentList<Api.Method> preferredInitMethods;
    private Api.FluentList<Api.Method> nonObjectLowestOverrideMethodsCache;
    private Api.FluentList<Api.Method> lowestOverrideMethodsCache;

    public SourceClassImpl(
            @NotNull final String name, @Nullable String canonicalName, @NotNull final Api.FluentList<SourceClass> superClasses, @NotNull final String packageName,
            @NotNull final Api.FluentList<Constructor> constructors, @Nullable final Constructor preferredConstructor,
            @NotNull final Api.FluentList<Method> methods, @NotNull final Api.FluentList<Method> preferredInitSetters,
            @NotNull final Api.FluentList<ClassMember> fields, @NotNull final Api.FluentList<Api.Annotation> annotations, @NotNull final SingletonInfo singletonInfo,
            @NotNull final EnumInfo enumInfo, @NotNull final AbstractClassInfo abstractClassInfo, final boolean sealed, @NotNull final Type type,
            final boolean hasGenerics, final boolean dtoBean, final boolean dtoBeanWithInputIoProperty, @NotNull final String suggestedTestClassMemberName,
            @NotNull final String suggestedTestClassLocalFieldName) {
        this.name = name;
        this.canonicalName = canonicalName;
        this.superClasses = superClasses;
        this.packageName = packageName;
        this.constructors = constructors;
        this.preferredConstructor = preferredConstructor;
        this.annotations = annotations;
        this.singletonInfo = singletonInfo;
        this.enumInfo = enumInfo;
        this.abstractClassInfo = abstractClassInfo;
        this.sealed = sealed;
        this.type = type;
        this.dtoBean = dtoBean;
        this.dtoBeanWithInputIoProperty = dtoBeanWithInputIoProperty;
        this.testClassMemberName = suggestedTestClassMemberName;
        this.testClassLocalFieldName = suggestedTestClassLocalFieldName;
        this.methods = methods;
        this.preferredInitSetters = preferredInitSetters;
        this.preferredInitMethods = new FluentListImpl<>(preferredInitSetters);
        this.fields = fields;
        this.hasGenerics = hasGenerics;
        this.nonObjectLowestOverrideMethodsCache = null;
        this.lowestOverrideMethodsCache = null;
        for(final Api.Method method : this.methods) {
            ((MethodImpl) method).setContainingClass(this);
        }
        for(final Api.ClassMember field : this.fields) {
            ((ClassMemberImpl) field).setContainingClass(this);
        }
    }

    @Override
    @NotNull
    public String getName() {
        return name;
    }

    @Override
    public String getCanonicalName() {
        return canonicalName;
    }

    @Override
    public boolean isSealed() {
        return sealed;
    }

    @Override
    public boolean isSealedAbstract() {
        return sealed && abstractClassInfo.isAbstract();
    }

    @Override
    public boolean isDtoBean() {
        return dtoBean;
    }

    @Override
    public boolean isDtoBeanWithInputIoProperty() {
        return dtoBeanWithInputIoProperty;
    }

    /**
     * @return the name of the package containing the class or "" if the class is in the default package.
     */
    @Override
    @NotNull
    public String getPackageName() {
        return packageName;
    }

    @Override
    public Api.FluentList<Api.Annotation> getAnnotations() {
        return annotations;
    }

    // Constructor info.
    @Override
    @NotNull
    public Api.FluentList<Constructor> getConstructors() {
        return constructors;
    }

    @Override
    @NotNull
    public Api.FluentList<Constructor> getPublicConstructors() {
        return constructors.stream().filter(Constructor::isPublic).collect(Collectors.toCollection(FluentListImpl::new));
    }

    @Override
    @NotNull
    public Api.FluentList<Constructor> getProtectedConstructors() {
        return constructors.stream().filter(Constructor::isProtected).collect(Collectors.toCollection(FluentListImpl::new));
    }

    @Override
    @NotNull
    public Api.FluentList<Constructor> getPackageLocalConstructors() {
        return constructors.stream().filter(Constructor::isPackageLocal).collect(Collectors.toCollection(FluentListImpl::new));
    }

    @Override
    @NotNull
    public Api.FluentList<Constructor> getPrivateConstructors() {
        return constructors.stream().filter(Constructor::isPrivate).collect(Collectors.toCollection(FluentListImpl::new));
    }

    @Override
    @Nullable
    public Constructor getPreferredConstructor() {
        return preferredConstructor;
    }

    @Override
    public void setPreferredConstructor(@Nullable final Constructor preferredConstructor) {
        this.preferredConstructor = preferredConstructor;
    }

    // Methods info.
    @Override
    @NotNull
    public Api.FluentList<Method> getPublicInstanceMethods() {
        return methods.stream().filter(
                        method -> method.isPublic()
                                && !method.isStatic())
                .collect(Collectors.toCollection(FluentListImpl::new));
    }

    @Override
    @NotNull
    public Api.FluentList<Method> getProtectedInstanceMethods() {
        return methods.stream().filter(
                        method -> method.isProtected()
                                && !method.isStatic())
                .collect(Collectors.toCollection(FluentListImpl::new));
    }

    @Override
    @NotNull
    public Api.FluentList<Method> getPackageLocalInstanceMethods() {
        return methods.stream().filter(
                        method -> method.isPackageLocal()
                                && !method.isStatic())
                .collect(Collectors.toCollection(FluentListImpl::new));
    }

    @NotNull
    @Override
    public Api.FluentList<Method> getPrivateInstanceMethods() {
        return methods.stream().filter(
                        method -> method.isPrivate()
                                && !method.isStatic())
                .collect(Collectors.toCollection(FluentListImpl::new));
    }

    @NotNull
    @Override
    public Api.FluentList<Method> getSimpleSetters() {
        return methods.stream().filter(Method::isSetter).collect(Collectors.toCollection(FluentListImpl::new));
    }

    @NotNull
    @Override
    public Api.FluentList<Method> getSimpleGetters() {
        return methods.stream().filter(Method::isGetter).collect(Collectors.toCollection(FluentListImpl::new));
    }

    @Override
    @NotNull
    public Api.FluentList<Method> getPreferredInitSetters() {
        return preferredInitSetters;
    }

    @Override
    @NotNull
    public Api.FluentList<Method> getPreferredInitMethods() {
        return preferredInitMethods;
    }

    @NotNull
    @Override
    public Api.FluentList<Method> getInstanceMethods() {
        return methods.stream().filter(
                        method -> !method.isStatic())
                .collect(Collectors.toCollection(FluentListImpl::new));
    }

    @Override
    @NotNull
    public Api.FluentList<Method> getPublicStaticMethods() {
        return methods.stream().filter(
                        method -> method.isPublic() && method.isStatic())
                .collect(Collectors.toCollection(FluentListImpl::new));
    }

    @Override
    @NotNull
    public Api.FluentList<Method> getPackageLocalStaticMethods() {
        return methods.stream().filter(
                        method -> method.isPackageLocal() && method.isStatic())
                .collect(Collectors.toCollection(FluentListImpl::new));
    }

    @Override
    @NotNull
    public Api.FluentList<Method> getProtectedStaticMethods() {
        return methods.stream().filter(
                        method -> method.isProtected() && method.isStatic())
                .collect(Collectors.toCollection(FluentListImpl::new));
    }

    @NotNull
    @Override
    public Api.FluentList<Method> getPrivateStaticMethods() {
        return methods.stream().filter(
                        method -> method.isPrivate() && method.isStatic())
                .collect(Collectors.toCollection(FluentListImpl::new));
    }

    @NotNull
    @Override
    public Api.FluentList<Method> getStaticMethods() {
        return methods.stream().filter(
                        Method::isStatic)
                .collect(Collectors.toCollection(FluentListImpl::new));
    }

    @Override
    @NotNull
    public Api.FluentList<Method> getMethods() {
        return methods;
    }

    @Override
    public Api.FluentList<Method> getAllMethods() {
        return getAllMethodsImpl(true);
    }

    @Override
    public Api.FluentList<Method> getAllNonObjectMethods() {
        return getAllMethodsImpl(false);
    }

    @NotNull
    private Api.FluentList<Method> getAllMethodsImpl(final boolean includeObjectMethods) {
        final Api.FluentList<Method> ret = new FluentListImpl<>();
        ret.addAll(methods);
        for(final SourceClass superClass : superClasses) {
            if(!includeObjectMethods && StringUtils.equals(JavaNames.JavaLangObject, superClass.getCanonicalName())) {
                continue;
            }
            ret.addAll(superClass.getMethods());
        }
        return ret;
    }

    @Override
    public Api.FluentList<Method> getAllLowestOverrideMethods() {
        if(lowestOverrideMethodsCache == null) {
            lowestOverrideMethodsCache = getAllLowestOverrideMethodsImpl(true);
        }
        return lowestOverrideMethodsCache;
    }

    @Override
    public Api.FluentList<Method> getAllNonObjectLowestOverrideMethods() {
        if(nonObjectLowestOverrideMethodsCache == null) {
            nonObjectLowestOverrideMethodsCache = getAllLowestOverrideMethodsImpl(false);
        }
        return nonObjectLowestOverrideMethodsCache;
    }

    @NotNull
    private Api.FluentList<Method> getAllLowestOverrideMethodsImpl(final boolean includeObjectMethods) {
        final Api.FluentList<Method> ret = new FluentListImpl<>();
        final Set<Method> higherMethods = new HashSet<>();
        final List<SourceClass> thisAndSupers = new ArrayList<>(superClasses.size() + 1);
        thisAndSupers.add(this);
        thisAndSupers.addAll(superClasses);
        for(final SourceClass sourceClass : thisAndSupers) {
            if(!includeObjectMethods && StringUtils.equals(JavaNames.JavaLangObject, sourceClass.getCanonicalName())) {
                continue;
            }
            for(final Method method : sourceClass.getMethods()) {
                if(!higherMethods.contains(method)) {
                    ret.add(method);
                }
                higherMethods.addAll(method.getSuperMethods());
            }
        }
        return ret;
    }

    @NotNull
    @Override
    public Api.FluentList<ClassMember> getInstanceFields() {
        return fields.stream().filter(
                member -> !member.isStatic()).collect(Collectors.toCollection(FluentListImpl::new));
    }

    @NotNull
    @Override
    public Api.FluentList<ClassMember> getPublicInstanceFields() {
        return fields.stream().filter(
                member -> (!member.isStatic()) && member.isPublic()).collect(Collectors.toCollection(FluentListImpl::new));
    }

    @NotNull
    @Override
    public Api.FluentList<ClassMember> getPackageLocalInstanceFields() {
        return fields.stream().filter(
                member -> (!member.isStatic()) && member.isPackageLocal()).collect(Collectors.toCollection(FluentListImpl::new));
    }

    @NotNull
    @Override
    public Api.FluentList<ClassMember> getProtectedInstanceFields() {
        return fields.stream().filter(
                member -> (!member.isStatic()) && member.isProtected()).collect(Collectors.toCollection(FluentListImpl::new));
    }

    @NotNull
    @Override
    public Api.FluentList<ClassMember> getPrivateInstanceFields() {
        return fields.stream().filter(
                member -> (!member.isStatic()) && member.isPrivate()).collect(Collectors.toCollection(FluentListImpl::new));
    }

    @NotNull
    @Override
    public Api.FluentList<ClassMember> getStaticFields() {
        return fields.stream().filter(
                ClassMember::isStatic).collect(Collectors.toCollection(FluentListImpl::new));
    }

    @NotNull
    @Override
    public Api.FluentList<ClassMember> getPublicStaticFields() {
        return fields.stream().filter(
                member -> member.isStatic() && member.isPublic()).collect(Collectors.toCollection(FluentListImpl::new));
    }

    @NotNull
    @Override
    public Api.FluentList<ClassMember> getPackageLocalStaticFields() {
        return fields.stream().filter(
                member -> member.isStatic() && member.isPackageLocal()).collect(Collectors.toCollection(FluentListImpl::new));
    }

    @NotNull
    @Override
    public Api.FluentList<ClassMember> getProtectedStaticFields() {
        return fields.stream().filter(
                member -> member.isStatic() && member.isProtected()).collect(Collectors.toCollection(FluentListImpl::new));
    }

    @NotNull
    @Override
    public Api.FluentList<ClassMember> getPrivateStaticFields() {
        return fields.stream().filter(
                member -> member.isStatic() && member.isPrivate()).collect(Collectors.toCollection(FluentListImpl::new));
    }

    @Override
    @NotNull
    public Api.FluentList<ClassMember> getFields() {
        return fields;
    }

    @NotNull
    @Override
    public Api.FluentList<Method> getPackageVisibleStaticCreatorMethods() {
        return this.methods.stream().filter(
                method -> {
                    final boolean isStatic = method.isStatic();
                    final boolean isAccessible = method.isPublic() || method.isProtected() || method.isPackageLocal();
                    final String returnTypeCanonicalName = method.getReturnType() == null ? null : method.getReturnType().getCanonicalName();
                    final boolean paramsDoNotUseSourceClass = canonicalName == null || method.getParameters().stream().noneMatch(x -> x.getType().isOrHasAnyNestedTypeParamWith(canonicalName));
                    final boolean ret = isStatic
                            && isAccessible
                            && paramsDoNotUseSourceClass
                            && (returnTypeCanonicalName != null && Objects.equals(returnTypeCanonicalName, canonicalName));
                    return ret;
                }).collect(Collectors.toCollection(FluentListImpl::new));
    }

    // Fields Info.
    @Override
    @NotNull
    public Api.FluentList<ClassMember> getDependencyAnnotatedFields() {
        return this.fields.stream().filter(
                        ClassMember::isDependencyAnnotated)
                .collect(Collectors.toCollection(FluentListImpl::new));
    }

    @NotNull
    @Override
    public Api.FluentList<Method> methodsAnnotatedWith(final String... annotations) {
        if(annotations == null || annotations.length == 0) {
            return Api.FluentListFactory.emptyList();
        }
        return this.methods.stream().filter(x -> x.hasAnnotation(annotations)).collect(Collectors.toCollection(FluentListImpl::new));
    }

    @NotNull
    @Override
    public Api.FluentList<Method> constructorsAnnotatedWith(final String... annotations) {
        if(annotations == null || annotations.length == 0) {
            return Api.FluentListFactory.emptyList();
        }
        return this.constructors.stream().filter(x -> x.hasAnnotation(annotations)).collect(Collectors.toCollection(FluentListImpl::new));
    }

    @Override
    @NotNull
    public Api.FluentList<ClassMember> fieldsAnnotatedWith(final String... annotations) {
        if(annotations == null || annotations.length == 0) {
            return Api.FluentListFactory.emptyList();
        }

        final FluentListImpl<ClassMember> annotatedFields = new FluentListImpl<>();
        for(final ClassMember classMember : this.fields) {
            if(classMember.hasAnnotation(annotations)) {
                annotatedFields.add(classMember);
            }
        }
        return annotatedFields;
    }

    // Template class field naming info.
    @Override
    public String getTestClassMemberName() {
        return testClassMemberName;
    }

    @Override
    public void setTestClassMemberName(final String instanceMemberName) {
        if(instanceMemberName == null) {
            this.testClassMemberName = "null";
        } else {
            this.testClassMemberName = instanceMemberName;
        }
    }

    @Override
    public String getTestClassLocalFieldName() {
        return this.testClassLocalFieldName;
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
    public boolean isSingleton() {
        return singletonInfo.isSingleton();
    }

    @Override
    public boolean isEnum() {
        return enumInfo.isEnum();
    }

    @Override
    @Nullable
    public String getEnumFirstValue() {
        return enumInfo.firstEnumValue();
    }

    @Override
    @NotNull
    public Api.FluentList<String> getEnumValues() {
        return enumInfo.getEnumValues();
    }

    @Override
    public String getSingletonAccessExpression() {
        return singletonInfo.accessExpression();
    }

    // Abstract class info.
    @Override
    public boolean isAbstract() {
        return abstractClassInfo.isAbstract();
    }

    @Override
    @NotNull
    public Type getType() {
        return type;
    }

    @Override
    public boolean getHasGenerics() {
        return hasGenerics;
    }

    @Override
    public Api.FluentList<ClassMember> getAllFields() {
        final Api.FluentList<ClassMember> ret = new FluentListImpl<>();
        ret.addAll(fields);
        for(final SourceClass superClass : superClasses) {
            ret.addAll(superClass.getFields());
        }
        return ret;
    }

    @Override
    @NotNull
    @Deprecated
    public Api.FluentList<String> getAllSuperTypes() {
        return getAllSuperTypeNames();
    }

    @Override
    public Api.FluentList<String> getAllSuperTypeNames() {
        final Api.FluentList<String> ret = new FluentListImpl<>(superClasses.size());
        for(final SourceClass superClass : superClasses) {
            if(superClass.getCanonicalName() != null) {
                ret.add(superClass.getCanonicalName());
            }
        }
        return ret;
    }

    @Override
    public Api.FluentList<SourceClass> getAllSuperClasses() {
        return superClasses;
    }

    @Override
    @Nullable
    public String getAbstractClassBody() {
        return abstractClassInfo.getClassBodyWithStubMethodImplementations();
    }

    @NotNull
    public AbstractClassInfo getAbstractClassInfo() {
        return abstractClassInfo;
    }

    @Override
    public String toString() {
        return "SourceClassImpl{" +
                "name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", constructors=" + constructors +
                ", methods=" + methods +
                ", fields=" + fields +
                ", singletonInfo=" + singletonInfo +
                ", enumInfo=" + enumInfo +
                ", abstractClassInfo=" + abstractClassInfo +
                ", type=" + type +
                ", hasGenerics=" + hasGenerics +
                ", testClassMemberName='" + testClassMemberName + '\'' +
                ", testClassLocalFieldName='" + testClassLocalFieldName + '\'' +
                ", preferredConstructor=" + preferredConstructor +
                '}';
    }
}
