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
package com.squaretest.utils;

import org.apache.commons.collections4.SetUtils;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Set;

/**
 * Provides a mechanism for building an IdentitySet&lt;T&gt;, but only allocates memory when a second item is added
 * to the set. This provides a more efficient way to build large numbers of sets that contain zero or
 * one items. This is similar to {@link com.intellij.util.containers.SmartHashSet SmartHashSet} but uses an
 * IdentitySet instead of a {@link java.util.HashSet HashSet}.
 *
 * @param <T> the type of the set.
 */
public class LazyIdentitySetBuilder<T> {
    @Nullable
    private Set<T> theSet;
    @Nullable
    private T onlyItem;

    public boolean add(final T theItem) {
        if(theSet != null) {
            // We've already added the second item. Delegate further operations to the set.
            return theSet.add(theItem);
        }
        if(onlyItem == null) {
            // This is the first item. Store it in the member field.
            onlyItem = theItem;
            return true;
        }
        // This is the second item. Construct the set and use that from now on.
        theSet = SetUtils.newIdentityHashSet();
        theSet.add(onlyItem);
        onlyItem = null;
        return theSet.add(theItem);
    }

    public Set<T> toSet() {
        if(onlyItem != null) {
            return new SingletonIdentitySet<>(onlyItem);
        }
        if(theSet == null) {
            return Collections.emptySet();
        }
        return theSet;
    }
}
