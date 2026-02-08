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
package com.squaretest.generation.existingtest.main;

import com.intellij.psi.*;
import com.intellij.psi.util.InheritanceUtil;
import com.intellij.psi.util.PsiUtil;
import com.squaretest.generation.InitStrategy;
import com.squaretest.generation.PsiToTemplateVarsMapper;
import com.squaretest.generation.dependencyinteraction.DependencyInteractionCollectorUtils;
import com.squaretest.generation.existingtest.common.Constants;
import com.squaretest.generation.existingtest.common.InternalInvokedConstructorInfo;
import com.squaretest.generation.existingtest.common.InvokedConstructorInfo;
import com.squaretest.generation.existingtest.common.MemberField;
import com.squaretest.generation.existingtest.common.PackageLocalFieldsInfo;
import com.squaretest.generation.existingtest.common.RefInfo;
import com.squaretest.generation.existingtest.common.RefType;
import com.squaretest.generation.existingtest.common.TestClassInfo;
import com.squaretest.generation.existingtest.common.VariableInfo;
import com.squaretest.template.api.Api;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.plugins.groovy.lang.psi.GroovyRecursiveElementVisitor;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.GrField;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.GrVariable;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.GrVariableDeclaration;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.arguments.GrArgumentList;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.expressions.GrAssignmentExpression;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.expressions.GrConstructorCall;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.expressions.GrExpression;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.expressions.GrNewExpression;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.expressions.GrReferenceExpression;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.typedef.members.GrMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.squaretest.generation.existingtest.main.MemberFieldPopulator.getCanonicalOrShortName;
import static com.squaretest.generation.existingtest.main.Utils.isMocked;
import static com.squaretest.template.impl.NameUtils.computeSimpleName;

public class ExistingInfoPopulator {

    @NotNull
    private final PsiConstantEvaluationHelper psiConstantEvaluationHelper;
    @NotNull
    private final MemberFieldPopulator memberFieldPopulator;
    @NotNull
    private final PsiClass sourceClass;
    @NotNull
    private final PsiClass testClass;
    @NotNull
    private final PsiToTemplateVarsMapper psiToTemplateVarsMapper;
    @NotNull
    private final SpringInfoPopulator springInfoPopulator;
    @NotNull
    private final Set<PsiClass> testAndSupers;

    public ExistingInfoPopulator(
            @NotNull final PsiConstantEvaluationHelper psiConstantEvaluationHelper,
            @NotNull final MemberFieldPopulator memberFieldPopulator, @NotNull final PsiClass sourceClass,
            @NotNull final PsiClass testClass, @NotNull final PsiToTemplateVarsMapper psiToTemplateVarsMapper,
            @NotNull final SpringInfoPopulator springInfoPopulator) {
        this.psiConstantEvaluationHelper = psiConstantEvaluationHelper;
        this.memberFieldPopulator = memberFieldPopulator;
        this.sourceClass = sourceClass;
        this.testClass = testClass;
        this.psiToTemplateVarsMapper = psiToTemplateVarsMapper;
        this.springInfoPopulator = springInfoPopulator;
        final Set<PsiClass> testSupers = DependencyInteractionCollectorUtils.computeSuperClasses(testClass);
        this.testAndSupers = new LinkedHashSet<>();
        this.testAndSupers.add(testClass);
        this.testAndSupers.addAll(testSupers);
    }

