package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner.class)
@WebMvcTest(MyClass.class)
@CompileStatic
class MyClassTest {

    @Autowired
    private MockMvc mockMvc

    @Test
    void testGetFooById() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/getFooById")
                .param("fooId", "0")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGetFooByIdOpt() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/getFooByIdOpt")
                .param("fooId", "0")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGetFooByIdOpt1() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/getFooByIdOpt1")
                .param("fooId", "0")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }
}
