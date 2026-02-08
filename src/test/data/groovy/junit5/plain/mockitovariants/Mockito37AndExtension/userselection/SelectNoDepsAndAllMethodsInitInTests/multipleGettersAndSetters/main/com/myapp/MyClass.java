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

public class MyClass {
    private AlphaData alphaData;
    private BetaData betaData;
    private GammaData gammaData;
    private LocalDate startDate;
    private String serializedValue;

    public MyClass(AlphaData alphaData, BetaData betaData) {
        this.alphaData = alphaData;
        this.betaData = betaData;
    }

    public AlphaData getPrimaryValue() {
        return alphaData;
    }

    public void setPrimaryValue(final AlphaData value) {
        this.alphaData = value;
    }

    public AlphaData getAlphaData() {
        return alphaData;
    }

    public void setAlphaData(AlphaData value) {
        this.alphaData = value;
    }

    public String getTheAlphaString() {
        return alphaData.getId() + alphaData.getName();
    }

    public BetaData getSecondaryValue() {
        return betaData;
    }

    public BetaData getThirdValue() {
        return betaData;
    }

    public Object getFourthValue() {
        return betaData;
    }

    public BetaData getBetaData() {
        return betaData;
    }

    public void setBetaData(BetaData value) {
        this.betaData = value;
    }

    public String getTheBetaString() {
        return betaData.getId() + betaData.getName();
    }

    public GammaData getGammaData() {
        return gammaData;
    }

    public void setGammaData(GammaData value) {
        this.gammaData = value;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate value) {
        this.startDate = value;
    }

    public LocalDate getFirstDate() {
        return startDate;
    }

    public void setFirstDate(LocalDate value) {
        this.startDate = value;
    }

    public String getSerializedValue() {
        return serializedValue;
    }

    public void setSerializedValue(String value) {
        this.serializedValue = value;
    }

    public String getFirstSerializedValue() {
        return serializedValue;
    }

    public void setFirstSerializedValue(String value) {
        this.serializedValue = value;
    }
}
