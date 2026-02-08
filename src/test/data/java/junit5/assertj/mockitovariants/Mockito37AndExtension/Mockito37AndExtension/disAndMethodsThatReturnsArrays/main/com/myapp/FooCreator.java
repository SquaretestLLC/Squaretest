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

import java.util.*;

public class FooCreator {
    public int[] getTheInts() {
        return new int[]{};
    }

    public List<int[]> getTheIntArrays() {
        return Collections.emptyList();
    }

    public Bean[] getTheBeans() {
        return new Bean[]{};
    }

    public List<Bean[]> getTheBeanArrays() {
        return Collections.emptyList();
    }

    public int[][][] getTheIntCube() {
        return new int[][][]{};
    }

    public List<String[][]> getTheTables() {
        return Collections.emptyList();
    }

    public Bean[][] getTheBeanMatrix() {
        return new Bean[][]{};
    }

    public List<byte[]> getTheByteArrays() {
        return Collections.emptyList();
    }

    public List<byte[][]> getTheByteMatricies() {
        return Collections.emptyList();
    }

    public byte[] getTheByteArray() {
        return new byte[]{};
    }

    public byte[][] getTheByteMatrix() {
        return new byte[][]{};
    }
}
