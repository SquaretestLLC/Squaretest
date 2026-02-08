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
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PathKey {
    @NotNull
    private final DiAndNode sourceNode;
    @NotNull
    private final DiAndNode targetNode;

    public PathKey(@NotNull final DiAndNode sourceNode, @NotNull final DiAndNode targetNode) {
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final PathKey pathKey = (PathKey) o;
        return sourceNode.equals(pathKey.sourceNode) && targetNode.equals(pathKey.targetNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceNode, targetNode);
    }
}
