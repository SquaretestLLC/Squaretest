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
package com.squaretest.generation.dependencyinteraction;

import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassReferenceType;
import com.intellij.psi.util.InheritanceUtil;
import com.intellij.psi.util.PropertyUtil;
import com.intellij.psi.util.PsiUtil;
import com.squaretest.generation.CompiledUtils;
import com.squaretest.generation.defaulttypes.WildcardInfo;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.squaretest.generation.CompiledUtils.getClassWithSourceCode;
import static com.squaretest.generation.CompiledUtils.getMethodWithSourceCode;

public class DependencyInteractionCollectorUtils {

    private static final String[] RecognizedClassesWithStaticMethodsThatInvokeExecutor = new String[]{
            "java.util.concurrent.CompletableFuture",
            "java.util.concurrent.CompletionStage",
            "com.google.common.util.concurrent.Futures",
            "com.google.api.core.ApiFutures"
    };
    private static final String[] FunctionCanonicalNamePrefixes = new String[]{
            "java.util.function",
            "io.reactivex.functions",
            "io.reactivex.rxjava3.functions"
    };
    private static final String[] AsyncCanonicalNames = new String[]{
            "java.util.concurrent.Callable",
            "java.lang.Runnable",
            "io.reactivex.Observer",
            "com.google.common.base.Function",
            "com.google.common.base.Supplier",
            "com.google.common.util.concurrent.FutureCallback"
    };

    private DependencyInteractionCollectorUtils() {
    }

    static Map<PsiMethod, PsiMethod> computeSuperMethodsToLowestOverrideMap(final PsiClass psiClass, final Set<PsiClass> superClasses) {
        final Map<PsiMethod, PsiMethod> superMethodsToLowestOverride = new IdentityHashMap<>();
        addSuperMethodsToLowestOverride(psiClass, superMethodsToLowestOverride);
        for(final PsiClass superClass : superClasses) {
            addSuperMethodsToLowestOverride(superClass, superMethodsToLowestOverride);
        }
        return superMethodsToLowestOverride;
    }

    private static void addSuperMethodsToLowestOverride(final PsiClass theClass, final Map<PsiMethod, PsiMethod> superMethodsToLowestOverride) {
        for(final PsiMethod method : theClass.getMethods()) {
            if(superMethodsToLowestOverride.containsKey(method)) {
                // This method is already in the map. That means it is overridden by a previously seen method.
                // It and its super methods were already added to the map. Skip it.
                continue;
            }
            addSuperMethodsForMethod(method, superMethodsToLowestOverride);
        }
    }

    private static void addSuperMethodsForMethod(final PsiMethod method, final Map<PsiMethod, PsiMethod> superMethodsToLowestOverride) {
        for(final PsiMethod superMethod : method.findSuperMethods()) {
            addSuperMethodsForMethodRecursive(method, getMethodWithSourceCode(superMethod), superMethodsToLowestOverride);
        }
    }

    private static void addSuperMethodsForMethodRecursive(final PsiMethod method, final PsiMethod superMethod, final Map<PsiMethod, PsiMethod> superMethodsToLowestOverride) {
        if(superMethodsToLowestOverride.containsKey(superMethod)) {
            return;
        }
        superMethodsToLowestOverride.put(superMethod, method);
        for(final PsiMethod otherSuperMethod : superMethod.findSuperMethods()) {
            addSuperMethodsForMethodRecursive(method, getMethodWithSourceCode(otherSuperMethod), superMethodsToLowestOverride);
        }
    }

    /**
     * Returns the set of super classes and interfaces for the given {@link PsiClass}. This is a LinkedHashSet containing
     * the items in DFS order. All classes in the type hierarchy WILL come before all interfaces. The classes will appear
     * in order of: classes closest to the {@link PsiClass} will come first.
     *
     * @param psiClass the class to compute super classes for.
     * @return A LinkedHashSet containing the super classes and interfaces.
     */
    public static Set<PsiClass> computeSuperClasses(final PsiClass psiClass) {
        // Get the set of super classes.
        final Set<PsiClass> superClasses = new LinkedHashSet<>();
        InheritanceUtil.getSuperClasses(psiClass, superClasses, true);

        // If any of the super classes are compiled and the source code is available, use the PsiClass with the source
        // code. This way we can inspect the methods to find dependency interactions.
        final Set<PsiClass> classesWithSources = new LinkedHashSet<>();
        for(final PsiClass superClass : superClasses) {
            final PsiClass classToUse = getClassWithSourceCode(superClass);
            classesWithSources.add(classToUse);
        }
        return classesWithSources;
    }

