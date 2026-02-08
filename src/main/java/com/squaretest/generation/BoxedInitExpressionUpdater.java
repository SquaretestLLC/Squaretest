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
package com.squaretest.generation;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.squaretest.completion.frameworkdetection.FrameworkInferencer;
import com.squaretest.generation.defaulttypes.TestDependencyInfoProvider;
import com.squaretest.generation.dependencyinteraction.RegexUtils;
import com.squaretest.generation.runconfig.infoprovider.FrameworkInfo;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.MethodImpl;
import com.squaretest.template.impl.SimpleExitInfoImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

public class BoxedInitExpressionUpdater {
    private static final Set<String> SpecialBoxedTypes = Set.of(
            "java.lang.Byte",
            "java.lang.Character",
            "java.lang.Integer",
            "java.lang.Long",
            "java.lang.Short"
    );

    private static final List<Pattern> ByteRegexes = List.of(
            Pattern.compile("^\\(\\s*byte\\s*\\)\\s*[\\d]+$"),
            Pattern.compile("^\\(\\s*byte\\s*\\)\\s*0[bB][0,1]+$"),
            Pattern.compile("^0[bB][0,1]+$"));

    private static final List<Pattern> CharRegexes = List.of(
            Pattern.compile("^\\(\\s*char\\s*\\)\\s*(\\d+)$"),
            Pattern.compile("^'\\\\u.{4}'$"),
            Pattern.compile("^'.'$")
    );

    private static final Pattern IntRegex = Pattern.compile("^[-+]?\\d+$");
    private static final Pattern LongRegex = Pattern.compile("^[-+]?\\d+L?$");
    private static final Pattern ShortRegex = Pattern.compile("^\\(\\s*short\\s*\\)\\s*[\\d]+$");

    private final FrameworkInferencer frameworkInferencer;
    private final TestDependencyInfoProvider testDependencyInfoProvider;

    public BoxedInitExpressionUpdater(
            final FrameworkInferencer frameworkInferencer,
            final TestDependencyInfoProvider testDependencyInfoProvider) {
        this.frameworkInferencer = frameworkInferencer;
        this.testDependencyInfoProvider = testDependencyInfoProvider;
    }

    public void updateInitExpressionsIfNeeded(final FileTemplate fileTemplate, final Api.SourceClass sourceClass) {
        if(!shouldUpdate(fileTemplate)) {
            return;
        }

        for(final Api.Method method : sourceClass.getAllMethods()) {
            final Api.Type returnType = method.getReturnType();
            if(returnType == null) {
                continue;
            }
            final String canonicalText = returnType.getCanonicalText();
            if(!SpecialBoxedTypes.contains(canonicalText)) {
                continue;
            }
            final String initExpression = returnType.getInitExpression();
            final Api.SimpleExitInfo simpleExitInfo = method.getSimpleExitInfo();
            final String simpleExitReturnExpression = simpleExitInfo.getReturnExpression();
            // Check the return type's initExpression to see if it is actually a literal (not BigDecimal.Zero).
            switch(canonicalText) {
                case "java.lang.Byte" -> {
                    if(matchesByteString(initExpression)) {
                        final String newInitExpression = String.format("java.lang.Byte.valueOf(%s)", initExpression);
                        returnType.setInitExpression(newInitExpression);
                        returnType.setDefaultInitExpression(newInitExpression);
                    }
                    if(matchesByteString(simpleExitReturnExpression)) {
                        final String newInitExpression = String.format("java.lang.Byte.valueOf(%s)", simpleExitReturnExpression);
                        ((MethodImpl) method).setSimpleExitInfo(new SimpleExitInfoImpl(newInitExpression, simpleExitInfo.getThrownException()));
                    }
                    continue;
                }
                case "java.lang.Character" -> {
                    if(matchesCharString(initExpression)) {
                        final String newInitExpression = String.format("java.lang.Character.valueOf(%s)", initExpression);
                        returnType.setInitExpression(newInitExpression);
                        returnType.setDefaultInitExpression(newInitExpression);
                    }
                    if(matchesCharString(simpleExitReturnExpression)) {
                        final String newInitExpression = String.format("java.lang.Character.valueOf(%s)", simpleExitReturnExpression);
                        ((MethodImpl) method).setSimpleExitInfo(new SimpleExitInfoImpl(newInitExpression, simpleExitInfo.getThrownException()));
                    }
                    continue;
                }
                case "java.lang.Integer" -> {
                    if(matchesIntegerString(initExpression)) {
                        final String newInitExpression = String.format("java.lang.Integer.valueOf(%s)", initExpression);
                        returnType.setInitExpression(newInitExpression);
                        returnType.setDefaultInitExpression(newInitExpression);
                    }
                    if(matchesIntegerString(simpleExitReturnExpression)) {
                        final String newInitExpression = String.format("java.lang.Integer.valueOf(%s)", simpleExitReturnExpression);
                        ((MethodImpl) method).setSimpleExitInfo(new SimpleExitInfoImpl(newInitExpression, simpleExitInfo.getThrownException()));
                    }

                    continue;
                }
                case "java.lang.Long" -> {
                    if(matchesLongString(initExpression)) {
                        final String newInitExpression = String.format("java.lang.Long.valueOf(%s)", initExpression);
                        returnType.setInitExpression(newInitExpression);
                        returnType.setDefaultInitExpression(newInitExpression);
                    }
                    if(matchesLongString(simpleExitReturnExpression)) {
                        final String newInitExpression = String.format("java.lang.Long.valueOf(%s)", simpleExitReturnExpression);
                        ((MethodImpl) method).setSimpleExitInfo(new SimpleExitInfoImpl(newInitExpression, simpleExitInfo.getThrownException()));
                    }
                    continue;
                }
                case "java.lang.Short" -> {
                    if(matchesShortString(initExpression)) {
                        final String newInitExpression = String.format("java.lang.Short.valueOf(%s)", initExpression);
                        returnType.setInitExpression(newInitExpression);
                        returnType.setDefaultInitExpression(newInitExpression);
                    }
                    if(matchesShortString(simpleExitReturnExpression)) {
                        final String newInitExpression = String.format("java.lang.Short.valueOf(%s)", simpleExitReturnExpression);
                        ((MethodImpl) method).setSimpleExitInfo(new SimpleExitInfoImpl(newInitExpression, simpleExitInfo.getThrownException()));
                    }
                }
            }
        }
    }

