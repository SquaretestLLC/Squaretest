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

import com.intellij.analysis.AnalysisScope;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.psi.JavaRecursiveElementWalkingVisitor;
import com.intellij.psi.JavaTokenType;
import com.intellij.psi.PsiBinaryExpression;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiLocalVariable;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiReferenceExpression;
import com.intellij.psi.PsiTypeCastExpression;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.tree.IElementType;
import com.intellij.slicer.JavaSliceUsage;
import com.intellij.slicer.SliceAnalysisParams;
import com.intellij.slicer.SliceUsage;
import com.intellij.util.CommonProcessors;
import com.squaretest.generation.dependencyinteraction.DependencyInteractionCollector;
import com.squaretest.generation.dependencyinteraction.DependencyInteractionCollectorUtils;
import com.squaretest.generation.dependencyinteraction.DependencyInteractionSet;
import com.squaretest.generation.dependencyinteraction.InternalDependencyInteraction;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.squaretest.generation.dependencyinteraction.SQExpressionUtils.isNullLiteral;

public class DataflowNullabilityDecider {
    @NotNull
    private final DependencyInteractionCollector dependencyInteractionCollector;
    @NotNull
    private final PsiClass sourceClass;
    @NotNull
    private final Set<PsiClass> superClasses;
    @NotNull
    private final Set<PsiClass> sourceAndSupers;
    @NotNull
    private final LocalSearchScope sourceAndSuperScope;
    @NotNull
    private final Map<PsiMethodCallExpression, NullabilityStatus> methodCallToNullabilityStatus;
    private boolean isInitialized;

    public DataflowNullabilityDecider(
            @NotNull final DependencyInteractionCollector dependencyInteractionCollector,
            @NotNull final PsiClass sourceClass) {
        this.dependencyInteractionCollector = dependencyInteractionCollector;
        this.sourceClass = sourceClass;
        this.superClasses = DependencyInteractionCollectorUtils.computeSuperClasses(sourceClass);
        this.sourceAndSupers = new LinkedHashSet<>();
        this.sourceAndSupers.add(sourceClass);
        this.sourceAndSupers.addAll(superClasses);
        this.sourceAndSuperScope = new LocalSearchScope(sourceAndSupers.toArray(PsiElement.EMPTY_ARRAY));
        this.methodCallToNullabilityStatus = new IdentityHashMap<>();
        this.isInitialized = false;
    }

    public NullabilityStatus computeNullabilityStatus(final PsiMethodCallExpression psiMethodCallExpression) {
        initializeIfNeeded();
        if(psiMethodCallExpression == null) {
            return NullabilityStatus.UNKNOWN;
        }
        return methodCallToNullabilityStatus.getOrDefault(psiMethodCallExpression, NullabilityStatus.UNKNOWN);
    }

    private void initializeIfNeeded() {
        if(!isInitialized) {
            initialize();
        }
    }

    private void initialize() {
        // Determine the unique set of dependency method calls.
        final Map<PsiMethod, DependencyInteractionSet> dependencyMap = dependencyInteractionCollector.computeMethodToPsiMap();
        final Set<PsiMethodCallExpression> diCallExpressions = SetUtils.newIdentityHashSet();
        for(final DependencyInteractionSet interactionSet : dependencyMap.values()) {
            for(final InternalDependencyInteraction internalDependencyInteraction : interactionSet) {
                final PsiMethodCallExpression methodCallExpression = internalDependencyInteraction.getInternalMethodCallExpression().methodCallExpression();
                if(methodCallExpression == null) {
                    continue;
                }
                diCallExpressions.add(methodCallExpression);
            }
        }
        final Set<PsiExpression> nullableExpressions = computeNullableExpressionsSet();
        for(final PsiExpression nullableExpression : nullableExpressions) {
            final PsiMethodCallExpression nullableMethodCall = determineNullableDiMethod(diCallExpressions, nullableExpression);
            if(nullableMethodCall != null) {
                methodCallToNullabilityStatus.put(nullableMethodCall, NullabilityStatus.NULLABLE);
            }
        }
        isInitialized = true;
    }

