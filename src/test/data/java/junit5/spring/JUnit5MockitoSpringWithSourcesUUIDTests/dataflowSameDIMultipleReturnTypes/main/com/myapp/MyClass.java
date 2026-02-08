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

import org.springframework.web.client.RestTemplate;

import java.util.UUID;

public class MyClass {
    private final RestTemplate restTemplate;

    public MyClass(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getStringValue(final String getStringValueParam) {
        return restTemplate.getForEntity("https://example.com/str/", String.class, getStringValueParam).getBody();
    }

    public UUID getUUIDValue(final String getUUIDValueParam) {
        return restTemplate.getForEntity("https://example.com/uuid/", UUID.class, getUUIDValueParam).getBody();
    }
}
