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
package com.squaretest.generation.dependencyinteraction.followup;

import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.*;
import com.intellij.psi.util.InheritanceUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtil;
import com.squaretest.generation.defaulttypes.JavaNames;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.intellij.psi.util.PsiUtil.getLanguageLevel;
import static com.squaretest.generation.dependencyinteraction.SQExpressionUtils.isNullLiteral;
import static com.squaretest.generation.dependencyinteraction.SQExpressionUtils.isZeroLiteral;
import static com.squaretest.generation.dependencyinteraction.outcomes.OutcomePopulator.isEmptyInitExpression;

public class SQPsiUtil {

    public static boolean isLanguageLevel6OrHigher(@NotNull PsiElement element) {
        return getLanguageLevel(element).isAtLeast(LanguageLevel.JDK_1_6);
    }

    static boolean isPrimitiveBoolean(final PsiType type) {
        if(!(type instanceof PsiPrimitiveType)) {
            return false;
        }
        return StringUtils.equals("boolean", type.getCanonicalText());
    }

    static boolean isNotEqualNullCheck(final PsiBinaryExpression psiBinaryExpression) {
        if(!JavaTokenType.NE.equals(psiBinaryExpression.getOperationTokenType())) {
            return false;
        }
        final PsiExpression left = psiBinaryExpression.getLOperand();
        final PsiExpression right = psiBinaryExpression.getROperand();
        return isNullLiteral(left) || isNullLiteral(right);
    }

    static boolean isEqualZeroCheck(final PsiBinaryExpression psiBinaryExpression) {
        if(!JavaTokenType.EQEQ.equals(psiBinaryExpression.getOperationTokenType())) {
            return false;
        }
        final PsiExpression left = psiBinaryExpression.getLOperand();
        final PsiExpression right = psiBinaryExpression.getROperand();
        return isZeroLiteral(left) || isZeroLiteral(right);
    }

    static boolean isNotEqualZeroCheck(final PsiBinaryExpression psiBinaryExpression) {
        if(!JavaTokenType.NE.equals(psiBinaryExpression.getOperationTokenType())) {
            return false;
        }
        final PsiExpression left = psiBinaryExpression.getLOperand();
        final PsiExpression right = psiBinaryExpression.getROperand();
        return isZeroLiteral(left) || isZeroLiteral(right);
    }

    static boolean isEqualNullCheck(final PsiBinaryExpression psiBinaryExpression) {
        if(!JavaTokenType.EQEQ.equals(psiBinaryExpression.getOperationTokenType())) {
            return false;
        }
        final PsiExpression left = psiBinaryExpression.getLOperand();
        final PsiExpression right = psiBinaryExpression.getROperand();
        return isNullLiteral(left) || isNullLiteral(right);
    }

    public static boolean isSingleArgWithEmptyInitExpression(final PsiMethodCallExpression currentMethodCall) {
        final PsiExpression[] args = currentMethodCall.getArgumentList().getExpressions();
        if(args.length != 1) {
            return false;
        }
        return isEmptyInitExpression(args[0]);
    }

    public static boolean isCalledWithSingleNullArg(final PsiMethodCallExpression currentMethodCall) {
        final PsiExpression[] args = currentMethodCall.getArgumentList().getExpressions();
        if(args.length != 1) {
            return false;
        }
        return isNullLiteral(args[0]);
    }

    public static boolean isJavaOrGuavaOptional(final PsiType psiType) {
        if(psiType == null) {
            return false;
        }
        final PsiClass theClass = PsiUtil.resolveClassInType(psiType);
        return isJavaOrGuavaOptional(theClass);
    }

    public static boolean isJavaOrGuavaOptional(final PsiClass theClass) {
        return isJavaOptional(theClass) || isGuavaOptional(theClass);
    }

    public static boolean isJavaOptional(final PsiType psiType) {
        if(psiType == null) {
            return false;
        }
        final PsiClass theClass = PsiUtil.resolveClassInType(psiType);
        return isJavaOptional(theClass);
    }

