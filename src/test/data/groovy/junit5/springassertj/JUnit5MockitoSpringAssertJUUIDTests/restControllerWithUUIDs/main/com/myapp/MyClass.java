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

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/deals", produces = MediaType.APPLICATION_JSON_VALUE)
public class MyClass {

    private final DealsProvider dealsProvider;

    public MyClass(
            final DealsProvider dealsProvider) {
        this.dealsProvider = dealsProvider;
    }

    @GetMapping("/getDeal")
    public Deal getDeal(@RequestParam(name = "dealId") final UUID dealId) {
        return dealsProvider.getDealForId(dealId);
    }
}
