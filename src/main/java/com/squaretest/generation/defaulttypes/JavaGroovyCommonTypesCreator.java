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

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.TypeConversionUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;

public class JavaGroovyCommonTypesCreator {

    @NotNull
    private final TestDependencyInfoProvider testDependencyInfoProvider;
    @NotNull
    private final StringAndUUIDValueProvider stringAndUUIDValueProvider;
    @NotNull
    private final PrimitiveValueProvider primitiveValueProvider;
    @NotNull
    private final DefaultTypesHolder defaultTypesHolder;

    public JavaGroovyCommonTypesCreator(
            @NotNull final TestDependencyInfoProvider testDependencyInfoProvider,
            @NotNull final StringAndUUIDValueProvider stringAndUUIDValueProvider,
            @NotNull final PrimitiveValueProvider primitiveValueProvider) {
        this.testDependencyInfoProvider = testDependencyInfoProvider;
        this.stringAndUUIDValueProvider = stringAndUUIDValueProvider;
        this.primitiveValueProvider = primitiveValueProvider;
        this.defaultTypesHolder = DefaultTypesHolder.getInstance();
    }

    @Nullable
    public DefaultTypeInfo createType(
            @Nullable final String canonicalName,
            @NotNull final String parameterName,
            @Nullable final PsiElement source) {
        if(canonicalName == null) {
            return null;
        }

        // Use values from the dataflow analysis for primitive types if they're available.
        if(isPrimitiveOrBoxed(canonicalName)) {
            final String initExpression = primitiveValueProvider.getInitExpressionForPrimitive(source);
            if(initExpression != null) {
                final DefaultTypeInfo info = this.defaultTypesHolder.get(canonicalName);
                if(info != null) {
                    return updateInfoWithInitExpression(info, initExpression);
                }
            }
        }

        // Look up the type in the map.
        final DefaultTypeInfo info = this.defaultTypesHolder.get(canonicalName);
        if(info != null) {
            return applySpecialParamNameLogicIfNeeded(info, parameterName);
        }

        // Special case for strings and similar classes.
        switch(canonicalName) {
            case "java.lang.String" -> {
                final String initExpression = stringAndUUIDValueProvider.getStringInitExpression(parameterName, source);
                return new DefaultTypeInfo(
                        canonicalName,
                        initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                        false, false, true, true, false, false, true, false, false, false, null, null);
            }
            case "java.lang.CharSequence" -> {
                final String initExpression = stringAndUUIDValueProvider.getStringInitExpression(parameterName, source);
                return new DefaultTypeInfo(
                        canonicalName,
                        initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                        true, false, false, true, false, false, true, false, false, false, null, null);
            }
            case "java.lang.Object" -> {
                final String initExpression = stringAndUUIDValueProvider.getStringInitExpression(parameterName, source);
                return new DefaultTypeInfo(
                        canonicalName,
                        initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                        true, false, false, false, false, false, true, false, false, false, null, null);
            }

            // If we have an Executor and Guava is on the test classpath, use MoreExecutors.directExecutor().
            case "java.util.concurrent.Executor" -> {
                if(testDependencyInfoProvider.testPathContainsClass("com.google.common.util.concurrent.MoreExecutors")) {
                    final String initExpression = "com.google.common.util.concurrent.MoreExecutors.directExecutor()";
                    return new DefaultTypeInfo(
                            canonicalName,
                            initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                            true, false, false, false, false, false, false, false, false, false, null, null);
                } else {
                    // Mock the Executor by default, but also set the init expressions to method refs to Runnable.run() in
                    // case the developer decides not to mock it.
                    return new DefaultTypeInfo(
                            canonicalName,
                            "java.lang.Runnable::run", null, "java.lang.Runnable.&run", null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                            true, true, false, false, false, false, false, false, false, false, null, null);
                }
            }

            // If we have an ExecutorService and Guava is on the test classpath, use MoreExecutors.newDirectExecutorService().
            case "java.util.concurrent.ExecutorService" -> {
                if(testDependencyInfoProvider.testPathContainsClass("com.google.common.util.concurrent.MoreExecutors")) {
                    final String initExpression = "com.google.common.util.concurrent.MoreExecutors.newDirectExecutorService()";
                    return new DefaultTypeInfo(
                            canonicalName,
                            initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                            true, false, false, false, false, false, false, false, false, false, null, null);
                } else {
                    final String initExpression = "java.util.concurrent.Executors.newSingleThreadExecutor()";
                    return new DefaultTypeInfo(
                            canonicalName,
                            initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                            true, true, false, false, false, false, false, false, false, false, null, null);
                }
            }

            // If we have an ListeningExecutorService, use a ListeningExecutorService that invokes the task immediately and returns a ListenableFuture containing the result.
            case "com.google.common.util.concurrent.ListeningExecutorService" -> {
                final String initExpression = "com.google.common.util.concurrent.MoreExecutors.listeningDecorator(com.google.common.util.concurrent.MoreExecutors.newDirectExecutorService())";
                return new DefaultTypeInfo(
                        canonicalName,
                        initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                        true, false, false, false, false, false, false, false, false, false, null, null);
            }

            // Generate a new UUID each time one is requested; the generated test might have use UUIDs in several methods.
            // Each one should be unique.
            case "java.util.UUID" -> {
                final String initExpression = stringAndUUIDValueProvider.getUUIDInitExpression(source);
                return new DefaultTypeInfo(
                        canonicalName,
                        initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                        false, false, true, false, false, false, true, false, false, false, null, null);
            }
        }
        // Use the MockHttpServletRequest from Spring if they're available on the test classpath.
        if(StringUtils.equalsAny(canonicalName, "javax.servlet.http.HttpServletRequest", "javax.servlet.ServletRequest",
                "jakarta.servlet.http.HttpServletRequest", "jakarta.servlet.http.HttpServlet")) {
            if(testDependencyInfoProvider.testPathContainsClass("org.springframework.mock.web.MockHttpServletRequest")) {
                final String initExpression = "new org.springframework.mock.web.MockHttpServletRequest()";
                return new DefaultTypeInfo(
                        canonicalName,
                        initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                        true, false, false, false, false, false, false, false, false, false, null, "org.springframework.mock.web.MockHttpServletRequest");
            } else {
                final String initExpression = "null";
                return new DefaultTypeInfo(
                        canonicalName,
                        initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                        true, true, false, false, false, false, false, false, false, false, null, null);
            }
        }
        if(StringUtils.equalsAny(canonicalName, "javax.servlet.ServletConfig", "jakarta.servlet.ServletConfig")) {
            if(testDependencyInfoProvider.testPathContainsClass("org.springframework.mock.web.MockServletConfig")) {
                final String initExpression = "new org.springframework.mock.web.MockServletConfig()";
                return new DefaultTypeInfo(
                        canonicalName,
                        initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                        true, false, false, false, false, false, false, false, false, false, null, "org.springframework.mock.web.MockServletConfig");
            } else {
                final String initExpression = "null";
                return new DefaultTypeInfo(
                        canonicalName,
                        initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                        true, false, false, false, false, false, false, false, false, false, null, null);
            }
        }
        if(StringUtils.equalsAny(canonicalName, "javax.servlet.http.HttpServletResponse", "javax.servlet.ServletResponse",
                "jakarta.servlet.http.HttpServletResponse", "jakarta.servlet.ServletResponse")) {
            if(testDependencyInfoProvider.testPathContainsClass("org.springframework.mock.web.MockHttpServletResponse")) {
                final String initExpression = "new org.springframework.mock.web.MockHttpServletResponse()";
                return new DefaultTypeInfo(
                        canonicalName,
                        initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                        true, false, false, false, false, false, false, false, false, false, null, "org.springframework.mock.web.MockHttpServletResponse");
            } else {
                final String initExpression = "null";
                return new DefaultTypeInfo(
                        canonicalName,
                        initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                        true, true, false, false, false, false, false, false, false, false, null, null);
            }
        }
        switch(canonicalName) {
            case "org.springframework.web.multipart.MultipartFile" -> {
                if(testDependencyInfoProvider.testPathContainsClass("org.springframework.mock.web.MockMultipartFile")) {
                    final String initExpression = "new org.springframework.mock.web.MockMultipartFile(\"name\", \"content\".getBytes())";
                    return new DefaultTypeInfo(
                            canonicalName,
                            initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                            true, false, false, false, false, false, false, false, false, false, null, null);
                } else {
                    final String initExpression = "null";
                    return new DefaultTypeInfo(
                            canonicalName,
                            initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                            true, true, false, false, false, false, false, false, false, false, null, null);
                }
            }
            case "java.security.Principal" -> {
                if(testDependencyInfoProvider.testPathContainsClass("org.springframework.security.authentication.TestingAuthenticationToken")) {
                    final String initExpression = "new org.springframework.security.authentication.TestingAuthenticationToken(\"user\", \"pass\", \"ROLE_USER\")";
                    return new DefaultTypeInfo(
                            canonicalName,
                            initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                            true, false, true, false, false, false, false, false, false, false, null, null);
                } else {
                    final String initExpression = "new com.sun.security.auth.UserPrincipal(\"name\")";
                    return new DefaultTypeInfo(
                            canonicalName,
                            initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                            true, false, true, false, false, false, false, false, false, false, null, null);
                }
            }
            case "org.springframework.core.env.Environment" -> {
                if(testDependencyInfoProvider.testPathContainsClass("org.springframework.mock.env.MockEnvironment")) {
                    final String initExpression = "new org.springframework.mock.env.MockEnvironment()";
                    return new DefaultTypeInfo(
                            canonicalName,
                            initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                            true, false, false, false, false, false, false, false, false, false, null, null);
                } else {
                    final String initExpression = "null";
                    return new DefaultTypeInfo(
                            canonicalName,
                            initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                            true, true, false, false, false, false, false, false, false, false, null, null);
                }
            }
            case "io.jsonwebtoken.Claims" -> {
                if(testDependencyInfoProvider.testPathContainsClass("io.jsonwebtoken.impl.DefaultClaims")) {
                    final String initExpression = "new io.jsonwebtoken.impl.DefaultClaims()";
                    return new DefaultTypeInfo(
                            canonicalName,
                            initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                            true, false, true, false, false, false, false, false, false, false, null, null);
                } else {
                    final String initExpression = "null";
                    return new DefaultTypeInfo(
                            canonicalName,
                            initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                            true, false, true, false, false, false, false, false, false, false, null, null);
                }
            }
            case "io.jsonwebtoken.Clock" -> {
                if(testDependencyInfoProvider.testPathContainsClass("io.jsonwebtoken.impl.FixedClock")) {
                    final String initExpression = "new io.jsonwebtoken.impl.FixedClock(new java.util.GregorianCalendar(2020, java.util.Calendar.JANUARY, 1).getTime())";
                    return new DefaultTypeInfo(
                            canonicalName,
                            initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                            true, false, false, false, false, false, false, false, false, false, null, null);
                } else {
                    final String initExpression = "null";
                    return new DefaultTypeInfo(
                            canonicalName,
                            initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                            true, true, false, false, false, false, false, false, false, false, null, null);
                }
            }
            case "io.jsonwebtoken.Header" -> {
                if(testDependencyInfoProvider.testPathContainsClass("io.jsonwebtoken.impl.DefaultHeader")) {
                    final String initExpression = "new io.jsonwebtoken.impl.DefaultHeader<>()";
                    return new DefaultTypeInfo(
                            canonicalName,
                            initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                            true, false, true, false, false, false, false, false, false, false, null, null);
                } else {
                    final String initExpression = "null";
                    return new DefaultTypeInfo(
                            canonicalName,
                            initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                            true, false, true, false, false, false, false, false, false, false, null, null);
                }
            }
            case "io.jsonwebtoken.Jwt" -> {
                if(testDependencyInfoProvider.testPathContainsClass("io.jsonwebtoken.impl.DefaultJwt")) {
                    final String initExpression = "new io.jsonwebtoken.impl.DefaultJwt<>(new io.jsonwebtoken.impl.DefaultHeader<>(), null)";
                    return new DefaultTypeInfo(
                            canonicalName,
                            initExpression, "new io.jsonwebtoken.impl.DefaultJwt<>(new io.jsonwebtoken.impl.DefaultHeader<>(), {{VALUE2}})", initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                            true, false, false, false, false, false, false, false, true, true, null, null);
                } else {
                    final String initExpression = "null";
                    return new DefaultTypeInfo(
                            canonicalName,
                            initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                            true, false, false, false, false, false, false, false, false, false, null, null);
                }
            }
        }
        if(StringUtils.equalsAny(canonicalName, "org.reactivestreams.Subscriber", "org.reactivestreams.Subscription")) {
            final String initExpression;
            if(testDependencyInfoProvider.testPathContainsClass("io.reactivex.rxjava3.subscribers.TestSubscriber")) {
                initExpression = "io.reactivex.rxjava3.subscribers.TestSubscriber.create()";
            } else if(testDependencyInfoProvider.testPathContainsClass("io.reactivex.subscribers.TestSubscriber")) {
                initExpression = "io.reactivex.subscribers.TestSubscriber.create()";
            } else {
                initExpression = "null";
            }
            return new DefaultTypeInfo(
                    canonicalName,
                    initExpression, null, initExpression, null, null, null, false, "null", null, null, null, null, null, null, null, Collections.emptyList(),
                    true, false, false, false, false, false, false, false, false, false, null, null);
        }
        if(StringUtils.equalsAny(canonicalName, "org.reactivestreams.Publisher")) {
            final String initExpression;
            final String initExpressionWithTypePlaceholder;
            final String failureInitExpression;
            if(testDependencyInfoProvider.testPathContainsClass("io.reactivex.rxjava3.core.Flowable")) {
                initExpression = "io.reactivex.rxjava3.core.Flowable.just(null)";
                initExpressionWithTypePlaceholder = "io.reactivex.rxjava3.core.Flowable.just({{VALUE1}})";
                failureInitExpression = "io.reactivex.rxjava3.core.Flowable.error(new java.lang.Exception(\"message\"))";
            } else {
                initExpression = "io.reactivex.Flowable.just(null)";
                initExpressionWithTypePlaceholder = "io.reactivex.Flowable.just({{VALUE1}})";
                failureInitExpression = "io.reactivex.Flowable.error(new java.lang.Exception(\"message\"))";
            }
            return new DefaultTypeInfo(
                    canonicalName,
                    initExpression,
                    initExpressionWithTypePlaceholder, initExpression, null, null, null, false, "null", null, null, failureInitExpression, null, null, null, null, Collections.emptyList(),
                    true, false, false, false, false, false, false, false, false, false, null, null);
        }
        return null;
    }

