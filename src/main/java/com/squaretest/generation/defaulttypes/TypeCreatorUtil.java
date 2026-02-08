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

import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassReferenceType;
import com.intellij.psi.util.InheritanceUtil;
import com.intellij.psi.util.PsiUtil;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.SQStringUtils;
import com.squaretest.template.impl.TypeImpl;
import com.squaretest.utils.SQMutableInt;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class TypeCreatorUtil {

    @Nullable
    static String tryCreateSimpleBuilderCall(final PsiType psiType) {
        final PsiClass psiClass = PsiUtil.resolveClassInType(psiType);
        if(psiClass == null || psiClass.getQualifiedName() == null) {
            return null;
        }
        if(TypeCreatorUtil.isNonStaticInnerClass(psiClass)) {
            return null;
        }
        return tryCreateSimpleBuilderCall(psiClass);
    }

    @Nullable
    private static String tryCreateSimpleBuilderCall(final PsiClass psiClass) {
        if(psiClass.isInterface() || psiClass.isEnum()) {
            return null;
        }

        final PsiMethod[] builderMethods = psiClass.findMethodsByName("builder", false);
        if(builderMethods.length != 1) {
            return null;
        }

        // Find the builder() method.
        final PsiMethod builderMethod = builderMethods[0];
        if(!isBuilder(builderMethod)) {
            return null;
        }

        // Find the Builder class.
        final PsiType builderReturnType = builderMethod.getReturnType();
        final PsiClass builderMethodClass = PsiUtil.resolveClassInType(builderReturnType);
        if(builderMethodClass == null) {
            return null;
        }

        // Find the build method; i.e. Builder.build().
        final PsiMethod[] buildMethods = builderMethodClass.findMethodsByName("build", true);
        if(buildMethods.length == 0 || !hasAtLeastOneCorrectBuildMethod(buildMethods)) {
            return null;
        }

        // We can't validate the return type, because the build() method may be defined in a base class.
        // However, we can assume this is the builder pattern. If the developer has a class Foo where
        // Foo.builder().build() does not return an instance of Foo, this won't work; however, that case should be uncommon.
        return psiClass.getQualifiedName() + ".builder().build()";
    }

    private static boolean hasAtLeastOneCorrectBuildMethod(final PsiMethod[] buildMethods) {
        return Arrays.stream(buildMethods)
                .anyMatch(x -> x.hasModifierProperty(PsiModifier.PUBLIC) && x.getParameterList().getParameters().length == 0);
    }

    private static boolean isBuilder(final PsiMethod builderMethod) {
        return builderMethod.getParameterList().getParameters().length == 0
                && builderMethod.hasModifierProperty(PsiModifier.STATIC)
                && builderMethod.hasModifierProperty(PsiModifier.PUBLIC);
    }

    public static boolean overridesEquals(@Nullable final PsiClass psiClass) {
        if(psiClass == null) {
            return false;
        }

        if(psiClass.isEnum() || psiClass.isRecord()) {
            return true;
        }

        final List<PsiMethod> equalsMethods = findEqualsMethods(psiClass);
        if(equalsMethods.isEmpty()) {
            return false;
        }

        if(equalsMethods.size() > 1) {
            // If we have more than 1, someone in the type hierarchy overrode Object.equals(Object).
            return true;
        }

        final PsiMethod equalsMethod = equalsMethods.get(0);
        final PsiClass equalsContainingClass = equalsMethod.getContainingClass();
        if(equalsContainingClass == null) {
            return false;
        }

        final String containingClassQualifiedName = equalsContainingClass.getQualifiedName();
        if(containingClassQualifiedName == null) {
            return false;
        }

        return !JavaNames.JavaLangObject.equals(containingClassQualifiedName);
    }

    /**
     * Finds the public boolean equals(Object o) method.
     *
     * @param clazz the class.
     * @return the method if it exists or null if it does not.
     */
    @NotNull
    private static List<PsiMethod> findEqualsMethods(@NotNull final PsiClass clazz) {
        final List<PsiMethod> foundEqualsMethods = new ArrayList<>();
        PsiMethod[] methods = clazz.findMethodsByName("equals", true);

        // is it public boolean equals(Object o)
        for(PsiMethod method : methods) {
            // must be public
            if(!method.hasModifierProperty(PsiModifier.PUBLIC)) {
                continue;
            }

            // must not be static
            if(method.hasModifierProperty(PsiModifier.STATIC)) {
                continue;
            }

            // must have boolean as return type
            PsiType returnType = method.getReturnType();
            if(!PsiTypes.booleanType().equals(returnType)) {
                continue;
            }

            // must have one parameter
            PsiParameter[] parameters = method.getParameterList().getParameters();
            if(parameters.length != 1) {
                continue;
            }

            // parameter must be Object
            if(!(parameters[0].getType().getCanonicalText().equals(JavaNames.JavaLangObject))) {
                continue;
            }

            // equals method found
            foundEqualsMethods.add(method);
        }

        // equals not found
        return foundEqualsMethods;
    }

    @NotNull
    static String createDefaultConstructorCall(final ConstructorInfo constructorInfo) {
        final String genericStr = constructorInfo.classToConstruct().hasTypeParameters() ? "<>" : "";
        return "new " + constructorInfo.className() + genericStr + "()";
    }

    @Nullable
    static String createDefaultConstructorCall(final PsiClass psiClass) {
        String name = psiClass.getQualifiedName();
        if(name == null) {
            name = psiClass.getName();
        }
        if(name == null) {
            return null;
        }
        final String genericStr = psiClass.hasTypeParameters() ? "<>" : "";
        return "new " + name + genericStr + "()";
    }

    public static String determineName(final PsiType psiType) {
        final String typePresentableText = psiType.getPresentableText();
        if(typePresentableText.contains("<") && typePresentableText.contains(">")) {
            int firstIndex = typePresentableText.indexOf('<');
            int lastIndex = typePresentableText.lastIndexOf('>');
            if(lastIndex > firstIndex) {
                return typePresentableText.substring(0, firstIndex) + typePresentableText.substring(lastIndex + 1);
            }
        }
        return typePresentableText;
    }

    public static int determineNumberOfPlaceholdersInInitExpression(@Nullable final String initExpressionWithTypePlaceholder) {
        if(initExpressionWithTypePlaceholder == null) {
            return 0;
        }
        if(initExpressionWithTypePlaceholder.contains("{{VALUE3}}") || initExpressionWithTypePlaceholder.contains("{{TYPE3}}") || initExpressionWithTypePlaceholder.contains("{{TYPETEXT3}}")) {
            return 3;
        }
        if(initExpressionWithTypePlaceholder.contains("{{VALUE2}}") || initExpressionWithTypePlaceholder.contains("{{TYPE2}}") || initExpressionWithTypePlaceholder.contains("{{TYPETEXT2}}")) {
            return 2;
        }
        if(initExpressionWithTypePlaceholder.contains("{{VALUE1}}") || initExpressionWithTypePlaceholder.contains("{{TYPE1}}") || initExpressionWithTypePlaceholder.contains("{{TYPETEXT1}}")) {
            return 1;
        }
        return 0;
    }

    static boolean hasTypeParameter(
            final PsiMethod constructor,
            final PsiTypeParameter typeParameter,
            final boolean includeVargs) {
        for(final PsiParameter param : constructor.getParameterList().getParameters()) {
            if(isTypeParam(typeParameter, param, includeVargs)) {
                return true;
            }
        }
        return false;
    }

    static boolean isTypeParam(final PsiTypeParameter typeParameterToSearchFor, final PsiParameter methodParam, final boolean includeVargs) {
        final PsiType paramType = methodParam.getType();
        PsiClassReferenceType paramTypeReference = getTypeReferenceIfExists(paramType, includeVargs);
        if(paramTypeReference == null) {
            return false;
        }
        // The parameter's generic type must be the same as the class's type parameter.
        return paramTypeReference.resolve() == typeParameterToSearchFor;
    }

    public static boolean hasPublicStaticCreatorMethodThatUsesSingleTypeParam(final PsiClass psiClass) {
        return findPublicStaticCreatorMethodThatUsesSingleTypeParam(psiClass) != null;
    }

    @Nullable
    public static PsiMethod findPublicStaticCreatorMethodThatUsesSingleTypeParam(final PsiClass psiClass) {
        final PsiMethod[] methods = psiClass.getMethods();
        final boolean isInterface = psiClass.isInterface();
        return Arrays.stream(methods).filter(x -> (x.hasModifierProperty(PsiModifier.PUBLIC) || (isInterface && x.hasModifierProperty(PsiModifier.PACKAGE_LOCAL))) && x.hasModifierProperty(PsiModifier.STATIC)
                && !x.isDeprecated() && TypeCreatorUtil.hasSameType(psiClass, x.getReturnType()) && !hasParamOfType(x, psiClass)
                && staticMethodHasSingleArgThatIsTheTypeParameter(x, true)).findAny().orElse(null);
    }

    private static boolean hasParamOfType(final PsiMethod method, final PsiClass psiClass) {
        for(final PsiParameter param : method.getParameterList().getParameters()) {
            final PsiClass paramClass = PsiUtil.resolveClassInType(param.getType());
            if(paramClass == null) {
                continue;
            }
            if(Objects.equals(paramClass.getQualifiedName(), psiClass.getQualifiedName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean allParamsSimpleTypeParamArgs(final PsiClass psiClass, final PsiMethod constructor) {
        final List<PsiTypeParameter> classTypeParams = Arrays.asList(psiClass.getTypeParameters());
        return allArgsSimpleTypeParams(constructor, classTypeParams);
    }

    static boolean allParamsSimpleTypeParamArgs(final PsiMethod method) {
        final PsiTypeParameterList methodTypeParamsList = method.getTypeParameterList();
        if(methodTypeParamsList == null) {
            return false;
        }
        final List<PsiTypeParameter> methodTypeParams = Arrays.asList(methodTypeParamsList.getTypeParameters());
        return allArgsSimpleTypeParams(method, methodTypeParams);
    }

    private static boolean allArgsSimpleTypeParams(final PsiMethod method, final List<PsiTypeParameter> methodTypeParams) {
        final PsiParameter[] methodParams = method.getParameterList().getParameters();
        for(final PsiParameter param : methodParams) {
            final PsiType paramType = param.getType();
            PsiClassReferenceType paramTypeReference = getTypeReferenceIfExists(paramType, true);
            if(paramTypeReference == null) {
                return false;
            }
            final PsiClass resolvedTypeParam = paramTypeReference.resolve();
            if(resolvedTypeParam == null) {
                return false;
            }
            if(!containsUsingIdentityEquals(methodTypeParams, resolvedTypeParam)) {
                return false;
            }
        }
        return true;
    }

    private static boolean containsUsingIdentityEquals(final List<PsiTypeParameter> methodTypeParams, final PsiClass resolvedTypeParam) {
        for(final PsiTypeParameter typeParameter : methodTypeParams) {
            if(typeParameter == resolvedTypeParam) {
                return true;
            }
        }
        return false;
    }

    static boolean staticMethodHasSingleArgThatIsTheTypeParameter(final PsiMethod method, final boolean checkVargs) {
        // Determine the method type parameter.
        final PsiTypeParameterList typeParameterList = method.getTypeParameterList();
        if(typeParameterList == null || typeParameterList.getTypeParameters().length != 1) {
            return false;
        }
        final PsiTypeParameter methodTypeParam = method.getTypeParameterList().getTypeParameters()[0];

        // Determine the single parameter's type.
        if(method.getParameterList().getParameters().length != 1) {
            return false;
        }
        final PsiType paramType = method.getParameterList().getParameters()[0].getType();
        PsiClassReferenceType paramTypeReference = getTypeReferenceIfExists(paramType, checkVargs);
        if(paramTypeReference == null) {
            return false;
        }

        // The parameter's generic type must be the same as the method type parameter.
        if(paramTypeReference.resolve() != methodTypeParam) {
            return false;
        }

        // Determine the return type.
        final PsiType returnType = method.getReturnType();
        if(!(returnType instanceof final PsiClassReferenceType returnTypeReference)) {
            return false;
        }
        if(returnTypeReference.getParameters().length != 1) {
            return false;
        }
        final PsiType returnTypeInnerTypeReference = returnTypeReference.getParameters()[0];
        if(!(returnTypeInnerTypeReference instanceof PsiClassReferenceType)) {
            return false;
        }
        final PsiClass returnTypeInnerTypeReferent = ((PsiClassReferenceType) returnTypeInnerTypeReference).resolve();

        // Ensure the type parameter in the return type refers to the method's type parameter.
        return returnTypeInnerTypeReferent == methodTypeParam;
    }

    @Nullable
    public static PsiClassReferenceType getTypeReferenceIfExists(final PsiType type, final boolean checkVargs) {
        if(type instanceof PsiEllipsisType) {
            if(!checkVargs) {
                return null;
            }
            final PsiType componentType = ((PsiEllipsisType) type).getComponentType();
            if(componentType instanceof PsiClassReferenceType) {
                return (PsiClassReferenceType) componentType;
            }
            return null;
        }
        if(type instanceof PsiClassReferenceType) {
            return (PsiClassReferenceType) type;
        }
        return null;
    }

    static boolean hasSameType(final PsiClass psiClass, final PsiType returnType) {
        if(returnType == null || PsiTypes.voidType().equals(returnType)) {
            return false;
        }
        if(returnType instanceof PsiArrayType) {
            return false;
        }
        final PsiClass returnTypeClass = PsiUtil.resolveClassInType(returnType);
        if(returnTypeClass == null) {
            return false;
        }
        return Objects.equals(returnTypeClass.getQualifiedName(), psiClass.getQualifiedName());
    }

    @Nullable
    public static String getCanonicalKey(final PsiMethod method) {
        final PsiClass containingClass = method.getContainingClass();
        if(containingClass == null || containingClass.getQualifiedName() == null) {
            return null;
        }
        return containingClass.getQualifiedName() + "_" + getMethodKey(method);
    }

    public static String getMethodKey(final PsiMethod method) {
        final StringBuilder builder = new StringBuilder(method.getName());
        final PsiParameter[] parameters = method.getParameterList().getParameters();
        if(parameters.length > 0) {
            builder.append("_");
        }
        for(int i = 0; i < parameters.length; i++) {
            final PsiParameter param = parameters[i];
            builder.append(getTypeSimpleName(param.getType()));
            if(i != parameters.length - 1) {
                builder.append("_");
            }
        }
        return builder.toString();
    }

    private static String getTypeSimpleName(PsiType type) {
        if(type instanceof PsiPrimitiveType || (type instanceof PsiArrayType && !(type instanceof PsiEllipsisType))) {
            // For primitives and arrays, use determineName().
            return determineName(type);
        }

        // If the type is Ellipsis (e.g. "String..."), use the component type.
        if(type instanceof PsiEllipsisType) {
            type = ((PsiEllipsisType) type).getComponentType();
        }

        // Resolve the class.
        final PsiClass psiClass = PsiUtil.resolveClassInType(type);
        if(psiClass instanceof PsiTypeParameter) {
            // The class is a generic; e.g. T, K.
            return determineName(type);
        }

        if(psiClass != null) {
            return psiClass.getName() != null ? psiClass.getName() : determineName(type);
        }

        return determineName(type);
    }

    static Set<PsiTypeParameter> getUsedTypeParams(final PsiMethod constructor) {
        final Set<PsiTypeParameter> ret = SetUtils.newIdentityHashSet();
        for(final PsiParameter param : constructor.getParameterList().getParameters()) {
            final PsiType paramType = param.getType();

            // Check to see if the param is a type reference.
            final PsiClassReferenceType paramTypeReference = getTypeReferenceIfExists(paramType, true);
            if(paramTypeReference == null) {
                continue;
            }
            final PsiClass resolvedTypeRef = paramTypeReference.resolve();
            if(resolvedTypeRef instanceof PsiTypeParameter) {
                ret.add((PsiTypeParameter) resolvedTypeRef);
                continue;
            }

            // Check to see if the paramType has an inner type that is a type reference.
            if(paramType instanceof PsiClassReferenceType) {
                final PsiType[] innerTypeParams = ((PsiClassReferenceType) paramType).getParameters();
                for(final PsiType innerTypeParam : innerTypeParams) {
                    if(!(innerTypeParam instanceof PsiClassReferenceType)) {
                        continue;
                    }
                    final PsiClass resolvedInnerTypeRef = ((PsiClassReferenceType) innerTypeParam).resolve();
                    if(resolvedInnerTypeRef instanceof PsiTypeParameter) {
                        ret.add((PsiTypeParameter) resolvedInnerTypeRef);
                    }
                }
            }
        }
        return ret;
    }

    static boolean usesAllTypeParams(final PsiClass psiClass, final PsiMethod constructor) {
        final Set<PsiTypeParameter> classTypeParams = SetUtils.newIdentityHashSet();
        classTypeParams.addAll(Arrays.asList(psiClass.getTypeParameters()));
        final Set<PsiTypeParameter> usedTypeParams = getUsedTypeParams(constructor);
        return usedTypeParams.containsAll(classTypeParams);
    }

    static boolean isNullArg(@NotNull final PsiType psiType) {
        return psiType instanceof PsiPrimitiveType && psiType.equalsToText("null");
    }

    static String removeExtraNewlines(final String canonicalName, final String builderCall) {
        final String[] awsV2namesToReplace = new String[]{
                canonicalName + ".builder()\n.build()",
                canonicalName + ".builder()\n\n.build()",
        };
        if(StringUtils.equalsAny(builderCall, awsV2namesToReplace)) {
            return canonicalName + ".builder().build()";
        }
        final String[] protobufNamesToReplace = new String[]{
                canonicalName + ".newBuilder()\n.build()",
                canonicalName + ".newBuilder()\n\n.build()"
        };
        if(StringUtils.equalsAny(builderCall, protobufNamesToReplace)) {
            return canonicalName + ".newBuilder().build()";
        }
        return builderCall;
    }

    @Nullable
    static PsiMethod findFailureInitMethod(@NotNull final PsiClass typeClass) {

        final boolean isInterface = typeClass.isInterface();

        // Find visible static creator methods.
        List<PsiMethod> candidateMethods = Arrays.stream(typeClass.getMethods())
                .filter(x -> (x.hasModifierProperty(PsiModifier.PUBLIC) || (isInterface && x.hasModifierProperty(PsiModifier.PACKAGE_LOCAL))) && x.hasModifierProperty(PsiModifier.STATIC)
                        && !x.isDeprecated() && hasSameType(typeClass, x.getReturnType()))
                .toList();
        if(candidateMethods.isEmpty()) {
            return null;
        }

        // Find static creator methods that have a Throwable parameter.
        List<PsiMethod> creatorMethodsWithThrowableParam = candidateMethods.stream().filter(TypeCreatorUtil::hasThrowableParam)
                .collect(Collectors.toList());
        if(!creatorMethodsWithThrowableParam.isEmpty()) {
            return chooseBestFailureCaseCreatorMethodWithThrowable(creatorMethodsWithThrowableParam);
        }

        // None of the static creator methods take in a Throwable.
        // Search for one named: failure(), exception() or error().
        List<PsiMethod> creatorMethodsWithFailureName = candidateMethods.stream().filter(
                        x -> StringUtils.equalsAnyIgnoreCase(x.getName(), "fail", "failure", "withFailure", "exception", "withException", "error", "withError"))
                .collect(Collectors.toList());
        if(!creatorMethodsWithFailureName.isEmpty()) {
            return chooseBestFailureCaseCreatorMethodWithoutThrowable(creatorMethodsWithFailureName);
        }

        // Search for a method whose name contains failure words.
        List<PsiMethod> creatorMethodsWithFailureSubstring = candidateMethods.stream().filter(
                        x -> SQStringUtils.containsAny(x.getName(), "fail", "Fail", "exception", "Exception", "error", "Error"))
                .collect(Collectors.toList());
        if(!creatorMethodsWithFailureSubstring.isEmpty()) {
            return chooseBestFailureCaseCreatorMethodWithoutThrowable(creatorMethodsWithFailureSubstring);
        }

        // We couldn't find a failure method.
        return null;
    }

    static void updateEmptyInitExpressionForKnownTypeIfNeeded(final TypeImpl typeImpl) {
        if(typeImpl.isEmptyIfTypeParamsAreEmpty() && typeImpl.getInitExpressionWithTypePlaceholder() != null
                && typeImpl.getNumberOfTypesInPlaceholderExpression() == typeImpl.getParameters().size()
                && typeImpl.getParameters().stream().anyMatch(x -> x.getEmptyInitExpression() != null)) {

            String newEmptyInitExpression = typeImpl.getInitExpressionWithTypePlaceholder();
            Api.FluentList<Api.Type> parameters = typeImpl.getParameters();
            for(int i = 0; i < parameters.size(); i++) {
                final Api.Type type = parameters.get(i);
                final String valueKey = String.format("{{VALUE%d}}", i + 1);
                // The type.getEmptyInitExpression() can't be null, because we check type.getEmptyInitExpression() != null
                // for all types in the if statement.
                if(type.getEmptyInitExpression() != null) {
                    newEmptyInitExpression = newEmptyInitExpression.replace(valueKey, type.getEmptyInitExpression());
                }
                final String typeKey = String.format("{{TYPE%d}}", i + 1);
                newEmptyInitExpression = newEmptyInitExpression.replace(typeKey, type.getCanonicalNameOrName());
                final String typeTextKey = String.format("{{TYPETEXT%d}}", i + 1);
                newEmptyInitExpression = newEmptyInitExpression.replace(typeTextKey, type.getCanonicalText());
            }

            typeImpl.setEmptyInitExpression(newEmptyInitExpression);
        }
    }

    private static PsiMethod chooseBestFailureCaseCreatorMethodWithoutThrowable(final List<PsiMethod> creatorMethodsWithFailureName) {
        // Choose no-args method if available.
        if(creatorMethodsWithFailureName.size() == 1) {
            return creatorMethodsWithFailureName.get(0);
        }
        // Use the method with the fewest args.
        return creatorMethodsWithFailureName.stream().min(
                Comparator.comparing(x -> x.getParameterList().getParameters().length)).get();
    }

    private static PsiMethod chooseBestFailureCaseCreatorMethodWithThrowable(final List<PsiMethod> creatorMethodsWithThrowableParam) {
        if(creatorMethodsWithThrowableParam.size() == 1) {
            return creatorMethodsWithThrowableParam.get(0);
        }

        // Use the method with the fewest args.
        return creatorMethodsWithThrowableParam.stream().min(
                Comparator.comparing(x -> x.getParameterList().getParameters().length)).get();
    }

    private static boolean hasThrowableParam(final PsiMethod psiMethod) {
        return Arrays.stream(psiMethod.getParameterList().getParameters()).anyMatch(x -> InheritanceUtil.isInheritor(x.getType(), CommonClassNames.JAVA_LANG_THROWABLE));
    }

    static boolean isOptional(final PsiType parameterType) {
        final PsiClass typeClass = PsiUtil.resolveClassInType(parameterType);
        if(typeClass == null) {
            return false;
        }
        final String canonicalName = typeClass.getQualifiedName();
        if(canonicalName == null) {
            return false;
        }
        if(TypeCreatorUtil.isNonStaticInnerClass(typeClass)) {
            return false;
        }

        // Ensure the type name is Optional.
        if(!StringUtils.equals(typeClass.getName(), "Optional")) {
            return false;
        }

        // Ensure it has exactly one type parameter.
        final PsiTypeParameterList typeParams = typeClass.getTypeParameterList();
        return typeParams != null && typeParams.getTypeParameters().length == 1;
    }

    static void useInitExpressionWithPlaceholderIfPossible(
            @NotNull final TypeImpl typeImpl, @NotNull final ResolveTypeInfo resolveTypeInfo,
            final SQMutableInt numberOfConstructorParams, final SQMutableInt numberOfBuilderMethodsCalled) {
        // If we have a type like Optional<Void> or Mono<Void>, set the normal expression to the absent value and the
        // absentInitExpression to null.
        if(typeImpl.isRecognized() && hasSingleVoidTypeParam(typeImpl.getParameters()) && typeImpl.getAbsentInitExpression() != null && !StringUtils.equals(typeImpl.getAbsentInitExpression(), "null")) {
            typeImpl.setInitExpression(typeImpl.getAbsentInitExpression());
            typeImpl.setAbsentInitExpression(null);
            return;
        }
        if(canUsePlaceholder(typeImpl)) {
            if(typeImpl.isArray() && typeImpl.getDeepArrayComponentType() != null) {
                if(!isByte(typeImpl.getDeepArrayComponentType())) {
                    final TypeImpl deepComponentType = (TypeImpl) typeImpl.getDeepArrayComponentType();
                    String updatedInitExpression = typeImpl.getInitExpression();
                    if(!deepComponentType.getInitExpression().equals("null")) {
                        updatedInitExpression = typeImpl.getInitExpressionWithTypePlaceholder().replace("{{VALUE1}}",
                                deepComponentType.getInitExpression());
                    }
                    typeImpl.setInitExpression(updatedInitExpression);
                    typeImpl.setDefaultInitExpression(updatedInitExpression);
                    typeImpl.addInitExpressionBeans(deepComponentType.getInitExpressionBeans());
                    numberOfConstructorParams.add(resolveTypeInfo.numberOfConstructorParams());
                    numberOfBuilderMethodsCalled.add(resolveTypeInfo.numberOfBuilderMethodsCalled());
                    typeImpl.setInitExpressionUsesAllTypeParams(true);
                }
            } else {
                // We have a recognized type with an init expression for its inner type; e.g. List<T> with Arrays.asList({{VALUE1}}).
                String newInitExpression = typeImpl.getInitExpressionWithTypePlaceholder();
                final List<Api.Type> initExpressionBeans = new ArrayList<>();
                final Api.FluentList<Api.Type> parameters = typeImpl.getParameters();
                for(int i = 0; i < parameters.size(); i++) {
                    final TypeImpl type = (TypeImpl) parameters.get(i);
                    final String valueKey = String.format("{{VALUE%d}}", i + 1);
                    final boolean hasValueKey = newInitExpression.contains(valueKey);
                    newInitExpression = newInitExpression.replace(valueKey, type.getInitExpression());
                    final String typeKey = String.format("{{TYPE%d}}", i + 1);
                    newInitExpression = newInitExpression.replace(typeKey, type.getCanonicalNameOrName());
                    final String typeTextKey = String.format("{{TYPETEXT%d}}", i + 1);
                    newInitExpression = newInitExpression.replace(typeTextKey, type.getCanonicalText());
                    if(hasValueKey) {
                        // Only add initExpressionBeans if we replaced the valueKey.
                        initExpressionBeans.addAll(type.getInitExpressionBeans());
                    }
                }
                typeImpl.setInitExpression(newInitExpression);
                typeImpl.setDefaultInitExpression(newInitExpression);
                typeImpl.addInitExpressionBeans(initExpressionBeans);
                numberOfConstructorParams.add(resolveTypeInfo.numberOfConstructorParams());
                numberOfBuilderMethodsCalled.add(resolveTypeInfo.numberOfBuilderMethodsCalled());
                typeImpl.setInitExpressionUsesAllTypeParams(true);
            }
        }
    }

    private static boolean canUsePlaceholder(final TypeImpl typeImpl) {
        if(!typeImpl.isRecognized()) {
            return false;
        }
        final String placeholderInitExpression = typeImpl.getInitExpressionWithTypePlaceholder();
        if(placeholderInitExpression == null) {
            return false;
        }
        if(typeImpl.getNumberOfTypesInPlaceholderExpression() != typeImpl.getParameters().size()) {
            return false;
        }
        if(placeholderInitExpression.contains("{{VALUE")) {
            // Confirm all type params are valid and return false if not.
            if(!allTypeParamsValidForTypePlaceholder(typeImpl)) {
                return false;
            }
        }
        if(placeholderInitExpression.contains("{{TYPE")) {
            if(typeImpl.getParameters().stream().anyMatch(Api.Type::isGeneric)) {
                return false;
            }
        }
        return true;
    }

    private static boolean allTypeParamsValidForTypePlaceholder(@NotNull final TypeImpl typeImpl) {
        final Api.FluentList<Api.Type> params = typeImpl.getParameters();
        if(hasSingleVoidTypeParam(params)) {
            // Handle cases like: Optional<Void>. We don't want to construct Optional.of(null), because that will
            // throw. The only possibly case is Optional.empty() (the default initExpression for Optional).
            return false;
        }
        return params.stream().allMatch(TypeCreatorUtil::isValidForTypePlaceholder);
    }

    private static boolean hasSingleVoidTypeParam(final Api.FluentList<Api.Type> params) {
        return params.size() == 1 && StringUtils.equals(params.get(0).getCanonicalName(), "java.lang.Void");
    }

    private static boolean isValidForTypePlaceholder(final Api.Type type) {
        final TypeImpl typeImpl = (TypeImpl) type;
        if(typeImpl.isRecognized()) {
            if(typeImpl.getShouldBeMocked()) {
                return false;
            }
            return true;
        }
        return !typeImpl.getInitExpression().equals("null");
    }

    static void useFailureInitExpressionWithPlaceholderIfPossible(@NotNull final TypeImpl typeImpl) {
        if(typeImpl.isRecognized() && typeImpl.getFailureInitExpressionWithTypePlaceholder() != null
                && typeImpl.getNumberOfTypesInPlaceholderExpression() == typeImpl.getParameters().size()
                && allTypeParamsValidForTypePlaceholder(typeImpl)) {
            String newInitExpression = typeImpl.getFailureInitExpressionWithTypePlaceholder();
            final Api.FluentList<Api.Type> parameters = typeImpl.getParameters();
            for(int i = 0; i < parameters.size(); i++) {
                final Api.Type type = parameters.get(i);
                final String valueKey = String.format("{{VALUE%d}}", i + 1);
                newInitExpression = newInitExpression.replace(valueKey, type.getInitExpression());
                final String typeKey = String.format("{{TYPE%d}}", i + 1);
                newInitExpression = newInitExpression.replace(typeKey, type.getCanonicalNameOrName());
                final String typeTextKey = String.format("{{TYPETEXT%d}}", i + 1);
                newInitExpression = newInitExpression.replace(typeTextKey, type.getCanonicalText());
            }
            typeImpl.setFailureInitExpression(newInitExpression);
        }
    }

    static boolean isAWSSdkPublisher(final PsiType psiType) {
        return isAWSAndInheritedFrom(psiType, "software.amazon.awssdk.core.async.SdkPublisher");
    }

    static boolean isAWSSdkIterable(final PsiType psiType) {
        return isAWSAndInheritedFrom(psiType, "software.amazon.awssdk.core.pagination.sync.SdkIterable");
    }

    static boolean isAWSV1Request(final PsiType psiType) {
        final PsiClass typeClass = PsiUtil.resolveClassInType(psiType);
        if(typeClass == null) {
            return false;
        }
        final String canonicalName = typeClass.getQualifiedName();
        if(canonicalName == null) {
            return false;
        }
        if(!canonicalName.startsWith("com.amazonaws")) {
            // Bailout if this is not AWS.
            return false;
        }
        return true;
    }

    private static boolean isAWSAndInheritedFrom(final PsiType psiType, final String baseClassCanonicalName) {
        final PsiClass typeClass = PsiUtil.resolveClassInType(psiType);
        if(typeClass == null) {
            return false;
        }
        final String canonicalName = typeClass.getQualifiedName();
        if(canonicalName == null) {
            return false;
        }
        if(!canonicalName.startsWith("software.amazon.awssdk")) {
            // Bailout if this is not AWS.
            return false;
        }

        // Find the SDK Class.
        final PsiClass sdkIterableClass = JavaPsiFacade.getInstance(typeClass.getProject()).findClass(baseClassCanonicalName, typeClass.getResolveScope());
        if(sdkIterableClass == null) {
            return false;
        }

        // Do not check the entire inheritance chain (checkDeep = true). I'm afraid this would be slow, because most types are not
        // SdkIterables.
        return typeClass.isInheritor(sdkIterableClass, true);
    }

    static boolean isGCSBackgroundResource(final PsiType psiType) {
        final PsiClass typeClass = PsiUtil.resolveClassInType(psiType);
        if(typeClass == null) {
            return false;
        }
        final String canonicalName = typeClass.getQualifiedName();
        if(canonicalName == null) {
            return false;
        }
        if(!canonicalName.startsWith("com.google")) {
            // Bailout if this is not Google.
            return false;
        }
        // Find the BackgroundResource Class.
        final PsiClass backgroundResourceClass = JavaPsiFacade.getInstance(typeClass.getProject()).findClass("com.google.api.gax.core.BackgroundResource", typeClass.getResolveScope());
        if(backgroundResourceClass == null) {
            return false;
        }
        return typeClass.isInheritor(backgroundResourceClass, true);
    }

    static boolean isMap(final PsiType paramType) {
        final PsiClass psiClass = PsiUtil.resolveClassInType(paramType);
        return psiClass != null && StringUtils.equals(psiClass.getQualifiedName(), CommonClassNames.JAVA_UTIL_MAP);
    }

    @Nullable
    static PsiMethod findMethodWithSingleTypeParam(final List<PsiMethod> candidateMethods, final boolean checkVargs) {
        if(candidateMethods.isEmpty()) {
            return null;
        }
        final PsiClass containingClass = candidateMethods.get(0).getContainingClass();
        if(containingClass == null) {
            return null;
        }

        for(final PsiMethod method : candidateMethods) {
            if(staticMethodHasSingleArgThatIsTheTypeParameter(method, checkVargs)) {
                return method;
            }
        }
        return null;
    }

    /**
     * Substitutors maintain a mapping of a PsiClass's type parameter (e.g. T) to the actual type used in a PsiType. For example,
     * if a PsiType is: List&lt;String&gt;, the PsiClass is List and the substitutor contains a mapping of T to String.
     * If you have an instance method in the class that returns type T, you can use the substutitor to replace T with String.
     * <p>
     * Often times classes that have type parameters will have static creator methods that have similar type parameters; e.g. the
     * ImmutableMap&lt;K, V&gt; class in Google Guava. The static method: ImmutableMap.of(K k, V v) can be used to construct an
     * ImmutableMap&lt;K, V&gt;. The type parameters in the static method are separate from the type parameters in the class type,
     * though; hence, they cannot be substituted by a substitutor.
     * <p>
     * The static method type parameters usually have the same names as the corresponding Class type parameters. We leverage this
     * fact to replace the generic types in the method with the types in the Class's substitutor.
     * <p>
     * This way, if we have an ImmutableMap&lt;String, Integer&gt;, and the substitutor has mapping: K -> String and V -> Integer,
     * we will correctly replace the type params in of(K k, V v) with String and Integer respectively.
     *
     * @param genericSubstitutor the substitutor for the PsiClass and PsiType we're trying to construct.
     * @param type               the type in the static creator method that may or may not be a generic type.
     * @return the substituted type.
     */
    static PsiType substituteByName(final PsiSubstitutor genericSubstitutor, final PsiType type) {
        String name;
        if((type instanceof PsiTypeParameter)) {
            name = ((PsiTypeParameter) type).getName();
        } else if(type instanceof PsiClassType) {
            name = ((PsiClassType) type).getName();
        } else {
            return type;
        }

        for(final Map.Entry<PsiTypeParameter, PsiType> entry : genericSubstitutor.getSubstitutionMap().entrySet()) {
            if(Objects.equals(entry.getKey().getName(), name)) {
                PsiType entryValue = entry.getValue();
                return entryValue != null ? entryValue : type;
            }
        }
        return type;
    }

    public static boolean isClosable(@Nullable final PsiClass psiClass) {
        if(psiClass == null) {
            return false;
        }
        return InheritanceUtil.isInheritor(psiClass, CommonClassNames.JAVA_LANG_AUTO_CLOSEABLE);
    }

    private static boolean isByte(final Api.Type deepArrayComponentType) {
        return Objects.equals(deepArrayComponentType.getCanonicalName(), "byte");
    }

    static String getFirstEnumValue(final PsiClass psiClass) {
        final PsiField[] fields = psiClass.getFields();
        final Optional<PsiField> enumField = Arrays.stream(fields).filter(x -> x instanceof PsiEnumConstant).findFirst();
        if(enumField.isEmpty()) {
            return TypeCreator.DefaultInitExpression;
        }
        if(psiClass.getQualifiedName() == null) {
            return TypeCreator.DefaultInitExpression;
        }

        return psiClass.getQualifiedName() + "." + enumField.get().getName();
    }

    public static boolean isNonStaticInnerClass(final PsiClass psiClass) {
        return psiClass.getContainingClass() != null && !psiClass.hasModifierProperty(PsiModifier.STATIC);
    }

    public static boolean isNonStaticInnerClass(final PsiType psiType) {
        final PsiClassType.ClassResolveResult resolveResult = PsiUtil.resolveGenericsClassInType(psiType);
        final PsiClass psiClass = resolveResult.getElement();
        if(psiClass == null || psiClass.getQualifiedName() == null) {
            return false;
        }
        return isNonStaticInnerClass(psiClass);
    }
}
