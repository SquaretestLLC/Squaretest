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

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NameUtils {
    public static boolean containsAnnotation(
            final List<String> memberAnnotationCanonicalNames, final String[] simpleOrCanonicalNamesToSearchFor) {
        if(simpleOrCanonicalNamesToSearchFor == null || simpleOrCanonicalNamesToSearchFor.length == 0) {
            return false;
        }

        final List<String> simpleNamesToSearchFor = Arrays.stream(simpleOrCanonicalNamesToSearchFor).filter(x -> x != null && !x.contains(".")).toList();
        final List<String> canonicalNamesToSearchFor = Arrays.stream(simpleOrCanonicalNamesToSearchFor).filter(x -> x != null && x.contains(".")).toList();
        final List<String> memberAnnotationSimpleNames = computeSimpleNames(memberAnnotationCanonicalNames);

        // If the lists of simple names have at least 1 element in common, return true.
        if(!Collections.disjoint(simpleNamesToSearchFor, memberAnnotationSimpleNames)) {
            return true;
        }

        // If the lists of canonical names have at least 1 element in common, return true.
        if(!Collections.disjoint(canonicalNamesToSearchFor, memberAnnotationCanonicalNames)) {
            return true;
        }

        return false;
    }

    public static boolean containsAnnotationWithPrefix(
            final List<String> canonicalNamesToSearch, final String[] simpleOrCanonicalPrefixesToSearchFor) {
        if(simpleOrCanonicalPrefixesToSearchFor == null || simpleOrCanonicalPrefixesToSearchFor.length == 0) {
            return false;
        }

        final String[] simplePrefixesToSearchFor = Arrays.stream(simpleOrCanonicalPrefixesToSearchFor).filter(x -> x != null && !x.contains(".")).toArray(String[]::new);
        final String[] canonicalPrefixesToSearchFor = Arrays.stream(simpleOrCanonicalPrefixesToSearchFor).filter(x -> x != null && x.contains(".")).toArray(String[]::new);
        final List<String> memberAnnotationSimpleNames = computeSimpleNames(canonicalNamesToSearch);

        // If the lists of simple names have at least 1 element in common, return true.
        for(final String memberAnnotationSimpleName : memberAnnotationSimpleNames) {
            if(StringUtils.startsWithAny(memberAnnotationSimpleName, simplePrefixesToSearchFor)) {
                return true;
            }
        }

        for(final String memberCanonicalName : canonicalNamesToSearch) {
            if(StringUtils.startsWithAny(memberCanonicalName, canonicalPrefixesToSearchFor)) {
                return true;
            }
        }

        return false;
    }

    public static List<String> computeSimpleNames(final List<String> simpleOrCanonicalNames) {
        final List<String> ret = new ArrayList<>(simpleOrCanonicalNames.size());
        for(final String annotation : simpleOrCanonicalNames) {
            ret.add(computeSimpleName(annotation));
        }
        return ret;
    }

    public static String computeSimpleName(final String simpleOrCanonicalNames) {
        if(simpleOrCanonicalNames.contains(".")) {
            return StringUtils.substringAfterLast(simpleOrCanonicalNames, ".");
        } else {
            return simpleOrCanonicalNames;
        }
    }
}
