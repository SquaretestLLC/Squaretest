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
package com.myapp.bases;

import java.io.IOException;

public class BaseClass implements IBase, IOtherBase {

    protected final FooService fooService;

    protected BaseClass(final FooService fooService) {
        this.fooService = fooService;
    }

    @Override
    public String getFoo(final String key) {
        try {
            return fooService.getData(key);
        } catch (IOException e) {
            return null;
        }
    }

//    @Override
//    public String getUpperFoo(final String key) {
//        return IBase.super.getUpperFoo(key);
//    }
//
//    @Override
//    public String getUpperFoo1(final String key) {
//        return IOtherBase.super.getUpperFoo1(key);
//    }
}
