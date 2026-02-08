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

import com.myapp.bases.FooService;
import com.myapp.bases.SubBaseClass;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyClass extends SubBaseClass {

    public MyClass(final FooService fooService) {
        super(fooService);
    }

    @Override
    public String getData(final String someValue) throws IOException {
        return super.getData(someValue);
    }

    @Override
    public String getOtherData(final String otherValue) throws IOException {
        return super.getOtherData(otherValue);
    }

    @Override
    public String getThingFromDatabase(final String value) throws SQLException {
        return super.getThingFromDatabase(value);
    }

    @Override
    public String doSomething(final String purchaseId) {
        return super.doSomething(purchaseId);
    }

    @Override
    public String activateBar(final String computeCodeForOrder) {
        return super.activateBar(computeCodeForOrder);
    }

    // Test interact directly with super class param.
    public String activateBar2(final String something) {
        return fooService.activateBar(something + "0");
    }

    // Test interact directly with super class param.
    public String activateBar3(final String something) {
        final List<String> theList = new ArrayList<>();
        theList.stream().map(MyClass.super::activateBar);
        return something;
    }

}


