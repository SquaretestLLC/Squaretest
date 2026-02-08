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

import com.myapp.lists.*;

public class MyClass {

    private final FooService fooService;

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    public ListWithDelegateArgConstructor<String> makeListWithDelegateArgConstructor(final String key) {
        return fooService.makeListWithDelegateArgConstructor(key);
    }

    public ListWithMultipleConstructorsIncludingNoArgs<String> makeListWithMultipleConstructorsIncludingNoArgs(final String key) {
        return fooService.makeListWithMultipleConstructorsIncludingNoArgs(key);
    }

    public ListWithNoConstructor<String> makeListWithNoConstructor(final String key) {
        return fooService.makeListWithNoConstructor(key);
    }

    public ListWithOnlyNoArgsConstructor<String> makeListWithOnlyNoArgsConstructor(final String key) {
        return fooService.makeListWithOnlyNoArgsConstructor(key);
    }

    public ListWithEmptyStaticCreatorMethod<String> makeListWithEmptyStaticCreatorMethod(final String key) {
        return fooService.makeListWithEmptyStaticCreatorMethod(key);
    }
}
