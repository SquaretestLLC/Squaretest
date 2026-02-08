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
package com.squaretest.generation.dataflow;

import com.intellij.analysis.AnalysisScope;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.psi.impl.light.LightRecordField;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtil;
import com.intellij.psi.util.TypeConversionUtil;
import com.intellij.slicer.JavaSliceUsage;
import com.intellij.slicer.SliceAnalysisParams;
import com.intellij.slicer.SliceUsage;
import com.squaretest.annotations.SpringReferenceResolver;
import com.squaretest.generation.CompiledUtils;
import com.squaretest.generation.dataflow.usedmethods.CalledMethodInfo;
import com.squaretest.generation.dataflow.usedmethods.CalledMethodsInfoProvider;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.defaulttypes.ProtobufUtils;
import com.squaretest.generation.defaulttypes.beans.BeanInfoProvider;
import com.squaretest.generation.dependencyinteraction.DependencyFlowInfoProvider;
import com.squaretest.generation.dependencyinteraction.DependencyInteractionCollectorUtils;
import com.squaretest.generation.dependencyinteraction.DependencyInteractionSet;
import com.squaretest.generation.dependencyinteraction.InternalDependencyInteraction;
import com.squaretest.generation.simpleexit.ConstantInitExpressionValueProvider;
import com.squaretest.generation.sourcevars.SourceVariableProvider;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jgrapht.alg.connectivity.KosarajuStrongConnectivityInspector;
import org.jgrapht.alg.interfaces.StrongConnectivityAlgorithm;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.squaretest.generation.DataflowNullabilityDecider.safeAddChildren;
import static com.squaretest.generation.dataflow.DataflowUtils.*;
import static com.squaretest.generation.dataflow.usedmethods.UsedPropertyInfoProvider.getIndexOfParamWithName;
import static com.squaretest.generation.defaulttypes.AWSBuilderUtils.isAWSBuilderMethod;

public class DataflowAnalyzer {

    @NotNull
    private final DependencyFlowInfoProvider dependencyFlowInfoProvider;
    @NotNull
    private final BeanInfoProvider beanInfoProvider;
    @NotNull
    private final SourceVariableProvider sourceVariableProvider;
    @NotNull
    private final CalledMethodsInfoProvider calledMethodsInfoProvider;
    @NotNull
    private final PsiClass sourceClass;
    @NotNull
    private final PsiConstantEvaluationHelper constantEvaluationHelper;
    @NotNull
    private final ConstantInitExpressionValueProvider constantInitExpressionValueProvider;
    @NotNull
    private final SpringReferenceResolver springReferenceResolver;
    @NotNull
    private final Set<PsiClass> superClasses;
    @NotNull
    private final Set<PsiClass> sourceAndSupers;
    @NotNull
    private final LocalSearchScope sourceAndSuperScope;
    @NotNull
    private final Map<PsiElement, ImmutableDataValue> sourceElementToStringAndUUIDInitValues;
    @NotNull
    private final Map<PsiElement, String> sourceElementToEnumAndPrimitiveInitValue;
    @NotNull
    private final Map<PsiElement, String> sourceElementToLibraryReferenceValue;
    private boolean isInitialized;

    public DataflowAnalyzer(
            @NotNull final DependencyFlowInfoProvider dependencyFlowInfoProvider,
            @NotNull final BeanInfoProvider beanInfoProvider,
            @NotNull final SourceVariableProvider sourceVariableProvider,
            @NotNull final CalledMethodsInfoProvider calledMethodsInfoProvider,
            @NotNull final PsiClass sourceClass,
            @NotNull final PsiConstantEvaluationHelper psiConstantEvaluationHelper,
            @NotNull final ConstantInitExpressionValueProvider constantInitExpressionValueProvider,
            @NotNull final SpringReferenceResolver springReferenceResolver) {
        this.dependencyFlowInfoProvider = dependencyFlowInfoProvider;
        this.beanInfoProvider = beanInfoProvider;
        this.sourceVariableProvider = sourceVariableProvider;
        this.calledMethodsInfoProvider = calledMethodsInfoProvider;
        this.sourceClass = sourceClass;
        this.superClasses = DependencyInteractionCollectorUtils.computeSuperClasses(sourceClass);
        this.constantInitExpressionValueProvider = constantInitExpressionValueProvider;
        this.springReferenceResolver = springReferenceResolver;
        this.sourceAndSupers = new LinkedHashSet<>();
        this.sourceAndSupers.add(sourceClass);
        this.sourceAndSupers.addAll(superClasses);
        this.sourceAndSuperScope = new LocalSearchScope(sourceAndSupers.toArray(PsiElement.EMPTY_ARRAY));
        this.constantEvaluationHelper = psiConstantEvaluationHelper;
        this.sourceElementToStringAndUUIDInitValues = new IdentityHashMap<>();
        this.sourceElementToEnumAndPrimitiveInitValue = new IdentityHashMap<>();
        this.sourceElementToLibraryReferenceValue = new IdentityHashMap<>();
        this.isInitialized = false;
    }

    ImmutableDataValue computeBestDataValue(final Set<Node> nodes) {
        if(anyValueIsSpringDateParam(nodes)) {
            return new ImmutableDataValue(DefaultSpringDateParamValue, null);
        }
        // If one of the items is a parameter or field with an @Value(..) annotation, use the value from that.
        final ImmutableDataValue springValue = tryGetSpringValue(nodes);
        if(springValue != null) {
            return springValue;
        }

        // Choose the best value.
        ValueSource bestValueSource = ValueSource.IntermediateValue;
        boolean bestValueSourceIsUUID = false;
        boolean isBestValueCharset = false;
        String bestValue = "value";
        for(final Node node : nodes) {
            final ValueSource currentValueSource = node.valueSource();
            if(bestValueSourceIsUUID) {
                // We already know this type contains a UUID.
                if(node.isUUID() && currentValueSource.ordinal() < bestValueSource.ordinal()) {
                    // Replace existing data value and continue.
                    if(currentValueSource == ValueSource.ConstantValue) {
                        bestValue = node.valueAsString();
                        bestValueSource = currentValueSource;
                    }
                }
            } else {
                // The best value we've found so far is not a UUID.
                if(node.isUUID()) {
                    // This type is a UUID. That means we need to use a UUID for the value.
                    if(node.valueSource() == ValueSource.ConstantValue) {
                        // If the value source is a constant and contains a UUID, use it.
                        bestValue = node.valueAsString();
                    } else {
                        // Else, generate a new UUID and use that.
                        bestValue = UUID.randomUUID().toString();
                    }
                    bestValueSourceIsUUID = true;
                    bestValueSource = node.valueSource();
                } else {
                    // Neither type is a UUID.
                    if(currentValueSource.ordinal() < bestValueSource.ordinal()) {
                        // If our type has a better source value, use it.
                        bestValue = node.valueAsString();
                        bestValueSource = currentValueSource;
                        if(bestValueSource != ValueSource.ConstantValue && isCharSet(node)) {
                            bestValue = "UTF-8";
                            isBestValueCharset = true;
                        }
                    } else if(currentValueSource.ordinal() == bestValueSource.ordinal()) {
                        if(isBestValueCharset) {
                            continue;
                        }
                        if(isCharSet(node)) {
                            bestValue = "UTF-8";
                            isBestValueCharset = true;
                            continue;
                        }
                        // If the two value sources have equal priority, choose the one that comes first alphabetically.
                        // This way, if we have a source methods like:
                        // deleteOrder(String orderIdToDelete) and
                        // getOrder(String orderId),
                        // we will choose orderId for both instead of orderIdToDelete for both.
                        // Also we need the chosen value to be the same everytime in order for unit tests to pass;
                        // if we don't select the best one alphabetically, we will pick the first one we encounter, and
                        // that can be different each time we run the tests.
                        final String psiTypeStringValue = node.valueAsString();
                        if(psiTypeStringValue.compareToIgnoreCase(bestValue) < 0) {
                            bestValue = psiTypeStringValue;
                        }
                    }
                }
            }
        }
        String uuidInitExpression = null;
        if(bestValueSourceIsUUID) {
            uuidInitExpression = String.format(UUIDFormatString, bestValue);
        }
        return new ImmutableDataValue(quote(bestValue), uuidInitExpression);
    }

    @Nullable
    private ImmutableDataValue tryGetSpringValue(final Set<Node> nodes) {
        for(final Node node : nodes) {
            final PsiElement source = node.source();
            final PsiNameValuePair nameValuePair = getSpringValueAnnotationKeyValuePair(source);
            if(nameValuePair == null) {
                continue;
            }
            final PsiAnnotationMemberValue value = nameValuePair.getValue();
            if(value instanceof PsiLiteralExpression) {
                final String textWithResolvedRefs = springReferenceResolver.getStringContentWithPropertiesFilledIn((PsiLiteralExpression) value);
                if(textWithResolvedRefs == null) {
                    return null;
                }
                if(StringUtils.containsAny(textWithResolvedRefs, "${", "#{")) {
                    return null;
                }
                final boolean isUUID = isUUIDString(textWithResolvedRefs);
                final String uuidInitExpression = isUUID ? String.format(UUIDFormatString, textWithResolvedRefs) : null;
                return new ImmutableDataValue(quote(textWithResolvedRefs), uuidInitExpression);
            }
            if(value instanceof PsiReferenceExpression || value instanceof PsiPolyadicExpression) {
                final Object constantValue = constantEvaluationHelper.computeConstantExpression(value, false);
                if(constantValue == null) {
                    return null;
                }
                if(constantValue instanceof final String strConstant) {
                    if(StringUtils.containsAny(strConstant, "${", "#{")) {
                        return null;
                    }
                    final String strValue = StringUtil.escapeStringCharacters(strConstant);
                    final boolean isUUID = isUUIDString(strValue);
                    final String uuidInitExpression = isUUID ? String.format(UUIDFormatString, strValue) : null;
                    return new ImmutableDataValue(quote(strValue), uuidInitExpression);
                }
                return null;
            }
        }
        return null;
    }

    private static PsiNameValuePair getSpringValueAnnotationKeyValuePair(final PsiElement source) {
        if(!(source instanceof PsiParameter) && !(source instanceof PsiField)) {
            return null;
        }
        final PsiVariable psiVariable = (PsiVariable) source;
        final PsiAnnotation[] annotations = psiVariable.getAnnotations();
        for(final PsiAnnotation annotation : annotations) {
            if(!StringUtils.equals(annotation.getQualifiedName(), "org.springframework.beans.factory.annotation.Value")) {
                continue;
            }
            final PsiNameValuePair[] attributes = annotation.getParameterList().getAttributes();
            final Optional<PsiNameValuePair> valueEntry = Arrays.stream(attributes).filter(x -> StringUtils.equals(x.getAttributeName(), "value")).findFirst();
            if(valueEntry.isPresent()) {
                return valueEntry.get();
            }
        }
        return null;
    }

