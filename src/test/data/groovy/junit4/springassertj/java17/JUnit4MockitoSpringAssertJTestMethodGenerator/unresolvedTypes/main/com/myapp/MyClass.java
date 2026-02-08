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

import com.mycompany.FooBar;
import com.mycompany.DifferentFooBar;
import com.mycompany.OtherFooBar;

import java.util.Map;
import java.util.List;

import java.util.Arrays;

public class MyClass {

    private final FooBar foobar;
    private final Map<String, FooBar> foobarMap;
    private final OtherFooBar[] otherFooBars;

    public MyClass(final FooBar foobar,
                   final Map<String, FooBar> foobarMap,
                   final OtherFooBar[] otherFooBars) {
        this.foobar = foobar;
        this.foobarMap = foobarMap;
        this.otherFooBars = otherFooBars;
    }

    public void addFoo(final DifferentFooBar differentFooBar) {
        System.out.println("");
    }

    public DifferentFooBar retrieveFooBar() {
        return new DifferentFooBar();
    }

    public void addSpecialFoo(final List<? extends OtherFooBars> otherFooBarList){
        System.out.println(otherFooBarList);
    }
}
