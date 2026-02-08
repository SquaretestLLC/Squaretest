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

public class FullData {
    private AlphaData alphaData;
    private BetaData betaData;
    private GammaData gammaData;
    private DeltaData deltaData;
    private EpsilonData epsilonData;

    public AlphaData getAlphaData() {
        return alphaData;
    }

    public void setAlphaData(final AlphaData alphaData) {
        this.alphaData = alphaData;
    }

    public BetaData getBetaData() {
        return betaData;
    }

    public void setBetaData(final BetaData betaData) {
        this.betaData = betaData;
    }

    public GammaData getGammaData() {
        return gammaData;
    }

    public void setGammaData(final GammaData gammaData) {
        this.gammaData = gammaData;
    }

    public DeltaData getDeltaData() {
        return deltaData;
    }

    public void setDeltaData(final DeltaData deltaData) {
        this.deltaData = deltaData;
    }

    public EpsilonData getEpsilonData() {
        return epsilonData;
    }

    public void setEpsilonData(final EpsilonData epsilonData) {
        this.epsilonData = epsilonData;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FullData fullData = (FullData) o;
        return Objects.equals(alphaData, fullData.alphaData) && Objects.equals(betaData, fullData.betaData) && Objects.equals(gammaData, fullData.gammaData) && Objects.equals(deltaData, fullData.deltaData) && Objects.equals(epsilonData, fullData.epsilonData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alphaData, betaData, gammaData, deltaData, epsilonData);
    }
}
