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
import com.intellij.psi.util.PsiTreeUtil;
import com.squaretest.generation.TypeSubstitutorProvider;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.squaretest.generation.CompiledUtils.getMethodWithSourceCode;
import static com.squaretest.generation.dependencyinteraction.DependencyInteractionCollectorUtils.*;

public class DependencyInteractionCollectorImpl {

    static final int MaxCallstacksToCollect = 7;
    private final PsiClass psiClass;
    private final TypeSubstitutorProvider typeSubstitutorProvider;
    private final MethodCallsProvider methodCallsProvider;
    private final Set<PsiClass> superClasses;
    private final Set<PsiClass> sourceAndSupers;
    private final Map<PsiMethod, PsiMethod> superMethodsToLowestOverrideMap;
    private final Set<PsiFile> superClassContainingFileSet;

    public DependencyInteractionCollectorImpl(final PsiClass psiClass, final TypeSubstitutorProvider typeSubstitutorProvider, final MethodCallsProvider methodCallsProvider) {
        this.psiClass = psiClass;
        this.superClasses = DependencyInteractionCollectorUtils.computeSuperClasses(psiClass);
        this.superClassContainingFileSet = DependencyInteractionCollectorUtils.computeSuperClassFileSet(psiClass, superClasses);
        this.sourceAndSupers = new LinkedHashSet<>();
        this.sourceAndSupers.add(psiClass);
        this.sourceAndSupers.addAll(superClasses);
        this.superMethodsToLowestOverrideMap = DependencyInteractionCollectorUtils.computeSuperMethodsToLowestOverrideMap(psiClass, superClasses);
        this.typeSubstitutorProvider = typeSubstitutorProvider;
        this.methodCallsProvider = methodCallsProvider;
    }

    public Map<PsiMethod, DependencyInteractionSet> computeMethodToPsiMap() {
        final Map<PsiMethod, DependencyInteractionSet> memoizedMethodToDIListsMap = new IdentityHashMap<>();
        final Set<PsiMethod> traversalStack = SetUtils.newIdentityHashSet();
        final Set<PsiMethod> methodsDIsAreMissingFrom = SetUtils.newIdentityHashSet();
        final Set<PsiMethod> methodsWeHavePartialResultsFor = SetUtils.newIdentityHashSet();
        for(final PsiMethod method : psiClass.getMethods()) {
            collectDIsForMethod(method, traversalStack, methodsDIsAreMissingFrom, methodsWeHavePartialResultsFor, memoizedMethodToDIListsMap);
        }

        collectDIsForInnerClassMethods(psiClass, traversalStack, methodsDIsAreMissingFrom, methodsWeHavePartialResultsFor, memoizedMethodToDIListsMap);

        // Compute the DIs for super methods.
        for(final PsiClass superClass : superClasses) {
            if(StringUtils.equals(superClass.getQualifiedName(), "java.lang.Object")) {
                continue;
            }
            for(final PsiMethod method : superClass.getMethods()) {
                collectDIsForMethod(method, traversalStack, methodsDIsAreMissingFrom, methodsWeHavePartialResultsFor, memoizedMethodToDIListsMap);
            }
            collectDIsForInnerClassMethods(superClass, traversalStack, methodsDIsAreMissingFrom, methodsWeHavePartialResultsFor, memoizedMethodToDIListsMap);
        }

        return memoizedMethodToDIListsMap;
    }

    private void collectDIsForInnerClassMethods(final PsiClass psiClass, final Set<PsiMethod> traversalStack, final Set<PsiMethod> methodsDIsAreMissingFrom, final Set<PsiMethod> methodsWeHavePartialResultsFor, final Map<PsiMethod, DependencyInteractionSet> memoizedMethodToDIListsMap) {
        for(final PsiClass innerClass : psiClass.getInnerClasses()) {
            for(final PsiMethod method : innerClass.getMethods()) {
                collectDIsForMethod(method, traversalStack, methodsDIsAreMissingFrom, methodsWeHavePartialResultsFor, memoizedMethodToDIListsMap);
            }
            collectDIsForInnerClassMethods(innerClass, traversalStack, methodsDIsAreMissingFrom, methodsWeHavePartialResultsFor, memoizedMethodToDIListsMap);
        }
    }

