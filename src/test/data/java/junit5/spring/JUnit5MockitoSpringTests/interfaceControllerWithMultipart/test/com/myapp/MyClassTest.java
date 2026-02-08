package com.myapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyClass.class)
class MyClassTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testStoreFile() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(multipart("/storeFile")
                        .file(new MockMultipartFile("newFile", "originalFilename", MediaType.APPLICATION_JSON_VALUE,
                                "content".getBytes()))
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testStoreFile1() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(multipart("/storeFile1")
                        .file(new MockMultipartFile("newFile", "originalFilename", MediaType.APPLICATION_JSON_VALUE,
                                "content".getBytes()))
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testStoreFile2() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(multipart("/storeFile2")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testStoreFile3() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(multipart("/storeFile3")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testStoreFile4() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(multipart("/storeFile4")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testStoreFile5() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(multipart("/storeFile5")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testStoreFile6() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(multipart("/storeFile6")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testStoreDataWithFiles() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(multipart("/storeDataWithFiles")
                        .file(new MockMultipartFile("jsonFile", "originalFilename", MediaType.APPLICATION_JSON_VALUE,
                                "content".getBytes()))
                        .file(new MockMultipartFile("otherFiles", "originalFilename", MediaType.APPLICATION_JSON_VALUE,
                                "content".getBytes()))
                        .param("formParam1", "theFormParam1")
                        .param("formParam2", "theFormParam2")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testStoreDataWithFiles_ThrowsException() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(multipart("/storeDataWithFiles")
                        .file(new MockMultipartFile("jsonFile", "originalFilename", MediaType.APPLICATION_JSON_VALUE,
                                "content".getBytes()))
                        .file(new MockMultipartFile("otherFiles", "originalFilename", MediaType.APPLICATION_JSON_VALUE,
                                "content".getBytes()))
                        .param("formParam1", "theFormParam1")
                        .param("formParam2", "theFormParam2")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testStoreDataWithFiles0() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(multipart("/storeDataWithFiles0")
                        .file(new MockMultipartFile("jsonFile", "originalFilename", MediaType.APPLICATION_JSON_VALUE,
                                "content".getBytes()))
                        .file(new MockMultipartFile("otherFiles", "originalFilename", MediaType.APPLICATION_JSON_VALUE,
                                "content".getBytes()))
                        .param("formParam1", "theFormParam1")
                        .param("formParam2", "theFormParam2")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testStoreDataWithFiles0_ThrowsException() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(multipart("/storeDataWithFiles0")
                        .file(new MockMultipartFile("jsonFile", "originalFilename", MediaType.APPLICATION_JSON_VALUE,
                                "content".getBytes()))
                        .file(new MockMultipartFile("otherFiles", "originalFilename", MediaType.APPLICATION_JSON_VALUE,
                                "content".getBytes()))
                        .param("formParam1", "theFormParam1")
                        .param("formParam2", "theFormParam2")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testStoreDataWithFiles1() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(multipart("/storeDataWithFiles1")
                        .file(new MockMultipartFile("multipartFile", "originalFilename", MediaType.APPLICATION_JSON_VALUE,
                                "content".getBytes()))
                        .param("name", "name")
                        .param("displayName", "displayName")
                        .param("id", "0")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testStoreDataWithFiles2() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(multipart("/storeDataWithFiles2")
                        .file(new MockMultipartFile("multipartFile", "originalFilename", MediaType.APPLICATION_JSON_VALUE,
                                "content".getBytes()))
                        .param("id", "0")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testStoreDataWithFiles3() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(multipart("/storeDataWithFiles3")
                        .file(new MockMultipartFile("multipartFile", "originalFilename", MediaType.APPLICATION_JSON_VALUE,
                                "content".getBytes()))
                        .param("id", "0")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testStoreDataWithFiles4() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(multipart("/storeDataWithFiles4")
                        .file(new MockMultipartFile("multipartFile", "originalFilename", MediaType.APPLICATION_JSON_VALUE,
                                "content".getBytes()))
                        .param("name", "name")
                        .param("displayName", "displayName")
                        .param("id", "0")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testSubmitFormWithNestedTypes() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(put("/submitFormWithNestedTypes")
                        .param("formObj1_name", "name")
                        .param("formObj1_data_id", "0")
                        .param("formObj1_data_level3Data_someValue", "someValue")
                        .param("formObj1_data_level3Data_level4Data_id", "0")
                        .param("formObj1_data_level3Data_level4Data_formObj1", "formObj1")
                        .param("formObj1_data_level3Data_level4Data_object2s", "value1", "value2")
                        .param("formObj1_data_level3Data_level4Data_otherObj4", "otherObj4")
                        .param("formObj1_data_level3Data_level4Data_object4s", "value1", "value2")
                        .param("formObj1_data_level3Data_level4Data_object4sInArray", "value1", "value2")
                        .param("formObj1_data_level3Data_level4Data_otherObj3s", "value1", "value2")
                        .param("names", "value1", "value2")
                        .param("keys", "value1", "value2")
                        .param("mapOfThings", "mapOfThings")
                        .param("optionalFormData", "optionalFormData")
                        .param("locale", "locale")
                        .param("simpleForms0.id", "0")
                        .param("simpleForms0.name", "name")
                        .param("simpleForms0.shortDisplayName", "shortDisplayName")
                        .param("simpleForms0.startDate", "startDate")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testSubmitFormWithNestedTypes1() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(put("/submitFormWithNestedTypes1")
                        .param("formObj1_name", "name")
                        .param("formObj1_data_id", "0")
                        .param("formObj1_data_level3Data_someValue", "someValue")
                        .param("formObj1_data_level3Data_level4Data_id", "0")
                        .param("formObj1_data_level3Data_level4Data_formObj1", "formObj1")
                        .param("formObj1_data_level3Data_level4Data_object2s", "value1", "value2")
                        .param("formObj1_data_level3Data_level4Data_otherObj4", "otherObj4")
                        .param("formObj1_data_level3Data_level4Data_object4s", "value1", "value2")
                        .param("formObj1_data_level3Data_level4Data_object4sInArray", "value1", "value2")
                        .param("formObj1_data_level3Data_level4Data_otherObj3s", "value1", "value2")
                        .param("names", "value1", "value2")
                        .param("keys", "value1", "value2")
                        .param("mapOfThings", "mapOfThings")
                        .param("optionalFormData", "optionalFormData")
                        .param("locale", "locale")
                        .param("simpleForms0.id", "0")
                        .param("simpleForms0.name", "name")
                        .param("simpleForms0.shortDisplayName", "shortDisplayName")
                        .param("simpleForms0.startDate", "startDate")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }
}
