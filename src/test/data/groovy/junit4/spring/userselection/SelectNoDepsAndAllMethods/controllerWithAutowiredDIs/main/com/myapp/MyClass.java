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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Locale;

@Controller
public class MyClass {

    @Autowired
    private BarService barService;
    @Autowired
    private MetricsServiceAdapter metricsServiceAdapter;

    @PostMapping("/greeting")
    public String greeting(final BarBean barBeanFromModel, @RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @PostMapping("/greeting1")
    public String greeting1(final Locale locale, @ModelAttribute final BarBean barBeanFromModel, @RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        barService.saveBar(barBeanFromModel);
        metricsServiceAdapter.recordMetric("greeting1");
        return "greeting";
    }

    @PostMapping("/greeting2")
    public String greeting2(final Locale locale, final BarBean barBeanFromModel, @RequestParam(name="name", required=false, defaultValue="World") String name, Model model) throws InvalidBarDataIdException {
        model.addAttribute("name", name);
        barService.saveBar(barBeanFromModel);
        // This should be ignored:
        barService.getBarDataById(0);
        metricsServiceAdapter.recordMetric("greeting1");
        return "greeting";
    }

    @PostMapping("/greetAll")
    public String greetAll(final BarBean barBeanFromModel, @RequestParam(name="name", required=false, defaultValue="World") String name, Model model) throws IOException {
        model.addAttribute("name", name);
        model.addAttribute("bars", barService.getAllBars());
        metricsServiceAdapter.recordMetric("greetAll");
        return "greeting";
    }

    public void helperToIgnore() throws InvalidBarDataIdException, NetworkException {
        barService.deleteBarWithId(0);
    }
}