    private DependencyInteractionSet collectDIsForMethod(
            final PsiMethod psiMethod, final Set<PsiMethod> traversalStack,
            final Set<PsiMethod> methodsDIsAreMissingFrom, final Set<PsiMethod> methodsWeHavePartialResultsFor,
            final Map<PsiMethod, DependencyInteractionSet> finalMemoizedMethodToDIListsMap) {

        // We've already processed this method and have the final, set of DIs.
        if(finalMemoizedMethodToDIListsMap.containsKey(psiMethod)) {
            return finalMemoizedMethodToDIListsMap.get(psiMethod);
        }

        // This method is static.
        if(psiMethod.hasModifierProperty(PsiModifier.STATIC)) {
            finalMemoizedMethodToDIListsMap.put(psiMethod, DependencyInteractionSet.emptySet());
            return DependencyInteractionSet.emptySet();
        }

        final PsiCodeBlock methodBody = psiMethod.getBody();
        if(methodBody == null) {
            finalMemoizedMethodToDIListsMap.put(psiMethod, DependencyInteractionSet.emptySet());
            return DependencyInteractionSet.emptySet();
        }

        final DependencyInteractionSet returnSet = new DependencyInteractionSet();
        // Visit all method-call expressions in this method.
        final MutableInt sequenceId = new MutableInt(0);
        collectDIsInChildElement(psiMethod, methodBody, traversalStack, methodsDIsAreMissingFrom, methodsWeHavePartialResultsFor, finalMemoizedMethodToDIListsMap, sequenceId, returnSet);

        // Remove ourselves from the methodsDIsAreMissingFrom; we've added the DIs from our own method body.
        methodsDIsAreMissingFrom.remove(psiMethod);
        // If the set of methodsDIsAreMissingFrom from is empty, we have the result-set for this method.
        // Add it to the finalMemoizedMethodToDIListsMap.
        if(methodsDIsAreMissingFrom.isEmpty()) {
            finalMemoizedMethodToDIListsMap.put(psiMethod, returnSet);
            methodsWeHavePartialResultsFor.clear();
        } else {
            // Otherwise, either: (us, one of the methods we call, one of the methods it calls, ... etc)
            // makes a call to something higher in the stack. We only have the partial result-set, because we can't
            // get the dependencies from higher in the stack at this time.
            methodsWeHavePartialResultsFor.add(psiMethod);
        }

        return returnSet;
    }

