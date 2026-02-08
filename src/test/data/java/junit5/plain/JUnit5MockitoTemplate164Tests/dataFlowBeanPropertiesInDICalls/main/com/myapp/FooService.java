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

import java.util.Locale;

public class FooService {

    public FirstResponseBean makeFirstServiceCall(final String firstServiceCallId) {
        return new FirstResponseBean();
    }

    public SecondResponseBean makeSecondServiceCall(final String secondServiceCallId) {
        return new SecondResponseBean();
    }

    public FirstResponseBean1 makeFirstServiceCall1(final String firstServiceCallId1) {
        return new FirstResponseBean1();
    }

    public SecondResponseBean1 makeSecondServiceCall1(final String secondServiceCallId1) {
        return new SecondResponseBean1();
    }

    public String makeStringServiceCall(final String stringServiceCallId) {
        return stringServiceCallId.toUpperCase(Locale.ROOT);
    }

    public SecondResponseBean2 makeSecondServiceCall2(final String secondServiceCallId1) {
        return new SecondResponseBean2();
    }

    public String makeStringServiceCall1(final String stringServiceCallId1) {
        return stringServiceCallId1.toUpperCase(Locale.ROOT);
    }


}
