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

import com.squaretest.supertypes.base.df.BarService;
import com.squaretest.supertypes.base.df.BaseClass;
import com.squaretest.supertypes.base.df.data1.BarDTO1;
import com.squaretest.supertypes.base.df.data1.BarResponse1;
import com.squaretest.supertypes.base.df.data2.BarResponse2;
import com.squaretest.supertypes.base.df.data3.BarDTO3;
import com.squaretest.supertypes.base.df.data4.BarDTO4;
import com.squaretest.supertypes.base.df.data5.BarDTO5;

public class MyClass extends BaseClass {
    private final BarService barService;

    public MyClass(final BarService barService) {
        this.barService = barService;
    }

    /**
     * Test calling helper method in compiled super class to convert from response data to DTO bean.
     */
    public BarDTO1 getBar1(final String myClassGetBarParam1) {
        final BarResponse1 barResponse1 = barService.getBar1(myClassGetBarParam1);
        return convert1(barResponse1);
    }

    /**
     * Test calling helper method in compiled super class that reads a property from the bean.
     */
    public String getBar2Name(final String myClassGetBar2NameParam) {
        final BarResponse2 barResponse2 = barService.getBar2(myClassGetBar2NameParam);
        return computeBarResponse2Name(barResponse2);
    }

    /**
     * Test calling instance helper method in compiled super class to convert from response bean into DTO (via constructor call).
     */
    public BarDTO3 getBar3(final String myClassGetBar3NameParam) {
        return computeBarDTO3(barService.getBar3(myClassGetBar3NameParam));
    }

    /**
     * Test calling static helper method in compiled super class to convert from response bean into DTO (via constructor call).
     */
    public BarDTO4 getBar4(final String myClassGetBar4NameParam) {
        return computeBarDTO4(barService.getBar4(myClassGetBar4NameParam));
    }

    /**
     * Test calling static helper method in compiled super class to convert from response DTO into other DTO (via constructor call).
     */
    public BarDTO5 getBar5(final String myClassGetBar5NameParam) {
        return computeBarDTO5(barService.getBar5(myClassGetBar5NameParam));
    }

    /**
     * Test calling super method that returns hardcoded String.
     */
    public String getMyClassId() {
        return super.getId();
    }

    /**
     * Test calling super method that returns the parameter.
     */
    public String getTheString(final String myClassGetTheStringParam) {
        return super.baseGetTheString(myClassGetTheStringParam);
    }

}