    public static boolean isJavaOptional(final PsiClass containingClass) {
        if(containingClass == null) {
            return false;
        }
        return StringUtils.equals(containingClass.getQualifiedName(), "java.util.Optional");
    }

    public static boolean isJavaNumericOptional(final PsiClass containingClass) {
        if(containingClass == null) {
            return false;
        }
        return StringUtils.equalsAny(containingClass.getQualifiedName(),
                "java.util.OptionalInt", "java.util.OptionalDouble", "java.util.OptionalLong");
    }

    public static boolean isGuavaOptional(final PsiClass containingClass) {
        if(containingClass == null) {
            return false;
        }
        return StringUtils.equals(containingClass.getQualifiedName(), "com.google.common.base.Optional");
    }

    public static boolean isJavaCollection(final PsiClass containingClass) {
        if(containingClass == null) {
            return false;
        }
        return InheritanceUtil.isInheritor(containingClass, JavaNames.JavaUtilCollection);
    }

    static PsiReturnStatement getContainingReturnStatement(final PsiElement callstackMethod, final PsiElement psiMethodCallExpression) {
        return getParentOfType(psiMethodCallExpression, PsiReturnStatement.class, callstackMethod);
    }

    static PsiThrowStatement getContainingThrowStatement(final PsiElement callstackMethod, final PsiElement psiMethodCallExpression) {
        return getParentOfType(psiMethodCallExpression, PsiThrowStatement.class, callstackMethod);
    }

    static PsiYieldStatement getContainingYieldStatement(final PsiElement callstackMethod, final PsiElement psiMethodCallExpression) {
        return getParentOfType(psiMethodCallExpression, PsiYieldStatement.class, callstackMethod);
    }

    static PsiSwitchLabeledRuleStatement getContainingSimpleSwitchLabeledRule(final PsiElement topElement, final PsiElement startingElement) {
        if(startingElement == null) {
            return null;
        }
        PsiElement currentElement = startingElement.getParent();
        while(currentElement != null) {
            if(currentElement instanceof PsiSwitchLabeledRuleStatement) {
                return (PsiSwitchLabeledRuleStatement) currentElement;
            }
            if(currentElement == topElement) {
                return null;
            }
            if(currentElement instanceof PsiBlockStatement) {
                return null;
            }
            currentElement = currentElement.getParent();
        }
        return null;
    }

    public static <T extends PsiElement> T getParentOfType(final PsiElement startingElement, final Class<T> parentType, final PsiElement topElement) {
        if(startingElement == null) {
            return null;
        }
        PsiElement currentElement = startingElement.getParent();
        while(currentElement != null) {
            if(parentType.isInstance(currentElement)) {
                return parentType.cast(currentElement);
            }
            if(currentElement == topElement) {
                return null;
            }
            currentElement = currentElement.getParent();
        }
        return null;
    }

    static PsiIfStatement getIfWithConditionThatContainsMethodCall(final PsiElement callstackMethod, final PsiElement psiMethodCallExpression) {
        PsiElement currentElement = psiMethodCallExpression.getParent();
        while(currentElement != null) {
            if(currentElement instanceof final PsiIfStatement ifStatement) {
                final PsiExpression ifCondition = ifStatement.getCondition();
                if(PsiTreeUtil.isAncestor(ifCondition, psiMethodCallExpression, false)) {
                    return (PsiIfStatement) currentElement;
                }
                return null;
            }
            if(currentElement == callstackMethod) {
                return null;
            }
            currentElement = currentElement.getParent();
        }
        return null;
    }

    static PsiSwitchExpression getSwitchExpressionWithConditionThatContainsMethodCall(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression) {
        PsiElement currentElement = psiMethodCallExpression.getParent();
        while(currentElement != null) {
            if(currentElement instanceof final PsiSwitchExpression psiSwitchExpression) {
                final PsiExpression conditionExpression = psiSwitchExpression.getExpression();
                if(PsiTreeUtil.isAncestor(conditionExpression, psiMethodCallExpression, false)) {
                    return (PsiSwitchExpression) currentElement;
                }
                return null;
            }
            if(currentElement == callstackMethod) {
                return null;
            }
            currentElement = currentElement.getParent();
        }
        return null;
    }