    @Nullable
    public String getInitExpressionForString(@NotNull final PsiElement source) {
        initializeIfNeeded();
        final ImmutableDataValue dataValue = sourceElementToStringAndUUIDInitValues.get(source);
        if(dataValue == null) {
            return null;
        }
        return dataValue.stringInitExpression();
    }

    @Nullable
    public String getInitExpressionForUUID(@NotNull final PsiElement source) {
        initializeIfNeeded();
        final ImmutableDataValue dataValue = sourceElementToStringAndUUIDInitValues.get(source);
        if(dataValue == null) {
            return null;
        }
        return dataValue.uuidInitExpression();
    }

    @Nullable
    public String getPrimitiveOrEnumValue(@NotNull final PsiElement source) {
        initializeIfNeeded();
        return sourceElementToEnumAndPrimitiveInitValue.get(source);
    }

    @Nullable
    public String getReferenceToLibraryConstant(@NotNull final PsiElement source) {
        initializeIfNeeded();
        return sourceElementToLibraryReferenceValue.get(source);
    }

    private void initializeIfNeeded() {
        if(!isInitialized) {
            initialize();
        }
    }

    private void initialize() {
        // Determine the unique set of dependency method calls.
        final Map<PsiMethod, DependencyInteractionSet> dependencyMap = dependencyFlowInfoProvider.computeMethodToPsiMap();
        final Map<PsiMethodCallExpression, InternalDependencyInteraction> callExpressionsToDisMap = new IdentityHashMap<>();
        for(final DependencyInteractionSet interactionSet : dependencyMap.values()) {
            for(final InternalDependencyInteraction internalDependencyInteraction : interactionSet) {
                final PsiMethodCallExpression methodCallExpression = internalDependencyInteraction.getInternalMethodCallExpression().methodCallExpression();
                if(methodCallExpression == null) {
                    continue;
                }
                callExpressionsToDisMap.put(methodCallExpression, internalDependencyInteraction);
            }
        }

        // Compute the sets of nodes that should hold the same value.
        final List<Set<Node>> sameValueSets = new ArrayList<>();
        final Set<Node> alreadyVisitedNodes = new HashSet<>();
        // Compute the sets for the dependency method call parameters.
        for(final InternalDependencyInteraction internalDependencyInteraction : callExpressionsToDisMap.values()) {
            computeSameValueSetsForDi(callExpressionsToDisMap, sameValueSets, alreadyVisitedNodes, internalDependencyInteraction);
        }

        // Compute the sets for source method return values.
        for(final PsiClass psiClass : sourceAndSupers) {
            if(StringUtils.equals(psiClass.getQualifiedName(), "java.lang.Object")) {
                continue;
            }
            for(final PsiMethod method : psiClass.getMethods()) {
                computeSameValueSetsForMethodReturnStatements(callExpressionsToDisMap, sameValueSets, alreadyVisitedNodes, method);
            }
        }

        // Compute the sets for constructor call params and setter call params in the source class.
        Collection<CalledMethodInfo> calledMethodInfos = calledMethodsInfoProvider.getMethodsCalledInSourceClass();
        for(final CalledMethodInfo calledMethodInfo : calledMethodInfos) {
            final PsiMethod calledMethod = calledMethodInfo.getCalledMethod();
            final PsiClass calledMethodContainingClass = calledMethod.getContainingClass();
            if(calledMethodContainingClass == null) {
                continue;
            }
            if(calledMethod.isConstructor()) {
                if(!sourceAndSupers.contains(calledMethodContainingClass)) {
                    // This is a data constructor call. Follow the String params.
                    for(final PsiCall callExpression : calledMethodInfo.getMethodCallExpressions()) {
                        final ValueSource valueSource;
                        if(calledMethod instanceof PsiCompiledElement || !calledMethod.isWritable()) {
                            valueSource = ValueSource.ExternalClassConstructorParamNotWritable;
                        } else {
                            valueSource = ValueSource.ExternalClassConstructorParamWritable;
                        }
                        computeSameValueSetsForMethodParams(callExpressionsToDisMap, sameValueSets, alreadyVisitedNodes, calledMethod, callExpression, valueSource);
                    }
                }
                continue;
            }
            if(shouldAnalyzeMethodParams(calledMethod)) {
                for(final PsiCall callExpression : calledMethodInfo.getMethodCallExpressions()) {
                    ValueSource valueSource;
                    if(calledMethod instanceof PsiCompiledElement || !calledMethod.isWritable()) {
                        valueSource = ValueSource.ExternalClassSetterParamNotWritable;
                    } else {
                        valueSource = ValueSource.ExternalClassSetterParamWritable;
                    }
                    if(ProtobufUtils.isProtobufBuilderMethod(calledMethod)) {
                        valueSource = ValueSource.ProtobufBuilderMethodParam;
                    } else if(StringUtils.equals(getOnlyParamName(calledMethod), "value")) {
                        valueSource = ValueSource.ExternalClassSetterParamNamedValue;
                    }
                    computeSameValueSetsForMethodParams(callExpressionsToDisMap, sameValueSets, alreadyVisitedNodes, calledMethod, callExpression, valueSource);
                }
            }
        }

        // Compute the sets for constructor call params and setter call params in static helper methods.
        calledMethodInfos = calledMethodsInfoProvider.getMethodsCalledInStaticMethods();
        for(final CalledMethodInfo calledMethodInfo : calledMethodInfos) {
            final PsiMethod calledMethod = calledMethodInfo.getCalledMethod();
            final PsiClass calledMethodContainingClass = calledMethod.getContainingClass();
            if(calledMethodContainingClass == null) {
                continue;
            }
            if(calledMethod.isConstructor()) {
                if(!sourceAndSupers.contains(calledMethodContainingClass)) {
                    // This is a data constructor call. Follow the String params.
                    for(final PsiCall callExpression : calledMethodInfo.getMethodCallExpressions()) {
                        final ValueSource valueSource;
                        if(calledMethod instanceof PsiCompiledElement || !calledMethod.isWritable()) {
                            valueSource = ValueSource.ExternalClassConstructorParamNotWritable;
                        } else {
                            valueSource = ValueSource.ExternalClassConstructorParamWritable;
                        }
                        computeSameValueSetsForMethodParamsLight(Collections.emptyMap(), sameValueSets, calledMethod, callExpression, valueSource);
                    }
                }
                continue;
            }
            if(shouldAnalyzeMethodParams(calledMethod)) {
                for(final PsiCall callExpression : calledMethodInfo.getMethodCallExpressions()) {
                    final ValueSource valueSource;
                    if(calledMethod instanceof PsiCompiledElement || !calledMethod.isWritable()) {
                        valueSource = ValueSource.ExternalClassSetterParamNotWritable;
                    } else {
                        valueSource = ValueSource.ExternalClassSetterParamWritable;
                    }
                    computeSameValueSetsForMethodParamsLight(Collections.emptyMap(), sameValueSets, calledMethod, callExpression, valueSource);
                }
            }
        }

        // Build the graph.
        final DefaultDirectedGraph<Node, DefaultEdge> sameValuesGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
        for(final Set<Node> sameValueSet : sameValueSets) {
            Node previousNode = null;
            for(final Node currentNode : sameValueSet) {
                sameValuesGraph.addVertex(currentNode);
                // We need to have a path connecting all the nodes in the set. Just connect each node we find
                // to the previous one.
                if(previousNode != null) {
                    sameValuesGraph.addEdge(previousNode, currentNode);
                    sameValuesGraph.addEdge(currentNode, previousNode);
                }
                previousNode = currentNode;
            }
        }

        // Find the connected components.
        final StrongConnectivityAlgorithm<Node, DefaultEdge> connectedComponentsAlgo = new KosarajuStrongConnectivityInspector<>(sameValuesGraph);
        final List<Set<Node>> connectedComponents = connectedComponentsAlgo.stronglyConnectedSets();

        // For each set choose the best value, then add an entry for Node.source -> ImmutableDataValue in the final map.
        for(final Set<Node> sameValueNodes : connectedComponents) {
            final ImmutableDataValue dataValue = computeBestDataValue(sameValueNodes);
            DataflowUtils.putAll(sourceElementToStringAndUUIDInitValues, sameValueNodes, dataValue);
        }

        // Compute init expressions for enum and numeric types.
        // Compute the expressions for methods called in the source and super classes.
        calledMethodInfos = calledMethodsInfoProvider.getMethodsCalledInSourceClass();
        computeInitExpressionsForEnumAndNumericTypes(calledMethodInfos);

        // Compute the expressions for methods called in static helper methods.
        calledMethodInfos = calledMethodsInfoProvider.getMethodsCalledInStaticMethods();
        computeInitExpressionsForEnumAndNumericTypes(calledMethodInfos);

        // Compute the expressions for DI methods called with numeric/enum params.
        final Collection<InternalDependencyInteraction> internalDependencyInteractions = callExpressionsToDisMap.values();
        computeInitExpressionsForEnumAndNumericTypeDIs(internalDependencyInteractions);

        // Compute the expressions for DI methods called with references to JDK or known library constants.
        computeInitExpressionsForLibraryReferenceTypeDIs(internalDependencyInteractions);

        isInitialized = true;
    }

    private String getOnlyParamName(final PsiMethod calledMethod) {
        final PsiParameter[] parameters = calledMethod.getParameterList().getParameters();
        if(parameters.length != 1) {
            return null;
        }
        return parameters[0].getName();
    }

    private void computeInitExpressionsForEnumAndNumericTypeDIs(final Collection<InternalDependencyInteraction> internalDependencyInteractions) {
        for(final InternalDependencyInteraction dependencyInteraction : internalDependencyInteractions) {
            final PsiMethod calledMethod = dependencyInteraction.getPsiMethod();
            final PsiMethodCallExpression methodCallExpression = dependencyInteraction.getInternalMethodCallExpression().methodCallExpression();
            if(methodCallExpression == null) {
                continue;
            }
            final PsiParameter[] params = calledMethod.getParameterList().getParameters();
            final PsiExpressionList callExpressionsList = methodCallExpression.getArgumentList();
            final PsiExpression[] callExpressions = callExpressionsList.getExpressions();
            for(int i = 0; i < params.length && i < callExpressions.length; i++) {
                final PsiParameter formalParam = params[i];
                final PsiExpression actualParamExpression = callExpressions[i];
                final boolean isValid = isNumericOrEnumOrObject(formalParam.getType()) && isNumericOrEnum(actualParamExpression.getType());
                if(!isValid) {
                    // If this is not a numeric or enum type, continue.
                    continue;
                }
                if(sourceElementToEnumAndPrimitiveInitValue.containsKey(actualParamExpression)) {
                    // We've already processed this param in another iteration.
                    continue;
                }
                final PsiExpression expressionToUse = PsiUtil.deparenthesizeExpression(actualParamExpression);
                final PsiType sourceVarType = formalParam.getType();
                final String initExpression = constantInitExpressionValueProvider.getInitExpressionForMethodParam(sourceVarType, expressionToUse);
                if(initExpression == null) {
                    // This is not a constant.
                    continue;
                }
                sourceElementToEnumAndPrimitiveInitValue.put(actualParamExpression, initExpression);
            }
        }
    }