    @NotNull
    public TestClassInfo determineTestClassInfo() {

        final TestClassInfo testClassInfo = new TestClassInfo();

        // Compute the member fields (needed for all scenarios).
        final List<MemberField> memberFields = memberFieldPopulator.computeMembers(testClass);
        testClassInfo.setTestClassMembers(memberFields);

        // Find the source class member.
        final MemberField sourceClassMember = findSourceClassMember(memberFields);
        if(sourceClassMember != null) {
            testClassInfo.setTestClassMemberName(sourceClassMember.name());
        }

        // Find the source class local variable(s).
        final LocalVariableInfo localVariable = findSourceClassLocalVariable(testClass);
        testClassInfo.setTestClassLocalVariableInfo(localVariable);

        // Check to see if this is a Spring MVC test or Spring Boot test.
        springInfoPopulator.provisionSpringInfoIfNeeded(memberFields, testClassInfo);
        if(testClassInfo.isSpringTestClass()) {
            return testClassInfo;
        }

        // Set the name of the source class (SourceClass.testClassMemberName).
        final LocalFieldsInfoPopulator localFieldsInfoPopulator = new LocalFieldsInfoPopulator(psiToTemplateVarsMapper, sourceClass, testClass, memberFields);
        final PackageLocalFieldsInfo packageLocalFieldsInfo = localFieldsInfoPopulator.computePackageLocalInfo();
        testClassInfo.setPackageLocalFieldsInfo(packageLocalFieldsInfo);

        final SetterCallInfoProvider setterCallInfoProvider = new SetterCallInfoProvider(psiToTemplateVarsMapper, testClass, memberFields);
        final Map<Api.Variable, VariableInfo> setterInfo = setterCallInfoProvider.computeSetterInfo();
        testClassInfo.setCalledSetterInfo(setterInfo);

        final ReflectionCallInfoProvider reflectionCallInfoProvider = new ReflectionCallInfoProvider(psiConstantEvaluationHelper, psiToTemplateVarsMapper, sourceClass, testClass, memberFields);
        final Map<Api.Variable, VariableInfo> reflectionInfo = reflectionCallInfoProvider.computeReflectionInfo();
        testClassInfo.setReflectionInfo(reflectionInfo);

        final InternalInvokedConstructorInfo invokedConstructorInfo = findConstructorCall(sourceClassMember);
        if(invokedConstructorInfo == null) {
            return testClassInfo;
        }

        final PsiMethod calledConstructor = invokedConstructorInfo.invokedConstructor();
        if(calledConstructor == null) {
            // This happens when the test class calls the zero-arg constructor that was auto generated by the Java
            // compiler. There is no PsiMethod for the constructor in this case.
            return testClassInfo;
        }
        if(calledConstructor.getContainingClass() != sourceClass) {
            // We have a subclass constructor call.
            return testClassInfo;
        }

        // If we have a no-args constructor add info about which package local fields are set and return.
        if(calledConstructor.getParameterList().getParametersCount() == 0) {
            return testClassInfo;
        }

        // Resolve parameters in the constructorCall; if any are test class members, set their testClassMemberNames.
        // Also set their shouldBeMocked property. Also set $dependencies = the parameters.
        final Api.Constructor sourceClassConstructor = (Api.Constructor) this.psiToTemplateVarsMapper.getMethod(calledConstructor, InitStrategy.Default);
        if(sourceClassConstructor == null) {
            // The constructor is in the source class but not in our data model.
            // This shouldn't happen.
            return testClassInfo;
        }

        if(!invokedConstructorInfo.isCompleteCall()) {
            // The constructor call is incomplete; e.g. "fooUnderTest = new Foo(bar, ;"
            return testClassInfo;
        }

        // Provision constructor call.
        final Map<Api.Variable, VariableInfo> variableUpdateMap = new IdentityHashMap<>();
        final List<PsiElement> constructorCallArguments = invokedConstructorInfo.actualArguments();
        for(int i = 0; i < sourceClassConstructor.getParameters().size() && i < constructorCallArguments.size(); i++) {
            final PsiElement actualArgumentExpression = constructorCallArguments.get(i);
            final VariableInfo variableInfo = new VariableInfo();
            final PsiField actualArgumentField = getFieldThisExpressionResolvesTo(actualArgumentExpression);
            if(actualArgumentField != null) {
                if(hasMockAnnotation(actualArgumentField)) {
                    // The argument resolves to a field and it is mocked.
                    variableInfo.setShouldStoreInReference(true);
                    variableInfo.setShouldBeMocked(true);
                    variableInfo.setRefInfo(new RefInfo(actualArgumentField.getName(), RefType.TestClassMember));
                } else {
                    variableInfo.setShouldStoreInReference(true);
                    variableInfo.setShouldBeMocked(false);
                    variableInfo.setRefInfo(new RefInfo(actualArgumentField.getName(), RefType.TestClassMember));
                }
                variableUpdateMap.put(sourceClassConstructor.getParameters().get(i), variableInfo);
                continue;
            }

            final PsiVariable actualLocalVariable = getLocalVariableThisExpressionResolvesTo(actualArgumentExpression);
            if(actualLocalVariable != null) {
                if(isMocked(actualLocalVariable)) {
                    // The argument resolves to a field and it is mocked.
                    variableInfo.setShouldStoreInReference(true);
                    variableInfo.setShouldBeMocked(true);
                    variableInfo.setRefInfo(new RefInfo(actualLocalVariable.getName(), RefType.LocalVariable));
                } else {
                    variableInfo.setShouldStoreInReference(true);
                    variableInfo.setShouldBeMocked(false);
                    variableInfo.setRefInfo(new RefInfo(actualLocalVariable.getName(), RefType.LocalVariable));
                }
                variableUpdateMap.put(sourceClassConstructor.getParameters().get(i), variableInfo);
                continue;
            }

            variableInfo.setShouldStoreInReference(false);
            variableInfo.setShouldBeMocked(false);
            variableUpdateMap.put(sourceClassConstructor.getParameters().get(i), variableInfo);
        }
        testClassInfo.setInvokedConstructorInfo(new InvokedConstructorInfo(sourceClassConstructor, variableUpdateMap, invokedConstructorInfo.isInSetupMethod(), invokedConstructorInfo.isCompleteCall()));
        return testClassInfo;
    }

