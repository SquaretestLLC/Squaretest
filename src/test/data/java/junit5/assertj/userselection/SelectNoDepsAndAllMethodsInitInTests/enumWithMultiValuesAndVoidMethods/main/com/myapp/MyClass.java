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

import org.apache.commons.lang3.mutable.MutableInt;

public enum MyClass {

    FirstSpace(1),
    SecondSpace(2),
    ThirdSpace(3),
    FourthSpace(4);

    private final int baseValue;

    MyClass(final int baseValue) {
        this.baseValue = baseValue;
    }

    public void normalize(final MutableInt theIntToNormalize) {
        System.out.println(theIntToNormalize);
    }

    public void normalizeNoArg() {
        System.out.println("");
    }

    public void tryNormalize(final MutableInt someInt) throws RuntimeException {
        System.out.println(socketsToClose);
    }

    public void tryNormalizeNoArg() throws RuntimeException {
        System.out.println("");
    }
}
