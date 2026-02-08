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

public class SubFoo {
    private String subFooId;
    private String subFooName;

    public String getSubFooId() {
        return subFooId;
    }

    public void setSubFooId(final String subFooId) {
        this.subFooId = subFooId;
    }

    public String getSubFooName() {
        return subFooName;
    }

    public void setSubFooName(final String subFooName) {
        this.subFooName = subFooName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SubFoo subFoo = (SubFoo) o;
        return Objects.equals(subFooId, subFoo.subFooId) && Objects.equals(subFooName, subFoo.subFooName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subFooId, subFooName);
    }
}
