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

public class FooData1 {
    private String id;
    private String name;
    private List<SubFoo> subFoos;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<SubFoo> getSubFoos() {
        return subFoos;
    }

    public void setSubFoos(final List<SubFoo> subFoos) {
        this.subFoos = subFoos;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FooData1 fooData = (FooData1) o;
        return Objects.equals(id, fooData.id) && Objects.equals(name, fooData.name) && Objects.equals(subFoos, fooData.subFoos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, subFoos);
    }
}
