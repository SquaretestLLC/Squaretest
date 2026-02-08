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

import java.time.LocalDateTime

import static org.mockito.Mockito.*
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
    void testGetFooById() {
        // Setup
        // Configure FooService.safeGetFooDataById(...).
        def fooData = new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockFooService.safeGetFooDataById(0L)).thenReturn(fooData)

        // Run the test and verify the results
        mockMvc.perform(get("/getFooById")
                .param("fooId", "0")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
        verify(mockFooService).saveFoo(
                new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                        LocalDateTime.of(2020, 1, 1, 0, 0, 0)))
    }

    @Test
    void testGetFooById_FooServiceSafeGetFooDataByIdReturnsNull() {
        // Setup
        when(mockFooService.safeGetFooDataById(0L)).thenReturn(null)

        // Run the test and verify the results
        mockMvc.perform(get("/getFooById")
                .param("fooId", "0")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
        verify(mockFooService).saveFoo(
                new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                        LocalDateTime.of(2020, 1, 1, 0, 0, 0)))
    }

    @Test
    void testGetFooByIdOpt() {
        // Setup
        // Configure FooService.getFooDataByIdOpt(...).
        def fooData = Optional.of(new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                LocalDateTime.of(2020, 1, 1, 0, 0, 0)))
        when(mockFooService.getFooDataByIdOpt(0L)).thenReturn(fooData)

        // Run the test and verify the results
        mockMvc.perform(get("/getFooByIdOpt")
                .param("fooId", "0")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
        verify(mockFooService).saveFoo(
                new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                        LocalDateTime.of(2020, 1, 1, 0, 0, 0)))
    }

    @Test
    void testGetFooByIdOpt_FooServiceGetFooDataByIdOptReturnsAbsent() {
        // Setup
        when(mockFooService.getFooDataByIdOpt(0L)).thenReturn(Optional.empty())

        // Run the test and verify the results
        mockMvc.perform(get("/getFooByIdOpt")
                .param("fooId", "0")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGetFooByIdOpt1() {
        // Setup
        // Configure FooService.getFooDataByIdOpt(...).
        def fooData = Optional.of(new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                LocalDateTime.of(2020, 1, 1, 0, 0, 0)))
        when(mockFooService.getFooDataByIdOpt(0L)).thenReturn(fooData)

        // Run the test and verify the results
        mockMvc.perform(get("/getFooByIdOpt1")
                .param("fooId", "0")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
        verify(mockFooService).saveFoo1(
                new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                        LocalDateTime.of(2020, 1, 1, 0, 0, 0)))
    }

    @Test
    void testGetFooByIdOpt1_FooServiceGetFooDataByIdOptReturnsAbsent() {
        // Setup
        when(mockFooService.getFooDataByIdOpt(0L)).thenReturn(Optional.empty())

        // Run the test and verify the results
        mockMvc.perform(get("/getFooByIdOpt1")
                .param("fooId", "0")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGetFooByIdOpt1_FooServiceSaveFoo1ThrowsRuntimeException() {
        // Setup
        // Configure FooService.getFooDataByIdOpt(...).
        def fooData = Optional.of(new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                LocalDateTime.of(2020, 1, 1, 0, 0, 0)))
        when(mockFooService.getFooDataByIdOpt(0L)).thenReturn(fooData)

        doThrow(RuntimeException.class).when(mockFooService).saveFoo1(
                new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                        LocalDateTime.of(2020, 1, 1, 0, 0, 0)))

        // Run the test and verify the results
        mockMvc.perform(get("/getFooByIdOpt1")
                .param("fooId", "0")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"))
    }
}
