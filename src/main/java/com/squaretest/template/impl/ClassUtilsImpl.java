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
package com.squaretest.template.impl;

import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.squaretest.generation.CompiledUtils;
import com.squaretest.generation.InitStrategy;
import com.squaretest.generation.PsiToTemplateDataConverter;
import com.squaretest.generation.defaulttypes.DefaultTypesHolder;
import com.squaretest.template.api.Api;
import com.squaretest.template.api.BeanContext;
import com.squaretest.template.impl.beans.AcceptAllMethodsDecider;
import com.squaretest.template.impl.beans.InitSetterIncludedDecider;
import com.squaretest.template.impl.beans.PreferredNameDecider;
import com.squaretest.template.impl.beans.PropertyUsedInSourceDecider;
import com.squaretest.template.impl.beans.PropertyUsedInStaticHelperDecider;
import com.squaretest.template.impl.beans.WrappedSourceClass;
import com.squaretest.utils.MathUtils;
import com.squaretest.utils.SetterMethodSet;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import static com.squaretest.generation.setters.DefaultInitSetterDecider.allParamsRecognizedTypes;

public class ClassUtilsImpl implements Api.ClassUtils {

    private static final int MaxRecursiveBeanDepth = 15;
    private static final List<InitSetterIncludedDecider> InitSetterDeciders =
            Arrays.asList(
                    new PropertyUsedInSourceDecider(false),
                    new PropertyUsedInStaticHelperDecider(false),
                    new PreferredNameDecider(true),
                    new AcceptAllMethodsDecider(true));

    private static final Pattern FindCanonicalNamesRegex = Pattern.compile("([\\p{L}_$][\\p{L}\\p{N}_$]*\\.)+([\\p{Lu}_$][\\p{L}\\p{N}_$]*)", Pattern.DOTALL);

    private final PsiElement testSourcesRoot;
    private final JavaPsiFacade psiFacade;
    private final PsiToTemplateDataConverter psiToTemplateDataConverter;
    private final DefaultTypesHolder defaultTypesHolder;
    private final Map<ClassAndBeanPref, Api.SourceClass> canonicalNameToSourceClassMap;
    private final IdentityHashMap<BeanContext, Map<String, WrappedSourceClass>> beanContextToSourceClassesMap;
    private final Map<BeanContext, Map<String, WrappedSourceClass>> permanentBeanContextToSourceClassesMap;

    public ClassUtilsImpl(
            final PsiElement testSourcesRoot, final JavaPsiFacade psiFacade,
            final PsiToTemplateDataConverter psiToTemplateDataConverter, final DefaultTypesHolder defaultTypesHolder) {
        this.testSourcesRoot = testSourcesRoot;
        this.psiFacade = psiFacade;
        this.psiToTemplateDataConverter = psiToTemplateDataConverter;
        this.defaultTypesHolder = defaultTypesHolder;
        this.canonicalNameToSourceClassMap = new HashMap<>();
        this.beanContextToSourceClassesMap = new IdentityHashMap<>();
        this.permanentBeanContextToSourceClassesMap = new HashMap<>();
    }

    @Override
    public boolean isInTestClasspath(final String canonicalName) {
        if(canonicalName == null) {
            return false;
        }
        return psiFacade.findClass(canonicalName, testSourcesRoot.getResolveScope()) != null;
    }

    @Override
    public Api.FluentList<Api.Method> updateOverloadSuffixes(final Api.FluentList<Api.Method> methods) {
        if(allHaveSameClass(methods)) {
            return methods;
        }

        final Map<String, Integer> methodNameToOverloadCount = new HashMap<>();
        final Map<Api.Method, Integer> methodToOverloadNumber = new IdentityHashMap<>();
        for(final Api.Method method : methods) {
            final String methodName = method.getName();
            int count = methodNameToOverloadCount.getOrDefault(methodName, 0) + 1;
            methodToOverloadNumber.put(method, count);
            methodNameToOverloadCount.put(methodName, count);
        }

        for(final Api.Method method : methods) {
            final MethodImpl methodImpl = (MethodImpl) method;
            final Integer overloadNumber = methodToOverloadNumber.get(method);
            methodImpl.setOverloadNumber(overloadNumber);
            boolean isOverload = methodNameToOverloadCount.get(method.getName()) != 1;
            methodImpl.setIsOverload(isOverload);
        }
        return methods;
    }