    /**
     * Copied from {@link com.siyeh.ig.psiutils.MethodCallUtils#hasSuperQualifier(PsiMethodCallExpression)}.
     *
     * @param expression the method call expression
     * @return whether or not the call expression has a super qualifier.
     */
    static boolean hasSuperQualifier(@NotNull PsiMethodCallExpression expression) {
        return PsiUtil.skipParenthesizedExprDown(expression.getMethodExpression().getQualifierExpression()) instanceof PsiSuperExpression;
    }

    static boolean hasSuperQualifier(@NotNull PsiMethodReferenceExpression expression) {
        return PsiUtil.skipParenthesizedExprDown(expression.getQualifierExpression()) instanceof PsiSuperExpression;
    }

    public static Set<PsiFile> computeSuperClassFileSet(final PsiClass psiClass, final Set<PsiClass> superClasses) {
        final Set<PsiFile> ret = SetUtils.newIdentityHashSet();
        ret.add(psiClass.getContainingFile());
        for(final PsiClass superClass : superClasses) {
            ret.add(superClass.getContainingFile());
        }
        return ret;
    }

    @NotNull
    static List<InternalDependencyInteraction> determineDependencyInteractionsForStaticMethod(
            @NotNull final Set<PsiClass> sourceAndSupers,
            @NotNull final PsiClass sourceClass,
            @NotNull final PsiMethod methodContainingCallstackNode,
            @NotNull final PsiMethod calledMethod,
            @NotNull final PsiMethodCallExpression methodCallExpression, final MutableInt sequenceId) {
        InternalDependencyInteraction internalDependencyInteraction = tryGetDiForClassesThatInvokeExecutor(sourceAndSupers, sourceClass, methodContainingCallstackNode, calledMethod, methodCallExpression, sequenceId);
        if(internalDependencyInteraction != null) {
            return Collections.singletonList(internalDependencyInteraction);
        }

        return tryGetDisForClassesThatInvokeClockMethods(sourceAndSupers, methodContainingCallstackNode, calledMethod, methodCallExpression, sequenceId);
    }

    @Nullable
    private static InternalDependencyInteraction tryGetDiForClassesThatInvokeExecutor(@NotNull final Set<PsiClass> sourceAndSupers, @NotNull final PsiClass sourceClass, @NotNull final PsiMethod methodContainingCallstackNode, @NotNull final PsiMethod calledMethod, @NotNull final PsiMethodCallExpression methodCallExpression, final MutableInt sequenceId) {
        final PsiClass calledMethodContainingClass = calledMethod.getContainingClass();
        // Bail out if we can't resolve the called method's containing class.
        if(calledMethodContainingClass == null) {
            return null;
        }
        final boolean isRecognizedClass = StringUtils.equalsAny(calledMethodContainingClass.getQualifiedName(), RecognizedClassesWithStaticMethodsThatInvokeExecutor);

        // Determine the Executor member that is passed to the static method.
        final PsiField executorMemberUsed = getExecutorMemberUsedInMethodCall(sourceAndSupers, calledMethod, methodCallExpression);
        if(executorMemberUsed == null) {
            return null;
        }

        // Find the lowest override for the "void execute(Runnable r)" method.
        final PsiMethod executeMethod = findExecuteMethod(executorMemberUsed);
        if(executeMethod == null) {
            return null;
        }

        if(!isRecognizedClass && !returnsFutureOrStage(calledMethod)) {
            return null;
        }

        // Create a Runnable type to use for the InternalMethodCallExpression we need to create.
        final JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(sourceClass.getProject());
        final PsiElementFactory elementFactory = javaPsiFacade.getElementFactory();
        final PsiClass runnableClass = javaPsiFacade.findClass("java.lang.Runnable", sourceClass.getResolveScope());
        if(runnableClass == null) {
            return null;
        }
        final PsiClassType runnableType = elementFactory.createType(runnableClass);

        // Build and return the dependency interaction.
        final InternalMethodCallExpression internalMethodCallExpression = new InternalMethodCallExpression(Collections.singletonList(runnableType), PsiTypes.voidType(), methodCallExpression);
        final Node newNode = new Node(methodContainingCallstackNode, methodCallExpression, methodCallExpression, sequenceId.getAndIncrement(), null);
        if(nodePassesThroughMethod(methodContainingCallstackNode, newNode)) {
            return null;
        }
        final List<Node> callstacks = new ArrayList<>(DependencyInteractionCollectorImpl.MaxCallstacksToCollect);
        callstacks.add(newNode);
        return new InternalDependencyInteraction(executorMemberUsed, executeMethod, internalMethodCallExpression, false, callstacks);
    }