    private void computeInitExpressionsForLibraryReferenceTypeDIs(final Collection<InternalDependencyInteraction> internalDependencyInteractions) {
        for(final InternalDependencyInteraction dependencyInteraction : internalDependencyInteractions) {
            final PsiMethod calledMethod = dependencyInteraction.getPsiMethod();
            final PsiMethodCallExpression methodCallExpression = dependencyInteraction.getInternalMethodCallExpression().methodCallExpression();
            if(methodCallExpression == null) {
                continue;
            }
            final PsiParameter[] params = calledMethod.getParameterList().getParameters();
            final PsiExpressionList callExpressionsList = methodCallExpression.getArgumentList();
            final PsiExpression[] callExpressions = callExpressionsList.getExpressions();
            for(int i = 0; i < params.length && i < callExpressions.length; i++) {
                final PsiExpression actualParamExpression = callExpressions[i];
                final String referenceToLibraryConstant = getReferenceToLibraryConstant(actualParamExpression);
                if(referenceToLibraryConstant == null) {
                    continue;
                }
                if(sourceElementToLibraryReferenceValue.containsKey(actualParamExpression)) {
                    // We've already processed this param in another iteration.
                    continue;
                }
                sourceElementToLibraryReferenceValue.put(actualParamExpression, referenceToLibraryConstant);
            }
        }
    }

    private void computeInitExpressionsForEnumAndNumericTypes(final Collection<CalledMethodInfo> calledMethodInfos) {
        for(final CalledMethodInfo calledMethodInfo : calledMethodInfos) {
            final PsiMethod calledMethod = calledMethodInfo.getCalledMethod();
            if(!calledMethodInfo.getMethodReferenceExpressions().isEmpty()
                    || calledMethod.hasModifierProperty(PsiModifier.STATIC) || calledMethod.hasModifierProperty(PsiModifier.PRIVATE)) {
                continue;
            }
            if(!isRecognizedImmutableBuilderMethod(calledMethod)) {
                continue;
            }
            for(final PsiCall methodCallExpression : calledMethodInfo.getMethodCallExpressions()) {
                final PsiExpressionList callExpressionsList = methodCallExpression.getArgumentList();
                if(callExpressionsList == null) {
                    continue;
                }
                final PsiParameter[] params = calledMethod.getParameterList().getParameters();
                final PsiExpression[] callExpressions = callExpressionsList.getExpressions();
                for(int i = 0; i < params.length && i < callExpressions.length; i++) {
                    final PsiParameter formalParam = params[i];
                    if(!isNumericOrEnum(formalParam.getType()) || !isNumericOrEnum(callExpressions[i].getType())) {
                        // If this is not a numeric or enum type, continue.
                        continue;
                    }
                    if(sourceElementToEnumAndPrimitiveInitValue.containsKey(formalParam) || sourceElementToEnumAndPrimitiveInitValue.containsKey(callExpressions[i])) {
                        // We've already processed this param in another iteration.
                        continue;
                    }
                    // Get the target field.
                    final PsiField targetField = getTargetField(calledMethod);
                    if(targetField == null) {
                        continue;
                    }
                    final List<PsiParameter> sourceVars = sourceVariableProvider.getSourceVariables(targetField).stream()
                            .filter(x -> (x instanceof PsiParameter) && isNumericOrEnum(x.getType()))
                            .map(x -> (PsiParameter) x).collect(Collectors.toList());
                    if(!allCallsUseSameConstantValues(sourceVars)) {
                        continue;
                    }
                    // Choose the first value and add entries for the other method params that are the same type.
                    final ValueAndType valueToUse = getFirstConstantValue(sourceVars);
                    addValueEntries(sourceVars, valueToUse);
                }
            }
        }
    }

    private String getReferenceToLibraryConstant(final PsiExpression callExpression) {
        final PsiExpression expressionToUse = PsiUtil.deparenthesizeExpression(callExpression);
        if(!(expressionToUse instanceof final PsiReferenceExpression referenceExpression)) {
            return null;
        }
        final PsiElement refTarget = referenceExpression.resolve();
        if(!(refTarget instanceof final PsiField targetField)) {
            return null;
        }
        if(!(targetField.hasModifierProperty(PsiModifier.PUBLIC) && targetField.hasModifierProperty(PsiModifier.STATIC) && targetField.hasModifierProperty(PsiModifier.FINAL))) {
            return null;
        }
        final PsiClass topLevelClass = PsiTreeUtil.getTopmostParentOfType(targetField, PsiClass.class);
        if(topLevelClass == null) {
            return null;
        }
        final String qualifiedName = topLevelClass.getQualifiedName();
        if(qualifiedName == null) {
            return null;
        }
        final PsiClass containingClass = targetField.getContainingClass();
        if(containingClass == null) {
            return null;
        }
        final String containingClassQualifiedName = containingClass.getQualifiedName();
        return containingClassQualifiedName + "." + targetField.getName();
    }

    private boolean isRecognizedImmutableBuilderMethod(final PsiMethod calledMethod) {
        return isAWSBuilderMethod(calledMethod) || ProtobufUtils.isProtobufBuilderMethod(calledMethod);
    }

    private void addValueEntries(final List<PsiParameter> sourceVars, final ValueAndType valueToUse) {
        for(final PsiParameter sourceVar : sourceVars) {
            final PsiType type = sourceVar.getType();
            if(TypeConversionUtil.isAssignable(type, valueToUse.psiType())) {
                sourceElementToEnumAndPrimitiveInitValue.put(sourceVar, valueToUse.value());
            }
        }
    }

    private boolean isNumericOrEnumOrObject(final PsiType type) {
        if(isNumericOrEnum(type)) {
            return true;
        }
        final PsiClass psiClass = PsiUtil.resolveClassInType(type);
        if(psiClass == null) {
            return false;
        }
        return StringUtils.equals(psiClass.getQualifiedName(), JavaNames.JavaLangObject);
    }

    private static boolean isNumericOrEnum(final PsiType psiType) {
        return TypeConversionUtil.isEnumType(psiType)
                || TypeConversionUtil.isPrimitiveAndNotNull(psiType)
                || TypeConversionUtil.isPrimitiveWrapper(psiType);
    }

    private ValueAndType getFirstConstantValue(final List<PsiParameter> sourceVars) {
        for(final PsiParameter sourceVar : sourceVars) {
            final PsiMethod containingMethod = getContainingMethodForParameter(sourceVar);
            CalledMethodInfo calledMethodInfo = calledMethodsInfoProvider.getSourceAndSuperMethodCallInfo(containingMethod);
            ValueAndType firstConstantValue = getFirstConstantValue(sourceVar, containingMethod, calledMethodInfo);
            if(firstConstantValue != null) {
                return firstConstantValue;
            }

            calledMethodInfo = calledMethodsInfoProvider.getStaticMethodCallInfo(containingMethod);
            firstConstantValue = getFirstConstantValue(sourceVar, containingMethod, calledMethodInfo);
            if(firstConstantValue != null) {
                return firstConstantValue;
            }
        }
        return null;
    }

    private ValueAndType getFirstConstantValue(final PsiParameter sourceVar, final PsiMethod containingMethod, @Nullable final CalledMethodInfo calledMethodInfo) {
        if(calledMethodInfo == null) {
            return null;
        }
        for(final PsiCall methodCallExpression : calledMethodInfo.getMethodCallExpressions()) {
            final PsiExpression actualParam = getActualParam(containingMethod, methodCallExpression, sourceVar);
            if(actualParam == null) {
                continue;
            }
            final PsiExpression expressionToUse = PsiUtil.deparenthesizeExpression(actualParam);
            final PsiType sourceVarType = sourceVar.getType();
            final String initExpression = constantInitExpressionValueProvider.getInitExpressionForMethodParam(sourceVarType, expressionToUse);
            if(initExpression == null) {
                // This is not a constant.
                continue;
            }
            return new ValueAndType(sourceVarType, initExpression);
        }
        return null;
    }

    private boolean allCallsUseSameConstantValues(final List<PsiParameter> sourceVars) {
        final ValueAndType firstValue = getFirstConstantValue(sourceVars);
        if(firstValue == null) {
            return false;
        }
        for(final PsiParameter sourceVar : sourceVars) {
            final PsiMethod containingMethod = getContainingMethodForParameter(sourceVar);
            CalledMethodInfo calledMethodInfo = calledMethodsInfoProvider.getSourceAndSuperMethodCallInfo(containingMethod);
            if(!allCallsUseSameConstantValues(sourceVar, firstValue, containingMethod, calledMethodInfo)) {
                return false;
            }
            calledMethodInfo = calledMethodsInfoProvider.getStaticMethodCallInfo(containingMethod);
            if(!allCallsUseSameConstantValues(sourceVar, firstValue, containingMethod, calledMethodInfo)) {
                return false;
            }
        }
        return true;
    }

    private boolean allCallsUseSameConstantValues(final PsiParameter sourceVar, final ValueAndType firstValue, final PsiMethod containingMethod, @Nullable final CalledMethodInfo calledMethodInfo) {
        if(calledMethodInfo == null) {
            return true;
        }
        if(!calledMethodInfo.getMethodReferenceExpressions().isEmpty()) {
            return false;
        }
        for(final PsiCall methodCallExpression : calledMethodInfo.getMethodCallExpressions()) {
            final PsiExpression actualParam = getActualParam(containingMethod, methodCallExpression, sourceVar);
            if(actualParam == null) {
                continue;
            }
            final PsiExpression expressionToUse = PsiUtil.deparenthesizeExpression(actualParam);
            final String initExpression = constantInitExpressionValueProvider.getInitExpressionForMethodParam(sourceVar.getType(), expressionToUse);
            if(initExpression == null) {
                // This is not a constant.
                return false;
            }
            if(!StringUtils.equals(firstValue.value(), initExpression)) {
                return false;
            }
        }
        return true;
    }

    private PsiExpression getActualParam(final PsiMethod containingMethod, final PsiCall methodCallExpression, final PsiParameter sourceVar) {
        final int index = getIndexOfParamWithName(containingMethod, sourceVar.getName());
        if(index == -1) {
            return null;
        }
        final PsiExpressionList argumentList = methodCallExpression.getArgumentList();
        if(argumentList == null) {
            return null;
        }
        final PsiExpression[] expressions = argumentList.getExpressions();
        if(expressions.length <= index) {
            // The method call is incomplete. There is no actual param for this formal param.
            return null;
        }
        return expressions[index];
    }

    private boolean shouldAnalyzeMethodParams(final PsiMethod calledMethod) {
        if(calledMethod.hasModifierProperty(PsiModifier.STATIC) || calledMethod.hasModifierProperty(PsiModifier.PRIVATE)) {
            return false;
        }
        final PsiParameter onlyParam = getOnlyParam(calledMethod);
        if(onlyParam == null) {
            return false;
        }
        final PsiField targetField = sourceVariableProvider.getTargetField(calledMethod, onlyParam);
        if(targetField != null) {
            return true;
        }
        final PsiVariable lombokTargetElement = getLombokTargetVariable(calledMethod);
        return lombokTargetElement != null;
    }