    private void collectDIsInChildElement(
            final PsiMethod psiMethod, final PsiElement childElement, final Set<PsiMethod> traversalStack,
            final Set<PsiMethod> methodsDIsAreMissingFrom, final Set<PsiMethod> methodsWeHavePartialResultsFor,
            final Map<PsiMethod, DependencyInteractionSet> finalMemoizedMethodToDIListsMap,
            final MutableInt sequenceId, final DependencyInteractionSet returnSet) {
        final List<PsiElement> methodCallsOrRefs = methodCallsProvider.getMethodCallsOrRefsInElement(childElement);
        for(final PsiElement methodCallOrRef : methodCallsOrRefs) {
            if(methodCallOrRef instanceof final PsiMethodCallExpression methodCallExpression) {
                // Process method parameters that will be evaluated first.
                final List<PsiExpression> argsToProcessFirst = DependencyInteractionCollectorUtils.getArgsToProcessFirst(methodCallExpression);
                for(final PsiElement argElement : argsToProcessFirst) {
                    collectDIsInChildElement(psiMethod, argElement, traversalStack, methodsDIsAreMissingFrom, methodsWeHavePartialResultsFor, finalMemoizedMethodToDIListsMap, sequenceId, returnSet);
                }

                // Check to see if this is a dependency interaction.
                final List<InternalDependencyInteraction> internalDependencyInteractions = getDependencyInteractionsIfPresent(psiMethod, methodCallExpression, sequenceId);
                if(!internalDependencyInteractions.isEmpty()) {
                    for(final InternalDependencyInteraction internalDependencyInteraction : internalDependencyInteractions) {
                        addDependencyInteraction(returnSet, internalDependencyInteraction);
                    }
                    continue;
                }

                // Check to see if this is a method call we should follow to search for more DIs.
                final PsiMethod methodToCheckRecursively = getMethodToCheckRecursivelyIfPresent(methodCallExpression);
                if(methodToCheckRecursively != null) {
                    checkMethodRecursively(methodToCheckRecursively, methodCallExpression, finalMemoizedMethodToDIListsMap, psiMethod, sequenceId, returnSet, traversalStack, methodsDIsAreMissingFrom, methodsWeHavePartialResultsFor);
                }
                continue;
            }

            if(methodCallOrRef instanceof final PsiMethodReferenceExpression methodReferenceExpression) {
                // Check to see if this is a dependency interaction.
                final InternalDependencyInteraction internalDependencyInteraction = getDependencyInteractionIfPresent1(psiMethod, methodReferenceExpression, sequenceId);
                if(internalDependencyInteraction != null) {
                    addDependencyInteraction(returnSet, internalDependencyInteraction);
                    continue;
                }

                // Check to see if this is a method call we should follow to search for more DIs.
                final PsiMethod methodToCheckRecursively = getMethodToCheckRecursivelyIfPresent1(methodReferenceExpression);
                if(methodToCheckRecursively != null) {
                    checkMethodRecursively(methodToCheckRecursively, methodReferenceExpression, finalMemoizedMethodToDIListsMap, psiMethod, sequenceId, returnSet, traversalStack, methodsDIsAreMissingFrom, methodsWeHavePartialResultsFor);
                }
            }
        }
    }

    private void checkMethodRecursively(
            final PsiMethod methodToCheckRecursively, final PsiElement callstackElement,
            final Map<PsiMethod, DependencyInteractionSet> finalMemoizedMethodToDIListsMap,
            final PsiMethod psiMethod, final MutableInt sequenceId, final DependencyInteractionSet returnSet,
            final Set<PsiMethod> traversalStack, final Set<PsiMethod> methodsDIsAreMissingFrom,
            final Set<PsiMethod> methodsWeHavePartialResultsFor) {
        // Check to see if we have completed results for this method.
        if(finalMemoizedMethodToDIListsMap.containsKey(methodToCheckRecursively)) {
            final DependencyInteractionSet disFromMethod = finalMemoizedMethodToDIListsMap.get(methodToCheckRecursively);
            final DependencyInteractionSet disToAdd = addCallstackNode(psiMethod, disFromMethod, callstackElement, sequenceId);
            addDependencyInteractions(returnSet, disToAdd);
        } else if(psiMethod == methodToCheckRecursively) {
            // This is a recursive call to ourselves; ignore it.
        } else if(traversalStack.contains(methodToCheckRecursively)) {
            // The method is higher in the call-stack. Report that we can't check it and return.
            methodsDIsAreMissingFrom.add(methodToCheckRecursively);
        } else if(methodsWeHavePartialResultsFor.contains(methodToCheckRecursively)) {
            // We already have partial results for this method; no need to check it again.
        } else {
            // Collect dependencies recursively.
            // Note: I know that methodsDIsAreMissingFrom and methodsWeHavePartialResultsFor are
            // inaccurate for methodToCheckRecursively; an argument can be made that it doesn't make sense
            // to pass them to the method call, as it will prevent the call from memoizing any of the
            // computed methods.
            //
            // Unfortunately, we have to do this to avoid exploring overlapping subtrees of method calls
            // multiple times. If we do not avoid this, we have a very bad worst case runtime of
            // O(n^n). It's better to forgo memoization than to have that worst case runtime.
            traversalStack.add(psiMethod);
            final DependencyInteractionSet disFromMethod = collectDIsForMethod(methodToCheckRecursively, traversalStack, methodsDIsAreMissingFrom, methodsWeHavePartialResultsFor, finalMemoizedMethodToDIListsMap);
            final DependencyInteractionSet disToAdd = addCallstackNode(psiMethod, disFromMethod, callstackElement, sequenceId);
            addDependencyInteractions(returnSet, disToAdd);
            traversalStack.remove(psiMethod);
        }
    }

