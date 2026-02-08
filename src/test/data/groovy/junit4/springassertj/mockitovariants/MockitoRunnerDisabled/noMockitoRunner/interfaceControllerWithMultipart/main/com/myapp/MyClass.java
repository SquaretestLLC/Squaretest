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

import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import javax.servlet.ServletRequest;
import javax.servlet.http.Part;
import java.util.List;

public class MyClass implements IControllerWithMultipart {
    @Override
    public String storeFile(MultipartFile newFileParam) {
        return null;
    }

    @Override
    public String storeFile1(MultipartFile newFileParam) {
        return null;
    }

    @Override
    public String storeFile2(Mono<MultiValueMap<String, Part>> parts) {
        return null;
    }

    @Override
    public String storeFile3(ServletRequest request) {
        return null;
    }

    @Override
    public String storeFile4(ServletRequest request) {
        return null;
    }

    @Override
    public String storeFile5(ServletRequest request) {
        return null;
    }

    @Override
    public String storeFile6(ServletRequest request) {
        return null;
    }

    @Override
    public String storeDataWithFiles(String theFormParam1, String theFormParam2, JsonDto jsonFile, List<MultipartFile> files) throws Exception {
        return null;
    }

    @Override
    public String storeDataWithFiles0(String theFormParam1, String theFormParam2, JsonDto jsonFile, MultipartFile[] files) throws Exception {
        return null;
    }

    @Override
    public String storeDataWithFiles1(FormBeanWithFile formBeanWithFile) {
        return null;
    }

    @Override
    public String storeDataWithFiles2(FormBeanWithFileList formBeanWithFile) {
        return null;
    }

    @Override
    public String storeDataWithFiles3(FormBeanWithFileArray formBeanWithFileArray) {
        return null;
    }

    @Override
    public String storeDataWithFiles4(FormBeanWithFile formBeanWithFile, ModelMap modelMap) {
        return null;
    }

    @Override
    public String submitFormWithNestedTypes(FormWithNestedObjects form) {
        return null;
    }

    @Override
    public String submitFormWithNestedTypes1(FormWithNestedObjects form) {
        return null;
    }
}
