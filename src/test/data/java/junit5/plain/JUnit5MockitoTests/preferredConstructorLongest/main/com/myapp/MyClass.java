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
    private final String id;
    private final String prop1;
    private final String prop2;
    private final String prop3;

    public MyClass(final String id, final String prop1) {
        this(id, prop1, "DefaultProp2", "DefaultProp3");
    }

    public MyClass(final String id, final String prop1, final String prop2) {
        this(id, prop1, prop2, "DefaultProp3");
    }

    public MyClass(final String id, final String prop1, final String prop2, final String prop3, final String prop4) {
        this(id, prop1, prop2, prop3);
        System.out.println(prop4);
    }

    public MyClass(final String id, final String prop1, final String prop2, final String prop3) {
        this.id = id;
        this.prop1 = prop1;
        this.prop2 = prop2;
        this.prop3 = prop3;
    }

    public String getConcat1() {
        return id + prop1 + prop2 + prop3;
    }
}