    private static boolean returnsFutureOrStage(final PsiMethod calledMethod) {
        final PsiType returnType = calledMethod.getReturnType();
        return InheritanceUtil.isInheritor(returnType, "java.util.concurrent.Future") || InheritanceUtil.isInheritor(returnType, "java.util.concurrent.CompletionStage");
    }

    private static boolean returnsInstant(final PsiMethod method) {
        final PsiType returnType = method.getReturnType();
        final PsiClass psiClass = PsiUtil.resolveClassInType(returnType);
        if(psiClass == null) {
            return false;
        }
        return StringUtils.equals(psiClass.getQualifiedName(), "java.time.Instant");
    }

    private static boolean returnsZoneId(final PsiMethod method) {
        final PsiType returnType = method.getReturnType();
        return InheritanceUtil.isInheritor(returnType, "java.time.ZoneId");
    }

    @NotNull
    private static List<InternalDependencyInteraction> tryGetDisForClassesThatInvokeClockMethods(@NotNull final Set<PsiClass> sourceAndSupers, @NotNull final PsiMethod methodContainingCallstackNode, @NotNull final PsiMethod calledMethod, @NotNull final PsiMethodCallExpression methodCallExpression, final MutableInt sequenceId) {
        final PsiClass calledMethodContainingClass = calledMethod.getContainingClass();
        // Bail out if we can't resolve the called method's containing class.
        if(calledMethodContainingClass == null) {
            return Collections.emptyList();
        }

        // Check the containing class and bail out if it is not a recognized class that invokes clock methods.
        // Do this check here to avoid doing more expensive checks later on.
        final String methodContainingClassQualifiedName = calledMethodContainingClass.getQualifiedName();
        if(!StringUtils.equalsAny(methodContainingClassQualifiedName,
                "java.time.LocalDate", "java.time.LocalDateTime", "java.time.LocalTime",
                "java.time.OffsetDateTime", "java.time.OffsetTime", "java.time.ZonedDateTime", "java.time.Instant")) {
            return Collections.emptyList();
        }

        // Determine the Clock member that is passed to the static method.
        final PsiField clockMember = getClockMemberUsedInMethodCall(sourceAndSupers, calledMethod, methodCallExpression);
        if(clockMember == null) {
            return Collections.emptyList();
        }
        List<PsiMethod> methodsToAdd = new ArrayList<>();
        if(StringUtils.equals(methodContainingClassQualifiedName, "java.time.Instant") && StringUtils.equals(calledMethod.getName(), "now")) {
            final PsiMethod instantMethod = findInstantMethod(clockMember);
            if(instantMethod == null) {
                return Collections.emptyList();
            }
            methodsToAdd.add(instantMethod);
        } else if(StringUtils.equalsAny(methodContainingClassQualifiedName,
                "java.time.LocalDate", "java.time.LocalDateTime", "java.time.LocalTime",
                "java.time.OffsetDateTime", "java.time.OffsetTime", "java.time.ZonedDateTime")
                && StringUtils.equals(calledMethod.getName(), "now")) {
            final PsiMethod instantMethod = findInstantMethod(clockMember);
            if(instantMethod == null) {
                return Collections.emptyList();
            }
            final PsiMethod getZoneMethod = findGetZoneMethod(clockMember);
            if(getZoneMethod == null) {
                return Collections.emptyList();
            }
            methodsToAdd.add(instantMethod);
            methodsToAdd.add(getZoneMethod);
        }

        if(methodsToAdd.isEmpty()) {
            return Collections.emptyList();
        }

        final List<InternalDependencyInteraction> ret = new ArrayList<>(methodsToAdd.size());
        // Build and return the dependency interactions.
        for(final PsiMethod methodToAdd : methodsToAdd) {
            final InternalMethodCallExpression internalMethodCallExpression = new InternalMethodCallExpression(Collections.emptyList(), methodToAdd.getReturnType(), methodCallExpression);
            final Node newNode = new Node(methodContainingCallstackNode, methodCallExpression, methodCallExpression, sequenceId.getAndIncrement(), null);
            if(nodePassesThroughMethod(methodContainingCallstackNode, newNode)) {
                return Collections.emptyList();
            }
            final List<Node> callstacks = new ArrayList<>(DependencyInteractionCollectorImpl.MaxCallstacksToCollect);
            callstacks.add(newNode);
            ret.add(new InternalDependencyInteraction(clockMember, methodToAdd, internalMethodCallExpression, false, callstacks));
        }
        return ret;
    }

