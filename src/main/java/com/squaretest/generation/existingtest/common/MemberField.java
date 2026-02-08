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
package com.squaretest.generation.existingtest.common;

import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public record MemberField(@NotNull List<String> superTypeCanonicalNames, @NotNull List<AnnotationInfo> annotations,
                          @Nullable String typeCanonicalName, @NotNull String name, boolean isInSuperClass,
                          String canonicalText) {

    public boolean hasAnnotation(final String annotationQualifiedName) {
        return annotations.stream().anyMatch(x -> x.getAnnotationCanonicalName().equals(annotationQualifiedName));
    }

    public boolean hasAnnotationWithNameParameter(final String value) {
        return hasAnnotationWithKeyValuePair(AnnotationInfo.NameKey, value);
    }

    public boolean hasAnnotationWithKeyValuePair(final String name, final String value) {
        for(final AnnotationInfo annotationInfo : annotations) {
            final List<Pair<String, String>> nameValuePairs = annotationInfo.getNameValuePairs();
            for(final Pair<String, String> nameValuePair : nameValuePairs) {
                if(Objects.equals(name, nameValuePair.getLeft()) && Objects.equals(value, nameValuePair.getRight())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isExactType(@NotNull final String canonicalName) {
        return typeCanonicalName != null && this.typeCanonicalName.equals(canonicalName);
    }

    public boolean isType(@NotNull final String canonicalName) {
        return Objects.equals(this.typeCanonicalName, canonicalName) || superTypeCanonicalNames.contains(canonicalName);
    }

    public boolean isMock() {
        return annotations.stream().anyMatch(AnnotationInfo::isMock);
    }

    public boolean isInTestClass() {
        return !isInSuperClass;
    }
}
