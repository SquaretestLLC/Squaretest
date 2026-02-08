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
    private FooData mainFooData;
    private FooData otherFooData;
    private FooData thirdFooData;

    private Object fourthData;

    private String fifthData;

    private Object sixthData;

    private String seventhData;

    private String eighthData;

    private String ninthData;

    public MyClass(FooService fooService) {
        this.fooService = fooService;
        this.mainFooData = null;
    }

    protected FooData getMainFooData() {
        if(mainFooData == null) {
            mainFooData = getMainFooDataImpl();
        }
        return mainFooData;
    }

    protected void setMainFooData(final String param) {
        mainFooData = fooService.getFoo1(param);
    }

    protected MyClass setOtherFooData(final String param) {
        this.otherFooData = fooService.getFoo1(param);
        return this;
    }

    private FooData getMainFooDataImpl() {
        return fooService.getFoo1("MyId");
    }

    public FooData getThirdFooData() {
        return thirdFooData;
    }

    public void setThirdFooData(FooData thirdFooData) {
        this.thirdFooData = thirdFooData;
    }

    public String getFourthData() {
        return (String) fourthData;
    }

    public void setFourthData(String fourthData) {
        this.fourthData = (String) fourthData;
    }

    public String getFifthData() {
        return fifthData;
    }

    public void setFifthData(Object fifthData) {
        this.fifthData = (String) fifthData;
    }

    public String getSixthData() {
        return ((String)sixthData);
    }

    public void setSixthData(String sixthData) {
        this.sixthData = ((Object)sixthData);
    }

    public String getSeventhData() {
        return seventhData;
    }

    public MyClass setSeventhData(String seventhData) {
        this.seventhData = seventhData;
        return this;
    }

    public String getEighthData() {
        return eighthData;
    }

    public MyClass setEighthData(Object eighthDataParam) {
        eighthData = ((String) eighthDataParam);
        return this;
    }

    public String getNinthData() {
        return ninthData;
    }

    public void setNinthData(String ninthDataParam) {
        ninthData = ninthDataParam;
    }
}
