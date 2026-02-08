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

    public MyClass(FooService fooService) {
        this.fooService = fooService;
    }

    public String getData(final String getDataParam) {
        final FirstResponseBean firstResponseBean = fooService.makeFirstServiceCall(getDataParam);
        return fooService.makeSecondServiceCall(firstResponseBean.getFirstResponseBeanId()).getSecondResponseBeanId();
    }

    public String getData1(final String getDataParam1) {
        final FirstResponseBean1 firstResponseBean1 = fooService.makeFirstServiceCall1(getDataParam1);
        String tempVar = firstResponseBean1.getFirstResponseBeanId1();
        return fooService.makeSecondServiceCall1(tempVar).getSecondResponseBeanId1();
    }

    public String getData2(final String getDataParam2) {
        final String firstServiceCallResp = fooService.makeStringServiceCall(getDataParam2);
        return fooService.makeSecondServiceCall2(firstServiceCallResp).getSecondResponseBeanId2();
    }

    public String getData3(final FirstResponseBean2 getData3Param) {
        final String getData3FirstServiceCallResp = fooService.makeStringServiceCall1(getData3Param.getFirstResponseBeanId2());
        return getData3FirstServiceCallResp;
    }
}
