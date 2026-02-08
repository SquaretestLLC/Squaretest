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
package com.squaretest.generation.dataflow.usedmethods;

import com.intellij.psi.*;
import com.intellij.psi.impl.light.LightElement;
import com.intellij.psi.util.PsiUtil;
import com.squaretest.generation.CompiledUtils;
import com.squaretest.generation.dataflow.DataflowUtils;
import com.squaretest.generation.defaulttypes.DefaultTypeInfo;
import com.squaretest.generation.defaulttypes.DefaultTypesHolder;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.defaulttypes.TypeCreatorUtil;
import com.squaretest.generation.dependencyinteraction.DependencyInteractionCollectorUtils;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.squaretest.generation.simpleexit.ConstantInitExpressionValueProvider.JdkOrLibraryPrefixes;

public class CalledMethodsInfoProvider {

    private static final String[] NonDataParamTypes = new String[]{
            "java.util.concurrent.Executor", "java.util.concurrent.ExecutorService", "com.google.common.util.concurrent.ListeningExecutorService", "java.lang.Class", "java.lang.Package"};

    private static final int MaxNumberOfHopsAwayFromSourceClass = 6;
    private static final int MaxNumberOfStaticMethodsToAnalyze = 300;
    private static final int MaxNumberOfCallsToRecordInStaticMethods = 10_000;

    @NotNull
    private final Set<PsiClass> sourceAndSupers;
    @NotNull
    private final Map<PsiMethod, CalledMethodInfo> methodsCalledInSourceAndSupers;
    @NotNull
    private final Map<String, CalledMethodInfo> lombokMethodsCalledInSourceAndSupers;
    @NotNull
    private final Map<PsiMethod, CalledMethodInfo> methodsCalledInStaticHelpers;
    @NotNull
    private final Map<String, CalledMethodInfo> lombokMethodsCalledInStaticHelpers;
    @NotNull
    private final Set<PsiField> fieldsReferencedInSourceAndSupers;
    @NotNull
    private final Set<PsiField> fieldsReferencedInStaticHelpers;
    @NotNull
    private final DefaultTypesHolder defaultTypesHolder;
    private boolean isInitialized;

    public CalledMethodsInfoProvider(
            @NotNull final PsiClass sourceClass,
            @NotNull final DefaultTypesHolder defaultTypesHolder) {
        this.defaultTypesHolder = defaultTypesHolder;
        Set<PsiClass> superClasses = DependencyInteractionCollectorUtils.computeSuperClasses(sourceClass);
        this.sourceAndSupers = new LinkedHashSet<>();
        this.sourceAndSupers.add(sourceClass);
        this.sourceAndSupers.addAll(superClasses);
        this.methodsCalledInSourceAndSupers = new IdentityHashMap<>();
        this.lombokMethodsCalledInSourceAndSupers = new HashMap<>();
        this.methodsCalledInStaticHelpers = new IdentityHashMap<>();
        this.lombokMethodsCalledInStaticHelpers = new HashMap<>();
        this.isInitialized = false;
        this.fieldsReferencedInSourceAndSupers = SetUtils.newIdentityHashSet();
        this.fieldsReferencedInStaticHelpers = SetUtils.newIdentityHashSet();
    }

    public Collection<CalledMethodInfo> getMethodsCalledInSourceClass() {
        initializeIfNeeded();
        final List<CalledMethodInfo> ret = new ArrayList<>();
        ret.addAll(methodsCalledInSourceAndSupers.values());
        ret.addAll(lombokMethodsCalledInSourceAndSupers.values());
        return ret;
    }

    public Collection<CalledMethodInfo> getMethodsCalledInStaticMethods() {
        initializeIfNeeded();
        final List<CalledMethodInfo> ret = new ArrayList<>();
        ret.addAll(methodsCalledInStaticHelpers.values());
        ret.addAll(lombokMethodsCalledInStaticHelpers.values());
        return ret;
    }

