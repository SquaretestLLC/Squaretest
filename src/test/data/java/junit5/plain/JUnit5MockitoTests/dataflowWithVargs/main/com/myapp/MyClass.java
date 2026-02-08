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

    private final FooRestClient fooRestClient;

    public MyClass(final FooRestClient fooRestClient) {
        this.fooRestClient = fooRestClient;
    }

    public String createFoo1(final String createFooIdParam1, final String createFooNameParam1, final String createFooOtherParam1) {
        return fooRestClient.getData1("https://example.com/{fooId}/{fooName}/{fooOtherValue}/", createFooIdParam1, createFooNameParam1, createFooOtherParam1);
    }

    public String createFoo2(final String createFooIdParam2, final String createFooNameParam2, final String createFooOtherParam2) {
        final String firstPart = fooRestClient.getData2("https://example.com/{fooId}", createFooIdParam2);
        final String secondPart = fooRestClient.getData2("https://example.com/{fooOtherValue}", createFooNameParam2);
        final String thirdPart = fooRestClient.getData2("https://example.com/{fooOtherValue}", createFooOtherParam2);
        return firstPart + secondPart + thirdPart;
    }

    public String createFoo3(final String createFooIdParam3, final String createFooNameParam3, final String createFooOtherParam3) {
        return fooRestClient.getData3("https://example.com/");
    }
}
