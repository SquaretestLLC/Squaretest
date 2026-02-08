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
import static org.mockito.BDDMockito.given
import static org.mockito.BDDMockito.then
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
    void testGreeting() {
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
    void testGreeting1() {
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
        then(mockBarService).should().saveBar(any(BarBean.class))
        then(mockMetricsServiceAdapter).should().recordMetric("greeting1")
    }

    @Test
    void testGreeting2() {
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
        then(mockBarService).should().saveBar(any(BarBean.class))
        then(mockBarService).should().getBarDataById(0L)
        then(mockMetricsServiceAdapter).should().recordMetric("greeting1")
    }

    @Test
    void testGreeting2_BarServiceGetBarDataByIdThrowsInvalidBarDataIdException() {
        // Setup
        given(mockBarService.getBarDataById(0L)).willThrow(InvalidBarDataIdException.class)

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
        then(mockBarService).should().saveBar(any(BarBean.class))
    }

    @Test
    void testGreetAll() {
        // Setup
        // Configure BarService.getAllBars(...).
        def barBean = new BarBean()
        barBean.setId(0L)
        barBean.setName("name")
        barBean.setShortDisplayName("shortDisplayName")
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        def barBeans = [barBean]
        given(mockBarService.getAllBars()).willReturn(barBeans)

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
        then(mockMetricsServiceAdapter).should().recordMetric("greetAll")
    }

    @Test
    void testGreetAll_BarServiceReturnsNoItems() {
        // Setup
        given(mockBarService.getAllBars()).willReturn([])

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
        then(mockMetricsServiceAdapter).should().recordMetric("greetAll")
    }

    @Test
    void testGreetAll_BarServiceThrowsIOException() {
        // Setup
        given(mockBarService.getAllBars()).willThrow(IOException.class)

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
