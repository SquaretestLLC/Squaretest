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

public class MyClass {
    private final FooService fooService;
    private final MetricService metricService;

    public MyClass(FooService fooService, MetricService metricService) {
        this.fooService = fooService;
        this.metricService = metricService;
    }

    public List<String> getFoos1(String id) {
        List<String> foos = fooService.getFoos1(id);
        if(foos.size() == 0) {
            metricService.recordNoFoos(id);
        }
        return foos;
    }

    public List<String> getFoos2(String id) {
        List<String> foos = fooService.getFoos2(id);
        if(foos != null && foos.size() == 0) {
            metricService.recordNoFoos(id);
        }
        return foos;
    }

    public List<String> getFoos3(String id) {
        List<String> foos = fooService.getFoos3(id);
        if(foos.size() != 0) {
            metricService.recordFoosFound(id);
        }
        return foos;
    }

    public List<String> getFoos4(String id) {
        List<String> foos = fooService.getFoos4(id);
        if(foos != null && foos.size() != 0) {
            metricService.recordFoosFound(id);
        }
        return foos;
    }

    public List<String> getFoos5(String id) {
        List<String> foos = fooService.getFoos2(id);
        if(foos == null || foos.size() == 0) {
            metricService.recordNoFoos(id);
        }
        return foos;
    }

    public List<String> getFoos6(String id) {
        List<String> foos = fooService.getFoos5(id);
        if(customSizeOf(foos) == 0) {
            metricService.recordNoFoos(id);
        }
        return foos;
    }

    public List<String> getFoos7(String id) {
        List<String> foos = fooService.getFoos5(id);
        if(customSizeOf(foos) != 0) {
            metricService.recordNoFoos(id);
        }
        return foos;
    }

    private int customSizeOf(List<String> foos) {
        return foos.size();
    }
}
