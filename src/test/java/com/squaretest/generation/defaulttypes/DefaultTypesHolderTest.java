/*
 *
 *  * Copyright 2026 Squaretest LLC.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.squaretest.generation.defaulttypes;

import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiModifier;
import com.intellij.testFramework.LightProjectDescriptor;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import helpers.SquaretestProjectDescriptor;
import helpers.TestLibs;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.squaretest.generation.defaulttypes.TypeCreatorUtil.overridesEquals;

public class DefaultTypesHolderTest extends LightJavaCodeInsightFixtureTestCase {

    private static final List<String> PrimitiveTypes = List.of("byte", "short", "int", "long", "float", "double", "boolean", "char");
    private static final List<String> FinalClassesTreatedAsMockable = List.of("okhttp3.Response");
    private static final List<String> StaticClassesTreatedAsMockable =
            List.of(
                    "org.springframework.web.client.RestClient.RequestBodySpec",
                    "org.springframework.web.client.RestClient.RequestBodyUriSpec",
                    "org.springframework.web.client.RestClient.RequestHeadersUriSpec",
                    "org.springframework.web.reactive.function.client.WebClient.RequestBodySpec",
                    "org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec",
                    "org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec");

    private DefaultTypesHolder defaultTypesHolderUnderTest;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        defaultTypesHolderUnderTest = DefaultTypesHolder.getInstance();
    }

    @Test
    public void testVerifyFinalClassesNotMarkedAsMockable() throws Throwable {
        runTestRunnable(() -> {
            final List<String> errors = new ArrayList<>();
            final Map<String, DefaultTypeInfo> canonicalNamesMap = defaultTypesHolderUnderTest.getCanonicalNameToDefaultTypeMap();
            for(final Map.Entry<String, DefaultTypeInfo> nameInfoPair : canonicalNamesMap.entrySet()) {
                final String canonicalName = nameInfoPair.getKey();
                final DefaultTypeInfo defaultTypeInfo = nameInfoPair.getValue();
                if(isPrimitive(canonicalName)) {
                    if(defaultTypeInfo.isMockable()) {
                        errors.add("primitive type marked as mockable: " + nameInfoPair.getKey());
                    }
                    continue;
                }
                final PsiClass theClass = myFixture.findClass(canonicalName);
                if(theClass.hasModifierProperty(PsiModifier.FINAL)) {
                    if(defaultTypeInfo.isMockable() && !FinalClassesTreatedAsMockable.contains(canonicalName)) {
                        errors.add("final class marked as mockable " + canonicalName);
                    }
                } else if(theClass.isEnum()) {
                    if(defaultTypeInfo.isMockable()) {
                        errors.add("Enum marked as mockable " + canonicalName);
                    }
                } else if(theClass.hasModifierProperty(PsiModifier.STATIC) && !StaticClassesTreatedAsMockable.contains(canonicalName)) {
                    if(defaultTypeInfo.isMockable()) {
                        errors.add("Static class marked as mockable " + canonicalName);
                    }
                } else {
                    if(!defaultTypeInfo.isMockable()) {
                        errors.add("Normal class not marked as mockable " + canonicalName);
                    }
                }
            }

            printErrors(errors);
            assertEquals(0, errors.size());
        });
    }

    @Test
    public void testHasEqualsAndIsPrimitiveCorrect() throws Throwable {
        runTestRunnable(() -> {
            final Map<String, DefaultTypeInfo> canonicalNamesMap = defaultTypesHolderUnderTest.getCanonicalNameToDefaultTypeMap();
            final List<String> errors = new ArrayList<>();
            for(final Map.Entry<String, DefaultTypeInfo> nameInfoPair : canonicalNamesMap.entrySet()) {
                final String canonicalName = nameInfoPair.getKey();
                final DefaultTypeInfo value = nameInfoPair.getValue();
                // Check isPrimitive().
                if(value.isPrimitive() != isPrimitive(canonicalName)) {
                    errors.add(String.format("Entry %s does not have correct isPrimitive field", canonicalName));
                }
                if((!value.isSimple()) && isPrimitive(canonicalName)) {
                    errors.add(String.format("Entry %s is primitive but has isSimple = false", canonicalName));
                }

                if(isPrimitive(canonicalName)) {
                    continue;
                }

                // Check java.lang.Class and java.lang.Package. They "override equals" for our purposes, because the instances
                // are unique to the ClassLoader; i.e. Class<?> foo = Foo.class; Class<?> bar = Foo.class; foo.equals(bar) is true.
                // Also, java.nio.ByteOrder "overrides equals", because the only instances that can exist are defined in the class
                // (the class is final and the only constructor is private).
                final String[] classesThatEffectivelyOverrideEquals = {JavaNames.JavaLangPackage, JavaNames.JavaLangClass, "java.nio.ByteOrder"};
                if(StringUtils.equalsAny(canonicalName, classesThatEffectivelyOverrideEquals)) {
                    if(!value.overridesEquals()) {
                        errors.add(String.format("%s do override .equals() (for our purposes)", Arrays.toString(classesThatEffectivelyOverrideEquals)));
                    }
                    continue;
                }
                if(canonicalName.equals("java.util.concurrent.TimeUnit")) {
                    // TODO: Remove this check when this issue is resolved.
                    //  Class java.util.concurrent.TimeUnit is not found in JDK21.
                    //  Tracking the issue in https://youtrack.jetbrains.com/issue/IDEA-321080.
                    continue;
                }
                final PsiClass theClass = myFixture.findClass(canonicalName);
                final boolean actuallyOverridesEquals = overridesEquals(theClass);
                if(actuallyOverridesEquals != value.overridesEquals()) {
                    errors.add(String.format("Entry %s does not have correct overridesEquals field", canonicalName));
                }
            }
            printErrors(errors);
            assertEquals(0, errors.size());
        });
    }

    @Test
    public void testAbsentInitExpressionPresentIfNotPrimitive() {
        final Map<String, DefaultTypeInfo> canonicalNamesMap = defaultTypesHolderUnderTest.getCanonicalNameToDefaultTypeMap();
        final List<String> errors = new LinkedList<>();
        for(final Map.Entry<String, DefaultTypeInfo> nameInfoPair : canonicalNamesMap.entrySet()) {
            final String canonicalName = nameInfoPair.getKey();
            final DefaultTypeInfo value = nameInfoPair.getValue();
            if(!value.isPrimitive()) {
                if(value.getAbsentInitExpression() == null) {
                    errors.add("Non primitive type without absent init expression: " + canonicalName);
                }
            }
        }
        printErrors(errors);
        assertEquals(0, errors.size());
    }

    @Test
    public void testVerifyCanonicalNamesInKeysMatchThoseInDefaultTypeInfos() {
        final Map<String, DefaultTypeInfo> canonicalNamesMap = defaultTypesHolderUnderTest.getCanonicalNameToDefaultTypeMap();
        System.err.println("Number of types: " + canonicalNamesMap.size());
        final List<String> errors = new LinkedList<>();
        for(final Map.Entry<String, DefaultTypeInfo> nameInfoPair : canonicalNamesMap.entrySet()) {
            final String canonicalname = nameInfoPair.getKey();
            final String defaultTypeInfoCanonicalName = nameInfoPair.getValue().getCanonicalName();
            if(!Objects.equals(canonicalname, defaultTypeInfoCanonicalName)) {
                errors.add(String.format("CanonicalName in key/value don't match: key=%s, value=%s", canonicalname,
                        defaultTypeInfoCanonicalName));
            }
        }
        printErrors(errors);
        assertEquals(0, errors.size());
    }

    @Test
    public void testVerifyTypesWithMapsHaveSeparateGroovyInitExpressions() {
        final Map<String, DefaultTypeInfo> canonicalNamesMap = defaultTypesHolderUnderTest.getCanonicalNameToDefaultTypeMap();
        System.err.println("Number of types: " + canonicalNamesMap.size());
        final List<String> errors = new LinkedList<>();
        for(final Map.Entry<String, DefaultTypeInfo> nameInfoPair : canonicalNamesMap.entrySet()) {
            final DefaultTypeInfo info = nameInfoPair.getValue();
            if(info.getInitExpression().contains("java.util.HashMap")) {
                if(info.getGroovyInitExpression() == null) {
                    errors.add(String.format("Type: %s uses HashMap in initExpression but does not have a groovyInitExpression",
                            nameInfoPair.getKey()));
                }
                if(info.getJava9InitExpression() == null) {
                    errors.add(String.format("Type: %s uses HashMap in initExpression but does not have a java9InitExpression",
                            nameInfoPair.getKey()));
                }
            }
        }
        printErrors(errors);
        assertEquals(0, errors.size());
    }

    @Test
    public void testVerifyTypesWithGroovyPlaceholdersHaveGroovyInitExpressions() {
        final Map<String, DefaultTypeInfo> canonicalNamesMap = defaultTypesHolderUnderTest.getCanonicalNameToDefaultTypeMap();
        System.err.println("Number of types: " + canonicalNamesMap.size());
        final List<String> errors = new LinkedList<>();
        for(final Map.Entry<String, DefaultTypeInfo> nameInfoPair : canonicalNamesMap.entrySet()) {
            final DefaultTypeInfo info = nameInfoPair.getValue();
            if(info.getGroovyInitExpressionWithTypePlaceholder() != null
                    && info.getGroovyInitExpression() == null) {
                errors.add(String.format("Type: %s has groovyInitExpressionWithTypePlaceholder but no groovyInitExpression",
                        nameInfoPair.getKey()));
            }
        }
        printErrors(errors);
        assertEquals(0, errors.size());
    }

    @Test
    public void testVerifyTypesWith_isEmptyIfTypeParamsAreEmpty_Have_initExpressionWithTypePlaceholder() {
        final Map<String, DefaultTypeInfo> canonicalNamesMap = defaultTypesHolderUnderTest.getCanonicalNameToDefaultTypeMap();
        System.err.println("Number of types: " + canonicalNamesMap.size());
        final List<String> errors = new LinkedList<>();
        for(final Map.Entry<String, DefaultTypeInfo> nameInfoPair : canonicalNamesMap.entrySet()) {
            final DefaultTypeInfo info = nameInfoPair.getValue();
            boolean initExpressionWithPlaceholderIsNull = info.getInitExpressionWithTypePlaceholder() == null;
            if(info.isEmptyIfTypeParamsAreEmpty()) {
                if(initExpressionWithPlaceholderIsNull) {
                    errors.add(String.format("Type: %s has isEmptyIfTypeParamsAreEmpty = true, but no initExpressionWithTypePlaceholder",
                            nameInfoPair.getKey()));
                }
            }
            if(info.isSimpleIfTypeParamsAreSimple()) {
                if(initExpressionWithPlaceholderIsNull) {
                    errors.add(String.format("Type: %s has isSimpleIfTypeParamsAreSimple = true, but no initExpressionWithTypePlaceholder",
                            nameInfoPair.getKey()));
                }
            }
            if(info.isAbsentIfTypeParamsAreAbsent()) {
                if(initExpressionWithPlaceholderIsNull) {
                    errors.add(String.format("Type: %s has isAbsentIfTypeParamsAreAbsent = true, but no initExpressionWithTypePlaceholder",
                            nameInfoPair.getKey()));
                }
            }
        }
        printErrors(errors);
        assertEquals(0, errors.size());
    }

    @Test
    public void verifyCommonsLang2AndCommonsLang3TypesDontMix() {
        final Map<String, DefaultTypeInfo> canonicalNamesMap = defaultTypesHolderUnderTest.getCanonicalNameToDefaultTypeMap();
        final List<String> errors = new LinkedList<>();
        for(final Map.Entry<String, DefaultTypeInfo> nameInfoPair : canonicalNamesMap.entrySet()) {
            final DefaultTypeInfo info = nameInfoPair.getValue();
            final String canonicalName = info.getCanonicalName();
            final String commonsLang3Substring = "org.apache.commons.lang3";
            final String commonsLang2Substring = "org.apache.commons.lang.";
            if(canonicalName.contains(commonsLang3Substring)) {
                if(containsStringContaining(info.getImportsRequired(), commonsLang2Substring)) {
                    errors.add(String.format("Name %s contains imports from commonslang2", canonicalName));
                }
            } else if(canonicalName.contains(commonsLang2Substring)) {
                if(containsStringContaining(info.getImportsRequired(), commonsLang3Substring)) {
                    errors.add(String.format("Name %s contains imports from commonslang3", canonicalName));
                }
            }
        }
        printErrors(errors);
        assertEquals(0, errors.size());
    }

    /**
     * At this time, all importsRequired entries are empty. All initExpressions use fully-qualified names.
     * This was done to reduce the possibility of importing conflicting names from different packages.
     */
    @Test
    public void verifyNoImports() {
        final List<String> errors = new LinkedList<>();
        final Map<String, DefaultTypeInfo> canonicalNamesMap = defaultTypesHolderUnderTest.getCanonicalNameToDefaultTypeMap();
        for(final DefaultTypeInfo info : canonicalNamesMap.values()) {
            if(!info.getImportsRequired().isEmpty()) {
                errors.add(String.format("%s contains a non-empty importsRequired list.", info.getCanonicalName()));
            }
        }
        printErrors(errors);
        assertEquals(0, errors.size());
    }

    @NotNull
    @Override
    protected LightProjectDescriptor getProjectDescriptor() {
        return new SquaretestProjectDescriptor(LanguageLevel.JDK_X, TestLibs.AWSV1Libs, TestLibs.GRPCApi, TestLibs.AWSV2Libs,
                TestLibs.AWSV1LambdaLibs, TestLibs.CommonLibs, TestLibs.JacksonLibs, TestLibs.TencentLibs,
                TestLibs.ElasticSearchLibs, TestLibs.GoogleCloudLibs, TestLibs.SpringFrameworkLibs,
                TestLibs.JakartaWSAPILibs, TestLibs.JavaXWSAPILibs, TestLibs.ApacheHttpLibs,
                Arrays.asList(TestLibs.CommonsLang2, TestLibs.JodaTime, TestLibs.Guava, TestLibs.OkHttp3,
                        TestLibs.RxJava3, TestLibs.JavaXPersistence));
    }

    private static boolean containsStringContaining(final List<String> stringsToCheck, final String containsStringToFilterOut) {
        for(final String str : stringsToCheck) {
            if(str.contains(containsStringToFilterOut)) {
                return true;
            }
        }
        return false;
    }

    private static void printErrors(final List<String> errors) {
        for(final String error : errors) {
            System.err.println(error);
        }
    }

    private boolean isPrimitive(final String canonicalName) {
        return PrimitiveTypes.contains(canonicalName);
    }
}