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

import com.squaretest.generation.dependencyinteraction.DiAndNode;
import com.squaretest.generation.dependencyinteraction.DiAndNodeSet;

import java.util.Iterator;
import java.util.function.Function;

public class StackFrame {
    private final DiAndNode diAndNode;
    private final Iterator<NodeStep> nodeSteps;
    private NodeStep currentNodeStep;
    private final Function<NodeStep, CacheValue> disHitAfterProvider;
    private CacheValue currentCacheValue;
    private DiAndNodeSet currentDisHitAfter;
    private Iterator<DiAndNode> currentDisHitAfterIterator;

    public StackFrame(
            final DiAndNode diAndNode,
            final Iterable<NodeStep> nodeSteps,
            final Function<NodeStep, CacheValue> disHitAfterProvider) {
        this.diAndNode = diAndNode;
        this.nodeSteps = nodeSteps.iterator();
        this.disHitAfterProvider = disHitAfterProvider;
        this.currentNodeStep = this.nodeSteps.next();
        this.currentCacheValue = disHitAfterProvider.apply(currentNodeStep);
        this.currentDisHitAfter = currentCacheValue.disHitAfter();
        this.currentDisHitAfterIterator = currentDisHitAfter.iterator();
    }

    public DiAndNodeSet getCurrentDisHitAfter() {
        return currentDisHitAfter;
    }

    public NodeStep getCurrentNodeStep() {
        return currentNodeStep;
    }

    public boolean hasNextDiHitAfter() {
        return currentDisHitAfterIterator.hasNext();
    }

    public DiAndNode getNextDiHitAfter() {
        return currentDisHitAfterIterator.next();
    }

    public boolean hasNextDecision() {
        return nodeSteps.hasNext();
    }

    public void advanceToNextDecision() {
        this.currentNodeStep = nodeSteps.next();
        this.currentCacheValue = disHitAfterProvider.apply(currentNodeStep);
        this.currentDisHitAfter = currentCacheValue.disHitAfter();
        this.currentDisHitAfterIterator = currentDisHitAfter.iterator();
    }

    public CacheValue getCurrentCacheValue() {
        return currentCacheValue;
    }

    public DiAndNode getDiAndNode() {
        return diAndNode;
    }
}
