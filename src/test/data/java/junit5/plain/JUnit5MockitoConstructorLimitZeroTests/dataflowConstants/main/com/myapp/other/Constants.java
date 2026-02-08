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
package com.myapp.other;

public class Constants {
    public static final String OtherClassConstant1 = "OtherClassConstantValue";
    public static final String OtherClassConstant2 = OtherClassConstant1;
    public static final String OtherClassConstant3 = OtherClassConstant2;

    public static final String OtherClassCircular1 = OtherClassCircular2;
    public static final String OtherClassCircular2 = OtherClassCircular1;

    public static String getConstant() {
        return "GetConstantValue";
    }
}
