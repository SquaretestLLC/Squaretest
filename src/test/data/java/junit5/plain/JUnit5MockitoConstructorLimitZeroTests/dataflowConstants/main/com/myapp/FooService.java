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

import java.util.Locale;
import java.util.UUID;

public class FooService {
    public Foo getFooById(final String id) {
        return new Foo(id,"name");
    }

    public Foo getFooById2(final String id) {
        return new Foo(id,"name");
    }

    public Foo getFooById3(final String id) {
        return new Foo(id,"name");
    }

    public Foo getFooById4(final String id) {
        return new Foo(id,"name");
    }

    public Foo getFooById5(final String id) {
        return new Foo(id,"name");
    }
    public Foo getFooById6(final String id) {
        return new Foo(id,"name");
    }
    public Foo getFooById7(final String id) {
        return new Foo(id,"name");
    }
    public Foo getFooById8(final String id) {
        return new Foo(id,"name");
    }
    public Foo getFooById9(final String id) {
        return new Foo(id,"name");
    }
    public Foo getFooById10(final String id) {
        return new Foo(id,"name");
    }
    public Foo getFooById11(final String id) {
        return new Foo(id,"name");
    }
    public Foo getFooById12(final String id) {
        return new Foo(id,"name");
    }
    public Foo getFooById13(final String id) {
        return new Foo(id,"name");
    }

    public Foo getFooById14(final String id) {
        return new Foo(id.toString(),"name");
    }

    public Foo getFooById15(final UUID id) {
        return new Foo(id.toString(),"name");
    }

    public Foo getFooById16(final String id) {
        return new Foo(id,"name");
    }

    public Foo getFooById17(final String id) {
        return new Foo(id,"name");
    }

    public Foo getFooById18(final String id) {
        return new Foo(id,"name");
    }

    public Foo getFooById19(final String id) {
        return new Foo(id,"name");
    }

    public Foo getFooById20(final String id) {
        return new Foo(id,"name");
    }

    public Foo getFooById21(final String id) {
        return new Foo(id,"name");
    }

    public Foo getFooById22(final String id) {
        return new Foo(id,"name");
    }

    public Foo getFooByUUID(final UUID id) {
        return new Foo(id.toString(),"name");
    }

    public String convertId(final String id) {
        return id.toUpperCase(Locale.ROOT);
    }
}
