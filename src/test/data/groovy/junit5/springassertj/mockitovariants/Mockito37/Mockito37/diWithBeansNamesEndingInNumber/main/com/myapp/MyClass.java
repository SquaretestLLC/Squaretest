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

    private FooCreator fooCreator;

    public MyClass(FooCreator fooCreator) {
        this.fooCreator = fooCreator;
    }

    // Test bean with subbeans of the same type. Ensure Squaretest correctly updates the names to avoid collisions.
    public ResponseBean sendRequest(final String params) {
        return this.fooCreator.submitRequest();
    }

    // Test bean with subbeans of the same type. Ensure Squaretest resets the name suffixes after creating the test
    // for sendRequest().
    public ResponseBean sendDifferentRequest(final String params) {
        return this.fooCreator.submitRequest2();
    }

    // Test bean with subbeans of the same type in two separate dependency interactions in the same method.
    // Ensure Squaretest correctly updates the names to avoid collisions.
    public ResponseBean sendBothRequests(final String params) {
        this.fooCreator.submitRequest();
        return this.fooCreator.submitRequest2();
    }
}
