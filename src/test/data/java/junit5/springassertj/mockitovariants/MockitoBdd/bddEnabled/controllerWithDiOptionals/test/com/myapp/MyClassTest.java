package com.myapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
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
    void testGetFooById() throws Exception {
        // Setup
        // Configure FooService.safeGetFooDataById(...).
        final FooData fooData = new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        given(mockFooService.safeGetFooDataById(0L)).willReturn(fooData);

        // Run the test and verify the results
        mockMvc.perform(get("/getFooById")
                        .param("fooId", "0")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
        then(mockFooService).should().saveFoo(
                new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                        LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
    }

    @Test
    void testGetFooById_FooServiceSafeGetFooDataByIdReturnsNull() throws Exception {
        // Setup
        given(mockFooService.safeGetFooDataById(0L)).willReturn(null);

        // Run the test and verify the results
        mockMvc.perform(get("/getFooById")
                        .param("fooId", "0")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
        then(mockFooService).should().saveFoo(
                new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                        LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
    }

    @Test
    void testGetFooByIdOpt() throws Exception {
        // Setup
        // Configure FooService.getFooDataByIdOpt(...).
        final Optional<FooData> fooData = Optional.of(
                new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                        LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        given(mockFooService.getFooDataByIdOpt(0L)).willReturn(fooData);

        // Run the test and verify the results
        mockMvc.perform(get("/getFooByIdOpt")
                        .param("fooId", "0")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
        then(mockFooService).should().saveFoo(
                new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                        LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
    }

    @Test
    void testGetFooByIdOpt_FooServiceGetFooDataByIdOptReturnsAbsent() throws Exception {
        // Setup
        given(mockFooService.getFooDataByIdOpt(0L)).willReturn(Optional.empty());

        // Run the test and verify the results
        mockMvc.perform(get("/getFooByIdOpt")
                        .param("fooId", "0")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGetFooByIdOpt1() throws Exception {
        // Setup
        // Configure FooService.getFooDataByIdOpt(...).
        final Optional<FooData> fooData = Optional.of(
                new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                        LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        given(mockFooService.getFooDataByIdOpt(0L)).willReturn(fooData);

        // Run the test and verify the results
        mockMvc.perform(get("/getFooByIdOpt1")
                        .param("fooId", "0")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
        then(mockFooService).should().saveFoo1(
                new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                        LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
    }

    @Test
    void testGetFooByIdOpt1_FooServiceGetFooDataByIdOptReturnsAbsent() throws Exception {
        // Setup
        given(mockFooService.getFooDataByIdOpt(0L)).willReturn(Optional.empty());

        // Run the test and verify the results
        mockMvc.perform(get("/getFooByIdOpt1")
                        .param("fooId", "0")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGetFooByIdOpt1_FooServiceSaveFoo1ThrowsRuntimeException() throws Exception {
        // Setup
        // Configure FooService.getFooDataByIdOpt(...).
        final Optional<FooData> fooData = Optional.of(
                new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                        LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        given(mockFooService.getFooDataByIdOpt(0L)).willReturn(fooData);

        willThrow(RuntimeException.class).given(mockFooService).saveFoo1(
                new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                        LocalDateTime.of(2020, 1, 1, 0, 0, 0)));

        // Run the test and verify the results
        mockMvc.perform(get("/getFooByIdOpt1")
                        .param("fooId", "0")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"));
    }
}