    @Nullable
    public CalledMethodInfo getSourceAndSuperMethodCallInfo(final PsiMethod psiMethod) {
        initializeIfNeeded();
        if(isSpecialLombokMethod(psiMethod)) {
            final String methodKey = getLombokMethodKey(psiMethod);
            if(methodKey == null) {
                return null;
            }
            return lombokMethodsCalledInSourceAndSupers.get(methodKey);
        }
        return methodsCalledInSourceAndSupers.get(psiMethod);
    }

    @Nullable
    public CalledMethodInfo getStaticMethodCallInfo(final PsiMethod psiMethod) {
        initializeIfNeeded();
        if(isSpecialLombokMethod(psiMethod)) {
            final String methodKey = getLombokMethodKey(psiMethod);
            if(methodKey == null) {
                return null;
            }
            return lombokMethodsCalledInStaticHelpers.get(methodKey);
        }
        return methodsCalledInStaticHelpers.get(psiMethod);
    }

    public boolean calledInSource(final PsiMethod psiMethod) {
        initializeIfNeeded();
        if(isSpecialLombokMethod(psiMethod)) {
            final String methodKey = getLombokMethodKey(psiMethod);
            if(methodKey == null) {
                return false;
            }
            return lombokMethodsCalledInSourceAndSupers.containsKey(methodKey);
        }
        return methodsCalledInSourceAndSupers.containsKey(psiMethod);
    }

    public boolean calledInStaticHelpers(final PsiMethod psiMethod) {
        initializeIfNeeded();
        if(isSpecialLombokMethod(psiMethod)) {
            final String methodKey = getLombokMethodKey(psiMethod);
            if(methodKey == null) {
                return false;
            }
            return lombokMethodsCalledInStaticHelpers.containsKey(methodKey);
        }
        return methodsCalledInStaticHelpers.containsKey(psiMethod);
    }

    public boolean referencedInSourceAndSupers(final PsiField psiField) {
        initializeIfNeeded();
        return fieldsReferencedInSourceAndSupers.contains(psiField);
    }

    public boolean referencedInStaticHelpers(final PsiField psiField) {
        initializeIfNeeded();
        return fieldsReferencedInStaticHelpers.contains(psiField);
    }

    private void initializeIfNeeded() {
        if(!isInitialized) {
            initialize();
        }
    }