    static PsiSwitchStatement getSwitchStatementWithConditionThatContainsMethodCall(
            final PsiElement callstackMethod, final PsiElement psiMethodCallExpression) {
        PsiElement currentElement = psiMethodCallExpression.getParent();
        while(currentElement != null) {
            if(currentElement instanceof final PsiSwitchStatement psiSwitchStatement) {
                final PsiExpression conditionExpression = psiSwitchStatement.getExpression();
                if(PsiTreeUtil.isAncestor(conditionExpression, psiMethodCallExpression, false)) {
                    return (PsiSwitchStatement) currentElement;
                }
                return null;
            }
            if(currentElement == callstackMethod) {
                return null;
            }
            currentElement = currentElement.getParent();
        }
        return null;
    }

    static PsiWhileStatement getWhileWithConditionThatContainsMethodCall(final PsiElement callstackMethod, final PsiElement psiMethodCallExpression) {
        PsiElement currentElement = psiMethodCallExpression.getParent();
        while(currentElement != null) {
            if(currentElement instanceof final PsiWhileStatement whileStatement) {
                final PsiExpression whileCondition = whileStatement.getCondition();
                if(PsiTreeUtil.isAncestor(whileCondition, psiMethodCallExpression, false)) {
                    return (PsiWhileStatement) currentElement;
                }
                return null;
            }
            if(currentElement == callstackMethod) {
                return null;
            }
            currentElement = currentElement.getParent();
        }
        return null;
    }

    static PsiDoWhileStatement getDoWhileWithConditionThatContainsMethodCall(final PsiElement callstackMethod, final PsiElement psiMethodCallExpression) {
        PsiElement currentElement = psiMethodCallExpression.getParent();
        while(currentElement != null) {
            if(currentElement instanceof final PsiDoWhileStatement doWhileStatement) {
                final PsiExpression doWHileCondition = doWhileStatement.getCondition();
                if(PsiTreeUtil.isAncestor(doWHileCondition, psiMethodCallExpression, false)) {
                    return (PsiDoWhileStatement) currentElement;
                }
                return null;
            }
            if(currentElement == callstackMethod) {
                return null;
            }
            currentElement = currentElement.getParent();
        }
        return null;
    }

    static PsiForeachStatement getForeachWithIteratedValueThatContainsMethodCall(final PsiElement callstackMethod, final PsiElement psiMethodCallExpression) {
        PsiElement currentElement = psiMethodCallExpression.getParent();
        while(currentElement != null) {
            if(currentElement instanceof final PsiForeachStatement foreachStatement) {
                final PsiExpression iteratedValue = foreachStatement.getIteratedValue();
                if(PsiTreeUtil.isAncestor(iteratedValue, psiMethodCallExpression, false)) {
                    return (PsiForeachStatement) currentElement;
                }
                return null;
            }
            if(currentElement == callstackMethod) {
                return null;
            }
            currentElement = currentElement.getParent();
        }
        return null;
    }

    static PsiSynchronizedStatement getSynchronizedStatementWithLockThatContainsCall(final PsiElement callstackMethod, final PsiElement psiMethodCallExpression) {
        PsiElement currentElement = psiMethodCallExpression.getParent();
        while(currentElement != null) {
            if(currentElement instanceof final PsiSynchronizedStatement synchronizedStatement) {
                final PsiExpression lockExpression = synchronizedStatement.getLockExpression();
                if(PsiTreeUtil.isAncestor(lockExpression, psiMethodCallExpression, false)) {
                    return (PsiSynchronizedStatement) currentElement;
                }
                return null;
            }
            if(currentElement == callstackMethod) {
                return null;
            }
            currentElement = currentElement.getParent();
        }
        return null;
    }

