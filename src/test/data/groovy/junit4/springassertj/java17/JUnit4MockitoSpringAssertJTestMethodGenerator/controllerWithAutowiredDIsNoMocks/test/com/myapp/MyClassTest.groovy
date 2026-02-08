package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner.class)
@WebMvcTest(MyClass.class)
@CompileStatic
class MyClassTest {

    @Autowired
    private MockMvc mockMvc

    @Test
    void testGreeting3() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/greeting")
                .param("id", "0")
                .param("name", "name")
                .param("shortDisplayName", "shortDisplayName")
                .param("startDate", "startDate")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting11() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/greeting1")
                .locale(Locale.US)
                .param("barBeanFromModel", "barBeanFromModel")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting21() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/greeting2")
                .locale(Locale.US)
                .param("id", "0")
                .param("name", "name")
                .param("shortDisplayName", "shortDisplayName")
                .param("startDate", "startDate")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting2_ThrowsInvalidBarDataIdException1() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/greeting2")
                .locale(Locale.US)
                .param("id", "0")
                .param("name", "name")
                .param("shortDisplayName", "shortDisplayName")
                .param("startDate", "startDate")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetAll1() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/greetAll")
                .param("id", "0")
                .param("name", "name")
                .param("shortDisplayName", "shortDisplayName")
                .param("startDate", "startDate")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetAll_ThrowsIOException1() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/greetAll")
                .param("id", "0")
                .param("name", "name")
                .param("shortDisplayName", "shortDisplayName")
                .param("startDate", "startDate")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"))
    }
}