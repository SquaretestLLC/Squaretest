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

    public static final String ConstantValue = null;

    private final FooService fooService;

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    public String getFoo(final String fooId) {
        return extractValueWeCareAbout(fooService.getFoo(fooId));
    }

    public String getValue1() {
        return fooService.getValue1(ConstantValue);
    }

    public String getValue2() {
        return fooService.getValue2(null);
    }

    private String extractValueWeCareAbout(final FooDTO foo) {
        if(foo != null) {
            final SubFoo1 subFoo1 = foo.getSubFoo1();
            if(subFoo1 != null) {
                final SubFoo2 subFoo2 = subFoo1.getSubFoo2();
                if(subFoo2 != null) {
                    return subFoo2.getSubFoo2ValueWeCareAbout();
                }
            }
        }
        return null;
    }

}
