package com.myapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyClass.class)
class MyClassTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGreeting() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/getGreeting")
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testPostGreeting() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/postGreeting")
                        .param("name", "name")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testPutGreeting() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(put("/putGreeting")
                        .param("name", "name")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testDeleteGreeting() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(delete("/deleteGreeting")
                        .param("name", "name")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testPatchGreeting() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(patch("/patchGreeting")
                        .param("name", "name")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testOptionsGreeting() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(options("/optionsGreeting")
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testHeadGreeting() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(head("/headGreeting")
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testTraceGreeting() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(request(HttpMethod.TRACE, "/traceGreeting")
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreeting1() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/getGreeting1")
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testPostGreeting1() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/postGreeting1")
                        .param("name", "name")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testPutGreeting1() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(put("/putGreeting")
                        .param("name", "name")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testDeleteGreeting1() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(delete("/deleteGreeting")
                        .param("name", "name")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testPatchGreeting1() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(patch("/patchGreeting")
                        .param("name", "name")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }
}
