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

import java.util.List;
import java.util.ListIterator;

public class MyClass {
    private final FooService fooService;
    private final BarService barService;
    private final AlphaService alphaService;
    private final MetricService metricService;

    public MyClass(
            final FooService fooService, final BarService barService,
            final AlphaService alphaService, final MetricService metricService) {
        this.fooService = fooService;
        this.barService = barService;
        this.alphaService = alphaService;
        this.metricService = metricService;
    }

    public void validateFooData1(final String fooId) {
        metricService.recordStartOfValidateMethod(fooId);
        for (final FooData fooData : fooService.getFoos1(fooId))
            for (final BarData barData : barService.getBars1(fooData.getBarId()))
                for (final AlphaData alphaData : alphaService.getAlphaData1(barData.getAlphaId()))
                    metricService.recordTuple(fooData.getId(), barData.getId(), alphaData.getId());
        metricService.recordEndOfValidateMethod(fooId);
    }

    public List<FooData> getFooData1(final String id) {
        final List<FooData> fooDatas = fooService.getFoos1(id);
        for (final FooData fooData : fooDatas)
            addBarData1(fooData);
        return fooDatas;
    }

    private void addBarData1(final FooData fooData) {
        final List<BarData> bars = barService.getBars1(fooData.getBarId());
        fooData.setBarData(bars);
        for (final BarData bar : bars)
            addAlphaData1(bar);
    }

    private void addAlphaData1(final BarData bar) {
        final List<AlphaData> alphas = alphaService.getAlphaData1(bar.getAlphaId());
        bar.setAlphaData(alphas);
    }

    public List<FooData> getFooData2(final String id) {
        final List<FooData> ret = fooService.getFoos1(id);
        final ListIterator<FooData> iterator = ret.listIterator();
        while(iterator.hasNext())
            addBarData1(iterator.next());
        return ret;
    }
    public List<FooData> getFooData3(final String id) {
        final List<FooData> ret = fooService.getFoos1(id);
        final ListIterator<FooData> iterator = ret.listIterator(ret.size());
        while(iterator.hasPrevious())
            addBarData1(iterator.previous());
        return ret;
    }

    public void validateFoo2(final String fooId) {
        metricService.recordStartOfValidateMethod(fooId);
        do
            for (final FooData fooData : fooService.getFoos1(fooId))
                for (final BarData barData : barService.getBars1(fooData.getBarId()))
                    for (final AlphaData alphaData : alphaService.getAlphaData1(barData.getAlphaId()))
                        metricService.recordTuple(fooData.getId(), barData.getId(), alphaData.getId());
        while(!fooService.getFoos2(fooId).isEmpty());
        metricService.recordEndOfValidateMethod(fooId);
    }
}