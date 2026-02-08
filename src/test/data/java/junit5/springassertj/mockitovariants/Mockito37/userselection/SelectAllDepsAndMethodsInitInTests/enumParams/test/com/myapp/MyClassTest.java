package com.myapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    void testGetFoos1() throws Exception {
        // Setup
        // Configure FooService.getFoos1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        fooData1.setFooType(FooType.Normal);
        final List<FooData> fooData = Arrays.asList(fooData1);
        when(mockFooService.getFoos1(FooType.Normal)).thenReturn(fooData);

        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos1/{fooTypeParamName}", FooType.Normal)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetFoos1_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockFooService.getFoos1(FooType.Normal)).thenReturn(Collections.emptyList());

        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos1/{fooTypeParamName}", FooType.Normal)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true));
    }

    @Test
    void testGetFoos2() throws Exception {
        // Setup
        // Configure FooService.getFoos2(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        fooData1.setFooType(FooType.Normal);
        final List<FooData> fooData = Arrays.asList(fooData1);
        when(mockFooService.getFoos2(FooType.Normal)).thenReturn(fooData);

        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos2")
                        .param("fooTypeRequestParamName", "Normal")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetFoos2_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockFooService.getFoos2(FooType.Normal)).thenReturn(Collections.emptyList());

        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos2")
                        .param("fooTypeRequestParamName", "Normal")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true));
    }

    @Test
    void testGetFoos3() throws Exception {
        // Setup
        // Configure FooService.getFoos3(...).
        final FooData2 fooData2 = new FooData2();
        fooData2.setId("id");
        fooData2.setName("name");
        fooData2.setFooType(FooData2.FooType.Normal);
        final List<FooData2> fooData2s = Arrays.asList(fooData2);
        when(mockFooService.getFoos3(FooData2.FooType.Normal)).thenReturn(fooData2s);

        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos3/{fooTypeParamName}", FooData2.FooType.Normal)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetFoos3_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockFooService.getFoos3(FooData2.FooType.Normal)).thenReturn(Collections.emptyList());

        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos3/{fooTypeParamName}", FooData2.FooType.Normal)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true));
    }

    @Test
    void testGetFoos4() throws Exception {
        // Setup
        // Configure FooService.getFoos4(...).
        final FooData2 fooData2 = new FooData2();
        fooData2.setId("id");
        fooData2.setName("name");
        fooData2.setFooType(FooData2.FooType.Normal);
        final List<FooData2> fooData2s = Arrays.asList(fooData2);
        when(mockFooService.getFoos4(FooData2.FooType.Normal)).thenReturn(fooData2s);

        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos4")
                        .param("fooTypeRequestParamName", "Normal")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetFoos4_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockFooService.getFoos4(FooData2.FooType.Normal)).thenReturn(Collections.emptyList());

        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos4")
                        .param("fooTypeRequestParamName", "Normal")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true));
    }
}
