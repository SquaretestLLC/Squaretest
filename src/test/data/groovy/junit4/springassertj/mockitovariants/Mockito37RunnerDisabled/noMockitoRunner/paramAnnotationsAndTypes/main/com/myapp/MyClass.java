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

import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Controller
public class MyClass {

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String theName, Model model) {
        model.addAttribute("name", theName);
        return "greeting";
    }

    @GetMapping("/greeting1")
    public String greeting1(
            @RequestHeader(name="name", required=false, defaultValue="World") String theName, Model model) {
        model.addAttribute("name", theName);
        return "greeting";
    }

    @GetMapping("/greeting2")
    public String greeting2(
            @CookieValue(name="cookieName", required=false, defaultValue="World") String theName, Model model) {
        model.addAttribute("name", theName);
        return "greeting";
    }

    @GetMapping("/greeting3")
    public String greeting3(
            @SessionAttribute(name="name", required=false) String theName, Model model) {
        model.addAttribute("name", theName);
        return "greeting";
    }

    @GetMapping("/greeting4")
    public String greeting4(
            @RequestAttribute(name="name", required=false) String theName, Model model) {
        model.addAttribute("name", theName);
        return "greeting";
    }

    @PostMapping("submitFormAsBody1")
    public String submitFormAsBody1(final Mono<HttpEntity<BarBean>> bodyBean) {
        return "submitFormAsBody1";
    }

    @PostMapping("submitFormAsBodyJsonResponse")
    @ResponseBody
    public String submitFormAsBodyJsonResponse(final Mono<HttpEntity<BarBean>> bodyBean) {
        return "submitFormAsBody1";
    }

    @PostMapping("submitFormAsPojo")
    public String submitFormAsPojo(@Valid final BarBean bodyBean) {
        return "submitFormAsBody1";
    }

    @PostMapping("submitFormAsPojo")
    public String submitFormAsPojoWithCsrf(final BarBean bodyBean, final CsrfToken token) {
        return "submitFormAsBody1";
    }

    @PostMapping("submitFormAsPojo")
    @ResponseBody
    public String submitFormAsPojoWithCsrfAndJson(final BarBean bodyBean, final CsrfToken token) {
        return "submitFormAsBody1";
    }

    @PostMapping("submitFormAsPojoJsonResponse")
    @ResponseBody
    public String submitFormAsPojoJsonResponse(@Valid final BarBean bodyBean) {
        return "submitFormAsBody1";
    }

    @PostMapping("submitStringAsBody")
    public String submitStringAsBody(@RequestBody final String bodyString) {
        return "submitStringAsBody";
    }

    @PostMapping("submitStringAsBody1")
    public String submitStringAsBody1(@RequestBody final Mono<HttpEntity<String>> bodyString) {
        return "submitStringAsBody1";
    }

    @PostMapping("submitStringAsBody2")
    public String submitStringAsBody2(final Mono<HttpEntity<String>> bodyString) {
        return "submitStringAsBody2";
    }

    @PostMapping("submitStringAsBody3")
    public String submitStringAsBody3(@RequestBody final Mono<String> bodyString) {
        return "submitStringAsBody3";
    }

    @PostMapping("submitStringAsBody4")
    public String submitStringAsBody4(@RequestBody final HttpEntity<String> bodyString) {
        return "submitStringAsBody4";
    }

    @PostMapping("submitStringAsBody5")
    public String submitStringAsBody5(@RequestBody final RequestEntity<String> bodyString) {
        return "submitStringAsBody5";
    }

    @PostMapping("submitStringAsBodyWithSession")
    public String submitStringAsBodyWithSession(@RequestBody final String bodyString, @SessionAttribute final String previousValue) {
        return "submitStringAsBodyWithSession";
    }

    @PostMapping("submitStringAsBodyWithRequestAttr")
    public String submitStringAsBodyWithRequestAttr(@RequestBody final String bodyString, @RequestAttribute final String previousValue) {
        return "submitStringAsBodyWithRequestAttr";
    }
}
