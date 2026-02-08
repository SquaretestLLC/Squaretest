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

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class MyClass {

    private static final String PathDateParam = "pathDateName";
    public static final String RequestParam = "requestVarDateName";
    private final FooService fooService;

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    @GetMapping("getNextEvent1/{pathDateName}")
    public StringResponse getNextEvent1(
            @PathVariable(PathDateParam) @DateTimeFormat final LocalDateTime pathDate,
            @RequestParam(RequestParam) @DateTimeFormat final LocalDateTime requestVarDate) {
        return new StringResponse(pathDate.toString() + requestVarDate.toString());
    }

    @GetMapping("getNextEvent2/{pathDateName}")
    public StringResponse getNextEvent2(
            @PathVariable(value = PathDateParam) @DateTimeFormat final String pathDate,
            @RequestParam(value = RequestParam) @DateTimeFormat final String requestVarDate) {
        return new StringResponse(pathDate.toString() + requestVarDate.toString());
    }

    @GetMapping("getNextEvent3/{pathDateName}")
    public StringResponse getNextEvent3(
            @PathVariable(name = PathDateParam) final LocalDateTime pathDate,
            @RequestParam(name = RequestParam) final LocalDateTime requestVarDate) {
        return new StringResponse(pathDate.toString() + requestVarDate.toString());
    }

    @GetMapping("getNextEvent4/{pathDate}")
    public StringResponse getNextEvent4(
            @PathVariable final LocalDate pathDate,
            @RequestParam final LocalDate requestVarDate) {
        return new StringResponse(pathDate.toString() + requestVarDate.toString());
    }

    @GetMapping("getNextEvent5/{pathDate}")
    public StringResponse getNextEvent5(
            @PathVariable @DateTimeFormat final String pathDate,
            @RequestParam @DateTimeFormat final String requestVarDate) {
        final String ret = fooService.getTheDate(pathDate, requestVarDate);
        return new StringResponse(ret);
    }

    @GetMapping("getNextEvent6/{pathDateName}")
    public StringResponse getNextEvent6(
            @PathVariable(PathDateParam) final Date pathDate,
            @RequestParam(RequestParam) final Date requestVarDate) {
        return new StringResponse(pathDate.toString() + requestVarDate.toString());
    }
}
