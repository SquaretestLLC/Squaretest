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

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiConstantEvaluationHelper;
import com.intellij.psi.PsiEnumConstant;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiSubstitutor;
import com.intellij.psi.PsiType;
import com.intellij.psi.SyntheticElement;
import com.intellij.psi.impl.light.LightMethod;
import com.squaretest.annotations.AnnotationCreator;
import com.squaretest.generation.abstractmethods.AbstractClassInfo;
import com.squaretest.generation.abstractmethods.AbstractClassInfoProvider;
import com.squaretest.generation.defaulttypes.TypeCreator;
import com.squaretest.generation.defaulttypes.beans.BeanInfoProvider;
import com.squaretest.generation.dependencyinteraction.DependencyFlowInfoProvider;
import com.squaretest.generation.dependencyinteraction.DependencyInteractionCollectorUtils;
import com.squaretest.generation.dependencyinteraction.DependencyInteractionPopulator;
import com.squaretest.generation.dependencyinteraction.FieldToSourceVariableCollector;
import com.squaretest.generation.dependencyinteraction.FieldToSourceVariablePopulator;
import com.squaretest.generation.dependencyinteraction.FieldsWithSameSourceVarProvider;
import com.squaretest.generation.existingtest.common.TestClassInfo;
import com.squaretest.generation.existingtest.main.ExistingInfoPopulator;
import com.squaretest.generation.existingtest.main.MemberFieldPopulator;
import com.squaretest.generation.existingtest.main.SpringInfoPopulator;
import com.squaretest.generation.setters.DefaultInitSetterDecider;
import com.squaretest.generation.singletons.SingletonDetector;
import com.squaretest.generation.singletons.SingletonInfo;
import com.squaretest.template.AccessModifier;
import com.squaretest.template.EnumInfo;
import com.squaretest.template.api.Api;
import com.squaretest.template.api.Api.ClassMember;
import com.squaretest.template.api.Api.Constructor;
import com.squaretest.template.api.Api.Method;
import com.squaretest.template.api.Api.Type;
import com.squaretest.template.impl.ClassMemberImpl;
import com.squaretest.template.impl.ConstructorImpl;
import com.squaretest.template.impl.FluentListImpl;
import com.squaretest.template.impl.MethodImpl;
import com.squaretest.template.impl.SourceClassImpl;
import com.squaretest.template.impl.TemplateData;
import com.squaretest.template.impl.VariableImpl;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class PsiToTemplateDataConverter {
    @NotNull
    private final PsiClass sourceClass;

    @NotNull
    private final ImportListGenerator importListGenerator;
    @NotNull
    private final TypeCreator typeCreator;
    @NotNull
    private final SingletonDetector singletonDetector;
    @NotNull
    private final AbstractClassInfoProvider abstractClassInfoProvider;
    @NotNull
    private final JavaPsiFacade javaPsiFacade;
    @NotNull
    private final SuggestedNameProvider suggestedNameProvider;
    @NotNull
    private final DefaultInitSetterDecider initSetterDecider;
    @NotNull
    private final BeanInfoProvider beanInfoProvider;
    @NotNull
    private final DependencyFlowInfoProvider dependencyFlowInfoProvider;
    @NotNull
    private final FieldToSourceVariableCollector fieldToSourceVariableCollector;
    @NotNull
    private final FieldsWithSameSourceVarProvider fieldsWithSameSourceVarProvider;
    @NotNull
    private final PsiFieldToVariableConverter fieldToVariableConverter;
    @NotNull
    private final PsiMethodToMethodConverter methodToMethodConverter;
    @NotNull
    private final VisibilityInfoProvider visibilityInfoProvider;
    @NotNull
    private final AnnotationCreator annotationCreator;
    @NotNull
    private final TypeSubstitutorProvider typeSubstitutorProvider;
    @NotNull
    private final BoxedInitExpressionUpdater boxedInitExpressionUpdater;
    @NotNull
    private final PsiConstantEvaluationHelper psiConstantEvaluationHelper;

    public PsiToTemplateDataConverter(
            @NotNull final PsiClass sourceClass,
            @NotNull final ImportListGenerator importListGenerator,
            @NotNull final TypeCreator typeCreator,
            @NotNull final SingletonDetector singletonDetector,
            @NotNull final AbstractClassInfoProvider abstractClassInfoProvider,
            @NotNull final JavaPsiFacade javaPsiFacade,
            @NotNull final SuggestedNameProvider suggestedNameProvider,
            @NotNull final DefaultInitSetterDecider initSetterDecider,
            @NotNull final DependencyFlowInfoProvider dependencyFlowInfoProvider,
            @NotNull final FieldToSourceVariableCollector fieldToSourceVariableCollector,
            @NotNull final BeanInfoProvider beanInfoProvider,
            @NotNull final FieldsWithSameSourceVarProvider fieldsWithSameSourceVarProvider,
            @NotNull final PsiFieldToVariableConverter fieldToVariableConverter,
            @NotNull final PsiMethodToMethodConverter methodToMethodConverter,
            @NotNull final VisibilityInfoProvider visibilityInfoProvider,
            @NotNull final AnnotationCreator annotationCreator,
            @NotNull final TypeSubstitutorProvider typeSubstitutorProvider,
            @NotNull final BoxedInitExpressionUpdater boxedInitExpressionUpdater,
            @NotNull final PsiConstantEvaluationHelper psiConstantEvaluationHelper) {
        this.sourceClass = sourceClass;
        this.importListGenerator = importListGenerator;
        this.typeCreator = typeCreator;
        this.singletonDetector = singletonDetector;
        this.abstractClassInfoProvider = abstractClassInfoProvider;
        this.javaPsiFacade = javaPsiFacade;
        this.suggestedNameProvider = suggestedNameProvider;
        this.initSetterDecider = initSetterDecider;
        this.dependencyFlowInfoProvider = dependencyFlowInfoProvider;
        this.fieldToSourceVariableCollector = fieldToSourceVariableCollector;
        this.beanInfoProvider = beanInfoProvider;
        this.fieldsWithSameSourceVarProvider = fieldsWithSameSourceVarProvider;
        this.fieldToVariableConverter = fieldToVariableConverter;
        this.methodToMethodConverter = methodToMethodConverter;
        this.visibilityInfoProvider = visibilityInfoProvider;
        this.annotationCreator = annotationCreator;
        this.typeSubstitutorProvider = typeSubstitutorProvider;
        this.boxedInitExpressionUpdater = boxedInitExpressionUpdater;
        this.psiConstantEvaluationHelper = psiConstantEvaluationHelper;
    }

    public TemplateData convertToTemplate(@NotNull final FileTemplate fileTemplate, @NotNull final PsiJavaFile psiJavaFile) {
        return convertToTemplate(fileTemplate, psiJavaFile, null);
    }

    public Api.SourceClass createSourceClass(@NotNull final PsiJavaFile psiJavaFile, @NotNull final PsiClass psiClass, final InitStrategy initStrategy) {
        return createSourceClass(psiJavaFile, psiClass, initStrategy, new PsiToTemplateVarsMapperImpl(), new HashSet<>());
    }

    private Api.SourceClass createSourceClass(@NotNull final PsiJavaFile psiJavaFile, @NotNull final PsiClass psiClass, final InitStrategy initStrategy, @NotNull final PsiToTemplateVarsMapper psiToTemplateVarsMapper, final Set<String> canonicalNamesInCallStack) {
        final Api.SourceClass cachedSourceClass = psiToTemplateVarsMapper.getSourceClass(psiClass, initStrategy);
        if(cachedSourceClass != null) {
            return cachedSourceClass;
        }

        // Create the SourceClass from the PsiClass.
        final Api.FluentList<ClassMember> fields = createFieldsFromClass(psiClass, psiToTemplateVarsMapper, initStrategy);
        final Api.FluentList<Constructor> constructors = createConstructors(psiClass, psiToTemplateVarsMapper, initStrategy);
        final Api.FluentList<Method> methods = createMethodsFromClass(psiClass, psiToTemplateVarsMapper, initStrategy);
        final boolean dtoBean = beanInfoProvider.isBean(psiClass, initStrategy);
        final boolean dtoBeanWithInputIoProperty = beanInfoProvider.isDtoBeanWithInputIoProperty(psiClass);
        final String packageName = psiJavaFile.getPackageName();
        final String sourceClassName = getClassName(psiClass);
        final String canonicalName = psiClass.getQualifiedName();
        final EnumInfo enumInfo = createEnumInfo(psiClass);
        final SingletonInfo singletonInfo = this.singletonDetector.getSingletonInfo(psiClass);
        final AbstractClassInfo abstractClassInfo = abstractClassInfoProvider.createAbstractClassInfo(psiClass);

        // Compute the super classes.
        final Set<PsiClass> superClassesSet = DependencyInteractionCollectorUtils.computeSuperClasses(psiClass);
        final Api.FluentList<Api.SourceClass> superClasses = new FluentListImpl<>(superClassesSet.size());
        for(final PsiClass superClass : superClassesSet) {
            final PsiFile superFile = superClass.getContainingFile();
            final String superClassCanonicalName = superClass.getQualifiedName();
            if(superFile instanceof PsiJavaFile && superClassCanonicalName != null && !canonicalNamesInCallStack.contains(superClassCanonicalName)) {
                canonicalNamesInCallStack.add(superClassCanonicalName);
                final Api.SourceClass sourceClass = createSourceClass((PsiJavaFile) superFile, superClass, initStrategy, psiToTemplateVarsMapper, canonicalNamesInCallStack);
                superClasses.add(sourceClass);
                canonicalNamesInCallStack.remove(superClassCanonicalName);
            }
        }

        final Type type = createTypeForClass(psiClass, initStrategy);
        final boolean hasGenerics = psiClass.hasTypeParameters();
        final String sourceClassSuggestedMemberName = suggestedNameProvider.suggestMemberFieldName(sourceClassName);
        final String sourceClassSuggestedLocalFieldName = suggestedNameProvider.suggestLocalFieldName(sourceClassName);
        final boolean sealed = psiClass.hasModifierProperty(PsiModifier.SEALED);
        final Constructor preferredConstructor = getDefaultPreferredConstructor(constructors);
        final Api.FluentList<Api.Method> preferredInitSetters = initSetterDecider.determineInitSetters(psiClass.getQualifiedName(), methods);
        final Api.FluentList<Api.Annotation> annotationCanonicalNames = annotationCreator.createAnnotations(psiClass);
        final Api.SourceClass theClassUnderTest = new SourceClassImpl(
                sourceClassName, canonicalName, superClasses, packageName, constructors, preferredConstructor,
                methods, preferredInitSetters, fields, annotationCanonicalNames, singletonInfo, enumInfo, abstractClassInfo, sealed, type,
                hasGenerics, dtoBean, dtoBeanWithInputIoProperty, sourceClassSuggestedMemberName, sourceClassSuggestedLocalFieldName);

        // Wire up fields and getters/setters.
        for(final Api.Method method : theClassUnderTest.getAllMethods()) {
            final MethodImpl methodImpl = (MethodImpl) method;
            final ClassMember targetField = methodImpl.getTargetField();
            if(targetField == null) {
                continue;
            }
            final Api.SourceClass targetFieldClass = ((ClassMemberImpl) targetField).getContainingClass();
            final Api.SourceClass methodClass = methodImpl.getContainingClass();
            if(targetFieldClass == null || methodClass == null) {
                continue;
            }
            if(!StringUtils.equals(targetFieldClass.getCanonicalNameOrName(), methodClass.getCanonicalNameOrName())) {
                continue;
            }
            if(method.isGetter()) {
                final Api.FluentList<Method> getters = targetField.getGetters();
                if(!getters.contains(methodImpl)) {
                    getters.add(methodImpl);
                }
                continue;
            }
            if(method.isSetter()) {
                final Api.FluentList<Method> setters = targetField.getSetters();
                if(!setters.contains(methodImpl)) {
                    setters.add(methodImpl);
                }
                // Update setter param's testClassLocalFieldName.
                final Api.Variable firstParam = methodImpl.getParameters().first();
                if(firstParam == null) {
                    continue;
                }
                firstParam.setTestClassMemberName(StringUtils.uncapitalize(targetField.getDeclaredNameWithoutPrefix()));
                firstParam.setTestClassLocalFieldName(StringUtils.uncapitalize(targetField.getDeclaredNameWithoutPrefix()));
            }
        }

        // Cache the source class.
        psiToTemplateVarsMapper.putSourceClass(psiClass, theClassUnderTest, initStrategy);
        return theClassUnderTest;
    }

    public TemplateData convertToTemplate(
            @NotNull final FileTemplate fileTemplate,
            @NotNull final PsiJavaFile psiJavaFile,
            @Nullable final PsiClass existingTestClass) {

        // Create the SourceClass from the PsiClass.
        final PsiToTemplateVarsMapper psiToTemplateVarsMapper = new PsiToTemplateVarsMapperImpl();
        final PsiClass topLevelClass = psiJavaFile.getClasses()[0];
        final Api.SourceClass theClassUnderTest = createSourceClass(psiJavaFile, topLevelClass, InitStrategy.Default, psiToTemplateVarsMapper, new HashSet<>());

        final Set<String> importStatements = importListGenerator.createImportStatementListForAllDependencies(theClassUnderTest, ((SourceClassImpl) theClassUnderTest).getAbstractClassInfo());

        // Compute parts of the data model that require in-depth static analysis.
        // Compute the DependencyInteractions for each method.
        final DependencyInteractionPopulator dependencyInteractionPopulator = new DependencyInteractionPopulator(psiToTemplateVarsMapper, methodToMethodConverter, typeCreator, fieldsWithSameSourceVarProvider, dependencyFlowInfoProvider);
        dependencyInteractionPopulator.populateDependencies();

        // Compute the possible source variables for the class's constructors.
        final FieldToSourceVariablePopulator fieldToSourceVariablePopulator = new FieldToSourceVariablePopulator(psiToTemplateVarsMapper, fieldToSourceVariableCollector, topLevelClass);
        fieldToSourceVariablePopulator.populateDependencies();

        // Update Method.shouldUseSimpleTest based on the dependency interactions.
        updateMethodShouldUseSimpleTestWithDependencyInteractions(theClassUnderTest);

        // Update Method.returnValue and Method.simpleExitInfo based on the test framework selected.
        boxedInitExpressionUpdater.updateInitExpressionsIfNeeded(fileTemplate, theClassUnderTest);

        // Set preferredConstructor to the visible constructor that sets the most fields.
        // If there is a tie, choose the longest one.
        // Compute IdentityMap<Constructor, sourceVarCount>.
        final Map<Api.Constructor, Integer> constructorToSourceVarCount = new IdentityHashMap<>();
        final Api.FluentList<ClassMember> fields = theClassUnderTest.getAllFields();
        final List<Constructor> candidateConstructors = theClassUnderTest.getConstructors().stream().filter(x -> x.isVisibleToTestClass() && !x.isLombokSuperBuilderConstructor()).toList();
        for(final Api.Constructor constructor : candidateConstructors) {
            final int fieldSourceVarCount = computeFieldToSourceVarCount(constructor, fields);
            constructorToSourceVarCount.put(constructor, fieldSourceVarCount);
        }
        // Choose the constructor that sets the most fields.
        final Optional<Constructor> preferredCons = candidateConstructors.stream().max((leftCons, rightCons) -> {
            final int leftSourceCount = constructorToSourceVarCount.get(leftCons);
            final int rightSourceCount = constructorToSourceVarCount.get(rightCons);
            final int comp = Integer.compare(leftSourceCount, rightSourceCount);
            if(comp != 0) {
                return comp;
            }
            return Integer.compare(leftCons.getParameters().size(), rightCons.getParameters().size());
        });
        if(preferredCons.isPresent()) {
            theClassUnderTest.setPreferredConstructor(preferredCons.get());
        }

        TestClassInfo testClassInfo = null;
        if(existingTestClass != null) {
            final ExistingInfoPopulator existingInfoPopulator = new ExistingInfoPopulator(psiConstantEvaluationHelper, new MemberFieldPopulator(), sourceClass, existingTestClass, psiToTemplateVarsMapper, new SpringInfoPopulator());
            testClassInfo = existingInfoPopulator.determineTestClassInfo();
        }

        return new TemplateData(importStatements, theClassUnderTest, psiToTemplateVarsMapper, testClassInfo);
    }

    private int computeFieldToSourceVarCount(final Constructor constructor, final List<ClassMember> fields) {
        if(constructor.getParameters().isEmpty() || fields.isEmpty()) {
            return 0;
        }
        final Set<Api.Variable> params = SetUtils.newIdentityHashSet();
        params.addAll(constructor.getParameters());
        int count = 0;
        for(final ClassMember field : fields) {
            if(!Collections.disjoint(params, field.getPossibleSourceVariables())) {
                count++;
            }
        }
        return count;
    }

    /**
     * Updates the shouldUseSimpleTest property on all of the instance methods based on the method's dependency interactions.
     * The dependency interactions should be populated before calling this method.
     */
    private static void updateMethodShouldUseSimpleTestWithDependencyInteractions(final Api.SourceClass topLevelClass) {
        // Do not use simple tests for instance methods in Spring controllers.
        // This part is a hack. This should be done in the template. We need this hack to avoid breaking user templates that
        // were forked from older versions of the Squaretest templates.
        if(isSpringController(topLevelClass)) {
            return;
        }

        for(final Api.Method method : topLevelClass.getAllNonObjectMethods()) {
            if(method.getShouldUseSimpleTest() || method.isStatic() || topLevelClass.isEnum()) {
                continue;
            }
            final Api.Type returnType = method.getReturnType();
            final Api.FluentList<Api.Variable> params = method.getParameters();
            if(method.getDependencyInteractions().isEmpty() && returnType != null && returnType.isSimple() && params.stream().allMatch(x -> x.getType().isSimple() || !x.isUsed())) {
                // Use a simple test for the method.
                method.setShouldUseSimpleTest(true);
                for(final Api.Variable param : params) {
                    param.setShouldStoreInReference(false);
                }
            }
        }
    }

    private Constructor getDefaultPreferredConstructor(final List<Constructor> constructors) {
        final List<Constructor> candidateConstructors = constructors.stream().filter(
                constructor -> constructor.isVisibleToTestClass() && !constructor.isLombokSuperBuilderConstructor()).toList();
        if(candidateConstructors.isEmpty()) {
            return null;
        }
        return Collections.max(candidateConstructors, Comparator.comparingInt(cons -> cons.getParameters().size()));
    }

    private Type createTypeForClass(@NotNull final PsiClass topLevelClass, final InitStrategy initStrategy) {
        PsiSubstitutor substitutor = typeSubstitutorProvider.getSubstitutor(topLevelClass);
        if(substitutor == null) {
            substitutor = PsiSubstitutor.EMPTY;
        }
        final PsiType classType = javaPsiFacade.getElementFactory().createType(topLevelClass, substitutor);
        final String paramNameToUse = StringUtils.uncapitalize(getClassName(topLevelClass));
        return typeCreator.createTypeForFormalParameter(paramNameToUse, classType, initStrategy, topLevelClass);
    }

    @NotNull
    private EnumInfo createEnumInfo(@NotNull final PsiClass topLevelClass) {
        final boolean isEnum = topLevelClass.isEnum();
        final FluentListImpl<String> enumValues = new FluentListImpl<>();
        for(final PsiField field : topLevelClass.getFields()) {
            if(field instanceof PsiEnumConstant) {
                enumValues.add(field.getName());
            }
        }
        return new EnumInfo(isEnum, enumValues);
    }

    private Api.FluentList<Constructor> createConstructors(
            @NotNull final PsiClass topLevelClass, @NotNull final PsiToTemplateVarsMapper psiToTemplateVarsMapper, final InitStrategy initStrategy) {
        final FluentListImpl<Constructor> constructors = new FluentListImpl<>();
        int overloadCount = 0;
        final PsiMethod[] psiConstructors = topLevelClass.getConstructors();
        for(int i = 0; i < psiConstructors.length; i++) {
            final PsiMethod method = psiConstructors[i];
            final Constructor constructor = methodToMethodConverter.createConstructor(method, psiConstructors.length > 1, overloadCount++, i, psiToTemplateVarsMapper, initStrategy);
            constructors.add(constructor);
        }
        // If no constructors are found, add the default one provided by the Java compiler.
        // Note: we do not add the default constructor to psiToTemplateVarsMapper, because there is no underlying
        // PsiMethod.
        if(psiConstructors.length == 0) {
            final Type returnType = createTypeForClass(topLevelClass, initStrategy);
            final String sourceClassName = getClassName(topLevelClass);
            final boolean isInMainSourceClass = isMainSourceClass(topLevelClass);
            constructors.add(
                    new ConstructorImpl(
                            sourceClassName, Api.FluentListFactory.emptyList(),
                            AccessModifier.Public, returnType, Api.FluentListFactory.emptyList(), true, sourceClassName, false,
                            false, false, false,
                            false, false, false, false, isInMainSourceClass, overloadCount, 0, false, false, false, false, false, false));
        }
        return constructors;
    }

    private boolean isMainSourceClass(final PsiClass topLevelClass) {
        final String qualifiedName = topLevelClass.getQualifiedName();
        if(qualifiedName == null) {
            return false;
        }
        return Objects.equals(sourceClass.getQualifiedName(), topLevelClass.getQualifiedName());
    }

    private Api.FluentList<ClassMember> createFieldsFromClass(@NotNull final PsiClass topLevelClass, @NotNull final PsiToTemplateVarsMapper psiToTemplateVarsMapper, final InitStrategy initStrategy) {
        final FluentListImpl<ClassMember> ret = new FluentListImpl<>(topLevelClass.getFields().length);
        for(final PsiField field : topLevelClass.getFields()) {
            final VariableImpl variable = fieldToVariableConverter.createFieldFromPsiVariable(field, field, field.getName(), true, initStrategy);
            final AccessModifier modifier = PsiConverterUtils.modifierFromPsi(field);
            final boolean isStatic = field.hasModifierProperty(PsiModifier.STATIC);
            final boolean isTransient = field.hasModifierProperty(PsiModifier.TRANSIENT);
            final boolean isVisibleToTestClass = visibilityInfoProvider.isVisibleToTestClass(field);
            final ClassMember apiMember = new ClassMemberImpl(variable, modifier, isStatic, isTransient, isVisibleToTestClass);
            psiToTemplateVarsMapper.putVariable(field, apiMember, initStrategy);
            ret.add(apiMember);
        }
        return ret;
    }

    private Api.FluentList<Method> createMethodsFromClass(
            @NotNull final PsiClass psiClass, @NotNull final PsiToTemplateVarsMapper psiToTemplateVarsMapper, final InitStrategy initStrategy) {
        final FluentListImpl<Method> ret = new FluentListImpl<>();
        final Map<String, Integer> methodNameToOverloadCount = new HashMap<>();
        final PsiMethod[] methods = psiClass.getMethods();
        for(int i = 0; i < methods.length; i++) {
            final PsiMethod method = methods[i];
            if(method.isConstructor()) {
                continue;
            }
            if(psiClass.isEnum() && (method instanceof LightMethod || method instanceof SyntheticElement)) {
                continue;
            }
            final Integer existingMethodNameOverloadCount = methodNameToOverloadCount.get(method.getName());
            final int overloadNumber = existingMethodNameOverloadCount == null ? 1 : existingMethodNameOverloadCount;
            methodNameToOverloadCount.put(method.getName(), overloadNumber + 1);
            ret.add(methodToMethodConverter.createMethod(psiToTemplateVarsMapper, method, overloadNumber, i, initStrategy));
        }
        return ret;
    }

    @NotNull
    private static String getClassName(@NotNull final PsiClass topLevelClass) {
        String className = topLevelClass.getName();
        if(className == null) {
            className = "sourceClass";
        }
        return className;
    }

    private static boolean isSpringController(final Api.SourceClass topLevelClass) {
        if(hasSpringControllerAnnotations(topLevelClass)) {
            return true;
        }
        for(final Api.SourceClass sourceClass : topLevelClass.getAllSuperClasses()) {
            if(hasSpringControllerAnnotations(sourceClass)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasSpringControllerAnnotations(Api.SourceClass sourceClass) {
        return sourceClass.hasAnnotation("org.springframework.stereotype.Controller", "org.springframework.web.bind.annotation.RestController");
    }
}
