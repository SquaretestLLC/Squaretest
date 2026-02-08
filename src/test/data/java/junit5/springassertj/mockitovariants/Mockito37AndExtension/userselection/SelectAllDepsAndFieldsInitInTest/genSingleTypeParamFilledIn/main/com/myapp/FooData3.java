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

public class FooData3 {
    private String fooData3Id;
    private String fooData3Name;

    public String getFooData3Id() {
        return fooData3Id;
    }

    public void setFooData3Id(final String fooData3Id) {
        this.fooData3Id = fooData3Id;
    }

    public String getFooData3Name() {
        return fooData3Name;
    }

    public void setFooData3Name(final String fooData3Name) {
        this.fooData3Name = fooData3Name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FooData3 fooData3 = (FooData3) o;
        return Objects.equals(fooData3Id, fooData3.fooData3Id) && Objects.equals(fooData3Name, fooData3.fooData3Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fooData3Id, fooData3Name);
    }
}
