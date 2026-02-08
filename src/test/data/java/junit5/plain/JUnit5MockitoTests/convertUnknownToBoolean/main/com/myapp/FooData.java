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

public class FooData {
    private String id;
    private SubData subData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SubData getSubData() {
        return subData;
    }

    public void setSubData(SubData subData) {
        this.subData = subData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FooData fooData = (FooData) o;
        return Objects.equals(id, fooData.id) && Objects.equals(subData, fooData.subData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subData);
    }
}
