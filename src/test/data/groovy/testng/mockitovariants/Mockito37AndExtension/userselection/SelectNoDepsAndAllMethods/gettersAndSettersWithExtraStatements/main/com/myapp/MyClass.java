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
import java.util.logging.Logger;

public class MyClass {

    private static final Logger Log = Logger.getLogger(MyClass.class.getName());

    private AlphaData alphaData;
    private BetaData betaData;
    private GammaData gammaData;
    private LocalDate startDate;
    private String serializedValue;

    public MyClass(AlphaData alphaData, BetaData betaData) {
        this.alphaData = alphaData;
        this.betaData = betaData;
    }

    public AlphaData getAlphaData() {
        Log.info("getAlphaCalled");
        System.out.println("getAlphaCalled");
        System.err.println("getAlphaCalled");
        return alphaData;
    }

    public void setAlphaData(AlphaData value) {
        Log.info("getAlphaCalled");
        System.out.println("getAlphaCalled");
        System.err.println("getAlphaCalled");
        Objects.requireNonNull(value);
        this.alphaData = Objects.requireNonNull(value);
    }

    public String getTheAlphaString() {
        return alphaData.getId() + alphaData.getName();
    }

    public BetaData getBetaData() {
        return betaData;
    }

    public void setBetaData(BetaData value) {
        Log.info("getAlphaCalled");
        System.out.println("getAlphaCalled");
        System.err.println("getAlphaCalled");
        Objects.requireNonNull(value);
        this.betaData = Objects.requireNonNull(value);
    }

    public String getTheBetaString() {
        return betaData.getId() + betaData.getName();
    }

    public GammaData getGammaData() {
        return gammaData;
    }

    public void setGammaData(GammaData value) {
        Log.info("getAlphaCalled");
        System.out.println("getAlphaCalled");
        System.err.println("getAlphaCalled");
        Objects.requireNonNull(value);
        this.gammaData = Objects.requireNonNull(value);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate value) {
        Log.info("getAlphaCalled");
        System.out.println("getAlphaCalled");
        System.err.println("getAlphaCalled");
        Objects.requireNonNull(value);
        this.startDate = Objects.requireNonNull(value);
    }

    public String getSerializedValue() {
        return serializedValue;
    }

    public void setSerializedValue(String value) {
        Log.info("getAlphaCalled");
        System.out.println("getAlphaCalled");
        System.err.println("getAlphaCalled");
        Objects.requireNonNull(value);
        this.serializedValue = Objects.requireNonNull(value);
    }
}
