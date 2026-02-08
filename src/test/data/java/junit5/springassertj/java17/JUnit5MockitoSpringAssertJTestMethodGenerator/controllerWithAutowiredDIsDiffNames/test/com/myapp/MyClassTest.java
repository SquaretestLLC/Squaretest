package com.myapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyClass.class)
class MyClassTest {

    @Autowired
    private MockMvc theMockMvc;

    @MockBean
    private BarService theMockBarService;
    @MockBean
    private MetricsServiceAdapter theMockMetricsServiceAdapter;

    @Test
    void testGreeting3() throws Exception {
        // Setup
        // Run the test and verify the results
        theMockMvc.perform(post("/greeting")
                        .param("id", "0")
                        .param("name", "name")
                        .param("shortDisplayName", "shortDisplayName")
                        .param("startDate", "startDate")
                        .param("name", "name")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreeting11() throws Exception {
        // Setup
        // Run the test and verify the results
        theMockMvc.perform(post("/greeting1")
                        .locale(Locale.US)
                        .param("barBeanFromModel", "barBeanFromModel")
                        .param("name", "name")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
        verify(theMockBarService).saveBar(any(BarBean.class));
        verify(theMockMetricsServiceAdapter).recordMetric("greeting1");
    }

    @Test
    void testGreeting21() throws Exception {
        // Setup
        // Run the test and verify the results
        theMockMvc.perform(post("/greeting2")
                        .locale(Locale.US)
                        .param("id", "0")
                        .param("name", "name")
                        .param("shortDisplayName", "shortDisplayName")
                        .param("startDate", "startDate")
                        .param("name", "name")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
        verify(theMockBarService).saveBar(any(BarBean.class));
        verify(theMockBarService).getBarDataById(0L);
        verify(theMockMetricsServiceAdapter).recordMetric("greeting1");
    }

    @Test
    void testGreeting2_BarServiceGetBarDataByIdThrowsInvalidBarDataIdException1() throws Exception {
        // Setup
        when(theMockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test and verify the results
        theMockMvc.perform(post("/greeting2")
                        .locale(Locale.US)
                        .param("id", "0")
                        .param("name", "name")
                        .param("shortDisplayName", "shortDisplayName")
                        .param("startDate", "startDate")
                        .param("name", "name")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"));
        verify(theMockBarService).saveBar(any(BarBean.class));
    }

    @Test
    void testGreetAll1() throws Exception {
        // Setup
        // Configure BarService.getAllBars(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final List<BarBean> barBeans = List.of(barBean);
        when(theMockBarService.getAllBars()).thenReturn(barBeans);

        // Run the test and verify the results
        theMockMvc.perform(post("/greetAll")
                        .param("id", "0")
                        .param("name", "name")
                        .param("shortDisplayName", "shortDisplayName")
                        .param("startDate", "startDate")
                        .param("name", "name")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
        verify(theMockMetricsServiceAdapter).recordMetric("greetAll");
    }

    @Test
    void testGreetAll_BarServiceReturnsNoItems1() throws Exception {
        // Setup
        when(theMockBarService.getAllBars()).thenReturn(Collections.emptyList());

        // Run the test and verify the results
        theMockMvc.perform(post("/greetAll")
                        .param("id", "0")
                        .param("name", "name")
                        .param("shortDisplayName", "shortDisplayName")
                        .param("startDate", "startDate")
                        .param("name", "name")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
        verify(theMockMetricsServiceAdapter).recordMetric("greetAll");
    }

    @Test
    void testGreetAll_BarServiceThrowsIOException1() throws Exception {
        // Setup
        when(theMockBarService.getAllBars()).thenThrow(IOException.class);

        // Run the test and verify the results
        theMockMvc.perform(post("/greetAll")
                        .param("id", "0")
                        .param("name", "name")
                        .param("shortDisplayName", "shortDisplayName")
                        .param("startDate", "startDate")
                        .param("name", "name")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"));
    }
}