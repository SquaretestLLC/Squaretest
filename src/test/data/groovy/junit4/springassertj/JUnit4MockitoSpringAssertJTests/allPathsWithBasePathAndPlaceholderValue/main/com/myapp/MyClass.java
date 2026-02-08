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
@RequestMapping("/${baseDeals}/")
public class MyClass {

    private static final String Deals6 = "/deals6";
    private static final String Deals7 = "/deals6";
    private static final String Deals13 = "/deals13";
    private static final String Deals14Quoted = "\"/deals14\"";

    private static final String Deal = "/deals";
    private static final String CompanyNameWithDeal = "${companyName}/deals";
    private static final String EvilPath1 = "/<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>/deals1";
    private static final String EvilPath2 = "/<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>/deals2";
    private static final String EvilPath3 = "/<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>/deals3";

    private final DealsProvider dealsProvider;

    public MyClass(
            final DealsProvider dealsProvider) {
        this.dealsProvider = dealsProvider;
    }

    @GetMapping("/deals1")
    public Deal getDeal1(@RequestParam(name = "dealId") final String dealId) {
        return dealsProvider.getDealForId(dealId);
    }

    @GetMapping
    public Deal getDeal2(@RequestParam(name = "dealId") final String dealId) {
        return dealsProvider.getDealForId(dealId);
    }

    @GetMapping()
    public Deal getDeal3(@RequestParam(name = "dealId") final String dealId) {
        return dealsProvider.getDealForId(dealId);
    }

    @GetMapping("/${companyName}/deals")
    public Deal getDeal4(@RequestParam(name = "dealId") final String dealId) {
        return dealsProvider.getDealForId(dealId);
    }

    @GetMapping(value="/deals2")
    public Deal getDeal5(@RequestParam(name = "dealId") final String dealId) {
        return dealsProvider.getDealForId(dealId);
    }

    @GetMapping(value={"/deals3"})
    public Deal getDeal6(@RequestParam(name = "dealId") final String dealId) {
        return dealsProvider.getDealForId(dealId);
    }

    @GetMapping(value={"/deals3", "/deals4"})
    public Deal getDeal7(@RequestParam(name = "dealId") final String dealId) {
        return dealsProvider.getDealForId(dealId);
    }

    @GetMapping(value={Deals6, Deals7})
    public Deal getDeal8(@RequestParam(name = "dealId") final String dealId) {
        return dealsProvider.getDealForId(dealId);
    }

    @GetMapping(value={Deal + 8, Deal + 9})
    public Deal getDeal9(@RequestParam(name = "dealId") final String dealId) {
        return dealsProvider.getDealForId(dealId);
    }

    @GetMapping(value={CompanyNameWithDeal + 10, CompanyNameWithDeal + 11})
    public Deal getDeal10(@RequestParam(name = "dealId") final String dealId) {
        return dealsProvider.getDealForId(dealId);
    }

    @GetMapping(value={})
    public Deal getDeal11(@RequestParam(name = "dealId") final String dealId) {
        return dealsProvider.getDealForId(dealId);
    }

    @GetMapping(Deals13)
    public Deal getDeal12(@RequestParam(name = "dealId") final String dealId) {
        return dealsProvider.getDealForId(dealId);
    }

    @GetMapping(Deals14Quoted)
    public Deal getDeal13(@RequestParam(name = "dealId") final String dealId) {
        return dealsProvider.getDealForId(dealId);
    }

    @GetMapping(EvilPath1)
    public Deal getDeal14(@RequestParam(name = "dealId") final String dealId) {
        return dealsProvider.getDealForId(dealId);
    }

    @GetMapping("/<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>/deals4")
    public Deal getDeal15(@RequestParam(name = "dealId") final String dealId) {
        return dealsProvider.getDealForId(dealId);
    }

    @GetMapping({EvilPath2, EvilPath3})
    public Deal getDeal16(@RequestParam(name = "dealId") final String dealId) {
        return dealsProvider.getDealForId(dealId);
    }

    @GetMapping({"/<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>/deals5", "/<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>/deals6"})
    public Deal getDeal17(@RequestParam(name = "dealId") final String dealId) {
        return dealsProvider.getDealForId(dealId);
    }
}
