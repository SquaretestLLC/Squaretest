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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.Optional;

@Controller
public class MyClass extends BaseController {

    private final BarService barService;
    private final OtherService otherService;

    public MyClass(final BarService barService, final OtherService otherService) {
        super(barService, otherService);
        this.barService = barService;
        this.otherService = otherService;
    }

    @GetMapping("/greeting")
    public String greeting1(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        final Optional<BarBean> mainBar = barService.getMainBar1(0);
        if(mainBar.isPresent()) {
            final Optional<BarBean> barDataOpt = barService.getBarDataById1(1);
            barDataOpt.ifPresent(x -> model.addAttribute("Bar1", x));
        }
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/greeting1")
    public String greeting2(final Locale locale, @RequestParam(name="name", required=false, defaultValue="World") String name, Model model) throws InvalidBarDataIdException {
        final BarBean barBean = loadBarBean1(0);
        model.addAttribute("barBean", barBean);
        return "greeting";
    }

    public BarBean loadBarBean1(final long barId) throws InvalidBarDataIdException {
        final Optional<BarBean> barDataOpt = barService.getBarDataById1(barId);
        if(barDataOpt.isPresent()) {
            final BarBean barData = barDataOpt.get();
            barData.setOtherData(otherService.getOtherData1(barData.getOtherId()));
            return barData;
        }
        return barService.getBarDataById2(barId);
    }
}
