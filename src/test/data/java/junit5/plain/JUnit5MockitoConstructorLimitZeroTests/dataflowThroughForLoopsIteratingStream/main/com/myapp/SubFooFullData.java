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

import java.util.Objects;

public class SubFooFullData {
    private final String subFooFullDataId;
    private final String subFooFullDataValue;
    private final String subFooFullDataOtherValue;
    private final String subFooFullDataDifferentValue;

    public SubFooFullData(final String subFooFullDataId, final String subFooFullDataValue, final String subFooFullDataOtherValue, final String subFooFullDataDifferentValue) {
        this.subFooFullDataId = subFooFullDataId;
        this.subFooFullDataValue = subFooFullDataValue;
        this.subFooFullDataOtherValue = subFooFullDataOtherValue;
        this.subFooFullDataDifferentValue = subFooFullDataDifferentValue;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SubFooFullData that = (SubFooFullData) o;
        return Objects.equals(subFooFullDataId, that.subFooFullDataId) && Objects.equals(
                subFooFullDataValue, that.subFooFullDataValue) && Objects.equals(subFooFullDataOtherValue,
                that.subFooFullDataOtherValue) && Objects.equals(subFooFullDataDifferentValue,
                that.subFooFullDataDifferentValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subFooFullDataId, subFooFullDataValue, subFooFullDataOtherValue,
                subFooFullDataDifferentValue);
    }
}