    private void addDependencyInteractions(final DependencyInteractionSet returnSet, final DependencyInteractionSet disToAdd) {
        for(final InternalDependencyInteraction diToAdd : disToAdd) {
            addDependencyInteraction(returnSet, diToAdd);
        }
    }

    private void addDependencyInteraction(final DependencyInteractionSet returnSet, final InternalDependencyInteraction internalDependencyInteraction) {
        final InternalDependencyInteraction existingDependencyInteraction = returnSet.get(internalDependencyInteraction);
        if(existingDependencyInteraction == null) {
            returnSet.add(internalDependencyInteraction);
            return;
        }
        final List<Node> existingCallstacks = existingDependencyInteraction.getCallstacks();
        if(existingCallstacks.size() >= MaxCallstacksToCollect) {
            return;
        }
        for(final Node newCallstack : internalDependencyInteraction.getCallstacks()) {
            if(!existingCallstacks.contains(newCallstack)) {
                existingCallstacks.add(newCallstack);
                if(existingCallstacks.size() >= MaxCallstacksToCollect) {
                    return;
                }
            }
        }
    }

    private DependencyInteractionSet addCallstackNode(final PsiMethod methodContainingCallstackNode, final DependencyInteractionSet disToAdd, final PsiElement callstackNode, final MutableInt sequenceId) {
        final DependencyInteractionSet ret = new DependencyInteractionSet();
        for(final InternalDependencyInteraction diToAdd : disToAdd) {
            final List<Node> existingCallstacks = diToAdd.getCallstacks();
            final List<Node> newCallstacks = addNodeToCallstacks(methodContainingCallstackNode, existingCallstacks, callstackNode, sequenceId);
            ret.add(new InternalDependencyInteraction(diToAdd.getPsiField(), diToAdd.getPsiMethod(),
                    diToAdd.getInternalMethodCallExpression(), diToAdd.isReturnValueIgnored(), newCallstacks));
        }
        return ret;
    }

    @NotNull
    private List<Node> addNodeToCallstacks(final PsiMethod methodContainingCallstackNode, final List<Node> existingCallstacks, final PsiElement callstackNode, final MutableInt sequenceId) {
        final List<Node> newCallstacks = new ArrayList<>(MaxCallstacksToCollect);
        for(final Node existingCallstack : existingCallstacks) {
            final Node newCallstack = new Node(methodContainingCallstackNode, callstackNode, existingCallstack.getEndElement(), sequenceId.getAndIncrement(), existingCallstack);
            if(!nodePassesThroughMethod(methodContainingCallstackNode, newCallstack)) {
                newCallstacks.add(newCallstack);
            }
        }
        return newCallstacks;
    }

    private PsiMethod getMethodToCheckRecursivelyIfPresent1(final PsiMethodReferenceExpression methodReferenceExpression) {
        final PsiElement methodElement = methodReferenceExpression.resolve();
        if(!(methodElement instanceof PsiMethod)) {
            return null;
        }
        final PsiMethod calledMethod = getMethodWithSourceCode((PsiMethod) methodElement);
        if(calledMethod.hasModifierProperty(PsiModifier.STATIC)) {
            return null;
        }
        final boolean isSuperCall = DependencyInteractionCollectorUtils.hasSuperQualifier(methodReferenceExpression);
        return getMethodToCheckRecursivelyImpl(isSuperCall, calledMethod);
    }

