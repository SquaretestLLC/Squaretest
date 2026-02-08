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

public class MyClass extends BaseClass {

    private final FooService fooService;

    public MyClass(ConfigBean configBean, OtherBean otherBean, FooService fooService) {
        super(configBean, otherBean, fooService);
        this.fooService = fooService;
    }

    // Override a super class method.
    @Override
    public FooData getFooData1(String id) {
        return fooService.getFooData1("newPath", id);
    }

    // Override and call the super.
    @Override
    public FooData getFooData3(String id) {
        return super.getFooData3(id);
    }

    // Override getters and setters.
    // These won't be recognized as getters/setters, but we should still create tests for them.
    // Additionally, we should not create tests for the super methods.
    @Override
    public String getOtherData() {
        return super.getOtherData();
    }

    @Override
    public void setOtherData(String otherData) {
        super.setOtherData(otherData);
    }
}
