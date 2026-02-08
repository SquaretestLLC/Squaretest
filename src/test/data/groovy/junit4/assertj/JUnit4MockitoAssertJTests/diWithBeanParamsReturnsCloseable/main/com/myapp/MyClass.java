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

import java.io.Closeable;
import java.io.IOException;

public class MyClass {
    private final FooService fooService;

    public MyClass(FooService fooService) {
        this.fooService = fooService;
    }

    public void doSomething1() {
        final FooParams fooParams = new FooParams();
        fooParams.setId("THEID");
        fooParams.setName("THENAME");
        try(final SpecialCloseable closeable = fooService.getCloseable1(fooParams)) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSomething2() {
        try(final SpecialCloseable closeable = fooService.getCloseable2()) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSomething3() {
        final FooParams fooParams = new FooParams();
        fooParams.setId("THEID");
        fooParams.setName("THENAME");
        try(final CloseableWithConstructor closeable = fooService.getCloseable3(fooParams)) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSomething4() {
        try(final CloseableWithConstructor closeable = fooService.getCloseable4()) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSomething5() {
        final FooParams fooParams = new FooParams();
        fooParams.setId("THEID");
        fooParams.setName("THENAME");
        fooService.getCloseable1(fooParams);
    }

    public void doSomething6() {
        fooService.getCloseable2();
    }

    public void doSomething7() {
        final FooParams fooParams = new FooParams();
        fooParams.setId("THEID");
        fooParams.setName("THENAME");
        fooService.getCloseable3(fooParams);
    }

    public void doSomething8() {
        fooService.getCloseable4();
    }

    public SpecialCloseable doSomething9() {
        final FooParams fooParams = new FooParams();
        fooParams.setId("THEID");
        fooParams.setName("THENAME");
        return fooService.getCloseable1(fooParams);
    }

    public SpecialCloseable doSomething10() {
        return fooService.getCloseable2();
    }

    public Closeable doSomething11() {
        final FooParams fooParams = new FooParams();
        fooParams.setId("THEID");
        fooParams.setName("THENAME");
        return fooService.getCloseable3(fooParams);
    }

    public Closeable doSomething12() {
        return fooService.getCloseable4();
    }
}
