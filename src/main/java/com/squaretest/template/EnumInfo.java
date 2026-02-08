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
package com.squaretest.template;

import com.squaretest.template.api.Api;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Objects;

/**
 * Stores info about this class's status as an enumeration; e.g. whether or not it is an enum and, if it is, the
 * values.
 */
public class EnumInfo {
    private final boolean isEnum;
    @Nullable
    private final Api.FluentList<String> enumValues;

    public EnumInfo(final boolean isEnum, @Nullable final Api.FluentList<String> enumValues) {
        this.isEnum = isEnum;
        this.enumValues = enumValues;
    }

    public boolean isEnum() {
        return isEnum;
    }

    @Nullable
    public String firstEnumValue() {
        if(enumValues == null) {
            return null;
        }
        if(enumValues.isEmpty()) {
            return null;
        }
        return enumValues.get(0);
    }

    /**
     * @return the enum-values or {@link Collections#emptyList()} if {@link #isEnum()} returns false.
     */
    @NotNull
    public Api.FluentList<String> getEnumValues() {
        return enumValues == null ? Api.FluentListFactory.emptyList() : enumValues;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final EnumInfo enumInfo = (EnumInfo) o;
        return isEnum == enumInfo.isEnum &&
                Objects.equals(enumValues, enumInfo.enumValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isEnum, enumValues);
    }
}
