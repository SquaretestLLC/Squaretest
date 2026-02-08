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
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public class MyClass {
    private final CustomOptionalFooProvider optionalFooProvider;

    public MyClass(final CustomOptionalFooProvider optionalFooProvider) {
        this.optionalFooProvider = optionalFooProvider;
    }

    public Optional<String> getTheStr() {
        return optionalFooProvider.getTheStr();
    }

    public String submitString(final String theData) {
        final Optional<String> existingData = optionalFooProvider.getTheStr();
        String newValue = existingData.orElse("") + theData;
        optionalFooProvider.submitTheStr(newValue);
        return newValue;
    }

    public String submitString1(final String theData) {
        final OptionalDouble existingData = optionalFooProvider.getTheDouble();
        final Optional<String> unusedData = optionalFooProvider.getTheStr();
        String newValue = existingData.orElse(0.0) + theData;
        optionalFooProvider.submitTheStr(newValue);
        return newValue;
    }

    public String submitString2(final String theData) {
        final OptionalInt existingData = optionalFooProvider.getTheInt();
        final Optional<String> unusedData = optionalFooProvider.getTheStr();
        String newValue = existingData.orElse(0) + theData;
        optionalFooProvider.submitTheStr(newValue);
        return newValue;
    }

    public String submitString3(final String theData) {
        final OptionalLong existingData = optionalFooProvider.getTheLong();
        final Optional<String> unusedData = optionalFooProvider.getTheStr();
        String newValue = existingData.orElse(0) + theData;
        optionalFooProvider.submitTheStr(newValue);
        return newValue;
    }
}
