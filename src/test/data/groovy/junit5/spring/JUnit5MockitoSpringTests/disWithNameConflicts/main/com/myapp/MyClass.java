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
    private final FooService mainFooService;
    private final FooService oldFooService;

    public MyClass(FooService mainFooService, FooService oldFooService) {
        this.mainFooService = mainFooService;
        this.oldFooService = oldFooService;
    }

    public FooData getFooData1(final String id) {
        if(id.startsWith("OLD")) {
            return oldFooService.getFooData(id);
        }
        return mainFooService.getFooData(id);
    }

    public FooData getFooData2(final String id) {
        if(id.startsWith("A")) {
            return mainFooService.getFooData(FooType.Normal);
        }
        return mainFooService.getFooData(id);
    }

    public FooData getFooData3(final String id) {
        if(id.startsWith("A")) {
            return mainFooService.getFooData(FooType.Normal);
        }
        if(id.startsWith("B")) {
            return mainFooService.getFooData(id);
        }
        return mainFooService.getFooData(id, FooType.Normal);
    }

    public FooData getFooData4(final String id) {
        if(id.startsWith("A")) {
            return mainFooService.getFooData("A" + id);
        }
        if(id.startsWith("B")) {
            return mainFooService.getFooData("B" + id);
        }
        return mainFooService.getFooData("C" + id);
    }
}
