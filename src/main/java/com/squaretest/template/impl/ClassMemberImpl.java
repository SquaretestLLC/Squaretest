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

import com.intellij.psi.PsiAnnotation;
import com.squaretest.template.AccessModifier;
import com.squaretest.template.api.Api;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class ClassMemberImpl extends VariableImpl implements Api.ClassMember {

    /**
     * Inject annotationCanonicalNames.
     * Include both the class name and qualified name; if the workspace is not configured correctly and cannot resolve
     * the types, {@link PsiAnnotation#getQualifiedName()} will return just the classname.
     */
    private static final List<String> InjectAnnotations = Arrays.asList(
            "Inject",
            "javax.inject.Inject",
            "Autowired",
            "org.springframework.beans.factory.annotation.Autowired",
            "com.google.inject.Inject");

    @NotNull
    private final AccessModifier accessModifier;
    private final boolean isStatic;
    private final boolean isTransient;
    private final FluentListImpl<Api.Variable> possibleSourceVariables;
    private final FluentListImpl<Api.Method> getters;
    private final FluentListImpl<Api.Method> setters;
    private final boolean isVisibleToTestClass;
    private Api.SourceClass containingClass;

    public ClassMemberImpl(
            @NotNull final VariableImpl variable,
            @NotNull final AccessModifier accessModifier,
            final boolean isStatic, final boolean isTransient, final boolean isVisibleToTestClass) {
        super(variable);
        this.accessModifier = accessModifier;
        this.isStatic = isStatic;
        this.isTransient = isTransient;
        this.isVisibleToTestClass = isVisibleToTestClass;
        this.possibleSourceVariables = new FluentListImpl<>();
        this.getters = new FluentListImpl<>();
        this.setters = new FluentListImpl<>();
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
    public boolean isTransient() {
        return isTransient;
    }

    @Override
    public boolean isDependencyAnnotated() {
        for(final Api.Annotation annotation : this.annotations) {
            String nameToUse = annotation.getCanonicalName();
            if(nameToUse == null) {
                nameToUse = annotation.getName();
            }


            if(InjectAnnotations.contains(nameToUse)) {
                return true;
            }
            // Search the InjectAnnotations for the simple name as well. This handles the case where the code has
            // an @Inject annotation that is not from javax.inject.
            final String annotationSimpleName = StringUtils.substringAfterLast(nameToUse, ".");
            // If the annotation has no ".", annotationSimpleName will be "". This can happen when the annotation
            // is defined in the same package as the SubjectClass or the classpath is not configured correctly and
            // the canonicalName for the annotation could not be resolved; the canonicalName will be the simple-name
            // in that case.
            if(StringUtils.isNotEmpty(nameToUse) && InjectAnnotations.contains(annotationSimpleName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Api.FluentList<Api.Variable> getPossibleSourceVariables() {
        return possibleSourceVariables;
    }

    public void addPossibleSourceVariable(final Api.Variable possibleSourceVar) {
        this.possibleSourceVariables.add(possibleSourceVar);
    }

    @Override
    public Api.FluentList<Api.Method> getGetters() {
        return getters;
    }

    @Override
    public Api.FluentList<Api.Method> getSetters() {
        return setters;
    }

    @Override
    public boolean isVisibleToTestClass() {
        return isVisibleToTestClass;
    }

    public Api.SourceClass getContainingClass() {
        return containingClass;
    }

    public void setContainingClass(final Api.SourceClass containingClass) {
        this.containingClass = containingClass;
    }
}
