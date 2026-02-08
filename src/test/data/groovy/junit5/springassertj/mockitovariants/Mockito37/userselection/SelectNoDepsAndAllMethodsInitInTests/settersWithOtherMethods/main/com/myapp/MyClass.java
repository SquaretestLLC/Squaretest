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

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class MyClass {
    private AlphaData alphaData;
    private BetaData betaData;
    private GammaData gammaData;
    private LocalDate startDate;
    private String serializedValue;

    @Autowired
    public void setAlphaData(AlphaData value) {
        this.alphaData = value;
    }

    @Autowired
    public void setBetaData(BetaData value) {
        this.betaData = value;
    }

    public void setGammaData(GammaData value) {
        this.gammaData = value;
    }

    public void setStartDate(LocalDate value) {
        this.startDate = value;
    }

    public void setSerializedValue(String value) {
        this.serializedValue = value;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "alphaData=" + alphaData +
                ", betaData=" + betaData +
                ", gammaData=" + gammaData +
                ", startDate=" + startDate +
                ", serializedValue='" + serializedValue + '\'' +
                '}';
    }
}
