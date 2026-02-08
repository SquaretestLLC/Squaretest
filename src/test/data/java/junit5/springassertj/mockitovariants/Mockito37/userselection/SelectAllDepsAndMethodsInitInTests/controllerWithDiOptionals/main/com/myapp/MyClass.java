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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MyClass {

    private final FooService fooService;

    @Autowired
    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    /**
     * Squaretest should create alt-flow test where fooService.safeGetFooDataById returns null.
     * The call to fooService.saveFoo(data) should have a verify statement.
     * Also, the method name should be included in the alt-flow test.
     */
    @GetMapping("/getFooById")
    public String getFooById(@RequestParam(name = "fooId", required = false, defaultValue = "World") long theFooId, Model model) {
        final FooData data = fooService.safeGetFooDataById(theFooId);
        data.setLastAccessDate(LocalDateTime.now());
        fooService.saveFoo(data);
        return "indexWithNoPath";
    }

    @GetMapping("/getFooByIdOpt")
    public String getFooByIdOpt(@RequestParam(name = "fooId", required = false, defaultValue = "World") long theFooId, Model model) {
        final Optional<FooData> data = fooService.getFooDataByIdOpt(theFooId);
        data.ifPresent(x -> {
            x.setLastAccessDate(LocalDateTime.now());
            fooService.saveFoo(x);
        });
        return "indexWithNoPath";
    }

    @GetMapping("/getFooByIdOpt1")
    public String getFooByIdOpt1(@RequestParam(name = "fooId", required = false, defaultValue = "World") long theFooId, Model model) {
        final Optional<FooData> data = fooService.getFooDataByIdOpt(theFooId);
        data.ifPresent(x -> x.setLastAccessDate(LocalDateTime.now()));
        data.ifPresent(fooService::saveFoo1);
        return "indexWithNoPath";
    }
}