    private static boolean matchesShortString(final String initExpression) {
        return initExpression != null && ShortRegex.matcher(initExpression).matches();
    }

    private static boolean matchesLongString(final String initExpression) {
        return initExpression != null && LongRegex.matcher(initExpression).matches();
    }

    private static boolean matchesIntegerString(final String initExpression) {
        return initExpression != null && IntRegex.matcher(initExpression).matches();
    }

    private boolean matchesCharString(final String initExpression) {
        return RegexUtils.matchesAny(CharRegexes, initExpression);
    }

    private boolean matchesByteString(final String initExpression) {
        return RegexUtils.matchesAny(ByteRegexes, initExpression);
    }

    private boolean shouldUpdate(final FileTemplate fileTemplate) {
        if(fileTemplate.getExtension().endsWith("groovy")) {
            return false;
        }
        final FrameworkInfo frameworkInfo = frameworkInferencer.determineFrameworkInfoFromTemplate(fileTemplate);
        if(frameworkInfo.hasAssertJ() || frameworkInfo.hasGoogleTruth()) {
            return false;
        }
        if(frameworkInfo.hasJUnit5()) {
            // If we have an old version of junit 5, perform the update.
            // If we have no version or a newer version, don't.
            final PsiClass assertClass = testDependencyInfoProvider.getPsiClass("org.junit.jupiter.api.Assertions");
            if(assertClass == null) {
                return false;
            }
            final Optional<PsiMethod> overload = Arrays.stream(assertClass.findMethodsByName("assertEquals", false)).filter(x -> {
                final PsiParameter[] params = x.getParameterList().getParameters();
                if(params.length == 0) {
                    return false;
                }
                if(params[0].getType().getCanonicalText().equals("java.lang.Integer")) {
                    return true;
                }
                return false;
            }).findAny();
            return overload.isEmpty();
        }
        if(frameworkInfo.hasTestNG()) {
            final PsiClass assertClass = testDependencyInfoProvider.getPsiClass("org.testng.Assert");
            if(assertClass == null) {
                return false;
            }
            final Optional<PsiMethod> overload = Arrays.stream(assertClass.findMethodsByName("assertEquals", false)).filter(x -> {
                final PsiParameter[] params = x.getParameterList().getParameters();
                if(params.length == 0) {
                    return false;
                }
                if(params[0].getType().getCanonicalText().equals("java.lang.Integer")) {
                    return true;
                }
                return false;
            }).findAny();
            return overload.isEmpty();
        }
        if(frameworkInfo.hasJUnit4()) {
            return true;
        }
        return false;
    }
}