    private PsiField getTargetField(final PsiMethod calledMethod) {
        if(calledMethod.hasModifierProperty(PsiModifier.STATIC) || calledMethod.hasModifierProperty(PsiModifier.PRIVATE)) {
            return null;
        }
        final PsiParameter onlyParam = getOnlyParam(calledMethod);
        if(onlyParam == null) {
            return null;
        }
        return sourceVariableProvider.getTargetField(calledMethod, onlyParam);
    }

    private void computeSameValueSetsForMethodReturnStatements(
            final Map<PsiMethodCallExpression, InternalDependencyInteraction> callExpressionsToDisMap,
            final List<Set<Node>> ret, final Set<Node> alreadyVisitedNodes,
            final PsiMethod topLevelMethod) {
        if(topLevelMethod.hasModifierProperty(PsiModifier.PRIVATE) || topLevelMethod.isConstructor()) {
            return;
        }
        final TypeInfo methodReturnTypeInfo = DataflowUtils.getTypeInfo(topLevelMethod.getReturnType());
        if(!methodReturnTypeInfo.isStringOrUUID()) {
            return;
        }
        final Set<Node> sameValueNodes = new HashSet<>();
        final Node formalParamNode = new Node(
                topLevelMethod, methodReturnTypeInfo.isUUID(),
                "result",
                "result",
                ValueSource.SourceMethodReturnValue);
        sameValueNodes.add(formalParamNode);
        final PsiReturnStatement[] returnStatements = PsiUtil.findReturnStatements(topLevelMethod);
        for(final PsiReturnStatement returnStatement : returnStatements) {
            final PsiExpression returnValue = returnStatement.getReturnValue();
            if(returnValue == null) {
                continue;
            }
            // Determine if the return statement value is an end node.
            final ExpressionInfo expressionInfo = DataflowUtils.removeExtras(returnValue);
            final EndNodeInfo endNodeInfo = getEndNodeInfo(callExpressionsToDisMap,
                    expressionInfo.getExpressionToUse(), expressionInfo.isUUID(),
                    "result", ValueSource.SourceMethodReturnValue);
            if(endNodeInfo != null) {
                sameValueNodes.addAll(endNodeInfo.endNodes());
                continue;
            }

            // Follow the return statement value recursively if needed.
            final Node actualParamNode = new Node(
                    expressionInfo.getExpressionToUse(), expressionInfo.isUUID(),
                    "result",
                    "result",
                    ValueSource.SourceMethodReturnValue);
            sameValueNodes.add(actualParamNode);
            determineNodesRecursivelyForReturnValue(callExpressionsToDisMap, topLevelMethod, expressionInfo.getExpressionToUse(), alreadyVisitedNodes, sameValueNodes);
            // Add any items that were explored recursively to our alreadyVisitedNodes set. Subsequent recursive runs
            // that intersect one of the graph nodes we've already explored (one of the nodes in recursiveNodes) can
            // avoid following the same part of the graph again.
            alreadyVisitedNodes.addAll(sameValueNodes);
        }
        ret.add(sameValueNodes);
    }

    private void computeSameValueSetsForDi(
            final Map<PsiMethodCallExpression, InternalDependencyInteraction> callExpressionsToDisMap,
            final List<Set<Node>> ret,
            final Set<Node> alreadyVisitedNodes,
            final InternalDependencyInteraction internalDependencyInteraction) {
        final PsiMethod calledMethod = internalDependencyInteraction.getPsiMethod();
        final ValueSource valueSource;
        if(calledMethod instanceof PsiCompiledElement || !calledMethod.isWritable()) {
            valueSource = ValueSource.DependencyInteractionFormalParamNotWritable;
        } else {
            valueSource = ValueSource.DependencyInteractionFormalParamWritable;
        }
        computeSameValueSetsForMethodParams(callExpressionsToDisMap,
                ret,
                alreadyVisitedNodes,
                calledMethod,
                internalDependencyInteraction.getInternalMethodCallExpression().methodCallExpression(), valueSource);
    }

    private void computeSameValueSetsForMethodParams(
            final Map<PsiMethodCallExpression, InternalDependencyInteraction> callExpressionsToDisMap,
            final List<Set<Node>> ret,
            final Set<Node> alreadyVisitedNodes,
            final PsiMethod psiMethod,
            final PsiCall methodCallExpression, final ValueSource valueSource) {
        final PsiParameter[] params = psiMethod.getParameterList().getParameters();
        if(methodCallExpression == null) {
            return;
        }
        final PsiExpressionList callExpressionsList = methodCallExpression.getArgumentList();
        if(callExpressionsList == null) {
            return;
        }
        if(DataflowUtils.isCommonMethod(psiMethod)) {
            return;
        }
        final PsiExpression[] callExpressions = callExpressionsList.getExpressions();
        for(int i = 0; i < params.length && i < callExpressions.length; i++) {
            final PsiParameter formalParam = params[i];
            final TypeInfo formalParamTypeInfo = DataflowUtils.getTypeInfoForParameter(formalParam);
            final PsiExpression actualParam = callExpressions[i];
            final ExpressionInfo actualParamExpressionInfo = DataflowUtils.removeExtras(actualParam);
            if(!formalParamTypeInfo.isStringOrUUID() && !actualParamExpressionInfo.isStringOrUUID()) {
                continue;
            }
            // Compute the same value nodes for this parameter.
            final Set<Node> sameValueNodes = new HashSet<>();

            // Create nodes for the formal parameter.
            final List<Node> formalParamNodes = createFormalParamNodes(psiMethod, methodCallExpression, formalParam, formalParamTypeInfo.isUUID(), valueSource);
            sameValueNodes.addAll(formalParamNodes);

            if(!shouldAddFormalParamToGraph(valueSource) && actualParam != actualParamExpressionInfo.getExpressionToUse()) {
                // We skipped adding the formal param because it is a DI call param. The TypeCreator will use the actual param
                // to compute the value for the method call. If the actual param is different from the expressionToUse,
                // we need to add it to the graph. If it's the same, we want to avoid adding it. The expression will
                // be added after this; if we add it first, the subsequent calls to sameValueNodes.add(..) won't add
                // their item to the set, because it's already there.
                final Node actualParamNode = new Node(
                        actualParam, actualParamExpressionInfo.isUUID(),
                        formalParam.getName(),
                        formalParam.getName(),
                        ValueSource.ActualParam);
                sameValueNodes.add(actualParamNode);
            }

            // Determine if the parameter is an end node.
            final EndNodeInfo endNodeInfo = getEndNodeInfo(callExpressionsToDisMap, actualParamExpressionInfo.getExpressionToUse(), actualParamExpressionInfo.isUUID(), formalParam.getName(), ValueSource.ActualParam);
            if(endNodeInfo != null) {
                sameValueNodes.addAll(endNodeInfo.endNodes());
                ret.add(sameValueNodes);
                continue;
            }
            // Follow the parameter recursively if needed.
            final Node actualParamNode = new Node(
                    actualParamExpressionInfo.getExpressionToUse(), actualParamExpressionInfo.isUUID(),
                    formalParam.getName(),
                    formalParam.getName(),
                    ValueSource.ActualParam);
            sameValueNodes.add(actualParamNode);
            determineNodesRecursively(callExpressionsToDisMap, actualParamExpressionInfo.getExpressionToUse(), alreadyVisitedNodes, sameValueNodes);
            // Add any items that were explored recursively to our alreadyVisitedNodes set.
            alreadyVisitedNodes.addAll(sameValueNodes);
            ret.add(sameValueNodes);
        }
    }

    private void computeSameValueSetsForMethodParamsLight(
            final Map<PsiMethodCallExpression, InternalDependencyInteraction> callExpressionsToDisMap,
            final List<Set<Node>> ret,
            final PsiMethod psiMethod,
            final PsiCall methodCallExpression, final ValueSource valueSource) {
        final PsiParameter[] params = psiMethod.getParameterList().getParameters();
        if(methodCallExpression == null) {
            return;
        }
        final PsiExpressionList callExpressionsList = methodCallExpression.getArgumentList();
        if(callExpressionsList == null) {
            return;
        }
        if(DataflowUtils.isCommonMethod(psiMethod)) {
            return;
        }
        final PsiExpression[] callExpressions = callExpressionsList.getExpressions();
        for(int i = 0; i < params.length && i < callExpressions.length; i++) {
            final PsiParameter formalParam = params[i];
            final TypeInfo formalParamTypeInfo = DataflowUtils.getTypeInfoForParameter(formalParam);
            final ExpressionInfo actualParamExpressionInfo = DataflowUtils.removeExtras(callExpressions[i]);
            if(!formalParamTypeInfo.isStringOrUUID() && !actualParamExpressionInfo.isStringOrUUID()) {
                continue;
            }
            // Compute the same value nodes for this parameter.
            final Set<Node> sameValueNodes = new HashSet<>();

            // Create nodes for the formal parameter.
            final List<Node> formalParamNodes = createFormalParamNodes(psiMethod, methodCallExpression, formalParam, formalParamTypeInfo.isUUID(), valueSource);
            sameValueNodes.addAll(formalParamNodes);

            // Determine if the parameter is an end node.
            final EndNodeInfo endNodeInfo = getEndNodeInfo(callExpressionsToDisMap, actualParamExpressionInfo.getExpressionToUse(), actualParamExpressionInfo.isUUID(), formalParam.getName(), ValueSource.ActualParam);
            if(endNodeInfo != null) {
                sameValueNodes.addAll(endNodeInfo.endNodes());
                ret.add(sameValueNodes);
                continue;
            }
            // Follow the parameter recursively if needed.
            final Node actualParamNode = new Node(
                    actualParamExpressionInfo.getExpressionToUse(), actualParamExpressionInfo.isUUID(),
                    formalParam.getName(),
                    formalParam.getName(),
                    ValueSource.ActualParam);
            sameValueNodes.add(actualParamNode);
            determineNodesRecursivelyLight(callExpressionsToDisMap, actualParamExpressionInfo.getExpressionToUse(), sameValueNodes);
            ret.add(sameValueNodes);
        }
    }

