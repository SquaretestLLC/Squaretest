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

import com.intellij.psi.CommonClassNames;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;
import com.intellij.psi.util.InheritanceUtil;
import com.intellij.psi.util.PsiUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProtobufUtils {

    private static final String[] MethodNamesToExclude = new String[]{
            "setUnknownFields"
    };

    public static PsiMethod findNewBuilderMethod(final PsiClass psiClass) {
        final PsiMethod[] newBuilderMethods = psiClass.findMethodsByName("newBuilder", false);
        for(final PsiMethod method : newBuilderMethods) {
            if(method.getParameterList().getParameters().length == 0
                    && method.hasModifierProperty(PsiModifier.STATIC)
                    && method.hasModifierProperty(PsiModifier.PUBLIC)) {
                return method;
            }
        }
        return null;
    }

    public static List<PsiMethod> determineBuilderMethodsToUse(final PsiClass builderClass) {
        final List<PsiMethod> ret = new ArrayList<>();
        final List<PsiMethod> methods = Arrays.stream(builderClass.getMethods()).filter(x -> x.hasModifierProperty(PsiModifier.PUBLIC)
                && !x.hasModifierProperty(PsiModifier.STATIC)).toList();
        final Set<String> namesAlreadyAdded = new HashSet<>();
        for(final PsiMethod method : methods) {
            String methodName = method.getName();
            if(method.isDeprecated() || namesAlreadyAdded.contains(methodName) || StringUtils.equalsAny(methodName, MethodNamesToExclude) || hasEnumVariant(builderClass, method) || hasNonBytesVariant(builderClass, method)) {
                continue;
            }
            // Ensure the method returns the Builder.
            final PsiClass returnTypeClass = PsiUtil.resolveClassInType(method.getReturnType());
            if(returnTypeClass == null) {
                continue;
            }
            if(!StringUtils.equals(builderClass.getQualifiedName(), returnTypeClass.getQualifiedName())) {
                continue;
            }

            // Store the params and name for convenience.
            final PsiParameter[] params = method.getParameterList().getParameters();

            // Check to see if this is a put() method that adds fields to a Map.
            if(methodName.startsWith("put") && methodName.length() >= 4 && Character.isUpperCase(
                    methodName.charAt(3)) && params.length == 2) {
                ret.add(method);
                namesAlreadyAdded.add(methodName);
                continue;
            }

            // If this has multiple params, continue.
            if(params.length != 1) {
                continue;
            }

            // Resolve the type of the first param.
            PsiType firstParamType = params[0].getType();
            if(StringUtils.startsWithAny(methodName, "set", "add")
                    && methodName.length() >= 4 && Character.isUpperCase(methodName.charAt(3))
                    && !hasBuilderName(firstParamType)
                    && !(methodName.endsWith("Builder") && firstParamType.getCanonicalText().equals("int"))
                    && !isObjectIterable(firstParamType)) {
                namesAlreadyAdded.add(methodName);
                ret.add(method);
            }
        }

        return ret;
    }

    private static boolean hasNonBytesVariant(final PsiClass builderClass, final PsiMethod method) {
        final String methodName = method.getName();
        final PsiParameter[] params = method.getParameterList().getParameters();
        if(params.length == 1) {
            final PsiType firstParamType = params[0].getType();
            final PsiClass firstParamClass = PsiUtil.resolveClassInType(firstParamType);
            if(firstParamClass != null && StringUtils.equals(firstParamClass.getQualifiedName(), "com.google.protobuf.ByteString")
                    && methodName.endsWith("Bytes") && methodName.length() > 5) {
                final PsiMethod[] altMethods = builderClass.findMethodsByName(StringUtils.removeEnd(methodName, "Bytes"), false);
                if(altMethods.length == 1) {
                    final PsiMethod altMethod = altMethods[0];
                    final PsiParameter[] enumOverrideParams = altMethod.getParameterList().getParameters();
                    if(enumOverrideParams.length == 1) {
                        final PsiType altMethodParam = enumOverrideParams[0].getType();
                        final PsiClass altMethodParamClass = PsiUtil.resolveClassInType(altMethodParam);
                        if(altMethodParamClass != null && StringUtils.equals(altMethodParamClass.getQualifiedName(), "java.lang.String")) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean isObjectIterable(final PsiType firstParamType) {
        final PsiClass paramClass = PsiUtil.resolveClassInType(firstParamType);
        if(paramClass != null) {
            if(StringUtils.equals(paramClass.getQualifiedName(), "com.google.protobuf.ByteString")) {
                return false;
            }
        }
        return InheritanceUtil.isInheritor(firstParamType, CommonClassNames.JAVA_LANG_ITERABLE);
    }

    private static boolean hasEnumVariant(final PsiClass builderClass, final PsiMethod method) {
        final String methodName = method.getName();
        final PsiParameter[] params = method.getParameterList().getParameters();
        if(params.length == 1) {
            final PsiType firstParamType = params[0].getType();
            if(firstParamType.getCanonicalText().equals("int")
                    && methodName.endsWith("Value") && methodName.length() > 5) {
                final PsiMethod[] enumOverrides = builderClass.findMethodsByName(StringUtils.removeEnd(methodName, "Value"), false);
                if(enumOverrides.length == 1) {
                    final PsiMethod enumOverrideMethod = enumOverrides[0];
                    final PsiParameter[] enumOverrideParams = enumOverrideMethod.getParameterList().getParameters();
                    if(enumOverrideParams.length == 1) {
                        final PsiType enumParam = enumOverrideParams[0].getType();
                        final PsiClass enumParamClass = PsiUtil.resolveClassInType(enumParam);
                        if(enumParamClass != null && enumParamClass.isEnum()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean hasBuilderName(@NotNull final PsiType param) {
        // Ensure the class is resolvable and has a canonical name.
        final PsiClass psiClass = PsiUtil.resolveClassInType(param);
        if(psiClass == null) {
            return false;
        }
        final String canonicalName = psiClass.getQualifiedName();
        if(canonicalName == null) {
            return false;
        }

        String className = psiClass.getName();
        return StringUtils.equals(className, "Builder") || StringUtils.endsWith(className, ".Builder");
    }

    public static boolean isProtobufBuilderMethod(final PsiMethod calledMethod) {
        final PsiClass containingClass = calledMethod.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName == null) {
            return false;
        }
        return StringUtils.equals(containingClass.getName(), "Builder")
                && InheritanceUtil.isInheritor(containingClass, "com.google.protobuf.GeneratedMessageV3.Builder");
    }
}