    @Nullable
    static InternalDependencyInteraction determineDependencyInteractionsForFutureInstanceMethod(
            @NotNull final Set<PsiClass> sourceAndSupers,
            @NotNull final PsiClass sourceClass,
            @NotNull final PsiMethod methodContainingCallstackNode,
            @NotNull final PsiMethod calledMethod,
            @NotNull final PsiMethodCallExpression methodCallExpression, final MutableInt sequenceId) {
        final PsiClass calledMethodContainingClass = calledMethod.getContainingClass();
        // Bail out if we can't resolve the called method's containing class.
        if(calledMethodContainingClass == null) {
            return null;
        }
        // Bail out if the called method's containing class is not one of the classes that can contain DIs.
        if(!InheritanceUtil.isInheritor(calledMethodContainingClass, "java.util.concurrent.Future")) {
            return null;
        }

        // Determine the Executor member that is passed to the method.
        final PsiField executorMemberUsed = getExecutorMemberUsedInMethodCall(sourceAndSupers, calledMethod, methodCallExpression);
        if(executorMemberUsed == null) {
            return null;
        }

        // Find the lowest override for the "void execute(Runnable r)" method.
        final PsiMethod executeMethod = findExecuteMethod(executorMemberUsed);
        if(executeMethod == null) {
            return null;
        }

        // Create a Runnable type to use for the InternalMethodCallExpression we need to create.
        final JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(sourceClass.getProject());
        final PsiElementFactory elementFactory = javaPsiFacade.getElementFactory();
        final PsiClass runnableClass = javaPsiFacade.findClass("java.lang.Runnable", sourceClass.getResolveScope());
        if(runnableClass == null) {
            return null;
        }
        final PsiClassType runnableType = elementFactory.createType(runnableClass);

        // Build and return the dependency interaction.
        final InternalMethodCallExpression internalMethodCallExpression = new InternalMethodCallExpression(Collections.singletonList(runnableType), PsiTypes.voidType(), methodCallExpression);
        final Node newNode = new Node(methodContainingCallstackNode, methodCallExpression, methodCallExpression, sequenceId.getAndIncrement(), null);
        if(nodePassesThroughMethod(methodContainingCallstackNode, newNode)) {
            // Only add the node if it does not circle back around and come into the source method.
            return null;
        }
        final List<Node> callstacks = new ArrayList<>(DependencyInteractionCollectorImpl.MaxCallstacksToCollect);
        callstacks.add(newNode);
        return new InternalDependencyInteraction(executorMemberUsed, executeMethod, internalMethodCallExpression, false, callstacks);
    }

    @Nullable
    private static PsiMethod findExecuteMethod(@NotNull final PsiField executorMemberUsed) {
        final PsiType memberType = executorMemberUsed.getType();
        final PsiClass memberClass = PsiUtil.resolveClassInType(memberType);
        if(memberClass == null) {
            return null;
        }
        final PsiMethod[] possibleExecuteMethods = memberClass.findMethodsByName("execute", true);
        for(final PsiMethod possibleExecuteMethod : possibleExecuteMethods) {
            final PsiParameter[] parameters = possibleExecuteMethod.getParameterList().getParameters();
            final PsiType possibleExecuteMethodReturnType = possibleExecuteMethod.getReturnType();
            if((possibleExecuteMethodReturnType != null && PsiTypes.voidType().equals(possibleExecuteMethodReturnType))
                    && parameters.length == 1
                    && isRunnable(parameters[0])) {
                return possibleExecuteMethod;
            }
        }
        return null;
    }

