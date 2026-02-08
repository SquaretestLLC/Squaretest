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

public class BaseClass {
    private final ConfigBean configBean;
    private final OtherBean otherBean;
    private final FooService fooService;

    private String someData;

    private String otherData;

    public BaseClass(ConfigBean configBean, OtherBean otherBean, FooService fooService) {
        this.configBean = configBean;
        this.otherBean = otherBean;
        this.fooService = fooService;
    }

    public FooData getFooData1(String id) {
        return fooService.getFooData1(configBean.getBasePath(), id);
    }

    public FooData getFooData2(String id) {
        return fooService.getFooData2(configBean.getSpecialPath(), id);
    }

    public FooData getFooData3(String id) {
        return fooService.getFooData3(configBean.getThirdPath(), id);
    }

    public OtherBean getOtherBean() {
        return otherBean;
    }

    public String getSomeData() {
        return someData;
    }

    public void setSomeData(String someData) {
        this.someData = someData;
    }

    public String getOtherData() {
        return otherData;
    }

    public void setOtherData(String otherData) {
        this.otherData = otherData;
    }
}
