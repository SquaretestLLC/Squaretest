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

public class FooData5 {
    private String fooData5Id;
    private String fooData5Name;

    public String getFooData5Id() {
        return fooData5Id;
    }

    public void setFooData5Id(final String fooData5Id) {
        this.fooData5Id = fooData5Id;
    }

    public String getFooData5Name() {
        return fooData5Name;
    }

    public void setFooData5Name(final String fooData5Name) {
        this.fooData5Name = fooData5Name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FooData5 fooData5 = (FooData5) o;
        return Objects.equals(fooData5Id, fooData5.fooData5Id) && Objects.equals(fooData5Name, fooData5.fooData5Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fooData5Id, fooData5Name);
    }
}