    static ForLoopInfo getForLoopWithHeaderThatContainsMethodCall(final PsiElement callstackMethod, final PsiElement psiMethodCallExpression) {
        PsiElement currentElement = psiMethodCallExpression.getParent();
        while(currentElement != null) {
            if(currentElement instanceof final PsiForStatement forStatement) {
                final PsiExpression forCondition = forStatement.getCondition();
                if(PsiTreeUtil.isAncestor(forCondition, psiMethodCallExpression, false)) {
                    return new ForLoopInfo(forStatement, forCondition);
                }
                final PsiStatement initStatement = forStatement.getInitialization();
                if(PsiTreeUtil.isAncestor(initStatement, psiMethodCallExpression, false)) {
                    return new ForLoopInfo(forStatement, initStatement);
                }
                final PsiStatement updateStatement = forStatement.getUpdate();
                if(PsiTreeUtil.isAncestor(updateStatement, psiMethodCallExpression, false)) {
                    return new ForLoopInfo(forStatement, updateStatement);
                }
                return null;
            }
            if(currentElement == callstackMethod) {
                return null;
            }
            currentElement = currentElement.getParent();
        }
        return null;
    }

    static PsiAssertStatement getAssertExpressionWithConditionThatContainsMethodCall(final PsiElement callstackMethod, final PsiElement psiMethodCallExpression) {
        PsiElement currentElement = psiMethodCallExpression.getParent();
        while(currentElement != null) {
            if(currentElement instanceof final PsiAssertStatement foreachStatement) {
                final PsiExpression assertCondition = foreachStatement.getAssertCondition();
                if(PsiTreeUtil.isAncestor(assertCondition, psiMethodCallExpression, false)) {
                    return (PsiAssertStatement) currentElement;
                }
                return null;
            }
            if(currentElement == callstackMethod) {
                return null;
            }
            currentElement = currentElement.getParent();
        }
        return null;
    }

    static PsiExpression getHighestExpressionBetween(final PsiElement topElement, final PsiElement startingExpression) {
        PsiExpression lastExpression;
        if(startingExpression instanceof PsiExpression) {
            lastExpression = (PsiExpression) startingExpression;
        } else {
            lastExpression = null;
        }

        if(topElement == startingExpression) {
            return lastExpression;
        }

        PsiElement currentElement = startingExpression.getParent();
        while(currentElement != null) {
            if(currentElement == topElement) {
                if(topElement instanceof PsiExpression) {
                    return (PsiExpression) topElement;
                }
                return lastExpression;
            }
            if(currentElement instanceof PsiExpression) {
                lastExpression = (PsiExpression) currentElement;
            }
            currentElement = currentElement.getParent();
        }
        return lastExpression;
    }

    public static Set<PsiElement> getParentsBetweenInclusive(final PsiElement topElement, final PsiElement startingPoint) {
        if(startingPoint == topElement || startingPoint == null || topElement == null) {
            return Collections.emptySet();
        }
        final Set<PsiElement> ret = SetUtils.newIdentityHashSet();
        ret.add(startingPoint);
        PsiElement currentElement = startingPoint.getParent();
        while(currentElement != null) {
            ret.add(currentElement);
            if(currentElement == topElement) {
                return ret;
            }
            currentElement = currentElement.getParent();
        }

        // If we reach this point, the startingPoint is not a child element of topElement.
        // There are no parent elements between them.
        return Collections.emptySet();
    }

    public static PsiElement getArgAtIndex(final PsiMethodCallExpression currentMethodCall, final int index) {
        final PsiExpression[] expressions = currentMethodCall.getArgumentList().getExpressions();
        if(0 <= index && index < expressions.length) {
            return expressions[index];
        }
        return null;
    }

    @Nullable
    static <T extends PsiElement> T getElementThatContainsChild(final T[] elements, final PsiElement searchElement) {
        for(final T element : elements) {
            if(PsiTreeUtil.isAncestor(element, searchElement, false)) {
                return element;
            }
        }
        return null;
    }

    public static <T extends PsiElement> List<T> getElementsAfter(final T[] elements, final T searchElement) {
        final int index = getIndexOfElement(elements, searchElement);
        if(index == -1) {
            return Collections.emptyList();
        }
        return Arrays.asList(elements).subList(index + 1, elements.length);
    }

