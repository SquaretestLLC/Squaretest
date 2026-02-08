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

import java.time.LocalDate;
import java.util.Objects;

public class DeltaData {
    private String id;
    private LocalDate createDate;
    private String description;
    private String epsilonId;
    private EpsilonData epsilonData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEpsilonId() {
        return epsilonId;
    }

    public void setEpsilonId(String epsilonId) {
        this.epsilonId = epsilonId;
    }

    public EpsilonData getEpsilonData() {
        return epsilonData;
    }

    public void setEpsilonData(EpsilonData epsilonData) {
        this.epsilonData = epsilonData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeltaData deltaData = (DeltaData) o;
        return Objects.equals(id, deltaData.id) && Objects.equals(createDate, deltaData.createDate) && Objects.equals(description, deltaData.description) && Objects.equals(epsilonId, deltaData.epsilonId) && Objects.equals(epsilonData, deltaData.epsilonData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, description, epsilonId, epsilonData);
    }
}
