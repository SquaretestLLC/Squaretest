package com.myapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyClass.class)
class MyClassTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FooService mockFooService;

    @Test
    void testGetNextEvent1() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/getNextEvent1/{pathDateName}", "2020-01-01")
                        .param("requestVarDateName", "2020-01-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetNextEvent2() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/getNextEvent2/{pathDateName}", "2020-01-01")
                        .param("requestVarDateName", "2020-01-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetNextEvent3() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/getNextEvent3/{pathDateName}", "2020-01-01")
                        .param("requestVarDateName", "2020-01-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetNextEvent4() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/getNextEvent4/{pathDate}", "2020-01-01")
                        .param("requestVarDate", "2020-01-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetNextEvent5() throws Exception {
        // Setup
        when(mockFooService.getTheDate("2020-01-01", "2020-01-01")).thenReturn("payload");

        // Run the test and verify the results
        mockMvc.perform(get("/getNextEvent5/{pathDate}", "2020-01-01")
                        .param("requestVarDate", "2020-01-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetNextEvent6() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/getNextEvent6/{pathDateName}", "2020-01-01")
                        .param("requestVarDateName", "2020-01-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }
}
