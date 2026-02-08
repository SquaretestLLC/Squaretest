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
package com.squaretest.generation.defaulttypes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squaretest.di.SharedComponents;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages loading and storing the {@link Map Map&lt;String, DefaultTypeInfo&gt;} from resources so that it is only
 * loaded once.
 */
public class DefaultTypesHolder {

    private static DefaultTypesHolder instance;
    private final Map<String, DefaultTypeInfo> canonicalNameToDefaultTypeMap;

    private DefaultTypesHolder() {
        final ObjectMapper objectMapper = SharedComponents.getObjectMapper();
        try {
            final String defaultTypesFile = IOUtils.toString(getClass()
                    .getResourceAsStream("/defaulttypes.json"), StandardCharsets.UTF_8);
            final TypeReference<HashMap<String, DefaultTypeInfo>> typeRef
                    = new TypeReference<>() {
            };
            this.canonicalNameToDefaultTypeMap = objectMapper.readValue(defaultTypesFile, typeRef);
        } catch(final IOException ex) {
            throw new RuntimeException("Unable to find default types resource file", ex);
        }
    }

    public boolean contains(final String canonicalName) {
        if(canonicalName == null) {
            return false;
        }
        return this.canonicalNameToDefaultTypeMap.containsKey(canonicalName);
    }

    public DefaultTypeInfo get(final String canonicalName) {
        if(canonicalName == null) {
            return null;
        }
        return this.canonicalNameToDefaultTypeMap.get(canonicalName);
    }

    public static synchronized DefaultTypesHolder getInstance() {
        if(instance == null) {
            instance = new DefaultTypesHolder();
        }
        return instance;
    }

    /**
     * Visible for testing
     *
     * @return a new instance of the {@link #canonicalNameToDefaultTypeMap}.
     */
    synchronized Map<String, DefaultTypeInfo> getCanonicalNameToDefaultTypeMap() {
        return new DefaultTypesHolder().canonicalNameToDefaultTypeMap;
    }
}
