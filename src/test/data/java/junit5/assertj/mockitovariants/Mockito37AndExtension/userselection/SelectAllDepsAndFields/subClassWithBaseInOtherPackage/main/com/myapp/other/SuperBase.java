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
package com.myapp.other;

import com.myapp.FooService;

import java.util.List;

public class SuperBase {
    private final String bucketName;
    private final FooService fooService;

    public SuperBase(final String bucketName, final FooService fooService) {
        this.bucketName = bucketName;
        this.fooService = fooService;
    }

    protected List<String> getItems(final String criteria, final String otherData) {
        return fooService.getItems(bucketName, criteria, otherData);
    }

    List<String> getItems(final String criteria, final String otherData, final String thirdData) {
        return fooService.getItems(bucketName, criteria, otherData, thirdData);
    }

    public List<String> getItems(final String criteria, final String otherData, final String thirdData, final String fourthData) {
        return fooService.getItems(bucketName, criteria, otherData, thirdData, fourthData);
    }
}
