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

import java.util.List;

public record DiAndNode(@NotNull InternalDependencyInteraction internalDependencyInteraction, @NotNull Node node) {

    @Override
    public InternalDependencyInteraction internalDependencyInteraction() {
        return internalDependencyInteraction;
    }

    @Override
    public Node node() {
        return node;
    }

    public boolean isFirstCallstackNodeForDi() {
        final List<Node> callstacks = internalDependencyInteraction.getCallstacks();
        if(callstacks.isEmpty()) {
            return false;
        }
        return callstacks.get(0) == node;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final DiAndNode diAndNode = (DiAndNode) o;
        return internalDependencyInteraction.equals(diAndNode.internalDependencyInteraction) && node.equals(diAndNode.node);
    }
}
