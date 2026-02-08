package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import javax.servlet.http.Cookie

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
        mockMvc.perform(get("/greeting")
                .param("name", "theName")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting1() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/greeting1")
                .header("name", "theName")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting2() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/greeting2")
                .cookie(new Cookie("cookieName", "theName"))
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting3() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/greeting3")
                .sessionAttr("name", "theName")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting4() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/greeting4")
                .requestAttr("name", "theName")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testSubmitFormAsBody1() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/submitFormAsBody1")
                .param("id", "0")
                .param("name", "name")
                .param("shortDisplayName", "shortDisplayName")
                .param("startDate", "startDate")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testSubmitFormAsBodyJsonResponse() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/submitFormAsBodyJsonResponse")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testSubmitFormAsPojo() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/submitFormAsPojo")
                .param("id", "0")
                .param("name", "name")
                .param("shortDisplayName", "shortDisplayName")
                .param("startDate", "startDate")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testSubmitFormAsPojoWithCsrf() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/submitFormAsPojo")
                .param("id", "0")
                .param("name", "name")
                .param("shortDisplayName", "shortDisplayName")
                .param("startDate", "startDate")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testSubmitFormAsPojoWithCsrfAndJson() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/submitFormAsPojo")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testSubmitFormAsPojoJsonResponse() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/submitFormAsPojoJsonResponse")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testSubmitStringAsBody() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/submitStringAsBody")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testSubmitStringAsBody1() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/submitStringAsBody1")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testSubmitStringAsBody2() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/submitStringAsBody2")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testSubmitStringAsBody3() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/submitStringAsBody3")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testSubmitStringAsBody4() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/submitStringAsBody4")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testSubmitStringAsBody5() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/submitStringAsBody5")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testSubmitStringAsBodyWithSession() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/submitStringAsBodyWithSession")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .sessionAttr("previousValue", "previousValue")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testSubmitStringAsBodyWithRequestAttr() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/submitStringAsBodyWithRequestAttr")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .requestAttr("previousValue", "previousValue")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }
}
