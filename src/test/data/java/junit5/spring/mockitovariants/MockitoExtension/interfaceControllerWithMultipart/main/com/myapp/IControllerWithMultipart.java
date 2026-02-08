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

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import javax.servlet.ServletRequest;
import javax.servlet.http.Part;
import java.util.List;

@Controller
@RequestMapping("/")
public interface IControllerWithMultipart {

    @PostMapping("/storeFile")
    String storeFile(@RequestParam(value="newFile") MultipartFile newFileParam);

    @RequestMapping(value = "/storeFile1", method = RequestMethod.POST)
    String storeFile1(@RequestParam(value="newFile") MultipartFile newFileParam);

    @PostMapping("/storeFile2")
    String storeFile2(@RequestBody final Mono<MultiValueMap<String, Part>> parts);

    // Test the cases where the only indication that the request is multipart is the consumes param.
    // Example: the developer is receiving multiple files via this API and wants to read them from the
    // Servlet request directly instead of going through Spring.
    @PostMapping(value = "/storeFile3", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String storeFile3(final ServletRequest request);

    @PostMapping(value = "/storeFile4", consumes = "multipart/mixed")
    String storeFile4(final ServletRequest request);

    @PostMapping(value = "/storeFile5", consumes = {"application/json", "multipart/mixed", "application/xml"})
    String storeFile5(final ServletRequest request);

    @PostMapping(value = "/storeFile6", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, "application/xml"})
    String storeFile6(final ServletRequest request);

    // This test is based on https://stackoverflow.com/questions/21800726/using-spring-mvc-test-to-unit-test-multipart-post-request/21805186.
    @RequestMapping(value = "/storeDataWithFiles", method = RequestMethod.POST)
    String storeDataWithFiles(
            @RequestParam(value = "formParam1") String theFormParam1,
            @RequestParam(value = "formParam2") String theFormParam2,
            @RequestPart(value = "jsonFile") JsonDto jsonFile,
            @RequestParam(value = "otherFiles", required = false) List<MultipartFile> files) throws Exception;

    @RequestMapping(value = "/storeDataWithFiles0", method = RequestMethod.POST)
    String storeDataWithFiles0(
            @RequestParam(value = "formParam1") String theFormParam1,
            @RequestParam(value = "formParam2") String theFormParam2,
            @RequestPart(value = "jsonFile") JsonDto jsonFile,
            @RequestParam(value = "otherFiles", required = false) MultipartFile[] files) throws Exception;

    // Test cases where the file is in a form object. These cases are similar to https://www.baeldung.com/spring-file-upload.
    @RequestMapping(value = "/storeDataWithFiles1", method = RequestMethod.POST)
    String storeDataWithFiles1(final FormBeanWithFile formBeanWithFile);

    @RequestMapping(value = "/storeDataWithFiles2", method = RequestMethod.POST)
    String storeDataWithFiles2(final FormBeanWithFileList formBeanWithFile);

    @RequestMapping(value = "/storeDataWithFiles3", method = RequestMethod.POST)
    String storeDataWithFiles3(final FormBeanWithFileArray formBeanWithFileArray);

    @RequestMapping(value = "/storeDataWithFiles4", method = RequestMethod.POST)
    String storeDataWithFiles4(@ModelAttribute final FormBeanWithFile formBeanWithFile, ModelMap modelMap);

    @PutMapping("/submitFormWithNestedTypes")
    String submitFormWithNestedTypes(@RequestBody final FormWithNestedObjects form);

    @PutMapping("/submitFormWithNestedTypes1")
    String submitFormWithNestedTypes1(final FormWithNestedObjects form);
}
