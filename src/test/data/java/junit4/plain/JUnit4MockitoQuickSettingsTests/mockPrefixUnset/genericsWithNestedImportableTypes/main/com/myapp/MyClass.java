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

import java.net.Socket;
import java.text.DateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MyClass {

    private final Map<Date, Map<String, DateFormat>> nestedGenericsField;

    public MyClass(Map<Date, Map<String, DateFormat>> nestedGenericsField) {
        this.nestedGenericsField = nestedGenericsField;
    }

    public void doSomethingWithMultimap(final Map<String, List<Socket>> idToSocketMap) {
        System.out.println(idToSocketMap);
    }

    public static <T extends Currency> T doSomethingWithListOfCurrencies(final Map<String, T> theMap) {
        return theMap.get("TEST");
    }
}
