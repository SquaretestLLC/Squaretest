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

import com.intellij.codeInsight.AnnotationUtil;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiJavaCodeReferenceElement;
import com.intellij.psi.PsiJvmModifiersOwner;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.PsiModifierListOwner;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class SQAnnotationUtil {

    /**
     * Prefixes that can be on Classes, Fields or Methods and imply the containing class is likely a data transfer
     * object.
     */
    private static final String[] DataAnnotationPrefixes = new String[]{
            "lombok", "javax.xml.bind.annotation", "com.fasterxml.jackson.annotation",
            "com.fasterxml.jackson.dataformat",
            "com.google.gson.annotations", "com.amazonaws.services.dynamodbv2.datamodeling",
            "software.amazon.awssdk.enhanced.dynamodb.mapper.annotations",
            "com.thoughtworks.xstream.annotations.XStreamAlias",
            "com.thoughtworks.xstream.annotations.XStreamAliasType",
            "com.thoughtworks.xstream.annotations.XStreamAsAttribute",
            "com.thoughtworks.xstream.annotations.XStreamImplicit",
            "com.thoughtworks.xstream.annotations.XStreamImplicitCollection",
            "com.thoughtworks.xstream.annotations.XStreamInclude",
            "com.thoughtworks.xstream.annotations.XStreamOmitField",
            "io.swagger.annotations.ApiModel",
            "io.swagger.annotations.ApiModelProperty",
            "javax.persistence.Table",
            "javax.persistence.Entity",
            "javax.persistence.MappedSuperclass",
            "javax.persistence.Basic",
            "javax.persistence.Column",
            "javax.persistence.Id",
            "javax.persistence.IdClass",
            "javax.persistence.GeneratedValue",
            "javax.persistence.DiscriminatorColumn",
            "jakarta.persistence.Table",
            "jakarta.persistence.Entity",
            "jakarta.persistence.MappedSuperclass",
            "jakarta.persistence.Basic",
            "jakarta.persistence.Column",
            "jakarta.persistence.Id",
            "jakarta.persistence.IdClass",
            "jakarta.persistence.GeneratedValue",
            "jakarta.persistence.DiscriminatorColumn"
    };

    /**
     * Annotations that are considered "Data Annotations" only if they appear on a field.
     */
    private static final String[] FieldAnnotationCanonicalNames = new String[]{
            "io.swagger.annotations.ApiParam",
            "io.swagger.v3.oas.annotations.media.Schema",
            "io.swagger.v3.oas.annotations.media.ArraySchema"
    };

    /**
     * Annotation prefixes that are considered "Data Annotations" only if they appear on a field.
     */
    private static final String[] FieldAnnotationCanonicalNamePrefixes = new String[]{
            "javax.validation"
    };

    // Cover annotations with data-like names. This way, Squaretest will recognize annotations from other libraries;
    // e.g. influxdb https://github.com/influxdata/influxdb-java/blob/master/src/main/java/org/influxdb/annotation/Column.java.
    private static final String[] DataAnnotationNames = new String[]{
            "Table",
            "Column"
    };

    public static boolean hasDataAnnotation(final PsiClass psiClass) {
        final PsiMethod[] methods = psiClass.getMethods();
        final PsiField[] fields = psiClass.getFields();
        return hasAnnotationWithPrefix(psiClass, DataAnnotationPrefixes)
                || hasAnnotationWithDataName(psiClass)
                || containsAnyWithDataAnnotationPrefix(methods)
                || containsAnyWithDataAnnotationPrefix(fields)
                || containsAnyWithFieldDataAnnotationCanonicalName(fields)
                || containsAnyWithFieldDataAnnotationPrefixes(fields)
                || containsAnyAnnotationWithDataName(methods)
                || containsAnyAnnotationWithDataName(fields);
    }

    private static boolean containsAnyWithFieldDataAnnotationPrefixes(final PsiField[] fields) {
        return Arrays.stream(fields).anyMatch(x -> hasAnnotationWithPrefix(x, FieldAnnotationCanonicalNamePrefixes));
    }

    private static boolean containsAnyWithFieldDataAnnotationCanonicalName(final PsiField[] fields) {
        return Arrays.stream(fields).anyMatch(SQAnnotationUtil::hasAnnotationWithFieldDataCanonicalName);
    }

    private static boolean containsAnyAnnotationWithDataName(final PsiModifierListOwner[] modifierListOwnerList) {
        return Arrays.stream(modifierListOwnerList).anyMatch(SQAnnotationUtil::hasAnnotationWithDataName);
    }

    private static boolean containsAnyWithDataAnnotationPrefix(final PsiModifierListOwner[] modifierListOwnerList) {
        return Arrays.stream(modifierListOwnerList).anyMatch(psiModifierListOwner -> hasAnnotationWithPrefix(psiModifierListOwner, DataAnnotationPrefixes));
    }

    private static boolean hasAnnotationWithPrefix(
            final PsiModifierListOwner psiModifierListOwner, final String[] dataAnnotationPrefixes) {
        final PsiModifierList modifierList = psiModifierListOwner.getModifierList();
        if(modifierList != null) {
            final PsiAnnotation[] annotations = modifierList.getAnnotations();
            return annotations.length != 0 && Arrays.stream(annotations).anyMatch(x -> StringUtils.startsWithAny(x.getQualifiedName(), dataAnnotationPrefixes));
        }
        return false;
    }

    private static boolean hasAnnotationWithFieldDataCanonicalName(
            final PsiModifierListOwner psiModifierListOwner) {
        final PsiModifierList modifierList = psiModifierListOwner.getModifierList();
        if(modifierList != null) {
            final PsiAnnotation[] annotations = modifierList.getAnnotations();
            return annotations.length != 0 && Arrays.stream(annotations).anyMatch(x -> StringUtils.equalsAny(x.getQualifiedName(), FieldAnnotationCanonicalNames));
        }
        return false;
    }

    private static boolean hasAnnotationWithDataName(
            final PsiModifierListOwner psiModifierListOwner) {
        return hasAnnotationWithName(psiModifierListOwner, DataAnnotationNames);
    }

    private static boolean hasAnnotationWithName(
            final PsiModifierListOwner psiModifierListOwner, final String... annotationNames) {
        final PsiModifierList modifierList = psiModifierListOwner.getModifierList();
        if(modifierList != null) {
            final PsiAnnotation[] annotations = modifierList.getAnnotations();
            return annotations.length != 0 && Arrays.stream(annotations).anyMatch(x -> {
                final PsiJavaCodeReferenceElement nameReferenceElement = x.getNameReferenceElement();
                return nameReferenceElement != null && StringUtils.equalsAny(nameReferenceElement.getReferenceName(), annotationNames);
            });
        }
        return false;
    }

    public static String getStringAnnotationValue(@NotNull PsiAnnotation psiAnnotation, @NotNull String parameter, @NotNull String defaultValue) {
        final String result = AnnotationUtil.getDeclaredStringAttributeValue(psiAnnotation, parameter);
        return result != null ? result : defaultValue;
    }

    public static boolean hasAnnotation(@NotNull final PsiJvmModifiersOwner annotationOwner, final String... annotations) {
        return getAnnotation(annotationOwner, annotations) != null;
    }

    public static PsiAnnotation getAnnotation(@NotNull final PsiJvmModifiersOwner annotationOwner, final String... annotations) {
        final PsiAnnotation[] ownerAnnotations = annotationOwner.getAnnotations();
        for(final PsiAnnotation ownerAnnotation : ownerAnnotations) {
            if(StringUtils.equalsAny(ownerAnnotation.getQualifiedName(), annotations)) {
                return ownerAnnotation;
            }
        }
        return null;
    }
}