    private EndNodeInfo getEndNodeInfo(
            final Map<PsiMethodCallExpression, InternalDependencyInteraction> callExpressionsToDisMap,
            final PsiExpression actualParamCallExpression,
            final boolean isUUID,
            final String formalParamName,
            final ValueSource actualParamValueSource) {
        // Determine if the param is a literal String value; e.g. fooService.get("hardcodedStr").
        final String constantStringValue = determineStringConstant(actualParamCallExpression);
        if(constantStringValue != null) {
            final boolean isUUIDConstant = isUUID || DataflowUtils.isUUIDString(constantStringValue);
            final Node actualParamNode = new Node(
                    actualParamCallExpression, isUUIDConstant,
                    formalParamName,
                    constantStringValue,
                    ValueSource.ConstantValue);
            return new EndNodeInfo(Collections.singletonList(actualParamNode));
        }

        // Determine if the param contains a UUID constant.
        final String uuidConstant = determineUUIDConstantInfo(actualParamCallExpression);
        if(uuidConstant != null) {
            final Node actualParamNode = new Node(
                    actualParamCallExpression, true,
                    formalParamName,
                    uuidConstant,
                    ValueSource.ConstantValue);
            return new EndNodeInfo(Collections.singletonList(actualParamNode));
        }

        // Determine if the param is a getter method call.
        final EndNodeInfo getterCallInfo = determineGetterCallInfo(actualParamCallExpression);
        if(getterCallInfo != null) {
            final Node actualParamNode = new Node(
                    actualParamCallExpression, isUUID,
                    formalParamName,
                    formalParamName,
                    actualParamValueSource);
            final List<Node> nodes = new ArrayList<>();
            nodes.add(actualParamNode);
            nodes.addAll(getterCallInfo.endNodes());
            return new EndNodeInfo(nodes);
        }

        // Determine if the param is another DI call.
        final EndNodeInfo endNodeInfo = determineDiCallInfo(callExpressionsToDisMap, actualParamCallExpression);
        if(endNodeInfo != null) {
            final Node actualParamNode = new Node(
                    actualParamCallExpression, isUUID,
                    formalParamName,
                    formalParamName,
                    actualParamValueSource);
            final List<Node> nodes = new ArrayList<>();
            nodes.add(actualParamNode);
            nodes.addAll(endNodeInfo.endNodes());
            return new EndNodeInfo(nodes);
        }

        // Determine if the param is a reference to a member field.
        final EndNodeInfo memberFieldInfo = determineMemberFieldInfo(actualParamCallExpression);
        if(memberFieldInfo != null) {
            final Node actualParamNode = new Node(
                    actualParamCallExpression, isUUID,
                    formalParamName,
                    formalParamName,
                    actualParamValueSource);
            final List<Node> nodes = new ArrayList<>();
            nodes.add(actualParamNode);
            nodes.addAll(memberFieldInfo.endNodes());
            return new EndNodeInfo(nodes);
        }
        return null;
    }

    private EndNodeInfo determineMemberFieldInfo(final PsiExpression argumentExpression) {
        if(!(argumentExpression instanceof final PsiReferenceExpression psiReferenceExpression)) {
            return null;
        }
        final PsiElement target = psiReferenceExpression.resolve();
        if(!(target instanceof PsiField)) {
            return null;
        }
        final PsiField targetField = CompiledUtils.getFieldWithSourceCode((PsiField) target);
        final Set<PsiVariable> sourceVariables = sourceVariableProvider.getSourceVariables(targetField);
        final Set<Node> variableSources = getNodesFromSourceVariables(sourceVariables);
        return new EndNodeInfo(variableSources);
    }

    private void determineNodesRecursively(
            final Map<PsiMethodCallExpression, InternalDependencyInteraction> callExpressionsToDisMap,
            final PsiExpression actualParamCallExpression,
            final Set<Node> alreadyVisitedNodes,
            final Set<Node> ret) {
        // Determine if we need to recursively follow references for the actualParamCallExpression.
        if(actualParamCallExpression instanceof final PsiMethodCallExpression psiMethodCallExpression) {
            if(shouldFollow(psiMethodCallExpression)) {
                final SliceAnalysisParams params = createSliceAnalysisParams();
                final JavaSliceUsage rootUsage = JavaSliceUsage.createRootUsage(actualParamCallExpression, params);
                determineNodesRecursivelyImpl(rootUsage, callExpressionsToDisMap, alreadyVisitedNodes, ret);
            }
        } else if(actualParamCallExpression instanceof final PsiReferenceExpression psiReferenceExpression) {
            final PsiElement target = psiReferenceExpression.resolve();
            if(target == null) {
                return;
            }
            if(target instanceof PsiLocalVariable) {
                final SliceAnalysisParams params = createSliceAnalysisParams();
                final JavaSliceUsage rootUsage = JavaSliceUsage.createRootUsage(actualParamCallExpression, params);
                determineNodesRecursivelyImpl(rootUsage, callExpressionsToDisMap, alreadyVisitedNodes, ret);
                return;
            }
            if(target instanceof PsiParameter) {
                if(!DataflowUtils.isMethodParamOrPatternVariable((PsiParameter) target)) {
                    return;
                }
                final SliceAnalysisParams params = createSliceAnalysisParams();
                final JavaSliceUsage rootUsage = JavaSliceUsage.createRootUsage(actualParamCallExpression, params);
                determineNodesRecursivelyImpl(rootUsage, callExpressionsToDisMap, alreadyVisitedNodes, ret);
            }
        }
    }

    private void determineNodesRecursivelyForReturnValue(
            final Map<PsiMethodCallExpression, InternalDependencyInteraction> callExpressionsToDisMap,
            final PsiMethod topLevelMethod,
            final PsiExpression actualParamCallExpression,
            final Set<Node> alreadyVisitedNodes,
            final Set<Node> ret) {
        // Determine if we need to recursively follow references for the actualParamCallExpression.
        if(actualParamCallExpression instanceof final PsiMethodCallExpression psiMethodCallExpression) {
            if(shouldFollow(psiMethodCallExpression)) {
                final SliceAnalysisParams params = createSliceAnalysisParams();
                final JavaSliceUsage rootUsage = JavaSliceUsage.createRootUsage(actualParamCallExpression, params);
                determineNodesRecursivelyForReturnValueImpl(rootUsage, callExpressionsToDisMap, topLevelMethod, alreadyVisitedNodes, ret);
            }
        } else if(actualParamCallExpression instanceof final PsiReferenceExpression psiReferenceExpression) {
            final PsiElement target = psiReferenceExpression.resolve();
            if(target == null) {
                return;
            }
            if(target instanceof PsiLocalVariable) {
                final SliceAnalysisParams params = createSliceAnalysisParams();
                final JavaSliceUsage rootUsage = JavaSliceUsage.createRootUsage(actualParamCallExpression, params);
                determineNodesRecursivelyForReturnValueImpl(rootUsage, callExpressionsToDisMap, topLevelMethod, alreadyVisitedNodes, ret);
                return;
            }
            if(target instanceof PsiParameter) {
                if(!DataflowUtils.isMethodParamOrPatternVariable((PsiParameter) target)) {
                    return;
                }
                final SliceAnalysisParams params = createSliceAnalysisParams();
                final JavaSliceUsage rootUsage = JavaSliceUsage.createRootUsage(actualParamCallExpression, params);
                determineNodesRecursivelyForReturnValueImpl(rootUsage, callExpressionsToDisMap, topLevelMethod, alreadyVisitedNodes, ret);
            }
        }
    }

    private void determineNodesRecursivelyLight(
            final Map<PsiMethodCallExpression, InternalDependencyInteraction> callExpressionsToDisMap,
            final PsiExpression actualParamCallExpression,
            final Set<Node> ret) {
        if(actualParamCallExpression instanceof final PsiReferenceExpression psiReferenceExpression) {
            final PsiElement target = psiReferenceExpression.resolve();
            if(target == null) {
                return;
            }
            if(target instanceof final PsiLocalVariable localVariable) {
                final PsiExpression localVariableInitializer = localVariable.getInitializer();
                if(localVariableInitializer == null) {
                    return;
                }
                final ExpressionInfo localVariableInfo = DataflowUtils.removeExtras(localVariableInitializer);
                final EndNodeInfo endNodeInfo = getEndNodeInfo(callExpressionsToDisMap, localVariableInfo.getExpressionToUse(), localVariableInfo.isUUID(), localVariable.getName(), ValueSource.IntermediateValue);
                if(endNodeInfo != null) {
                    ret.addAll(endNodeInfo.endNodes());
                }
            }
        }
    }

