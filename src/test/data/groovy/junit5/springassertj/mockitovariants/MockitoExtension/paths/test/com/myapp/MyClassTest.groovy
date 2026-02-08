package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
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
    void testIndexWithNoPath() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/")
                .param("name", "theName")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testIndexWithSlashPath() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/")
                .param("name", "theName")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/greeting")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting1() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/greeting1")
                .locale(Locale.US)
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    @WithMockUser("username")
    void testAuthorizedGreeting() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/authorizedGreeting")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    @WithMockUser("username")
    void testAuthorizedGreeting1() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/authorizedGreeting1")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    @WithMockUser("username")
    void testAuthorizedGreeting2() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/authorizedGreeting2")
                .param("username", "username")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithValueParam() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/greetingWithValue")
                .param("name", "theName")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithPathParam() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/greetingWithPath")
                .param("name", "theName")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithPathAndValue() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/greetingWithPathAndValue")
                .param("name", "theName")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithMultiplePaths() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/hola")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithPathSetToMultiplePaths() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/hola")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithValueSetToMultiplePaths() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/hola")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithConstant() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithMultipleConstant() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithPlaceholder() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/greet_path/other/stuff")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithPathVar() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/greetingForUser/{userId}", "userId")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithPathVarRegex() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/greetingForUserRegex/{numericId:[\\d]+}", "userId")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testFindPet() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/owners/{ownerId}/pets/{petId}", 0, 0)
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }
}
