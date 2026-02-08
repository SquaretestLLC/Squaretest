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
package com.squaretest.completion;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.util.PsiUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

public class SourceClassFinder {

    private static final String[] TestClassSuffixes = new String[]{
            "IntegrationTest", "IntegrationTests",
            "UnitTest", "UnitTests",
            "Test", "Tests", "IT"
    };
    private static final String TestClassPrefix = "Test";

    private final ProjectSearcher projectSearcher;

    public SourceClassFinder(final ProjectSearcher projectSearcher) {
        this.projectSearcher = projectSearcher;
    }

    public static boolean hasPrefix(final String name, final String prefix) {
        if(name == null || prefix == null) {
            return false;
        }

        if(name.startsWith(prefix) && name.length() > prefix.length()) {
            final char charAfterPrefix = name.charAt(prefix.length());
            return Character.isUpperCase(charAfterPrefix) || charAfterPrefix == '_';
        }
        return false;
    }

    public PsiClass findSourceClassForTestClass(final PsiClass testClass) {

        // Try to determine the source class from the test class fields; i.e. look for a test class member whose name
        // ends with underTest.
        PsiClass sourceClass = determineSourceClassFromFields(testClass);
        if(sourceClass != null) {
            return sourceClass;
        }

        // Try removing known suffixes from the test class's canonical name to find the corresponding source class.
        final List<String> canonicalNamesToTry = getCanonicalNamesToTry(testClass.getQualifiedName());
        sourceClass = findFirstClassWithCanonicalName(canonicalNamesToTry);
        if(sourceClass != null) {
            return sourceClass;
        }

        // Try removing known suffixes and searching for the class name.
        final List<String> namesToTry = getShortNamesToTry(testClass.getName());
        sourceClass = findFirstClassWithName(namesToTry);
        return sourceClass;
    }

    @Nullable
    private PsiClass determineSourceClassFromFields(final PsiClass testClass) {
        for(final PsiField field : testClass.getFields()) {
            if(StringUtils.endsWithIgnoreCase(field.getName(), "UnderTest")) {
                return PsiUtil.resolveClassInType(field.getType());
            }
        }
        return null;
    }

    private PsiClass findFirstClassWithName(final List<String> shortNames) {
        // Search for classes with one of the given shortNames.
        for(final String shortName : shortNames) {
            final PsiClass psiClass = projectSearcher.findClassWithShortName(shortName);
            if(psiClass != null) {
                return psiClass;
            }
        }
        return null;
    }

    private PsiClass findFirstClassWithCanonicalName(final List<String> canonicalNames) {
        for(final String name : canonicalNames) {
            final PsiClass psiClass = projectSearcher.findClassWithCanonicalName(name);
            if(psiClass != null) {
                return psiClass;
            }
        }
        return null;
    }

    private List<String> getShortNamesToTry(final String testClassName) {
        final List<String> names = new LinkedList<>();
        for(final String possibleSuffix : TestClassSuffixes) {
            if(testClassName.endsWith(possibleSuffix)) {
                names.add(testClassName.substring(0, testClassName.lastIndexOf(possibleSuffix)));
            }
        }
        if(hasPrefix(testClassName, TestClassPrefix)) {
            // Remove the prefix and any underscores that come after it.
            String nameWithoutPrefix = StringUtils.stripStart(StringUtils.removeStart(testClassName, TestClassPrefix), "_");
            if(!StringUtils.isEmpty(nameWithoutPrefix)) {
                names.add(nameWithoutPrefix);
            }
        }
        return names;
    }

    private List<String> getCanonicalNamesToTry(final String testClassCanonicalName) {
        final List<String> names = new LinkedList<>();
        for(final String possibleSuffix : TestClassSuffixes) {
            if(testClassCanonicalName.endsWith(possibleSuffix)) {
                names.add(testClassCanonicalName.substring(0, testClassCanonicalName.lastIndexOf(possibleSuffix)));
            }
        }
        if(hasPrefix(testClassCanonicalName, TestClassPrefix)) {
            // Remove the prefix and any underscores that come after it.
            String nameWithoutPrefix = StringUtils.stripStart(StringUtils.removeStart(testClassCanonicalName, TestClassPrefix), "_");
            if(!StringUtils.isEmpty(nameWithoutPrefix)) {
                names.add(nameWithoutPrefix);
            }
        }
        return names;
    }
}
