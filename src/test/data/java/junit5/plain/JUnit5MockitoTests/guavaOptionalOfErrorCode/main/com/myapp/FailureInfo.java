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

import java.util.List;
import java.util.Objects;

public class FailureInfo {
    private final List<ErrorInfo> errorInfos;

    public FailureInfo(final List<ErrorInfo> errorInfos) {
        this.errorInfos = errorInfos;
    }

    public List<ErrorInfo> getErrorInfos() {
        return errorInfos;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FailureInfo that = (FailureInfo) o;
        return Objects.equals(errorInfos, that.errorInfos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorInfos);
    }
}