    @Nullable
    private static PsiMethod findInstantMethod(@NotNull final PsiField clockMember) {
        final PsiType memberType = clockMember.getType();
        final PsiClass memberClass = PsiUtil.resolveClassInType(memberType);
        if(memberClass == null) {
            return null;
        }
        final PsiMethod[] possibleInstantMethods = memberClass.findMethodsByName("instant", true);
        for(final PsiMethod possibleInstantMethod : possibleInstantMethods) {
            final PsiParameter[] parameters = possibleInstantMethod.getParameterList().getParameters();
            if(parameters.length == 0 && !possibleInstantMethod.hasModifierProperty(PsiModifier.STATIC) && returnsInstant(possibleInstantMethod)) {
                return possibleInstantMethod;
            }
        }
        return null;
    }

    private static PsiMethod findGetZoneMethod(final PsiField clockMember) {
        final PsiType memberType = clockMember.getType();
        final PsiClass memberClass = PsiUtil.resolveClassInType(memberType);
        if(memberClass == null) {
            return null;
        }
        final PsiMethod[] possibleInstantMethods = memberClass.findMethodsByName("getZone", true);
        for(final PsiMethod possibleInstantMethod : possibleInstantMethods) {
            final PsiParameter[] parameters = possibleInstantMethod.getParameterList().getParameters();
            if(parameters.length == 0 && !possibleInstantMethod.hasModifierProperty(PsiModifier.STATIC) && returnsZoneId(possibleInstantMethod)) {
                return possibleInstantMethod;
            }
        }
        return null;
    }

    private static boolean isRunnable(@NotNull final PsiParameter parameter) {
        return InheritanceUtil.isInheritor(parameter.getType(), "java.lang.Runnable");
    }

    @Nullable
    private static PsiField getExecutorMemberUsedInMethodCall(
            @NotNull final Set<PsiClass> sourceAndSupers,
            @NotNull final PsiMethod calledMethod,
            @NotNull final PsiMethodCallExpression methodCallExpression) {
        final PsiParameter[] formalParams = calledMethod.getParameterList().getParameters();
        final PsiExpression[] argumentExpressions = methodCallExpression.getArgumentList().getExpressions();
        for(int i = 0; i < formalParams.length && i < argumentExpressions.length; i++) {
            final PsiClass formalParamClass = PsiUtil.resolveClassInType(formalParams[i].getType());
            // If the formal param's class could not be resolved or it is not an Executor, continue.
            if(formalParamClass == null || !StringUtils.equals(formalParamClass.getQualifiedName(), "java.util.concurrent.Executor")) {
                continue;
            }
            // The formal param is an executor. Determine if the actual param is a member of the source class.
            final PsiField member = getSourceClassMemberThisExpressionResolvesTo(sourceAndSupers, argumentExpressions[i]);
            if(member != null) {
                return member;
            }
        }
        return null;
    }

    @Nullable
    private static PsiField getClockMemberUsedInMethodCall(
            @NotNull final Set<PsiClass> sourceAndSupers,
            @NotNull final PsiMethod calledMethod,
            @NotNull final PsiMethodCallExpression methodCallExpression) {
        final PsiParameter[] formalParams = calledMethod.getParameterList().getParameters();
        final PsiExpression[] argumentExpressions = methodCallExpression.getArgumentList().getExpressions();
        for(int i = 0; i < formalParams.length && i < argumentExpressions.length; i++) {
            if(!InheritanceUtil.isInheritor(formalParams[i].getType(), "java.time.Clock")) {
                continue;
            }
            final PsiField member = getSourceClassMemberThisExpressionResolvesTo(sourceAndSupers, argumentExpressions[i]);
            if(member != null) {
                return member;
            }
        }
        return null;
    }

