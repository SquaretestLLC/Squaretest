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

public class OtherBean {
    private FooData ourData;
    private String ourStr;
    private int ourInt;
    private boolean ourValue;
    private boolean ourOtherValue;

    public FooData getOurData() {
        return ourData;
    }

    public void setOurData(FooData ourData) {
        this.ourData = ourData;
    }

    public String getOurStr() {
        return ourStr;
    }

    public void setOurStr(String ourStr) {
        this.ourStr = ourStr;
    }

    public int getOurInt() {
        return ourInt;
    }

    public void setOurInt(int ourInt) {
        this.ourInt = ourInt;
    }

    public boolean isOurValue() {
        return ourValue;
    }

    public void setOurValue(boolean ourValue) {
        this.ourValue = ourValue;
    }

    public boolean isOurOtherValue() {
        return ourOtherValue;
    }

    public void setOurOtherValue(boolean ourOtherValue) {
        this.ourOtherValue = ourOtherValue;
    }
}