    private void initialize() {
        // Determine the methods called in the source class and its super classes.
        for(final PsiClass psiClass : sourceAndSupers) {
            if(psiClass instanceof PsiCompiledElement) {
                // We don't want to explore compiled elements. Also using JavaRecursiveElementWalkingVisitor on
                // compiled elements triggers an exception.
                continue;
            }
            if(StringUtils.equals(psiClass.getQualifiedName(), "java.lang.Object")) {
                continue;
            }
            psiClass.accept(new JavaRecursiveElementWalkingVisitor() {

                @Override
                public void visitNewExpression(@NotNull final PsiNewExpression expression) {
                    super.visitNewExpression(expression);
                    final PsiMethod calledMethod = CompiledUtils.getMethodWithSourceCode(expression.resolveConstructor());
                    if(calledMethod == null) {
                        return;
                    }
                    CalledMethodInfo calledInfo = methodsCalledInSourceAndSupers.get(calledMethod);
                    if(calledInfo != null) {
                        calledInfo.addCallExpression(expression);
                    } else {
                        calledInfo = new CalledMethodInfo(calledMethod, new LinkedList<>(), new LinkedList<>());
                        calledInfo.addCallExpression(expression);
                        methodsCalledInSourceAndSupers.put(calledMethod, calledInfo);
                    }
                }

                @Override
                public void visitMethodCallExpression(@NotNull final PsiMethodCallExpression expression) {
                    super.visitMethodCallExpression(expression);
                    final PsiMethod calledMethod = CompiledUtils.getMethodWithSourceCode(expression.resolveMethod());
                    if(calledMethod == null) {
                        return;
                    }
                    if(isSpecialLombokMethod(calledMethod)) {
                        final String methodKey = getLombokMethodKey(calledMethod);
                        if(methodKey == null) {
                            // This shouldn't happen. If it does, there is likely a bug in the Lombok plugin.
                            return;
                        }
                        CalledMethodInfo calledInfo = lombokMethodsCalledInSourceAndSupers.get(methodKey);
                        if(calledInfo != null) {
                            calledInfo.addCallExpression(expression);
                        } else {
                            calledInfo = new CalledMethodInfo(calledMethod, new LinkedList<>(), new LinkedList<>());
                            calledInfo.addCallExpression(expression);
                            lombokMethodsCalledInSourceAndSupers.put(methodKey, calledInfo);
                        }
                        return;
                    }
                    CalledMethodInfo calledInfo = methodsCalledInSourceAndSupers.get(calledMethod);
                    if(calledInfo != null) {
                        calledInfo.addCallExpression(expression);
                    } else {
                        calledInfo = new CalledMethodInfo(calledMethod, new LinkedList<>(), new LinkedList<>());
                        calledInfo.addCallExpression(expression);
                        methodsCalledInSourceAndSupers.put(calledMethod, calledInfo);
                    }
                }

                @Override
                public void visitMethodReferenceExpression(@NotNull final PsiMethodReferenceExpression expression) {
                    super.visitMethodReferenceExpression(expression);
                    final PsiElement calledMethodElement = expression.resolve();
                    if(!(calledMethodElement instanceof PsiMethod)) {
                        return;
                    }
                    final PsiMethod calledMethod = CompiledUtils.getMethodWithSourceCode((PsiMethod) calledMethodElement);
                    if(isSpecialLombokMethod(calledMethod)) {
                        final String methodKey = getLombokMethodKey(calledMethod);
                        if(methodKey == null) {
                            // This shouldn't happen. If it does, there is likely a bug in the Lombok plugin.
                            return;
                        }
                        CalledMethodInfo calledInfo = lombokMethodsCalledInSourceAndSupers.get(methodKey);
                        if(calledInfo != null) {
                            calledInfo.addReferenceExpression(expression);
                        } else {
                            calledInfo = new CalledMethodInfo(calledMethod, new LinkedList<>(), new LinkedList<>());
                            calledInfo.addReferenceExpression(expression);
                            lombokMethodsCalledInSourceAndSupers.put(methodKey, calledInfo);
                        }
                        return;
                    }
                    CalledMethodInfo calledInfo = methodsCalledInSourceAndSupers.get(calledMethod);
                    if(calledInfo != null) {
                        calledInfo.addReferenceExpression(expression);
                    } else {
                        calledInfo = new CalledMethodInfo(calledMethod, new LinkedList<>(), new LinkedList<>());
                        calledInfo.addReferenceExpression(expression);
                        methodsCalledInSourceAndSupers.put(calledMethod, calledInfo);
                    }
                }

                @Override
                public void visitReferenceExpression(@NotNull final PsiReferenceExpression expression) {
                    super.visitReferenceExpression(expression);
                    final PsiElement target = expression.resolve();
                    if(target instanceof PsiField) {
                        final PsiField targetField = CompiledUtils.getFieldWithSourceCode((PsiField) target);
                        fieldsReferencedInSourceAndSupers.add(targetField);
                    }
                }
            });
        }

        // Compute the methods called in static methods that are called in the source and supers.
        final Set<PsiMethod> alreadyFollowedStaticMethods = SetUtils.newIdentityHashSet();
        final MutableInt numberOfStaticMethodsAnalyzed = new MutableInt(0);
        final MutableInt numberOfCallsRecordedInStaticMethods = new MutableInt(0);
        for(final PsiMethod method : methodsCalledInSourceAndSupers.keySet()) {
            followStaticMethodRecursively(alreadyFollowedStaticMethods, method, 0, numberOfStaticMethodsAnalyzed,
                    numberOfCallsRecordedInStaticMethods);
        }
        this.isInitialized = true;
    }

