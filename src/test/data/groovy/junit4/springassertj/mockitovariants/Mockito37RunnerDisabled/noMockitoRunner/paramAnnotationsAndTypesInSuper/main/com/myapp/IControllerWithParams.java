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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface IControllerWithParams {
    @GetMapping("/greeting")
    String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String theName, Model model);

    @GetMapping("/greeting1")
    String greeting1(
            @RequestHeader(name = "name", required = false, defaultValue = "World") String theName, Model model);

    @GetMapping("/greeting2")
    String greeting2(
            @CookieValue(name = "cookieName", required = false, defaultValue = "World") String theName, Model model);

    @GetMapping("/greeting3")
    String greeting3(
            @SessionAttribute(name = "name", required = false) String theName, Model model);

    @GetMapping("/greeting4")
    String greeting4(
            @RequestAttribute(name = "name", required = false) String theName, Model model);

    @PostMapping("submitFormAsBody1")
    String submitFormAsBody1(Mono<HttpEntity<BarBean>> bodyBean);

    @PostMapping("submitFormAsBodyJsonResponse")
    @ResponseBody
    String submitFormAsBodyJsonResponse(Mono<HttpEntity<BarBean>> bodyBean);

    @PostMapping("submitFormAsPojo")
    String submitFormAsPojo(@Valid BarBean bodyBean);

    @PostMapping("submitFormAsPojo")
    String submitFormAsPojoWithCsrf(BarBean bodyBean, CsrfToken token);

    @PostMapping("submitFormAsPojo")
    @ResponseBody
    String submitFormAsPojoWithCsrfAndJson(BarBean bodyBean, CsrfToken token);

    @PostMapping("submitFormAsPojoJsonResponse")
    @ResponseBody
    String submitFormAsPojoJsonResponse(@Valid BarBean bodyBean);

    @PostMapping("submitStringAsBody")
    String submitStringAsBody(@RequestBody String bodyString);

    @PostMapping("submitStringAsBody1")
    String submitStringAsBody1(@RequestBody Mono<HttpEntity<String>> bodyString);

    @PostMapping("submitStringAsBody2")
    String submitStringAsBody2(Mono<HttpEntity<String>> bodyString);

    @PostMapping("submitStringAsBody3")
    String submitStringAsBody3(@RequestBody Mono<String> bodyString);

    @PostMapping("submitStringAsBody4")
    String submitStringAsBody4(@RequestBody HttpEntity<String> bodyString);

    @PostMapping("submitStringAsBody5")
    String submitStringAsBody5(@RequestBody RequestEntity<String> bodyString);

    @PostMapping("submitStringAsBodyWithSession")
    String submitStringAsBodyWithSession(@RequestBody String bodyString, @SessionAttribute String previousValue);

    @PostMapping("submitStringAsBodyWithRequestAttr")
    String submitStringAsBodyWithRequestAttr(@RequestBody String bodyString, @RequestAttribute String previousValue);
}
