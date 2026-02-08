package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner.class)
@WebMvcTest(MyClass.class)
@CompileStatic
class MyClassTest {

    @Autowired
    private MockMvc mockMvc

    @MockBean
    private FooService mockFooService

    @Test
    void testGetFoos1() {
        // Setup
        // Configure FooService.getFoos1(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        fooData1.setFooType(FooType.Normal)
        def fooData = [fooData1]
        when(mockFooService.getFoos1(FooType.Normal)).thenReturn(fooData)

        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos1/{fooTypeParamName}", FooType.Normal)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetFoos1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1(FooType.Normal)).thenReturn([])

        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos1/{fooTypeParamName}", FooType.Normal)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true))
    }

    @Test
    void testGetFoos2() {
        // Setup
        // Configure FooService.getFoos2(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        fooData1.setFooType(FooType.Normal)
        def fooData = [fooData1]
        when(mockFooService.getFoos2(FooType.Normal)).thenReturn(fooData)

        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos2")
                .param("fooTypeRequestParamName", "Normal")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetFoos2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2(FooType.Normal)).thenReturn([])

        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos2")
                .param("fooTypeRequestParamName", "Normal")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true))
    }

    @Test
    void testGetFoos3() {
        // Setup
        // Configure FooService.getFoos3(...).
        def fooData2 = new FooData2()
        fooData2.setId("id")
        fooData2.setName("name")
        fooData2.setFooType(FooData2.FooType.Normal)
        def fooData2s = [fooData2]
        when(mockFooService.getFoos3(FooData2.FooType.Normal)).thenReturn(fooData2s)

        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos3/{fooTypeParamName}", FooData2.FooType.Normal)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetFoos3_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos3(FooData2.FooType.Normal)).thenReturn([])

        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos3/{fooTypeParamName}", FooData2.FooType.Normal)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true))
    }

    @Test
    void testGetFoos4() {
        // Setup
        // Configure FooService.getFoos4(...).
        def fooData2 = new FooData2()
        fooData2.setId("id")
        fooData2.setName("name")
        fooData2.setFooType(FooData2.FooType.Normal)
        def fooData2s = [fooData2]
        when(mockFooService.getFoos4(FooData2.FooType.Normal)).thenReturn(fooData2s)

        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos4")
                .param("fooTypeRequestParamName", "Normal")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetFoos4_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos4(FooData2.FooType.Normal)).thenReturn([])

        // Run the test and verify the results
        mockMvc.perform(get("/searchFoos4")
                .param("fooTypeRequestParamName", "Normal")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true))
    }
}