    private static boolean isPrimitiveOrBoxed(@NotNull final String canonicalName) {
        return TypeConversionUtil.isPrimitive(canonicalName) || TypeConversionUtil.isPrimitiveWrapper(canonicalName);
    }

    private DefaultTypeInfo updateInfoWithInitExpression(final DefaultTypeInfo info, final String initExpression) {
        return new DefaultTypeInfo(info.getCanonicalName(), initExpression, info.getInitExpressionWithTypePlaceholder(), initExpression, info.getGroovyInitExpressionWithTypePlaceholder(), info.getJava9InitExpression(), info.getJava9InitExpressionWithTypePlaceholder(), info.isOptional(), info.getAbsentInitExpression(),
                null, null, null, null, null, null, null, info.getImportsRequired(), info.isMockable, info.getShouldBeMocked(), info.overridesEquals(), info.isPrimitive(), info.isBean(),
                false, info.isSimple(), false,
                info.isAbsentIfTypeParamsAreAbsent(), false, info.getInitSetterKeys(), null);
    }

    public static boolean isLikelyUUID(final String parameterName) {
        return StringUtils.containsAny(parameterName, "UUID", "GUID")
                || StringUtils.equalsAny(parameterName, "uuid", "uuid4", "guid");
    }

    private static DefaultTypeInfo applySpecialParamNameLogicIfNeeded(@NotNull final DefaultTypeInfo info, @NotNull final String parameterName) {
        boolean isNumeric = StringUtils.equalsAny(info.getCanonicalName(), "byte", "java.lang.Byte", "short", "java.lang.Short", "int", "java.lang.Integer", "long", "java.lang.Long");
        if(!isNumeric) {
            return info;
        }
        boolean isDayWeekOrMonth = VariableNameTimePatterns.DayPattern.matcher(parameterName).matches() || VariableNameTimePatterns.WeekPattern.matcher(parameterName).matches() || VariableNameTimePatterns.MonthPattern.matcher(parameterName).matches();
        boolean isYear = VariableNameTimePatterns.YearPattern.matcher(parameterName).matches();
        String newInitExpression = null;
        if(isDayWeekOrMonth) {
            // Update init expression to use 1 instead of 0. This fixes issues like https://github.com/SquaretestLLC/Squaretest/issues/21.
            newInitExpression = createOneInitExpression(info.getCanonicalName());
        } else if(isYear) {
            newInitExpression = createYearInitExpression(info.getCanonicalName());
        }

        if(newInitExpression != null) {
            return new DefaultTypeInfo(info.getCanonicalName(), newInitExpression, info.getInitExpressionWithTypePlaceholder(), info.getGroovyInitExpression(), info.getGroovyInitExpressionWithTypePlaceholder(), info.getJava9InitExpression(), info.getJava9InitExpressionWithTypePlaceholder(), info.isOptional(), info.getAbsentInitExpression(),
                    null, null, null, null, null, null, null, info.getImportsRequired(), info.isMockable, info.getShouldBeMocked(), info.overridesEquals(), info.isPrimitive(), info.isBean(),
                    false, info.isSimple(), false,
                    info.isAbsentIfTypeParamsAreAbsent(), false, info.getInitSetterKeys(), null);
        }
        return info;
    }