    private boolean allHaveSameClass(final List<Api.Method> methods) {
        if(methods.isEmpty()) {
            return true;
        }
        final Api.SourceClass containingClass = methods.get(0).getContainingClass();
        for(final Api.Method method : methods) {
            if(method.getContainingClass() != containingClass) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String removePackageQualifiers(final String canonicalText) {
        if(StringUtils.isEmpty(canonicalText)) {
            return canonicalText;
        }
        return FindCanonicalNamesRegex.matcher(canonicalText).replaceAll("$2");
    }

    @Nullable
    @Override
    public Api.SourceClass resolveClass(@Nullable final Api.Type type) {
        if(type == null) {
            return null;
        }
        return resolveClass(type.getCanonicalName());
    }

    @Nullable
    @Override
    public Api.SourceClass resolveClass(@Nullable final Api.Variable variable) {
        if(variable == null) {
            return null;
        }
        return resolveClass(variable.getType().getCanonicalName());
    }

    @Nullable
    @Override
    public Api.SourceClass resolveClass(@Nullable final String canonicalName) {
        if(canonicalName == null) {
            return null;
        }
        final ClassAndBeanPref key = new ClassAndBeanPref(canonicalName, InitStrategy.Default);
        if(canonicalNameToSourceClassMap.containsKey(key)) {
            return canonicalNameToSourceClassMap.get(key);
        }

        final Api.SourceClass sourceClass = computeSourceClass(canonicalName, InitStrategy.Default);
        canonicalNameToSourceClassMap.put(key, sourceClass);
        return sourceClass;
    }

    @Override
    public boolean hasBeanWithUsedProperty(final Api.Type type) {
        if(type == null) {
            return false;
        }
        final Api.FluentList<Api.Type> initExpressionBeans = type.getInitExpressionBeans();
        for(final Api.Type beanType : initExpressionBeans) {
            final BeanContext beanContext = new BeanContext(beanType, 0, 5);
            final Api.SourceClass sourceClass = resolveBean(beanContext, beanType);
            if(sourceClass == null) {
                continue;
            }
            if(!sourceClass.getPreferredInitMethods().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Nullable
    public Api.SourceClass resolveBean(
            @Nullable final BeanContext beanContext, @Nullable final Api.Variable variable) {
        if(beanContext == null || variable == null) {
            return null;
        }
        return resolveBean(beanContext, variable.getType());
    }

    @Override
    @Nullable
    public Api.SourceClass resolveBean(
            @Nullable final BeanContext beanContext, @Nullable final Api.Type type) {
        if(beanContext == null || type == null) {
            return null;
        }
        return resolveBeanImpl(beanContext, type);
    }

    @Nullable
    private Api.SourceClass resolveBeanImpl(
            @NotNull final BeanContext beanContext, @NotNull final Api.Type type) {
        final String canonicalName = type.getCanonicalName();
        if(canonicalName == null) {
            return null;
        }
        final String canonicalText = type.getCanonicalText();
        final Map<String, WrappedSourceClass> canonicalNameToSourceClassesMap = beanContextToSourceClassesMap.get(beanContext);
        if(canonicalNameToSourceClassesMap == null) {
            // This is the first time we've seen this context pointer. This is a top level bean.
            // Check the permanent cache to see if we've resolved another top level bean with this exact context
            // information (context.minSetters, context.maxSetters, firstBeanCanonicalName). If we have, use it.
            final Map<String, WrappedSourceClass> permanentCanonicalNameToSourceClassesMap = permanentBeanContextToSourceClassesMap.get(beanContext);
            if(permanentCanonicalNameToSourceClassesMap != null) {
                // Reuse this bean hierarchy.
                beanContextToSourceClassesMap.put(beanContext, permanentCanonicalNameToSourceClassesMap);
                final WrappedSourceClass wrappedSourceClass = permanentCanonicalNameToSourceClassesMap.get(canonicalText);
                if(wrappedSourceClass != null) {
                    return wrappedSourceClass.getSourceClass();
                } else {
                    return null;
                }
            } else {
                // Otherwise, we need to compute the bean hierarchy for the first time.
                final Map<String, WrappedSourceClass> mapToUse = computeCanonicalNameToSourceClassesMap(type, beanContext);
                if(mapToUse == null) {
                    return null;
                }
                beanContextToSourceClassesMap.put(beanContext, mapToUse);
                permanentBeanContextToSourceClassesMap.put(beanContext, mapToUse);
                final WrappedSourceClass wrappedSourceClass = mapToUse.get(canonicalText);
                if(wrappedSourceClass != null) {
                    return wrappedSourceClass.getSourceClass();
                } else {
                    return null;
                }
            }
        } else {
            // We've already computed the hierarchy for this context. Look up the class for the canonical name and return it.
            final WrappedSourceClass wrappedSourceClass = canonicalNameToSourceClassesMap.get(canonicalText);
            if(wrappedSourceClass != null) {
                return wrappedSourceClass.getSourceClass();
            } else {
                return null;
            }
        }
    }

    @Nullable
    private Map<String, WrappedSourceClass> computeCanonicalNameToSourceClassesMap(
            @NotNull final Api.Type type, @NotNull final BeanContext beanContext) {
        final int minSetter = MathUtils.clamp(beanContext.getMinSettersToCall(), 0, 100_000);
        final int maxSetter = MathUtils.clamp(beanContext.getMaxSettersToCall(), 0, 100_000);
        final String topLevelBeanCanonicalName = type.getCanonicalName();
        if(topLevelBeanCanonicalName == null) {
            return null;
        }
        final Api.SourceClass topLevelBeanClass = computeSourceClass(topLevelBeanCanonicalName, InitStrategy.GeneralBeans);
        if(topLevelBeanClass == null) {
            return null;
        }
        if(!topLevelBeanClass.isDtoBean()) {
            return null;
        }
        // Compute the preferredInitMethods recursively.
        final Map<String, WrappedSourceClass> finalMap = new HashMap<>();
        final MutableInt numberOfInitMethodsCalled = new MutableInt(0);
        final Set<String> canonicalNamesInCallstack = new HashSet<>();
        for(final InitSetterIncludedDecider initSetterIncludedDecider : InitSetterDeciders) {
            final int setterLimitToUse = initSetterIncludedDecider.shouldUseMinSetterLimit() ? minSetter : maxSetter;
            canonicalNamesInCallstack.add(topLevelBeanCanonicalName);
            computeBeanMapRecursively(type, topLevelBeanClass, initSetterIncludedDecider, setterLimitToUse, finalMap, 0, canonicalNamesInCallstack, numberOfInitMethodsCalled);
            canonicalNamesInCallstack.remove(topLevelBeanCanonicalName);
        }
        sortBeanSetters(finalMap);
        return finalMap;
    }

    private void sortBeanSetters(final Map<String, WrappedSourceClass> finalMap) {
        for(final WrappedSourceClass wrappedSourceClass : finalMap.values()) {
            final Api.SourceClass sourceClass = wrappedSourceClass.getSourceClass();
            final Api.FluentList<Api.SourceClass> superClasses = sourceClass.getAllSuperClasses();
            final Api.FluentList<Api.Method> preferredInitMethods = sourceClass.getPreferredInitMethods();
            if(wrappedSourceClass.isRecognized()) {
                final SetterMethodSet setterMethodSet = new SetterMethodSet();
                // Add the preferred init setters.
                for(final Api.Method method : sourceClass.getPreferredInitSetters()) {
                    setterMethodSet.add(method);
                }
                // Add methods that were previously selected and also used.
                for(final Api.Method method : preferredInitMethods) {
                    if(!setterMethodSet.contains(method)) {
                        setterMethodSet.add(method);
                    }
                }
                // Add the JAXB methods.
                for(final Api.Method method : sourceClass.getMethods()) {
                    if(method.isJaxbListGetter()) {
                        setterMethodSet.add(method);
                    }
                }
                // Update the preferredInitMethods list.
                preferredInitMethods.clear();
                preferredInitMethods.addAll(setterMethodSet.values());
            } else {
                preferredInitMethods.sort((o1, o2) -> {
                    final MethodImpl methodImpl1 = (MethodImpl) o1;
                    final MethodImpl methodImpl2 = (MethodImpl) o2;
                    final int classIndex1 = computeClassIndex(sourceClass, superClasses, methodImpl1);
                    final int classIndex2 = computeClassIndex(sourceClass, superClasses, methodImpl2);
                    if(classIndex1 > classIndex2) {
                        // Higher class index means classIndex1 is a super type. Return -1 so it will come first.
                        return -1;
                    }
                    if(classIndex1 < classIndex2) {
                        return 1;
                    }
                    return Integer.compare(methodImpl1.getIndexInSourceClass(), methodImpl2.getIndexInSourceClass());
                });
            }
        }
    }

    private static int computeClassIndex(final Api.SourceClass sourceClass, final Api.FluentList<Api.SourceClass> superClasses, final MethodImpl method) {
        final Api.SourceClass containingClass = method.getContainingClass();
        if(containingClass == null) {
            // This shouldn't happen.
            return 0;
        }
        if(containingClass == sourceClass) {
            return 0;
        }
        final int indexInSupers = superClasses.indexOf(containingClass);
        if(indexInSupers == -1) {
            // This shouldn't happen.
            return 0;
        }
        return indexInSupers + 1;
    }

    private void computeBeanMapRecursively(
            final Api.Type type, final Api.SourceClass beanClass,
            final InitSetterIncludedDecider initSetterDecider,
            final int maxSettersToCall, final Map<String, WrappedSourceClass> finalMap, final int recursiveDepth,
            final Set<String> canonicalNamesInCallstack, final MutableInt numberOfSettersCalled) {
        final String canonicalName = type.getCanonicalName();
        final String canonicalText = type.getCanonicalText();
        if(canonicalName == null) {
            return;
        }
        final Api.SourceClass beanClassToUse;
        final SetterMethodSet existingCalledMethods;
        final boolean isUpdatingExistingClass;
        final WrappedSourceClass existingWrappedClass = finalMap.get(canonicalText);
        if(existingWrappedClass != null) {
            if(existingWrappedClass.getLastInitSetterDecider() == initSetterDecider) {
                return;
            }
            beanClassToUse = existingWrappedClass.getSourceClass();
            existingCalledMethods = existingWrappedClass.getCalledMethodOverloads();
            isUpdatingExistingClass = true;
        } else {
            beanClassToUse = beanClass;
            existingCalledMethods = SetterMethodSet.emptySet();
            isUpdatingExistingClass = false;
        }
        final boolean isRecognized = defaultTypesHolder.contains(canonicalName);
        // Determine the setters to invoke.
        final SetterMethodSet setterMethodsToCall = new SetterMethodSet();
        final Api.FluentList<Api.Method> preferredInitMethods = beanClassToUse.getPreferredInitMethods();
        if(!isUpdatingExistingClass) {
            preferredInitMethods.clear();
        }
        for(final Api.Method method : beanClassToUse.getAllNonObjectLowestOverrideMethods()) {
            final Api.Method existingCalledMethod = existingCalledMethods.getMethodWithSameNameOrField(method);
            if(existingCalledMethod != null) {
                // We've already called an overload of this method in a previous iteration (initMethodDecider).
                if(existingCalledMethod == method) {
                    // We've already called this method in a previous iteration. Add the method to our set so it
                    // will be processed again with the new decider.
                    setterMethodsToCall.add(method);
                }
                continue;
            }
            if(isRecognized) {
                final MethodImpl methodImpl = (MethodImpl) method;
                if(!methodImpl.isSimpleSetter() && !method.isJaxbListGetter()) {
                    continue;
                }
                if(methodImpl.isDeprecated()) {
                    continue;
                }
                if(beanClassToUse.getPreferredInitSetters().contains(method)
                        || methodImpl.isJaxbListGetter()
                        || methodImpl.getPropertyIsUsedInSource() || methodImpl.getPropertyIsUsedInStaticHelpers()) {
                    setterMethodsToCall.add(method);
                }
                continue;
            }
            if(!isPotentialCandidate(method)) {
                continue;
            }
            if(!initSetterDecider.isSetterIncluded(method)) {
                continue;
            }
            if(method.getContainingClass() != beanClassToUse && !isUsedAnywhere(method)) {
                // Do not call super bean methods that are not used.
                continue;
            }
            if(setterMethodsToCall.contains(method)) {
                final Api.Method preferredSetter = getPreferredSetter(method, setterMethodsToCall.getMethodWithSameNameOrField(method));
                setterMethodsToCall.add(preferredSetter);
            } else {
                setterMethodsToCall.add(method);
            }
        }

        // Add setters to the list.
        // Invoke recursive if needed (using type substitution if needed).
        //   Check the callstack, limits, etc.
        final int numberOfMethodsCalledBeforeThisBean = numberOfSettersCalled.getValue();
        for(final Api.Method method : setterMethodsToCall.values()) {
            final boolean methodCalledInPreviousRun = method == existingCalledMethods.getMethodWithSameNameOrField(method);
            final Api.Type possibleBeanType = getBeanTypeForInitMethod(type, method);
            if(possibleBeanType == null) {
                // This shouldn't happen. If it does, just skip this setter.
                continue;
            }
            // If we're at any of the limits, continue.
            if(recursiveDepth >= MaxRecursiveBeanDepth || numberOfSettersCalled.getValue() >= maxSettersToCall) {
                break;
            }
            if(possibleBeanType.isDtoBean()) {
                final String beanCanonicalName = possibleBeanType.getCanonicalName();
                final String beanCanonicalText = possibleBeanType.getCanonicalText();
                if(beanCanonicalName == null || canonicalNamesInCallstack.contains(beanCanonicalName)) {
                    continue;
                }
                final WrappedSourceClass alreadyVisitedBean = finalMap.get(beanCanonicalText);
                if(alreadyVisitedBean != null) {
                    if(!methodCalledInPreviousRun) {
                        numberOfSettersCalled.increment();
                    }
                    if(alreadyVisitedBean.getLastInitSetterDecider() == initSetterDecider) {
                        // We've already visited this bean with the current initSetterDecider (priority level).
                        if(!methodCalledInPreviousRun) {
                            numberOfSettersCalled.add(alreadyVisitedBean.getNumberOfBeanSettersCalled());
                            preferredInitMethods.add(method);
                        }
                    } else {
                        // Add the existing bean setter methods.
                        // Additional bean setter methods will be accounted for when the recursive call
                        // increments the counter.
                        if(!methodCalledInPreviousRun) {
                            numberOfSettersCalled.add(alreadyVisitedBean.getNumberOfBeanSettersCalled());
                        }
                        canonicalNamesInCallstack.add(beanCanonicalName);
                        computeBeanMapRecursively(
                                possibleBeanType, alreadyVisitedBean.getSourceClass(), initSetterDecider,
                                maxSettersToCall, finalMap, recursiveDepth + 1,
                                canonicalNamesInCallstack, numberOfSettersCalled);
                        canonicalNamesInCallstack.remove(beanCanonicalName);
                        if(!methodCalledInPreviousRun) {
                            preferredInitMethods.add(method);
                        }
                    }
                } else {
                    // This is the first time we've seen this bean.
                    final Api.SourceClass beanClassForProperty = computeSourceClass(beanCanonicalName, InitStrategy.GeneralBeans);
                    if(beanClassForProperty == null) {
                        // This shouldn't happen.
                        continue;
                    }
                    if(!methodCalledInPreviousRun) {
                        numberOfSettersCalled.increment();
                    }
                    canonicalNamesInCallstack.add(beanCanonicalName);
                    computeBeanMapRecursively(
                            possibleBeanType, beanClassForProperty, initSetterDecider,
                            maxSettersToCall, finalMap, recursiveDepth + 1,
                            canonicalNamesInCallstack, numberOfSettersCalled);
                    canonicalNamesInCallstack.remove(beanCanonicalName);
                    if(!methodCalledInPreviousRun) {
                        preferredInitMethods.add(method);
                    }
                }
            } else if(!possibleBeanType.getInitExpressionBeans().isEmpty()) {
                if(!methodCalledInPreviousRun) {
                    numberOfSettersCalled.increment();
                }
                for(final Api.Type initExpressionBeanType : possibleBeanType.getInitExpressionBeans()) {
                    final String beanCanonicalName = initExpressionBeanType.getCanonicalName();
                    final String beanCanonicalText = initExpressionBeanType.getCanonicalText();
                    if(beanCanonicalName == null || canonicalNamesInCallstack.contains(beanCanonicalName)) {
                        continue;
                    }
                    final WrappedSourceClass alreadyVisitedBean = finalMap.get(beanCanonicalText);
                    if(alreadyVisitedBean != null) {
                        if(alreadyVisitedBean.getLastInitSetterDecider() == initSetterDecider) {
                            // We've already visited this bean with the current initSetterDecider (priority level).
                            if(!methodCalledInPreviousRun) {
                                numberOfSettersCalled.add(alreadyVisitedBean.getNumberOfBeanSettersCalled());
                            }
                        } else {
                            // Add the existing bean setter methods.
                            // Additional bean setter methods will be accounted for when the recursive call
                            // increments the counter.
                            if(!methodCalledInPreviousRun) {
                                numberOfSettersCalled.add(alreadyVisitedBean.getNumberOfBeanSettersCalled());
                            }
                            canonicalNamesInCallstack.add(beanCanonicalName);
                            computeBeanMapRecursively(
                                    initExpressionBeanType, alreadyVisitedBean.getSourceClass(), initSetterDecider,
                                    maxSettersToCall, finalMap, recursiveDepth + 1,
                                    canonicalNamesInCallstack, numberOfSettersCalled);
                            canonicalNamesInCallstack.remove(beanCanonicalName);
                        }
                    } else {
                        // This is the first time we've seen this bean.
                        final Api.SourceClass beanClassForProperty = computeSourceClass(beanCanonicalName, InitStrategy.GeneralBeans);
                        if(beanClassForProperty == null) {
                            // This shouldn't happen.
                            continue;
                        }
                        canonicalNamesInCallstack.add(beanCanonicalName);
                        computeBeanMapRecursively(
                                initExpressionBeanType, beanClassForProperty, initSetterDecider,
                                maxSettersToCall, finalMap, recursiveDepth + 1,
                                canonicalNamesInCallstack, numberOfSettersCalled);
                        canonicalNamesInCallstack.remove(beanCanonicalName);
                    }
                }
                if(!methodCalledInPreviousRun) {
                    preferredInitMethods.add(method);
                }
            } else {
                // The type is not a bean and does not have beans in its init expression.
                if(!methodCalledInPreviousRun) {
                    numberOfSettersCalled.increment();
                    preferredInitMethods.add(method);
                }
            }
        }

        final int numberOfMethodsCalledDuringThisBean = numberOfSettersCalled.getValue() - numberOfMethodsCalledBeforeThisBean;

        // Otherwise, create a new WrappedSourceClass with this info and add it to our map.
        if(existingWrappedClass != null) {
            // We're updating an existing bean, update its numberOfMethodsCalled, calledMethods set, etc.
            existingWrappedClass.setNumberOfBeanSettersCalled(existingWrappedClass.getNumberOfBeanSettersCalled() + numberOfMethodsCalledDuringThisBean);
            existingWrappedClass.addCalledSetters(setterMethodsToCall);
            existingWrappedClass.setLastInitSetterDecider(initSetterDecider);
        } else {
            final WrappedSourceClass wrappedSourceClass = new WrappedSourceClass(beanClassToUse, numberOfMethodsCalledDuringThisBean, isRecognized, setterMethodsToCall, initSetterDecider);
            finalMap.put(canonicalText, wrappedSourceClass);
        }
    }

    private static boolean isUsedAnywhere(final Api.Method method) {
        final MethodImpl methodImpl = (MethodImpl) method;
        return methodImpl.isCalledInSource() || methodImpl.getPropertyIsUsedInSource() || methodImpl.isCalledInStaticHelpers() || methodImpl.getPropertyIsUsedInStaticHelpers();
    }

    @Nullable
    private Api.Type getBeanTypeForInitMethod(final Api.Type parentType, final Api.Method initMethod) {
        Api.Type typeToUse = null;
        if(initMethod.isSetter()) {
            final Api.Variable firstParam = initMethod.getParameters().first();
            if(firstParam != null) {
                typeToUse = firstParam.getType();
            }
        } else if(initMethod.isJaxbListGetter()) {
            typeToUse = initMethod.getReturnType();
        }

        if(typeToUse == null) {
            return null;
        }
        final Api.FluentList<Api.Type> parentTypeParams = parentType.getParameters();
        if(typeToUse.isGeneric() && parentTypeParams.size() == 1 && !parentTypeParams.get(0).isGeneric()) {
            typeToUse = parentTypeParams.get(0);
        }

        return typeToUse;
    }

    private Api.Method getPreferredSetter(final Api.Method method, final Api.Method otherMethod) {
        final MethodImpl methodImpl = (MethodImpl) method;
        final MethodImpl otherMethodImpl = (MethodImpl) otherMethod;
        // Prioritize methods called in the source class first.
        if(methodImpl.isCalledInSource()) {
            if(!otherMethodImpl.isCalledInSource()) {
                return methodImpl;
            }
            // They're both called in the source.
            if(allParamsRecognizedTypes(methodImpl)) {
                if(!allParamsRecognizedTypes(otherMethodImpl)) {
                    return methodImpl;
                }
                // They both have recognized params.
                return methodImpl;
            } else if(allParamsRecognizedTypes(otherMethodImpl)) {
                return otherMethodImpl;
            }
        } else if(otherMethodImpl.isCalledInSource()) {
            return otherMethodImpl;
        }

        // Then prioritize methods for properties accessed in the source class.
        if(methodImpl.getPropertyIsUsedInSource()) {
            if(!otherMethodImpl.getPropertyIsUsedInSource()) {
                return methodImpl;
            }
            // They're both called in the source.
            if(allParamsRecognizedTypes(methodImpl)) {
                if(!allParamsRecognizedTypes(otherMethodImpl)) {
                    return methodImpl;
                }
                // They both have recognized params.
                return methodImpl;
            } else if(allParamsRecognizedTypes(otherMethodImpl)) {
                return otherMethodImpl;
            }
        } else if(otherMethodImpl.getPropertyIsUsedInSource()) {
            return otherMethodImpl;
        }

        // Then prioritize methods called in static helper methods.
        if(methodImpl.isCalledInStaticHelpers()) {
            if(!otherMethodImpl.isCalledInStaticHelpers()) {
                return methodImpl;
            }
            // They're both called in the source.
            if(allParamsRecognizedTypes(methodImpl)) {
                if(!allParamsRecognizedTypes(otherMethodImpl)) {
                    return methodImpl;
                }
                // They both have recognized params.
                return methodImpl;
            } else if(allParamsRecognizedTypes(otherMethodImpl)) {
                return otherMethodImpl;
            }
        } else if(otherMethodImpl.isCalledInStaticHelpers()) {
            return otherMethodImpl;
        }

        // Then prioritize methods for properties used in static helper methods.
        if(methodImpl.getPropertyIsUsedInStaticHelpers()) {
            if(!otherMethodImpl.getPropertyIsUsedInStaticHelpers()) {
                return methodImpl;
            }
            // They're both called in the source.
            if(allParamsRecognizedTypes(methodImpl)) {
                if(!allParamsRecognizedTypes(otherMethodImpl)) {
                    return methodImpl;
                }
                // They both have recognized params.
                return methodImpl;
            } else if(allParamsRecognizedTypes(otherMethodImpl)) {
                return otherMethodImpl;
            }
        } else if(otherMethodImpl.getPropertyIsUsedInStaticHelpers()) {
            return otherMethodImpl;
        }

        if(allParamsRecognizedTypes(methodImpl)) {
            if(!allParamsRecognizedTypes(otherMethodImpl)) {
                return methodImpl;
            }
            // They both have recognized params.
            return methodImpl;
        } else if(allParamsRecognizedTypes(otherMethodImpl)) {
            return otherMethodImpl;
        }
        return methodImpl;
    }

    private boolean isPotentialCandidate(final Api.Method method) {
        if(method.isDeprecated()) {
            return false;
        }
        if(!method.isSetter() && !method.isJaxbListGetter()) {
            return false;
        }
        return true;
    }

    @Nullable
    private Api.SourceClass computeSourceClass(@NotNull final String canonicalName, final InitStrategy initStrategy) {
        final PsiClass psiClass = CompiledUtils.getClassWithSourceCode(psiFacade.findClass(canonicalName, testSourcesRoot.getResolveScope()));
        if(psiClass == null || psiClass.getQualifiedName() == null || psiClass.getName() == null) {
            return null;
        }

        final PsiFile containingFile = psiClass.getContainingFile();
        if(containingFile instanceof PsiJavaFile) {
            final PsiClass[] classesInJavaFile = ((PsiJavaFile) containingFile).getClasses();
            if(classesInJavaFile.length == 0) {
                return null;
            }
            return psiToTemplateDataConverter.createSourceClass((PsiJavaFile) containingFile, psiClass, initStrategy);
        }
        return null;
    }
}
