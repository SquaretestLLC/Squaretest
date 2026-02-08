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

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    public OtherData1 convert1(final FooData1 fooData1) {
        final OtherData1 otherData1 = new OtherData1();
        otherData1.setId(fooData1.getId());
        otherData1.setName(fooData1.getName());
        otherData1.setOther(fooData1.getOther());
        return otherData1;
    }

    public OtherData2 convert2(final FooData2 fooData2) {
        return fooService.convertTo(fooData2, OtherData2.class);
    }

    public OtherData3 convert3(final FooData3 fooData3) {
        return new OtherData3().withId(fooData3.getId())
                .withName(fooData3.getName())
                .withOther(fooData3.getOther());
    }
}