    /**
     * @param rootUsage               the node to analyze.
     * @param callExpressionsToDisMap the way to determine if we've arrived at a DI call.
     * @param alreadyVisitedNodes     the set of nodes included in other Sets. If we find a node that is already here,
     *                                we need to add it to our set, and then stop recursing. The nodes in our set will
     *                                be joined with the elements of the existing set when we merge them later.
     * @param ret                     the set containing the same value nodes.
     */
    private void determineNodesRecursivelyImpl(
            final SliceUsage rootUsage,
            final Map<PsiMethodCallExpression, InternalDependencyInteraction> callExpressionsToDisMap,
            final Set<Node> alreadyVisitedNodes,
            final Set<Node> ret) {
        final List<SliceUsage> children = new ArrayList<>();
        ProgressManager.getInstance().executeNonCancelableSection(
                () -> safeAddChildren(rootUsage, children));

        for(final SliceUsage usage : children) {
            final PsiElement target = usage.getElement();
            if(target instanceof final PsiExpression actualParamCallExpression) {
                final ExpressionInfo expressionInfo = DataflowUtils.removeExtras(actualParamCallExpression);
                final PsiExpression expressionToUse = expressionInfo.getExpressionToUse();
                final boolean expressionToUseIsSameAsActualParam = actualParamCallExpression == expressionToUse;

                // Determine if the parameter is an end node.
                final EndNodeInfo endNodeInfo = getEndNodeInfo(callExpressionsToDisMap,
                        expressionToUse, expressionInfo.isUUID(), "value",
                        ValueSource.IntermediateValue);
                if(endNodeInfo != null) {
                    ret.addAll(endNodeInfo.endNodes());
                    continue;
                }

                if(expressionToUse instanceof final PsiMethodCallExpression psiMethodCallExpression) {
                    if(shouldFollow(psiMethodCallExpression)) {
                        final Node actualParamNode = new Node(
                                expressionToUse, expressionInfo.isUUID(),
                                "value",
                                "value",
                                ValueSource.InternalMethodCall);
                        if(ret.contains(actualParamNode)) {
                            // We've already processed this node (either in the call stack or in an
                            // overlapping subtree).
                            continue;
                        }
                        if(alreadyVisitedNodes.contains(actualParamNode)) {
                            // We processed this node while computing same value nodes for another DI param.
                            // Add it to our same value set and continue. We don't need to recursively analyze this, because
                            // our node will get merged with the other nodes in the final step.
                            ret.add(actualParamNode);
                            continue;
                        }
                        ret.add(actualParamNode);
                        if(expressionToUseIsSameAsActualParam) {
                            // If we didn't have to remove any extras, just use the current dataflow path to follow the
                            // refs.
                            determineNodesRecursivelyImpl(usage, callExpressionsToDisMap, alreadyVisitedNodes, ret);
                        } else {
                            // We did need to remove extras; e.g. toString() calls, etc. Invoke a new, top-level
                            // recursive traversal on the expressionToUse.
                            determineNodesRecursively(callExpressionsToDisMap, expressionToUse,
                                    alreadyVisitedNodes, ret);
                        }
                    }
                    continue;
                }
                if(expressionToUse instanceof PsiReferenceExpression) {
                    final PsiElement resolvedTarget = ((PsiReferenceExpression) expressionToUse).resolve();
                    if(resolvedTarget instanceof final PsiParameter formalParam) {
                        if(!DataflowUtils.isMethodParamOrPatternVariable(formalParam)) {
                            continue;
                        }
                        final TypeInfo formalParamTypeInfo = DataflowUtils.getTypeInfoForParameter(formalParam);
                        if(!formalParamTypeInfo.isStringOrUUID()) {
                            continue;
                        }
                        final Node actualParamNode = new Node(
                                expressionToUse, expressionInfo.isUUID(),
                                "value",
                                "value",
                                ValueSource.IntermediateValue);
                        if(ret.contains(actualParamNode)) {
                            // We've already processed this node (either in the call stack or in an
                            // overlapping subtree).
                            continue;
                        }
                        if(alreadyVisitedNodes.contains(actualParamNode)) {
                            // We processed this node while computing same value nodes for another DI param.
                            // Add it to our same value set and continue. We don't need to recursively analyze this, because
                            // our node will get merged with the other nodes in the final step.
                            ret.add(actualParamNode);
                            continue;
                        }
                        ret.add(actualParamNode);
                        if(expressionToUseIsSameAsActualParam) {
                            // If we didn't have to remove any extras, just use the current dataflow path to follow the
                            // refs.
                            determineNodesRecursivelyImpl(usage, callExpressionsToDisMap, alreadyVisitedNodes, ret);
                        } else {
                            // We did need to remove extras; e.g. toString() calls, etc. Invoke a new, top-level
                            // recursive traversal on the expressionToUse.
                            determineNodesRecursively(callExpressionsToDisMap, expressionToUse,
                                    alreadyVisitedNodes, ret);
                        }
                        continue;
                    }
                    if(resolvedTarget instanceof final PsiLocalVariable localVariable) {
                        final TypeInfo localVariableTypeInfo = DataflowUtils.getTypeInfoForLocalVariable(localVariable);
                        if(!localVariableTypeInfo.isStringOrUUID()) {
                            continue;
                        }
                        final Node actualParamNode = new Node(
                                expressionToUse, false,
                                "value",
                                "value",
                                ValueSource.IntermediateValue);
                        if(ret.contains(actualParamNode)) {
                            // We've already processed this node (either in the call stack or in an
                            // overlapping subtree).
                            continue;
                        }
                        if(alreadyVisitedNodes.contains(actualParamNode)) {
                            // We processed this node while computing same value nodes for another DI param.
                            // Add it to our same value set and continue. We don't need to recursively analyze this, because
                            // our node will get merged with the other nodes in the final step.
                            ret.add(actualParamNode);
                            continue;
                        }
                        ret.add(actualParamNode);
                        if(expressionToUseIsSameAsActualParam) {
                            // If we didn't have to remove any extras, just use the current dataflow path to follow the
                            // refs.
                            determineNodesRecursivelyImpl(usage, callExpressionsToDisMap, alreadyVisitedNodes, ret);
                        } else {
                            // We did need to remove extras; e.g. toString() calls, etc. Invoke a new, top-level
                            // recursive traversal on the expressionToUse.
                            determineNodesRecursively(callExpressionsToDisMap, expressionToUse,
                                    alreadyVisitedNodes, ret);
                        }
                        continue;
                    }
                    continue;
                }
                continue;
            } // end instanceof PsiExpression
            if(target instanceof final PsiParameter formalParam) {
                if(!DataflowUtils.isMethodParamOrPatternVariable(formalParam)) {
                    continue;
                }
                final TypeInfo formalParamTypeInfo = DataflowUtils.getTypeInfoForParameter(formalParam);
                if(!formalParamTypeInfo.isStringOrUUID()) {
                    continue;
                }
                final ValueSource valueSource = determineValueSourceForParam(formalParam);
                final Node formalParamNode = new Node(
                        formalParam, formalParamTypeInfo.isUUID(),
                        formalParam.getName(),
                        formalParam.getName(),
                        valueSource);
                if(ret.contains(formalParamNode)) {
                    // We've already processed this node (either in the call stack or in an
                    // overlapping subtree).
                    continue;
                }
                if(alreadyVisitedNodes.contains(formalParamNode)) {
                    // We processed this node while computing same value nodes for another DI param.
                    // Add it to our same value set and continue. We don't need to recursively analyze this, because
                    // our node will get merged with the other nodes in the final step.
                    ret.add(formalParamNode);
                    continue;
                }
                ret.add(formalParamNode);
                // Determine if this is an instanceof expression with a pattern (Java 14+).
                // For example: if(fooId instanceof UUID fooAsType) {..}
                // We want to start a new recursive traversal to follow fooId.
                final InstanceOfPatternExpressionInfo instanceOfPatternExpressionInfo = DataflowUtils.getInstanceOfPatternExpressionInfo(formalParam);
                if(instanceOfPatternExpressionInfo != null) {
                    final ExpressionInfo expressionInfo = DataflowUtils.removeExtras(instanceOfPatternExpressionInfo.targetToUse(), instanceOfPatternExpressionInfo.isUUID());
                    final EndNodeInfo endNodeInfo = getEndNodeInfo(callExpressionsToDisMap,
                            expressionInfo.getExpressionToUse(),
                            expressionInfo.isUUID(), formalParam.getName(), ValueSource.IntermediateValue);
                    if(endNodeInfo != null) {
                        // The LHS of the instanceof check is an end node. Add it to our set and continue.
                        ret.addAll(endNodeInfo.endNodes());
                        continue;
                    }
                    final Node actualParamNode = new Node(
                            expressionInfo.getExpressionToUse(), expressionInfo.isUUID(),
                            "value",
                            "value",
                            ValueSource.IntermediateValue);
                    ret.add(actualParamNode);
                    determineNodesRecursively(callExpressionsToDisMap, expressionInfo.getExpressionToUse(),
                            alreadyVisitedNodes, ret);
                    continue;
                }
                determineNodesRecursivelyImpl(usage, callExpressionsToDisMap, alreadyVisitedNodes, ret);
                continue;
            }
            if(target instanceof final PsiLocalVariable localVariable) {
                final TypeInfo localVariableInfo = DataflowUtils.getTypeInfoForLocalVariable(localVariable);
                if(!localVariableInfo.isStringOrUUID()) {
                    continue;
                }
                final Node localVariableNode = new Node(
                        localVariable, localVariableInfo.isUUID(),
                        localVariable.getName(),
                        localVariable.getName(),
                        ValueSource.IntermediateValue);
                if(ret.contains(localVariableNode)) {
                    // We've already processed this node (either in the call stack or in an
                    // overlapping subtree).
                    continue;
                }
                if(alreadyVisitedNodes.contains(localVariableNode)) {
                    // We processed this node while computing same value nodes for another DI param.
                    // Add it to our same value set and continue. We don't need to recursively analyze this, because
                    // our node will get merged with the other nodes in the final step.
                    ret.add(localVariableNode);
                    continue;
                }
                ret.add(localVariableNode);
                determineNodesRecursivelyImpl(usage, callExpressionsToDisMap, alreadyVisitedNodes, ret);
            }
        }
    }

