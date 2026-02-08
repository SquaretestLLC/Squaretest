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

import java.util.Optional;

public class MyClass {
    private final FooService fooService;

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    public String getFoo1(final String id) {
        final String mainFoo = fooService.getFooAsString1(id);
        synchronized (this) {
            if(mainFoo == null) {
                return fooService.getFooAsString2(id);
            }
        }
        return mainFoo;
    }
    public String getFoo2(final String id) {
        final String mainFoo = fooService.getFooAsString1(id);
        synchronized (this) {
            if(mainFoo == null) {
                return fooService.getFooAsString2(id);
            }
            return mainFoo;
        }
    }
    public String getFoo3(final String id) {
        final String mainFoo = fooService.getFooAsString1(id);
        synchronized (fooService.getFooAsString3(id)) {
            if(mainFoo == null) {
                return fooService.getFooAsString2(id);
            }
            return mainFoo;
        }
    }
    public String getFoo4(final String id) {
        final String mainFoo = fooService.getFooAsString1(id);
        synchronized (this) {{
            if(mainFoo == null) {
                return fooService.getFooAsString2(id);
            }
            return mainFoo;
        }}
    }
    public String getFoo5(final String id) {
        final String mainFoo = fooService.getFooAsString1(id);
        synchronized (this) {{{
            if(mainFoo == null) {
                return fooService.getFooAsString2(id);
            }
            return mainFoo;
        }}}
    }
    public String getFoo6(final String id) {
        final String mainFoo = fooService.getFooAsString1(id);
        synchronized (fooService.getFooAsString4(id)) {
            if(mainFoo == null) {
                return fooService.getFooAsString2(id);
            }
            return mainFoo;
        }
    }
    public String getFoo7(final String id) {
        final String mainFoo = fooService.getFooAsString1(id);
        Optional<String> lockValue;
        synchronized (lockValue = fooService.getFooAsString4(id)) {
            if(mainFoo == null) {
                return fooService.getFooAsString2(id);
            }
            if(lockValue.isPresent()) {
                return lockValue.get();
            }
            return mainFoo;
        }
    }
    public String getFoo8(final String id) {
        final String mainFoo = fooService.getFooAsString1(id);
        synchronized (fooService.getFooAsString4(id).orElseThrow()) {
            if(mainFoo == null) {
                return fooService.getFooAsString2(id);
            }
            return mainFoo;
        }
    }
}
