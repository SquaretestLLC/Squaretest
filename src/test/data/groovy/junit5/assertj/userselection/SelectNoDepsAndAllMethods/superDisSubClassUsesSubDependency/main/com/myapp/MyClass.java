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

import com.myapp.bases.BaseClass;
import com.myapp.bases.SubFooService;

import java.io.IOException;

public class MyClass extends BaseClass {

    private final SubFooService subFooService;

    public MyClass(final SubFooService subFooService) {
        super(subFooService);
        this.subFooService = subFooService;
    }

    /**
     * Both this method and the super method call {@link com.myapp.bases.FooService#getData(String)}.
     * The {@link SubFooService#getData(String)} and {@link com.myapp.bases.FooService#getData(String)} methods are
     * different PsiMethods. Squaretest should only generate one when(...) statement form them.
     */
    @Override
    public String getFoo(final String key) {
        try {
            return subFooService.getData(key) +  super.getFoo(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Squaretest should generate alt-flow tests for both FooService.getOtherData(...) and FooService.doSomething(..).
     * It will still generate when statements for FooService.doSomething(...) that comes after FooService.getOtherData(..)
     * throws an exception. This is a bug. Fixing it is fairly complicated, so I'm going to leave it for now.
     *
     * Also, the bug only happens when:
     *  1. the SourceClass constructor stores a pointer to a base class constructor parameter.
     *  2. The SourceClass and BaseClass use different names for the field the parameter is stored in; e.g.
     *     this class uses: subFooService while the BaseClass uses fooService.
     */
    @Override
    public String getFoo2(final String key) {
        return super.getFoo2(key) + subFooService.doSomething(key);
    }
}


