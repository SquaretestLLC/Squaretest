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

import com.intellij.psi.PsiCall;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiExpressionList;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiVariable;
import com.squaretest.generation.dataflow.DataflowUtils;
import com.squaretest.generation.defaulttypes.beans.BeanInfoProvider;
import com.squaretest.generation.dependencyinteraction.SQExpressionUtils;
import com.squaretest.generation.sourcevars.SourceVariableProvider;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class UsedPropertyInfoProvider {
    @NotNull
    private final CalledMethodsInfoProvider calledMethodsInfoProvider;
    @NotNull
    private final SourceVariableProvider sourceVariableProvider;
    @NotNull
    private final BeanInfoProvider beanInfoProvider;
    @NotNull
    private final MethodCalledDecider sourceAndSuperDecider;
    @NotNull
    private final MethodCalledDecider staticMethodDecider;

    public UsedPropertyInfoProvider(
            @NotNull final CalledMethodsInfoProvider calledMethodsInfoProvider,
            @NotNull final SourceVariableProvider sourceVariableProvider,
            @NotNull final BeanInfoProvider beanInfoProvider) {
        this.calledMethodsInfoProvider = calledMethodsInfoProvider;
        this.sourceVariableProvider = sourceVariableProvider;
        this.beanInfoProvider = beanInfoProvider;
        this.sourceAndSuperDecider = new UsedInSourceDecider(calledMethodsInfoProvider);
        this.staticMethodDecider = new UsedInStaticHelpersDecider(calledMethodsInfoProvider);
    }

    public boolean calledInSource(@NotNull final PsiMethod psiMethod) {
        return calledMethodsInfoProvider.calledInSource(psiMethod);
    }

    public boolean calledInStaticHelpers(@NotNull final PsiMethod psiMethod) {
        return calledMethodsInfoProvider.calledInStaticHelpers(psiMethod);
    }

    public boolean propertyIsUsedInSource(@NotNull final PsiMethod psiMethod) {
        return propertyIsUsed(psiMethod, sourceAndSuperDecider);
    }

    public boolean propertyIsUsedInStaticHelpers(@NotNull final PsiMethod psiMethod) {
        return propertyIsUsed(psiMethod, staticMethodDecider);
    }

    public PsiField getTargetField(@NotNull final PsiMethod psiMethod) {
        return sourceVariableProvider.getTargetField(psiMethod, null);
    }

    private boolean propertyIsUsed(@NotNull final PsiMethod psiMethod, @NotNull final MethodCalledDecider methodCalledDecider) {
        final Set<PsiVariable> sourceVariables = sourceVariableProvider.getSourceVariables(psiMethod, null);
        final Set<PsiMethod> getters = getGetterMethods(sourceVariables);
        for(final PsiVariable psiVariable : sourceVariables) {
            if(psiVariable instanceof PsiField) {
                if(methodCalledDecider.isReferenced((PsiField) psiVariable)) {
                    return true;
                }
                continue;
            }
            if(psiVariable instanceof PsiParameter) {
                final PsiMethod containingMethod = DataflowUtils.getContainingMethodForParameter(psiVariable);
                if(containingMethod == null) {
                    continue;
                }
                if(containingMethod.isConstructor()) {
                    // If the containing method is a constructor, check to see if we have a call expression that passes in
                    // a value that is not "null". Also return true if we have a method reference expression.
                    final CalledMethodInfo calledMethodInfo = methodCalledDecider.getCalledMethodInfo(containingMethod);
                    if(isCalledWithNonNullParam(calledMethodInfo, (PsiParameter) psiVariable)) {
                        return true;
                    }
                    continue;
                }
                if(methodCalledDecider.isMethodCalled(containingMethod)) {
                    return true;
                }
            }
        }
        for(final PsiMethod getter : getters) {
            if(methodCalledDecider.isMethodCalled(getter)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCalledWithNonNullParam(@Nullable final CalledMethodInfo calledMethodInfo, @NotNull final PsiParameter psiVariable) {
        if(calledMethodInfo == null) {
            return false;
        }
        final String paramName = psiVariable.getName();
        final PsiMethod calledMethod = calledMethodInfo.getCalledMethod();
        final int indexOfParamWithName = getIndexOfParamWithName(calledMethod, paramName);
        if(indexOfParamWithName == -1) {
            // This shouldn't happen. The called method must have the provided parameter. Assume the method is called
            // with a value if this error case occurs.
            return true;
        }
        final List<PsiCall> callExpressions = calledMethodInfo.getMethodCallExpressions();
        for(final PsiCall call : callExpressions) {
            final PsiExpressionList argumentList = call.getArgumentList();
            if(argumentList == null) {
                // The method call expression is incomplete. Assume the developer will provide a value for the
                // parameter.
                return true;
            }
            final PsiExpression[] expressions = argumentList.getExpressions();
            if(indexOfParamWithName >= expressions.length) {
                // The call is incomplete (called with wrong number of args). Assume the developer will provide a value
                // for the parameter when they finish the expression.
                return true;
            }
            final PsiExpression argumentExpression = expressions[indexOfParamWithName];
            if(!SQExpressionUtils.isNullLiteral(argumentExpression)) {
                // The constructor is called with a non-null value.
                return true;
            }
        }

        if(!calledMethodInfo.getMethodReferenceExpressions().isEmpty()) {
            // Assume the method reference expression causes an argument to be provided for the param.
            return true;
        }
        return false;
    }

    public static int getIndexOfParamWithName(final PsiMethod psiMethod, final String name) {
        PsiParameter[] parameters = psiMethod.getParameterList().getParameters();
        for(int i = 0; i < parameters.length; i++) {
            final PsiParameter parameter = parameters[i];
            if(StringUtils.equals(parameter.getName(), name)) {
                return i;
            }
        }
        return -1;
    }

    private Set<PsiMethod> getGetterMethods(final Set<PsiVariable> sourceVariables) {
        for(final PsiVariable psiVariable : sourceVariables) {
            if(psiVariable instanceof PsiField) {
                return beanInfoProvider.getGettersForField((PsiField) psiVariable);
            }
        }
        return Collections.emptySet();
    }
}
