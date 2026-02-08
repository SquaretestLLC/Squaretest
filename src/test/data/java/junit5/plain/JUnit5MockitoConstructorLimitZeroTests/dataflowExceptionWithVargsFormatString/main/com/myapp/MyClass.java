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

import java.util.Optional;

public class MyClass {

    private final MetricService metricService;

    public MyClass(MetricService metricService) {
        this.metricService = metricService;
    }

    public String doSomething(final String firstValue, final String secondValue) {
        validateInput(firstValue, secondValue);
        return firstValue + secondValue;
    }
    private void validateInput(final String theFirstValue, final String theSecondValue){
        Optional.ofNullable(theFirstValue).orElseThrow(() -> new MyException(
                "Cannot use params with secondValue = %s, because firstValue is null", theSecondValue));
        Optional.ofNullable(theSecondValue).orElseThrow(() -> new MyException(
                "Cannot use params with firstValue = %s, because secondValue is null", theFirstValue));
    }

    public String doSomethingElse(final String otherFirstValue, final String otherSecondValue) {
        if(otherFirstValue == null) {
            metricService.recordError("Cannot use params with secondValue = %s, because firstValue is null", otherSecondValue);
            return null;
        }
        if(otherSecondValue == null) {
            metricService.recordError("Cannot use params with secondValue = %s, because firstValue is null", otherFirstValue);
            return null;
        }
        return otherFirstValue + otherSecondValue;
    }

    public String doThirdThing(final String thirdThingParam) {
        if(thirdThingParam == null) {
            metricService.recordError("Third thing param is null", thirdThingParam);
        }
        return thirdThingParam.toUpperCase();
    }

    public String doSomething1(final String firstValue1, final String secondValue1) {
        validateInput1(firstValue1, secondValue1);
        return firstValue1 + secondValue1;
    }
    private void validateInput1(final String theFirstValue1, final String theSecondValue1){
        Optional.ofNullable(theFirstValue1).orElseThrow(() -> new MyOtherException(
                "Cannot use params with secondValue = %s, because firstValue is null", theSecondValue1));
        Optional.ofNullable(theSecondValue1).orElseThrow(() -> new MyOtherException(
                "Cannot use params with firstValue = %s, because secondValue is null", theFirstValue1));
    }

}
