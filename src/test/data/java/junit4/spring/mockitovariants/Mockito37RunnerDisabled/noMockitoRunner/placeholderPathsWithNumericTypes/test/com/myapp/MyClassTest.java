package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MyClass.class)
public class MyClassTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGreetingWithPath() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/companyName/greetings/greetingWithPath/{pathId}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    public void testGreetingWithPrimitivePath() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/companyName/greetings/greetingWithPrimitivePath/{pathId}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    public void testGreetingWithDoublePath() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/companyName/greetings/greetingWithDoublePath/{pathId}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    public void testGreetingWithFloatPath() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/companyName/greetings/greetingWithFloatPath/{pathId}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    public void testGreeting() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/companyName/greetings/greeting")
                        .param("name", "nameParam")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }
}
