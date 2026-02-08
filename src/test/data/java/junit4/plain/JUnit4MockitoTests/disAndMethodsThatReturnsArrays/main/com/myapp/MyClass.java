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

import java.util.List;

public class MyClass {

    private FooCreator fooCreator;

    public MyClass(FooCreator fooCreator) {
        this.fooCreator = fooCreator;
    }

    public int[] getTheInts() {
        return fooCreator.getTheInts();
    }

    public int[][][] getTheIntCube() {
        return fooCreator.getTheIntCube();
    }

    public List<int[]> getTheIntArrays() {
        return fooCreator.getTheIntArrays();
    }

    public List<String[][]> getTheTables() {
        return fooCreator.getTheTables();
    }

    public Bean[] getTheBeans() {
        return fooCreator.getTheBeans();
    }

    public Bean[][] getTheBeanMatrix() {
        return fooCreator.getTheBeanMatrix();
    }

    public List<Bean[]> getTheBeanArrays() {
        return fooCreator.getTheBeanArrays();
    }

    public byte[] getTheByteArray() {
        return fooCreator.getTheByteArray();
    }

    public byte[][] getTheByteMatrix() {
        return fooCreator.getTheByteMatrix();
    }

    public List<byte[]> getTyeByteArrays() {
        return fooCreator.getTheByteArrays();
    }

    public List<byte[][]> getTyeByteMatricies() {
        return fooCreator.getTheByteMatricies();
    }
}