    private String getLombokMethodKey(final PsiMethod calledMethod) {
        final PsiClass containingClass = calledMethod.getContainingClass();
        if(containingClass == null) {
            return null;
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName == null) {
            return null;
        }
        return qualifiedName + "_" + TypeCreatorUtil.getMethodKey(calledMethod);
    }

    /**
     * Returns true if this is a Light method within a Light class.
     * Light methods and classes don't exist in the code; they are added to the PSI by the Lombok plugin.
     * <p>
     * If we have a light method within a light class, we need to store it in a special Map that uses the
     * "methodKey" as the key. We cannot store it in an IdentityHashMap, because the lombok plugin returns different
     * PsiMethod instances depending on how the methods are accessed.
     * <p>
     * This only applies to builder methods. Getter and Setter methods generated by lombok are contained in an
     * actual class (not a generated class). The Lombok plugin always returns the PsiMethod instance.
     *
     * @param calledMethod the method to check.
     * @return true if this is a special lombok method.
     */
    private boolean isSpecialLombokMethod(final PsiMethod calledMethod) {
        if(!(calledMethod instanceof LightElement)) {
            return false;
        }
        if(!StringUtils.contains(calledMethod.getClass().getName(), JavaNames.LombokLight)) {
            return false;
        }
        final PsiClass containingClass = calledMethod.getContainingClass();
        if(!(containingClass instanceof LightElement)) {
            return false;
        }
        if(!StringUtils.contains(containingClass.getClass().getName(), JavaNames.LombokLight)) {
            return false;
        }
        return true;
    }

    private void followStaticMethodRecursively(
            final Set<PsiMethod> alreadyFollowedStaticMethods, final PsiMethod method, final int callstackDepth,
            final MutableInt numberOfStaticMethodsAnalyzed, final MutableInt numberOfCallsRecordedInStaticMethods) {
        if(callstackDepth >= MaxNumberOfHopsAwayFromSourceClass) {
            return;
        }
        if(numberOfStaticMethodsAnalyzed.getValue() >= MaxNumberOfStaticMethodsToAnalyze) {
            return;
        }
        if(numberOfCallsRecordedInStaticMethods.getValue() >= MaxNumberOfCallsToRecordInStaticMethods) {
            return;
        }
        if(alreadyFollowedStaticMethods.contains(method)) {
            return;
        }
        if(!shouldFollowStaticMethod(method)) {
            return;
        }

        alreadyFollowedStaticMethods.add(method);
        numberOfStaticMethodsAnalyzed.increment();
        final int newCallStackDepth = callstackDepth + 1;
        method.accept(new JavaRecursiveElementWalkingVisitor() {

            @Override
            public void visitNewExpression(@NotNull final PsiNewExpression expression) {
                super.visitNewExpression(expression);
                final PsiMethod calledMethod = CompiledUtils.getMethodWithSourceCode(expression.resolveConstructor());
                if(calledMethod == null) {
                    return;
                }
                CalledMethodInfo calledInfo = methodsCalledInStaticHelpers.get(calledMethod);
                if(calledInfo != null) {
                    calledInfo.addCallExpression(expression);
                } else {
                    calledInfo = new CalledMethodInfo(calledMethod, new LinkedList<>(), new LinkedList<>());
                    calledInfo.addCallExpression(expression);
                    methodsCalledInStaticHelpers.put(calledMethod, calledInfo);
                }
                numberOfCallsRecordedInStaticMethods.increment();
            }

            @Override
            public void visitMethodCallExpression(@NotNull final PsiMethodCallExpression expression) {
                super.visitMethodCallExpression(expression);
                final PsiMethod calledMethod = CompiledUtils.getMethodWithSourceCode(expression.resolveMethod());
                if(calledMethod == null) {
                    return;
                }
                if(calledMethod.hasModifierProperty(PsiModifier.STATIC)) {
                    followStaticMethodRecursively(alreadyFollowedStaticMethods, calledMethod, newCallStackDepth, numberOfStaticMethodsAnalyzed, numberOfCallsRecordedInStaticMethods);
                    return;
                }
                if(isSpecialLombokMethod(calledMethod)) {
                    final String methodKey = getLombokMethodKey(calledMethod);
                    if(methodKey == null) {
                        // This shouldn't happen. If it does, there is likely a bug in the Lombok plugin.
                        return;
                    }
                    CalledMethodInfo calledInfo = lombokMethodsCalledInStaticHelpers.get(methodKey);
                    if(calledInfo != null) {
                        calledInfo.addCallExpression(expression);
                    } else {
                        calledInfo = new CalledMethodInfo(calledMethod, new LinkedList<>(), new LinkedList<>());
                        calledInfo.addCallExpression(expression);
                        lombokMethodsCalledInStaticHelpers.put(methodKey, calledInfo);
                    }
                } else {
                    CalledMethodInfo calledInfo = methodsCalledInStaticHelpers.get(calledMethod);
                    if(calledInfo != null) {
                        calledInfo.addCallExpression(expression);
                    } else {
                        calledInfo = new CalledMethodInfo(calledMethod, new LinkedList<>(), new LinkedList<>());
                        calledInfo.addCallExpression(expression);
                        methodsCalledInStaticHelpers.put(calledMethod, calledInfo);
                    }
                }
                numberOfCallsRecordedInStaticMethods.increment();
            }

            @Override
            public void visitMethodReferenceExpression(@NotNull final PsiMethodReferenceExpression expression) {
                super.visitMethodReferenceExpression(expression);
                final PsiElement calledMethodElement = expression.resolve();
                if(!(calledMethodElement instanceof PsiMethod)) {
                    return;
                }
                final PsiMethod calledMethod = CompiledUtils.getMethodWithSourceCode((PsiMethod) calledMethodElement);
                if(calledMethod.hasModifierProperty(PsiModifier.STATIC)) {
                    followStaticMethodRecursively(alreadyFollowedStaticMethods, calledMethod, newCallStackDepth, numberOfStaticMethodsAnalyzed, numberOfCallsRecordedInStaticMethods);
                }
            }

            @Override
            public void visitReferenceExpression(@NotNull final PsiReferenceExpression expression) {
                super.visitReferenceExpression(expression);
                final PsiElement target = expression.resolve();
                if(target instanceof PsiField) {
                    final PsiField targetField = CompiledUtils.getFieldWithSourceCode((PsiField) target);
                    fieldsReferencedInStaticHelpers.add(targetField);
                }
            }
        });
    }

