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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MyClass {
    private final FooService fooService;
    private final FooService altFooService;
    private final OtherDataService otherDataService;
    private final MetricService metricService;

    public MyClass(FooService fooService, FooService altFooService, OtherDataService otherDataService, MetricService metricService) {
        this.fooService = fooService;
        this.altFooService = altFooService;
        this.otherDataService = otherDataService;
        this.metricService = metricService;
    }

    public List<FooData> getFoosInCat1(final Category category) {
        final List<FooData> foos = fooService.getFoosInCategory1(category);
        for(final FooData foo : foos) {
            foo.setOtherData(otherDataService.getOtherDataById(foo.getOtherDataId()));
        }
        metricService.recordEndOfMethod();
        return foos;
    }

    public List<FooData> getFoosInCat2(final Category category) {
        final List<FooData> foos = new ArrayList<>(fooService.getFoosInCategory1(category));
        for(final FooData foo : foos) {
            foo.setOtherData(otherDataService.getOtherDataById(foo.getOtherDataId()));
        }
        metricService.recordEndOfMethod();
        return foos;
    }

    public List<FooData> getFoosInCat3(final Category category) {
        final List<FooData> foos = fooService.getFoosInCategory2(category).orElse(Collections.emptyList());
        for(final FooData foo : foos) {
            foo.setOtherData(otherDataService.getOtherDataById(foo.getOtherDataId()));
        }
        metricService.recordEndOfMethod();
        return foos;
    }

    public List<FooData> getFoosInCat4(final Category category) {
        final List<FooData> foos = fooService.getFoosInCategory2(category).orElse(new ArrayList<>());
        foos.addAll(altFooService.getFoosInCategory1(category));
        for(final FooData foo : foos) {
            foo.setOtherData(otherDataService.getOtherDataById(foo.getOtherDataId()));
        }
        metricService.recordEndOfMethod();
        return foos;
    }

    public List<FooData> getFoosInCat5(final Category category) {
        final List<FooData> foos = new ArrayList<>(fooService.getFoosInCategory1(category));
        for(final FooData foo : altFooService.getFoosInCategory1(category)) {
            if(foo.getId().startsWith("PREFIX")) {
                foos.add(foo);
            }
        }
        for(final FooData foo : foos) {
            foo.setOtherData(otherDataService.getOtherDataById(foo.getOtherDataId()));
        }
        metricService.recordEndOfMethod();
        return foos;
    }

    public List<FooData> getFoosInCat6(final Category category) {
        List<FooData> foosInCategory = fooService.getFoosInCategory1(category);
        final List<FooData> ret = new ArrayList<>(foosInCategory.size());
        for(final FooData foo : foosInCategory) {
            if(foo.getId().startsWith("PREFIX")) {
                ret.add(foo);
            }
        }
        for(final FooData foo : ret) {
            foo.setOtherData(otherDataService.getOtherDataById(foo.getOtherDataId()));
        }
        metricService.recordEndOfMethod();
        return ret;
    }

    public List<FooData> getFoosInCat7(final Category category) {
        List<FooData> foosInCategory = fooService.getFoosInCategory1(category);
        final List<FooData> ret = new ArrayList<>();
        for(final FooData foo : foosInCategory) {
            if(foo.getId().startsWith("PREFIX")) {
                ret.add(foo);
            }
        }
        for(final FooData foo : ret) {
            foo.setOtherData(otherDataService.getOtherDataById(foo.getOtherDataId()));
        }
        metricService.recordEndOfMethod();
        return ret;
    }

    public List<FooData> getFoosInCat8(final Category category) {
        final List<FooData> foos = fooService.getFoosInCategory1(category);
        final List<FooData> foosToUse;
        if(!foos.isEmpty()) {
            foosToUse = foos;
        } else {
            foosToUse = altFooService.getFoosInCategory1(category);
        }
        metricService.recordEndOfMethod();
        return foosToUse;
    }

    public List<FooData> getFoosInCat9(final Category category) {
        final List<FooData> foos = fooService.getFoosInCategory1(category);
        List<FooData> foosToUse = null;
        if(!foos.isEmpty()) {
            foosToUse = foos;
        } else {
            foosToUse = altFooService.getFoosInCategory1(category);
        }
        metricService.recordEndOfMethod();
        return foosToUse;
    }

    public List<FooData> getFoosInCat10(final Category category) {
        final List<FooData> foos = fooService.getFoosInCategory1(category);
        List<FooData> ret = foos.stream().filter(x -> x.getId().startsWith("PREFIX")).collect(Collectors.toList());
        metricService.recordEndOfMethod();
        return ret;
    }

    public List<FooData> getFoosInCat11(final Category category) {
        final List<FooData> foos = fooService.getFoosInCategory1(category);
        List<FooData> ret = foos.stream().peek(x -> x.setOtherData(otherDataService.getOtherDataById(x.getOtherDataId()))).collect(Collectors.toList());
        metricService.recordEndOfMethod();
        return ret;
    }

    public FooData getValidFoo1(final Category category) {
        final List<FooData> foos = fooService.getFoosInCategory1(category);
        for(final FooData foo : foos) {
            if(foo.getId().startsWith("PREFIX")) {
                metricService.recordEndOfMethod();
                return foo;
            }
        }
        throw new RuntimeException("No valid foo found");
    }
}