    private void determineNodesRecursivelyForReturnValueImpl(
            final SliceUsage rootUsage,
            final Map<PsiMethodCallExpression, InternalDependencyInteraction> callExpressionsToDisMap,
            final PsiMethod topLevelMethod,
            final Set<Node> alreadyVisitedNodes,
            final Set<Node> ret) {
        final List<SliceUsage> children = new ArrayList<>();
        ProgressManager.getInstance().executeNonCancelableSection(
                () -> safeAddChildren(rootUsage, children));

        for(final SliceUsage usage : children) {
            final PsiElement target = usage.getElement();
            if(target instanceof final PsiExpression actualParamCallExpression) {
                final ExpressionInfo expressionInfo = DataflowUtils.removeExtras(actualParamCallExpression);
                final PsiExpression expressionToUse = expressionInfo.getExpressionToUse();
                final boolean expressionToUseIsSameAsActualParam = actualParamCallExpression == expressionToUse;

                // Determine if the parameter is an end node.
                final EndNodeInfo endNodeInfo = getEndNodeInfo(callExpressionsToDisMap,
                        expressionToUse, expressionInfo.isUUID(), "value",
                        ValueSource.IntermediateValue);
                if(endNodeInfo != null) {
                    ret.addAll(endNodeInfo.endNodes());
                    continue;
                }

                if(expressionToUse instanceof final PsiMethodCallExpression psiMethodCallExpression) {
                    if(shouldFollow(psiMethodCallExpression)) {
                        final Node actualParamNode = new Node(
                                expressionToUse, expressionInfo.isUUID(),
                                "value",
                                "value",
                                ValueSource.InternalMethodCall);
                        if(ret.contains(actualParamNode)) {
                            // We've already processed this node (either in the call stack or in an
                            // overlapping subtree).
                            continue;
                        }
                        if(alreadyVisitedNodes.contains(actualParamNode)) {
                            // We processed this node while computing same value nodes for another DI param.
                            // Add it to our same value set and continue. We don't need to recursively analyze this, because
                            // our node will get merged with the other nodes in the final step.
                            ret.add(actualParamNode);
                            continue;
                        }
                        ret.add(actualParamNode);
                        if(expressionToUseIsSameAsActualParam) {
                            // If we didn't have to remove any extras, just use the current dataflow path to follow the
                            // refs.
                            determineNodesRecursivelyForReturnValueImpl(usage, callExpressionsToDisMap, topLevelMethod, alreadyVisitedNodes, ret);
                        } else {
                            // We did need to remove extras; e.g. toString() calls, etc. Invoke a new, top-level
                            // recursive traversal on the expressionToUse.
                            determineNodesRecursivelyForReturnValue(callExpressionsToDisMap, topLevelMethod, expressionToUse,
                                    alreadyVisitedNodes, ret);
                        }
                    }
                    continue;
                }
                if(expressionToUse instanceof PsiReferenceExpression) {
                    final PsiElement resolvedTarget = ((PsiReferenceExpression) expressionToUse).resolve();
                    if(resolvedTarget instanceof final PsiParameter formalParam) {
                        if(!DataflowUtils.isMethodParamOrPatternVariable(formalParam)) {
                            continue;
                        }
                        final TypeInfo formalParamTypeInfo = DataflowUtils.getTypeInfoForParameter(formalParam);
                        if(!formalParamTypeInfo.isStringOrUUID()) {
                            continue;
                        }
                        final Node actualParamNode = new Node(
                                expressionToUse, expressionInfo.isUUID(),
                                "value",
                                "value",
                                ValueSource.IntermediateValue);
                        if(ret.contains(actualParamNode)) {
                            // We've already processed this node (either in the call stack or in an
                            // overlapping subtree).
                            continue;
                        }
                        if(alreadyVisitedNodes.contains(actualParamNode)) {
                            // We processed this node while computing same value nodes for another DI param.
                            // Add it to our same value set and continue. We don't need to recursively analyze this, because
                            // our node will get merged with the other nodes in the final step.
                            ret.add(actualParamNode);
                            continue;
                        }
                        ret.add(actualParamNode);
                        if(expressionToUseIsSameAsActualParam) {
                            // If we didn't have to remove any extras, just use the current dataflow path to follow the
                            // refs.
                            determineNodesRecursivelyForReturnValueImpl(usage, callExpressionsToDisMap, topLevelMethod, alreadyVisitedNodes, ret);
                        } else {
                            // We did need to remove extras; e.g. toString() calls, etc. Invoke a new, top-level
                            // recursive traversal on the expressionToUse.
                            determineNodesRecursivelyForReturnValue(callExpressionsToDisMap, topLevelMethod, expressionToUse,
                                    alreadyVisitedNodes, ret);
                        }
                        continue;
                    }
                    if(resolvedTarget instanceof final PsiLocalVariable localVariable) {
                        final TypeInfo localVariableTypeInfo = DataflowUtils.getTypeInfoForLocalVariable(localVariable);
                        if(!localVariableTypeInfo.isStringOrUUID()) {
                            continue;
                        }
                        final Node actualParamNode = new Node(
                                expressionToUse, false,
                                "value",
                                "value",
                                ValueSource.IntermediateValue);
                        if(ret.contains(actualParamNode)) {
                            // We've already processed this node (either in the call stack or in an
                            // overlapping subtree).
                            continue;
                        }
                        if(alreadyVisitedNodes.contains(actualParamNode)) {
                            // We processed this node while computing same value nodes for another DI param.
                            // Add it to our same value set and continue. We don't need to recursively analyze this, because
                            // our node will get merged with the other nodes in the final step.
                            ret.add(actualParamNode);
                            continue;
                        }
                        ret.add(actualParamNode);
                        if(expressionToUseIsSameAsActualParam) {
                            // If we didn't have to remove any extras, just use the current dataflow path to follow the
                            // refs.
                            determineNodesRecursivelyForReturnValueImpl(usage, callExpressionsToDisMap, topLevelMethod, alreadyVisitedNodes, ret);
                        } else {
                            // We did need to remove extras; e.g. toString() calls, etc. Invoke a new, top-level
                            // recursive traversal on the expressionToUse.
                            determineNodesRecursivelyForReturnValue(callExpressionsToDisMap, topLevelMethod, expressionToUse,
                                    alreadyVisitedNodes, ret);
                        }
                        continue;
                    }
                    continue;
                }
                continue;
            } // end instanceof PsiExpression
            if(target instanceof final PsiParameter formalParam) {
                if(!DataflowUtils.isMethodParamOrPatternVariable(formalParam)) {
                    continue;
                }
                final TypeInfo formalParamTypeInfo = DataflowUtils.getTypeInfoForParameter(formalParam);
                if(!formalParamTypeInfo.isStringOrUUID()) {
                    continue;
                }
                final ValueSource valueSource = determineValueSourceForParam(formalParam);
                final Node formalParamNode = new Node(
                        formalParam, formalParamTypeInfo.isUUID(),
                        formalParam.getName(),
                        formalParam.getName(),
                        valueSource);
                if(ret.contains(formalParamNode)) {
                    // We've already processed this node (either in the call stack or in an
                    // overlapping subtree).
                    continue;
                }
                if(alreadyVisitedNodes.contains(formalParamNode)) {
                    // We processed this node while computing same value nodes for another DI param.
                    // Add it to our same value set and continue. We don't need to recursively analyze this, because
                    // our node will get merged with the other nodes in the final step.
                    ret.add(formalParamNode);
                    continue;
                }
                ret.add(formalParamNode);
                // Determine if this is an instanceof expression with a pattern (Java 14+).
                // For example: if(fooId instanceof UUID fooAsType) {..}
                // We want to start a new recursive traversal to follow fooId.
                final InstanceOfPatternExpressionInfo instanceOfPatternExpressionInfo = DataflowUtils.getInstanceOfPatternExpressionInfo(formalParam);
                if(instanceOfPatternExpressionInfo != null) {
                    final ExpressionInfo expressionInfo = DataflowUtils.removeExtras(instanceOfPatternExpressionInfo.targetToUse(), instanceOfPatternExpressionInfo.isUUID());
                    final EndNodeInfo endNodeInfo = getEndNodeInfo(callExpressionsToDisMap,
                            expressionInfo.getExpressionToUse(),
                            expressionInfo.isUUID(), formalParam.getName(), ValueSource.IntermediateValue);
                    if(endNodeInfo != null) {
                        // The LHS of the instanceof check is an end node. Add it to our set and continue.
                        ret.addAll(endNodeInfo.endNodes());
                        continue;
                    }
                    final Node actualParamNode = new Node(
                            expressionInfo.getExpressionToUse(), expressionInfo.isUUID(),
                            "value",
                            "value",
                            ValueSource.IntermediateValue);
                    ret.add(actualParamNode);
                    determineNodesRecursively(callExpressionsToDisMap, expressionInfo.getExpressionToUse(),
                            alreadyVisitedNodes, ret);
                    continue;
                }
                if(!isParamOf(topLevelMethod, formalParam)) {
                    determineNodesRecursivelyForReturnValueImpl(usage, callExpressionsToDisMap, topLevelMethod, alreadyVisitedNodes, ret);
                }
                continue;
            }
            if(target instanceof final PsiLocalVariable localVariable) {
                final TypeInfo localVariableInfo = DataflowUtils.getTypeInfoForLocalVariable(localVariable);
                if(!localVariableInfo.isStringOrUUID()) {
                    continue;
                }
                final Node localVariableNode = new Node(
                        localVariable, localVariableInfo.isUUID(),
                        localVariable.getName(),
                        localVariable.getName(),
                        ValueSource.IntermediateValue);
                if(ret.contains(localVariableNode)) {
                    // We've already processed this node (either in the call stack or in an
                    // overlapping subtree).
                    continue;
                }
                if(alreadyVisitedNodes.contains(localVariableNode)) {
                    // We processed this node while computing same value nodes for another DI param.
                    // Add it to our same value set and continue. We don't need to recursively analyze this, because
                    // our node will get merged with the other nodes in the final step.
                    ret.add(localVariableNode);
                    continue;
                }
                ret.add(localVariableNode);
                determineNodesRecursivelyForReturnValueImpl(usage, callExpressionsToDisMap, topLevelMethod, alreadyVisitedNodes, ret);
            }
        }
    }