    @Nullable
    static PsiField getSourceClassMemberThisExpressionResolvesTo(final Set<PsiClass> sourceAndSupers, final PsiExpression psiExpression) {
        PsiField dependency = null;
        if(psiExpression instanceof PsiReferenceExpression) {
            final PsiElement resolvedQualifier = ((PsiReferenceExpression) psiExpression).resolve();
            if(resolvedQualifier instanceof PsiField) {
                final PsiField resolvedFieldQualifier = CompiledUtils.getFieldWithSourceCode((PsiField) resolvedQualifier);
                if(sourceAndSupers.contains(resolvedFieldQualifier.getContainingClass()) && !resolvedFieldQualifier.hasModifierProperty(
                        PsiModifier.STATIC)) {
                    // The field is defined in this class. This is a dependency interaction.
                    dependency = resolvedFieldQualifier;
                }
            }
        } else if(psiExpression instanceof PsiMethodCallExpression) {
            // If this is a getter method call the corresponding field is the dependency.
            final PsiMethod calledMethod = ((PsiMethodCallExpression) psiExpression).resolveMethod();
            if(calledMethod != null) {
                final PsiMethod resolvedMethodQualifier = CompiledUtils.getMethodWithSourceCode(calledMethod);
                if(sourceAndSupers.contains(resolvedMethodQualifier.getContainingClass()) && !resolvedMethodQualifier.hasModifierProperty(PsiModifier.STATIC)) {
                    final PsiField field = PropertyUtil.getFieldOfGetter(resolvedMethodQualifier);
                    if(field != null) {
                        final PsiField resolvedFieldQualifier = CompiledUtils.getFieldWithSourceCode(field);
                        if(sourceAndSupers.contains(resolvedFieldQualifier.getContainingClass()) && !resolvedFieldQualifier.hasModifierProperty(PsiModifier.STATIC)) {
                            dependency = resolvedFieldQualifier;
                        }
                    }
                }
            }
        }
        return dependency;
    }

    static List<PsiExpression> getArgsToProcessFirst(final PsiMethodCallExpression methodCallExpression) {
        // Process the arg first if it is not a runnable, callable or other async type.
        final PsiMethod calledMethod = getMethodWithSourceCode(methodCallExpression.resolveMethod());
        if(calledMethod == null) {
            return Collections.emptyList();
        }
        final PsiClass containingClass = calledMethod.getContainingClass();
        if(containingClass == null) {
            return Collections.emptyList();
        }
        final PsiParameter[] formalParams = calledMethod.getParameterList().getParameters();
        final PsiExpression[] actualParams = methodCallExpression.getArgumentList().getExpressions();
        final List<PsiExpression> ret = new ArrayList<>(formalParams.length);
        for(int i = 0; i < formalParams.length && i < actualParams.length; i++) {
            final PsiParameter formalParam = formalParams[i];
            final PsiExpression actualParam = actualParams[i];
            if(isParamCalledFirst(calledMethod, formalParam.getType())) {
                ret.add(actualParam);
            }
        }
        return ret;
    }

    public static List<PsiExpression> getArgsToProcessLast(final PsiMethodCallExpression methodCallExpression) {
        // Process the arg first if it is not a runnable, callable or other async type.
        final PsiMethod calledMethod = getMethodWithSourceCode(methodCallExpression.resolveMethod());
        if(calledMethod == null) {
            return Collections.emptyList();
        }
        final PsiClass containingClass = calledMethod.getContainingClass();
        if(containingClass == null) {
            return Collections.emptyList();
        }
        final PsiParameter[] formalParams = calledMethod.getParameterList().getParameters();
        final PsiExpression[] actualParams = methodCallExpression.getArgumentList().getExpressions();
        final List<PsiExpression> ret = new ArrayList<>(formalParams.length);
        for(int i = 0; i < formalParams.length && i < actualParams.length; i++) {
            final PsiParameter formalParam = formalParams[i];
            final PsiExpression actualParam = actualParams[i];
            if(!isParamCalledFirst(calledMethod, formalParam.getType())) {
                ret.add(actualParam);
            }
        }
        return ret;
    }

    public static boolean isParamCalledFirst(final PsiMethod calledMethod, final PsiType formalParamType) {
        final boolean asyncType = isAsyncType(formalParamType);
        if(asyncType) {
            return false;
        }

        // If this is a call to Optional.or() or Optional.orElse(), act as though the method takes in a Supplier.
        // If there is a DI call in the method, the user should be using a method that takes in a supplier to avoid
        // having the alternate flow case be evaluated when the primary flow value is present. It's better to generate
        // failing tests based on this assumption so the user can fix their code.
        final boolean isOptionalAlternateMethod = isOptionalAlternateMethod(calledMethod);
        if(isOptionalAlternateMethod) {
            return false;
        }

        return true;
    }

