/*
 *
 *  * Copyright 2026 Squaretest LLC.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.squaretest.template.impl;

import java.util.Objects;

public class Bar {
    private final String canonicalName;
    private final boolean mockable;
    private final int number;

    public Bar(final String canonicalName) {
        this(canonicalName, false);
    }

    public Bar(final String canonicalName, final boolean mockable) {
        this(canonicalName, mockable, 0);
    }

    public Bar(final String canonicalName, final boolean mockable, int number) {
        this.canonicalName = canonicalName;
        this.mockable = mockable;
        this.number = number;
    }

    public boolean isMockable() {
        return mockable;
    }

    public String getCanonicalName() {
        return canonicalName;
    }


    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Bar bar = (Bar) o;
        return Objects.equals(canonicalName, bar.canonicalName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(canonicalName);
    }
}
