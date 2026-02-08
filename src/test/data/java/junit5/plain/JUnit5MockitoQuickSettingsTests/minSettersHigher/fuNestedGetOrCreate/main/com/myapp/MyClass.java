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

public class MyClass {
    private final AlphaService alphaService;
    private final BetaService betaService;
    private final GammaService gammaService;
    private final DeltaService deltaService;
    private final EpsilonService epsilonService;

    public MyClass(final AlphaService alphaService, final BetaService betaService, final GammaService gammaService, final DeltaService deltaService, final EpsilonService epsilonService) {
        this.alphaService = alphaService;
        this.betaService = betaService;
        this.gammaService = gammaService;
        this.deltaService = deltaService;
        this.epsilonService = epsilonService;
    }

    public AlphaData getOrCreateAlphaData1(final FullData fullData) {
        return alphaService.findById(fullData.getAlphaData().getId()).orElseGet(() -> createAlphaData(fullData));
    }

    private AlphaData createAlphaData(final FullData fullData) {
        final AlphaData alphaData = fullData.getAlphaData();
        alphaData.setBetaData(getOrCreateBetaData(fullData.getBetaData()));
        alphaData.setDeltaData(getOrCreateDeltaData(fullData.getDeltaData()));
        return alphaService.save(alphaData);
    }

    private DeltaData getOrCreateDeltaData(final DeltaData deltaData) {
        return deltaService.findById(deltaData.getId()).orElseGet(() -> createDeltaData(deltaData));
    }

    private DeltaData createDeltaData(final DeltaData deltaData) {
        deltaData.setEpsilonData(getOrCreateEpsilonData(deltaData.getEpsilonData()));
        return deltaService.save(deltaData);
    }

    private EpsilonData getOrCreateEpsilonData(final EpsilonData epsilonData) {
        return epsilonService.findById(epsilonData.getId()).orElseGet(() -> createEpsilonData(epsilonData));
    }

    private EpsilonData createEpsilonData(final EpsilonData epsilonData) {
        return epsilonService.save(epsilonData);
    }

    private BetaData getOrCreateBetaData(final BetaData betaData) {
        return betaService.findById(betaData.getId()).orElseGet(() -> createBetaData(betaData));
    }

    private BetaData createBetaData(final BetaData betaData) {
        betaData.setGammaData(getOrCreateGammaData(betaData.getGammaData()));
        return betaService.save(betaData);
    }

    private GammaData getOrCreateGammaData(final GammaData gammaData) {
        return gammaService.findById(gammaData.getId()).orElseGet(() -> createGammaData(gammaData));
    }

    private GammaData createGammaData(final GammaData gammaData) {
        return gammaService.save(gammaData);
    }

}
