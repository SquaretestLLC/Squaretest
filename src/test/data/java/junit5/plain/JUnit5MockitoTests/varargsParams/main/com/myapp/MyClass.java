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

import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MyClass {
    public String getProp(final FooData... fooData) {
        return Arrays.stream(fooData).min(Comparator.comparing(FooData::getId)).map(FooData::getName).orElse(null);
    }

    public String getProp(final List<FooData>... fooData) {
        return fooData[0].stream().min(Comparator.comparing(FooData::getId)).map(FooData::getName).orElse(null);
    }

    public String getProp(final FooData[]... fooData) {
        return Arrays.stream(fooData[0]).min(Comparator.comparing(FooData::getId)).map(FooData::getName).orElse(null);
    }

    public String getProp(final FooData[][]... fooData) {
        return Arrays.stream(fooData[0][0]).min(Comparator.comparing(FooData::getId)).map(FooData::getName).orElse(null);
    }

    public String getProp(final Wrapper<FooData>... fooData) {
        return fooData[0].getTheValue().getId();
    }

    public String getProp(final Wrapper<FooData>[]... fooData) {
        return fooData[0][0].getTheValue().getId();
    }

    public String getProp(final Wrapper<FooData>[][]... fooData) {
        return fooData[0][0][0].getTheValue().getId();
    }

    public void close(final InputStream... inputStreams) {
        for(final InputStream is : inputStreams) {
            closeQuietly(is);
        }
    }

    public void close(final InputStream[]... inputStreams) {
        for(final InputStream is : inputStreams[0]) {
            closeQuietly(is);
        }
    }

    public void close(final InputStream[][]... inputStreams) {
        for(final InputStream is : inputStreams[0][0]) {
            closeQuietly(is);
        }
    }

    private void closeQuietly(InputStream is) {
        try {
            if(is != null) {
                is.close();
            }
        } catch (final Exception e) {
        }
    }
}
