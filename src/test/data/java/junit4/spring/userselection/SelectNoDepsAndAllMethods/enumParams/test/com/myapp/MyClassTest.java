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
    public void testGetFoos1() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos1/{fooTypeParamName}", FooType.Normal)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetFoos2() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos2")
                        .param("fooTypeRequestParamName", "Normal")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetFoos3() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos3/{fooTypeParamName}", FooData2.FooType.Normal)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetFoos4() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos4")
                        .param("fooTypeRequestParamName", "Normal")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }
}
