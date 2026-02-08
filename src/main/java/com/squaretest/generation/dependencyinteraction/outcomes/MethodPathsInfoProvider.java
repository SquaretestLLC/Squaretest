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
package com.squaretest.generation.dependencyinteraction.outcomes;

import com.intellij.psi.*;
import com.intellij.psi.util.InheritanceUtil;
import com.intellij.psi.util.PsiUtil;
import com.squaretest.generation.defaulttypes.JavaNames;
import com.squaretest.generation.dependencyinteraction.DiAndNode;
import com.squaretest.generation.dependencyinteraction.RegexUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.squaretest.generation.dependencyinteraction.SQExpressionUtils.isNullLiteral;
import static com.squaretest.generation.dependencyinteraction.outcomes.OutcomePopulator.*;

public class MethodPathsInfoProvider {
    private static final List<Pattern> HelperMethodThatThrowsNameRegexes = List.of(
            // throwException
            // throwExceptionWithData
            // throwError
            // throwInvalidDataException
            Pattern.compile("^(throw|raise).*(Exception|Error).*"),
            // logAndThrow
            // logThenThrow
            // logThenThrowRuntimeException
            // logThenRaiseException
            // recordMetricAndThenThrow
            // recordMetricAndThenThrowInvalidDataException
            Pattern.compile("^.*(And|Then)(Throw|Raise).*(Exception)?$")
    );
    // Error
    // Failure
    // FirstError
    // FirstErrorCode
    // ErrorInfo
    // FailureInfo
    public static final Pattern ErrorClassNameRegex = Pattern.compile("^.*(Error|Failure)(Code|Info)*$");
    private final MethodExitPointsProvider methodExitPointsProvider;
    @NotNull
    private final Map<PsiMethod, Boolean> methodToReturnsCSViolation;
    @NotNull
    private final Map<PsiMethod, Boolean> methodToReturnsOptionalError;

    public MethodPathsInfoProvider(final MethodExitPointsProvider methodExitPointsProvider) {
        this.methodExitPointsProvider = methodExitPointsProvider;
        this.methodToReturnsCSViolation = new IdentityHashMap<>();
        this.methodToReturnsOptionalError = new IdentityHashMap<>();
    }

    public boolean canReturnAltValue(final PsiMethod sourceMethod) {
        final PsiType returnType = sourceMethod.getReturnType();
        if(returnType == null || PsiTypes.voidType().equals(returnType)) {
            return false;
        }
        final List<PsiElement> returnOrThrowStatements = methodExitPointsProvider.getReturnOrThrowStatements(sourceMethod);
        for(final PsiElement returnOrThrow : returnOrThrowStatements) {
            if(returnOrThrow instanceof PsiThrowStatement) {
                continue;
            }
            if(returnOrThrow instanceof final PsiReturnStatement psiReturnStatement) {
                final PsiExpression rhsExpression = psiReturnStatement.getReturnValue();
                if(rhsExpression == null) {
                    // The developer has incomplete code.
                    continue;
                }
                if(isNullLiteral(rhsExpression)) {
                    return true;
                }
                if(isFailedFutureInitExpression(rhsExpression)) {
                    return true;
                }
                if(isAbsentInitExpression(rhsExpression)) {
                    return true;
                }
                if(isEmptyInitExpression(rhsExpression)) {
                    return true;
                }
                continue;
            }
        }
        return false;
    }

