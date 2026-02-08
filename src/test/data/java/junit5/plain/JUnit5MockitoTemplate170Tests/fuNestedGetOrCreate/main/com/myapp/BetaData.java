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

public class BetaData {
    private String id;
    private LocalDate createDate;
    private String description;
    private String gammaId;
    private GammaData gammaData;

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

    public String getGammaId() {
        return gammaId;
    }

    public void setGammaId(String gammaId) {
        this.gammaId = gammaId;
    }

    public GammaData getGammaData() {
        return gammaData;
    }

    public void setGammaData(GammaData gammaData) {
        this.gammaData = gammaData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetaData betaData = (BetaData) o;
        return Objects.equals(id, betaData.id) && Objects.equals(createDate, betaData.createDate) && Objects.equals(description, betaData.description) && Objects.equals(gammaId, betaData.gammaId) && Objects.equals(gammaData, betaData.gammaData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, description, gammaId, gammaData);
    }
}