    @Nullable
    private PsiMethod getMethodToCheckRecursivelyIfPresent(final PsiMethodCallExpression methodCallExpression) {
        final PsiMethod calledMethod = getMethodWithSourceCode(methodCallExpression.resolveMethod());
        if(calledMethod == null) {
            return null;
        }
        if(calledMethod.hasModifierProperty(PsiModifier.STATIC)) {
            return null;
        }
        boolean isSuperCall = DependencyInteractionCollectorUtils.hasSuperQualifier(methodCallExpression);
        return getMethodToCheckRecursivelyImpl(isSuperCall, calledMethod);
    }

    @Nullable
    private PsiMethod getMethodToCheckRecursivelyImpl(final boolean isSuperCall, final PsiMethod calledMethod) {
        final PsiFile calledMethodContainingFile = calledMethod.getContainingFile();
        if(isSuperCall) {
            if(superClassContainingFileSet.contains(calledMethodContainingFile)) {
                return calledMethod;
            }
            return null;
        }

        // If this method is overridden in the source class or one of its super types, use the override.
        final PsiMethod lowestOverrideMethod = superMethodsToLowestOverrideMap.get(calledMethod);
        if(lowestOverrideMethod != null) {
            return lowestOverrideMethod;
        }

        // If the method is in the source class file or one of it's super class files, return it.
        // Check to see if the method is in the file instead of the class, because we could be analyzing statements in an anon inner class.
        if(superClassContainingFileSet.contains(calledMethodContainingFile)) {
            return calledMethod;
        }

        return null;
    }

    @NotNull
    private List<InternalDependencyInteraction> getDependencyInteractionsIfPresent(
            final PsiMethod methodContainingCallstackNode,
            final PsiMethodCallExpression methodCallExpression, final MutableInt sequenceId) {
        // Determine the method called.
        final PsiReferenceExpression methodExpression = methodCallExpression.getMethodExpression();
        final PsiMethod calledMethod = getMethodWithSourceCode(methodCallExpression.resolveMethod());
        if(calledMethod == null) {
            return Collections.emptyList();
        }

        if(calledMethod.hasModifierProperty(PsiModifier.STATIC)) {
            return determineDependencyInteractionsForStaticMethod(
                    sourceAndSupers, psiClass, methodContainingCallstackNode, calledMethod, methodCallExpression, sequenceId);
        }

        // Check for cases where we pass an Executor to a Future instance.
        final InternalDependencyInteraction internalDependencyInteraction = determineDependencyInteractionsForFutureInstanceMethod(sourceAndSupers, psiClass, methodContainingCallstackNode, calledMethod, methodCallExpression, sequenceId);
        if(internalDependencyInteraction != null) {
            return Collections.singletonList(internalDependencyInteraction);
        }

        // Determine the field.
        final PsiExpression leftSideOfTheDot = methodExpression.getQualifierExpression();
        PsiField dependency = getSourceClassMemberThisExpressionResolvesTo(sourceAndSupers, leftSideOfTheDot);
        if(dependency == null) {
            return Collections.emptyList();
        }

        if(calledMethod.hasModifierProperty(PsiModifier.STATIC)) {
            // If we can't resolved the called method, return null.
            // If this is a static method referenced from a non-static context (the class member), return null.
            return Collections.emptyList();
        }
        final InternalMethodCallExpression internalMethodCallExpression = computeMethodCallExpression(methodCallExpression);
        final boolean returnValueIgnored = SQExpressionUtils.isReturnValueIgnored(calledMethod, methodCallExpression);
        final Node newNode = new Node(methodContainingCallstackNode, methodCallExpression, methodCallExpression, sequenceId.getAndIncrement(), null);
        if(nodePassesThroughMethod(methodContainingCallstackNode, newNode)) {
            return Collections.emptyList();
        }
        final List<Node> callstacks = new ArrayList<>(MaxCallstacksToCollect);
        callstacks.add(newNode);
        return Collections.singletonList(new InternalDependencyInteraction(dependency, calledMethod, internalMethodCallExpression, returnValueIgnored, callstacks));
    }