    @NotNull
    private LocalVariableInfo findSourceClassLocalVariable(final PsiClass testClass) {
        final MutableObject<LocalVariableInfo> result = new MutableObject<>();
        for(final PsiMethod method : testClass.getMethods()) {
            if(isSetupMethod(method)) {
                continue;
            }
            if(method instanceof final GrMethod grMethod) {
                grMethod.accept(new GroovyRecursiveElementVisitor() {
                    @Override
                    public void visitNewExpression(@NotNull final GrNewExpression expression) {
                        super.visitNewExpression(expression);
                        if(result.getValue() != null) {
                            return;
                        }
                        final PsiMethod calledMethod = expression.resolveMethod();
                        if(calledMethod == null || !calledMethod.isConstructor()) {
                            return;
                        }
                        final PsiClass containingClass = calledMethod.getContainingClass();
                        if(containingClass == null) {
                            return;
                        }
                        if(!InheritanceUtil.isInheritorOrSelf(containingClass, sourceClass, true)) {
                            return;
                        }
                        // We are calling a constructor in the source class or one of its subclasses.
                        final PsiElement parent = expression.getParent();
                        if(parent instanceof final GrAssignmentExpression psiAssignmentExpression) {
                            final GrExpression lhs = psiAssignmentExpression.getLValue();
                            if(lhs instanceof final GrReferenceExpression referenceExpression) {
                                final PsiElement target = referenceExpression.resolve();
                                if(target instanceof final GrVariable localVariable) {
                                    final String targetName = localVariable.getName();
                                    result.setValue(new LocalVariableInfo(targetName));
                                    return;
                                }
                            }
                        }
                        if(parent instanceof final GrVariable grVariable) {
                            final String variableName = grVariable.getName();
                            result.setValue(new LocalVariableInfo(variableName));
                            return;
                        }
                        result.setValue(new LocalVariableInfo(true));
                    }
                });
            } else {
                method.accept(new JavaRecursiveElementWalkingVisitor() {
                    @Override
                    public void visitNewExpression(@NotNull final PsiNewExpression expression) {
                        super.visitNewExpression(expression);
                        final PsiMethod calledMethod = expression.resolveMethod();
                        if(calledMethod == null || !calledMethod.isConstructor()) {
                            return;
                        }
                        final PsiClass containingClass = calledMethod.getContainingClass();
                        if(containingClass == null) {
                            return;
                        }
                        if(!InheritanceUtil.isInheritorOrSelf(containingClass, sourceClass, true)) {
                            return;
                        }
                        // We are calling a constructor in the source class or one of its subclasses.
                        final PsiElement parent = expression.getParent();
                        if(parent == null) {
                            return;
                        }
                        if(parent instanceof final PsiAssignmentExpression psiAssignmentExpression) {
                            final PsiExpression lhs = psiAssignmentExpression.getLExpression();
                            if(lhs instanceof PsiReference) {
                                final PsiReferenceExpression referenceExpression = (PsiReferenceExpression) lhs;
                                final PsiElement target = referenceExpression.resolve();
                                if(target instanceof final PsiLocalVariable localVariable) {
                                    final String targetName = localVariable.getName();
                                    result.setValue(new LocalVariableInfo(targetName));
                                    stopWalking();
                                    return;
                                }
                            }
                        }
                        if(parent instanceof final PsiLocalVariable psiVariable) {
                            final String variableName = psiVariable.getName();
                            result.setValue(new LocalVariableInfo(variableName));
                            stopWalking();
                            return;
                        }
                        result.setValue(new LocalVariableInfo(true));
                        stopWalking();
                    }
                });
            }
            if(result.getValue() != null) {
                return result.getValue();
            }
        }

        // Search for local vars with the source class's type. It could be assigned via static factory method call.
        for(final PsiMethod method : testClass.getMethods()) {
            if(isSetupMethod(method)) {
                continue;
            }
            if(method instanceof final GrMethod grMethod) {
                grMethod.accept(new GroovyRecursiveElementVisitor() {
                    @Override
                    public void visitVariable(@NotNull final GrVariable variable) {
                        super.visitVariable(variable);
                        if(result.getValue() != null) {
                            return;
                        }
                        final PsiType type = variable.getType();
                        final PsiClass typeClass = PsiUtil.resolveClassInType(type);
                        if(typeClass == null) {
                            return;
                        }
                        if(InheritanceUtil.isInheritorOrSelf(typeClass, sourceClass, true)) {
                            result.setValue(new LocalVariableInfo(variable.getName()));
                        }
                    }
                });
            } else {
                method.accept(new JavaRecursiveElementWalkingVisitor() {
                    @Override
                    public void visitLocalVariable(@NotNull final PsiLocalVariable variable) {
                        final PsiType type = variable.getType();
                        final PsiClass typeClass = PsiUtil.resolveClassInType(type);
                        if(typeClass == null) {
                            return;
                        }
                        if(InheritanceUtil.isInheritorOrSelf(typeClass, sourceClass, true)) {
                            result.setValue(new LocalVariableInfo(variable.getName()));
                            stopWalking();
                        }
                    }
                });
            }
        }
        if(result.getValue() == null) {
            result.setValue(new LocalVariableInfo(false));
        }
        return result.getValue();
    }