    private static boolean isOptionalAlternateMethod(final PsiMethod calledMethod) {
        final PsiClass containingClass = calledMethod.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName == null) {
            return false;
        }
        final String methodName = calledMethod.getName();
        if(qualifiedName.equals("com.google.common.base.Optional")) {
            return methodName.equals("or");
        }
        if(qualifiedName.equals("java.util.Optional")) {
            return methodName.equals("orElse");
        }
        return false;
    }

    public static boolean isAsyncType(final PsiType formalParamType) {
        final PsiClass typeClass = PsiUtil.resolveClassInType(formalParamType);
        if(typeClass == null) {
            return false;
        }

        // Return true if this class is a Callable or Runnable.
        if(isAsyncClass(typeClass)) {
            return true;
        }

        // Return true if this class extends Callable or Runnable.
        final LinkedHashSet<PsiClass> superClasses = InheritanceUtil.getSuperClasses(typeClass);
        for(final PsiClass superClass : superClasses) {
            if(isAsyncClass(superClass)) {
                return true;
            }
        }

        // Return true if this class has a type param that is Callable or Runnable.
        if(formalParamType instanceof final PsiClassReferenceType referenceType) {
            final PsiType[] typeParameters = referenceType.getParameters();
            for(final PsiType typeParam : typeParameters) {
                final PsiType typeToUse = getWildcardInfo(typeParam).adjustedType();
                final PsiClass typeParamClass = PsiUtil.resolveClassInType(typeToUse);
                if(typeParamClass != null && isAsyncClass(typeParamClass)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isAsyncClass(final PsiClass typeClass) {
        final String qualifiedName = typeClass.getQualifiedName();
        if(qualifiedName == null) {
            return false;
        }
        if(StringUtils.equalsAny(qualifiedName, AsyncCanonicalNames)) {
            return true;
        }
        if(StringUtils.startsWithAny(qualifiedName, FunctionCanonicalNamePrefixes)) {
            return true;
        }
        return false;
    }

    @NotNull
    public static WildcardInfo getWildcardInfo(final PsiType paramValue) {
        final PsiWildcardType wildcardType;
        if(paramValue instanceof PsiWildcardType) {
            // If the paramValue is type: "? extends XXX", set paramValue to type: XXX.
            wildcardType = (PsiWildcardType) paramValue;
        } else if(paramValue instanceof PsiCapturedWildcardType) {
            wildcardType = ((PsiCapturedWildcardType) paramValue).getWildcard();
        } else {
            return new WildcardInfo(false, paramValue);
        }

        if(wildcardType.isExtends()) {
            return new WildcardInfo(true, wildcardType.getExtendsBound());
        }
        if(wildcardType.isSuper()) {
            return new WildcardInfo(true, wildcardType.getSuperBound());
        }
        return new WildcardInfo(true, paramValue);
    }

    public static Map<PsiMethod, DiAndNodeSet> computeFlatDisMap(final Map<PsiMethod, DependencyInteractionSet> sourceMethodToDisMap) {
        final Map<PsiMethod, DiAndNodeSet> ret = new IdentityHashMap<>();
        for(final Map.Entry<PsiMethod, DependencyInteractionSet> entry : sourceMethodToDisMap.entrySet()) {
            final DependencyInteractionSet dis = entry.getValue();
            final PsiMethod sourceMethod = entry.getKey();
            final List<DiAndNode> nodeList = new ArrayList<>();
            for(final InternalDependencyInteraction di : dis) {
                for(final Node node : di.getCallstacks()) {
                    nodeList.add(new DiAndNode(di, node));
                }
            }
            nodeList.sort(Comparator.comparing(x -> x.node().getSequenceId()));
            final DiAndNodeSet nodeSet = new DiAndNodeSet();
            nodeSet.addAll(nodeList);
            ret.put(sourceMethod, nodeSet);
        }
        return ret;
    }

    public static boolean nodePassesThroughMethod(final PsiMethod sourceMethod, final Node node) {
        for(Node currentNode = node.getNext(); currentNode != null; currentNode = currentNode.getNext()) {
            if(currentNode.getSourceMethodContainingElement() == sourceMethod) {
                return true;
            }
        }
        return false;
    }
}
