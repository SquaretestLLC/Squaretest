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

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SQStringUtils extends StringUtils {

    public static String longest(final String... strings) {
        return longest(Arrays.asList(strings));
    }

    public static String longest(final List<String> strings) {
        if(strings == null || strings.isEmpty()) {
            return null;
        }
        Optional<String> longest = strings.stream().filter(Objects::nonNull).max(Comparator.comparing(String::length));
        return longest.orElse(null);
    }

    public static String shortest(final String... strings) {
        return shortest(Arrays.asList(strings));
    }

    public static String shortest(final List<String> strings) {
        if(strings == null || strings.isEmpty()) {
            return null;
        }
        Optional<String> shortest = strings.stream().filter(Objects::nonNull).min(Comparator.comparing(String::length));
        return shortest.orElse(null);
    }

    public static String[] substringsBetween(final String str, final String tag) {
        return StringUtils.substringsBetween(str, tag, tag);
    }
}