    private PsiMethodCallExpression determineNullableDiMethod(final Set<PsiMethodCallExpression> diCallExpressions, final PsiExpression nullableExpression) {
        final PsiExpression expressionToUse = removeExtras(nullableExpression);
        if(expressionToUse instanceof final PsiReferenceExpression referenceExpression) {
            final PsiElement target = referenceExpression.resolve();
            if(target instanceof PsiLocalVariable) {
                final SliceAnalysisParams params = createSliceAnalysisParams();
                final JavaSliceUsage rootUsage = JavaSliceUsage.createRootUsage(referenceExpression, params);
                return determineNullableDiMethodRecursively(diCallExpressions, SetUtils.newIdentityHashSet(), rootUsage);
            }
        } else if(expressionToUse instanceof final PsiMethodCallExpression methodCallExpression) {
            if(diCallExpressions.contains(methodCallExpression)) {
                return methodCallExpression;
            }
            if(isInternalInstanceMethod(methodCallExpression)) {
                final SliceAnalysisParams params = createSliceAnalysisParams();
                final JavaSliceUsage rootUsage = JavaSliceUsage.createRootUsage(methodCallExpression, params);
                return determineNullableDiMethodRecursively(diCallExpressions, SetUtils.newIdentityHashSet(), rootUsage);
            }
        }
        return null;
    }

    @Nullable
    private PsiMethodCallExpression determineNullableDiMethodRecursively(
            final Set<PsiMethodCallExpression> diCallExpressions, final Set<PsiElement> alreadyProcessedElements,
            final SliceUsage rootUsage) {
        if(alreadyProcessedElements.contains(rootUsage.getElement())) {
            return null;
        }
        alreadyProcessedElements.add(rootUsage.getElement());
        final List<SliceUsage> children = new ArrayList<>();
        ProgressManager.getInstance().executeNonCancelableSection(
                () -> safeAddChildren(rootUsage, children));
        if(children.size() != 1) {
            return null;
        }
        final SliceUsage usage = children.get(0);
        final PsiElement target = usage.getElement();
        if(target instanceof PsiLocalVariable) {
            return determineNullableDiMethodRecursively(diCallExpressions, alreadyProcessedElements, usage);
        }
        if(target instanceof PsiExpression) {
            final PsiExpression expressionToUse = removeExtras((PsiExpression) target);
            if(expressionToUse instanceof final PsiReferenceExpression referenceExpression) {
                final PsiElement refTarget = referenceExpression.resolve();
                if(refTarget instanceof PsiLocalVariable) {
                    return determineNullableDiMethodRecursively(diCallExpressions, alreadyProcessedElements, usage);
                }
            }
            if(expressionToUse instanceof final PsiMethodCallExpression methodCallExpression) {
                if(diCallExpressions.contains(methodCallExpression)) {
                    return methodCallExpression;
                }
                if(isInternalInstanceMethod(methodCallExpression)) {
                    return determineNullableDiMethodRecursively(diCallExpressions, alreadyProcessedElements, usage);
                }
            }
        }
        return null;
    }

    public static void safeAddChildren(final SliceUsage rootUsage, final List<SliceUsage> children) {
        try {
            // Sometimes the first call to rootUsage.processChildren(..) fails with:
            // com.intellij.openapi.util.RecursionManager$CachingPreventedException: Caching disabled due to recursion prevention, please get rid of cyclic dependencies.
            // I can only reproduce the issue in the unit test environment, in the cfNullabilityDataflowInfiniteLoop test.
            // The root cause appears to be a bug in the IntelliJ IDEA method. If this happens, stop the analysis and
            // return. Note: This is not a bug in Squaretest. The crash occurs on the first call to usage.processChildren(..).
            // Update in IDEA 2024.2: The IntelliJ IDEA Ultimate Edition dataflow algorithm tries to follow Spring
            // configuration references. This causes internal IntelliJ IDEA components to throw an exception if invoked
            // on the EDT thread. Swallow the exception, as the values the algorithm is trying to retrieve are not
            // values that we actually use in the Squaretest dataflow algorithm.
            rootUsage.processChildren(new CommonProcessors.CollectProcessor<>(children));
        } catch(final Throwable ignored) {
        }
    }

