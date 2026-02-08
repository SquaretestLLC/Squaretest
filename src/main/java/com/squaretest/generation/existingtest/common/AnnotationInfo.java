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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.squaretest.template.impl.NameUtils.computeSimpleName;

public class AnnotationInfo {

    public static final String NameKey = "name";

    @NotNull
    private final String annotationCanonicalName;
    @NotNull
    private final List<Pair<String, String>> nameValuePairs;

    public AnnotationInfo(
            @NotNull final String annotationName, @NotNull final List<Pair<String, String>> nameValuePairs) {
        this.annotationCanonicalName = annotationName;
        this.nameValuePairs = nameValuePairs;
    }

    @NotNull
    public String getAnnotationCanonicalName() {
        return annotationCanonicalName;
    }

    @NotNull
    public List<Pair<String, String>> getNameValuePairs() {
        return nameValuePairs;
    }

    public boolean isMock() {
        final String simpleName = computeSimpleName(annotationCanonicalName);
        return StringUtils.equalsAny(simpleName, Constants.MockAnnotationNames);
    }
}
