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
    private final FooService fooService;
    private final BarService barService;
    private final String defaultBarId;

    public MyClass(FooService fooService, BarService barService, String defaultBarId) {
        this.fooService = fooService;
        this.barService = barService;
        this.defaultBarId = defaultBarId;
    }

    public FooAndBar getFooAndBar1(String id) {
        final FooData foo = fooService.getFoo1(id);
        final BarData barData = barService.getDefaultBar1(defaultBarId);
        return new FooAndBar(foo, barData);
    }
}
