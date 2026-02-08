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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MyClass {
    private final Claims claimsDep;
    private final Clock clockDep;
    private final Jwt<Header, String> jwtDep;

    private final FooService fooService;

    public MyClass(Claims claimsDep, Clock clockDep, Jwt<Header, String> jwtDep, FooService fooService) {
        this.claimsDep = claimsDep;
        this.clockDep = clockDep;
        this.jwtDep = jwtDep;
        this.fooService = fooService;
    }

    public String getInfo1(final Claims claimsParam) {
        return claimsParam.getId();
    }

    public String getDefaultInfo() {
        return claimsDep.getId();
    }

    public Date getInfo2() {
        return clockDep.now();
    }

    public String getInfo3(final Jwt<Header, String> input) {
        return input.getBody();
    }

    public String getDefaultInfo2() {
        return jwtDep.getBody();
    }

    public FooData getFooData1(final String id) {
        return fooService.getFoo1(id).getBody();
    }
    public List<FooData> getFooData2(final String id) {
        return fooService.getFoo2(id).getBody();
    }
    public Optional<FooData> getFooData3(final String id) {
        return fooService.getFoo3(id).getBody();
    }
    public Optional<List<FooData>> getFooData4(final String id) {
        return fooService.getFoo4(id).getBody();
    }
}
