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
    void testGetFoos1() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos1/{fooTypeParamName}", FooType.Normal)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetFoos2() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos2")
                .param("fooTypeRequestParamName", "Normal")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetFoos3() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos3/{fooTypeParamName}", FooData2.FooType.Normal)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetFoos4() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos4")
                .param("fooTypeRequestParamName", "Normal")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }
}