    private boolean isParamOf(final PsiMethod topLevelMethod, final PsiParameter formalParam) {
        for(final PsiParameter methodParam : topLevelMethod.getParameterList().getParameters()) {
            if(formalParam == methodParam) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    private ValueSource determineValueSourceForParam(final PsiParameter formalParam) {
        ValueSource valueSource = ValueSource.IntermediateValue;
        final PsiMethod containingMethod = DataflowUtils.getContainingMethodForParameter(formalParam);
        if(containingMethod != null) {
            final PsiClass containingClass = containingMethod.getContainingClass();
            if(containingMethod.isConstructor()) {
                if(containingClass == sourceClass) {
                    valueSource = ValueSource.SourceClassConstructorParam;
                } else if(superClasses.contains(containingClass)) {
                    valueSource = ValueSource.SuperClassConstructorParam;
                } else {
                    if(containingMethod instanceof PsiCompiledElement || !containingMethod.isWritable()) {
                        valueSource = ValueSource.ExternalClassConstructorParamNotWritable;
                    } else {
                        valueSource = ValueSource.ExternalClassConstructorParamWritable;
                    }
                }
            } else {
                if(containingClass == sourceClass) {
                    if(containingMethod.hasModifierProperty(PsiModifier.PUBLIC)) {
                        valueSource = ValueSource.SourceClassPublicMethodParam;
                    } else {
                        valueSource = ValueSource.SourceClassPackageMethodParam;
                    }
                } else if(superClasses.contains(containingClass)) {
                    if(containingMethod.hasModifierProperty(PsiModifier.PUBLIC)) {
                        valueSource = ValueSource.SuperClassPublicMethodParam;
                    } else {
                        valueSource = ValueSource.SuperClassPackageMethodParam;
                    }
                } else {
                    if(containingClass instanceof PsiCompiledElement || (containingClass != null && !containingClass.isWritable())) {
                        valueSource = ValueSource.ExternalClassMethodParamNotWritable;
                    } else {
                        valueSource = ValueSource.ExternalClassMethodParamWritable;
                    }
                }
            }
        }
        return valueSource;
    }

    private boolean shouldFollow(final PsiMethodCallExpression psiMethodCallExpression) {
        if(!isInternal(psiMethodCallExpression)) {
            return false;
        }
        if(hasStringOrUUIDParam(psiMethodCallExpression)) {
            return false;
        }
        return true;
    }

    private boolean hasStringOrUUIDParam(final PsiMethodCallExpression psiMethodCallExpression) {
        final PsiMethod resolvedMethod = psiMethodCallExpression.resolveMethod();
        if(resolvedMethod == null) {
            return false;
        }
        final PsiExpression[] argumentExpressions = psiMethodCallExpression.getArgumentList().getExpressions();
        for(final PsiExpression argumentExpression : argumentExpressions) {
            final TypeInfo typeInfo = DataflowUtils.getTypeInfo(argumentExpression.getType());
            if(typeInfo.isStringOrUUID()) {
                return true;
            }
        }

        for(final PsiParameter param : resolvedMethod.getParameterList().getParameters()) {
            final PsiType paramType = param.getType();
            final TypeInfo typeInfo = DataflowUtils.getTypeInfo(paramType);
            if(typeInfo.isStringOrUUID()) {
                return true;
            }
        }
        return false;
    }

    private boolean isInternal(final PsiMethodCallExpression psiMethodCallExpression) {
        final PsiMethod resolvedMethod = psiMethodCallExpression.resolveMethod();
        if(resolvedMethod == null) {
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

    private String determineUUIDConstantInfo(final PsiExpression expression) {
        // Determine if the param is a constant field that contains a UUID.
        // For example:
        // private static final UUID FooId = UUID.fromString("9e83b0e2-1d33-462e-905e-6e85bc8d6404");
        // fooService.getFoo(FooId);
        // Or possibly:
        // private static final String FooIdString = "9e83b0e2-1d33-462e-905e-6e85bc8d6404";
        // private static final UUID FooId = UUID.fromString(FooIdString);
        // fooService.getFoo(FooId);
        final PsiField field = determineConstantField(expression);
        if(field == null) {
            return null;
        }
        final PsiExpression fieldInitializer = field.getInitializer();
        if(fieldInitializer == null) {
            return null;
        }
        final ExpressionInfo expressionInfo = DataflowUtils.removeExtras(fieldInitializer);
        if(!expressionInfo.isUUID()) {
            return null;
        }
        final String constantExpression = determineStringConstant(expressionInfo.getExpressionToUse());
        if(DataflowUtils.isUUIDString(constantExpression)) {
            return constantExpression;
        }
        return null;
    }

    @Nullable
    private EndNodeInfo determineGetterCallInfo(final PsiExpression expression) {
        if(!(expression instanceof final PsiMethodCallExpression psiMethodCallExpression)) {
            return null;
        }
        final PsiMethod calledMethod = CompiledUtils.getMethodWithSourceCode(psiMethodCallExpression.resolveMethod());
        if(calledMethod == null) {
            return null;
        }
        final PsiClass containingClass = calledMethod.getContainingClass();
        if(containingClass == null) {
            return null;
        }
        if(calledMethod.getParameterList().getParameters().length != 0) {
            return null;
        }
        if(calledMethod.isConstructor()) {
            return null;
        }
        final PsiField field = beanInfoProvider.getFieldForGetter(calledMethod);
        if(field == null) {
            return null;
        }
        // Determine the class to use for the getSourceVariables(..) call.
        // The getter could be in a super class while the actual type (qualifier) is a subclass.
        // We need to invoke getSourceVariables(..) for the subclass instead of the super class.
        // This enables constructor parameters in subclass constructors to be correctly identified as source variables
        // for the getter's property.
        final PsiClass classToUse = getQualifierClassToUse(containingClass, psiMethodCallExpression);
        final Set<PsiVariable> sourceVariables = sourceVariableProvider.getSourceVariables(classToUse, field);
        final Set<Node> variableSources = getNodesFromSourceVariables(sourceVariables);
        if(field instanceof final LightRecordField lightRecordField) {
            final String fieldName = field.getName();
            final PsiRecordComponent recordComponent = lightRecordField.getRecordComponent();
            variableSources.add(new Node(recordComponent, false, fieldName, fieldName, ValueSource.ExternalClassRecordComponent));
        }
        return new EndNodeInfo(variableSources);
    }

    private Set<Node> getNodesFromSourceVariables(final Set<PsiVariable> sourceVars) {
        final Set<Node> ret = new HashSet<>();
        for(final PsiVariable variable : sourceVars) {
            final Node info = getNodeFromSourceVariable(variable);
            ret.add(info);
        }
        return ret;
    }

    private Node getNodeFromSourceVariable(final PsiVariable variable) {
        ValueSource valueSource = ValueSource.ExternalClassConstructorParamWritable;
        if(variable instanceof PsiField) {
            valueSource = ValueSource.ExternalClassField;
        } else {
            // Check to see if it's in a constructor or a setter.
            final PsiMethod containingMethod = DataflowUtils.getContainingMethodForParameter(variable);
            if(containingMethod != null) {
                if(containingMethod.isConstructor()) {
                    valueSource = ValueSource.ExternalClassConstructorParamWritable;
                } else {
                    if(variable instanceof PsiCompiledElement || !variable.isWritable()) {
                        valueSource = ValueSource.ExternalClassSetterParamNotWritable;
                    } else {
                        valueSource = ValueSource.ExternalClassSetterParamWritable;
                    }
                    if(ProtobufUtils.isProtobufBuilderMethod(containingMethod)) {
                        valueSource = ValueSource.ProtobufBuilderMethodParam;
                    } else if(StringUtils.equals(variable.getName(), "value")) {
                        valueSource = ValueSource.ExternalClassSetterParamNamedValue;
                    }
                }
            }
        }
        String name = variable.getName();
        if(name == null) {
            // Variable.getName() should not return null. It's always good to check though.
            name = "value";
        }
        if(variable instanceof PsiField) {
            name = StringUtils.strip(name, "_");
        }
        return new Node(variable, false, name, name, valueSource);
    }

    @Nullable
    private EndNodeInfo determineDiCallInfo(
            @NotNull final Map<PsiMethodCallExpression, InternalDependencyInteraction> callExpressionsToDisMap,
            @NotNull final PsiExpression callExpression) {
        if(!(callExpression instanceof final PsiMethodCallExpression psiMethodCallExpression)) {
            return null;
        }
        if(callExpressionsToDisMap.containsKey(psiMethodCallExpression)) {
            final PsiType methodCallExpressionReturnType = psiMethodCallExpression.getType();
            final InternalDependencyInteraction internalDependencyInteraction = callExpressionsToDisMap.get(psiMethodCallExpression);
            final PsiType formalParamType = internalDependencyInteraction.getPsiMethod().getReturnType();
            if(formalParamType == null || methodCallExpressionReturnType == null) {
                // This shouldn't happen. The call expression is passed as an argument to a dependency method call.
                // If it doesn't have a return type (return type = void), that would be a syntax error.
                return null;
            }
            return new EndNodeInfo(Collections.emptyList());
        }
        return null;
    }

    @Nullable
    private String determineStringConstantFieldValue(final PsiExpression callExpression) {
        final PsiField targetField = determineConstantField(callExpression);
        if(targetField == null) {
            return null;
        }
        // Compute the constant value if the field contains a constant.
        final PsiExpression fieldInitializer = targetField.getInitializer();
        if(fieldInitializer == null) {
            return null;
        }
        final Object constantValue = constantEvaluationHelper.computeConstantExpression(fieldInitializer);
        if(constantValue instanceof String) {
            return StringUtil.escapeStringCharacters((String) constantValue);
        }
        return null;
    }

    private PsiField determineConstantField(final PsiExpression callExpression) {
        if(!(callExpression instanceof final PsiReferenceExpression referenceExpression)) {
            return null;
        }
        final PsiElement target = referenceExpression.resolve();
        if(target == null) {
            return null;
        }
        if(!(target instanceof final PsiField field)) {
            return null;
        }
        if(!field.hasModifierProperty(PsiModifier.FINAL)) {
            return null;
        }
        return CompiledUtils.getFieldWithSourceCode(field);
    }

    @Nullable
    private String determineStringConstant(final PsiExpression argumentExpression) {
        String ret = DataflowUtils.determineStringLiteralContent(argumentExpression);
        if(ret != null) {
            return ret;
        }
        ret = determineStringConstantFieldValue(argumentExpression);
        if(ret != null) {
            return ret;
        }
        ret = determinePolyadicConstantValue(argumentExpression);
        return ret;
    }

    private List<Node> createFormalParamNodes(
            final PsiMethod psiMethod, final PsiCall methodCallExpression,
            final PsiParameter formalParam,
            final boolean isUUID, final ValueSource valueSource) {
        final List<Node> ret = new ArrayList<>();
        // Create a node for the formal parameter.
        final String formalParamName = formalParam.getName();
        if(shouldAddFormalParamToGraph(valueSource)) {
            // Only add the formalParam if it is not a dependency interaction method param.
            // The source class could have multiple members that are both JPA repos. If it calls findById() on both
            // members, the calls will resolve to the same method:
            // org.springframework.data.repository.CrudRepository#findById(ID id). We don't want to use the same value
            // for both of these cases.
            final Node formalParamNode = new Node(
                    formalParam, isUUID,
                    formalParamName,
                    formalParamName,
                    valueSource);
            ret.add(formalParamNode);
        }
        if(psiMethod.isConstructor()) {
            // Add the record component if the containing class is a record.
            final PsiRecordComponent recordComponent = DataflowUtils.getComponentForCanonicalConstructorParameter(formalParam);
            if(recordComponent != null) {
                final Node recordParamNode = new Node(
                        recordComponent, isUUID,
                        formalParamName,
                        formalParamName,
                        ValueSource.ExternalClassRecordComponent);
                ret.add(recordParamNode);
                return ret;
            }
            final PsiField field = sourceVariableProvider.getTargetField(psiMethod, formalParam);
            if(field == null) {
                return ret;
            }
            final Set<PsiVariable> sourceVariables = sourceVariableProvider.getSourceVariables(field);
            final Set<Node> variableSources = getNodesFromSourceVariables(sourceVariables);
            ret.addAll(variableSources);
            return ret;
        }

        // If this is a lombok builder method or setter method, add the physical PsiVariable that it refers to.
        // This will either be a field, constructor param or static factory method param.
        final PsiVariable lombokTargetElement = getLombokTargetVariable(psiMethod);
        if(lombokTargetElement != null) {
            ret.add(getNodeFromSourceVariable(lombokTargetElement));
        }

        final PsiField field = sourceVariableProvider.getTargetField(psiMethod, formalParam);
        if(field == null) {
            return ret;
        }
        // Invoke getSourceVariables(..) with the qualifier class as the starting point.
        // This handles the case where the field is defined in a super class but the method being called is a
        // constructor in a subclass.
        final PsiClass containingClass = psiMethod.getContainingClass();
        if(containingClass != null) {
            PsiClass classToUse = containingClass;
            if(methodCallExpression instanceof PsiMethodCallExpression) {
                classToUse = getQualifierClassToUse(containingClass, (PsiMethodCallExpression) methodCallExpression);
            }
            final Set<PsiVariable> sourceVariables = sourceVariableProvider.getSourceVariables(classToUse, field);
            final Set<Node> sourceVariableNodes = getNodesFromSourceVariables(sourceVariables);
            ret.addAll(sourceVariableNodes);
        }

        // Also invoke getSourceVariables(..) with the field's containing class.
        // This handles the case where the method is a Lombok builder method within a Lombok Builder class.
        // The field is inside an actual class. We need to add constructor parameters in the actual class that are
        // source variables for the field.
        final PsiClass fieldContainingClass = field.getContainingClass();
        if(fieldContainingClass != null) {
            final Set<PsiVariable> sourceVariables = sourceVariableProvider.getSourceVariables(fieldContainingClass, field);
            final Set<Node> sourceVariableNodes = getNodesFromSourceVariables(sourceVariables);
            ret.addAll(sourceVariableNodes);
        }
        return ret;
    }

    private boolean shouldAddFormalParamToGraph(final ValueSource valueSource) {
        return valueSource != ValueSource.DependencyInteractionFormalParamNotWritable && valueSource != ValueSource.DependencyInteractionFormalParamWritable;
    }

    private String determinePolyadicConstantValue(final PsiExpression argumentExpression) {
        if(!(argumentExpression instanceof PsiPolyadicExpression)) {
            return null;
        }
        final Object constantValue = constantEvaluationHelper.computeConstantExpression(argumentExpression);
        if(constantValue instanceof String) {
            return StringUtil.escapeStringCharacters((String) constantValue);
        }
        return null;
    }

    private PsiClass getQualifierClassToUse(final PsiClass methodContainingClass, final PsiMethodCallExpression psiMethodCallExpression) {
        PsiClass classToUse = methodContainingClass;
        final PsiExpression qualifierExpression = psiMethodCallExpression.getMethodExpression().getQualifierExpression();
        if(qualifierExpression != null) {
            final PsiClass qualifierClass = CompiledUtils.getClassWithSourceCode(PsiUtil.resolveClassInType(qualifierExpression.getType()));
            if(qualifierClass != null) {
                classToUse = qualifierClass;
            }
        }
        return classToUse;
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
