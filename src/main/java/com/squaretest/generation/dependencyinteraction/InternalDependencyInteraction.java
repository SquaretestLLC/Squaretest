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

import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import static com.squaretest.generation.dependencyinteraction.SerializationUtils.serializeMethod;

public class InternalDependencyInteraction {
    @NotNull
    private final PsiField psiField;
    @NotNull
    private final PsiMethod psiMethod;
    @NotNull
    private final InternalMethodCallExpression methodCallExpression;
    private final boolean returnValueIgnored;
    private final List<Node> callstacks;
    private final DependencyInteractionKey diKey;
    private boolean canBeFirstCallstackNode;

    public InternalDependencyInteraction(
            @NotNull final PsiField psiField, @NotNull final PsiMethod psiMethod,
            @NotNull final InternalMethodCallExpression methodCallExpression,
            final boolean returnValueIgnored, final List<Node> callstacks) {
        this.psiField = psiField;
        this.psiMethod = psiMethod;
        this.methodCallExpression = methodCallExpression;
        this.returnValueIgnored = returnValueIgnored;
        this.callstacks = callstacks;
        this.diKey = new DependencyInteractionKey(this.psiField, this.psiMethod);
    }

    @NotNull
    public PsiField getPsiField() {
        return psiField;
    }

    @NotNull
    public PsiMethod getPsiMethod() {
        return psiMethod;
    }

    @NotNull
    public InternalMethodCallExpression getInternalMethodCallExpression() {
        return methodCallExpression;
    }

    public boolean isReturnValueIgnored() {
        return returnValueIgnored;
    }

    public List<Node> getCallstacks() {
        return callstacks;
    }

    public DependencyInteractionKey getDiKey() {
        return diKey;
    }

    public boolean canBeFirstCallstackNode() {
        return canBeFirstCallstackNode;
    }

    public void setCanBeFirstCallstackNode(final boolean canBeFirstCallstackNode) {
        this.canBeFirstCallstackNode = canBeFirstCallstackNode;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final InternalDependencyInteraction that = (InternalDependencyInteraction) o;
        return psiField == that.psiField && psiMethod == that.psiMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(System.identityHashCode(psiField), System.identityHashCode(psiMethod));
    }

    @Override
    public String toString() {
        return String.format("%s.%s\n%s", psiField.getName(), serializeMethod(psiMethod), callstackString());
    }

    private String callstackString() {
        final StringBuilder builder = new StringBuilder();
        for(int i = 0; i < callstacks.size(); i++) {
            final Node node = callstacks.get(i);
            builder.append(node.toString());
            if(i != callstacks.size() - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
