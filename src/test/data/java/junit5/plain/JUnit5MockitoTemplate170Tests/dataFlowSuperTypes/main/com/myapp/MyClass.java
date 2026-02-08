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

import com.myapp.bases1.BaseClass;
import com.myapp.bases1.FooService;

import java.io.IOException;

public class MyClass extends BaseClass {

    private final FooService fooService;

    public MyClass(final FooService fooService) {
        super(fooService);
        this.fooService = fooService;
    }

    public String performGetUpData(final String myClassPerformGetUpDataParam) {
        try {
            return getUpperFoo(myClassPerformGetUpDataParam) + fooService.getData(myClassPerformGetUpDataParam) + fooService.activateBar(myClassPerformGetUpDataParam);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String performGetUpData1(final String myClassPerformGetUpData1Param) {
        // Note: the generated test class will use the same value for this param as for performGetUpData().
        // The reason is: When we run the dataflow algorithm on the fooService.getData(<<fooServiceGetDataParam>>) call in com.myapp.BaseClass.getFoo(),
        // It finds the following:
        // 16 return fooService.getData(baseClassGetFooParam);
        // 14 public String getFoo(final String baseClassGetFooParam) {
        // 14 return getFoo(iBaseGetUpperFoo1Param).toUpperCase(Locale.ROOT);
        // 13 default String getUpperFoo1(final String iBaseGetUpperFoo1Param) {
        // 33 return getUpperFoo1(myClassPerformGetUpData1Param);
        // 23 public String performGetUpData1(final String myClassPerformGetUpData1Param) {
        // The root cause is: IDEA dataflow algorithm finds all superclass calls that can provide a value for
        // fooServiceGetDataParam. In this case IBase.getUpperFoo1() calls getFoo(). getFoo() calls fooService.getData().
        // Our call to getUpperFoo1() does not call IBase.getUpperFoo1(), it calls com.myapp.IOtherBase.getUpperFoo1().
        // However, because getUpperFoo1(<<param>>) is linked to getFoo(<<param>>), which is linked to the fooService.getData(<<param>>),
        // our param gets merged with the other params and will have the same value.
        return getUpperFoo1(myClassPerformGetUpData1Param);
    }
}


