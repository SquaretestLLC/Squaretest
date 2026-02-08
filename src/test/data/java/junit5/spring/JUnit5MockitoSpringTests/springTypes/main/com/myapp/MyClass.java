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

import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

public class MyClass {
    private final MultipartFile multipartFileDep;
    private final Environment environmentDep;

    public MyClass(MultipartFile multipartFileDep, Environment environmentDep) {
        this.multipartFileDep = multipartFileDep;
        this.environmentDep = environmentDep;
    }

    public String getInfo1(final MultipartFile multipartFileParam, final Environment environmentParam) {
        return multipartFileParam.getName() + environmentParam.getProperty("PROP1");
    }

    public String getDefaultInfo() {
        return multipartFileDep.getName() + environmentDep.getProperty("PROP1");
    }
}
