package com.myapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyClass.class)
class MyClassTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BarService mockBarService;
    @MockBean
    private MetricsServiceAdapter mockMetricsServiceAdapter;

    @Test
    void testGreeting() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/greeting")
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
    void testGreeting1() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/greeting1")
                        .locale(Locale.US)
                        .param("barBeanFromModel", "barBeanFromModel")
                        .param("name", "name")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
        then(mockBarService).should().saveBar(any(BarBean.class));
        then(mockMetricsServiceAdapter).should().recordMetric("greeting1");
    }

    @Test
    void testGreeting2() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/greeting2")
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
        then(mockBarService).should().saveBar(any(BarBean.class));
        then(mockBarService).should().getBarDataById(0L);
        then(mockMetricsServiceAdapter).should().recordMetric("greeting1");
    }

    @Test
    void testGreeting2_BarServiceGetBarDataByIdThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        given(mockBarService.getBarDataById(0L)).willThrow(InvalidBarDataIdException.class);

        // Run the test and verify the results
        mockMvc.perform(post("/greeting2")
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
        then(mockBarService).should().saveBar(any(BarBean.class));
    }

    @Test
    void testGreetAll() throws Exception {
        // Setup
        // Configure BarService.getAllBars(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final List<BarBean> barBeans = Arrays.asList(barBean);
        given(mockBarService.getAllBars()).willReturn(barBeans);

        // Run the test and verify the results
        mockMvc.perform(post("/greetAll")
                        .param("id", "0")
                        .param("name", "name")
                        .param("shortDisplayName", "shortDisplayName")
                        .param("startDate", "startDate")
                        .param("name", "name")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
        then(mockMetricsServiceAdapter).should().recordMetric("greetAll");
    }

    @Test
    void testGreetAll_BarServiceReturnsNoItems() throws Exception {
        // Setup
        given(mockBarService.getAllBars()).willReturn(Collections.emptyList());

        // Run the test and verify the results
        mockMvc.perform(post("/greetAll")
                        .param("id", "0")
                        .param("name", "name")
                        .param("shortDisplayName", "shortDisplayName")
                        .param("startDate", "startDate")
                        .param("name", "name")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
        then(mockMetricsServiceAdapter).should().recordMetric("greetAll");
    }

    @Test
    void testGreetAll_BarServiceThrowsIOException() throws Exception {
        // Setup
        given(mockBarService.getAllBars()).willThrow(IOException.class);

        // Run the test and verify the results
        mockMvc.perform(post("/greetAll")
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
