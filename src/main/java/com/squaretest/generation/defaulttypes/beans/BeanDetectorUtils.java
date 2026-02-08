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
package com.squaretest.generation.defaulttypes.beans;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiReturnStatement;
import com.intellij.psi.PsiStatement;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypes;
import com.intellij.psi.impl.light.LightRecordField;
import com.intellij.psi.impl.light.LightRecordMethod;
import com.intellij.psi.util.PsiUtil;
import com.intellij.psi.util.TypeConversionUtil;
import com.squaretest.generation.CompiledUtils;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.utils.IdentityMultimapWithIdentitySets;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class BeanDetectorUtils {
    /**
     * Allow specific instance methods that are not getters or setters.
     */
    static final String[] AllowedNonGetterMethods =
            new String[]{"close", "equals", "hashCode", "toString", "finalize", "clone", "compareTo", "marshall"};
    private static final List<String> KnownInstanceMemberPrefixes = Arrays.asList("m_", "m", "_", "__", "my");
    static final List<String> FieldSuffixes = Arrays.asList("__", "_");
    static final List<String> GetterPrefixes = Arrays.asList("get", "is", "getIs");
    static final List<String> WithMethodPrefixes = Arrays.asList("with", "withis", "withIs");
    static final List<String> SetterPrefixes = Arrays.asList("set", "setIs");

    private BeanDetectorUtils() {
    }

    static void matchFieldsAndSetters(
            final String fieldNamePrefix,
            final IdentityMultimapWithIdentitySets<PsiField, PsiMethod> fieldToSettersMultimap,
            final Map<PsiMethod, PsiField> setterToFieldMap, final Set<PsiField> fieldsForName,
            final Set<PsiMethod> settersWithName) {
        if(fieldsForName.size() == 1) {
            final PsiField field = fieldsForName.iterator().next();
            for(final PsiMethod setter : settersWithName) {
                if(!setterToFieldMap.containsKey(setter)) {
                    setterToFieldMap.put(setter, field);
                    fieldToSettersMultimap.put(field, setter);
                }
            }
            return;
        }

        // Compute all (field, setter) combinations.
        final List<FieldMethodPair> fieldMethodPairs = new ArrayList<>(fieldsForName.size() * settersWithName.size());
        for(final PsiField field : fieldsForName) {
            for(final PsiMethod setter : settersWithName) {
                fieldMethodPairs.add(new FieldMethodPair(field, setter, computeMatchingScoreForSetter(fieldNamePrefix, field, setter)));
            }
        }
        // Sort the combinations by score.
        fieldMethodPairs.sort(Comparator.comparing(FieldMethodPair::score).reversed());
        for(final FieldMethodPair pair : fieldMethodPairs) {
            final PsiMethod method = pair.method();
            if(!setterToFieldMap.containsKey(method)) {
                final PsiField field = pair.field();
                setterToFieldMap.put(method, field);
                fieldToSettersMultimap.put(field, method);
            }
        }
    }

    private static int computeMatchingScoreForSetter(final String fieldNamePrefix, final PsiField field, final PsiMethod setter) {
        // Create all (field, setter) tuples.
        // Give each (field, setter) pair a score.
        // Sort the pairs by score.
        // Iterate through them and make the assignments.
        // Assign 50,000 points for having the same name as the field; e.g. method: isValid() and field: this.isValid.
        // Assign 10,000 points for having name consistent with Bean naming convention.
        // Assign 1 point for each letter that matches (including case) after removing field name prefix (if there is one).
        int score = 0;

        // Determine if the method has the same name as the field. Remove the prefix if there is one.
        String primaryName;
        if(fieldNamePrefix != null) {
            primaryName = StringUtils.uncapitalize(StringUtils.removeStart(field.getName(), fieldNamePrefix));
        } else {
            primaryName = field.getName();
        }
        if(setter.getName().equals(primaryName)) {
            score += 50_000;
            return score;
        }

        // Determine if the method has the same name as the Bean naming convention for the field.
        final String setterName = "set" + StringUtils.capitalize(primaryName);
        if(setter.getName().equals(setterName)) {
            score += 10_000;
            return score;
        }

        final String setterNameWithoutPrefix = StringUtils.uncapitalize(StringUtils.removeStart(setter.getName(), "set"));
        score += countMatchingChars(primaryName, setterNameWithoutPrefix);
        return score;
    }

    static void matchFieldsAndGetters(
            final String fieldNamePrefix, final IdentityMultimapWithIdentitySets<PsiField, PsiMethod> fieldToGettersMultimap,
            final Map<PsiMethod, PsiField> getterToFieldMap, final Set<PsiField> fieldsForName,
            final Set<PsiMethod> gettersWithName) {
        if(fieldsForName.size() == 1) {
            final PsiField field = fieldsForName.iterator().next();
            for(final PsiMethod getter : gettersWithName) {
                if(!getterToFieldMap.containsKey(getter) && isCompatible(field, getter)) {
                    getterToFieldMap.put(getter, field);
                    fieldToGettersMultimap.put(field, getter);
                }
            }
            return;
        }
        // Compute all (field, setter) combinations.
        final List<FieldMethodPair> fieldMethodPairs = new ArrayList<>(fieldsForName.size() * gettersWithName.size());
        for(final PsiField field : fieldsForName) {
            for(final PsiMethod setter : gettersWithName) {
                fieldMethodPairs.add(new FieldMethodPair(field, setter, computeMatchingScoreForGetter(fieldNamePrefix, field, setter)));
            }
        }
        // Sort the combinations by score.
        fieldMethodPairs.sort(Comparator.comparing(FieldMethodPair::score).reversed());
        for(final FieldMethodPair pair : fieldMethodPairs) {
            final PsiMethod method = pair.method();
            if(!getterToFieldMap.containsKey(method)) {
                final PsiField field = pair.field();
                getterToFieldMap.put(method, field);
                fieldToGettersMultimap.put(field, method);
            }
        }
    }

    private static boolean isCompatible(final PsiField field, final PsiMethod getter) {
        final PsiType getterReturnType = getter.getReturnType();
        if(getterReturnType == null) {
            // This shouldn't happen.
            return false;
        }
        final PsiType fieldType = field.getType();
        if(TypeConversionUtil.isAssignable(getterReturnType, fieldType)) {
            return true;
        }
        if(PsiTypes.intType().equals(getterReturnType)) {
            // Handle the case where the getter is an int, and the field is an enum.
            // Example: com.google.cloud.dialogflow.cx.v3.Match#getMatchType().
            return TypeConversionUtil.isEnumType(fieldType);
        }
        if(TypeConversionUtil.isEnumType(getterReturnType)) {
            // Handle the case where the getter returns an enum but the field is an int.
            if(PsiTypes.intType().equals(fieldType)) {
                return true;
            }
            return isString(fieldType);
        }
        if(isString(getterReturnType)) {
            // Handle the case where the getter returns a String and the field is an enum.
            // AWS SDK V2 has method overloads that use this convention.
            // Also return true if the getter returns a String and the field is explicitly "java.lang.Object";
            // Protobuf3 uses this convention.
            return TypeConversionUtil.isEnumType(fieldType) || isObject(fieldType);
        }
        return false;
    }

    static boolean isString(final PsiType psiType) {
        final PsiClass psiClass = PsiUtil.resolveClassInType(psiType);
        if(psiClass == null) {
            return false;
        }
        return StringUtils.equals(psiClass.getQualifiedName(), JavaNames.JavaLangString);
    }

    static boolean isObject(final PsiType psiType) {
        final PsiClass psiClass = PsiUtil.resolveClassInType(psiType);
        if(psiClass == null) {
            return false;
        }
        return StringUtils.equals(psiClass.getQualifiedName(), JavaNames.JavaLangObject);
    }

    static void matchFieldsAndWithMethods(
            final String fieldNamePrefix, final IdentityMultimapWithIdentitySets<PsiField, PsiMethod> fieldToWithMethodsMultimap,
            final Map<PsiMethod, PsiField> withMethodsToFieldMap, final Set<PsiField> fieldsForName,
            final Set<PsiMethod> withMethodsWithName) {
        if(fieldsForName.size() == 1) {
            final PsiField field = fieldsForName.iterator().next();
            for(final PsiMethod withMethod : withMethodsWithName) {
                if(!withMethodsToFieldMap.containsKey(withMethod)) {
                    withMethodsToFieldMap.put(withMethod, field);
                    fieldToWithMethodsMultimap.put(field, withMethod);
                }
            }
            return;
        }
        // Compute all (field, setter) combinations.
        final List<FieldMethodPair> fieldMethodPairs = new ArrayList<>(fieldsForName.size() * withMethodsWithName.size());
        for(final PsiField field : fieldsForName) {
            for(final PsiMethod withMethod : withMethodsWithName) {
                fieldMethodPairs.add(new FieldMethodPair(field, withMethod, computeMatchingScoreForWithMethod(fieldNamePrefix, field, withMethod)));
            }
        }
        // Sort the combinations by score.
        fieldMethodPairs.sort(Comparator.comparing(FieldMethodPair::score).reversed());
        for(final FieldMethodPair pair : fieldMethodPairs) {
            final PsiMethod method = pair.method();
            if(!withMethodsToFieldMap.containsKey(method)) {
                final PsiField field = pair.field();
                withMethodsToFieldMap.put(method, field);
                fieldToWithMethodsMultimap.put(field, method);
            }
        }
    }

    private static int computeMatchingScoreForGetter(final String fieldNamePrefix, final PsiField field, final PsiMethod getter) {
        // Create all (field, setter) tuples.
        // Give each (field, setter) pair a score.
        // Sort the pairs by score.
        // Iterate through them and make the assignments.
        // Assign 50,000 points for having the same name as the field; e.g. method: isValid() and field: this.isValid.
        // Assign 10,000 points for having name consistent with Bean naming convention.
        // Assign 1 point for each letter that matches (including case) after removing field name prefix (if there is one).
        int score = 0;

        // Determine if the method has the same name as the field. Remove the prefix if there is one.
        String primaryName;
        if(fieldNamePrefix != null) {
            primaryName = StringUtils.uncapitalize(StringUtils.removeStart(field.getName(), fieldNamePrefix));
        } else {
            primaryName = field.getName();
        }
        final String getterName = getter.getName();
        if(getterName.equals(primaryName)) {
            score += 50_000;
            return score;
        }

        // Determine if the method has the same name as the Bean naming convention for the field.
        final String expectedGetterName;
        if(StringUtils.equalsAny(field.getType().getCanonicalText(), "boolean", "java.lang.Boolean")) {
            expectedGetterName = "is" + StringUtils.capitalize(primaryName);
        } else {
            expectedGetterName = "get" + StringUtils.capitalize(primaryName);
        }
        if(getterName.equals(expectedGetterName)) {
            score += 10_000;
            return score;
        }

        // Compute the getter name without get or is prefix. Then uncapitalize it.
        String getterNameWithoutPrefix = getterName;
        for(final String prefix : GetterPrefixes) {
            if(nameHasPrefix(getterName, prefix)) {
                getterNameWithoutPrefix = StringUtils.removeStart(getterName, prefix);
                break;
            }
        }
        getterNameWithoutPrefix = StringUtils.uncapitalize(getterNameWithoutPrefix);
        score += countMatchingChars(primaryName, getterNameWithoutPrefix);
        return score;
    }

    private static int computeMatchingScoreForWithMethod(final String fieldNamePrefix, final PsiField field, final PsiMethod withMethod) {
        // Create all (field, setter) tuples.
        // Give each (field, setter) pair a score.
        // Sort the pairs by score.
        // Iterate through them and make the assignments.
        // Assign 50,000 points for having the same name as the field; e.g. method: isValid() and field: this.isValid.
        // Assign 10,000 points for having name consistent with Bean naming convention.
        // Assign 1 point for each letter that matches (including case) after removing field name prefix (if there is one).
        int score = 0;

        // Determine if the method has the same name as the field. Remove the prefix if there is one.
        String primaryName;
        if(fieldNamePrefix != null) {
            primaryName = StringUtils.uncapitalize(StringUtils.removeStart(field.getName(), fieldNamePrefix));
        } else {
            primaryName = field.getName();
        }
        final String withMethodName = withMethod.getName();
        if(withMethodName.equals(primaryName)) {
            score += 50_000;
            return score;
        }

        // Determine if the method has the same name as the Bean naming convention for the field.
        final String setterName = "with" + StringUtils.capitalize(primaryName);
        if(withMethodName.equals(setterName)) {
            score += 10_000;
            return score;
        }

        final String withMethodNameWithoutPrefix = StringUtils.uncapitalize(StringUtils.removeStart(withMethodName, "with"));
        score += countMatchingChars(primaryName, withMethodNameWithoutPrefix);
        return score;
    }

    private static int countMatchingChars(final String primaryName, final String setterNameWithoutPrefix) {
        int count = 0;
        for(int i = 0; i < primaryName.length() && i < setterNameWithoutPrefix.length(); i++) {
            if(primaryName.charAt(i) == setterNameWithoutPrefix.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    static boolean isWithMethod(final PsiMethod method) {
        if(!method.getName().startsWith("with")) {
            return false;
        }
        if(method.getParameterList().getParameters().length != 1) {
            return false;
        }

        final PsiType returnType = method.getReturnType();
        if(returnType == null || PsiTypes.voidType().equals(returnType)) {
            return false;
        }
        final PsiClass containingClass = method.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        final String containingClassCanonicalName = containingClass.getQualifiedName();
        final String methodReturnTypeCanonicalName = getReturnTypeCanonicalName(returnType);
        return Objects.equals(containingClassCanonicalName, methodReturnTypeCanonicalName);
    }

    @Nullable
    private static String getReturnTypeCanonicalName(final PsiType type) {
        final PsiClass resolvedClass = PsiUtil.resolveClassInType(type);
        if(resolvedClass != null) {
            return resolvedClass.getQualifiedName();
        }
        return null;
    }

    static boolean nameEndsWithDto(final PsiClass psiClass) {
        final String qualifiedName = psiClass.getQualifiedName();
        if(qualifiedName == null) {
            return false;
        }
        return StringUtils.endsWithAny(qualifiedName, "Dto", "DTO", "DtoBean", "DTOBean");
    }

    static boolean isPossibleJAXBGetter(final PsiMethod getter, final PsiField instanceField) {
        if(getter == null) {
            return false;
        }
        if(getter instanceof LightRecordMethod || instanceField instanceof LightRecordField) {
            return false;
        }
        final String methodName = getter.getName();
        if(!isGetterNameThatStartsWithGet(methodName)) {
            return false;
        }

        if(!Character.isUpperCase(methodName.charAt(3))) {
            // Check for the bean name that is less common.
            // If we have field: eTag, IDEA will generate getter: geteTag. This happens when the first letter of the
            // field is lowercase and the second letter is upper case. It does this to comply the the bean naming spec; see:
            // https://intellij-support.jetbrains.com/hc/en-us/community/posts/206879995-bean-naming-conventions-getter-setter.
            final String instanceFieldName = instanceField.getName();
            boolean isTechnicallyCorrectBeanName = instanceFieldName.length() >= 2 && Character.isLowerCase(
                    instanceFieldName.charAt(0)) && Character.isUpperCase(instanceFieldName.charAt(1));
            if(!isTechnicallyCorrectBeanName) {
                return false;
            }
        }

        if(!isStandardCollection(instanceField.getType())) {
            return false;
        }

        if(getterReturnsUnmodifiableView(getter)) {
            return false;
        }

        return true;
    }

    private static boolean getterReturnsUnmodifiableView(PsiMethod psiMethod) {
        // If the method is compiled, use the source if available.
        psiMethod = CompiledUtils.getMethodWithSourceCode(psiMethod);
        final PsiType returnType = psiMethod.getReturnType();
        if(returnType == null) {
            return false;
        }
        final PsiCodeBlock body = psiMethod.getBody();
        if(body == null) {
            return false;
        }
        final PsiStatement[] statements = body.getStatements();
        if(statements.length != 1) {
            return false;
        }
        final PsiStatement statement = statements[0];
        if(!(statement instanceof final PsiReturnStatement returnStatement)) {
            return false;
        }
        final PsiExpression returnExpression = returnStatement.getReturnValue();
        if(!(returnExpression instanceof final PsiMethodCallExpression returnCall)) {
            return false;
        }
        final PsiMethod returnMethod = returnCall.resolveMethod();
        if(returnMethod == null) {
            return false;
        }
        final PsiClass returnMethodClass = returnMethod.getContainingClass();
        if(returnMethodClass == null) {
            return false;
        }
        return returnMethod.getName().startsWith("unmodifiable") && StringUtils.equals(returnMethodClass.getQualifiedName(), "java.util.Collections");
    }

    private static boolean isGetterNameThatStartsWithGet(final String methodName) {
        if(!methodName.startsWith("get")) {
            return false;
        }
        return methodName.length() > 3;
    }

    private static boolean isStandardCollection(@Nullable final PsiType type) {
        if(type == null) {
            return false;
        }
        return StringUtils.startsWithAny(type.getCanonicalText(), "java.util.List", "java.util.Collection", "java.util.Set");
    }

    static boolean hasWithPrefix(final String methodName) {
        return StringUtils.startsWith(methodName, "with") && methodName.length() >= 5 && Character.isUpperCase(methodName.charAt(4));
    }

    static boolean isSameType(final PsiType returnType, final PsiClass containingClass) {
        if(containingClass == null || returnType == null) {
            return false;
        }
        final PsiClass returnClass = PsiUtil.resolveClassInType(returnType);
        if(returnClass == null) {
            return false;
        }
        return Objects.equals(returnClass.getQualifiedName(), containingClass.getQualifiedName());
    }

    @Nullable
    static String determinePrefixIfExists(final List<PsiField> instanceFields) {
        for(final String prefix : KnownInstanceMemberPrefixes) {
            if(instanceFields.stream().allMatch(x -> nameHasPrefix(x.getName(), prefix))) {
                return prefix;
            }
        }
        return null;
    }

    static boolean nameHasPrefix(final String fieldName, final String prefix) {
        return fieldName.startsWith(prefix) && fieldName.length() > prefix.length() &&
                (prefix.endsWith("_") || Character.isUpperCase(fieldName.charAt(prefix.length())));
    }

    public static String removeSuffix(final String fieldName) {
        for(final String suffix : FieldSuffixes) {
            if(fieldName.endsWith(suffix)) {
                return StringUtils.removeEnd(fieldName, suffix);
            }
        }
        return fieldName;
    }
}
