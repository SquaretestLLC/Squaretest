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
    private final WeirdFactory weirdFactory;

    public MyClass(final WeirdFactory weirdFactory) {
        this.weirdFactory = weirdFactory;
    }

    public String doSomething1(final String name) {
        return weirdFactory.createFrom(name).toString();
    }

    public String doSomething2(final String name) {
        return weirdFactory.createFrom(makeClass(name)).toString();
    }

    public String doSomething3(final String name) {
        final Wrapper temp = weirdFactory.createFrom(makeClass(name));
        return weirdFactory.createFrom(temp.getClass()).toString();
    }

    public String doSomething4(final Class<?> theCLass) {
        return weirdFactory.createFrom(theCLass).toString();
    }

    public String doSomething5() {
        return weirdFactory.createFrom((Class)Fake.class).toString();
    }

    public String doSomething6() {
        final Wrapper<?> startingWrapper = Wrapper.create(String.class);
        final Wrapper<?> otherWrapper = weirdFactory.createFrom(startingWrapper);
        return otherWrapper.toString();
    }

    private Class makeClass(final String name) {
        return Object.class;
    }
}
