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
    private final long id;
    private final String name;
    private final String shortDisplayName;
    private final LocalDateTime startDate;

    public FooData(long id, String name, String shortDisplayName, LocalDateTime startDate) {
        this.id = id;
        this.name = name;
        this.shortDisplayName = shortDisplayName;
        this.startDate = startDate;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortDisplayName() {
        return shortDisplayName;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FooData fooData = (FooData) o;
        return id == fooData.id &&
                Objects.equals(name, fooData.name) &&
                Objects.equals(shortDisplayName, fooData.shortDisplayName) &&
                Objects.equals(startDate, fooData.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, shortDisplayName, startDate);
    }
}
