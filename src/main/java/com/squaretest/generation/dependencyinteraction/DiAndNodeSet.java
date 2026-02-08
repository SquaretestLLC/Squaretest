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

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class DiAndNodeSet implements Iterable<DiAndNode> {

    private static final DiAndNodeSet EMPTY = new DiAndNodeSet(Collections.emptyMap());

    public static DiAndNodeSet emptySet() {
        return EMPTY;
    }

    private final Map<DiAndNode, DiAndNode> dependencyInteractions;

    public DiAndNodeSet() {
        this(new LinkedHashMap<>());
    }

    private DiAndNodeSet(final Map<DiAndNode, DiAndNode> dependencyInteractions) {
        this.dependencyInteractions = dependencyInteractions;
    }

    public void addAll(final Iterable<DiAndNode> diAndNodes) {
        for(final DiAndNode node : diAndNodes) {
            add(node);
        }
    }

    public void add(final DiAndNode diAndNode) {
        this.dependencyInteractions.put(diAndNode, diAndNode);
    }

    public DiAndNode get(final DiAndNode diAndNode) {
        return dependencyInteractions.get(diAndNode);
    }

    public boolean contains(final DiAndNode diAndNode) {
        return dependencyInteractions.get(diAndNode) != null;
    }

    public boolean contains(final InternalDependencyInteraction di) {
        for(final DiAndNode diAndNode : dependencyInteractions.keySet()) {
            if(diAndNode.internalDependencyInteraction().equals(di)) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    @Override
    public Iterator<DiAndNode> iterator() {
        return dependencyInteractions.values().iterator();
    }

    public DiAndNodeSet itemsBefore(final Node currentNode) {
        final DiAndNodeSet ret = new DiAndNodeSet();
        for(final DiAndNode diAndNode : this) {
            if(diAndNode.node().getSequenceId() >= currentNode.getSequenceId()) {
                return ret;
            }
            ret.add(diAndNode);
        }
        return ret;
    }

    public DiAndNodeSet itemsAfter(final Node currentNode) {
        final DiAndNodeSet ret = new DiAndNodeSet();
        for(final DiAndNode diAndNode : this) {
            if(diAndNode.node().getSequenceId() > currentNode.getSequenceId()) {
                ret.add(diAndNode);
            }
        }
        return ret;
    }

    public DiAndNodeSet itemsAfter(final DiAndNode currentNode) {
        return itemsAfter(currentNode.node());
    }

    public boolean containsAll(final DiAndNodeSet otherSet) {
        for(final DiAndNode other : otherSet) {
            if(!contains(other)) {
                return false;
            }
        }
        return true;
    }

    public void removeAll(final Iterable<DiAndNode> disToRemove) {
        for(final DiAndNode diAndNode : disToRemove) {
            remove(diAndNode);
        }
    }

    private void remove(final DiAndNode diAndNode) {
        dependencyInteractions.remove(diAndNode);
    }

    public boolean containsAny(final DiAndNodeSet otherSet) {
        for(final DiAndNode diAndNode : otherSet) {
            if(contains(diAndNode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final DiAndNodeSet that = (DiAndNodeSet) o;
        return Objects.equals(dependencyInteractions, that.dependencyInteractions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dependencyInteractions);
    }
}
