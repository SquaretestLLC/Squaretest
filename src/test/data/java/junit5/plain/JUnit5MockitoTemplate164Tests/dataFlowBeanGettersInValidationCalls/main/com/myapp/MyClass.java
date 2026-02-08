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

import java.util.Objects;

public class MyClass {

    private final FooService fooService;

    public MyClass(FooService fooService) {
        this.fooService = fooService;
    }

    public String getData(final String getDataParam) {
        final FirstResponseBean firstResponseBean = Objects.requireNonNull(fooService.makeFirstServiceCall(Objects.requireNonNull(getDataParam)));
        return Objects.requireNonNull(fooService.makeSecondServiceCall(Objects.requireNonNull(firstResponseBean.getFirstResponseBeanId())).getSecondResponseBeanId());
    }

    public String getData1(final String getDataParam1) {
        final FirstResponseBean1 firstResponseBean1 = Objects.requireNonNull(fooService.makeFirstServiceCall1(Objects.requireNonNull(getDataParam1)));
        String tempVar = Objects.requireNonNull(firstResponseBean1.getFirstResponseBeanId1());
        return Objects.requireNonNull(fooService.makeSecondServiceCall1(Objects.requireNonNull(tempVar)).getSecondResponseBeanId1());
    }

    public String getData2(final String getDataParam2) {
        final String firstServiceCallResp = Objects.requireNonNull(fooService.makeStringServiceCall(Objects.requireNonNull(getDataParam2)));
        return Objects.requireNonNull(fooService.makeSecondServiceCall2(Objects.requireNonNull(firstServiceCallResp)).getSecondResponseBeanId2());
    }

    public String getData3(final FirstResponseBean2 getData3Param) {
        final String getData3FirstServiceCallResp = Objects.requireNonNull(fooService.makeStringServiceCall1(Objects.requireNonNull(getData3Param.getFirstResponseBeanId2(), "message")));
        return Objects.requireNonNull(getData3FirstServiceCallResp);
    }
}
