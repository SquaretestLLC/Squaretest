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

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiType;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RValueInfo {
    private final List<PsiElement> elementsContainingDisHit;
    private final List<PsiMethodCallExpression> methodCallElements;
    private ReturnOutcome returnOutcome;
    private PsiType exceptionThrown;
    private PsiElement elementThatThrows;
    private BooleanConversionMethod booleanConversionMethod;

    public RValueInfo() {
        elementsContainingDisHit = new ArrayList<>();
        methodCallElements = new ArrayList<>();
    }

    public List<PsiElement> getElementsContainingDisHit() {
        return elementsContainingDisHit;
    }

    public RValueInfo addElementContainingDisHit(final PsiElement element) {
        if(element != null) {
            this.elementsContainingDisHit.add(element);
        }
        return this;
    }

    public List<PsiMethodCallExpression> getMethodCallElements() {
        return methodCallElements;
    }

    public RValueInfo addMethodCallElement(final PsiMethodCallExpression element) {
        if(element != null) {
            this.methodCallElements.add(element);
        }
        return this;
    }

    public RValueInfo addMethodCallElements(final Collection<? extends PsiMethodCallExpression> elements) {
        this.methodCallElements.addAll(elements);
        return this;
    }

    public RValueInfo withReturnOutcome(final ReturnOutcome newReturnOutcome) {
        this.returnOutcome = newReturnOutcome;
        this.exceptionThrown = null;
        return this;
    }

    public ReturnOutcome getReturnOutcome() {
        return returnOutcome;
    }


    public RValueInfo withUnknownReturnValue() {
        this.returnOutcome = ReturnOutcome.Unknown;
        this.exceptionThrown = null;
        return this;
    }

    public PsiElement getElementThatThrows() {
        return elementThatThrows;
    }

    public PsiType getExceptionThrown() {
        return exceptionThrown;
    }

    public RValueInfo withExceptionThrown(final PsiElement elementThatThrows, final PsiType exceptionToThrow) {
        this.elementThatThrows = elementThatThrows;
        this.exceptionThrown = exceptionToThrow;
        this.returnOutcome = null;
        return this;
    }

    public RValueInfo addElementsContainingDisHit(final Collection<? extends PsiElement> elementsContainingDisHit) {
        this.elementsContainingDisHit.addAll(elementsContainingDisHit);
        return this;
    }

    public BooleanConversionMethod getBooleanConversionMethod() {
        return booleanConversionMethod;
    }

    public RValueInfo withBooleanConversionMethod(final BooleanConversionMethod booleanConversionMethod) {
        this.booleanConversionMethod = booleanConversionMethod;
        return this;
    }
}
