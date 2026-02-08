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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyClass {
    private final FooService fooService;

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    @GetMapping("/searchFoos1/{fooTypeParamName}")
    public List<FooData> getFoos1(@PathVariable("fooTypeParamName") final FooType fooType) {
        return fooService.getFoos1(fooType);
    }

    @GetMapping("/searchFoos2")
    public List<FooData> getFoos2(@RequestParam("fooTypeRequestParamName") final FooType fooType) {
        return fooService.getFoos2(fooType);
    }
    @GetMapping("/searchFoos3/{fooTypeParamName}")
    public List<FooData2> getFoos3(@PathVariable("fooTypeParamName") final FooData2.FooType fooType) {
        return fooService.getFoos3(fooType);
    }

    @GetMapping("/searchFoos4")
    public List<FooData2> getFoos4(@RequestParam("fooTypeRequestParamName") final FooData2.FooType fooType) {
        return fooService.getFoos4(fooType);
    }
}
