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

import com.myapp.dtoio.*;

public class MyClass {
    private final FooProvider fooProvider;

    public MyClass(final FooProvider fooProvider) {
        this.fooProvider = fooProvider;
    }

    public BeanWithIs makeBeanWithIs() {
        return fooProvider.makeBeanWithIs();
    }

    public BeanWithIsAndReader makeBeanWithIsAndReader() {
        return fooProvider.makeBeanWithIsAndReader();
    }

    public BeanWithReader makeBeanWithReader() {
        return fooProvider.makeBeanWithReader();
    }

    public BeanWithSubIs makeBeanWithSubIs() {
        return fooProvider.makeBeanWithSubIs();
    }

    public BeanWithSubIsMultipleSetterOverloads makeBeanWithSubIsMultipleSetterOverloads() {
        return fooProvider.makeBeanWithSubIsMultipleSetterOverloads();
    }

    public BeanWithSubReader makeBeanWithSubReader() {
        return fooProvider.makeBeanWithSubReader();
    }

    public BeanWithSubReaderMultipleSetterOverloads makeBeanWithSubReaderMultipleSetterOverloads() {
        return fooProvider.makeBeanWithSubReaderMultipleSetterOverloads();
    }

    public CloseableBeanWithIs makeCloseableBeanWithIs() {
        return fooProvider.makeCloseableBeanWithIs();
    }

    public CloseableBeanWithReader makeCloseableBeanWithReader() {
        return fooProvider.makeCloseableBeanWithReader();
    }
}