    public boolean canReturnNormalValue(final PsiMethod sourceMethod) {
        final PsiType returnType = sourceMethod.getReturnType();
        if(returnType == null || PsiTypes.voidType().equals(returnType)) {
            return false;
        }
        final List<PsiElement> returnOrThrowStatements = methodExitPointsProvider.getReturnOrThrowStatements(sourceMethod);
        for(final PsiElement returnOrThrow : returnOrThrowStatements) {
            if(returnOrThrow instanceof PsiThrowStatement) {
                continue;
            }
            if(returnOrThrow instanceof final PsiReturnStatement psiReturnStatement) {
                final PsiExpression rhsExpression = psiReturnStatement.getReturnValue();
                if(rhsExpression == null) {
                    // The developer has incomplete code.
                    continue;
                }
                if(isNullLiteral(rhsExpression)) {
                    continue;
                }
                if(isFailedFutureInitExpression(rhsExpression)) {
                    continue;
                }
                if(isAbsentInitExpression(rhsExpression)) {
                    continue;
                }
                if(isEmptyInitExpression(rhsExpression)) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    public List<ReturnOutcome> getReturnOutcomes(final PsiMethod sourceMethod) {
        final List<PsiElement> returnOrThrowStatements = methodExitPointsProvider.getReturnOrThrowStatements(sourceMethod);
        if(returnOrThrowStatements.isEmpty()) {
            return Collections.emptyList();
        }
        final List<ReturnOutcome> ret = new ArrayList<>();
        for(final PsiElement returnOrThrow : returnOrThrowStatements) {
            if(returnOrThrow instanceof PsiThrowStatement) {
                continue;
            }
            if(returnOrThrow instanceof final PsiReturnStatement psiReturnStatement) {
                final PsiExpression rhsExpression = psiReturnStatement.getReturnValue();
                if(rhsExpression == null) {
                    // The developer has incomplete code or this is a void method.
                    continue;
                }
                if(isNullLiteral(rhsExpression)) {
                    ret.add(ReturnOutcome.Null);
                }
                if(isFailedFutureInitExpression(rhsExpression)) {
                    ret.add(ReturnOutcome.Failure);
                }
                if(isAbsentInitExpression(rhsExpression)) {
                    ret.add(ReturnOutcome.Absent);
                }
                if(isEmptyInitExpression(rhsExpression)) {
                    ret.add(ReturnOutcome.Empty);
                }
                ret.add(ReturnOutcome.Unknown);
            }
        }
        return ret;
    }

    @Nullable
    public ReturnOutcome getFirstReturnedAltValue(final PsiMethod sourceMethod) {
        final PsiType returnType = sourceMethod.getReturnType();
        if(returnType == null || PsiTypes.voidType().equals(returnType)) {
            return null;
        }
        final List<PsiElement> returnOrThrowStatements = methodExitPointsProvider.getReturnOrThrowStatements(sourceMethod);
        for(final PsiElement returnOrThrow : returnOrThrowStatements) {
            if(returnOrThrow instanceof PsiThrowStatement) {
                continue;
            }
            if(returnOrThrow instanceof final PsiReturnStatement psiReturnStatement) {
                final PsiExpression rhsExpression = psiReturnStatement.getReturnValue();
                if(rhsExpression == null) {
                    // The developer has incomplete code.
                    continue;
                }
                if(isNullLiteral(rhsExpression)) {
                    return ReturnOutcome.Null;
                }
                if(isFailedFutureInitExpression(rhsExpression)) {
                    return ReturnOutcome.Failure;
                }
                if(isAbsentInitExpression(rhsExpression)) {
                    return ReturnOutcome.Absent;
                }
                if(isEmptyInitExpression(rhsExpression)) {
                    return ReturnOutcome.Empty;
                }
                continue;
            }
        }
        return null;
    }

    public PsiType tryGetHelperMethodException(final PsiExpression psiExpression) {
        if(!(psiExpression instanceof PsiMethodCallExpression)) {
            return null;
        }
        final PsiMethod psiMethod = ((PsiMethodCallExpression) psiExpression).resolveMethod();
        return tryGetHelperMethodException(psiMethod);
    }

    @Nullable
    public PsiType tryGetHelperMethodException(final PsiMethod psiMethod) {
        if(psiMethod == null) {
            return null;
        }
        final String methodName = psiMethod.getName();
        if(!matchesHelperMethodThatThrowsName(methodName)) {
            return null;
        }
        return getAlwaysThrownExceptionType(psiMethod);
    }

    private boolean matchesHelperMethodThatThrowsName(final String methodName) {
        return RegexUtils.matchesAny(HelperMethodThatThrowsNameRegexes, methodName);
    }

    public boolean alwaysThrows(final PsiMethod sourceMethod) {
        final List<PsiElement> returnOrThrowStatements = methodExitPointsProvider.getReturnOrThrowStatements(sourceMethod);
        if(returnOrThrowStatements.stream().anyMatch(x -> x instanceof PsiReturnStatement)) {
            return false;
        }
        // Determine if we have a throw that is a direct child of the source method's code block.
        for(final PsiElement returnOrThrow : returnOrThrowStatements) {
            if(!(returnOrThrow instanceof final PsiThrowStatement psiThrowStatement)) {
                continue;
            }
            final PsiElement throwParent = psiThrowStatement.getParent();
            if(!(throwParent instanceof PsiCodeBlock)) {
                continue;
            }
            final PsiElement codeBlockParent = throwParent.getParent();
            if(codeBlockParent == sourceMethod) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public String getAlwaysThrownException(final PsiMethod sourceMethod) {
        final PsiType exceptionType = getAlwaysThrownExceptionType(sourceMethod);
        return exceptionType != null ? exceptionType.getCanonicalText() : null;
    }

    @Nullable
    public PsiType getAlwaysThrownExceptionType(final PsiMethod sourceMethod) {
        final List<PsiElement> returnOrThrowStatements = methodExitPointsProvider.getReturnOrThrowStatements(sourceMethod);
        if(returnOrThrowStatements.stream().anyMatch(x -> x instanceof PsiReturnStatement)) {
            return null;
        }
        // Determine if we have a throw that is a direct child of the source method's code block.
        for(final PsiElement returnOrThrow : returnOrThrowStatements) {
            if(!(returnOrThrow instanceof final PsiThrowStatement psiThrowStatement)) {
                continue;
            }
            final PsiElement throwParent = psiThrowStatement.getParent();
            if(!(throwParent instanceof PsiCodeBlock)) {
                continue;
            }
            final PsiElement codeBlockParent = throwParent.getParent();
            if(codeBlockParent == sourceMethod) {
                final PsiExpression exception = psiThrowStatement.getException();
                return exception != null ? exception.getType() : null;
            }
        }
        return null;
    }

    @Nullable
    public PsiType getAlwaysThrownExceptionType(final PsiLambdaExpression psiLambdaExpression) {
        final List<PsiElement> returnOrThrowStatements = methodExitPointsProvider.getReturnOrThrowStatements(psiLambdaExpression);
        if(returnOrThrowStatements.stream().anyMatch(x -> x instanceof PsiReturnStatement)) {
            return null;
        }
        // Determine if we have a throw that is a direct child of the lambda's code block.
        for(final PsiElement returnOrThrow : returnOrThrowStatements) {
            if(!(returnOrThrow instanceof final PsiThrowStatement psiThrowStatement)) {
                continue;
            }
            final PsiElement throwParent = psiThrowStatement.getParent();
            if(!(throwParent instanceof PsiCodeBlock)) {
                continue;
            }
            final PsiElement codeBlockParent = throwParent.getParent();
            if(codeBlockParent == psiLambdaExpression) {
                final PsiExpression exception = psiThrowStatement.getException();
                return exception != null ? exception.getType() : null;
            }
        }

        final PsiElement body = psiLambdaExpression.getBody();
        // Check to see if the body is a call like: x -> throwXAlreadyExists(..).
        if(body instanceof PsiMethodCallExpression) {
            final PsiType thrownException = tryGetHelperMethodException((PsiMethodCallExpression) body);
            if(thrownException != null) {
                return thrownException;
            }
        }

        // The lambda body should be a code block. If not, bail out.
        if(!(body instanceof PsiCodeBlock)) {
            return null;
        }
        // Check statements for a call to helper methods that throw.
        final PsiStatement[] statements = ((PsiCodeBlock) body).getStatements();
        for(final PsiStatement statement : statements) {
            if(statement instanceof PsiExpressionStatement) {
                final PsiExpression psiExpression = ((PsiExpressionStatement) statement).getExpression();
                final PsiType thrownException = tryGetHelperMethodException(psiExpression);
                if(thrownException != null) {
                    return thrownException;
                }
            }
        }
        return null;
    }

    public PsiType getAlwaysThrownExceptionType(final PsiMethodReferenceExpression psiMethodReferenceExpression) {
        final PsiElement methodElement = psiMethodReferenceExpression.resolve();
        if(!(methodElement instanceof PsiMethod)) {
            return null;
        }
        return tryGetHelperMethodException((PsiMethod) methodElement);
    }

    public boolean returnsListOfErrors(final DiAndNode startingNode) {
        final PsiMethod psiMethod = startingNode.internalDependencyInteraction().getPsiMethod();
        return returnsListOfErrors(psiMethod);
    }

    public boolean returnsListOfErrors(final PsiMethod psiMethod) {
        return methodToReturnsCSViolation.computeIfAbsent(psiMethod, this::returnsListOfErrorsImpl);
    }

    public boolean returnsOptionalError(final DiAndNode startingNode) {
        final PsiMethod psiMethod = startingNode.internalDependencyInteraction().getPsiMethod();
        return returnsOptionalError(psiMethod);
    }

    public boolean returnsOptionalError(final PsiMethod psiMethod) {
        return methodToReturnsOptionalError.computeIfAbsent(psiMethod, this::returnsOptionalErrorImpl);
    }

    private boolean returnsOptionalErrorImpl(final PsiMethod psiMethod) {
        final PsiType returnType = psiMethod.getReturnType();
        if(isNullOrVoid(returnType)) {
            return false;
        }
        final PsiClassType.ClassResolveResult resolveResult = PsiUtil.resolveGenericsClassInType(returnType);
        final PsiClass returnTypeClass = resolveResult.getElement();
        if(returnTypeClass == null) {
            return false;
        }
        if(!StringUtils.equalsAny(returnTypeClass.getQualifiedName(), JavaNames.JavaUtilOptional, JavaNames.GuavaOptional)) {
            return false;
        }
        final Map<PsiTypeParameter, PsiType> substitutionMap = resolveResult.getSubstitutor().getSubstitutionMap();
        if(substitutionMap.size() != 1) {
            return false;
        }
        final Map.Entry<PsiTypeParameter, PsiType> mapEntry = substitutionMap.entrySet().iterator().next();
        PsiType typeToUse = mapEntry.getValue();
        if(typeToUse instanceof PsiCapturedWildcardType) {
            typeToUse = ((PsiCapturedWildcardType) typeToUse).getUpperBound();
        }
        if(typeToUse == null) {
            return false;
        }
        if(InheritanceUtil.isInheritor(typeToUse, JavaNames.Throwable)) {
            return true;
        }
        final PsiClass subjectTypeClass = PsiUtil.resolveClassInType(typeToUse);
        if(subjectTypeClass == null) {
            return false;
        }
        final String qualifiedName = subjectTypeClass.getQualifiedName();
        if(qualifiedName == null) {
            return false;
        }
        final String name = subjectTypeClass.getName();
        if(name == null) {
            return false;
        }
        return ErrorClassNameRegex.matcher(name).matches();
    }

    private boolean returnsListOfErrorsImpl(final PsiMethod psiMethod) {
        final PsiType returnType = psiMethod.getReturnType();
        if(isNullOrVoid(returnType)) {
            return false;
        }
        final PsiClassType.ClassResolveResult resolveResult = PsiUtil.resolveGenericsClassInType(returnType);
        final PsiClass returnTypeClass = resolveResult.getElement();
        if(!InheritanceUtil.isInheritor(returnTypeClass, JavaNames.JavaLangIterable)) {
            return false;
        }
        final Map<PsiTypeParameter, PsiType> substitutionMap = resolveResult.getSubstitutor().getSubstitutionMap();
        if(substitutionMap.size() != 1) {
            return false;
        }
        final Map.Entry<PsiTypeParameter, PsiType> mapEntry = substitutionMap.entrySet().iterator().next();
        PsiType iteratedType = mapEntry.getValue();
        if(iteratedType instanceof PsiCapturedWildcardType) {
            iteratedType = ((PsiCapturedWildcardType) iteratedType).getUpperBound();
        }
        if(iteratedType == null) {
            return false;
        }
        if(InheritanceUtil.isInheritor(iteratedType, JavaNames.ConstraintViolation)) {
            return true;
        }
        final PsiClass iteratedClass = PsiUtil.resolveClassInType(iteratedType);
        if(iteratedClass == null) {
            return false;
        }
        return StringUtils.equals(iteratedClass.getQualifiedName(), JavaNames.DynamoDbFailedBatch);
    }
}
