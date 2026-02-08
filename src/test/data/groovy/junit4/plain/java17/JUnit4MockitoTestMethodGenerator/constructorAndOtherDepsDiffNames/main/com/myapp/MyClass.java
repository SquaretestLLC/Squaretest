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
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;
import java.util.logging.Logger;

public class MyClass {
    // Private static final field should be ignored.
    private static final Logger Log = Logger.getLogger(MyClass.class.getName());
    // Private static field with "setter" should be ignored.
    private static EpsilonService epsilonService;
    // Package static field should be ignored.
    static ZetaService zetaService;
    // Satisfied by constructor. The setter should not be used.
    private FooService fooService;
    // Satisfied by constructor. It should not be set via package-local field set statements.
    BarService barService;
    // Not set in constructor. This should be set by ReflectionTestUtils.
    @Autowired
    private GammaService gammaService;

    // Not set in constructor. This should be set by package local field access.
    @Autowired
    BetaService betaService;
    // Set in constructor.
    private String defaultBarId;
    // Not mocked by default, but can be selected.
    // If selected, this should be set with ReflectionTestUtils.
    private AlphaService alphaService;
    // Has Autowired setter that should be invoked.
    private DeltaService deltaService;
    // Value field that won't be mocked by default (it is a Map), but can be selected to be mocked.
    @Value("specialMap")
    private Map<String, String> specialMap;

    public MyClass(FooService fooService, BarService barService, GammaService gammaService, String defaultBarId) {
        this.fooService = fooService;
        this.barService = barService;
        this.gammaService = gammaService;
        this.defaultBarId = defaultBarId;
    }

    public FooAndBar getFooAndBar1(String id) {
        Log.info("getFooAndBar1");
        final FooData foo = fooService.getFoo1(id);
        final BarData barData = barService.getDefaultBar1(defaultBarId);
        return new FooAndBar(foo, barData);
    }

    public AlphaData getAlpha1(String id) {
        Log.info("getAlpha1");
        return alphaService.getAlpha1(id);
    }

    public BetaData getBeta1(String id) {
        Log.info("getBeta1");
        return betaService.getBeta1(id);
    }

    public GammaData getGamma1(String id) {
        Log.info("getGamma1");
        return gammaService.getGamma1(id);
    }

    public GammaData getGamma2() {
        Log.info("getGamma1");
        String gammaId = specialMap.get("GammaId");
        if(gammaId == null) {
            return null;
        }
        return gammaService.getGamma1(gammaId);
    }

    public static EpsilonData getEpsilon1(String id) {
        return epsilonService.getEpsilon1(id);
    }

    public static ZetaData getZetaData1(String id) {
        return zetaService.getZetaData1(id);
    }

    @Autowired
    public void setDeltaService(DeltaService deltaService) {
        Log.info("setDeltaService called");
        this.deltaService = deltaService;
    }

    public void setFooService(FooService fooService) {
        Log.info("setFooService called");
        this.fooService = fooService;
    }

    public static void setEpsilonService(EpsilonService epsilonService) {
        MyClass.epsilonService = epsilonService;
    }
}
