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

import java.time.LocalDateTime;
import java.util.Objects;

public class FooData {
    private String id;
    private String name;
    private LocalDateTime createDateTime;

    public FooData() {
    }

    public FooData(String id, String name, LocalDateTime createDateTime) {
        this.id = id;
        this.name = name;
        this.createDateTime = createDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FooData fooData = (FooData) o;
        return Objects.equals(id, fooData.id) && Objects.equals(name, fooData.name) && Objects.equals(createDateTime, fooData.createDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createDateTime);
    }
}
