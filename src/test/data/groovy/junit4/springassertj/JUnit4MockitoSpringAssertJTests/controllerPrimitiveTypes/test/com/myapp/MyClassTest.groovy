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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
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
    void testGreeting() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/greetingWithPrimitiveParams")
                .param("name", "theName")
                .param("longId", "0")
                .param("intId", "0")
                .param("floatId", "0")
                .param("doubleId", "0")
                .param("char", "0")
                .param("byte", "0")
                .param("numberId", "0")
                .param("bigDouble", "0")
                .param("characterId", "0")
                .param("isVal", "false")
                .param("isBigVal", "false")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testSubmitFormWithPrimitives() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/submitFormWithPrimitives")
                .param("theName", "theName")
                .param("longId", "0")
                .param("intId", "0")
                .param("floatId", "0")
                .param("doubleId", "0")
                .param("charId", "0")
                .param("byteId", "0")
                .param("numberId", "0")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testSubmitFormWithPrimitives2() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/submitFormWithPrimitives2")
                .param("characterId", "0")
                .param("isVal", "false")
                .param("isBigVal", "false")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }
}
