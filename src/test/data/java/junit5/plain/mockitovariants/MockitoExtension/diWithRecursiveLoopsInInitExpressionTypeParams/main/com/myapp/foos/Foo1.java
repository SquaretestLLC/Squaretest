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
package com.myapp.foos;

import java.util.HashSet;
import java.util.List;

public class Foo1 {
    private List<Foo1> parents;
    private List<Foo1> children;
    private Bar bar;
    private HashSet<Bar> myBarSet;
    private OtherBar otherBar;
    private List<OtherBar> otherBars;
    private SimpleBean simpleBean;
    private SimpleBean simpleBean2;
    private SimpleBean simpleBean3;
    private SimpleBean simpleBean4;

    public Foo1(List<Foo1> parents, List<Foo1> children, Bar bar, HashSet<Bar> myBarSet, OtherBar otherBar, List<OtherBar> otherBars, SimpleBean simpleBean, SimpleBean simpleBean2, SimpleBean simpleBean3, SimpleBean simpleBean4) {
        this.parents = parents;
        this.children = children;
        this.bar = bar;
        this.myBarSet = myBarSet;
        this.otherBar = otherBar;
        this.otherBars = otherBars;
        this.simpleBean = simpleBean;
        this.simpleBean2 = simpleBean2;
        this.simpleBean3 = simpleBean3;
        this.simpleBean4 = simpleBean4;
    }
}