    private static String createOneInitExpression(final String canonicalName) {
        if(StringUtils.equalsAny(canonicalName, "int", "java.lang.Integer")) {
            return "1";
        } else if(StringUtils.equalsAny(canonicalName, "short", "java.lang.Short")) {
            return "(short) 1";
        } else if(StringUtils.equalsAny(canonicalName, "byte", "java.lang.Byte")) {
            return "(byte) 0b1";
        } else if(StringUtils.equalsAny(canonicalName, "long", "java.lang.Long")) {
            return "1L";
        }
        return null;
    }

    private static String createYearInitExpression(final String canonicalName) {
        final int year = 2020;
        if(StringUtils.equalsAny(canonicalName, "int", "java.lang.Integer")) {
            return Integer.toString(year);
        } else if(StringUtils.equalsAny(canonicalName, "short", "java.lang.Short")) {
            return "(short) " + year;
        } else if(StringUtils.equalsAny(canonicalName, "byte", "java.lang.Byte")) {
            return "(byte) 0b1";
        } else if(StringUtils.equalsAny(canonicalName, "long", "java.lang.Long")) {
            return year + "L";
        }
        return null;
    }

    public boolean isKnown(@Nullable final String canonicalName) {
        if(canonicalName == null) {
            return false;
        }
        return defaultTypesHolder.get(canonicalName) != null;
    }
}
