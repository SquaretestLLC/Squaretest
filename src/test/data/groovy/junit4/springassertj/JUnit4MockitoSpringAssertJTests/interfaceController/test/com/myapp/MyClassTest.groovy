package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
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
    void testIndexWithNoPath() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths")
                .param("name", "theName")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testIndexWithSlashPath() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/")
                .param("name", "theName")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/greeting")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting1() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/greeting1")
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
        mockMvc.perform(get("/controllerWithPaths/authorizedGreeting")
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
        mockMvc.perform(get("/controllerWithPaths/authorizedGreeting1")
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
        mockMvc.perform(get("/controllerWithPaths/authorizedGreeting2")
                .param("username", "username")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithValueParam() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/greetingWithValue")
                .param("name", "theName")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithPathParam() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/greetingWithPath")
                .param("name", "theName")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithPathAndValue() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/greetingWithPathAndValue")
                .param("name", "theName")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithMultiplePaths() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/hola")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithPathSetToMultiplePaths() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/hola")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithValueSetToMultiplePaths() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/hola")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithConstant() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithMultipleConstant() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithPlaceholder() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/greet_path/other/stuff")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithPathVar() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/greetingForUser/{userId}", "userId")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetingWithPathVarRegex() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/greetingForUserRegex/{numericId:[\\d]+}", "userId")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testFindPet() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/controllerWithPaths/owners/{ownerId}/pets/{petId}", 0, 0)
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }
}