    private boolean shouldFollowStaticMethod(final PsiMethod method) {
        if(method instanceof PsiCompiledElement || !method.isWritable()) {
            // Only follow static methods if we have the source and the method is also in this project (writable).
            return false;
        }

        if(!method.hasModifierProperty(PsiModifier.STATIC)) {
            return false;
        }
        // If the method is in the source or super, we don't need to follow it. We do full dataflow analysis on
        // those methods.
        final PsiClass containingClass = method.getContainingClass();
        if(containingClass == null || sourceAndSupers.contains(containingClass)) {
            return false;
        }

        // If the method has a type parameter, don't follow it.
        final PsiTypeParameterList typeParameterList = method.getTypeParameterList();
        if(typeParameterList != null && typeParameterList.getTypeParameters().length != 0) {
            return false;
        }

        if(StringUtils.equalsAny(containingClass.getName(), "Log", "Logger", "LoggerFactory")) {
            return false;
        }

        // If the method is a JDK method or a collection factory method;
        // e.g. com.google.common.collect.FluentIterable.from(..), don't follow it.
        // Also don't follow methods from known third party libraries, even if they're in the project (writable).
        // The libraries won't have setter calls or constructor calls for domain data objects.
        if(StringUtils.startsWithAny(containingClass.getQualifiedName(), JdkOrLibraryPrefixes)) {
            return false;
        }

        // If the method has no parameters, return false.
        final PsiParameter[] parameters = method.getParameterList().getParameters();
        if(parameters.length == 0) {
            return false;
        }
        for(final PsiParameter psiParameter : parameters) {
            if(isPossiblyDataObject(psiParameter.getType())) {
                return true;
            }
        }
        return isPossiblyDataObject(method.getReturnType());
    }

