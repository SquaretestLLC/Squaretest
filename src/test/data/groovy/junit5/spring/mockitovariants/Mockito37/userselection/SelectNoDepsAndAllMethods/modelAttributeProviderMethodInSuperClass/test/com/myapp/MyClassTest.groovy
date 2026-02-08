package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(MyClass.class)
@CompileStatic
class MyClassTest {

    @Autowired
    private MockMvc mockMvc

    @Test
    void testGreeting() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/greeting")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting1() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/greeting1")
                .locale(Locale.US)
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting2() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/greeting2")
                .locale(Locale.US)
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting2_ThrowsInvalidBarDataIdException() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/greeting2")
                .locale(Locale.US)
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetAll() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/greetAll")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetAll_ThrowsIOException() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/greetAll")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"))
    }
}
