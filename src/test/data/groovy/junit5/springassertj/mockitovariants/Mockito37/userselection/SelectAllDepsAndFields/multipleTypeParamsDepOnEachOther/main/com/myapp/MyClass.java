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

public class MyClass<K extends Comparable<V>, V> {

    private K left;
    private V right;

    public MyClass() {
    }

    public K getTheLeft() {
        return left;
    }

    public void setTheLeft(final K left) {
        this.left = left;
    }

    public V getTheRight() {
        return right;
    }

    public void setTheRight(final V right) {
        this.right = right;
    }

    public String combine() {
        return left.toString() + right.toString();
    }
}
