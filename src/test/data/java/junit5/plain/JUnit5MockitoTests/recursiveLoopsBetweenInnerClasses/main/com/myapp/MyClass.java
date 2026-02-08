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
    private final FooService fooService;

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    public String getFoo1(final String id) {
        final InnerClass1 innerClass1 = new InnerClass1();
        return innerClass1.doSomething(id);
    }

    public String getFoo2(final String id) {
        final InnerClass2 innerClass2 = new InnerClass2();
        return innerClass2.doSomething(id);
    }

    private class InnerClass1 {
        public String doSomething(final String id) {
            if(id.startsWith("a")) {
                return getFoo1(id.substring(1));
            }
            return fooService.getFoo1(id);
        }
    }

    private class InnerClass2 {
        public String doSomething(final String id) {
            final InnerInnerClass1 innerClass2 = new InnerInnerClass1();
            return innerClass2.doSomething(id);
        }

        private class InnerInnerClass1 {
        public String doSomething(final String id) {
            if(id.startsWith("a")) {
                return getFoo2(id.substring(1));
            }
            return fooService.getFoo1(id);
        }
        }
    }
}
