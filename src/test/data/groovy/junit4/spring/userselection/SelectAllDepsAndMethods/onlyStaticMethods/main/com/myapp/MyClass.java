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
package com.myapp;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.regex.PatternSyntaxException;

public class MyClass {
    private MyClass() {
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }

    @Nullable
    public static String trimToNull(final String str) {
        final String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    public static Optional<String> trimToOptional(final String str) {
        final String ts = trim(str);
        return isEmpty(ts) ? Optional.empty() : Optional.of(ts);
    }

    public static String[] split(final String str, final String separatorChars) {
        return str.split(separatorChars);
    }

    /**
     * Squaretest should use a normal test method (not a simple one) for multidimensional arrays.
     */
    public static String[][] splitAll(final String[] strs, final String separatorChars) {
        return new String[][]{};
    }

    public static String replaceFirst(final String text, final String regex, final String replacement) throws PatternSyntaxException {
        return "";
    }

    public static long binomialCoefficient(final int n, final int k) throws IllegalArgumentException, ArithmeticException{
        return 0;
    }

    public static double distance(int[] p1, int[] p2) {
        return 0.0;
    }

    public static double distance(double[] p1, double[] p2) {
        return 0.0;
    }

    public static int[][] multMatrix(int[][] m1, int[][] m2) {
        return new int[][]{};
    }
}
