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

import java.io.IOException;

public enum MyClass {

    RED("red"),
    BLUE("blue"),
    GREEN("green"),
    PURPLE("purple");

    final String name;

    MyClass(final String name) {
        this.name = name;
    }

    public String getLowercaseColor() {
        return this.name.toLowerCase();
    }

    public String getUppercaseColor() {
        return this.name.toUpperCase();
    }

    public boolean isGreen() {
        return this == GREEN;
    }

    public String somethingThatThrows(final String arg) throws IOException {
        throw new IOException();
    }

    public static boolean isSupported(final String colorName) {
        try {
            MyClass.valueOf(colorName);
            return true;
        } catch (final Exception ex) {
            return false;
        }
    }

    public static String convertTo(final String name) throws IOException {
        throw new IOException();
    }

    public static String convertToSafe(final String name) {
        return "";
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "name='" + name + '\'' +
                '}';
    }
}