    public static <T extends PsiElement> List<T> getElementsBefore(final T[] elements, final T searchElement) {
        final int index = getIndexOfElement(elements, searchElement);
        if(index == -1) {
            return Collections.emptyList();
        }
        return Arrays.asList(elements).subList(0, index);
    }

    private static <T extends PsiElement> int getIndexOfElement(final T[] elements, final T searchElement) {
        for(int i = 0; i < elements.length; i++) {
            if(searchElement == elements[i]) {
                return i;
            }
        }
        return -1;
    }

    public static List<PsiElement> getSiblingsAfter(final PsiElement startingElement) {
        if(startingElement == null) {
            return Collections.emptyList();
        }
        PsiElement currentSibling = startingElement.getNextSibling();
        if(currentSibling == null) {
            return Collections.emptyList();
        }
        final List<PsiElement> ret = new ArrayList<>();
        while(currentSibling != null) {
            ret.add(currentSibling);
            currentSibling = currentSibling.getNextSibling();
        }
        return ret;
    }

    public static PsiExpression getArgumentThatContainsElement(final PsiMethodCallExpression currentMethodCall, final PsiElement originalStartingElement) {
        final PsiExpressionList argumentList = currentMethodCall.getArgumentList();
        final PsiExpression[] args = argumentList.getExpressions();
        final int index = getIndexOfElementThatContainsElement(args, originalStartingElement);
        if(index == -1) {
            return null;
        }
        return args[index];
    }

    public static <T extends PsiElement> List<T> getElementsAfterElementThatContainsStartingElement(final T[] operands, final PsiElement startingElement) {
        final int index = getIndexOfElementThatContainsElement(operands, startingElement);
        if(index == -1) {
            return Collections.emptyList();
        }
        return Arrays.asList(operands).subList(index, operands.length);
    }

    public static <T extends PsiElement> int getIndexOfElementThatContainsElement(final T[] argumentExpressions, final PsiElement startingElement) {
        for(int i = 0; i < argumentExpressions.length; i++) {
            if(PsiTreeUtil.isAncestor(argumentExpressions[i], startingElement, false)) {
                return i;
            }
        }
        return -1;
    }

    public static List<PsiExpression> getArgsThatDoNotContainElement(final PsiExpression[] operands, final PsiElement startingElement) {
        final List<PsiExpression> ret = new ArrayList<>(operands.length);
        boolean found = false;
        for(final PsiExpression expression : operands) {
            if(found) {
                ret.add(expression);
                continue;
            }
            if(PsiTreeUtil.isAncestor(expression, startingElement, false)) {
                found = true;
            } else {
                ret.add(expression);
            }
        }
        return ret;
    }

    public static List<PsiExpression> getNonSupplierArgsThatDoNotContainElement(final PsiParameter[] params, final PsiExpression[] operands, final PsiElement startingElement) {
        final List<PsiExpression> ret = new ArrayList<>(operands.length);
        boolean found = false;
        boolean isLastArgSupplier = params.length != 0 && isSupplier(params[params.length - 1]);
        for(int i = 0; i < operands.length; i++) {
            final PsiExpression expression = operands[i];
            if(found) {
                if((i < params.length && !isSupplier(params[i])) || (i >= params.length && !isLastArgSupplier)) {
                    ret.add(expression);
                }
                continue;
            }
            if(PsiTreeUtil.isAncestor(expression, startingElement, false)) {
                found = true;
            } else {
                if((i < params.length && !isSupplier(params[i])) || (i >= params.length && !isLastArgSupplier)) {
                    ret.add(expression);
                }
            }
        }
        return ret;
    }

    private static boolean isSupplier(final PsiParameter param) {
        return isSupplier(param.getType());
    }

    private static boolean isSupplier(final PsiType psiType) {
        final PsiClass typeClass = PsiUtil.resolveClassInType(psiType);
        if(typeClass == null) {
            return false;
        }
        final String qualifiedName = typeClass.getQualifiedName();
        return StringUtils.equals(qualifiedName, "java.util.function.Supplier");
    }
}
