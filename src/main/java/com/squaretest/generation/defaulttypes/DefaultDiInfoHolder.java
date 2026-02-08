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
import com.squaretest.generation.NullabilityStatus;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultDiInfoHolder {
    private static DefaultDiInfoHolder instance;
    private final Map<String, DefaultDiInfo> canonicalKeyToDefaultDiInfoMap;

    private DefaultDiInfoHolder() {
        final ObjectMapper objectMapper = SharedComponents.getObjectMapper();
        try {
            final String defaultDiFile = IOUtils.toString(getClass().getResourceAsStream("/defaultdiinfo.json"), StandardCharsets.UTF_8);
            final TypeReference<HashMap<String, DefaultDiInfo>> typeRef = new TypeReference<>() {
            };
            this.canonicalKeyToDefaultDiInfoMap = objectMapper.readValue(defaultDiFile, typeRef);
        } catch(final IOException ex) {
            throw new RuntimeException("Unable to find default di info resource file", ex);
        }
    }

    public NullabilityStatus getNullabilityStatus(final String canonicalKey) {
        if(canonicalKey == null) {
            return null;
        }
        final DefaultDiInfo info = this.canonicalKeyToDefaultDiInfoMap.get(canonicalKey);
        if(info != null) {
            return info.nullabilityStatus();
        }
        return null;
    }

    public List<String> getUndeclaredExceptions(final String canonicalKey) {
        if(canonicalKey == null) {
            return Collections.emptyList();
        }
        final DefaultDiInfo info = this.canonicalKeyToDefaultDiInfoMap.get(canonicalKey);
        if(info != null) {
            return info.undeclaredExceptions();
        }
        return Collections.emptyList();
    }

    public static synchronized DefaultDiInfoHolder getInstance() {
        if(instance == null) {
            instance = new DefaultDiInfoHolder();
        }
        return instance;
    }
}
