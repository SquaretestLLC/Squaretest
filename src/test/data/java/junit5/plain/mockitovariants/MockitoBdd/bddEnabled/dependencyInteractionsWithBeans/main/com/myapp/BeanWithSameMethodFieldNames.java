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

public class BeanWithSameMethodFieldNames {
    private FooData fooData;
    private String theString;
    private int theInt;
    private boolean isValue;
    private boolean otherValue;

    public FooData fooData() {
        return fooData;
    }

    public void fooData(FooData fooData) {
        this.fooData = fooData;
    }

    public String theString() {
        return theString;
    }

    public void theString(String theString) {
        this.theString = theString;
    }

    public int theInt() {
        return theInt;
    }

    public void theInt(int theInt) {
        this.theInt = theInt;
    }

    public boolean value() {
        return isValue;
    }

    public void value(boolean value) {
        isValue = value;
    }

    public boolean otherValue() {
        return otherValue;
    }

    public void otherValue(boolean otherValue) {
        this.otherValue = otherValue;
    }
}