    private static boolean hasMockAnnotation(@NotNull final PsiField field) {
        final PsiModifierList modifierList = field.getModifierList();
        if(modifierList == null) {
            return false;
        }
        final PsiAnnotation[] annotations = modifierList.getAnnotations();
        for(final PsiAnnotation annotation : annotations) {
            final String qualifiedName = getCanonicalOrShortName(annotation);
            if(qualifiedName != null && StringUtils.endsWithAny(qualifiedName, Constants.MockAnnotationNames)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    private PsiField getFieldThisExpressionResolvesTo(final PsiElement expression) {
        if(!(expression instanceof final PsiReference leftReferenceExp)) {
            return null;
        }
        final PsiElement refTarget = leftReferenceExp.resolve();
        if(!(refTarget instanceof final PsiField leftField)) {
            return null;
        }
        final PsiClass containingClass = leftField.getContainingClass();
        if(containingClass == null) {
            return null;
        }
        if(testAndSupers.contains(containingClass)) {
            return leftField;
        }
        return null;
    }

    private PsiVariable getLocalVariableThisExpressionResolvesTo(final PsiElement expression) {
        if(!(expression instanceof final PsiReference leftReferenceExp)) {
            return null;
        }
        final PsiElement leftField = leftReferenceExp.resolve();
        if(leftField instanceof PsiVariable) {
            return (PsiVariable) leftField;
        }
        return null;
    }

    @Nullable
    private MemberField findSourceClassMember(final List<MemberField> memberFields) {
        final MemberField sourceClassMember = tryFindSourceClassMemberInFields(memberFields);
        if(sourceClassMember != null) {
            return sourceClassMember;
        }
        final PsiMethod setupMethod = findSetupMethod(testClass);
        if(setupMethod == null) {
            return null;
        }
        final MutableObject<MemberField> result = new MutableObject<>();
        if(setupMethod instanceof final GrMethod grMethod) {
            grMethod.accept(new GroovyRecursiveElementVisitor() {
                @Override
                public void visitNewExpression(@NotNull final GrNewExpression expression) {
                    super.visitNewExpression(expression);
                    if(result.getValue() != null) {
                        return;
                    }
                    final PsiMethod calledMethod = expression.resolveMethod();
                    if(calledMethod == null || !calledMethod.isConstructor()) {
                        return;
                    }
                    final PsiClass containingClass = calledMethod.getContainingClass();
                    if(containingClass == null) {
                        return;
                    }
                    if(!InheritanceUtil.isInheritorOrSelf(containingClass, sourceClass, true)) {
                        return;
                    }
                    // We are calling the source class constructor.
                    final PsiElement parent = expression.getParent();
                    if(parent == null) {
                        return;
                    }
                    if(!(parent instanceof final GrAssignmentExpression assignmentExpression)) {
                        return;
                    }
                    final GrExpression lhs = assignmentExpression.getLValue();
                    if(!(lhs instanceof final GrReferenceExpression referenceExpression)) {
                        return;
                    }
                    final PsiElement target = referenceExpression.resolve();
                    if(!(target instanceof final GrField targetField)) {
                        return;
                    }
                    final String targetFieldName = targetField.getName();
                    final Optional<MemberField> memberField = memberFields.stream().filter(x -> x.name().equals(targetFieldName)).findAny();
                    if(memberField.isPresent()) {
                        result.setValue(memberField.get());
                    }
                }
            });
        } else {
            setupMethod.accept(new JavaRecursiveElementWalkingVisitor() {
                @Override
                public void visitNewExpression(@NotNull final PsiNewExpression expression) {
                    super.visitNewExpression(expression);
                    final PsiMethod calledMethod = expression.resolveMethod();
                    if(calledMethod == null || !calledMethod.isConstructor()) {
                        return;
                    }
                    final PsiClass containingClass = calledMethod.getContainingClass();
                    if(containingClass == null) {
                        return;
                    }
                    if(!InheritanceUtil.isInheritorOrSelf(containingClass, sourceClass, true)) {
                        return;
                    }
                    // We are calling the source class constructor.
                    final PsiElement parent = expression.getParent();
                    if(parent == null) {
                        return;
                    }
                    if(!(parent instanceof final PsiAssignmentExpression assignmentExpression)) {
                        return;
                    }
                    final PsiExpression lhs = assignmentExpression.getLExpression();
                    if(!(lhs instanceof final PsiReferenceExpression referenceExpression)) {
                        return;
                    }
                    final PsiElement target = referenceExpression.resolve();
                    if(!(target instanceof final PsiField targetField)) {
                        return;
                    }
                    final String targetFieldName = targetField.getName();
                    final Optional<MemberField> memberField = memberFields.stream().filter(x -> x.name().equals(targetFieldName)).findAny();
                    if(memberField.isPresent()) {
                        result.setValue(memberField.get());
                        stopWalking();
                    }
                }
            });
        }
        return result.getValue();
    }

    @Nullable
    private MemberField tryFindSourceClassMemberInFields(final List<MemberField> memberFields) {
        final String sourceClassCanonicalName = sourceClass.getQualifiedName();
        if(sourceClassCanonicalName == null) {
            return null;
        }

        List<MemberField> possibleSource = memberFields.stream().filter(x -> x.isExactType(sourceClassCanonicalName)).filter(x -> !x.isMock()).collect(Collectors.toList());
        if(possibleSource.isEmpty()) {
            possibleSource = memberFields.stream().filter(x -> x.isType(sourceClassCanonicalName)).filter(x -> !x.isMock()).collect(Collectors.toList());
        }

        if(possibleSource.isEmpty()) {
            return null;
        }

        if(possibleSource.size() == 1) {
            return possibleSource.get(0);
        }

        List<MemberField> filteredSource = possibleSource.stream().filter(x -> x.name().endsWith("UnderTest")).collect(Collectors.toList());
        if(filteredSource.size() == 1) {
            return filteredSource.get(0);
        }
        if(filteredSource.size() > 1) {
            possibleSource = filteredSource;
        }

        filteredSource = possibleSource.stream().filter(x -> x.hasAnnotation(Constants.InjectMocksCanonicalName)).collect(Collectors.toList());
        if(!filteredSource.isEmpty()) {
            return filteredSource.get(0);
        }

        return possibleSource.get(0);
    }

    @Nullable
    private InternalInvokedConstructorInfo findConstructorCall(@Nullable final MemberField sourceClassMember) {
        final PsiMethod[] testClassMethods = testClass.getMethods();
        final List<PsiMethod> setupMethods = Arrays.stream(testClassMethods).filter(ExistingInfoPopulator::isSetupMethod).toList();

        if(sourceClassMember != null) {
            // First check the setup method if there is one.
            for(final PsiMethod method : setupMethods) {
                final InternalInvokedConstructorInfo constructorCall = checkMethodForSourceClassMemberInit(sourceClassMember, method, true);
                if(constructorCall != null) {
                    return constructorCall;
                }
            }
            // Check for a constructor call in any method.
            for(final PsiMethod method : testClassMethods) {
                final InternalInvokedConstructorInfo constructorCall = checkMethodForSourceClassMemberInit(sourceClassMember, method, false);
                if(constructorCall != null) {
                    return constructorCall;
                }
            }
        }

        // Check for any source class constructor call in the setup methods.
        for(final PsiMethod method : setupMethods) {
            final InternalInvokedConstructorInfo constructorCall = checkMethodForSourceConstructorCall(method, true);
            if(constructorCall != null) {
                return constructorCall;
            }
        }
        // Check for any source class constructor call in the any method.
        for(final PsiMethod method : testClassMethods) {
            final InternalInvokedConstructorInfo constructorCall = checkMethodForSourceConstructorCall(method, false);
            if(constructorCall != null) {
                return constructorCall;
            }
        }
        return null;
    }

    @Nullable
    private InternalInvokedConstructorInfo checkMethodForSourceClassMemberInit(
            final MemberField sourceClassMember, final PsiMethod method, final boolean isInSetupMethod) {
        final MutableObject<InternalInvokedConstructorInfo> returnedConstructorInfo = new MutableObject<>();

        if(method instanceof final GrMethod grMethod) {
            grMethod.accept(new GroovyRecursiveElementVisitor() {
                @Override
                public void visitAssignmentExpression(
                        @NotNull final GrAssignmentExpression assignmentExpression) {
                    super.visitAssignmentExpression(assignmentExpression);
                    if(returnedConstructorInfo.getValue() != null) {
                        return;
                    }
                    // Determine if LHS resolves to the instance of the source class.
                    boolean leftSideIsSourceClass = false;
                    final GrExpression leftExpression = assignmentExpression.getLValue();
                    if(leftExpression instanceof final GrReferenceExpression leftReferenceExp) {
                        final PsiElement leftField = leftReferenceExp.resolve();
                        leftSideIsSourceClass = fieldMatches(leftField, sourceClassMember);
                    }

                    if(!leftSideIsSourceClass) {
                        return;
                    }

                    // Determine if RHS is a constructor call.
                    final GrExpression rightExpression = assignmentExpression.getRValue();
                    if(rightExpression instanceof final PsiConstructorCall rightMethodCall) {
                        returnedConstructorInfo.setValue(createInfoFromPsiConstructorCall(rightMethodCall, isInSetupMethod));
                    } else if(rightExpression instanceof final GrNewExpression rightNewExpression) {
                        returnedConstructorInfo.setValue(createInfoFromGrNewExpression(rightNewExpression, isInSetupMethod));
                    }
                }
            });
        } else {
            method.accept(new JavaRecursiveElementWalkingVisitor() {
                @Override
                public void visitAssignmentExpression(@NotNull final PsiAssignmentExpression assignmentExpression) {
                    super.visitAssignmentExpression(assignmentExpression);
                    // Determine if LHS resolves to the instance of the source class.
                    boolean leftSideIsSourceClass = false;
                    final PsiExpression leftExpression = assignmentExpression.getLExpression();
                    if(leftExpression instanceof final PsiReferenceExpression leftReferenceExp) {
                        final PsiElement leftField = leftReferenceExp.resolve();
                        leftSideIsSourceClass = fieldMatches(leftField, sourceClassMember);
                    }

                    if(!leftSideIsSourceClass) {
                        return;
                    }

                    // Determine if RHS is a constructor call.
                    final PsiExpression rightExpression = assignmentExpression.getRExpression();
                    if(rightExpression instanceof final PsiConstructorCall rightMethodCall) {
                        returnedConstructorInfo.setValue(createInfoFromPsiConstructorCall(rightMethodCall, isInSetupMethod));
                        stopWalking();
                    }
                }
            });
        }
        return returnedConstructorInfo.getValue();
    }

    @Nullable
    private InternalInvokedConstructorInfo checkMethodForSourceConstructorCall(final PsiMethod method, final boolean isInSetupMethod) {
        final MutableObject<InternalInvokedConstructorInfo> returnedConstructorInfo = new MutableObject<>();

        if(method instanceof final GrMethod grMethod) {
            grMethod.accept(new GroovyRecursiveElementVisitor() {

                @Override
                public void visitVariableDeclaration(@NotNull final GrVariableDeclaration variableDeclaration) {
                    final GrVariable[] declaredElements = variableDeclaration.getVariables();
                    for(final GrVariable declaredElement : declaredElements) {
                        final GrExpression initializer = declaredElement.getInitializerGroovy();
                        if(initializer instanceof final GrConstructorCall rightMethodCall) {
                            final PsiMethod psiMethod = rightMethodCall.resolveMethod();
                            if(!isInSourceClass(psiMethod)) {
                                continue;
                            }
                            returnedConstructorInfo.setValue(createInfoFromPsiConstructorCall(rightMethodCall, isInSetupMethod));
                            break;
                        }
                    }
                }

                @Override
                public void visitAssignmentExpression(
                        @NotNull final GrAssignmentExpression assignmentExpression) {
                    if(returnedConstructorInfo.getValue() != null) {
                        return;
                    }

                    // Determine if RHS is a constructor call.
                    final GrExpression rightExpression = assignmentExpression.getRValue();
                    if(rightExpression instanceof final PsiConstructorCall rightMethodCall) {
                        final PsiMethod calledMethod = rightMethodCall.resolveMethod();
                        if(isInSourceClass(calledMethod)) {
                            returnedConstructorInfo.setValue(createInfoFromPsiConstructorCall(rightMethodCall, isInSetupMethod));
                            return;
                        }
                    } else if(rightExpression instanceof final GrNewExpression rightNewExpression) {
                        final PsiMethod calledMethod = rightNewExpression.resolveMethod();
                        if(isInSourceClass(calledMethod)) {
                            returnedConstructorInfo.setValue(createInfoFromGrNewExpression(rightNewExpression, isInSetupMethod));
                            return;
                        }
                    }
                }
            });
        } else {
            method.accept(new JavaRecursiveElementWalkingVisitor() {

                @Override
                public void visitDeclarationStatement(@NotNull final PsiDeclarationStatement statement) {
                    final PsiElement[] declaredElements = statement.getDeclaredElements();
                    for(final PsiElement declaredElement : declaredElements) {
                        if(!(declaredElement instanceof final PsiLocalVariable psiLocalVariable)) {
                            continue;
                        }
                        final PsiExpression initializer = psiLocalVariable.getInitializer();
                        if(!(initializer instanceof final PsiConstructorCall rightMethodCall)) {
                            continue;
                        }
                        final PsiMethod psiMethod = rightMethodCall.resolveMethod();
                        if(!isInSourceClass(psiMethod)) {
                            continue;
                        }
                        returnedConstructorInfo.setValue(createInfoFromPsiConstructorCall(rightMethodCall, isInSetupMethod));
                        stopWalking();
                        break;
                    }
                }

                @Override
                public void visitAssignmentExpression(@NotNull final PsiAssignmentExpression assignmentExpression) {
                    // Determine if RHS is a constructor call.
                    final PsiExpression rightExpression = assignmentExpression.getRExpression();
                    if(!(rightExpression instanceof final PsiConstructorCall rightMethodCall)) {
                        return;
                    }
                    final PsiMethod psiMethod = rightMethodCall.resolveMethod();
                    if(!isInSourceClass(psiMethod)) {
                        return;
                    }
                    returnedConstructorInfo.setValue(createInfoFromPsiConstructorCall(rightMethodCall, isInSetupMethod));
                    stopWalking();
                }
            });
        }
        return returnedConstructorInfo.getValue();
    }

    private boolean isInSourceClass(final PsiMethod calledMethod) {
        if(calledMethod == null) {
            return false;
        }
        final PsiClass containingClass = calledMethod.getContainingClass();
        if(containingClass == null) {
            return false;
        }
        return StringUtils.equals(containingClass.getQualifiedName(), sourceClass.getQualifiedName());
    }

    private static InternalInvokedConstructorInfo createInfoFromPsiConstructorCall(final PsiConstructorCall constructorCall, final boolean isInSetupMethod) {
        final PsiMethod calledConstructor = constructorCall.resolveMethod();
        if(calledConstructor == null) {
            return new InternalInvokedConstructorInfo(new ArrayList<>(0), null, true, true, isInSetupMethod);
        }

        final PsiExpressionList argsList = constructorCall.getArgumentList();
        if(argsList == null) {
            return new InternalInvokedConstructorInfo(new ArrayList<>(), calledConstructor, false, false, isInSetupMethod);
        } else {
            final PsiExpression[] argArray = argsList.getExpressions();
            final ArrayList<PsiElement> argArrayList = new ArrayList<>(argArray.length);
            argArrayList.addAll(Arrays.asList(argArray));
            return new InternalInvokedConstructorInfo(argArrayList, calledConstructor, false, true, isInSetupMethod);
        }
    }

    private static InternalInvokedConstructorInfo createInfoFromPsiConstructorCall(final GrConstructorCall constructorCall, final boolean isInSetupMethod) {
        final PsiMethod calledConstructor = constructorCall.resolveMethod();
        if(calledConstructor == null) {
            return new InternalInvokedConstructorInfo(new ArrayList<>(0), null, true, true, isInSetupMethod);
        }

        final GrArgumentList argsList = constructorCall.getArgumentList();
        if(argsList == null) {
            return new InternalInvokedConstructorInfo(new ArrayList<>(), calledConstructor, false, false, isInSetupMethod);
        } else {
            final GrExpression[] expressionArguments = argsList.getExpressionArguments();
            final ArrayList<PsiElement> argArrayList = new ArrayList<>(expressionArguments.length);
            argArrayList.addAll(Arrays.asList(expressionArguments));
            return new InternalInvokedConstructorInfo(argArrayList, calledConstructor, false, true, isInSetupMethod);
        }
    }

    private static InternalInvokedConstructorInfo createInfoFromGrNewExpression(final GrNewExpression constructorCall, final boolean isInSetupMethod) {
        final PsiMethod calledConstructor = constructorCall.resolveMethod();
        if(calledConstructor == null) {
            return new InternalInvokedConstructorInfo(new ArrayList<>(0), null, true, true, isInSetupMethod);
        }

        final GrArgumentList argsList = constructorCall.getArgumentList();
        if(argsList == null) {
            return new InternalInvokedConstructorInfo(new ArrayList<>(), calledConstructor, false, false, isInSetupMethod);
        } else {
            // The reason we need this method (createInfoFromGrNewExpression) exists is: argsList.getExpressions()
            // returns an empty list. We need to call getExpressionArguments() to get the actual list of arguments
            // used in the GrNewExpression.
            final GrExpression[] argArray = argsList.getExpressionArguments();
            final ArrayList<PsiElement> argArrayList = new ArrayList<>(argArray.length);
            argArrayList.addAll(Arrays.asList(argArray));
            return new InternalInvokedConstructorInfo(argArrayList, calledConstructor, false, true, isInSetupMethod);
        }
    }

    private static boolean fieldMatches(final PsiElement leftField, final MemberField sourceClassMember) {
        if(leftField instanceof final PsiField leftPsiField) {
            return Objects.equals(leftPsiField.getName(), sourceClassMember.name());
        }
        return false;
    }

    static PsiMethod findSetupMethod(final PsiClass sourceClass) {
        for(final PsiMethod method : sourceClass.getMethods()) {
            if(isSetupMethod(method)) {
                return method;
            }
        }
        return null;
    }

    private static boolean isSetupMethod(final PsiMethod method) {
        // Check for known setup method names.
        if(Constants.SetupMethodNames.contains(method.getName())) {
            return true;
        }

        // Check for know setup method annotations.
        final PsiModifierList modifierList = method.getModifierList();
        final PsiAnnotation[] annotations = modifierList.getAnnotations();
        for(final PsiAnnotation annotation : annotations) {
            final String qualifiedName = annotation.getQualifiedName();
            if(qualifiedName != null) {
                final String simpleName = computeSimpleName(qualifiedName);
                if(Constants.SetupMethodAnnotations.contains(simpleName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
