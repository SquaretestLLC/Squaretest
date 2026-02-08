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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MyClass {

    private final ObjectMapper objectMapper;

    @Autowired
    public MyClass(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/storeFile")
    public String storeFile(@RequestParam(value="newFile") MultipartFile newFileParam) {
        return "storeFileConfirmation";
    }

    @RequestMapping(value = "/storeFile1", method = RequestMethod.POST)
    public String storeFile1(@RequestParam(value="newFile") MultipartFile newFileParam) {
        return "storeFile1Confirmation";
    }

    @PostMapping("/storeFile2")
    public String storeFile2(@RequestBody final Mono<MultiValueMap<String, Part>> parts) {
        return "storeFile2Confirmation";
    }

    // Test the cases where the only indication that the request is multipart is the consumes param.
    // Example: the developer is receiving multiple files via this API and wants to read them from the
    // Servlet request directly instead of going through Spring.
    @PostMapping(value = "/storeFile3", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String storeFile3(final ServletRequest request) {
        return "storeFile3Confirmation";
    }

    @PostMapping(value = "/storeFile4", consumes = "multipart/mixed")
    public String storeFile4(final ServletRequest request) {
        return "storeFile4Confirmation";
    }

    @PostMapping(value = "/storeFile5", consumes = {"application/json", "multipart/mixed", "application/xml"})
    public String storeFile5(final ServletRequest request) {
        return "storeFile5Confirmation";
    }

    @PostMapping(value = "/storeFile6", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, "application/xml"})
    public String storeFile6(final ServletRequest request) {
        return "storeFile6Confirmation";
    }

    // This test is based on https://stackoverflow.com/questions/21800726/using-spring-mvc-test-to-unit-test-multipart-post-request/21805186.
    @RequestMapping(value = "/storeDataWithFiles", method = RequestMethod.POST)
    public String storeDataWithFiles(
            @RequestParam(value = "formParam1") String theFormParam1,
            @RequestParam(value = "formParam2") String theFormParam2,
            @RequestPart(value = "jsonFile") JsonDto jsonFile,
            @RequestParam(value = "otherFiles", required = false) List<MultipartFile> files) throws Exception {
        System.out.println(theFormParam1);
        System.out.println(theFormParam2);
        System.out.println(objectMapper.writeValueAsString(jsonFile));
        for (MultipartFile file : files) {
            System.out.println(file.getOriginalFilename());
        }
        return "storeDataWithFilesConfirmation";
    }

    @RequestMapping(value = "/storeDataWithFiles0", method = RequestMethod.POST)
    public String storeDataWithFiles0(
            @RequestParam(value = "formParam1") String theFormParam1,
            @RequestParam(value = "formParam2") String theFormParam2,
            @RequestPart(value = "jsonFile") JsonDto jsonFile,
            @RequestParam(value = "otherFiles", required = false) MultipartFile[] files) throws Exception {
        System.out.println(theFormParam1);
        System.out.println(theFormParam2);
        System.out.println(objectMapper.writeValueAsString(jsonFile));
        for (MultipartFile file : files) {
            System.out.println(file.getOriginalFilename());
        }
        return "storeDataWithFilesConfirmation";
    }

    // Test cases where the file is in a form object. These cases are similar to https://www.baeldung.com/spring-file-upload.
    @RequestMapping(value = "/storeDataWithFiles1", method = RequestMethod.POST)
    public String storeDataWithFiles1(final FormBeanWithFile formBeanWithFile) {
        return "storeDataWithFiles1Confirmation1";
    }

    @RequestMapping(value = "/storeDataWithFiles2", method = RequestMethod.POST)
    public String storeDataWithFiles2(final FormBeanWithFileList formBeanWithFile) {
        return "storeDataWithFiles1Confirmation2";
    }

    @RequestMapping(value = "/storeDataWithFiles3", method = RequestMethod.POST)
    public String storeDataWithFiles3(final FormBeanWithFileArray formBeanWithFileArray) {
        return "storeDataWithFiles1Confirmation3";
    }

    @RequestMapping(value = "/storeDataWithFiles4", method = RequestMethod.POST)
    public String storeDataWithFiles4(@ModelAttribute final FormBeanWithFile formBeanWithFile, ModelMap modelMap) {
        return "storeDataWithFiles4Confirmation1";
    }

    @PutMapping("/submitFormWithNestedTypes")
    public String submitFormWithNestedTypes(@RequestBody final FormWithNestedObjects form) {
        return "submitFormWithNestedTypesConfirmation";
    }

    @PutMapping("/submitFormWithNestedTypes1")
    public String submitFormWithNestedTypes1(final FormWithNestedObjects form) {
        return "submitFormWithNestedTypesConfirmation1";
    }
}
