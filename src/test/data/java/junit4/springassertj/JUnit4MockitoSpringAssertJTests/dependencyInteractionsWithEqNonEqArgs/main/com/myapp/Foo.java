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

public class Foo {

    public static class Bar {
    }

    public static class BarWithEquals {
        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

    public static class SubBarWithEquals extends BarWithEquals {

    }

    public int doSomethingWithPrimitiveArgs(final int a, final int b) {
        return 0;
    }

    public String useNonEqualsOverridingArgsWithNormalArgs(final Bar bar, final String something, final int a) {
        return "";
    }

    public Bar useNonEqualsOverridingArgs(final Bar bar) {
        return new Bar();
    }

    public BarWithEquals useEqualsOverridingArg(final BarWithEquals bar) {
        return new BarWithEquals();
    }

    public BarWithEquals useEqualsOverridingArgSubtype(final SubBarWithEquals bar) {
        return new SubBarWithEquals();
    }

    public void useRunnableAndEqualsArg(final Runnable runnable, final BarWithEquals barWithEquals) {

    }

    public void useRunnableAndNonEqualsArg(final Runnable runnable, final Bar bar) {

    }

}
