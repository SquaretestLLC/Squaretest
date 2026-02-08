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
package com.squaretest.generation.existingtest.main;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.PsiNameValuePair;
import com.intellij.psi.PsiType;
import com.intellij.psi.util.InheritanceUtil;
import com.squaretest.generation.existingtest.common.AnnotationInfo;
import com.squaretest.generation.existingtest.common.MemberField;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.plugins.groovy.lang.psi.api.auxiliary.modifiers.annotation.GrAnnotation;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.expressions.literals.GrLiteral;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MemberFieldPopulator {

    public List<MemberField> computeMembers(final PsiClass testClass) {
        final PsiField[] members = testClass.getAllFields();
        final List<MemberField> ret = new ArrayList<>(members.length);
        for(final PsiField member : members) {
            final String name = member.getName();
            final List<AnnotationInfo> annotations = getAnnotations(member);
            @Nullable final PsiClass typeClass = getClassFromType(member.getType());
            final List<String> superTypes = getSuperTypesForClass(typeClass);
            String qualifiedName = null;
            if(typeClass != null) {
                qualifiedName = typeClass.getQualifiedName();
            }
            boolean isInSuperClass = member.getContainingClass() != testClass;
            ret.add(new MemberField(
                    superTypes,
                    annotations,
                    qualifiedName,
                    name,
                    isInSuperClass, member.getType().getCanonicalText()));
        }

        // Put fields in this test class first.
        ret.sort((object1, object2) -> Boolean.compare(object1.isInTestClass(), object2.isInTestClass()));

        return ret;
    }

    public static PsiClass getClassFromType(final PsiType type) {
        if(type instanceof PsiClassType) {
            return ((PsiClassType) type).resolve();
        }
        return null;
    }

    private static List<String> getSuperTypesForClass(@Nullable final PsiClass topLevelClass) {
        if(topLevelClass == null) {
            return Collections.emptyList();
        }
        final Set<PsiClass> superClasses = new LinkedHashSet<>();
        InheritanceUtil.getSuperClasses(topLevelClass, superClasses, false);
        final List<String> ret = new ArrayList<>(superClasses.size());
        for(final PsiClass superType : superClasses) {
            ret.add(superType.getQualifiedName());
        }
        return ret;
    }

    private static List<AnnotationInfo> getAnnotations(final PsiField member) {
        final PsiModifierList modifierList = member.getModifierList();
        if(modifierList == null) {
            return Collections.emptyList();
        }
        final PsiAnnotation[] annotations = modifierList.getAnnotations();
        final List<AnnotationInfo> annotationInfos = new ArrayList<>(annotations.length);
        for(final PsiAnnotation annotation : annotations) {
            String annotationCanonicalName = getCanonicalOrShortName(annotation);
            if(annotationCanonicalName == null) {
                continue;
            }
            final List<Pair<String, String>> nameValuePairs = new ArrayList<>(annotation.getParameterList().getAttributes().length);
            for(final PsiNameValuePair pair : annotation.getParameterList().getAttributes()) {
                String literalValue = pair.getLiteralValue();
                if(literalValue == null) {
                    // This will return null if the value is GrLiteral. If the value is GrLiteral, we need to call
                    // getText().
                    final PsiAnnotationMemberValue value = pair.getValue();
                    if(value instanceof GrLiteral) {
                        final String textValue = StringUtils.unwrap(value.getText(), "\"");
                        if(StringUtils.isNotBlank(textValue)) {
                            literalValue = textValue;
                        }
                    }
                }
                nameValuePairs.add(new ImmutablePair<>(pair.getName(), literalValue));
            }
            annotationInfos.add(new AnnotationInfo(annotationCanonicalName, nameValuePairs));
        }
        return annotationInfos;
    }

    @Nullable
    static String getCanonicalOrShortName(@NotNull final PsiAnnotation annotation) {
        String annotationCanonicalName = annotation.getQualifiedName();
        if(annotationCanonicalName == null) {
            if(annotation instanceof GrAnnotation) {
                annotationCanonicalName = ((GrAnnotation) annotation).getShortName();
            } else {
                return null;
            }
        }
        return annotationCanonicalName;
    }
}
