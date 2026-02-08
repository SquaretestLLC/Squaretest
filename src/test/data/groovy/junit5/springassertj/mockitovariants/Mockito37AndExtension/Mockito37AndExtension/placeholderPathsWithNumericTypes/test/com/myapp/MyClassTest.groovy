package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(MyClass.class)
@CompileStatic
class MyClassTest {

    @Autowired
    private MockMvc mockMvc

    @Test
    void testGreetingWithPath() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/companyName/greetings/greetingWithPath/{pathId}", 0)
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithPrimitivePath() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/companyName/greetings/greetingWithPrimitivePath/{pathId}", 0)
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithDoublePath() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/companyName/greetings/greetingWithDoublePath/{pathId}", 0)
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithFloatPath() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/companyName/greetings/greetingWithFloatPath/{pathId}", 0)
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/companyName/greetings/greeting")
                .param("name", "nameParam")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }
}
