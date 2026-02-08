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

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class DiEntrySet implements Iterable<DiEntry> {

    private static final DiEntrySet Empty = new DiEntrySet(Collections.emptyMap());

    @NotNull
    private final Map<String, DiEntry> diKeyToDiEntryMap;

    public DiEntrySet() {
        this(new LinkedHashMap<>());
    }

    private DiEntrySet(@NotNull final Map<String, DiEntry> theMap) {
        this.diKeyToDiEntryMap = theMap;
    }

    public boolean add(final DiEntry diEntry) {
        if(diKeyToDiEntryMap.containsKey(diEntry.diKey())) {
            return false;
        }
        diKeyToDiEntryMap.put(diEntry.diKey(), diEntry);
        return true;
    }

    public DiEntry getFirstEntry(final Collection<String> diKeys) {
        for(final String diKey : diKeys) {
            final DiEntry diEntry = diKeyToDiEntryMap.get(diKey);
            if(diEntry != null) {
                return diEntry;
            }
        }
        return null;
    }

    public static DiEntrySet empty() {
        return Empty;
    }

    @NotNull
    @Override
    public Iterator<DiEntry> iterator() {
        return diKeyToDiEntryMap.values().iterator();
    }
}