    private boolean isInternalInstanceMethod(final PsiMethodCallExpression psiMethodCallExpression) {
        final PsiMethod resolvedMethod = psiMethodCallExpression.resolveMethod();
        if(resolvedMethod == null) {
            return false;
        }
        if(resolvedMethod.hasModifierProperty(PsiModifier.STATIC)) {
            return false;
        }
        final PsiClass containingClass = resolvedMethod.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        if(sourceClass == containingClass || superClasses.contains(containingClass)) {
            return true;
        }
        return false;
    }

    private PsiExpression removeExtras(final PsiExpression expression) {
        if(expression instanceof PsiTypeCastExpression) {
            final PsiExpression operand = ((PsiTypeCastExpression) expression).getOperand();
            if(operand != null) {
                return operand;
            }
        }
        return expression;
    }

    private Set<PsiExpression> computeNullableExpressionsSet() {
        final Set<PsiExpression> ret = SetUtils.newIdentityHashSet();
        for(final PsiClass psiClass : sourceAndSupers) {
            if(StringUtils.equals(psiClass.getQualifiedName(), "java.lang.Object")) {
                continue;
            }
            for(final PsiMethod method : psiClass.getMethods()) {
                if(method.hasModifierProperty(PsiModifier.STATIC)) {
                    continue;
                }
                final PsiCodeBlock methodBody = method.getBody();
                if(methodBody == null) {
                    continue;
                }
                methodBody.accept(new JavaRecursiveElementWalkingVisitor() {
                    @Override
                    public void visitBinaryExpression(@NotNull final PsiBinaryExpression binaryExpression) {
                        super.visitBinaryExpression(binaryExpression);
                        final IElementType operationSign = binaryExpression.getOperationTokenType();
                        if(!JavaTokenType.EQEQ.equals(operationSign) && !JavaTokenType.NE.equals(operationSign)) {
                            return;
                        }
                        final PsiExpression left = binaryExpression.getLOperand();
                        final PsiExpression right = binaryExpression.getROperand();
                        if(isNullLiteral(left)) {
                            ret.add(right);
                        } else if(isNullLiteral(right)) {
                            ret.add(left);
                        }
                    }

                    @Override
                    public void visitMethodCallExpression(@NotNull final PsiMethodCallExpression expression) {
                        super.visitMethodCallExpression(expression);
                        if(!isOptionalOfNullableMethod(expression.resolveMethod())) {
                            return;
                        }
                        final PsiExpression[] argumentExpressions = expression.getArgumentList().getExpressions();
                        if(argumentExpressions.length != 1) {
                            return;
                        }
                        ret.add(argumentExpressions[0]);
                    }
                });
            }
        }
        return ret;
    }

    private boolean isOptionalOfNullableMethod(@Nullable final PsiMethod psiMethod) {
        if(psiMethod == null) {
            return false;
        }
        final PsiClass containingClass = psiMethod.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        final String qualifiedName = containingClass.getQualifiedName();
        if(qualifiedName == null) {
            return false;
        }
        final String methodName = psiMethod.getName();
        if(StringUtils.equals(qualifiedName, "java.util.Optional")) {
            return StringUtils.equals(methodName, "ofNullable");
        }
        if(StringUtils.equals(qualifiedName, "com.google.common.base.Optional")) {
            return StringUtils.equals(methodName, "fromNullable");
        }
        return false;
    }

    @NotNull
    private SliceAnalysisParams createSliceAnalysisParams() {
        final SliceAnalysisParams params = new SliceAnalysisParams();
        params.dataFlowToThis = true;
        params.showInstanceDereferences = false;
        params.scope = new AnalysisScope(sourceAndSuperScope, sourceClass.getProject());
        return params;
    }
}
