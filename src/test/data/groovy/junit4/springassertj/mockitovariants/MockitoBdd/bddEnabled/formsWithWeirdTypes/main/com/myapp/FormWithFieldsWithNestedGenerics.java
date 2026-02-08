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
package com.myapp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FormWithFieldsWithNestedGenerics {
    private List<List<String>> names;
    private List<Map<String, List<String>>> maps;
    private Map<String, List<String>> primaryMap;
    private String[] stringArr;
    private String[][] stringMatrix;
    private String[][][] stringCube;
    private SimpleForm1 missingForm;

    private transient String hashCodeCache;
    private transient boolean isHashCodeCacheDirty;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormWithFieldsWithNestedGenerics that = (FormWithFieldsWithNestedGenerics) o;
        return Objects.equals(names, that.names) &&
                Objects.equals(maps, that.maps) &&
                Objects.equals(primaryMap, that.primaryMap) &&
                Arrays.equals(stringArr, that.stringArr) &&
                Arrays.equals(stringMatrix, that.stringMatrix) &&
                Arrays.equals(stringCube, that.stringCube);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(names, maps, primaryMap);
        result = 31 * result + Arrays.hashCode(stringArr);
        result = 31 * result + Arrays.hashCode(stringMatrix);
        result = 31 * result + Arrays.hashCode(stringCube);
        return result;
    }
}
