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

public class FooData1 {
    private String fooData1Id;
    private String fooData1Name;

    public String getFooData1Id() {
        return fooData1Id;
    }

    public void setFooData1Id(final String fooData1Id) {
        this.fooData1Id = fooData1Id;
    }

    public String getFooData1Name() {
        return fooData1Name;
    }

    public void setFooData1Name(final String fooData1Name) {
        this.fooData1Name = fooData1Name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FooData1 fooData1 = (FooData1) o;
        return Objects.equals(fooData1Id, fooData1.fooData1Id) && Objects.equals(fooData1Name, fooData1.fooData1Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fooData1Id, fooData1Name);
    }
}
