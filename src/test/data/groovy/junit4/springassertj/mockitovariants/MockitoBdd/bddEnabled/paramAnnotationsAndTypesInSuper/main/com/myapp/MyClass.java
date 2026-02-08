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
import reactor.core.publisher.Mono;

@Controller
public class MyClass implements IControllerWithParams {

    @Override
    public String greeting(String theName, Model model) {
        model.addAttribute("name", theName);
        return "greeting";
    }

    @Override
    public String greeting1(String theName, Model model) {
        model.addAttribute("name", theName);
        return "greeting";
    }

    @Override
    public String greeting2(String theName, Model model) {
        model.addAttribute("name", theName);
        return "greeting";
    }

    @Override
    public String greeting3(String theName, Model model) {
        model.addAttribute("name", theName);
        return "greeting";
    }

    @Override
    public String greeting4(String theName, Model model) {
        model.addAttribute("name", theName);
        return "greeting";
    }

    @Override
    public String submitFormAsBody1(final Mono<HttpEntity<BarBean>> bodyBean) {
        return "submitFormAsBody1";
    }

    @Override
    public String submitFormAsBodyJsonResponse(final Mono<HttpEntity<BarBean>> bodyBean) {
        return "submitFormAsBody1";
    }

    @Override
    public String submitFormAsPojo(final BarBean bodyBean) {
        return "submitFormAsBody1";
    }

    @Override
    public String submitFormAsPojoWithCsrf(final BarBean bodyBean, final CsrfToken token) {
        return "submitFormAsBody1";
    }

    @Override
    public String submitFormAsPojoWithCsrfAndJson(final BarBean bodyBean, final CsrfToken token) {
        return "submitFormAsBody1";
    }

    @Override
    public String submitFormAsPojoJsonResponse(final BarBean bodyBean) {
        return "submitFormAsBody1";
    }

    @Override
    public String submitStringAsBody(final String bodyString) {
        return "submitStringAsBody";
    }

    @Override
    public String submitStringAsBody1(final Mono<HttpEntity<String>> bodyString) {
        return "submitStringAsBody1";
    }

    @Override
    public String submitStringAsBody2(final Mono<HttpEntity<String>> bodyString) {
        return "submitStringAsBody2";
    }

    @Override
    public String submitStringAsBody3(final Mono<String> bodyString) {
        return "submitStringAsBody3";
    }

    @Override
    public String submitStringAsBody4(final HttpEntity<String> bodyString) {
        return "submitStringAsBody4";
    }

    @Override
    public String submitStringAsBody5(final RequestEntity<String> bodyString) {
        return "submitStringAsBody5";
    }

    @Override
    public String submitStringAsBodyWithSession(final String bodyString, final String previousValue) {
        return "submitStringAsBodyWithSession";
    }

    @Override
    public String submitStringAsBodyWithRequestAttr(final String bodyString, final String previousValue) {
        return "submitStringAsBodyWithRequestAttr";
    }
}