    private boolean isPossiblyDataObject(@Nullable final PsiType type) {
        if(type == null) {
            return false;
        }

        if(type instanceof PsiPrimitiveType || PsiTypes.voidType().equals(type)) {
            return false;
        }

        if(type instanceof PsiEllipsisType) {
            return isPossiblyDataObject(type.getDeepComponentType());
        }

        if(type instanceof PsiArrayType) {
            return isPossiblyDataObject(type.getDeepComponentType());
        }

        // Ensure not simple and not interface.
        final PsiClass psiClass = PsiUtil.resolveClassInType(type);
        if(psiClass == null) {
            return false;
        }

        // Determine if isSimple from DefaultTypesHolder info.
        // Also check for String, UUID, CharSequence, Object.
        final String canonicalName = psiClass.getQualifiedName();
        if(canonicalName == null) {
            return false;
        }

        // Strings and UUIDs are simple, but not stored in the DefaultTypes JSON file.
        if(DataflowUtils.isStringOrUUID(canonicalName)) {
            return false;
        }

        if(StringUtils.equalsAny(canonicalName, NonDataParamTypes)) {
            return false;
        }

        if(psiClass.isEnum()) {
            return false;
        }

        final DefaultTypeInfo defaultTypeInfo = defaultTypesHolder.get(canonicalName);
        if(defaultTypeInfo == null) {
            return true;
        }

        if(defaultTypeInfo.isSimple()) {
            return false;
        }

        if(defaultTypeInfo.isSimpleIfTypeParamsAreSimple()) {
            if(allTypeParamsSimple(type)) {
                return false;
            }
        }
        return true;
    }

    private boolean allTypeParamsSimple(final PsiType psiType) {
        final PsiClassType.ClassResolveResult resolveResult = PsiUtil.resolveGenericsClassInType(psiType);
        final PsiClass psiClass = resolveResult.getElement();
        final PsiSubstitutor typeSubstitutor = resolveResult.getSubstitutor();
        if(psiClass == null || psiClass.getQualifiedName() == null) {
            return false;
        }
        final PsiTypeParameterList typeParameterList = psiClass.getTypeParameterList();
        if(typeParameterList == null) {
            return false;
        }
        for(final PsiTypeParameter param : typeParameterList.getTypeParameters()) {
            final PsiType paramValue = typeSubstitutor.substitute(param);
            if(!isSimple(paramValue)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSimple(final PsiType type) {
        if(type == null) {
            return false;
        }
        if(type instanceof PsiPrimitiveType) {
            return true;
        }

        if(type instanceof PsiEllipsisType) {
            return isSimple(type.getDeepComponentType());
        }

        if(type instanceof PsiArrayType) {
            return isSimple(type.getDeepComponentType());
        }

        // Ensure not simple and not interface.
        final PsiClass psiClass = PsiUtil.resolveClassInType(type);
        if(psiClass == null) {
            return false;
        }
        if(psiClass.isInterface()) {
            return false;
        }
        if(psiClass.isEnum()) {
            return true;
        }
        // Determine if isSimple from DefaultTypesHolder info.
        // Also check for String, UUID, Charsequence, Object.
        final String canonicalName = psiClass.getQualifiedName();
        if(canonicalName == null) {
            return false;
        }

        // Strings and UUIDs are simple, but not stored in the DefaultTypes JSON file.
        if(DataflowUtils.isStringOrUUID(canonicalName)) {
            return true;
        }

        final DefaultTypeInfo defaultTypeInfo = defaultTypesHolder.get(canonicalName);
        if(defaultTypeInfo == null) {
            return false;
        }

        if(defaultTypeInfo.isSimple()) {
            return true;
        }

        if(defaultTypeInfo.isSimpleIfTypeParamsAreSimple()) {
            if(allTypeParamsSimple(type)) {
                return true;
            }
        }
        return false;
    }
}
