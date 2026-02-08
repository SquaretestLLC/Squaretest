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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyClass {
    private final FooService fooService;
    private final MetricService metricService;

    public MyClass(final FooService fooService, final MetricService metricService) {
        this.fooService = fooService;
        this.metricService = metricService;
    }

    public List<Integer> getFoos1(final String id) {
        final List<Integer> ret = new ArrayList<>();
        for(int i = fooService.getStartingValue1(id); i < fooService.getEndingValue1(id); i = fooService.getNextValue1(id)) {
            metricService.recordAddingValue(i);
            ret.add(i);
        }
        metricService.recordFinishedAddingValues(id);
        return ret;
    }

    public List<Integer> getFoos2(final String id) {
        final List<Integer> ret = new ArrayList<>();
        for(int i = fooService.getStartingValue2(id).orElse(0); i < fooService.getEndingValue2(id).orElse(1); i = fooService.getNextValue2(id).orElse(2)) {
            metricService.recordAddingValue(i);
            ret.add(i);
        }
        metricService.recordFinishedAddingValues(id);
        return ret;
    }

    public List<Integer> getFoos3(final String id) {
        final List<Integer> ret = new ArrayList<>();
        for(int i = fooService.getStartingValue2(id).orElseThrow(); i < fooService.getEndingValue2(id).orElseThrow(); i = fooService.getNextValue2(id).orElseThrow()) {
            metricService.recordAddingValue(i);
            ret.add(i);
        }
        metricService.recordFinishedAddingValues(id);
        return ret;
    }

    public List<Integer> getFoos4(final String id) {
        final List<Integer> ret = new ArrayList<>();
        for(Optional<Integer> i = fooService.getStartingValue2(id); fooService.getEndingValue2(id).isPresent(); i = fooService.getNextValue2(id)) {
            if(i.isEmpty()) {
                throw new RuntimeException("Received empty value");
            }
            metricService.recordAddingValue(i.get());
            ret.add(i.get());
        }
        metricService.recordFinishedAddingValues(id);
        return ret;
    }

    public List<Integer> getFoos5(final String id) throws FooServiceException {
        final List<Integer> ret = new ArrayList<>();
        for(int i = fooService.getStartingValue3(id); i < fooService.getEndingValue3(id); i = fooService.getNextValue3(id)) {
            metricService.recordAddingValue(i);
            ret.add(i);
        }
        metricService.recordFinishedAddingValues(id);
        return ret;
    }

    public List<Integer> getFoos6(final String id) throws FooServiceException {
        final List<Integer> ret = new ArrayList<>();
        for(Optional<Integer> i = fooService.getStartingValue4(id); fooService.getEndingValue4(id).isPresent(); i = fooService.getNextValue4(id)) {
            if(i.isEmpty()) {
                throw new RuntimeException("Received empty value");
            }
            metricService.recordAddingValue(i.get());
            ret.add(i.get());
        }
        metricService.recordFinishedAddingValues(id);
        return ret;
    }

    public List<Integer> getFoos7(final String id) {
        final List<Integer> ret = new ArrayList<>();
        for(int i = fooService.getStartingValue1(id); i < fooService.getStartingValue1(id); i = fooService.getNextValue1(id)) {
            metricService.recordAddingValue(i);
            ret.add(i);
        }
        metricService.recordFinishedAddingValues(id);
        return ret;
    }

    public List<Integer> getFoos8(final String id) {
        final List<Integer> ret = new ArrayList<>();
        for(int i = fooService.getStartingValue1(id); i < fooService.getStartingValue1(id); i = fooService.getStartingValue1(id)) {
            metricService.recordAddingValue(i);
            ret.add(i);
        }
        metricService.recordFinishedAddingValues(id);
        return ret;
    }
    public List<Integer> getFoos9(final String id) {
        final List<Integer> ret = new ArrayList<>();
        for(int i = fooService.getStartingValue1(id); i < fooService.getStartingValue1(id); i = fooService.getStartingValue1(id))
            ret.add(i);

        metricService.recordFinishedAddingValues(id);
        return ret;
    }

    public List<Integer> getFoos10(final String id) {
        final List<Integer> ret = new ArrayList<>();
        for(int i = fooService.getStartingValue2(id).orElseGet(() -> fooService.getStartingValue5(id)); i < fooService.getEndingValue2(id).orElse(1); i = fooService.getNextValue2(id).orElse(2)) {
            metricService.recordAddingValue(i);
            ret.add(i);
        }
        metricService.recordFinishedAddingValues(id);
        return ret;
    }
}
