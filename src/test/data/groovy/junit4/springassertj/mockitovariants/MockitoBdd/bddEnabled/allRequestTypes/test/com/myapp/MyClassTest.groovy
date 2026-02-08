package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner.class)
@WebMvcTest(MyClass.class)
@CompileStatic
class MyClassTest {

    @Autowired
    private MockMvc mockMvc

    @Test
    void testGreeting() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/getGreeting")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testPostGreeting() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/postGreeting")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testPutGreeting() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(put("/putGreeting")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testDeleteGreeting() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(delete("/deleteGreeting")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testPatchGreeting() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(patch("/patchGreeting")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testOptionsGreeting() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(options("/optionsGreeting")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testHeadGreeting() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(head("/headGreeting")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testTraceGreeting() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(request(HttpMethod.TRACE, "/traceGreeting")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting1() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/getGreeting1")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testPostGreeting1() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/postGreeting1")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testPutGreeting1() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(put("/putGreeting")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testDeleteGreeting1() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(delete("/deleteGreeting")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testPatchGreeting1() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(patch("/patchGreeting")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }
}
