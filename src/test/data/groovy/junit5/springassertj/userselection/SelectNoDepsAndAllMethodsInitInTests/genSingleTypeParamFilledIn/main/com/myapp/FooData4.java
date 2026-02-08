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

public class FooData4 {
    private String fooData4Id;
    private String fooData4Name;

    public String getFooData4Id() {
        return fooData4Id;
    }

    public void setFooData4Id(final String fooData4Id) {
        this.fooData4Id = fooData4Id;
    }

    public String getFooData4Name() {
        return fooData4Name;
    }

    public void setFooData4Name(final String fooData4Name) {
        this.fooData4Name = fooData4Name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FooData4 fooData4 = (FooData4) o;
        return Objects.equals(fooData4Id, fooData4.fooData4Id) && Objects.equals(fooData4Name, fooData4.fooData4Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fooData4Id, fooData4Name);
    }
}
