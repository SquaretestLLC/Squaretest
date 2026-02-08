package com.myapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyClass.class)
class MyClassTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testIndexWithNoPath() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths")
                        .param("name", "theName")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testIndexWithSlashPath() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/")
                        .param("name", "theName")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreeting() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/greeting")
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreeting1() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/greeting1")
                        .locale(Locale.US)
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    @WithMockUser("username")
    void testAuthorizedGreeting() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/authorizedGreeting")
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    @WithMockUser("username")
    void testAuthorizedGreeting1() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/authorizedGreeting1")
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    @WithMockUser("username")
    void testAuthorizedGreeting2() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/authorizedGreeting2")
                        .param("username", "username")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreetingWithValueParam() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/greetingWithValue")
                        .param("name", "theName")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreetingWithPathParam() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/greetingWithPath")
                        .param("name", "theName")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreetingWithPathAndValue() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/greetingWithPathAndValue")
                        .param("name", "theName")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreetingWithMultiplePaths() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/hola")
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreetingWithPathSetToMultiplePaths() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/hola")
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreetingWithValueSetToMultiplePaths() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/hola")
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreetingWithConstant() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths")
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreetingWithMultipleConstant() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths")
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreetingWithPlaceholder() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/greet_path/other/stuff")
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreetingWithPathVar() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/greetingForUser/{userId}", "userId")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreetingWithPathVarRegex() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/greetingForUserRegex/{numericId:[\\d]+}", "userId")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testFindPet() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/owners/{ownerId}/pets/{petId}", 0, 0)
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }
}