    private InternalDependencyInteraction getDependencyInteractionIfPresent1(final PsiMethod methodContainingCallstackNode, final PsiMethodReferenceExpression methodReferenceExpression, final MutableInt sequenceId) {
        final PsiElement qualifierElement = methodReferenceExpression.getQualifier();
        if(!(qualifierElement instanceof PsiExpression)) {
            return null;
        }
        final PsiField dependency = getSourceClassMemberThisExpressionResolvesTo(sourceAndSupers, (PsiExpression) qualifierElement);
        if(dependency == null) {
            return null;
        }

        final PsiElement methodElement = methodReferenceExpression.resolve();
        if(!(methodElement instanceof PsiMethod)) {
            return null;
        }
        final PsiMethod calledMethod = getMethodWithSourceCode((PsiMethod) methodElement);
        if(calledMethod.hasModifierProperty(PsiModifier.STATIC)) {
            // If we can't resolve the called method, return null.
            // If this is a static method referenced from a non-static context (the class member), return null.
            return null;
        }
        // Get the actual return type of the method reference expression.
        // This is needed, because we could have a method reference expression to a method that returns an instance of <T>.
        final PsiSubstitutor sourceClassSubstitutor = getSourceClassSubstitutor(methodReferenceExpression);
        PsiType returnTypeToUse = PsiMethodReferenceUtil.getMethodReferenceReturnType(methodReferenceExpression);
        if(returnTypeToUse == null) {
            returnTypeToUse = calledMethod.getReturnType();
        }
        if(sourceClassSubstitutor != null) {
            returnTypeToUse = sourceClassSubstitutor.substitute(returnTypeToUse);
        }
        // Use methodReferenceExpression.advancedResolve(false) to get a substitutor that will fill in any type params in the method.
        final JavaResolveResult methodReferenceResult = methodReferenceExpression.advancedResolve(false);
        final PsiSubstitutor methodReferenceExpressionSubstitutor = methodReferenceResult.getSubstitutor();
        final List<PsiType> paramTypes = new ArrayList<>();
        for(final PsiParameter param : calledMethod.getParameterList().getParameters()) {
            PsiType typeToUse = methodReferenceExpressionSubstitutor.substitute(param.getType());
            if(sourceClassSubstitutor != null) {
                typeToUse = sourceClassSubstitutor.substitute(typeToUse);
            }
            paramTypes.add(typeToUse);
        }
        final InternalMethodCallExpression internalMethodCallExpression = new InternalMethodCallExpression(paramTypes, returnTypeToUse, null);
        final boolean returnValueIgnored = SQExpressionUtils.isReturnValueIgnored(calledMethod, methodReferenceExpression);
        final Node newNode = new Node(methodContainingCallstackNode, methodReferenceExpression, methodReferenceExpression, sequenceId.getAndIncrement(), null);
        if(nodePassesThroughMethod(methodContainingCallstackNode, newNode)) {
            return null;
        }
        final List<Node> callstacks = new ArrayList<>(MaxCallstacksToCollect);
        callstacks.add(newNode);
        return new InternalDependencyInteraction(dependency, calledMethod, internalMethodCallExpression, returnValueIgnored, callstacks);
    }

    private PsiSubstitutor getSourceClassSubstitutor(final PsiElement sourceElement) {
        final List<PsiClass> containingClasses = PsiTreeUtil.collectParents(sourceElement, PsiClass.class, false, x -> x instanceof PsiFile);
        for(final PsiClass containingClass : containingClasses) {
            final PsiSubstitutor substitutor = typeSubstitutorProvider.getSubstitutor(containingClass);
            if(substitutor != null) {
                return substitutor;
            }
        }
        return null;
    }

    private InternalMethodCallExpression computeMethodCallExpression(final PsiMethodCallExpression methodCallExpression) {
        final PsiType[] types = methodCallExpression.getArgumentList().getExpressionTypes();
        final PsiType type = methodCallExpression.getType();
        return new InternalMethodCallExpression(Arrays.asList(types), type, methodCallExpression);
    }
}
