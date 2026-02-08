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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MyClass {
    private final RestTemplate restTemplate;
    private final FooService fooService;

    public MyClass(RestTemplate restTemplate, FooService fooService) {
        this.restTemplate = restTemplate;
        this.fooService = fooService;
    }
    public ResponseEntity<String> getFooData() {
        return restTemplate.exchange("https://example.org", HttpMethod.GET, new HttpEntity<>("default"), String.class);
    }
    public ResponseEntity<String> putFooData(final String input) {
        return restTemplate.exchange("https://example.org", HttpMethod.PUT, new HttpEntity<>(input), String.class, "params");
    }
    public ResponseEntity<String> putFooDataOther(final String input) {
        return restTemplate.exchange("https://example.org", HttpMethod.PUT, new HttpEntity<>(input), String.class, new FooData());
    }
    public void doSomething() {
        // Ensure we override the default value (OK) and use CREATED.
        fooService.doSomethingWithCode(HttpStatus.CREATED);
    }
    public void doSomethingElse() {
        fooService.doSomethingElseWithCode();
    }
    public void doSomethingElse1() {
        fooService.doSomethingElseWithCode(HttpStatus.ACCEPTED);
    }
}
