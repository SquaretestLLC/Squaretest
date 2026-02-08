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

    public MyClass() {

    }

    public void doSomethingCool(final String... theStrings) {
        for (final String str : theStrings) {
            System.out.println(str);
        }
    }

    public void doSomethingCoolWithChars(final char... theChars) {
        for (final char theChar : theChars) {
            System.out.println(theChar);
        }
    }

    public void doSomethingCoolWithTables(final int[][]... theTables) {
    }

    public native String doSomethingInC();

}
