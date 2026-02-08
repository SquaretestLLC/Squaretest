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
import org.apache.commons.collections4.multimap.AbstractSetValuedMap;

import java.util.IdentityHashMap;
import java.util.Set;

public class IdentityMultimapWithIdentitySets<K, V> extends AbstractSetValuedMap<K, V> {

    public IdentityMultimapWithIdentitySets() {
        super(new IdentityHashMap<>());
    }

    @Override
    protected Set<V> createCollection() {
        return SetUtils.newIdentityHashSet();
    }
}
