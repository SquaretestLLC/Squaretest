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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class MyClass extends BaseController {

    private final DealsProvider dealsProvider;

    public MyClass(final DealsProvider dealsProvider) {
        super(dealsProvider);
        this.dealsProvider = dealsProvider;
    }

    @Override
    @GetMapping("/deals1")
    public Deal getDeal1(@RequestParam(name = "dealId") final String dealId) {
        return dealsProvider.getDealForId1(dealId);
    }
}
