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

public class AlphaData {
    private String id;
    private LocalDate createDate;
    private String betaId;
    private BetaData betaData;

    private DeltaData deltaData;

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

    public String getBetaId() {
        return betaId;
    }

    public void setBetaId(String betaId) {
        this.betaId = betaId;
    }

    public BetaData getBetaData() {
        return betaData;
    }

    public void setBetaData(BetaData betaData) {
        this.betaData = betaData;
    }

    public DeltaData getDeltaData() {
        return deltaData;
    }

    public void setDeltaData(final DeltaData deltaData) {
        this.deltaData = deltaData;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AlphaData alphaData = (AlphaData) o;
        return Objects.equals(id, alphaData.id) && Objects.equals(createDate, alphaData.createDate) && Objects.equals(betaId, alphaData.betaId) && Objects.equals(betaData, alphaData.betaData) && Objects.equals(deltaData, alphaData.deltaData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, betaId, betaData, deltaData);
    }
}
