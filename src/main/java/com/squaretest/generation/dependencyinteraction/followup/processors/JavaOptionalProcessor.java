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
package com.squaretest.generation.dependencyinteraction.followup.processors;

import com.intellij.codeInspection.streamToLoop.FunctionHelper;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.LambdaUtil;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiLambdaExpression;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiMethodReferenceExpression;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiType;
import com.intellij.psi.util.InheritanceUtil;
import com.intellij.psi.util.PsiUtil;
import com.siyeh.ig.psiutils.FunctionalExpressionUtils;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.dependencyinteraction.followup.BooleanConversionMethod;
import com.squaretest.generation.dependencyinteraction.followup.RValueInfo;
import com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil;
import com.squaretest.generation.dependencyinteraction.outcomes.MethodPathsInfoProvider;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Optional;

import static com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil.*;

public class JavaOptionalProcessor implements MethodCallProcessor {

    @NotNull
    private final MethodPathsInfoProvider methodPathsInfoProvider;
    @NotNull
    private final JavaPsiFacade javaPsiFacade;
    @NotNull
    private final PsiClass sourceClass;

    public JavaOptionalProcessor(
            @NotNull final MethodPathsInfoProvider methodPathsInfoProvider,
            @NotNull final JavaPsiFacade javaPsiFacade, @NotNull final PsiClass sourceClass) {
        this.methodPathsInfoProvider = methodPathsInfoProvider;
        this.javaPsiFacade = javaPsiFacade;
        this.sourceClass = sourceClass;
    }

    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithArgThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final PsiElement originalStartingElement,
            @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!isJavaOptional(containingClass) && !isJavaNumericOptional(containingClass)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        // We're the params of a method call.
        final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
        if(StringUtils.equals(methodName, "of")) {
            if(currentReturnOutcome == ReturnOutcome.Null) {
                // This is likely a bug that will cause a NullPointerException.
                // Assume the developer meant to call ofNullable(..).
                ret.withReturnOutcome(ReturnOutcome.Absent);
                return NextStep.Continue;
            }
            if(currentReturnOutcome == ReturnOutcome.Unknown) {
                final PsiExpression arg = SQPsiUtil.getArgumentThatContainsElement(currentMethodCall, originalStartingElement);
                if(arg == null) {
                    return NextStep.Continue;
                }
                final PsiType argType = arg.getType();
                if(InheritanceUtil.isInheritor(argType, JavaNames.JavaLangIterable)) {
                    return NextStep.Continue;
                }
                ret.withReturnOutcome(ReturnOutcome.Other);
                return NextStep.Continue;
            }
            return NextStep.Continue;
        }
        if(methodName.equals("ofNullable")) {
            if(currentReturnOutcome == ReturnOutcome.Null) {
                ret.withReturnOutcome(ReturnOutcome.Absent);
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "get", "isPresent", "isEmpty", "stream")) {
            // These methods either don't have args, return values or are too complicated to follow.
            // For method that don't have args: we shouldn't even be here. We can't be passed to a method
            // that takes no args.
            // Bail out.
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "ifPresent", "ifPresentOrElse")) {
            // These methods either don't have args, return values or are too complicated to follow.
            // For method that don't have args: we shouldn't even be here. We can't be passed to a method
            // that takes no args.
            // Bail out.
            ret.withReturnOutcome(ReturnOutcome.Void);
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "filter")) {
            if(currentReturnOutcome == ReturnOutcome.False) {
                ret.withReturnOutcome(ReturnOutcome.Absent);
                return NextStep.Continue;
            } else if(currentReturnOutcome == ReturnOutcome.True) {
                ret.withReturnOutcome(ReturnOutcome.Other);
                return NextStep.Continue;
            } else {
                ret.withReturnOutcome(ReturnOutcome.Unknown);
                return NextStep.Continue;
            }
        }
        if(StringUtils.equalsAny(methodName, "map", "flatMap")) {
            // The current RValue is being passed to map or flatMap.
            if(currentReturnOutcome == ReturnOutcome.Null) {
                ret.withReturnOutcome(ReturnOutcome.Absent);
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "or")) {
            // We only reach this point if we're a simple supplier passed to the or(..) method.
            // Let the return value and DIs propagate.
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "orElse")) {
            // We are being passed to the orElse() method.
            // Keep our current return outcome and continue.
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "orElseGet")) {
            // We only reach this point if we're a simple supplier passed to the or(..) method.
            // Let the return value and DIs propagate.
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "orElseThrow")) {
            // Our DI is passed to .orElseThrow(..).
            final PsiType absentException = getOrElseThrowException(currentMethodCall);
            if(absentException != null) {
                ret.withExceptionThrown(currentMethodCall, absentException);
                return NextStep.Return;
            } else {
                // We couldn't infer the exception. This shouldn't happen.
                // If it does, just keep going.
                return NextStep.Continue;
            }
        }
        // If we reach this point we have a call like optional.equals() or optional.toString().
        // Just keep going.
        final PsiExpression[] args = currentMethodCall.getArgumentList().getExpressions();
        ret.addElementsContainingDisHit(getArgsThatDoNotContainElement(args, originalStartingElement));
        ret.withReturnOutcome(ReturnOutcome.Unknown);
        return NextStep.Continue;
    }

    @Override
    @Nullable
    public NextStep tryProcessMethodCallWithQualifierThatContainsElement(
            @NotNull final PsiClass containingClass, @NotNull final PsiMethod psiMethod,
            @NotNull final PsiMethodCallExpression currentMethodCall, @NotNull final RValueInfo ret, final boolean preserveUnknown) {
        if(!isJavaOptional(containingClass) && !isJavaNumericOptional(containingClass)) {
            return null;
        }
        final String methodName = psiMethod.getName();
        if(StringUtils.equalsAny(methodName, "get", "getAsInt", "getAsDouble", "getAsLong")) {
            // We are exiting the Optional call chain.
            if(ret.getReturnOutcome() == ReturnOutcome.Absent) {
                ret.withExceptionThrown(currentMethodCall, getNoSuchElementExceptionType());
                return NextStep.Return;
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "isPresent")) {
            final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
            ret.withBooleanConversionMethod(BooleanConversionMethod.Optional);
            if(currentReturnOutcome == ReturnOutcome.Absent || currentReturnOutcome == ReturnOutcome.Null) {
                ret.withReturnOutcome(ReturnOutcome.False);
            } else {
                if(currentReturnOutcome != ReturnOutcome.Unknown || !preserveUnknown) {
                    ret.withReturnOutcome(ReturnOutcome.True);
                }
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "isEmpty")) {
            final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
            ret.withBooleanConversionMethod(BooleanConversionMethod.Optional);
            if(currentReturnOutcome == ReturnOutcome.Absent || currentReturnOutcome == ReturnOutcome.Null) {
                ret.withReturnOutcome(ReturnOutcome.True);
            } else {
                if(currentReturnOutcome != ReturnOutcome.Unknown || !preserveUnknown) {
                    ret.withReturnOutcome(ReturnOutcome.False);
                }
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "ifPresent")) {
            final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
            if(currentReturnOutcome != ReturnOutcome.Absent) {
                final PsiElement firstArg = SQPsiUtil.getArgAtIndex(currentMethodCall, 0);
                ret.addElementContainingDisHit(firstArg);
                if(currentReturnOutcome != ReturnOutcome.Unknown) {
                    final PsiType alwaysThrownException = determineAlwaysThrownExceptionForIfPresentArg(firstArg);
                    if(alwaysThrownException != null) {
                        ret.withExceptionThrown(firstArg, alwaysThrownException);
                        return NextStep.Return;
                    }
                }
            }
            ret.withReturnOutcome(ReturnOutcome.Void);
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "ifPresentOrElse")) {
            final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
            if(currentReturnOutcome == ReturnOutcome.Absent) {
                final PsiElement secondArg = SQPsiUtil.getArgAtIndex(currentMethodCall, 1);
                ret.addElementContainingDisHit(secondArg);
                final PsiType alwaysThrownException = determineAlwaysThrownExceptionForIfPresentArg(secondArg);
                if(alwaysThrownException != null) {
                    ret.withExceptionThrown(secondArg, alwaysThrownException);
                    return NextStep.Return;
                }
            } else {
                final PsiElement firstArg = SQPsiUtil.getArgAtIndex(currentMethodCall, 0);
                ret.addElementContainingDisHit(firstArg);
                if(currentReturnOutcome != ReturnOutcome.Unknown) {
                    final PsiType alwaysThrownException = determineAlwaysThrownExceptionForIfPresentArg(firstArg);
                    if(alwaysThrownException != null) {
                        ret.withExceptionThrown(firstArg, alwaysThrownException);
                        return NextStep.Return;
                    }
                }
            }
            ret.withReturnOutcome(ReturnOutcome.Void);
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "stream")) {
            final ReturnOutcome currentReturnOutcome = ret.getReturnOutcome();
            if(currentReturnOutcome == ReturnOutcome.Absent) {
                ret.withReturnOutcome(ReturnOutcome.Empty);
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "filter", "map", "flatMap")) {
            if(ret.getReturnOutcome() != ReturnOutcome.Absent) {
                ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "or")) {
            // This only runs if our DI returns absent.
            if(ret.getReturnOutcome() == ReturnOutcome.Absent) {
                ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
                ret.withReturnOutcome(ReturnOutcome.Other);
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "orElse")) {
            if(ret.getReturnOutcome() == ReturnOutcome.Absent) {
                ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
                if(SQPsiUtil.isCalledWithSingleNullArg(currentMethodCall)) {
                    ret.withReturnOutcome(ReturnOutcome.Null);
                } else if(SQPsiUtil.isSingleArgWithEmptyInitExpression(currentMethodCall)) {
                    ret.withReturnOutcome(ReturnOutcome.Empty);
                } else {
                    ret.withReturnOutcome(ReturnOutcome.Other);
                }
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "orElseGet")) {
            if(ret.getReturnOutcome() == ReturnOutcome.Absent) {
                ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
                ret.withReturnOutcome(ReturnOutcome.Other);
            }
            return NextStep.Continue;
        }
        if(StringUtils.equalsAny(methodName, "orElseThrow")) {
            if(ret.getReturnOutcome() == ReturnOutcome.Absent) {
                // The orElse(..) code is run in the absentCase (add to absentParentElements).
                // The exception is thrown in the absentCase.
                ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
                final PsiType absentException = getOrElseThrowException(currentMethodCall);
                if(absentException == null) {
                    // This shouldn't happen.
                    ret.withReturnOutcome(ReturnOutcome.Unknown);
                    return NextStep.Continue;
                }
                ret.withExceptionThrown(currentMethodCall, absentException);
                return NextStep.Return;
            }
            return NextStep.Continue;
        }
        // If we reach this point we have a call like optional.equals() or optional.toString().
        // Just keep going.
        ret.addElementContainingDisHit(currentMethodCall.getArgumentList());
        ret.withReturnOutcome(ReturnOutcome.Unknown);
        return NextStep.Continue;
    }

    @Nullable
    private PsiType determineAlwaysThrownExceptionForIfPresentArg(final PsiElement arg) {
        if(arg instanceof PsiLambdaExpression) {
            return methodPathsInfoProvider.getAlwaysThrownExceptionType((PsiLambdaExpression) arg);
        }
        if(arg instanceof PsiMethodReferenceExpression) {
            return methodPathsInfoProvider.getAlwaysThrownExceptionType((PsiMethodReferenceExpression) arg);
        }
        return null;
    }

    @Nullable
    private PsiType getOrElseThrowException(final PsiMethodCallExpression currentMethodCall) {
        final PsiExpression[] argumentExpressions = currentMethodCall.getArgumentList().getExpressions();
        if(argumentExpressions.length == 0) {
            return getNoSuchElementExceptionType();
        }
        PsiType resultType = getFunctionalType(argumentExpressions[0], 0);
        if(resultType == null) {
            resultType = getSupplierType(argumentExpressions[0]);
        }
        if(resultType == null) {
            return getNoSuchElementExceptionType();
        }
        if(InheritanceUtil.isInheritor(resultType, "java.lang.Throwable")) {
            return resultType;
        }
        return getNoSuchElementExceptionType();
    }

    @Nullable
    private PsiType getSupplierType(final PsiExpression argumentExpression) {
        final PsiType argType = argumentExpression.getType();
        if(argType == null) {
            return null;
        }
        final PsiClass psiClass = PsiUtil.resolveClassInType(argType);
        if(psiClass == null) {
            return null;
        }

        // Search for a get() method and check the return type.
        final PsiMethod[] getMethods = psiClass.findMethodsByName("get", false);
        final Optional<PsiMethod> getMethod = Arrays.stream(getMethods).filter(
                x -> x.getParameterList().getParametersCount() == 0
                        && x.hasModifierProperty(PsiModifier.PUBLIC)
                        && !x.hasModifierProperty(PsiModifier.STATIC)).findAny();
        if(getMethod.isEmpty()) {
            return null;
        }

        final PsiType returnType = getMethod.get().getReturnType();
        final PsiClass resolvedClassInReturnType = PsiUtil.resolveClassInType(returnType);
        if(resolvedClassInReturnType != null && resolvedClassInReturnType.getQualifiedName() != null) {
            return returnType;
        }
        return null;
    }

    private PsiType getNoSuchElementExceptionType() {
        final PsiElementFactory elementFactory = javaPsiFacade.getElementFactory();
        final PsiClass exceptionClass = javaPsiFacade.findClass("java.util.NoSuchElementException", sourceClass.getResolveScope());
        if(exceptionClass == null) {
            return null;
        }
        return elementFactory.createType(exceptionClass);
    }

    /**
     * Copied from {@link FunctionHelper#create(PsiExpression, int, boolean)}.
     *
     * @param expression the lambda expression.
     * @param paramCount the number of arguments in the lambda expression.
     * @return the type, if one could be determined.
     */
    @Nullable
    private static PsiType getFunctionalType(PsiExpression expression, int paramCount) {
        expression = PsiUtil.skipParenthesizedExprDown(expression);
        if(expression == null) {
            return null;
        }
        PsiType type = FunctionalExpressionUtils.getFunctionalExpressionType(expression);
        if(!(type instanceof PsiClassType)) {
            return null;
        }
        PsiMethod interfaceMethod = LambdaUtil.getFunctionalInterfaceMethod(type);
        if(interfaceMethod == null || interfaceMethod.getParameterList().getParametersCount() != paramCount) {
            return null;
        }
        PsiType returnType = interfaceMethod.getReturnType();
        if(returnType == null) {
            return null;
        }
        returnType = ((PsiClassType) type).resolveGenerics().getSubstitutor().substitute(returnType);
        return returnType;
    }
}
