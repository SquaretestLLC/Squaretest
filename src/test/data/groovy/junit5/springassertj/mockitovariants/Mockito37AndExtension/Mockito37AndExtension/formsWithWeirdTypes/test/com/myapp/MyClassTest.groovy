package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(MyClass.class)
@CompileStatic
class MyClassTest {

    @Autowired
    private MockMvc mockMvc

    @Test
    void testSubmitFormWithNestedTypes() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(put("/submitFormWithNestedTypes")
                .param("names", "value1", "value2")
                .param("maps", "value1", "value2")
                .param("primaryMap", "primaryMap")
                .param("stringArr", "value1", "value2")
                .param("stringMatrix", "value1", "value2")
                .param("stringCube", "value1", "value2")
                .param("missingForm", "missingForm")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }
}
