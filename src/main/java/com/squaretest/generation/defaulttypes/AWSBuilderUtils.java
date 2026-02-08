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

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;
import com.intellij.psi.util.PsiUtil;
import com.squaretest.generation.CompiledUtils;
import com.squaretest.generation.dataflow.DataflowUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AWSBuilderUtils {
    public static final String[] MethodNamesToExclude = new String[]{
            "overrideConfiguration", "addNestedAttributesToProject", "addNestedAttributeToProject", "addAttributeToProject", "close", "equals", "hashCode", "toString", "finalize", "clone", "compareTo", "marshall"
    };

    public static boolean isAWSBuilderMethod(final PsiMethod psiMethod) {
        final PsiClass containingClass = psiMethod.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName == null) {
            return false;
        }
        return StringUtils.startsWith(qualifiedName, "software.amazon.awssdk")
                && StringUtils.equals(containingClass.getName(), "Builder")
                && containingClass.getContainingClass() != null;
    }

    public static List<PsiMethod> determineBuilderMethodsToUse(final PsiClass builderClass) {
        final List<PsiMethod> ret = new ArrayList<>();
        final List<PsiMethod> methods = Arrays.stream(builderClass.getMethods()).filter(x -> x.hasModifierProperty(PsiModifier.PUBLIC)
                && !x.hasModifierProperty(PsiModifier.STATIC)).toList();
        for(final PsiMethod method : methods) {
            final String methodName = method.getName();
            if(method.isDeprecated()
                    || StringUtils.equalsAny(methodName, MethodNamesToExclude)
                    || hasEnumVariant(builderClass, method)
                    || hasPreferredStringVariant(builderClass, method)
                    || hasConsumerParam(method)
                    || hasAltNameVariant(builderClass, method)) {
                continue;
            }
            // Ensure the method returns the Builder.
            final PsiClass returnTypeClass = CompiledUtils.getClassWithSourceCode(PsiUtil.resolveClassInType(method.getReturnType()));
            if(returnTypeClass == null) {
                continue;
            }
            if(!StringUtils.equals(builderClass.getQualifiedName(), returnTypeClass.getQualifiedName())) {
                continue;
            }

            // Store the params and name for convenience.
            final PsiParameter[] params = method.getParameterList().getParameters();

            // If this has multiple params, continue.
            if(params.length != 1) {
                continue;
            }

            // Resolve the type of the first param.
            PsiType firstParamType = params[0].getType();
            if(isCollection(firstParamType) || isConsumer(firstParamType)) {
                continue;
            }

            ret.add(method);
        }

        return ret;
    }

    public static boolean hasConsumerParam(final PsiMethod method) {
        for(final PsiParameter param : method.getParameterList().getParameters()) {
            final PsiClass paramClass = PsiUtil.resolveClassInType(param.getType());
            if(paramClass == null) {
                return false;
            }
            if(StringUtils.equals(paramClass.getQualifiedName(), JavaNames.JavaLangConsumer)) {
                return true;
            }
            return false;
        }
        return false;
    }

    private static boolean hasPreferredStringVariant(final PsiClass builderClass, final PsiMethod method) {
        final PsiParameter onlyParam = DataflowUtils.getOnlyParam(method);
        if(onlyParam == null) {
            return false;
        }
        final PsiClass paramClass = PsiUtil.resolveClassInType(onlyParam.getType());
        if(paramClass == null) {
            return false;
        }
        if(StringUtils.equals(paramClass.getQualifiedName(), JavaNames.JavaLangString) || paramClass.isEnum()) {
            return false;
        }
        final PsiMethod[] altMethods = builderClass.findMethodsByName(method.getName(), true);
        for(final PsiMethod altMethod : altMethods) {
            final PsiParameter altParam = DataflowUtils.getOnlyParam(altMethod);
            if(altParam == null) {
                continue;
            }
            final PsiClass altParamClass = PsiUtil.resolveClassInType(altParam.getType());
            if(altParamClass == null) {
                continue;
            }
            if(StringUtils.equals(altParamClass.getQualifiedName(), JavaNames.JavaLangString)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasAltNameVariant(final PsiClass builderClass, final PsiMethod method) {
        final String methodName = method.getName();
        if(methodName.startsWith("add") && methodName.length() >= 4 && Character.isUpperCase(methodName.charAt(3))) {
            // Method name: software.amazon.awssdk.enhanced.dynamodb.NestedAttributeName.Builder#addElements(java.lang.String...)
            // Method name 2: software.amazon.awssdk.enhanced.dynamodb.NestedAttributeName.Builder#addElement(java.lang.String)
            // Preferred method name: software.amazon.awssdk.enhanced.dynamodb.NestedAttributeName.Builder#elements(java.lang.String...)
            final List<String> altNames = new ArrayList<>(2);
            final String altName1 = StringUtils.uncapitalize(StringUtils.removeStart(methodName, "add"));
            altNames.add(altName1);
            if(!altName1.endsWith("s")) {
                altNames.add(altName1 + "s");
                altNames.add(altName1 + "es");
            }
            for(final String altName : altNames) {
                if(builderClass.findMethodsByName(altName, false).length != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isConsumer(final PsiType firstParamType) {
        final PsiClass paramClass = PsiUtil.resolveClassInType(firstParamType);
        if(paramClass != null) {
            return StringUtils.equals(paramClass.getQualifiedName(), "java.util.function.Consumer");
        }
        return false;
    }

    private static boolean isCollection(final PsiType firstParamType) {
        final PsiClass paramClass = PsiUtil.resolveClassInType(firstParamType);
        if(paramClass != null) {
            return StringUtils.equals(paramClass.getQualifiedName(), "java.util.Collection");
        }
        return false;
    }

    private static boolean hasEnumVariant(final PsiClass builderClass, final PsiMethod method) {
        final String methodName = method.getName();
        final PsiParameter[] params = method.getParameterList().getParameters();
        if(params.length == 1) {
            final PsiType firstParamType = params[0].getType();

            // If the parameter is a String, check to see if we have an overload with the same name that takes in an Enum.
            if(firstParamType.getCanonicalText().equals("java.lang.String")) {
                final PsiMethod[] enumOverrides = builderClass.findMethodsByName(methodName, false);
                if(enumOverrides.length >= 2) {
                    for(final PsiMethod enumOverride : enumOverrides) {
                        final PsiParameter[] enumOverrideParams = enumOverride.getParameterList().getParameters();
                        if(enumOverrideParams.length == 1) {
                            final PsiType enumParamType = enumOverrideParams[0].getType();
                            final PsiClass enumParamClass = PsiUtil.resolveClassInType(enumParamType);
                            if(enumParamClass != null && enumParamClass.isEnum()) {
                                return true;
                            }
                        }
                    }
                }
            }

            // If the method name ends in WithStrings, check to see if we have an overload without "WithStrings" that
            // takes in a varargs of Enum type. Example: software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest.Builder.attributeNamesWithStrings(String... names) and
            // software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest.Builder.attributeNames(software.amazon.awssdk.services.sqs.model.QueueAttributeName... names).
            // Also: software.amazon.awssdk.services.sqs.model.Message.Builder#attributesWithStrings(Map<String, String>) and
            // software.amazon.awssdk.services.sqs.model.Message.Builder#attributes(Map<MessageSystemAttributeName, String>).
            if(methodName.endsWith("WithStrings")) {
                return true;
            }
        }
        return false;
    }
}
