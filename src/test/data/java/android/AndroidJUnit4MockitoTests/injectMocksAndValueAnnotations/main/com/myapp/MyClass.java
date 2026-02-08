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

public class MyClass {
    @Autowired
    private FooService fooService;
    @Autowired
    private BarService barService;
    @Autowired
    private ConfigBean configBean;
    @Value("defaultFooId")
    private String defaultFooId;
    @Value("specialValuesMap")
    private Map<String, String> specialValuesMap;

    public FooAndBar getFooAndBar1(String id) {
        final FooData foo = fooService.getFoo1(id);
        final BarData barData = barService.getDefaultBar1(defaultFooId);
        return new FooAndBar(foo, barData);
    }

    public FooData getDefaultFoo1() {
        return fooService.getDefaultFoo1(defaultFooId);
    }

    public FooData getSpecialFoo1() {
        return fooService.getSpecialFoo1(specialValuesMap.get("SpecialFooId"));
    }

    public FooData getSpecialFoo2() {
        return fooService.getSpecialFoo2(configBean.getSpecialPath());
    }
}
