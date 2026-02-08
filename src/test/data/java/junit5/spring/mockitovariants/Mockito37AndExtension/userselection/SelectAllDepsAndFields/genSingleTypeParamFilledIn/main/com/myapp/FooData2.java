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

public class FooData2 {
    private String fooData2Id;
    private String fooData2Name;

    public String getFooData2Id() {
        return fooData2Id;
    }

    public void setFooData2Id(final String fooData2Id) {
        this.fooData2Id = fooData2Id;
    }

    public String getFooData2Name() {
        return fooData2Name;
    }

    public void setFooData2Name(final String fooData2Name) {
        this.fooData2Name = fooData2Name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FooData2 fooData2 = (FooData2) o;
        return Objects.equals(fooData2Id, fooData2.fooData2Id) && Objects.equals(fooData2Name, fooData2.fooData2Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fooData2Id, fooData2Name);
    }
}
