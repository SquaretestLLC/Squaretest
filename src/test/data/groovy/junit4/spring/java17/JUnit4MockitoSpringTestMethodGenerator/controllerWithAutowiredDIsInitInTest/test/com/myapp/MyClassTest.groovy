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

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner.class)
@WebMvcTest(MyClass.class)
@CompileStatic
class MyClassTest {

    @Autowired
    private MockMvc mockMvc

    @MockBean
    private BarService mockBarService
    @MockBean
    private MetricsServiceAdapter mockMetricsServiceAdapter

    @Test
    void testGreeting3() {
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
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting11() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/greeting1")
                .locale(Locale.US)
                .param("barBeanFromModel", "barBeanFromModel")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
        verify(mockBarService).saveBar(any(BarBean.class))
        verify(mockMetricsServiceAdapter).recordMetric("greeting1")
    }

    @Test
    void testGreeting21() {
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
                .andExpect(content().string("expectedResponse"))
        verify(mockBarService).saveBar(any(BarBean.class))
        verify(mockBarService).getBarDataById(0L)
        verify(mockMetricsServiceAdapter).recordMetric("greeting1")
    }

    @Test
    void testGreeting2_BarServiceGetBarDataByIdThrowsInvalidBarDataIdException1() {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class)

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
                .andExpect(content().string("expectedResponse"))
        verify(mockBarService).saveBar(any(BarBean.class))
    }

    @Test
    void testGreetAll1() {
        // Setup
        // Configure BarService.getAllBars(...).
        def barBean = new BarBean()
        barBean.setId(0L)
        barBean.setName("name")
        barBean.setShortDisplayName("shortDisplayName")
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        def barBeans = [barBean]
        when(mockBarService.getAllBars()).thenReturn(barBeans)

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
                .andExpect(content().string("expectedResponse"))
        verify(mockMetricsServiceAdapter).recordMetric("greetAll")
    }

    @Test
    void testGreetAll_BarServiceReturnsNoItems1() {
        // Setup
        when(mockBarService.getAllBars()).thenReturn([])

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
                .andExpect(content().string("expectedResponse"))
        verify(mockMetricsServiceAdapter).recordMetric("greetAll")
    }

    @Test
    void testGreetAll_BarServiceThrowsIOException1() {
        // Setup
        when(mockBarService.getAllBars()).thenThrow(IOException.class)

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
                .andExpect(content().string("expectedResponse"))
    }
}