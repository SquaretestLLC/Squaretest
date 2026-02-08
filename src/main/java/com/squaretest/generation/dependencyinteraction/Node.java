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

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static com.squaretest.generation.dependencyinteraction.SerializationUtils.serializeMethod;

public class Node {
    private final PsiMethod sourceMethodContainingElement;
    private final PsiElement element;
    private final PsiElement endElement;
    private final int sequenceId;
    @Nullable
    private final Node next;
    private final int hashCode;
    private Boolean passesThroughCatchBlock;
    private Boolean passesThroughFinallyBlock;
    @Nullable
    private TextRange elementTextRange;

    public Node(
            final PsiMethod sourceMethodContainingElement, final PsiElement element,
            final PsiElement endElement, final int sequenceId, @Nullable final Node next) {
        this.sourceMethodContainingElement = sourceMethodContainingElement;
        this.element = element;
        this.endElement = endElement;
        this.sequenceId = sequenceId;
        this.next = next;
        this.hashCode = Objects.hash(System.identityHashCode(this.element), System.identityHashCode(this.endElement), this.next != null ? this.next.hashCode : 0);
        this.passesThroughCatchBlock = null;
    }

    public PsiMethod getSourceMethodContainingElement() {
        return sourceMethodContainingElement;
    }

    public PsiElement getElement() {
        return element;
    }

    public PsiElement getEndElement() {
        return endElement;
    }

    public int getSequenceId() {
        return sequenceId;
    }

    @Nullable
    public Node getNext() {
        return next;
    }

    public Boolean getPassesThroughCatchBlock() {
        return passesThroughCatchBlock;
    }

    public void setPassesThroughCatchBlock(final Boolean passesThroughCatchBlock) {
        this.passesThroughCatchBlock = passesThroughCatchBlock;
    }

    public Boolean getPassesThroughFinallyBlock() {
        return passesThroughFinallyBlock;
    }

    public void setPassesThroughFinallyBlock(final Boolean passesThroughFinallyBlock) {
        this.passesThroughFinallyBlock = passesThroughFinallyBlock;
    }

    public int getStartOffset() {
        return getElementTextRange().getStartOffset();
    }

    @NotNull
    public TextRange getElementTextRange() {
        if(elementTextRange == null) {
            elementTextRange = element.getTextRange();
        }
        return elementTextRange;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final Node node = (Node) o;
        return hashCode == node.hashCode && Objects.equals(element, node.element) && Objects.equals(endElement, node.endElement) && Objects.equals(next, node.next);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("  callstack:\n");
        builder.append("   ");
        builder.append(element.getText().trim()).append(" in ").append(serializeMethod(sourceMethodContainingElement));
        Node currentNode = this.next;
        int indentLevel = 4;
        while(currentNode != null) {
            builder.append("\n");
            builder.append(StringUtils.repeat(" ", indentLevel));
            builder.append(currentNode.getElement().getText().trim()).append(" in ").append(serializeMethod(currentNode.sourceMethodContainingElement));
            indentLevel++;
            currentNode = currentNode.next;
        }
        return builder.toString();
    }
}
